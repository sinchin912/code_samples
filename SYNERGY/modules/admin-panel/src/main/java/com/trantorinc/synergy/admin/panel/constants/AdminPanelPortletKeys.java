package com.trantorinc.synergy.admin.panel.constants;

import java.util.Arrays;
import java.util.List;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;
import static com.trantorinc.synergy.common.service.constant.AppConstantService.TASK_PERFORMANCE;

/**
 * @author sachin.goyal
 */
public class AdminPanelPortletKeys {
	public static final String ADMINPANEL = "com_trantorinc_synergy_admin_panel_AdminPanelPortlet";
	public static final List<String> TASK_LIST = Arrays.asList(
			TASK_AM_LMS,
			TASK_GAME,
			TASK_AM_PROBATION,
			TASK_AM_EXIT,
			TASK_AM_SKILL,
			TASK_GENERIC,
			TASK_PM_SKILL,
			TASK_PM_LMS,
			TASK_PM_PROBATION,
			TASK_PM_EXIT,
			TASK_PERFORMANCE
	);

	public static final List<String> MODULE_LIST = Arrays.asList(
			MODULE_EMAIL,
			MODULE_EXIT,
			MODULE_EXPENSE,
			MODULE_GAME,
			MODULE_GENERIC,
			MODULE_LMS,
			MODULE_PERFORMANCE,
			MODULE_PROBATION,
			MODULE_SKILL,
			MODULE_TRAINING
	);

