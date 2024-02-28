var certificateNewRows = 0;

function setFilename(obj) {
   var fileName = obj.files[0].name;
   var fileSize = obj.files[0].size;
   var index = obj.id.substring(6);
   if (fileSize >= 4194304) {
      alertModal("Skill:Maximum file size per upload is 4mb. Try within limit");
      $(obj).val('');
      return false;

   } else {
      if (fileName.length > 70) {
         alertModal("Skill:Filename is too long. Please rename filename");
         $(obj).val('');
         return false;
      } else {
         $('#filename' + index).val(fileName);
      }
   }
}

function changeCertificateFormToEditMode() {
   $('#editCertificateButton').hide();
   $("#addCertificateRow").show();
   $("#cancelButtonCertificate").show();
   $("#submitCertificateDiv").show();
   $('#empCertificateTable').find('th:nth-child(1)').show();
   $('#empCertificateTable').find('td:nth-child(1)').show();
   var counter = document.getElementById("empCertificateTableBody").rows.length;
   if (counter == 0) {
      addNewCertificateRow();
   } else {
      $('#submitCertificateDiv').show();
   }
}

function customCertificateFormValidation(action, obj) {
   $("#certificateActionPerformed").val(action);
   var totalRows = $('#empCertificateTableBody tr').length;
   if (action == 1) {
      $("#totalRowsCertificate").val(certificateNewRows);
      $("[id^='certificateCategory']").prop("required", "required");
      $("[id^='certificateName']").prop("required", "required");
      $("[id^='upload']").prop("required", "required");
      var totalValue = [];
      for (i = 0; i < totalRows; i++) {
         var certificateCategory;
         var certificateName = $("#empCertificateTableBody").find("tr").eq(i).find("td").eq(2).find("input").val();
         var fileExist = $("#empCertificateTableBody").find("tr").eq(i).find("td").eq(5).find("button");

         if (fileExist.length > 0) //When page load and file is already present then length greater then 0
         {
            certificateCategory = $("#empCertificateTableBody").find("tr").eq(i).find("td").eq(1).find("input").val();
         } else {
            certificateCategory = $("#empCertificateTableBody").find("tr").eq(i).find("td").eq(1).find("select").val();
         }
         if (certificateCategory != '' && certificateName != '') {
            var comparableString = certificateCategory + certificateName;
            totalValue.push(comparableString);
         }
      }
      var uniqueValue = new Set(totalValue);
      if (totalValue.length != uniqueValue.size) {
         alertModal("Skill:Please enter unique certificates.");
         return false;
      }
   } else if (action == 2) {
      $("[id^='certificateCategory']").prop("required", false);
      $("[id^='certificateName']").prop("required", false);
      $("[id^='upload']").prop("required", false);
      $("#deletedCertificateId").val(obj.id);
   }

}

function confirmCertificateFormSubmission() {
   var action = $("#certificateActionPerformed").val();
   event.preventDefault();
   if (action == 1) {
      confirmModal('Skill:Are you sure you want to update your certification ?', 'certificateForm',null);
   } else if (action == 2) {
      confirmModal('Skill:Are you sure you want to delete this certification ?', 'certificateForm',null);
   }
}

