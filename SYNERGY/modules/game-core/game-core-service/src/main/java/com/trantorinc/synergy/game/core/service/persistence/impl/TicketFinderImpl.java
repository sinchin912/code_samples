package com.trantorinc.synergy.game.core.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.game.core.model.Ticket;
import com.trantorinc.synergy.game.core.model.impl.TicketImpl;
import com.trantorinc.synergy.game.core.service.TicketLocalServiceUtil;
import com.trantorinc.synergy.game.core.service.persistence.TicketFinder;
import org.osgi.service.component.annotations.Component;

import java.util.List;


@Component(
        service = TicketFinder.class
)
public class TicketFinderImpl extends TicketPersistenceImpl implements TicketFinder {

    public List<Ticket> findTicketByYear(int year) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(TicketImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("year").eq(year));
        return TicketLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    public List<Ticket> findTicketByYearAndEcode(int year, String ecode) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(TicketImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("year").eq(year));
        dynamicQuery.add(PropertyFactoryUtil.forName("ecode").eq(ecode));
        return TicketLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    public List<Ticket> findTicketByYearAndNumber(int year, String ticketNumber) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(TicketImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("year").eq(year));
        dynamicQuery.add(PropertyFactoryUtil.forName("ticketNumber").eq(ticketNumber));
        return TicketLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
}

