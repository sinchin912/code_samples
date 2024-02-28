package com.trantorinc.synergy.expense.web.service;


import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.trantorinc.synergy.common.service.constant.AppConstantService;
import com.trantorinc.synergy.common.service.drive.DriveService;
import com.trantorinc.synergy.common.service.util.UtilService;
import com.trantorinc.synergy.email.core.model.Email;
import com.trantorinc.synergy.email.core.service.EmailLocalServiceUtil;
import com.trantorinc.synergy.expense.core.model.Expense;
import com.trantorinc.synergy.expense.core.model.ExpenseLine;
import com.trantorinc.synergy.expense.core.service.ExpenseLineLocalServiceUtil;
import com.trantorinc.synergy.expense.core.service.ExpenseLocalServiceUtil;
import com.trantorinc.synergy.expense.web.dto.ExpenseDto;
import com.trantorinc.synergy.expense.web.dto.ExpenseLineDto;
import com.trantorinc.synergy.lms.core.model.Employee;
import com.trantorinc.synergy.lms.core.service.DriveLocalServiceUtil;
import com.trantorinc.synergy.lms.core.service.EmployeeLocalServiceUtil;

import javax.portlet.ActionRequest;
import javax.portlet.RenderRequest;

import static com.liferay.portlet.usersadmin.search.OrganizationDisplayTerms.ZIP;
import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;
import static com.trantorinc.synergy.common.service.constant.AppConstantService.MODULE_EXPENSE;
import static com.trantorinc.synergy.common.service.util.UtilService.*;
import static com.trantorinc.synergy.common.service.util.UtilService.convertDateToLocalDateTime;
import static com.trantorinc.synergy.expense.web.constants.ExpenseWebPortletKeys.*;

