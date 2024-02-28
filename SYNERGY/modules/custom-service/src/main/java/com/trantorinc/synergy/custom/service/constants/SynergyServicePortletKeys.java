package com.trantorinc.synergy.custom.service.constants;

import java.time.format.DateTimeFormatter;

/**
 * @author sachin.goyal
 */
public class SynergyServicePortletKeys {

	public static final DateTimeFormatter FORMATTER_DD_MMM_YYYY_HH_MM_SS = DateTimeFormatter.ofPattern("dd MMM, yyyy h:mm:ss a");
	public static final String ANNOUNCEMENT_SUBJECT_PREFIX = "Announcement - ";
	public static final String ANNOUNCEMENT_EMAIL_DELIMITER = ";";
	public static final String EMAIL_DELIMITER = ",";
	public static final String EMAIL_FROM_NAME = "Synergy Intranet Admin";
	public static final String SUBJECT_UPDATING_ROLE = "{0} : Role has been updated";
	public static final String BODY_UPDATING_ROLE = "Role {0} has been updated with Title as {1}, Description as {2} and Key as {3}.";
	public static final String SUBJECT_ROLE_ASSIGNED = "{0} : Assignee has been added to role";
	public static final String BODY_ROLE_ASSIGNED = "User {0} has been added to {1} role.";
	public static final String SUBJECT_ROLE_REMOVED = "{0} : Assignee has been removed from role";
	public static final String BODY_ROLE_REMOVED = "User {0} has been deleted from {1} role.";
	public static final String SUBJECT_USER_ROLE_DELETE = "{0} : Existing role has been deleted from a user's profile";
	public static final String BODY_USER_ROLE_DELETE = "Role {0} has been removed from user {1} as regular role.";
	public static final String SUBJECT_USER_ROLE_ADD = "{0} : A new role has been added to a user's profile";
	public static final String BODY_USER_ROLE_ADD = "Role {0} has been added to user {1} as regular role.";

	//Game Email Constant
	public static final String SUBJECT_GAME_OPEN ="Synergy - {0} system needs HR attention ";
	public static final String SANTA_GAME_OPEN = "Dear HR,<br><br>{0} system will open for all employees on {1}. Please visit Synergy to review timelines for this year.<br><br><ul><li>Game & Registration opens :{2}</li><li>Secret Santa are assigned by system :{3}</li><li>First reminder to send gifts :{4}</li><li>Second reminder to send gifts :{5}</li><li>Final reminder to send gifts :{6}</li><li>Guessing game begins :{7}</li><li>Guessing game reminder :{8}</li><li>Guessing game stops :{9}</li></ul><br><br>Click <a href=''{10}''>Here</a> to view and update the timelines.<br><br>Regards,<br>Synergy Admin";
	public static final String RAFFLE_GAME_OPEN = "Dear HR,<br><br>{0} system will open for all employees on {1}. Please visit Synergy to review timelines for this year.<br><br><ul><li>Game & Ticket Buying opens :{2}</li><li>Ticket Buying closes :{3}</li><li>Raffle draw start :{4}</li></ul><br> Click <a href=''{5}''>Here</a> to view and update the timelines.<br><br>Regards,<br>Synergy Admin";
	public static final String SUBJECT_SANTA_ALLOCATION ="Secret Santa - Send your gift now! ";
	public static final String BODY_SANTA_ALLOCATION = "Hi {0},<br><br>You are Secret Santa and you have to send gift to :<br><br>Employee Name : {1}<br>Address : {2}<br>Mobile : {3}<br><br>" +
			"Please remember, the spending limit should be min INR 200 and max INR 500 and gift should be delivered on/before {4}.<br>  Note - Once you have registered, participation is mandatory. In case of back out, we will send gift coupon of INR 500 on your behalf and deduct the same from your salary.<br><br>" +
			"Once you have sent gift, please update synergy at - Click <a href=''{5}''>Here</a>.<br>{6}<br><br>Looking forward to enjoying a Merry Christmas !<br><br>Thanks & Regards,<br>Team Synergy";
	public static final String SUBJECT_SANTA_GAME ="Secret Santa - Guess who is your Secret Santa and send a Thank You note";
	public static final String BODY_SANTA_GAME = "Hi {0}, <br><br>You would have received your Xmas gift by now.<br><br>Guess who is your Secret Santa and send a thank you note to your Secret Santa.." +
			"<br><br>Click <a href=''{1}''>Here</a> to guess now.<br><br>Thanks & Regards,<br>Team Synergy";
	public static final String SUBJECT_SANTA_GIFT ="Secret Santa - Update Gift Sending Status for Secret Santa";
	public static final String BODY_SANTA_GIFT = "Dear {0}, <br><br>Please visit Synergy to confirm that you have sent the gift to the assigned employee.<br>Otherwise, we will consider that you have not sent and HR will send the gift on your behalf." +
			"<br><br>Click <a href=''{1}''>Here</a> to confirm.<br><br>Note - This link closes on EOD of {2}.<br><br>Thanks & Regards,<br>Team Synergy";


