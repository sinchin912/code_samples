<%@ include file="/init.jsp" %>
<input type="hidden" id="topSecret" />
<script>
setInterval(myFunction, 50);
function myFunction(){
    $('#topSecret').parent().parent().parent().css('background-color','transparent');
    $('#topSecret').parent().parent().prev().hide();
    $('.taglib-discussion').hide();
    var is_document = $('#isDocument').val();
    var window_url = $(location).attr('href');
    if(is_document=='false'){
        $('button[title="New"]').hide();
        $('button[title="Toggle Info Panel"]').hide();
        $('[aria-label="Show Actions"]').hide()
        $('input.entry-selector:checkbox').hide();
        if(window_url.includes("knowledge-hub") || window_url.includes("reusable-templates") || window_url.includes("important-forms")){
            $('div.file-entry-actions:first  ul  li:nth-child(2)').show();
            $("[id$='shareButton']").hide();
            $("[id$='sidebarPanel'] .sidebar-body ul").hide();
            $("[id$='TabsSection'] dl").hide();
            $("[id$='TabsSection'] dl").next().hide();
            $("[id$='TabsSection'] dl").prev().prev().prev().prev().hide();
            $("[id$='TabsSection'] dl").prev().prev().prev().hide();
            $("[id$='TabsSection'] dl").prev().hide();
        } else {
            $('div.file-entry-actions:first  ul  li:nth-child(2)').hide();
        }
        $('div.file-entry-actions:first  ul  li:nth-child(3)').hide();
    }
}
</script>