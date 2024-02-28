/*
 * These are the data attributes passed.
 */
var currentScript = document.currentScript;
if (currentScript == null) {
	currentScript = getCurrentScript("claim_detail.js");
}

var submitClaim_url = currentScript.getAttribute('submit_data');
var PORTLET_NAMESPACE_CLAIM = currentScript.getAttribute('portlet_namespace_claim');
var lastClicked = '';

document.title = 'HBG Retail Portal: Build a Claim';

$('.search-result-bar').parent('.main-wrapper').hide();

$("#submitClaimFm").submit(function (e) {
	$("#btnSubmitClaim").attr("disabled", true);
	$('#loader-wrapper').show();
	if(submitClaim_url===null) {
		submitClaim_url = $("#submaitClaimFormUrl").val();
	}
	if(PORTLET_NAMESPACE_CLAIM === null) {
		PORTLET_NAMESPACE_CLAIM = $("#portletNameSpaceValue").val();
	}
	var url = submitClaim_url;
	var zendeskErrorMsgVal = $("#zendeskErrorMsg").val();
	var formData = $("#submitClaimFm").serialize();
	var formDataReplace = "";
	formData = PORTLET_NAMESPACE_CLAIM + formData;
	formDataReplace = formData.replace(/\&/gi, "&" + PORTLET_NAMESPACE_CLAIM);

	AUI().use('aui-io-request', function (A) {
		A.io.request(url, {
			method: 'post',
			data: formDataReplace,
			on: {
				success: function () {
					$('#loader-wrapper').hide();
					resp = this.get("responseData");
					if (resp == undefined || resp == null || !resp)
						return false;
					var result = resp;

					if (resp == null) {
						$("#erroModalData").modal({
							backdrop: 'static',
							keyboard: false
						});
					}
					else if (result.indexOf("success") != -1) {

						$("#successModalData").modal({
							backdrop: 'static',
							keyboard: false
						});
					}
					else if (result.indexOf("error") != -1) {
						var arr = result.split(";");

						if (typeof arr[1] !== "undefined") {
							$("#errorClaimMsg").text(arr[1]);
						}
						else {
							$("#errorClaimMsg").text(zendeskErrorMsgVal);
						}
						$("#erroModalData").modal({
							backdrop: 'static',
							keyboard: false
						});
					}
					else {
						$("#errorClaimMsg").text(zendeskErrorMsgVal);
						$("#erroModalData").modal({
							backdrop: 'static',
							keyboard: false
						});
					}
					$("#btnSubmitClaim").attr("disabled", false);
				}
			}
		});
	});
	e.preventDefault(); //STOP default action
});

function getInvoiceObj(invoiceLineId) {
	selectedIndex = getInvoicelistIndex(invoiceDetailList, invoiceLineId);
	return invoiceDetailList[selectedIndex];
}

function retailclaimNumberValidation(obj) {
	var regx = /^[A-Za-z0-9 _.-]+$/;

	if (regx.test(obj.value)) {
		// alert("correct");
		return true;
	}

	e.preventDefault();
	return false;
}

function retailclaimNumberPasteValidation(e) {
	var regex = new RegExp("^[a-zA-Z0-9]+$");
	var str = e.clipboardData.getData('text');

	if (regex.test(str)) {
		return true;
	}

	e.preventDefault();
	return false;
}

function getInvoicelistIndex(list, invoiceLineId) {
	for (i = 0; i < list.length; i++) {
		if (list[i].invoiceLineId == invoiceLineId) {
			return i;
		}
	}
}

function claimCommentValidation(e) {
	var userAgentString = navigator.userAgent; 
	if((navigator.userAgent.indexOf("Chrome") > -1) || (navigator.userAgent.indexOf("Firefox") > -1)) {
		var commentLength = $('#submitClaimHeader\\.claimComments').val();
		var maxlength = $('#submitClaimHeader\\.claimComments').attr("maxlength");
		var newLines = commentLength.match(/(\r\n|\n|\r)/g);
		var addition = 0;
		var enteryKey = false;
		if(!((e.ctrlKey === true &&(e.key=="C" || e.key=="A" || e.key=="X") ||
				(e.code=="KeyC" || e.code=="KeyA" || e.code=="KeyX"|| e.code == "ControlLeft")) || (e.key=="Backspace"))) {
			if(e.keyCode ==13) {
				addition = 1;
				enteryKey = true;
			}
			else if(newLines != null)  {
				addition = newLines.length;
			} 
			if((addition !== 0) && (newLines != null) && 
					(($('#submitClaimHeader\\.claimComments').val().length+newLines.length) > maxlength)) {
				if(enteryKey) {
					$('#submitClaimHeader\\.claimComments').val(commentLength.substring(0, commentLength.length-addition));
				}
				else {
					$('#submitClaimHeader\\.claimComments').val(commentLength.substring(0, commentLength.length-((commentLength.length+newLines.length)-maxlength)));
				}

			}
		}
	}
}

