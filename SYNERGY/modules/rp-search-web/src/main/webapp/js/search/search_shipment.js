/*
 * These are the data attributes passed.
 */
var currentScript = document.currentScript;
if( currentScript == null) {
	currentScript = getCurrentScript("search_shipment.js");
}

var PORTLET_NAMESPACE = currentScript.getAttribute('portlet_namespace');
var ACCOUNTS_URL = currentScript.getAttribute('accounts_url');
var TYPE_AHEAD_STORES_URL = currentScript.getAttribute('type_ahead_stores_url');
var STORE_DATA_URL = currentScript.getAttribute('store_data_url');

/*
 * This method validates the shipment form.
 */
function commonShipValidation(value, id, message, boolFlag){
	if(value.indexOf("'")!=-1){
		$(id).text(message);
		return false;
	}else{
		$(id).text('');
		return boolFlag;
	}
}
function validateShipmentForm(event) {
	var isValidForm = true;
	var commValdFlag = true;
	var invoiceNbr = $.trim($("#ship-invoiceNbr").val());
	if(invoiceNbr.length > 0){
		if(invoiceNbr.length > 18 || !(/^\d+$/.test(invoiceNbr)) || ($.isNumeric(invoiceNbr) && invoiceNbr < 0) || invoiceNbr.indexOf(".")!=-1) {
			$("#ship-invoiceNbr-error").text("Invalid Invoice Number");
			isValidForm = false;
	    }else{
	    	$("#ship-invoiceNbr-error").text("");
	    }
	}else{
    	$("#ship-invoiceNbr-error").text("");
    }
	var referenceNbr = $.trim($("#ship-referenceNbr").val());
	if(referenceNbr.length > 0){
		if(referenceNbr.length > 11 || !(/^\d+$/.test(referenceNbr)) || ($.isNumeric(referenceNbr) && referenceNbr < 0) || referenceNbr.indexOf(".")!=-1) {
			$("#ship-referenceNbr-error").text("Invalid Reference Number");
			isValidForm = false;
	    }else{
	    	$("#ship-referenceNbr-error").text("");
	    }
	}else{
    	$("#ship-referenceNbr-error").text("");
    }
	
	commValdFlag = commonShipValidation($.trim($("#ship-destination").val()), '#ship-destination-error', 'Invalid Destination Zip Code.', commValdFlag);
	commValdFlag = commonShipValidation($.trim($("#ship-itemcode").val()), '#ship-itemcode-error', 'Invalid Item Code.', commValdFlag);
	commValdFlag = commonShipValidation($.trim($("#ship-trackingNbr").val()), '#ship-trackingNbr-error', 'Invalid Tracking Number.', commValdFlag);
	
	/* 
	 * Remove ship-shipGroup on new Search.
	 * As only required when doing load-more of previous ShipGroup JumpToLink.
	 */
	$("#ship-shipGroup").val('');
	
	if(isValidForm && commValdFlag){
		$("#p_auth_token_Shipments").val(Liferay.authToken);
		document.getElementById('searchShipmentForm').submit();
	}
}

