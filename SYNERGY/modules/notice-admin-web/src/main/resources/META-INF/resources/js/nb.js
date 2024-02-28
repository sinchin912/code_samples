var holidays = new Array();
var roleIndex = 0;
var counter;
var managerEmail = $('#managerEmail').val();
var assigneeEmail = $('#assigneeEmail').val();
var lastWorkingDate = $('#lastWorkingDate').val();
var tentativeLWD = $('#tentativeLWD').val();
$(document).ready(function ()
{
   var leadRole = $('#isTeamlead').val();
   var coordinatorRole = $('#isCoordinator').val();
   var managerRole = $('#isManager').val();
   var itRole = $('#isIT').val();
   var adminRole = $('#isAdmin').val();
   var financeRole = $('#isFinance').val();
   var hrRole = $('#isHR').val();
   if (leadRole == 'true' || coordinatorRole == 'true' || managerRole == 'true')
   {
      $('#managerRole').show();
      $('#first-tab').addClass("active");
      $('#first').addClass("show active");

   }
   if (itRole == 'true')
   {
      $('#itRole').show();
      if (leadRole == 'false' && coordinatorRole == 'false' && managerRole == 'false')
      {
         $('#second-tab').addClass("active");
         $('#second').addClass("show active");
      }
   }
   if (adminRole == 'true')
   {
      $('#adminRole').show();
      if (leadRole == 'false' && coordinatorRole == 'false' && managerRole == 'false' && itRole == 'false')
      {
         $('#third-tab').addClass("active");
         $('#third').addClass("show active");
      }
   }
   if (financeRole == 'true')
   {
      roleIndex = 4;
      var recoveryFormDataSize = $('#recoveryFormDataSize').val();
      counter = $("#recoveryBody tr").length - recoveryFormDataSize;
      $('#recoveryExistingRows').val(counter);
      var reimbursementFormDataSize = $('#reimbursementFormDataSize').val();
      reimbursementExistingRows = $("#reimbursementBody tr").length - reimbursementFormDataSize;
      $('#reimbursementExistingRows').val(reimbursementExistingRows);
      $('#financeRole').show();
      if (leadRole == 'false' && coordinatorRole == 'false' && managerRole == 'false' && itRole == 'false' && adminRole == 'false')
      {
         $('#fourth-tab').addClass("active");
         $('#fourth').addClass("show active");
      }
   }
   if (hrRole == 'true')
   {
      roleIndex = 5;
      $('#hrRole').show();
      if (leadRole == 'false' && coordinatorRole == 'false' && managerRole == 'false' && itRole == 'false' && adminRole == 'false' && financeRole == 'false')
      {
         $('#fifth-tab').addClass("active");
         $('#fifth').addClass("show active");
      }
   }


   function setHolidays(date)
   {
      var string = $.datepicker.formatDate('yy-mm-dd', date);
      var filterDate = new Date(string);
      var day = filterDate.getDay();
      var isHoliday = ($.inArray(string, holidays) != -1);
      return [day != 0 && day != 6 && !isHoliday]
   }

   $("#terminationStartDate").datepicker(
   {
      maxDate: 0,
      dateFormat: "yy-mm-dd",
      firstDay: 0,
   });
   $("#abscondDate,#absFinalLwd").datepicker(
   {
      maxDate: 0,
      dateFormat: "yy-mm-dd",
      beforeShowDay: setHolidays
   });
   $('#itExitFormsTable,#adminExitFormsTable,#financeExitFormsTable,#hrExitFormsTable,#managerResignationsTable').DataTable(
   {
      dom: 'Bfrtip',
      buttons: ['excel'],
      "pageLength": 10,
      "lengthMenu": [
         [5, 10, 20, -1],
         [5, 10, 20, "All"]
      ],
      "aaSorting": [
         [0, 'desc']
      ],
      "columnDefs": [
      {
         "targets": "_all",
         "orderable": false
      }]
   });
   $('#hrResignationsTable').DataTable(
   {
      dom: 'Bfrtip',
      buttons: ['excel'],
      "pageLength": 15,
      "lengthMenu": [
         [5, 10, 20, -1],
         [5, 10, 20, "All"]
      ],
      "aaSorting": [
         [1, 'desc']
      ],
      "columnDefs": [
      {
         "targets": "fn_no-sort",
         "orderable": false
      }]
   });
   //   resignation.jsp
   $("#finalLwd").datepicker(
   {
      minDate: 0,
      dateFormat: "yy-mm-dd",
      beforeShowDay: setHolidays
   });

   $("#abscondDate").datepicker(
   {
      maxDate: 0,
      dateFormat: "yy-mm-dd",
      firstDay: 0,
      beforeShowDay: setHolidays
   });
});
//    VIEW.JSP
function viewForm(slide)
{
   $('#managerDiv').hide();
   $('#itDiv').hide();
   $('#adminDiv').hide();
   $('#financeDiv').hide();
   $('#hrDiv').hide();
   $('#exitFormStages').carousel(slide);
}


function cancelView()
{
   $('#managerDiv').show();
   $('#itDiv').show();
   $('#adminDiv').show();
   $('#financeDiv').show();
   $('#hrDiv').show();
}
$('#keyToRetention').change(function ()
{
   if ($('#keyToRetention').val() == 'Yes')
   {
      $('#retainEmpId').show();
      $('#NonRetainEmpLabelId').hide();
      $('#NonRetainEmpId').hide();
      $('#reasonNonRetention').prop("required", false);
   }
   else if ($('#keyToRetention').val() == 'No')
   {
      $('#NonRetainEmpLabelId').show();
      $('#NonRetainEmpId').show();
      $('#retainEmpId').hide();
      $('#reasonNonRetention').prop("required", true);
   }
   else
   {
      $('#NonRetainEmpLabelId').hide();
      $('#NonRetainEmpId').hide();
      $('#retainEmpId').hide();
      $('#reasonNonRetention').prop("required", false);
   }
});


