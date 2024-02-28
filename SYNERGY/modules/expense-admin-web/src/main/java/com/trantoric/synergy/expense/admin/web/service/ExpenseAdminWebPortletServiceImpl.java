package com.trantoric.synergy.expense.admin.web.service;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.trantoric.synergy.expense.admin.web.dto.ExpenseDto;
import com.trantoric.synergy.expense.admin.web.dto.ExpenseLineDto;
import com.trantorinc.synergy.common.service.constant.AppConstantService;
import com.trantorinc.synergy.common.service.drive.DriveService;
import com.trantorinc.synergy.email.core.model.Email;
import com.trantorinc.synergy.email.core.service.EmailLocalServiceUtil;
import com.trantorinc.synergy.expense.core.model.Expense;
import com.trantorinc.synergy.expense.core.model.ExpenseLine;
import com.trantorinc.synergy.expense.core.service.ExpenseLineLocalServiceUtil;
import com.trantorinc.synergy.expense.core.service.ExpenseLocalServiceUtil;
import com.trantorinc.synergy.lms.core.model.Employee;
import com.trantorinc.synergy.lms.core.service.EmployeeLocalServiceUtil;

import javax.portlet.ActionRequest;
import java.io.File;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.liferay.portlet.usersadmin.search.OrganizationDisplayTerms.ZIP;
import static com.trantoric.synergy.expense.admin.web.constants.ExpenseAdminWebPortletKeys.*;
import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;
import static com.trantorinc.synergy.common.service.constant.AppConstantService.MODULE_EXPENSE;
import static com.trantorinc.synergy.common.service.util.UtilService.*;