$(document).ready(function () {
	$('#pannelClaimEmpty').show();
	$('#pannelClaimData').hide();
	$('#claimErrorMessage').hide();

	$('#btnSubmitClaim').prop('disabled', true);

	$('#submitClaimHeader\\.claimComments').keypress(function (e) {
		var userAgentString = navigator.userAgent; 
		if((navigator.userAgent.indexOf("Chrome") > -1) || (navigator.userAgent.indexOf("Firefox") > -1)) {
			var x = $('#submitClaimHeader\\.claimComments').val();
			var newLines = x.match(/(\r\n|\n|\r)/g);
			var maxlength = $('#submitClaimHeader\\.claimComments').attr("maxlength");
			if(e.keyCode ==13 && newLines != null && ($('#submitClaimHeader\\.claimComments').val().length+newLines.length) >= maxlength) {
				e.preventDefault();
				return false;
			}
			else if(newLines != null && ($('#submitClaimHeader\\.claimComments').val().length+newLines.length) >= 2000) {
					if((e.ctrlKey === true &&(e.key=="V" || e.key=="C" || e.key=="A" || e.key=="X")) || (e.key=="Backspace") || (e.keyCode=== 37 ||
							e.keyCode=== 38||e.keyCode=== 39||e.keyCode=== 40)) {
						return true;
					}
					else {
						e.preventDefault();
						return false;
					}
				}

			}
	});

	$('#submitClaimHeader\\.retailerClaimNumber').keypress(function (e) {
		if(e.key=="Backspace") {
			return true;
		}
		var regex = new RegExp("^[a-zA-Z0-9]+$");
		var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
		if (regex.test(str)) {
			return true;
		}

		e.preventDefault();
		return false;
	});

	$("#txtretailerClaimNumber").focus(function () {
		$('#submitClaimHeader\\.retailerClaimNumber').show();
		$('#submitClaimHeader\\.retailerClaimNumber').attr('title', $('#submitClaimHeader\\.retailerClaimNumber').val());
		$('#txtretailerClaimNumber').hide();
		$('#submitClaimHeader\\.retailerClaimNumber').focus();

	});

	$('#submitClaimHeader\\.retailerClaimNumber').focusout(function() {
		retailValue =  "Retailer Claim #:" + $('#submitClaimHeader\\.retailerClaimNumber').val();
		$('#txtretailerClaimNumber').val(retailValue);
		$('#submitClaimHeader\\.retailerClaimNumber').hide();
		$('#txtretailerClaimNumber').show();
		$('#txtretailerClaimNumber').attr('title', retailValue);

	});


	$('#submitClaimHeader\\.retailerClaimNumber').hide();
});



function quantityClaimedValidation(e, typeString) {
	if(e.code=="Backspace" || (e.ctrlKey === true &&(e.code=="KeyV" || e.code=="KeyC" || e.code=="KeyA" || e.code=="KeyX" ))) {
		return true;  
	}
	var regex = new RegExp("^[0-9]+$");

	var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);

	if (regex.test(str)) {
		return true;
	}

	e.preventDefault();
	return false;
}

function quantityClaimedPasteValidation(e) {
	var regex = new RegExp("^[0-9]+$");
	var str;
	if(typeof e.clipboardData === "undefined") {
		str = window.clipboardData.getData('text');
	} else {
		str = e.clipboardData.getData('text');
	}

	if (regex.test(str)) {
		return true;
	}

	e.preventDefault();
	return false;
}