function addNewCertificateRow() {
   $('#noCertificate').hide();
   if (certificateNewRows < 5) {
      certificateNewRows++;
      var certificateList = JSON.parse($('#certificateSet').val());
      var namespace = $('#portletNamespace').val();
      var newRow = '<tr><td class="p-0" id="deleteCertificate' + certificateNewRows + '"><button type="button" onclick="removeCertificateRow(this)" class="btn btn-sm btn-outline-danger btn-sm"><i class="far fa-trash-alt"></i></button></td>';
      newRow += '<td class="p-0"><select class="custom-select custom-select-sm mb-0"  id="certificateCategory' + certificateNewRows + '" name="' + namespace + 'certificateCategory' + certificateNewRows + '">';
      newRow += '<option  selected disabled value="">Select Category</option>';
      for (var x = 0; x < certificateList.length; x++) {
         newRow += '<option  value="' + certificateList[x] + '">' + certificateList[x] + '</option>';
      }
      newRow += '</select></td>';
      newRow += '<td class="p-0"><input type="text"   class="form-control form-control-sm p-0 pl-1" maxlength="75"  onblur="$(this).val($(this).val().trim())"   id="certificateName' + certificateNewRows + '"   name="' + namespace + 'certificateName' + certificateNewRows + '"/></td>';
      newRow += '<td class="p-0"><textarea rows="2" cols="50" maxlength="1000" style="height:65px;" class="form-control form-control-sm"   onblur="$(this).val($(this).val().trim())"  id="certificateDesc' + certificateNewRows + '"  name="' + namespace + 'certificateDesc' + certificateNewRows + '"></textarea></td>';
      newRow += '<td class="p-0"><input type="text" style="cursor:pointer"  class="form-control form-control-sm fn_datePicker" id="expiryDate' + certificateNewRows + '" readonly  name="' + namespace + 'expiryDate' + certificateNewRows + '"/></td>';
      newRow += '<td class="p-0 pl-1"><input type="hidden" id="filename' + certificateNewRows + '" name="' + namespace + 'filename' + certificateNewRows + '"/><input type="file" class="ml-4"  required onchange="setFilename(this)"   id="upload' + certificateNewRows + '"  name="' + namespace + 'upload' + certificateNewRows + '"/></td></tr>';
      $("#empCertificateTableBody").append(newRow);
      $("#submitCertificateDiv").show();

      $('.fn_datePicker').datepicker({
         dateFormat: "yy-mm-dd",
         changeMonth: true,
         changeYear: true,
         yearRange: '-60:+60',
      });
   } else {
      alertModal('Skill:You cannot add more than 5 Certificates');
      return false;
   }
}

function changeSkillFormToEditMode() {
   $('#editSkillButton').hide();
   $('#cancelButtonSkill').show();
   $("#addSkillRow").show();
   $('#empSkillTable').find('th:nth-child(1)').show();
   var counter = document.getElementById("empSkillTableBody").rows.length;
   if (counter == 0) {
      addNewSkillRow();
   } else {
      $('#submitCancelSkillDiv').show();
   }
   for (var x = 1; x <= counter; x++) {
      if ($('#empSkillTableBody').find('tr').eq(x - 1).find('td').eq(6).text().trim() != "Submitted") {
         if ($('#empSkillTableBody').find('tr').eq(x - 1).find('td').eq(1).find('select').eq(0).val() != "Information Security") {

            var coreSkillDropDownId = "coreSkill" + x;
            $('#' + coreSkillDropDownId).children().show();
            var coreSkillValue = $('#' + coreSkillDropDownId).val();
            var subSkillDropDownId = "subSkill" + x;
            var subSkillValue = $('#' + subSkillDropDownId).val();
            $('#' + subSkillDropDownId).children().hide();
            $('#' + subSkillDropDownId).children('.' + $.escapeSelector(coreSkillValue)).show();
            $('#' + subSkillDropDownId).val(subSkillValue);
            $('#type' + x).children().show();
            $('#type' + x).children().prop('disabled', false);
            $('#' + coreSkillDropDownId).find('option[value="Information Security"]').hide();
            $('#' + subSkillDropDownId).find('option[value="Information Security"]').hide();
         }

         $('#validity' + x).attr("class", "form-control form-control-sm fn_datePicker");
         $('#tool' + x).prop('readonly', false);
         $('#tool' + x).attr("class", "form-control form-control-sm");
         $('#version' + x).prop('readonly', false);
         $('#version' + x).attr("class", "form-control form-control-sm");
         $('#proficiencyLevel' + x).children().show();
         $('#proficiencyLevel' + x).children().prop('disabled', false);

      }
      $('#action' + x).show();
      $('.fn_datePicker').datepicker({
         dateFormat: "yy-mm-dd",
         changeMonth: true,
         changeYear: true,
         yearRange: '-60:+60',
      });
   }
}

