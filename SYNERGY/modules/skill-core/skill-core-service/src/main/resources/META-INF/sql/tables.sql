create table SKILL_Certificate (
	certificateId LONG not null primary key,
	ecode VARCHAR(75) null,
	name VARCHAR(75) null,
	category VARCHAR(75) null,
	description VARCHAR(1000) null,
	fileId VARCHAR(75) null,
	filename VARCHAR(75) null,
	createdDate DATE null,
	expiryDate DATE null
);

create table SKILL_Skill (
	skillId LONG not null primary key,
	ecode VARCHAR(75) null,
	project VARCHAR(75) null,
	coreSkill VARCHAR(75) null,
	subSkill VARCHAR(75) null,
	tool VARCHAR(1000) null,
	validity VARCHAR(75) null,
	version VARCHAR(1000) null,
	status INTEGER,
	reviewer VARCHAR(75) null,
	manager VARCHAR(75) null,
	primarySkill BOOLEAN,
	proficiencyLevel INTEGER,
	requiredLevel INTEGER
);

create table SKILL_SkillGuide (
	guideId LONG not null primary key,
	coreSkill VARCHAR(75) null,
	subSkill VARCHAR(75) null
);

create table SKILL_SkillRejectionComment (
	commentId LONG not null primary key,
	ecode VARCHAR(75) null,
	author VARCHAR(75) null,
	description VARCHAR(1000) null,
	createdDate DATE null
);