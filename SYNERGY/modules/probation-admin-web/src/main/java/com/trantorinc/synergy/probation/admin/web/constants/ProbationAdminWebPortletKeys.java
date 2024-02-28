package com.trantorinc.synergy.probation.admin.web.constants;

import java.util.Arrays;
import java.util.List;

/**
 * @author sachin.goyal
 */
public class ProbationAdminWebPortletKeys {

	public static final String PROBATIONADMINWEB =
		"com_trantorinc_synergy_probation_admin_web_ProbationAdminWebPortlet";

	public static final String SUBJECT_SUBMITTED = "Feedback Received : Probation Review";
	public static final String BODY_SUBMITTED = "Dear {0},<br><br>Thank you for submitting ''Probation Feedback form'' for {1} with status ''{2}''.<br><br>Regards,<br>Synergy Admin";
	public static final String SUBJECT_CLOSED = "Closed : Probation Review";
	public static final String BODY_CLOSED = "Dear {0},<br><br>Probation Review for ''{1}'' has been Closed.<br><br>For more information, please contact Team HR-Trantor.<br><br>Regards,<br>Synergy Admin";

	public static final List<String> HEADERS_COMPLETED_EXPORT = Arrays.asList(
			"Employee Code","Employee Name","Employee Account", "Manager Email","Probation Status", "Probation Start Date (yyyy-mm-dd)",
			"Completion Date (yyyy-mm-dd)", "Manager Comments");

	public static final List<String> HEADERS_PENDING_EXPORT = Arrays.asList(
			"Employee Code","Employee Name", "Employee Account","Manager Email","Probation Status","Probation Start Date (yyyy-mm-dd)");
}