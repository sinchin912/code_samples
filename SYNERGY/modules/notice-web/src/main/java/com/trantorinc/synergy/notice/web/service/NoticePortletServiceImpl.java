package com.trantorinc.synergy.notice.web.service;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.trantorinc.synergy.email.core.model.Email;
import com.trantorinc.synergy.email.core.service.EmailLocalServiceUtil;
import com.trantorinc.synergy.lms.core.model.Employee;
import com.trantorinc.synergy.lms.core.model.Holiday;
import com.trantorinc.synergy.lms.core.service.EmployeeLocalServiceUtil;
import com.trantorinc.synergy.lms.core.service.HolidayLocalServiceUtil;
import com.trantorinc.synergy.notice.core.model.ExitForm;
import com.trantorinc.synergy.notice.core.model.ExitState;
import com.trantorinc.synergy.notice.core.model.QuestionnaireForm;
import com.trantorinc.synergy.notice.core.model.Resignation;
import com.trantorinc.synergy.notice.core.service.ExitFormLocalServiceUtil;
import com.trantorinc.synergy.notice.core.service.ExitStateLocalServiceUtil;
import com.trantorinc.synergy.notice.core.service.QuestionnaireFormLocalServiceUtil;
import com.trantorinc.synergy.notice.core.service.ResignationLocalServiceUtil;
import com.trantorinc.synergy.notice.web.dto.EmployeeDto;
import com.trantorinc.synergy.notice.web.dto.QuestionnaireFormDataDTO;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;
import static com.trantorinc.synergy.common.service.constant.AppConstantService.NO;
import static com.trantorinc.synergy.common.service.util.UtilService.*;
import static com.trantorinc.synergy.common.service.util.UtilService.convertDateToLocalDateTime;
import static com.trantorinc.synergy.notice.web.constants.NoticeWebPortletKeys.*;

public class NoticePortletServiceImpl implements NoticePortletService{

    @Override
    public void findAllDataList(RenderRequest request, RenderResponse response) {
        List<Employee> employeeDataList = EmployeeLocalServiceUtil.findAllActiveEmployees();
        Employee employeeData = EmployeeLocalServiceUtil.findByEmail(getLoggedUser(request));
        List<Resignation> resignEntryList = ResignationLocalServiceUtil.findResignationByEcode(employeeData.getEcode());
        List<ExitState> statusMap=	ExitStateLocalServiceUtil.getExitStates(-1,-1);
        List<EmployeeDto> entryList = new ArrayList<>();
        int initiateResignationButton=1;
        for (Resignation entry : resignEntryList) {
            EmployeeDto dto = new EmployeeDto();
            dto.setResignationId(entry.getId());
            dto.setStatus(statusMap.get(entry.getStatus()).getExitStateDisplay());
            if(entry.getStatus()!=6){
                initiateResignationButton=0;
                List<QuestionnaireForm> questionnaireForm =  QuestionnaireFormLocalServiceUtil.findQuestionnaireFormByResignationId(entry.getId());
                List<ExitForm> exitFormData =  ExitFormLocalServiceUtil.findExitFormByResignationId(entry.getId());
                if(!questionnaireForm.isEmpty()){
                    dto.setQuestionnaireFormGenerated(entry.getStatus() !=1);
                    dto.setQuestionnaireFormSubmitted(null!=questionnaireForm.get(0).getSubmittedDate());
                }
                if(!exitFormData.isEmpty() && exitFormData.get(0).getHrFormStatus() && entry.getStatus()!=6)
                {
                    dto.setStatus(CLEARANCES_COMPLETED);
                }
            }
            dto.setCreateDate(convertDateToLocalDateTime(entry.getCreationDate()).format(FORMATTER_YYYY_MM_DD));

            dto.setEmpStatus(entry.getStatus());
            dto.setActionMode((entry.getStatus() == statusMap.get(0).getExitStateKey() ||  entry.getStatus() == statusMap.get(1).getExitStateKey() || entry.getStatus() == statusMap.get(2).getExitStateKey() || (entry.getStatus() == statusMap.get(5).getExitStateKey() && entry.getMobile().isEmpty())) && entry.getWithdrawCount() != 2);
            List<Employee> managerDataResignation = employeeDataList.stream().filter(s ->s.getEmail().equalsIgnoreCase(entry.getManagerEmail())).collect(Collectors.toList());

            if(!managerDataResignation.isEmpty()){
                dto.setManagerName(managerDataResignation.get(0).getName());
            }
            dto.setAccount(entry.getAccount());
            entryList.add(dto);
        }
        LocalDate nowDate = LocalDate.now();
        List<Holiday> holidayList= HolidayLocalServiceUtil.findHolidaysByYear(nowDate.getYear());
        List<String> holidayDate = holidayList.stream().map(Holiday::getOnDate).map(d->convertDateToLocalDateTime(d).format(FORMATTER_YYYY_MM_DD)).collect(Collectors.toList());
        if((initiateResignationButton != 0) && (nowDate.getDayOfWeek().getValue() == 6 || nowDate.getDayOfWeek().getValue() == 7 || holidayDate.contains(nowDate.format(FORMATTER_YYYY_MM_DD))))
            initiateResignationButton = 2;
        request.setAttribute("statusMap", statusMap);
        request.setAttribute("initiateResignation", initiateResignationButton);
        request.setAttribute("empResignList", entryList);

    }

