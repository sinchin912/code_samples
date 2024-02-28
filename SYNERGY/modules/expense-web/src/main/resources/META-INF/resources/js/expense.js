
$(document).ready(function () {
   $('#expenseTable').dataTable({
      "order": [
         [0, 'desc'],
      ],
      "lengthChange": false,
      "pageLength": 20,
      "columnDefs": [{
         "targets": "_all",
         "orderable": false
      }]
   });
   $('.fn_text-trim').on('blur', function () {
      var trimValue = $(this).val().trim();
      $(this).val(trimValue);
   });
   $('#marriageDate').datepicker({
      dateFormat: "yy-mm-dd",
      minDate: $("#fyStartDate").val(),
      maxDate: -1,
      changeMonth: true,
      changeYear: true,


   });
   $('.fn_startDateDatepicker,#dobBaby').datepicker({
      dateFormat: "yy-mm-dd",
      minDate: $("#fyStartDate").val(),
      maxDate: 0,
      changeMonth: true,
      changeYear: true,

   });
   $('#dobSpouse').datepicker({
      dateFormat: "yy-mm-dd",
      maxDate: 0,
      changeMonth: true,
      changeYear: true,
      yearRange: '-60:+0',
   });
   $('.fn_endDateDatepicker').datepicker({
      dateFormat: "yy-mm-dd",
      minDate: 0,
      maxDate: 0,
      changeMonth: true,
      changeYear: true,

   });
   if($('#expenseId').val() == '0'){
   $('.fn_startDateDatepicker,.fn_endDateDatepicker').datepicker("setDate", new Date());
   }
   $(".fn_startDateDatepicker").on("change", function () {
      var ids = $(this).attr('id');
      validateDate(ids);
   });

    $(".custom-file-input").on("change", function () {
       var fileInput = $('.fn_upload-file');
       var fileName = $(this).val().split("\\").pop();
       var ext = fileName.substr(fileName.lastIndexOf('.'));
       if (ext == '.zip') {
          if (fileInput.get(0).files.length) {
             var fileSize = fileInput.get(0).files[0].size; // in bytes 1024 1024 30
             if (fileSize > 31457280) {
                alertModal('Expense:File size is more than 30 MB');
                return false;
             } else {
                $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
             }
          }

       } else {
          alertModal('Expense:Only .zip file can be uploaded.');
          $(this).siblings('.custom-file-label').html("Choose file");
          $(this).val(null);
       }
    });
});

