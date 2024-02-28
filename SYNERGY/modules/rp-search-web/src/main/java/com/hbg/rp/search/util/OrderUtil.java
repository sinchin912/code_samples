package com.hbg.rp.search.util;

import com.hbg.rp.search.constants.ApplicationConstant;
import com.hbg.rp.search.constants.ApplicationPropertyReader;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;


/**
 * @author ravi.kumar
 *
 */
@Component
public class OrderUtil implements IOrderUtil {

	private static String orderStatuses = ApplicationPropertyReader.getProperty("ORDER_STATUSES");

	/*
	 * Empty Constructor.
	 */
	public OrderUtil() {
		// default constructor
	}

	/**
	 * 
	 * @return
	 * @throws SystemException
	 */
	@Override
	public Map<String, Object> getPreRequisiteModel() throws SystemException {
		Map<String, Object> model = new HashMap<>();
		model.put(ApplicationConstant.PARAM_STATUSES, Arrays.asList(orderStatuses.split("\\s*,\\s*")));
		return model;
	}

}
