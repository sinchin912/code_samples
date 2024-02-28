create table LMS_Drive (
	driveId LONG not null primary key,
	folderName VARCHAR(75) null,
	folderId VARCHAR(75) null,
	filesCount INTEGER,
	updateDate DATE null
);

create table LMS_Employee (
	ecode VARCHAR(75) not null primary key,
	status BOOLEAN,
	employeeType VARCHAR(75) null,
	name VARCHAR(75) null,
	doj DATE null,
	dob DATE null,
	email VARCHAR(75) null,
	band VARCHAR(75) null,
	designation VARCHAR(75) null,
	manager VARCHAR(75) null,
	reviewer VARCHAR(75) null,
	coordinator VARCHAR(75) null,
	account VARCHAR(75) null,
	project VARCHAR(200) null,
	experience VARCHAR(75) null,
	skill VARCHAR(75) null,
	location VARCHAR(75) null,
	confirmed BOOLEAN,
	mobile VARCHAR(75) null,
	fileId VARCHAR(75) null,
	skype VARCHAR(75) null,
	lmsUser BOOLEAN
);

create table LMS_Holiday (
	holidayId LONG not null primary key,
	holidayYear INTEGER,
	onDate DATE null,
	name VARCHAR(75) null
);

create table LMS_Scheduler (
	schedulerId LONG not null primary key,
	name VARCHAR(75) null,
	status BOOLEAN,
	onDate DATE null,
	runDate DATE null
);