function removeClaimed(invoiceLineId) {
	btnId = '#' + invoiceLineId + '_btnAddClaim';
	$(btnId).prop('disabled', false);
	$(btnId).removeClass("btn-theme-gray");
	$(btnId).addClass("btn-theme");
	selectedIndex = getInvoicelistIndex(invoiceClaimList, invoiceLineId);

	invoiceClaimList.splice(selectedIndex, 1);
	$("#tblClaimForm tbody").html('');
	defaultRow = '<tr></tr><tr></tr>';
	$("#tblClaimForm tbody").append(defaultRow);



	if (invoiceClaimList.length == 0) {
		$('#pannelClaimEmpty').show();
		$('#pannelClaimData').hide();
	} else {
		for (i = 0; i <= invoiceClaimList.length; i++) {
			if (invoiceClaimList[i]) {
				var data = getClaimRow(i + 1, invoiceClaimList[i])
				$("#tblClaimForm tbody tr").eq(i + 2 - 1).after(data);
				var index = parseInt(i+1);
				$('#submitClaimLineList'+index+'\\.claimTypeDesc').prop('value', invoiceClaimList[i].claimTypeDesc);
				$('#submitClaimLineList'+index+'\\.claimTypeKey option[value='+invoiceClaimList[i].claimTypeKey + ']').attr('selected','selected');
				$('#submitClaimLineList'+index+'\\.wrongIsbn').prop('value', invoiceClaimList[i].wrongIsbn);
				if(invoiceClaimList[i].claimTypeDesc == 'Wrong Book Shipped') {
					$('#submitClaimLineList'+index+'\\.reshipFlag').prop('checked', false);
					$('#submitClaimLineList'+index+'\\.reshipFlag').prop('disabled', true);
					$('#submitClaimLineList'+index+'\\.wrongIsbn').prop('disabled', false);
					if(invoiceClaimList[i].wrongIsbn == undefined || invoiceClaimList[i].wrongIsbn == '') {
						$('#submitClaimLineList'+index+'\\.wrongIsbn').addClass('red-border');
					}
				}
				if(invoiceClaimList[i].claimQty <= 0 || invoiceClaimList[i].claimQty == '') {
					$('#submitClaimLineList'+index+'\\.claimQty').addClass('red-border');
				}
			}
		}
		defaultRow = '<tr class="total-row invoice-total gray-color-row"><td colspan="9">&nbsp;</td></td></tr>';
		$("#tblClaimForm tbody").append(defaultRow);
	}
	validateSubmitButton();
}

function validateSubmitButton() {
	var hasError = false;
	for (i = 0; i < invoiceClaimList.length; i++) {
		claimQty = invoiceClaimList[i].claimQty.toString().trim() == '' ? 0 : invoiceClaimList[i].claimQty;
		if (claimQty == 0) {
			hasError = true;
		}
		
		if(invoiceClaimList[i].claimTypeDesc == 'Wrong Book Shipped' && (invoiceClaimList[i].wrongIsbn == undefined || invoiceClaimList[i].wrongIsbn == '')) {
			hasError = true;
		}
		if(invoiceClaimList[i].claimTypeKey == undefined || invoiceClaimList[i].claimTypeKey == '') {
			hasError = true;
		}
	}

	if ($('.red-border').length == 0 && !hasError && invoiceClaimList.length > 0) {
		$('#btnSubmitClaim').removeClass('btn-theme-gray');
		$('#btnSubmitClaim').addClass('btn-theme');
		$('#btnSubmitClaim').prop('disabled', false);
		$('#claimErrorMessage').hide();
	} else {
		$('#btnSubmitClaim').addClass('btn-theme-gray');
		$('#btnSubmitClaim').removeClass('btn-theme');
		$('#btnSubmitClaim').prop('disabled', true);
		if (invoiceClaimList.length > 0) {
			// $('#claimErrorMessage').show();	
		} else {
			$('#claimErrorMessage').hide();
		}

	}
}

function reshipFlagChange(inputObj, rowIndex) {
	invoiceClaimList[rowIndex - 1].reshipFlag = inputObj.checked ? true : false;
}

