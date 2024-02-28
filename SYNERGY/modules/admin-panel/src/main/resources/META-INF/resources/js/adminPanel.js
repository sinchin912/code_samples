var emailStatGraph;
$(document).ready(function() {
    $('#schedulerDate').datepicker({
        dateFormat: "yy-mm-dd",
        minDate: new Date("2023-05-01"),
        maxDate: 0,
        onClose: function(){
            getSchedulerEntries();
        }
    });
    $('#emailDate').datepicker({
        dateFormat: "yy-mm-dd",
        minDate: new Date("2023-05-01"),
        maxDate: 0,
        onClose: function(){
            getEmailEntries();
        }
    });
    $('#userDate').datepicker({
        dateFormat: "yy-mm-dd",
        minDate: new Date("2023-05-01"),
        maxDate: 0,
        onClose: function(){
            getUserEntries();
        }
    });
    $('#schedulerDate, #emailDate, #userDate').datepicker("setDate",'${today}');
    getSchedulerEntries();
    getEmailEntries();
    getUserEntries();
    var emailStat = JSON.parse($('#emailStats').val());
    emailStatGraph = new Chart(document.getElementById("emailStatCanvas"), {
       type: 'line',
       data: {
         labels:emailStat.legend,
         datasets: [
         {
            label: "Exit",
            backgroundColor: "#FF1493",
            borderColor:"#FF1493",
            data: emailStat.Exit
         },
         {
            label: "Expense",
            backgroundColor: "#FFD700",
            borderColor:"#FFD700",
            data: emailStat.Expense
         },
         {
            label: "Game",
            backgroundColor: "#8A2BE2",
            borderColor:"#8A2BE2",
            data: emailStat.Game
         },
         {
            label: "Generic",
            backgroundColor: "#008000",
            borderColor:"#008000",
            data: emailStat.Generic
         },
         {
            label: "Lms",
            backgroundColor: "#0000FF",
            borderColor:"#0000FF",
            data: emailStat.Lms
         },
         {
            label: "Performance",
            backgroundColor: "#FF0000",
            borderColor:"#FF0000",
            data: emailStat.Performance
         },
         {
            label: "Probation",
            backgroundColor: "#000000",
            borderColor:"#000000",
            data: emailStat.Probation
         },
         {
            label: "Skill",
            backgroundColor: "#34BD90",
            borderColor:"#34BD90",
            data: emailStat.Skill
         },
         {
            label: "Training",
            backgroundColor: "#BD3470",
            borderColor:"#BD3470",
            data: emailStat.Training
         }
         ]
       },
       options: {
          plugins: {
                zoom: {
                  zoom: {
                    drag: {
                     enabled: true,
                    },
                    mode: 'x',
                  }
                }
              }
       }
    });
});

function resetZoom(){
    emailStatGraph.resetZoom();
}

function newFileOnChange(){
	var uploadedFiles = document.getElementById("newFile").files;
    if(uploadedFiles.length > 0){
        var fileName = "";
        for(var x=0; x<uploadedFiles.length; x++){
            fileName = fileName + uploadedFiles[x].name+";"
        }
        $('#newFilename').val(fileName);
    }
}


function getSchedulerEntries(){
  var newDate = $('#schedulerDate').val();
  $("#schedulerTableBody").empty();
  var forDate = $('#portletNamespace').val()+'schedulerDate';
  var postData = {
    [forDate] : newDate
  }
  var url = $('#getSchedulerResourceUrl').val();
  AUI().use('aui-io-request',function(A) {
    A.io.request(url,{
        method : 'post',
        data : postData,
        on : {
          success : function() {
             var schedulerData = JSON.parse(this.get('responseData'));
             var tableRow = "";
 		     for(i=0; i<schedulerData.data.length; i++){
 		        var action = 'NA';
 		        if(schedulerData.data[i].execute){
 		            action = '<button type="button" onclick="executeScheduler(this)" class="btn btn-primary btn-sm" >Execute</button>';
 		        }
 				tableRow += '<tr><td><button type="button" class="btn btn-link btn-sm" data-toggle="collapse" data-target="#'+schedulerData.data[i].taskName+'">View</button></td><td>'+schedulerData.data[i].taskName+'</td><td>'+schedulerData.data[i].schedule+'</td><td>'+schedulerData.data[i].status+'</td><td>'+schedulerData.data[i].triggered+'</td><td>'+action+'</td></tr>';
 				tableRow += '<tr class="collapse" id="'+schedulerData.data[i].taskName+'"><td></td><td colspan="6">'+schedulerData.data[i].description+'</td></tr>';
 			 }
 			 $("#schedulerTableBody").append(tableRow);
          }
        }
    });
  });
}