function confirmSkillFormSubmission() {
   var action = $("#skillActionPerformed").val();
   event.preventDefault();
   if (action == 1) {
      confirmModal('Skill:Are you sure you want to submit the skills to your manager ?', 'skillForm',null);
   } else if (action == 2) {
      confirmModal('Skill:Are you sure you want to delete this skill from your skills set ?', 'skillForm',null);
   }
}

function customSkillFormValidation(action, obj) {
   $("#skillActionPerformed").val(action);
   if (action == 1) {
      var totalRows = $('#empSkillTableBody tr').length;
      $("[id^='coreSkill']").prop("required", "required");
      $("[id^='subSkill']").prop("required", "required");
      $("[id^='type']").prop("required", "required");
      $("[id^='proficiencyLevel']").prop("required", "required");
      $("#totalRows").val(totalRows);
      if (totalRows > 0) {
         var totalValue = [];
         for (i = 0; i < totalRows; i++) {
            var coreSkill = $("#empSkillTableBody").find("tr").eq(i).find("td").eq(1).find("select").eq(0).val();
            var subSkill = $("#empSkillTableBody").find("tr").eq(i).find("td").eq(1).find("select").eq(1).val();
            if (coreSkill != '' || subSkill != '') {
               var comparableString = coreSkill + subSkill;
               totalValue.push(comparableString);
            }
         }
         var uniqueValue = new Set(totalValue);
         if (totalValue.length != uniqueValue.size) {
            alertModal("Skill:Please enter unique skills.");
            return false;
         }
      } else {
         alertModal("Skill:Please enter at least one skill to submit");
         return false;
      }
   } else if (action == 2) {
      $("[id^='coreSkill']").prop("required", false);
      $("[id^='subSkill']").prop("required", false);
      $("[id^='type']").prop("required", false);
      $("[id^='proficiencyLevel']").prop("required", false);
      $("#deletedSkillId").val(obj.id);
   }
}

