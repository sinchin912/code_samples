create table GAME_GameTimeline (
	name VARCHAR(75) not null primary key,
	openDate DATE null,
	freezeDate DATE null,
	actionDate DATE null,
	closeDate DATE null
);

create table GAME_Prize (
	prizeId LONG not null primary key,
	year INTEGER,
	sequence INTEGER,
	description VARCHAR(75) null,
	winner VARCHAR(75) null,
	ticketId LONG,
	surprise BOOLEAN,
	fileId VARCHAR(75) null
);

create table GAME_Santa (
	santaId LONG not null primary key,
	year INTEGER,
	ecode VARCHAR(75) null,
	mobile VARCHAR(75) null,
	city VARCHAR(75) null,
	state_ VARCHAR(75) null,
	pincode VARCHAR(75) null,
	postalAddress VARCHAR(2000) null,
	santaEcode VARCHAR(75) null,
	guessedEcode VARCHAR(75) null,
	giftSent BOOLEAN,
	emailSent BOOLEAN,
	fileId VARCHAR(75) null,
	createDatetime DATE null
);

create table GAME_Ticket (
	ticketId LONG not null primary key,
	year INTEGER,
	ecode VARCHAR(75) null,
	ticketNumber VARCHAR(75) null,
	createDatetime DATE null,
	draw BOOLEAN
);
