package com.trantorinc.synergy.hr.feedback.web.constants;

/**
 * @author sachin.goyal
 */
public class HrFeedbackWebPortletKeys {

	public static final String HRFEEDBACKWEB =
		"com_trantorinc_synergy_hr_feedback_web_HrFeedbackWebPortlet";
	public static final String SUBJECT_HR_FEEDBACK_RECEIVED = "Synergy - HR feedback received";
	public static final String BODY_HR_FEEDBACK_RECEIVED = "Dear HR,<br><br>{0}({1}) has submitted HR feedback on Synergy. Details as below<br>" +
			"<table style='border:1px solid black;'><tbody>" +
			"<tr><td style='border:1px solid black;'>Which area of HR you would like to give feedback on ?</td><td style='border:1px solid black;'>{2}</td></tr>" +
			"<tr><td style='border:1px solid black;'>How satisfied are you with query resolution by HR team ?</td><td style='border:1px solid black;'>{3}</td></tr>" +
			"<tr><td style='border:1px solid black;'>How satisfied are you with turnaround response time ?</td><td style='border:1px solid black;'>{4}</td></tr>" +
			"<tr><td style='border:1px solid black;'>How can we improve our process ?</td><td style='border:1px solid black;'>{5}</td></tr>" +
			"<tr><td style='border:1px solid black;'>Any other comments</td><td style='border:1px solid black;'>{6}</td></tr>" +
			"<tr><td style='border:1px solid black;'>HR Representative who helped you out</td><td style='border:1px solid black;'>{7}</td></tr></tbody></table>" +
			"<br><br>Regards,<br>Synergy Admin";

}