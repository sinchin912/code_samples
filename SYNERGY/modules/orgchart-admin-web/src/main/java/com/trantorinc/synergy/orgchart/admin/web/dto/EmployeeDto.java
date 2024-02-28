package com.trantorinc.synergy.orgchart.admin.web.dto;


public class EmployeeDto {
	private String ecode;
	private String name;
	private String designation;
	private String email;
	private String manager;
	private String managerEcode;
	private String reviewer;
	private String reviewerEcode;
	private String subProject;

	public EmployeeDto(String ecode, String name, String email, String designation, String manager, String reviewer, String subProject) {
		this.ecode = ecode;
		this.name = name;
		this.manager = manager;
		this.designation = designation;
		this.email = email;
		this.reviewer = reviewer;
		this.subProject = subProject;
	}


	public EmployeeDto(String ecode, String name, String email, String designation, String manager, String reviewer, String managerEcode, String reviewerEcode) {
		this.ecode = ecode;
		this.name = name;
		this.manager = manager;
		this.designation = designation;
		this.email = email;
		this.reviewer = reviewer;
		this.managerEcode = managerEcode;
		this.reviewerEcode = reviewerEcode;
	}


	public String getEcode() {
		return ecode;
	}

	public void setEcode(String ecode) {
		this.ecode = ecode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public String getSubProject() { return subProject; }

	public void setSubProject(String subProject) { this.subProject = subProject; }
}
