/**
 * This method clears the form values.
 * @param form
 */
function clearSearchForm(form) {
	$(form)[0].reset();
	$('.form-control').val('');
	$( ".hiddenAccountKey").val('');
	$( ".storedataRetain" ).val('');
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
