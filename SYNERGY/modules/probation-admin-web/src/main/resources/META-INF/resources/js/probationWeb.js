$(document).ready(function () {
     var isHR = $('#isHR').val();
     var isManager = $('#isManager').val();
     if(isHR == 'true' && isManager == 'true') {
        $('#second-tab').addClass('active');
        $('#second').addClass('show active');
     } else {
          if(isManager == 'true') {
             $('#second-tab').parent().addClass('hide');
             $('#second').addClass('hide');
             $('#first-tab').addClass('active');
             $('#first').addClass('show active');
          } else if(isHR == 'true') {
            $('#first-tab').parent().addClass('hide');
            $('#first').addClass('hide');
            $('#second-tab').addClass('active');
            $('#second').addClass('show active');
          } else {
             $('#second-tab').parent().addClass('hide');
             $('#first-tab').parent().addClass('hide');
             $('#first').addClass('hide');
             $('#second').addClass('hide');
          }
     }

     $('#managerTable').DataTable({
         dom : 'Bfrtip',
         buttons : [ 'excel' ],
         "pageLength" : 10,
         "lengthMenu" : [ [ 5, 10,20, -1 ], [ 5, 10,20, "All" ] ],
         "aaSorting": [ [4,'asc'] ],
         "bFilter": false,
         "columnDefs": [ {
            "targets": "_all",
            "orderable": false
         }]
     });
     $('#pendingTable, #completedTable').DataTable({
         dom : 'Bfrtip',
         buttons : [ 'excel' ],
         "pageLength" : 10,
         "lengthMenu" : [ [ 5, 10,20, -1 ], [ 5, 10,20, "All" ] ],
         "aaSorting": [ [4,'desc'] ],
         "bFilter": true,
         "columnDefs": [ {
            "targets": "_all",
            "orderable": false
         }]
     });
     $('#numOfDays').on('propertychange input', function (e) {
         if (this.value.length > 3) {
              this.value = this.value.slice(0,3);
         }
     });
     $(".fn_submit").click(function() {
         var x = $(this).val();
         $('#newProbationState').val(x);
         if(x == '3'){
            $("select").each(function() {
                 $(this).prop('required', false);
            });
            $('#areaOfStrength, #areaOfImprovement').prop('required', false);
            $('#numOfDays').prop('max', '60');
         }else if(x == '4'){
            $("select").each(function() {
                 $(this).prop('required',false);
            });
            $('#areaOfStrength, #areaOfImprovement').prop('required', false);
            $('#numOfDays').prop('max', '999');
         } else {
            $("select").each(function() {
                 $(this).prop('required', true);
            });
            $('#areaOfStrength, #areaOfImprovement').prop('required', true);
         }
     });
});


function downloadHrUserManual() {
   var url = $('#downloadHrUserManualResourceUrl').val();
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

function downloadManagerUserManual() {
   var url = $('#downloadManagerUserManualResourceUrl').val();
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

function saveProbation(){
    var newState = $('#newProbationState').val();
    var numOfDays = $('#numOfDays').val();
    if (newState == '3' || newState == '4') {
          if(numOfDays == ""){
              alertModal('Probation Admin:Please specify number of days for extension.');
              return false;
          }else {
              if(newState=='3'){
                event.preventDefault();
                confirmModal('Probation Admin:Do you wish to extend probation by '+numOfDays+' days ?','probationForm',null);
              } else {
                event.preventDefault();
                confirmModal('Probation Admin:Do you wish to revise probation by '+numOfDays+' days ?','probationForm',null);
              }
          }
    } else if (newState == '1'){
        event.preventDefault();
        confirmModal('Probation Admin:Do you wish to confirm the Probation Review ?','probationForm',null);
    } else if (newState == '2'){
        event.preventDefault();
        confirmModal('Probation Admin:Do you wish to not confirm the Probation Review ?','probationForm',null);
    }
}
