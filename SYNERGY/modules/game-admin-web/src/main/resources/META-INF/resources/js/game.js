$(document).ready(function() {
    var startDate = $('#startDate').val();
    var endDate = $('#endDate').val();
    $('#santaOpenDate, #santaFreezeDate, #santaActionDate, #santaCloseDate').datepicker({
        dateFormat: "yy-mm-dd",
        minDate: new Date(startDate),
        maxDate: new Date(endDate),
        onClose: function(){
            validateSantaTimelines();
        }
    });
    $('#raffleOpenDate, #raffleFreezeDate, #raffleActionDate').datetimepicker({
        dateFormat: "yy-mm-dd",
        timeFormat: 'HH:mm:ss',
        controlType: 'select',
        oneLine: true,
        minDate: new Date(startDate),
        maxDate: new Date(endDate),
        onClose: function(){
            validateRaffleTimelines();
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
                 $('#'+photoId).css('margin-left', '0px');
                 $('#'+fileId).attr('src', 'data:image/png;base64,'+imageData.data);
                 $('#anchor_'+fileId).attr('href', 'data:image/png;base64,'+imageData.data);
              }
            }
        });
      });
    });

    $.each($('.fn_winner'), function() {
      var photoElem = $(this);
      var photoId = photoElem.attr('id');
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
                 photoElem.attr('src', 'data:image/png;base64,'+imageData.data);
              }
            }
        });
      });
    });


    $('.fn_surprise').click(function(e) {
       var clickedIndex = parseInt($(this).closest('tr').find('td').eq(1).html());
        var totalPrizes = $('#prizeTableBody tr').length;
        for(var count = 2 ; count <= totalPrizes ; count++){
            if(count < clickedIndex){
                $('#prizeTableBody').find('tr').eq(count-1).find('td').eq(2).find('input').prop('checked',false);
            } else {
                $('#prizeTableBody').find('tr').eq(count-1).find('td').eq(2).find('input').prop('checked','checked');
            }
        }
    });
});

function prizePicOnChange(obj){
    var prizeId = $(obj).attr('id');
    var prizeRow = $(obj).closest("tr");
    var uploadedFile = document.getElementById(prizeId).files[0];
    if(uploadedFile != null){
        $(prizeRow).find('td').eq(4).find('img').attr('src', themeDisplay.getPathThemeImages()+'/spinner.gif');
        $(prizeRow).find('td').eq(4).find('a').attr('href', themeDisplay.getPathThemeImages()+'/spinner.gif');
        $(obj).next().hide();
        var url = $('#updatePrizePicResourceUrl').val();
        var portletNamespace = $('#portletNamespace').val()
            const inpFile = document.getElementById(prizeId);
             const xhr = new XMLHttpRequest();
             const formData = new FormData();
             formData.append(portletNamespace+"prizeId", prizeId)
             for(const file of inpFile.files ){
                 formData.append(portletNamespace+"prizePic",file)
             }
             xhr.open("post",url);
             xhr.send(formData);
             xhr.onreadystatechange = function() {
                  if (xhr.readyState === 4) {
                    var status = xhr.status;
                    var imageData  = JSON.parse(xhr.responseText);
                    if(imageData.data != ''){
                        $(prizeRow).find('td').eq(4).find('img').attr('src', 'data:image/png;base64,'+imageData.data);
                        $(prizeRow).find('td').eq(4).find('a').attr('href', 'data:image/png;base64,'+imageData.data);
                    }
                  }
                  $(obj).next().show();
             }
    }
}

function enableRaffleTimeline(){
    $('#raffleOpenDate, #raffleFreezeDate, #raffleActionDate').prop('disabled',false);
    $('#updateRaffleTimeline').hide();
    $('#cancelUpdateRaffleTimeline').show();
    $('#submitRaffleTimeline').show();
}

function resetRaffleTimeline(){
    $('#raffleOpenDate').val($('#raffleOpenDateOriginal').val());
    $('#raffleFreezeDate').val($('#raffleFreezeDateOriginal').val());
    $('#raffleActionDate').val($('#raffleActionDateOriginal').val());
    $('#raffleOpenDate, #raffleFreezeDate, #raffleActionDate').prop('disabled','disabled');
    $('#raffleOpenDate, #raffleFreezeDate, #raffleActionDate').removeClass('border border-danger');
    $('#updateRaffleTimeline').show();
    $('#cancelUpdateRaffleTimeline').hide();
    $('#submitRaffleTimeline').hide();
}

