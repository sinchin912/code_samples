$('.fn_text-trim').on('blur', function () {
   var trimValue = $(this).val().trim();
   $(this).val(trimValue);
});

function validateKpiSubmissionForm() {
   event.preventDefault();
   var confirmFromUser = confirmModal('Performance:Are you sure you want to finalize these KPIs ?', 'kpiSubmissionForm', null);

}

function updateKpiSubmissionForm() {
   event.preventDefault();
   confirmModal('Performance:Are you sure you want to update KPI Setting of account ' + $('#account').val() + '?', 'updateKpiForm', null);

}

function generateReviewForm() {
   event.preventDefault();
  confirmModal('Performance:Your Review Form will appear in the Performance Review table below for further process. If you wish to update KPIs, please do it before generating Review Form. Are you sure you want to Generate Review Form now ?', 'generateReviewForm', null);

}

function updateTargetOnSelect(obj) {
   var checkboxId = $(obj).attr('id');
   var index = checkboxId.substring(8);
   var objTargetId = "objTarget" + index;
   var selectedAttributes = $("[id^=checkbox]").filter(':checked').length;
   var customKpi = $('.fn_customKpi').length;
   var totalObjectives = selectedAttributes + customKpi;

   if ($(obj).prop('checked') == true) {
      if (totalObjectives < 11) {
         $("#" + objTargetId).attr("class", "form-control form-control-sm text-trim");
         $("#" + objTargetId).prop("readonly", false);
         $("#" + objTargetId).prop("required", "required");
         return true;
      } else {
         alertModal('Performance:You have already added maximum limit of KPIs.');
         $(obj).prop('checked', false);
         return false;
      }
   } else {
      $("#" + objTargetId).attr("class", "form-control-plaintext form-control-sm text-trim");
      $("#" + objTargetId).prop("readonly", "readonly");
      $("#" + objTargetId).prop("required", false);
      return true;
   }
}

function addObjectives() {
   var namespace = $('#portletNamespace').val();
   var selectedAttributes = $("[id^=checkbox]").filter(':checked').length;
   var customKpi = $('.fn_customKpi').length;
   var totalObjectives = selectedAttributes + customKpi;
   if (totalObjectives < 10) {
      var counter = $("#numOfOtherObjectives").val();
      counter++;
      $("#numOfOtherObjectives").val(counter);
      $("#objSettingsRows").append('<tr><td><button class="btn btn-sm btn-outline-danger py-0 fn_customKpi" id="deleteKpi' + counter + '" onclick="removeKpiRow(this);" > <i class="fas fa-trash-alt" aria-hidden="true"></i></button></td><td class="p-0"><input type="text" onblur="$(this).val($(this).val().trim())" class="form-control-sm form-control"  placeholder="Others" maxlength="75" required="required"   name="' + namespace + 'otherTitle' + counter + '"></td> <td class="p-0"><textarea class="form-control form-control-sm" onblur="$(this).val($(this).val().trim())"  style="height:80px;"  maxlength="10000" required="required"  name="' + namespace + 'otherDescription' + counter + '"></textarea></td><td  class="p-0"><textarea row="2" column="50"  style="height:80px;" maxlength="10000"  class="form-control form-control-sm" onblur="$(this).val($(this).val().trim())"   name="' + namespace + 'otherTarget' + counter + '" required></textarea></td></tr>');
   } else {
      alertModal('Performance:You have already added maximum limit of KPIs.');
   }
}

function removeKpiRow(obj) {
   var namespace = $('#portletNamespace').val();
   var index = $(obj).attr('id');
   var val = parseInt(index.substring(9));
   var child = $(obj).closest('tr').nextAll();
   child.each(function () {
      $(this).find('td').eq(0).find('button').attr('id', 'deleteKpi' + val);
      $(this).find('td').eq(1).find('input').eq(0).attr('name', namespace + 'otherTitle' + val);
      $(this).find('td').eq(2).find('textarea').eq(0).attr('name', namespace + 'otherDescription' + val);
      $(this).find('td').eq(3).find('textarea').eq(0).attr('name', namespace + 'otherTarget' + val);
      val++;
   });
   $(obj).closest("tr").remove();
}