    @Override
    public void submitResignForm(ActionRequest request) {
        ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        List<ExitState> statusMap=	ExitStateLocalServiceUtil.getExitStates(-1,-1);
        User loginedUser = td.getUser();
        long resignationId = ParamUtil.getLong(request, RESIGNATION_ID);
        String email = ParamUtil.getString(request, "email");
        String reason = ParamUtil.getString(request, "reason");
        String employeeComment = ParamUtil.getString(request, "employeeComment");
        String stateName = ParamUtil.getString(request, "state");
        String cityName = ParamUtil.getString(request, "city");
        String pincode = ParamUtil.getString(request, "pincode");
        String postalAddress = ParamUtil.getString(request, "postalAddress");
        String mobileNo = ParamUtil.getString(request, "mobileNo");
        Employee employeeData = EmployeeLocalServiceUtil.findByEmail(loginedUser.getEmailAddress());
        Employee managerData = EmployeeLocalServiceUtil.fetchEmployee(employeeData.getManager());
        boolean alreadyDataExist=false;
        Date now = getIstDate();
        List<Resignation> resignation= ResignationLocalServiceUtil.findResignationByEcode(employeeData.getEcode());
        if(!resignation.isEmpty())
        {
            for(Resignation entry : resignation){
                if (entry.getStatus()!=6)
                {
                    alreadyDataExist=true;
                }
            }
        }
        if (resignationId==0 ) {
            if(!alreadyDataExist) {
                Resignation resign = ResignationLocalServiceUtil.createResignation(CounterLocalServiceUtil.increment());
                resign.setEcode(employeeData.getEcode());
                resign.setAccount(employeeData.getAccount());
                resign.setManagerEmail(managerData.getEmail());
                resign.setAssigneeEmail(managerData.getEmail());
                resign.setAssigneeTo(false);
                resign.setAlternateEmail(email);
                resign.setReason(reason);
                resign.setCreationDate(now);
                resign.setInitiatedDate(now);
                resign.setStatus(0);
                resign.setStateName(stateName);
                resign.setCityName(cityName);
                resign.setMobile(mobileNo);
                resign.setPincode(pincode);
                resign.setPostalAddress(postalAddress);
                resign.setEmployeeComment(employeeComment);
                ResignationLocalServiceUtil.addResignation(resign);

                Email emailer = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                emailer.setSubject(MessageFormat.format(INITIATE_RESIGNATION_EMAIL_SUBJECT, employeeData.getEcode(), employeeData.getName()));
                emailer.setBody(MessageFormat.format(INITIATE_RESIGNATION_EMAIL_BODY, employeeData.getName(), employeeData.getEcode(), employeeData.getAccount(), convertDateToLocalDateTime(now).format(FORMATTER_YYYY_MM_DD), reason, getPortalUrl()+URL_EXIT_ADMIN));
                emailer.setToAddress(DL_HR);
                emailer.setCreatedDate(getIstDate());
                emailer.setModule(MODULE_EXIT);
                emailer.setScheduled(false);
                emailer.setSent(false);
                EmailLocalServiceUtil.addEmail(emailer);
            }
        } else {
            String   withdrawReason = ParamUtil.getString(request, "withdrawReason");
            Resignation resign = ResignationLocalServiceUtil.fetchResignation(resignationId);
            if(!withdrawReason.isEmpty()) {
                resign.setEmpWithdrawComment(withdrawReason);
                resign.setWithdrawInitiatedDate(now);
                resign.setStatus(statusMap.get(3).getExitStateKey());
                if(resign.getWithdrawCount() == 1){
                    resign.setWithdrawCount(2);
                }
                else {
                    resign.setWithdrawCount(1);
                }
                String managerEmail=resign.getAssigneeTo() ? resign.getManagerEmail()+COMMA+resign.getAssigneeEmail():resign.getManagerEmail();

                Email emailer = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
                emailer.setSubject(MessageFormat.format(WITHDRAW_INITIATED_EMAIL_SUBJECT,employeeData.getEcode(),employeeData.getName()));
                emailer.setBody(MessageFormat.format(WITHDRAW_INITIATED_EMAIL_BODY,employeeData.getName(),employeeData.getEcode(),getPortalUrl()+URL_EXIT_ADMIN));
                emailer.setCcAddress(getLoggedUser(request)+COMMA+managerEmail);
                emailer.setToAddress(DL_HR);
                emailer.setModule(MODULE_EXIT);
                emailer.setScheduled(false);
                emailer.setSent(false);
                emailer.setCreatedDate(getIstDate());
                EmailLocalServiceUtil.addEmail(emailer);
            }
            else if(resign.getStatus()==statusMap.get(5).getExitStateKey()) {
                resign.setStateName(stateName);
                resign.setCityName(cityName);
                resign.setPincode(pincode);
                resign.setMobile(mobileNo);
                resign.setPostalAddress(postalAddress);
                resign.setAlternateEmail(email);
            }
            ResignationLocalServiceUtil.updateResignation(resign);
        }
    }

