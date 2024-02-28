package com.trantorinc.synergy.common.service.excel;

import com.trantorinc.synergy.common.service.dto.ExcelDto;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExcelService {

    private ExcelService(){
        // do nothing
    }

    public static void createWorkBook (ResourceResponse response, String filename, List<ExcelDto> sheets) throws IOException {
        Workbook workbook = new SXSSFWorkbook();
        CellStyle headerStyle = getHeaderStyle(workbook);
        CellStyle totalStyle = getTotalStyle(workbook);
        for(ExcelDto sheetDto : sheets){
            Sheet sheet = workbook.createSheet(sheetDto.getSheetName());

            List<Integer> columnToWidth = new ArrayList<>();
            Map<Integer,Integer> headersToWidthMap = new HashMap<>();

            if(null != sheetDto.getHeadersToWidth() && !sheetDto.getHeadersToWidth().isEmpty()){
                for(int i : sheetDto.getHeadersToWidth()){
                    headersToWidthMap.put(i,sheetDto.getHeaders().get(i).length()+3); //adding buffer of 3
                }
            }
            if(null != sheetDto.getColumnsToWidth() && !sheetDto.getColumnsToWidth().isEmpty()) {
                columnToWidth= sheetDto.getColumnsToWidth();
            }

            int rowNumber = 0;
            Row row = sheet.createRow(rowNumber);
            for (int count = 0; count < sheetDto.getHeaders().size(); count++) {
                Cell headerCell = row.createCell(count);
                headerCell.setCellStyle(headerStyle);
                headerCell.setCellValue(sheetDto.getHeaders().get(count));
            }
            for (List<String> sheetRow : sheetDto.getData()) {
                rowNumber++;
                row = sheet.createRow(rowNumber);
                for (int count = 0; count < sheetRow.size(); count++) {
                    String cellValue = String.valueOf(sheetRow.get(count)).trim();
                    if(cellValue.length() > 32765){ //excel text limit
                        cellValue = cellValue.substring(0,32760)+"...";
                    }
                    if(cellValue.equalsIgnoreCase("null")){
                        cellValue = "";
                    }
                    row.createCell(count).setCellValue(cellValue);
                }
            }

            if(null !=sheetDto.getTotal() && !sheetDto.getTotal().isEmpty()){
                rowNumber++;
                row = sheet.createRow(rowNumber);
                for (int count = 0; count < sheetDto.getTotal().size(); count++) {
                    Cell totalCell = row.createCell(count);
                    totalCell.setCellStyle(totalStyle);
                    totalCell.setCellValue(sheetDto.getTotal().get(count));
                }
            }

            SXSSFSheet xssfSheet = (SXSSFSheet) sheet;
            xssfSheet.trackAllColumnsForAutoSizing();
            for (int i = 0; i < sheetDto.getHeaders().size() ; i++) {
                if(columnToWidth.contains(i)) {
                    xssfSheet.setColumnWidth(i,20480); // (No. of char * 256)= 80*256=20480
                }else if(headersToWidthMap.containsKey(i)){
                    xssfSheet.setColumnWidth(i,headersToWidthMap.get(i)*256); // (No. of char * 256)
                } else{
                    xssfSheet.autoSizeColumn(i);
                }
            }

        }
        Map<String, Integer> sheetOrders = sheets.stream().collect(Collectors.toMap(ExcelDto::getSheetName, ExcelDto::getSheetIndex));
        for(Map.Entry<String, Integer> sheetOrder : sheetOrders.entrySet()){
            workbook.setSheetOrder(sheetOrder.getKey(), sheetOrder.getValue());
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setProperty("Content-Disposition", "attachment; filename="+filename+".xlsx");
        workbook.write(response.getPortletOutputStream());
        response.flushBuffer();
    }

    private static CellStyle getHeaderStyle(Workbook workbook) {
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    private static CellStyle getNumberStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
        return style;
    }

    private static CellStyle getCurrencyStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setDataFormat((short) 7);
        return style;
    }

    private static CellStyle getTotalStyle(Workbook workbook) {
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 10);
        font.setBold(true);
        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        return style;
    }
}
