create table EXPENSE_Expense (
	expenseId LONG not null primary key,
	ecode VARCHAR(75) null,
	expenseType VARCHAR(75) null,
	totalBillAmount LONG,
	assignee VARCHAR(75) null,
	managerComment VARCHAR(1000) null,
	financeComment VARCHAR(1000) null,
	approvingManager VARCHAR(75) null,
	status INTEGER,
	createdDate DATE null,
	managerDate DATE null,
	financeDate DATE null,
	fileId VARCHAR(75) null
);

create table EXPENSE_ExpenseLine (
	lineId LONG not null primary key,
	expenseId LONG,
	startDate DATE null,
	endDate DATE null,
	description VARCHAR(1000) null,
	billAmount LONG
);