    @Override
    public void submitQuestionnaireForm(ActionRequest request) {
        List<QuestionnaireForm> questionnaireFormData = QuestionnaireFormLocalServiceUtil.findQuestionnaireFormByResignationId(ParamUtil.getLong(request, RESIGNATION_ID));
        Employee employeeData = EmployeeLocalServiceUtil.findByEmail(getLoggedUser(request));
        Employee managerData = EmployeeLocalServiceUtil.fetchEmployee(employeeData.getManager());
        List<Resignation> resignedEntry = ResignationLocalServiceUtil.findResignationByEcode(employeeData.getEcode());
        Date now = getIstDate();
        if(!questionnaireFormData.isEmpty() && !resignedEntry.isEmpty()) {
            QuestionnaireForm questionnaireForm=questionnaireFormData.get(0);
            questionnaireForm.setWorkExperience(ParamUtil.getString(request, "workExperience"));
            questionnaireForm.setCommunicationWithEmployees(ParamUtil.getString(request, "communicationWithEmployees"));
            questionnaireForm.setTrainingOpportunity(ParamUtil.getString(request, "trainingOpportunity"));
            questionnaireForm.setDealingWithStaff(ParamUtil.getString(request, "dealingWithStaff"));
            questionnaireForm.setSatisfactionLevel(ParamUtil.getString(request, "satisfactionLevel"));
            questionnaireForm.setReasonForLeaving(ParamUtil.getString(request, "reasonForLeaving"));
            questionnaireForm.setReasonForLeavingDescribe(ParamUtil.getString(request, "reasonForLeavingDescribe"));
            questionnaireForm.setReasonForJoining(ParamUtil.getString(request, "reasonForJoining"));
            questionnaireForm.setWorkAgain(ParamUtil.getInteger(request, "workAgain"));
            questionnaireForm.setNotWorkAgain(ParamUtil.getString(request, "notWorkAgain"));
            questionnaireForm.setRecommendTrantor(ParamUtil.getInteger(request, "recommendTrantor"));
            questionnaireForm.setNotRecommendTrantor(ParamUtil.getString(request, "notRecommendTrantor"));
            questionnaireForm.setCompanyName(ParamUtil.getString(request, "companyName"));
            questionnaireForm.setCompanyDetails(ParamUtil.getString(request, "companyDetails"));
            questionnaireForm.setDesignation(ParamUtil.getString(request, "designation"));
            questionnaireForm.setLocation(ParamUtil.getString(request, "location"));
            questionnaireForm.setSalaryHike(ParamUtil.getString(request, "salaryHike"));
            questionnaireForm.setFeedback(ParamUtil.getString(request, "feedback"));
            questionnaireForm.setSubmittedDate(now);
            QuestionnaireFormLocalServiceUtil.updateQuestionnaireForm(questionnaireForm);
            Resignation resignation=resignedEntry.get(0);

            Email managerEmail = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
            managerEmail.setSubject(MessageFormat.format(MANAGER_CLEARANCE_EMAIL_SUBJECT,employeeData.getEcode(),employeeData.getName()));
            managerEmail.setBody(MessageFormat.format(MANAGER_CLEARANCE_EMAIL_BODY,managerData.getName(),employeeData.getName(),employeeData.getEcode(), getPortalUrl() + URL_EXIT_ADMIN));
            managerEmail.setToAddress(resignation.getAssigneeTo()?resignation.getManagerEmail()+COMMA+resignation.getAssigneeEmail():resignation.getManagerEmail());
            managerEmail.setCcAddress(DL_HR);
            managerEmail.setModule(MODULE_EXIT);
            managerEmail.setScheduled(false);
            managerEmail.setSent(false);
            managerEmail.setCreatedDate(getIstDate());
            EmailLocalServiceUtil.addEmail(managerEmail);
        }
    }