function updateClaimQty(inputObj, rowIndex, quantity, previouslyClaimed) {
	// inputObj.value = inputObj.value == '' ? 0: inputObj.value;
	var claimTypeDesc = document.getElementById('submitClaimLineList'+rowIndex+'.claimTypeDesc').value;
	
	claimQuantity = parseInt(inputObj.value)
	totalQuantity = parseInt(quantity)
	totalClaimed = parseInt(previouslyClaimed)
	if (claimQuantity <= 0 || inputObj.value == '') {
		$(inputObj).addClass('red-border');
		validateSubmitButton();
		return false;
	}
	else if (claimTypeDesc != 'Wrong Book Shipped' && totalQuantity < (claimQuantity + totalClaimed)) {
		$(inputObj).addClass('red-border');
		validateSubmitButton();
		$('#claimErrorMessage').show();
		return false;
	}
	else {
		$(inputObj).removeClass('red-border')
		rowIndex = rowIndex > 0 ? rowIndex - 1 : rowIndex
		invoiceClaimList[rowIndex].claimQty = inputObj.value;
	}
	
	validateSubmitButton();
}

function getClaimRow(rowIndex, invoiceObj) {
	invoiceObj.claimQty = invoiceObj.claimQty ? invoiceObj.claimQty : 0;

	reshipCheckbox = '<input type="checkbox" onclick="reshipFlagChange(this,' + rowIndex + ')" id="submitClaimLineList' + rowIndex + '.reshipFlag" value="true" name="submitClaimLineList[' + rowIndex + '].reshipFlag">';
	if (invoiceObj.reshipFlag) {
		reshipCheckbox = '<input type="checkbox" onclick="reshipFlagChange(this,' + rowIndex + ')" id="submitClaimLineList' + rowIndex + '.reshipFlag" value="true" name="submitClaimLineList[' + rowIndex + '].reshipFlag" checked>';
	}
	
	var claimJsonArrayData = document.getElementById('claimTypeJsonArray').innerHTML;
	var claimTypeJsonArray = JSON.parse(claimJsonArrayData);
	
	var options = '';
	options += '<option value="">Select Claim Type</option>'
	for(var i=0; i<claimTypeJsonArray.length; i++) {
		options += '<option value="'+claimTypeJsonArray[i]['claimTypeKey']+'">'+claimTypeJsonArray[i]['claimTypeDesc'] +'</option>'
	}

	return '<tr>' +
	'<td>' + rowIndex +
	'<input type="hidden" class="d-none" id="submitClaimLineList' + rowIndex + '.claimLineNumber" name="submitClaimLineList[' + rowIndex + '].claimLineNumber" value="' + rowIndex + '"/>' +
	'<input type="hidden" class="d-none" id="submitClaimLineList' + rowIndex + '.invoiceLineKey" name="submitClaimLineList[' + rowIndex + '].invoiceLineKey" value="' + invoiceObj.invoiceLineId + '"/>' +
	'<input type="hidden" class="d-none" id="submitClaimLineList' + rowIndex + '.productKey" name="submitClaimLineList[' + rowIndex + '].productKey" value="' + invoiceObj.productId + '"/>' +
	'<input type="hidden" class="d-none" id="submitClaimLineList' + rowIndex + '.unitPrice" name="submitClaimLineList[' + rowIndex + '].unitPrice" value="' + invoiceObj.unitPrice + '"/>' +
	'<input type="hidden" class="d-none" id="submitClaimLineList' + rowIndex + '.discountPct" name="submitClaimLineList[' + rowIndex + '].discountPct" value="' + invoiceObj.discount + '"/>' +
	'<input type="hidden" class="d-none" id="submitClaimLineList' + rowIndex + '.lineAmount" name="submitClaimLineList[' + rowIndex + '].lineAmount" value="' + invoiceObj.netCharge + '"/>' +
	'<input type="hidden" class="d-none" id="submitClaimLineList' + rowIndex + '.reportingGroupName" name="submitClaimLineList[' + rowIndex + '].reportingGroupName" value="' + invoiceObj.reportingGroupName + '"/>' +
	'<input type="hidden" class="d-none" id="submitClaimLineList' + rowIndex + '.reportingGroupCode" name="submitClaimLineList[' + rowIndex + '].reportingGroupCode" value="' + invoiceObj.reportingGroupCode + '"/>' +
	'<input type="hidden" class="d-none" id="submitClaimLineList' + rowIndex + '.claimTypeDesc" name="submitClaimLineList[' + rowIndex + '].claimTypeDesc" value=""/>' +
	'<input type="hidden" class="d-none" id="submitClaimLineList' + rowIndex + '.shortAuthor" name="submitClaimLineList[' + rowIndex + '].shortAuthor" value="' + invoiceObj.shortAuthor + '"/>' +
	'</td>' +
	'<td> <input type="hidden" class="d-none" id="submitClaimLineList' + rowIndex + '.isbn" name="submitClaimLineList[' + rowIndex + '].isbn" value="' + invoiceObj.isbn + '"/>' + invoiceObj.isbn + '</td>' +
	'<td> <input type="hidden" class="d-none" id="submitClaimLineList' + rowIndex + '.shortTittle" name="submitClaimLineList[' + rowIndex + '].shortTittle" value="' + invoiceObj.shortTitle + '"/>' + invoiceObj.title + '</td>' +
//	'<td> <input type="hidden" class="d-none" id="submitClaimLineList' + rowIndex + '.shortAuthor" name="submitClaimLineList[' + rowIndex + '].shortAuthor" value="' + invoiceObj.shortAuthor + '"/>' + invoiceObj.shortAuthor + '</td>' +
	'<td class="p-0" valign="middle">' +
	'<input type="text" maxlength="20" placeholder="(wrong book claim)" id="submitClaimLineList' + rowIndex + '.wrongIsbn" name="submitClaimLineList[' + rowIndex + '].wrongIsbn" class="form-control w-100" onpaste="wrongBookPasteValidation(event, this, '+rowIndex+');"  onkeypress="wrongBookChange(event, this, '+rowIndex+')" onkeyup="wrongBookChange(event, this, '+rowIndex+')" onfocus="emptyPlaceholder(this);"  onblur="changePlaceholderValue(this);" autocomplete="off" disabled></td>' +
	'<td> <input type="hidden" class="d-none" id="submitClaimLineList' + rowIndex + '.shipQty" name="submitClaimLineList[' + rowIndex + '].shipQty" value="' + invoiceObj.quantity + '"/>' + invoiceObj.quantity + '</td>' +
	'<td>' + invoiceObj.previouslyClaimed + '</td>' +
	'<td><div class="custom-checkbox">' +
	reshipCheckbox +
	'<label for="submitClaimLineList' + rowIndex + '.reshipFlag"></label></div></td>' +
	'<td class="p-0"><select class="form-control p-0" id="submitClaimLineList' + rowIndex + '.claimTypeKey" name="submitClaimLineList[' + rowIndex + '].claimTypeKey" style="padding-top:4px;" onchange="updateClaimType(this, '+rowIndex + ',' + invoiceObj.quantity + ',' + invoiceObj.previouslyClaimed + ')">'+ options +'</select></td>'+
	'<td class="p-0" valign="middle">' +
	'<input type="text" onpaste="quantityClaimedPasteValidation(event)" onkeypress="quantityClaimedValidation(event)" maxlength="5" id="submitClaimLineList' + rowIndex + '.claimQty" onkeyup="updateClaimQty(this,' + rowIndex + ',' + invoiceObj.quantity + ',' + invoiceObj.previouslyClaimed + ')" value="' + invoiceObj.claimQty + '" name="submitClaimLineList[' + rowIndex + '].claimQty" class="form-control w-100"></td>' +
	'<td class="p-0"><a style="font-size: 25px;" onclick="removeClaimed(' + invoiceObj.invoiceLineId + ')" href="javascript:void(0)"><i class="fa fa-times-circle-o close-icon"  aria-hidden="true"></i></a></td>' +
	'</tr>';
}