public class ExpenseAdminWebPortletServiceImpl implements ExpenseAdminWebPortletService {
    @Override
    public void submitExpenseForm(ActionRequest request) throws PortalException {

        long expenseId = ParamUtil.getLong(request, "expenseId");
        String comments = ParamUtil.getString(request, "comments");
        String actionState = ParamUtil.getString(request, "action");
        String managerName = ParamUtil.getString(request, "managerName");
        boolean submitStatus = ParamUtil.getBoolean(request, "noResubmit");
        String financeEmail = AppConstantService.DL_FINANCE;
        boolean closedByManager = false;
        boolean closedByFinance = false;
        String loggedUserEmail = getLoggedUser(request);
        Expense expenseDetails = ExpenseLocalServiceUtil.getExpense(expenseId);
        Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
        if (expenseDetails.getAssignee().equalsIgnoreCase(financeEmail)) {
            expenseDetails.setFinanceComment(comments);
            expenseDetails.setFinanceDate(getIstDate());
            if (actionState.equalsIgnoreCase(APPROVED)) {
                expenseDetails.setStatus(FINANCE_APPROVED);
            } else if (submitStatus) {
                expenseDetails.setStatus(STATUS_CLOSED);
                closedByFinance = true;
            } else {
                expenseDetails.setStatus(FINANCE_REJECTED);
            }
        } else {
            expenseDetails.setManagerComment(comments);
            expenseDetails.setManagerDate(getIstDate());
            if (actionState.equalsIgnoreCase(APPROVED)) {
                expenseDetails.setStatus(MANAGER_APPROVED);
                expenseDetails.setAssignee(financeEmail);
            } else if (submitStatus) {
                expenseDetails.setStatus(STATUS_CLOSED);
                closedByManager = true;
            } else {
                expenseDetails.setStatus(MANAGER_REJECTED);
            }
        }
        ExpenseLocalServiceUtil.updateExpense(expenseDetails);
        String employeeEmail = BLANK;
        Employee employeeInfo = EmployeeLocalServiceUtil.fetchEmployee(expenseDetails.getEcode());
        if (null != employeeInfo) {
            employeeEmail = employeeInfo.getEmail();
        }

        if (expenseDetails.getStatus() == FINANCE_APPROVED || (expenseDetails.getStatus() == FINANCE_REJECTED) || (expenseDetails.getStatus() == STATUS_CLOSED && closedByFinance)) {
            email.setSubject(MessageFormat.format(SUBJECT_EXPENSE_REQUEST_FINANCE, getStatus(expenseDetails.getStatus())));
            email.setBody(MessageFormat.format(BODY_EXPENSE_REQUEST_FINANCE, expenseDetails.getExpenseType(), getStatus(expenseDetails.getStatus()), getComment(expenseDetails.getStatus(), expenseDetails.getFinanceComment())));
            email.setToAddress(employeeEmail);
            if (!expenseDetails.getExpenseType().equalsIgnoreCase(EXPENSETYPE_BIRTHDAY) && !expenseDetails.getExpenseType().equalsIgnoreCase(EXPENSETYPE_COVID1) && !expenseDetails.getExpenseType().equalsIgnoreCase(EXPENSETYPE_COVID2)) {
                email.setCcAddress(financeEmail.equalsIgnoreCase(expenseDetails.getApprovingManager()) ? financeEmail : financeEmail + COMMA + expenseDetails.getApprovingManager());
            } else {
                email.setCcAddress(financeEmail);
            }
        } else if (expenseDetails.getStatus() == MANAGER_APPROVED) {
            email.setSubject(SUBJECT_DOUBLE_WORKFLOW_MANAGER_APPROVED);
            email.setBody(MessageFormat.format(BODY_DOUBLE_WORKFLOW_MANAGER_APPROVED, managerName, expenseDetails.getExpenseType(), null != employeeInfo ? employeeInfo.getName() : BLANK, null != employeeInfo ? employeeInfo.getEcode() : BLANK, getPortalUrl() + URL_EXPENSE_ADMIN));
            email.setToAddress(financeEmail);
            email.setCcAddress(loggedUserEmail + COMMA + employeeEmail);
        } else if (expenseDetails.getStatus() == MANAGER_REJECTED || (expenseDetails.getStatus() == STATUS_CLOSED && closedByManager)) {
            email.setSubject(MessageFormat.format(SUBJECT_DOUBLE_WORKFLOW_MANAGER_DISAPPROVED, getStatus(expenseDetails.getStatus())));
            email.setBody(MessageFormat.format(BODY_DOUBLE_WORKFLOW_MANAGER_DISAPPROVED, expenseDetails.getExpenseType(), getStatus(expenseDetails.getStatus()), expenseDetails.getManagerComment()));
            email.setToAddress(employeeEmail);
            email.setCcAddress(loggedUserEmail); //CC manager
        }
        email.setModule(MODULE_EXPENSE);
        email.setScheduled(false);
        email.setSent(false);
        email.setCreatedDate(getIstDate());
        EmailLocalServiceUtil.addEmail(email);
    }

