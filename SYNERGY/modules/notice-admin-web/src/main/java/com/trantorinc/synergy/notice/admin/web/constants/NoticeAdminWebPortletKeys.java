package com.trantorinc.synergy.notice.admin.web.constants;

/**
 * @author saurabh.kumar
 */
public class NoticeAdminWebPortletKeys {

	public static final String NOTICEADMINWEB =
		"com_trantorinc_synergy_notice_admin_web_NoticeAdminWebPortlet";
	public static final String ZERO = "0";
	public static final String ONE = "1";
	public static final String NA ="NA";
	public static final String IMMEDIATE_TERMINATION ="ImmediateTerminate";
	public static final String CLEARANCES_COMPLETED= "Clearance Completed";
	public static final String REPLACEMENT_PLAN= "replacementPlan";
	public static final String KEY_TO_PROJECT="keyToProject";
	public static final String ELIGIBLE_FOR_REHIRE="eligibleForRehire";
	public static final String RESIGNATION_ID=  "resignationId";
	public static final String ROLE_TYPE= "roleType";

	public static final String MANAGER_COMMENT= "managerComment";
	public static final String IT_TICKET_REMARK="itTicketRemark";
	public static final String LUNCH_DEDUCTION_AMOUNT_STATUS="lunchDeductionAmtStatus";
	public static final String TERMINATION_NOTICE_PERIOD="terminationNoticePeriod";
	public static final String MVCPATH="mvcPath";
	public static final String  IT_TICKET="itTicket";
	public static final String OTHER_ISSUE="otherIssue";
	public static final String CLIENT_IMPACT="clientImpact";
	public static final String RATING="rating";
	public static final String HR_PUTS_NOTICE_PERIOD_EMAIL_SUBJECT="Approve Last Working Day - {0} {1}";
	public static final String HR_PUTS_NOTICE_PERIOD_EMAIL_BODY="Dear {0},<br><br>HR has updated notice period.<br><br>E-Code - {1} <br> Account - {2} <br> Date of Resign - {3} <br> Reason - {4} <br> Last Working Day - {5} <br><br> As a manager, you can approve the last working day calculated on basis of same. <br><br>" +
			"In case any changes are needed in last working day, same needs to be discussed with HR before approving the last working day.<br>For more details, please <a href=''{6}''>click here</a>.<br><br>Thanks & Regards,<br>HR Team";
	public static final String MANAGER_RESIGNATION_APPROVED_EMAIL_SUBJECT ="Resign has been approved by the Manager - {0} {1}";
	public static final String MANAGER_RESIGNATION_APPROVED_EMAIL_BODY ="Dear Employee,<br><br>Your resignation has been accepted and approved by {0}.<br><br>" +
			"Your Last Working Day with us will be {1}.<br> You can connect with your manager for additional questions.<br><br>Please go through separation policy in employee handbook.<br><br> We wish you luck in your future endeavors.<br><br> " +
			"Thanks & Regards,<br>HR Team";
	public static final String LWD_UPDATION_EMAIL_SUBJECT ="Last Working Date has been updated - {0} {1} ";
	public static final String LWD_UPDATION_EMAIL_BODY="Dear Employee,<br><br>Your Last Working Date has been updated to {0}. You can connect with your manager for additional questions." +
			"<br><br>Thanks & Regards,<br>HR Team";
	public static final String NO_IT_ASSETS_ISSUED ="Note  : No assets assigned <br>";
	public static final String IT_ASSESTS_SUBMISSION_EMAIL_SUBJECT ="List of IT Assets to be published - {0} {1}";
	public static final String IT_ASSESTS_SUBMISSION_EMAIL_BODY="Hi IT Team,<br><br>You are requested to provide the list of IT Assets with {0}  {1} as we need to initiate exit accordingly on the last working day.<br><br> Kindly submit the list of IT Assets with the employee by clicking<a href=''{2}''> here</a> and take the necessary action on the clearance forms generated." +
			"<br><br>Thanks & Regards,<br>HR Team";
	public static final String IT_ASSESTS_SUBMITTED_EMAIL_SUBJECT="Review the list of IT assets to be submitted before leaving Trantor - {0} {1}";
	public static final String IT_ASSESTS_SUBMITTED_EMAIL_BODY="Dear Employee,<br><br>Please review below the list of assets to be submitted before leaving Trantor.<br><br>{0}" +
			"<br>In case of any mis match in asset list, kindly get in touch with IT Team.<br><br>Thanks & Regards,<br>HR Team";
	public static final String IMMEDIATE_IT_ASSESTS_SUBMITTED_EMAIL_SUBJECT="Review the list of IT Assets assigned to {0} {1}";
	public static final String IMMEDIATE_IT_ASSESTS_SUBMITTED_EMAIL_BODY="Dear HR,<br><br>Please review the list of assets assigned to emp {0} {1}.<br><br>{2}<br><br>Thanks & Regards,<br>HR Team";

