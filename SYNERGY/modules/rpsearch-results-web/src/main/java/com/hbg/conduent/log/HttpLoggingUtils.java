package com.hbg.conduent.log;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.springframework.ws.WebServiceMessage;
import org.springframework.xml.transform.TransformerObjectSupport;

/**
 * The com.hbg.conduent.log.HttpLoggingUtils is derived from TransformerObjectSupport.
 * Logs the Conduent request & response. 
 * 
 * @author Gurpreet.Singh
 *
 */
public class HttpLoggingUtils extends TransformerObjectSupport {

  private static final Log LOGGER = LogFactoryUtil.getLog(HttpLoggingUtils.class);

  private static final String NEW_LINE = System.getProperty("line.separator");

  private HttpLoggingUtils() {}
  
  /**
   * Logs the message along with Id.
   * 
   * @param id	Id of the message i.e. Client Request Message or Client Response Message.
   * @param webServiceMessage	
   */
  public static void logMessage(String id, WebServiceMessage webServiceMessage) {
    try {
      ByteArrayTransportOutputStream byteArrayTransportOutputStream =
          new ByteArrayTransportOutputStream();
      webServiceMessage.writeTo(byteArrayTransportOutputStream);

      String httpMessage = new String(byteArrayTransportOutputStream.toByteArray());
      LOGGER.debug(NEW_LINE + "----------------------------" + NEW_LINE + id + NEW_LINE
          + "----------------------------" + NEW_LINE + httpMessage + NEW_LINE);
    } catch (Exception e) {
      LOGGER.error("Unable to log HTTP message.", e);
    }
  }
}