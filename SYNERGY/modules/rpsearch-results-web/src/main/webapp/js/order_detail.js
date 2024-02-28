/*
 * These are the data attributes passed.
 */
var currentScript = document.currentScript;
if( currentScript == null) {
       currentScript = getCurrentScript("order_detail.js");
}
var PORTLET_NAMESPACE_ORDERS = currentScript.getAttribute('portlet_namespace');
var ZENDESK_URL = currentScript.getAttribute('zendesk_url');
var reference_number = currentScript.getAttribute('reference_number');

// Setting the order detail page title.
document.title='HBG Retailer Portal: Order ' + reference_number + ' ';

/*
 * On-click event binding for the order-status li tags.
 */
$(document).ready(function() {
	
	$('body').on('click','div#view-order-by-status ul li',function(event) {
		if(event != undefined && event.currentTarget != undefined && event.currentTarget.innerText != undefined){
			var status = event.currentTarget.innerText;
			var divId = status.split(" ").join("");
			var divIdToFocus = $("div.order-status-rows #"+divId).attr('id');
			// console.log(divIdToFocus);
			// console.log($('div#'+divIdToFocus));
			if (divIdToFocus != undefined) {
				$('html, body').animate({ scrollTop: $('div#'+divIdToFocus).offset().top-20 }, 'fast');
			}
		}
	});
	/* For JumpToLinks: On click of qty links from recent-orders, 
		the div#view-order-by-status will have class active.
		This code will be rendered only once document ready.
	*/
	if ($('#view-order-by-status').hasClass('active')) {
		getTrackingDataForAllShipments();
		
		// Un-bind the link if it is open by default.
		$('#statusLink').off('click');
		$('.nav>li.export-link').show();
	} else {
		$('.nav>li.export-link').hide();
	}
	// This is getting used on order-detail page.
	//updateShipmentBlockIDs();
});

// Hide the search bar menu if order-detail page.
// $('.main-wrapper .search-result-bar').hide(); NRP-1351
$('.search-result-bar').parent('.main-wrapper').hide();

$('#customTab a').click(function (link) {
	var innerText = link.currentTarget.innerText.trim();
    if(innerText == 'View Order by Status') {
    	 $('.nav>li.export-link').show();
    } else if(innerText == 'Order Overview'){
    	$('.nav>li.export-link').hide();
    }
});


function openLineItems(orderLineId){
	if($('.'+orderLineId).hasClass('hidden')){
		$('.'+orderLineId).removeClass('hidden');
		$('a.'+orderLineId).find('.caret').addClass('rotate-icon');
		$('.'+orderLineId+':eq(1)').css({'border-top': '1px solid #DCDCDC'})
		$('.'+orderLineId+':last').css({'border': 'none','border-bottom': '3px solid #DCDCDC'});
	}else{
		$('.'+orderLineId).addClass('hidden');
		$('a.'+orderLineId).find('.caret').removeClass('rotate-icon');
		$('a.'+orderLineId).removeClass('hidden');
	}
}

/*
 * This method fetches all the shipment tracking information.
 */

function getTrackingDataForAllShipments() {
	/* $(".trackingDataLoader").each(function() {
		 
		 var trackingInput = $(this).find('img').attr('data-bind');
		 getTrackingDataForShipment(trackingInput, this, 1); 
	 }); */
}


/**
 * This is for View-Shipment link on search-shipment page to redirect on 
 * 	order-detail page.
 */
function updateShipmentBlockIDs() {
	$(".shipment-blocks").each(function() {
		var trackingInput = $(this).find('.trackingNumberClass a').text();
		var existingId = $(this).attr('id');
		//console.log(existingId + '-' + trackingInput);
		if(trackingInput != null) {
			$(this).attr('id', existingId + '-' + trackingInput);
		}
	});
}
/*

*/
/*
 * On-click binding for the view line item link on order detail page.
 */

$('body').on('click','.lineItemsLink',function(event){
	var count = event.currentTarget.lastElementChild.getAttribute('id');
	if($('#show-line-items'+count).hasClass('hidden')){
    	$('#show-line-items'+count).removeClass('hidden');
    	$('#show-line-items'+count).prev().find('.caret').removeClass('rotate-icon');
    }else{
    	$('#show-line-items'+count).addClass('hidden');
    	$('#show-line-items'+count).prev().find('.caret').addClass('rotate-icon');
    }
});


/*
 * This fetches data based on ship-group-id from Zendesk server.
 */
$('body').on('click','div#shipment-status-bs-wizard .request-tracking-block button',function(event) {
	var zendeskValue = $('.request-tracking-block button').attr('data-bind');
 	AUI().use("aui-base", "aui-io-request-deprecated", function(A) {
	    A.io.request(ZENDESK_URL,{
			dataType : "json",
			method : "get",
			data: PORTLET_NAMESPACE_ORDERS + 'shipGroupId='+ '',
			// data : { <portlet:namespace/>shipGroupId : '' },
			on : {
				success : function() {
					resp = this.get("responseData");
					$("div#shipment-status-bs-wizard div#defaultMessageZendesk").addClass("hidden");
					$("div#shipment-status-bs-wizard div#finalMessageZendesk").removeClass("hidden");
				}
			}
		});
	});
});