function changeSeparationType()
{
   var statusMapJSON = JSON.parse($('#statusMapJSON').val());
   if ($('#absTerSeparationType').val() == statusMapJSON[4]._exitStateDescription)
   {
      $("#actionPerformed").val(statusMapJSON[4]._exitStateDescription);
      $('#terminationTypeDiv').hide();
      $('#abscondLebelDiv').show();
      $('#terminationNoticePeriodDiv').hide();
      $('#emailIdField').hide();
      $('#terminationType').prop("required", false);
      $('#terminationStartDate').prop("required", false);
      $('#terminationNoticePeriod').prop("required", false);
      $('#lwd').prop("required", false);
      $('#terminationType').val('');
      $('#terminationStartDate').val('');
      $('#terminationNoticePeriod').val('');
      $('#lwd').val('');

      $('#absFinalLwd').prop("required", true);
   }
   else if ($('#absTerSeparationType').val() == statusMapJSON[5]._exitStateDescription)
   {
      $("#actionPerformed").val(statusMapJSON[5]._exitStateDescription);
      $('#terminationTypeDiv').show();
      $('#abscondLebelDiv').hide();
      $('#terminationNoticePeriodDiv').show();
      $('#emailIdField').show();
      $('#terminationType').prop("required", true);
      $('#terminationStartDate').prop("required", true);
      $('#terminationNoticePeriod').prop("required", true);
      $('#lwd').prop("required", true);
      $('#absFinalLwd').prop("required", false);
      $('#absFinalLwd').val('');
   }
   else
   {
      $("#actionPerformed").val('');
      $('#terminationTypeDiv').hide();
      $('#abscondLebelDiv').hide();
      $('#terminationNoticePeriodDiv').hide();
      $('#emailIdField').hide();
      $('#terminationType').prop("required", false);
      $('#terminationStartDate').prop("required", false);
      $('#terminationNoticePeriod').prop("required", false);
      $('#terminationType').val('');
      $('#terminationStartDate').val('');
      $('#terminationNoticePeriod').val('');
      $('#lwd').val('');
      $('#lwd').prop("required", false);
      $('#absFinalLwd').prop("required", false);
      $('#absFinalLwd').val('');
   }
}

function setLastWorkingDay()
{
   var noticePeriod = $("#noticePeriod").val() == 0 ? $("#noticePeriod").val() : $("#noticePeriod").val()-1;
   var date = new Date();
   var lwdDate = new Date(date.getTime() + (noticePeriod * 24 * 60 * 60 * 1000));

   var day = lwdDate.getDate();
   var month = lwdDate.getMonth() + 1;
   var year = lwdDate.getFullYear();
   if (month < 10)
   {
      month = "0" + month;
   }
   if (day < 10)
   {
      day = "0" + day;
   }
   $("#finalLwdByHr").val(year + "-" + month + "-" + day);
}

function setNoticePeriodDate()
{
   var terminationStartDate = $("#terminationStartDate").val();
   var lwd = $("#lwd").val();
   if (lwd != '' && terminationStartDate != '')
   {

      var lwdDate = new Date(lwd);
      var terminationStartDateDate = new Date(terminationStartDate);
      if (lwdDate < terminationStartDateDate)
      {
         alertModal("Exit Admin:Lwd should be greater");
         $("#terminationStartDate").val('');
         $("#lwd").val('');
         $("#terminationNoticePeriod").val('');
         return false;

      }
      var dates = (lwdDate - terminationStartDateDate) / 86400000;
      $("#terminationNoticePeriod").val(dates + 1);
   }
}

function confirmAbscondTerminateFormSubmission(flag)
{
     var statusMapJSON = JSON.parse($('#statusMapJSON').val());
   var actionPerformed = flag == 1 ? $("#managerActionPerformed").val() : $("#actionPerformed").val();
   if (actionPerformed == statusMapJSON[4]._exitStateDescription)
   {
      event.preventDefault();
      if (flag == 1)
      {
         confirmModal('Exit Admin:Are you sure you want to abscond the selected employee ?', 'managerAbscondTerminateForm', null)
      }
      else
      {
         confirmModal('Exit Admin:Are you sure you want to abscond the selected employee ?', 'abscondTerminateForm', null)
      }
   }
   else if (actionPerformed == statusMapJSON[5]._exitStateDescription)
   {
      event.preventDefault();
      confirmModal('Exit Admin:Are you sure you want to terminate the selected employee ?', 'abscondTerminateForm', null)
   }
}
function downloadUserManual(val) {
   var url = $('#downloadUserManualResourceUrl').val();
   var portal = $('#portletNamespace').val();
   var roleValKey = $('#portletNamespace').val() + 'roleAction';

   var postData = {
      [roleValKey]: val
   };
   AUI().use('aui-io-request', function (A) {
      A.io.request(url, {
         method: 'post',
         data: postData,
         on: {
            success: function () {
               var headerData = JSON.parse(this.get('responseData'));
               const blob = convertBase64toBlob(headerData.data, 'application/pdf');
               let tab = window.open();
               tab.location.href = URL.createObjectURL(blob);
            }
         }
      });
   });

}
function submitDownloadAction()
{
   if ($("#selectedYear").val() === null || $("#selectedYear").val() == '')
   {
      alertModal("Exit Admin:Please select an year");
      return false;
   }
}



