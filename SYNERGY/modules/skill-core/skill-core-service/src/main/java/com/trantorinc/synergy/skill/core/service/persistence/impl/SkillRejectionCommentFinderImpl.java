package com.trantorinc.synergy.skill.core.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.skill.core.model.SkillRejectionComment;
import com.trantorinc.synergy.skill.core.model.impl.SkillRejectionCommentImpl;
import com.trantorinc.synergy.skill.core.service.SkillRejectionCommentLocalServiceUtil;
import com.trantorinc.synergy.skill.core.service.persistence.SkillRejectionCommentFinder;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        service = SkillRejectionCommentFinder.class
)

public class SkillRejectionCommentFinderImpl extends SkillRejectionCommentPersistenceImpl implements SkillRejectionCommentFinder {

    @Override
    public List<SkillRejectionComment> findRejectionCommentByEcode(String ecode) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SkillRejectionCommentImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("ecode").eq(ecode));
        return SkillRejectionCommentLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
}
