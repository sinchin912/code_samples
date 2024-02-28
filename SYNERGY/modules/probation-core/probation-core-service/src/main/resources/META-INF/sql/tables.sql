create table PROBATION_Probation (
	ecode VARCHAR(75) not null primary key,
	manager VARCHAR(100) null,
	reviewer VARCHAR(100) null,
	state_ INTEGER,
	stateName VARCHAR(75) null,
	alertDate DATE null,
	createDate DATE null,
	updateDate DATE null,
	areaOfStrength VARCHAR(1000) null,
	areaOfImprovement VARCHAR(1000) null,
	comment_ VARCHAR(1000) null
);

create table PROBATION_ProbationLine (
	lineId LONG not null primary key,
	probationId VARCHAR(75) null,
	lineNumber INTEGER,
	rating INTEGER,
	comment_ VARCHAR(300) null
);