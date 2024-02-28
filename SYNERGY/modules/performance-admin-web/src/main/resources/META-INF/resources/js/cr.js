var leadRole;
$(document).ready(function () {
     $('#leadTable').DataTable({
             dom : 'Bfrtip',
             buttons : [ 'excel' ],
             "paging" : false,
             "aaSorting": [ [3,'asc'],[4,'asc'] ],
             "columnDefs": [ {
                "targets": "_all",
                "orderable": false
             }]
         });
         $('#leadKpiTable').DataTable({
             dom : 'Bfrtip',
             buttons : [ 'excel' ],
             "paging" : false,
             "aaSorting": [ [4,'desc'] ],
             "columnDefs": [ {
                "targets": "_all",
                "orderable": false
             }]
         });
         $('#managerKpiTable').DataTable({
             dom : 'Bfrtip',
             buttons : [ 'excel' ],
             "paging" : false,
             "aaSorting": [ [4,'desc'] ],
             "columnDefs": [ {
                "targets": "_all",
                "orderable": false
             }]
         });
         $('#hrKpiTable').DataTable({
             dom : 'Bfrtip',
             buttons : [ 'excel' ],
             "paging" : false,
             "aaSorting": [ [6,'desc'] ],
             "columnDefs": [ {
                "targets": "_all",
                "orderable": false
             }]
         });

          $('#managerSearchTable_paginate,#hrSearchTable_paginate').hide();
                 $('#managerRollbackTable').DataTable({
                      dom : 'Bfrtip',
                      buttons : [ 'excel' ],
                      "aaSorting": [ [7,'desc'] ],
                      "paging": false,
                      "columnDefs": [ {
                            "targets": "_all",
                            "orderable": false
                      }]
                 });
                 $('#hrRollbackTable').DataTable({
                      dom : 'Bfrtip',
                      buttons : [ 'excel' ],
                      "aaSorting": [ [7,'desc'] ],
                      "paging": false,
                      "columnDefs": [ {
                            "targets": "_all",
                            "orderable": false
                      }]
                 });
                  $('.dataTables_info').hide();
   leadRole = $('#isTeamlead').val();
   var managerRole = $('#isManager').val();
   var hrRole = $('#isHR').val();
   var leadershipRole = $('#isLeader').val();
   var recruiterRole = $('#isRecruiter').val();
   var performanceRole = $('#isPerformance').val();
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
      $('.fn_AnnualDate').datepicker({
         dateFormat: "yy-mm-dd",
         minDate: new Date($('#financialNextMidDateYear').val()),
         maxDate: new Date($('#financialYearEnd').val()),
         onClose: function () {
            validateFinalYearTimelines();
         }
      });

      $('.fn_AnnualDate').datepicker('disable');
      $('.fn_midYearDate').datepicker({
         dateFormat: "yy-mm-dd",
         minDate: new Date($('#financialYearStart').val()),
         maxDate: new Date($('#financialMidYear').val()),
         onClose: function () {
            validateMidYearTimelines();
         }
      });


      $('.fn_midYearDate').datepicker('disable');
      $('#hrRole').show();
      if (leadRole == 'false' && managerRole == 'false') {
         $('#third-tab').addClass("active");
         $('#third').addClass("show active");
      }
   }
   if (performanceRole == 'true') {
      $('#performanceRole').show();
      if (leadRole == 'false' && managerRole == 'false' && hrRole == 'false') {
         $('#fourth-tab').addClass("active");
         $('#fourth').addClass("show active");
      }
   }

    $('[id^=managerRating]').change( function (e) {
                calculateFinalRating( 'manager', 'managerFinalRating');
           });
           $('[id^=hrRating]').change( function (e) {
                calculateFinalRating( 'hr', 'hrFinalRating');
           });
   var reviewType = $('#currentYrReview').val();
        if(reviewType == 'true'){
            calculateFinalRating( 'manager', 'managerFinalRating');
            calculateFinalRating( 'hr', 'hrFinalRating');

        }

});

function validateMidYearTimelines() {
   var valid = true;
   for (var x = 1; x < 3; x++) {
      var previousDateIndex = x - 1;
      var previousDateString = $("#midyearDate" + previousDateIndex).val();
      var currentDateString = $("#midyearDate" + x).val();
      var previousDate = new Date(previousDateString);
      var currentDate = new Date(currentDateString);
      if (previousDate.getTime() >= currentDate.getTime()) {
         valid = false;
      }
   }
   if (!valid) {
      $('#updateMidTimelineSubmit').prop('disabled', 'disabled');
      alertModal("Performance Admin : Incorrect date selected. Mid-year assessment dates should be ascending with not 2 dates same.");
   } else {
      $('#updateMidTimelineSubmit').prop('disabled', false);
   }
}

