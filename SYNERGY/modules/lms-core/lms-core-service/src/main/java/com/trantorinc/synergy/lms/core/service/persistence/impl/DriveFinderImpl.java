package com.trantorinc.synergy.lms.core.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.trantorinc.synergy.lms.core.model.Drive;
import com.trantorinc.synergy.lms.core.model.impl.DriveImpl;
import com.trantorinc.synergy.lms.core.service.DriveLocalServiceUtil;
import com.trantorinc.synergy.lms.core.service.persistence.DriveFinder;
import org.osgi.service.component.annotations.Component;

import java.util.List;


@Component(
        service = DriveFinder.class
)
public class DriveFinderImpl extends DrivePersistenceImpl implements DriveFinder {

    public List<Drive> findByFolderName(String folderName)
    {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DriveImpl.class, PortalClassLoaderUtil.getClassLoader());
        dynamicQuery.add(PropertyFactoryUtil.forName("folderName").eq(folderName));
        return DriveLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

}