function addNewSkillRow() {
   $('#noSkill').hide();
   var skillsMap = JSON.parse($('#skillsMap').val());
   var namespace = $('#portletNamespace').val();
   var counter = $('#empSkillTableBody tr').length;
   counter++;
   var cols = "<tr>";
   if (counter == 1) {
      cols += '<td  class="p-0"><small id="action' + counter + '" >Mandatory</small></td>';
   } else {
      cols += '<td  class="p-0"  id="action' + counter + '"><button type="button"  onclick="removeSkillRow(this)" class="btn btn-sm btn-outline-danger py-0"><i class="fas fa-trash-alt"></i></button></td>';
   }
   var coreSkill = '<select class="custom-select custom-select-sm mb-1 " id="coreSkill' + counter + '" onChange="setSubSkillDropdown(this)"  name="' + namespace + 'coreSkill' + counter + '" required  style="width:100%" ><option value="" selected disabled>Select Core Skill</option>';
   var subSkill = '<select class="custom-select custom-select-sm mb-1 " id="subSkill' + counter + '"  name="' + namespace + 'subSkill' + counter + '" required  style="width:100%" ><option value="" selected disabled>Select Sub Skill</option>';
   $.each(skillsMap, function (k, v) {
      coreSkill += '<option value="' + k + '">' + k + '</option>';
      var subSKill = v;
      for (var x = 0; x < subSKill.length; x++) {
         subSkill += '<option  style="display:none" class="' + k + '" value="' + v[x] + '">' + v[x] + '</option>';
      }
   });

   coreSkill += '</select>';
   subSkill += '</select>';
   cols += '<td class="p-0"><input type="hidden" value="0"  name="' + namespace + 'skillId' + counter + '"/>' + coreSkill + subSkill + '</td>';
   cols += '<td class="p-0"><select class="custom-select custom-select-sm mb-1" required  id="type' + counter + '"  name="' + namespace + 'type' + counter + '"><option value="" disabled selected>Select Type</option><option value="Primary">Primary</option><option value="Secondary">Secondary</option></select><select class="custom-select custom-select-sm mb-1" required id="proficiencyLevel' + counter + '"  name="' + namespace + 'proficiencyLevel' + counter + '" ><option value="" disabled selected>Select Level</option><option value="1">Basic</option><option value="2">Beginner</option><option value="3">Intermediate</option><option value="4">Advanced</option><option value="5">Expert</option></select></td>';
   cols += '<td class="p-0"><textarea rows="2" cols="50" style="height:80px;" maxlength="1000" onblur="$(this).val($(this).val().trim())"  class="form-control form-control-sm" id="tool' + counter + '"  name="' + namespace + 'tool' + counter + '"></textarea></td>';
   cols += '<td class="p-0"><textarea rows="2" cols="50" style="height:80px;" maxlength="1000" onblur="$(this).val($(this).val().trim())"  class="form-control form-control-sm" id="version' + counter + '"  name="' + namespace + 'version' + counter + '"></textarea></td>';
   cols += '<td class="p-0"><input type="text" class="form-control form-control-sm pl-2 fn_datePicker"  style="cursor:pointer"  id="validity' + counter + '"  name="' + namespace + 'validity' + counter + '"/></td>';
   cols += '<td class="p-0"><input type="text"  class="form-control-plaintext form-control-sm text-center" readonly value ="Open" /></td>';
   cols += '</tr>';
   $("#empSkillTableBody").append(cols);
   if (counter == 1) {
      $('#coreSkill' + counter).val("Information Security");
      $('#subSkill' + counter).val("Information Security");
      $('#coreSkill' + counter).children().hide();
      $('#subSkill' + counter).children().hide();
      $('#coreSkill' + counter).find('option[value="Information Security"]').show();
      $('#subSkill' + counter).find('option[value="Information Security"]').show();
      $('#type' + counter).val("Primary");
      $('#type' + counter).children().hide();
      $('#type' + counter).find('option[value="Primary"]').show();
   } else {
      $('#coreSkill' + counter).find('option[value="Information Security"]').hide();
      $('#subSkill' + counter).find('option[value="Information Security"]').hide();

   }
   $("#submitCancelSkillDiv").show();
   $('.fn_datePicker').datepicker({
      dateFormat: "yy-mm-dd",
      changeMonth: true,
      changeYear: true,
      yearRange: '-60:+60',
   });

}

function removeCertificateRow(obj) {
   var namespace = $('#portletNamespace').val();
   certificateNewRows--;
   if (certificateNewRows == 0) {
      $("#submitCertificateDiv").hide();
      $("#noCertificate").show();
   } else {
      $("#submitCertificateDiv").show();
      $("#noCertificate").hide();
   }
   var index = $(obj).parent().attr('id');
   var val = parseInt(index.substring(17));
   var child = $(obj).closest('tr').nextAll();
   child.each(function () {
      $(this).find('td').eq(0).find('button').attr('id', 'deleteCertificate' + val);
      $(this).find('td').eq(1).find('select').eq(0).attr('id', 'certificateCategory' + val);
      $(this).find('td').eq(1).find('select').eq(0).attr('name', namespace + 'certificateCategory' + val);
      $(this).find('td').eq(2).find('input').eq(0).attr('id', 'certificateName' + val);
      $(this).find('td').eq(2).find('input').eq(0).attr('name', namespace + 'certificateName' + val);
      $(this).find('td').eq(3).find('textarea').eq(0).attr('id', 'certificateDesc' + val);
      $(this).find('td').eq(3).find('textarea').eq(0).attr('name', namespace + 'certificateDesc' + val);
      $(this).find('td').eq(4).find('input').eq(0).attr('id', 'expiryDate' + val);
      $(this).find('td').eq(4).find('input').eq(0).attr('name', namespace + 'expiryDate' + val);
      $(this).find('td').eq(5).find('input').eq(0).attr('id', 'filename' + val);
      $(this).find('td').eq(5).find('input').eq(0).attr('name', namespace + 'filename' + val);
      $(this).find('td').eq(5).find('input').eq(1).attr('id', 'upload' + val);
      $(this).find('td').eq(5).find('input').eq(1).attr('name', namespace + 'upload' + val);
      val++;
   });
   $(obj).closest("tr").remove();

}

