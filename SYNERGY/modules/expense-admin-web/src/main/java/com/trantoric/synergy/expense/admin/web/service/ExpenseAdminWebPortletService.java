package com.trantoric.synergy.expense.admin.web.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.trantoric.synergy.expense.admin.web.dto.ExpenseDto;
import com.trantorinc.synergy.expense.core.model.Expense;
import com.trantorinc.synergy.lms.core.model.Employee;

import javax.portlet.ActionRequest;
import java.util.List;

public interface ExpenseAdminWebPortletService {
    void  getExpenseFormDetails(ActionRequest request) throws PortalException;
    List<ExpenseDto> managerViewEntries(List<Expense> expenseList , List<Employee> employeeList);
    List<ExpenseDto> managerEditableEntries(List<Expense> expenseList ,  List<Employee> employeeList);
    List<ExpenseDto> financeViewEntries(List<Expense> expenseList , List<Employee> employeeList);
    List<ExpenseDto> financeEditableEntries(List<Expense> expenseList , List<Employee> employeeList);
    public String getExpenseFile(String fileId);
     void submitExpenseForm(ActionRequest request) throws PortalException;
}