	public static final String WITHDRAW_RESIGN_REQUEST_APPROVED_EMAIL_SUBJECT="You have been retained - {0} {1}";
	public static final String WITHDRAW_RESIGN_REQUEST_APPROVED_EMAIL_BODY="Dear {0},<br><br>We are pleased to inform you that you have been retained at Trantor with your consent. With this retention, your separation process with the company stands null and void. We would request you to acknowledge this email. We look forward to continuous collaboration with you. Have a happy journey at Trantor! <br><br>Thanks & Regards,<br>HR Team";
	public static final String WITHDRAW_RESIGN_REQUEST_REJECTED_EMAIL_SUBJECT="Your Withdraw Resign Request has been Declined - {0} {1}";
	public static final String WITHDRAW_RESIGN_REQUEST_REJECTED_EMAIL_BODY="Dear Employee,<br><br>Your Withdraw Resign Request has been declined by the HR.You can connect with your manager or HR for further questions.<br><br>Thanks & Regards,<br>HR Team";
	public static final String CLEARANCE_COMPLETED_EMAIL_SUBJECT="Exit Clearance completed - {0} {1}";
	public static final String CLEARANCE_COMPLETED_EMAIL_BODY="Dear HR team<br><br>Exit clearances have been completed for {0}. Click <a href=''{1}''>Here</a> to view consolidated clearance form.<br><br>Thanks & Regards,<br>HR Team";


