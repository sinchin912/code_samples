package com.trantorinc.synergy.skill.core.service.persistence.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.skill.core.model.Skill;
import com.trantorinc.synergy.skill.core.model.impl.SkillImpl;
import com.trantorinc.synergy.skill.core.service.SkillLocalServiceUtil;
import com.trantorinc.synergy.skill.core.service.persistence.SkillFinder;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        service = SkillFinder.class
)

public class SkillFinderImpl extends SkillPersistenceImpl implements SkillFinder {
    private static final String BLANK = "";
    @Override
    public List<Skill> findSkillsByEcode(String ecode) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SkillImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("ecode").eq(ecode));
        return SkillLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
    @Override
    public List<Skill> findSkillsByReviewer(String reviewerEmail) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SkillImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("reviewer").eq(reviewerEmail));
        return SkillLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
}

