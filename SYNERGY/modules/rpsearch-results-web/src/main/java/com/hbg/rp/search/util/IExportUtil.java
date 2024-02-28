package com.hbg.rp.search.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.portlet.ResourceResponse;

import org.apache.poi.ss.usermodel.Workbook;

import com.hbg.rp.search.exceptions.ShipmentTrackingException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author ravi.kumar
 */
public interface IExportUtil {

	Workbook generateExcel(Object excelData, String permittedGroups,long extRepId,boolean hasAllTerritories)  throws ParseException, SystemException, ShipmentTrackingException;

	String formatAmount(double amount);
	
	String formatCurrency(double amount);
		
	String exportFileName(Object exportObject);
	
	OutputStream writeExcel(ResourceResponse response, Object excelData, String permittedGroups,long extRepId,boolean hasAllTerritories) throws IOException, ParseException, SystemException, ShipmentTrackingException;
	
	String formatDate(Date date);
	
	OutputStream writeSearchResultExcel(ResourceResponse response, List<Object[]> searchList, String permittedGroups,String exportType,Long extRepId,boolean hasAllTerritories) throws IOException, ParseException, SystemException, PortalException ;
}