function addClaimForm(invoiceLineId) {
	if (invoiceClaimList.length == 0) {
		$('#claimTypeSelect').prop('selectedIndex',0);
	}
	btnId = '#' + invoiceLineId + '_btnAddClaim';
	$('#submitClaimHeader\\.claimTypeKey').val($( "#claimTypeSelect option:selected" ).val());
	$(btnId).prop('disabled', true);
	$(btnId).removeClass("btn-theme")
	$(btnId).addClass("btn-theme-gray")

	$('#pannelClaimEmpty').hide();
	$('#pannelClaimData').show();
	var tableObj = $('#tblClaimForm');
	var invoiceObj = getInvoiceObj(invoiceLineId);
	invoiceObj = Object.assign({}, invoiceObj);
	invoiceClaimList.push(invoiceObj);
	var data = getClaimRow(invoiceClaimList.length, invoiceObj)
	$("#tblClaimForm tbody tr").eq(invoiceClaimList.length).after(data);
	validateSubmitButton();
	$('html, body').animate({
		scrollTop: $('#submitClaimFm').offset().top
	}, 'slow');
}

$("select#claimTypeSelect").change(function(){
    var selectedClaimtype = $(this).children("option:selected").val();
    $('#submitClaimHeader\\.claimTypeKey').val(selectedClaimtype);
});