function confirmRaffleTimeline(){
    event.preventDefault();
    confirmModal('Game Admin:Are you sure to update Raffle Draw timeline ?','raffleTimelineForm',null);
}

function validateRaffleTimelines (){
    var valid = true;
    var error = "";
    $('#raffleOpenDate, #raffleFreezeDate, #raffleActionDate').removeClass('border border-danger');
    var raffleOpenDateString = $("#raffleOpenDate").val();
    var raffleFreezeDateString = $("#raffleFreezeDate").val();
    var raffleActionDateString = $("#raffleActionDate").val();
    var raffleOpenDate = new Date(raffleOpenDateString);
    var raffleFreezeDate = new Date(raffleFreezeDateString);
    var raffleActionDate = new Date(raffleActionDateString);
    if(raffleOpenDate.getTime() >= raffleFreezeDate.getTime()){
       valid =  false;
       error = "Ticket buy close date should be after Ticket buy open date.";
       $("#raffleFreezeDate").addClass('border border-danger');
    } else if (raffleFreezeDate.getTime() >= raffleActionDate.getTime()){
        valid =  false;
        error = "Raffle draw date should be after Ticket buy close date.";
        $("#raffleActionDate").addClass('border border-danger');
    }
    if(!valid){
       $('#submitRaffleTimeline').hide();
       alertModal("Game Admin:"+error);
    } else {
        $('#submitRaffleTimeline').show();
    }
}

function enableSantaTimeline(){
    $('#santaOpenDate, #santaFreezeDate, #santaActionDate, #santaCloseDate').prop('disabled',false);
    $('#updateSantaTimeline').hide();
    $('#cancelUpdateSantaTimeline').show();
    $('#submitSantaTimeline').show();
}

function resetSantaTimeline(){
    $('#santaOpenDate').val($('#santaOpenDateOriginal').val());
    $('#santaFreezeDate').val($('#santaFreezeDateOriginal').val());
    $('#santaActionDate').val($('#santaActionDateOriginal').val());
    $('#santaCloseDate').val($('#santaCloseDateOriginal').val());
    $('#santaOpenDate, #santaFreezeDate, #santaActionDate, #santaCloseDate').prop('disabled','disabled');
    $('#santaOpenDate, #santaFreezeDate, #santaActionDate, #santaCloseDate').removeClass('border border-danger');
    $('#updateSantaTimeline').show();
    $('#cancelUpdateSantaTimeline').hide();
    $('#submitSantaTimeline').hide();
}

function confirmSantaTimeline(){
    event.preventDefault();
    confirmModal('Game Admin:Dates can be updated unless Secret-Santa are assigned. Are you sure to update Secret Santa timeline ?','santaTimelineForm',null);

}

function validateSantaTimelines(){
    var valid = true;
    var error = "";
    $('#santaOpenDate, #santaFreezeDate, #santaActionDate, #santaCloseDate').removeClass('border border-danger');
    var santaOpenDateString = $("#santaOpenDate").val();
    var santaFreezeDateString = $("#santaFreezeDate").val();
    var santaActionDateString = $("#santaActionDate").val();
    var santaCloseDateString = $("#santaCloseDate").val();
    var santaOpenDate = new Date(santaOpenDateString);
    var santaFreezeDate = new Date(santaFreezeDateString);
    var santaFreeze11Date = new Date(santaFreezeDateString);
    santaFreeze11Date = new Date(santaFreeze11Date.setDate(santaFreeze11Date.getDate() + 11));
    var santaActionDate = new Date(santaActionDateString);
    var santaAction5Date = new Date(santaActionDateString);
    santaAction5Date = new Date(santaAction5Date.setDate(santaAction5Date.getDate() + 5));
    var santaCloseDate = new Date(santaCloseDateString);
    if(santaOpenDate.getTime() >= santaFreezeDate.getTime()){
       valid =  false;
       error = "Santa assignment date should be after registration open date.";
        $("#santaFreezeDate").addClass('border border-danger');
    } else if (santaFreeze11Date.getTime() >= santaActionDate.getTime()){
        valid =  false;
        error = "Guessing date should be at least 11 days after Santa assignment date.";
        $("#santaActionDate").addClass('border border-danger');
    } else if (santaAction5Date.getTime() >= santaCloseDate.getTime()){
        valid =  false;
        error = "Game close date should be at least 5 days after guessing date.";
        $("#santaCloseDate").addClass('border border-danger');
    }
    if(!valid){
       $('#submitSantaTimeline').hide();
       alertModal("Game Admin:"+error);
    } else {
        $('#submitSantaTimeline').show();
    }
}

