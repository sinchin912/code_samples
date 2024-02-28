package com.trantorinc.synergy.billing.web.util;

import com.trantorinc.synergy.billing.core.model.Billing;
import com.trantorinc.synergy.birthday.core.model.EmployeeData;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import java.util.*;
import java.util.stream.Collectors;

import static com.trantorinc.synergy.billing.web.constants.BillingWebPortletKeys.*;

public class BillingUtil {

    private BillingUtil() {

    }

    public static Workbook createFinanceWorkbook(List<EmployeeData> allEmployeeList, List<Billing> allBillingData) {
        Workbook workbook = new SXSSFWorkbook();
        Sheet billingSheet = workbook.createSheet(BILLING_DETAILS);
        billingSheet.createFreezePane( 0, 1, 0, 1 );
        int currentIndex = 0;
        Map<String, String> projectMap = new HashMap<>();
        projectMap.put( "42Layers","42Layers Corp");
        projectMap.put( "Answer Lab","AnswerLab, LLC");
        projectMap.put( "APC","ApplePie Capital, LLC");
        projectMap.put( "Avaya","ApplePie Capital, LLC");
        projectMap.put( "Buck","Buck Global, LLC");
        projectMap.put( "CheckFerret","CheckFerret , Inc.");
        projectMap.put( "Comcate","Comcate, Inc.");
        projectMap.put( "GGA","Customized Service Administrators, Inc.");
        projectMap.put( "EMS","Early Morning Software");
        projectMap.put( "EQ Holdings","Equus Holdings,Inc.");
        projectMap.put( "Extron","Extron, Inc.");
        projectMap.put( "Finnings","Finning International Inc.");
        projectMap.put( "FIQ","FortressIQ");
        projectMap.put( "Genpact","Genpact(UK Limited)");
        projectMap.put( "HBG","Hachette Book Group");
        projectMap.put( "HeyOye","HeyOye, Inc.");
        projectMap.put( "IntuityMedical","Intuity Medical Inc");
        projectMap.put( "Kaimo LLC","Kaimo, LLC.");
        projectMap.put( "Kibo","Kibo Commerce");
        projectMap.put( "LaJolla","La Jolla Group");
        projectMap.put( "Lending Point","Lending Point");
        projectMap.put( "LIDO","Lido");
        projectMap.put( "McKesson","McKesson, Inc.");
        projectMap.put( "MDBACKLINE","MD Backline");
        projectMap.put( "Metric Stream","MetricStream, Inc.");
        projectMap.put( "BBU - Projects","PayPal - BBU");
        projectMap.put( "FOAHI","Finance of America-Home Improvement");
        projectMap.put( "Ziosk","Tabletop Media, LLC d/b/a Ziosk");
        projectMap.put( "ThermoFisher","Thermo Fisher");
        projectMap.put( "Thomson Reuters","Thomson Reuters");
        projectMap.put( "Integra LifeSciences","Tribola Tech, Inc");
        projectMap.put( "VMware","VMware, Inc.");
        projectMap.put( "URW","Westfield America, Inc.");
        projectMap.put( "XR Sports","XR Sports Group");
        projectMap.put( "Zebrium","Zebrium");

        List<Billing> allBillingDataComplete = allBillingData.stream().filter(s->!s.getDeleted()).filter(s -> !(s.getAccount().
                equalsIgnoreCase(GOOGLE) || s.getAccount().equalsIgnoreCase(CONTINUUM) || s.getAccount().equalsIgnoreCase(CONTINUUMGLOBAL))).collect(Collectors.toList());
        Set<String> ecodeList = allBillingDataComplete.stream().filter(s -> !s.getShared()).map(Billing::getEcode).collect(Collectors.toSet());
        List<List<String>> billingRows = new ArrayList<>();
        for (String ecode : ecodeList) {
            Set<String> accountList = allBillingDataComplete.stream().filter(s -> s.getEcode().equalsIgnoreCase(ecode)).filter(s -> !s.getShared()).map(Billing::getAccount).collect(Collectors.toSet());
            for (String account : accountList) {
                List<Billing> employeeBillingDetailsList = allBillingDataComplete.stream().filter(s -> s.getEcode().equalsIgnoreCase(ecode)).
                        filter(s -> s.getAccount().equals(account)).filter(s -> !s.getShared()).collect(Collectors.toList());
                employeeBillingDetailsList.sort((Billing ac1, Billing ac2) -> ac2.getUpdatedDate().compareTo(ac1.getUpdatedDate()));
                Billing billingData = employeeBillingDetailsList.get(0);
                EmployeeData employeeData = null;
                List<EmployeeData> employeeDataList = allEmployeeList.stream().filter(s -> s.getEmpId().equalsIgnoreCase(ecode)).collect(Collectors.toList());
                if (!employeeDataList.isEmpty()) {
                    employeeData = employeeDataList.get(0);
                }
                List<String> bill = new ArrayList<>();
                //Ecode column -1
                bill.add(ecode);
                //Name column -2
                if (employeeData != null) {
                    bill.add(employeeData.getUserName());
                } else {
                    bill.add(BLANK);
                }
                //Manager ecode column -3
                if(billingData.getManagerEcode()!=null){
                    bill.add(billingData.getManagerEcode());
                }else{
                    bill.add(BLANK);
                }
                //Manager name column -4
                List<EmployeeData> employeeManagerList = allEmployeeList.stream().filter(s -> s.getEmpId().equalsIgnoreCase(billingData.getManagerEcode())).collect(Collectors.toList());
                if(!employeeManagerList.isEmpty()){
                    bill.add(employeeManagerList.get(0).getUserName());
                }else{
                    bill.add(BLANK);
                }
                //Lead code -5
                if(billingData.getLeadEcode()!=null){
                    bill.add(billingData.getLeadEcode());
                }else{
                    bill.add(BLANK);
                }
                //Lead Name -6
                List<EmployeeData> employeeLeadList = allEmployeeList.stream().filter(s -> s.getEmpId().equalsIgnoreCase(billingData.getLeadEcode())).collect(Collectors.toList());
                if(!employeeLeadList.isEmpty()){
                    bill.add(employeeLeadList.get(0).getUserName());
                }else{
                    bill.add(BLANK);
                }
                // DOJ column -7
                if (employeeData != null && employeeData.getDoj() != null) {
                    bill.add(formatter.format(employeeData.getDoj()));
                } else {
                    bill.add(BLANK);
                }
                //Account column-8 //verify with Sachin Sir
                String accountName = projectMap.get(billingData.getAccount());
                if(accountName == null)
                {
                    bill.add(billingData.getAccount());
                }else {
                    bill.add(accountName);
                }

                //Project column -9
                if(billingData.getProject()!=null){
                    bill.add(billingData.getProject());
                }else{
                    bill.add(BLANK);
                }

                //Billing current date -10
                if(billingData.getCurrent()!=null) {
                    bill.add(billingData.getCurrent());
                }else{
                    bill.add(BLANK);
                }
                //Month hours -11
                if(billingData.getMonthHours()!=null) {
                    bill.add(billingData.getMonthHours());
                }else{
                    bill.add(BLANK);
                }

                //Billable hours -12
                if(billingData.getBillableHours()!=null) {
                    bill.add(billingData.getBillableHours());
                }else{
                    bill.add(BLANK);
                }
                //Shadow resource type-13
                if (billingData.getShadowResourceType() != null) {
                    bill.add(billingData.getShadowResourceType());
                } else {
                    bill.add(BLANK);
                }
                //Shadow column date-14
                if (billingData.getShadowStartDate() != null) {
                    bill.add(formatter.format(billingData.getShadowStartDate()));
                } else {
                    bill.add(BLANK);
                }
                //Bench column date -15
                if (billingData.getBenchStartDate() != null) {
                    bill.add(formatter.format(billingData.getBenchStartDate()));
                } else {
                    bill.add(BLANK);
                }
                //Billing start date -16
                if (billingData.getBillingStartDate() != null) {
                    bill.add(formatter.format(billingData.getBillingStartDate()));
                } else {
                    bill.add(BLANK);
                }
                //Employee Status -17
                if (billingData.getEmployeeStatus() != null) {
                    bill.add(billingData.getEmployeeStatus());
                } else {
                    bill.add(BLANK);
                }
                //LWD -18
                if (billingData.getLastWorkingDate() != null) {
                    bill.add(formatter.format(billingData.getLastWorkingDate()));
                } else {
                    bill.add(BLANK);
                }
                //Billing end date -19
                if (billingData.getBillingEndDate() != null) {
                    bill.add(formatter.format(billingData.getBillingEndDate()));
                } else {
                    bill.add(BLANK);
                }
                //Percentage -20
                if (billingData.getPercentUtilization() != null) {
                    bill.add(billingData.getPercentUtilization());
                } else {
                    bill.add(BLANK);
                }
                //Shared status -21
                bill.add("No");

                //Vertical-22
                if (billingData.getVertical() != null) {
                    bill.add(billingData.getVertical());
                } else {
                    bill.add(BLANK);
                }
                //Remarks-23
                if (billingData.getRemarks() != null) {
                    bill.add(billingData.getRemarks());
                } else {
                    bill.add(BLANK);
                }

                //Account Movement date - 24
                if (billingData.getAccountMovementDate() != null) {
                    bill.add(formatter.format(billingData.getAccountMovementDate()));
                } else {
                    bill.add(BLANK);
                }
                billingRows.add(bill);
            }
        }
        List<Billing> sharedBillingData = allBillingDataComplete.stream().filter(Billing::getShared).collect(Collectors.toList());
        Set<String> sharedEcodeList = sharedBillingData.stream().map(Billing::getEcode).collect(Collectors.toSet());
        if (!sharedEcodeList.isEmpty())
        {
            for (String ecode : sharedEcodeList) {
                List<Billing> employeeSharedBillingDetailsList = sharedBillingData.stream().filter(s -> s.getEcode().equalsIgnoreCase(ecode)).collect(Collectors.toList());
                Set<String> accountList = employeeSharedBillingDetailsList.stream().map(Billing::getAccount).collect(Collectors.toSet());
                Set<String> managerEcodeList = employeeSharedBillingDetailsList.stream().map(Billing::getUpdatedBy).collect(Collectors.toSet());
                for (String account : accountList) {
                    for (String mEcodes : managerEcodeList) {
                        List<Billing> employeeSharedByManagerList = employeeSharedBillingDetailsList.stream().filter(s -> s.getAccount().equals(account)).filter(s -> s.getUpdatedBy().equalsIgnoreCase(mEcodes)).collect(Collectors.toList());
                        if (!employeeSharedByManagerList.isEmpty()) {
                            employeeSharedByManagerList.sort((Billing ac1, Billing ac2) -> ac2.getUpdatedDate().compareTo(ac1.getUpdatedDate()));
                            Billing sharedEmployeeBillingData = employeeSharedByManagerList.get(0);
                            List<String> sharedBill = new ArrayList<>();
                            EmployeeData sharedEmployeeData = null;
                            List<EmployeeData> sharedEmployeeDataList = allEmployeeList.stream().filter(s -> s.getEmpId().equalsIgnoreCase(ecode)).collect(Collectors.toList());
                            if (!sharedEmployeeDataList.isEmpty()) {
                                sharedEmployeeData = sharedEmployeeDataList.get(0);
                            }
                            //Shared ecode-1
                            sharedBill.add(ecode);
                            //Shared ename-2
                            if(sharedEmployeeData != null){
                                sharedBill.add(sharedEmployeeData.getUserName());
                            } else {
                                sharedBill.add(BLANK);
                            }
                            //shared manager ecode -3
                            if(sharedEmployeeBillingData.getManagerEcode()!=null){
                                sharedBill.add(sharedEmployeeBillingData.getManagerEcode());
                            }
                            else{
                                sharedBill.add(BLANK);
                            }
                            //shared manager name -4
                            List<EmployeeData> employeeManagerList = allEmployeeList.stream().filter(s -> s.getEmpId().equalsIgnoreCase(sharedEmployeeBillingData.getManagerEcode())).collect(Collectors.toList());
                            if(!employeeManagerList.isEmpty()){
                                sharedBill.add(employeeManagerList.get(0).getUserName());
                            }else{
                                sharedBill.add(BLANK);
                            }
                            //Lead code -5
                            if(sharedEmployeeBillingData.getLeadEcode()!=null){
                                sharedBill.add(sharedEmployeeBillingData.getLeadEcode());
                            }
                            else{
                                sharedBill.add(BLANK);
                            }
                            //Lead Name -6
                            List<EmployeeData> employeeLeadList = allEmployeeList.stream().filter(s -> s.getEmpId().equalsIgnoreCase(sharedEmployeeBillingData.getLeadEcode())).collect(Collectors.toList());
                            if(!employeeLeadList.isEmpty()){
                                sharedBill.add(employeeLeadList.get(0).getUserName());
                            }else{
                                sharedBill.add(BLANK);
                            }
                            //DOJ -7
                            if (sharedEmployeeData != null && sharedEmployeeData.getDoj() != null) {
                                sharedBill.add(formatter.format(sharedEmployeeData.getDoj()));
                            } else {
                                sharedBill.add(BLANK);
                            }
                            //Account -8
                            String accountName = projectMap.get(sharedEmployeeBillingData.getAccount());
                            if(accountName == null)
                            {
                                sharedBill.add(sharedEmployeeBillingData.getAccount());
                            }else {
                                sharedBill.add(accountName);
                            }
                            //Project -9
                            if(sharedEmployeeBillingData.getProject()!=null){
                                sharedBill.add(sharedEmployeeBillingData.getProject());
                            }
                            else{
                                sharedBill.add(BLANK);
                            }
                            //Billing current -10
                            if(sharedEmployeeBillingData.getCurrent()!=null){
                                sharedBill.add(sharedEmployeeBillingData.getCurrent());
                            }
                            else{
                                sharedBill.add(BLANK);
                            }
                            //Month hours -11
                            if(sharedEmployeeBillingData.getMonthHours()!=null){
                                sharedBill.add(sharedEmployeeBillingData.getMonthHours());
                            }
                            else{
                                sharedBill.add(BLANK);
                            }
                            //Billable hours -12
                            if(sharedEmployeeBillingData.getBillableHours()!=null){
                                sharedBill.add(sharedEmployeeBillingData.getBillableHours());
                            }
                            else{
                                sharedBill.add(BLANK);
                            }
                            //Shadow resource type-13
                            if (sharedEmployeeBillingData.getShadowResourceType() != null) {
                                sharedBill.add(sharedEmployeeBillingData.getShadowResourceType());
                            } else {
                                sharedBill.add(BLANK);
                            }
                            //Shadow column date-14
                            if (sharedEmployeeBillingData.getShadowStartDate() != null) {
                                sharedBill.add(formatter.format(sharedEmployeeBillingData.getShadowStartDate()));
                            } else {
                                sharedBill.add(BLANK);
                            }
                            //Bench start date -15
                            if (sharedEmployeeBillingData.getBenchStartDate() != null) {
                                sharedBill.add(formatter.format(sharedEmployeeBillingData.getBenchStartDate()));
                            } else {
                                sharedBill.add(BLANK);
                            }
                            //Billing start date -16
                            if (sharedEmployeeBillingData.getBillingStartDate() != null) {
                                sharedBill.add(formatter.format(sharedEmployeeBillingData.getBillingStartDate()));
                            } else {
                                sharedBill.add(BLANK);
                            }
                            //Employee status-17
                            if(sharedEmployeeBillingData.getEmployeeStatus()!=null){
                                sharedBill.add(sharedEmployeeBillingData.getEmployeeStatus());
                            }
                            else{
                                sharedBill.add(BLANK);
                            }
                            //LWD-18
                            if (sharedEmployeeBillingData.getLastWorkingDate() != null) {
                                sharedBill.add(formatter.format(sharedEmployeeBillingData.getLastWorkingDate()));
                            } else {
                                sharedBill.add(BLANK);
                            }
                            //Billing end date -19
                            if (sharedEmployeeBillingData.getBillingEndDate() != null) {
                                sharedBill.add(formatter.format(sharedEmployeeBillingData.getBillingEndDate()));
                            } else {
                                sharedBill.add(BLANK);
                            }
                            //Percentage -20
                            if(sharedEmployeeBillingData.getPercentUtilization()!=null){
                                sharedBill.add(sharedEmployeeBillingData.getPercentUtilization());
                            }
                            else{
                                sharedBill.add(BLANK);
                            }
                            //shared status -21
                            sharedBill.add("Yes");

                            //Vertical-22
                            if(sharedEmployeeBillingData.getVertical()!=null){
                                sharedBill.add(sharedEmployeeBillingData.getVertical());
                            }
                            else{
                                sharedBill.add(BLANK);
                            }
                            //Remarks-23
                            if(sharedEmployeeBillingData.getRemarks()!=null){
                                sharedBill.add(sharedEmployeeBillingData.getRemarks());
                            }
                            else{
                                sharedBill.add(BLANK);
                            }

                            //Account movement date - 24
                            if (sharedEmployeeBillingData.getAccountMovementDate() != null) {
                                sharedBill.add(formatter.format(sharedEmployeeBillingData.getAccountMovementDate()));
                            } else {
                                sharedBill.add(BLANK);
                            }
                            billingRows.add(sharedBill);
                        }
                    }
                }
            }
        }
        currentIndex = writeTableHeader(tableHeaderFinanceBilling, workbook, billingSheet, currentIndex);
        writeTableRows(billingRows, billingSheet, currentIndex);
        return workbook;
    }

