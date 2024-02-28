package com.trantorinc.synergy.probation.core.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.probation.core.model.ProbationLine;
import com.trantorinc.synergy.probation.core.model.impl.ProbationLineImpl;
import com.trantorinc.synergy.probation.core.service.ProbationLineLocalServiceUtil;
import com.trantorinc.synergy.probation.core.service.persistence.ProbationLineFinder;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        service = ProbationLineFinder.class
)
public class ProbationLineFinderImpl extends ProbationLinePersistenceImpl implements ProbationLineFinder {

    @Override
    public List<ProbationLine> findByProbationId(String probationId){
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProbationLineImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("probationId").eq(probationId));
        return ProbationLineLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

}