	// Probation Email Constants
	public static final String SUBJECT_PROBATION_AUTO = "Probation - Auto Probation Status Change";
	public static final String BODY_PROBATION_AUTO = "Hi HR Team,<br><br>Following probation(s) extended/revised initiated are again pending for review with {0}.<br><br>{1}<br><br>Regards,<br>Synergy Admin";
	public static final String TABLE_START_PROBATION = "<table style='border:1px solid black;'><thead><tr><th style='border:1px solid black;'>Emp. ID</th><th style='border:1px solid black;'>Employee name</th><th style='border:1px solid black;'>Manager name</th><th style='border:1px solid black;'>Initiated on</th></tr></thead><tbody>";
	public static final String SUBJECT_PROBATION_INITIATED = "Probation - High Priority Probation appraisal";
	public static final String BODY_PROBATION_INITIATED = "Dear {0},<br>" +
			"<br>" +
			"You are requested to complete probation appraisal of your team member(s) as listed below. Please note, it is very critical that you review all your probationary employees with 0 error since this exercise effects the notice period of employee.<br>" +
			"Please click on this <a href=''{1}''>link</a> to submit review form(s). You have an option to<br>" +
			"<ul><li>Confirm employee - No effect on salary and notice period revises as per Separation policy</li>" +
			"<li>Extend probation - You can extend probation period by minimum 1 month and maximum 2 months in case you are not able to evaluate particular resource''s performance</li>" +
			"<li>Revise Probation - You can revise probation review date by mentioning reason for same</li>" +
			"<li>Non-Confirmation - Would lead to termination on basis of non-performance. Employee will have to undergo PIP in that case</li></ul><br>" +
			"<br>" +
			"We would also encourage you to talk to previous leads/managers of your team members, in case employee has worked with more than one lead/manager. Please connect with HR in case of any issues/queries.<br>" +
			"<br>" +
			"<strong><u>Kindly submit the feedback form(s) of following employees</u></strong><br>" +
			"<br>" +
			"{2}<br>" +
			"<br>" +
			"Regards,<br>" +
			"HR Team";
	public static final String SUBJECT_PROBATION_RECEIVED = "Probation - Probation Feedback Received";
	public static final String BODY_PROBATION_RECEIVED = "Hi HR Team,<br><br>Following ''Probation Feedback form'' has been received in last 24 hours.<br><br>{0}<br><br>Regards,<br>Synergy Admin";
	public static final String TABLE_START_PROBATION_RECEIVED = "<table style='border:1px solid black;'><thead><tr><th style='border:1px solid black;'>Emp. ID</th><th style='border:1px solid black;'>Employee Name</th><th style='border:1px solid black;'>Employee Email</th><th style='border:1px solid black;'>Date of Joining</th><th style='border:1px solid black;'>Manager Name</th><th style='border:1px solid black;'>Probation Status</th><th style='border:1px solid black;'>Probation  Start Date</th><th style='border:1px solid black;'>Completion Date</th><th style='border:1px solid black;'>Account</th><th style='border:1px solid black;'>Manager Comments</th></tr></thead><tbody>";