	public static final String FINANCE_CLEARANCE_EMAIL_SUBJECT="Finance Clearance form - {0} {1}";
	public static final String FINANCE_CLEARANCE_EMAIL_BODY="Hi Finance Team,<br><br>Clearance Form for employee {0} with ecode {1} has been generated.<br>Kindly submit the form at the earliest, preferably today." +
			"<br>Click <a href=''{2}''>Here</a> to go to the clearance form.<br><br> Thanks & Regards, <br>HR Team";
	public static final String ADMIN_CLEARANCE_EMAIL_SUBJECT="Admin Clearance form - {0} {1}";
	public static final String ADMIN_CLEARANCE_EMAIL_BODY="Hi Admin Team,<br><br>Clearance Form for employee {0} with ecode {1} has been generated.<br>Kindly submit the form at the earliest, preferably today." +
			"<br>Click <a href=''{2}''>Here</a> to go to the clearance form.<br><br> Thanks & Regards, <br>HR Team";
	public static final String MANAGER_CLEARANCE_EMAIL_SUBJECT="Manager Clearance form - {0} {1}";
	public static final String MANAGER_CLEARANCE_EMAIL_BODY="Dear {0},<br><br>Clearance Form for employee {1} with ecode {2} has been generated.<br>Kindly submit the form earliest by 4 pm  today." +
			"<br>Click <a href=''{3}''>Here</a> to go to the clearance form.<br><br> Thanks & Regards, <br>HR Team";
	public static final String IT_CLEARANCE_EMAIL_SUBJECT="IT Clearance form - {0} {1}";
	public static final String IT_CLEARANCE_EMAIL_BODY="Hi IT Team,<br><br>Clearance Form for employee {0} with ecode {1} has been generated.<br>Kindly submit the form by <a href=''{2}''>clicking here</a>." +
			"<br><br> Thanks & Regards, <br>HR Team";
	public static final String HR_CLEARANCE_EMAIL_SUBJECT="HR Clearance form - {0} {1}";
	public static final String HR_CLEARANCE_EMAIL_BODY="Hi HR Team,<br><br>Clearance Form for employee {0} with ecode {1} has been generated.<br>Kindly submit the form by clicking <a href=''{2}''> here</a>." +
			"<br><br> Thanks & Regards, <br>HR Team";
	public static final String MANAGER_MARKED_ABSCONDED_EMAIL_SUBJECT="Employee {0} {1} - Absconded";
	public static final String MANAGER_MARKED_ABSCONDED_EMAIL_BODY="Dear HR,<br><br>Manager ''{0}'' has marked employee ''{1}'' ''{2}'' as Absconded on Synergy wef tentative ''{3}''." +
			"<br>Kindly <a href=''{4}''>click here</a> to submit the details as required for this case.<br><br> Thanks & Regards, <br>HR Team";
	public static final String HR_MARKED_ABSCONDED_EMAIL_SUBJECT="Employee {0} {1} - Absconded";
	public static final String HR_MARKED_ABSCONDED_EMAIL_BODY="Dear Manager,<br><br>HR has marked employee ''{0}'' ''{1}'' as Absconded wef ''{2}'' on Synergy." +
			"<br>Kindly <a href=''{3}''>click here</a> to submit the details as required for this case.<br><br> Thanks & Regards, <br>HR Team";
	public static final String TERMINATION_EMAIL_SUBJECT="Employee {0} {1} - Separation Intimation";
	public static final String TERMINATION_EMAIL_BODY="Dear Manager,<br><br>HR has initiated notice of employee ''{0}'' ''{1}'' wef ''{2}'' and Last working day of employee will be ''{3}''." +
			"<br>Kindly <a href=''{4}''>click here</a> to submit the details as required for this case.<br><br> Thanks & Regards, <br>HR Team";
	public static final String EMPLOYEE_REPLACEMENT_EMAIL_SUBJECT="FYI - Employee Separation Notice";
	public static final String EMPLOYEE_REPLACEMENT_EMAIL_BODY="Dear AMs,<br><br>Following employee seperation has been initiated. You are requested to connect with Respective Project Manager to plan replacement as needed" +
			"<br><br>Ecode - {0}<br>Name - {1}<br>Account - {2}<br>Manager - {3}<br>Last Working Day  - {4}<br>Replacement Plan - {5}<br><br> Thanks & Regards, <br>HR Team";
	public static final String REOPEN_CLEARANCE_EMAIL_SUBJECT="Clearance Form reopened - {0} {1}";
	public static final String REOPEN_CLEARANCE_EMAIL_BODY="Hi,<br><br> Clearance form  in Exit System online has been reopened by HR for Employee {0}  {1}.<br><br>Kindly take the necessary action and submit the form at the earliest.<br><br>Thanks & Regards, <br>HR Team";
	public static final String LWD_CHANGED_AFTER_CLEARANCE_GENERATION_EMAIL_SUBJECT="Change in Last Working Day - {0} {1}";
	public static final String LWD_CHANGED_AFTER_CLEARANCE_GENERATION_EMAIL_BODY="Dear All,<br><br>This is to update you that last working day has been changed for employee {0} {1}.<br><br>Hence, already generated clearance forms are no longer valid.<br><br> Thanks & Regards, <br>HR team";
	public static final String ASSIGNEE_EMAIL_SUBJECT="Resignation case reassigned - {0} {1}";
	public static final String ASSIGNEE_CLEARANCE_GENERATED_EMAIL_BODY="Dear {0},<br><br>Last working day for ''{1}'' ''{2}'' is ''{3}'' . Employee''s manager clearance form has been reassigned to you for further action.<br><br>Click<a href=''{4}''> Here</a> to view the form and take the necessary action.<br><br> Thanks & Regards, <br>HR Team";
	public static final String ASSIGNEE_CLEARANCE_NOT_GENERATED_BODY="Dear {0},<br><br>Exit for ''{1}'' ''{2}''  is in process. Employee''s Exit has been assigned to you. You are requested to review separation details and fill the details in form as needed.<br><br>Click<a href=''{3}''> Here</a> to view the form and take the necessary action.<br><br> Thanks & Regards, <br>HR Team";

