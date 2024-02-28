package com.trantorinc.synergy.expense.core.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.expense.core.model.Expense;
import com.trantorinc.synergy.expense.core.model.impl.ExpenseImpl;
import com.trantorinc.synergy.expense.core.service.ExpenseLocalServiceUtil;
import com.trantorinc.synergy.expense.core.service.persistence.ExpenseFinder;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        service = ExpenseFinder.class
)
public class ExpenseFinderImpl extends ExpensePersistenceImpl implements ExpenseFinder {

    @Override
    public List<Expense> findByEcode(String ecode){
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ExpenseImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("ecode").eq(ecode));
        return ExpenseLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List<Expense> findByAssignee(String email)
    {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ExpenseImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("assignee").eq(email));
        return ExpenseLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List<Expense> findByManagerEmail(String email)
    {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ExpenseImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("approvingManager").eq(email));
        return ExpenseLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
}