function enableRafflePrize(){
    $('#updateRafflePrize').hide();
    $('.fn_fileUpload').hide();
    $('.fn_surprise').prop('disabled', false);
    $('.fn_prizeName').prop('readonly', false);
    $('#addPrize').show();
    $('.fn_deletePrize').show();
    $('#cancelUpdateRafflePrize').show();
    $('#submitRafflePrize').show();
}

function addNewPrize(){
 var newRowIndex = $('#prizeTableBody tr').length + 1;
 var portletNamespace = $('#portletNamespace').val();
 var checked = $('#prizeTableBody').find('tr').eq(newRowIndex-2).find('td').eq(2).find('input').prop('checked') ? 'checked' : '';
 var newRow = '<tr>';
 newRow = newRow + '<td><button type="button" alt="Delete this prize" class="btn btn-sm btn-outline-danger py-0 fn_deletePrize" onclick="deletePrize(this)" ><i class="fas fa-trash-alt"></i></button></td>';
 newRow = newRow + '<td>'+newRowIndex+'</td>';
 newRow = newRow + '<td><input type="checkbox" class="fn_surprise" name="'+portletNamespace+'surprise'+newRowIndex+'"  '+checked+' /></td>';
 newRow = newRow + '<td><input type="text" maxlength="70" class="form-control form-control-sm fn_prizeName"  name="'+portletNamespace+'prizeName'+newRowIndex+'" value="" required /></td>';
 newRow = newRow + '<td colspan="2"></td></tr>';
 $('#prizeTableBody').append(newRow);
}

function deletePrize(obj) {
    var portletNamespace = $('#portletNamespace').val();
    var clickedIndex = parseInt($(obj).closest('tr').find('td').eq(1).html());
    var totalRows = $('#prizeTableBody tr').length;
    $(obj).closest('tr').remove();
    if(clickedIndex < totalRows) {
        for(var count = clickedIndex; count < totalRows; count++){
            $('#prizeTableBody').find('tr').eq(count-1).find('td').eq(1).html(count);
            $('#prizeTableBody').find('tr').eq(count-1).find('td').eq(2).find('input').prop('name',portletNamespace+'surprise'+count);
            $('#prizeTableBody').find('tr').eq(count-1).find('td').eq(3).find('input').prop('name',portletNamespace+'prizeName'+count);
        }
    }

}

function confirmSaveRafflePrizes(){
    var totalRows = $('#prizeTableBody tr').length;
    $('#totalRows').val(totalRows);
    event.preventDefault();
    confirmModal('Game Admin:Are you sure to update Raffle prize list ?','saveRafflePrizeForm',null);
}

function getTicketFromServer(obj){
        $('#shufflingModal').modal('show');
        setTimeout(function(){
            var isReshuffle = false;
            var id = obj.id;
            var index = '';
            if(id.startsWith("start")){
                index = id.substring(5);
                $(obj).prop('class','hide');
            } else {
                index = id.substring(9);
                isReshuffle = true;
            }
            var url = $('#getWinnerResourceUrl').val();
            AUI().use('aui-io-request',function(A) {
              A.io.request(url,{
                method : 'post',
                        on : {
                            success : function() {
                                $('#winnerDiv'+index).prop('class','show');
                                var result = JSON.parse(this.get('responseData'));
                                $('#ticketId'+index).val(result.data.ticketId);
                                $('#ticketNumber'+index).val(result.data.ticketNumber);
                                $('#ecode'+index).val(result.data.winnerEcode);
                                $('#ename'+index).val(result.data.winnerName);
                                $('#account'+index).val(result.data.winnerAccount);
                                $(".fn_winnerphoto"+index).attr('src', 'data:image/png;base64,'+result.data.winnerPhoto);
                                if (result.data.action) {
                                    $('#message'+index).prop('class','text-center pt-4 text-danger show');
                                    $('#confirm'+index).hide();
                                } else {
                                    $('#message'+index).prop('class','text-center pt-4 text-danger hide');
                                    $('#confirm'+index).show();
                                }
                                $('#shufflingModal').modal('hide');
                            }
                        }
                    });
                });
        }, 2000);
 }