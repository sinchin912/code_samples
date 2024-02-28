/**
 * This script initiates the Date-range picker for the Search-orders & Search-shipment. 
 */

// Check if date range is coming from last search.
var start = moment().subtract(29, 'days');
var end = moment();
    
var element = $('div[id="daterange"]');
var elementInput = $('div[id="daterange"] input');

//Date Range for Orders, Shipments and Invoices (Last 3 years)
updateDatePicker(element, elementInput, true);
//Date Range for Catalogs without min and max date
element = $('div[id="catalog-daterange"]');
elementInput = $('div[id="catalog-daterange"] input');
updateDatePicker(element, elementInput, false);

function updateDatePicker(element, elementInput, minDateFlag) {
	var isDateEmpty = false;
	if ( !(elementInput.val() != undefined && elementInput.val().length > 0) ) {
		isDateEmpty = true; // Date input is empty.
	} else {
		start = elementInput.val().split("-")[0].trim();
		end = elementInput.val().split("-")[1].trim();
	}

	function cb(start, end) {
		if (isDateEmpty) {
			elementInput.val(start.format('MM/DD/YYYY') + ' - ' + end.format('MM/DD/YYYY'));
		} else {
			elementInput.val(start + ' - ' + end);
		}
    }
	var date = new Date();
	if(minDateFlag){
		element.daterangepicker({
			startDate: start,
			endDate: end,
	    	locale: { cancelLabel: 'Clear' },
	    	ranges: {
		   		 'All Time': [moment().subtract(3, 'year'), moment()],
		   		 'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   		 'Last 6 Months': [moment().subtract(6, 'month'), moment()],
		   		 'Last Year': [moment().subtract(1, 'year'), moment()]
		   	},
	    	"maxDate": (date.getMonth() + 1) + '/' + date.getDate() + '/' +  date.getFullYear(),
	    	"minDate": (date.getMonth() + 1) + '/' + date.getDate() + '/' +  (date.getFullYear() - 2),
	    	"opens": "left",
	    	"applyClass": "applyBtn btn btn-sm" 
	    }, cb);
	}else{
		element.daterangepicker({
			startDate: start,
			endDate: end,
	    	locale: { cancelLabel: 'Clear' },
	    	ranges: {
		   		 'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   		 'Last 6 Months': [moment().subtract(6, 'month'), moment()],
		   		 'Last Year': [moment().subtract(1, 'year'), moment()]
		   	},
		   	"maxDate": new Date(9999, 12 - 1, 31),
	    	"minDate": new Date(1753, 0, 01),
	    	"opens": "left",
	    	"applyClass": "applyBtn btn btn-sm" 
	    }, cb);
	}
	
	
	cb(start, end);
	
	// For the first time, make date field empty.
	if (isDateEmpty) { 
		clearDate();
	}
	isDateEmpty = true;
	
	element.on('cancel.daterangepicker', function(ev, picker) {
		clearDate();
	});
	element.on('apply.daterangepicker', function(ev, picker) {
    	if( $(this).find('input').val() == '' ) {
    		cb(picker.oldStartDate, picker.oldEndDate);
    	}
    });
	
	function clearDate() {
		elementInput.val('');
		if(elementInput.hasClass('osdclass')){
			elementInput.attr("placeholder", "Search within OSD Range");
		}else{
			elementInput.attr("placeholder", "Search within Date Range");
		}
	}
}
