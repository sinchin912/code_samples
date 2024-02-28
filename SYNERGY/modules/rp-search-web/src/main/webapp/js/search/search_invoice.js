/*
 * These are the data attributes passed.
 */
var currentScript = document.currentScript;
if( currentScript == null) {
	currentScript = getCurrentScript("search_invoice.js");
}

var PORTLET_NAMESPACE = currentScript.getAttribute('portlet_namespace');
var ACCOUNTS_URL = currentScript.getAttribute('accounts_url');
var TYPE_AHEAD_STORES_URL = currentScript.getAttribute('type_ahead_stores_url');
var STORE_DATA_URL = currentScript.getAttribute('store_data_url');

$(document).ready(function() {
	$("#transactionopt").selectmenu();
});
/*
 * This method validates the shipment form.
 */

function invoiceNoValidation(value, id, message, boolFlag){
	if(value.length > 0){
		if(value.length > 18 || !(/^\d+$/.test(value)) || ($.isNumeric(value) && value < 0) || value.indexOf(".")!=-1) {
			$(id).text(message);
			return false;
	    }else{
	    	$(id).text('');
	    	return boolFlag;
	    }
	}else{
		$(id).text('');
    	return boolFlag;
    }
}

function commonInvoiceValidation(len, value, id, message, boolFlag){
	if(value.indexOf("'")!=-1 || value.length > len){
		$(id).text(message);
		return false;
	}else{
		$(id).text('');
		return boolFlag;
	}
}
function validateInvoiceForm(type) {
	var isValidForm = true;
	var commValdFlag = true;
	var referenceNbr = $.trim($('#'+type+'-referenceNbr').val());
	if(referenceNbr.length > 0){
		if(referenceNbr.length > 11 || !(/^\d+$/.test(referenceNbr)) || ($.isNumeric(referenceNbr) && referenceNbr < 0) || referenceNbr.indexOf(".")!=-1) {
			$('#'+type+'-refno-error').text('Invalid Reference Number');
			isValidForm = false;
	    }else{
	    	$('#'+type+'-refno-error').text('');
	    }
	}else{
    	$('#'+type+'-refno-error').text('');
    }
	
	var documentType = type.charAt(0).toUpperCase() + type.substr(1);
	commValdFlag = invoiceNoValidation($.trim($('#'+type+'no').val()), '#'+type+'no-error', 'Invalid '+documentType+' Number.', commValdFlag);
	commValdFlag = invoiceNoValidation($.trim($('#'+type+'-originvoiceno').val()), '#'+type+'-originvoiceno-error', 'Invalid Invoice Number.', commValdFlag);
	
	commValdFlag = commonInvoiceValidation(9, $.trim($('#'+type+'-destination').val()), '#'+type+'-destination-error', 'Invalid Destination Zip Code.', commValdFlag);
	commValdFlag = commonInvoiceValidation(20, $.trim($('#'+type+'-itemcode').val()), '#'+type+'-itemcode-error', 'Invalid Item Code.', commValdFlag);
	commValdFlag = commonInvoiceValidation(32, $.trim($('#'+type+'-trackingNbr').val()), '#'+type+'-trackingNbr-error', 'Invalid Tracking Number.', commValdFlag);
	
	if(isValidForm && commValdFlag){
		$('#p_auth_token_'+type).val(Liferay.authToken);
		document.getElementById('search'+type+'Form').submit();
	}
}