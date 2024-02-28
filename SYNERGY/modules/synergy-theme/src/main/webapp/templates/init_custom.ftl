<#--
This file allows you to override and define new FreeMarker variables.
-->
<#assign
    url_facebook = page_group.getExpandoBridge().getAttribute("url_facebook", false)
    url_twitter = page_group.getExpandoBridge().getAttribute("url_twitter", false)
    url_linkedin = page_group.getExpandoBridge().getAttribute("url_linkedin", false)
    url_feedback = page_group.getExpandoBridge().getAttribute("url_feedback", false)
    url_privacypolicy = page_group.getExpandoBridge().getAttribute("url_privacypolicy", false)
    url_backgroundimage = page_group.getExpandoBridge().getAttribute("url_backgroundimage", false)
    version_number = page_group.getExpandoBridge().getAttribute("version_number", false)
    tag_line = page_group.getExpandoBridge().getAttribute("tag_line", false)
/>