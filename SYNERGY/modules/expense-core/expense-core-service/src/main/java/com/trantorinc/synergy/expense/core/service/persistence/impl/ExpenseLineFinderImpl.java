package com.trantorinc.synergy.expense.core.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.expense.core.model.ExpenseLine;
import com.trantorinc.synergy.expense.core.model.impl.ExpenseLineImpl;
import com.trantorinc.synergy.expense.core.service.ExpenseLineLocalServiceUtil;
import com.trantorinc.synergy.expense.core.service.persistence.ExpenseLineFinder;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        service = ExpenseLineFinder.class
)
public class ExpenseLineFinderImpl extends ExpenseLinePersistenceImpl implements ExpenseLineFinder {

    @Override
    public List<ExpenseLine> findByExpenseId(long expenseId)
    {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ExpenseLineImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("expenseId").eq(expenseId));
        return ExpenseLineLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

}
