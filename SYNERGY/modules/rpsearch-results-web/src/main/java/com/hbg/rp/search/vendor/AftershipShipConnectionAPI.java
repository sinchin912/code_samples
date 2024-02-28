/**
 * 
 */
package com.hbg.rp.search.vendor;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.springframework.stereotype.Component;

import com.liferay.portal.kernel.util.PropsUtil;

import aftership.classes.ConnectionAPI;

/**
 * @author iagoupov
 * AftershipShipConnectionAPI class.
 */
@Component
public class AftershipShipConnectionAPI implements IAftershipShipConnectionAPI {
	private final Log logger = LogFactoryUtil.getLog(this.getClass());
	
	private static final String HBG_AFTERSHIP_API_KEY = "hbg.aftership.api.key";
    
	/**
	 * Constructor AftershipShipConnectionAPI.
	 */
	public AftershipShipConnectionAPI() { }
	
	@Override
	public ConnectionAPI getAftershipShipConnectionAPI() {
		logger.info(" befor obtaining connection HBG_AFTERSHIP_API_KEY");
		return new ConnectionAPI(PropsUtil.get(HBG_AFTERSHIP_API_KEY)); 
	}
}
