package com.trantorinc.synergy.custom.service;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.trantorinc.synergy.custom.service.constants.SynergyServicePortletKeys;
import com.trantorinc.synergy.custom.service.dto.MailDto;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.File;
import java.io.UnsupportedEncodingException;


public class MailService {


	private MailService(){
		//default constructor
	}

	public static void sendMail(MailDto mailDto)
	{
		final Log log = LogFactoryUtil.getLog(MailService.class.getName());
		try {
			MailMessage mailMessage = new MailMessage();

			if(null != mailDto.getToAddress() && !mailDto.getToAddress().isEmpty()) {
				InternetAddress[] allToAddress = new InternetAddress[mailDto.getToAddress().size()];
				for(int count = 0; count < mailDto.getToAddress().size(); count++){
					allToAddress[count] = new InternetAddress(mailDto.getToAddress().get(count));
				}
				mailMessage.setTo(allToAddress);
			}

			if(null != mailDto.getCcAddress() && !mailDto.getCcAddress().isEmpty()) {
				InternetAddress[] allCcAddress = new InternetAddress[mailDto.getCcAddress().size()];
				for(int count = 0; count < mailDto.getCcAddress().size(); count++){
					allCcAddress[count] = new InternetAddress(mailDto.getCcAddress().get(count));
				}
				mailMessage.setCC(allCcAddress);
			}

			if(null != mailDto.getBccAddress() && !mailDto.getBccAddress().isEmpty()) {
				InternetAddress[] allBccAddress = new InternetAddress[mailDto.getBccAddress().size()];
				for(int count = 0; count < mailDto.getBccAddress().size(); count++){
					allBccAddress[count] = new InternetAddress(mailDto.getBccAddress().get(count));
				}
				mailMessage.setBCC(allBccAddress);
			}

			mailMessage.setFrom(new InternetAddress(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS, SynergyServicePortletKeys.EMAIL_FROM_NAME));
			mailMessage.setSubject(mailDto.getSubject());
			mailMessage.setBody(mailDto.getBody());
			mailMessage.setHTMLFormat(true);
			if(null != mailDto.getAttachments() && !mailDto.getAttachments().isEmpty()){
				for(File attachment : mailDto.getAttachments()){
					mailMessage.addFileAttachment(attachment);
				}
				log.info("**** Email attachments added for "+mailDto.getToAddress()+" ****");
			}
			MailServiceUtil.sendEmail(mailMessage);
		}   catch (AddressException | UnsupportedEncodingException exception) {
			log.error(exception.getStackTrace()[0].getMethodName()+":" +exception.getStackTrace()[0].getLineNumber()+":"+ exception.getMessage());
		}
	}

}
