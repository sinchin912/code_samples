package com.trantorinc.synergy.expense.web.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.trantorinc.synergy.expense.web.constants.ExpenseWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import com.trantorinc.synergy.expense.web.dto.ExpenseDto;
import com.trantorinc.synergy.expense.web.service.ExpensePortletService;
import com.trantorinc.synergy.expense.web.service.ExpensePortletServiceImpl;
import org.osgi.service.component.annotations.Component;

import java.io.*;
import java.util.*;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;
import static com.trantorinc.synergy.common.service.util.UtilService.*;
import static com.trantorinc.synergy.expense.web.constants.ExpenseWebPortletKeys.*;

/**
 * @author saurabh.kumar
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=category.sample",
                "com.liferay.portlet.header-portlet-css=/css/main.css",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=ExpenseWeb",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.name=" + ExpenseWebPortletKeys.EXPENSEWEB,
                "javax.portlet.init-param.add-process-action-success-action=false",
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class ExpenseWebPortlet extends MVCPortlet {
    private static final Log log = LogFactoryUtil.getLog(ExpenseWebPortlet.class.getName());
    private ExpensePortletService expensePortletService = null;

    @Override
    public void init() throws PortletException {
        expensePortletService = new ExpensePortletServiceImpl();
        super.init();
    }

    @Override
    public void destroy() {
        expensePortletService = null;
        super.destroy();
    }

    @Override
    public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        List<ExpenseDto> list = expensePortletService.findDetails(request);
        request.setAttribute(EMP_EXPENSE_DETAILS, list);
        super.doView(request, response);
    }

    @ProcessAction(name = "expenseWorkflow")
    public void expenseWorkflow(ActionRequest request, ActionResponse response) throws PortalException {
        expensePortletService.getExpenseFormDetails(request);
        response.getRenderParameters().setValue("mvcPath", "/jsp/expense.jsp");
    }

    @ProcessAction(name = "submitExpense")
    public void submitExpense(ActionRequest request, ActionResponse response) throws IOException, PortalException {
        expensePortletService.submitExpenseDetail(request);
        redirect(request, response);
    }

    @Override
    public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
        Object dataToWrite = null;
        if (resourceRequest.getResourceID().equals("downloadExpenseBill")) {
            String fileId = ParamUtil.getString(resourceRequest, "fileId");
            dataToWrite = expensePortletService.getExpenseFile(fileId);
        } else if (resourceRequest.getResourceID().equals("downloadUserManual")) {
            dataToWrite = getUserManual(MODULE_EXPENSE, ROLE_EMPLOYEE);
        }
        try (PrintWriter writer = resourceResponse.getWriter()) {
            writer.print("{\"data\": \"" + dataToWrite + "\"}");
            writer.flush();
        } catch (IOException exception) {
            log.error(exception.getStackTrace()[0].getMethodName() + ":" + exception.getStackTrace()[0].getLineNumber() + ":" + exception.getMessage());
        }
    }
}