	public static final String HR_CLEARANCE_COMPLETED_EMAIL_SUBJECT ="Exit Clearance completion - {0} {1}";
	public static final String HR_CLEARANCE_COMPLETED_EMAIL_BODY = "Dear Employee,<br><br>This is to inform you that all your Exit clearance have been completed and your date of clearance completion {0}.<br><br>" +
			"Please note SLA of closure of Full and Final, payout of dues, if any and issual of relieving letter is 30 days from the date all pending assets have been submitted and all clearances have been completed i.e is {1}<br><br>" +
			"Here are details of leaves and details of amount recoverable:{2}<br><br>{3}<br><br>For any queries, please write to hr@trantorinc.com<br><br> Thanks & Regards, <br>HR Team ";
	public static final String RETAINED_EMAIL_SUBJECT ="Retained - {0} {1}";
	public static final String RETAINED_EMAIL_BODY ="Dear All,<br><br>Employee ''{0}'' with Ecode ''{1}'' has been retained. Clearance forms have been closed for this employee.<br><br>@IT Team is  requested to unblock the accesses.<br><br>@other teams, please mark employee active again in your records.<br><br>For more details, please connect with HR.<br><br> Thanks & Regards, <br>HR Team ";
	public static final String RECOVERY_TABLE_START = "<table style='border:1px solid black;'><thead><tr><th style='border:1px solid black;'>Item </th><th style='border:1px solid black;'>Recovery Amount</th><th style='border:1px solid black;'>Recovery to be done (Yes/No)</th></tr></thead><tbody>";
	public static final String ROW_START1 = "<tr><td style=\"border:1px solid black;\">";
	public static final String CELL_SEPERATOR1 = "</td><td style=\"border:1px solid black;\">";
	public static final String CELL_SEPERATOR2 = "</td><td style='border:1px solid black;color: red;'>";

	public static final String CELL_SEPERATOR = "</td><td style='border:1px solid black;padding-right: 15px;padding-left: 15px;'>";
	public static final String CELL_SEPERATOR_COLSPAN = "</td><td style='border:1px solid black;padding-right: 15px;padding-left: 15px;'colspan=4>";
	public static final String ROW_START_ROWSPAN= "<tr class='text-center'><td  style='border:1px solid black;'colspan=3>";


	public static final String LEAVE_TABLE_START = "<table style='border:1px solid black;'><tr>";
	public static final String LEAVE_TABLE_END = "</table>";

	public static final String LEAVE_DURING_NOTICE_PERIOD ="<td style='border:1px solid black;'rowspan='3'>Leaves During Notice Period ";
	public static final String LOP_DURING_NOTICE_PERIOD ="<td style='border:1px solid black;'rowspan='3'>LOP (Unapproved Leaves) During Notice Period  ";

	public static final String MONTHS ="Months";
	public static final Integer DEPARTMENT_IT =2;
	public static final Integer DEPARTMENT_HR =5;
	public static final Integer DEPARTMENT_ADMIN =3;
	public static final Integer DEPARTMENT_FINANCE =4;
	public static final String DAYS ="<tr><td style='border:1px solid black;padding-right: 15px;padding-left: 15px;'>No. of Days";
	public static final String DATE ="<tr><td style='border:1px solid black;padding-right: 15px;padding-left: 15px;'>Date";

	public static final String EARNED_LEAVE ="<tr><td style='border:1px solid black;padding-right: 15px;padding-left: 15px;'>Earned Leave balance";

	public static final String DESKTOP ="Desktop";
	public static final String DESKTOP_NAMESPACE ="Desktop";
	public static final String LAPTOP ="Laptop";
	public static final String LIST_START ="<li>";
	public static final String LIST_CLOSE ="</li>";
	public static final String LAPTOP_NAMESPACE ="Laptop";
	public static final String TOTAL_ROWS ="totalRows";
	public static final String REIMBURSEMENT_TOTAL_ROWS ="reimbursementTotalRows";
	public static final String COMMENT ="comment";
	public static final String COMMENT_IT ="commentIt";
	public static final String APPROVE ="approved";
	public static final String APPROVE_IT ="approvedIt";
	public static final String COMMENT_FINANCE ="commentFinance";
	public static final String APPROVED_FINANCE ="approvedFinance";
	public static final String MONITOR ="Monitor";
	public static final String MONITOR_NAMESPACE ="Monitor";
	public static final String TELEPHONE_MOBILE_SIMCARD ="Telephone/Mobile/Sim/Data Card";
	public static final String TELEPHONE_NAMESPACE ="Telephone";
	public static final String HEADPHONE ="Headphone";
	public static final String HEADPHONE_NAMESPACE ="Headphone";
	public static final String HEADSET ="Headset";
	public static final String HEADSET_NAMESPACE ="Headset";
	public static final String LAPTOP_BAG ="Laptop Bag";
	public static final String LAPTOP_BAG_NAMESPACE ="LaptopBag";
	public static final String CHARGER ="Charger";
	public static final String CHARGER_NAMESPACE ="Charger";
	public static final String MOUSE ="Mouse";
	public static final String MOUSE_NAMESPACE ="Mouse";


