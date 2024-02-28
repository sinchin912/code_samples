package com.trantorinc.synergy.performance.admin.web.constants;

import java.util.Arrays;
import java.util.List;

/**
 * @author saurabh.kumar
 */
public class PerformanceAdminWebPortletKeys {

	public static final String PERFORMANCEADMINWEB =
		"com_trantorinc_synergy_performance_admin_web_PerformanceAdminWebPortlet";
	public static final String AY = "AY ";
	public static final String KPI_ID = "kpiId";
	public static final String PENDING = "Pending";

	public static final String 	ANNUAL = "Annual";
	public static final String 	RATING_FINAL_YEAR = "F";
	public static final String RATING_MID_YEAR = "M";
	public static final String 	PRIMARY_ACCOUNT = "P";

	public static final String 	SECONDARY_ACCOUNT = "S";
	public static final String 	COMPLETE = "Complete";
	public static final String FOR_DATE = "forDate";
	public static final String MID_YEAR = "Mid Year";
	public static final String ACTION_REQUIRED = "Action Required";
	public static final String NO_ACTION_REQUIRED = "No Action Required";
	public static final String REVIEW_ID = "reviewId";
	public static final String REVIEW_LINE_ID ="reviewLineId";
	public static final String BRACES = ") ";
	public static final String CONSISTENTLY_EXCEEDS_EXPECTATIONS = "Consistently Exceeds Expectations";
	public static final String SOMETIMES_EXCEEDS_EXPECTATIONS = "Sometimes Exceeds Expectations";
	public static final String CONSISTENTLY_MEETS_EXPECTATIONS = "Consistently Meets Expectations";
	public static final String INCONSISTENTLY_MEETS_EXPECTATIONS = "Inconsistently Meets Expectations";
	public static final String DOES_NOT_MEETS_EXPECTATIONS = "Does not Meet Expectations";

