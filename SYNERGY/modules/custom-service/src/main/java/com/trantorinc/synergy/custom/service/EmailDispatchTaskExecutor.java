package com.trantorinc.synergy.custom.service;

import com.liferay.dispatch.executor.BaseDispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutorOutput;
import com.liferay.dispatch.model.DispatchTrigger;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.trantorinc.synergy.common.service.drive.DriveService;
import com.trantorinc.synergy.email.core.model.Email;
import com.trantorinc.synergy.email.core.model.EmailAttachment;
import com.trantorinc.synergy.email.core.service.EmailAttachmentLocalServiceUtil;
import com.trantorinc.synergy.email.core.service.EmailLocalServiceUtil;
import com.trantorinc.synergy.custom.service.dto.MailDto;
import org.osgi.service.component.annotations.Component;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;

@Component(
        property = {
                "dispatch.task.executor.name=emailTask", "dispatch.task.executor.type=emailTask"
        },
        service = DispatchTaskExecutor.class
)
public class EmailDispatchTaskExecutor extends BaseDispatchTaskExecutor {

    private static final Log log = LogFactoryUtil.getLog(EmailDispatchTaskExecutor.class);
    @Override
    public void doExecute(DispatchTrigger dispatchTrigger, DispatchTaskExecutorOutput dispatchTaskExecutorOutput) {
        Email toSendEmail = EmailLocalServiceUtil.findNextEmail(!ENVIRONMENT.equalsIgnoreCase(PROD));
        if(null != toSendEmail){
            log.info("**** Sending "+toSendEmail.getModule()+" email to "+toSendEmail.getToAddress()+" ****");
            MailDto mailDto = new MailDto();
            mailDto.setSubject(toSendEmail.getSubject());
            mailDto.setBody(toSendEmail.getBody());
            mailDto.setToAddress(getCleanAddresses(toSendEmail.getToAddress()));
            mailDto.setCcAddress(getCleanAddresses(toSendEmail.getCcAddress()));
            mailDto.setBccAddress(getCleanAddresses(toSendEmail.getBccAddress()));
            List<EmailAttachment> emailAttachments = EmailAttachmentLocalServiceUtil.findEmailAttachmentByEmailId(toSendEmail.getEmailId());
            if(!emailAttachments.isEmpty()){
                List<File> files = new ArrayList<>();
                Set<String> fileIds = emailAttachments.stream().map(EmailAttachment::getAttachmentFileId).collect(Collectors.toSet());
                for(String fileId : fileIds){
                    EmailAttachment emailAttachment = emailAttachments.stream().filter(e -> e.getAttachmentFileId().equalsIgnoreCase(fileId)).collect(Collectors.toList()).get(0);
                    String[] fileNameArray = emailAttachment.getAttachmentName().split("\\.");
                    File file =DriveService.getFile(fileId,fileNameArray[0],'.' + fileNameArray[1]);
                    if(null != file) {
                        files.add(file);
                    }
                }
                mailDto.setAttachments(files);
            }
            MailService.sendMail(mailDto);

            toSendEmail.setSent(ENVIRONMENT.equalsIgnoreCase(PROD));
            EmailLocalServiceUtil.updateEmail(toSendEmail);
            log.info("**** Email sent to "+toSendEmail.getToAddress()+" ****");
        }
    }

    @Override
    public String getName() {
        return "emailTask";
    }


    private List<String> getCleanAddresses(String toAddress) {
        List<String> finalList = null;
        if(toAddress != null){
            Set<String> emailAddresses = Arrays.stream(toAddress.split(COMMA)).map(String::trim).collect(Collectors.toSet());
            emailAddresses.remove(BLANK);
            Set<String> emailAddressesNoNumber = new HashSet<>();
            for(String s : emailAddresses){
                String emailNoDomain = s.split("@")[0];
                try{
                    Integer.parseInt(emailNoDomain);
                }catch(NumberFormatException ex){
                    emailAddressesNoNumber.add(s);
                }
            }
            if(!emailAddressesNoNumber.isEmpty()){
                finalList = new ArrayList<>(emailAddressesNoNumber);
            }
        }
        return  finalList;
    }

}
