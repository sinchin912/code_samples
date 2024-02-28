package com.training.student.portlet;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.training.employee.model.Student;
import com.training.employee.service.StudentService;
import com.training.student.constants.StudentWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author anusha.n
 */
@Component(
		immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=StudentWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + StudentWebPortletKeys.STUDENTWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class StudentWebPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		renderRequest.setAttribute("totalStudents", _studentService.getStudentsCount());
		renderRequest.setAttribute("entries", _studentService.getStudents(0,10));

		super.doView(renderRequest, renderResponse);
	}

	private StudentService _studentService;


	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException, PortletException {

		resourceResponse.setContentType("application/json");

		List<Student> students = _studentService.getStudents(0, 10);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (Student student : students) {

			JSONObject studentJson = JSONFactoryUtil.createJSONObject();
			studentJson.put("name", student.getName());
			studentJson.put("dob", student.getDob());
			studentJson.put("emailAddress", student.getEmailAddress());
			studentJson.put("phoneNo", student.getPhoneNo());
			studentJson.put("address", student.getAddress());

			jsonArray.put(studentJson);
		}

		PrintWriter out = resourceResponse.getWriter();
		out.println(jsonArray.toString());
		out.flush();

		super.serveResource(resourceRequest, resourceResponse);
	}


	@Reference(unbind = "-")
	public void set_studentService(StudentService studentService) {

		_studentService = studentService;
	}
}