    @Override
    public void exitQuestionnaireFormWorkflow(ActionRequest request, ActionResponse response) {
        String resignationFormId = ParamUtil.getString(request, RESIGNATION_ID);
        ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        User loginedUser = td.getUser();
        Employee employeeData = EmployeeLocalServiceUtil.findByEmail(loginedUser.getEmailAddress());
        Employee managerData = EmployeeLocalServiceUtil.fetchEmployee(employeeData.getManager());
        Resignation employeeResignData = ResignationLocalServiceUtil.fetchResignation(Long.parseLong(resignationFormId));

        EmployeeDto dataDto = new EmployeeDto();
        dataDto.setManagerName(managerData != null ? managerData.getName():BLANK);
        if(null !=employeeData ){
            dataDto.setEmployeeName(employeeData.getName());
            dataDto.setEmployeeCode(employeeData.getEcode());
            dataDto.setEmpMobNo(employeeResignData.getMobile().isEmpty() ? employeeData.getMobile():employeeResignData.getMobile());
            dataDto.setAccount(employeeData.getAccount());
        }

        dataDto.setResignationId(Long.parseLong(resignationFormId));
        if(null!=employeeResignData.getLastWorkingDate()){
            dataDto.setLastWorkingDate(convertDateToLocalDateTime(employeeResignData.getLastWorkingDate()).format(FORMATTER_YYYY_MM_DD));
        }
        dataDto.setPostalAddress(employeeResignData.getPostalAddress());
        dataDto.setCityName(employeeResignData.getCityName());

        QuestionnaireFormDataDTO questionnaireFormDataDTO=new QuestionnaireFormDataDTO();
        questionnaireFormDataDTO.setSubmitted(false);
        List<QuestionnaireForm> questionnaireForm = QuestionnaireFormLocalServiceUtil.findQuestionnaireFormByResignationId(Long.parseLong(resignationFormId));
        if(!questionnaireForm.isEmpty()){
            QuestionnaireForm questionnaireFormData= questionnaireForm.get(0);
            questionnaireFormDataDTO.setWorkExperience(questionnaireFormData.getWorkExperience());
            questionnaireFormDataDTO.setCommunicationWithEmployees(questionnaireFormData.getCommunicationWithEmployees());
            questionnaireFormDataDTO.setTrainingOpportunity(questionnaireFormData.getTrainingOpportunity());
            questionnaireFormDataDTO.setDealingWithStaff(questionnaireFormData.getDealingWithStaff());
            questionnaireFormDataDTO.setSatisfactionLevel(questionnaireFormData.getSatisfactionLevel());
            questionnaireFormDataDTO.setReasonForLeaving(questionnaireFormData.getReasonForLeaving());
            questionnaireFormDataDTO.setReasonForLeavingDescribe(questionnaireFormData.getReasonForLeavingDescribe());
            questionnaireFormDataDTO.setReasonForJoining(questionnaireFormData.getReasonForJoining());
            questionnaireFormDataDTO.setWorkAgain(questionnaireFormData.getWorkAgain());
            questionnaireFormDataDTO.setNotWorkAgain(questionnaireFormData.getNotWorkAgain());
            questionnaireFormDataDTO.setRecommendTrantor(questionnaireFormData.getRecommendTrantor());
            questionnaireFormDataDTO.setNotRecommendTrantor(questionnaireFormData.getNotRecommendTrantor());
            questionnaireFormDataDTO.setCompanyName(questionnaireFormData.getCompanyName());
            questionnaireFormDataDTO.setCompanyDetails(questionnaireFormData.getCompanyDetails());
            questionnaireFormDataDTO.setDesignation(questionnaireFormData.getDesignation());
            questionnaireFormDataDTO.setLocation(questionnaireFormData.getLocation());
            questionnaireFormDataDTO.setSalaryHike(questionnaireFormData.getSalaryHike());
            questionnaireFormDataDTO.setFeedback(questionnaireFormData.getFeedback());
            if(null!=questionnaireFormData.getSubmittedDate()){
                questionnaireFormDataDTO.setSubmittedDate(convertDateToLocalDateTime(questionnaireFormData.getSubmittedDate()).format(FORMATTER_YYYY_MM_DD));
                questionnaireFormDataDTO.setSubmitted(true);
            }
        }
        request.setAttribute("questionnaireFormData", questionnaireFormDataDTO);
        request.setAttribute("empCoreDetails", dataDto);
        response.getRenderParameters().setValue("mvcPath", "/jsp/questionnaireForm.jsp");
    }

