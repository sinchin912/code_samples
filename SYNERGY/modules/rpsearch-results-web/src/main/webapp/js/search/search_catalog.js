/**
 * 
 */

function commonValidation(value, id, message, boolFlag){
	if(value.indexOf("'")!=-1){
		$(id).text(message);
		return false;
	}else{
		$(id).text('');
		return boolFlag;
	}
}


function validateAndProcess(){
	var commValdFlag = true;
	commValdFlag = commonValidation($.trim($("#catalog-itemcode").val()), '#itemCodeError', 'Invalid Item Code.', commValdFlag);
	if(/^[a-zA-Z0-9- ]*$/.test($("#catalog-itemcode").val()) == false) {
		commValdFlag = false;
		$("#itemCodeError").text("Invalid Item Code.");
	}
	$("#short_author").val('');
	if(commValdFlag){
		//$("#search-catalogs .form").submit();
		document.getElementById('searchCatalogForm').submit();
	}
}

