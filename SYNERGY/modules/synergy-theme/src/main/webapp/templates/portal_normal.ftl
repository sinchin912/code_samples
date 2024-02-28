<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${html_title}</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	<@liferay_util["include"] page=top_head_include />

	<link rel="stylesheet" href="${css_folder}/jquery-ui.min.css">

	<link rel="stylesheet" href="${css_folder}/jquery-ui-timepicker-addon.min.css">

	<link rel="stylesheet" href="${css_folder}/jquery.orgchart.min.css">

	<link rel="stylesheet" href="${css_folder}/jquery.dataTables.min.css">

	<link rel="stylesheet" href="${css_folder}/fontawesome.min.css">
	<link rel="stylesheet" href="${css_folder}/brands.min.css">
	<link rel="stylesheet" href="${css_folder}/solid.min.css">

	<link rel="stylesheet" href="${css_folder}/enjoyhint.css">

	<link rel="stylesheet" href="${css_folder}/progress-wizard.min.css">

	<link rel="stylesheet" href="${css_folder}/lightbox.min.css">

    <script src="${javascript_folder}/jquery-ui.min.js"></script>

    <script src="${javascript_folder}/jquery-ui-timepicker-addon.min.js"></script>

    <script src="${javascript_folder}/jquery.orgchart.min.js"></script>

    <script src="${javascript_folder}/jquery.dataTables.min.js"></script>

    <script src="${javascript_folder}/hammer.min.js"></script>
    <script src="${javascript_folder}/chart.umd.min.js"></script>
    <script src="${javascript_folder}/chartjs-plugin-zoom.js"></script>

    <script src="${javascript_folder}/kinetic.js"></script>
    <script src="${javascript_folder}/jquery.scrollTo.min.js"></script>
    <script src="${javascript_folder}/enjoyhint.min.js"></script>

    <script src="${javascript_folder}/lightbox.min.js"></script>

	<style>
	@media (min-width: 768px) {
        .ds_custombck {
            background: url(${url_backgroundimage}) no-repeat center center;
            background-size: cover;
            background-attachment: fixed;
            min-height: 100vh;
        }
    }
	</style>
</head>

<body class="${css_class}">

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<@liferay.control_menu />

