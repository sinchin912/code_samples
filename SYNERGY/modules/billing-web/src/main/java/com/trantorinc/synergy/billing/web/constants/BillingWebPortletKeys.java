package com.trantorinc.synergy.billing.web.constants;

import com.liferay.portal.kernel.util.PropsUtil;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author riya.gupta
 */
public class BillingWebPortletKeys {

	public static final String BILLINGWEB =
		"com_trantorinc_synergy_billing_web_BillingWebPortlet";
	//WebPortlet constants
	public static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	public static final String MANAGER_USER_MANUAL = "Billing - Manager User Manual.pdf";
	public static final String HR_USER_MANUAL = "Billing - HR User Manual.pdf";
	public static final String LEADER_USER_MANUAL = "Billing - Management User Manual.pdf";
	public static final String FINANCE_USER_MANUAL = "Billing - Finance User Manual.pdf";
	public static final String BENCH = "Bench";
	public static final String GOOGLE = "Google";
	public static final String CONTINUUM = "Continuum";
	public static final String CONTINUUMGLOBAL = "Continuumglobal";
	public static final String HOSTNAME = PropsUtil.get("portal.hostname");
	public static final String FINANCE_DL = PropsUtil.get("mail.address.finance");
	public static final String BILLING_URL = "/group/intranet/billing";
	public static final String SUBJECT_BILLING_SUBMISSION = "Billing Details submitted by ''{0}'' for {1}";
	public static final String BODY_BILLING_SUBMISSION = "Hi,<br><br>Manager ''{0}'' has submitted billing details for the month of {1}." +
			"<br><br>Please check the report <a href={2}>here</a>: <br><br>Thanks & Regards,<br>Synergy Admin";
	public static final String ACTION_PERFORMED = "actionPerformed";
	public static final String CHECKBOX = "Checkbox";
	public static final String CURRENT = "Current";
	public static final String CURRENT_PLUS_ONE = "CurrentPlusOne";
	public static final String CURRENT_PLUS_TWO = "CurrentPlusTwo";
	public static final String SHADOW_RESOURCE_TYPE = "ShadowResourceType";
	public static final String SHADOW_START_DATE = "shadowStartDate";
	public static final String BENCH_START_DATE = "benchStartDate";
	public static final String BILLING_START_DATE = "billingStartDate";
	public static final String END_DATE_STR = "billingEndDate";
	public static final String ALLOCATION_STATUS = "AllocationStatus";
	public static final String EMPLOYEE_STATUS = "employeeStatus";
	public static final String LAST_WORKING_DAY = "lastWorkingDate";
	public static final String PERCENT_UTILIZATION = "percentUtilization";
	public static final String REMARKS = "Remarks";
	public static final String VERTICAL = "Vertical";
	public static final String MONTH_HOURS = "MonthHours";
	public static final String BILLABLE_HOURS ="billableHours";
	public static final String EMPLOYEE_ROLE ="EmployeeRole";
	public static final String EXISTING_ROWS = "existingRows";
	public static final String TOTAL_ROWS = "totalRows";
	public static final String SHARED_CHECKBOX = "SharedCheckbox";
	public static final String SHARED_ECODE = "SharedEcode";
	public static final String SHARED_MANAGER_ECODE = "SharedManagerEcode";
	public static final String SHARED_LEAD_ECODE = "SharedleadEcode";
	public static final String SHARED_CURRENT = "SharedCurrent";
	public static final String SHARED_CURRENT_PLUS_ONE = "SharedCurrentPlusOne";
	public static final String SHARED_CURRENT_PLUS_TWO = "SharedCurrentPlusTwo";
	public static final String SHARED_SHADOW_START_DATE = "SharedshadowStartDate";
	public static final String SHARED_BENCH_START_DATE = "SharedbenchStartDate";
	public static final String SHARED_BILLING_START_DATE = "SharedbillingStartDate";
	public static final String SHARED_BILLING_END_DATE = "SharedbillingEndDate";
	public static final String SHARED_LAST_WORKING_DATE = "SharedlastWorkingDate";
	public static final String SHARED_SHADOW_RESOURCE_TYPE = "SharedShadowResourceType";
	public static final String SHARED_EMPLOYEE_STATUS = "SharedemployeeStatus";
	public static final String SHARED_SHARED_PERCENTAGE = "SharedpercentUtilization";
	public static final String SHARED_ACCOUNT = "SharedAccount";
	public static final String SHARED_PROJECT = "SharedProject";
	public static final String SHARED_COORDINATOR_ECODE = "SharedCoordinatorEcode";
	public static final String SHARED_REMARKS = "SharedRemarks";
	public static final String SHARED_BILLABLE_HOURS = "SharedbillableHours";
	public static final String SHARED_EMPLOYEE_ROLE = "SharedEmployeeRole";
	public static final String SHARED_MONTH_HOURS ="SharedMonthHours";
	public static final String SHARED_VERTICAL = "SharedVertical";
	public static final String VIEW_MODE = "viewMode";
	public static final String XLSX = ".xlsx";
	public static final String SUCCESS = "success";
	public static final String SELECTED_MONTH = "?selectedMonth=";
	public static final String CONTENT_DISPOSITION = "Content-Disposition";
	public static final String EXCEL_HEADER = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	//Billing util constants
	public static final String BLANK = "";
	public static final String ECODE = "Ecode";
	public static final String NAME = "Name";
	public static final String MANAGER_ECODE_HEADER = "Manager Ecode";
	public static final String MANAGER_NAME_HEADER = "Manager Name";
	public static final String LEAD_ECODE_HEADER = "Lead Ecode";
	public static final String LEAD_NAME_HEADER = "Lead Name";
	public static final String ACCOUNT_HEADER = "Account";
	public static final String PROJECT_HEADER = "Project";
	public static final String CURRENT_HEADER = "Billing / Shadow / Bench - Current";