function doSearchUser(obj, flag)
{
     var statusMapJSON = JSON.parse($('#statusMapJSON').val());
   var value;
   if (($("#searchEmpEcode").val() == '' && flag == 1) || ($("#searchEcode").val() == '' && flag == 2))
   {
      alertModal("Exit Admin:Please provide employee ecode to search");
      return false;
   }
   else
   {
      flag == 2 ? value = $("#searchEcode").val() : value = $("#searchEmpEcode").val();
      var url = $('#employeeEcodeSearchResourceUrl').val();
      $(obj).prop('disabled', 'disabled');
      $('#employeeDetailCard').hide();
      $('#employeeDataCard').hide();
      var dateSearchKey = $('#portletNamespace').val() + 'ecode';
      var flagKey = $('#portletNamespace').val() + 'flag';
      var postData = {
         [dateSearchKey]: value,
         [flagKey]: flag,
      };
      AUI().use('aui-io-request', function (A)
      {
         A.io.request(url,
         {
            method: 'post',
            data: postData,
            on:
            {
               success: function ()
               {
                  var employeeData = JSON.parse(this.get('responseData'));
                  if (null !=employeeData  && null != employeeData.data)
                  {
                     if (employeeData.data.status == statusMapJSON[4]._exitStateDescription)
                     {

                        alertModal('Exit Admin:Employee is already Absconded ');
                        $('#searchEcode').val('');
                        $(obj).prop('disabled', false);
                     }
                     else if (employeeData.data.status == "ImmediateTerminate")
                     {
                        alertModal('Exit Admin:Employee is already terminate with zero notice period ');
                        $('#searchEcode').val('');
                        $(obj).prop('disabled', false);
                     }
                     else
                     {
                        if (employeeData.data.status == statusMapJSON[5]._exitStateDescription)
                        {
                           alertModal('Exit Admin:Employee is already Terminated');
                           $('option#Terminated').hide();
                        }
                        if (flag == 1)
                        {
                           $('#absEcode').val(employeeData.data.employeeCode);
                           $('#absName').val(employeeData.data.employeeName);
                           $('#absManagerName').val(employeeData.data.managerName);
                           $('#absProjectName').val(employeeData.data.account);
                           $('#absLocation').val(employeeData.data.location);
                           $('#absBand').val(employeeData.data.employeeBand);
                           $('#absDoj').val(employeeData.data.employeeDoj);
                        }
                        if (flag == 2)
                        {
                           $('#ecode').val(employeeData.data.employeeCode);
                           $('#name').val(employeeData.data.employeeName);
                           $('#managerName').val(employeeData.data.managerName);
                           $('#projectName').val(employeeData.data.account);
                           $('#location').val(employeeData.data.location);
                           $('#band').val(employeeData.data.employeeBand);
                           $('#doj').val(employeeData.data.employeeDoj);
                           $('#hrComments').val(employeeData.data.hrComment);
                        }
                        flag == 2 ? $("#employeeDetailCard").show() : $('#employeeDataCard').show();
                        $(obj).prop('disabled', false);
                     }

                  }
                  else
                  {
                     alertModal('Exit Admin:No employee found with given search values.');
                     $('#searchEcode').val('');
                     $('#searchEmpEcode').val('');
                     $('#employeeDetailCard').hide();
                     $('#employeeDataCard').hide();
                     $(obj).prop('disabled', false);
                  }
               }
            }
         });
      });
   }
}

function validEmail()
{
   var email = $("#emailId").val();
   var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
   if (!regex.test(email) && email != '' && email != null)
   {
      alertModal("Exit Admin:Personal email address is not valid.");
      $("#emailId").val("");
      return false;
   }
   else if (email == '' && email == null)
   {
      return false;
   }
   else if (email.includes("@trantorinc.com") || email.includes("@chd.trantorinc.com"))
   {
      alertModal("Exit Admin:personal email not should be trantor email");
      $("#emailId").val("");
      return false;
   }
   else
   {
      return true;
   }
}

//resignation.jsp
$('#managerDisclaimer').click(function ()
{
   if ($(this).is(':checked'))
   {
      $("#lwdButton").attr("disabled", false);
   }
   else
   {
      $("#lwdButton").attr("disabled", "disabled");
   }
});

function retainEmployeeForm()
{
   event.preventDefault();
   confirmModal('Exit Admin:Are you sure you want to retain the  employee ?', 'retainEmployeeForm', null);
}

function confirmHrApproveForm()
{
   event.preventDefault();
   confirmModal('Exit Admin:Are you sure you want to update the details ?', 'hrApproveForm', null);
}

function confirmManagerApproveForm()
{
   event.preventDefault();
   confirmModal(" Exit Admin:Are you sure you want to submit the details?", 'managerApproveForm', null);
}

function confirmItAssetsForm()
{
   event.preventDefault();
   confirmModal('Exit Admin:This list will be sent to the employee, Are you sure you want to submit?', 'itAssetsListForm', null);
}

function confirmFinalLwd()
{
   var finalLwd = $("#finalLwd").val();
   if (lastWorkingDate === finalLwd)
   {
      alertModal("Exit Admin : You are selecting already selected LWD ");
      return false;
   }
   else
   {
       event.preventDefault();
      confirmModal('Exit Admin:Are you sure you want to update the details?', 'updateFinalLwd', null);
   }

}

function approveRejectValidation(action)
{
   $("#withdrawActionPerform").val(action);
}
function submitAssignee()
{
   event.preventDefault();
   confirmModal('Exit Admin:Are you sure you want to assign this form ?', 'assigneeSubmissionForm', null);
}
function checkAssigneeRole()
{
   var mEmail = $('#assigneeEmailReg').val();
   if (mEmail.toLowerCase() == managerEmail.toLowerCase())
   {
      alertModal("Exit Admin:Manager and assignee email can't be same");
      return false;
   }
   else if (mEmail.toLowerCase() == assigneeEmail.toLowerCase())
   {
      alertModal("Exit Admin:Form is already assigned to mentioned email id.");
      return false;
   }
   else if (isEmail(mEmail))
   {
      var mEmailKey = $('#portletNamespace').val() + 'mEmail';
      var postData = {
         [mEmailKey]: mEmail,
      };
      AUI().use('aui-io-request', function (A)
      {
         A.io.request($('#getValidateRoleResourceUrl').val(),
         {
            method: 'post',
            data: mEmailKey,
            on:
            {
               success: function ()
               {
                  var employeeData = JSON.parse(this.get('responseData'));
                  if (employeeData.data != "")
                  {
                     $("#assigneeButton").attr("disabled", false);
                  }
                  else
                  {
                     alertModal("Exit Admin:Not a valid Lead/manager Email Id");
                  }
               }
            }
         });
      });
   }
}

function isEmail(email)
{
   var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
   if (!regex.test(email))
   {
      $(".email-has-error ").text("Not a valid email address.");
      $("#assigneeEmail").val("");
      return false;
   }
   else if (!(email.includes("@trantorinc.com") || email.includes("@chd.trantorinc.com")))
   {
      $(".email-has-error").text("Not a valid Trantor email address.");
      $("#assigneeEmail").val("");
      return false;
   }
   else
   {
      $(".email-has-error").text("");
      return true;
   }
}

function confirmHrWithdrawalActionForm()
{
   var withdrawActionPerform = $("#withdrawActionPerform").val();
   if (withdrawActionPerform == '1' || withdrawActionPerform == '2')
   {
      event.preventDefault();
      confirmModal('Exit Admin:Are you sure you want to retain the selected employee ?', 'hrWithdrawalActionForm', null);
   }
   else
   {
          event.preventDefault();
         confirmModal('Exit Admin:Are you sure you want to reject the Resignation Withdrawal request of selected employee ?', 'hrWithdrawalActionForm', null);

   }
}

