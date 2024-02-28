create table EMAIL_Email (
	emailId LONG not null primary key,
	scheduled BOOLEAN,
	module VARCHAR(75) null,
	toAddress TEXT null,
	ccAddress TEXT null,
	bccAddress TEXT null,
	subject TEXT null,
	body TEXT null,
	createdDate DATE null,
	sent BOOLEAN
);

create table EMAIL_EmailAttachment (
	emailAttachmentId LONG not null primary key,
	emailId LONG,
	attachmentName VARCHAR(500) null,
	attachmentFileId VARCHAR(75) null
);