	// LMS Email Constants
	public static final String SUBJECT_EMPLOYEE_SYNC_FAIL = "Synergy {0} - LMS Employee Sync Fail";
	public static final String BODY_EMPLOYEE_SYNC_FAIL = "LMS-Synergy Employee Sync has thrown an exception {0}. Please review message - {1}";
	public static final String SUBJECT_HOLIDAY_SYNC_FAIL = "Synergy {0} - LMS Holiday Sync Fail";
	public static final String BODY_HOLIDAY_SYNC_FAIL = "LMS-Synergy Holiday Sync has thrown an exception {0}. Please review message - {1}";
	public static final String SUBJECT_INCORRECT_LMS = "Synergy - Incorrect Data in LMS For Employee";
	public static final String BODY_INCORRECT_LMS = "Hi Team,<br><br>Following employees have incorrect manager/reviewer ecode in LMS. Such employee could face errors in accessing Synergy modules. Please correct them with correct ecode. Details are as below<br><br>{0}<br><br>Regards,<br>Synergy Admin";
	public static final String TABLE_START_INCORRECT_LMS = "<table style='border:1px solid black;'><thead><tr><th style='border:1px solid black;'>Emp. ID</th><th style='border:1px solid black;'>Employee name</th><th style='border:1px solid black;'>Account</th><th style='border:1px solid black;'>Manager Ecode</th><th style='border:1px solid black;'>Reviewer Ecode</th><th style='border:1px solid black;'>To Validate</th></tr></thead><tbody>";
	public static final String SUBJECT_ACCOUNT_MANAGER_CHANGE = "Synergy - LMS Change Email <{0}>-<{1}>";
	public static final String BODY_MANAGER_CHANGE = "Hi Team,<br><br>Please note the updated data of following resource.<br><br>{0}<br><br>Regards,<br>Synergy Admin";
	public static final String BODY_ACCOUNT_CHANGE = "Hi Team,<br><br>Please note the updated data of following resource. Previous Manager - {0} is required to connect with the IT team via ITHelpdesk, in case any data backup is required within 5 working days of this email. In the event of no data backup requirement is received from the previous manager, the IT team will not be responsible for any loss of data.<br><br>{1}<br><br>Regards,<br>Synergy Admin";
	public static final String TABLE_START_ACCOUNT_MANAGER_CHANGE = "<table style='border:1px solid black;'><thead><tr><td style='border:1px solid black;'>LMS Change Date:</td><td colspan='2' style='border:1px solid black;'>";
	public static final String ROW_START_ACCOUNT_MANAGER_CHANGE = "</td></tr><tr><th style='border:1px solid black;'></th><th style='border:1px solid black;'>Previous</th><th style='border:1px solid black;'>New</th></tr><tr><td style='border:1px solid black;'>Ecode</td><td style='border:1px solid black;'>";