import java.io.File;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class ExpensePortletServiceImpl implements ExpensePortletService {

    @Override
    public List<ExpenseDto> findDetails(RenderRequest request) {
        List<ExpenseDto> expenseDtoList = new ArrayList<>();
        Employee employeeDetails = EmployeeLocalServiceUtil.findByEmail(getLoggedUser(request));
        List<Expense> expenseList = ExpenseLocalServiceUtil.findByEcode(employeeDetails.getEcode());
        if (!expenseList.isEmpty()) {
            for (Expense expense : expenseList) {
                ExpenseDto expenseDto = new ExpenseDto();
                expenseDto.setCreatedDate(convertDateToLocalDateTime(expense.getCreatedDate()).format(FORMATTER_YYYY_MM_DD));
                expenseDto.setExpenseId(expense.getExpenseId());
                expenseDto.setExpenseType(expense.getExpenseType());
                expenseDto.setTotalBillAmount(expense.getTotalBillAmount());
                expenseDto.setAssignee(expense.getAssignee().equalsIgnoreCase(DL_FINANCE) ? FINANCE_TEAM : expense.getAssignee());
                String statusDescription;
                switch (expense.getStatus()) {
                    case 1:
                        statusDescription = APPROVED_BY_FINANCE;
                        break;
                    case 2:
                        statusDescription = REJECTED_BY_FINANCE;
                        break;
                    case 3:
                        statusDescription = APPROVED_BY_MANAGER;
                        break;
                    case 4:
                        statusDescription = REJECTED_BY_MANAGER;
                        break;
                    case 5:
                        statusDescription = CLOSED;
                        break;
                    default:
                        statusDescription = OPEN;
                }
                expenseDto.setStatus(statusDescription);
                expenseDto.setStatusInt(expense.getStatus());
                expenseDtoList.add(expenseDto);
            }
        }
        return expenseDtoList;
    }

    @Override
    public void getExpenseFormDetails(ActionRequest request) throws PortalException {
        Long expenseId = ParamUtil.getLong(request, "expenseId");
        String emailId = getLoggedUser(request);
        ExpenseDto expenseDto = new ExpenseDto();
        Employee empDetails = EmployeeLocalServiceUtil.findByEmail(emailId);
        List<Employee> allEmployeeList = EmployeeLocalServiceUtil.findAllActiveEmployees();
        expenseDto.setExpenseId(expenseId);
        expenseDto.setViewMode(false);
        expenseDto.setEcode(empDetails.getEcode());
        expenseDto.setName(empDetails.getName());
        expenseDto.setBand(empDetails.getBand());
        expenseDto.setAccount(empDetails.getAccount());
        expenseDto.setMobile(empDetails.getMobile());
        expenseDto.setLocation(empDetails.getLocation());

        Role managerRole = RoleLocalServiceUtil.fetchRole(COMPANY_ID, ROLE_MANAGER);
        List<User> managers = UserLocalServiceUtil.getRoleUsers(managerRole.getRoleId());
        Map<String, String> unsortedManagerMap = new HashMap<>();
        for (User roleManger : managers) {
            List<Employee> managerDetails = allEmployeeList.stream().filter(s -> s.getEmail().equalsIgnoreCase(roleManger.getEmailAddress())).collect(Collectors.toList());
            if (!managerDetails.isEmpty()) {
                unsortedManagerMap.put(roleManger.getEmailAddress(), roleManger.getFullName());
            }
        }
        int currentYear = UtilService.getCurrentYear();
        expenseDto.setFyStartDate(UtilService.getFinancialStartDate(currentYear - 1));
        expenseDto.setFyEndDate(UtilService.getFinancialEndDate(currentYear));
        expenseDto.setManagers(unsortedManagerMap.entrySet().stream().sorted(comparingByValue()).collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new)));
        expenseDto.setShowApprovingManagerField(false);
        expenseDto.setManagerFinanceSection(false);
        if (expenseId != 0) {
            boolean expenseByManager = false;
            Expense expense = ExpenseLocalServiceUtil.getExpense(expenseId);
            expenseDto.setViewMode(expense.getStatus() != 2 && expense.getStatus() != 4);
//          Status 2 - Manager Rejection state & Status 4 - finance Rejection state
            String expenseType = expense.getExpenseType();
            if (!(expenseType.equalsIgnoreCase(EXPENSETYPE_BIRTHDAY) || expenseType.equalsIgnoreCase(EXPENSETYPE_COVID1) || expenseType.equalsIgnoreCase(EXPENSETYPE_COVID2))) {
                expenseDto.setApprovingManager(expense.getApprovingManager());
                expenseDto.setShowApprovingManagerField(true);
                expenseByManager = true;
            }
            if (expense.getStatus() != 0) {
                expenseDto.setFinanceComment(expense.getFinanceComment());
                expenseDto.setManagerFinanceSection(true);
                if (expenseByManager && !expense.getManagerComment().equalsIgnoreCase(BLANK)) {
                    expenseDto.setManagerComment(expense.getManagerComment());
                }
            }
            expenseDto.setExpenseType(expenseType);
            expenseDto.setExpenseId(expense.getExpenseId());
            expenseDto.setTotalBillAmount(expense.getTotalBillAmount());
            List<ExpenseLine> lineList = ExpenseLineLocalServiceUtil.findByExpenseId(expenseId);
            if (expenseType.equals(EXPENSETYPE_CHILDBIRTH) || expenseType.equals(EXPENSETYPE_MARRIAGECARD)) {
                if (expenseType.equals(EXPENSETYPE_CHILDBIRTH) && !lineList.isEmpty()) {
                    ExpenseLine line = lineList.get(0);
                    String[] description = line.getDescription().split("#");
                    expenseDto.setBabyName(description[0]);
                    expenseDto.setSelectBabyGender(description[1]);
                    expenseDto.setDobBaby(convertDateToLocalDateTime(line.getStartDate()).format(FORMATTER_YYYY_MM_DD));
                } else if (expenseType.equals(EXPENSETYPE_MARRIAGECARD) && !lineList.isEmpty()) {
                    ExpenseLine line = lineList.get(0);
                    String[] description = line.getDescription().split("#");
                    expenseDto.setSpouseName(description[0]);
                    expenseDto.setSelectGender(description[description.length - 1]);
                    expenseDto.setDobSpouse(convertDateToLocalDateTime(line.getStartDate()).format(FORMATTER_YYYY_MM_DD));
                    expenseDto.setMarriageDate(convertDateToLocalDateTime(line.getEndDate()).format(FORMATTER_YYYY_MM_DD));
                }
            } else {
                expenseDto.setFileId(expense.getFileId());
                List<ExpenseLineDto> dtoList = new ArrayList<>();
                for (ExpenseLine expenseLine : lineList) {
                    ExpenseLineDto lineDto = new ExpenseLineDto();
                    lineDto.setStartDate(convertDateToLocalDateTime(expenseLine.getStartDate()).format(FORMATTER_YYYY_MM_DD));
                    lineDto.setEndDate(convertDateToLocalDateTime(expenseLine.getEndDate()).format(FORMATTER_YYYY_MM_DD));
                    lineDto.setDescription(expenseLine.getDescription());
                    lineDto.setBillAmount(expenseLine.getBillAmount());
                    dtoList.add(lineDto);
                }
                request.setAttribute("expenseLineList", dtoList);
            }
        }
        request.setAttribute(EMP_EXPENSE_DETAILS, expenseDto);
    }

    @Override
    public void submitExpenseDetail(ActionRequest request) throws PortalException {
        String reSubmit = BLANK;
        String ecode = ParamUtil.getString(request, "ecode");
        String name = ParamUtil.getString(request, "name");
        String approvingManager = ParamUtil.getString(request, "approvingManager");
        String expenseType = ParamUtil.getString(request, "expenseType");
        long totalBillAmount = ParamUtil.getLong(request, "totalAmount");
        long totalRows = ParamUtil.getLong(request, "totalRows");
        long expenseId = ParamUtil.getLong(request, "expenseId");
        Expense expense = expenseId == 0 ? ExpenseLocalServiceUtil.createExpense(CounterLocalServiceUtil.increment()) : ExpenseLocalServiceUtil.getExpense(expenseId);
        if (expenseType.equalsIgnoreCase(EXPENSETYPE_BIRTHDAY) || expenseType.equalsIgnoreCase(EXPENSETYPE_COVID1) || expenseType.equalsIgnoreCase(EXPENSETYPE_COVID2)) {
            approvingManager = AppConstantService.DL_FINANCE;
        }
        if (!(expenseType.equals(EXPENSETYPE_CHILDBIRTH) || expenseType.equals(EXPENSETYPE_MARRIAGECARD))) {
            if (!expense.getFileId().equalsIgnoreCase(BLANK)) {
                DriveService.deleteFile(expense.getFileId());
            }
            String folderId = DriveLocalServiceUtil.findFolderIdByFolderName(MODULE_EXPENSE);
            UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(request);
            File file = uploadPortletRequest.getFile("uploadZip");
            String fileId = DriveService.uploadFile(folderId, String.valueOf(expense.getExpenseId()), file);
            expense.setFileId(fileId);
        }
        expense.setManagerComment(BLANK);
        expense.setFinanceComment(BLANK);
        expense.setEcode(ecode);
        expense.setExpenseType(expenseType);
        expense.setTotalBillAmount(totalBillAmount);
        expense.setAssignee(approvingManager);
        expense.setApprovingManager(approvingManager);
        expense.setStatus(ZERO);
        expense.setCreatedDate(getIstDate());
        if (expenseId == 0) {
            ExpenseLocalServiceUtil.addExpense(expense);
        } else {
            reSubmit = "re-";
            ExpenseLocalServiceUtil.updateExpense(expense);
            List<ExpenseLine> expenseLines = ExpenseLineLocalServiceUtil.findByExpenseId(expenseId);
            if (!expenseLines.isEmpty()) {
                for (ExpenseLine line : expenseLines) {
                    ExpenseLineLocalServiceUtil.deleteExpenseLine(line.getLineId());
                }
            }
        }
        for (int i = 1; i <= totalRows; i++) {
            ExpenseLine expenseLine = ExpenseLineLocalServiceUtil.createExpenseLine(CounterLocalServiceUtil.increment());
            if (expenseType.equals(EXPENSETYPE_CHILDBIRTH)) {
                String babyName = ParamUtil.getString(request, "babyName");
                String selectBabyGender = ParamUtil.getString(request, "selectBabyGender");
                String dobBaby = ParamUtil.getString(request, "dobBaby");
                expenseLine.setStartDate(convertLocalDateTimeToDate(LocalDate.parse(dobBaby, FORMATTER_YYYY_MM_DD).atStartOfDay()));
                expenseLine.setEndDate(getIstDate());
                expenseLine.setDescription(babyName + "#" + selectBabyGender);
                expenseLine.setBillAmount(1000);
            } else if (expenseType.equals(EXPENSETYPE_MARRIAGECARD)) {
                String spouseName = ParamUtil.getString(request, "spouseName");
                String selectGender = ParamUtil.getString(request, "selectGender");
                String dobSpouse = ParamUtil.getString(request, "dobSpouse");
                String marriageDate = ParamUtil.getString(request, "marriageDate");
                expenseLine.setStartDate(convertLocalDateTimeToDate(LocalDate.parse(dobSpouse, FORMATTER_YYYY_MM_DD).atStartOfDay()));
                expenseLine.setEndDate(convertLocalDateTimeToDate(LocalDate.parse(marriageDate, FORMATTER_YYYY_MM_DD).atStartOfDay()));
                expenseLine.setDescription(spouseName + "#" + selectGender);
                expenseLine.setBillAmount(2000);
            } else {
                String startDate = ParamUtil.getString(request, "startDate" + i);
                String endDate = ParamUtil.getString(request, "endDate" + i);
                expenseLine.setStartDate(convertLocalDateTimeToDate(LocalDate.parse(startDate, FORMATTER_YYYY_MM_DD).atStartOfDay()));
                expenseLine.setEndDate(convertLocalDateTimeToDate(LocalDate.parse(endDate, FORMATTER_YYYY_MM_DD).atStartOfDay()));
                expenseLine.setDescription(ParamUtil.getString(request, "empDesc" + i));
                expenseLine.setBillAmount(ParamUtil.getLong(request, "amount" + i));
            }
            expenseLine.setExpenseId(expense.getExpenseId());
            ExpenseLineLocalServiceUtil.addExpenseLine(expenseLine);
        }


        Email employeeEmail = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
        employeeEmail.setSubject(MessageFormat.format(SUBJECT_EXPENSE_SUBMITTED, expense.getExpenseType(), reSubmit));
        employeeEmail.setBody(MessageFormat.format(BODY_EXPENSE_SUBMITTED, expense.getExpenseType(), reSubmit, approvingManager.equalsIgnoreCase(AppConstantService.DL_FINANCE) ? FINANCE_TEAM : MANAGER));
        employeeEmail.setToAddress(getLoggedUser(request));
        employeeEmail.setModule(MODULE_EXPENSE);
        employeeEmail.setScheduled(false);
        employeeEmail.setSent(false);
        employeeEmail.setCreatedDate(getIstDate());
        EmailLocalServiceUtil.addEmail(employeeEmail);

        Email approverEmail = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
        approverEmail.setSubject(MessageFormat.format(SUBJECT_EXPENSE_SUBMITTED, expense.getExpenseType(), reSubmit));
        approverEmail.setBody(MessageFormat.format(BODY_EXPENSE_APPROVER_SUBMISSION, approvingManager.equalsIgnoreCase(AppConstantService.DL_FINANCE) ? FINANCE_TEAM : MANAGER, name, ecode, reSubmit, expense.getExpenseType(), getPortalUrl() + URL_EXPENSE_ADMIN));
        approverEmail.setToAddress(approvingManager);
        approverEmail.setModule(MODULE_EXPENSE);
        approverEmail.setScheduled(false);
        approverEmail.setSent(false);
        approverEmail.setCreatedDate(getIstDate());
        EmailLocalServiceUtil.addEmail(approverEmail);
    }

    @Override
    public String getExpenseFile(String fileId) {
        File file = DriveService.getFile(fileId, fileId, ZIP);
        return getBase64File(file);
    }

}