function validateDate(ids) {
   var num = ids.charAt(ids.length - 1);
   var startDate = $("#startDate" + num).val();
      $("#endDate" + num).datepicker('destroy');
      $("#endDate" + num).datepicker({
         dateFormat: "yy-mm-dd",
         minDate: startDate,
         maxDate: 0,
         changeMonth: true,
         changeYear: true,
      });
}
$('#selectExpenseType').change(function () {
   $('#totalAmount').val("");
   $("#terms").prop("checked", false);
   $("#empTable").show();
   $("#uploadZipDiv").show();
   $("#addNewRow").show();
   $("#covidDiv").hide();
   $("#managerLabelDiv").hide();
   $("#managerOptionDiv").hide();
   $("#covidDiv").hide();
   $("#marriageExpense").hide();
   $("#childBirthExpense").hide();
   $("#approvingManager").prop("required", false);
   $("#spouseName").prop("required", false);
   $("#dobSpouse").prop("required", false);
   $("#marriageDate").prop("required", false);
   $("#selectGender").prop("required", false);
   $("#babyName").prop("required", false);
   $("#dobBaby").prop("required", false);
   $("#selectBabyGender").prop("required", false);
   $("#uploadZip").prop("required", true);
   var totalRows = $('#expItemsTable tr').length;
   for (var i = 1; i <= totalRows; i++) {
      $("#empDesc" + i).prop("required", true);
      $("#amount" + i).prop("required", true);
   }
   if ($('#selectExpenseType').val() == "Marriage Gift Card" || $('#selectExpenseType').val() == "Child Birth Gift Card") {
      $("#managerLabelDiv").show();
      $("#managerOptionDiv").show();
      $("#approvingManager").prop("required", true);
      $("#uploadZip").prop("required", false);
      $("#empTable").hide();
      $("#uploadZipDiv").hide();
      $("#uploadZip").prop("required", false);
      $("#tcBill").hide();
      $('#expenseForm').attr("enctype",false);
      for (var i = 1; i <= totalRows; i++) {
         $("#empDesc" + i).prop("required", false);
         $("#amount" + i).prop("required", false);
      }
      if ($('#selectExpenseType').val() == "Marriage Gift Card") {
         $('#totalAmount').val(2000);
         $('#spouseName').prop("required", true);
         $('#dobSpouse').prop("required", true);
         $('#marriageDate').prop("required", true);
         $('#selectGender').prop("required", true);
         $("#marriageExpense").show();
         $("#childBirthExpense").hide();
      } else {
         $('#totalAmount').val(1000);
         $('#babyName').prop("required", true);
         $('#selectBabyGender').prop("required", true);
         $('#dobBaby').prop("required", true);
         $("#marriageExpense").hide();
         $("#childBirthExpense").show();
      }
   } else {
      calculatedSum();
      $("#tcBill").show();
      $("#managerLabelDiv").show();
      $("#managerOptionDiv").show();
      $("#approvingManager").prop("required", true);
      $('#expenseForm').prop("enctype","multipart/form-data");
      if (($('#selectExpenseType').val() == "Birthday Celebrations") || ($('#selectExpenseType').val() == "Covid 19 Vaccination Reimbursement - I") || ($('#selectExpenseType').val() == "Covid 19 Vaccination Reimbursement - II")) {
         $("#managerLabelDiv").hide();
         $("#managerOptionDiv").hide();
         $("#approvingManager").prop("required", false);
         if (($('#selectExpenseType').val() == "Covid 19 Vaccination Reimbursement - I") || ($('#selectExpenseType').val() == "Covid 19 Vaccination Reimbursement - II")) {
            $("#addNewRow").hide();
            $("#covidDiv").show();
         }
      }
   }
});

function setValue() {
   var expType = $('#selectExpenseType').val();
   if(expType == 'Child Birth Gift Card' ||  expType == 'Marriage Gift Card'){
   $("#totalRows").val(1);
   }else{
   var totalRows = $('#expItemsTable tr').length;
   $("#totalRows").val(totalRows);
   }
}

function downloadUserManual() {
   var url = $('#downloadUserManualResourceUrl').val();
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

function downloadBill() {
   var expType = $('#selectExpenseType').val();
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
               file.download = expType + '.zip';
               document.body.appendChild(file);
               file.click();
            }
         }
      });
   });
}
function isAlfa(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode == 32 || charCode == 44 || charCode == 46 || (charCode >=65 &&  charCode <= 90) || (charCode >=97 && charCode <=122 )) {
        return true;
    }
    return false;
}

function removeRowAndResetIndex(obj) {
   var namespace = $('#portletNamespace').val();
   var index = $(obj).attr('id');
   var val = parseInt(index.substring(3));
   var child = $(obj).closest('tr').nextAll();
   child.each(function () {
      $(this).find('td').eq(0).find('button').attr('id', 'exp' + val);
      $(this).find('td').eq(1).find('input').attr('id', 'num' + val);
      $(this).find('td').eq(1).find('input').attr('value', val);
      $(this).find('td').eq(2).find('input').attr('id', 'startDate'+val);
      $(this).find('td').eq(2).find('input').attr('name',namespace+'startDate'+val);
      $(this).find('td').eq(3).find('input').attr('id', 'endDate'+val);
      $(this).find('td').eq(3).find('input').attr('name',namespace+'endDate'+val);
      $(this).find('td').eq(4).find('textarea').attr('id', 'empDesc'+val);
      $(this).find('td').eq(4).find('textarea').attr('name',namespace + 'empDesc'+val);
      $(this).find('td').eq(5).find('input').attr('id', 'amount'+val);
      $(this).find('td').eq(5).find('input').attr('name',namespace + 'amount'+val);
      val++;
   });
   $(obj).closest("tr").remove();
   calculatedSum();
}