    @Override
    public void getExpenseFormDetails(ActionRequest request) throws PortalException {
        Long expenseId = ParamUtil.getLong(request, "expenseId");
        String mode = ParamUtil.getString(request, "mode");
        String role = ParamUtil.getString(request, "role");
        Expense expense = ExpenseLocalServiceUtil.getExpense(expenseId);
        Employee empDetails = EmployeeLocalServiceUtil.getEmployee(expense.getEcode());
        Employee managerDetails = EmployeeLocalServiceUtil.findByEmail(expense.getApprovingManager());
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setExpenseId(expenseId);
        expenseDto.setEcode(expense.getEcode());
        expenseDto.setMode(mode);
        if (null != empDetails) {
            expenseDto.setName(empDetails.getName());
            expenseDto.setBand(empDetails.getBand());
            expenseDto.setAccount(empDetails.getAccount());
            expenseDto.setMobile(empDetails.getMobile());
            expenseDto.setLocation(empDetails.getLocation());
        }

        expenseDto.setShowApprovingManagerField(false);
        boolean expenseByManager = false;
        String expenseType = expense.getExpenseType();
        if (!(expenseType.equalsIgnoreCase(EXPENSETYPE_BIRTHDAY) || expenseType.equalsIgnoreCase(EXPENSETYPE_COVID1) || expenseType.equalsIgnoreCase(EXPENSETYPE_COVID2))) {
            expenseDto.setApprovingManager(null != managerDetails ? managerDetails.getName() : expense.getApprovingManager());
            expenseDto.setShowApprovingManagerField(true);
            expenseByManager = true;
        }
        if (expense.getStatus() != 0 && role.equalsIgnoreCase(FINANCE)) {
            if (expense.getStatus() == 1 || expense.getStatus() == 2) {
                String labelName = expense.getStatus() == 1 ? FINANCE + APPROVING : FINANCE + REJECTION;
                expenseDto.setFinanceSubmissionDate(convertDateToLocalDateTime(expense.getFinanceDate()).format(FORMATTER_YYYY_MM_DD));
                expenseDto.setFinanceLebelName(labelName);
                if (!expense.getManagerComment().equalsIgnoreCase(BLANK) && expenseByManager) {
                    expenseDto.setManagerSubmissionDate(convertDateToLocalDateTime(expense.getManagerDate()).format(FORMATTER_YYYY_MM_DD));
                    expenseDto.setManagerLebelName(MANAGER + APPROVING);
                }
            } else if (expense.getStatus() == 3 || expense.getStatus() == 4) {
                expenseDto.setManagerSubmissionDate(convertDateToLocalDateTime(expense.getManagerDate()).format(FORMATTER_YYYY_MM_DD));
                if (expense.getStatus() == 3) {
                    expenseDto.setManagerLebelName(MANAGER + APPROVING);
                } else if (expense.getStatus() == 4) {
                    expenseDto.setManagerLebelName(MANAGER + REJECTION);
                }
            } else if (expense.getStatus() == 5) {
                if (!expense.getFinanceComment().equalsIgnoreCase(BLANK) && !expense.getManagerComment().equalsIgnoreCase(BLANK)) {
                    expenseDto.setFinanceSubmissionDate(convertDateToLocalDateTime(expense.getFinanceDate()).format(FORMATTER_YYYY_MM_DD));
                    expenseDto.setManagerSubmissionDate(convertDateToLocalDateTime(expense.getManagerDate()).format(FORMATTER_YYYY_MM_DD));
                    if (expense.getFinanceDate().after(expense.getManagerDate())) {
                        expenseDto.setFinanceLebelName(FINANCE + REJECTION);
                        expenseDto.setManagerLebelName(MANAGER + APPROVING);
                    } else {
                        expenseDto.setManagerLebelName(MANAGER + REJECTION);
                        expenseDto.setFinanceLebelName(MANAGER + APPROVING);
                    }
                } else if (!expense.getFinanceComment().equalsIgnoreCase(BLANK)) {
                    expenseDto.setFinanceSubmissionDate(convertDateToLocalDateTime(expense.getFinanceDate()).format(FORMATTER_YYYY_MM_DD));
                    expenseDto.setFinanceLebelName(FINANCE + REJECTION);
                } else {
                    expenseDto.setManagerSubmissionDate(convertDateToLocalDateTime(expense.getManagerDate()).format(FORMATTER_YYYY_MM_DD));
                    expenseDto.setManagerLebelName(MANAGER + REJECTION);
                }
            }
        }
        expenseDto.setFinanceComment(expense.getFinanceComment());
        if (expenseByManager && !expense.getManagerComment().equalsIgnoreCase(BLANK)) {
            expenseDto.setManagerComment(expense.getManagerComment());
        }
        expenseDto.setExpenseType(expenseType);
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
        request.setAttribute("empExpenseDetails", expenseDto);
    }

    @Override
    public List<ExpenseDto> managerViewEntries(List<Expense> expenseList, List<Employee> employeeList) {
        List<ExpenseDto> expenseDtoList = new ArrayList<>();
        if (!expenseList.isEmpty()) {
            for (Expense expense : expenseList) {
                if (expense.getStatus() != 0) {
                    ExpenseDto expenseDto = setExpenseHeaders(expense, employeeList);
                    expenseDtoList.add(expenseDto);
                }
            }
        }
        return expenseDtoList;
    }

