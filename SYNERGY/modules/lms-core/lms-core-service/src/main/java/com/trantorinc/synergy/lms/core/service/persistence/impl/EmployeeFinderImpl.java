package com.trantorinc.synergy.lms.core.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.lms.core.model.Employee;
import com.trantorinc.synergy.lms.core.model.impl.EmployeeImpl;
import com.trantorinc.synergy.lms.core.service.EmployeeLocalServiceUtil;
import com.trantorinc.synergy.lms.core.service.persistence.EmployeeFinder;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component(
        service = EmployeeFinder.class
)
public class EmployeeFinderImpl extends EmployeePersistenceImpl implements EmployeeFinder {

    private static final String STATUS = "status";
    private static final String LMS_USER = "lmsUser";

    public List<String> findUniqueAccounts() {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(EmployeeImpl.class, PortalClassLoaderUtil.getClassLoader());
        Projection projection = ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property("account"));
        dynamicQuery.setProjection(projection);
        dynamicQuery.add(PropertyFactoryUtil.forName(STATUS).eq(true));
        dynamicQuery.add(PropertyFactoryUtil.forName(LMS_USER).eq(true));
        return EmployeeLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    public List<Employee> findAllEmployees() {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(EmployeeImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName(LMS_USER).eq(true));
        return EmployeeLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    public List<Employee> findManagerReporters(String manager)
    {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(EmployeeImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("manager").eq(manager));
        dynamicQuery.add(PropertyFactoryUtil.forName(STATUS).eq(true));
        dynamicQuery.add(PropertyFactoryUtil.forName(LMS_USER).eq(true));
        return EmployeeLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    public Employee findByEmail(String email) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(EmployeeImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("email").eq(email));
        List<Employee> employeeList = EmployeeLocalServiceUtil.dynamicQuery(dynamicQuery);
        if(!employeeList.isEmpty()) {
            return employeeList.get(0);
        } else {
            return null;
        }
    }

    public List<Employee> findByDoj(Date doj) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(EmployeeImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("doj").eq(doj));
        dynamicQuery.add(PropertyFactoryUtil.forName(STATUS).eq(true));
        dynamicQuery.add(PropertyFactoryUtil.forName(LMS_USER).eq(true));
        return EmployeeLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    public List<Object[]> findAnniversaries(Date today) {
        Session session = null;
        List<Object[]> employeeList = new ArrayList<>();
        try {
            session = openSession();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(today);
            int month = calendar.get(Calendar.MONTH)+1;
            int date = calendar.get(Calendar.DATE);
            String sql = "SELECT ecode, name , doj, fileId FROM lms_employee WHERE EXTRACT(DAY FROM doj) = "+date+" and EXTRACT(MONTH FROM doj) = "+month+" and status=1 and lmsUser=1";
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(true);
            employeeList =  q.list();
        } catch(Exception ex) {
            // do nothing
        } finally {
            closeSession(session);
        }
        return employeeList;
    }

    public List<Object[]> findBirthdays(Date today) {
        Session session = null;
        List<Object[]> employeeList = new ArrayList<>();
        try {
            session = openSession();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(today);
            int month = calendar.get(Calendar.MONTH)+1;
            int date = calendar.get(Calendar.DATE);
            String sql = "SELECT ecode, name, fileId FROM lms_employee WHERE EXTRACT(DAY FROM dob) = "+date+" and EXTRACT(MONTH FROM dob) = "+month+" and status=1 and lmsUser=1";
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(true);
            employeeList = q.list();
        } catch(Exception ex) {
            // do nothing
        } finally {
            closeSession(session);
        }
        return employeeList;
    }

}

