package com.trantoric.synergy.expense.admin.web.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.trantoric.synergy.expense.admin.web.constants.ExpenseAdminWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import com.trantoric.synergy.expense.admin.web.service.ExpenseAdminWebPortletService;
import com.trantoric.synergy.expense.admin.web.service.ExpenseAdminWebPortletServiceImpl;
import com.trantorinc.synergy.common.service.constant.AppConstantService;
import com.trantorinc.synergy.expense.core.model.Expense;
import com.trantorinc.synergy.expense.core.service.ExpenseLocalServiceUtil;
import com.trantorinc.synergy.lms.core.model.Employee;
import com.trantorinc.synergy.lms.core.service.EmployeeLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;
import static com.trantorinc.synergy.common.service.util.UtilService.*;

/**
 * @author saurabh.kumar
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=category.sample",
                "com.liferay.portlet.header-portlet-css=/css/main.css",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=ExpenseAdminWeb",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.name=" + ExpenseAdminWebPortletKeys.EXPENSEADMINWEB,
                "javax.portlet.init-param.add-process-action-success-action=false",
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class ExpenseAdminWebPortlet extends MVCPortlet {
    private static final Log log = LogFactoryUtil.getLog(ExpenseAdminWebPortlet.class.getName());
    private ExpenseAdminWebPortletService adminWebPortletService = null;

    @Override
    public void init() throws PortletException {
        adminWebPortletService = new ExpenseAdminWebPortletServiceImpl();
        super.init();
    }

    @Override
    public void destroy() {
        adminWebPortletService = null;
        super.destroy();
    }

    @Override
    public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
        Set<String> userRoles  = getLoggedUserRoles(request);
        List<Employee> employeeList = EmployeeLocalServiceUtil.findAllActiveEmployees();
        if (userRoles.contains(ROLE_MANAGER)) {
            List<Expense> managerEntryList = ExpenseLocalServiceUtil.findByManagerEmail(getLoggedUser(request));
            request.setAttribute("managerEditableEntries", adminWebPortletService.managerEditableEntries(managerEntryList, employeeList));
            request.setAttribute("managerViewEntries", adminWebPortletService.managerViewEntries(managerEntryList, employeeList));
        }
        if (userRoles.contains(ROLE_FINANCE)) {
            List<Expense> financeEntryList = ExpenseLocalServiceUtil.findByAssignee(AppConstantService.DL_FINANCE);
            request.setAttribute("financeEditableEntries", adminWebPortletService.financeEditableEntries(financeEntryList, employeeList));
            request.setAttribute("financeViewEntries", adminWebPortletService.financeViewEntries(financeEntryList, employeeList));
        }
        super.doView(request, response);
    }

    @ProcessAction(name = "expenseWorkflow")
    public void expenseWorkflow(ActionRequest request, ActionResponse response) throws PortalException  {
        adminWebPortletService.getExpenseFormDetails(request);
        response.getRenderParameters().setValue("mvcPath", "/jsp/expense.jsp");
    }

    @ProcessAction(name = "submitExpenseForm")
    public void submitExpenseForm(ActionRequest request, ActionResponse response) throws PortalException, IOException {
        adminWebPortletService.submitExpenseForm(request);
        redirect(request, response);
    }

    @Override
    public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
        Object dataToWrite = null;
        if (resourceRequest.getResourceID().equals("downloadExpenseBill")) {
            String fileId = ParamUtil.getString(resourceRequest, "fileId");
            dataToWrite = adminWebPortletService.getExpenseFile(fileId);
        }
        if (resourceRequest.getResourceID().equals("downloadUserManual")) {
            int action = ParamUtil.getInteger(resourceRequest, "roleAction");
            dataToWrite = getUserManual(MODULE_EXPENSE,  action ==1 ? ROLE_MANAGER : ROLE_FINANCE);
        }
        try (PrintWriter writer = resourceResponse.getWriter()) {
            writer.print("{\"data\": \"" + dataToWrite + "\"}");
            writer.flush();
        } catch (IOException exception) {
            log.error(exception.getStackTrace()[0].getMethodName() + ":" + exception.getStackTrace()[0].getLineNumber() + ":" + exception.getMessage());
        }
    }
}