create table PERFORMANCE_Kpi (
	kpiId LONG not null primary key,
	ecode VARCHAR(75) null,
	account VARCHAR(75) null,
	managerEmail VARCHAR(75) null,
	reviewerEmail VARCHAR(75) null,
	accountPrimary BOOLEAN,
	updateDate DATE null,
	kpiSettingStatus BOOLEAN,
	rejectionComment VARCHAR(200) null
);

create table PERFORMANCE_KpiGuide (
	guideId LONG not null primary key,
	title VARCHAR(75) null,
	description TEXT null,
	attribute BOOLEAN,
	other BOOLEAN,
	mandatory BOOLEAN
);

create table PERFORMANCE_KpiLine (
	lineId LONG not null primary key,
	kpiId LONG,
	guideId LONG,
	title VARCHAR(75) null,
	description TEXT null,
	target TEXT null,
	createDate DATE null
);

create table PERFORMANCE_Review (
	reviewId LONG not null primary key,
	kpiId LONG,
	ecode VARCHAR(75) null,
	account VARCHAR(75) null,
	manager VARCHAR(75) null,
	assignee VARCHAR(75) null,
	assigned BOOLEAN,
	reviewStartDate DATE null,
	reviewState INTEGER,
	accountPrimary BOOLEAN,
	finalYearReview BOOLEAN,
	comment_ TEXT null,
	employeeComment TEXT null,
	managerComment TEXT null,
	improvementComment TEXT null,
	hrComment TEXT null,
	rating VARCHAR(75) null,
	achievements TEXT null,
	trainings TEXT null,
	excelledArea VARCHAR(2000) null,
	rolePlayed VARCHAR(2000) null,
	expectedDesignation VARCHAR(75) null,
	expectedSalary VARCHAR(75) null,
	readyReason VARCHAR(2000) null,
	reasonRecommendation VARCHAR(2000) null,
	promotionRecommendation BOOLEAN,
	criticalToAccount INTEGER,
	criticalToCompany INTEGER,
	replacement INTEGER,
	replacementDetail VARCHAR(2000) null,
	replacementReason VARCHAR(2000) null,
	replacementReasonOther BOOLEAN,
	stage1Date DATE null,
	stage2Date DATE null,
	stage3Date DATE null,
	stage4Date DATE null,
	stage5Date DATE null,
	firstSubmit BOOLEAN
);

create table PERFORMANCE_ReviewClose (
	closeId LONG not null primary key,
	reviewId LONG,
	requestBy VARCHAR(75) null,
	requestDate DATE null,
	actionDate DATE null,
	status INTEGER
);

create table PERFORMANCE_ReviewComment (
	commentId LONG not null primary key,
	reviewId LONG,
	author VARCHAR(75) null,
	description TEXT null,
	createdDate DATE null
);

create table PERFORMANCE_ReviewLine (
	lineId LONG not null primary key,
	reviewId LONG,
	guideId LONG,
	title VARCHAR(75) null,
	description TEXT null,
	target TEXT null,
	employeeRating VARCHAR(75) null,
	employeeComment TEXT null,
	managerRating VARCHAR(75) null,
	managerComment TEXT null,
	hrRating VARCHAR(75) null,
	hrComment TEXT null
);

create table PERFORMANCE_ReviewRollback (
	rollbackId LONG not null primary key,
	reviewId LONG,
	requestBy VARCHAR(75) null,
	requestDate DATE null,
	actionDate DATE null,
	status INTEGER
);

create table PERFORMANCE_ReviewTimeline (
	timelineId LONG not null primary key,
	reviewState INTEGER,
	stateName VARCHAR(75) null,
	endDate DATE null,
	finalYear BOOLEAN
);

create table PERFORMANCE_ReviewUpload (
	uploadId LONG not null primary key,
	reviewId LONG,
	attachmentName VARCHAR(75) null,
	fileId VARCHAR(75) null,
	createdDate DATE null
);