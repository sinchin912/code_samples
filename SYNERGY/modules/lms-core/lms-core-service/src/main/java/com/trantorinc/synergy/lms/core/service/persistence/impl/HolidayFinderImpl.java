package com.trantorinc.synergy.lms.core.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.lms.core.model.Holiday;
import com.trantorinc.synergy.lms.core.model.impl.HolidayImpl;
import com.trantorinc.synergy.lms.core.service.HolidayLocalServiceUtil;
import com.trantorinc.synergy.lms.core.service.persistence.HolidayFinder;
import org.osgi.service.component.annotations.Component;

import java.util.List;
@Component(
        service = HolidayFinder.class
)
public class HolidayFinderImpl extends HolidayPersistenceImpl implements HolidayFinder {

    public List<Holiday> findHolidaysByYear(int year) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HolidayImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("holidayYear").eq(year));
        return HolidayLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

}