$('body').off('click').on('click','[id^=initiateZendesk_]',function(event) {
	console.log("initiateZendesk>>>>>>>>>>");
	var obj = $(this);
	$(this).prop("disabled",true);
	$(this).removeClass("btn-theme");
	$(this).addClass("btn-gray");
	
	var objId = $(this).prop("id");
	var id = objId.split("-")[1];
	var refNo= $(this).closest(".statusDiv").find("#refNo").val();
	var poNo= $(this).closest(".statusDiv").find("#poNo").val();
	var invoiceNo= $(this).closest(".statusDiv").find("#invoiceNo").val();
	var shipGroup= $(this).closest(".statusDiv").find("#shipGroup").val();
	var shipment= $(this).closest(".statusDiv").find("#shipment").val();
	var shipmentMethod= $(this).closest(".statusDiv").find("#shipmentMethod").val();
	var shipmentHeaderId= $(this).closest(".statusDiv").find("#shipmentHeaderId").val();	
	var accountName= $(this).closest(".statusDiv").find("#accountName").val();
	var accountNumber= $(this).closest(".statusDiv").find("#accountNumber").val();
	var orderRecievedDate= $(this).closest(".statusDiv").find("#orderRcvdDate").val();
	var shipDate= $(this).closest(".statusDiv").find("#shippDate").val();
	var offerCode= $("#offerCode").val();
	var orderSource= $("#orderSource").val();
	var orderProcessedDate= $("#orderProcessedDate").val();
	var shiptoNumber= $("#shiptoNumber").val();
	var destinationAddress= $("#destinationAddress").val();
	var $data = JSON.parse('{ "'+PORTLET_NAMESPACE_ORDERS+'refNo":"'+refNo+'"' 
 			+ ',"'+PORTLET_NAMESPACE_ORDERS+'poNo":"'+poNo+'"' 
 			+ ',"'+PORTLET_NAMESPACE_ORDERS+'invoiceNo":"'+invoiceNo+'"' 
 			+ ',"'+PORTLET_NAMESPACE_ORDERS+'shipGroup":"'+shipGroup+'"'
 			+ ',"'+PORTLET_NAMESPACE_ORDERS+'shipment":"'+shipment+'"'
 			+ ',"'+PORTLET_NAMESPACE_ORDERS+'shipmentMethod":"'+shipmentMethod+'"'
 			+ ',"'+PORTLET_NAMESPACE_ORDERS+'shipmentHeaderId":"'+shipmentHeaderId+'"'
 			+ ',"'+PORTLET_NAMESPACE_ORDERS+'accountName":"'+accountName+'"'
 			+ ',"'+PORTLET_NAMESPACE_ORDERS+'accountNumber":"'+accountNumber+'"'
 			+ ',"'+PORTLET_NAMESPACE_ORDERS+'orderRecievedDate":"'+orderRecievedDate+'"'
 			+ ',"'+PORTLET_NAMESPACE_ORDERS+'shipDate":"'+shipDate+'"'
 			+ ',"'+PORTLET_NAMESPACE_ORDERS+'offerCode":"'+offerCode+'"'
 			+ ',"'+PORTLET_NAMESPACE_ORDERS+'orderSource":"'+orderSource+'"'
 			+ ',"'+PORTLET_NAMESPACE_ORDERS+'orderProcessedDate":"'+orderProcessedDate+'"'
 			+ ',"'+PORTLET_NAMESPACE_ORDERS+'shiptoNumber":"'+shiptoNumber+'"'
 			+ ',"'+PORTLET_NAMESPACE_ORDERS+'destinationAddress":"'+destinationAddress+'"}');
	
	AUI().use("aui-base", "aui-io-request-deprecated", function(A) {
	    A.io.request(ZENDESK_URL,{
			dataType : "json",
			method : "get",
			data: $data,
			on : {
				success : function() {
					resp = this.get("responseData");
					if(resp && resp.STATUS){
						$(obj).closest('.inner-section').find('#sendRequest-'+id).addClass("hidden");
						$(obj).closest('.inner-section').find('#emailMessage-'+id).removeClass("hidden");
					} else {
						$(obj).parent().siblings(".col-sm-8").text("Failed to connect Zendesk, Please click again Request Tracking link to initiate a request with Customer Service to gather the tracking number and information from the carrier.")	
						 $(obj).removeClass("btn-gray");
						$(obj).addClass("btn-theme");
						 $(obj).prop("disabled", false);
					}
				},
	    		failure  : function () {
	    			 $(obj).parent().siblings(".col-sm-8").text("Failed to connect Zendesk, Please click again Request Tracking link to initiate a request with Customer Service to gather the tracking number and information from the carrier.")	
					 $(obj).removeClass("btn-gray");
					 $(obj).addClass("btn-theme");
					 $(obj).prop("disabled", false);
				}
			}
		});
	});
});
