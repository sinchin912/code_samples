package com.hbg.rp.search.jobs;

import com.hbg.rp.search.constants.ApplicationConstant;
import com.hbg.rp.service.BillToAccountLocalServiceUtil;
import com.hbg.rp.service.PortalMappingLocalServiceUtil;
import com.hbg.rp.service.ProductLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class NRPJob {

	private static final Log logger = LogFactoryUtil.getLog(NRPJob.class);

	//Below execute method will be caching every hour to reduce the db calls.
	@Scheduled(fixedRate = 3600000)
	public void execute() {
		long startTime = System.currentTimeMillis();
		logger.info("NRPJobs >>>>>startTime>>>>>>>" + startTime + "ms");
		BillToAccountLocalServiceUtil.getAccountListByJob();//BillToAccount job
		ProductLocalServiceUtil.getAllReportingGroupsByJob();//Product job
		PortalMappingLocalServiceUtil.getAllReportingGroupsByJob(ApplicationConstant.REPORTING_GROUP_KEY);//Portalmapping job for reporting group only 
		long endTime = System.currentTimeMillis();
		logger.info("NRPJobs >>>>>total time taken >>>>>>>" + (endTime - startTime) + "ms");
	}
}