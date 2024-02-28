package com.trantorinc.synergy.game.core.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.game.core.model.Santa;
import com.trantorinc.synergy.game.core.model.impl.SantaImpl;
import com.trantorinc.synergy.game.core.service.SantaLocalServiceUtil;
import com.trantorinc.synergy.game.core.service.persistence.SantaFinder;
import org.osgi.service.component.annotations.Component;

import java.util.List;


@Component(
        service = SantaFinder.class
)
public class SantaFinderImpl extends SantaPersistenceImpl implements SantaFinder {

    public List<Santa> findSantaByYear(int year) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SantaImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("year").eq(year));
        return SantaLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    public List<Santa> findByEcodeAndYear(String ecode, int year){
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SantaImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("ecode").eq(ecode));
        dynamicQuery.add(PropertyFactoryUtil.forName("year").eq(year));
        return SantaLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    public List<Santa> findBySantaEcodeAndYear(String ecode, int year){
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SantaImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("santaEcode").eq(ecode));
        dynamicQuery.add(PropertyFactoryUtil.forName("year").eq(year));
        return SantaLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

}

