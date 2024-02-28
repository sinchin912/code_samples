var proficiencyLevelVal;
$(document).ready(function () {

   var leadRole = $('#isTeamlead').val();
   var managerRole = $('#isManager').val();
   var hrRole = $('#isHR').val();
   var leadershipRole = $('#isLeader').val();
   var recruiterRole = $('#isRecruiter').val();
   if (leadRole == 'true') {
      $('#leadRole').show();
      $('#first-tab').addClass("active");
      $('#first').addClass("show active");

   }
   if (managerRole == 'true') {
      $('#managerRole').show();
      if (leadRole == 'false') {
         $('#second-tab').addClass("active");
         $('#second').addClass("show active");
      }
   }
   if (hrRole == 'true') {

      $('#hrRole').show();
      if (leadRole == 'false' && managerRole == 'false') {
         $('#third-tab').addClass("active");
         $('#third').addClass("show active");
      }
   }
   if (leadershipRole == 'true') {
      $('#leadershipRole').show();
      if (leadRole == 'false' && managerRole == 'false' && hrRole == 'false') {
         $('#fourth-tab').addClass("active");
         $('#fourth').addClass("show active");
      }
   }
   if (recruiterRole == 'true') {
      $('#recruiterRole').show();
      if (leadRole == 'false' && managerRole == 'false' && hrRole == 'false' && leadershipRole == 'false') {
         $('#fifth-tab').addClass("active");
         $('#fifth').addClass("show active");
      }
   }
   $('#leadTable,#managerTable').dataTable({
      "order": [
         [4, 'asc'],
      ],
      "lengthChange": false,
      "pageLength": 25,
      "columnDefs": [{
         "targets": "_all",
         "orderable": false
      }]
   });
});

function searchEmpData(obj, flag) {

   $("#leaderShipEmployeeDetailsDiv").hide();
   $("#recruiterEmployeeDetailsDiv").hide();
   $("#hrEmployeeDetailsDiv").hide();
   $("#hrEmpDetailsListTableBody").empty();
   $("#leaderShipEmpDetailsListTableBody").empty();
   $("#recruiterEmpDetailsListTableBody").empty();
   if ($('#keyword' + flag).val().length < 3) {
      alertModal("Skill Admin:Please provide at least 3 Characters to search");
      return false;
   } else {
      $(obj).prop('disabled', 'disabled');
      var url = $('#searchEmployeeDetailsUrl').val();
      var portal = $('#portletNamespace').val();
      var skillWorkflowUrl = $('#skillWorkflowUrl').val();
      var keyword = portal + 'keyword';
      var postData = {
         [keyword]: $('#keyword' + flag).val()
      };
      AUI().use('aui-io-request', function (A) {
         A.io.request(url, {
            method: 'post',
            data: postData,
            on: {
               success: function () {
                  var employeeData = JSON.parse(this.get('responseData'));
                  if (employeeData.data.length > 0) {
                     var tableRow = "";
                     var role;
                     if (flag == 1) {
                        role = 'Trantor_HR';
                     } else if (flag == 2) {
                        role = 'Trantor_Leader';
                     } else {
                        role = 'Trantor_Recruiter';
                     }
                     for (i = 0; i < employeeData.data.length; i++) {
                        var skillRow = "";
                        if (employeeData.data[i].skillCertificateAdded) {
                           skillRow = '<form action=' + skillWorkflowUrl + ' method="post"><input type="hidden" value=' + employeeData.data[i].empCode + ' name=' + portal + 'ecode /><input type="hidden" value=' + role + ' name=' + portal + 'role /> <button class="btn btn-sm btn-outline-primary">View</button> </form>';
                        } else {
                           skillRow = 'No Skill/Certificate Added';
                        }
                        tableRow += '<tr><td>' + employeeData.data[i].empCode + '</td><td>' + employeeData.data[i].name + '</td><td>' + employeeData.data[i].account + '</td><td>' + employeeData.data[i].managerName + '</td><td>' + skillRow + '</td></tr>';
                     }
                     if (flag == 1) {

                        $("#hrEmpDetailsListTableBody").append(tableRow);
                        $("#hrEmployeeDetailsDiv").show();
                     } else if (flag == 2) {
                        $("#leaderShipEmpDetailsListTableBody").append(tableRow);
                        $("#leaderShipEmployeeDetailsDiv").show();

                     } else if (flag == 3) {
                        $("#recruiterEmpDetailsListTableBody").append(tableRow);
                        $("#recruiterEmployeeDetailsDiv").show();

                     }

                     $(obj).prop('disabled', false);
                  } else {
                     alertModal("Skill Admin:No employee found with given search values.");
                     $(obj).prop('disabled', false);
                  }
               }
            }
         });
      });
   }
}

function setSubSkillList(obj) {
   $('#subSkillDetailsDiv').show();
   var selectedCoreSkill = obj.value;
   $('#subSkillTbody').children().hide();
   $('#subSkillTbody').children('.' + $.escapeSelector(selectedCoreSkill)).show();
   $('#additionalSubSkillRow').show();
   $('#submitCancelSubSkillButtonDiv').show();
}