	public static final String TABLE_START = "<table style='border:1px solid black;'><thead><tr><th style='border:1px solid black;'>Emp. ID</th><th style='border:1px solid black;'>Employee name</th><th style='border:1px solid black;'>Account</th><th style='border:1px solid black;'>Review Type</th><th style='border:1px solid black;'>Account Type</th></tr></thead><tbody>";
	public static final String SUBJECT_KPIREJECT = "PRS - KPIs require update : Recommended by Manager";
	public static final String BODY_KPIREJECT = "Dear Employee,<br><br>Your Lead/Manager has recommendations on KPIs update. Please discuss the same with your lead/manager, update and incorporate the changes in your KPIs.<br><br>Regards,<br>Synergy Admin";
	public static final String SUBJECT_REQUEST_ROLLBACK = "Performance Review System - Manager \"{0}\" has requested for Rollback";
	public static final String BODY_REQUEST_ROLLBACK = "Dear HR,<br><br>Manager \"{0}\" has requested for rollback.<br><br>Following are the employees details mentioned below<br><br>{1}<br><br><a href=''{2}''>Click here</a> to view the request and take the action.<br><br>Regards,<br>Synergy Admin";
	public static final String SUBJECT_REQUEST_CLOSE = "Performance Review System - Review Form Closure Request";
	public static final String BODY_REQUEST_CLOSE = "Dear HR,<br><br>Manager \"{0}\" has requested to close the review form.<br><br>Following are the employees mentioned below<br><br>{1}<br><br><a href=''{2}''>Click here</a> to take an action.<br><br>Regards,<br>Synergy Admin";
	public static final String SUBJECT_CLOSE = "Performance Review System - Your Review Form has been closed";
	public static final String BODY_CLOSE = "Dear Employee,<br><br>Your review form has been closed on request by the manager.<br>Please discuss with your manager if required.<br><br>Regards,<br>Synergy Admin";
	public static final String SUBJECT_OVERRIDE = "{0} Performance Review - HR generated form in self review stage";
	public static final String BODY_OVERRIDE = "Dear Employee,<br><br>HR has generated your self review form. You are requested to complete your self review and submit the form.<br><br><a href=''{0}''>Click here</a> to go to the review form and take the necessary action.<br><br>Regards,<br>Synergy Admin";
	public static final String SUBJECT_MIDYEAR_OVERRIDE = "{0} Performance Review - HR generated form in manager review stage";
	public static final String BODY_MIDYEAR_OVERRIDE = "Dear Manager,<br><br>HR has reopened the form to Manager Review state.<br><a href=''{0}''>Click here</a> to go to the review form and take the necessary action.<br><br>Regards,<br>Synergy Admin";
	public static final String SUBJECT_REVIEW_LINE_REJECT = "{0} Performance Review -  Review and update your Review form";
	public static final String BODY_REVIEW_LINE_REJECT = "Dear Employee,<br><br>Your Lead/Manager has recommendations on your \"{0}\" review form with respect to KPI and/or self rating.<br>Please discuss with your lead/manager and incorporate the changes in your review form itself and submit again in 2 working days.<br><br>Please <a href=''{1}''>click here</a> to go to your review.<br><br>Regards,<br>Synergy Admin";
	public static final String SUBJECT_MANAGER_REVIEW = "{0} Performance Review - Manager Review is complete";
	public static final String BODY_MANAGER_REVIEW = "Dear Employee,<br><br>Manager Review for your review has been completed. For Primary Account - You can either accept the rating or \"Raise to HR\" if you are not satisfied with your rating.<br>Please <a href=''{0}''>click here</a> to view your review.<br><br>Regards,<br>Synergy Admin";
	public static final String BODY_MIDYEAR_MANAGER_REVIEW = "Dear Employee,<br><br>Manager Review for your review has been completed. In case you wish to meet your manager on ratings given \"Raise a meeting request\" on your review form, otherwise no action is needed.<br>Please <a href=''{0}''>click here</a> to view your review.<br><br>Regards,<br>Synergy Admin";
	public static final String SUBJECT_HR_REVIEW = "{0} Performance Review - HR Review for your assessment has been completed.";
	public static final String BODY_HR_REVIEW = "Dear Employee,<br><br>HR Review for your review has been completed.<br>Please <a href=''{0}''>click here</a> to view your review.<br><br>Regards,<br>Synergy Admin";
	public static final String SUBJECT_APPROVE_ROLLBACK_MANAGER = "Rollback Request/s approved by HR";
	public static final String BODY_APPROVE_ROLLBACK_MANAGER = "Dear Manager,<br><br>Your request for rollback of review of following resources to \"Manager Review Stage\" has been approved by HR.<br><br>{0}<br><br>Note - In case the form has to be sent back to \"Self Review Stage\", you can do so from the review form itself.<br><br>Please visit Synergy to view latest updates.<br><br>Regards,<br>Synergy Admin";
	public static final String SUBJECT_REJECT_ROLLBACK_MANAGER = "Rollback Request/s rejected by HR";
	public static final String BODY_REJECT_ROLLBACK_MANAGER = "Dear Manager,<br><br>HR has rejected the rollback request/s sent by you.<br><br>Following are the employees details mentioned below<br><br>{0}<br><br><a href=''{1}''>Click here</a> for more information and connect with HR (hr@trantorinc.com) for further queries.<br><br>Regards,<br>Synergy Admin";
	public static final String SUBJECT_REJECT_CLOSE = "Performance Review System - Review Form Closure Request(s) rejected by HR";
	public static final String BODY_REJECT_CLOSE = "Dear Manager,<br><br>HR has rejected the assessment form closure request/s sent by you.<br>Please discuss with HR if required.<br><br>Regards,<br>Synergy Admin";
	public static final List<String> Admin_Account_Table_Headers = Arrays.asList("ECode", "Employee Name","Account Type", "Assessment Type", "Assessment Stage", "Account Name", "Manager Email", "Reviewer Email", "Rating","Critical To Account", "Key For Retention", "Employee's Expected Designation", "Employee's Expected Salary", "Promotion Required", "Where resource has continuously excelled", "If and how resource is already playing role being promoted to", "Reason for recommendation", "Why is employee ready for next role");
	public static final List<String> adminSummaryTableHeaders = Arrays.asList("Review Stage", "Count");
	public static final List<String> kpiSummaryTableHeaders = Arrays.asList("Ecode", "Employee Name","Manager Name","Reviewer Name","Account","Type","Status");
	public static final int STAGE_MANAGER_REVIEW = 2;
	public static final String SUBJECT_ROLLBACK_EMPLOYEE = "{0} Performance Review - Assessment Rollback";
	public static final String BODY_ROLLBACK_EMPLOYEE = "Dear Employee,<br><br>Your {0} review was rollback to \"Self Review\" state. You have to complete this activity and resubmit it in 5 working days.<br>Please visit Synergy to view latest updates.<br><br>Regards,<br>Synergy Admin";

	public static final String SUBJECT_ROLLBACK_MANAGER = "{0} Performance Review - Assessment Rollback";
	public static final String BODY_ROLLBACK_MANAGER = "Dear Manager,<br><br>Your {0} review was rollback to \"Manager Review Stage\". You have to complete this activity and resubmit it in 5 working days.<br>Please visit Synergy to view latest updates.<br><br>Regards,<br>Synergy Admin";
	public static final String SUBJECT_REVIEW_ASSIGNED = "{0} Performance Review - Review Form Assigned";
	public static final String BODY_REVIEW_ASSIGNED =  "Dear {0},<br><br>{1} performance review of \"{2}\" has been assigned to you by manager for review.<br>Please ensure closure by {3} otherwise manager rating will be marked as default \"Inconsistently Meets Expectation\". <strong>Form once closed in system can not be opened again.</strong><br><br>Please <a href=''{4}''>click here</a> to start the review.<br><br>Regards,<br>Synergy Admin";

}