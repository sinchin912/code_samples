package com.trantorinc.synergy.orgchart.admin.web.dto;

import java.util.List;

public class OrgchartDto {

    private String name;
    private String title;
    private String className;
    private List<OrgchartDto> children;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<OrgchartDto> getChildren() {
        return children;
    }

    public void setChildren(List<OrgchartDto> children) {
        this.children = children;
    }

}