	// Generic Email Constants
	public static final String SUBJECT_COVID = "Update - Covid 19 Vaccination and/or Current Location Status Change";
	public static final String BODY_COVID = "Dear All,<br><br>A reminder to update changes in your Covid 19 Vaccination status and/or your Current location in case there are any changes since last week.<br><br><a href=''{0}''>Click here</a> to access and edit your form.<br><br>Your support on this is highly appreciated.<br>For any queries, please write back to <a href=\"mailto:hr@trantorinc.com\">hr@trantorinc.com</a><br><br>Regards,<br>HR Team";
	public static final String COVID_LINK ="https://docs.google.com/forms/d/e/1FAIpQLSeqCk_3GMWyc0ltYoCnDlXfBn6FiPQCAJxgxdtvtJL8lgPYuQ/alreadyresponded?vc=0&c=0&w=1&flr=0";
	public static final String SUBJECT_BLOOD_DONATION = "Be a part of Trantor's Blood Donor Group, register now";
	public static final String BODY_BLOOD_DONATION = "Dear Employees,<br><br>This is a group of like minded people who want to donate blood and/or platelets to help others. We maintain database of Trantor's Blood Donors and in case need for any blood group arises, we help employee via this group.<br><br>If you are interested to be part of this group, please register yourself by <a href='https://docs.google.com/forms/d/e/1FAIpQLSedyqiAknKz-fYyAelDwZWIyN-NEYSvZ3BugryQ8DSsO_xOYQ/viewform'>Click here</a><br><br>Regards,<br>HR Team";
	public static final String SUBJECT_LMS ="Refresher - Keep your attendance and/or leaves updated on LMS";
	public static final String BODY_LMS ="Dear All,<br><br>This is a refresher email reiterating that you need to keep your attendance and leaves updated on LMS <a href=''{0}''>(Click Here)</a>." +
			" You should have attendance/Leave updated till last Friday and approved by manager.<br><br>" +
			"Please note that you should apply for<br><br>" +
			"<ul><li>Planned leave - As soon as possible and at least 7 days in advance</li>" +
			"<li>Emergency or Sick leave - Please apply on LMS as soon as emergency happens. In case you are not " +
			"able to access LMS, ensure that an email or message goes to your manager with HR in CC." +
			" As per policy, we can ask for medical certificate for more than 1 day of leave. Medical certificate " +
			"is mandatory for more than 3 days of Sick leave.</li></ul>" +
			"<br>All Managers/Leads are requested to ensure that your team is applying attendance and/or leave on time, ideally on daily or at least weekly" +
			" basis and not longer than that.  If your team member is not able to send email while on emergency/sick leave, please ensure you email HR with employee in copy." +
			"<br><br>Thanks,<br>Team HR";
	public static final String LMS_LINK="https://hms.trantorinc.com/login";
	public static final String SUBJECT_ROLE = "Synergy - Updated User Role";
	public static final String TABLE_START_ROLE = "<table style='border:1px solid black;'><tbody>";

	//Skill email constant
	public static final String SUBJECT_SKILL_QUATERLY = "Skill & Certification - Review Your Skill Details";
	public static final String BODY_SKILL_QUATERLY = "Dear Employee,<br><br>You are requested to review your skill details as well as certifications for HR records and update the same if needed.<br><br>Please <a href=''{0}''>click here</a> to go to Skill Details.<br><br>Regards,<br>Synergy Admin";
	public static final String SUBJECT_SKILL_WEEKLY = "Skill & Certification - Review Skill Details";
	public static final String BODY_SKILL_WEEKLY = "Dear Manager,<br><br>Skill Details and self rating submitted by your team member is pending for review. You are requested to review and update skill details of your team members. You are also required to update ''Required Rating''.<br>Please ignore if already done.<br><br>Please <a href=''{0}''>click here</a> to review Skill Details.<br><br>Regards,<br>Synergy Admin";
	public static  final String[] noSkillProjectList = {"ADMIN","CONTINUUM", "FINANCE", "IT","GOOGLE", "HR", "HR - RECRUITMENT", "MANAGEMENT", "SALES & MARKETING", "US RECRUITMENT"};
	public static final String DATE_SKILL_REMINDER_DOJ_POST = "2021-08-01";
	public static final String SUBJECT_NEW_JOINEE = "Skill & Certification - Update Your Skill Details for HR records";
	public static final String BODY_NEW_JOINEE = "Dear Employee,<br><br>Hope you have an exciting start at Trantor and enjoying your work. This is to inform you that you need to do following within first two weeks of your joining.<br>a) update your skill details.<br>b) update your certifications details and upload copy of your certificates.<br><br>In case of any changes in your skills and on acquiring new certifications, you can go easily into module and make necessary changes.<br><br>Please <a href=''{0}''>click here</a> to start submitting your skill details. Please ignore if already done.<br><br>In case of any queries, please feel free to write to hr@trantorinc.com<br><br>Note : Since this is a technical skill repository, Support and sales teams can ignore emails and reminder emails sent on this.<br><br>Regards,<br>Synergy Admin";
	public static final String BODY_NEW_JOINEE_REMINDER = "Dear Employee,<br><br>This is the reminder to inform you that you need to do following within first two weeks of your joining.<br>a) update your skill details.<br>b) update your certifications details and upload copy of your certificates.<br><br>In case of any changes in your skills and on acquiring new certifications, you can go easily into module and make necessary changes.<br><br>Please <a href=''{0}''>click here</a> to start submitting your skill details. Please ignore if already done.<br><br>In case of any queries, please feel free to write to hr@trantorinc.com<br><br>Regards,<br>Synergy Admin";

