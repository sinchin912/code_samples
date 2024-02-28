/*
 * These are the data attributes passed.
 */
var currentScript = document.currentScript;
if( currentScript == null) {
       currentScript = getCurrentScript("invoice_detail.js");
}
var PORTLET_NAMESPACE_ORDERS = currentScript.getAttribute('portlet_namespace_orders');
var CREDIT_URL = currentScript.getAttribute('credit_url');
var DEBIT_URL = currentScript.getAttribute('debit_url');
var InvoiceHierarchyData = currentScript.getAttribute('hierarchy_data');
var details_url = currentScript.getAttribute('details_url');
var lastClicked = '';
$(document).ready(function() {
	var highlight = false;
	
	var div = $('#all-related-transactions');
	//('.hierarchical-tree-view').addClass('loading-animation');
	var invoice_nbr = div.attr('data-bind');
	var url = InvoiceHierarchyData;
	
	AUI().use("aui-base", "aui-io-request-deprecated", function(A) {
	    A.io.request(url, {
			dataType : "jsonp",
			cache: false,
			method : "get",
			data : {
				 'invoiceNbr' : invoice_nbr.trim()
		    },
		    on : {
				success : function() {
					resp = this.get("responseData");
					if (resp == undefined || resp == null || !resp)
						return false;
					$('.hierarchical-tree-view').removeClass('loading-animation');
					var result = resp; //$(resp).find(id);
					//console.log(result);
					var json = JSON.parse(result);
					//console.log(json);
					var mainStr = '';
					var str = '';
					lastClicked = invoice_nbr.trim();
					
					/* This method is used to traverse through the JSON response. */
					function traverse(json) {
						str += '<li><a href="javascript:void(0)" class="tree-toggle nav-header link-invoice" data-bind='+json.text.INVOICE_NUMBER+'> ';
						if(json.children != undefined && json.children.length > 0) {
							str += ' <i class="fa fa-caret-right" style="padding-right: 0px !important; position: unset !important;"></i> <span>'
									+json.text.CATEGORY_MAPPING+' '+json.text.INVOICE_NUMBER+'</span>' +
									' <small>('+json.text.INVOICE_DATE+')</small></a> '; 
							str += '<ul class="nav nav-list tree">';
							json.children = json.children.sort(function(a, b) {
								return new Date(a.text.INVOICE_DATE).getTime() - new Date(b.text.INVOICE_DATE).getTime();
							});
							for(var i=0; i<json.children.length; i++) {
								traverse(json.children[i]);
							} 
							str += '</ul>';
						} else {
							str += '<b style="margin-right: 12px;">&nbsp</b><span>'+json.text.CATEGORY_MAPPING
									+' '+json.text.INVOICE_NUMBER+'</span> <small>('+json.text.INVOICE_DATE+')</small></a> '; 
						}
						str += '</li>';
					}
					traverse(json[0]);
					mainStr += str;
					//console.log('mainStr:'+mainStr);
					div.html(mainStr);
					//$(id+'-link').off('click'); // Un-bind the click link once pressed.
					
					/* This function  */
					$('.tree-toggle').click(function () {
						//debugger;
						if ( lastClicked == '' || !(lastClicked == $(this).attr('data-bind')) ) {
							var firstElement = $(this)[0].firstElementChild;
							if($(firstElement).hasClass('fa-caret-down') && $(firstElement).is('i')) {
								//alert('down clicked..');
								// As already opened, so close all the open ones under it.
								
								var openedEl = $('a[data-bind="'+$(this).attr('data-bind')+'"]').parentsUntil( "#all-related-transactions" ).find('ul.tree');
								if (openedEl != undefined) {
				                	for(var i=openedEl.length-1; i>=0; i--) {
				                		var firstElement = $(openedEl[i]).parent()[0].firstElementChild.firstElementChild;
				                		var d = $($(openedEl[i]).parent()[0].firstElementChild).attr('data-bind');
				                		
				                		if(d == $(this).attr('data-bind')) {
				                			break;
				                		} else if ( $(firstElement).hasClass('fa-caret-down') && $(firstElement).is('i') && d != $(this).attr('data-bind') ) {
				                			$(openedEl[i]).toggle(200);
					                		adjustCaret(firstElement);
				                		}
				                	}
				                }
								
							} else {
								$(this).parent().children('ul.tree').toggle(200);
								adjustCaret(this.firstElementChild);
							}
						}
						
						//lastClicked = $(this).attr('data-bind');
					});
					
	                $(function () {
	                    $('.tree-toggle').parent().children('ul.tree').toggle(200);
	                });
	                /*$(document).on('click', '.nav-header', function (e, node, id) {
	                	debugger;
	                	if ( lastClicked == '' || !(lastClicked == $(this).attr('data-bind')) ) {
		                	adjustCaret(this.firstElementChild);
	                	}
	                	lastClicked = $(this).attr('data-bind');
		            });*/
	                //debugger;
	                
	                // This logic is for rendering the exact transaction for the first time only.
	                var elements = $('a[data-bind="'+invoice_nbr+'"]').parentsUntil( "#all-related-transactions" ).find('ul.tree');
	                if (elements != undefined) {
	                	for(var i=0; i<elements.length; i++) {
	                		var firstElement = $(elements[i]).parent()[0].firstElementChild.firstElementChild;
	                		var d = $($(elements[i]).parent()[0].firstElementChild).attr('data-bind');
	                		
	                		var fl = false;
	                		$(elements[i]).children().each(function() {
	                			if ($(this.firstElementChild).attr('data-bind') == invoice_nbr) {
	                				fl = true;
	                			}
	                		});
	                		
	                		//console.log((elements[i]));
	                		if (fl || d == invoice_nbr) {
	                			$(elements[i]).toggle(200);
		                		adjustCaret($(elements[i]).parent()[0].firstElementChild.firstElementChild);
		                		break;
	                		} else {
	                			$(elements[i]).toggle(200);
		                		adjustCaret($(elements[i]).parent()[0].firstElementChild.firstElementChild);
	                		}
	                	}
	                }
	                //alert(invoice_nbr);
	                $('.hierarchical-tree-view .tree-toggle span').css('text-decoration','none');
	                $('a[data-bind="'+invoice_nbr+'"] span').css('text-decoration','underline');
				}
			}
		});
	});
	var orderHidenValue = $("#orderDetailHidden").val();
	var claimHiddenValue = $("#claimCallHidden").val();
	if((orderHidenValue.trim() && orderHidenValue.length > 0 && orderHidenValue === 'orderDetailPage') || (claimHiddenValue.trim() && claimHiddenValue.length > 0 && claimHiddenValue === 'claimPage')) {
		 $('html, body').animate({
		        scrollTop: $('#claim-data-container').offset().top
		    }, 'slow');
	}
});
function adjustCaret (firstElement) {
	//alert('asd');
	//debugger;
	if($(firstElement).hasClass('fa-caret-right') && $(firstElement).is('i')) {
        $(firstElement).removeClass('fa-caret-right');
        $(firstElement).addClass('fa-caret-down');
    } else if($(firstElement).is('i')) {
        $(firstElement).removeClass('fa-caret-down');
        $(firstElement).addClass('fa-caret-right');
    }
}
$(document).on('click', '.link-invoice', 
	function getData() {
		var detailsId = '#detail-part';
		var summaryId = '#summary-part';
		var headingId = '#heading-part';
		
		//console.log(this);
		var invoiceNo = $(this).attr('data-bind');
		//alert('invoiceNo:'+invoiceNo);
        $('.hierarchical-tree-view .tree-toggle span').css('text-decoration','none');
        $('a[data-bind="'+invoiceNo+'"] span').css('text-decoration','underline');
		//debugger;
		if ( lastClicked != invoiceNo ) {
			lastClicked = invoiceNo;
			//alert(invoiceNo);
			$(headingId).html('');
			$(headingId).addClass('loading-animation');
			$(summaryId).html('');
			$(detailsId).html('');
			
			AUI().use('aui-base', 'aui-io-request', function(A) {
				A.io.request(details_url, {
					method : 'get',
					data: '_rpsearchresultsweb_WAR_rpsearchresultsweb_invoiceno='+ invoiceNo.trim(),
					on : {
						success : function() {
							resp = this.get("responseData");
							if (resp == undefined || resp == null || !resp)
								return false;
							
							// Heading
							$(headingId).removeClass('loading-animation');
							var resultHeading = $(resp).find(headingId).children();
							if(resultHeading.length < 1) {
								$(headingId).html('Sorry, Information is not available.');
							} else {
								$(headingId).html(resultHeading);
							}
							
							// Summary
							var resultSummary = $(resp).find(summaryId).children();
							if(resultSummary.length > 0) {
								$(summaryId).html(resultSummary);
							}
							
							// Detail
							var resultDetail = $(resp).find(detailsId).children();
							if(resultDetail.length > 0) {
								$(detailsId).html(resultDetail);
							}
							//$(id+'-link').off('click'); // Un-bind the click link once pressed.
						}
					}
				});
			});
		}
	}
);
$(document).on('click', '.memos-link', 
	function getMemosData() {
		var name, url;
		//console.log(this.id);
		if (this.id.startsWith('credit')) {
			name = 'credit', url = CREDIT_URL;
		} else {
			name = 'debit', url = DEBIT_URL;
		}
		//console.log(name+","+url);
		var id = '#'+name+'-memos';
		var invoiceNo = $(id+'-link').attr('data-bind');
		$(id).addClass('loading-animation');
		$(id).html('');
		AUI().use('aui-base', 'aui-io-request', function(A) {
			A.io.request(url, {
				method : 'get',
				on : {
					success : function() {
						resp = this.get("responseData");
						if (resp == undefined || resp == null || !resp)
							return false;
						$(id).removeClass('loading-animation');
						var result = $(resp).find(id);
						//console.log(result);
						$(id).html(result);
						$(id+'-link').off('click'); // Un-bind the click link once pressed.
					}
				}
			});
		});
	}
);
//$('#debit-memos-link').on('click', getMemosData('debit', DEBIT_URL));
//RPC-9:- Show Claim line items
$('body').on('click','.claimLineItemsLink',function(event){
	var count = event.currentTarget.lastElementChild.getAttribute('id');
	
	
	if($('#show-claim-line-items'+count).hasClass('clicked') || $('#show-claim-line-items'+count).hasClass('show-claim-line-items')){
		if($('#show-claim-line-items'+count).hasClass('hidden')){
			$('#show-claim-line-items'+count).removeClass('hidden');
			$('#show-claim-line-items'+count).prev().find('.caret').addClass('rotate-icon');
		}else{
			$('#show-claim-line-items'+count).addClass('hidden');
			$('#show-claim-line-items'+count).prev().find('.caret').removeClass('rotate-icon');
		}
		return;
	}else{
	$('#show-claim-line-items'+count).addClass('clicked');
	$('#show-claim-line-items'+count).append('<div></div>');
	$('#show-claim-line-items'+count+' div').addClass('loading-animation');
	 var headerKey = event.currentTarget.lastElementChild.getAttribute('data-bind');
	 var URL = $('.view-claim-line-items-form'+count+' form').attr( 'action' );
   	 //AUI().use('aui-base', 'aui-io-request', function(A) {
	 $.ajax({
		//A.io.request($('.view-claim-line-items-form'+count+' form').attr( 'action' ), {
			//dataType : 'json',
			method : 'post',
			data: PORTLET_NAMESPACE_ORDERS + 'headerKey='+ headerKey,
			url:URL,
			headers: {
				 'X-CSRF-TOKEN': $('.view-claim-line-items-form'+count+' form').find('input[name=_csrf]').val()
		    },
			//on : {
				success : function(data) {
					resp = data;
					if (resp == undefined || resp == null || !resp)
						return false;
					var result = $(resp).find('#claim-line-items');
					$('#show-claim-line-items'+count).html(result);
					$('#show-claim-line-items'+count).addClass('show-claim-line-items');
					$('#show-claim-line-items'+count).prev().find('.caret').addClass('rotate-icon');
				}
			//}
		//});
	 });
	 }
});
/**
 * This method fetches the data for both Credit & Debit memos.
 * @param name
 */
