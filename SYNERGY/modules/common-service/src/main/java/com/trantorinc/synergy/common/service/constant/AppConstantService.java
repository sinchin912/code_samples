package com.trantorinc.synergy.common.service.constant;

import com.liferay.portal.kernel.util.PropsUtil;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class AppConstantService {

    private AppConstantService(){
        // do nothing
    }
    public static final int COMPANY_ID = 20096;
    public static final String URL_PORTAL = "https://synergy{0}.trantorinc.com/group/portal";
    public static final String PROD = "PROD";
    public static final String ENVIRONMENT = PropsUtil.get("portal.environment");
    public static final String GOOGLE_FOLDER_ID = PropsUtil.get("google.folderId");
    public static final String DEFAULT_ECODE = "E00001";
    public static final String DL_BGV = "bgv@trantorinc.com";
    public static final String DL_FINANCE = "finance@trantorinc.com";
    public static final String DL_IT = "it@trantorinc.com";
    public static final String DL_AMINDIA = "am.india@trantorinc.com";
    public static final String DL_TRANTORINDIA = "trantorindia@trantorinc.com";
    public static final String DL_TRANTORALL = "trantorall@trantorinc.com";
    public static final String DL_MANAGER = "managers@trantorinc.com";
    public static final String DL_REVIEWER = "leads@trantorinc.com";
    public static final String DL_INTRANET = "dlintrliferaycoe@trantorinc.com";
    public static final String DL_HR = "hr@trantorinc.com";
    public static final String DL_ADMIN = "trantoradmin@trantorinc.com";
    public static final String ROLE_EMPLOYEE ="Trantor_Employee";
    public static final String ROLE_HR ="Trantor_HR";
    public static final String ROLE_ADMIN ="Trantor_Admin";
    public static final String ROLE_COORDINATOR ="Trantor_Coordinator";
    public static final String ROLE_FINANCE ="Trantor_Finance";
    public static final String ROLE_IT ="Trantor_IT";
    public static final String ROLE_MANAGER ="Trantor_Manager";
    public static final String ROLE_LEADER ="Trantor_Leader";
    public static final String ROLE_RECRUITER ="Trantor_Recruiter";
    public static final String ROLE_TEAMLEAD ="Trantor_Teamlead";
    public static final String ROLE_ANNOUNCEMENT ="Trantor_Announcement";
    public static final String ROLE_PERFORMANCE ="Trantor_Performance";
    public static final String ROLE_DOCUMENT ="Trantor_Document";
    public static final String URL_SKILL = "/skill";
    public static final String URL_SKILL_ADMIN = "/skill-admin";
    public static final String URL_PROBATION_ADMIN = "/probation-admin";
    public static final String URL_PERFORMANCE = "/performance";
    public static final String URL_PERFORMANCE_ADMIN = "/performance-admin";
    public static final String URL_EXPENSE = "/expense";
    public static final String URL_EXPENSE_ADMIN = "/expense-admin";
    public static final String URL_HR_ADMIN = "/hr-admin";
    public static final String URL_SECRET_SANTA ="/secret-santa";
    public static final String URL_EXIT = "/exit";
    public static final String URL_EXIT_ADMIN = "/exit-admin";
    public static final String TASK_AM_LMS = "Lms_AM";
    public static final String TASK_PM_LMS = "Lms_PM";
    public static final String TASK_AM_SKILL = "Skill_AM";
    public static final String TASK_PM_SKILL = "Skill_PM";
    public static final String TASK_AM_PROBATION = "Probation_AM";
    public static final String TASK_PM_PROBATION = "Probation_PM";
    public static final String TASK_AM_EXIT = "Exit_AM";
    public static final String TASK_PM_EXIT = "Exit_PM";
    public static final String TASK_GENERIC = "Generic";
    public static final String TASK_PERFORMANCE = "Performance";
    public static final String TASK_GAME = "Game";
    public static final String MODULE_GENERIC = "Generic";
    public static final String MODULE_LMS = "Lms";
    public static final String MODULE_PROBATION = "Probation";
    public static final String MODULE_SKILL = "Skill";
    public static final String MODULE_PERFORMANCE = "Performance";
    public static final String MODULE_GAME = "Game";
    public static final String MODULE_EMAIL = "Email";
    public static final String MODULE_EXIT = "Exit";
    public static final String MODULE_EXPENSE = "Expense";
    public static final String MODULE_TRAINING = "Training";
    public static final DateTimeFormatter FORMATTER_DD_MMM_YYYY_HH_MM_SS = DateTimeFormatter.ofPattern("dd MMM, yyyy hh:mm:ss aa");
    public static final DateTimeFormatter FORMATTER_YYYY_MM_DD_HH_MM_SS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter FORMATTER_YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter FORMATTER_DD_MMM_YYYY = DateTimeFormatter.ofPattern("dd MMM, yyyy");
    public static final DateTimeFormatter FORMATTER_DD_MMM_EEE = DateTimeFormatter.ofPattern("dd MMM (EEE)");
    public static final String BLANK = "";
    public static final String COMMA = ",";
    public static final String HYPHEN = "-";
    public static final String JPG = ".jpg";
    public static final String PRIMARY = "Primary";
    public static final String SECONDARY = "Secondary";
    public static final String SUCCESS = "Success";
    public static final String FAIL = "Fail";
    public static final String YES = "Yes";
    public static final String NO = "No";
    public static final int MAX_EMAIL_LIST = 25;
    public static final String DRIVER_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String LMS_CONNECTION = "jdbc:sqlserver://192.168.176.125:1433;instanceName=SQLEXPRESS;databaseName=hms;encrypt=true;trustServerCertificate=true";
    public static final String LMS_USER = "hms.readonly";
    public static final String LMS_PWD = "Hm$R3@d0nly@543";
    public static final String SQL_LMS_EMPLOYEE = "SELECT " +
            "EmployeeCode as ecode, " +
            "IsActive as status, " +
            "EmpType as type, " +
            "NameOnPan as name, " +
            "DateOfJoining as doj, " +
            "DateOfBirth as dob, " +
            "Email as email, " +
            "GradeBand as band, " +
            "Designation as designation, " +
            "ManagerEcode as manager, " +
            "LeadEcode as reviewer, " +
            "Account as account, " +
            "ConfirmationStatus as confirmed, " +
            "[Current Contact] as mobile, " +
            "[Project(s)] as project, " +
            "TotalExperience as experience, " +
            "[Core Skill Set] as skill, " +
            "OfficeAddress as location, " +
            "BillingUpdaterEcode as coordinator, " +
            "EmployeePhotoBase64 as photo, " +
            "IsDeleted as deleted "+
            "FROM hms.readonly.VW_DataForSynergy";
    public static final String SQL_LMS_HOLIDAY = "SELECT description, holidayDate FROM hms.readonly.VW_HolidayListForSynergy";
    public static final int MAX_PROJECT_LENGTH = 199;
    public static final int MAX_SKILL_LENGTH = 74;
    public static final String SID_SIR = "siddharth.singh@trantorinc.com";
    public static final String FTE = "FTE";
    public static final String REMINDER = "Reminder : ";
    public static final String DAILY_REMINDER = "Daily Reminder : ";
    public static final String USER_MANUAL_TEMPLATE = "{0} - {1} User Manual.pdf";
    public static final List<String> INDIA_STATE = Arrays.asList("Andhra Pradesh","Andaman and Nicobar Islands",
            "Arunachal Pradesh","Assam","Bihar","Chandigarh","Chhattisgarh","Dadra and Nagar Haveli","Daman and Diu",
            "Delhi","Goa","Gujarat","Haryana","Himachal Pradesh","Jammu and Kashmir","Jharkhand","Karnataka","Kerala",
            "Ladakh","Lakshadweep","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Odisha",
            "Puducherry","Punjab","Rajasthan","Sikkim","Tamil Nadu","Telangana","Tripura","Uttar Pradesh","Uttarakhand","West Bengal");
    public static final String ROW_START = "<tr><td style='border:1px solid black;'>";
    public static final String CELL_SEPARATOR = "</td><td style='border:1px solid black;'>";
    public static final String ROW_END = "</td></tr>";
    public static final String TABLE_END = "</tbody></table>";
    public static final String LINE_SEPARATOR = "line.separator";
    public static final String NO_HISTORY = "====== No History ======";
    public static final String HISTORY = "====== History ======";
    public static final String SUBMITTED = "Submitted";
    public static final String APPROVED = "Approved";
    public static final String DISAPPROVED = "Disapproved";
    public static final String OPEN = "Open";
    public static final String CLOSED = "Closed";
    public static final String REJECTED = "Rejected";
    public static final String MODE_VIEW = "view";
    public static final String MODE_EDIT = "edit";
}