function addSecondaryAccount() {
$('#account').children().show();
$("#kpiTableBodyId tr").each(function() {
                 var accountName = $(this).find("td").eq(0).text().trim();
                 $('#account option[value="'+accountName+'"]').hide();
             });
$(".fn_editAccountDetail ,.fn_deleteAccountButton, .fn_viewFormButton").each(function() {
          $(this).hide();
        });
   $('#addEditAccountDiv').show();
   $('#addAccountButton').hide();
}
	function cancelAddAccount() {
    	$('#addAccountButton').show();
        $("#addEditAccountDiv").hide();
        removeWarning("M");
        removeWarning("R");
        $('#saveProjectButton').prop('disabled', false);
        document.getElementById("addNewAccountForm").reset();
        $(".fn_editAccountDetail ,.fn_deleteAccountButton, .fn_viewFormButton").each(function() {
          $(this).show();
        });
}
function getManagerName() {
   var mEmail = $('#managerEmail').val().trim();
   if (mEmail != '') {
      var isExisted = false;
      $('#saveAccountButton').prop('disabled', true);

      var selectedAccount = $('#account').val();
      $(".fn_email-has-errorManager").text("");
      var rowsCount = $('#kpiTableBodyId tr').length;
      if (rowsCount > 0) {
         for (i = 0; i < kpiTableBodyId.rows.length; i++) {
            var insideTableManagerEmail = $("#kpiTableBodyId").find("tr").eq(i).find("td").eq(2).find("input").eq(0).val();
            var insideTableAccountName = $("#kpiTableBodyId").find("tr").eq(i).find("td").eq(0).text();
            if (insideTableManagerEmail === mEmail && insideTableAccountName != selectedAccount) {
               isExisted = true;
            }
         }
         if (isExisted) {
            alertModal("Performance:Only one Account can be added with same manager !");
            $("#manageremail").val("");
            $("#managerName").val("");
            $(".fn_email-has-errorManager ").text("Change email address.");
            return false;
         } else {
            if (isEmail(mEmail, "M")) {
               var url = $('#getManagerNameResourceUrl').val();
               var mEmailKey = $('#portletNamespace').val() + 'mEmail';
               var postData = {
                  [mEmailKey]: mEmail,
               };
               AUI().use('aui-io-request', function (A) {
                  A.io.request(url, {
                     method: 'post',
                     data: postData,
                     on: {
                        success: function () {
                           var ManagerData = JSON.parse(this.get('responseData'));
                           if (null != ManagerData && "" != ManagerData && ManagerData.data != "" && ManagerData.data != null) {

                              $("#managerName").val(ManagerData.data._name);
                           } else {
                              $(".fn_email-has-errorManager").text("Not a valid Manager Email Id");
                              $("#managerName").val("");
                              $("#managerEmail").val("");
                           }
                           $('#saveAccountButton').prop('disabled', false);
                        }
                     }
                  });
               });
            }
         }
      }
   } else {
   $("#managerEmail").val("");
   }
}

function getReviewerName() {
   var reviewerEmail = $('#reviewerEmail').val().trim();
   if (reviewerEmail != '') {
      var isExisted = false;
      $('#saveAccountButton').prop('disabled', true);

      $(".fn_email-has-errorReviewer").text("");
      var selectedAccount = $('#account').val();
      var rowsCount = $('#kpiTableBodyId tr').length;
      if (rowsCount > 0) {
         for (i = 0; i < kpiTableBodyId.rows.length; i++) {
            var insideTableReviewerEmail = $("#kpiTableBodyId").find("tr").eq(i).find("td").eq(3).find("input").eq(0).val();
            var insideTableAccountName = $("#kpiTableBodyId").find("tr").eq(i).find("td").eq(0).text();
            if (insideTableReviewerEmail === reviewerEmail && insideTableAccountName != selectedAccount) {
               isExisted = true;
            }
         }
         if (isExisted) {
            alertModal("Performance:Only one account can be added with same reviewer !");
            $("#reviewerEmail").val("");
            $("#reviewerName").val("");
            $(".fn_email-has-errorReviewer ").text("Change email address.");
            return false;
         } else {
            if (isEmail(reviewerEmail, "R")) {
               var url = $('#getReviewerNameResourceUrl').val();
               var reviewerEmailKey = $('#portletNamespace').val() + 'reviewerEmail';
               var postData = {
                  [reviewerEmailKey]: reviewerEmail,
               };
               AUI().use('aui-io-request', function (A) {
                  A.io.request(url, {
                     method: 'post',
                     data: postData,
                     on: {
                        success: function () {
                           var reviewerData = JSON.parse(this.get('responseData'));
                           if (reviewerData.data != "" && reviewerData.data != null) {
                              $("#reviewerName").val(reviewerData.data._name);
                           } else {
                              $(".fn_email-has-errorReviewer").text("Not a valid Reviewer Email Id");
                              $("#reviewerName").val("");
                               $("#reviewerEmail").val("");
                           }
                           $('#saveAccountButton').prop('disabled', false);
                        }
                     }
                  });
               });
            }
         }
      }
   }
   else {
   $("#reviewerEmail").val("");
   }
}

      $("#saveSelfReview").click(function(ev) {

            var form = $("#selfReviewForms");
            var url = $('#saveFormDataResourceUrl').val();
            $.ajax({
                type: "POST",
                url: url,
                data: form.serialize(),
                success: function(data) {

                    alertModal('Performance:Form saved successfully');
                },
                error: function(data) {

                    // Some error in ajax call
                    alertalertModal('Performance:some Error');
                }
            });
        });
