/*
 * These are the data attributes passed.
 */
/*
 * These are the data attributes passed.
 */
var currentScript = document.currentScript;
if( currentScript == null) {
	currentScript = getCurrentScript("search_order.js");
}
/**
 * This method helps in getting current scripts for IE.
 * @param fileName
 * @returns
 */
function getCurrentScript(fileName) {
	var scripts = document.getElementsByTagName("script")
	String.prototype.endsWith = function(pattern) {
		var d = this.length - pattern.length;
		return d >= 0 && this.lastIndexOf(pattern) === d;
	};
	for (var i = 0; i < scripts.length; ++i) {
		//alert(scripts[i].getAttribute('src'));
		if (scripts[i].getAttribute('src') != null && 
				scripts[i].getAttribute('src').split('?')[0].endsWith(fileName)) {
	    	// alert('Matched:'+scripts[i].getAttribute('src'));
	    	return scripts[i];
	    }
	}
}

var PORTLET_NAMESPACE = currentScript.getAttribute('portlet_namespace');
var ACCOUNTS_URL = currentScript.getAttribute('accounts_url');
var STORES_URL = currentScript.getAttribute('store_url');

$(document).ready(function() {
	
	$("#statusopt").selectmenu();
	
});
		

function fnResetResultPage(searchType){
	$('.recent-orders').hide();
	$('#result-' + searchType).show();
}

function commonValidation(value, id, message, boolFlag){
	if(value.indexOf("'")!=-1){
		$(id).text(message);
		return false;
	}else{
		$(id).text('');
		return boolFlag;
	}
}
function validate(){
	var isValidForm = true;
	var commValdFlag = true;
	var refNo = $.trim($("#refno").val());
	
	if(refNo.length > 0){
		if(refNo.length > 11 || ($.isNumeric(refNo) && refNo<0) || refNo.indexOf(".")!=-1 || !(/^\d+$/.test(refNo))) {
			$("#refnoError").text("Invalid reference number.");
			isValidForm = false;
		}else{
			$("#refnoError").text("");
		}
	}else{
		$("#refnoError").text("");
	}
	commValdFlag = commonValidation($.trim($("#address").val()), '#addressError', 'Invalid Destination Zip Code.', commValdFlag);
	commValdFlag = commonValidation($.trim($("#itemcode").val()), '#itemcodeError', 'Invalid Item Code.', commValdFlag);
	if(isValidForm && commValdFlag){
		$("#p_auth_token_Orders").val(Liferay.authToken);
		document.getElementById('order-search-form').submit();
	}
}