$('#replacementPlan').change(function ()
{
   if ($('#replacementPlan').val() == 'Internal Allocation')
   {
      $('#replacementFormId').hide();
      $('#empLabel').show();
      $('#empList').show();
      $('#emailId').prop("required", true);
   }
   else
   {
      $('#empLabel').hide();
      $('#empList').hide();
      $('#replacementFormId').show();
      $('#emailId').prop("required", false);
   }
});
$('#keyToRetention').change(function ()
{

   if ($('#keyToRetention').val() == 'Yes')
   {
      $('#retainEmpId').show();
      $('#NonRetainEmpLabelId').hide();
      $('#NonRetainEmpId').hide();
      $('#reasonNonRetention').prop("required", false);
   }
   else if ($('#keyToRetention').val() == 'No')
   {
      $('#NonRetainEmpLabelId').show();
      $('#NonRetainEmpId').show();
      $('#retainEmpId').hide();
      $('#reasonNonRetention').prop("required", true);
   }
   else
   {
      $('#NonRetainEmpLabelId').hide();
      $('#NonRetainEmpId').hide();
      $('#retainEmpId').hide();
      $('#reasonNonRetention').prop("required", false);
   }
});
//clearance
$('.fn_enableDisable').change(function ()
{
   if ($(this).val() == '1')
   {
      $(this).parent().next().children().first().prop("readonly", false);
   }
   else
   {
      $(this).parent().next().children().first().val('');
      $(this).parent().next().children().first().prop("readonly", "readonly");
   }
});
$('.fn_isReadonly').change(function ()
{
           if($(this).prop("checked") == true){
                 $(this).parent().next().children().first().prop("required", false);
            }
            else if($(this).prop("checked") == false){
                $(this).parent().next().children().first().val('');
                $(this).parent().next().children().first().prop("required", "required");
            }
});
$("#saveForm").click(function (ev)
{
   var roleType = $('#roleType').val();
   var isSave = true;
   if (roleType === 'Trantor_Admin')
   {
      var totalRows = $("#adminBodyRows tr").length;
      $('#adminTotalRows').val(totalRows);
      var recovery = true;

      $("#adminBodyRows tr").each(function ()
      {
         var currentRow = $(this);
         var col1_value = currentRow.find('td').eq(2).find('select').eq(0).val();
         var col2_value = currentRow.find('td').eq(3).find('input').eq(0).val();
         if (col1_value == 1 && col2_value < 1)
         {
            alertModal("Exit Admin:Recovery Amount value should be 1 or above");
            isSave = false;
            return false;
         }
      });
   }
   else if (roleType === 'Trantor_IT')
   {
      var totalRows = $("#itTableBodyRows tr").length;
      $('#itTotalRows').val(totalRows);
      var recovery = true;

      $("#itTableBodyRows tr").each(function ()
      {
         var currentRow = $(this);
         var col1_value = currentRow.find('td').eq(2).find('select').eq(0).val();
         var col2_value = currentRow.find('td').eq(3).find('input').eq(0).val();
         if (col1_value == 1 && col2_value < 1)
         {
            alertModal("Exit Admin:Recovery Amount value should be 1 or above");
            isSave = false;
            return false;
         }
      });
   }
   else if (roleType === 'Trantor_Finance')
   {
      var totalRows = $("#recoveryBody tr").length;
      $('#totalRows').val(totalRows);
      var recoveryReimbursement = true;

      $("#reimbursementBody tr").each(function ()
      {
         var currentRow = $(this);
         var col1_value = currentRow.find('td').eq(2).find('select').eq(0).val();
         var col2_value = currentRow.find('td').eq(3).find('input').eq(0).val();
         if (col1_value == 1 && col2_value < 1)
         {
            alertModal("Exit Admin:Reimbursement Amount value should be 1 or above");
            isSave = false;
            return false;
         }
      });
      $("#recoveryBody tr").each(function (index)
      {
         var currentRow = $(this);
         var col1_value = currentRow.find('td').eq(2).find('select').eq(0).val();
         var col2_value = currentRow.find('td').eq(3).find('input').eq(0).val();
         if (col1_value == 1 && col2_value < 1)
         {
            alertModal("Exit Admin:Recovery Amount value should be 1 or above");
            isSave = false;
            return false;
         }
      });
      var reimbursementTotalRows = $("#reimbursementBody tr").length;
      $('#reimbursementTotalRows').val(reimbursementTotalRows);
   }
   else if (roleType === 'Trantor_HR')
   {
      var totalRows = $("#hrTableBodyRows tr").length;
      $('#hrTotalRows').val(totalRows);
      var recovery = true;

      $("#hrTableBodyRows tr").each(function ()
      {
         var currentRow = $(this);
         var col1_value = currentRow.find('td').eq(2).find('select').eq(0).val();
         var col2_value = currentRow.find('td').eq(3).find('input').eq(0).val();
         if (col1_value == 1 && col2_value < 1)
         {
            alertModal("Exit Admin:Recovery Amount value should be 1 or above");
            isSave = false;
            return false;
         }
      });
   }
   if (isSave)
   {
      var form = $("#resignForm");
      var url = $('#saveClearanceFormResourceURL').val();
      $.ajax(
      {
         type: "POST",
         url: url,
         data: form.serialize(),
         success: function (data)
         {

            alertModal('Exit Admin:Form saved Successfully');
         },
         error: function (data)
         {

            // Some error in ajax call
            alertalertModal('Exit Admin:some Error');
         }
      });
   }
});

