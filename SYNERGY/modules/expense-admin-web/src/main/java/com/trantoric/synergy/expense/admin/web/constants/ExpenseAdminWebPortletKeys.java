package com.trantoric.synergy.expense.admin.web.constants;

/**
 * @author saurabh.kumar
 */
public class ExpenseAdminWebPortletKeys {

	public static final String EXPENSEADMINWEB =
		"com_trantoric_synergy_expense_admin_web_ExpenseAdminWebPortlet";
	public static final String EXPENSETYPE_BIRTHDAY = "Birthday Celebrations";
	public static final String EXPENSETYPE_COVID1= "Covid 19 Vaccination Reimbursement - I";
	public static final String EXPENSETYPE_COVID2= "Covid 19 Vaccination Reimbursement - II";
	public static final String EXPENSETYPE_CHILDBIRTH = "Child Birth Gift Card";
	public static final String EXPENSETYPE_MARRIAGECARD = "Marriage Gift Card";
	public static final String APPROVED_BY_FINANCE = "Approved By Finance";
	public static final String APPROVED_BY_MANAGER = "Approved By Manager";
	public static final String REJECTED_BY_FINANCE = "Rejected By Finance";
	public static final String REJECTED_BY_MANAGER = "Rejected By Manager";
	public static final Integer FINANCE_APPROVED = 1;
	public static final Integer FINANCE_REJECTED = 2;
	public static final Integer MANAGER_APPROVED = 3;
	public static final Integer MANAGER_REJECTED = 4;
	public static final Integer STATUS_CLOSED = 5;
	public static final String APPROVING = " Approving Date";
	public static final String REJECTION = " Rejection Date";
	public static final String MANAGER = "Manager";
	public static final String FINANCE = "Finance";

	public static final String SUBJECT_EXPENSE_REQUEST_FINANCE = "Expense Request has been ''{0}'' by Finance Team";
	public static final String BODY_EXPENSE_REQUEST_FINANCE = "Dear Employee,<br><br>Your Expense for ''{0}'' has been ''{1}''.<br>{2}<br>For more details, please contact Finance Team.<br><br>Regards,<br>Finance Team";

	public static final String APPROVE_COMMENT = "This amount shall be credited in your account.";
	public static final String REJECT_COMMENT = "Reason : ";


	public static final String SUBJECT_DOUBLE_WORKFLOW_MANAGER_APPROVED = "Expense  has been approved by Manager, Pending Finance approval";
	public static final String BODY_DOUBLE_WORKFLOW_MANAGER_APPROVED = "Dear Finance Team,<br><br>Manager {0} has approved {1} Expense Type for {2} with ecode {3}.<br> Please <a href=''{4}''>click here</a> to take further action.<br><br>Regards,<br>Synergy Admin";

	public static final String SUBJECT_DOUBLE_WORKFLOW_MANAGER_DISAPPROVED = "Expense  has been ''{0}'' by Manager";
	public static final String BODY_DOUBLE_WORKFLOW_MANAGER_DISAPPROVED = "Dear Employee,<br><br>Your Expense for ''{0}'' has been ''{1}'' by Manager.<br>Reason: {2}<br><br>For more details, please contact your Manager.<br><br>Regards,<br>Synergy Admin";

}