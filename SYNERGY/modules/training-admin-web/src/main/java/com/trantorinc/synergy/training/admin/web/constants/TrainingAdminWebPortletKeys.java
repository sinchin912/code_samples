package com.trantorinc.synergy.training.admin.web.constants;

import java.util.Arrays;
import java.util.List;

/**
 * @author sachin.goyal
 */
public class TrainingAdminWebPortletKeys {

	public static final String TRAININGADMINWEB =
		"com_trantorinc_synergy_training_admin_web_TrainingAdminWebPortlet";

	public static final List<String> HEADERS_EMPLOYEE_EXPORT = Arrays.asList(
			"Ecode","Name","Account","Project",
			"Designation","DOJ", "Experience", "Skills","Type",
			"DOB","Band","Location","Mobile",
			"Manager Ecode","Manager Name",
			"Reviewer Ecode","Reviewer Name",
			"Coordinator Ecode", "Coordinator Name");
}