function validateFinalYearTimelines() {
   var valid = true;
   for (var x = 1; x < 5; x++) {
      var previousDateIndex = x - 1;
      var previousDateString = $("#finalyearDate" + previousDateIndex).val();
      var currentDateString = $("#finalyearDate" + x).val();
      var previousDate = new Date(previousDateString);
      var currentDate = new Date(currentDateString);
      if (previousDate.getTime() >= currentDate.getTime()) {
         valid = false;
      }
   }
   if (!valid) {
      $('#updateFinalTimelineSubmit').prop('disabled', 'disabled');
      alertModal("Performance Admin : Incorrect date selected. Annual assessment dates should be ascending with not 2 dates same.");
   } else {
      $('#updateFinalTimelineSubmit').prop('disabled', false);
   }
}

function updateFinalTimeline() {
   $('.fn_AnnualDate').datepicker('enable');
   $('#updateFinalTimelineButtons').show();
   $('#updateFinalTimelineAction').hide();
}



function updateMidTimeline() {
   $('.fn_midYearDate').datepicker('enable');
   $('#updateMidTimelineButtons').show();
   $('#updateMidTimelineAction').hide();
}








function onFocusBorder(obj) {
   $(".fn_AnnualDate ,.fn_midYearDate").each(function () {
      $(this).css("border", "none");
   });
   $(obj).css("border", "2px solid #0b5fff");
}

function isEmail(email) {
   var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
   if (!regex.test(email)) {
      $(".email-has-error ").text("Not a valid email address.");
      $("#assigneeEmail").val("");
      return false;
   } else if (!(email.includes("@trantorinc.com") || email.includes("@chd.trantorinc.com"))) {
      $(".email-has-error").text("Not a valid Trantor email address.");
      $("#assigneeEmail").val("");
      return false;
   } else {
      $(".email-has-error").text("");
      return true;
   }
}

function saveForm(val) {
   var form;
   if (val == 1) {
      form = $("#annualSubmissionForm");
   } else {
      form = $("#managerMidyearRatingForm");
   }
   var url = $('#saveFormDataResourceUrl').val();
   $.ajax({
      type: "POST",
      url: url,
      data: form.serialize(),
      success: function (data) {

         alertModal('Performance Admin:Form saved successfully');
      },
      error: function (data) {

         // Some error in ajax call
         alertModal('Performance Admin:some Error');
      }
   });
}
function saveHrForm(val) {
   var form;
   if (val == 1) {
      form = $("#annualSubmissionForm");
   } else {
      form = $("#managerMidyearRatingForm");
   }
   var url = $('#saveHrFormDataResourceUrl').val();
   $.ajax({
      type: "POST",
      url: url,
      data: form.serialize(),
      success: function (data) {

         alertModal('Performance Admin:Form saved successfully');
      },
      error: function (data) {

         // Some error in ajax call
         alertModal('Performance Admin:some Error');
      }
   });
}
function setYear(obj){
var href = $(obj).attr('href');
var hrefArray=href.split("year=");
var date=hrefArray[0]+"year="+$('#ayForHREntries').val();
$('#excel').attr("href",date);
}
function checkReviewerRole() {
   var reviewerEmail = $('#assigneeEmail').val();
   var reviewerEmailKey = $('#portletNamespace').val() + 'reviewerEmail';
   var postData = {
      [reviewerEmailKey]: reviewerEmail,
   };
   if (isEmail(reviewerEmail)) {
      var url = $('#getReviewerNameResourceUrl').val();
      AUI().use('aui-io-request', function (A) {
         A.io.request(url, {
            method: 'post',
            data: postData,
            on: {
               success: function () {
                  var employeeData = JSON.parse(this.get('responseData'));
                  if (employeeData.data != "" && employeeData.data != null) {
                     $("#assigneeButton").attr("disabled", false);
                  } else {
                     alertModal("Performance Admin:Performance:Not a valid Reviewer Email Id");
                  }
               }
            }
         });
      });
   }
}

function saveMidyearForm(obj) {
   var id = obj.id;
   if (id == "managerSave") {
      var urlForm = $('#setManagerFormResourceUrl').val().toString();
      $.ajax({
         url: urlForm,
         type: 'POST',
         form: {
            id: 'managerMidyearRatingForm'
         },
         on: {
            success: function () {
               alertModal("Performance Admin:Data saved successfully");
            }
         }
      });
   }

}

function submitMidyearForm(obj) {
   var id = obj.id;
   if (id == "managerReject") {
      $('#actionTaken').val(1);
      $('#managerFinalRating').prop('required', false);
      $('#apraiseeAchievements').prop('required', false);
      $('#improvementComment').prop('required', false);
      $('#managerOverallComment').prop('required', false);
      $('#rejectObjRsn').prop('required', true);
      $('#terms').prop('required', false);
      $('#terms').setCustomValidity('');
   } else {
      $('#actionTaken').val(2);
      $('#managerFinalRating').prop('required', 'required');
      $('#improvementComment').prop('required', 'required');
      $('#managerOverallComment').prop('required', 'required');
      $('#rejectObjRsn').prop('required', false);
      if ($('#undertakingDiv').is(':visible')) {
         $("#terms").required = true;
      }
   }
}