function removeSkillRow(obj) {
   var namespace = $('#portletNamespace').val();
   var index = $(obj).parent().attr('id');
   var val = parseInt(index.substring(6));
   var child = $(obj).closest('tr').nextAll();
   child.each(function () {
      $(this).find('td').eq(0).find('button').attr('id', 'action' + val);
      $(this).find('td').eq(1).find('select').eq(0).attr('id', 'coreSkill' + val);
      $(this).find('td').eq(1).find('select').eq(0).attr('name', namespace + 'coreSkill' + val);
      $(this).find('td').eq(1).find('select').eq(1).attr('id', 'subSkill' + val);
      $(this).find('td').eq(1).find('select').eq(1).attr('name', namespace + 'subSkill' + val);
      $(this).find('td').eq(2).find('select').eq(0).attr('id', 'type' + val);
      $(this).find('td').eq(2).find('select').eq(0).attr('name', namespace + 'type' + val);
      $(this).find('td').eq(2).find('select').eq(1).attr('id', 'proficiencyLevel' + val);
      $(this).find('td').eq(2).find('select').eq(1).attr('name', namespace + 'proficiencyLevel' + val);
      $(this).find('td').eq(3).find('textarea').eq(0).attr('id', 'tool' + val);
      $(this).find('td').eq(3).find('textarea').eq(0).attr('name', namespace + 'tool' + val);
      $(this).find('td').eq(4).find('textarea').eq(0).attr('id', 'version' + val);
      $(this).find('td').eq(4).find('textarea').eq(0).attr('name', namespace + 'version' + val);
      $(this).find('td').eq(5).find('input').eq(0).attr('id', 'validity' + val);
      $(this).find('td').eq(5).find('input').eq(0).attr('name', namespace + 'validity' + val);

      val++;
   });
   $(obj).closest("tr").remove();
   var counter = $('#empSkillTableBody tr').length;
   if (counter == 0) {
      $('#noSkill').show();
   }
}

function setSubSkillDropdown(obj) {
   var selectedCoreSkill = obj.value;
   var index = obj.id.substring(9);
   var subSkillDropDownId = "subSkill" + index;
   $('#' + subSkillDropDownId).children().hide();
   $('#' + subSkillDropDownId).children('.' + $.escapeSelector(selectedCoreSkill)).show();
   var selectedSubSkill = $('#' + subSkillDropDownId).children('.' + $.escapeSelector(selectedCoreSkill)).eq(0).val();
   $('#' + subSkillDropDownId).val(selectedSubSkill);

}

function downloadUserManual() {
   var url = $('#downloadUserManualResourceUrl').val();
   var portal = $('#portletNamespace').val();
   AUI().use('aui-io-request', function (A) {
      A.io.request(url, {
         method: 'post',
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

function downloadAttachments(certificateId, fileName) {
   var url = $('#downloadCertificatesUrl').val();
   var portal = $('#portletNamespace').val();
   var certificateIdKey = $('#portletNamespace').val() + 'certificateId';
   var postData = {
      [certificateIdKey]: certificateId,
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

function cancelButton(val) {
   var formId = val == 1 ? 'skillForm' : 'certificateForm';
   event.preventDefault();
   confirmModal('Skill:Any unsaved changes will be lost. Do you wish to continue ?', 'cancelButtonSkill',null);
}