    @Override
    public void exitWorkflowFormWorkflow(ActionRequest request, ActionResponse response) {
        long resignationFormId = ParamUtil.getLong(request, RESIGNATION_ID);
        Employee employeeData = EmployeeLocalServiceUtil.findByEmail(getLoggedUser(request));
        Employee managerData = EmployeeLocalServiceUtil.fetchEmployee(employeeData.getManager());
        List<ExitState> statusMap=	ExitStateLocalServiceUtil.getExitStates(-1,-1);
        EmployeeDto dataDto = new EmployeeDto();
        if(resignationFormId ==0) {
            dataDto.setManagerEmail(managerData.getEmail());
            dataDto.setManagerName(managerData.getName());
        }
        dataDto.setEmployeeBand(employeeData.getBand());
        dataDto.setEmployeeName(employeeData.getName());
        dataDto.setLocation(employeeData.getLocation());
        dataDto.setEmpMobNo(employeeData.getMobile());
        dataDto.setEmployeeCode(employeeData.getEcode());
        dataDto.setAccount(employeeData.getAccount());
        dataDto.setEmployeeUpdated(false);
        dataDto.setItAssetsSubmitted(false);
        dataDto.setAbscondTerminateRetained(false);
        dataDto.setStates(INDIA_STATE);
        if(resignationFormId !=0) {
            Resignation employeeResignData = ResignationLocalServiceUtil.fetchResignation(resignationFormId);
            Employee managerDetail = EmployeeLocalServiceUtil.findByEmail(employeeResignData.getManagerEmail());
            dataDto.setManagerEmail(employeeResignData.getManagerEmail());
            dataDto.setManagerName(managerDetail.getName());
            dataDto.setEmpStatus(employeeResignData.getStatus());
            dataDto.setResignationId(employeeResignData.getId());
            dataDto.setAlternateEmail(employeeResignData.getAlternateEmail());
            dataDto.setEmployeeUpdated(employeeResignData.getStatus() == statusMap.get(4).getExitStateKey());
            dataDto.setAbscondTerminateRetained(!(employeeResignData.getStatus() != statusMap.get(6).getExitStateKey() || (employeeResignData.getStatus() == statusMap.get(6).getExitStateKey() && !employeeResignData.getStateName().isEmpty() ) ));
            dataDto.setLastWorkingDate(null !=employeeResignData.getLastWorkingDate() ? convertDateToLocalDateTime(employeeResignData.getLastWorkingDate()).format(FORMATTER_YYYY_MM_DD): NOT_CONFIRMED_BY_HR  );
            if(null != employeeResignData.getItAssetsSubmissionDate() && (employeeResignData.getStatus() == statusMap.get(2).getExitStateKey() || employeeResignData.getStatus() == statusMap.get(5).getExitStateKey() )&& employeeResignData.getStatus()!=statusMap.get(6).getExitStateKey() ) {
                dataDto.setHasLaptop(getYesNoForInteger(employeeResignData.getHasLaptop()));
                dataDto.setHasMouse(getYesNoForInteger(employeeResignData.getHasMouse()));
                dataDto.setHasCharger(getYesNoForInteger(employeeResignData.getHasCharger()));
                dataDto.setHasHeadSet(getYesNoForInteger(employeeResignData.getHasHeadSet()));
                dataDto.setHasLaptopBag(getYesNoForInteger(employeeResignData.getHasLaptopBag()));
                dataDto.setHasHomeMonitor(getYesNoForInteger(employeeResignData.getHasHomeMonitor()));
                dataDto.setHasHomeDesktop(getYesNoForInteger(employeeResignData.getHasHomeDesktop()));
                dataDto.setOtherAssetsList(employeeResignData.getOtherAssetsList());
                dataDto.setItAssetsSubmitted(true);
            }
            if ((employeeResignData.getStatus() != statusMap.get(4).getExitStateKey()) && (employeeResignData.getStatus() != statusMap.get(5).getExitStateKey() || (employeeResignData.getStatus() == statusMap.get(5).getExitStateKey() &&  !employeeResignData.getMobile().isEmpty()))) {
                dataDto.setResignationId(employeeResignData.getId());
                dataDto.setEmployeeDesignation(employeeData.getDesignation());
                dataDto.setEmployeeEmail(employeeResignData.getAlternateEmail());
                dataDto.setStateName(employeeResignData.getStateName());
                dataDto.setCityName(employeeResignData.getCityName());
                dataDto.setPostalAddress(employeeResignData.getPostalAddress());
                dataDto.setPincode(employeeResignData.getPincode());
                dataDto.setDoj(convertDateToLocalDateTime(employeeData.getDoj()).format(FORMATTER_YYYY_MM_DD));
                if (!employeeResignData.getMobile().isEmpty() ) {
                    dataDto.setEmployeeUpdated(true);
                    dataDto.setEmpMobNo(employeeResignData.getMobile());
                }
                if (employeeResignData.getWithdrawCount() == 1 || employeeResignData.getWithdrawCount() == 2) {
                    dataDto.setWithdrawCount(employeeResignData.getWithdrawCount());
                    dataDto.setEmpWithdrawComment(employeeResignData.getEmpWithdrawComment());
                }
                if(null != employeeResignData.getAbscondTerminateDate())
                {
                    dataDto.setAbscondTerminateDate(convertDateToLocalDateTime(employeeResignData.getAbscondTerminateDate()).format(FORMATTER_YYYY_MM_DD));
                }
                dataDto.setReason(employeeResignData.getReason());
                dataDto.setEmployeeComment(employeeResignData.getEmployeeComment());

                List<ExitForm> exitForm = ExitFormLocalServiceUtil.findExitFormByResignationId(resignationFormId);
                ArrayList<Integer> exitStates = new ArrayList<>();
                exitStates.add(statusMap.get(0).getExitStateKey());
                exitStates.add(statusMap.get(1).getExitStateKey());
                exitStates.add(statusMap.get(2).getExitStateKey());
                dataDto.setExitStatus(exitStates.contains(employeeResignData.getStatus()) && exitForm.isEmpty());
                String mode = "";
                if (employeeResignData.getStatus() == statusMap.get(3).getExitStateKey()) {
                    mode = "View";
                } else {
                    mode = "PartialUpdate";
                }
                dataDto.setMode(mode);
            }
        }
        request.setAttribute("statusMap", statusMap);
        request.setAttribute("empCoreDetails", dataDto);
        response.getRenderParameters().setValue("mvcPath", "/jsp/resignation.jsp");
    }
    private String getYesNoForInteger(String value){
        if(value.equalsIgnoreCase(ONE)){
            return YES;
        } else {
            return NO;
        }
    }
}

