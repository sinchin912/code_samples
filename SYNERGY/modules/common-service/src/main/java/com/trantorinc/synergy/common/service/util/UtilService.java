package com.trantorinc.synergy.common.service.util;

import com.google.gson.Gson;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.apache.commons.io.FileUtils;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;

public class UtilService {

    private static final Log log = LogFactoryUtil.getLog(UtilService.class.getName());

    private UtilService(){
        // do nothing
    }

    public static Date getIstDate(){
        return Date.from(LocalDateTime.now(Clock.system(ZoneId.of("Asia/Calcutta"))).atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime getIstLocalDateTime(){
        return LocalDateTime.now(Clock.system(ZoneId.of("Asia/Calcutta")));
    }

    public static Date getStartOfDayDate(LocalDate localDate){
        return  Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date getEndOfDayDate(LocalDate localDate){
        return Date.from(localDate.atTime(23,59,59).atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date convertLocalDateTimeToDate(LocalDateTime localDateTime){
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime convertDateToLocalDateTime(Date date){
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static int getCurrentYear(){
        LocalDate today = getIstLocalDateTime().toLocalDate();
        int currentMonth = today.getMonthValue();
        int currentYear  = today.getYear();
        if(currentMonth < 4){
            currentYear = currentYear-1;
        }
        return currentYear;
    }
    public static LocalDate getFinancialStartDate(int year){
        return LocalDate.of(year,4,1);
    }
    public static LocalDate getFinancialEndDate(int year){
        return LocalDate.of(year+1,3,31);
    }
    public static LocalDate getMidFinancialEndDate(int year){
        return LocalDate.of(year,9,30);
    }

    public static String getPortalUrl(){
        String finalUrl;
        if(ENVIRONMENT.equalsIgnoreCase(PROD)){
            finalUrl = MessageFormat.format(URL_PORTAL, BLANK);
        } else {
            finalUrl = MessageFormat.format(URL_PORTAL, HYPHEN + ENVIRONMENT);
        }
        return finalUrl;
    }

    public static String convertToJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static String getBase64File(File file){
        String base64String = "";
        try{
            base64String = Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(file));
        } catch (IOException exception) {
            log.error(exception.getStackTrace()[0].getMethodName()+":" +exception.getStackTrace()[0].getLineNumber()+":"+ exception.getMessage());
        }
        return base64String;
    }

    public static void redirect(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException {
        ThemeDisplay td = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
        String portletName= td.getPortletDisplay().getPortletName();
        PortletURL portletURL = PortletURLFactoryUtil.create(PortalUtil.getHttpServletRequest(actionRequest),portletName,td.getLayout().getPlid(), PortletRequest.RENDER_PHASE);
        actionRequest.setAttribute(WebKeys.REDIRECT, portletURL.toString().split("\\?")[0]);
        actionResponse.sendRedirect(portletURL.toString().split("\\?")[0]);
    }

    public static String getLoggedUser(PortletRequest request){
        ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        User user = td.getUser();
        return user.getEmailAddress();
    }

    public static Set<String> getLoggedUserRoles(PortletRequest request){
        ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        User loggedUser = td.getUser();
        return loggedUser.getRoles().stream().map(Role::getName).collect(Collectors.toSet());
    }

    public static String getUserManual(String moduleName, String role) {
        role = role.substring(8);
        String base64String = BLANK;
        List<DLFileEntry> ls = DLFileEntryLocalServiceUtil.getDLFileEntries(-1, -1);
        long fileEntryId = 0L;
        for (DLFileEntry ts : ls) {
            if (ts.getFileName().equalsIgnoreCase(MessageFormat.format(USER_MANUAL_TEMPLATE, moduleName, role))) {
                fileEntryId = ts.getFileEntryId();
            }
        }
        try {
            FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEntryId);
            InputStream inputStream = DLFileEntryLocalServiceUtil.getFileAsStream(fileEntry.getPrimaryKey(), fileEntry.getVersion());
            byte[] bytesArray = new byte[(int) fileEntry.getSize()];
            inputStream.read(bytesArray);
            inputStream.close();
            byte[] encodeBase64 = Base64.getEncoder().encode(bytesArray);
            base64String = new String(encodeBase64, StandardCharsets.UTF_8);
        } catch (PortalException | IOException exception) {
            log.error(exception.getStackTrace()[0].getMethodName() + ":" + exception.getStackTrace()[0].getLineNumber() + ":" + exception.getMessage());
        }
        return base64String;
    }
    public static String displayCurrentAY(int currentYear) {
        int nextYear = currentYear + 1;
        return currentYear+HYPHEN+(BLANK+nextYear).substring(2);
    }
}
