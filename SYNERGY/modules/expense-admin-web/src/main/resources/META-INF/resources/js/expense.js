$(document).ready(function () {
   var financeRole = $('#isFinance').val();
   var managerRole = $('#isManager').val();
   $('#managerRole').hide();
   $('#financeRole').hide();

   if (managerRole == 'true') {
      $('#managerRole').show();
      $('#first-tab').addClass("active");
      $('#first').addClass("show active");

   }
   if (financeRole == 'true') {
      $('#financeRole').show();
      if (managerRole == 'false') {
         $('#second-tab').addClass("active");
         $('#second').addClass("show active");
      }
   }
   $('#managerEditableTable,#managerViewTable,#financeEditableTable,#financeViewTable').dataTable({
      "order": [
         [0, 'desc'],
      ],
      "lengthChange": false,
      "pageLength": 10,
      "columnDefs": [{
         "targets": "_all",
         "orderable": false
      }]
   });
});

function downloadUserManual(val) {
      var url = $('#downloadUserManualResourceUrl').val();
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

function confirmSubmitDetails() {
  event.preventDefault();
  confirmModal('Expense Admin:Are you sure you want to submit the details ?','expenseAdminForm',null);
}

function submitForm(action) {
   if (action == 1 && $('#noResubmitCheckbox').is(':checked')) {
      alertModal("Expense:'No ReSubmit' scenario is only applicable for 'Reject' cases, when there are Duplicate/NA bills. With enabling 'No Resubmit', Employee won't be able to resubmit the expense.");
      return false;
   } else {
      var val = action == 1 ? "Approved" : "Rejected";
      $("#action").val(val);
      return true;
   }
}

function downloadBill() {
   var expenseType = $('#expenseType').val();
   var url = $('#downloadExpenseBillResourceUrl').val();
   var fileIdKey = $('#portletNamespace').val() + 'fileId';
   var postData = {
      [fileIdKey]: $('#fileId').val()
   };
   AUI().use('aui-io-request', function (A) {
      A.io.request(url, {
         method: 'post',
         data: postData,
         on: {
            success: function () {
               var headerData = JSON.parse(this.get('responseData'));
               const blob = convertBase64toBlob(headerData.data, 'application/octet-stream');
               let file = document.createElement("a");
               file.href = URL.createObjectURL(blob);
               file.download = expenseType + '.zip';
               document.body.appendChild(file);
               file.click();
            }
         }
      });
   });
}