    public static Workbook createHrWorkbook(List<EmployeeData> allEmployeeList, List<Billing> allBillingData) {
        Workbook workbook = new SXSSFWorkbook();
        Sheet billingSheet = workbook.createSheet(BILLING_DETAILS);
        int currentIndex = 0;
        List<Billing> allBillingDataExtracted = allBillingData.stream().filter(s -> !(s.getAccount().equalsIgnoreCase(GOOGLE) || s.getAccount().equalsIgnoreCase(CONTINUUM) || s.getAccount().equalsIgnoreCase(CONTINUUMGLOBAL))).filter(s->!s.getProjectUpdated()).collect(Collectors.toList());
        Set<String> ecodeList = allEmployeeList.stream().filter(EmployeeData::getStatus).filter(s -> !(s.getProject().equalsIgnoreCase(GOOGLE) || s.getProject().equalsIgnoreCase(CONTINUUM) || s.getProject().equalsIgnoreCase(CONTINUUMGLOBAL))).map(EmployeeData::getEmpId).collect(Collectors.toSet());
        List<List<String>> billingRows = new ArrayList<>();
        for (String ecode : ecodeList)
        {

            EmployeeData employeeData = null;
            List<EmployeeData> employeeDataList = allEmployeeList.stream().filter(s -> s.getEmpId().equalsIgnoreCase(ecode)).collect(Collectors.toList());
            if(!employeeDataList.isEmpty()){
                employeeData = employeeDataList.get(0);
            }
            List<Billing> employeeBillingDetailsList = allBillingDataExtracted.stream().filter(s -> s.getEcode().equalsIgnoreCase(ecode)).filter(s -> !s.getShared()).filter(s -> s.getCurrent() != null).collect(Collectors.toList());
            if (!employeeBillingDetailsList.isEmpty())
            {
                List<String> bill = new ArrayList<>();
                employeeBillingDetailsList.sort((Billing ac1, Billing ac2) -> ac2.getUpdatedDate().compareTo(ac1.getUpdatedDate()));
                Billing billingData = employeeBillingDetailsList.get(0);
                bill.add(ecode); //Ecode
                if(employeeData != null) { //Name
                    bill.add(employeeData.getUserName());
                } else {
                    bill.add(BLANK);
                }
                bill.add(billingData.getManagerEcode()); //Manager Ecode

                List<EmployeeData> managerDatas = allEmployeeList.stream().filter(s -> s.getEmpId().equalsIgnoreCase(billingData.getManagerEcode())).collect(Collectors.toList());
                if (!managerDatas.isEmpty()) {  //Manager Name
                    bill.add(managerDatas.get(0).getUserName());
                } else {
                    bill.add(BLANK);
                }

                if(employeeData != null) { //Designation
                    bill.add(employeeData.getDesignation());
                } else {
                    bill.add(BLANK);
                }
                if (employeeData != null && employeeData.getDoj() != null) { //DOJ
                    bill.add(formatter.format(employeeData.getDoj()));
                } else {
                    bill.add(BLANK);
                }
                if(employeeData != null) {
                    bill.add(employeeData.getExperience()); //Experience
                    bill.add(employeeData.getSkill()); // Skill
                } else {
                    bill.add(BLANK);
                    bill.add(BLANK);
                }
                bill.add(billingData.getAccount()); //Account
                bill.add(billingData.getProject()); // Project

                bill.add(billingData.getCurrent()); //Current
                bill.add(billingData.getCurrentPlusOneMonth()); //Current+1
                bill.add(billingData.getCurrentPlusTwoMonth()); //Current+2
                bill.add(billingData.getShadowResourceType()); //Shadow Resource Type
                if (billingData.getShadowStartDate() != null) { //Shadow Start Date
                    bill.add(formatter.format(billingData.getShadowStartDate()));
                } else {
                    bill.add(BLANK);
                }
                if (billingData.getBenchStartDate() != null) { //Bench Start Date
                    bill.add(formatter.format(billingData.getBenchStartDate()));
                } else {
                    bill.add(BLANK);
                }
                if (billingData.getBillingStartDate() != null) { //Biling start date
                    bill.add(formatter.format(billingData.getBillingStartDate()));
                } else {
                    bill.add(BLANK);
                }

                bill.add(billingData.getEmployeeStatus()); //Employee Status
                if (billingData.getLastWorkingDate() != null) { // Last working day
                    bill.add(formatter.format(billingData.getLastWorkingDate()));
                } else {
                    bill.add(BLANK);
                }
                if (billingData.getBillingEndDate() != null) { //Billing end date
                    bill.add(formatter.format(billingData.getBillingEndDate()));
                } else {
                    bill.add(BLANK);
                }

                bill.add("No"); //Shared status
                bill.add(billingData.getAllocationStatus()); //Allocation Status
                bill.add(billingData.getRemarks()); //Remarks
                bill.add(billingData.getPercentUtilization()); //Percentage Utilization Per Month
                bill.add(billingData.getCoordinatorEcode()); //Coordinator Ecode

                List<EmployeeData> coordinatorDatas = allEmployeeList.stream().filter(s -> s.getEmpId().equalsIgnoreCase(billingData.getCoordinatorEcode())).collect(Collectors.toList());
                if (!coordinatorDatas.isEmpty()) {  //Coordinator Name
                    bill.add(coordinatorDatas.get(0).getUserName());
                } else {
                    bill.add(BLANK);
                }

                bill.add(billingData.getLeadEcode()); //Lead Ecode
                List<EmployeeData> leadDatas = allEmployeeList.stream().filter(s -> s.getEmpId().equalsIgnoreCase(billingData.getLeadEcode())).collect(Collectors.toList());
                if (!leadDatas.isEmpty()) {  //Lead Name
                    bill.add(leadDatas.get(0).getUserName());
                } else {
                    bill.add(BLANK);
                }
                if (billingData.getAccountMovementDate() != null) {
                    bill.add(formatter.format(billingData.getAccountMovementDate())); //Account Movement Date
                } else {
                    bill.add(BLANK);
                }
                billingRows.add(bill);
            }

        }
        List<Billing> sharedBillingData = allBillingDataExtracted.stream().filter(Billing::getShared).filter(s -> !s.getDeleted()).collect(Collectors.toList());
        Set<String> sharedEcodeList = sharedBillingData.stream().map(Billing::getEcode).collect(Collectors.toSet());
        if (!sharedEcodeList.isEmpty())
        {
            for (String ecode : sharedEcodeList) {
                EmployeeData sharedEmployeeData = null;
                List<EmployeeData> sharedEmployeeDataList = allEmployeeList.stream().filter(EmployeeData::isStatus).filter(s -> s.getEmpId().equalsIgnoreCase(ecode)).collect(Collectors.toList());
                if (!sharedEmployeeDataList.isEmpty())
                {
                    sharedEmployeeData = sharedEmployeeDataList.get(0);

                    List<Billing> employeeSharedBillingDetailsList = sharedBillingData.stream().filter(s -> s.getEcode().equalsIgnoreCase(ecode)).collect(Collectors.toList());
                    Set<String> accountList = employeeSharedBillingDetailsList.stream().map(Billing::getAccount).collect(Collectors.toSet());
                    Set<String> managerEcodeList = employeeSharedBillingDetailsList.stream().map(Billing::getUpdatedBy).collect(Collectors.toSet());
                    for (String account : accountList)
                    {
                        for (String mEcodes : managerEcodeList) {
                            List<Billing> employeeSharedByManagerList = employeeSharedBillingDetailsList.stream().filter(s -> s.getAccount().equals(account)).filter(s -> s.getUpdatedBy().equalsIgnoreCase(mEcodes)).collect(Collectors.toList());
                            if (!employeeSharedByManagerList.isEmpty())
                            {
                                employeeSharedByManagerList.sort((Billing ac1, Billing ac2) -> ac2.getUpdatedDate().compareTo(ac1.getUpdatedDate()));
                                Billing sharedEmployeeBillingData = employeeSharedByManagerList.get(0);
                                List<String> sharedBill = new ArrayList<>();
                                sharedBill.add(ecode);
                                if (sharedEmployeeData != null) {
                                    sharedBill.add(sharedEmployeeData.getUserName());
                                } else {
                                    sharedBill.add(BLANK);
                                }
                                sharedBill.add(sharedEmployeeBillingData.getManagerEcode());
                                List<EmployeeData> managerDatas = allEmployeeList.stream().filter(s -> s.getEmpId().equalsIgnoreCase(sharedEmployeeBillingData.getManagerEcode())).collect(Collectors.toList());
                                if (!managerDatas.isEmpty()) {  //Manager Name
                                    sharedBill.add(managerDatas.get(0).getUserName());
                                } else {
                                    sharedBill.add(BLANK);
                                }
                                if (sharedEmployeeData != null) {
                                    sharedBill.add(sharedEmployeeData.getDesignation());
                                } else {
                                    sharedBill.add(BLANK);
                                }
                                if (sharedEmployeeData != null && sharedEmployeeData.getDoj() != null) {
                                    sharedBill.add(formatter.format(sharedEmployeeData.getDoj()));
                                } else {
                                    sharedBill.add(BLANK);
                                }
                                if (sharedEmployeeData != null) {
                                    sharedBill.add(sharedEmployeeData.getExperience());
                                    sharedBill.add(sharedEmployeeData.getSkill());
                                } else {
                                    sharedBill.add(BLANK);
                                    sharedBill.add(BLANK);
                                }
                                sharedBill.add(sharedEmployeeBillingData.getAccount());
                                sharedBill.add(sharedEmployeeBillingData.getProject());

                                sharedBill.add(sharedEmployeeBillingData.getCurrent());
                                sharedBill.add(sharedEmployeeBillingData.getCurrentPlusOneMonth());
                                sharedBill.add(sharedEmployeeBillingData.getCurrentPlusTwoMonth());
                                sharedBill.add(sharedEmployeeBillingData.getShadowResourceType());
                                if (sharedEmployeeBillingData.getShadowStartDate() != null) {
                                    sharedBill.add(formatter.format(sharedEmployeeBillingData.getShadowStartDate()));
                                } else {
                                    sharedBill.add(BLANK);
                                }
                                if (sharedEmployeeBillingData.getBenchStartDate() != null) {
                                    sharedBill.add(formatter.format(sharedEmployeeBillingData.getBenchStartDate()));
                                } else {
                                    sharedBill.add(BLANK);
                                }
                                if (sharedEmployeeBillingData.getBillingStartDate() != null) {
                                    sharedBill.add(formatter.format(sharedEmployeeBillingData.getBillingStartDate()));
                                } else {
                                    sharedBill.add(BLANK);
                                }

                                sharedBill.add(sharedEmployeeBillingData.getEmployeeStatus());
                                if (sharedEmployeeBillingData.getLastWorkingDate() != null) {
                                    sharedBill.add(formatter.format(sharedEmployeeBillingData.getLastWorkingDate()));
                                } else {
                                    sharedBill.add(BLANK);
                                }
                                if (sharedEmployeeBillingData.getBillingEndDate() != null) {
                                    sharedBill.add(formatter.format(sharedEmployeeBillingData.getBillingEndDate()));
                                } else {
                                    sharedBill.add(BLANK);
                                }

                                sharedBill.add("Yes");
                                sharedBill.add(BLANK);
                                sharedBill.add(BLANK);
                                sharedBill.add(sharedEmployeeBillingData.getPercentUtilization());
                                sharedBill.add(sharedEmployeeBillingData.getCoordinatorEcode());
                                List<EmployeeData> coordinatorDatas = allEmployeeList.stream().filter(s -> s.getEmpId().equalsIgnoreCase(sharedEmployeeBillingData.getCoordinatorEcode())).collect(Collectors.toList());
                                if (!coordinatorDatas.isEmpty()) {  //Coordinator Name
                                    sharedBill.add(coordinatorDatas.get(0).getUserName());
                                } else {
                                    sharedBill.add(BLANK);
                                }
                                sharedBill.add(sharedEmployeeBillingData.getLeadEcode());
                                List<EmployeeData> leadDatas = allEmployeeList.stream().filter(s -> s.getEmpId().equalsIgnoreCase(sharedEmployeeBillingData.getLeadEcode())).collect(Collectors.toList());
                                if (!leadDatas.isEmpty()) {  //Lead Name
                                    sharedBill.add(leadDatas.get(0).getUserName());
                                } else {
                                    sharedBill.add(BLANK);
                                }
                                sharedBill.add(BLANK);
                                billingRows.add(sharedBill);
                            }
                        }
                    }
                }
            }
        }
        currentIndex = writeTableHeader(tableHeaderHrBilling, workbook, billingSheet, currentIndex);
        writeTableRows(billingRows, billingSheet, currentIndex);
        return workbook;
    }
    public static Workbook createManagerWorkbook(List<EmployeeData> allEmployeeList,List<Billing> allBillingData) {
        Workbook workbook = new SXSSFWorkbook();
        Sheet billingSheet = workbook.createSheet(BILLING_DETAILS);
        int currentIndex = 0;
        List<List<String>> billingRows = new ArrayList<>();
            Set<String> ecodeList = allBillingData.stream().filter(s -> !(s.getProject().equalsIgnoreCase(GOOGLE) || s.getProject().equalsIgnoreCase(CONTINUUM) || s.getProject().equalsIgnoreCase(CONTINUUMGLOBAL))).filter(s->!s.isShared()).map(Billing::getEcode).collect(Collectors.toSet());
            for (String ecode : ecodeList) {
                List<String> bill = new ArrayList<>();
                EmployeeData employeeData = null;
                List<EmployeeData> employeeDataList = allEmployeeList.stream().filter(s -> s.getEmpId().equalsIgnoreCase(ecode)).collect(Collectors.toList());
                if(!employeeDataList.isEmpty()){
                    employeeData = employeeDataList.get(0);
                }
                List<Billing> employeeBillingDetailsList = allBillingData.stream().filter(s -> s.getEcode().equalsIgnoreCase(ecode)).collect(Collectors.toList());
                if (!employeeBillingDetailsList.isEmpty()) {
                    employeeBillingDetailsList.sort((Billing ac1, Billing ac2) -> ac2.getUpdatedDate().compareTo(ac1.getUpdatedDate()));
                    Billing billingData = employeeBillingDetailsList.get(0);
                    bill.add("No");
                    bill.add(ecode);
                    if(employeeData != null) {
                        bill.add(employeeData.getUserName());
                    } else {
                        bill.add(BLANK);
                    }
                    bill.add(billingData.getManagerEcode());
                    List<EmployeeData> managerDatas = allEmployeeList.stream().filter(s -> s.getEmpId().equalsIgnoreCase(billingData.getManagerEcode())).collect(Collectors.toList());
                    if (!managerDatas.isEmpty()) {  //Manager Name
                        bill.add(managerDatas.get(0).getUserName());
                    } else {
                        bill.add(BLANK);
                    }
                    bill.add(billingData.getCoordinatorEcode());
                    List<EmployeeData> coordinatorDatas = allEmployeeList.stream().filter(s -> s.getEmpId().equalsIgnoreCase(billingData.getCoordinatorEcode())).collect(Collectors.toList());
                    if (!coordinatorDatas.isEmpty()) {  //Coordinator Name
                        bill.add(coordinatorDatas.get(0).getUserName());
                    } else {
                        bill.add(BLANK);
                    }
                    bill.add(billingData.getLeadEcode());
                    List<EmployeeData> leadDatas = allEmployeeList.stream().filter(s -> s.getEmpId().equalsIgnoreCase(billingData.getLeadEcode())).collect(Collectors.toList());
                    if (!leadDatas.isEmpty()) {  //Lead Name
                        bill.add(leadDatas.get(0).getUserName());
                    } else {
                        bill.add(BLANK);
                    }
                    if(employeeData != null) {
                        bill.add(employeeData.getDesignation());
                    } else {
                        bill.add(BLANK);
                    }
                    if (employeeData != null && employeeData.getDoj() != null) {
                        bill.add(formatter.format(employeeData.getDoj()));
                    } else {
                        bill.add(BLANK);
                    }
                    if(employeeData != null) {
                        bill.add(employeeData.getExperience());
                        bill.add(employeeData.getSkill());
                    } else {
                        bill.add(BLANK);
                        bill.add(BLANK);
                    }
                    bill.add(billingData.getAccount());
                    bill.add(billingData.getProject());
                    bill.add(billingData.getCurrent());
                    bill.add(billingData.getCurrentPlusOneMonth());
                    bill.add(billingData.getCurrentPlusTwoMonth());
                    bill.add(billingData.getEmployeeStatus());
                    if (billingData.getLastWorkingDate() != null) {
                        bill.add(formatter.format(billingData.getLastWorkingDate()));
                    } else {
                        bill.add(BLANK);
                    }

                    bill.add(billingData.getAllocationStatus());
                    if (billingData.getBenchStartDate() != null) {
                        bill.add(formatter.format(billingData.getBenchStartDate()));
                    } else {
                        bill.add(BLANK);
                    }
                    bill.add(billingData.getShadowResourceType());
                    if (billingData.getShadowStartDate() != null) {
                        bill.add(formatter.format(billingData.getShadowStartDate()));
                    } else {
                        bill.add(BLANK);
                    }
                    if (billingData.getBillingStartDate() != null) {
                        bill.add(formatter.format(billingData.getBillingStartDate()));
                    } else {
                        bill.add(BLANK);
                    }
                    if (billingData.getBillingEndDate() != null) {
                        bill.add(formatter.format(billingData.getBillingEndDate()));
                    } else {
                        bill.add(BLANK);
                    }
                    bill.add(billingData.getPercentUtilization());
                    bill.add(billingData.getBillableHours());
                    bill.add(billingData.getEmployeeRole());
                    bill.add(billingData.getVertical());
                    bill.add(billingData.getRemarks());

                }
                billingRows.add(bill);
            }
            List<Billing> sharedBillingData = allBillingData.stream().filter(Billing::isShared).filter(s->!s.isDeleted()).collect(Collectors.toList());
            Set<String> sharedEcodeList = sharedBillingData.stream().map(Billing::getEcode).collect(Collectors.toSet());
            if (!sharedEcodeList.isEmpty())
            {
                for (String ecode : sharedEcodeList) {
                    List<Billing> employeeSharedBillingDetailsList = sharedBillingData.stream().filter(s -> s.getEcode().equalsIgnoreCase(ecode)).collect(Collectors.toList());
                    for (Billing sharedEmployeeBillingData : employeeSharedBillingDetailsList)
                    {
                    List<String> sharedBill = new ArrayList<>();
                    EmployeeData sharedEmployeeData = null;
                    List<EmployeeData> sharedEmployeeDataList = allEmployeeList.stream().filter(s -> s.getEmpId().equalsIgnoreCase(ecode)).collect(Collectors.toList());
                    if (!sharedEmployeeDataList.isEmpty()) {
                        sharedEmployeeData = sharedEmployeeDataList.get(0);
                    }
                    sharedBill.add("Yes");
                    sharedBill.add(ecode);
                    if (sharedEmployeeData != null) {
                        sharedBill.add(sharedEmployeeData.getUserName());
                    } else {
                        sharedBill.add(BLANK);
                    }
                    sharedBill.add(sharedEmployeeBillingData.getManagerEcode());


                    List<EmployeeData> managerDatas = allEmployeeList.stream().filter(s -> s.getEmpId().equalsIgnoreCase(sharedEmployeeBillingData.getManagerEcode())).collect(Collectors.toList());
                    if (!managerDatas.isEmpty()) {  //Manager Name
                        sharedBill.add(managerDatas.get(0).getUserName());
                    } else {
                        sharedBill.add(BLANK);
                    }
                    sharedBill.add(sharedEmployeeBillingData.getCoordinatorEcode());
                    List<EmployeeData> coordinatorDatas = allEmployeeList.stream().filter(s -> s.getEmpId().equalsIgnoreCase(sharedEmployeeBillingData.getCoordinatorEcode())).collect(Collectors.toList());
                    if (!coordinatorDatas.isEmpty()) {  //Coordinator Name
                        sharedBill.add(coordinatorDatas.get(0).getUserName());
                    } else {
                        sharedBill.add(BLANK);
                    }
                    sharedBill.add(sharedEmployeeBillingData.getLeadEcode());
                    List<EmployeeData> leadDatas = allEmployeeList.stream().filter(s -> s.getEmpId().equalsIgnoreCase(sharedEmployeeBillingData.getLeadEcode())).collect(Collectors.toList());
                    if (!leadDatas.isEmpty()) {  //Lead Name
                        sharedBill.add(leadDatas.get(0).getUserName());
                    } else {
                        sharedBill.add(BLANK);
                    }
                    if (sharedEmployeeData != null) {
                        sharedBill.add(sharedEmployeeData.getDesignation());
                    } else {
                        sharedBill.add(BLANK);
                    }
                    if (sharedEmployeeData != null && sharedEmployeeData.getDoj() != null) {
                        sharedBill.add(formatter.format(sharedEmployeeData.getDoj()));
                    } else {
                        sharedBill.add(BLANK);
                    }
                    if (sharedEmployeeData != null) {
                        sharedBill.add(sharedEmployeeData.getExperience());
                        sharedBill.add(sharedEmployeeData.getSkill());
                    } else {
                        sharedBill.add(BLANK);
                        sharedBill.add(BLANK);
                    }
                    sharedBill.add(sharedEmployeeBillingData.getAccount());
                    sharedBill.add(sharedEmployeeBillingData.getProject());

                    sharedBill.add(sharedEmployeeBillingData.getCurrent());
                    sharedBill.add(sharedEmployeeBillingData.getCurrentPlusOneMonth());
                    sharedBill.add(sharedEmployeeBillingData.getCurrentPlusTwoMonth());
                    sharedBill.add(sharedEmployeeBillingData.getEmployeeStatus());
                    if (sharedEmployeeBillingData.getLastWorkingDate() != null) {
                        sharedBill.add(formatter.format(sharedEmployeeBillingData.getLastWorkingDate()));
                    } else {
                        sharedBill.add(BLANK);
                    }
                    sharedBill.add(sharedEmployeeBillingData.getAllocationStatus());
                    if (sharedEmployeeBillingData.getBenchStartDate() != null) {
                        sharedBill.add(formatter.format(sharedEmployeeBillingData.getBenchStartDate()));
                    } else {
                        sharedBill.add(BLANK);
                    }
                    sharedBill.add(sharedEmployeeBillingData.getShadowResourceType());
                    if (sharedEmployeeBillingData.getShadowStartDate() != null) {
                        sharedBill.add(formatter.format(sharedEmployeeBillingData.getShadowStartDate()));
                    } else {
                        sharedBill.add(BLANK);
                    }

                    if (sharedEmployeeBillingData.getBillingStartDate() != null) {
                        sharedBill.add(formatter.format(sharedEmployeeBillingData.getBillingStartDate()));
                    } else {
                        sharedBill.add(BLANK);
                    }
                    if (sharedEmployeeBillingData.getBillingEndDate() != null) {
                        sharedBill.add(formatter.format(sharedEmployeeBillingData.getBillingEndDate()));
                    } else {
                        sharedBill.add(BLANK);
                    }
                    sharedBill.add(sharedEmployeeBillingData.getPercentUtilization());
                    sharedBill.add(sharedEmployeeBillingData.getBillableHours());
                    sharedBill.add(sharedEmployeeBillingData.getEmployeeRole());
                    sharedBill.add(sharedEmployeeBillingData.getVertical());
                    sharedBill.add(sharedEmployeeBillingData.getRemarks());
                    billingRows.add(sharedBill);
                }
                }
            }
        currentIndex = writeTableHeader(tableHeaderManagerBilling, workbook, billingSheet, currentIndex);
        writeTableRows(billingRows, billingSheet, currentIndex);
        return workbook;
    }

    private static int writeTableHeader(String[] tableHeaders, Workbook workbook, Sheet sheet, int currentIndex) {
        Row headerRow = sheet.createRow(currentIndex);
        for (int headerCount = 0; headerCount < tableHeaders.length; headerCount++) {
            Cell orderValueCell = headerRow.createCell(headerCount);
            orderValueCell.setCellStyle(getTableHeaderStyle(workbook));
            orderValueCell.setCellValue(tableHeaders[headerCount]);
        }
        currentIndex++;
        return currentIndex;
    }

    private static CellStyle getTableHeaderStyle(Workbook workbook) {
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 10);
        font.setFontName("Arial");
        font.setBold(true);
        font.setItalic(false);
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.LEFT);
        return style;
    }

    private static int writeTableRows(List<List<String>> tableData, Sheet sheet, int currentIndex) {
        for (List<String> tableEntry : tableData) {
            int dataCount = tableEntry.size();
            Row tableValue = sheet.createRow(currentIndex);
            for (int count = 0; count < dataCount; count++) {
                tableValue.createCell(count).setCellValue(tableEntry.get(count));
            }
            currentIndex++;
        }
        return currentIndex;
    }
}