function updateClaimType(obj, rowIndex, quantity, previouslyClaimed){
	var claimTypeDesc = $('#submitClaimLineList'+rowIndex+'\\.claimTypeKey').find("option:selected").text();
	invoiceClaimList[rowIndex-1].claimTypeKey = $('#submitClaimLineList'+rowIndex+'\\.claimTypeKey').find("option:selected").attr('value');
	invoiceClaimList[rowIndex-1].claimTypeDesc = claimTypeDesc;
	
	document.getElementById('submitClaimLineList'+rowIndex+'.claimTypeDesc').value = claimTypeDesc;
	$('#submitClaimLineList'+rowIndex+'\\.reshipFlag').prop('disabled', false);
	
	var claimQtyInput = document.getElementById('submitClaimLineList'+rowIndex+'.claimQty');
	
	if(claimTypeDesc != 'Wrong Book Shipped'){
		 $('#submitClaimLineList'+rowIndex+'\\.wrongIsbn').prop('disabled', true);
		 $('#submitClaimLineList'+rowIndex+'\\.wrongIsbn').prop('value', '');
		 $('#submitClaimLineList'+rowIndex+'\\.wrongIsbn').removeClass('red-border');
		 invoiceClaimList[rowIndex-1].wrongIsbn = "";
	}else{
		$('#submitClaimLineList'+rowIndex+'\\.wrongIsbn').prop('disabled', false);
	    $('#submitClaimLineList'+rowIndex+'\\.reshipFlag').prop('disabled', true);
		$('#submitClaimLineList'+rowIndex+'\\.reshipFlag').prop('checked', false);
		invoiceClaimList[rowIndex - 1].reshipFlag = false;
		$('#claimErrorMessage').hide();
		$('#submitClaimLineList'+rowIndex+'\\.wrongIsbn').addClass('red-border');
	}
	updateClaimQty(claimQtyInput, rowIndex, quantity, previouslyClaimed);
}

function wrongBookChange(e, obj, rowIndex){
	if(obj.value == '') {
		$('#submitClaimLineList'+rowIndex+'\\.wrongIsbn').addClass('red-border');
	}
	if(e.code=="Backspace" || (e.ctrlKey === true &&(e.code=="KeyV" || e.code=="KeyC" || e.code=="KeyA" || e.code=="KeyX" ))) {
		invoiceClaimList[rowIndex-1].wrongIsbn = obj.value;
		validateSubmitButton();
		return true;  
	}
	var regex = new RegExp("^[A-Za-z0-9]+$");

	var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);

	if (regex.test(str)) {
		invoiceClaimList[rowIndex-1].wrongIsbn = obj.value;
		$('#submitClaimLineList'+rowIndex+'\\.wrongIsbn').removeClass('red-border');
		validateSubmitButton();
		return true;
	}
	e.preventDefault();
	validateSubmitButton();
	return false;
}

function wrongBookPasteValidation(e, obj, rowIndex){ 
	var regex = new RegExp("^[A-Za-z0-9]+$");
	var str;
	if(typeof e.clipboardData === "undefined") {
		str = window.clipboardData.getData('text');
	} else {
		str = e.clipboardData.getData('text');
	}

	if (regex.test(str)) {
		invoiceClaimList[rowIndex-1].wrongIsbn = obj.value;
		$('#submitClaimLineList'+rowIndex+'\\.wrongIsbn').removeClass('red-border');
		validateSubmitButton();
		return true;
	}
	if(obj.value == '') {
		$('#submitClaimLineList'+rowIndex+'\\.wrongIsbn').addClass('red-border');
	}
	e.preventDefault();
	validateSubmitButton();
	return false;
}

function emptyPlaceholder(inputObj) {
	$(inputObj).attr('placeholder', '');
}

function changePlaceholderValue(inputObj) {
	$(inputObj).attr('placeholder', '(wrong book claim)');
}