	public static final String TASK_LMS_DESCRIPTION = "<ol>\n" +
			"<li>Update employee from lms including photos, send sync failure email to Intranet.</li>\n" +
			"<li>Validate manager and reviewer for each employee, if there is any invalid entry send consolidated email to HR & Intranet.</li>\n" +
			"<li>Send 1-1 email for any employee with account/manager/reviewer change.</li>\n" +
			"<li>Update holidays from lms, send sync failure email to Intranet.</li>\n" +
			"<li>Update google file count to database.</li>\n" +
			"</ol>";
	public static final String TASK_GAME_DESCRIPTION = "<ol>\n" +
			"<li>If today is 1 April, copy raffle prizes from last year to current year.</li>\n" +
			"<li>If today is 30 days before Raffles buyOpenDate or Santa registrationDate, send alert email to HR and Intranet.</li>\n" +
			"<li>\n" +
			"Perform following action for Secret Santa :\n" +
			"<ul>\n" +
			"<li>If today is allocation date, assign secret santa,send 1-1 email to all participants.</li>\n" +
			"<li>If today is allocation date+2, send 1-1 email to all participants.</li>\n" +
			"<li>If today is allocation date+4, send 1-1 email to all participants.</li>\n" +
			"<li>If today is allocation date+10, send 1-1 email to participants who have not sent gift yet.</li>\n" +
			"<li>If today is game date, send 1-1 email to all employees who have not guessed secret santa.</li>\n" +
			"<li>If today is game date+3, send 1-1 email to all employees who have not guessed secret santa.</li>\n" +
			"</ul>\n" +
			"</li>\n" +
			"</ol>";
	public static final String TASK_AM_PROBATION_DESCRIPTION = "<ol>\n" +
			"<li>\n" +
			"Perform following updates :\n" +
			"<ul>\n" +
			"<li>If today is alertDate for extended/revised probation, convert to pending state and send 1-1 email to manager</li>\n" +
			"<li>if probation is pending/extended/revised & (probation manager!=LMS manager || probation reviewer!=LMS Reviewer)), update probation and send 1-1 email to manager/reviewer per probation</li>\n" +
			"</ul>\n" +
			"</li>\n" +
			"<li>If today is DOJ+50 of FTE & not confirmed employees, create probation and send 1-1 email to manager</li>\n" +
			"<li>If today is pending probation createDate+5, send reminder email to per manager.</li>\n" +
			"<li>If today is pending probation createDate+7, send reminder email to per manager.</li>\n" +
			"<li>If today is between pending probation createDate+(9-14), send reminder email to per manager.</li>\n" +
			"<li>If today is over pending probation createDate+14, send reminder email to per manager.</li>\n" +
			"<li>If today is completed probation submitDate+1, send consolidated email to HR.</li>\n" +
			"</ol>";
	public static final String TASK_AM_EXIT_DESCRIPTION = "<ol>\n" +
			"<li>ERRROR</li>\n" +
			"</ol>";
	public static final String TASK_PM_EXIT_DESCRIPTION = "<ol>\n" +
			"<li>Send consolidated email to HR DL for today's exit not having IT tickets</li>\n" +
			"</ol>";
	public static final String TASK_AM_SKILL_DESCRIPTION = "<ol>\n" +
			"<li>Update manager and reviewer of all skill of all active employees.</li>\n" +
			"</ol>";
	public static final String TASK_GENERIC_DESCRIPTION = "<ol>\n" +
			"<li>If today is alternate friday, send blood donation email to TrantorIndia.</li>\n" +
			"<li>If today is wednesday, send LMS update email to TrantorIndia.</li>\n" +
			"<li>If today date is 1 or 16, send Covid vaccination email to TrantorIndia.</li>\n" +
			"<li>Send consolidated role email to Intranet.</li>\n" +
			"</ol>";
	public static final String TASK_PM_SKILL_DESCRIPTION = "<ol>\n" +
			"<li>If today is DOJ+(10/15), send 1-1 email to each employee.</li>\n" +
			"<li>Find no skill employee joined after 2021-07-26 and 20 days before today, send 1-1 email to each employee.</li>\n" +
			"</ol>";
	public static final String TASK_PM_PROBATION_DESCRIPTION = "<ol>\n" +
			"<li>If today is over pending probation createDate+14, send reminder email to per manager.</li>\n" +
			"</ol>";
	public static final String TASK_PERFORMANCE_DESCRIPTION = "<ol>\n" +
			"<li>If today is DOJ+10/15/20, send email per FTE joiner and if kpis not set for later 2.</li>\n" +
			"<li>Perform following updates on KPIs\n" +
			"<ul>\n" +
			"<li>For each active employee, create/update primary KPI based on account.</li>\n" +
			"<li>For each active employee, update manager and reviewer of primary KPI.</li>\n" +
			"</ul>\n" +
			"</li>\n" +
			"<li>Perform following updates on Reviews\n" +
			"<ul>\n" +
			"<li>Annual\n" +
			"<ul>\n" +
			"<li>If today is 23 day before self review end date,send generic email to all.</li>\n" +
			"<li>If today is 10 day before self review end date, send reminder generic email to all.</li>\n" +
			"<li>If today is 5 days or less before self review end date, send reminder email to all eligible employee email who have not complete Review in batch of 25 emailId & 1 consolidate email per manager.</li>\n" +
			"<li>If today is self review end date, generic email to manager DL & reviewer DL that reviews are ready, update pending review with D self rating & move to manager review, 1-1 mail to such each employee.</li>\n" +
			"<li>If today is 20, 15, 10 , 5 or less days before manager review end date, send manager review pending report per manager.</li>\n" +
			"<li>If today is 5 or less days before manager review end date, 1-1 mail to each employee for each project pending manager review.</li>\n" +
			"<li>If today is manager review end date, update review with manager rating 2 and move to sign_off(primary) or completed(secondary), 1-1 employee email for such case.</li>\n" +
			"<li>If today is 1 day after manager review end date, consolidated email to hr for all review rated less than 3.</li>\n" +
			"<li>If today is employee signoff end date, update review with HR rating as manager rating, move it to HR review and 1-1 email to each employee.</li>\n" +
			"<li>If today is hr review end date, move all reviews to complete & 1-1 email to such employees.</li>\n" +
			"<li>If today is complete end date, close all rollback and close requests, find review which are still open (possibly due to rollback/end back), update such forms with default rating to which ever state applicable and move it complete.</li>\n" +
			"</ul>\n" +
			"</li>\n" +
			"<li>Mid-Year\n" +
			"<ul>\n" +
			"<li>If today is 23 day before self review end date,send generic email to all.</li>\n" +
			"<li>If today is 10 day before self review end date, send reminder generic email to all.</li>\n" +
			"<li>If today is 5 days or less before self review end date, send reminder email to all eligible employee email who have not complete Review in batch of 25 emailId & 1 consolidate email per manager.</li>\n" +
			"<li>If today is self review end date, generic email to manager DL & reviewer DL that reviews are ready, update pending to manager review, 1-1 mail to such each employee.</li>\n" +
			"<li>If today is 15, 10 , 5 or less days before manager review end date, send manager review pending report per manager.</li>\n" +
			"<li>If today is 5 or less days before manager review end date, 1-1 mail to each employee for each project pending manager review.</li>\n" +
			"<li>If today is manager review end date, update review with manager rating 2 and move to complete, 1-1 employee email for such case.</li>\n" +
			"<li>If today is 1 day after manager review end date, consolidated email to hr for all review rated less than 3.</li>\n" +
			"<li>If today is complete end date, close all rollback and close requests, find review which are still open (possibly due to rollback/end back), update such forms with default rating to which ever state applicable and move it complete.</li>\n" +
			"<ul>\n" +
			"</li>\n" +
			"</ul>\n" +
			"</li>\n" +
			"</ol>";


}