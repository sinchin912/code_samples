package com.trantorinc.synergy.notice.web.constants;

/**
 * @author saurabh.kumar
 */
public class NoticeWebPortletKeys {

	public static final String NOTICEWEB =
		"com_trantorinc_synergy_notice_web_NoticeWebPortlet";
	public static final String ONE ="1";
	public static final String CLEARANCES_COMPLETED= "Clearance Completed";
	public static final String INITIATE_RESIGNATION_EMAIL_BODY = "Dear HR,<br><br> Please note that Employee ''{0}'' has submitted Notice of Resignation.<br><br>" +
			"E-Code - {1} <br> Account - {2} <br> Date of Resign - {3} <br> Reason - {4} <br><br> You are required to update notice period and post that manager will be required to confirm on last working day. For more details, please <a href=''{5}''>click here</a>. <br><br>" +
			"Thanks & Regards,<br>Synergy Admin";
	public static final String INITIATE_RESIGNATION_EMAIL_SUBJECT = "Notice of Resignation - {0}  {1}";

	public static final String WITHDRAW_INITIATED_EMAIL_SUBJECT="Withdraw Resign Request - {0}  {1}";
	public static final String WITHDRAW_INITIATED_EMAIL_BODY="Dear HR,<br><br> Employee {0} with E-Code {1} wants to withdraw the resignation submitted. " +
			"Please <a href=''{2}''>click here</a> to take the necessary action.<br><br>Thanks & Regards,<br>HR Team";
	public static final String MANAGER_CLEARANCE_EMAIL_SUBJECT="Manager Clearance form - {0} {1}";
	public static final String MANAGER_CLEARANCE_EMAIL_BODY="Dear  {0},<br><br>Clearance Form for employee {1} with ecode {2} has been generated.<br>Kindly submit the form earliest by 4 pm today." +
			"<br><a href=''{3}''>Click Here</a> to go to the clearance form.<br><br> Thanks & Regards, <br>HR Team";
	public static final String RESIGNATION_ID= "resignationId";
	public static final String NOT_CONFIRMED_BY_HR ="Not confirmed by HR";
}