	public static final String STATIONARY ="Stationary";
	public static final String STATIONARY_NAMESPACE ="Stationary";
	public static final String KEYS ="Keys";
	public static final String KEYS_NAMESPACE ="Keys";
	public static final String LIBRARY ="Library";
	public static final String LIBRARY_NAMESPACE ="Library";
	public static final String SPORTS_EQUIPMENT ="Sports equipment issued";
	public static final String SPORTS_EQUIPMENT_NAMESPACE ="SportsEquipmentIssued";
	public static final String INFRASTRUCTURE_ISSUED ="Infrastructure Issued";
	public static final String INFRASTRUCTURE_ISSUED_NAMESPACE ="InfrastructureIssued";
	public static final String LUNCH_DEDUCTION ="Lunch Deduction";
	public static final String LUNCH_DEDUCTION_NAMESPACE ="LunchDeduction";
	public static final String ACCESS_CARD ="Access Card";
	public static final String ACCESS_CARD_NAMESPACE ="AccessCard";
	public static final String IDENTITY_CARD ="Identity Card";
	public static final String IDENTITY_CARD_NAMESPACE ="IdentityCard";
	public static final String FOOD_REIMBURSMENT ="Reimbursement for Advance Subsidised Food Deducted";


	public static final String ADMIN_RECOVERY ="<p style='font-weight:bold'> Admin Recovery</p>";
	public static final String IT_RECOVERY ="<p style='font-weight:bold'> IT Recovery</p>";
	public static final String FINANCE_RECOVERY ="<p style='font-weight:bold'> Finance Recovery</p>";
	public static final String HR_RECOVERY ="<p style='font-weight:bold'> HR Recovery</p>";

