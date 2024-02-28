package com.trantorinc.synergy.expense.web.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.trantorinc.synergy.expense.web.dto.ExpenseDto;

import javax.portlet.ActionRequest;
import javax.portlet.RenderRequest;
import java.util.List;

public interface ExpensePortletService {
    List<ExpenseDto> findDetails(RenderRequest request);

    void getExpenseFormDetails(ActionRequest request) throws PortalException;

    String getExpenseFile(String fileId);

    void submitExpenseDetail(ActionRequest request) throws PortalException;

}