function validateNewSkill() {
   var skillMap = $('#skillMapGson').val();
   var newSubSkill = $('#newSubskill').val();
   var alreadyExist = false;
   var coreSkillClass = $('#selectedCoreSkill').val();
   var skillMapGson = JSON.parse(skillMap);
   var subSkills = skillMapGson[coreSkillClass];
   for (var x = 0; x < subSkills.length; x++) {
      if (subSkills[x] == newSubSkill) {
         alertModal("Skill Admin:Subskill already exist, please provide unique value");
         alreadyExist = true;
      }
   }
   if (alreadyExist) {
      return false;

   } else {
      event.preventDefault();
      confirmModal('Skill Admin:Do you wish to save a new subskill ' + newSubSkill + ' ?', 'addSkillForm',null);
   }
}

function validateNewCertificate() {
   var newCertificate = $('#newCertificate').val();
   var alreadyExist = false;
   $(".certificates").each(function () {
      if ($(this).text() == newCertificate) {
         alertModal("Skill Admin:Certificate Category already exist, please provide unique value");
         alreadyExist = true;
      }
   });
   if (alreadyExist) {
      return false;

   } else {
      event.preventDefault();
      confirmModal("Skill Admin:Do you wish to save a new Certification  " + newCertificate + " ?", 'addCertificateForm',null);
   }
}

function add() {
   $('#certificateDetailsDiv').show();
   $('#additionalCategoryRow').show();
   $('#submitCancelCategoryButtonDiv').show();
   $('#addButton').hide();
}

function cancel() {
   $('#additionalSubSkillRow').hide();
   $('#submitCancelSubSkillButtonDiv').hide();
   $('#subSkillDetailsDiv').hide();
}

function cancelCertificate() {
   $('#certificateDetailsDiv').hide();
   $('#additionalCategoryRow').hide();
   $('#submitCancelCategoryButtonDiv').hide();
   $('#addButton').show();
}

$("#selectAllCheckbox").click(function () {
   if ($(this).prop("checked")) {
      $("[id^=checkbox]").prop("checked", "checked");
   } else {
      $("[id^=checkbox]").prop("checked", false);
   }
});

function singleCheckboxChecked() {
   if (!$(this).prop("checked")) {
      $("#selectAllCheckbox").prop("checked", false);
   }
}

function confirmFormSubmission() {
   var action = $("#actionPerformed").val();
   event.preventDefault();
   if (action == 1) {
      confirmModal('Skill Admin:Are you sure you want to approve selected skills of employee ?', 'submitSkillForm',null);
   } else if (action == 2) {
      confirmModal('Skill Admin:Are you sure you want to disapprove selected skills of employee ?', 'submitSkillForm',null);
   }
}

function formValidation(action) {
   var checkboxStatus = false;
   $("#terms").prop("required", false);
   $("#actionPerformed").val(action);
   if ($('[id^="checkbox"]:checked').length > 0) {
      checkboxStatus = true;
   }
   if (!checkboxStatus) {
      alertModal("Skill Admin:Please select at least one skill to continue");
      return false;
   } else {
      if (action == 1) {
         $('#empSkillTableBody').find('tr').each(function () {
            var row = $(this);
            if (row.find("input[id^='checkbox']").is(":checked")) {
               $("#skillRejectionReason").prop("required", false);
               var status = row.find("[id^='proficiencyLevelChanged']").val();
               var status = row.find("[id^='proficiencyLevelChanged']").val();
               if (status == "true") {
                  $("#terms").prop("required", "required");
               }
            }
         });
      } else {
         $("#terms").prop("required", false);
         $("#skillRejectionReason").prop("required", "required");
      }
   }
}

function downloadAttachments(certificateId, fileName) {
   var url = $('#downloadCertificatesUrl').val();
   var portal = $('#portletNamespace').val();
   var fileIdKey = $('#portletNamespace').val() + 'fileID';
   var certificateIdKey = $('#portletNamespace').val() + 'certificateId';
   var postData = {
      [certificateIdKey]: certificateId
   };
   AUI().use('aui-io-request', function (A) {
      A.io.request(url, {
         method: 'post',
         data: postData,
         on: {
            success: function () {
               var headerData = JSON.parse(this.get('responseData'));
               const fileData = convertBase64toBlob(headerData.data, 'application/octet-stream');
               let file = document.createElement("a");
               file.href = URL.createObjectURL(fileData);
               file.download = fileName;
               document.body.appendChild(file);
               file.click();
            }
         }
      });
   });
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

function setProficiencyLevelValue(obj) {
   proficiencyLevelVal = obj.value;
}

function compareWithInitialProficiencyVal(obj) {
   var index = obj.id.substring(16);
   var x = obj.value;
   if (proficiencyLevelVal != x) {
      $('#proficiencyLevelChanged' + index).val("true");
   }
}