function addNewRows() {
   var namespace = $('#portletNamespace').val();
   var totalRows = $('#expItemsTable tr').length;
   if (totalRows < 7) {
      var num = totalRows + 1;
      var cols = "<tr>";
      cols += '<td><button type="button" id="exp' + num + '" onclick="removeRowAndResetIndex(this)"  class="btn btn-sm btn-outline-danger py-0"><i class="fas fa-trash-alt" style="cursor: pointer"></i></button></td>';
      cols += '<td><input type="text" id="num' + num + '" value="' + num + '" class="form-control-plaintext text-center " readonly required   /></td>';
      cols += '<td><input type="text"  class="fn_startDateDatepicker "   readonly onfocus="changeDatePickerVal(this)" name="' + namespace + 'startDate' + num + '" id="startDate' + num + '" /></td>';
      cols += '<td><input type="text"  class="fn_endDateDatepicker "  readonly name="' + namespace + 'endDate' + num + '" id="endDate' + num + '"  /></td>';
      cols += '<td class="p-0"><textarea rows="2" cols="50" id="empDesc' + num + '" class="form-control form-control-sm " onblur="$(this).val($(this).val().trim())" style="height:80px;"  maxlength="1000"  placeholder="Add Employee Id and other additional Information here..." name="' + namespace + 'empDesc' + num + '" required ></textarea></td>';
      cols += '<td><input type="number" id="amount' + num + '"  oninvalid="setCustomValidity(\'Bill amount should be less than 1 lakh but greater than zero.\')"  oninput="setCustomValidity(\'\')" step="0.01"  min="0.01" max="100000" onKeyUp= "calculatedSum()" onblur="roundTextBoxes(this)" class="form-control fn_amount " name="' + namespace + 'amount' + num + '" required /></td>';
      cols += "</tr>";

      $("#expItemsTable").append(cols);

      $('.fn_startDateDatepicker').datepicker({
         dateFormat: "yy-mm-dd",
         minDate: $("#fyStartDate").val(),
         maxDate: 0,
         changeMonth: true,
         changeYear: true,

      });
      $('.fn_endDateDatepicker').datepicker({
         dateFormat: "yy-mm-dd",
         minDate: 0,
         maxDate: 0,
         changeMonth: true,
         changeYear: true,

      });
      $('#startDate'+num).datepicker("setDate", new Date());
      $('#endDate'+num).datepicker("setDate", new Date());
      $(".fn_startDateDatepicker").on("change", function () {
         var ids = $(this).attr('id');
         validateDate(ids);
      });

   } else {
      alertModal('Expense:You cannot add more than 7 Bills');
      return false;
   }
}

function roundTextBoxes(obj) {
   var amount = obj.value;
   if (amount.trim() != "") {
      obj.value = parseFloat(amount).toFixed(0);
   } else {
      obj.value = "";
   }
   calculatedSum();
}

function calculatedSum() {
var sum = 0;
   $(".fn_amount").each(function () {
      if ((this.value.trim() != "") && this.value.length != 0) {
         sum += parseFloat(this.value);
      }
   });
$('#totalAmount').val(parseFloat(sum).toFixed(0));
}

function confirmSubmitDetails() {
    event.preventDefault();
    confirmModal('Expense:Are you sure you want to submit the details ?','expenseForm',null);
}
function changeDatePickerVal(obj) {
   var index = $(obj).attr('id');
   if (index == 'startDate2') {
      $('#' + index).datepicker('destroy');
      $('#' + index).datepicker({
         dateFormat: "yy-mm-dd",
         minDate: $("#fyStartDate").val(),
         maxDate: 0,
         changeMonth: true,
         changeYear: true,
      });
   }
}
