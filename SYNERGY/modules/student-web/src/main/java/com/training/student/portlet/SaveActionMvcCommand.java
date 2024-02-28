package com.training.student.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.training.employee.service.StudentService;
import com.training.student.constants.StudentWebPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StudentWebPortletKeys.STUDENTWEB,
                "mvc.command.name=saveStudent" },

                service = MVCActionCommand.class)
public class SaveActionMvcCommand extends BaseMVCActionCommand {

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String name = ParamUtil.get(actionRequest, "name", "");
        Date dob = dateFormat.parse(ParamUtil.get(actionRequest, "dob", ""));
        String emailAddress = ParamUtil.get(actionRequest, "emailAddress", "0");
        String phoneNo = ParamUtil.get(actionRequest, "phoneNo", "");
        String address = ParamUtil.get(actionRequest, "address", "");

        _studentService.addStudent(name, address, emailAddress, phoneNo, dob);

        System.out.println("SaveActionMvcCommand.doProcessAction()");
    }

    private StudentService _studentService;

    @Reference(unbind = "-")
    public void set_studentService(StudentService studentService) {

        _studentService = studentService;
    }
}