function confirmSubmission()
{
   var roleType = $('#roleType').val();

   if (roleType === 'Trantor_Admin')
   {
      var totalRows = $("#adminBodyRows tr").length;
      $('#adminTotalRows').val(totalRows);
      var recovery = true;

      $("#adminBodyRows tr").each(function ()
      {
         var currentRow = $(this);
         var col1_value = currentRow.find('td').eq(2).find('select').eq(0).val();
         var col2_value = currentRow.find('td').eq(3).find('input').eq(0).val();
         if (col1_value == 1 && col2_value < 1)
         {
            alertModal("Exit Admin:Recovery Amount value should be 1 or above");
            recovery = false;
            return false;
         }
      });
      if (recovery == false)
      {
         alertModal("Exit Admin:Recovery Amount value should be 1 or above");
         return false;
      }
      if ($('#stationaryRecoveryAmtStatus').val() == '1' && $('#stationaryRecoveryAmt').val() < 1)
      {
         alertModal("Exit Admin:Recovery Amount value should be 1 or above");
         return false;
      }
      else if ($('#keysRecoveryAmtStatus').val() == '1' && $('#keysRecoveryAmt').val() < 1)
      {
         alertModal("Exit Admin:Recovery Amount value should be 1 or above");
         return false;
      }
      else if ($('#libraryRecoveryAmtStatus').val() == '1' && $('#libraryRecoveryAmt').val() < 1)
      {
         alertModal("Exit Admin:Recovery Amount value should be 1 or above");
         return false;
      }
      else if ($('#sportsRecoveryAmtStatus').val() == '1' && $('#sportsRecoveryAmt').val() < 1)
      {
         alertModal("Exit Admin:Recovery Amount value should be 1 or above");
         return false;
      }
      else if ($('#infrastructureIssuedAmtStatus').val() == '1' && $('#infrastructureIssuedAmt').val() < 1)
      {
         alertModal("Exit Admin:Recovery Amount value should be 1 or above");
         return false;
      }
      else if ($('#lunchDeductionAmtStatus').val() == '1' && $('#lunchDeductionAmt').val() < 1)
      {
         alertModal("Exit Admin:Recovery Amount value should be 1 or above");
         return false;
      }
      else if ($('#accessCardRecoveryAmtStatus').val() == '1' && $('#accessCardRecoveryAmt').val() < 1)
      {
         alertModal("Exit Admin:Recovery Amount value should be 1 or above");
         return false;
      }
      else if ($('#identityCardStatus').val() == '1' && $('#identityCardAmt').val() < 1)
      {
         alertModal("Exit Admin:Recovery Amount value should be 1 or above");
         return false;
      }
      else if ($('#otherRecoveryAmtStatus').val() == '1' && $('#otherRecoveryAmtAdmin').val() < 1)
      {
         alertModal("Exit Admin:Recovery Amount value should be 1 or above");
         return false;
      }
      else
      {
         return true;
      }
   }

   else if (roleType === 'Trantor_IT')
   {
      var totalRows = $("#itTableBodyRows tr").length;
      $('#itTotalRows').val(totalRows);
      var recovery = true;

      $("#itTableBodyRows tr").each(function ()
      {
         var currentRow = $(this);
         var col1_value = currentRow.find('td').eq(2).find('select').eq(0).val();
         var col2_value = currentRow.find('td').eq(3).find('input').eq(0).val();
         if (col1_value == 1 && col2_value < 1)
         {
            alertModal("Exit Admin:Recovery Amount value should be 1 or above");
            recovery = false;
            return false;
         }
      });
      if (recovery == false)
      {
         alertModal("Exit Admin:Recovery Amount value should be 1 or above");
         return false;
      }
      if ($('#systemRecoveryAmtStatus').val() == '1' && $('#systemRecoveryAmt').val() < 1)
      {
         alertModal("Exit Admin:Recovery Amount value should be 1 or above");
         return false;
      }
      else if ($('#laptopRecoveryAmtStatus').val() == '1' && $('#laptopRecoveryAmt').val() < 1)
      {
         alertModal("Exit Admin:Recovery Amount value should be 1 or above");
         return false;
      }
      else if ($('#communicationRecoveryStatus').val() == '1' && $('#communicationRecoveryAmt').val() < 1)
      {
         alertModal("Exit Admin:Recovery Amount value should be 1 or above");
         return false;
      }
      else if ($('#headphoneRecoveryAmtStatus').val() == '1' && $('#headphoneRecoveryAmt').val() < 1)
      {
         alertModal("Exit Admin:Recovery Amount value should be 1 or above");
         return false;
      }
      else if ($('#laptopBagRecoveryAmtStatus').val() == '1' && $('#laptopBagRecoveryAmt').val() < 1)
      {
         alertModal("Exit Admin:Recovery Amount value should be 1 or above");
         return false;
      }
      else if ($('#chargerRecoveryAmtStatus').val() == '1' && $('#chargerAmt').val() < 1)
      {
         alertModal("Exit Admin:Recovery Amount value should be 1 or above");
         return false;
      }
      else if ($('#monitorRecoveryAmtStatus').val() == '1' && $('#monitorAmt').val() < 1)
      {
         alertModal("Exit Admin:Recovery Amount value should be 1 or above");
         return false;
      }
      else if ($('#mouseRecoveryAmtStatus').val() == '1' && $('#mouseRecoveryAmt').val() < 1)
      {
         alertModal("Exit Admin:Recovery Amount value should be 1 or above");
         return false;
      }
      else if ($('#otherRecoveryAmtStatusIt').val() == '1' && $('#otherRecoveryAmt').val() < 1)
      {
         alertModal("Exit Admin:Recovery Amount value should be 1 or above");
         return false;
      }
      else
      {
         return true;
      }
   }
   else if (roleType === 'Trantor_Finance')
   {
      var totalRows = $("#recoveryBody tr").length;
      $('#totalRows').val(totalRows);
      var recoveryReimbursement = true;

      $("#reimbursementBody tr").each(function ()
      {
         var currentRow = $(this);
         var col1_value = currentRow.find('td').eq(2).find('select').eq(0).val();
         var col2_value = currentRow.find('td').eq(3).find('input').eq(0).val();
         if (col1_value == 1 && col2_value < 1)
         {
            alertModal("Exit Admin:Reimbursement Amount value should be 1 or above");
            recoveryReimbursement = false;
            return false;
         }
      });
      $("#recoveryBody tr").each(function (index)
      {
         var currentRow = $(this);
         var col1_value = currentRow.find('td').eq(2).find('select').eq(0).val();
         var col2_value = currentRow.find('td').eq(3).find('input').eq(0).val();
         if (col1_value == 1 && col2_value < 1)
         {
            alertModal("Exit Admin:Recovery Amount value should be 1 or above");
            recoveryReimbursement = false;
            return false;
         }
      });
      var reimbursementTotalRows = $("#reimbursementBody tr").length;
      $('#reimbursementTotalRows').val(reimbursementTotalRows);
      if (recoveryReimbursement == false)
      {
         return false;
      }
      if ($('#foodReimbursementStatus').val() == '1' && $('#foodReimbursementAmt').val() < 1)
      {
         alertModal("Exit Admin:Recovery Amount value should be 1 or above");
         return false;
      }
      else if ($('#travelRecoveryStatus').val() == '1' && $('#travelRecoveryAmt').val() < 1)
      {
         alertModal("Exit Admin:Recovery Amount value should be 1 or above");
         return false;
      }
      else if ($('#hotelRecoveryStatus').val() == '1' && $('#hotelRecoveryAmt').val() < 1)
      {
         alertModal("Exit Admin:Recovery Amount value should be 1 or above");
         return false;
      }
      else if ($('#internationalRecoveryStatus').val() == '1' && $('#internationalRecoveryAmt').val() < 1)
      {
         alertModal("Exit Admin:Recovery Amount value should be 1 or above");
         return false;
      }
      else if ($('#educationRecoveryStatus').val() == '1' && $('#educationRecoveryAmt').val() < 1)
      {
         alertModal("Exit Admin:Recovery Amount value should be 1 or above");
         return false;
      }
      else if ($('#joiningBonusRecoveryStatus').val() == '1' && $('#joiningBonusRecoveryAmt').val() < 1)
      {
         alertModal("Exit Admin:Recovery Amount value should be 1 or above");
         return false;
      }
      else if ($('#noticePeriodRecoveryStatusFinance').val() == '1' && $('#noticePeriodRecoveryAmtFinance').val() < 1)
      {
         alertModal("Exit Admin:Recovery Amount value should be 1 or above");
         return false;
      }
      else
      {
         return true;
      }
   }
   else if (roleType === 'Trantor_HR')
   {
      var totalRows = $("#hrTableBodyRows tr").length;
      $('#hrTotalRows').val(totalRows);
      var recovery = true;

      $("#hrTableBodyRows tr").each(function ()
      {
         var currentRow = $(this);
         var col1_value = currentRow.find('td').eq(2).find('select').eq(0).val();
         var col2_value = currentRow.find('td').eq(3).find('input').eq(0).val();
         if (col1_value == 1 && col2_value < 1)
         {
            alertModal("Exit Admin:Recovery Amount value should be 1 or above");
            recovery = false;
            return false;
         }
      });
      if (recovery == false)
      {
         alertModal("Exit Admin:Recovery Amount value should be 1 or above");
         return false;
      }
      if ($('#trainingAgreementStatus').val() == '1' && $('#trainingAgreementAmt').val() < 1)
      {
         alertModal("Exit Admin:Recovery Amount value should be 1 or above");
         return false;
      }
      else if ($('#recoverableBonusStatus').val() == '1' && $('#recoverableBonusAmt').val() < 1)
      {
         alertModal("Exit Admin:Recovery Amount value should be 1 or above");
         return false;
      }
      else if ($('#noticePeriodRecoveryStatus').val() == '1' && $('#noticePeriodRecoveryAmt').val() < 1)
      {
         alertModal("Exit Admin:Recovery Amount value should be 1 or above");
         return false;
      }
      else if ($('#otherStatus').val() == '1' && $('#otherAmt').val() < 1)
      {
         alertModal("Exit Admin:Recovery Amount value should be 1 or above");
         return false;
      }
      else
      {
         return true;
      }
   }
}

