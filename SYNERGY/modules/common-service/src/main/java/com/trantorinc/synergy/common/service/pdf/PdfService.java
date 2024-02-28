package com.trantorinc.synergy.common.service.pdf;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.lowagie.text.DocumentException;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.portlet.ResourceResponse;
import java.io.*;

public class PdfService {

    private PdfService(){
        // do nothing
    }

    private static final Log log = LogFactoryUtil.getLog(PdfService.class.getName());

    public static byte[] createPdf (String htmlString) throws IOException {
        byte[] arr = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlString);
            renderer.layout();
            renderer.createPDF(baos);
            baos.close();
            arr = baos.toByteArray();
            return arr;
        } catch (DocumentException exception) {
            log.error(exception.getStackTrace()[0].getMethodName()+":" +exception.getStackTrace()[0].getLineNumber()+":"+ exception.getMessage());
        }
        return  arr;
    }

    public static void downloadPdf (ResourceResponse response, String filename, byte[] arr) throws IOException {
            response.setContentType("application/pdf");
            response.setProperty("Content-Disposition", "attachment; filename="+filename+".pdf");
            response.getPortletOutputStream().write(arr);
            response.flushBuffer();
    }

}