    @Override
    public List<ExpenseDto> managerEditableEntries(List<Expense> expenseList, List<Employee> employeeList) {
        List<ExpenseDto> expenseDtoList = new ArrayList<>();
        if (!expenseList.isEmpty()) {
            for (Expense expense : expenseList) {
                if (expense.getStatus() == 0) {
                    ExpenseDto expenseDto = setExpenseHeaders(expense, employeeList);
                    expenseDtoList.add(expenseDto);
                }
            }
        }
        return expenseDtoList;
    }

    @Override
    public List<ExpenseDto> financeViewEntries(List<Expense> expenseList, List<Employee> employeeList) {
        List<ExpenseDto> expenseDtoList = new ArrayList<>();
        if (!expenseList.isEmpty()) {
            for (Expense expense : expenseList) {
                if (expense.getStatus() != 0 && expense.getStatus() != 3) {
                    ExpenseDto expenseDto = setExpenseHeaders(expense, employeeList);
                    expenseDtoList.add(expenseDto);
                }
            }
        }
        return expenseDtoList;
    }

    @Override
    public List<ExpenseDto> financeEditableEntries(List<Expense> expenseList, List<Employee> employeeList) {
        List<ExpenseDto> expenseDtoList = new ArrayList<>();
        if (!expenseList.isEmpty()) {
            for (Expense expense : expenseList) {
                if (expense.getStatus() == 0 || expense.getStatus() == 3) {
                    ExpenseDto expenseDto = setExpenseHeaders(expense, employeeList);
                    expenseDtoList.add(expenseDto);
                }
            }
        }
        return expenseDtoList;
    }

    @Override
    public String getExpenseFile(String fileId) {
        File file = DriveService.getFile(fileId, fileId, ZIP);
        return getBase64File(file);
    }

    private ExpenseDto setExpenseHeaders(Expense expense, List<Employee> employeeList) {
        List<Employee> employee = employeeList.stream().filter(s -> s.getEcode().equalsIgnoreCase(expense.getEcode())).collect(Collectors.toList());
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setExpenseId(expense.getExpenseId());
        expenseDto.setSubmittedDate(convertDateToLocalDateTime(expense.getCreatedDate()).format(FORMATTER_YYYY_MM_DD_HH_MM_SS));
        expenseDto.setSubmittedBy(!employee.isEmpty() ? employee.get(0).getName() : expense.getEcode());
        expenseDto.setExpenseType(expense.getExpenseType());
        expenseDto.setTotalBillAmount(expense.getTotalBillAmount());
        String statusDescription ;
        switch(expense.getStatus()) {
            case 1 :
                statusDescription = APPROVED_BY_FINANCE;
                break;
            case 2 :
                statusDescription = REJECTED_BY_FINANCE;
                break;
            case 3 :
                statusDescription = APPROVED_BY_MANAGER;
                break;
            case 4 :
                statusDescription = REJECTED_BY_MANAGER;
                break;
            case 5 :
                statusDescription = CLOSED;
                break;
            default: statusDescription = OPEN;
        }
        expenseDto.setStatus(statusDescription);
        expenseDto.setStatusInt(expense.getStatus());
        return expenseDto;
    }

    private String getStatus(int status) {
        String returnValue = BLANK;
        if (status == 1) {
            returnValue = APPROVED;
        } else if (status == 2 || status == 4) {
            returnValue = REJECTED;
        } else if (status == 5) {
            returnValue = CLOSED;
        }
        return returnValue;
    }

    private String getComment(int status, String comment) {
        String returnValue;
        if (status == 1) {
            returnValue = APPROVE_COMMENT;
        } else {
            returnValue = REJECT_COMMENT + comment;
        }
        return returnValue;
    }

}