function roundOfTwoDecimal(obj)
{
   var amount = obj.value;
   if (amount.trim() != "")
   {
      obj.value = parseFloat(amount).toFixed(2);
   }
   else
   {
      obj.value = "";
   }
}

function roundOfNoDecimal(obj)
{
   var amount = obj.value;
   if (amount.trim() != "")
   {
      obj.value = parseFloat(amount).toFixed(0);
   }
   else
   {
      obj.value = "";
   }
}

function reopenForm()
{
  event.preventDefault();
   confirmModal('Exit Admin:Are you sure you want to reopen the form?', 'reopenForm', null);
}

function confirmClearanceFormSubmission()
{
   event.preventDefault();
   confirmModal('Exit Admin:Are you sure you want to submit the details ?', 'resignForm', null);
}
$('#exitFormStages').on('slid.bs.carousel', function (ev)
{

   var carouselData = $(this).data('bs.carousel');
   currentIndex = $('.carousel-item.active').index() + 1;
   roleIndex == currentIndex ? $("#commonButtonDiv").show() : $("#commonButtonDiv").hide();

})
$('#ticketNo').change(function ()
{
   var roleType = $('#roleType').val();

   if ($('#ticketNo').val() == '1' && roleType == 'Trantor_Manager')
   {
      $('#ticketNoRemark').prop("required", true);
   }
   else
   {
      $('#ticketNoRemark').prop("required", false);
   }
});

$('#otherRecoveryAmtStatusIt').change(function ()
{

   if ($('#otherRecoveryAmtStatusIt').val() == '1' && roleType == 'Trantor_IT')
   {
      $('#otherItemIt').prop("required", true);
   }
   else
   {
      $('#otherItemIt').prop("required", false);
   }
});
$('#otherRecoveryAmtStatus').change(function ()
{
   if ($('#otherRecoveryAmtStatus').val() == '1' && roleType == 'Trantor_Admin')
   {
      $('#otherItemAdmin').prop("required", true);
   }
   else
   {
      $('#otherItemAdmin').prop("required", false);
   }
});
$('#otherStatus').change(function ()
{
   if ($('#otherStatus').val() == '1' && roleType == 'Trantor_HR')
   {
      $('#otherItemHr').prop("required", true);
   }
   else
   {
      $('#otherItemHr').prop("required", false);
   }
});