	public static final String RECOVERY_FOR_TRAVEL ="Recovery for travel under relocation policy in last one year";
	public static final String RECOVERY_FOR_TRAVEL_NAMESPACE ="RecoveryForTravel";
	public static final String RECOVERY_FOR_HOTEL ="Recovery for hotel stay under relocation policy in last one year";
	public static final String RECOVERY_FOR_HOTEL_NAMESPACE ="RecoveryForHotel";
	public static final String RECOVERY_FOR_INTERNATIONAL_TRAVEL ="Recovery for International travel (in previous 6 months)";
	public static final String RECOVERY_FOR_INTERNATIONAL_TRAVEL_NAMESPACE ="RecoveryForInternationalTravel";
	public static final String RECOVERY_FOR_EDUCATION ="Recovery for Further education Program (in previous 6 months)";
	public static final String RECOVERY_FOR_EDUCATION_NAMESPACE ="RecoveryForEducation";
	public static final String RECOVERY_FOR_BONUS ="Recovery for joining bonus paid to employee (in previous one year)";
	public static final String RECOVERY_FOR_BONUS_NAMESPACE  ="RecoveryForJoining";
	public static final String RECOVERY_FOR_NOTICE_PERIOD ="Recovery for Notice period buy out done on behalf of employee to previous employer (in previous one year)";
	public static final String RECOVERY_FOR_NOTICE_PERIOD_NAMESPACE ="RecoveryForNoticePeriod";
	public static final String TRAINING_AGGREMENT_TRAINEES ="Training agreement (for trainees)";
	public static final String RECOVERABLE_BONUS ="Recoverable bonus (paid in last one year)";
	public static final String NOTICE_PERIOD_RECOVERY ="Notice period recovery, if any";
	public static final String[] tableHeaders = {"Emp Code","Emp Name","Manager Code","Manager Name","Employee Status","Want to retain employee","Reason for Non-Retention","Replacement Plan","Key to Project", "Client impact","Performance Evaluation","Reason for Leaving (By Employee)","Reason for Leaving (By Manager)","Is employee eligible for rehire","Any other issue including but not limited to attitude, work timings, etc","Manager Comment","Notice Period","Notice Start Date","Final LWD"};
	public static final String[] quesHeaders = {"Timestamp","Email Address","Employee Code","Name","Complete Permanent Address (Including Pincode)","City You are currently working from?","Mobile No","Account/Project","Reporting Manager/Assignee Name","Last working day","Please indicate how you feel about the following applicable to your most recent position at Trantor. [Overall work experience at Trantor]","Please indicate how you feel about the following applicable to your most recent position at Trantor. [Perception of the Trantor's overall communication with employees and transparency in the system]","Please indicate how you feel about the following applicable to your most recent position at Trantor. [The opportunity to enroll in Training courses and workshops]","Please indicate how you feel about the following applicable to your most recent position at Trantor. [Your perception of the Trantor's ability to deal fairly with staff]","Please indicate how you feel about the following applicable to your most recent position at Trantor. [Overall Satisfaction Level]","Please elaborate on your top reason for leaving","Please describe why are you leaving Trantor","1. What first attracted you to join Trantor?","2. Would you work for Trantor again in the future","3. Please let us know the reason why you would not want to work for Trantor again, if indicated 'No' in question no 2 above","4. If a friend asked, would you recommend employment at Trantor?","5. Please let us know the reason why you would not want to recommend Trantor to your friends, if indicated 'No' in question no 4 above","6. What is the name of company you are going to join?","I. Designation in your new Company","II. Location of your new Company","III. Salary Hike in your new Company - Select One","7. What are your suggestions / feedback on the current processes and functions?"};
	public static final String PENDING =" Pending";
	public static final String COMPLETED =" Completed";
	public static final String ALL =" All ";
	public static final String APPROVAL =" Approval";
	public static final String MANAGER =" Manager ";
	public static final String HR =" Hr ";
	public static final String ADMIN =" Admin ";
	public static final String IT =" It ";
	public static final String FINANCE =" Finance ";
	public static final String CLEARANCE =" Clearance ";
	public static final String RETAINED =" Retained ";
	public static final String CLEARANCE_TO_BE_INITIATED ="Clearance to be initiated";
	public static final String RESIGNATION_CLEARANCE_GUIDELINE_EMAIL_SUBJECT="Exit Clearance - Guidelines  - {0} {1}";
	public static final String RESIGNATION_CLEARANCE_GUIDELINE_EMAIL_BODY="Dear Employee,<br><br>As ''{0}'' is your last working day, all your clearances related to your exit will be done through online Exit System in Synergy. Here are detailed guidelines for you.<br><br>" +
			"a. Employee-<br><ul><li>Fill Exit interview questionnaire  - <a href=''{1}''>click here</a><br></li><li>Check your attendance, apply leaves and get approvals from manager as applicable - <a href=''{2}''>click here</a><br></li><li>Download all important documents such as increment letters, salary slips etc<br></li></ul>" +
			"b. HR will initiate clearances once you confirm above three steps are completed, kindly make sure to close the above points by 12 noon tomorrow.<br><br>c.   As your email access may get blocked as soon as manager does his/her clearance<br>" +
			"<ul><li>Ensure you have filled the Employee Interview Questionnaire Form -  shared with you on email Before Manager Clearance</li><li>Ensure you have emailed yourself important documents such as increment letters, salary slips etc before manager clearance</li>" +
			"<li>Send your goodbye and/or thank you note, if you want to send the same. Please ensure that if case you are sending Goodbye and Thank You note to everyone, all such employees are marked in BCC and not in TO or CC email field</li></ul>" +
			"<br>d.   You will be issued provisional relieving letter on last working day<br><br> e.   You will be required to submit any pending assets with you before HR team processes your full and final, clear any dues and issue final relieving letter<br><br> f.   Pending assets may include any or all of following : " +
			"<br><ul><li>ID card <br></li><li>Access Card <br></li><li>Any assets issued by IT Team <br></li></li><li>Any assets issued by Admin team <br></li><li>Any assets issued by client <br></li><li>Hand signed copy of NDA</li></ul>" +
			"g. Once all pending assets are submitted and all clearances are completed, HR will process Full and final<br><br>h. Full and Final settlement will be issued within 30 days from date of completion of clearance formalities.<br><br>i. Soft copy of relieving letter will be issued once full and final is settled and recoveries, if any are paid. Hard copy of relieving letter will be issued when we receive the hand Signed scanned copy of the same as an acknowledgment." +
			" <br><br>Please connect with HR team if you have any further questions on this. <br><br> Thanks & Regards,<br>HR Team";
	public static final String EXIT_RESIGNATION_CLEARANCE_GUIDELINE_EMAIL_BODY="Dear Employee,<br><br>As ''{0}'' is your last working day, all your clearances related to your exit will be done through online Exit System in Synergy. Here are detailed guidelines for you.<br><br>" +
			"a. Employee-<br><ul><li>Check your attendance, apply leaves and get approvals from manager as applicable - <a href=''{1}''>click here</a><br></li><li>Download all important documents such as increment letters, salary slips etc<br></li></ul>" +
			"b. HR will initiate clearances once you confirm above three steps are completed, kindly make sure to close the above points by 12 noon tomorrow.<br><br>c.   As your email access may get blocked as soon as manager does his/her clearance<br>" +
			"<ul><li>Ensure you have emailed yourself important documents such as increment letters, salary slips etc before manager clearance</li>" +
			"<li>Send your goodbye and/or thank you note, if you want to send the same. Please ensure that if case you are sending Goodbye and Thank You note to everyone, all such employees are marked in BCC and not in TO or CC email field</li></ul>" +
			"<br>d.   You will be issued provisional relieving letter on last working day<br><br> e.   You will be required to submit any pending assets with you before HR team processes your full and final, clear any dues and issue final relieving letter<br><br> f.   Pending assets may include any or all of following : " +
			"<br><ul><li>ID card <br></li><li>Access Card <br></li><li>Any assets issued by IT Team <br></li></li><li>Any assets issued by Admin team <br></li><li>Any assets issued by client <br></li><li>Hand signed copy of NDA</li></ul>" +
			"g. Once all pending assets are submitted and all clearances are completed, HR will process Full and final<br><br>h. Full and Final settlement will be issued within 30 days from date of completion of clearance formalities.<br><br>i. Soft copy of relieving letter will be issued once full and final is settled and recoveries, if any are paid. Hard copy of relieving letter will be issued when we receive the hand Signed scanned copy of the same as an acknowledgment." +
			" <br><br>Please connect with HR team if you have any further questions on this. <br><br> Thanks & Regards,<br>HR Team";
	public static final String TERMINATION_CLEARANCE_GUIDELINE_EMAIL_SUBJECT="Exit Clearance - Guidelines  - {0} {1}";
	public static final String TERMINATION_CLEARANCE_GUIDELINE_EMAIL_BODY="Dear Employee,<br><br>As ''{0}'' is your last working day, all your clearances related to your exit will be done through the online Exit System in Synergy. Here are detailed guidelines for you.<br><br>" +
			"a. Employee-<br><ul><li>Check your attendance, apply leaves and get approvals from manager as applicable - <a href=''{1}''>click here</a><br></li><li>Download all important documents such as increment letters, salary slips etc<br></li></ul>" +
			"b.   HR will initiate clearances once you confirm above two steps are completed, kindly make sure to close the above points by 12 noon tomorrow.<br><br>c.   As your email access may get blocked as soon as manager does his/her clearance<br>" +
			"<ul><li>Ensure you have emailed yourself important documents such as increment letters, salary slips etc before manager clearance</li><li>Send your goodbye and/or thank you note, if you want to send the same. Please ensure that if case you are sending Goodbye and Thank You note to everyone, all such employees are marked in BCC and not in TO or CC email field</li></ul>" +
			"d. You will be issued provisional relieving letter on last working day<br><br>  e.  Pending assets may include any or all of following<br>" +
			"<ul><li>ID card <br></li><li>Access Card <br></li><li>Any assets issued by IT Team <br></li><li>Any assets issued by Admin team <br></li><li>Any assets issued by client <br></li><li>Hand signed copy of NDA</li></ul>" +
			"f. Once all pending assets are submitted and all clearances are completed, HR will process Full and final.<br><br>g. Full and Final settlement will be issued within 30 days from date of completion of clearance formalities.<br><br>h. Soft copy of relieving letter will be issued once full and final is settled and recoveries, if any are paid. Hard copy of relieving letter will be issued when we receive the hand Signed scanned copy of the same as an acknowledgment." +
			" <br><br>Please connect with HR team if you have any further questions on this. <br><br> Thanks & Regards, <br>HR Team";
	public static final String LMS_LINK="https://hms.trantorinc.com/login";

}