<div class="pt-0 ds_custombck ds_stickyfooter" id="wrapper">
	<header id="banner" role="banner" class="ds_banner">
	    <div class="container" id="heading">
	        <div class="row py-3">
                <div aria-level="1" class="site-title align-items-center autofit-row col-9" role="heading">
                    <a class="${logo_css_class} align-items-center d-md-inline-flex" href="${site_default_url}" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
                        <img alt="${logo_description}" height="${site_logo_height}" src="${site_logo}" width="${site_logo_width}"  class="mr-2 ds-logo" />
                    </a>
                    <#if show_site_name>
                        <div class="d-md-block font-weight-bold h5 mb-0 text-white" >
                            ${site_name}<br>
                            <span class="h6">
                            ${tag_line}
                            </span>
                        </div>
                    </#if>
                    <div class="autofit-col autofit-col-expand mnt-10">
                        <div class="justify-content-md-end mr-4 navbar-form" role="search">
                            <#if is_signed_in>
                             <#--   <@liferay.search_bar default_preferences="${preferences}" /> -->
                            </#if>
                        </div>
                    </div>
                </div>
                <div class="ds_logout col-3">
                        <#if is_signed_in>
                            <div class="ds_welcome_msg">
                                <#if is_signed_in>Welcome, ${user_first_name}</#if>
                            </div>
                            <span>
                            <button type="button" class="btn btn-link p-0 text-white" onclick="siteTour()">Site Tour</button> |  <button type="button" class="btn btn-link p-0 text-white" onclick="location.href='/c/portal/logout'">Logout</button>
                            </span>
                        <#else>
                        </#if>
                </div>
            </div>
		</div>



		<#if has_navigation && is_setup_complete>
		<div class="navbar navbar-classic navbar-expand-md navbar-light py-0 ds_prim_nav">
		    <div class="container ds_bg_transparent">
			    <#include "${full_templates_path}/navigation.ftl" />
			</div>
        </div>
		</#if>
	</header>

	<section id="content" class="container pt-2">
		<h2 class="hide-accessible sr-only" role="heading" aria-level="1">${htmlUtil.escape(the_title)}</h2>
        <#if is_signed_in>
            <input type="hidden" id="isHR" value="${is_Trantor_HR}" />
            <input type="hidden" id="isAdmin" value="${is_Trantor_Admin}" />
            <input type="hidden" id="isFinance" value="${is_Trantor_Finance}" />
            <input type="hidden" id="isIT" value="${is_Trantor_IT}" />
            <input type="hidden" id="isManager" value="${is_Trantor_Manager}" />
            <input type="hidden" id="isLeader" value="${is_Trantor_Leader}" />
            <input type="hidden" id="isCoordinator" value="${is_Trantor_Coordinator}" />
            <input type="hidden" id="isTeamlead" value="${is_Trantor_Teamlead}" />
            <input type="hidden" id="isRecruiter" value="${is_Trantor_Recruiter}" />
            <input type="hidden" id="isPerformance" value="${is_Trantor_Performance}" />
            <input type="hidden" id="isAnnouncement" value="${is_Trantor_Announcement}" />
            <input type="hidden" id="isDocument" value="${is_Trantor_Document}" />
        </#if>

		<#if selectable>
			<@liferay_util["include"] page=content_include />
		<#else>
			${portletDisplay.recycle()}

			${portletDisplay.setTitle(the_title)}

			<@liferay_theme["wrap-portlet"] page="portlet.ftl">
				<@liferay_util["include"] page=content_include />
			</@>
		</#if>
	</section>

	<footer id="footer" role="contentinfo">
        <div class="container d-flex justify-content-between pt-2">
            <p class="text-white">&copy; ${version_number} All Rights Reserved. <a target="_blank" class="text-white" href="${url_privacypolicy}">Privacy Policy</a></p>
            <p>
                <#if is_signed_in>
                    <a href="${url_feedback}" target="_blank" class="ds_footer_btn">Feedback</a>
                </#if>
                <span id="trantor_social">
                    <a href="${url_facebook}" target="_blank" class="icon-facebook-sign ds_footer_icon" title="Like Trantor on Facebook"></a>
                    <a href="${url_linkedin}" target="_blank" class="icon-linkedin-sign ds_footer_icon" title="Connect with Trantor on LinkedIn"></a>
                    <a href="${url_twitter}" target="_blank" class="icon-twitter-sign ds_footer_icon" title="Follow Trantor on Twitter"></a>
                </span>
            </p>
        </div>
	</footer>
</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />
    <!-- Loading Modal -->
    <div class="modal fade" id="loadingModal" tabindex="-1" role="dialog" aria-labelledby="loadingModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
      <div class="modal-dialog" role="document">
        <div class="modal-content border-0" style="background:transparent;padding-top: 200px;">
          <div class="modal-body text-center p-0 border-0">
            <img src="${images_folder}/spinner.gif" style="height:80px">
          </div>
        </div>
      </div>
    </div>

    <!-- Alert Modal -->
    <div class="modal fade" id="alertModal" tabindex="-1" role="dialog" aria-labelledby="alertModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header text-center border-0">
            <h6 class="modal-title text-dark" id="alertModalLabel"></h6>
          </div>
          <div class="modal-body text-center p-0 border-0">
            <p id="alertModalDescription"></p>
          </div>
          <div class="modal-footer d-flex justify-content-center border-0" >
            <button type="button" class="btn btn-outline-info" onclick="dismissAlertModal()">OK</button>
          </div>
        </div>
      </div>
    </div>

    <!-- Confirm Modal -->
    <div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header text-center border-0">
            <h6 class="modal-title text-dark" id="confirmModalLabel"></h6>
          </div>
          <div class="modal-body text-center p-0 border-0">
            <p id="confirmModalDescription"></p>
          </div>
          <div class="modal-footer d-flex justify-content-center border-0" >
            <button type="button" id="confirm_yes" class="btn btn-outline-success mr-5" >Yes</button>
            <button type="button" id="confirm_no" class="btn btn-outline-danger" >No</button>
          </div>
        </div>
      </div>
    </div>

</body>

</html>