function removeRowAndResetIndex(obj)
{
   var namespace = $('#portletNamespace').val();
   var index = $(obj).attr('id');
   if (index.substring(0, 3) == "rec")
   {
      var val = parseInt(index.substring(3));
      var child = $(obj).closest('tr').nextAll();

      child.each(function ()
      {

         $(this).find('td').eq(0).find('button').eq(0).attr('id', 'rec' + val);
         $(this).find('td').eq(1).find('input').eq(0).attr('id', 'recoveryItem' + val);
         $(this).find('td').eq(1).find('input').eq(0).attr('name', namespace + "recoveryItem" + val);
         $(this).find('td').eq(2).find('select').eq(0).attr('id', 'recoveryStatus' + val);
         $(this).find('td').eq(2).find('select').eq(0).attr('name', namespace + "recoveryStatus" + val);
         $(this).find('td').eq(3).find('input').eq(0).attr('id', 'recoveryAmount' + val);
         $(this).find('td').eq(3).find('input').eq(0).attr('name', namespace + "recoveryAmount" + val);
         val++;
      });
      $(obj).closest("tr").remove();
   }
   else
   {
      var val = parseInt(index.substring(3));
      var child = $(obj).closest('tr').nextAll();

      child.each(function ()
      {
         $(this).find('td').eq(0).find('button').eq(0).attr('id', 'rem' + val);
         $(this).find('td').eq(1).find('input').eq(0).attr('id', 'reimbursementItem' + val);
         $(this).find('td').eq(1).find('input').eq(0).attr('name', namespace + "reimbursementItem" + val);
         $(this).find('td').eq(2).find('select').eq(0).attr('id', 'reimbursementStatus' + val);
         $(this).find('td').eq(2).find('select').eq(0).attr('name', namespace + "reimbursementStatus" + val);
         $(this).find('td').eq(3).find('input').eq(0).attr('id', 'reimbursementAmount' + val);
         $(this).find('td').eq(3).find('input').eq(0).attr('name', namespace + "reimbursementAmount" + val);
         val++;
      });
      $(obj).closest("tr").remove();
   }
}

function removeRowAndResetAdminIndex(obj)
{
   var namespace = $('#portletNamespace').val();
   var index = $(obj).attr('id');
   var val = parseInt(index.substring(5));
   var child = $(obj).closest('tr').nextAll();

   child.each(function ()
   {

      $(this).find('td').eq(0).find('button').eq(0).attr('id', 'admin' + val);
      $(this).find('td').eq(1).find('input').eq(0).attr('id', 'adminItem' + val);
      $(this).find('td').eq(1).find('input').eq(0).attr('name', namespace + "adminItem" + val);
      $(this).find('td').eq(2).find('select').eq(0).attr('id', 'adminRecoveryStatus' + val);
      $(this).find('td').eq(2).find('select').eq(0).attr('name', namespace + "adminRecoveryStatus" + val);
      $(this).find('td').eq(3).find('input').eq(0).attr('id', 'adminRecoveryAmount' + val);
      $(this).find('td').eq(3).find('input').eq(0).attr('name', namespace + "adminRecoveryAmount" + val);
      val++;
   });
   $(obj).closest("tr").remove();
}


function validateColumnsOnSelect(obj)
{
   var rowId = $(obj).attr('id');
   var value = $("#" + rowId).val();
   if (value == '1')
   {
      $(obj).parent().next().children().first().prop("readonly", false);
   }
   else
   {
      $(obj).parent().next().children().first().val('');
      $(obj).parent().next().children().first().prop("readonly", "readonly");
   }
}

function removeRowAndResetHrIndex(obj)
{
   var index = $(obj).attr('id');
   var val = parseInt(index.substring(2));
   var child = $(obj).closest('tr').nextAll();
   var namespace = $('#portletNamespace').val();
   child.each(function ()
   {

      $(this).find('td').eq(0).find('button').eq(0).attr('id', 'hr' + val);
      $(this).find('td').eq(1).find('input').eq(0).attr('id', 'hrItem' + val);
      $(this).find('td').eq(1).find('input').eq(0).attr('name', namespace + "hrItem" + val);
      $(this).find('td').eq(2).find('select').eq(0).attr('id', 'hrRecoveryStatus' + val);
      $(this).find('td').eq(2).find('select').eq(0).attr('name', namespace + "hrRecoveryStatus" + val);
      $(this).find('td').eq(3).find('input').eq(0).attr('id', 'hrRecoveryAmount' + val);
      $(this).find('td').eq(3).find('input').eq(0).attr('name', namespace + "hrRecoveryAmount" + val);
      val++;
   });
   $(obj).closest("tr").remove();
}

function removeRowAndResetItIndex(obj)
{
   var index = $(obj).attr('id');
   var val = parseInt(index.substring(2));
   var child = $(obj).closest('tr').nextAll();
   var namespace = $('#portletNamespace').val();
   child.each(function ()
   {

      $(this).find('td').eq(0).find('button').eq(0).attr('id', 'it' + val);
      $(this).find('td').eq(1).find('input').eq(0).attr('id', 'itItem' + val);
      $(this).find('td').eq(1).find('input').eq(0).attr('name', namespace + "itItem" + val);
      $(this).find('td').eq(2).find('select').eq(0).attr('id', 'itRecoveryStatus' + val);
      $(this).find('td').eq(2).find('select').eq(0).attr('name', namespace + "itRecoveryStatus" + val);
      $(this).find('td').eq(3).find('input').eq(0).attr('id', 'itRecoveryAmount' + val);
      $(this).find('td').eq(3).find('input').eq(0).attr('name', namespace + "itRecoveryAmount" + val);
      val++;
   });
   $(obj).closest("tr").remove();
}

function addItRows()
{
   var namespace = $('#portletNamespace').val();
   var rows = $("#itTableBodyRows tr").length;
   var cols = "<tr>";
   cols += '<td class="py-0 "><button type="button" id="it' + rows + '" onclick="removeRowAndResetItIndex(this)"  class="btn-danger"><i class="far fa-trash-alt" style="cursor: pointer"></i></button></td>';
   cols += '<td class="p-0"><input type="text"  class="form-control form-control-sm" id="itItem' + rows + '" onblur="$(this).val($(this).val().trim())" name="' + namespace + 'itItem' + rows + '" required  />  </td>';
   cols += '<td class="p-0"><select class="mdb-select md-form form-control form-control-sm "  id="itRecoveryStatus' + rows + '" onchange="return validateColumnsOnSelect(this);" name="' + namespace + 'itRecoveryStatus' + rows + '"  required  > <option value="" disabled selected>Choose your option</option> <option value="1">Yes</option> <option  value="2">No</option> <option value="3">NA</option> </select>  </td>';
   cols += '<td class="p-0"><input type="number" step="0.01" class="form-control form-control-sm ds_readonlyTextbox" onblur="roundOfTwoDecimal(this)" readonly id="itRecoveryAmount' + rows + '"  name="' + namespace + 'itRecoveryAmount' + rows + '"   /></td>';
   cols += "</tr>";
   $("#itTableBodyRows").append(cols);
}