function showCancelConfirmationModel(claimHeaderKey) {
	$('#cancelClaimHeaderKey').prop('value', claimHeaderKey);
	$("#cancelConfirmationModal").modal({
		backdrop: 'static',
		keyboard: false
	});
}

$('body').on('click','.openCancelClaim',function(e){
	$("#btnCancelClaim").attr("disabled", true);
	$('.openCancelClaim').addClass('disabled');
    $('.cancel-claim-button').addClass('disabled');
	$('#loader-wrapper').show();
	var claimHeaderKey = $('#cancelClaimHeaderKey').val();
	cancelClaimForm(claimHeaderKey);
	e.preventDefault();
});

$('body').on('click','.successCancelClaim',function(e){
	$('.successCancelClaim').addClass('disabled');
	$('#cancelClainSuccessModalData').modal("toggle");
	location.reload(true);
});

$('body').on('click','.errorCancelClaim',function(e){
	$('.errorCancelClaim').addClass('disabled');
	$('#cancelClainErroModalData').modal("toggle");
	location.reload(true);
});

function cancelClaimForm(claimHeaderKey){ 
	var CancelClaim_URL= $('#'+claimHeaderKey).prop('action');
	$.ajax({
		   type: "POST",
		   url:CancelClaim_URL,
		   data: {
				 'claimHeaderKey' : claimHeaderKey
		    },
		    headers: {"X-CSRF-TOKEN": $(" [name='cancelClaimForm'] input[name='_csrf']").val()},
    		   success: function(data) {
					$('#loader-wrapper').hide();
					$('#cancelConfirmationModal').modal("toggle");
					$('.cancel-claim-button').addClass('disabled');
					
					result = data;
					if (result == undefined || result == null || !result) {
						$("#btnCancelClaim").attr("disabled", false);
						$('.openCancelClaim').removeClass('disabled');
						$('.cancel-claim-button').removeClass('disabled');
						return false;
					}
					
					if (result == null) {
						$("#errorClaimMsg").text("Something went wrong. Please try cancelling your claim again");
						$("#cancelClainErroModalData").modal({
							backdrop: 'static',
							keyboard: false
						});
					}
					else if (result.indexOf("success") != -1) {
						$("#cancelClainSuccessModalData").modal({
							backdrop: 'static',
							keyboard: false
						});
					}
					else if (result.indexOf("error") != -1) {
						var arr = result.split(";");
						if (typeof arr[1] !== "undefined") {
							$("#errorClaimMsg").text(arr[1]);
						} else {
							$("#errorClaimMsg").text("Something went wrong. Please try cancelling your claim again");
						}
						$("#cancelClainErroModalData").modal({
							backdrop: 'static',
							keyboard: false
						});
					}
				}
		//	}
		//});
	});
}

$('#customInvoiceTab a').click(function (link) {
	var innerText = link.currentTarget.innerText.trim();
    if(innerText == 'View Shipment') {
    	 $('.nav>li.export-link').show();
    } else if(innerText == 'Invoice Items') {
    	$('.nav>li.export-link').hide();
    }
});