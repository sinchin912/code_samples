/**
 * This method clears the form values.
 * @param form
 */
function clearForm(form) {
	$(form)[0].reset();
	$('.form-control').val('');
	//form.find("input").val('');
	form.find("select").each(function() {
		this.selectedIndex = 0; 
		if(this.id) {
			var id = '#' + this.id; 
			// console.log(id);   
			if($(id).selectmenu( "instance" )) { 
				$(id).selectmenu("refresh"); 
			}
		}
	});
	// Clear all the error messages.
	$('.error.text-danger').text('');
	$( ".storeTypeAhead" ).prop('disabled', true);
	AUI().use("aui-base", "aui-io-request-deprecated", function(A) {
	    A.io.request(ACCOUNTS_URL, {
			dataType : "jsonp",
			cache: false,
			method : "get",
			data: PORTLET_NAMESPACE + 'searchStr='+ '',
			// data: { <portlet:namespace />searchStr: searchString, },
			on : {
				success : function() {
					resp = this.get("responseData");
					if(resp && resp != null){
						var reponseArr= JSON.parse(resp);
						 if(reponseArr.length==1){
							$( ".accountTypeAhead" ).val(reponseArr[0].label);
							$( ".hiddenAccountKey").val(reponseArr[0].id);
							$( ".storeTypeAhead" ).val('');
							$( ".storedataRetain" ).val('');
                            // NRP-2032 Remove auto selection of store
							//$( ".storeTypeAhead" ).prop('disabled', true);
							$( ".accountTypeAhead" ).prop('disabled', true);
							fnLoadStores(reponseArr[0].id);
							return false;
						}
					}
				}
			}
		}); 
	});
}


function searchByPO(formId){
	document.getElementById(formId).submit();
}

function searchByStore(formId){
	document.getElementById(formId).submit();
}

function searchByDestinationZip(formId){
	document.getElementById(formId).submit();
}

function searchByAccountName(formId){
	document.getElementById(formId).submit();
}

function searchByAccountNo(formId){
	document.getElementById(formId).submit();
}

function searchByViewAllOrders(formId){
	document.getElementById(formId).submit();
}

$(document).ready(function () {
 	if(window.location.search != '') {
 		var presentUrl = window.location.href;
 		var urlString = presentUrl.substring(presentUrl.lastIndexOf("/") + 1, presentUrl.lastIndexOf("?"));
 		if(urlString.indexOf("statements") > -1) {
 			$('#p_p_id_rpstatementsweb_WAR_rpstatementsweb_').hide();
 		} else if(urlString.indexOf("catalog-detail") > -1) {
 			$('#custom-layout').hide();
 		} else if(urlString.indexOf("permission") > -1) {
 			$('#p_p_id_permissionsweb_WAR_permissionsweb_').hide();
 		} else if(urlString.indexOf("faqs") > -1) {
 			$('[id^="p_p_id_com_liferay_journal_content_web_portlet"]').hide();
 		} else if(urlString.indexOf("home") > -1) {
 			$('[id^="p_p_id_com_liferay_asset_publisher_web_portlet"]').hide();
 		}
 	}
});