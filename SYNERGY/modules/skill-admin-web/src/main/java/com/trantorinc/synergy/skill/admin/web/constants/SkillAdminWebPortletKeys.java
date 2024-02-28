package com.trantorinc.synergy.skill.admin.web.constants;

import java.util.Arrays;
import java.util.List;

/**
 * @author saurabh.kumar
 */
public class SkillAdminWebPortletKeys {

    public static final String SKILLADMINWEB =
            "com_trantorinc_synergy_skill_admin_web_SkillAdminWebPortlet";
    public static final Integer SUBMISSION_STATE = 0;
    public static final String ACTION_REQUIRED = "Action Required";
    public static final String NO_ACTION_REQUIRED = "No Action Required";
    public static final String NO_SKILL_ADDED = "No Skill Added";
    public static final String BASIC = "Basic";
    public static final String BEGINNER = "Beginner";
    public static final String INTERMEDIATE = "Intermediate";
    public static final String ADVANCED = "Advanced";
    public static final String EXPERT = "Expert";
    public static final int SKILL_APPROVED = 1;
    public static final String CERTIFICATE = "Certificate";
    public static final String NO_EXPIRY_DATE = "No Expiry Date";
    public static final String CORESKILLS = "coreSkills";
    public static final String SUBSKILLS = "subSkills";
    public static final String CERTIFICATES = "certificates";
    public static final int STATUS_DISAPPROVED = 2;
    public static final List<String> HEADERS_SKILL_REPORT = Arrays.asList(
            "Ecode", "Name", "Account", "Manager", "Reviewer", "Core Skill", "Sub Skill", "Status", "Type", "Level", "Required Level", "Tool", "Validity", "Version");
    public static final List<String> HEADERS_CERTIFICATE_REPORT = Arrays.asList(
            "Ecode", "Name", "Account", "Manager", "Reviewer", "Certificate Category", "Certificate Name", "Certificate Description", "Certificate Expiry Date");
    public static final String ARROW = "<br>-->";
    public static final String OPEN_BRACES = "(";
    public static final String CLOSE_BRACES = ")<br>";
    public static final String SUBJECT_CHANGE_SKILL_PROFICIENCYLEVEL = "Skill & Certification Module - Changes in Skill details";
    public static final String BODY_CHANGE_SKILL_PROFICIENCYLEVEL = "Dear Employee,<br><br>Manager {0} has updated Proficiency Level for following Skill Details submitted by you.<br>{1}<br>Please review changes and connect with your manager as needed.<br><br>Please <a href=''{2}''>click here</a> to go to Skill System.<br><br>Regards,<br>Synergy Admin";
    public static final String SUBJECT_SKILL_REJECTION = "Skill & Certification Module - Changes in Skill details required";
    public static final String BODY_SKILL_REJECTION = "Dear Employee,<br><br>Manager {0} has asked for an update/change in Skill Details submitted by you.<br>Please update and incorporate the changes in your Skill Details and submit again in 2 working days.<br><br>Please <a href=''{1}''>click here</a> to go to Skill Set System.<br><br>Regards,<br>Synergy Admin";


}