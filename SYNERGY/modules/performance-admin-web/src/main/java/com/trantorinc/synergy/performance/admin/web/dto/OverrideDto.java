package com.trantorinc.synergy.performance.admin.web.dto;

import java.util.List;

public class OverrideDto {
    private List<ReviewDto> reviewList;
    private List<KpiDto> kpiList;

    public List<ReviewDto> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<ReviewDto> reviewList) {
        this.reviewList = reviewList;
    }

    public List<KpiDto> getKpiList() {
        return kpiList;
    }

    public void setKpiList(List<KpiDto> kpiList) {
        this.kpiList = kpiList;
    }
}