function removeWarning(type) {
   if (type == "M") {
      $(".fn_email-has-errorManager").text("");
   } else {
      $(".fn_email-has-errorReviewer").text("");
   }
}

function isEmail(email, type) {
   var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
   if (!regex.test(email)) {
      if (type == "M") {
         $("#managername").val("");
         $(".fn_email-has-errorManager ").text("Not a valid email address.");
      } else {
         $("#reviewername").val("");
         $(".fn_email-has-errorReviewer ").text("Not a valid email address.");
      }
      return false;
   } else if (!(email.includes("@trantorinc.com") || email.includes("@chd.trantorinc.com"))) {
      if (type == "M") {
         $("#managername").val("");
         $(".fn_email-has-errorManager").text("Not a valid Trantor email address.");
      } else {
         $("#reviewername").val("");
         $(".fn_email-has-errorReviewer").text("Not a valid Trantor email address.");
      }
      return false;
   } else {
      return true;
   }
}

function collapseAll() {
   $(' .collapse').removeClass('show');
   return false;
}

function expandAll() {
   $('.collapse').addClass('show');
   return false;
}

function uploadFile(reviewId) {
   var totalFileSize = 0;
   var inp = document.getElementById('uploadAchievements');
   for (var i = 0; i < inp.files.length; ++i) {
      totalFileSize = totalFileSize + inp.files.item(i).size;
   }

   if (totalFileSize >= 4194304) {
      alertModal('Performance:Maximum file size per upload is 4mb. Try to upload fewer files in single upload');
      return false;
   }

   $('#uploadFileCloud').hide();
   $('#uploadingFileCloud').show();
   var nameSpace = $('#portletNamespace').val();
   setTimeout(function () {
      const inpFile = document.getElementById('uploadAchievements');
      const xhr = new XMLHttpRequest();
      const formData = new FormData();
      formData.append(nameSpace + 'reviewId', reviewId)
      for (const file of inpFile.files) {
         formData.append(nameSpace + 'myFiles', file)
      }
      xhr.open("post", $('#uploadAttachmentsResourceUrl').val());
      xhr.send(formData);
      xhr.onreadystatechange = function () {
         if (xhr.readyState === 4) {
            var status = xhr.status;

            var posts = JSON.parse(xhr.responseText);

            if (posts.data != '' && posts.data != null) {
               $("#filesList").empty();
               var fileList = '<ul>'
               for (i = 0; i < posts.data.uploadsAttachments.length; i++) {
                  fileList += '<li style="height:25px;"><span class ="btn btn-link btn-sm" type= "button"  onClick="downloadAttachments(\'' + posts.data.uploadsAttachments[i].uploadId + '\',\'' + posts.data.uploadsAttachments[i].fileName + '\')">' + posts.data.uploadsAttachments[i].fileName + '</span> - <button   style="color:red;" class="close" type="button" aria-label="Close" id="' + posts.data.uploadsAttachments[i].uploadId + '" onClick ="deleteAttachment(this,\'' + posts.data.uploadsAttachments[i].fileName + '\')"><i class="fas fa-trash-alt" aria-hidden="true"></i></button></li>';
               }
               fileList += '</ul>';
               $("#filesList").append(fileList);
            } else {
               alertModal('Performance:Something went wrong while uploading your file');
            }
            $('#uploadingFileCloud').hide();
            $('#uploadFileCloud').show();
         }
      }
   }, 2000);
}
function getYearlyEntries(){
        $('#previousProjectTable').DataTable().destroy();
        $('#yearlyReviewDiv').hide();
        $('#previousAccountTableBody').empty();
           var nameSpace = $('#portletNamespace').val();
              var ecodeKey = nameSpace + 'ecode';
              var forDateKey = nameSpace + 'forDate';
              var postData = {
                 [ecodeKey]: $('#ecode').val(),
                 [forDateKey]: $('#ayForEntries').val(),
              };
        AUI().use('aui-io-request',function(A) {
			A.io.request($('#getYearlyEntriesResourceUrl').val(),{
				method : 'post',
				data : postData,
				on : {
					success : function() {
						var reviewData = JSON.parse(this.get('responseData'));
						if(null != reviewData && null != reviewData.data && reviewData.data.length > 0){
						    var tableRow = '';
						    for(var x=0 ; x< reviewData.data.length ; x++){
						        var colorClass = 'black';

                                tableRow += '<tr><td>'+reviewData.data[x].account+'</td><td>'+reviewData.data[x].reviewType+'</td><td>'+reviewData.data[x].accountType+'</td><td>'+reviewData.data[x].managerName+'</td><td><div style="color: '+colorClass+'"><strong>'+reviewData.data[x].reviewStateName+'</strong></div></td><td><form action=' + $('#reviewWorkflowResourceUrl').val() + ' method="post"><input type="hidden" name="'+nameSpace+'reviewId"  value="'+reviewData.data[x].reviewId+'"  /><button type="submit" class=" btn btn-outline-primary btn-sm" style="cursor: pointer;">  View </button></form></td></tr>';
						    }
						    $('#previousAccountTableBody').append(tableRow);
                            $('#previousProjectTable').DataTable({
                                        dom : 'Bfrtip',
                                        "aaSorting": [ [1,'desc'], [2,'asc'] ],
                                        "paging": false,
                                        "bFilter": false,
                                        "columnDefs": [ {
                                            "targets": "_all",
                                            "orderable": false
                                        }]
                            });
                            $('#yearlyReviewDiv').show();
						} else {
						    alertModal("Performance:No review found for selected Review Year");
						}
					}
				}
			});
		});
    }
