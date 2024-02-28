/*
 * These are the data attributes passed.
 */
var PAGE_SIZE = parseInt(10);
var undefined = undefined;

var currentScript = document.currentScript;
if( currentScript == null) {
	currentScript = getCurrentScript("search_common.js");
}

var PORTLET_NAMESPACE = currentScript.getAttribute('portlet_namespace');
var STORE_DATA_URL = currentScript.getAttribute('store_data_url');
var TITLE_DATA_URL = currentScript.getAttribute('title_data_url');
var CHANGE_PASSWORD_URL = currentScript.getAttribute('change_password_url');
var jspPage = currentScript.getAttribute('jspPage');
var viewId = currentScript.getAttribute('view-id');

//Select menu Dropdown To Alignment
$('.select-offset-top').bind('click', function(event){
	if($(event.currentTarget).find('select')) {
		var selectId  = $(event.currentTarget).find('select')[0].id;
		
		if(selectId) {
			selectId = '#' + selectId + '-menu';
			var parentElement = $(selectId)[0].parentElement;
			
			if($(parentElement).height() > 60) {
			
				$(parentElement).css({ top: ($(parentElement)[0].offsetTop - $(parentElement).height() - 20) + 'px' });
			}
		}
	}
});

$(document).ready(function () {
	$('.search-options').hide();
	$('#allSearchPage').removeClass('d-none').show();
	var showSearchOrderOption = window.location.search == '' && window.location.href.indexOf('orders') > -1;
	if (jspPage == 'search-order' || showSearchOrderOption) {
		$('#search-orders').show();
	} else if (jspPage == 'search-shipment') {
		$('#search-shipments').show();
	} else if (jspPage == 'search-invoices') {
		$('#search-invoices').show();
	} else if (jspPage == 'search-debits') {
		$('#search-debits').show();
	} else if (jspPage == 'search-credits') {
		$('#search-credits').show();
	} else if (jspPage == 'search-transactions') {
		$('#search-transactions').show();
	} else if (jspPage == 'search-catalogs') {
		$('#search-catalogs').show();
	} else  {
		$('.search-options').hide();
	}
	
	if($( ".accountTypeAhead" ).val()==''){
		// NRP-2032 Remove auto selection of store
        $( ".storeTypeAhead" ).prop('disabled', true);
		AUI().use("aui-base", "aui-io-request-deprecated", function(A) {
		    A.io.request(ACCOUNTS_URL, {
				dataType : "jsonp",
				cache: false,
				method : "get",
				data: PORTLET_NAMESPACE + 'searchStr='+ '',
				on : {
					success : function() {
						resp = this.get("responseData");
						if(resp && resp != null){
							var reponseArr= JSON.parse(resp);
							 if(reponseArr.length==1){
								$( ".accountTypeAhead" ).val(reponseArr[0].label);
								$( ".hiddenAccountKey").val(reponseArr[0].id);
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
	
	$('.advanced-search form input').keypress(function(event) {
		if (event.which == 13) {
			if($(this).attr('class').indexOf('accountTypeAhead')!=-1 || $(this).attr('class').indexOf('storeTypeAhead')!=-1)
				return false;
			event.preventDefault();
			var formId = $(this).closest('.advanced-search').attr('id')
			if(formId == 'search-invoices' || formId == 'search-debits' || formId == 'search-credits' || formId == 'search-transactions'){
				var formName = formId == 'search-invoices' ? 'invoice' : (formId == 'search-debits' ? 'debit' : (formId == 'search-credits' ? 'credit' : 'transaction'));
				validateInvoiceForm(formName);
			}else if(formId == 'search-shipments'){
				validateShipmentForm();
			}else if(formId == 'search-orders'){
				validate();
			}else if(formId == 'search-catalogs'){
				validateAndProcess();
			}
		} 
	});
	
	if($( ".accountTypeAhead" ).val()!=''){
		fnLoadStores($(".hiddenAccountKey").val());
	}
	
    $(".nav-collapse li:last-child").click(function () {
      $(this).toggleClass("open");
    });

    $("#_145_navSiteNavigationNavbarBtn").click(function () {
      $(".navright").css("display", "block");
    }); 
    
    $(document).on('click', '#search-select', function(event) {
    	var selectedValue = '';
    	if (event.target.innerText) {
    		selectedValue = event.target.innerText.toLowerCase();
    	} else {
    		selectedValue = event.target.innerHTML.toLowerCase();
    	}
    	
        if(selectedValue == 'all financial transactions'){
        	selectedValue = 'transactions';
    	}
        if(selectedValue == 'titles/items'){
    		selectedValue = 'catalogs';
    	}
        
        $('.search-options').hide();
        $('#search-' + selectedValue).show();
        
        if($( '#shipmentaccountdata' ).val()==''){
			$( '#shipmentstoredata' ).prop('disabled', true);
		}
        
        $('#search-select .select-selected').html('Search for ' + $('#search-select .select-selected').html());
        clearSearchForm($('#search-' + selectedValue + ' form'));
    });
    
    $(".close_link").on('click', function () {
  		$("#search-select select").val("0");
  		// $(this).closest('form').find("input").val("");
  		clearSearchForm($(this).closest('form'));
  		$('.search-options').hide();
  		
  		// Below code needs some improvement.
  		$('#search-select .select-selected').html('Search for...');
  		$('#search-select .select-items div').removeClass('same-as-selected');
  		$('#search-select .select-items div:first-child').addClass('same-as-selected');
  	});
    
    $(".clear_all").on('click', function (event) {
    	clearSearchForm($(this).closest('form'));
  	});
	
  	// Validations.
  	$('#searchForm #refno').keypress(function(event) {
       if (event.which != 8 && isNaN(String.fromCharCode(event.which))) {
           event.preventDefault(); //stop character from entering input
       }
    });
  	
  	/* Note: For every custom select box, selectmenu() needs to be called as below. */
  	$("#ship-poNbrOpt" ).selectmenu();
  	$("#pono_operator" ).selectmenu();
  	$("#invoice-poNbrOpt" ).selectmenu();
  	$("#debit-poNbrOpt" ).selectmenu();
  	$("#credit-poNbrOpt" ).selectmenu();
  	$("#transaction-poNbrOpt" ).selectmenu();
	$(".accountdata").selectmenu({change : loadRelativeStore}); 
 	$(".storedata").selectmenu();
 	$("#catalog_formatdata").selectmenu();
	$("#catalog_publisherdata").selectmenu();
	$("#catalog_imprintdata").selectmenu();
	$("#catalog_pubstatusdata").selectmenu();
	$("#catalog_reportinggroup").selectmenu();
	// End calling selectmenu().
	
	$('.storedata').on('selectmenuchange', function() {
		  
	    $("#hiddenStoreName").val("");
	    $("#hiddenStoreNumber").val("");
	    
	    $("#ship-hiddenStoreName").val("");
	    $("#ship-hiddenStoreNumber").val("");
	    
	    
	    $("#storedataRetain").val("");
	    if(this != '' && this.options != '' && this.options != undefined && this.selectedIndex != '' && this.selectedIndex != undefined){
	    	 var storeValue =  this.options[this.selectedIndex].text;
	    	 var storeId = this.options[this.selectedIndex].value;
			 var number = storeValue.trim().split(/\s+/).splice(-1,1);
			 storeValue = storeValue.split(number)[0].trim();
			 if(storeValue == 'All Stores'){
				 storeValue = '';
				 storeId = '';
			 }
	  		 $("#hiddenStoreName").val(storeValue);
	  		 $("#hiddenStoreNumber").val(number);
	  		 
	  		$("#ship-hiddenStoreName").val(storeValue);
		    $("#ship-hiddenStoreNumber").val(number);
		    
		    
	  		 $("#storedataRetain").val(storeId);
	    }
	}); // end of selectmenuchange function.
	
	updateShipmentBlockHREFs();
});


var loadRelativeStore = function(context) {
    $('#hiddenStoreName').val("");
    $('#ship-hiddenStoreName').val("");
    $("#storedataRetain").val('');
	var instanceExists = $("#storedata").selectmenu('instance');
	if(instanceExists == undefined)
		$(".storebuttondata").remove();
	else{
		$('.storedata').find('option').remove().end();
		$('.storedata').selectmenu('destroy').selectmenu({ style: 'dropdown' });
		$('.storedata').append('<option selected value="All Stores">All Stores</option>');
	}
	fnLoadStores(this,''); 
}


function fnLoadStores(id){
	var accountNameKey = id;
		if(accountNameKey == null || accountNameKey === undefined || accountNameKey === "undefined" || accountNameKey == ''){
			return false;	
		} 
	
    AUI().use("aui-base", "aui-io-request-deprecated", function(A) {
	    A.io.request(STORE_DATA_URL,{
			dataType : "json",
			cache: false,
			method : "get",
			data: PORTLET_NAMESPACE + 'accountNameKey='+ accountNameKey.trim(),
			//data :  <portlet:namespace/>accountNameKey : accountNameKey.trim() },
			on : {
				success : function() {
					resp = this.get("responseData");
					if (resp == 'failure'){
						$( ".storeTypeAhead" ).val("No Stores Found...");
						$( ".storeTypeAhead" ).prop('disabled', true);
					}
					else {
					$( ".storeTypeAhead" ).prop('disabled', false);
					}
                    // NRP-2032 Remove auto selection of store
					/*else{	
						if(resp.length==1){
							 $( ".storeTypeAhead" ).val( resp[0].label);
							 $( ".storedataRetain" ).val(resp[0].id);
							 $( ".storeTypeAhead" ).prop('disabled', true);
								return false;
								}
						 
					}*/
				}
			}
		});
	});
}

/*
 * Load-more functionality for search shipments & orders.
 */
$(document).ready(function () {
	/**
	 * This is the load more functionality for Shipments.
	 */
	
	$("#p_auth_token_Orders").val(Liferay.authToken);
	$("#p_auth_token_Shipments").val(Liferay.authToken);
	$('#p_auth_token_Invoice').val(Liferay.authToken);
	$('#p_auth_token_Debit').val(Liferay.authToken);
	$('#p_auth_token_Credit').val(Liferay.authToken);
	$('#p_auth_token_Transaction').val(Liferay.authToken);
	$("#p_auth_token_Catalogs").val(Liferay.authToken);
	
	var dataFormShipments = $('#search-shipments form').serializeArray();
	var dataFormInvoices = $('#search-invoices form').serializeArray();
	var dataFormDebits = $('#search-debits form').serializeArray();
	var dataFormCredits = $('#search-credits form').serializeArray();
	var dataFormTransactions = $('#search-transactions form').serializeArray();
	var dataFormCatalogs = $('#search-catalogs form').serializeArray();
	$("body").on("click", ".loadMore", function(event) {
		var pageName = event.currentTarget.name;
		var formData;
		var loadMoreName = pageName;
		if(pageName == 'invoices'){
			formData = dataFormInvoices;
			loadMoreName = 'invoices';
		}else if(pageName == 'credits'){
			formData = dataFormCredits;
			loadMoreName = 'invoices';
		}else if(pageName == 'debits'){
			formData = dataFormDebits;
			loadMoreName = 'invoices';
		}else if(pageName == 'transactions'){
			formData = dataFormTransactions;
			loadMoreName = 'invoices';
		}else if(pageName == 'shipments'){
			formData = dataFormShipments;
		}else if(pageName == 'catalogs'){
			formData = dataFormCatalogs;
		}
		var loadmoreElement = formData.filter(function(x) { return x.name.indexOf(loadMoreName+"-loadmore") != -1; })[0];
		loadmoreElement.value = 'true';
		
		var offsetElement = formData.filter(function(x) { return x.name.indexOf(loadMoreName+"-offset") != -1; })[0];
		var offset_val = parseInt(offsetElement.value);
		if (isNaN(offset_val)) {
			offset_val = "0";
		}
		if(offsetElement != null) { 
			offsetElement.value = offset_val + PAGE_SIZE;
		}
		var formDataStr = '';
		formData.forEach(function(e) { formDataStr += encodeURIComponent(e.name) + '=' + encodeURIComponent(e.value) + '&'; });
		
		offset_val = parseInt(offsetElement.value);
		var totalSize = $('#total-size-'+loadMoreName).text().replace(/\,/g,'');
		var shipSizeElement = formData.filter(function(x) { return x.name.indexOf(loadMoreName+"-size") != -1; })[0];
		shipSizeElement.value = totalSize;
		
		$('#'+loadMoreName+'-loading').addClass('loading-animation');
		$('.loadMore').hide();
		
		AUI().use("aui-base", "aui-io-request-deprecated", function(A) {
		    A.io.request($('#search-'+pageName+' form').attr( 'action' ), {
				dataType : "json",
				method : "get",
				data : formDataStr,
				on : {
					success : function() {
						resp = this.get("responseData");
						if (resp == undefined || resp == null || !resp)
							return false;
						var result = $(resp).find('#result-'+loadMoreName+' .'+loadMoreName+'_rows');
						$('#result-'+loadMoreName+' .'+loadMoreName+'_rows .append_div').html(result);
						$('#result-'+loadMoreName+' .'+loadMoreName+'_rows .append_div').first().removeClass('append_div');
						
						loadmoreElement.value = '';
						if((parseInt(offset_val) + PAGE_SIZE) >= totalSize){
							$('#loadMore-'+loadMoreName).hide();
							$('#current-size-'+loadMoreName).text($('#total-size-'+loadMoreName).text());
						}else{
							$('#current-size-'+loadMoreName).text(parseInt(offset_val) + PAGE_SIZE);
						}
						// Calling updateShipmentBlockHREFs for Jump-To links.
						updateShipmentBlockHREFs();
					}
				}
			});
		});	
	});
	
	/**
	 * This is the load more functionality for Orders.
	 */
	var formDataOrders = $('#search-orders form').serializeArray();
	$("body").on("click", "#loadMore", function() {
		var listSize = 0; //${data.size()};
		var offsetElement = formDataOrders.filter(function(x) { return x.name.indexOf("order-offset") != -1; })[0];
		var offset_val = parseInt(offsetElement.value);
		if (isNaN(offset_val)) {
			offset_val = "0";
		}
		var loadmoreElement = formDataOrders.filter(function(x) { return x.name.indexOf("order-loadmore") != -1; })[0];
		loadmoreElement.value = 'true';
		if(offsetElement != null) {
			offsetElement.value = parseInt(offset_val) + parseInt(PAGE_SIZE);
		}
		var formDataOrdersStr = '';
		formDataOrders.forEach(function(e) { formDataOrdersStr += encodeURIComponent(e.name) + '=' + encodeURIComponent(e.value) + '&'; });
		offset_val = parseInt(offsetElement.value); // Updated offset_val.
		$('#order-loading').addClass('loading-animation');
		$('#loadMore').hide();
		
		AUI().use("aui-base", "aui-io-request-deprecated", function(A) {
		    A.io.request($('#search-orders form').attr( 'action' ), {
				dataType : "json",
				method : "get",
				data : formDataOrdersStr,
				on : {
					success : function() {
						resp = this.get("responseData");
						if (resp == undefined || resp == null || !resp)
							return false;
						var result = $(resp).find('#result-orders .order_rows');
						$('#result-orders .order_rows .append_div').html(result);
						$('#result-orders .order_rows .append_div').first().removeClass('append_div');
						loadmoreElement.value = 'false';
						listSize = parseInt($(resp).find('#main_wrapper_content #current-size').text());
						
						var totalSize = $('#total-size-orders b').text().trim();
						var listTotal = parseInt(offset_val) + listSize;
						
						if(listTotal == parseInt(totalSize)){
							$('#orderBtnLoadMore').hide();
						}
						
						if (listSize < 10) {
							$('#current-size').text(parseInt(offset_val) + listSize);
						} else {
							$('#current-size').text(parseInt(offset_val) + PAGE_SIZE);
						}
					}
				}
			});
		});
	});
}); // End of document ready function.

var itemCodeSelected = '';

function clearSliderOptions(){
	$(".scrollable-slider").remove();
	$("div.modal-body .body-content div").removeClass("after-search-carousel");
	$(".content-block blockQuote").remove();
	var blockQuote = $('<blockquote/>').appendTo(".content-block");
	var pTag =  $('<p>', {
	     text: 'Search for your title from the options below.',
	}).appendTo(blockQuote);
	var spanTag =  $('<span>');
	var imgTag =  $('<img />').attr({
        src:'/RetailPortal-Theme-1.0-SNAPSHOT/images/ap/icons/openbook.svg'
     }).appendTo(spanTag);  
	spanTag.appendTo(blockQuote);
}

function fnSetItemCode(context){
	var itemcode = context.id.split(",");
	itemCodeSelected = itemcode[0];
}

function enableSearchButton(context){
	var itemcode = $("#modelisbn").val();
	var title = $("#modeltitle").val();
	var subject = $("#subject").val();
	var selectedFormat = $('#formatdata :selected').text();
	var selectedPublisher = $('#publisherdata :selected').text();
	var selectedPubStatus = $('#pubstatusdata :selected').text();
	var selectedImprint = $('#imprintdata :selected').text();
	var author = $("#author").val();
	var authorResidence = $("#authorresidence").val();
	
	if(itemcode != '' || title != '' || subject != '' || author != '' || authorResidence != '' || (selectedFormat != '' && selectedFormat != 'All Formats') || (selectedPublisher != '' && selectedPublisher != 'All Publishers') || (selectedPubStatus != '' && selectedPubStatus != 'All Pub Status') || (selectedImprint != '' && selectedImprint != 'All Imprints')){
		$("#searchbuttondiv button").removeClass("btn-theme-gray");
		$("#searchbuttondiv button").addClass("btn-theme");
		$("#clearButton").addClass("active");
	}else{
		$("#searchbuttondiv button").removeClass("btn-theme");
		$("#searchbuttondiv button").addClass("btn-theme-gray");
		$("#clearButton").removeClass("active");
	}
}

function fnClrModalSearchForm() {
	 $('#formatdata	').prop('selectedIndex',0);
	 $("#formatdata").selectmenu("refresh");
	 $('#publisherdata').prop('selectedIndex',0);
	 $("#publisherdata").selectmenu("refresh");
	 $('#imprintdata').prop('selectedIndex',0);
	 $("#imprintdata").selectmenu("refresh");
	 $('#pubstatusdata').prop('selectedIndex',0);
	 $("#pubstatusdata").selectmenu("refresh");
	 $("#modeltitle").val("");
	 $("#modelisbn").val("");
	 $("#author").val("");
	 $("#authorresidence").val("");
	 $("#subject").val("");
}

	function getProductData() {
		$("#no_of_results").html("");
		$("#bookSelected").html("");
	var itemcode = $("#modelisbn").val();
	var title = $("#modeltitle").val();
	var subject = $("#subject").val();
	var selectedFormat = $('#formatdata :selected').text();
	var selectedPublisher = $('#publisherdata :selected').text();
	var selectedPubStatus = $('#pubstatusdata :selected').text();
	var selectedImprint = $('#imprintdata :selected').text();
	var author = $("#author").val();
	var authorResidence = $("#authorresidence").val();
	
	
	if(itemcode == '' && title == '' && subject == '' && selectedFormat == 'All Formats' && selectedPublisher == 'All Publishers' && selectedPubStatus == 'All Pub Status' && selectedImprint == 'All Imprints' && author == '' && authorResidence == ''){
		return false;
	}
	
	var $data = JSON.parse('{ "'+PORTLET_NAMESPACE+'title":"'+title+'"' 
			+ ',"'+PORTLET_NAMESPACE+'itemCode":"'+itemCode+'"' 
			+ ',"'+PORTLET_NAMESPACE+'selectedFormat":"'+selectedFormat+'"' 
			+ ',"'+PORTLET_NAMESPACE+'selectedPublisher":"'+selectedPublisher+'"' 
			+ ',"'+PORTLET_NAMESPACE+'selectedImprint":"'+selectedImprint+'"' 
			+ ',"'+PORTLET_NAMESPACE+'author":"'+author+'"' 
			+ ',"'+PORTLET_NAMESPACE+'authorResidence":"'+authorResidence+'"' 
			+ ',"'+PORTLET_NAMESPACE+'subject":"'+subject+'"' 
			+ ',"'+PORTLET_NAMESPACE+'selectedPubStatus":"'+selectedPubStatus+'"}');
	
	AUI().use('aui-base', 'aui-io-request-deprecated', function(A) {
		A.io.request(TITLE_DATA_URL, {
			dataType : 'json',
			method : 'post',
			data : $data,
			on : {
				success : function() {
					    var resp = this.get('responseData');
					    if(resp == undefined || resp == null || resp.length == 0){
					    	clearSliderOptions();
					    	$("#doneButton").removeClass("btn-theme");
							$("#doneButton").addClass("btn-theme-gray");
							$("#no_of_results").html("<b>No results found</b>");
					    	return false;
					    }
							
						$("#doneButton").removeClass("btn-theme-gray");
						$("#doneButton").addClass("btn-theme");
						
						var arrayLength = resp.length;
						$(".content-block").addClass("after-search-carousel").empty().append('<div class="scrollable-slider"></div>');
						var ulTag = $('<ul/>').addClass('item-list').appendTo('.scrollable-slider');
						var results = "results";
							
						if(arrayLength === 1)
							results = "result";
						for (var i = 0; i < arrayLength; i++) {	
							$("#no_of_results").html("<b>"+arrayLength+" "+ results+" found</b>");
                            var headingTagBefore = $('<h5/>').text(resp[i].format);
                            var headingTagAfter = $('<h5/>').text(resp[i].title);
                            var liTag = $('<li/>').addClass('image-data'+i);
                            var imgUrl =  "http://media.hdp.hbgusa.com/coverimages/"+resp[i].isbn;
							var imgTag =  $('<img />').attr({
                              src:imgUrl,
                              width:'104px',
                              height:'160px',
                              id:resp[i].isbn + "," + resp[i].title + "," + resp[i].format,
                              alt:'No Image'
                            });  
							var imgDivTag = $('<div/>').addClass('img-thumb');
							imgDivTag.append(imgTag);
							imgDivTag.appendTo($('<a />').attr({
                              href:'#'
                            }).append(imgDivTag).appendTo(liTag));
                            liTag.appendTo(ulTag);
							$('li.image-data'+i+' .img-thumb').before(headingTagBefore);
							$('li.image-data'+i+' .img-thumb').after(headingTagAfter);
						}
				}
			}
		});
	});
}
	
/**
 * This script is used on order_detail, order_results & shipment_results page.
 * It redirects to Search order pages.
 */
$("body").on("mousedown", ".jumpto-link, .view_orders", function(event){
	switch (event.which) {
	    case 1:
	        $('#search-orders form').attr('target','_self');
	        showJumpToResults($(this),event);
	        break;
	    case 2:
	        $('#search-orders form').attr('target','_blank');
	        showJumpToResults($(this),event);
	        break;
	    case 3:
	        if (confirm("Would you like to view the results in a new tab?")) {
				$('#search-orders form').attr('target','_blank');
				showJumpToResults($(this),event);
			} else {
				break;
			}
	        break;
	    default:
	    	$('#search-orders form').attr('target','_self');
	    	showJumpToResults($(this),event);
	    	break;
	}
});

function showJumpToResults(element,event){
	clearSearchForm($('#search-orders form'));
	if(element.attr('data-attr')=='storedata'){
		$('#search-orders form').find('.storeTypeAhead').prop('disabled', false);
		$('#search-orders form').find('.hiddenAccountKey').val(event.currentTarget.children["accountpk"].innerText);
		$('#search-orders form').find('.accountTypeAhead').val(event.currentTarget.children["accountname"].innerText+" | "+event.currentTarget.children["accountnumber"].innerText);
		$('#search-orders form').find('.storedataRetain').val(event.currentTarget.children["storepk"].innerText);
		$('#search-orders form').find('.storeTypeAhead').val(event.currentTarget.children["storename"].value+" | "+event.currentTarget.children["storenumber"].innerText);
	}
	else if(element.attr('data-attr')=='accountdata'){
		$('#search-orders form').find('.hiddenAccountKey').val(event.currentTarget.children["accountpk"].innerText);
		$('#search-orders form').find('.accountTypeAhead').val(event.currentTarget.children["accountname"].innerText+" | "+event.currentTarget.children["accountnumber"].innerText);
	}
	else if(element.attr('data-attr')=='address'){
		$('#search-orders form').find('#address').val(event.currentTarget.children["zipcode"].innerText);	
	}else{
		$('#search-orders form').find('#'+element.attr('data-attr')).val(event.currentTarget.innerText);
	}
	//$('#search-orders form').submit();
	document.getElementById('order-search-form').submit();
};

/*
 * This is used on shipment_results page.
 * Also this now getting used on order_detail page.
 */
$("body").on("mousedown", ".shipments-jumpto-link", function(event){
    switch (event.which) {
    case 1:
        $('#search-shipments form').attr('target','_self');
        showShipmentJumpToResults($(this),event);
        break;
    case 2:
        $('#search-shipments form').attr('target','_blank');
        showShipmentJumpToResults($(this),event);
        break;
    case 3:
        if (confirm("Would you like to view the results in a new tab?")) {
			$('#search-shipments form').attr('target','_blank');
		showShipmentJumpToResults($(this),event);
		} else {
			break;
			   }
        break;
    default:
    	$('#search-shipments form').attr('target','_self');
    	showShipmentJumpToResults($(this),event);
    	break;
}
});

function showShipmentJumpToResults(element,event){
	clearSearchForm($('#search-shipments form'));
	if(element.attr('data-attr')=='shipGroup') {
		$('#search-shipments form').find('#ship-shipGroup').val(event.currentTarget.text);	
	}
	$('#search-shipments form').submit();
};

$("body").on("mousedown", ".catalog-jumpto-link", function(event){
    switch (event.which) {
    case 1:
        $('#search-catalogs form').attr('target','_self');
        showCatalogJumpToResults($(this),event);
        break;
    case 2:
        $('#search-catalogs form').attr('target','_blank');
        showCatalogJumpToResults($(this),event);
        break;
    case 3:
        if (confirm("Would you like to view the results in a new tab?")) {
			$('#search-catalogs form').attr('target','_blank');
			showCatalogJumpToResults($(this),event);
		} else {
			break;
			   }
        break;
    default:
    	$('#search-catalogs form').attr('target','_self');
    showCatalogJumpToResults($(this),event);
    	break;
}
});

function showCatalogJumpToResults(element,event){
	clearSearchForm($('#search-catalogs form'));
	if(element.attr('data-attr')=='short_author') {
		$('#search-catalogs form').find('#short_author').val(event.currentTarget.text);	
	}
	$('#search-catalogs form').submit();
};


/**
 * This link is on line_template. It redirects to view shipment tab.
 */
$("body").on("click", ".view-shipment-link", function(event) {
	if($(this).attr('href') == window.location.href) {
		location.reload(true);
	}
});

/**
 * This is for View-Shipment link on search-shipment page.
 */
function updateShipmentBlockHREFs() {
	$('.shipments_rows .carrierTrackingNumber').each(function() {
		var trackingInput = $(this).find('.trackingNbr a').text();
		var shipmentLink = $(this).find('a.shipment-data');
		var existingHref = shipmentLink.attr('href');
		//alert('updateShipmentBlockHREFs,'+trackingInput+','+shipmentLink+','+existingHref);
		//console.log(existingHref + '-' + trackingInput);
		if(trackingInput != null) {
			shipmentLink.attr('href', existingHref + '-' + trackingInput);
		}
	});
	
	$('.accountTypeAhead').on('keyup focusin', function(){
		var searchString = encodeURIComponent ($(this).val());
	    $(this).autocomplete({
	    	source: function(request,response){
	    		AUI().use("aui-base", "aui-io-request-deprecated", function(A) {
	    		    A.io.request(ACCOUNTS_URL, {
	    				dataType : "jsonp",
	    				method : "get",
	    				data: PORTLET_NAMESPACE + 'searchStr='+ searchString,
	    				on : {
	    					success : function() {
	    						resp = this.get("responseData");
	    						response(JSON.parse(resp));
	    					}
	    				}
	    			});
	    		});
	    	},
	    	change: function (event, ui) {
	    		
	            if(!ui.item || (ui.item && ui.item.id=='-999')){
	            	$( ".accountTypeAhead" ).val('');
					$( ".hiddenAccountKey").val('');
					$( ".storeTypeAhead" ).val('');
					$( ".storedataRetain" ).val('');
					$( ".storeTypeAhead" ).prop('disabled', true);
					return false;
	            }
	        },
	    	minLength: 0,
	    	select: function( event, ui ) {
	    		if(ui.item.id=='-999'){
	    			$( ".accountTypeAhead" ).val('');
					$( ".hiddenAccountKey").val('');
					$( ".storeTypeAhead" ).val('');
					$( ".storedataRetain" ).val('');
					$( ".storeTypeAhead" ).prop('disabled', true);
					return false;
	    		}
				$( ".accountTypeAhead" ).val( ui.item.label);
				$( ".hiddenAccountKey").val( ui.item.id);
				$( ".storeTypeAhead" ).val('');
				$( ".storedataRetain" ).val('');
				$( ".storeTypeAhead" ).prop('disabled', true);
				fnLoadStores(ui.item.id);
				return false;
			}
	    }).focus(function () {
	        $(this).autocomplete("search");
	    }).data( "ui-autocomplete" )._renderItem = function( ul, item ) {
			return $( "<li></li>" )
			.data( "item.autocomplete", item )
			.append( "<a>" + item.label + "</a>" )
			.appendTo( ul );
		};	 
	}); 
	
	$('.storeTypeAhead').on('keyup focusin',function(){ 
		var accountNameKey = $( ".hiddenAccountKey").val();
		var searchString = $(this).val();
		var $data = JSON.parse('{ "'+PORTLET_NAMESPACE+'searchStr":"'+searchString+'"' 
	 			+ ',"'+PORTLET_NAMESPACE+'accountNameKey":"'+accountNameKey+'"}');
		$(this).autocomplete({
			source: function(request,response){
	    		AUI().use("aui-base", "aui-io-request-deprecated", function(A) {
	    		    A.io.request(STORES_URL, {
	    				dataType : "jsonp",
	    				method : "get",
	    				data: $data,
	    				on : {
	    					success : function() {
	    						resp = this.get("responseData");
	    						if(resp.trim() != "") {
	    			            	response(JSON.parse(resp));
	    						}
	    					}
	    				}
	    			});
				});
			},
			change: function (event, ui) {
		        if(!ui.item || (ui.item && ui.item.id=='-999')){
					$( ".storeTypeAhead" ).val('');
					$( ".storedataRetain" ).val('');
					return false;
		        }
		    },
	    	minLength: 0,
	    	select: function( event, ui ) {
	    		if(ui.item.id=='-999'){
	    			$( ".storeTypeAhead" ).val('');
					$( ".storedataRetain" ).val('');
					return false;
	    		}
				$( ".storeTypeAhead" ).val( ui.item.label);
				$( ".storedataRetain" ).val(ui.item.id);
				return false;
			}
	    }).focus(function () {
	        $(this).autocomplete("search");
	    }).data( "ui-autocomplete" )._renderItem = function( ul, item ) {
			return $( "<li></li>" )
			.data( "item.autocomplete", item )
			.append( "<a>" + item.label + "</a>" )
			.appendTo( ul );
		};
	});
}