	public static final String SHADOW_RESOURCE_TYPE_HEADER = "Shadow Resource Type";
	public static final String SHADOW_START_DATE_HEADER =  "Shadow Start Date";
	public static final String BILLING_START_DATE_HEADER =  "Billing Start Date";
	public static final String EMPLOYEE_STATUS_HEADER = "Employee Status";
	public static final String LAST_WORKING_DATE_HEADER = "Last Working Day";
	public static final String BILLING_END_DATE_HEADER = "Billing End Date";
	public static final String PERCENT_UTILIZATION_HEADER = "Percentage Utilization Per Month";
	public static final String SHARED_STATUS_HEADER = "Shared Status";
	public static final String REMARKS_HEADER =  "Remarks";
	public static final String BILLING_DETAILS =  "Billing Details";
	public static final String[] tableHeaderFinanceBilling = {ECODE, NAME, MANAGER_ECODE_HEADER, MANAGER_NAME_HEADER,LEAD_ECODE_HEADER,LEAD_NAME_HEADER, "DOJ", ACCOUNT_HEADER, PROJECT_HEADER, CURRENT_HEADER,"Month Hours","Billable Hours",SHADOW_RESOURCE_TYPE_HEADER, SHADOW_START_DATE_HEADER, "Bench Start Date", BILLING_START_DATE_HEADER, EMPLOYEE_STATUS_HEADER, LAST_WORKING_DATE_HEADER, BILLING_END_DATE_HEADER, PERCENT_UTILIZATION_HEADER, SHARED_STATUS_HEADER, "Vertical", REMARKS_HEADER ,"Account Movement Date"};
	public static final String[] tableHeaderHrBilling = {ECODE, NAME, MANAGER_ECODE_HEADER, MANAGER_NAME_HEADER, "Designation", "DOJ", "Experience", "Skill", ACCOUNT_HEADER, PROJECT_HEADER, CURRENT_HEADER, "Billing / Shadow / Bench - Current +1 month", "Billing / Shadow / Bench - Current + 2 month", SHADOW_RESOURCE_TYPE_HEADER, SHADOW_START_DATE_HEADER, "Bench Start Date", BILLING_START_DATE_HEADER, EMPLOYEE_STATUS_HEADER, LAST_WORKING_DATE_HEADER, BILLING_END_DATE_HEADER, SHARED_STATUS_HEADER,"Allocation Status" , REMARKS_HEADER  , PERCENT_UTILIZATION_HEADER,"Coordinator Ecode", "Coordinator Name" , LEAD_ECODE_HEADER, LEAD_NAME_HEADER, "Account Movement Date" };
	public static final String[] tableHeaderManagerBilling = {SHARED_STATUS_HEADER, ECODE, NAME, MANAGER_ECODE_HEADER, MANAGER_NAME_HEADER, "Coordinator Ecode", "Coordinator Name", LEAD_ECODE_HEADER, LEAD_NAME_HEADER, "Designation", "DOJ", "Experience", "Skill", ACCOUNT_HEADER, PROJECT_HEADER, CURRENT_HEADER, "Billing / Shadow / Bench - Current +1 month", "Billing / Shadow / Bench - Current + 2 month", EMPLOYEE_STATUS_HEADER, LAST_WORKING_DATE_HEADER, "Allocation Status" , "Bench Start Date(YYYY-MM-DD)", SHADOW_RESOURCE_TYPE_HEADER, SHADOW_START_DATE_HEADER, BILLING_START_DATE_HEADER, BILLING_END_DATE_HEADER, PERCENT_UTILIZATION_HEADER, "Working Hours", "Employee Role", "Vertical",REMARKS_HEADER };
}