function addHrRows()
{
   var namespace = $('#portletNamespace').val();
   var rows = $("#hrTableBodyRows tr").length;
   var cols = "<tr>";
   cols += '<td class="py-0 "><button type="button" id="hr' + rows + '" onclick="removeRowAndResetHrIndex(this)"  class="btn-danger"><i class="far fa-trash-alt" style="cursor: pointer"></i></button></td>';
   cols += '<td class="p-0"><input type="text"  class="form-control form-control-sm" id="hrItem' + rows + '" onblur="$(this).val($(this).val().trim())" name="' + namespace + 'hrItem' + rows + '" required  />  </td>';
   cols += '<td class="p-0"><select class="mdb-select md-form form-control form-control-sm "  id="hrRecoveryStatus' + rows + '" onchange="return validateColumnsOnSelect(this);" name="' + namespace + 'hrRecoveryStatus' + rows + '"  required  > <option value="" disabled selected>Choose your option</option> <option value="1">Yes</option> <option  value="2">No</option></select>  </td>';
   cols += '<td class="p-0"><input type="number" step="0.01" class="form-control form-control-sm ds_readonlyTextbox" onblur="roundOfTwoDecimal(this)" readonly id="hrRecoveryAmount' + rows + '"  name="' + namespace + 'hrRecoveryAmount' + rows + '"   /></td>';
   cols += "</tr>";
   $("#hrTableBodyRows").append(cols);
}

function addAdminRows()
{
   var namespace = $('#portletNamespace').val();
   var rows = $("#adminBodyRows tr").length;
   var cols = "<tr>";
   cols += '<td class="py-0 "><button type="button" id="admin' + rows + '" onclick="removeRowAndResetAdminIndex(this)"  class="btn-danger"><i class="far fa-trash-alt" style="cursor: pointer"></i></button></td>';
   cols += '<td class="p-0"><input type="text"  class="form-control form-control-sm" id="adminItem' + rows + '" onblur="$(this).val($(this).val().trim())" name="' + namespace + 'adminItem' + rows + '" required  />  </td>';
   cols += '<td class="p-0"><select class="mdb-select md-form form-control form-control-sm "  id="adminRecoveryStatus' + rows + '" onchange="return validateColumnsOnSelect(this);" name="' + namespace + 'adminRecoveryStatus' + rows + '"  required  > <option value="" disabled selected>Choose your option</option> <option value="1">Yes</option> <option  value="2">No</option> <option value="3">NA</option> </select>  </td>';
   cols += '<td class="p-0"><input type="number" step="0.01" class="form-control form-control-sm ds_readonlyTextbox" onblur="roundOfTwoDecimal(this)" readonly id="adminRecoveryAmount' + rows + '"  name="' + namespace + 'adminRecoveryAmount' + rows + '"   /></td>';
   cols += "</tr>";
   $("#adminBodyRows").append(cols);
}

function addNewRow()
{
   var namespace = $('#portletNamespace').val();
   var rows = $("#recoveryBody tr").length;
   var cols = "<tr>";
   cols += '<td class="py-0 "><button type="button" id="rec' + rows + '" onclick="removeRowAndResetIndex(this)"  class="btn-danger"><i class="far fa-trash-alt" style="cursor: pointer"></i></button></td>';
   cols += '<td class="p-0"><input type="text"  class="form-control form-control-sm" id="recoveryItem' + rows + '" onblur="$(this).val($(this).val().trim())" name="' + namespace + 'recoveryItem' + rows + '" required  />  </td>';
   cols += '<td class="p-0"><select class="mdb-select md-form form-control form-control-sm "  id="recoveryStatus' + rows + '" onchange="return validateColumnsOnSelect(this);" name="' + namespace + 'recoveryStatus' + rows + '"  required  > <option value="" disabled selected>Choose your option</option> <option value="1">Yes</option> <option  value="2">No</option> <option value="3">NA</option> </select>  </td>';
   cols += '<td class="p-0"><input type="number" step="0.01" class="form-control form-control-sm ds_readonlyTextbox" onblur="roundOfTwoDecimal(this)" readonly id="recoveryAmount' + rows + '"  name="' + namespace + 'recoveryAmount' + rows + '"   /></td>';
   cols += "</tr>";
   $("#recoveryBody").append(cols);
}

function addReimbursementRows()
{
   var namespace = $('#portletNamespace').val();
   var rows = $("#reimbursementBody tr").length;
   var cols = "<tr>";
   cols += '<td class="py-0 "><button type="button" id="rem' + rows + '" onclick="removeRowAndResetIndex(this)"  class="btn-danger"><i class="far fa-trash-alt" style="cursor: pointer"></i></button></td>';
   cols += '<td class="p-0"><input type="text"  class="form-control form-control-sm " id="reimbursementItem' + rows + '" onblur="$(this).val($(this).val().trim())" name="' + namespace + 'reimbursementItem' + rows + '" required   /></td>';
   cols += '<td class="p-0"><select class="mdb-select md-form form-control form-control-sm "  id="reimbursementStatus' + rows + '" onchange="return validateColumnsOnSelect(this);" name="' + namespace + 'reimbursementStatus' + rows + '" required > <option value="" disabled selected>Choose your option</option> <option value="1">Yes</option> <option  value="2">No</option> <option value="3">NA</option> </select>  </td>';
   cols += '<td class="p-0"><input type="number" step="0.01" class="form-control form-control-sm ds_readonlyTextbox" onblur="roundOfTwoDecimal(this)" readonly id="reimbursementAmount' + rows + '"  name="' + namespace + 'reimbursementAmount' + rows + '"  /></td>';
   cols += "</tr>";
   $("#reimbursementBody").append(cols);
}
//  function blockSymbols(evt)
//  {
//     evt = (evt) ? evt : window.event;
//     var charCode = (evt.which) ? evt.which : evt.keyCode;
//     if (charCode == 34 || charCode == 38 || charCode == 39 || charCode == 59 || charCode == 60 || charCode == 62)
//     {
//        return false;
//     }
//     return true;
//  }