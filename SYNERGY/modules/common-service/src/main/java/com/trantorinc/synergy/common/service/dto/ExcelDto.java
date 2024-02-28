package com.trantorinc.synergy.common.service.dto;

import java.util.List;

public class ExcelDto {
    private String sheetName;
    private List<String> headers;
    private List<List<String>> data;
    private List<String> total;
    private List<Integer> headersToWidth;
    private List<Integer> columnsToWidth;
    private Integer sheetIndex;

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public List<List<String>> getData() {
        return data;
    }

    public void setData(List<List<String>> data) {
        this.data = data;
    }

    public Integer getSheetIndex() {
        return sheetIndex;
    }

    public void setSheetIndex(Integer sheetIndex) {
        this.sheetIndex = sheetIndex;
    }

    public List<String> getTotal() {
        return total;
    }

    public void setTotal(List<String> total) {
        this.total = total;
    }

    public List<Integer> getHeadersToWidth() {
        return headersToWidth;
    }

    public void setHeadersToWidth(List<Integer> headersToWidth) {
        this.headersToWidth = headersToWidth;
    }

    public List<Integer> getColumnsToWidth() {
        return columnsToWidth;
    }

    public void setColumnsToWidth(List<Integer> columnsToWidth) {
        this.columnsToWidth = columnsToWidth;
    }
}