function collapseAll() {
   $("tr[id^='reviewLineId']").removeClass('show');
   return false;
}

function toggleOverlay() {
   var value = $('#overlay').css('opacity');
   if (value == '1')
      $('#overlay').css("opacity", "0.0");
   else
      $('#overlay').css("opacity", "1");
}

function expandAll() {
   $("tr[id^='reviewLineId']").addClass('show');
   return false;
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
function toggleReplacementDetail() {
   var replacementRadio = $('#replacementRadioDiv input[type=radio]:checked').val();
   if (replacementRadio == 'Yes') {
      $('#replacementDropdownDiv').show();
      $('#replacementDropdown').prop("required", "required");
      $('#replacementReasonDiv').hide();
      $('#replacementReason').prop("required", false);
      $('#replacementReasonDropdownDiv').show();
      $('#replacementReasonId').prop("required", "required");
      $('#replacementReasonId').val() == 'Other' ? $('#replacementOtherReason').prop('required', "required") : $('#replacementOtherReason').prop('required', false);
   } else if (replacementRadio == 'No') {
      $('#replacementDropdownDiv').hide();
      $('#replacementDropdown').prop("required", false);
      $('#replacementReasonDiv').show();
      $('#replacementReason').prop("required", "required");
      $('#replacementReasonDropdownDiv').hide();
      $('#replacementReasonId').prop("required", false);
      $('#replacementOtherReason').prop('required', false);
   } else {
      $('#replacementDropdownDiv').hide();
      $('#replacementDropdown').prop("required", false);
      $('#replacementReasonDiv').hide();
      $('#replacementReason').prop("required", false);
      $('#replacementReasonDropdownDiv').hide();
      $('#replacementReasonId').prop("required", false);
      $('#replacementOtherReason').prop('required', false);
   }
}
$('#promotionReqd').change(function () {
   if (this.checked) {
      $('#promotionRequiredBody').show();
      $('#excelledAreaAns').prop('required', 'required');
      $('#rolePlayedAns').prop('required', 'required');
      $('#reasonRecommendAns').prop('required', 'required');
      $('#readyAns').prop('required', 'required');
   } else {
      $('#promotionRequiredBody').hide();
      $('#excelledAreaAns').prop('required', false);
      $('#rolePlayedAns').prop('required', false);
      $('#reasonRecommendAns').prop('required', false);
      $('#readyAns').prop('required', false);
   }
});

function confirmMidyearWithUser(obj) {
   var action = $('#actionTaken').val();
   if (action == 1) {
      obj.action = $('#rejectReviewLinesResourceUrl').val();
      event.preventDefault();
      confirmModal('Performance Admin:Are you sure you want employee to update KPIs ?', 'managerMidyearRatingForm', null);
   } else {
      obj.action = $('#submitManagerReviewResourceUrl').val();
      if (leadRole === true) {
         event.preventDefault();
         confirmModal("Performance Admin:Are you sure you want to submit the review, Review form once submitted will be moved to the Manager view and no longer visible to lead.", 'managerMidyearRatingForm', null);
      } else {
         event.preventDefault();
         confirmModal("Performance Admin:Are you sure you want to submit reviews for this review ?", 'managerMidyearRatingForm', null);
      }
   }
}

function confirmWithUser(obj) {
   var action = $('#actionTaken').val();
   var totalCount = $("#managerReviewBody tr").length / 2;
   if (action == 1) {
      obj.action = $('#rejectReviewLinesResourceUrl').val();
      event.preventDefault();
      confirmModal('Performance Admin:Are you sure you want employee to update KPIs ?', 'annualSubmissionForm', null);
   } else if (action == 2) {
      var invalidForm = false;
      for (i = 0; i < totalCount; i++) {
         if ($('#managerComment' + i).val() == '') {
            invalidForm = true;
            break;
         }
      }
      if (invalidForm) {
         alertModal("Performance Admin:Please provide manager comments for all KPIs.");
         var expanding = expandAll();
         window.scrollTo(0, 0);
         return expanding;
      } else {
         obj.action = $('#submitManagerReviewResourceUrl').val();
         if (leadRole == 'true') {
            event.preventDefault();
            confirmModal("Performance Admin:Are you sure you want to submit the review, Review form once submitted will be moved to the Manager view and no longer visible to lead.", 'annualSubmissionForm', null);
         } else {
            event.preventDefault();
            confirmModal("Performance Admin:Are you sure you want to submit reviews for this review ?", 'annualSubmissionForm', null);
         }
      }
   } else {
      obj.action = $('#submitHrReviewResourceUrl').val();
      event.preventDefault();
      confirmModal('Performance Admin:Are you sure you want to submit reviews for this review ?', 'annualSubmissionForm', null);
   }
}

function submitForm(obj) {
   var primaryAccount = $('#primaryAccount').val();

   var id = obj.id;
   var totalCount = $("#managerReviewBody tr").length / 2;
   if (id == "managerReject") {
      $('#actionTaken').val(1);
      if ($('#reviewState').val() > 1) {
         for (i = 0; i < totalCount; i++) {
            $('#managerRating' + i).prop('required', false);
         }
         $('#managerOverallComment').prop('required', false);
         $('.fn_criticalToAcct').prop("required", false);
         $('.fn_criticalToCompany').prop("required", false);
         if (primaryAccount == 'true') {
            $('.fn_replacement').prop("required", false);
            $('#replacementDropdown').prop("required", false);
            $('#replacementReason').prop("required", false);
         }
         $('#terms').prop('required', false);
         document.getElementById('terms').setCustomValidity('');
         $('#excelledAreaAns').prop('required', false);
         $('#rolePlayedAns').prop('required', false);
         $('#reasonRecommendAns').prop('required', false);
         $('#improvementComment').prop('required', false);
         $('#readyAns').prop('required', false);
      }
      $('#rejectObjRsn').prop('required', true);
   } else if (id == "managerSubmit") {
      $('#actionTaken').val(2);
      for (i = 0; i < totalCount; i++) {
         $('#managerRating' + i).prop('required', 'required');
      }
      $('#improvementComment').prop('required', 'required');
      $('#managerOverallComment').prop('required', 'required');
      $('.fn_criticalToAcct').prop("required", "required");
      $('.fn_criticalToCompany').prop("required", "required");
      if (primaryAccount == 'true') {
         $('.fn_replacement').prop("required", "required");
      }
      $('#rejectObjRsn').prop('required', false);
      if ($('#undertakingDiv').is(':visible')) {
         $('#terms').prop('required', 'required');
      }
   } else {
      $('#actionTaken').val(3);
      $('#hrOverallComments').prop('required', 'required')
   }
}

function getRollbackRequestStatus() {
   var url = $('#getRollbackRequestStatusEntriesResourceUrl').val();
   $('#rollbackSearchTable').DataTable().destroy();
   $('#rollbackSearchTableBody').empty();
   $('#rollbackSearchTable').hide();
   var rollbackRequestSearchKey = $('#portletNamespace').val() + 'rollbackRequestSearch';
   var postData = {
      [rollbackRequestSearchKey]: $('#rollbackRequestSearch').val(),
   };
   AUI().use('aui-io-request', function (A) {
      A.io.request(url, {
         method: 'post',
         data: postData,
         on: {
            success: function () {
               var reviewData = JSON.parse(this.get('responseData'));
               if (null != reviewData && reviewData != '' && reviewData.data.length > 0) {
                  var tableRow = '';
                  for (var x = 0; x < reviewData.data.length; x++) {
                     tableRow += '<tr><td>' + reviewData.data[x].employeeCode + '</td><td>' + reviewData.data[x].employeeName + '</td><td>' + reviewData.data[x].account + '</td><td>' + reviewData.data[x].reviewType + '</td><td>' + reviewData.data[x].accountType + '</td><td>' + reviewData.data[x].reviewStateName + '</td><td>' + reviewData.data[x].reviewStartDate + '</td><td>' + reviewData.data[x].rollbackStatus + '</td></tr>';
                  }
                  $('#rollbackSearchTableBody').append(tableRow);
                  $('#rollbackSearchTable').DataTable({
                     dom: 'Bfrtip',
                     buttons: ['excel'],
                     "aaSorting": [
                        [7, 'asc']
                     ],
                     "paging": false,
                     "columnDefs": [{
                        "targets": "_all",
                        "orderable": false
                     }]
                  });
                  $('#rollbackSearchTable').show();
                  $('#rollbackCardSearchTableDiv').show();
                  $('#rollbackSearchTable_info').hide();
               } else {
                  alertModal("Performance Admin:No review found for selected status");
               }
            }
         }
      });
   });
}

function submitDownloadAction() {
   if ($("#ayForHREntries").val() === null || $("#ayForHREntries").val() == '') {
      alertModal("Performance Admin:Please select a year");
      return false;
   } else {
      var url = $('#downloadHrYearWiseReportResourceUrl').val();
      var dateSearchKey = $('#portletNamespace').val() + 'forDate';
      var postData = {
         [dateSearchKey]: $('#ayForHREntries').val(),
      };
      AUI().use('aui-io-request', function (A) {
         A.io.request(url, {
            method: 'post',
            data: postData,
            on: {
               success: function () {
                  alert("yes");
               }
            }
         });
      });

   }
}

function getCloseRequestStatus() {
   var url = $('#getCloseRequestStatusEntriesResourceUrl').val();
   $('#closeSearchTable').DataTable().destroy();
   $('#closeSearchTableBody').empty();
   $('#closeSearchTable').hide();
   var closeRequestSearchKey = $('#portletNamespace').val() + 'closeRequestSearch';
   var postData = {
      [closeRequestSearchKey]: $('#closeRequestSearch').val(),
   };
   AUI().use('aui-io-request', function (A) {
      A.io.request(url, {
         method: 'post',
         data: postData,
         on: {
            success: function () {
               var reviewData = JSON.parse(this.get('responseData'));
               if (null != reviewData && reviewData != '' && reviewData.data.length > 0) {
                  var tableRow = '';
                  for (var x = 0; x < reviewData.data.length; x++) {
                     tableRow += '<tr><td>' + reviewData.data[x].employeeCode + '</td><td>' + reviewData.data[x].employeeName + '</td><td>' + reviewData.data[x].account + '</td><td>' + reviewData.data[x].reviewType + '</td><td>' + reviewData.data[x].accountType + '</td><td>' + reviewData.data[x].reviewStateName + '</td><td>' + reviewData.data[x].reviewStartDate + '</td><td>' + reviewData.data[x].closeStatus + '</td></tr>';
                  }
                  $('#closeSearchTableBody').append(tableRow);
                  $('#closeSearchTable').DataTable({
                     dom: 'Bfrtip',
                     buttons: ['excel'],
                     "aaSorting": [
                        [7, 'asc']
                     ],
                     "paging": false,
                     "columnDefs": [{
                        "targets": "_all",
                        "orderable": false
                     }]
                  });
                  $('#closeSearchTable').show();
                  $('#closeCardSearchTableDiv').show();
                  $('#closeSearchTable_info').hide();
               } else {
                  alertModal("Performance Admin:No review found for selected status");
               }
            }
         }
      });
   });
}

function submitRollbackRequest() {
   var checkboxStatus = false;
   if ($('input[id^="managerCheckbox"]:checked').length > 0) {
      checkboxStatus = true;
   }
   if (!checkboxStatus) {
      alertModal("Performance Admin:Please select at least one review to continue");
      return false;
   } else {
      event.preventDefault();
      confirmModal("Performance Admin:Are you sure you want to request these reviews for rollback to HR ?", 'rollbackRequestForm', null);
   }
}

function submitCloseRequest() {
   var checkboxStatus = false;
   if ($('input[id^="managerCheckboxClose"]:checked').length > 0) {
      checkboxStatus = true;
   }
   if (!checkboxStatus) {
      alertModal("Performance Admin:Please select at least one review to continue");
      return false;
   } else {
       event.preventDefault();
      confirmModal("Performance Admin:Are you sure you want to send the Review Form closure requests to HR ?", 'closeRequestForm', null);
   }
}

function filterManagerSearchTable() {
   var selectedClass = 'M' + $('#managerSearchYear').val() + $('#managerSearchType').val();
   $('#managerSearchTableBody').children().hide();
   var totalApplicableRowsCount = $('#managerSearchTableBody tr[class*="' + selectedClass + '"]').length;
   if (($('#managerSearchYear').val() == '') || ($('#managerSearchType').val() == '')) {
      $('#managerSearchTableDiv').hide();
      $('#managerSearchTableBody').children().show();
   } else if (totalApplicableRowsCount > 0) {
      $('#managerSearchTableBody tr[class*="' + selectedClass + '"]').show();
      $('#managerSearchTableDiv').show();
   } else {
      $('#managerSearchTableDiv').hide();
      $('#managerSearchTableBody').children().show();
      alertModal('Performance Admin:No match found with given filters');
   }
}

function getEmployeeReview(obj) {
   var nameSpace = $('#portletNamespace').val();
   var ecode = $('#overrideId').val().trim();
   $("#kpiHeader").hide();
   $("#reviewHeader").hide();
   $('#hrBlockTableBody').empty();
   $('#hrBlockKpiTableBody').empty();
   $("#overrideCardBody").hide();
   if (ecode != '' && ecode.length < 3) {
      alertModal("Please provide atleast 3 characters for search");
      return false;
   } else {
      var empty = false;
      $(obj).attr('disabled', 'disabled');
      var ecodeKey = $('#portletNamespace').val() + 'ecode';
      var postData = {
         [ecodeKey]: ecode,
      };
      AUI().use('aui-io-request', function (A) {
         A.io.request($('#getEmployeeEntriesByHrResourceUrl').val(), {
            method: 'post',
            data: postData,
            on: {
               success: function () {
                  var overrideData = JSON.parse(this.get('responseData'));
                  if (null != overrideData && overrideData != '' && null != overrideData.data.reviewList && overrideData.data.reviewList != '' && overrideData.data.reviewList.length > 0) {
                     var tableRow = '';
                     for (var x = 0; x < overrideData.data.reviewList.length; x++) {
                        var closed = "";
                        if (overrideData.data.reviewList[x].reviewState == 6) {
                           closed = "disabled";
                        }
                        tableRow += '<tr><td class="text-center">' + overrideData.data.reviewList[x].employeeName + '</td><td class="text-center">' + overrideData.data.reviewList[x].account + '</td><td class="text-center">' + overrideData.data.reviewList[x].managerName + '</td><td class="text-center">' + overrideData.data.reviewList[x].reviewType + '</td><td class="text-center">' + overrideData.data.reviewList[x].accountType + '</td><td class="text-center">' + overrideData.data.reviewList[x].reviewStateName + '</td><td class="text-center"><form id="closeReviewForm" method="post" onsubmit="return closeReviewFormByHR(this)"  style="display: inline"> <input type="hidden" value="' + overrideData.data.reviewList[x].reviewId + '" name="' + nameSpace + 'reviewId" /><button type="submit" title="Close" ' + closed + ' class="btn btn-outline-danger btn-sm ml-2" style="cursor: pointer;"> <i class="far fa-window-close"></i> </button></form></td></tr>';
                     }
                     $('#hrBlockTableBody').append(tableRow);
                     $("#reviewHeader").show();
                     $("#overrideCardBody").show();
                  } else {
                     empty = true;
                  }

                  if (null != overrideData && overrideData != '' && null != overrideData.data.kpiList && overrideData.data.kpiList != '' && overrideData.data.kpiList.length > 0) {
                     var tableRow = '';
                     for (var x = 0; x < overrideData.data.kpiList.length; x++) {
                        var reviewFinal = "";
                        if (!overrideData.data.kpiList[x].overrideActionButtonEnable) {
                           reviewFinal = "disabled";
                        }
                        tableRow += '<tr><td class="text-center">' + overrideData.data.kpiList[x].employeeName + '</td><td class="text-center">' + overrideData.data.kpiList[x].accountName + '</td><td class="text-center">' + overrideData.data.kpiList[x].managerName + '</td><td class="text-center">' + overrideData.data.kpiList[x].accountType + '</td><td class="text-center">' + overrideData.data.kpiList[x].kpiSettingStatusString + '</td><td class="text-center"><form id="allowSelfReview_'+ overrideData.data.kpiList[x].kpiId+'" onsubmit="return submitSelfReviewFormByHR(this,'+ overrideData.data.kpiList[x].kpiId+')" method="post" style="display: inline"> <input type="hidden" value="' + overrideData.data.kpiList[x].kpiId + '" name="' + nameSpace + 'kpiId" /><button type="submit" ' + reviewFinal + '  class="btn btn-outline-primary btn-sm" title="Generate Review Form" style="cursor: pointer;"> <i class="fas fa-vote-yea"></i> </button></form></td></tr>';
                     }
                     $('#hrBlockKpiTableBody').append(tableRow);
                     $("#kpiHeader").show();
                     $("#overrideCardBody").show();
                  } else {
                     empty = true;
                  }
                  if (empty) {
                     alertModal("Performance Admin:No KPIs & Reviews found for ecode " + ecode);
                  }

               }
            }
         });
      });
      $(obj).attr('disabled', false);
   }
}

function submitSelfReviewFormByHR(obj,id) {
   obj.action = $('#allowSelfReviewResourceUrl').val();
   event.preventDefault();
   confirmModal('Performance Admin:Are you sure you want to allow employee to generate review form out of permitted time window for further review process ?', 'allowSelfReview_'+id, null);
}

function closeReviewFormByHR(obj) {
   obj.action = $('#closeReviewFormResourceUrl').val();
   event.preventDefault();
   confirmModal('Performance Admin:Are you sure to close this review. This action is irrevocable ?', 'closeReviewForm', null);
}

function submitRollbackActionByHR() {
   var checkboxStatus = false;
   if ($('input[id^="hrCheckbox"]:checked').length > 0) {
      checkboxStatus = true;
   }
   if (!checkboxStatus) {
      alertModal("Performance Admin:Please select at least one review to continue");
      return false;
   } else {
      var action = $('#actionTaken').val();
      event.preventDefault();
      if (action == 1) {
         confirmModal('Performance Admin:Are you sure you want approve selected rollback request ?', 'hrRollbackForm', null);
      } else {
         confirmModal('Performance Admin:Are you sure you want to reject selected rollback request ?', 'hrRollbackForm', null);
      }
   }
}

function actionOnRollbackByHr(obj) {
   var id = obj.id;
   if (id == "rollbackApprove") {
      $('#actionTaken').val(1);
   } else {
      $('#actionTaken').val(2);
   }
}

function submitCloseActionByHR() {
   var checkboxStatus = false;
   if ($('input[id^="hrCloseCheckbox"]:checked').length > 0) {
      checkboxStatus = true;
   }
   if (!checkboxStatus) {
      alertModal("Performance Admin:Please select at least one review to continue");
      return false;
   } else {
      var action = $('#actionTakenOnClose').val();
      event.preventDefault();
      if (action == 1) {
         confirmModal('Performance Admin:Are you sure you want approve selected close request ?', 'hrCloseForm', null);

      } else {
         confirmModal('Performance Admin:Are you sure you want to reject selected close request ?', 'hrCloseForm', null);

      }
   }
}

function actionOnCloseByHr(obj) {
   var id = obj.id;
   if (id == "closeApprove") {
      $('#actionTakenOnClose').val(1);
   } else {
      $('#actionTakenOnClose').val(2);
   }
}

function filterHrSearchTable() {
   var selectedClass = 'H' + $('#hrSearchAccount').val() + $('#hrSearchYear').val() + $('#hrSearchType').val();
   $('#hrSearchTableBody').children().hide();
   var totalApplicableRows = $('#hrSearchTableBody tr[class*="' + selectedClass + '"]').length;
   if (($('#hrSearchAccount').val() == '') || ($('#hrSearchYear').val() == '') || ($('#hrSearchType').val() == '')) {
      $('#hrSearchTableDiv').hide();
      $('#hrSearchTableBody').children().show();
   } else if (totalApplicableRows > 0) {
      $('#hrSearchTableBody tr[class*="' + selectedClass + '"]').show();
      $('#hrSearchTableDiv').show();
   } else {
      $('#hrSearchTableDiv').hide();
      $('#hrSearchTableBody').children().show();
      alertModal('Performance Admin:No match found with given filters');
   }
}



function rollbackReviewFormSubmission() {
   event.preventDefault();
   confirmModal('Performance Admin:Are you sure you want to rollback this review ?', 'rollbackReviewForm', null);
}

function assigneeFormSubmission() {
   event.preventDefault();
   confirmModal('Performance Admin:Are you sure you want to assign this review ?', 'assigneeReviewForm', null);
}
function getManagerYearlyEntries(val) {
var role;
var date;
if(val ==1 ){
   $('#ayForManagerEntriesTable').DataTable().destroy();
   $('#ayForManagerEntriesDiv').hide();
   $('#ayForManagerEntriesTableBody').empty();
   role ='Trantor_Manager';
   date =$('#ayForManagerEntries').val();
}else{
   $('#ayForAdminEntriesTable').DataTable().destroy();
   $('#ayForAdminEntriesDiv').hide();
   $('#ayForAdminEntriesTableBody').empty();
   role ='Trantor_Performance';
   date =$('#ayForAdminEntries').val();
}

   var dateKey = $('#portletNamespace').val() + 'forDate';
   var postData = {
      [dateKey]:date,
   };
   AUI().use('aui-io-request', function (A) {
      A.io.request($('#getManagerYearlyEntriesResourceUrl').val(), {
         method: 'post',
         data: postData,
         on: {
            success: function () {
               var reviewData = JSON.parse(this.get('responseData'));
               if (null != reviewData && reviewData != '' && reviewData.data.length > 0) {
                  var tableRow = '';
                  for (var x = 0; x < reviewData.data.length; x++) {
                     var colorClass = 'black';
                     if (reviewData.data[x].reviewState == 5) {
                        colorClass = 'green';
                     } else if (reviewData.data[x].reviewState == 6) {
                        colorClass = 'red';
                     }
                     tableRow += '<tr><td>' + reviewData.data[x].employeeCode + '</td><td>' + reviewData.data[x].employeeName + '</td><td>' + reviewData.data[x].reviewType + '</td><td>' + reviewData.data[x].accountType + '</td><td><div style="color: ' + colorClass + '">' + reviewData.data[x].reviewStateName + '</div></td><td><form action="' + $('#reviewWorkflowResourceUrl').val() + '" method="post" style="display: inline"><input type="hidden" value="' + reviewData.data[x].reviewId + '" name="' + $('#portletNamespace').val() + 'reviewId" /><input type="hidden" value='+role+' name="' + $('#portletNamespace').val() + 'role" /><button type="submit" title="Go to Review Form" class=" btn btn-outline-primary btn-sm" style="cursor: pointer;">  View </button></form></td></tr>';
                  }
                       if(val ==1){
                    $('#ayForManagerEntriesTableBody').append(tableRow);
                    $('#ayForManagerEntriesTable').DataTable({
                       dom: 'Bfrtip',
                       buttons: ['excel'],
                       "aaSorting": [
                          [0, 'asc'],
                          [2, 'desc'],
                          [3, 'asc']
                       ],
                       "paging": false,
                       "columnDefs": [{
                          "targets": "_all",
                          "orderable": false
                       }]
                    });
                    $('#ayForManagerEntriesDiv').show();
                                    } else {
                                       $('#ayForAdminEntriesTableBody').append(tableRow);
                                                      $('#ayForHREntriesTable').DataTable({
                                                        dom: 'Bfrtip',
                                                                             buttons: ['excel'],
                                                                             "aaSorting": [
                                                                                [0, 'asc'],
                                                                                [2, 'desc'],
                                                                                [3, 'asc']
                                                                             ],
                                                                             "paging": false,
                                                                             "columnDefs": [{
                                                                                "targets": "_all",
                                                                                "orderable": false
                                                                             }]
                                                      });
                                                      $('#ayForAdminEntriesDiv').show();
                                    }

               } else {
                  alertModal("Performance Admin:No review found for selected Review Year");
               }
            }
         }
      });
   });
}
function getHRYearlyEntries(val) {
   $('#ayForHREntriesTable').DataTable().destroy();
   $('#ayForHREntriesDiv').hide();
   $('#ayForHREntriesTableBody').empty();
   var dateKey = $('#portletNamespace').val() + 'forDate';
   var postData = {
      [dateKey]: $('#ayForHREntries').val(),
   };
   AUI().use('aui-io-request', function (A) {
      A.io.request($('#getHRYearlyEntriesResourceUrl').val(), {
         method: 'post',
         data: postData,
         on: {
            success: function () {
               var reviewData = JSON.parse(this.get('responseData'));
               if (null != reviewData && reviewData != '' && reviewData.data.length > 0) {
                  var tableRow = '';
                  for (var x = 0; x < reviewData.data.length; x++) {
                     var colorClass = 'black';
                     if (reviewData.data[x].reviewState == 5) {
                        colorClass = 'green';
                     } else if (reviewData.data[x].reviewState == 6) {
                        colorClass = 'red';
                     }
                     tableRow += '<tr><td>' + reviewData.data[x].employeeCode + '</td><td>' + reviewData.data[x].employeeName + '</td><td>' + reviewData.data[x].reviewType + '</td><td>' + reviewData.data[x].accountType + '</td><td><div style="color: ' + colorClass + '">' + reviewData.data[x].reviewStateName + '</div></td><td><form action="' + $('#reviewWorkflowResourceUrl').val() + '" method="post" style="display: inline"><input type="hidden" value="' + reviewData.data[x].reviewId + '" name="' + $('#portletNamespace').val() + 'reviewId" /><input type="hidden" value="Trantor_HR" name="' + $('#portletNamespace').val() + 'role" /><button type="submit" title="Go to Review Form" class=" btn btn-outline-primary btn-sm" style="cursor: pointer;">  View </button></form></td></tr>';
                  }
                  $('#ayForHREntriesTableBody').append(tableRow);
                  $('#ayForHREntriesTable').DataTable({
                     dom: 'Bfrtip',
                     buttons: ['excel'],
                     "aaSorting": [
                        [0, 'asc'],
                        [2, 'desc'],
                        [3, 'asc']
                     ],
                     "paging": false,
                     "columnDefs": [{
                        "targets": "_all",
                        "orderable": false
                     }]
                  });
                  $('#ayForHREntriesDiv').show();
               } else {
                  alertModal("Performance Admin:No review found for selected Review Year");
               }
            }
         }
      });
   });
}
  function calculateFinalRating(reviewer, target)
    {
        var kpiSum =0;
        var attrSum=0;
        var selectorId = reviewer+"Rating";
        var totalCount =  $("#managerReviewBody tr").length/2;
        var kpiCount = totalCount-7;
        var attrCount = 7;
        for(var x=0; x<kpiCount; x++){
            var instantRating = $('#'+selectorId+x).val();
            switch (instantRating) {
                  case "A":
                  case "Consistently Exceeds Expectations":
                      kpiSum = kpiSum + 5;
                    break;
                  case "B":
                  case "Sometimes Exceeds Expectations":
                      kpiSum = kpiSum + 4;
                    break;
                  case "C":
                  case "Consistently Meets Expectations":
                      kpiSum = kpiSum + 3;
                    break;
                  case "D":
                  case "Inconsistently Meets Expectations":
                      kpiSum = kpiSum + 2;
                    break;
                  case "E":
                  case "Does not Meet Expectations":
                      kpiSum = kpiSum + 1;
                    break;
            }
        }
        kpiSum = (kpiSum/kpiCount)*0.7;
        for(var x=kpiCount; x<totalCount; x++){
            var instantRating = $('#'+selectorId+x).val();
            switch (instantRating) {
                  case "A":
                  case "Consistently Exceeds Expectations":
                      attrSum = attrSum + 5;
                    break;
                  case "B":
                  case "Sometimes Exceeds Expectations":
                      attrSum = attrSum + 4;
                    break;
                  case "C":
                  case "Consistently Meets Expectations":
                      attrSum = attrSum + 3;
                    break;
                  case "D":
                  case "Inconsistently Meets Expectations":
                      attrSum = attrSum + 2;
                    break;
                  case "E":
                  case "Does not Meet Expectations":
                      attrSum = attrSum + 1;
                    break;
            }
        }
        attrSum = (attrSum/7)*0.3;
        var totalSum = attrSum+kpiSum;
        $('#'+target).val(totalSum.toFixed(2));
    }