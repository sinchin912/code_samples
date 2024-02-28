package com.trantorinc.synergy.game.core.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.game.core.model.Prize;
import com.trantorinc.synergy.game.core.model.impl.PrizeImpl;
import com.trantorinc.synergy.game.core.service.PrizeLocalServiceUtil;
import com.trantorinc.synergy.game.core.service.persistence.PrizeFinder;
import org.osgi.service.component.annotations.Component;

import java.util.List;


@Component(
        service = PrizeFinder.class
)
public class PrizeFinderImpl extends PrizePersistenceImpl implements PrizeFinder {

    public List<Prize> findPrizeByYear(int year)
    {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PrizeImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("year").eq(year));
        dynamicQuery.addOrder(OrderFactoryUtil.asc("sequence"));
        return PrizeLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

}