function getEmailEntries(){
  var newDate = $('#emailDate').val();
  $("#emailTableBody").empty();
  var forDate = $('#portletNamespace').val()+'emailDate';
  var postData = {
    [forDate] : newDate
  }
  var url = $('#getEmailResourceUrl').val();
  AUI().use('aui-io-request',function(A) {
    A.io.request(url,{
        method : 'post',
        data : postData,
        on : {
          success : function() {
             var emailData = JSON.parse(this.get('responseData'));
             var tableRow = "";
 		     for(i=0; i<emailData.data.length; i++){
 				tableRow += '<tr><td><button type="button" class="btn btn-link btn-sm" data-toggle="collapse" data-target="#'+emailData.data[i].emailId+'">View</button></td><td>'+emailData.data[i].module+'</td><td>'+emailData.data[i].scheduled+'</td><td style="word-break:break-all">'+emailData.data[i].toAddress+'</td><td style="word-break:break-all">'+emailData.data[i].ccAddress+'</td><td style="word-break:break-all">'+emailData.data[i].bccAddress+'</td><td>'+emailData.data[i].subject+'</td></tr>';
 				tableRow += '<tr class="collapse" id="'+emailData.data[i].emailId+'"><td></td><td colspan="7">'+emailData.data[i].body+'</td></tr>';
 			 }
 			 $("#emailTableBody").append(tableRow);
          }
        }
    });
  });
}


function getUserEntries(){
  var newDate = $('#userDate').val();
  $("#userTableBody").empty();
  var forDate = $('#portletNamespace').val()+'userDate';
  var postData = {
    [forDate] : newDate
  }
  var url = $('#getUserResourceUrl').val();
  AUI().use('aui-io-request',function(A) {
    A.io.request(url,{
        method : 'post',
        data : postData,
        on : {
          success : function() {
             var userData = JSON.parse(this.get('responseData'));
             var tableRow = "";
 		     for(i=0; i<userData.data.length; i++){
                var detailsDiv = '';
 				var keys = Object.keys(userData.data[i].userTrackerPath);
 				for(j=0;j< keys.length; j++){
 						var urlAccess = keys[j];
 						detailsDiv +='<div class="row"><div class="col-9" style="word-break:break-all">'+urlAccess+'</div><div class="col-3 text-right">'+userData.data[i].userTrackerPath[urlAccess]+'</div></div>';
 				}
 				tableRow += '<tr><td><button type="button" class="btn btn-link btn-sm" data-toggle="collapse" data-target="#'+userData.data[i].userTrackerId+'">View</button></td><td>'+userData.data[i].userName+'</td><td>'+userData.data[i].userLoginTime+'</td><td>'+userData.data[i].userIp+'</td><td>'+userData.data[i].userAgent+'</td></tr>';
 				tableRow += '<tr class="collapse" id="'+userData.data[i].userTrackerId+'"><td></td><td colspan="4">'+detailsDiv+'</td></tr>';
 			 }
 			 $("#userTableBody").append(tableRow);
          }
        }
    });
  });
}


function executeScheduler(btnObj){
  var taskRow = $(btnObj).closest("tr");
  var taskName = $(taskRow).find('td').eq(1).html();
  confirmModal('Execute Scheduler:Are you sure to execute scheduler \''+taskName+'\' ?', function(confirm) {
   if(confirm) {
          var taskKey = $('#portletNamespace').val()+'taskName';
          var postData = {
            [taskKey] : taskName
          }
          var spinnerImage =  themeDisplay.getPathThemeImages().replace("admin", "synergy")+'/spinner.gif';
          var imageHtml = "<img style='height:25px;' src=\""+spinnerImage+"\" />";
          $(taskRow).find('td').eq(3).html(imageHtml);
          $(taskRow).find('td').eq(4).html(imageHtml);
          $(taskRow).find('td').eq(5).html(imageHtml);
          var url = $('#executeSchedulerResourceUrl').val();
          AUI().use('aui-io-request',function(A) {
            A.io.request(url,{
                method : 'post',
                data : postData,
                on : {
                  success : function() {
                     var schedulerStatus = JSON.parse(this.get('responseData'));
                     $(taskRow).find('td').eq(3).html(schedulerStatus.data.status);
                     $(taskRow).find('td').eq(4).html(schedulerStatus.data.triggered);
                     $(taskRow).find('td').eq(5).html(btnObj);
                  }
                }
            });
          });
   }
  });
}

function confirmUploadFile(){
    confirmModal('Upload File:Are you sure to upload file on Google drive ?', function(confirmUpload) {
        if(confirmUpload) {
            return true;
        } else {
            return false;
        }
    });
}

function confirmCreateFolder(){
    confirmModal('Create Folder:Are you sure to create folder on Google drive ?', function(confirmCreate) {
        if(confirmCreate) {
            return true;
        } else {
            return false;
        }
    });
}


function confirmModal(message, callback) {
    var confirmIndex = true;
    const myArray = message.split(":");
    $('#confirmAdminModalLabel').html(myArray[0]);
    $('#confirmAdminModalDescription').html(myArray[1]);
    $('#confirmAdminModal').modal('show');

    $('#confirmAdmin_no').on("click", function() {
        if(confirmIndex) {
            callback(false);
            $('#confirmAdminModal').modal('hide');
            confirmIndex = false;
        }
    });

    $('#confirmAdmin_yes').on("click", function() {
        if(confirmIndex) {
            callback(true);
            $('#confirmAdminModal').modal('hide');
            confirmIndex = false;
        }
    });
}