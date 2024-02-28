$(document).ready(function() {
    $('#ticketTable').hide();
    $('#tcDiv').hide();
    $('#ackDiv').hide();
    $('#buttonDiv').hide();
    $('#terms').prop('checked', false);
    $('input').keydown(function (e) {
        if((e.keyCode == 69)||(e.keyCode == 110)||(e.keyCode == 107)||(e.keyCode == 109)||(e.keyCode == 189)) {
            e.preventDefault();
            return false;
        }
    });
    var photoKey = $('#portletNamespace').val()+'prizePicId';
    var url = $('#getPrizePicResourceUrl').val();
    $.each($('.fn_raffle'), function() {
      var photoId = $(this).attr('id');
      var fileId = photoId.substring(7);
      var postData = {
        [photoKey] : fileId
      }
      AUI().use('aui-io-request',function(A) {
        A.io.request(url,{
            method : 'post',
            data : postData,
            on : {
              success : function() {
                var imageData = JSON.parse(this.get('responseData'));
                 $('#'+photoId).attr('src', 'data:image/png;base64,'+imageData.data);
                 $('#'+photoId).css('height', '400px');
                 $('#'+photoId).css('width', '450px');
                 $('#'+photoId).css('margin-top', '0px');
                 $('#anchor_'+fileId).attr('href', 'data:image/png;base64,'+imageData.data);
              }
            }
        });
      });
    });
});

function cancelTicketForm(){
    $('#ticketTable').hide();
    $('#ackDiv').hide();
    $('#tcDiv').hide();
    $('#buttonDiv').hide();
    $('#numTicket').val("");
    $('#terms').prop('checked', false);
    for(var x=1 ; x<=20 ;x++){
        $('#ticketNum'+x).prop('required',false);
        $('#ticketNum'+x).val('');
        $('#message'+x).text('');
        $('#message'+x).prop('class','');
        $('#row'+x).hide();
    }
}

function validateNum(){
    var numofTicket = $('#numTicket').val();
    for(var x=1 ; x<=20 ;x++){
        if(x <= parseInt(numofTicket)){
            $('#row'+x).show();
            $('#ticketNum'+x).prop('required','required');
        } else {
           $('#ticketNum'+x).prop('required',false);
           $('#row'+x).hide();
        }
    }
    $('#ackDiv').show();
    $('#tcDiv').show();
    $('#buttonDiv').show();
    $('#ticketTable').show();
    $('#ticketNum1').focus();
}

function checkAvailability(obj){
    var id = obj.id ;
    var index = id.substring(9);
    var numOfTicket = $(obj).val();
    if(numOfTicket != ''){
    var dataKey = $('#portletNamespace').val()+'ticketId';
    var url = $('#getTicketAvailabilityResourceUrl').val();
    var postData = {
      [dataKey] : numOfTicket
    }
      AUI().use('aui-io-request',function(A) {
        A.io.request(url,{
            method : 'post',
            data : postData,
            on : {
              success : function() {
                var result = JSON.parse(this.get('responseData'));
                $('#message'+index).text(result.data);
				if (result.data == "Ticket available") {
				    $('#message'+index).prop('class','text-success');
				} else {
				    $('#message'+index).prop('class','text-danger');
				}
              }
            }
        });
      });
    } else {
        $('#message'+index).text('');
    }
}


function recheckAvailabilty(){
    var numofTicket = $('#numTicket').val();
    for(var x=1 ; x<= parseInt(numofTicket) ; x++){
        var input = document.getElementById('ticketNum'+x);
        checkAvailability(input);
    }
}

function validateForm(){
    var numofTicket = $('#numTicket').val();
    var ticketNums = [];
    var noAvailableTicket = false;
    for(var x=1 ; x<= parseInt(numofTicket) ; x++){
        ticketNums.push(parseInt($('#ticketNum'+x).val()));
        if($('#message'+x).text() == 'Ticket already taken'){
            noAvailableTicket = true;
        }
    }
    var unique = [...new Set(ticketNums)];
    if(unique.length != parseInt(numofTicket)){
        alertModal("Please enter all unique numbers");
        return false;
    }else if(noAvailableTicket){
        alertModal("Some tickets are unavailable. Please try some different number.");
        return false;
    } else {
      event.preventDefault();
      confirmModal('Raffle:Are you sure you want to buy the tickets ?','raffleTicketForm',null);
    }
}
