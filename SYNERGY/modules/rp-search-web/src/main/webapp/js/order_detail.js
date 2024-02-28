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
	}
	// This is getting used on order-detail page.
	updateShipmentBlockIDs();
});

// Hide the search bar menu if order-detail page.
// $('.main-wrapper .search-result-bar').hide(); NRP-1351
$('.search-result-bar').parent('.main-wrapper').hide();

if(!$('#view-order-by-status').hasClass('active')){
	$('.aui .nav>li.export-link').hide();	
}
if($('.trackingDataLoader').length>0){
	$('.aui .nav>li.export-link').show();
}
else{
	$('.aui .nav>li.export-link').hide();
}
/*$('#order-overview-link').click(function(){
	$('.aui .nav>li.export-link').hide();
});*/
/*
 * On-click binding for view order by status link on order detail page.
 */
//$(document).on('click','#statusLink', function getProductData() {
//$("#statusLink").click(function(){
 $("#order-status-form").submit(function(e){
//function myFunction() {
//document.getElementById("statusLink").addEventListener("click", function() {
	var refNo = $('#statusLink').attr('data-bind');
	$('#view-order-by-status').addClass('loading-animation');
	$('#view-order-by-status').html('');
	$('#refNo').val(refNo);
	e.preventDefault();
	var form = $(this);
	var url = form.attr('action');
	
	$.ajax({
		   type: "POST",
		   url: url,
		   data: {
		     key: refNo.trim()
		   },
		   dataType: "html",
		   headers: {"X-CSRF-TOKEN": $("input[name='_csrf']").val()},
		   success: function(data) {
				var resp = data;//this.get("responseData");
				if (resp == undefined || resp == null || !resp)
					return false;
				$('#view-order-by-status').removeClass('loading-animation');
				var result = $(resp).find('#view-order-by-status');
				$('#view-order-by-status').html(result);
				$('#statusLink').off('click'); // Un-bind the click link once pressed.
		   },
		   error: function(jqXHR, textStatus, errorThrown){
		        console.log("Something thing went wrong please try again " + textStatus);
		   }
		});
	
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
	 $(".trackingDataLoader").each(function() {
		 
		 var trackingInput = $(this).find('img').attr('data-bind');
		 getTrackingDataForShipment(trackingInput, this, 1); 
	 }); 
}
function getTrackingDataForShipment(trackingInput, obj, countVal) {
	if(!trackingInput) { 
	   	return false;
 	}
	var inputParam = trackingInput;
 	trackingInput =  trackingInput.split(",");
 	var carrierName = trackingInput[0];
 	var orderHeaderId = trackingInput[1];
 	var trackingNumber = trackingInput[2];
 	
 	var orderRecievedDate = trackingInput[3];
 	var shipDate = trackingInput[4];

 	var invoiceNumber = trackingInput[5];
 	var  shipmentHeaderId = trackingInput[6];
 	var suffixedId = '_'+orderHeaderId+'_'+invoiceNumber;
 	if(!carrierName){
 		return false;
 	}
	
 	var count = countVal;
 	var $data = JSON.parse('{ "'+PORTLET_NAMESPACE_ORDERS+'carrier":"'+carrierName+'"' 
 			+ ',"'+PORTLET_NAMESPACE_ORDERS+'trackingNo":"'+trackingNumber+'"' 
 			+ ',"'+PORTLET_NAMESPACE_ORDERS+'shipDate":"'+shipDate+'"' 
 			+ ',"'+PORTLET_NAMESPACE_ORDERS+'orderHeaderId":"'+orderHeaderId+'"'
 			+ ',"'+PORTLET_NAMESPACE_ORDERS+'shipmentHeaderId":"'+shipmentHeaderId+'"}');
 	
 	AUI().use("aui-base", "aui-io-request-deprecated", function(A) {
 		A.io.request($('.tracking-data-form form').attr( 'action' ), {
			dataType : 'json',
			method : 'post',
			data : $data,
			on : {
				success : function() {
					resp = this.get("responseData");
					if (resp == undefined || resp == null || !resp)
						return false;
					var result = $(resp).find('#shipment-data-row');
					var trackingStatus = $(resp).find('.trackingStatus');
					var reloadCount = $('#shipmentReloadCount')[0].value;
					
					$(obj).html(result);
					$(obj).find('#orderRecievedDate').text(orderRecievedDate);
					$(obj).find('#shipDate').text(shipDate);
					var trackingUrlData = $(resp).find('.trackingURL');
					
					
					var isUrlValid = true;
					if (trackingUrlData && trackingUrlData[0]) {
						//var trackingId = trackingUrlData[0].name;
						var trackingUrl = trackingUrlData[0].value;
						//console.log(trackingNumber+','+trackingUrl);
						if ( trackingUrl == 'null' || trackingUrl == undefined 
								|| trackingUrl.trim() == null || trackingUrl.trim().length == 0) {
							isUrlValid = false;
						} else if(trackingNumber && trackingUrl){
							//console.log(trackingNumber+suffixedId);
							$('#'+trackingNumber+suffixedId).prop('href',trackingUrl);
							$('#'+trackingNumber+suffixedId).prop('target','_blank');
						}
					} else {
						isUrlValid = false;
					}
					
					// Based on above block, below code will disable the Anchor tag.
					if(!isUrlValid) {
						// Remove the link in this case.
						// console.log('Url invalid case :Tracking information is unavailable at this time');
						$('#'+trackingNumber + suffixedId).replaceWith(function() {
					        return $("<b id='"+$(this).attr('id')+"' title='Tracking information is unavailable at this time'>"
					        		+ $(this).html() +"</b>");
					    });
					}
					if((!trackingStatus || !trackingStatus[0] || trackingStatus[0].value == 'PRE_TRANSIT') && count <= reloadCount){
						var reloadTime = $('#shipmentReloadTime')[0].value;
						++count;
						$(obj).find('#shipmentLoadingImage').removeClass('hidden');
						setTimeout(function () {
							getTrackingDataForShipment(inputParam, obj, count)
						}, reloadTime);
					}
				}
		    }
		});
	});
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
 * On-click binding for the view line item link on order detail page.
 */
$('body').on('click','.lineItemsLink',function(event){
	var count = event.currentTarget.lastElementChild.getAttribute('id');
	
	
	if($('#show-line-items'+count).hasClass('clicked') || $('#show-line-items'+count).hasClass('show-line-items')){
		if($('#show-line-items'+count).hasClass('hidden')){
			$('#show-line-items'+count).removeClass('hidden');
			$('#show-line-items'+count).prev().find('.caret').addClass('rotate-icon');
		}else{
			$('#show-line-items'+count).addClass('hidden');
			$('#show-line-items'+count).prev().find('.caret').removeClass('rotate-icon');
		}
		return;
	}else{
	$('#show-line-items'+count).addClass('clicked');
	$('#show-line-items'+count).append('<div></div>');
	$('#show-line-items'+count+' div').addClass('loading-animation');
	 var shipmentHeaderId = event.currentTarget.lastElementChild.getAttribute('data-bind');
	
   	AUI().use("aui-base", "aui-io-request-deprecated", function(A) {
		A.io.request($('.view-line-items-form form').attr( 'action' ), {
			dataType : 'json',
			method : 'post',
			data: PORTLET_NAMESPACE_ORDERS + 'shipmentHeaderId='+ shipmentHeaderId,
			//data : { <portlet:namespace/>shipmentHeaderId : shipmentHeaderId },
			on : {
				success : function() {
					resp = this.get("responseData");
					if (resp == undefined || resp == null || !resp)
						return false;
					var result = $(resp).find('#line-items');
					$('#show-line-items'+count).html(result);
					$('#show-line-items'+count).addClass('show-line-items');
					$('#show-line-items'+count).prev().find('.caret').addClass('rotate-icon');
				}
			}
		});
	 });
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

$('body').on('click','#initiateZendesk',function(event) {
	var obj = $(this);
	$(this).prop("disabled",true);
	$(this).removeClass("btn-theme");
	$(this).addClass("btn-gray");
	
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
						$(obj).closest('.inner-section').find('#sendRequest').addClass("hidden");
						$(obj).closest('.inner-section').find('#emailMessage').removeClass("hidden");
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
