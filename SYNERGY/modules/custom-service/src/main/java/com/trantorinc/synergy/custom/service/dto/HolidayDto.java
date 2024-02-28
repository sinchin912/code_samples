package com.trantorinc.synergy.custom.service.dto;

import java.util.Date;

public class HolidayDto {
    private String description;
    private Date holidayDate;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(Date holidayDate) {
        this.holidayDate = holidayDate;
    }
}
