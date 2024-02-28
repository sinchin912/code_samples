/*
 * These are the data attributes passed.
 */
var currentScript = document.currentScript;
if( currentScript == null) {
	currentScript = getCurrentScript("show_view.js" );
}

var viewId = currentScript.getAttribute('view-id');

/*
 * Used in file:  jsp
 */
$('.search-options').hide();
//var viewId = "${formparam['viewId']}";
if (viewId != "") { // This condition prevents from an exception in case of empty viewId.
	$('#' + viewId ).show();
}