	// Performance Email constants
	public static final String SUBJECT_KPI_UPDATE = "Performance Review - Reminder to review and update your KPIs";
	public static final String BODY_KPI_UPDATE = "Dear Employee,<br><br>This is your quarterly reminder to review and update your KPIs for current and previous project in current year in discussion with your manager.<br><br>If no update is required, you can ignore this email.<br><br><a href=''{0}''>Click here</a> to start updating KPIs in Synergy.<br><br>Regards,<br>Synergy Admin";
	public static final String SUBJECT_KPI_NEW_JOINEE = "Performance Review - Complete KPI Settings";
	public static final String BODY_KPI_NEW_JOINEE = "Dear Employee,<br><br>{0} This is to inform that your KPI setting is to be submitted within first three weeks of joining. These KPIs (Key Performance Indicators) set in consultation with your manager will be basis of your future performance reviews.<br><br>You can <a href=''{1}''>refer here</a> to know suggested KPIs or discuss these with your lead/manager. Do note that KPIs will be open to change and update through out the year except during annual/mid year review cycle.<br><br>Please <a href=''{2}''>click here</a> to start the KPI Settings. Please ignore if already done.<br><br>Regards,<br>Synergy Admin";
	public static final String LINE_KPI_NEW_JOINEE = "Hope you have an exciting start at Trantor and enjoying your work.";
	public static final String LINE_KPI_NEW_JOINEE_REMINDER = "This is a reminder email to complete your KPI settings.";
	public static final String LINE_KPI_NEW_JOINEE_FINAL_REMINDER = "This is final reminder email to complete your KPI settings.";
	public static final String SUBJECT_ANNUAL_SELF_REVIEW = "Annual Performance Review - Complete your Self Review";
	public static final String BODY_ANNUAL_SELF_REVIEW = "Dear Employee,<br><br>{0}<br>You are requested to complete following activities - <br><br>a) Review your primary and secondary projects added in KPI setting tab and update your KPIs, if needed after consulting your lead/manager.<br>b) Add secondary project, if missing in KPI setting tab and create KPIs as needed.<br>c) You should create secondary project only if lead/manager is different. Secondary projects must not be created if lead/manager is same for any other primary or secondary project in current review cycle.<br>d) Review your KPIs and make changes, if needed in consultation with your lead/manager.<br>e) Generate Self Review form for each project added.<br>f) Complete your self review for each self review form generated and submit form for manager review<br><br>Last date to complete self-review of annual review is {1}.<br>Please ensure to complete your review before due date otherwise your rating will be marked as default \"Inconsistently Meets Expectation\" against all KPIs set.<br><br>Note:<br>a) Resources who are on rolls on or before Dec 31 will be eligible for annual review. However, merit increment will be decided on case to case basis and as per policy<br>b) You can view your previous year KPIs in previous year review form, should you want to copy them. Do consult your manager/lead while doing KPI setting<br><br>Please <a href=''{2}''>click here</a> to go to your review window.<br><br>Regards,<br>Synergy Admin";
	public static final String LINE_ANNUAL_SELF_REVIEW = "Performance Review System(PRS) is now open to start self review of Annual Review.";
	public static final String LINE_ANNUAL_SELF_REVIEW_REMINDER = "This is a reminder to complete your self review of Annual Review. Please ignore if already done";
	public static final String LINE_ANNUAL_SELF_REVIEW_FINAL_REMINDER = "This is a final reminder to complete your self review of Annual Review.";
	public static final String SUBJECT_MIDYEAR_SELF_REVIEW = "Mid-Year Performance Review - Review your KPI's and Generate Manager Review Form";
	public static final String BODY_MIDYEAR_SELF_REVIEW = "Dear Employee,<br><br>{0}<br>You are requested to complete following activities - <br><br>a) Review your primary and secondary projects added in KPI setting tab.<br>b) Add secondary project, if missing in KPI setting tab.<br>c) You should create secondary project only if lead/manager is different. Secondary projects must not be created if lead/manager is same for any other primary or secondary project in current review cycle.<br>d) Do Final Review of your KPIs and make changes, if needed in consultation with your manager.<br>e) Generate your manager review form for primary as well as secondary projects.<br>f) Self Review will not be done in Mid-Year review cycle.<br><br>Last date to do this activity of mid-year review is {1}. In case of non completion, employee will no longer be able to generate the review forms for Manager Review.<br>Please ensure to generate review form before due date as system will not allow it further.<br><br>Note - <br>a) Resources who are on rolls on or before June 30th will be eligible for mid year review. <br>b) Mid Year Reviews are review check point and mandatory to complete activity. However, no compensation and/or designation review and changes in same will happen on basis of mid year review.<br><br>Please <a href=''{2}''>click here</a> to go to your review window. Please ignore if already done.<br><br>Regards,<br>Synergy Admin";
	public static final String LINE_MIDYEAR_SELF_REVIEW = "Performance Review System(PRS) is now open to start your Mid Year reviews.";
	public static final String LINE_MIDYEAR_SELF_REVIEW_REMINDER = "This is a reminder to generate your review form for Mid Year review. Please ignore if already done";
	public static final String LINE_MIDYEAR_SELF_REVIEW_FINAL_REMINDER = "This is a final reminder to generate your review form for Mid Year review.";
	public static final String SUBJECT_ANNUAL_NO_SELF_REVIEW = "Annual Performance Review - Self Review Form not yet submitted by your team member";
	public static final String BODY_ANNUAL_NO_SELF_REVIEW = "Dear Manager,<br><br>Following are the employees who have not completed self review yet. Please ensure initiation and submission of review form by {0} to avoid any inconvenience.<br><br>{1}<br><br>Regards,<br>Synergy Admin";
	public static final String SUBJECT_MIDYEAR_NO_SELF_REVIEW = "Mid-Year Performance Review - Manager Review Form not yet generated by your team member";
	public static final String BODY_MIDYEAR_NO_SELF_REVIEW= "Dear Manager,<br><br>Following are the employees who have not generated manager review form yet. Please ensure initiation and submission of review form by {0} to avoid any inconvenience.<br><br>{1}<br><br>Regards,<br>Synergy Admin";
	public static final String TABLE_START_NO_SELF_REVIEW = "<table style='border:1px solid black;'><thead><tr><th style='border:1px solid black;'>Emp. ID</th><th style='border:1px solid black;'>Employee name</th><th style='border:1px solid black;'>Project</th><th style='border:1px solid black;'>Review Type</th><th style='border:1px solid black;'>Project Type</th></tr></thead><tbody>";
	public static final String SUBJECT_MANAGER_READY = "{0} Performance Review - Review ready for Manager Review";
	public static final String BODY_MANAGER_READY = "Dear Manager,<br><br>{0} Performance Review for your team is ready for Manager Review.<br>Please ensure closure by {1} otherwise manager rating will be marked as default \"Inconsistently Meets Expectation\". <strong>Form once closed in system can not be opened again.</strong><br><br>Please <a href=''{2}''>click here</a> to start the review.<br><br>Regards,<br>Synergy Admin";
	public static final String SUBJECT_MANAGER_NO_MANAGER_REVIEW = "{0} Performance Review - Manager Review not yet submitted";
	public static final String BODY_MANAGER_NO_MANAGER_REVIEW = "Dear Manager,<br><br>Manager Review of following employees are pending.<br>Please ensure closure by {0} otherwise manager rating will be marked as default \"Inconsistently Meets Expectation\".<br><br>{1}<br><br>Please <a href=''{2}''>click here</a> to start the review. If you are a lead doing review, please ensure that review forms are discussed with manager before meeting with employee for review meeting.<br><br>Regards,<br>Synergy Admin";
	public static final String TABLE_START_MANAGER_NO_MANAGER_REVIEW = "<table style='border:1px solid black;'><thead><tr><th style='border:1px solid black;'>Emp. ID</th><th style='border:1px solid black;'>Employee name</th><th style='border:1px solid black;'>Employee Email</th><th style='border:1px solid black;'>Account</th><th style='border:1px solid black;'>Review Type</th></tr></thead><tbody>";
	public static final String SUBJECT_EMPLOYEE_NO_MANAGER_REVIEW = "{0} Performance Review - Manager Review not yet submitted for you";
	public static final String BODY_EMPLOYEE_NO_MANAGER_REVIEW = "Dear Employee,<br><br>Please note that your lead/manager has not closed your manager review for project {0}<br>Please connect with your lead/manager and ensure closure by {1} otherwise manager rating will be marked as default \"Inconsistently Meets Expectation\".<br><br>Regards,<br>Synergy Admin";
	public static final String SUBJECT_POOR_RATING = "{0} Performance Review - Rated below 3";
	public static final String BODY_POOR_RATING = "Dear HR,<br><br>Below mentioned are the employees who have been rated less than 3 by respective managers :<br><br>{0}<br><br>Regards,<br>Synergy Admin";
	public static final String TABLE_START_POOR_RATING = "<table style='border:1px solid black;'><thead><tr><th style='border:1px solid black;'>Emp. ID</th><th style='border:1px solid black;'>Employee name</th><th style='border:1px solid black;'>Rating</th><th style='border:1px solid black;'>Account</th><th style='border:1px solid black;'>Review Type</th></tr></thead><tbody>";
	public static final String SUBJECT_REVIEW_AUTO_UPDATE = "{0} Performance Review - Progressed to {1}";
	public static final String BODY_REVIEW_AUTO_UPDATE = "Dear Employee,<br><br>Your {0} review for project {1} progressed to \"{2}\" state based on deadline date.<br><br>Please visit Synergy to view latest updates and connect with your manager for any queries.<br><br>Regards,<br>Synergy Admin";
	public static final int STAGE_FREEZE_DATE_REVIEW = 0;
	public static final int STAGE_SELF_REVIEW = 1;
	public static final int STAGE_MANAGER_REVIEW = 2;
	public static final int STAGE_EMPLOYEE_SIGNOFF = 3;
	public static final int STAGE_HR_REVIEW = 4;
	public static final int STAGE_COMPLETE = 5;
	public static final String ANNUAL = "Annual";
	public static final String SYSTEM_RATING_ADMIN = "C";
	public static final String SYSTEM_RATING = "D";
	public static final String SYSTEM_COMMENT = "System rating as deadline was missed";
	public static final String NA = "Not Available";
	public static final String MID_YEAR = "Mid Year";
	public static final String YEAR_END_SMALL = "annual";
	public static final String MID_YEAR_SMALL = "mid-year";
	public static final String SYSTEM_NUMERIC_RATING = "2.00";
	public static final String SYSTEM_ADMIN_NUMERIC_RATING = "3.00";
	public static final String KPI_DOCUMENT ="";
	public static final String FULLTIME = "FTE";

}