function deleteAttachment(obj, filename) {
   var action = confirm('Performance:Do you wish to delete file ' + filename + ' ?');
   if (action) {
      var uploadId = $(obj).attr('id');
      var nameSpace = $('#portletNamespace').val();
      var url = $('#deleteAttachmentsResourceUrl').val();
      var deleteKey = nameSpace + 'uploadId';
      var postData = {
         [deleteKey]: uploadId,
      };
      AUI().use('aui-io-request', function (A) {
         A.io.request(url, {
            method: 'post',
            data: postData,
            on: {
               success: function () {
                  $('#' + uploadId).parent().remove();
               }
            }
         });
      });
   }
}

function downloadAttachments(uploadId, fileName) {
   var nameSpace = $('#portletNamespace').val();
   var url = $('#downloadAttachmentsUrlResourceUrl').val();
   var deleteKey = nameSpace + 'uploadId';
   var postData = {
      [deleteKey]: uploadId,
   };
   AUI().use('aui-io-request', function (A) {
      A.io.request(url, {
         method: 'post',
         data: postData,
         on: {
            success: function () {

               var headerData = JSON.parse(this.get('responseData'));
               const file = convertBase64toBlob(headerData.data, 'application/x-www-form-urlencoded');
               let a = document.createElement("a");
               a.href = URL.createObjectURL(file);

               a.download = fileName;
               document.body.appendChild(a);
               a.click();
            }
         }
      });
   });
}
function downloadUserManual(val) {
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
function submitSelfReview() {
   var totalCount = $('#selfReviewState tr').length / 2;
      var invalidForm = false;
      for (i = 0; i < totalCount; i++) {
         if ($.trim($("#selfComments" + i).val()) == "") {
            invalidForm = true;
         }
      }
      if (invalidForm) {
         alertModal("Performance:Please provide self comments for all KPIs.");
         var expanding = expandAll();
         window.scrollTo(0, 0);
         return expanding;
      } else {
         event.preventDefault();
         confirmModal('Performance:Are you sure you want to submit this self rating to your manager ?', 'selfReviewForms', null);
      }
}
function kpiUpdateForm(obj) {
  var id=obj.id;
   event.preventDefault();
       confirmModal('Performance:Are you sure you want to review/update KPIs Settings of Account ' + $('#account').val() + " ?", id, null);
}

function submitSelfReviewForm() {
   event.preventDefault();
   confirmModal('Performance:Are you sure you want to submit this self rating to your manager ?', 'selfReviewForms', null);
}

function addNewAccountFormValidation() {
   var managerName = $("#managername").val();
   var reviewerName = $("#reviewername").val();
   if (managerName == '' || reviewerName == '') {
      alertModal("Performance:Manager or Reviewer name can't be empty");
      return false;
   } else {
      event.preventDefault();
      confirmModal('Performance:Are you sure you want to add/update account details ?', 'addNewAccountForm', null);
   }
}

function managerMeetingRaise() {
   event.preventDefault();
   confirmModal('Performance:Are you sure you want to request a meeting with manager ?', 'raiseManagerMeeting', null);
}

function managerMeetingRaise() {
   var account = $('#account').val();
   event.preventDefault();
   confirmModal('Performance:Are you sure you want to review/update KPIs Settings of account ' + account + ' ?', 'kpiUpdateForm', null);
}
function deleteReviewForm(obj) {
var account = $('#deleteAccount').val();
   event.preventDefault();
   confirmModal('Performance:Are you sure to delete Review for account ' + account + ' ?', 'deleteReviewForm_'+obj, null);
}
function setAccountVal(obj) {
 $('#deleteAccount').val(obj.value);
}
function setKpiVal(obj) {
 $('#deleteKpi').val(obj.value);
}
function deleteAccountForm(obj) {
   event.preventDefault();
   confirmModal('Performance:Are you sure to delete  account ' +  $('#deleteKpi').val() + ' ?', 'deleteKpiAccount_'+obj, null);
}

function validateEmpSignOff(action) {
   if (action == 1) {
      $('#action').val(1);
      $('#raiseToHrComments').prop('required', false);
   }
   if (action == 2) {
      $('#action').val(2);
      $('#raiseToHrComments').prop('required', 'required');
   }
}

function submitEmpSignOff(obj) {
   var action = $('#action').val();
   if (action == 1) {
      obj.action = $('#acceptManagerRatingResourceUrl').val();
      event.preventDefault();
      confirmModal("Performance Admin:Are you sure you want to accept manager rating ?", 'empSignOffForm', null);
   }
   if (action == 2) {
      obj.action = $('#rejectManagerRatingResourceUrl').val();
      event.preventDefault();
      confirmModal("Performance Admin:Are you sure you want to raise manager rating to HR ?", 'empSignOffForm', null);
   }
}
function editAccountDetails(obj)
    {
    document.getElementById("addNewAccountForm").reset();
        var kpiId =  obj.id;
        var tableRow = 'tr'+kpiId;
        var accountName = $("#"+tableRow).find("td").eq(0).text().trim();
        var accountType = $("#"+tableRow).find("td").eq(1).text().trim();
        $('#account').val(accountName);
        $('#account').children().hide();
        $('#account option[value="'+accountName+'"]').show();
        var managerEmail = $("#"+tableRow).find("td").eq(2).find('input').eq(0).val().trim();
        var managerName = $("#"+tableRow).find("td").eq(2).text().replace(/\n/g, '').trim();
        var reviewerEmail = $("#"+tableRow).find("td").eq(3).find('input').eq(0).val().trim();
        var reviewerName = $("#"+tableRow).find("td").eq(3).text().replace(/\n/g, '').trim();


        $(".fn_editAccountDetail ,.fn_deleteAccountButton, .fn_viewFormButton").each(function() {
                  $(this).hide();
                });
        $("#kpiId").val(kpiId);
        $("#account").val(accountName);
        $("#managerEmail").val(managerEmail);
        $("#reviewerEmail").val(reviewerEmail);
        $("#managerName").val(managerName);
        $("#reviewerName").val(reviewerName);
        $("#addAccountButton").hide();
        $("#saveAccountButton").prop('disabled', false);
        $("#addEditAccountDiv").show();
    }

