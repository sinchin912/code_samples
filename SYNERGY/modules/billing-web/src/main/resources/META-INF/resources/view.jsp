<%@ include file="/init.jsp" %>
<portlet:actionURL name="submitBillingManagerDetails" var="submitBillingManagerDetails"></portlet:actionURL>
<portlet:actionURL name="submitBillingHrDetails" var="submitBillingHrDetails"></portlet:actionURL>
<portlet:actionURL var="submitHrSharedBillingDetails" name="submitHrSharedBillingDetails" ></portlet:actionURL>
<portlet:actionURL name="submitBillingFinanceDetails" var="submitBillingFinanceDetails"></portlet:actionURL>
<portlet:renderURL var="billingRenderURL" windowState="normal"></portlet:renderURL>
<portlet:actionURL var="downloadEmployeeFinanceReportUrl" name="downloadEmployeeFinanceReport"></portlet:actionURL>
<portlet:actionURL var="downloadEmployeeHrReportUrl" name="downloadEmployeeHrReport"></portlet:actionURL>
<portlet:actionURL var="downloadEmployeeManagerReportUrl" name="downloadEmployeeManagerReport"></portlet:actionURL>
<portlet:resourceURL id="getEmployeeDetails" var="getEmployeeDetailsUrl"></portlet:resourceURL>
<portlet:resourceURL id="downloadManual" var="downloadManualUrl"></portlet:resourceURL>
<portlet:resourceURL var="employeeSkillUrl" id="employeeSkill"></portlet:resourceURL>
<style>
    .looklikelink {
        color: #0048bc;
        text-decoration: underline;
    }
    table {
        display: block;
        overflow-x: auto;
        white-space: nowrap;
    }
    .container {
        max-width: 1550px;
    }
    textarea {
    	resize: none;
    }
    .table td {
        border-color: white;
    }
    .highlight td {
        background: yellow;
    }
    .managerHrColFixed1{
            position: absolute !important;
            z-index: 999 !important;
            left : 0 !important;
            width : 50px !important;
            padding : 0px!important;
    }
    .managerHrColFixed2{
            position: absolute !important;
            z-index: 999 !important;
            left : 50px !important;
            width : 100px !important;
            padding : 0px!important;
    }
    .managerHrColFixed3{
            position: absolute !important;
            z-index: 999 !important;
            left : 150px !important;
            width : 250px !important;
            padding : 0px!important;
            border-right: 1px solid !important;
        }
    .managerHrExtraCol{
            left : 400px !important;
            padding : 0px!important;
    }
    .managerHrSharedColFixed1{
            position: absolute !important;
            z-index: 999 !important;
            left : 0 !important;
            width : 70px !important;
            padding : 0px!important;
    }
    .managerHrSharedColFixed2{
            position: absolute !important;
            z-index: 999 !important;
            left : 70px !important;
            width : 50px !important;
            padding : 0px!important;
    }
    .managerHrSharedColFixed3{
            position: absolute !important;
            z-index: 999 !important;
            left : 120px !important;
            width : 100px !important;
            padding : 0px!important;
    }
    .managerHrSharedColFixed4{
            position: absolute !important;
            z-index: 999 !important;
            left : 220px !important;
            width : 250px !important;
            padding : 0px!important;
            border-right: 1px solid !important;
    }
    .managerHrSharedExtraCol{
            left : 470px !important;
            padding : 0px!important;
    }

    .leaderShipColFixed1{
            position: absolute !important;
            z-index: 999 !important;
            left : 0 !important;
            width : 100px !important;
            padding : 0px!important;
    }
    .leaderShipColFixed2{
            position: absolute !important;
            z-index: 999 !important;
            left : 100px !important;
            width : 250px !important;
            padding : 0px!important;
            border-right: 1px solid !important;
    }
    .leaderShipExtraCol{
            left : 350px !important;
            padding : 0px!important;
    }
    .benchColFixed1{
            position: absolute !important;
            z-index: 999 !important;
            left : 0 !important;
            width : 100px !important;
            padding : 0px!important;
    }
    .benchColFixed2{
            position: absolute !important;
            z-index: 999 !important;
            left : 100px !important;
            width : 200px !important;
            padding : 0px!important;
    }
    .benchColFixed3{
            position: absolute !important;
            z-index: 999 !important;
            left : 300px !important;
            width : 200px !important;
            padding : 0px!important;
            border-right: 1px solid !important;
    }
    .benchExtraCol{
            left : 500px !important;
            padding : 0px 10px 0px !important;
    }
    .modal {
        display: none; /* Hidden by default */
        position: fixed; /* Stay in place */
        z-index: 1000; /* Sit on top */
        left: 0;
        top: 0;
        width: 100%; /* Full width */
        height: 100%; /* Full height */
        overflow: auto; /* Enable scroll if needed */
        background-color: rgb(0, 0, 0); /* Fallback color */
        background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
    }
    .loadermodal{
        position: fixed; /* Stay in place */
        z-index: 1000; /* Sit on top */
        left: 0;
        top: 0;
        width: 100%; /* Full width */
        height: 100%; /* Full height */
        overflow: auto; /* Enable scroll if needed */
        background-color: rgb(0, 0, 0); /* Fallback color */
        background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
    }
    /* Modal Content/Box */
    .modal-content {
        background-color: #fefefe;
        margin: 15% auto; /* 15% from the top and centered */
        padding: 20px;
        border: 1px solid #888;
        width: 80%; /* Could be more or less, depending on screen size */
    }

    /* The Close Button */
    .close {
        color: #aaa;
        float: right;
        font-size: 28px;
        font-weight: bold;
    }

    .close:hover, .close:focus {
        color: black;
        text-decoration: none;
        cursor: pointer;
    }

    input.noedit:disabled {
        color: black;
    }

</style>


<liferay-ui:success key="success"
	message="No data available for given month to download" />

<div class="container">
    <c:set var="pageUrl"
    		value="${fn:split(billingRenderURL.toString(),'?')}" />

	<ul class="nav nav-pills mb-3" role="tablist">

		<c:if test="${(managerRole == 'true')}">
			<li class="nav-item"><a class="nav-link" id="manager-tab"
				data-toggle="tab" href="#manager" role="tab" aria-controls="manager"
				aria-selected="true">Manager/Coordinator Billing Tab</a></li>
		</c:if>
		<c:if test="${(hrRole == 'true')}">
			<li class="nav-item"><a class="nav-link" id="hr-tab"
				data-toggle="tab" href="#hr" role="tab" aria-controls="hr"
				aria-selected="true">HR Billing Tab</a></li>
		</c:if>
		<c:if test="${(leadershipRole == 'true')}">
			<li class="nav-item"><a class="nav-link" id="leadership-tab"
				data-toggle="tab" href="#leadership" role="tab"
				aria-controls="leadership" aria-selected="true">Management Billing Tab</a></li>
		</c:if>
		<c:if test="${(fncRole == 'true')}">
        			<li class="nav-item"><a class="nav-link" id="finance-tab"
        				data-toggle="tab" href="#finance" role="tab"
        				aria-controls="finance" aria-selected="true">Finance Billing Tab</a></li>
        </c:if>
        <c:if test="${(benchRole == 'true')}">
            <li class="nav-item"><a class="nav-link" id="bench-tab"
        		data-toggle="tab" href="#bench" role="tab" aria-controls="bench"
        		aria-selected="true">Bench Tab</a></li>
        </c:if>
	</ul>
    <div id="myModal" class="modal">
		<!-- Modal content -->
		<div class="modal-content">
			<div>
				<span class="close">&times;</span>
			</div>
			<div class="form-row">
				<div class="form-group col-md-1 mt-2 text-right">
					<label for="employeeName">Name</label>
				</div>
				<div class="form-group col-md-3">
					<input type="text" class="form-control-plaintext" id="employeeName"
						readonly />
				</div>
				<div class="form-group col-md-1 mt-2 text-right">
					<label for="employeeEcode">Ecode</label>
				</div>
				<div class="form-group col-md-3">
					<input type="text" class="form-control-plaintext" id="employeeEcode"
						readonly />
				</div>
				<div class="form-group col-md-1 mt-2 text-right">
					<label for="employeeAccount">Account</label>
				</div>
				<div class="form-group col-md-3">
					<input type="text" class="form-control-plaintext" id="employeeAccount"
						readonly />
				</div>
			</div>
			<div class="row">
				<div class="col-12">
					<table id="lineItemsSkillTable" class="table table-bordered mb-4 table-sm w-100  d-block d-md-table" >
						<thead class="thead-dark">
							<tr>
                                <th class="text-center align-middle" width="20%">Core Skill</th>
                                <th class="text-center align-middle" width="20%">Sub Skill</th>
                                <th class="text-center align-middle" width="20%">Type</th>
                                <th class="text-center align-middle" width="20%">Level</th>
                                <th class="text-center align-middle" width="20%">Tool</th>
							</tr>
						</thead>
						<tbody id="skillTable" class="table1">
						</tbody>
					</table>
				</div>
			</div>
			<br>
			<div class="row">
                <div class="col-12">
					<table id="lineItemsCertificateTable" class="table table-bordered mb-4 table-sm w-100  d-block d-md-table" >
						<thead class="thead-dark">
							<tr>
                                <th class="text-center align-middle" width="50%">Certificate Category</th>
                                <th class="text-center align-middle" width="50%">Certificate Name</th>
							</tr>
						</thead>
						<tbody id="certificateTable" class="table1">
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	 <div id="loaderModal" class="loadermodal">
    		<!-- Modal content -->
    		<div class="modal-content" style="width:5% !important">
            <img style="width:100% !important"
                       src="<%=request.getContextPath()%>/image/loader.gif">
    		</div>
     </div>
	<div class="tab-content" id="billingTabContent">
		<div class="tab-pane fade" id="manager" role="tabpanel" aria-labelledby="manager-tab">
        	<div id="managerDiv">
                 <div class="card">
                      <div class="card-header">
                           <div class="row">
                                <div class="col-3 pt-2 text-left">
                                    <h3>Viewing Data For Month</h3>
                                </div>
                                <div class="col-2">
                                    <div class="input-group" >
                                        <input type="text" class="form-control" value="" readonly="true" id="dataMonthIdm" required="required" />
                                    </div>
                                </div>
                                <div class="col-4"></div>
                                <div class="col-3">
                                    <form  method="post" action="${downloadEmployeeManagerReportUrl}" >
                                        <input type="hidden" id="requestMonthDownload" value="${selectedMonthYear}" name="<portlet:namespace/>dataMonth" />
                                        <button class="btn btn-secondary btn-block" id="downloadManagerReport" type="submit"><i class="fa fa-download" aria-hidden="true"></i> Download Report</button>
                                    </form>
                                </div>
                           </div>
                      </div>
                      <div class="card-body">
                          <form id="mBillingForm" method="post" onSubmit="return confirmFormSubmission('m');">
                           <input type="hidden" id="managerAction" name="<portlet:namespace/>managerAction" value="1"/>
                           <input type="hidden" id="mvalidateAction" value="" name="<portlet:namespace/>mvalidateAction" />
                                <div class="card">
                                        <div class="card-header">
                                            <div class="row pt-2">
                                                <div class="col-md-10">
                                                    <h3>My Team</h3><p>Please select the row to edit the billing details and click on submit <br>
                                                    Column 'Allocation Status' is applicable only for Bench & Shadow resources
                                                    <br>Green color is for non mandatory columns, Orange color is for mandatory columns, Violet color is for conditional columns </p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card-body">
                                            <input type="hidden" id="actionPerformedm" value="m" name="<portlet:namespace/>actionPerformed" />
                                            <input type="hidden" id="submitDatem" value="${selectedMonthYear}" name="<portlet:namespace/>submitDatem" />
                                            <table
                                                class="table table-sm table-bordered table-striped table-hover table-custom text-center"
                                                id="managerTable">
                                                <thead class="thead-dark">
                                                    <tr>
                                                      <th scope="col" class="managerHrColFixed1" style="padding-top: 14px !important;height:auto;">Select</th>
                                                      <th scope="col" class="managerHrColFixed2" style="padding-top: 14px !important;height: auto;">Ecode</th>
                                                      <th scope="col" class="managerHrColFixed3" style="padding-top: 14px !important;height: auto;">Name</th>
                                                      <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Manager Ecode</th>
                                                      <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Manager Name</th>
                                                      <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Coordinator Ecode</th>
                                                      <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Coordinator Name</th>
                                                      <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Lead Ecode</th>
                                                      <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Lead Name</th>
                                                      <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Designation</th>
                                                      <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">DOJ</th>
                                                      <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Experience</th>
                                                      <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Skill</th>
                                                      <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Account</th>
                                                      <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Project</th>
                                                      <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Billing / Shadow / Bench - Current*
                                                          <i class="fas fa-question text-danger" style="cursor: pointer"
                                                               title=" Billing : Billing resources &#013;Partial Billing : Resources partially billing in your project &#013;Bench : Non billing Resources, not allocated or projected for allocation and available in open pool &#013;Shadow : Non billing resources, who are projected for allocation and/or fixed allocation to project, not available for allocation &#013;Trainee : Non billing Resources, in training, joined as fresher &#013;Internal : Working on inhouse Trantor Projects &#013;Support : Recruitment, ISO, Admin, Finance, IT and HR &#013;Overhead : Non billing Resources, managing account or project &#013;Business Readiness : Non billing resources, hired for anticipated project"></i>
                                                      </th>
                                                      <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Billing / Shadow / Bench - Current + 1 month*
                                                          <i class="fas fa-question text-danger" style="cursor: pointer"
                                                               title=" Billing : Billing resources &#013;Partial Billing : Resources partially billing in your project &#013;Bench : Non billing Resources, not allocated or projected for allocation and available in open pool &#013;Shadow : Non billing resources, who are projected for allocation and/or fixed allocation to project, not available for allocation &#013;Trainee : Non billing Resources, in training, joined as fresher &#013;Internal : Working on inhouse Trantor Projects &#013;Support : Recruitment, ISO, Admin, Finance, IT and HR &#013;Overhead : Non billing Resources, managing account or project &#013;Business Readiness : Non billing resources, hired for anticipated project"></i>
                                                      </th>
                                                      <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Billing / Shadow / Bench - Current + 2 month*
                                                          <i class="fas fa-question text-danger" style="cursor: pointer"
                                                               title=" Billing : Billing resources &#013;Partial Billing : Resources partially billing in your project &#013;Bench : Non billing Resources, not allocated or projected for allocation and available in open pool &#013;Shadow : Non billing resources, who are projected for allocation and/or fixed allocation to project, not available for allocation &#013;Trainee : Non billing Resources, in training, joined as fresher &#013;Internal : Working on inhouse Trantor Projects &#013;Support : Recruitment, ISO, Admin, Finance, IT and HR &#013;Overhead : Non billing Resources, managing account or project &#013;Business Readiness : Non billing resources, hired for anticipated project"></i>
                                                      </th>
                                                      <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Employee Status*</th>
                                                      <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Last Working Date</th>
                                                      <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Allocation Status
                                                        <i class="fas fa-question text-danger" style="cursor: pointer"
                                                            title=" Red - Cannot be allocated to other project &#013;Yellow - Can be allocated to other project after discussion &#013;Green - Available for immediate allocation"></i>
                                                      </th>
                                                      <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Bench Start Date</th>
                                                      <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Shadow Resource Type (for shadow resources)</th>
                                                      <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Shadow Start Date</th>
                                                      <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Billing Start Date</th>
                                                      <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Billing End Date</th>
                                                      <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">% Utilization Per Month</th>

                                                      <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Billable Hours*</th>
                                                      <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Employee Role</th>
                                                      <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Remarks</th>
                                                      <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Vertical*</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="managerTablebBody">
                                                        <c:forEach items="${managerEmployeeList}" var="item"
                                                            varStatus="count">
                                                            <tr>
                                                                <td class="managerHrColFixed1">
                                                                <c:if test="${viewMode == 'edit'}">
                                                                    <input type="hidden" value="${item.billingId}" name="<portlet:namespace/>mbillingId${count.count}"/>
                                                                    <input type="checkbox" style="margin-top: 10px;" value="${item.billingId}" id="mCheckbox${count.count}" name="<portlet:namespace/>mCheckbox" onClick="changeFormToEditMode(this,'nonShared')"/>
                                                                </c:if>
                                                                 <c:if test="${viewMode == 'view'}">
                                                                     <input type="text" class="form-control-plaintext form-control-sm text-center" readonly="readonly" id=""  name=""  value=""/>
                                                                </c:if>
                                                                </td>
                                                                <td class="managerHrColFixed2" data-search="${item.employeeCode}" data-order="${item.employeeCode}"><input type="text" class="form-control-plaintext form-control-sm text-center" readonly="readonly" value="${item.employeeCode}" /></td>
                                                                <td class="managerHrColFixed3" data-search="${item.employeeName}" data-order="${item.employeeName}"><input type="text" class="form-control-plaintext form-control-sm text-center" readonly="readonly" value="${item.employeeName}" /></td>
                                                                <td class="managerHrExtraCol">${item.managerEcode}</td>
                                                                <td class="managerHrExtraCol">${item.managerName}</td>
                                                                <td class="managerHrExtraCol">${item.coordinatorEcode}</td>
                                                                <td class="managerHrExtraCol">${item.coordinatorName}</td>
                                                                <td class="managerHrExtraCol">${item.leadEcode}</td>
                                                                <td class="managerHrExtraCol">${item.leadName}</td>
                                                                <td class="managerHrExtraCol">${item.employeeDesignation}</td>
                                                                <td class="managerHrExtraCol">${item.doj}</td>
                                                                <td class="managerHrExtraCol">${item.experience}</td>
                                                                <td class="managerHrExtraCol">${item.skill}</td>
                                                                <td class="managerHrExtraCol">${item.account}</td>
                                                                <td class="managerHrExtraCol">${item.project}</td>

                                                                <td class="p-0 managerHrExtraCol" data-search="${item.current}" data-order="${item.current}"><input type="hidden" value="${item.current == '-' ? '' : item.current}" id="omCurrent${count.count}"/>
                                                                    <select class="mdb-select md-form form-control form-control-sm w-100" style="background-color: #F7DC6F;"  id="mCurrent${count.count}" name="<portlet:namespace/>mCurrent${item.billingId}" onchange="return validateColumnsOnSelect(this,'nonShared');">
                                                                        <option value="" disabled selected>Select</option>
                                                                        <option ${item.current == 'Bench' ? 'selected="selected"': 'style="display:none"'} value="Bench">Bench</option>
                                                                        <option ${item.current == 'Bench - Exit' ? 'selected="selected"': 'style="display:none"'} value="Bench - Exit">Bench - Exit</option>
                                                                        <option ${item.current == 'Bench - Maternity' ? 'selected="selected"': 'style="display:none"'} value="Bench - Maternity">Bench - Maternity</option>
                                                                        <option ${item.current == 'Billing' ? 'selected="selected"': 'style="display:none"'} value="Billing">Billing</option>
                                                                        <option ${item.current == 'Business Readiness' ? 'selected="selected"': 'style="display:none"'} value="Business Readiness">Business Readiness</option>
                                                                        <option ${item.current == 'Internal' ? 'selected="selected"': 'style="display:none"'} value="Internal">Internal</option>
                                                                        <option ${item.current == 'Internal - Exit' ? 'selected="selected"': 'style="display:none"'} value="Internal - Exit">Internal - Exit</option>
                                                                        <option ${item.current == 'Management' ? 'selected="selected"': 'style="display:none"'} value="Management">Management</option>
                                                                        <option ${item.current == 'Overhead' ? 'selected="selected"': 'style="display:none"'} value="Overhead">Overhead</option>
                                                                        <option ${item.current == 'Partial Billing' ? 'selected="selected"': 'style="display:none"'} value="Partial Billing">Partial Billing</option>
                                                                        <option ${item.current == 'Sales & Marketing' ? 'selected="selected"': 'style="display:none"'} value="Sales & Marketing">Sales & Marketing</option>
                                                                        <option ${item.current == 'Shadow' ? 'selected="selected"': 'style="display:none"'} value="Shadow">Shadow</option>
                                                                        <option ${item.current == 'Shadow - Client Committed/24*7 Support' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Client Committed/24*7 Support">Shadow - Client Committed/24*7 Support</option>
                                                                        <option ${item.current == 'Shadow - Exit' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Exit">Shadow - Exit</option>
                                                                        <option ${item.current == 'Shadow - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Planned Billing">Shadow - Planned Billing</option>
                                                                        <option ${item.current == 'Support' ? 'selected="selected"': 'style="display:none"'} value="Support">Support</option>
                                                                        <option ${item.current == 'Trainee' ? 'selected="selected"': 'style="display:none"'} value="Trainee">Trainee</option>
                                                                        <option ${item.current == 'Trainee - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Trainee - Planned Billing">Trainee - Planned Billing</option>
                                                                   </select>
                                                                </td>
                                                                <td class="p-0 managerHrExtraCol" data-search="${item.currentPlusOne}" data-order="${item.currentPlusOne}"><input type="hidden" value="${item.currentPlusOne == '-' ? '' : item.currentPlusOne}" id="omCurrentPlusOne${count.count}"/>
                                                                    <select class="mdb-select md-form form-control form-control-sm w-100" style="background-color: #F7DC6F;" id="mCurrentPlusOne${count.count}" name="<portlet:namespace/>mCurrentPlusOne${item.billingId}" onchange="return validateColumnsOnSelect(this,'nonShared');">
                                                                        <option value="" disabled selected>Select</option>
                                                                       <option ${item.currentPlusOne == 'Bench' ? 'selected="selected"': 'style="display:none"'} value="Bench">Bench</option>
                                                                       <option ${item.currentPlusOne == 'Bench - Exit' ? 'selected="selected"': 'style="display:none"'} value="Bench - Exit">Bench - Exit</option>
                                                                       <option ${item.currentPlusOne == 'Bench - Maternity' ? 'selected="selected"': 'style="display:none"'} value="Bench - Maternity">Bench - Maternity</option>
                                                                       <option ${item.currentPlusOne == 'Billing' ? 'selected="selected"': 'style="display:none"'} value="Billing">Billing</option>
                                                                       <option ${item.currentPlusOne == 'Business Readiness' ? 'selected="selected"': 'style="display:none"'} value="Business Readiness">Business Readiness</option>
                                                                       <option ${item.currentPlusOne == 'Internal' ? 'selected="selected"': 'style="display:none"'} value="Internal">Internal</option>
                                                                       <option ${item.currentPlusOne == 'Internal - Exit' ? 'selected="selected"': 'style="display:none"'} value="Internal - Exit">Internal - Exit</option>
                                                                       <option ${item.currentPlusOne == 'Management' ? 'selected="selected"': 'style="display:none"'} value="Management">Management</option>
                                                                       <option ${item.currentPlusOne == 'Overhead' ? 'selected="selected"': 'style="display:none"'} value="Overhead">Overhead</option>
                                                                       <option ${item.currentPlusOne == 'Partial Billing' ? 'selected="selected"': 'style="display:none"'} value="Partial Billing">Partial Billing</option>
                                                                       <option ${item.currentPlusOne == 'Sales & Marketing' ? 'selected="selected"': 'style="display:none"'} value="Sales & Marketing">Sales & Marketing</option>
                                                                       <option ${item.currentPlusOne == 'Shadow' ? 'selected="selected"': 'style="display:none"'} value="Shadow">Shadow</option>
                                                                       <option ${item.currentPlusOne == 'Shadow - Client Committed/24*7 Support' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Client Committed/24*7 Support">Shadow - Client Committed/24*7 Support</option>
                                                                       <option ${item.currentPlusOne == 'Shadow - Exit' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Exit">Shadow - Exit</option>
                                                                       <option ${item.currentPlusOne == 'Shadow - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Planned Billing">Shadow - Planned Billing</option>
                                                                       <option ${item.currentPlusOne == 'Support' ? 'selected="selected"': 'style="display:none"'} value="Support">Support</option>
                                                                       <option ${item.currentPlusOne == 'Trainee' ? 'selected="selected"': 'style="display:none"'} value="Trainee">Trainee</option>
                                                                       <option ${item.currentPlusOne == 'Trainee - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Trainee - Planned Billing">Trainee - Planned Billing</option>
                                                                   </select>
                                                                </td>
                                                                <td class="p-0 managerHrExtraCol" data-search="${item.currentPlusTwo}" data-order="${item.currentPlusTwo}"><input type="hidden" value="${item.currentPlusTwo == '-' ? '' : item.currentPlusTwo}" id="omCurrentPlusTwo${count.count}"/>
                                                                    <select class="mdb-select md-form form-control form-control-sm w-100" style="background-color: #F7DC6F;" id="mCurrentPlusTwo${count.count}" name="<portlet:namespace/>mCurrentPlusTwo${item.billingId}" onchange="return validateColumnsOnSelect(this,'nonShared');">
                                                                        <option value="" disabled selected>Select</option>
                                                                          <option ${item.currentPlusTwo == 'Bench' ? 'selected="selected"': 'style="display:none"'} value="Bench">Bench</option>
                                                                          <option ${item.currentPlusTwo == 'Bench - Exit' ? 'selected="selected"': 'style="display:none"'} value="Bench - Exit">Bench - Exit</option>
                                                                          <option ${item.currentPlusTwo == 'Bench - Maternity' ? 'selected="selected"': 'style="display:none"'} value="Bench - Maternity">Bench - Maternity</option>
                                                                          <option ${item.currentPlusTwo == 'Billing' ? 'selected="selected"': 'style="display:none"'} value="Billing">Billing</option>
                                                                          <option ${item.currentPlusTwo == 'Business Readiness' ? 'selected="selected"': 'style="display:none"'} value="Business Readiness">Business Readiness</option>
                                                                          <option ${item.currentPlusTwo == 'Internal' ? 'selected="selected"': 'style="display:none"'} value="Internal">Internal</option>
                                                                          <option ${item.currentPlusTwo == 'Internal - Exit' ? 'selected="selected"': 'style="display:none"'} value="Internal - Exit">Internal - Exit</option>
                                                                          <option ${item.currentPlusTwo == 'Management' ? 'selected="selected"': 'style="display:none"'} value="Management">Management</option>
                                                                          <option ${item.currentPlusTwo == 'Overhead' ? 'selected="selected"': 'style="display:none"'} value="Overhead">Overhead</option>
                                                                          <option ${item.currentPlusTwo == 'Partial Billing' ? 'selected="selected"': 'style="display:none"'} value="Partial Billing">Partial Billing</option>
                                                                          <option ${item.currentPlusTwo == 'Sales & Marketing' ? 'selected="selected"': 'style="display:none"'} value="Sales & Marketing">Sales & Marketing</option>
                                                                          <option ${item.currentPlusTwo == 'Shadow' ? 'selected="selected"': 'style="display:none"'} value="Shadow">Shadow</option>
                                                                          <option ${item.currentPlusTwo == 'Shadow - Client Committed/24*7 Support' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Client Committed/24*7 Support">Shadow - Client Committed/24*7 Support</option>
                                                                          <option ${item.currentPlusTwo == 'Shadow - Exit' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Exit">Shadow - Exit</option>
                                                                          <option ${item.currentPlusTwo == 'Shadow - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Planned Billing">Shadow - Planned Billing</option>
                                                                          <option ${item.currentPlusTwo == 'Support' ? 'selected="selected"': 'style="display:none"'} value="Support">Support</option>
                                                                          <option ${item.currentPlusTwo == 'Trainee' ? 'selected="selected"': 'style="display:none"'} value="Trainee">Trainee</option>
                                                                          <option ${item.currentPlusTwo == 'Trainee - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Trainee - Planned Billing">Trainee - Planned Billing</option>
                                                                   </select>
                                                                </td>
                                                                <td class="p-0 managerHrExtraCol" data-search="${item.employeeStatus}" data-order="${item.employeeStatus}"><input type="hidden" value="${item.employeeStatus == '-' ? '' : item.employeeStatus}" id="omemployeeStatus${count.count}"/>
                                                                    <select class="mdb-select md-form form-control form-control-sm w-100" style="background-color: #F7DC6F;" id="memployeeStatus${count.count}" name="<portlet:namespace/>memployeeStatus${item.billingId}" onchange="return validateColumnsOnSelect(this,'nonShared');">
                                                                        <option value="" disabled selected>Select</option>
                                                                        <option ${item.employeeStatus == 'Active' ? 'selected="selected"': 'style="display:none"'} value="Active">Active</option>
                                                                        <option ${item.employeeStatus == 'Resigned' ? 'selected="selected"': 'style="display:none"'} value="Resigned">Resigned</option>
                                                                        <option ${item.employeeStatus == 'Under Monitoring' ? 'selected="selected"': 'style="display:none"'} value="Under Monitoring">Under Monitoring</option>
                                                                        <option ${item.employeeStatus == 'Maternity Leave' ? 'selected="selected"': 'style="display:none"'} value="Maternity Leave">Maternity Leave</option>
                                                                        <option ${item.employeeStatus == 'Ramp up period' ? 'selected="selected"': 'style="display:none"'} value="Ramp up period">Ramp up period</option>
                                                                        <option ${item.employeeStatus == 'Sabbatical Leave' ? 'selected="selected"': 'style="display:none"'} value="Sabbatical Leave">Sabbatical Leave</option>
                                                                        <option ${item.employeeStatus == 'Deferred Billing' ? 'selected="selected"': 'style="display:none"'} value="Deferred Billing">Deferred Billing</option>
                                                                   </select>
                                                                </td>
                                                                <td class="p-0 managerHrExtraCol"><input type="hidden" <c:if test="${item.lastWorkingDate != '-'}">value="${item.lastWorkingDate}" </c:if> id="omlastWorkingDate${count.count}"/>
                                                                    <input type="date" class ="form-control form-control-sm" style="background-color: #D7BDE2;" style="cursor: pointer" onkeydown="return false" readonly="readonly"  id="mlastWorkingDate${count.count}" name="<portlet:namespace/>mlastWorkingDate${item.billingId}" <c:if test="${item.lastWorkingDate != '-'}">value="${item.lastWorkingDate}" </c:if> />
                                                                </td>
                                                                <td class="p-0 managerHrExtraCol" data-search="${item.allocationStatus}" data-order="${item.allocationStatus}"><input type="hidden" value="${item.allocationStatus == '-' ? '' : item.allocationStatus}" id="omAllocationStatus${count.count}"/>
                                                                    <select class="mdb-select md-form form-control form-control-sm w-100" style="background-color: #D7BDE2;" id="mAllocationStatus${count.count}" name="<portlet:namespace/>mAllocationStatus${item.billingId}">
                                                                        <option value="" disabled selected>Select</option>
                                                                        <option ${item.allocationStatus == 'Red' ? 'selected="selected"': 'style="display:none"'} value="Red">Red</option>
                                                                        <option ${item.allocationStatus == 'Yellow' ? 'selected="selected"': 'style="display:none"'} value="Yellow">Yellow</option>
                                                                        <option ${item.allocationStatus == 'Green' ? 'selected="selected"': 'style="display:none"'} value="Green">Green</option>
                                                                   </select>
                                                                </td>
                                                                <td class="p-0 managerHrExtraCol"><input type="hidden" <c:if test="${item.benchStartDate != '-'}">value="${item.benchStartDate}" </c:if> id="ombenchStartDate${count.count}"/>
                                                                    <input type="date" class ="form-control form-control-sm" style="background-color: #D7BDE2;" style="cursor: pointer" readonly="readonly" onkeydown="return false" id="mbenchStartDate${count.count}"  name="<portlet:namespace/>mbenchStartDate${item.billingId}" <c:if test="${item.benchStartDate != '-'}">value="${item.benchStartDate}" </c:if> />
                                                                </td>
                                                                <td class="p-0 managerHrExtraCol"><input type="hidden" value="${item.shadowResourceType == '-' ? '' : item.shadowResourceType}" id="omShadowResourceType${count.count}"/>
                                                                    <select class="mdb-select md-form form-control form-control-sm w-100" style="background-color: #D7BDE2;" id="mShadowResourceType${count.count}" name="<portlet:namespace/>mShadowResourceType${item.billingId}">
                                                                        <option value="" disabled selected>Select</option>
                                                                        <option ${item.shadowResourceType == 'Client Requested' ? 'selected="selected"': 'style="display:none"'} value="Client Requested">Client Requested</option>
                                                                        <option ${item.shadowResourceType == 'Strategic Hiring' ? 'selected="selected"': 'style="display:none"'} value="Strategic Hiring">Strategic Hiring</option>
                                                                        <option ${item.shadowResourceType == 'Replacement' ? 'selected="selected"': 'style="display:none"'} value="Replacement">Replacement</option>
                                                                   </select>
                                                                </td>
                                                                <td class="p-0 managerHrExtraCol"><input type="hidden" <c:if test="${item.shadowStartDate != '-'}">value="${item.shadowStartDate}"</c:if> id="omshadowStartDate${count.count}"/>
                                                                    <input type="date" class ="form-control form-control-sm" style="background-color: #D7BDE2;" style="cursor: pointer" readonly="readonly" onkeydown="return false" id="mshadowStartDate${count.count}"  name="<portlet:namespace/>mshadowStartDate${item.billingId}" <c:if test="${item.shadowStartDate != '-'}">value="${item.shadowStartDate}"</c:if> />
                                                                </td>

                                                                <td class="p-0 managerHrExtraCol"><input type="hidden" <c:if test="${item.billingStartDate != '-'}"> value="${item.billingStartDate}" </c:if> id="ombillingStartDate${count.count}"/>
                                                                    <input type="date" class ="form-control form-control-sm" style="background-color: #D7BDE2;" style="cursor: pointer" readonly="readonly" onkeydown="return false" id="mbillingStartDate${count.count}" name="<portlet:namespace/>mbillingStartDate${item.billingId}" <c:if test="${item.billingStartDate != '-'}"> value="${item.billingStartDate}" </c:if> />
                                                                </td>
                                                                <td class="p-0 managerHrExtraCol"><input type="hidden" <c:if test="${item.billingEndDate != '-'}">value="${item.billingEndDate}" </c:if> id="ombillingEndDate${count.count}"/>
                                                                    <input type="date" class ="form-control form-control-sm" style="background-color: #D7BDE2;" style="cursor: pointer" readonly="readonly" onkeydown="return false" id="mbillingEndDate${count.count}" name="<portlet:namespace/>mbillingEndDate${item.billingId}" <c:if test="${item.billingEndDate != '-'}">value="${item.billingEndDate}" </c:if> />
                                                                </td>
                                                                <td class="p-0 managerHrExtraCol"><input type="hidden" value="${item.percentUtilization == '-' ? '100' : item.percentUtilization}" id="ompercentUtilization${count.count}"/>
                                                                    <input type="number" class="form-control form-control-sm" style="background-color: #D7BDE2;"
                                                                         readonly="readonly" max="100" min="0" id="mpercentUtilization${count.count}" value="${item.percentUtilization == '-' ? '100' : item.percentUtilization}"  name="<portlet:namespace/>mpercentUtilization${item.billingId}">
                                                                </td>

                                                                <td class="p-0 managerHrExtraCol"><input type="hidden" value="${item.billableHours == '-' ? '' : item.billableHours}" id="ombillableHours${count.count}"/><input type="number"
                                                                            class="form-control form-control-sm" min="0" step="0.01" style="background-color: #F7DC6F;"
                                                                            readonly="readonly" id="mbillableHours${count.count}" name="<portlet:namespace/>mbillableHours${item.billingId}"  value="${item.billableHours == '-' ? '' : item.billableHours}" />
                                                                </td>
                                                                <td class="p-0 managerHrExtraCol"><input type="hidden" value="${item.employeeRole == '-' ? '' : item.employeeRole}" id="omEmployeeRole${count.count}"/><input type="text"
                                                                            class="form-control form-control-sm" style="background-color: palegreen;" maxlength="75"
                                                                            onblur="$(this).val($(this).val().trim())" readonly="readonly" id="mEmployeeRole${count.count}" name="<portlet:namespace/>mEmployeeRole${item.billingId}"  value="${item.employeeRole == '-' ? '' : item.employeeRole}" />
                                                                </td>
                                                                <td class="p-0 managerHrExtraCol"><input type="hidden" value="${item.remarks == '-' ? '' : item.remarks}" id="omRemarks${count.count}"/><input type="text"
                                                                    class="form-control form-control-sm" style="background-color: palegreen;" maxlength="200"
                                                                    onblur="$(this).val($(this).val().trim())" readonly="readonly" id="mRemarks${count.count}" title="${item.remarks}" name="<portlet:namespace/>mRemarks${item.billingId}"  value="${item.remarks == '-' ? '' : item.remarks}" />
                                                                </td>
                                                                <td class="p-0 managerHrExtraCol"><input type="hidden" value="${item.vertical == '-' ? '' : item.vertical}" id="omVertical${count.count}"/>
                                                                    <select class="mdb-select md-form form-control form-control-sm w-100" style="background-color: #F7DC6F;" id="mVertical${count.count}" name="<portlet:namespace/>mVertical${item.billingId}">
                                                                        <option value="" disabled selected>Select</option>
                                                                        <option ${item.vertical == 'BBU' ? 'selected="selected"': 'style="display:none"'} value="BBU">BBU</option>
                                                                        <option ${item.vertical == 'Captive CoE' ? 'selected="selected"': 'style="display:none"'} value="Captive CoE">Captive CoE</option>
                                                                        <option ${item.vertical == 'Cloud' ? 'selected="selected"': 'style="display:none"'} value="Cloud">Cloud</option>
                                                                        <option ${item.vertical == 'Enterprise' ? 'selected="selected"': 'style="display:none"'} value="Enterprise">Enterprise</option>
                                                                        <option ${item.vertical == 'Other' ? 'selected="selected"': 'style="display:none"'} value="Other">Other</option>
                                                                        <option ${item.vertical == 'Sales' ? 'selected="selected"': 'style="display:none"'} value="Sales">Sales</option>
                                                                        <option ${item.vertical == 'Support' ? 'selected="selected"': 'style="display:none"'} value="Support">Support</option>
                                                                   </select>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                </tbody>
                                            </table>
                                            <br></br>
                                        </div>
                                </div>
                                <div class="card">
                                        <div class="card-header">
                                            <div class="row pt-2">
                                                <div class="col">
                                                    <h3>Partial/Shared Resource</h3>
                                                    <p>Please add team member details who are working in Partial or Shared capacity</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card-body">
                                            <input type="hidden" id="mExistingRows"
                                                    value="" name="<portlet:namespace/>existingRows" />
                                            <input type="hidden" id="mTotalRows"
                                                    value="" name="<portlet:namespace/>totalRows" />
                                            <input type="hidden"
                                                id="actionPerformedSharedm" value="m"
                                                                name="<portlet:namespace/>actionPerformedShared" />
                                            <input type="hidden"
                                                id="submitDateSharedm" value="${selectedMonthYear}"
                                                            name="<portlet:namespace/>submitDateSharedm" />
                                            <table
                                                class="table table-sm table-bordered table-striped table-hover table-custom text-center"
                                                id="managerSharedTable">
                                                <thead class="thead-dark">
                                                    <tr>
                                                      <th scope="col" class="managerHrSharedColFixed1" style="padding-top: 14px !important;height: auto;">Select</th>
                                                      <th scope="col" class="managerHrSharedColFixed2" style="padding-top: 14px !important;height: auto;">Delete</th>
                                                      <th scope="col" class="managerHrSharedColFixed3" style="padding-top: 14px !important;height: auto;">Ecode*</th>
                                                      <th scope="col" class="managerHrSharedColFixed4" style="padding-top: 14px !important;height: auto;">Employee Name</th>
                                                      <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Manager Ecode*</th>
                                                      <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Manager Name*</th>
                                                      <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Coordinator Ecode</th>
                                                      <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Coordinator Name</th>
                                                      <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Lead Ecode</th>
                                                      <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Lead Name</th>
                                                      <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Employee Designation</th>
                                                      <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Employee DOJ</th>
                                                      <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Employee Experience</th>
                                                      <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Employee Skill</th>
                                                      <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Employee Account*</th>
                                                      <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Employee Project*</th>

                                                      <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Billing / Shadow / Bench - Current*
                                                            <i class="fas fa-question text-danger" style="cursor: pointer"
                                                                    title=" Billing : Billing resources &#013;Partial Billing : Resources partially billing in your project &#013;Bench : Non billing Resources, not allocated or projected for allocation and available in open pool &#013;Shadow : Non billing resources, who are projected for allocation and/or fixed allocation to project, not available for allocation &#013;Trainee : Non billing Resources, in training, joined as fresher &#013;Internal : Working on inhouse Trantor Projects &#013;Support : Recruitment, ISO, Admin, Finance, IT and HR &#013;Overhead : Non billing Resources, managing account or project &#013;Business Readiness : Non billing resources, hired for anticipated project"></i>
                                                      </th>
                                                      <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Billing / Shadow / Bench - Current + 1 month*
                                                            <i class="fas fa-question text-danger" style="cursor: pointer"
                                                                    title=" Billing : Billing resources &#013;Partial Billing : Resources partially billing in your project &#013;Bench : Non billing Resources, not allocated or projected for allocation and available in open pool &#013;Shadow : Non billing resources, who are projected for allocation and/or fixed allocation to project, not available for allocation &#013;Trainee : Non billing Resources, in training, joined as fresher &#013;Internal : Working on inhouse Trantor Projects &#013;Support : Recruitment, ISO, Admin, Finance, IT and HR &#013;Overhead : Non billing Resources, managing account or project &#013;Business Readiness : Non billing resources, hired for anticipated project"></i>
                                                      </th>
                                                      <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Billing / Shadow / Bench - Current + 2 month*
                                                            <i class="fas fa-question text-danger" style="cursor: pointer"
                                                                    title=" Billing : Billing resources &#013;Partial Billing : Resources partially billing in your project &#013;Bench : Non billing Resources, not allocated or projected for allocation and available in open pool &#013;Shadow : Non billing resources, who are projected for allocation and/or fixed allocation to project, not available for allocation &#013;Trainee : Non billing Resources, in training, joined as fresher &#013;Internal : Working on inhouse Trantor Projects &#013;Support : Recruitment, ISO, Admin, Finance, IT and HR &#013;Overhead : Non billing Resources, managing account or project &#013;Business Readiness : Non billing resources, hired for anticipated project"></i>
                                                      </th>
                                                      <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Employee Status*</th>
                                                      <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Last Working Date</th>
                                                      <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Bench Start Date</th>
                                                      <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Shadow Resource Type (for shadow resources)</th>
                                                      <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Shadow Start Date</th>
                                                      <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Billing Start Date</th>
                                                      <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Billing End Date</th>
                                                      <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">% Utilization Per Month</th>

                                                      <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Remarks</th>
                                                      <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Billable Hours*</th>
                                                      <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Employee Role</th>
                                                      <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Vertical*</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="managerSharedTablebBody">
                                                        <c:forEach items="${managerSharedEmployeeList}" var="item"
                                                            varStatus="count">
                                                            <tr class="rowclass">
                                                                <td class="managerHrSharedColFixed1">
                                                                <c:if test="${viewMode == 'edit'}">
                                                                    <input type="hidden" value="${item.employeeCode}" id="mSharedEcodeId${count.index}"/>
                                                                    <input type="checkbox" projectUpdated="${item.projectUpdated}"
                                                                       style="margin-top: 10px;"  value="${item.billingId}" id="mSharedCheckbox${count.index}" name="<portlet:namespace/>mSharedCheckbox${count.index}" onClick="changeFormToEditMode(this,'shared')"/>
                                                                </c:if>
                                                                <c:if test="${viewMode == 'view'}">
                                                                     <input type="text" class="form-control-plaintext form-control-sm text-center" readonly="readonly" id=""  name=""  value=""/>
                                                                </c:if>
                                                                </td>
                                                                <td class="p-0 managerHrSharedColFixed2">
                                                                    <c:if test="${viewMode == 'edit'}">
                                                                        <c:choose>
                                                                          <c:when test="${!item.projectUpdated}">
                                                                           <button type="submit" onclick="validateActionPerformed(1,'m')" value="${item.billingId}" name="<portlet:namespace/>mdeleting" class="btn-danger">
                                                                               <i class="far fa-trash-alt" style="cursor: pointer"></i>
                                                                           </button>
                                                                          </c:when>
                                                                          <c:otherwise>
                                                                            <button type="button" disabled value="${item.billingId}" name="<portlet:namespace/>mdeleting" class="btn-secondary" title="Account has been changed for this employee, kindly update billing information before the deadline.">
                                                                               <i class="far fa-trash-alt" ></i>
                                                                            </button>
                                                                          </c:otherwise>
                                                                        </c:choose>
                                                                    </c:if>
                                                                    <c:if test="${viewMode == 'view'}">
                                                                         <input type="text" class="form-control-plaintext form-control-sm text-center" readonly="readonly" id=""  name=""  value=""/>
                                                                    </c:if>
                                                                </td>
                                                                <td class="managerHrSharedColFixed3" data-search="${item.employeeCode}" data-order="${item.employeeCode}"><input type="text" class="form-control-sm form-control-plaintext pl-3" readonly="readonly" name="<portlet:namespace/>mSharedEcode${count.index}" value="${item.employeeCode}" /></td>
                                                                <td class="managerHrSharedColFixed4" data-search="${item.employeeName}" data-order="${item.employeeName}"><input type="text" class="form-control-plaintext form-control-sm text-center" readonly="readonly" value="${item.employeeName}" /></td>
                                                                <td class="p-0 managerHrSharedExtraCol"><input type="hidden" value="${item.managerEcode}" id="omSharedManagerEcode${count.index}"/>
                                                                <input type="text" <c:choose> <c:when  test="${item.projectUpdated}"> class="noedit form-control-sm form-control-plaintext pl-3" </c:when>
                                                                                   <c:otherwise> class="noedit form-control form-control-sm" </c:otherwise> </c:choose> maxlength="75"
                                                                            onblur="fetchAndValidateEmployeeDetails(this)" disabled id="mSharedManagerEcode${count.index}" name="<portlet:namespace/>mSharedManagerEcode${count.index}"  value="${item.managerEcode}"  required="required" />
                                                                </td>
                                                                <td class="p-0 managerHrSharedExtraCol"><input type="hidden" value="${item.managerName}" id="omSharedManagerName${count.index}"/>
                                                                <input type="text" <c:choose> <c:when  test="${item.projectUpdated}"> class="form-control-sm form-control-plaintext pl-3" </c:when>
                                                                                  <c:otherwise> class="form-control form-control-sm" </c:otherwise> </c:choose> maxlength="75"
                                                                             readonly="readonly" id="mSharedManagerName${count.index}" name="<portlet:namespace/>mSharedManagerName${count.index}"  value="${item.managerName}" required="required" />
                                                                </td>

                                                                <td class="p-0 managerHrSharedExtraCol"><input type="hidden" value="${item.coordinatorEcode}" id="omSharedCoordinatorEcode${count.index}"/>
                                                                <input type="text" <c:choose> <c:when  test="${item.projectUpdated}"> class="noedit form-control-sm form-control-plaintext pl-3" </c:when>
                                                                             <c:otherwise> class="noedit form-control form-control-sm" </c:otherwise> </c:choose> maxlength="75"
                                                                            onblur="fetchAndValidateEmployeeDetails(this)" disabled id="mSharedCoordinatorEcode${count.index}" name="<portlet:namespace/>mSharedCoordinatorEcode${count.index}"  value="${item.coordinatorEcode}" />
                                                                </td>
                                                                <td class="p-0 managerHrSharedExtraCol"><input type="hidden" value="${item.coordinatorName}" id="omSharedCoordinatorName${count.index}"/>
                                                               <input type="text" <c:choose> <c:when  test="${item.projectUpdated}"> class="form-control-sm form-control-plaintext pl-3" </c:when>
                                                                <c:otherwise> class="form-control form-control-sm" </c:otherwise> </c:choose> maxlength="75"
                                                                            readonly="readonly" id="mSharedCoordinatorName${count.index}" name="<portlet:namespace/>mSharedCoordinatorName${count.index}"  value="${item.coordinatorName}"/>
                                                                </td>
                                                                <td class="p-0 managerHrSharedExtraCol"><input type="hidden" value="${item.leadEcode}" id="omSharedleadEcode${count.index}"/>
                                                                <input type="text"<c:choose> <c:when  test="${item.projectUpdated}"> class="noedit form-control-sm form-control-plaintext pl-3" </c:when>
                                                                              <c:otherwise> class="noedit form-control form-control-sm" </c:otherwise> </c:choose> maxlength="75"
                                                                            onblur="fetchAndValidateEmployeeDetails(this)" disabled id="mSharedleadEcode${count.index}" name="<portlet:namespace/>mSharedleadEcode${count.index}"  value="${item.leadEcode}"  />
                                                                </td>
                                                                <td class="p-0 managerHrSharedExtraCol"><input type="hidden" value="${item.leadName}" id="omSharedleadName${count.index}"/>
                                                                <input type="text" <c:choose> <c:when  test="${item.projectUpdated}"> class="form-control-sm form-control-plaintext pl-3" </c:when>
                                                                               <c:otherwise> class="form-control form-control-sm" </c:otherwise> </c:choose> maxlength="75"
                                                                            readonly="readonly" id="mSharedleadName${count.index}" name="<portlet:namespace/>mSharedleadName${count.index}"  value="${item.leadName}"  />
                                                                </td>
                                                                <td class="managerHrSharedExtraCol">${item.employeeDesignation}</td>
                                                                <td class="managerHrSharedExtraCol">${item.doj}</td>
                                                                <td class="managerHrSharedExtraCol">${item.experience}</td>
                                                                <td class="managerHrSharedExtraCol">${item.skill}</td>
                                                                <td class="managerHrSharedExtraCol"><input type="hidden" value="${item.account}" id="omSharedAccount${count.index}"/>
                                                                <input type="text" <c:choose> <c:when  test="${item.projectUpdated}"> class="form-control-sm form-control-plaintext pl-3" </c:when>
                                                                          <c:otherwise> class="form-control form-control-sm" </c:otherwise> </c:choose> maxlength="75"
                                                                           onblur="$(this).val($(this).val().trim())" readonly="readonly" id="mSharedAccount${count.index}" name="<portlet:namespace/>mSharedAccount${count.index}"  value="${item.account}" required="required"/>
                                                                </td>
                                                                <td class="managerHrSharedExtraCol"><input type="hidden" value="${item.project}" id="omSharedProject${count.index}"/>
                                                                <input type="text" <c:choose> <c:when  test="${item.projectUpdated}"> class="form-control-sm form-control-plaintext pl-3" </c:when>
                                                                 <c:otherwise> class="form-control form-control-sm" </c:otherwise> </c:choose> maxlength="75"
                                                                           onblur="$(this).val($(this).val().trim())" readonly="readonly" id="mSharedProject${count.index}" name="<portlet:namespace/>mSharedProject${count.index}"  value="${item.project}" required="required"/>
                                                                </td>
                                                                <td class="p-0 managerHrSharedExtraCol" data-search="${item.current}" data-order="${item.current}"><input type="hidden" value="${item.current == '-' ? '' : item.current}" id="omSharedCurrent${count.index}"/>
                                                                    <select class="mdb-select md-form form-control form-control-sm w-100" id="mSharedCurrent${count.index}" name="<portlet:namespace/>mSharedCurrent${count.index}" onchange="return validateColumnsOnSelect(this,'shared');">
                                                                        <option value="" disabled selected>Select</option>
                                                                       <option ${item.current == 'Bench' ? 'selected="selected"': 'style="display:none"'} value="Bench">Bench</option>
                                                                       <option ${item.current == 'Bench - Exit' ? 'selected="selected"': 'style="display:none"'} value="Bench - Exit">Bench - Exit</option>
                                                                       <option ${item.current == 'Bench - Maternity' ? 'selected="selected"': 'style="display:none"'} value="Bench - Maternity">Bench - Maternity</option>
                                                                       <option ${item.current == 'Billing' ? 'selected="selected"': 'style="display:none"'} value="Billing">Billing</option>
                                                                       <option ${item.current == 'Business Readiness' ? 'selected="selected"': 'style="display:none"'} value="Business Readiness">Business Readiness</option>
                                                                       <option ${item.current == 'Internal' ? 'selected="selected"': 'style="display:none"'} value="Internal">Internal</option>
                                                                       <option ${item.current == 'Internal - Exit' ? 'selected="selected"': 'style="display:none"'} value="Internal - Exit">Internal - Exit</option>
                                                                       <option ${item.current == 'Management' ? 'selected="selected"': 'style="display:none"'} value="Management">Management</option>
                                                                       <option ${item.current == 'Overhead' ? 'selected="selected"': 'style="display:none"'} value="Overhead">Overhead</option>
                                                                       <option ${item.current == 'Partial Billing' ? 'selected="selected"': 'style="display:none"'} value="Partial Billing">Partial Billing</option>
                                                                       <option ${item.current == 'Sales & Marketing' ? 'selected="selected"': 'style="display:none"'} value="Sales & Marketing">Sales & Marketing</option>
                                                                       <option ${item.current == 'Shadow' ? 'selected="selected"': 'style="display:none"'} value="Shadow">Shadow</option>
                                                                       <option ${item.current == 'Shadow - Client Committed/24*7 Support' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Client Committed/24*7 Support">Shadow - Client Committed/24*7 Support</option>
                                                                       <option ${item.current == 'Shadow - Exit' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Exit">Shadow - Exit</option>
                                                                       <option ${item.current == 'Shadow - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Planned Billing">Shadow - Planned Billing</option>
                                                                       <option ${item.current == 'Support' ? 'selected="selected"': 'style="display:none"'} value="Support">Support</option>
                                                                       <option ${item.current == 'Trainee' ? 'selected="selected"': 'style="display:none"'} value="Trainee">Trainee</option>
                                                                       <option ${item.current == 'Trainee - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Trainee - Planned Billing">Trainee - Planned Billing</option>

                                                                   </select>
                                                                </td>
                                                                <td class="p-0 managerHrSharedExtraCol" data-search="${item.currentPlusOne}" data-order="${item.currentPlusOne}"><input type="hidden" value="${item.currentPlusOne == '-' ? '' : item.currentPlusOne}" id="omSharedCurrentPlusOne${count.index}"/>
                                                                    <select class="mdb-select md-form form-control form-control-sm w-100" id="mSharedCurrentPlusOne${count.index}" name="<portlet:namespace/>mSharedCurrentPlusOne${count.index}" onchange="return validateColumnsOnSelect(this,'shared');">
                                                                        <option value="" disabled selected>Select</option>
                                                                        <option ${item.currentPlusOne == 'Bench' ? 'selected="selected"': 'style="display:none"'} value="Bench">Bench</option>
                                                                        <option ${item.currentPlusOne == 'Bench - Exit' ? 'selected="selected"': 'style="display:none"'} value="Bench - Exit">Bench - Exit</option>
                                                                        <option ${item.currentPlusOne == 'Bench - Maternity' ? 'selected="selected"': 'style="display:none"'} value="Bench - Maternity">Bench - Maternity</option>
                                                                        <option ${item.currentPlusOne == 'Billing' ? 'selected="selected"': 'style="display:none"'} value="Billing">Billing</option>
                                                                        <option ${item.currentPlusOne == 'Business Readiness' ? 'selected="selected"': 'style="display:none"'} value="Business Readiness">Business Readiness</option>
                                                                        <option ${item.currentPlusOne == 'Internal' ? 'selected="selected"': 'style="display:none"'} value="Internal">Internal</option>
                                                                        <option ${item.currentPlusOne == 'Internal - Exit' ? 'selected="selected"': 'style="display:none"'} value="Internal - Exit">Internal - Exit</option>
                                                                        <option ${item.currentPlusOne == 'Management' ? 'selected="selected"': 'style="display:none"'} value="Management">Management</option>
                                                                        <option ${item.currentPlusOne == 'Overhead' ? 'selected="selected"': 'style="display:none"'} value="Overhead">Overhead</option>
                                                                        <option ${item.currentPlusOne == 'Partial Billing' ? 'selected="selected"': 'style="display:none"'} value="Partial Billing">Partial Billing</option>
                                                                        <option ${item.currentPlusOne == 'Sales & Marketing' ? 'selected="selected"': 'style="display:none"'} value="Sales & Marketing">Sales & Marketing</option>
                                                                        <option ${item.currentPlusOne == 'Shadow' ? 'selected="selected"': 'style="display:none"'} value="Shadow">Shadow</option>
                                                                        <option ${item.currentPlusOne == 'Shadow - Client Committed/24*7 Support' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Client Committed/24*7 Support">Shadow - Client Committed/24*7 Support</option>
                                                                        <option ${item.currentPlusOne == 'Shadow - Exit' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Exit">Shadow - Exit</option>
                                                                        <option ${item.currentPlusOne == 'Shadow - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Planned Billing">Shadow - Planned Billing</option>
                                                                        <option ${item.currentPlusOne == 'Support' ? 'selected="selected"': 'style="display:none"'} value="Support">Support</option>
                                                                        <option ${item.currentPlusOne == 'Trainee' ? 'selected="selected"': 'style="display:none"'} value="Trainee">Trainee</option>
                                                                        <option ${item.currentPlusOne == 'Trainee - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Trainee - Planned Billing">Trainee - Planned Billing</option>
                                                                   </select>
                                                                </td>
                                                                <td class="p-0 managerHrSharedExtraCol" data-search="${item.currentPlusTwo}" data-order="${item.currentPlusTwo}"><input type="hidden" value="${item.currentPlusTwo == '-' ? '' : item.currentPlusTwo}" id="omSharedCurrentPlusTwo${count.index}"/>
                                                                    <select class="mdb-select md-form form-control form-control-sm w-100" id="mSharedCurrentPlusTwo${count.index}" name="<portlet:namespace/>mSharedCurrentPlusTwo${count.index}" onchange="return validateColumnsOnSelect(this,'shared');">
                                                                        <option value="" disabled selected>Select</option>
                                                                       <option ${item.currentPlusTwo == 'Bench' ? 'selected="selected"': 'style="display:none"'} value="Bench">Bench</option>
                                                                       <option ${item.currentPlusTwo == 'Bench - Exit' ? 'selected="selected"': 'style="display:none"'} value="Bench - Exit">Bench - Exit</option>
                                                                       <option ${item.currentPlusTwo == 'Bench - Maternity' ? 'selected="selected"': 'style="display:none"'} value="Bench - Maternity">Bench - Maternity</option>
                                                                       <option ${item.currentPlusTwo == 'Billing' ? 'selected="selected"': 'style="display:none"'} value="Billing">Billing</option>
                                                                       <option ${item.currentPlusTwo == 'Business Readiness' ? 'selected="selected"': 'style="display:none"'} value="Business Readiness">Business Readiness</option>
                                                                       <option ${item.currentPlusTwo == 'Internal' ? 'selected="selected"': 'style="display:none"'} value="Internal">Internal</option>
                                                                       <option ${item.currentPlusTwo == 'Internal - Exit' ? 'selected="selected"': 'style="display:none"'} value="Internal - Exit">Internal - Exit</option>
                                                                       <option ${item.currentPlusTwo == 'Management' ? 'selected="selected"': 'style="display:none"'} value="Management">Management</option>
                                                                       <option ${item.currentPlusTwo == 'Overhead' ? 'selected="selected"': 'style="display:none"'} value="Overhead">Overhead</option>
                                                                       <option ${item.currentPlusTwo == 'Partial Billing' ? 'selected="selected"': 'style="display:none"'} value="Partial Billing">Partial Billing</option>
                                                                       <option ${item.currentPlusTwo == 'Sales & Marketing' ? 'selected="selected"': 'style="display:none"'} value="Sales & Marketing">Sales & Marketing</option>
                                                                       <option ${item.currentPlusTwo == 'Shadow' ? 'selected="selected"': 'style="display:none"'} value="Shadow">Shadow</option>
                                                                       <option ${item.currentPlusTwo == 'Shadow - Client Committed/24*7 Support' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Client Committed/24*7 Support">Shadow - Client Committed/24*7 Support</option>
                                                                       <option ${item.currentPlusTwo == 'Shadow - Exit' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Exit">Shadow - Exit</option>
                                                                       <option ${item.currentPlusTwo == 'Shadow - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Planned Billing">Shadow - Planned Billing</option>
                                                                       <option ${item.currentPlusTwo == 'Support' ? 'selected="selected"': 'style="display:none"'} value="Support">Support</option>
                                                                       <option ${item.currentPlusTwo == 'Trainee' ? 'selected="selected"': 'style="display:none"'} value="Trainee">Trainee</option>
                                                                       <option ${item.currentPlusTwo == 'Trainee - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Trainee - Planned Billing">Trainee - Planned Billing</option>

                                                                   </select>
                                                                </td>
                                                                <td class="p-0 managerHrSharedExtraCol" data-search="${item.employeeStatus}" data-order="${item.employeeStatus}"><input type="hidden" value="${item.employeeStatus == '-' ? '' : item.employeeStatus}" id="omSharedemployeeStatus${count.index}"/>
                                                                    <select class="mdb-select md-form form-control form-control-sm w-100"  id="mSharedemployeeStatus${count.index}" name="<portlet:namespace/>mSharedemployeeStatus${count.index}" onchange="return validateColumnsOnSelect(this,'shared');">
                                                                        <option value="" disabled selected>Select</option>
                                                                        <option ${item.employeeStatus == 'Active' ? 'selected="selected"': 'style="display:none"'} value="Active">Active</option>
                                                                        <option ${item.employeeStatus == 'Resigned' ? 'selected="selected"': 'style="display:none"'} value="Resigned">Resigned</option>
                                                                        <option ${item.employeeStatus == 'Under Monitoring' ? 'selected="selected"': 'style="display:none"'} value="Under Monitoring">Under Monitoring</option>
                                                                        <option ${item.employeeStatus == 'Maternity Leave' ? 'selected="selected"': 'style="display:none"'} value="Maternity Leave">Maternity Leave</option>
                                                                        <option ${item.employeeStatus == 'Ramp up period' ? 'selected="selected"': 'style="display:none"'} value="Ramp up period">Ramp up period</option>
                                                                        <option ${item.employeeStatus == 'Sabbatical Leave' ? 'selected="selected"': 'style="display:none"'} value="Sabbatical Leave">Sabbatical Leave</option>
                                                                        <option ${item.employeeStatus == 'Deferred Billing' ? 'selected="selected"': 'style="display:none"'} value="Deferred Billing">Deferred Billing</option>
                                                                   </select>
                                                                </td>
                                                                <td class="p-0 managerHrSharedExtraCol"><input type="hidden" <c:if test="${item.lastWorkingDate != '-'}">value="${item.lastWorkingDate}" </c:if> id="omSharedlastWorkingDate${count.index}"/>
                                                                    <input type="date" class ="form-control form-control-sm" style="cursor: pointer" onkeydown="return false" readonly="readonly"  id="mSharedlastWorkingDate${count.index}" name="<portlet:namespace/>mSharedlastWorkingDate${count.index}" <c:if test="${item.lastWorkingDate != '-'}">value="${item.lastWorkingDate}" </c:if> />
                                                                </td>

                                                                <td class="p-0 managerHrSharedExtraCol"><input type="hidden" <c:if test="${item.benchStartDate != '-'}">value="${item.benchStartDate}" </c:if> id="omSharedbenchStartDate${count.index}"/>
                                                                    <input type="date" class ="form-control form-control-sm" style="cursor: pointer" readonly="readonly" onkeydown="return false" id="mSharedbenchStartDate${count.index}"  name="<portlet:namespace/>mSharedbenchStartDate${count.index}" <c:if test="${item.benchStartDate != '-'}">value="${item.benchStartDate}" </c:if> />
                                                                </td>
                                                                <td class="p-0 managerHrSharedExtraCol"><input type="hidden" value="${item.shadowResourceType == '-' ? '' : item.shadowResourceType}" id="omSharedShadowResourceType${count.index}"/>
                                                                    <select class="mdb-select md-form form-control form-control-sm w-100" id="mSharedShadowResourceType${count.index}" name="<portlet:namespace/>mSharedShadowResourceType${count.index}">
                                                                        <option value="" disabled selected>Select</option>
                                                                        <option ${item.shadowResourceType == 'Client Requested' ? 'selected="selected"': 'style="display:none"'} value="Client Requested">Client Requested</option>
                                                                        <option ${item.shadowResourceType == 'Strategic Hiring' ? 'selected="selected"': 'style="display:none"'} value="Strategic Hiring">Strategic Hiring</option>
                                                                        <option ${item.shadowResourceType == 'Replacement' ? 'selected="selected"': 'style="display:none"'} value="Replacement">Replacement</option>
                                                                   </select>
                                                                </td>
                                                                <td class="p-0 managerHrSharedExtraCol"><input type="hidden" <c:if test="${item.shadowStartDate != '-'}">value="${item.shadowStartDate}"</c:if> id="omSharedshadowStartDate${count.index}"/>
                                                                    <input type="date" class ="form-control form-control-sm" style="cursor: pointer" readonly="readonly" onkeydown="return false" id="mSharedshadowStartDate${count.index}"  name="<portlet:namespace/>mSharedshadowStartDate${count.index}" <c:if test="${item.shadowStartDate != '-'}">value="${item.shadowStartDate}"</c:if> />
                                                                </td>
                                                                <td class="p-0 managerHrSharedExtraCol"><input type="hidden" <c:if test="${item.billingStartDate != '-'}"> value="${item.billingStartDate}" </c:if> id="omSharedbillingStartDate${count.index}"/>
                                                                    <input type="date" class ="form-control form-control-sm" style="cursor: pointer" readonly="readonly" onkeydown="return false" id="mSharedbillingStartDate${count.index}" name="<portlet:namespace/>mSharedbillingStartDate${count.index}" <c:if test="${item.billingStartDate != '-'}"> value="${item.billingStartDate}" </c:if> />
                                                                </td>
                                                                <td class="p-0 managerHrSharedExtraCol"><input type="hidden" <c:if test="${item.billingEndDate != '-'}">value="${item.billingEndDate}" </c:if> id="omSharedbillingEndDate${count.index}"/>
                                                                    <input type="date" class ="form-control form-control-sm" style="cursor: pointer" readonly="readonly" onkeydown="return false" id="mSharedbillingEndDate${count.index}" name="<portlet:namespace/>mSharedbillingEndDate${count.index}" <c:if test="${item.billingEndDate != '-'}">value="${item.billingEndDate}" </c:if> />
                                                                </td>
                                                                <td class="p-0 managerHrSharedExtraCol"><input type="hidden" value="${item.percentUtilization == '-' ? '100' : item.percentUtilization}" id="omSharedpercentUtilization${count.index}"/>
                                                                    <input type="number" class="form-control form-control-sm"
                                                                         readonly="readonly" max="100" min="0" id="mSharedpercentUtilization${count.index}" value="${item.percentUtilization == '-' ? '100' : item.percentUtilization}"  name="<portlet:namespace/>mSharedpercentUtilization${count.index}">
                                                                </td>

                                                                 <td class="p-0 managerHrSharedExtraCol"><input type="hidden" value="${item.remarks == '-' ? '' : item.remarks}" id="omSharedRemarks${count.index}"/>
                                                                    <input type="text" class="form-control form-control-sm" maxlength="200"
                                                                            onblur="$(this).val($(this).val().trim())" readonly="readonly" id="mSharedRemarks${count.index}" name="<portlet:namespace/>mSharedRemarks${count.index}"  value="${item.remarks == '-' ? '' : item.remarks}" />
                                                                </td>
                                                                <td class="managerHrSharedExtraCol"><input type="hidden" value="${item.billableHours}" id="omSharedbillableHours${count.index}"/>
                                                                    <input type="number" class="form-control form-control-sm" min="0" step="0.01" readonly="readonly"
                                                                        id="mSharedbillableHours${count.index}" name="<portlet:namespace/>mSharedbillableHours${count.index}"  value="${item.billableHours}" />
                                                                </td>
                                                                <td class="managerHrSharedExtraCol"><input type="hidden" value="${item.employeeRole}" id="omSharedEmployeeRole${count.index}"/>
                                                                    <input type="text" class="form-control form-control-sm" maxlength="75"
                                                                           onblur="$(this).val($(this).val().trim())" readonly="readonly" id="mSharedEmployeeRole${count.index}" name="<portlet:namespace/>mSharedEmployeeRole${count.index}" value="${item.employeeRole}" />
                                                                </td>
                                                                <td class="managerHrSharedExtraCol"><input type="hidden" value="${item.vertical}" id="omSharedVertical${count.index}"/>
                                                                    <select class="mdb-select md-form form-control form-control-sm w-100"  id="mSharedVertical${count.index}" name="<portlet:namespace/>mSharedVertical${count.index}">
                                                                        <option value="" disabled selected>Select</option>
                                                                        <option ${item.vertical == 'BBU' ? 'selected="selected"': 'style="display:none"'} value="BBU">BBU</option>
                                                                        <option ${item.vertical == 'Captive CoE' ? 'selected="selected"': 'style="display:none"'} value="Captive CoE">Captive CoE</option>
                                                                        <option ${item.vertical == 'Cloud' ? 'selected="selected"': 'style="display:none"'} value="Cloud">Cloud</option>
                                                                        <option ${item.vertical == 'Enterprise' ? 'selected="selected"': 'style="display:none"'} value="Enterprise">Enterprise</option>
                                                                        <option ${item.vertical == 'Other' ? 'selected="selected"': 'style="display:none"'} value="Other">Other</option>
                                                                        <option ${item.vertical == 'Sales' ? 'selected="selected"': 'style="display:none"'} value="Sales">Sales</option>
                                                                        <option ${item.vertical == 'Support' ? 'selected="selected"': 'style="display:none"'} value="Support">Support</option>
                                                                   </select>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                </tbody>
                                            </table>
                                            <br>
                                            <c:if test="${viewMode == 'edit'}">
                                                <button type="button" class="btn btn-outline-success btn-sm"
                                                         onclick="addNewRow('m')">
                                                        <i class="fas fa-plus"></i> Add Employee
                                                </button>
                                            </c:if>
                                        </div>

                                </div>
                                <c:if test="${viewMode == 'edit'}">
                                    <div class="row p-2 pl-3">
                                        <div class="custom-control custom-checkbox mb-2" id="termsDiv">
                                            <input type="checkbox" class="custom-control-input" id="terms" />
                                                  <label  for="terms"
                                                 class="custom-control-label pl-2" style="font-size:0.9em;">
                                                 I confirm that I have reviewed data for the month and no further updates are required. It can be freezed and final confirmation can be sent to Finance team
                                                 </label>
                                        </div>
                                    </div>
                                    <div class="row">
                                         <div class="col-md-4"></div>
                                        <div class="col-md-4 text-center">
                                            <button class="btn btn-primary" id="saveBtn" type="submit" onClick="validateActionPerformed(2,'m')">Submit</button>
                                        </div>
                                        <div class="col-md-4"></div>
                                    </div>
                                </c:if>
                           </form>
        		 	  </div>
        		 </div>
                 <div class="card">
                        <div class="card-header">
                            <div class="row">
                                <div class="col pt-2"> <h3>Help - Billing User Manual </h3></div>
                                <div class="col-2 text-right" ><input type="button" class="btn btn-link"  onClick="downloadManual(1);"  value="Download the file" /></div>
                            </div>
                        </div>
                 </div>
            </div>
        </div>
        <div class="tab-pane fade" id="hr" role="tabpanel" aria-labelledby="hr-tab">
        			<div id="hrDiv">
                        <div class="card">
                            <div class="card-header">
                                <div class="row">
                                    <div class="col-3 pt-2">
                                        <h3>Export HR Report</h3>
                                        <p>Latest Billing Status of Active Employees</p>
                                    </div>
                                    <div class="col-6"></div>
                                     <div class="col-3 pt-2">
                                        <form  method="post" action="${downloadEmployeeHrReportUrl}" >
                                            <button class="btn btn-secondary btn-block" type="submit"><i class="fa fa-download" aria-hidden="true"></i> Download</button>
                                        </form>
                                    </div>
                                </div>
                             </form>
                            </div>
                        </div>
                        <div class="card">
							<div class="card-header">
									<div class="row pt-2">
                                            <div class="col-md-10">
                                                <h3>All Employees</h3><p>Column 'Allocation Status' is applicable only for Bench & Shadow resources<br>Green color is for non mandatory columns, Orange color is for mandatory columns, Violet color is for conditional columns</p>
                                            </div>
                                            <div class="col-md-2 text-right">
                                                <a class="btn btn-secondary"
                                                    onclick="return confirm('Any unsaved changes will be lost. Do you wish to continue ?')"
                                                    href="${pageUrl[0]}" role="button"><i class="fa fa-undo" aria-hidden="true"></i> Reset</a>
                                            </div>
									</div>
							</div>
							<div class="card-body">
                                <form id="hBillingForm" method="post"
				                    onSubmit="return confirmFormSubmission('h');">
				                     <input type="hidden"
                                            id="actionPerformedh" value="h" name="<portlet:namespace/>actionPerformed" />
				                     <table
                                        class="table table-sm table-bordered table-striped table-hover table-custom text-center"
                                        id="hrTable">
                                        <thead class="thead-dark">
                                            <tr>
                                              <th scope="col" class="managerHrColFixed1" style="padding-top: 14px !important;height: auto;">Select</th>
                                              <th scope="col" class="managerHrColFixed2" style="padding-top: 14px !important;height: auto;">Ecode</th>
                                              <th scope="col" class="managerHrColFixed3" style="padding-top: 14px !important;height: auto;">Name</th>
                                              <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Manager Ecode</th>
                                              <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Manager Name</th>
                                              <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Coordinator Ecode</th>
                                              <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Coordinator Name</th>
                                              <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Lead Ecode</th>
                                              <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Lead Name</th>
                                              <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Designation</th>
                                              <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">DOJ</th>
                                              <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Experience</th>
                                              <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Skill</th>
                                              <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Account</th>
                                              <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Project</th>

                                              <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Billing / Shadow / Bench - Current*
                                                  <i class="fas fa-question text-danger" style="cursor: pointer"
                                                      title=" Billing : Billing resources &#013;Partial Billing : Resources partially billing in your project &#013;Bench : Non billing Resources, not allocated or projected for allocation and available in open pool &#013;Shadow : Non billing resources, who are projected for allocation and/or fixed allocation to project, not available for allocation &#013;Trainee : Non billing Resources, in training, joined as fresher &#013;Internal : Working on inhouse Trantor Projects &#013;Support : Recruitment, ISO, Admin, Finance, IT and HR &#013;Overhead : Non billing Resources, managing account or project &#013;Business Readiness : Non billing resources, hired for anticipated project"></i>
                                              </th>
                                              <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Billing / Shadow / Bench - Current + 1 month*
                                                  <i class="fas fa-question text-danger" style="cursor: pointer"
                                                      title=" Billing : Billing resources &#013;Partial Billing : Resources partially billing in your project &#013;Bench : Non billing Resources, not allocated or projected for allocation and available in open pool &#013;Shadow : Non billing resources, who are projected for allocation and/or fixed allocation to project, not available for allocation &#013;Trainee : Non billing Resources, in training, joined as fresher &#013;Internal : Working on inhouse Trantor Projects &#013;Support : Recruitment, ISO, Admin, Finance, IT and HR &#013;Overhead : Non billing Resources, managing account or project &#013;Business Readiness : Non billing resources, hired for anticipated project"></i>
                                              </th>
                                              <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Billing / Shadow / Bench - Current + 2 month*
                                                  <i class="fas fa-question text-danger" style="cursor: pointer"
                                                      title=" Billing : Billing resources &#013;Partial Billing : Resources partially billing in your project &#013;Bench : Non billing Resources, not allocated or projected for allocation and available in open pool &#013;Shadow : Non billing resources, who are projected for allocation and/or fixed allocation to project, not available for allocation &#013;Trainee : Non billing Resources, in training, joined as fresher &#013;Internal : Working on inhouse Trantor Projects &#013;Support : Recruitment, ISO, Admin, Finance, IT and HR &#013;Overhead : Non billing Resources, managing account or project &#013;Business Readiness : Non billing resources, hired for anticipated project"></i>
                                              </th>
                                              <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Employee Status*</th>
                                              <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Last Working Date</th>

                                              <th scope="col" class="managerHrExtraCol"style="padding: 0px 20px !important;">Allocation Status
                                                    <i class="fas fa-question text-danger" style="cursor: pointer"
                                               		title=" Red - Cannot be allocated to other project &#013;Yellow - Can be allocated to other project after discussion &#013;Green - Available for immediate allocation"></i>
                                              </th>
                                              <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Bench Start Date</th>
                                              <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Shadow Resource Type (for shadow resources)</th>
                                              <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Shadow Start Date</th>
                                              <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Billing Start Date</th>
                                              <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Billing End Date</th>
                                              <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">% Utilization Per Month</th>

                                              <th scope="col" class="managerHrExtraCol" style="padding: 0px 50px !important;">Remarks</th>
                                              <th scope="col" class="managerHrExtraCol" style="padding: 0px 50px !important;">Billing Status</th>
                                              <th scope="col" class="managerHrExtraCol" style="padding: 0px 50px !important;">Vertical*</th>
                                            </tr>
                                        </thead>
                                            <tbody id="hrTableBody">
                                                <c:forEach items="${hrEmployeeList}" var="item" varStatus="count">
                                                    <tr>
                                                        <td class="managerHrColFixed1">
                                                            <input type="checkbox" style="margin-top: 10px;" value="${item.billingId}" id="hCheckbox${count.count}" name="<portlet:namespace/>hCheckbox" onClick="changeFormToEditMode(this,'nonShared')"/>
                                                        </td>
                                                        <td class="managerHrColFixed2" data-search="${item.employeeCode}" data-order="${item.employeeCode}"><input type="text" class="form-control-plaintext form-control-sm text-center" readonly="readonly" value="${item.employeeCode}" /></td>
                                                        <td class="managerHrColFixed3" data-search="${item.employeeName}" data-order="${item.employeeName}"><input type="text" class="form-control-plaintext form-control-sm text-center" readonly="readonly" value="${item.employeeName}" /></td>
                                                        <td class="managerHrExtraCol">${item.managerEcode}</td>
                                                        <td class="managerHrExtraCol">${item.managerName}</td>
                                                        <td class="managerHrExtraCol">${item.coordinatorEcode}</td>
                                                        <td class="managerHrExtraCol">${item.coordinatorName}</td>
                                                        <td class="managerHrExtraCol">${item.leadEcode}</td>
                                                        <td class="managerHrExtraCol">${item.leadName}</td>
                                                        <td class="managerHrExtraCol">${item.employeeDesignation}</td>
                                                        <td class="managerHrExtraCol">${item.doj}</td>
                                                        <td class="managerHrExtraCol">${item.experience}</td>
                                                        <td class="managerHrExtraCol">${item.skill}</td>
                                                        <td class="managerHrExtraCol">${item.account}</td>
                                                        <td class="managerHrExtraCol">${item.project}</td>

                                                        <td class="p-0 managerHrExtraCol" data-search="${item.current}" data-order="${item.current}"><input type="hidden" value="${item.current == '-' ? '' : item.current}" id="ohCurrent${count.count}"/>
                                                            <select class="mdb-select md-form form-control form-control-sm w-100" style="background-color: #F7DC6F;" id="hCurrent${count.count}" name="<portlet:namespace/>hCurrent${item.billingId}" onchange="return validateColumnsOnSelect(this,'nonShared');">
                                                                <option value="" disabled selected>Select</option>
                                                                <option ${item.current == 'Bench' ? 'selected="selected"': 'style="display:none"'} value="Bench">Bench</option>
                                                                <option ${item.current == 'Bench - Exit' ? 'selected="selected"': 'style="display:none"'} value="Bench - Exit">Bench - Exit</option>
                                                                <option ${item.current == 'Bench - Maternity' ? 'selected="selected"': 'style="display:none"'} value="Bench - Maternity">Bench - Maternity</option>
                                                                <option ${item.current == 'Billing' ? 'selected="selected"': 'style="display:none"'} value="Billing">Billing</option>
                                                                <option ${item.current == 'Business Readiness' ? 'selected="selected"': 'style="display:none"'} value="Business Readiness">Business Readiness</option>
                                                                <option ${item.current == 'Internal' ? 'selected="selected"': 'style="display:none"'} value="Internal">Internal</option>
                                                                <option ${item.current == 'Internal - Exit' ? 'selected="selected"': 'style="display:none"'} value="Internal - Exit">Internal - Exit</option>
                                                                <option ${item.current == 'Management' ? 'selected="selected"': 'style="display:none"'} value="Management">Management</option>
                                                                <option ${item.current == 'Overhead' ? 'selected="selected"': 'style="display:none"'} value="Overhead">Overhead</option>
                                                                <option ${item.current == 'Partial Billing' ? 'selected="selected"': 'style="display:none"'} value="Partial Billing">Partial Billing</option>
                                                                <option ${item.current == 'Sales & Marketing' ? 'selected="selected"': 'style="display:none"'} value="Sales & Marketing">Sales & Marketing</option>
                                                                <option ${item.current == 'Shadow' ? 'selected="selected"': 'style="display:none"'} value="Shadow">Shadow</option>
                                                                <option ${item.current == 'Shadow - Client Committed/24*7 Support' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Client Committed/24*7 Support">Shadow - Client Committed/24*7 Support</option>
                                                                <option ${item.current == 'Shadow - Exit' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Exit">Shadow - Exit</option>
                                                                <option ${item.current == 'Shadow - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Planned Billing">Shadow - Planned Billing</option>
                                                                <option ${item.current == 'Support' ? 'selected="selected"': 'style="display:none"'} value="Support">Support</option>
                                                                <option ${item.current == 'Trainee' ? 'selected="selected"': 'style="display:none"'} value="Trainee">Trainee</option>
                                                                <option ${item.current == 'Trainee - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Trainee - Planned Billing">Trainee - Planned Billing</option>

                                                           </select>
                                                        </td>
                                                        <td class="p-0 managerHrExtraCol" data-search="${item.currentPlusOne}" data-order="${item.currentPlusOne}"><input type="hidden" value="${item.currentPlusOne == '-' ? '' : item.currentPlusOne}" id="ohCurrentPlusOne${count.count}"/>
                                                            <select class="mdb-select md-form form-control form-control-sm w-100" style="background-color: #F7DC6F;" id="hCurrentPlusOne${count.count}" name="<portlet:namespace/>hCurrentPlusOne${item.billingId}" onchange="return validateColumnsOnSelect(this,'nonShared');">
                                                                <option value="" disabled selected>Select</option>
                                                               <option ${item.currentPlusOne == 'Bench' ? 'selected="selected"': 'style="display:none"'} value="Bench">Bench</option>
                                                               <option ${item.currentPlusOne == 'Bench - Exit' ? 'selected="selected"': 'style="display:none"'} value="Bench - Exit">Bench - Exit</option>
                                                               <option ${item.currentPlusOne == 'Bench - Maternity' ? 'selected="selected"': 'style="display:none"'} value="Bench - Maternity">Bench - Maternity</option>
                                                               <option ${item.currentPlusOne == 'Billing' ? 'selected="selected"': 'style="display:none"'} value="Billing">Billing</option>
                                                               <option ${item.currentPlusOne == 'Business Readiness' ? 'selected="selected"': 'style="display:none"'} value="Business Readiness">Business Readiness</option>
                                                               <option ${item.currentPlusOne == 'Internal' ? 'selected="selected"': 'style="display:none"'} value="Internal">Internal</option>
                                                               <option ${item.currentPlusOne == 'Internal - Exit' ? 'selected="selected"': 'style="display:none"'} value="Internal - Exit">Internal - Exit</option>
                                                               <option ${item.currentPlusOne == 'Management' ? 'selected="selected"': 'style="display:none"'} value="Management">Management</option>
                                                               <option ${item.currentPlusOne == 'Overhead' ? 'selected="selected"': 'style="display:none"'} value="Overhead">Overhead</option>
                                                               <option ${item.currentPlusOne == 'Partial Billing' ? 'selected="selected"': 'style="display:none"'} value="Partial Billing">Partial Billing</option>
                                                               <option ${item.currentPlusOne == 'Sales & Marketing' ? 'selected="selected"': 'style="display:none"'} value="Sales & Marketing">Sales & Marketing</option>
                                                               <option ${item.currentPlusOne == 'Shadow' ? 'selected="selected"': 'style="display:none"'} value="Shadow">Shadow</option>
                                                               <option ${item.currentPlusOne == 'Shadow - Client Committed/24*7 Support' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Client Committed/24*7 Support">Shadow - Client Committed/24*7 Support</option>
                                                               <option ${item.currentPlusOne == 'Shadow - Exit' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Exit">Shadow - Exit</option>
                                                               <option ${item.currentPlusOne == 'Shadow - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Planned Billing">Shadow - Planned Billing</option>
                                                               <option ${item.currentPlusOne == 'Support' ? 'selected="selected"': 'style="display:none"'} value="Support">Support</option>
                                                               <option ${item.currentPlusOne == 'Trainee' ? 'selected="selected"': 'style="display:none"'} value="Trainee">Trainee</option>
                                                               <option ${item.currentPlusOne == 'Trainee - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Trainee - Planned Billing">Trainee - Planned Billing</option>

                                                           </select>
                                                        </td>
                                                        <td class="p-0 managerHrExtraCol" data-search="${item.currentPlusTwo}" data-order="${item.currentPlusTwo}"><input type="hidden" value="${item.currentPlusTwo == '-' ? '' : item.currentPlusTwo}" value="${item.currentPlusTwo}" id="ohCurrentPlusTwo${count.count}"/>
                                                            <select class="mdb-select md-form form-control form-control-sm w-100" style="background-color: #F7DC6F;" id="hCurrentPlusTwo${count.count}" name="<portlet:namespace/>hCurrentPlusTwo${item.billingId}" onchange="return validateColumnsOnSelect(this,'nonShared');">
                                                                <option value="" disabled selected>Select</option>
                                                               <option ${item.currentPlusTwo == 'Bench' ? 'selected="selected"': 'style="display:none"'} value="Bench">Bench</option>
                                                               <option ${item.currentPlusTwo == 'Bench - Exit' ? 'selected="selected"': 'style="display:none"'} value="Bench - Exit">Bench - Exit</option>
                                                               <option ${item.currentPlusTwo == 'Bench - Maternity' ? 'selected="selected"': 'style="display:none"'} value="Bench - Maternity">Bench - Maternity</option>
                                                               <option ${item.currentPlusTwo == 'Billing' ? 'selected="selected"': 'style="display:none"'} value="Billing">Billing</option>
                                                               <option ${item.currentPlusTwo == 'Business Readiness' ? 'selected="selected"': 'style="display:none"'} value="Business Readiness">Business Readiness</option>
                                                               <option ${item.currentPlusTwo == 'Internal' ? 'selected="selected"': 'style="display:none"'} value="Internal">Internal</option>
                                                               <option ${item.currentPlusTwo == 'Internal - Exit' ? 'selected="selected"': 'style="display:none"'} value="Internal - Exit">Internal - Exit</option>
                                                               <option ${item.currentPlusTwo == 'Management' ? 'selected="selected"': 'style="display:none"'} value="Management">Management</option>
                                                               <option ${item.currentPlusTwo == 'Overhead' ? 'selected="selected"': 'style="display:none"'} value="Overhead">Overhead</option>
                                                               <option ${item.currentPlusTwo == 'Partial Billing' ? 'selected="selected"': 'style="display:none"'} value="Partial Billing">Partial Billing</option>
                                                               <option ${item.currentPlusTwo == 'Sales & Marketing' ? 'selected="selected"': 'style="display:none"'} value="Sales & Marketing">Sales & Marketing</option>
                                                               <option ${item.currentPlusTwo == 'Shadow' ? 'selected="selected"': 'style="display:none"'} value="Shadow">Shadow</option>
                                                               <option ${item.currentPlusTwo == 'Shadow - Client Committed/24*7 Support' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Client Committed/24*7 Support">Shadow - Client Committed/24*7 Support</option>
                                                               <option ${item.currentPlusTwo == 'Shadow - Exit' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Exit">Shadow - Exit</option>
                                                               <option ${item.currentPlusTwo == 'Shadow - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Planned Billing">Shadow - Planned Billing</option>
                                                               <option ${item.currentPlusTwo == 'Support' ? 'selected="selected"': 'style="display:none"'} value="Support">Support</option>
                                                               <option ${item.currentPlusTwo == 'Trainee' ? 'selected="selected"': 'style="display:none"'} value="Trainee">Trainee</option>
                                                               <option ${item.currentPlusTwo == 'Trainee - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Trainee - Planned Billing">Trainee - Planned Billing</option>
                                                           </select>
                                                        </td>
                                                        <td class="p-0 managerHrExtraCol" data-search="${item.employeeStatus}" data-order="${item.employeeStatus}"><input type="hidden" value="${item.employeeStatus == '-' ? '' : item.employeeStatus}" id="ohemployeeStatus${count.count}"/>
                                                            <select class="mdb-select md-form form-control form-control-sm w-100" style="background-color: #F7DC6F;" id="hemployeeStatus${count.count}" name="<portlet:namespace/>hemployeeStatus${item.billingId}" onchange="return validateColumnsOnSelect(this,'nonShared');">
                                                                <option value="" disabled selected>Select</option>
                                                                <option ${item.employeeStatus == 'Active' ? 'selected="selected"': 'style="display:none"'} value="Active">Active</option>
                                                                <option ${item.employeeStatus == 'Resigned' ? 'selected="selected"': 'style="display:none"'} value="Resigned">Resigned</option>
                                                                <option ${item.employeeStatus == 'Under Monitoring' ? 'selected="selected"': 'style="display:none"'} value="Under Monitoring">Under Monitoring</option>
                                                                <option ${item.employeeStatus == 'Maternity Leave' ? 'selected="selected"': 'style="display:none"'} value="Maternity Leave">Maternity Leave</option>
                                                                <option ${item.employeeStatus == 'Ramp up period' ? 'selected="selected"': 'style="display:none"'} value="Ramp up period">Ramp up period</option>
                                                                <option ${item.employeeStatus == 'Sabbatical Leave' ? 'selected="selected"': 'style="display:none"'} value="Sabbatical Leave">Sabbatical Leave</option>
                                                                <option ${item.employeeStatus == 'Deferred Billing' ? 'selected="selected"': 'style="display:none"'} value="Deferred Billing">Deferred Billing</option>
                                                           </select>
                                                        </td>
                                                        <td class="p-0 managerHrExtraCol"><input type="hidden" <c:if test="${item.lastWorkingDate != '-'}">value="${item.lastWorkingDate}" </c:if> id="ohlastWorkingDate${count.count}"/>
                                                            <input type="date" class ="form-control form-control-sm" style="background-color: #D7BDE2;" style="cursor: pointer" onkeydown="return false" readonly="readonly" id="hlastWorkingDate${count.count}" name="<portlet:namespace/>hlastWorkingDate${item.billingId}" <c:if test="${item.lastWorkingDate != '-'}">value="${item.lastWorkingDate}" </c:if> />
                                                        </td>

                                                        <td class="p-0 managerHrExtraCol" data-search="${item.allocationStatus}" data-order="${item.allocationStatus}"><input type="hidden" value="${item.allocationStatus == '-' ? '' : item.allocationStatus}" id="ohAllocationStatus${count.count}"/>
                                                            <select class="mdb-select md-form form-control form-control-sm w-100" style="background-color: #D7BDE2;" id="hAllocationStatus${count.count}" name="<portlet:namespace/>hAllocationStatus${item.billingId}">
                                                                <option value="" disabled selected>Select</option>
                                                                <option ${item.allocationStatus == 'Red' ? 'selected="selected"': 'style="display:none"'} value="Red">Red</option>
                                                                <option ${item.allocationStatus == 'Yellow' ? 'selected="selected"': 'style="display:none"'} value="Yellow">Yellow</option>
                                                                <option ${item.allocationStatus == 'Green' ? 'selected="selected"': 'style="display:none"'} value="Green">Green</option>
                                                           </select>
                                                        <td class="p-0 managerHrExtraCol"><input type="hidden" <c:if test="${item.benchStartDate != '-'}">value="${item.benchStartDate}" </c:if> id="ohbenchStartDate${count.count}"/>
                                                            <input type="date" class ="form-control form-control-sm" style="background-color: #D7BDE2;" style="cursor: pointer" readonly="readonly" onkeydown="return false" id="hbenchStartDate${count.count}"  name="<portlet:namespace/>hbenchStartDate${item.billingId}" <c:if test="${item.benchStartDate != '-'}">value="${item.benchStartDate}" </c:if> />
                                                        </td>
                                                        <td class="p-0 managerHrExtraCol"><input type="hidden" value="${item.shadowResourceType == '-' ? '' : item.shadowResourceType}" id="ohShadowResourceType${count.count}"/>
                                                            <select class="mdb-select md-form form-control form-control-sm w-100" style="background-color: #D7BDE2;" id="hShadowResourceType${count.count}" name="<portlet:namespace/>hShadowResourceType${item.billingId}">
                                                                <option value="" disabled selected>Select</option>
                                                                <option ${item.shadowResourceType == 'Client Requested' ? 'selected="selected"': 'style="display:none"'} value="Client Requested">Client Requested</option>
                                                                <option ${item.shadowResourceType == 'Strategic Hiring' ? 'selected="selected"': 'style="display:none"'} value="Strategic Hiring">Strategic Hiring</option>
                                                                <option ${item.shadowResourceType == 'Replacement' ? 'selected="selected"': 'style="display:none"'} value="Replacement">Replacement</option>
                                                           </select>
                                                        </td>
                                                        <td class="p-0 managerHrExtraCol"><input type="hidden" <c:if test="${item.shadowStartDate != '-'}">value="${item.shadowStartDate}"</c:if> id="ohshadowStartDate${count.count}"/>
                                                            <input type="date" class ="form-control form-control-sm" style="background-color: #D7BDE2;" style="cursor: pointer" readonly="readonly" onkeydown="return false" id="hshadowStartDate${count.count}"  name="<portlet:namespace/>hshadowStartDate${item.billingId}" <c:if test="${item.shadowStartDate != '-'}">value="${item.shadowStartDate}"</c:if> />
                                                        </td>
                                                        <td class="p-0 managerHrExtraCol"><input type="hidden" <c:if test="${item.billingStartDate != '-'}"> value="${item.billingStartDate}" </c:if> id="ohbillingStartDate${count.count}"/>
                                                            <input type="date" class ="form-control form-control-sm" style="background-color: #D7BDE2;" style="cursor: pointer" readonly="readonly" onkeydown="return false" id="hbillingStartDate${count.count}" name="<portlet:namespace/>hbillingStartDate${item.billingId}" <c:if test="${item.billingStartDate != '-'}"> value="${item.billingStartDate}" </c:if>/>
                                                        </td>
                                                        <td class="p-0 managerHrExtraCol"><input type="hidden" <c:if test="${item.billingEndDate != '-'}">value="${item.billingEndDate}" </c:if> id="ohbillingEndDate${count.count}"/>
                                                            <input type="date" class ="form-control form-control-sm" style="background-color: #D7BDE2;" style="cursor: pointer" readonly="readonly" onkeydown="return false" id="hbillingEndDate${count.count}" name="<portlet:namespace/>hbillingEndDate${item.billingId}" <c:if test="${item.billingEndDate != '-'}">value="${item.billingEndDate}" </c:if>/>
                                                        </td>
                                                        <td class="p-0 managerHrExtraCol"><input type="hidden" value="${item.percentUtilization == '-' ? '100' : item.percentUtilization}" id="ohpercentUtilization${count.count}"/>
                                                              <input type="number" class="form-control form-control-sm" style="background-color: #D7BDE2;"
                                                                 readonly="readonly" max="100" min="0" id="hpercentUtilization${count.count}" value="${item.percentUtilization == '-' ? '100' : item.percentUtilization}"  name="<portlet:namespace/>hpercentUtilization${item.billingId}">
                                                        </td>

                                                        <td class="p-0 managerHrExtraCol"><input type="hidden" value="${item.remarks == '-' ? '' : item.remarks}" id="ohRemarks${count.count}"/><input type="text"
                                                                    class="form-control form-control-sm" style="background-color: palegreen;" maxlength="200"
                                                                    onblur="$(this).val($(this).val().trim())" readonly="readonly" id="hRemarks${count.count}" title="${item.remarks}" name="<portlet:namespace/>hRemarks${item.billingId}"  value="${item.remarks == '-' ? '' : item.remarks}" />
                                                        </td>
                                                        <td class="p-0 managerHrExtraCol" data-search="${item.billingStatus}" data-order="${item.billingStatus}">
                                                            <input type="text"
                                                               class="form-control form-control-sm" maxlength="75"
                                                               readonly="readonly" value="${item.billingStatus}" />
                                                        </td>
                                                        <td class="p-0 managerHrExtraCol" data-search="${item.vertical}" data-order="${item.vertical}"><input type="hidden" value="${item.vertical == '-' ? '' : item.vertical}" id="ohVertical${count.count}"/>
                                                            <select class="mdb-select md-form form-control form-control-sm w-100" style="background-color: #F7DC6F;" id="hVertical${count.count}" name="<portlet:namespace/>hVertical${item.billingId}">
                                                                <option value="" disabled selected>Select</option>
                                                                <option ${item.vertical == 'BBU' ? 'selected="selected"': 'style="display:none"'} value="BBU">BBU</option>
                                                                <option ${item.vertical == 'Captive CoE' ? 'selected="selected"': 'style="display:none"'} value="Captive CoE">Captive CoE</option>
                                                                <option ${item.vertical == 'Cloud' ? 'selected="selected"': 'style="display:none"'} value="Cloud">Cloud</option>
                                                                <option ${item.vertical == 'Enterprise' ? 'selected="selected"': 'style="display:none"'} value="Enterprise">Enterprise</option>
                                                                <option ${item.vertical == 'Other' ? 'selected="selected"': 'style="display:none"'} value="Other">Other</option>
                                                                <option ${item.vertical == 'Sales' ? 'selected="selected"': 'style="display:none"'} value="Sales">Sales</option>
                                                                <option ${item.vertical == 'Support' ? 'selected="selected"': 'style="display:none"'} value="Support">Support</option>
                                                           </select>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                    </table>
                                    <br></br>
                                    <div class="row">
                                        <div class="col-md-6 text-right">
                                            <button class="btn btn-primary" type="submit">Submit</button>
                                        </div>
                                    </div>
                                </form>
							</div>
						</div>
                        <div class="card">
        					<div class="card-header">
        						<div class="row pt-2">
                                        <div class="col">
                                            <h3>Partial/Shared Resource</h3>
                                            <p>Please add team member details who are working in Partial or Shared capacity</p>
                                        </div>
        						</div>
        					</div>
        					<div class="card-body">
                                <form id="hSharedBillingForm" method="post"
				                onSubmit="return confirmHrSharedFormSubmission('h');">
				                    <input type="hidden" id="hExistingRows"
                                        value="" name="<portlet:namespace/>existingRows" />
                                    <input type="hidden" id="hTotalRows"
                                        value="" name="<portlet:namespace/>totalRows" />
                                    <input type="hidden" id="hvalidateAction"
                                        value="" name="<portlet:namespace/>validateAction" />
				                    <input type="hidden"
                                		id="actionPerformedSharedh" value="h" name="<portlet:namespace/>actionPerformedShared" />
                                    <table
                                        class="table table-sm table-bordered table-striped table-hover table-custom text-center"
                                        id="hrSharedTable">
                                        <thead class="thead-dark">
                                            <tr>
                                              <th scope="col" class="managerHrSharedColFixed1" style="padding-top: 14px !important;height: auto;">Select</th>
                                              <th scope="col" class="managerHrSharedColFixed2" style="padding-top: 14px !important;height: auto;">Delete </th>
                                              <th scope="col" class="managerHrSharedColFixed3" style="padding-top: 14px !important;height: auto;">Ecode*</th>
                                              <th scope="col" class="managerHrSharedColFixed4" style="padding-top: 14px !important;height: auto;">Employee Name</th>
                                              <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Manager Ecode*</th>
                                              <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Manager Name*</th>
                                              <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Coordinator Ecode</th>
                                              <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Coordinator Name</th>
                                              <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Lead Ecode</th>
                                              <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Lead Name</th>
                                              <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Employee Designation</th>
                                              <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Employee DOJ</th>
                                              <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Employee Experience</th>
                                              <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Employee Skill</th>
                                              <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Employee Account*</th>
                                              <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Employee Project*</th>

                                              <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Billing / Shadow / Bench - Current*
                                                    <i class="fas fa-question text-danger" style="cursor: pointer"
                                                         title=" Billing : Billing resources &#013;Partial Billing : Resources partially billing in your project &#013;Bench : Non billing Resources, not allocated or projected for allocation and available in open pool &#013;Shadow : Non billing resources, who are projected for allocation and/or fixed allocation to project, not available for allocation &#013;Trainee : Non billing Resources, in training, joined as fresher &#013;Internal : Working on inhouse Trantor Projects &#013;Support : Recruitment, ISO, Admin, Finance, IT and HR &#013;Overhead : Non billing Resources, managing account or project &#013;Business Readiness : Non billing resources, hired for anticipated project"></i>
                                              </th>
                                              <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Billing / Shadow / Bench - Current + 1 month*
                                                    <i class="fas fa-question text-danger" style="cursor: pointer"
                                                         title=" Billing : Billing resources &#013;Partial Billing : Resources partially billing in your project &#013;Bench : Non billing Resources, not allocated or projected for allocation and available in open pool &#013;Shadow : Non billing resources, who are projected for allocation and/or fixed allocation to project, not available for allocation &#013;Trainee : Non billing Resources, in training, joined as fresher &#013;Internal : Working on inhouse Trantor Projects &#013;Support : Recruitment, ISO, Admin, Finance, IT and HR &#013;Overhead : Non billing Resources, managing account or project &#013;Business Readiness : Non billing resources, hired for anticipated project"></i>
                                              </th>
                                              <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Billing / Shadow / Bench - Current + 2 month*
                                                    <i class="fas fa-question text-danger" style="cursor: pointer"
                                                         title=" Billing : Billing resources &#013;Partial Billing : Resources partially billing in your project &#013;Bench : Non billing Resources, not allocated or projected for allocation and available in open pool &#013;Shadow : Non billing resources, who are projected for allocation and/or fixed allocation to project, not available for allocation &#013;Trainee : Non billing Resources, in training, joined as fresher &#013;Internal : Working on inhouse Trantor Projects &#013;Support : Recruitment, ISO, Admin, Finance, IT and HR &#013;Overhead : Non billing Resources, managing account or project &#013;Business Readiness : Non billing resources, hired for anticipated project"></i>
                                              </th>
                                              <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Employee Status*</th>
                                              <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Last Working Date</th>

                                              <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Bench Start Date</th>
                                              <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Shadow Resource Type (for shadow resources)</th>
                                              <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Shadow Start Date</th>
                                              <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Billing Start Date</th>
                                              <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Billing End Date</th>
                                              <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">% Utilization Per Month</th>

                                              <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Remarks</th>
                                              <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Vertical*</th>
                                            </tr>
                                        </thead>
                                        <tbody id="hrSharedTablebBody">
                                                <c:forEach items="${hrSharedEmployeeList}" var="item"
                                                    varStatus="count">
                                                        <td class="managerHrSharedColFixed1"><input type="checkbox"
                                                           style="margin-top: 10px;" value="${item.billingId}" projectUpdated="${item.projectUpdated}" id="hSharedCheckbox${count.index}" name="<portlet:namespace/>hSharedCheckbox${count.index}" onClick="changeFormToEditMode(this,'shared')"/>
                                                        </td>
                                                        <td class="p-0 managerHrSharedColFixed2">
                                                            <button type="submit" onClick="validateActionPerformed(1,'h')" value="${item.billingId}" name="<portlet:namespace/>deleting" class="btn-danger">
                                                                <i class="far fa-trash-alt" style="cursor: pointer"></i>
                                                            </button>
                                                        </td>
                                                        <td class="managerHrSharedColFixed3" data-search="${item.employeeCode}" data-order="${item.employeeCode}"><input type="text" class="form-control-sm form-control-plaintext pl-3" readonly="readonly" value="${item.employeeCode}" name="<portlet:namespace/>hSharedEcode${count.index}"/></td>
                                                        <td class="managerHrSharedColFixed4" data-search="${item.employeeName}" data-order="${item.employeeName}"><input type="text" class="form-control-plaintext form-control-sm text-center" readonly="readonly" value="${item.employeeName}" /></td>
                                                        <td class="p-0 managerHrSharedExtraCol"><input type="hidden" value="${item.managerEcode}" id="ohSharedManagerEcode${count.index}"/>
                                                        <input type="text" <c:choose> <c:when  test="${item.projectUpdated}"> class="noedit form-control-sm form-control-plaintext pl-3" </c:when>
                                                         <c:otherwise> class="noedit form-control form-control-sm" </c:otherwise> </c:choose> maxlength="75"
                                                                    onblur="fetchAndValidateEmployeeDetails(this)" disabled id="hSharedManagerEcode${count.index}" name="<portlet:namespace/>hSharedManagerEcode${count.index}"  value="${item.managerEcode}" />
                                                        </td>
                                                        <td class="p-0 managerHrSharedExtraCol"><input type="hidden" value="${item.managerName}" id="ohSharedManagerName${count.index}"/>
                                                        <input type="text"<c:choose> <c:when  test="${item.projectUpdated}"> class="form-control-sm form-control-plaintext pl-3" </c:when>
                                                        <c:otherwise> class="form-control form-control-sm" </c:otherwise> </c:choose> maxlength="75"
                                                                     readonly="readonly" id="hSharedManagerName${count.index}" name="<portlet:namespace/>hSharedManagerName${count.index}"  value="${item.managerName}" />
                                                        </td>
                                                        <td class="p-0 managerHrSharedExtraCol"><input type="hidden" value="${item.coordinatorEcode}" id="ohSharedCoordinatorEcode${count.index}"/>
                                                        <input type="text" <c:choose> <c:when  test="${item.projectUpdated}"> class="noedit form-control-sm form-control-plaintext pl-3" </c:when>
                                                           <c:otherwise> class="noedit form-control form-control-sm" </c:otherwise> </c:choose> maxlength="75"
                                                                    onblur="fetchAndValidateEmployeeDetails(this)" disabled id="hSharedCoordinatorEcode${count.index}" name="<portlet:namespace/>hSharedCoordinatorEcode${count.index}"  value="${item.coordinatorEcode}" />
                                                        </td>
                                                        <td class="p-0 managerHrSharedExtraCol"><input type="hidden" value="${item.coordinatorName}" id="ohSharedCoordinatorName${count.index}"/>
                                                        <input type="text" <c:choose> <c:when  test="${item.projectUpdated}"> class="form-control-sm form-control-plaintext pl-3" </c:when>
                                                          <c:otherwise> class="form-control form-control-sm" </c:otherwise> </c:choose> maxlength="75"
                                                                    readonly="readonly" id="hSharedCoordinatorName${count.index}" name="<portlet:namespace/>hSharedCoordinatorName${count.index}"  value="${item.coordinatorName}" />
                                                        </td>
                                                        <td class="p-0 managerHrSharedExtraCol"><input type="hidden" value="${item.leadEcode}" id="ohSharedleadEcode${count.index}"/>
                                                        <input type="text" <c:choose> <c:when  test="${item.projectUpdated}"> class="noedit form-control-sm form-control-plaintext pl-3" </c:when>
                                                           <c:otherwise> class="noedit form-control form-control-sm" </c:otherwise> </c:choose> maxlength="75"
                                                                    onblur="fetchAndValidateEmployeeDetails(this)" disabled id="hSharedleadEcode${count.index}" name="<portlet:namespace/>hSharedleadEcode${count.index}"  value="${item.leadEcode}" />
                                                        </td>
                                                        <td class="p-0 managerHrSharedExtraCol"><input type="hidden" value="${item.leadName}" id="ohSharedleadName${count.index}"/>
                                                        <input type="text" <c:choose> <c:when  test="${item.projectUpdated}"> class="form-control-sm form-control-plaintext pl-3" </c:when>
                                                           <c:otherwise> class="form-control form-control-sm" </c:otherwise> </c:choose> maxlength="75"
                                                                    readonly="readonly" id="hSharedleadName${count.index}" name="<portlet:namespace/>hSharedleadName${count.index}"  value="${item.leadName}" />
                                                        </td>
                                                        <td class="managerHrSharedExtraCol">${item.employeeDesignation}</td>
                                                        <td class="managerHrSharedExtraCol">${item.doj}</td>
                                                        <td class="managerHrSharedExtraCol">${item.experience}</td>
                                                        <td class="managerHrSharedExtraCol">${item.skill}</td>
                                                        <td class="managerHrSharedExtraCol"><input type="hidden" value="${item.account}" id="ohSharedAccount${count.index}"/>
                                                        <input type="text" <c:choose> <c:when  test="${item.projectUpdated}"> class="form-control-sm form-control-plaintext pl-3" </c:when>
                                                          <c:otherwise> class="form-control form-control-sm" </c:otherwise> </c:choose> maxlength="75"
                                                                   onblur="$(this).val($(this).val().trim())" readonly="readonly" id="hSharedAccount${count.index}" name="<portlet:namespace/>hSharedAccount${count.index}"  value="${item.account}" required="required"/>
                                                        </td>
                                                        <td class="managerHrSharedExtraCol"><input type="hidden" value="${item.project}" id="ohSharedProject${count.index}"/>
                                                        <input type="text" <c:choose> <c:when  test="${item.projectUpdated}"> class="form-control-sm form-control-plaintext pl-3" </c:when>
                                                         <c:otherwise> class="form-control form-control-sm" </c:otherwise> </c:choose> maxlength="75"
                                                                   onblur="$(this).val($(this).val().trim())" readonly="readonly" id="hSharedProject${count.index}" name="<portlet:namespace/>hSharedProject${count.index}"  value="${item.project}" required="required"/>
                                                        </td>

                                                        <td class="p-0 managerHrSharedExtraCol" data-search="${item.current}" data-order="${item.current}"><input type="hidden" value="${item.current == '-' ? '' : item.current}" id="ohSharedCurrent${count.index}"/>
                                                            <select class="mdb-select md-form form-control form-control-sm w-100"  id="hSharedCurrent${count.index}" name="<portlet:namespace/>hSharedCurrent${count.index}" onchange="return validateColumnsOnSelect(this,'shared');">
                                                                <option value="" disabled selected>Select</option>
                                                               <option ${item.current == 'Bench' ? 'selected="selected"': 'style="display:none"'} value="Bench">Bench</option>
                                                              <option ${item.current == 'Bench - Exit' ? 'selected="selected"': 'style="display:none"'} value="Bench - Exit">Bench - Exit</option>
                                                              <option ${item.current == 'Bench - Maternity' ? 'selected="selected"': 'style="display:none"'} value="Bench - Maternity">Bench - Maternity</option>
                                                              <option ${item.current == 'Billing' ? 'selected="selected"': 'style="display:none"'} value="Billing">Billing</option>
                                                              <option ${item.current == 'Business Readiness' ? 'selected="selected"': 'style="display:none"'} value="Business Readiness">Business Readiness</option>
                                                              <option ${item.current == 'Internal' ? 'selected="selected"': 'style="display:none"'} value="Internal">Internal</option>
                                                              <option ${item.current == 'Internal - Exit' ? 'selected="selected"': 'style="display:none"'} value="Internal - Exit">Internal - Exit</option>
                                                              <option ${item.current == 'Management' ? 'selected="selected"': 'style="display:none"'} value="Management">Management</option>
                                                              <option ${item.current == 'Overhead' ? 'selected="selected"': 'style="display:none"'} value="Overhead">Overhead</option>
                                                              <option ${item.current == 'Partial Billing' ? 'selected="selected"': 'style="display:none"'} value="Partial Billing">Partial Billing</option>
                                                              <option ${item.current == 'Sales & Marketing' ? 'selected="selected"': 'style="display:none"'} value="Sales & Marketing">Sales & Marketing</option>
                                                              <option ${item.current == 'Shadow' ? 'selected="selected"': 'style="display:none"'} value="Shadow">Shadow</option>
                                                              <option ${item.current == 'Shadow - Client Committed/24*7 Support' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Client Committed/24*7 Support">Shadow - Client Committed/24*7 Support</option>
                                                              <option ${item.current == 'Shadow - Exit' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Exit">Shadow - Exit</option>
                                                              <option ${item.current == 'Shadow - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Planned Billing">Shadow - Planned Billing</option>
                                                              <option ${item.current == 'Support' ? 'selected="selected"': 'style="display:none"'} value="Support">Support</option>
                                                              <option ${item.current == 'Trainee' ? 'selected="selected"': 'style="display:none"'} value="Trainee">Trainee</option>
                                                              <option ${item.current == 'Trainee - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Trainee - Planned Billing">Trainee - Planned Billing</option>
                                                           </select>
                                                        </td>
                                                        <td class="p-0 managerHrSharedExtraCol" data-search="${item.currentPlusOne}" data-order="${item.currentPlusOne}"><input type="hidden" value="${item.currentPlusOne == '-' ? '' : item.currentPlusOne}" id="ohSharedCurrentPlusOne${count.index}"/>
                                                            <select class="mdb-select md-form form-control form-control-sm w-100"  id="hSharedCurrentPlusOne${count.index}" name="<portlet:namespace/>hSharedCurrentPlusOne${count.index}" onchange="return validateColumnsOnSelect(this,'shared');">
                                                                <option value="" disabled selected>Select</option>
                                                               <option ${item.currentPlusOne == 'Bench' ? 'selected="selected"': 'style="display:none"'} value="Bench">Bench</option>
                                                              <option ${item.currentPlusOne == 'Bench - Exit' ? 'selected="selected"': 'style="display:none"'} value="Bench - Exit">Bench - Exit</option>
                                                              <option ${item.currentPlusOne == 'Bench - Maternity' ? 'selected="selected"': 'style="display:none"'} value="Bench - Maternity">Bench - Maternity</option>
                                                              <option ${item.currentPlusOne == 'Billing' ? 'selected="selected"': 'style="display:none"'} value="Billing">Billing</option>
                                                              <option ${item.currentPlusOne == 'Business Readiness' ? 'selected="selected"': 'style="display:none"'} value="Business Readiness">Business Readiness</option>
                                                              <option ${item.currentPlusOne == 'Internal' ? 'selected="selected"': 'style="display:none"'} value="Internal">Internal</option>
                                                              <option ${item.currentPlusOne == 'Internal - Exit' ? 'selected="selected"': 'style="display:none"'} value="Internal - Exit">Internal - Exit</option>
                                                              <option ${item.currentPlusOne == 'Management' ? 'selected="selected"': 'style="display:none"'} value="Management">Management</option>
                                                              <option ${item.currentPlusOne == 'Overhead' ? 'selected="selected"': 'style="display:none"'} value="Overhead">Overhead</option>
                                                              <option ${item.currentPlusOne == 'Partial Billing' ? 'selected="selected"': 'style="display:none"'} value="Partial Billing">Partial Billing</option>
                                                              <option ${item.currentPlusOne == 'Sales & Marketing' ? 'selected="selected"': 'style="display:none"'} value="Sales & Marketing">Sales & Marketing</option>
                                                              <option ${item.currentPlusOne == 'Shadow' ? 'selected="selected"': 'style="display:none"'} value="Shadow">Shadow</option>
                                                              <option ${item.currentPlusOne == 'Shadow - Client Committed/24*7 Support' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Client Committed/24*7 Support">Shadow - Client Committed/24*7 Support</option>
                                                              <option ${item.currentPlusOne == 'Shadow - Exit' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Exit">Shadow - Exit</option>
                                                              <option ${item.currentPlusOne == 'Shadow - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Planned Billing">Shadow - Planned Billing</option>
                                                              <option ${item.currentPlusOne == 'Support' ? 'selected="selected"': 'style="display:none"'} value="Support">Support</option>
                                                              <option ${item.currentPlusOne == 'Trainee' ? 'selected="selected"': 'style="display:none"'} value="Trainee">Trainee</option>
                                                              <option ${item.currentPlusOne == 'Trainee - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Trainee - Planned Billing">Trainee - Planned Billing</option>
                                                           </select>
                                                        </td>
                                                        <td class="p-0 managerHrSharedExtraCol" data-search="${item.currentPlusTwo}" data-order="${item.currentPlusTwo}"><input type="hidden" value="${item.currentPlusTwo == '-' ? '' : item.currentPlusTwo}" id="ohSharedCurrentPlusTwo${count.index}"/>
                                                            <select class="mdb-select md-form form-control form-control-sm w-100" id="hSharedCurrentPlusTwo${count.index}" name="<portlet:namespace/>hSharedCurrentPlusTwo${count.index}" onchange="return validateColumnsOnSelect(this,'shared');">
                                                                <option value="" disabled selected>Select</option>
                                                               <option ${item.currentPlusTwo == 'Bench' ? 'selected="selected"': 'style="display:none"'} value="Bench">Bench</option>
                                                              <option ${item.currentPlusTwo == 'Bench - Exit' ? 'selected="selected"': 'style="display:none"'} value="Bench - Exit">Bench - Exit</option>
                                                              <option ${item.currentPlusTwo == 'Bench - Maternity' ? 'selected="selected"': 'style="display:none"'} value="Bench - Maternity">Bench - Maternity</option>
                                                              <option ${item.currentPlusTwo == 'Billing' ? 'selected="selected"': 'style="display:none"'} value="Billing">Billing</option>
                                                              <option ${item.currentPlusTwo == 'Business Readiness' ? 'selected="selected"': 'style="display:none"'} value="Business Readiness">Business Readiness</option>
                                                              <option ${item.currentPlusTwo == 'Internal' ? 'selected="selected"': 'style="display:none"'} value="Internal">Internal</option>
                                                              <option ${item.currentPlusTwo == 'Internal - Exit' ? 'selected="selected"': 'style="display:none"'} value="Internal - Exit">Internal - Exit</option>
                                                              <option ${item.currentPlusTwo == 'Management' ? 'selected="selected"': 'style="display:none"'} value="Management">Management</option>
                                                              <option ${item.currentPlusTwo == 'Overhead' ? 'selected="selected"': 'style="display:none"'} value="Overhead">Overhead</option>
                                                              <option ${item.currentPlusTwo == 'Partial Billing' ? 'selected="selected"': 'style="display:none"'} value="Partial Billing">Partial Billing</option>
                                                              <option ${item.currentPlusTwo == 'Sales & Marketing' ? 'selected="selected"': 'style="display:none"'} value="Sales & Marketing">Sales & Marketing</option>
                                                              <option ${item.currentPlusTwo == 'Shadow' ? 'selected="selected"': 'style="display:none"'} value="Shadow">Shadow</option>
                                                              <option ${item.currentPlusTwo == 'Shadow - Client Committed/24*7 Support' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Client Committed/24*7 Support">Shadow - Client Committed/24*7 Support</option>
                                                              <option ${item.currentPlusTwo == 'Shadow - Exit' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Exit">Shadow - Exit</option>
                                                              <option ${item.currentPlusTwo == 'Shadow - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Planned Billing">Shadow - Planned Billing</option>
                                                              <option ${item.currentPlusTwo == 'Support' ? 'selected="selected"': 'style="display:none"'} value="Support">Support</option>
                                                              <option ${item.currentPlusTwo == 'Trainee' ? 'selected="selected"': 'style="display:none"'} value="Trainee">Trainee</option>
                                                              <option ${item.currentPlusTwo == 'Trainee - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Trainee - Planned Billing">Trainee - Planned Billing</option>
                                                           </select>
                                                        </td>
                                                        <td class="p-0 managerHrSharedExtraCol" data-search="${item.employeeStatus}" data-order="${item.employeeStatus}"><input type="hidden" value="${item.employeeStatus == '-' ? '' : item.employeeStatus}" id="ohSharedemployeeStatus${count.index}"/>
                                                            <select class="mdb-select md-form form-control form-control-sm w-100"  id="hSharedemployeeStatus${count.index}" name="<portlet:namespace/>hSharedemployeeStatus${count.index}" onchange="return validateColumnsOnSelect(this,'shared');">
                                                                <option value="" disabled selected>Select</option>
                                                                <option ${item.employeeStatus == 'Active' ? 'selected="selected"': 'style="display:none"'} value="Active">Active</option>
                                                                <option ${item.employeeStatus == 'Resigned' ? 'selected="selected"': 'style="display:none"'} value="Resigned">Resigned</option>
                                                                <option ${item.employeeStatus == 'Under Monitoring' ? 'selected="selected"': 'style="display:none"'} value="Under Monitoring">Under Monitoring</option>
                                                                <option ${item.employeeStatus == 'Maternity Leave' ? 'selected="selected"': 'style="display:none"'} value="Maternity Leave">Maternity Leave</option>
                                                                <option ${item.employeeStatus == 'Ramp up period' ? 'selected="selected"': 'style="display:none"'} value="Ramp up period">Ramp up period</option>
                                                                <option ${item.employeeStatus == 'Sabbatical Leave' ? 'selected="selected"': 'style="display:none"'} value="Sabbatical Leave">Sabbatical Leave</option>
                                                                <option ${item.employeeStatus == 'Deferred Billing' ? 'selected="selected"': 'style="display:none"'} value="Deferred Billing">Deferred Billing</option>
                                                           </select>
                                                        </td>
                                                        <td class="p-0 managerHrSharedExtraCol"><input type="hidden" <c:if test="${item.lastWorkingDate != '-'}">value="${item.lastWorkingDate}" </c:if> id="ohSharedlastWorkingDate${count.index}"/>
                                                            <input type="date" class ="form-control form-control-sm" style="cursor: pointer" onkeydown="return false" readonly="readonly"  id="hSharedlastWorkingDate${count.index}" name="<portlet:namespace/>hSharedlastWorkingDate${count.index}" <c:if test="${item.lastWorkingDate != '-'}">value="${item.lastWorkingDate}" </c:if> />
                                                        </td>
                                                        <td class="p-0 managerHrSharedExtraCol"><input type="hidden" <c:if test="${item.benchStartDate != '-'}">value="${item.benchStartDate}" </c:if> id="ohSharedbenchStartDate${count.index}"/>
                                                            <input type="date" class ="form-control form-control-sm" style="cursor: pointer" readonly="readonly" onkeydown="return false" id="hSharedbenchStartDate${count.index}"  name="<portlet:namespace/>hSharedbenchStartDate${count.index}" <c:if test="${item.benchStartDate != '-'}">value="${item.benchStartDate}" </c:if> />
                                                        </td>
                                                        <td class="p-0 managerHrSharedExtraCol"><input type="hidden" value="${item.shadowResourceType == '-' ? '' : item.shadowResourceType}" id="ohSharedShadowResourceType${count.index}"/>
                                                            <select class="mdb-select md-form form-control form-control-sm w-100" id="hSharedShadowResourceType${count.index}" name="<portlet:namespace/>hSharedShadowResourceType${count.index}">
                                                                <option value="" disabled selected>Select</option>
                                                                <option ${item.shadowResourceType == 'Client Requested' ? 'selected="selected"': 'style="display:none"'} value="Client Requested">Client Requested</option>
                                                                <option ${item.shadowResourceType == 'Strategic Hiring' ? 'selected="selected"': 'style="display:none"'} value="Strategic Hiring">Strategic Hiring</option>
                                                                <option ${item.shadowResourceType == 'Replacement' ? 'selected="selected"': 'style="display:none"'} value="Replacement">Replacement</option>
                                                           </select>
                                                        </td>
                                                        <td class="p-0 managerHrSharedExtraCol"><input type="hidden" <c:if test="${item.shadowStartDate != '-'}">value="${item.shadowStartDate}"</c:if> id="ohSharedshadowStartDate${count.index}"/>
                                                            <input type="date" class ="form-control form-control-sm" style="cursor: pointer" readonly="readonly" onkeydown="return false" id="hSharedshadowStartDate${count.index}"  name="<portlet:namespace/>hSharedshadowStartDate${count.index}" <c:if test="${item.shadowStartDate != '-'}">value="${item.shadowStartDate}"</c:if> />
                                                        </td>
                                                        <td class="p-0 managerHrSharedExtraCol"><input type="hidden" <c:if test="${item.billingStartDate != '-'}"> value="${item.billingStartDate}" </c:if> id="ohSharedbillingStartDate${count.index}"/>
                                                            <input type="date" class ="form-control form-control-sm" style="cursor: pointer" readonly="readonly" onkeydown="return false" id="hSharedbillingStartDate${count.index}" name="<portlet:namespace/>hSharedbillingStartDate${count.index}" <c:if test="${item.billingStartDate != '-'}"> value="${item.billingStartDate}" </c:if> />
                                                        </td>
                                                        <td class="p-0 managerHrSharedExtraCol"><input type="hidden" <c:if test="${item.billingEndDate != '-'}">value="${item.billingEndDate}" </c:if> id="ohSharedbillingEndDate${count.index}"/>
                                                            <input type="date" class ="form-control form-control-sm" style="cursor: pointer" readonly="readonly" onkeydown="return false" id="hSharedbillingEndDate${count.index}" name="<portlet:namespace/>hSharedbillingEndDate${count.index}" <c:if test="${item.billingEndDate != '-'}">value="${item.billingEndDate}" </c:if> />
                                                        </td>
                                                        <td class="p-0 managerHrSharedExtraCol"><input type="hidden" value="${item.percentUtilization == '-' ? '100' : item.percentUtilization}" id="ohSharedpercentUtilization${count.index}"/>
                                                            <input type="number" class="form-control form-control-sm"
                                                                readonly="readonly" max="100" min="0" id="hSharedpercentUtilization${count.index}" value="${item.percentUtilization == '-' ? '100' : item.percentUtilization}"  name="<portlet:namespace/>hSharedpercentUtilization${count.index}">
                                                        </td>

                                                        <td class="p-0 managerHrSharedExtraCol"><input type="hidden" value="${item.remarks == '-' ? '' : item.remarks}" id="ohSharedRemarks${count.index}"/>
                                                            <input type="text" class="form-control form-control-sm" maxlength="200"
                                                                    onblur="$(this).val($(this).val().trim())" readonly="readonly" id="hSharedRemarks${count.index}" name="<portlet:namespace/>hSharedRemarks${count.index}"  value="${item.remarks == '-' ? '' : item.remarks}" />
                                                        </td>
                                                        <td class="p-0 managerHrSharedExtraCol"><input type="hidden" value="${item.vertical == '-' ? '' : item.vertical}"  id="ohSharedVertical${count.index}"/>
                                                            <select class="mdb-select md-form form-control form-control-sm w-100" id="hSharedVertical${count.index}" name="<portlet:namespace/>hSharedVertical${count.index}">
                                                                <option value="" disabled selected>Select</option>
                                                                <option ${item.vertical == 'BBU' ? 'selected="selected"': 'style="display:none"'} value="BBU">BBU</option>
                                                                <option ${item.vertical == 'Captive CoE' ? 'selected="selected"': 'style="display:none"'} value="Captive CoE">Captive CoE</option>
                                                                <option ${item.vertical == 'Cloud' ? 'selected="selected"': 'style="display:none"'} value="Cloud">Cloud</option>
                                                                <option ${item.vertical == 'Enterprise' ? 'selected="selected"': 'style="display:none"'} value="Enterprise">Enterprise</option>
                                                                <option ${item.vertical == 'Other' ? 'selected="selected"': 'style="display:none"'} value="Other">Other</option>
                                                                <option ${item.vertical == 'Sales' ? 'selected="selected"': 'style="display:none"'} value="Sales">Sales</option>
                                                                <option ${item.vertical == 'Support' ? 'selected="selected"': 'style="display:none"'} value="Support">Support</option>
                                                            </select>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                        </tbody>
                                    </table>
                                    <br>
                                    <button type="button" class="btn btn-outline-success btn-sm"
                                    		 onclick="addNewRow('h')">
                                    		<i class="fas fa-plus"></i> Add Employee
                                    </button>
                                    <div class="row">
                                        <div class="col-md-6 text-right">
                                            <button class="btn btn-primary" onClick="validateActionPerformed(2,'h')" type="submit">Submit</button>
                                        </div>
                                    </div>
                                </form>
        					</div>
        				</div>
					</div>
                    <div class="card">
                        <div class="card-header">
                            <div class="row">
                                <div class="col pt-2"> <h3>Help - Billing User Manual </h3></div>
                                <div class="col-2 text-right" ><input type="button" class="btn btn-link"  onClick="downloadManual(2);"  value="Download the file" /></div>
                            </div>
                        </div>
                    </div>
        </div>
        <div class="tab-pane fade" id="leadership" role="tabpanel" aria-labelledby="leadership-tab">
        			<div id="leadershipDiv">
                        <div class="card">
							<div class="card-header">
							    <div class="row">
									<div class="col pt-2">
                                         <h3>All Employees</h3>
									</div>
								</div>
							</div>
							<div class="card-body">
								<table
									class="table table-sm table-bordered table-striped table-hover table-custom text-center"
									id="leadershipTable">
									<thead class="thead-dark">
										<tr>
                                          <th scope="col" class="leaderShipColFixed1" style="padding-top: 14px !important;">Ecode</th>
                                          <th scope="col" class="leaderShipColFixed2" style="padding-top: 14px !important;">Name</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Manager Ecode</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Manager Name</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Coordinator Ecode</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Coordinator Name</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Lead Ecode</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Lead Name</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Designation</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">DOJ</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Experience</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Skill</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Account</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Project</th>

                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Billing / Shadow / Bench - Current
                                                <i class="fas fa-question text-danger" style="cursor: pointer"
                                                      title=" Billing : Billing resources &#013;Partial Billing : Resources partially billing in your project &#013;Bench : Non billing Resources, not allocated or projected for allocation and available in open pool &#013;Shadow : Non billing resources, who are projected for allocation and/or fixed allocation to project, not available for allocation &#013;Trainee : Non billing Resources, in training, joined as fresher &#013;Internal : Working on inhouse Trantor Projects &#013;Support : Recruitment, ISO, Admin, Finance, IT and HR &#013;Overhead : Non billing Resources, managing account or project &#013;Business Readiness : Non billing resources, hired for anticipated project"></i>
                                          </th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Billing / Shadow / Bench - Current + 1 month
                                                <i class="fas fa-question text-danger" style="cursor: pointer"
                                                      title=" Billing : Billing resources &#013;Partial Billing : Resources partially billing in your project &#013;Bench : Non billing Resources, not allocated or projected for allocation and available in open pool &#013;Shadow : Non billing resources, who are projected for allocation and/or fixed allocation to project, not available for allocation &#013;Trainee : Non billing Resources, in training, joined as fresher &#013;Internal : Working on inhouse Trantor Projects &#013;Support : Recruitment, ISO, Admin, Finance, IT and HR &#013;Overhead : Non billing Resources, managing account or project &#013;Business Readiness : Non billing resources, hired for anticipated project"></i>
                                          </th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Billing / Shadow / Bench - Current + 2 month
                                                <i class="fas fa-question text-danger" style="cursor: pointer"
                                                      title=" Billing : Billing resources &#013;Partial Billing : Resources partially billing in your project &#013;Bench : Non billing Resources, not allocated or projected for allocation and available in open pool &#013;Shadow : Non billing resources, who are projected for allocation and/or fixed allocation to project, not available for allocation &#013;Trainee : Non billing Resources, in training, joined as fresher &#013;Internal : Working on inhouse Trantor Projects &#013;Support : Recruitment, ISO, Admin, Finance, IT and HR &#013;Overhead : Non billing Resources, managing account or project &#013;Business Readiness : Non billing resources, hired for anticipated project"></i>
                                          </th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Shadow Resource Type (only for shadow resources)</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Shadow Start Date</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Bench Start Date</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Billing Start Date</th>

                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Allocation Status</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Employee Status</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Last Working Date</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Billing End Date</th>

                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Percentage Utilization Per Month</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Billable Hours</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Employee Role</th>
										  <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Vertical</th>
										  <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Remarks</th>
										</tr>
									</thead>
                                    <tbody>
										<c:forEach items="${leaderShipEmployeeList}" var="item"
											varStatus="count">
											<tr>
											    <td class="leaderShipColFixed1">${item.employeeCode}</td>
                                            	<td class="leaderShipColFixed2">${item.employeeName}</td>
												<td class="leaderShipExtraCol">${item.managerEcode}</td>
												<td class="leaderShipExtraCol">${item.managerName}</td>
                                                <td class="leaderShipExtraCol">${item.coordinatorEcode}</td>
												<td class="leaderShipExtraCol">${item.coordinatorName}</td>
												<td class="leaderShipExtraCol">${item.leadEcode}</td>
												<td class="leaderShipExtraCol">${item.leadName}</td>
												<td class="leaderShipExtraCol">${item.employeeDesignation}</td>
												<td class="leaderShipExtraCol">${item.doj}</td>
												<td class="leaderShipExtraCol">${item.experience}</td>
												<td class="leaderShipExtraCol">${item.skill}</td>
												<td class="leaderShipExtraCol">${item.account}</td>
												<td class="leaderShipExtraCol">${item.project}</td>

                                                <td class="leaderShipExtraCol">${item.current}</td>
                                                <td class="leaderShipExtraCol">${item.currentPlusOne}</td>
                                                <td class="leaderShipExtraCol">${item.currentPlusTwo}</td>
                                                <td class="leaderShipExtraCol">${item.shadowResourceType}</td>
                                                <td class="leaderShipExtraCol">${item.shadowStartDate}</td>
                                                <td class="leaderShipExtraCol">${item.benchStartDate}</td>
                                                <td class="leaderShipExtraCol">${item.billingStartDate}</td>

                                                <td class="leaderShipExtraCol">${item.allocationStatus}</td>
                                                <td class="leaderShipExtraCol">${item.employeeStatus}</td>
                                                <td class="leaderShipExtraCol">${item.lastWorkingDate}</td>
                                                <td class="leaderShipExtraCol">${item.billingEndDate}</td>

                                                <td class="leaderShipExtraCol">${item.percentUtilization}</td>
                                                <td class="leaderShipExtraCol">${item.billableHours}</td>
                                                <td class="leaderShipExtraCol">${item.employeeRole}</td>
                                                <td class="leaderShipExtraCol">${item.vertical}</td>
                                                <td class="leaderShipExtraCol">${item.remarks}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
                        <div class="card">
							<div class="card-header">
								<div class="row">
								    <div class="col pt-2">
                                         <h3>Partial/Shared Resource</h3>
									</div>
								</div>
							</div>
							<div class="card-body">
								<table
									class="table table-sm table-bordered table-striped table-hover table-custom text-center"
									id="sharedLeadershipTable">
									<thead class="thead-dark">
										<tr>
                                          <th scope="col" class="leaderShipColFixed1"  style="padding-top: 14px !important;height: auto;">Ecode</th>
                                          <th scope="col" class="leaderShipColFixed2"  style="padding-top: 14px !important;height: auto;">Name</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Manager Ecode</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Manager Name</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Coordinator Ecode</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Coordinator Name</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Lead Ecode</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Lead Name</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Designation</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">DOJ</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Experience</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Skill</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Account</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Project</th>

                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Billing / Shadow / Bench - Current
                                                <i class="fas fa-question text-danger" style="cursor: pointer"
                                                       title=" Billing : Billing resources &#013;Partial Billing : Resources partially billing in your project &#013;Bench : Non billing Resources, not allocated or projected for allocation and available in open pool &#013;Shadow : Non billing resources, who are projected for allocation and/or fixed allocation to project, not available for allocation &#013;Trainee : Non billing Resources, in training, joined as fresher &#013;Internal : Working on inhouse Trantor Projects &#013;Support : Recruitment, ISO, Admin, Finance, IT and HR &#013;Overhead : Non billing Resources, managing account or project &#013;Business Readiness : Non billing resources, hired for anticipated project"></i>
                                          </th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Billing / Shadow / Bench - Current + 1 month
                                                <i class="fas fa-question text-danger" style="cursor: pointer"
                                                       title=" Billing : Billing resources &#013;Partial Billing : Resources partially billing in your project &#013;Bench : Non billing Resources, not allocated or projected for allocation and available in open pool &#013;Shadow : Non billing resources, who are projected for allocation and/or fixed allocation to project, not available for allocation &#013;Trainee : Non billing Resources, in training, joined as fresher &#013;Internal : Working on inhouse Trantor Projects &#013;Support : Recruitment, ISO, Admin, Finance, IT and HR &#013;Overhead : Non billing Resources, managing account or project &#013;Business Readiness : Non billing resources, hired for anticipated project"></i>
                                          </th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Billing / Shadow / Bench - Current + 2 month
                                                <i class="fas fa-question text-danger" style="cursor: pointer"
                                                       title=" Billing : Billing resources &#013;Partial Billing : Resources partially billing in your project &#013;Bench : Non billing Resources, not allocated or projected for allocation and available in open pool &#013;Shadow : Non billing resources, who are projected for allocation and/or fixed allocation to project, not available for allocation &#013;Trainee : Non billing Resources, in training, joined as fresher &#013;Internal : Working on inhouse Trantor Projects &#013;Support : Recruitment, ISO, Admin, Finance, IT and HR &#013;Overhead : Non billing Resources, managing account or project &#013;Business Readiness : Non billing resources, hired for anticipated project"></i>
                                          </th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Shadow Resource Type (only for shadow resources)</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Shadow Start Date</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Bench Start Date</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Billing Start Date</th>

                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Employee Status</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Last Working Date</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Billing End Date</th>

                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Percentage Utilization Per Month</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Billable Hours</th>
                                          <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Employee Role</th>
										  <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Vertical</th>
										  <th scope="col" class="leaderShipExtraCol" style="padding: 0px 20px !important;">Remarks</th>
										</tr>
									</thead>
                                    <tbody>
										<c:forEach items="${leadershipSharedEmployeeList}" var="item"
											varStatus="count">
											<tr>
												<td class="leaderShipColFixed1">${item.employeeCode}</td>
												<td class="leaderShipColFixed2">${item.employeeName}</td>
												<td class="leaderShipExtraCol">${item.managerEcode}</td>
												<td class="leaderShipExtraCol">${item.managerName}</td>
                                                <td class="leaderShipExtraCol">${item.coordinatorEcode}</td>
                                                <td class="leaderShipExtraCol">${item.coordinatorName}</td>
                                                <td class="leaderShipExtraCol">${item.leadEcode}</td>
                                                <td class="leaderShipExtraCol">${item.leadName}</td>
												<td class="leaderShipExtraCol">${item.employeeDesignation}</td>
												<td class="leaderShipExtraCol">${item.doj}</td>
												<td class="leaderShipExtraCol">${item.experience}</td>
												<td class="leaderShipExtraCol">${item.skill}</td>
												<td class="leaderShipExtraCol">${item.account}</td>
												<td class="leaderShipExtraCol">${item.project}</td>

                                                <td class="leaderShipExtraCol">${item.current}</td>
                                                <td class="leaderShipExtraCol">${item.currentPlusOne}</td>
                                                <td class="leaderShipExtraCol">${item.currentPlusTwo}</td>
                                                <td class="leaderShipExtraCol">${item.shadowResourceType}</td>
                                                <td class="leaderShipExtraCol">${item.shadowStartDate}</td>
                                                <td class="leaderShipExtraCol">${item.benchStartDate}</td>
                                                <td class="leaderShipExtraCol">${item.billingStartDate}</td>

                                                <td class="leaderShipExtraCol">${item.employeeStatus}</td>
                                                <td class="leaderShipExtraCol">${item.lastWorkingDate}</td>
                                                <td class="leaderShipExtraCol">${item.billingEndDate}</td>

                                                <td class="leaderShipExtraCol">${item.percentUtilization}</td>
                                                <td class="leaderShipExtraCol">${item.billableHours}</td>
                                                <td class="leaderShipExtraCol">${item.employeeRole}</td>
                                                <td class="leaderShipExtraCol">${item.vertical}</td>
                                                <td class="leaderShipExtraCol">${item.remarks}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
                    <div class="card">
                        <div class="card-header">
                            <div class="row">
                                <div class="col pt-2"> <h3>Help - Billing User Manual </h3></div>
                                <div class="col-2 text-right" ><input type="button" class="btn btn-link"  onClick="downloadManual(4);"  value="Download the file" /></div>
                            </div>
                        </div>
                    </div>
        </div>


        <div class="tab-pane fade" id="finance" role="tabpanel" aria-labelledby="finance-tab">
        	<div id="financeDiv">
        		<div class="card">
        			<div class="card-header">
        				<div class="row">
        					<div class="col-3 pt-2 text-left">
        						<h3>Viewing Data For Month</h3>
                            </div>
        					<div class="col-2">
                                <div class="input-group">
                                    <input type="text" class="form-control" value="" readonly="true" id="dataMonthIdf" required="required"  data-format="MM-yyyy"/>
                                </div>
                            </div>
        					<div class="col-4">
                            </div>
                            <div class="col-3">
                                <form  method="post" action="${downloadEmployeeFinanceReportUrl}">
                                    <input type="hidden" id="requestReportDownload" value="${selectedMonthYear}" name="<portlet:namespace/>reporting" />
                                    <button class="btn btn-secondary btn-block" id="downloadFinanceReport" type="submit"><i class="fa fa-download" aria-hidden="true"></i> Download Report</button>
                                </form>
                            </div>
        				</div>
        			</div>
        			<div class="card-body">
        			    <form id="fBillingForm" method="post" onSubmit="return confirmFormSubmission('f');">
        			    <input type="hidden" id="fvalidateAction" value="" name="<portlet:namespace/>fvalidateAction" />
        			        <div class="card">
                                <div class="card-header">
                                    <div class="row pt-2">
                                         <div class="col-md-10">
                                            <h3>All Employees</h3><p>Column 'Allocation Status' is applicable only for Bench & Shadow resources<br>Green color is for non mandatory columns, Orange color is for mandatory columns, Violet color is for conditional columns.</p>
                                         </div>
                                         <div class="col-md-2 text-right">
                                             <a class="btn btn-secondary"
                                                onclick="return confirm('Any unsaved changes will be lost. Do you wish to continue ?')"
                                                href="${pageUrl[0]}" role="button"><i class="fa fa-undo" aria-hidden="true"></i> Reset</a>
                                         </div>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <input type="hidden" id="actionPerformedf" value="f" name="<portlet:namespace/>actionPerformed" />
                                    <input type="hidden" id="submitDatef" value="${selectedMonthYear}" name="<portlet:namespace/>submitDatef" />
                                    <table class="table table-sm table-bordered table-striped table-hover table-custom text-center" id="financeTable">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th scope="col" class="managerHrColFixed1" style="padding-top: 14px !important;height:auto;">Select</th>
                                                <th scope="col" class="managerHrColFixed2" style="padding-top: 14px !important;height: auto;">Ecode</th>
                                                <th scope="col" class="managerHrColFixed3" style="padding-top: 14px !important;height: auto;">Name</th>
                                                 <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Manager Ecode</th>
                                                 <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Manager Name</th>
                                                 <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Coordinator Ecode</th>
                                                 <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Coordinator Name</th>
                                                 <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Lead Ecode</th>
                                                 <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Lead Name</th>
                                                 <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Designation</th>
                                                 <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">DOJ</th>
                                                 <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Experience</th>
                                                 <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Skill</th>
                                                 <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Account</th>
                                                 <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Project</th>

                                                 <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Billing / Shadow / Bench - Current*
                                                      <i class="fas fa-question text-danger" style="cursor: pointer"
                                                      title=" Billing : Billing resources &#013;Partial Billing : Resources partially billing in your project &#013;Bench : Non billing Resources, not allocated or projected for allocation and available in open pool &#013;Shadow : Non billing resources, who are projected for allocation and/or fixed allocation to project, not available for allocation &#013;Trainee : Non billing Resources, in training, joined as fresher &#013;Internal : Working on inhouse Trantor Projects &#013;Support : Recruitment, ISO, Admin, Finance, IT and HR &#013;Overhead : Non billing Resources, managing account or project &#013;Business Readiness : Non billing resources, hired for anticipated project"></i>
                                                 </th>
                                                 <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Billing / Shadow / Bench - Current + 1 month*
                                                      <i class="fas fa-question text-danger" style="cursor: pointer"
                                                      title=" Billing : Billing resources &#013;Partial Billing : Resources partially billing in your project &#013;Bench : Non billing Resources, not allocated or projected for allocation and available in open pool &#013;Shadow : Non billing resources, who are projected for allocation and/or fixed allocation to project, not available for allocation &#013;Trainee : Non billing Resources, in training, joined as fresher &#013;Internal : Working on inhouse Trantor Projects &#013;Support : Recruitment, ISO, Admin, Finance, IT and HR &#013;Overhead : Non billing Resources, managing account or project &#013;Business Readiness : Non billing resources, hired for anticipated project"></i>
                                                  </th>
                                                 <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Billing / Shadow / Bench - Current + 2 month*
                                                      <i class="fas fa-question text-danger" style="cursor: pointer"
                                                      title=" Billing : Billing resources &#013;Partial Billing : Resources partially billing in your project &#013;Bench : Non billing Resources, not allocated or projected for allocation and available in open pool &#013;Shadow : Non billing resources, who are projected for allocation and/or fixed allocation to project, not available for allocation &#013;Trainee : Non billing Resources, in training, joined as fresher &#013;Internal : Working on inhouse Trantor Projects &#013;Support : Recruitment, ISO, Admin, Finance, IT and HR &#013;Overhead : Non billing Resources, managing account or project &#013;Business Readiness : Non billing resources, hired for anticipated project"></i>
                                                 </th>
                                                 <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Employee Status*</th>
                                                 <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Last Working Date</th>

                                                 <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Allocation Status
                                                        <i class="fas fa-question text-danger" style="cursor: pointer"
                                                         title=" Red - Cannot be allocated to other project &#013;Yellow - Can be allocated to other project after discussion &#013;Green - Available for immediate allocation"></i>
                                                 </th>
                                                 <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Bench Start Date</th>
                                                 <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Shadow Resource Type (for shadow resources)</th>
                                                 <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Shadow Start Date</th>
                                                 <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Billing Start Date</th>
                                                 <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Billing End Date</th>
                                                 <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">% Utilization Per Month</th>

                                                 <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Billable Hours*</th>
                                                 <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Month Hours*</th>
                                                 <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Employee Role</th>
                                                 <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Remarks</th>
                                                 <th scope="col" class="managerHrExtraCol" style="padding: 0px 20px !important;">Vertical*</th>
                                            </tr>
                                        </thead>
                                        <tbody id="financeTableBody">
                                            <c:forEach items="${financeEmployeeList}" var="item"  varStatus="count">
                                                <tr>
                                                    <td class="managerHrColFixed1">
                                                        <input type="checkbox" style="margin-top: 10px;" value="${item.billingId}" id="fCheckbox${count.count}" name="<portlet:namespace/>fCheckbox" onClick="changeFormToEditMode(this,'nonShared')"/>
                                                    </td>
                                                    <td class="managerHrColFixed2" data-search="${item.employeeCode}" data-order="${item.employeeCode}"><input type="text" class="form-control-plaintext form-control-sm text-center" readonly="readonly" value="${item.employeeCode}" /></td>
                                                    <td class="managerHrColFixed3" data-search="${item.employeeName}" data-order="${item.employeeName}"><input type="text" class="form-control-plaintext form-control-sm text-center" readonly="readonly" value="${item.employeeName}" /></td>
                                                    <td class="managerHrExtraCol">${item.managerEcode}</td>
                                                    <td class="managerHrExtraCol">${item.managerName}</td>
                                                    <td class="managerHrExtraCol">${item.coordinatorEcode}</td>
                                                    <td class="managerHrExtraCol">${item.coordinatorName}</td>
                                                    <td class="managerHrExtraCol">${item.leadEcode}</td>
                                                    <td class="managerHrExtraCol">${item.leadName}</td>
                                                    <td class="managerHrExtraCol">${item.employeeDesignation}</td>
                                                    <td class="managerHrExtraCol">${item.doj}</td>
                                                    <td class="managerHrExtraCol">${item.experience}</td>
                                                    <td class="managerHrExtraCol">${item.skill}</td>
                                                    <td class="managerHrExtraCol">${item.account}</td>
                                                    <td class="managerHrExtraCol">${item.project}</td>

                                                    <td class="p-0 managerHrExtraCol" data-search="${item.current}" data-order="${item.current}">
                                                        <input type="hidden" value="${item.current == '-' ? '' : item.current}" id="ofCurrent${count.count}"/>
                                                        <select class="mdb-select md-form form-control form-control-sm w-100" style="background-color: #F7DC6F;"  id="fCurrent${count.count}"
                                                                name="<portlet:namespace/>fCurrent${item.billingId}" onchange="return validateColumnsOnSelect(this,'nonShared');">
                                                                      <option value="" disabled selected>Select</option>
                                                                      <option ${item.current == 'Bench' ? 'selected="selected"': 'style="display:none"'} value="Bench">Bench</option>
                                                                      <option ${item.current == 'Bench - Exit' ? 'selected="selected"': 'style="display:none"'} value="Bench - Exit">Bench - Exit</option>
                                                                      <option ${item.current == 'Bench - Maternity' ? 'selected="selected"': 'style="display:none"'} value="Bench - Maternity">Bench - Maternity</option>
                                                                      <option ${item.current == 'Billing' ? 'selected="selected"': 'style="display:none"'} value="Billing">Billing</option>
                                                                      <option ${item.current == 'Business Readiness' ? 'selected="selected"': 'style="display:none"'} value="Business Readiness">Business Readiness</option>
                                                                      <option ${item.current == 'Internal' ? 'selected="selected"': 'style="display:none"'} value="Internal">Internal</option>
                                                                      <option ${item.current == 'Internal - Exit' ? 'selected="selected"': 'style="display:none"'} value="Internal - Exit">Internal - Exit</option>
                                                                      <option ${item.current == 'Management' ? 'selected="selected"': 'style="display:none"'} value="Management">Management</option>
                                                                      <option ${item.current == 'Overhead' ? 'selected="selected"': 'style="display:none"'} value="Overhead">Overhead</option>
                                                                      <option ${item.current == 'Partial Billing' ? 'selected="selected"': 'style="display:none"'} value="Partial Billing">Partial Billing</option>
                                                                      <option ${item.current == 'Sales & Marketing' ? 'selected="selected"': 'style="display:none"'} value="Sales & Marketing">Sales & Marketing</option>
                                                                      <option ${item.current == 'Shadow' ? 'selected="selected"': 'style="display:none"'} value="Shadow">Shadow</option>
                                                                      <option ${item.current == 'Shadow - Client Committed/24*7 Support' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Client Committed/24*7 Support">Shadow - Client Committed/24*7 Support</option>
                                                                      <option ${item.current == 'Shadow - Exit' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Exit">Shadow - Exit</option>
                                                                      <option ${item.current == 'Shadow - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Planned Billing">Shadow - Planned Billing</option>
                                                                      <option ${item.current == 'Support' ? 'selected="selected"': 'style="display:none"'} value="Support">Support</option>
                                                                      <option ${item.current == 'Trainee' ? 'selected="selected"': 'style="display:none"'} value="Trainee">Trainee</option>
                                                                      <option ${item.current == 'Trainee - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Trainee - Planned Billing">Trainee - Planned Billing</option>
                                                        </select>
                                                    </td>
                                                    <td class="p-0 managerHrExtraCol" data-search="${item.currentPlusOne}" data-order="${item.currentPlusOne}">
                                                        <input type="hidden" value="${item.currentPlusOne == '-' ? '' : item.currentPlusOne}" id="ofCurrentPlusOne${count.count}"/>
                                                        <select class="mdb-select md-form form-control form-control-sm w-100" style="background-color: #F7DC6F;" id="fCurrentPlusOne${count.count}"
                                                                name="<portlet:namespace/>fCurrentPlusOne${item.billingId}" onchange="return validateColumnsOnSelect(this,'nonShared');">
                                                            <option value="" disabled selected>Select</option>
                                                            <option ${item.currentPlusOne == 'Bench' ? 'selected="selected"': 'style="display:none"'} value="Bench">Bench</option>
                                                            <option ${item.currentPlusOne == 'Bench - Exit' ? 'selected="selected"': 'style="display:none"'} value="Bench - Exit">Bench - Exit</option>
                                                            <option ${item.currentPlusOne == 'Bench - Maternity' ? 'selected="selected"': 'style="display:none"'} value="Bench - Maternity">Bench - Maternity</option>
                                                            <option ${item.currentPlusOne == 'Billing' ? 'selected="selected"': 'style="display:none"'} value="Billing">Billing</option>
                                                            <option ${item.currentPlusOne == 'Business Readiness' ? 'selected="selected"': 'style="display:none"'} value="Business Readiness">Business Readiness</option>
                                                            <option ${item.currentPlusOne == 'Internal' ? 'selected="selected"': 'style="display:none"'} value="Internal">Internal</option>
                                                            <option ${item.currentPlusOne == 'Internal - Exit' ? 'selected="selected"': 'style="display:none"'} value="Internal - Exit">Internal - Exit</option>
                                                            <option ${item.currentPlusOne == 'Management' ? 'selected="selected"': 'style="display:none"'} value="Management">Management</option>
                                                            <option ${item.currentPlusOne == 'Overhead' ? 'selected="selected"': 'style="display:none"'} value="Overhead">Overhead</option>
                                                            <option ${item.currentPlusOne == 'Partial Billing' ? 'selected="selected"': 'style="display:none"'} value="Partial Billing">Partial Billing</option>
                                                            <option ${item.currentPlusOne == 'Sales & Marketing' ? 'selected="selected"': 'style="display:none"'} value="Sales & Marketing">Sales & Marketing</option>
                                                            <option ${item.currentPlusOne == 'Shadow' ? 'selected="selected"': 'style="display:none"'} value="Shadow">Shadow</option>
                                                            <option ${item.currentPlusOne == 'Shadow - Client Committed/24*7 Support' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Client Committed/24*7 Support">Shadow - Client Committed/24*7 Support</option>
                                                            <option ${item.currentPlusOne == 'Shadow - Exit' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Exit">Shadow - Exit</option>
                                                            <option ${item.currentPlusOne == 'Shadow - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Planned Billing">Shadow - Planned Billing</option>
                                                            <option ${item.currentPlusOne == 'Support' ? 'selected="selected"': 'style="display:none"'} value="Support">Support</option>
                                                            <option ${item.currentPlusOne == 'Trainee' ? 'selected="selected"': 'style="display:none"'} value="Trainee">Trainee</option>
                                                            <option ${item.currentPlusOne == 'Trainee - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Trainee - Planned Billing">Trainee - Planned Billing</option>
                                                       </select>
                                                    </td>
                                                    <td class="p-0 managerHrExtraCol" data-search="${item.currentPlusTwo}" data-order="${item.currentPlusTwo}">
                                                        <input type="hidden" value="${item.currentPlusTwo == '-' ? '' : item.currentPlusTwo}" id="ofCurrentPlusTwo${count.count}"/>
                                                        <select class="mdb-select md-form form-control form-control-sm w-100" style="background-color: #F7DC6F;" id="fCurrentPlusTwo${count.count}"
                                                                name="<portlet:namespace/>fCurrentPlusTwo${item.billingId}" onchange="return validateColumnsOnSelect(this,'nonShared');">
                                                            <option value="" disabled selected>Select</option>
                                                            <option ${item.currentPlusTwo == 'Bench' ? 'selected="selected"': 'style="display:none"'} value="Bench">Bench</option>
                                                             <option ${item.currentPlusTwo == 'Bench - Exit' ? 'selected="selected"': 'style="display:none"'} value="Bench - Exit">Bench - Exit</option>
                                                             <option ${item.currentPlusTwo == 'Bench - Maternity' ? 'selected="selected"': 'style="display:none"'} value="Bench - Maternity">Bench - Maternity</option>
                                                             <option ${item.currentPlusTwo == 'Billing' ? 'selected="selected"': 'style="display:none"'} value="Billing">Billing</option>
                                                             <option ${item.currentPlusTwo == 'Business Readiness' ? 'selected="selected"': 'style="display:none"'} value="Business Readiness">Business Readiness</option>
                                                             <option ${item.currentPlusTwo == 'Internal' ? 'selected="selected"': 'style="display:none"'} value="Internal">Internal</option>
                                                             <option ${item.currentPlusTwo == 'Internal - Exit' ? 'selected="selected"': 'style="display:none"'} value="Internal - Exit">Internal - Exit</option>
                                                             <option ${item.currentPlusTwo == 'Management' ? 'selected="selected"': 'style="display:none"'} value="Management">Management</option>
                                                             <option ${item.currentPlusTwo == 'Overhead' ? 'selected="selected"': 'style="display:none"'} value="Overhead">Overhead</option>
                                                             <option ${item.currentPlusTwo == 'Partial Billing' ? 'selected="selected"': 'style="display:none"'} value="Partial Billing">Partial Billing</option>
                                                             <option ${item.currentPlusTwo == 'Sales & Marketing' ? 'selected="selected"': 'style="display:none"'} value="Sales & Marketing">Sales & Marketing</option>
                                                             <option ${item.currentPlusTwo == 'Shadow' ? 'selected="selected"': 'style="display:none"'} value="Shadow">Shadow</option>
                                                             <option ${item.currentPlusTwo == 'Shadow - Client Committed/24*7 Support' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Client Committed/24*7 Support">Shadow - Client Committed/24*7 Support</option>
                                                             <option ${item.currentPlusTwo == 'Shadow - Exit' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Exit">Shadow - Exit</option>
                                                             <option ${item.currentPlusTwo == 'Shadow - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Planned Billing">Shadow - Planned Billing</option>
                                                             <option ${item.currentPlusTwo == 'Support' ? 'selected="selected"': 'style="display:none"'} value="Support">Support</option>
                                                             <option ${item.currentPlusTwo == 'Trainee' ? 'selected="selected"': 'style="display:none"'} value="Trainee">Trainee</option>
                                                             <option ${item.currentPlusTwo == 'Trainee - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Trainee - Planned Billing">Trainee - Planned Billing</option>
                                                       </select>
                                                    </td>
                                                    <td class="p-0 managerHrExtraCol" data-search="${item.employeeStatus}" data-order="${item.employeeStatus}">
                                                        <input type="hidden" value="${item.employeeStatus == '-' ? '' : item.employeeStatus}" id="ofemployeeStatus${count.count}"/>
                                                        <select class="mdb-select md-form form-control form-control-sm w-100" style="background-color: #F7DC6F;" id="femployeeStatus${count.count}"
                                                                name="<portlet:namespace/>femployeeStatus${item.billingId}" onchange="return validateColumnsOnSelect(this,'nonShared');">
                                                            <option value="" disabled selected>Select</option>
                                                            <option ${item.employeeStatus == 'Active' ? 'selected="selected"': 'style="display:none"'} value="Active">Active</option>
                                                            <option ${item.employeeStatus == 'Resigned' ? 'selected="selected"': 'style="display:none"'} value="Resigned">Resigned</option>
                                                            <option ${item.employeeStatus == 'Under Monitoring' ? 'selected="selected"': 'style="display:none"'} value="Under Monitoring">Under Monitoring</option>
                                                            <option ${item.employeeStatus == 'Maternity Leave' ? 'selected="selected"': 'style="display:none"'} value="Maternity Leave">Maternity Leave</option>
                                                            <option ${item.employeeStatus == 'Ramp up period' ? 'selected="selected"': 'style="display:none"'} value="Ramp up period">Ramp up period</option>
                                                            <option ${item.employeeStatus == 'Sabbatical Leave' ? 'selected="selected"': 'style="display:none"'} value="Sabbatical Leave">Sabbatical Leave</option>
                                                            <option ${item.employeeStatus == 'Deferred Billing' ? 'selected="selected"': 'style="display:none"'} value="Deferred Billing">Deferred Billing</option>
                                                       </select>
                                                    </td>
                                                    <td class="p-0 managerHrExtraCol">
                                                        <input type="hidden" <c:if test="${item.lastWorkingDate != '-'}">value="${item.lastWorkingDate}" </c:if> id="oflastWorkingDate${count.count}"/>
                                                        <input type="date" class ="form-control form-control-sm" style="background-color: #D7BDE2;" style="cursor: pointer" onkeydown="return false"
                                                                readonly="readonly"  id="flastWorkingDate${count.count}" name="<portlet:namespace/>flastWorkingDate${item.billingId}"
                                                                <c:if test="${item.lastWorkingDate != '-'}">value="${item.lastWorkingDate}" </c:if> />
                                                    </td>



                                                    <td class="p-0 managerHrExtraCol" data-search="${item.allocationStatus}" data-order="${item.allocationStatus}">
                                                        <input type="hidden" value="${item.allocationStatus == '-' ? '' : item.allocationStatus}" id="ofAllocationStatus${count.count}"/>
                                                        <select class="mdb-select md-form form-control form-control-sm w-100" style="background-color: #D7BDE2;" id="fAllocationStatus${count.count}" name="<portlet:namespace/>fAllocationStatus${item.billingId}">
                                                            <option value="" disabled selected>Select</option>
                                                            <option ${item.allocationStatus == 'Red' ? 'selected="selected"': 'style="display:none"'} value="Red">Red</option>
                                                            <option ${item.allocationStatus == 'Yellow' ? 'selected="selected"': 'style="display:none"'} value="Yellow">Yellow</option>
                                                            <option ${item.allocationStatus == 'Green' ? 'selected="selected"': 'style="display:none"'} value="Green">Green</option>
                                                       </select>
                                                    </td>
                                                    <td class="p-0 managerHrExtraCol">
                                                        <input type="hidden" <c:if test="${item.benchStartDate != '-'}">value="${item.benchStartDate}" </c:if>
                                                             id="ofbenchStartDate${count.count}"/>
                                                        <input type="date" class ="form-control form-control-sm" style="background-color: #D7BDE2;" style="cursor: pointer" readonly="readonly"
                                                            onkeydown="return false" id="fbenchStartDate${count.count}"
                                                            name="<portlet:namespace/>fbenchStartDate${item.billingId}" <c:if test="${item.benchStartDate != '-'}">value="${item.benchStartDate}" </c:if> />
                                                    </td>
                                                    <td class="p-0 managerHrExtraCol">
                                                        <input type="hidden" value="${item.shadowResourceType == '-' ? '' : item.shadowResourceType}"
                                                        id="ofShadowResourceType${count.count}"/>
                                                        <select class="mdb-select md-form form-control form-control-sm w-100" style="background-color: #D7BDE2;"
                                                            id="fShadowResourceType${count.count}" name="<portlet:namespace/>fShadowResourceType${item.billingId}">
                                                                <option value="" disabled selected>Select</option>
                                                                <option ${item.shadowResourceType == 'Client Requested' ? 'selected="selected"': 'style="display:none"'} value="Client Requested">Client Requested</option>
                                                                <option ${item.shadowResourceType == 'Strategic Hiring' ? 'selected="selected"': 'style="display:none"'} value="Strategic Hiring">Strategic Hiring</option>
                                                                <option ${item.shadowResourceType == 'Replacement' ? 'selected="selected"': 'style="display:none"'} value="Replacement">Replacement</option>
                                                       </select>
                                                    </td>
                                                    <td class="p-0 managerHrExtraCol">
                                                        <input type="hidden" <c:if test="${item.shadowStartDate != '-'}">value="${item.shadowStartDate}"</c:if>
                                                            id="ofshadowStartDate${count.count}"/>
                                                        <input type="date" class ="form-control form-control-sm" style="background-color: #D7BDE2;" style="cursor: pointer"
                                                            readonly="readonly" onkeydown="return false" id="fshadowStartDate${count.count}"
                                                             name="<portlet:namespace/>fshadowStartDate${item.billingId}" <c:if test="${item.shadowStartDate != '-'}">value="${item.shadowStartDate}"</c:if> />
                                                    </td>
                                                    <td class="p-0 managerHrExtraCol">
                                                        <input type="hidden" <c:if test="${item.billingStartDate != '-'}"> value="${item.billingStartDate}" </c:if>
                                                            id="ofbillingStartDate${count.count}"/>
                                                        <input type="date" class ="form-control form-control-sm" style="background-color: #D7BDE2;" style="cursor: pointer" readonly="readonly"
                                                            onkeydown="return false" id="fbillingStartDate${count.count}"
                                                            name="<portlet:namespace/>fbillingStartDate${item.billingId}" <c:if test="${item.billingStartDate != '-'}"> value="${item.billingStartDate}" </c:if> />
                                                    </td>
                                                    <td class="p-0 managerHrExtraCol">
                                                        <input type="hidden" <c:if test="${item.billingEndDate != '-'}">value="${item.billingEndDate}" </c:if> id="ofbillingEndDate${count.count}"/>
                                                        <input type="date" class ="form-control form-control-sm" style="background-color: #D7BDE2;" style="cursor: pointer" readonly="readonly"
                                                            onkeydown="return false" id="fbillingEndDate${count.count}" name="<portlet:namespace/>fbillingEndDate${item.billingId}" <c:if test="${item.billingEndDate != '-'}">value="${item.billingEndDate}" </c:if> />
                                                    </td>
                                                    <td class="p-0 managerHrExtraCol">
                                                        <input type="hidden" value="${item.percentUtilization == '-' ? '100' : item.percentUtilization}" id="ofpercentUtilization${count.count}"/>
                                                        <input type="number" class="form-control form-control-sm" style="background-color: #D7BDE2;"
                                                             readonly="readonly" max="100" min="0" id="fpercentUtilization${count.count}" value="${item.percentUtilization == '-' ? '100' : item.percentUtilization}"
                                                             name="<portlet:namespace/>fpercentUtilization${item.billingId}">
                                                    </td>

                                                    <td class="p-0 managerHrExtraCol">
                                                         <input type="hidden" value="${item.billableHours == '-' ? '' : item.billableHours}"
                                                            id="ofbillableHours${count.count}"/>
                                                        <input type="number" class="form-control form-control-sm" min="0" step="0.01" style="background-color: #F7DC6F;"
                                                            readonly="readonly" id="fbillableHours${count.count}" name="<portlet:namespace/>fbillableHours${item.billingId}"
                                                            value="${item.billableHours == '-' ? '' : item.billableHours}" />
                                                    </td>
                                                     <td class="p-0 managerHrExtraCol">
                                                         <input type="hidden" value="${item.monthHours == '-' ? '' : item.monthHours}"
                                                            id="ofMonthHours${count.count}"/>
                                                         <input type="number" class="form-control form-control-sm" min="0" step="0.01" style="background-color: #F7DC6F;"
                                                            readonly="readonly" id="fMonthHours${count.count}" name="<portlet:namespace/>fMonthHours${item.billingId}"
                                                            value="${item.monthHours == '-' ? '' : item.monthHours}" />
                                                     </td>
                                                    <td class="p-0 managerHrExtraCol">
                                                        <input type="hidden" value="${item.employeeRole == '-' ? '' : item.employeeRole}" id="ofEmployeeRole${count.count}"/>
                                                        <input type="text" class="form-control form-control-sm" style="background-color: palegreen;" maxlength="75"
                                                            onblur="$(this).val($(this).val().trim())" readonly="readonly"
                                                            id="fEmployeeRole${count.count}" name="<portlet:namespace/>fEmployeeRole${item.billingId}"  value="${item.employeeRole == '-' ? '' : item.employeeRole}" />
                                                    </td>
                                                    <td class="p-0 managerHrExtraCol">
                                                        <input type="hidden" value="${item.remarks == '-' ? '' : item.remarks}" id="ofRemarks${count.count}"/>
                                                        <input type="text" class="form-control form-control-sm" style="background-color: palegreen;" maxlength="200"
                                                            onblur="$(this).val($(this).val().trim())" readonly="readonly" id="fRemarks${count.count}"
                                                            title="${item.remarks}" name="<portlet:namespace/>fRemarks${item.billingId}"
                                                            value="${item.remarks == '-' ? '' : item.remarks}" />
                                                    </td>
                                                    <td class="p-0 managerHrExtraCol"><input type="hidden" value="${item.vertical == '-' ? '' : item.vertical}"
                                                        id="ofVertical${count.count}"/>
                                                        <select class="mdb-select md-form form-control form-control-sm w-100" style="background-color: #F7DC6F;"
                                                            id="fVertical${count.count}" name="<portlet:namespace/>fVertical${item.billingId}">
                                                                <option value="" disabled selected>Select</option>
                                                                <option ${item.vertical == 'BBU' ? 'selected="selected"': 'style="display:none"'} value="BBU">BBU</option>
                                                                <option ${item.vertical == 'Captive CoE' ? 'selected="selected"': 'style="display:none"'} value="Captive CoE">Captive CoE</option>
                                                                <option ${item.vertical == 'Cloud' ? 'selected="selected"': 'style="display:none"'} value="Cloud">Cloud</option>
                                                                <option ${item.vertical == 'Enterprise' ? 'selected="selected"': 'style="display:none"'} value="Enterprise">Enterprise</option>
                                                                <option ${item.vertical == 'Other' ? 'selected="selected"': 'style="display:none"'} value="Other">Other</option>
                                                                <option ${item.vertical == 'Sales' ? 'selected="selected"': 'style="display:none"'} value="Sales">Sales</option>
                                                                <option ${item.vertical == 'Support' ? 'selected="selected"': 'style="display:none"'} value="Support">Support</option>
                                                       </select>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <br></br>
                                </div>
                            </div>

                            <div class="card">
                                <div class="card-header">
                                    <div class="row pt-2">
                                            <div class="col">
                                                <h3>Partial/Shared Resource</h3>
                                                <p>Please add team member details who are working in Partial or Shared capacity</p>
                                            </div>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <input type="hidden" id="fExistingRows" value="" name="<portlet:namespace/>existingRows" />
                                    <input type="hidden" id="fTotalRows"  value="" name="<portlet:namespace/>totalRows" />
                                    <input type="hidden" id="actionPerformedSharedf" value="f" name="<portlet:namespace/>actionPerformedShared" />
                                    <input type="hidden" id="submitDateShared" value="${selectedMonthYear}"  name="<portlet:namespace/>submitDateShared" />
                                    <table class="table table-sm table-bordered table-striped table-hover table-custom text-center" id="financeSharedTable">
                                        <thead class="thead-dark">
                                            <tr>
                                                  <th scope="col" class="managerHrSharedColFixed1" style="padding-top: 14px !important;height: auto;">Select</th>
                                                  <th scope="col" class="managerHrSharedColFixed2" style="padding-top: 14px !important;height: auto;">Delete</th>
                                                  <th scope="col" class="managerHrSharedColFixed3" style="padding-top: 14px !important;height: auto;">Ecode*</th>
                                                  <th scope="col" class="managerHrSharedColFixed4" style="padding-top: 14px !important;height: auto;">Employee Name</th>
                                                  <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Manager Ecode*</th>
                                                  <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Manager Name*</th>
                                                  <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Coordinator Ecode</th>
                                                  <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Coordinator Name</th>
                                                  <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Lead Ecode</th>
                                                  <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Lead Name</th>
                                                  <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Employee Designation</th>
                                                  <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Employee DOJ</th>
                                                  <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Employee Experience</th>
                                                  <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Employee Skill</th>
                                                  <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Employee Account*</th>
                                                  <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Employee Project*</th>

                                                  <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Billing / Shadow / Bench - Current*
                                                        <i class="fas fa-question text-danger" style="cursor: pointer"
                                                                title=" Billing : Billing resources &#013;Partial Billing : Resources partially billing in your project &#013;Bench : Non billing Resources, not allocated or projected for allocation and available in open pool &#013;Shadow : Non billing resources, who are projected for allocation and/or fixed allocation to project, not available for allocation &#013;Trainee : Non billing Resources, in training, joined as fresher &#013;Internal : Working on inhouse Trantor Projects &#013;Support : Recruitment, ISO, Admin, Finance, IT and HR &#013;Overhead : Non billing Resources, managing account or project &#013;Business Readiness : Non billing resources, hired for anticipated project">
                                                        </i>
                                                  </th>
                                                  <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Billing / Shadow / Bench - Current + 1 month*
                                                        <i class="fas fa-question text-danger" style="cursor: pointer"
                                                                title=" Billing : Billing resources &#013;Partial Billing : Resources partially billing in your project &#013;Bench : Non billing Resources, not allocated or projected for allocation and available in open pool &#013;Shadow : Non billing resources, who are projected for allocation and/or fixed allocation to project, not available for allocation &#013;Trainee : Non billing Resources, in training, joined as fresher &#013;Internal : Working on inhouse Trantor Projects &#013;Support : Recruitment, ISO, Admin, Finance, IT and HR &#013;Overhead : Non billing Resources, managing account or project &#013;Business Readiness : Non billing resources, hired for anticipated project"></i>
                                                  </th>
                                                  <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Billing / Shadow / Bench - Current + 2 month*
                                                        <i class="fas fa-question text-danger" style="cursor: pointer"
                                                                title=" Billing : Billing resources &#013;Partial Billing : Resources partially billing in your project &#013;Bench : Non billing Resources, not allocated or projected for allocation and available in open pool &#013;Shadow : Non billing resources, who are projected for allocation and/or fixed allocation to project, not available for allocation &#013;Trainee : Non billing Resources, in training, joined as fresher &#013;Internal : Working on inhouse Trantor Projects &#013;Support : Recruitment, ISO, Admin, Finance, IT and HR &#013;Overhead : Non billing Resources, managing account or project &#013;Business Readiness : Non billing resources, hired for anticipated project"></i>
                                                  </th>
                                                  <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Employee Status*</th>
                                                  <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Last Working Date</th>

                                                  <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Bench Start Date</th>
                                                  <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Shadow Resource Type (for shadow resources)</th>
                                                  <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Shadow Start Date</th>
                                                  <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Billing Start Date</th>
                                                  <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Billing End Date</th>
                                                  <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">% Utilization Per Month</th>

                                                  <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Remarks</th>
                                                  <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Billable Hours*</th>
                                                  <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Month Hours*</th>
                                                  <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Employee Role</th>
                                                  <th scope="col" class="managerHrSharedExtraCol" style="padding: 0px 20px !important;">Vertical*</th>
                                            </tr>
                                        </thead>
                                        <tbody id="financeSharedTableBody">
                                             <c:forEach items="${financeSharedEmployeeList}" var="item"  varStatus="count">
                                                <tr>
                                                    <td class="managerHrSharedColFixed1">
                                                        <input type="hidden" value="${item.employeeCode}" id="fSharedEcodeId${count.index}"/>
                                                        <input type="checkbox" style="margin-top: 10px;"  projectUpdated="${item.projectUpdated}" value="${item.billingId}" id="fSharedCheckbox${count.index}"
                                                            name="<portlet:namespace/>fSharedCheckbox${count.index}"
                                                            onClick="changeFormToEditMode(this,'shared')"/>
                                                    </td>
                                                    <td class="p-0 managerHrSharedColFixed2">
                                                        <c:choose>
                                                          <c:when test="${!item.projectUpdated}">
                                                            <button type="submit" onclick="validateActionPerformed(1,'f')" value="${item.billingId}" name="<portlet:namespace/>fdeleting" class="btn-danger">
                                                               <i class="far fa-trash-alt" style="cursor: pointer"></i>
                                                            </button>
                                                          </c:when>
                                                          <c:otherwise>
                                                             <button type="button" disbaled value="${item.billingId}" name="<portlet:namespace/>mdeleting" class="btn-secondary" title="Account has been changed for this employee, hence this cannot be deleted.">
                                                                  <i class="far fa-trash-alt"></i>
                                                             </button>
                                                          </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td class="managerHrSharedColFixed3" data-search="${item.employeeCode}" data-order="${item.employeeCode}">
                                                        <input type="text" class="form-control-sm form-control-plaintext pl-3" readonly="readonly" name="<portlet:namespace/>fSharedEcode${count.index}"
                                                            value="${item.employeeCode}" />
                                                     </td>
                                                    <td class="managerHrSharedColFixed4" data-search="${item.employeeName}" data-order="${item.employeeName}">
                                                        <input type="text" class="form-control-plaintext form-control-sm text-center" readonly="readonly"
                                                        value="${item.employeeName}" />
                                                    </td>
                                                    <td class="p-0 managerHrSharedExtraCol">
                                                        <input type="hidden" value="${item.managerEcode}" id="ofSharedManagerEcode${count.index}"/>
                                                        <input type="text" <c:choose> <c:when  test="${item.projectUpdated}"> class="noedit form-control-sm form-control-plaintext pl-3" </c:when>
                                                         <c:otherwise> class="noedit form-control form-control-sm" </c:otherwise> </c:choose> maxlength="75"
                                                                onblur="fetchAndValidateEmployeeDetails(this)" disabled
                                                                id="fSharedManagerEcode${count.index}" name="<portlet:namespace/>fSharedManagerEcode${count.index}"
                                                                 value="${item.managerEcode}"  required="required" />
                                                    </td>
                                                    <td class="p-0 managerHrSharedExtraCol">
                                                        <input type="hidden" value="${item.managerName}" id="ofSharedManagerName${count.index}"/>
                                                        <input type="text"  <c:choose> <c:when  test="${item.projectUpdated}"> class="form-control-sm form-control-plaintext pl-3" </c:when>
                                                        <c:otherwise> class="form-control form-control-sm" </c:otherwise> </c:choose> maxlength="75"
                                                                 readonly="readonly" id="fSharedManagerName${count.index}"
                                                                name="<portlet:namespace/>fSharedManagerName${count.index}"  value="${item.managerName}" required="required" />
                                                    </td>

                                                    <td class="p-0 managerHrSharedExtraCol">
                                                        <input type="hidden" value="${item.coordinatorEcode}" id="ofSharedCoordinatorEcode${count.index}"/>
                                                        <input type="text"  <c:choose> <c:when  test="${item.projectUpdated}"> class="noedit form-control-sm form-control-plaintext pl-3" </c:when>
                                                         <c:otherwise> class="noedit form-control form-control-sm" </c:otherwise> </c:choose> maxlength="75"
                                                                onblur="fetchAndValidateEmployeeDetails(this)" disabled id="fSharedCoordinatorEcode${count.index}"
                                                                name="<portlet:namespace/>fSharedCoordinatorEcode${count.index}"  value="${item.coordinatorEcode}" />
                                                    </td>
                                                    <td class="p-0 managerHrSharedExtraCol">
                                                         <input type="hidden" value="${item.coordinatorName}" id="ofSharedCoordinatorName${count.index}"/>
                                                         <input type="text"  <c:choose> <c:when  test="${item.projectUpdated}"> class="form-control-sm form-control-plaintext pl-3" </c:when>
                                                           <c:otherwise> class="form-control form-control-sm" </c:otherwise> </c:choose> maxlength="75"
                                                                 readonly="readonly" id="fSharedCoordinatorName${count.index}"
                                                                name="<portlet:namespace/>fSharedCoordinatorName${count.index}"  value="${item.coordinatorName}"/>
                                                    </td>
                                                    <td class="p-0 managerHrSharedExtraCol">
                                                        <input type="hidden" value="${item.leadEcode}" id="ofSharedleadEcode${count.index}"/>
                                                        <input type="text"  <c:choose> <c:when  test="${item.projectUpdated}"> class="noedit form-control-sm form-control-plaintext pl-3" </c:when>
                                                               <c:otherwise> class="noedit form-control form-control-sm" </c:otherwise> </c:choose> maxlength="75"
                                                                onblur="fetchAndValidateEmployeeDetails(this)" disabled
                                                                id="fSharedleadEcode${count.index}" name="<portlet:namespace/>fSharedleadEcode${count.index}"
                                                                value="${item.leadEcode}"  />
                                                    </td>
                                                    <td class="p-0 managerHrSharedExtraCol">
                                                        <input type="hidden" value="${item.leadName}" id="ofSharedleadName${count.index}"/>
                                                        <input type="text"  <c:choose> <c:when  test="${item.projectUpdated}"> class="form-control-sm form-control-plaintext pl-3" </c:when>
                                                          <c:otherwise> class="form-control form-control-sm" </c:otherwise> </c:choose> maxlength="75"
                                                                 readonly="readonly" id="fSharedleadName${count.index}"
                                                                name="<portlet:namespace/>fSharedleadName${count.index}"  value="${item.leadName}"  />
                                                    </td>
                                                    <td class="managerHrSharedExtraCol">
                                                        ${item.employeeDesignation}
                                                    </td>
                                                    <td class="managerHrSharedExtraCol">
                                                        ${item.doj}
                                                    </td>
                                                    <td class="managerHrSharedExtraCol">
                                                        ${item.experience}
                                                    </td>
                                                    <td class="managerHrSharedExtraCol">
                                                        ${item.skill}
                                                    </td>
                                                    <td class="managerHrSharedExtraCol">
                                                        <input type="hidden" value="${item.account}" id="ofSharedAccount${count.index}"/>
                                                        <input type="text"  <c:choose> <c:when  test="${item.projectUpdated}"> class="form-control-sm form-control-plaintext pl-3" </c:when>
                                                           <c:otherwise> class="form-control form-control-sm" </c:otherwise> </c:choose> maxlength="75"
                                                                   onblur="$(this).val($(this).val().trim())" readonly="readonly" id="fSharedAccount${count.index}"
                                                                   name="<portlet:namespace/>fSharedAccount${count.index}"  value="${item.account}" required="required"/>
                                                    </td>
                                                    <td class="managerHrSharedExtraCol">
                                                        <input type="hidden" value="${item.project}" id="ofSharedProject${count.index}"/>
                                                        <input type="text"  <c:choose> <c:when  test="${item.projectUpdated}"> class="form-control-sm form-control-plaintext pl-3" </c:when>
                                                          <c:otherwise> class="form-control form-control-sm" </c:otherwise> </c:choose> maxlength="75"
                                                                   onblur="$(this).val($(this).val().trim())" readonly="readonly" id="fSharedProject${count.index}"
                                                                   name="<portlet:namespace/>fSharedProject${count.index}"  value="${item.project}" required="required"/>
                                                    </td>

                                                    <td class="p-0 managerHrSharedExtraCol" data-search="${item.current}" data-order="${item.current}">
                                                        <input type="hidden" value="${item.current == '-' ? '' : item.current}" id="ofSharedCurrent${count.index}"/>
                                                        <select class="mdb-select md-form form-control form-control-sm w-100" id="fSharedCurrent${count.index}"
                                                            name="<portlet:namespace/>fSharedCurrent${count.index}" onchange="return validateColumnsOnSelect(this,'shared');">
                                                                <option value="" disabled selected>Select</option>
                                                                 <option ${item.current == 'Bench' ? 'selected="selected"': 'style="display:none"'} value="Bench">Bench</option>
                                                                  <option ${item.current == 'Bench - Exit' ? 'selected="selected"': 'style="display:none"'} value="Bench - Exit">Bench - Exit</option>
                                                                  <option ${item.current == 'Bench - Maternity' ? 'selected="selected"': 'style="display:none"'} value="Bench - Maternity">Bench - Maternity</option>
                                                                  <option ${item.current == 'Billing' ? 'selected="selected"': 'style="display:none"'} value="Billing">Billing</option>
                                                                  <option ${item.current == 'Business Readiness' ? 'selected="selected"': 'style="display:none"'} value="Business Readiness">Business Readiness</option>
                                                                  <option ${item.current == 'Internal' ? 'selected="selected"': 'style="display:none"'} value="Internal">Internal</option>
                                                                  <option ${item.current == 'Internal - Exit' ? 'selected="selected"': 'style="display:none"'} value="Internal - Exit">Internal - Exit</option>
                                                                  <option ${item.current == 'Management' ? 'selected="selected"': 'style="display:none"'} value="Management">Management</option>
                                                                  <option ${item.current == 'Overhead' ? 'selected="selected"': 'style="display:none"'} value="Overhead">Overhead</option>
                                                                  <option ${item.current == 'Partial Billing' ? 'selected="selected"': 'style="display:none"'} value="Partial Billing">Partial Billing</option>
                                                                  <option ${item.current == 'Sales & Marketing' ? 'selected="selected"': 'style="display:none"'} value="Sales & Marketing">Sales & Marketing</option>
                                                                  <option ${item.current == 'Shadow' ? 'selected="selected"': 'style="display:none"'} value="Shadow">Shadow</option>
                                                                  <option ${item.current == 'Shadow - Client Committed/24*7 Support' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Client Committed/24*7 Support">Shadow - Client Committed/24*7 Support</option>
                                                                  <option ${item.current == 'Shadow - Exit' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Exit">Shadow - Exit</option>
                                                                  <option ${item.current == 'Shadow - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Planned Billing">Shadow - Planned Billing</option>
                                                                  <option ${item.current == 'Support' ? 'selected="selected"': 'style="display:none"'} value="Support">Support</option>
                                                                  <option ${item.current == 'Trainee' ? 'selected="selected"': 'style="display:none"'} value="Trainee">Trainee</option>
                                                                  <option ${item.current == 'Trainee - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Trainee - Planned Billing">Trainee - Planned Billing</option>
                                                       </select>
                                                    </td>
                                                    <td class="p-0 managerHrSharedExtraCol" data-search="${item.currentPlusOne}" data-order="${item.currentPlusOne}">
                                                        <input type="hidden" value="${item.currentPlusOne == '-' ? '' : item.currentPlusOne}" id="ofSharedCurrentPlusOne${count.index}"/>
                                                        <select class="mdb-select md-form form-control form-control-sm w-100" id="fSharedCurrentPlusOne${count.index}"
                                                            name="<portlet:namespace/>fSharedCurrentPlusOne${count.index}" onchange="return validateColumnsOnSelect(this,'shared');">
                                                                <option value="" disabled selected>Select</option>
                                                                 <option ${item.currentPlusOne == 'Bench' ? 'selected="selected"': 'style="display:none"'} value="Bench">Bench</option>
                                                                  <option ${item.currentPlusOne == 'Bench - Exit' ? 'selected="selected"': 'style="display:none"'} value="Bench - Exit">Bench - Exit</option>
                                                                  <option ${item.currentPlusOne == 'Bench - Maternity' ? 'selected="selected"': 'style="display:none"'} value="Bench - Maternity">Bench - Maternity</option>
                                                                  <option ${item.currentPlusOne == 'Billing' ? 'selected="selected"': 'style="display:none"'} value="Billing">Billing</option>
                                                                  <option ${item.currentPlusOne == 'Business Readiness' ? 'selected="selected"': 'style="display:none"'} value="Business Readiness">Business Readiness</option>
                                                                  <option ${item.currentPlusOne == 'Internal' ? 'selected="selected"': 'style="display:none"'} value="Internal">Internal</option>
                                                                  <option ${item.currentPlusOne == 'Internal - Exit' ? 'selected="selected"': 'style="display:none"'} value="Internal - Exit">Internal - Exit</option>
                                                                  <option ${item.currentPlusOne == 'Management' ? 'selected="selected"': 'style="display:none"'} value="Management">Management</option>
                                                                  <option ${item.currentPlusOne == 'Overhead' ? 'selected="selected"': 'style="display:none"'} value="Overhead">Overhead</option>
                                                                  <option ${item.currentPlusOne == 'Partial Billing' ? 'selected="selected"': 'style="display:none"'} value="Partial Billing">Partial Billing</option>
                                                                  <option ${item.currentPlusOne == 'Sales & Marketing' ? 'selected="selected"': 'style="display:none"'} value="Sales & Marketing">Sales & Marketing</option>
                                                                  <option ${item.currentPlusOne == 'Shadow' ? 'selected="selected"': 'style="display:none"'} value="Shadow">Shadow</option>
                                                                  <option ${item.currentPlusOne == 'Shadow - Client Committed/24*7 Support' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Client Committed/24*7 Support">Shadow - Client Committed/24*7 Support</option>
                                                                  <option ${item.currentPlusOne == 'Shadow - Exit' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Exit">Shadow - Exit</option>
                                                                  <option ${item.currentPlusOne == 'Shadow - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Planned Billing">Shadow - Planned Billing</option>
                                                                  <option ${item.currentPlusOne == 'Support' ? 'selected="selected"': 'style="display:none"'} value="Support">Support</option>
                                                                  <option ${item.currentPlusOne == 'Trainee' ? 'selected="selected"': 'style="display:none"'} value="Trainee">Trainee</option>
                                                                  <option ${item.currentPlusOne == 'Trainee - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Trainee - Planned Billing">Trainee - Planned Billing</option>
                                                       </select>
                                                    </td>
                                                    <td class="p-0 managerHrSharedExtraCol" data-search="${item.currentPlusTwo}" data-order="${item.currentPlusTwo}">
                                                        <input type="hidden" value="${item.currentPlusTwo == '-' ? '' : item.currentPlusTwo}" id="ofSharedCurrentPlusTwo${count.index}"/>
                                                        <select class="mdb-select md-form form-control form-control-sm w-100" id="fSharedCurrentPlusTwo${count.index}"
                                                            name="<portlet:namespace/>fSharedCurrentPlusTwo${count.index}" onchange="return validateColumnsOnSelect(this,'shared');">
                                                                <option value="" disabled selected>Select</option>
                                                                <option ${item.currentPlusTwo == 'Bench' ? 'selected="selected"': 'style="display:none"'} value="Bench">Bench</option>
                                                                 <option ${item.currentPlusTwo == 'Bench - Exit' ? 'selected="selected"': 'style="display:none"'} value="Bench - Exit">Bench - Exit</option>
                                                                 <option ${item.currentPlusTwo == 'Bench - Maternity' ? 'selected="selected"': 'style="display:none"'} value="Bench - Maternity">Bench - Maternity</option>
                                                                 <option ${item.currentPlusTwo == 'Billing' ? 'selected="selected"': 'style="display:none"'} value="Billing">Billing</option>
                                                                 <option ${item.currentPlusTwo == 'Business Readiness' ? 'selected="selected"': 'style="display:none"'} value="Business Readiness">Business Readiness</option>
                                                                 <option ${item.currentPlusTwo == 'Internal' ? 'selected="selected"': 'style="display:none"'} value="Internal">Internal</option>
                                                                 <option ${item.currentPlusTwo == 'Internal - Exit' ? 'selected="selected"': 'style="display:none"'} value="Internal - Exit">Internal - Exit</option>
                                                                 <option ${item.currentPlusTwo == 'Management' ? 'selected="selected"': 'style="display:none"'} value="Management">Management</option>
                                                                 <option ${item.currentPlusTwo == 'Overhead' ? 'selected="selected"': 'style="display:none"'} value="Overhead">Overhead</option>
                                                                 <option ${item.currentPlusTwo == 'Partial Billing' ? 'selected="selected"': 'style="display:none"'} value="Partial Billing">Partial Billing</option>
                                                                 <option ${item.currentPlusTwo == 'Sales & Marketing' ? 'selected="selected"': 'style="display:none"'} value="Sales & Marketing">Sales & Marketing</option>
                                                                 <option ${item.currentPlusTwo == 'Shadow' ? 'selected="selected"': 'style="display:none"'} value="Shadow">Shadow</option>
                                                                 <option ${item.currentPlusTwo == 'Shadow - Client Committed/24*7 Support' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Client Committed/24*7 Support">Shadow - Client Committed/24*7 Support</option>
                                                                 <option ${item.currentPlusTwo == 'Shadow - Exit' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Exit">Shadow - Exit</option>
                                                                 <option ${item.currentPlusTwo == 'Shadow - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Shadow - Planned Billing">Shadow - Planned Billing</option>
                                                                 <option ${item.currentPlusTwo == 'Support' ? 'selected="selected"': 'style="display:none"'} value="Support">Support</option>
                                                                 <option ${item.currentPlusTwo == 'Trainee' ? 'selected="selected"': 'style="display:none"'} value="Trainee">Trainee</option>
                                                                 <option ${item.currentPlusTwo == 'Trainee - Planned Billing' ? 'selected="selected"': 'style="display:none"'} value="Trainee - Planned Billing">Trainee - Planned Billing</option>
                                                       </select>
                                                    </td>
                                                    <td class="p-0 managerHrSharedExtraCol" data-search="${item.employeeStatus}" data-order="${item.employeeStatus}">
                                                        <input type="hidden" value="${item.employeeStatus == '-' ? '' : item.employeeStatus}" id="ofSharedemployeeStatus${count.index}"/>
                                                        <select class="mdb-select md-form form-control form-control-sm w-100"  id="fSharedemployeeStatus${count.index}"
                                                            name="<portlet:namespace/>fSharedemployeeStatus${count.index}" onchange="return validateColumnsOnSelect(this,'shared');">
                                                                <option value="" disabled selected>Select</option>
                                                                <option ${item.employeeStatus == 'Active' ? 'selected="selected"': 'style="display:none"'} value="Active">Active</option>
                                                                <option ${item.employeeStatus == 'Resigned' ? 'selected="selected"': 'style="display:none"'} value="Resigned">Resigned</option>
                                                                <option ${item.employeeStatus == 'Under Monitoring' ? 'selected="selected"': 'style="display:none"'} value="Under Monitoring">Under Monitoring</option>
                                                                <option ${item.employeeStatus == 'Maternity Leave' ? 'selected="selected"': 'style="display:none"'} value="Maternity Leave">Maternity Leave</option>
                                                                <option ${item.employeeStatus == 'Ramp up period' ? 'selected="selected"': 'style="display:none"'} value="Ramp up period">Ramp up period</option>
                                                                <option ${item.employeeStatus == 'Sabbatical Leave' ? 'selected="selected"': 'style="display:none"'} value="Sabbatical Leave">Sabbatical Leave</option>
                                                                <option ${item.employeeStatus == 'Deferred Billing' ? 'selected="selected"': 'style="display:none"'} value="Deferred Billing">Deferred Billing</option>
                                                       </select>
                                                    </td>
                                                    <td class="p-0 managerHrSharedExtraCol">
                                                        <input type="hidden" <c:if test="${item.lastWorkingDate != '-'}">value="${item.lastWorkingDate}" </c:if> id="ofSharedlastWorkingDate${count.index}"/>
                                                        <input type="date" class ="form-control form-control-sm" style="cursor: pointer" onkeydown="return false" readonly="readonly"
                                                            id="fSharedlastWorkingDate${count.index}" name="<portlet:namespace/>fSharedlastWorkingDate${count.index}"
                                                            <c:if test="${item.lastWorkingDate != '-'}">value="${item.lastWorkingDate}" </c:if> />
                                                    </td>



                                                    <td class="p-0 managerHrSharedExtraCol">
                                                        <input type="hidden" <c:if test="${item.benchStartDate != '-'}">value="${item.benchStartDate}" </c:if>
                                                            id="ofSharedbenchStartDate${count.index}"/>
                                                        <input type="date" class ="form-control form-control-sm" style="cursor: pointer" readonly="readonly" onkeydown="return false"
                                                            id="fSharedbenchStartDate${count.index}"
                                                            name="<portlet:namespace/>fSharedbenchStartDate${count.index}"
                                                            <c:if test="${item.benchStartDate != '-'}">value="${item.benchStartDate}" </c:if> />
                                                    </td>
                                                    <td class="p-0 managerHrSharedExtraCol">
                                                        <input type="hidden" value="${item.shadowResourceType == '-' ? '' : item.shadowResourceType}"
                                                            id="ofSharedShadowResourceType${count.index}"/>
                                                        <select class="mdb-select md-form form-control form-control-sm w-100" id="fSharedShadowResourceType${count.index}"
                                                            name="<portlet:namespace/>fSharedShadowResourceType${count.index}">
                                                                <option value="" disabled selected>Select</option>
                                                                <option ${item.shadowResourceType == 'Client Requested' ? 'selected="selected"': 'style="display:none"'} value="Client Requested">Client Requested</option>
                                                                <option ${item.shadowResourceType == 'Strategic Hiring' ? 'selected="selected"': 'style="display:none"'} value="Strategic Hiring">Strategic Hiring</option>
                                                                <option ${item.shadowResourceType == 'Replacement' ? 'selected="selected"': 'style="display:none"'} value="Replacement">Replacement</option>
                                                       </select>
                                                    </td>
                                                    <td class="p-0 managerHrSharedExtraCol">
                                                        <input type="hidden" <c:if test="${item.shadowStartDate != '-'}">value="${item.shadowStartDate}"</c:if>
                                                            id="ofSharedshadowStartDate${count.index}"/>
                                                        <input type="date" class ="form-control form-control-sm" style="cursor: pointer" readonly="readonly"
                                                            onkeydown="return false" id="fSharedshadowStartDate${count.index}"
                                                            name="<portlet:namespace/>fSharedshadowStartDate${count.index}"
                                                            <c:if test="${item.shadowStartDate != '-'}">value="${item.shadowStartDate}"</c:if> />
                                                    </td>
                                                    <td class="p-0 managerHrSharedExtraCol">
                                                        <input type="hidden" <c:if test="${item.billingStartDate != '-'}"> value="${item.billingStartDate}" </c:if>
                                                            id="ofSharedbillingStartDate${count.index}"/>
                                                        <input type="date" class ="form-control form-control-sm" style="cursor: pointer" readonly="readonly" onkeydown="return false"
                                                            id="fSharedbillingStartDate${count.index}" name="<portlet:namespace/>fSharedbillingStartDate${count.index}"
                                                            <c:if test="${item.billingStartDate != '-'}"> value="${item.billingStartDate}" </c:if> />
                                                    </td>
                                                    <td class="p-0 managerHrSharedExtraCol">
                                                        <input type="hidden" <c:if test="${item.billingEndDate != '-'}">value="${item.billingEndDate}" </c:if>
                                                            id="ofSharedbillingEndDate${count.index}"/>
                                                        <input type="date" class ="form-control form-control-sm" style="cursor: pointer" readonly="readonly" onkeydown="return false"
                                                            id="fSharedbillingEndDate${count.index}" name="<portlet:namespace/>fSharedbillingEndDate${count.index}"
                                                            <c:if test="${item.billingEndDate != '-'}">value="${item.billingEndDate}" </c:if> />
                                                    </td>
                                                    <td class="p-0 managerHrSharedExtraCol">
                                                        <input type="hidden" value="${item.percentUtilization == '-' ? '100' : item.percentUtilization}"
                                                            id="ofSharedpercentUtilization${count.index}"/>
                                                        <input type="number" class="form-control form-control-sm"
                                                             readonly="readonly" max="100" min="0" id="fSharedpercentUtilization${count.index}"
                                                              value="${item.percentUtilization == '-' ? '100' : item.percentUtilization}"
                                                              name="<portlet:namespace/>fSharedpercentUtilization${count.index}">
                                                    </td>

                                                     <td class="p-0 managerHrSharedExtraCol">
                                                        <input type="hidden" value="${item.remarks == '-' ? '' : item.remarks}" id="ofSharedRemarks${count.index}"/>
                                                        <input type="text" class="form-control form-control-sm" maxlength="200"
                                                                onblur="$(this).val($(this).val().trim())" readonly="readonly" id="fSharedRemarks${count.index}"
                                                                name="<portlet:namespace/>fSharedRemarks${count.index}"  value="${item.remarks == '-' ? '' : item.remarks}" />
                                                    </td>
                                                    <td class="managerHrSharedExtraCol">
                                                        <input type="hidden" value="${item.billableHours}" id="ofSharedbillableHours${count.index}"/>
                                                        <input type="number" class="form-control form-control-sm" min="0" step="0.01"
                                                           readonly="readonly" id="fSharedbillableHours${count.index}"
                                                           name="<portlet:namespace/>fSharedbillableHours${count.index}"  value="${item.billableHours}" />
                                                    </td>
                                                    <td class="managerHrSharedExtraCol">
                                                         <input type="hidden" value="${item.monthHours}" id="ofSharedMonthHours${count.index}"/>
                                                         <input type="number" class="form-control form-control-sm" min="0" step="0.01"
                                                            readonly="readonly" id="fSharedMonthHours${count.index}" name="<portlet:namespace/>fSharedMonthHours${count.index}"
                                                            value="${item.monthHours}" />
                                                    </td>
                                                    <td class="managerHrSharedExtraCol">
                                                        <input type="hidden" value="${item.employeeRole}" id="ofSharedEmployeeRole${count.index}"/>
                                                        <input type="text" class="form-control form-control-sm" maxlength="75"
                                                               onblur="$(this).val($(this).val().trim())" readonly="readonly" id="fSharedEmployeeRole${count.index}"
                                                               name="<portlet:namespace/>fSharedEmployeeRole${count.index}" value="${item.employeeRole}" />
                                                    </td>
                                                    <td class="managerHrSharedExtraCol">
                                                        <input type="hidden" value="${item.vertical}" id="ofSharedVertical${count.index}"/>
                                                        <select class="mdb-select md-form form-control form-control-sm w-100"  id="fSharedVertical${count.index}"
                                                            name="<portlet:namespace/>fSharedVertical${count.index}">
                                                                <option value="" disabled selected>Select</option>
                                                                <option ${item.vertical == 'BBU' ? 'selected="selected"': 'style="display:none"'} value="BBU">BBU</option>
                                                                <option ${item.vertical == 'Captive CoE' ? 'selected="selected"': 'style="display:none"'} value="Captive CoE">Captive CoE</option>
                                                                <option ${item.vertical == 'Cloud' ? 'selected="selected"': 'style="display:none"'} value="Cloud">Cloud</option>
                                                                <option ${item.vertical == 'Enterprise' ? 'selected="selected"': 'style="display:none"'} value="Enterprise">Enterprise</option>
                                                                <option ${item.vertical == 'Other' ? 'selected="selected"': 'style="display:none"'} value="Other">Other</option>
                                                                <option ${item.vertical == 'Sales' ? 'selected="selected"': 'style="display:none"'} value="Sales">Sales</option>
                                                                <option ${item.vertical == 'Support' ? 'selected="selected"': 'style="display:none"'} value="Support">Support</option>
                                                       </select>
                                                    </td>
                                                </tr>
                                             </c:forEach>
                                        </tbody>
                                    </table>
                                    <br>
                                    <button type="button" class="btn btn-outline-success btn-sm" onclick="addNewRow('f')">
                                        <i class="fas fa-plus"></i> Add Employee
                                    </button>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6 text-right">
                                    <button class="btn btn-primary" id="submitBtn" type="submit" onClick="validateActionPerformed(2,'f')">Submit</button>
                                </div>
                            </div>
        			    </form>
        			</div>
        		</div>

        		 <div class="card">
        			<div class="card-header">
        				<div class="row">
                            <div class="col pt-2"> <h3>Help - Billing User Manual </h3></div>
                            <div class="col-2 text-right" ><input type="button" class="btn btn-link"  onClick="downloadManual(3);"  value="Download the file" /></div>
                        </div>
        			</div>
        		 </div>
        	</div>
        </div>

        <div class="tab-pane fade" id="bench" role="tabpanel" aria-labelledby="bench-tab">
        			<div id="benchDiv">
                        <div class="card">
							<div class="card-header">
									<div class="row">
                                         <h3>Bench Employees</h3>
									</div>
							</div>
							<div class="card-body">
								<table
									class="table table-sm table-bordered table-striped table-hover table-custom text-center"
									id="benchTable">
									<thead class="thead-dark">
										<tr>
                                          <th scope="col" class="benchColFixed1" style="padding-top: 14px !important;">Ecode</th>
                                          <th scope="col" class="benchColFixed2" style="padding-top: 14px !important;">Name</th>
                                          <th scope="col" class="benchColFixed3" style="padding-top: 14px !important;">Manager Name</th>
                                          <th scope="col" class="benchExtraCol">Employee Status</th>
                                          <th scope="col" class="benchExtraCol">Shadow / Bench - Current</th>
                                          <th scope="col" class="benchExtraCol">Designation</th>
                                          <th scope="col" class="benchExtraCol">Skill</th>
                                          <th scope="col" class="benchExtraCol">DOJ</th>
                                          <th scope="col" class="benchExtraCol">Experience</th>
                                          <th scope="col" class="benchExtraCol">Account</th>
                                          <th scope="col" class="benchExtraCol">Bench Start Date</th>
                                          <th scope="col" class="benchExtraCol">Allocation Status</th>
                                          <th scope="col" class="benchExtraCol">Last Working Date</th>
                                          <th scope="col" class="benchExtraCol">Remarks</th>
										</tr>
									</thead>
                                    <tbody>
										<c:forEach items="${benchEmployeeList}" var="item"
											varStatus="count">
											<tr>
												<td class="benchColFixed1">${item.employeeCode}</td>
                                                <td class="benchColFixed2">${item.employeeName}</td>
                                                <td class="benchColFixed3">${item.managerName}</td>
												<td class="benchExtraCol">${item.employeeStatus}</td>
												<td class="benchExtraCol">${item.current}</td>
												<td class="benchExtraCol">${item.employeeDesignation}</td>
												<td class="looklikelink benchExtraCol" style="cursor: pointer" onClick="getSkillData('${item.employeeCode}',this)">${item.skill}</td>
												<td class="benchExtraCol">${item.doj}</td>
												<td class="benchExtraCol">${item.experience}</td>
												<td class="benchExtraCol">${item.account}</td>
                                                <td class="benchExtraCol">${item.benchStartDate}</td>
                                                <td class="benchExtraCol">${item.allocationStatus}</td>
                                                <td class="benchExtraCol">${item.lastWorkingDate}</td>
                                                <td class="benchExtraCol">${item.remarks}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
                        <div class="card">
							<div class="card-header">
									<div class="row">
                                         <h3>Partial/Shared Bench Resources</h3>
									</div>
							</div>
							<div class="card-body">
								<table
									class="table table-sm table-bordered table-striped table-hover table-custom text-center"
									id="sharedBenchTable">
									<thead class="thead-dark">
										<tr>
                                          <th scope="col" class="benchColFixed1" style="padding-top: 14px !important;height: auto;">Ecode</th>
                                          <th scope="col" class="benchColFixed2" style="padding-top: 14px !important;height: auto;">Name</th>
                                          <th scope="col" class="benchColFixed3" style="padding-top: 14px !important;height: auto;">Manager Name</th>
                                          <th scope="col" class="benchExtraCol" style="padding: 0px 20px !important;">Employee Status</th>
                                          <th scope="col" class="benchExtraCol" style="padding: 0px 20px !important;">Shadow / Bench - Current</th>
                                          <th scope="col" class="benchExtraCol" style="padding: 0px 20px !important;">Designation</th>
                                          <th scope="col" class="benchExtraCol" style="padding: 0px 20px !important;">Skill</th>
                                          <th scope="col" class="benchExtraCol" style="padding: 0px 20px !important;">DOJ</th>
                                          <th scope="col" class="benchExtraCol" style="padding: 0px 20px !important;">Experience</th>
                                          <th scope="col" class="benchExtraCol" style="padding: 0px 20px !important;">Account</th>
                                          <th scope="col" class="benchExtraCol" style="padding: 0px 20px !important;">Bench Start Date</th>
                                          <th scope="col" class="benchExtraCol" style="padding: 0px 20px !important;">Last Working Date</th>
                                          <th scope="col" class="benchExtraCol" style="padding: 0px 20px !important;">Remarks</th>
										</tr>
									</thead>
                                    <tbody>
										<c:forEach items="${sharedBenchEmployeeList}" var="item" varStatus="count">
											<tr>
												<td class="benchColFixed1">${item.employeeCode}</td>
												<td class="benchColFixed2">${item.employeeName}</td>
												<td class="benchColFixed3">${item.managerName}</td>
												<td class="benchExtraCol">${item.employeeStatus}</td>
												<td class="benchExtraCol">${item.current}</td>
												<td class="benchExtraCol">${item.employeeDesignation}</td>
												<td class="looklikelink benchExtraCol" style="cursor: pointer" onClick="getSkillData('${item.employeeCode}',this)">${item.skill}</td>
                                                <td class="benchExtraCol">${item.doj}</td>
												<td class="benchExtraCol">${item.experience}</td>
												<td class="benchExtraCol">${item.account}</td>
                                                <td class="benchExtraCol">${item.benchStartDate}</td>
                                                <td class="benchExtraCol">${item.lastWorkingDate}</td>
                                                <td class="benchExtraCol">${item.remarks}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
        </div>
    </div>
</div>
<script>

     var hrRole;
     var managerRole;
     var leadershipRole;
     var financeRole;
     var benchRole;
     var mExistingRows;
     var hExistingRows;
     var fExistingRows;
     var counter = 0;
     var billingUrl;
     var modal = document.getElementById("myModal");
     var loaderModal=document.getElementById("loaderModal");

     var span = document.getElementsByClassName("close")[0];
     span.onclick = function() {
        modal.style.display = "none";
     }
     window.onclick = function(event) {
          if (event.target == modal) {
                modal.style.display = "none";
          }
     }

    window.addEventListener('load', (event) => {
      loaderModal.style.display = "none";
    });

     $(document).ready(function() {
         hrRole = '${hrRole}';
         managerRole = '${managerRole}';
         leadershipRole = '${leadershipRole}';
         financeRole='${fncRole}';
         benchRole='${benchRole}';
         billingUrl = '${billingUrl}';
         var selectedMonthYear = '${selectedMonthYear}';
         //alert(selectedMonthYear);
         mExistingRows = document.getElementById("managerSharedTablebBody").rows.length;
         $('#mExistingRows').val(mExistingRows);
         hExistingRows = document.getElementById("hrSharedTablebBody").rows.length;
         $('#hExistingRows').val(hExistingRows);
         fExistingRows=document.getElementById("financeSharedTableBody").rows.length;
         $('#fExistingRows').val(fExistingRows);
         //alert("fExistingRows=="+fExistingRows);

          var currentMonth = (new Date).getMonth() + 1;
          $('#dataMonthIdm, #dataMonthIdf').MonthPicker({
                 Button: false,
                 MinMonth: "10/2022",
                 MaxMonth:0,
                 SelectedMonth:  currentMonth +"/" + new Date().getFullYear(),
                 OnAfterChooseMonth: function(selectedDate) {
                    loaderModal.style.display="block";
                     var month = $(this).val();
                     $('#submitDatem').val(month);
                     $('#submitDatef').val(month);
                     var navigationURL = billingUrl+"?selectedMonth="+month;
                     window.location = navigationURL;
                }
          });

         $('#dataMonthIdm').val(selectedMonthYear);
         $('#dataMonthIdf').val(selectedMonthYear);
         if(managerRole == 'true'){
                 $('#manager-tab').addClass("active");
                 $('#manager').addClass("show active");
         }else if(hrRole == 'true'){
                  $('#hr-tab').addClass("active");
                  $('#hr').addClass("show active");
         }else if(leadershipRole == 'true'){
                 $('#leadership-tab').addClass("active");
                 $('#leadership').addClass("show active");
         }else if(financeRole == 'true'){
                 $('#finance-tab').addClass("active");
                  $('#finance').addClass("show active");
         }else {
                 $('#bench-tab').addClass("active");
                 $('#bench').addClass("show active");
         }
         $('#managerTable').DataTable({
              dom : 'Bfrtip',
              buttons : [ 'excel' ],
              "aaSorting": [ [13,'asc'],[15,'asc'],[2,'asc'] ],
              "paging" : false,
              "columnDefs": [ {
                 "targets": "_all",
                 "orderable": false
              }]
         });
         $('#managerSharedTable,#hrSharedTable,#financeSharedTable').DataTable({
                "aaSorting": [ [14,'asc'],[16,'asc'],[3,'asc'] ],
                dom : 'Bfrtip',
                bFilter : false,
                buttons : [ 'excel' ],
                "paging" : false,
                "columnDefs": [ {
                  "targets": "_all",
                  "orderable": false
               }]
         });
         $('#hrTable,#financeTable').DataTable({
                dom : 'Bfrtip',
                buttons : [ 'excel' ],
                "pageLength" : 15,
                "aaSorting": [ [13,'asc'],[15,'asc'],[2,'asc'] ],
                "lengthMenu" : [ [ 5, 10,20, -1 ], [ 5, 10,20, "All" ] ],
                "columnDefs": [ {
                      "targets": "_all",
                      "orderable": false
                }]
         });
        $('#leadershipTable,#sharedLeadershipTable').DataTable({
                 dom : 'Bfrtip',
                 buttons : [ 'excel' ],
                 "pageLength" : 15,
                 "aaSorting": [ [12,'asc'],[14,'asc'],[1,'asc'] ],
                 "lengthMenu" : [ [ 5, 10,20, -1 ], [ 5, 10,20, "All" ] ],
                 "columnDefs": [ {
                    "targets": "_all",
                    "orderable": false
                 }]
        });
        $('#benchTable,#sharedBenchTable').DataTable({
                 dom : 'Bfrtip',
                 buttons : [ 'excel' ],
                 "pageLength" : 15,
                 "aaSorting": [ [9,'asc'],[4,'asc'],[1,'asc'] ],
                 "lengthMenu" : [ [ 5, 10,20, -1 ], [ 5, 10,20, "All" ] ],
                 "columnDefs": [ {
                    "targets": "_all",
                    "orderable": false
                 }]
        });

        $('.dataTables_empty').parent().remove();
        $('#managerTable_info').hide();
     });

    function changeFormToEditMode(obj,section)
    {
        var rowId = $(obj).attr('id');
        var input = rowId.substring(0,1);
        var num = rowId.split(/(\d+)/)
        var index =num[1];
        var sectionKey="";
        var projectUpdated=$(obj).attr('projectUpdated');
        if(section=="shared")
        {
            sectionKey="Shared";
        }
        var billableHours; // only in manager and finance tab
        var employeeRole; // only in manager and finance tab
        var monthHours;  // only in finance tab

        //These columns are to be made editable for shared section.
        var managerEcode;
        var coordinatorEcode;
        var leadEcode;
        var account;
        var project;
        var managerName; //To retain old value of manager Name on unselection of checkbox.
        var coordinatorName; //To retain old value of coordinator Name on unselection of checkbox.
        var leadName; //To retain old value of lead Name on unselection of checkbox.
         var allocationStatus; //This column is only in nonShared section.
         var checkboxId = input+sectionKey+"Checkbox"+index;
         var current = input+sectionKey+"Current"+index;
         var currentPlusOne = input+sectionKey+"CurrentPlusOne"+index;
         var currentPlusTwo = input+sectionKey+"CurrentPlusTwo"+index;
         var shadowResourceType = input+sectionKey+"ShadowResourceType"+index;
         var shadowStartDate = input+sectionKey+"shadowStartDate"+index;
         var benchStartDate = input+sectionKey+"benchStartDate"+index;
         var billingStartDate = input+sectionKey+"billingStartDate"+index;
         var billingEndDate = input+sectionKey+"billingEndDate"+index;
         var employeeStatus = input+sectionKey+"employeeStatus"+index;
         var lastWorkingDate = input+sectionKey+"lastWorkingDate"+index;
         var percentUtilization = input+sectionKey+"percentUtilization"+index;
         var remarks = input+sectionKey+"Remarks"+index;
         var vertical = input+sectionKey+"Vertical"+index;
         if(section=="shared")
         {
             managerEcode = input+sectionKey+"ManagerEcode"+index;
             managerName = input+sectionKey+"ManagerName"+index;
             coordinatorEcode = input+sectionKey+"CoordinatorEcode"+index;
             coordinatorName = input+sectionKey+"CoordinatorName"+index;
             leadEcode = input+sectionKey+"leadEcode"+index;
             leadName = input+sectionKey+"leadName"+index;
             account = input+sectionKey+"Account"+index;
             project = input+sectionKey+"Project"+index;
         }
         else
         {
            allocationStatus = input+"AllocationStatus"+index;
         }
        if ($('#'+checkboxId).prop("checked"))
        {
                if(section=="shared" && projectUpdated == "false")
                {
                    $('#'+managerEcode).prop("disabled", false);
                    $('#'+coordinatorEcode).prop('disabled', false);
                    $('#'+leadEcode).prop('disabled', false);
                    $('#'+account).prop('readonly', false);
                    $('#'+project).prop('readonly', false);
                }
                $('#'+current).children().show();
                $('#'+current).prop("required", "required");
                if($('#'+current).val() == "Shadow")
                {
                    $('#'+shadowResourceType).children().show();
                    $('#'+shadowResourceType).prop('readonly', false);
                    $('#'+shadowResourceType).prop('required', 'required');
                    $('#'+shadowStartDate).prop('readonly', false);
                    $('#'+shadowStartDate).prop('required', 'required');
                    if(section=="nonShared")
                    {
                        $('#'+allocationStatus).children().show();
                        $('#'+allocationStatus).prop('readonly', false);
                        $('#'+allocationStatus).prop('required', 'required');
                    }
                }
                if($('#'+current).val() == "Bench")
                {
                    $('#'+benchStartDate).prop('readonly', false);
                    $('#'+benchStartDate).prop('required', 'required');
                    if(section=="nonShared")
                    {
                        $('#'+allocationStatus).children().show();
                        $('#'+allocationStatus).prop('readonly', false);
                        $('#'+allocationStatus).prop('required', 'required');
                    }
                }
                if($('#'+current).val() == "Billing")
                {
                    $('#'+billingStartDate).prop("required", "required");
                    $('#'+billingStartDate).prop('readonly', false);
                }
                if($('#'+current).val() == "Partial Billing")
                {
                    $('#'+percentUtilization).prop("required", "required");
                    $('#'+percentUtilization).prop('readonly', false);
                }
                if($('#'+current).val() == "Bench - Exit")
                {
                    //Bench start date and Last Working Date
                    $('#'+benchStartDate).prop('readonly', false);
                    $('#'+benchStartDate).prop('required', 'required');
                    $('#'+lastWorkingDate).prop('readonly', false);
                    $('#'+lastWorkingDate).prop("required", "required");
                }
                if($('#'+current).val() == "Bench - Maternity")
                {
                     //bench start date
                      $('#'+benchStartDate).prop('readonly', false);
                      $('#'+benchStartDate).prop('required', 'required');
                }
                if($('#'+current).val() == "Business Readiness")
                {
                      //billing start date
                       $('#'+billingStartDate).prop("required", "required");
                       $('#'+billingStartDate).prop('readonly', false);
                }
                if($('#'+current).val() == "Shadow - Client Committed/24*7 Support")
                {
                      //shadow start date
                       $('#'+shadowStartDate).prop('readonly', false);
                       $('#'+shadowStartDate).prop('required', 'required');
                }
                if($('#'+current).val() == "Shadow - Exit")
                {
                     //shadow start date and Last Working Date
                      $('#'+shadowStartDate).prop('readonly', false);
                      $('#'+shadowStartDate).prop('required', 'required');
                      $('#'+lastWorkingDate).prop('readonly', false);
                      $('#'+lastWorkingDate).prop("required", "required");
                }
                if($('#'+current).val() == "Shadow - Planned Billing")
                {
                     //shadow start date and Billing start date
                     $('#'+shadowStartDate).prop('readonly', false);
                     $('#'+shadowStartDate).prop('required', 'required');
                     $('#'+billingStartDate).prop("required", "required");
                     $('#'+billingStartDate).prop('readonly', false);
                }
                if($('#'+current).val() == "Trainee - Planned Billing")
                {
                     //billing start date
                     $('#'+billingStartDate).prop("required", "required");
                     $('#'+billingStartDate).prop('readonly', false);
                }
                 if($('#'+current).val() == "Internal - Exit")
                {
                     //billing start date
                     $('#'+billingStartDate).prop("required", "required");
                     $('#'+billingStartDate).prop('readonly', false);
                }
                $('#'+currentPlusOne).children().show();
                $('#'+currentPlusOne).prop("required", "required");
                if($('#'+currentPlusOne).val() == "Shadow")
                {
                    $('#'+shadowStartDate).prop('readonly', false);
                    $('#'+shadowStartDate).prop('required', 'required');
                    if(section=="nonShared")
                    {
                        $('#'+allocationStatus).children().show();
                        $('#'+allocationStatus).prop('readonly', false);
                        $('#'+allocationStatus).prop('required', 'required');
                    }
                }
                if($('#'+currentPlusOne).val() == "Bench")
                {
                    $('#'+benchStartDate).prop('readonly', false);
                    $('#'+benchStartDate).prop('required', 'required');
                    if(section=="nonShared")
                    {
                        $('#'+allocationStatus).children().show();
                        $('#'+allocationStatus).prop('readonly', false);
                        $('#'+allocationStatus).prop('required', 'required');
                    }
                }
                if($('#'+currentPlusOne).val() == "Billing")
                {
                    $('#'+billingStartDate).prop("required", "required");
                    $('#'+billingStartDate).prop('readonly', false);
                }
                if($('#'+currentPlusOne).val() == "Partial Billing")
                {
                    $('#'+percentUtilization).prop("required", "required");
                    $('#'+percentUtilization).prop('readonly', false);
                }
                if($('#'+currentPlusOne).val() == "Bench - Exit")
                {
                    $('#'+benchStartDate).prop('readonly', false);
                    $('#'+benchStartDate).prop('required', 'required');
                    $('#'+lastWorkingDate).prop('readonly', false);
                    $('#'+lastWorkingDate).prop("required", "required");
                }
                if($('#'+currentPlusOne).val() == "Bench - Maternity")
                {
                      $('#'+benchStartDate).prop('readonly', false);
                      $('#'+benchStartDate).prop('required', 'required');
                }
                if($('#'+currentPlusOne).val() == "Business Readiness")
                {
                      $('#'+billingStartDate).prop("required", "required");
                      $('#'+billingStartDate).prop('readonly', false);
                }
                if($('#'+currentPlusOne).val() == "Shadow - Client Committed/24*7 Support")
                {
                     $('#'+shadowStartDate).prop('readonly', false);
                     $('#'+shadowStartDate).prop('required', 'required');
                }
                if($('#'+currentPlusOne).val() == "Shadow - Exit")
                {
                    $('#'+shadowStartDate).prop('readonly', false);
                    $('#'+shadowStartDate).prop('required', 'required');
                    $('#'+lastWorkingDate).prop('readonly', false);
                    $('#'+lastWorkingDate).prop("required", "required");
                }
                if($('#'+currentPlusOne).val() == "Shadow - Planned Billing")
                {
                    $('#'+shadowStartDate).prop('readonly', false);
                    $('#'+shadowStartDate).prop('required', 'required');
                    $('#'+billingStartDate).prop("required", "required");
                    $('#'+billingStartDate).prop('readonly', false);
                }
                if($('#'+currentPlusOne).val() == "Trainee - Planned Billing")
                {
                     $('#'+billingStartDate).prop("required", "required");
                     $('#'+billingStartDate).prop('readonly', false);
                }
                if($('#'+currentPlusOne).val() == "Internal - Exit")
                {
                    $('#'+billingStartDate).prop("required", "required");
                     $('#'+billingStartDate).prop('readonly', false);
                }
                $('#'+currentPlusTwo).children().show();
                $('#'+currentPlusTwo).prop("required", "required");
                if($('#'+currentPlusTwo).val() == "Shadow")
                {
                    $('#'+shadowStartDate).prop('readonly', false);
                    $('#'+shadowStartDate).prop("required", "required");
                    if(section=="nonShared")
                    {
                        $('#'+allocationStatus).children().show();
                        $('#'+allocationStatus).prop('readonly', false);
                        $('#'+allocationStatus).prop('required', 'required');
                    }
                }
                if($('#'+currentPlusTwo).val() == "Bench")
                {
                    $('#'+benchStartDate).prop('readonly', false);
                    $('#'+benchStartDate).prop("required", "required");
                    if(section=="nonShared")
                    {
                        $('#'+allocationStatus).children().show();
                        $('#'+allocationStatus).prop('readonly', false);
                        $('#'+allocationStatus).prop('required', 'required');
                    }
                }
                if($('#'+currentPlusTwo).val() == "Billing")
                {
                    $('#'+billingStartDate).prop("required", "required");
                    $('#'+billingStartDate).prop('readonly', false);
                }
                if($('#'+currentPlusTwo).val() == "Partial Billing")
                {
                    $('#'+percentUtilization).prop("required", "required");
                    $('#'+percentUtilization).prop('readonly', false);
                }
                if($('#'+currentPlusTwo).val() == "Bench - Exit")
                {
                    $('#'+benchStartDate).prop('readonly', false);
                    $('#'+benchStartDate).prop('required', 'required');
                    $('#'+lastWorkingDate).prop('readonly', false);
                    $('#'+lastWorkingDate).prop("required", "required");
                }
                if($('#'+currentPlusTwo).val() == "Bench - Maternity")
                {
                    $('#'+benchStartDate).prop('readonly', false);
                    $('#'+benchStartDate).prop('required', 'required');
                }
                if($('#'+currentPlusTwo).val() == "Business Readiness")
                {
                    $('#'+billingStartDate).prop("required", "required");
                    $('#'+billingStartDate).prop('readonly', false);
                }
                if($('#'+currentPlusTwo).val() == "Shadow - Client Committed/24*7 Support")
                {
                     $('#'+shadowStartDate).prop('readonly', false);
                     $('#'+shadowStartDate).prop('required', 'required');
                }
                if($('#'+currentPlusTwo).val() == "Shadow - Exit")
                {
                      $('#'+shadowStartDate).prop('readonly', false);
                      $('#'+shadowStartDate).prop('required', 'required');
                      $('#'+lastWorkingDate).prop('readonly', false);
                      $('#'+lastWorkingDate).prop("required", "required");
                }
                if($('#'+currentPlusTwo).val() == "Shadow - Planned Billing")
                {
                      $('#'+shadowStartDate).prop('readonly', false);
                      $('#'+shadowStartDate).prop('required', 'required');
                      $('#'+billingStartDate).prop("required", "required");
                      $('#'+billingStartDate).prop('readonly', false);
                }
               if($('#'+currentPlusTwo).val() == "Trainee - Planned Billing")
               {
                     $('#'+billingStartDate).prop("required", "required");
                     $('#'+billingStartDate).prop('readonly', false);
               }
               if($('#'+currentPlusTwo).val() == "Internal - Exit")
               {
                     $('#'+billingStartDate).prop("required", "required");
                     $('#'+billingStartDate).prop('readonly', false);
               }
                $('#'+employeeStatus).children().show();
                $('#'+employeeStatus).prop("required", "required");
                if($('#'+employeeStatus).val() == "Resigned")
                {
                    $('#'+lastWorkingDate).prop('readonly', false);
                    $('#'+lastWorkingDate).prop("required", "required");
                    $('#'+billingEndDate).prop("required", "required");
                    $('#'+billingEndDate).prop('readonly', false);
                }
                $('#'+remarks).prop('readonly', false);
                $('#'+vertical).children().show();
                $('#'+vertical).prop("required", "required");
                $('#'+percentUtilization).prop("required", "required"); //percent utilization is mandatory field also.
                $('#'+percentUtilization).prop('readonly', false);
                if(input == 'm')
                {
                    var billableHours = input+sectionKey+"billableHours"+index;
                    var employeeRole = input+sectionKey+"EmployeeRole"+index;
                    $('#'+billableHours).prop('readonly', false);
                    $('#'+billableHours).prop("required", "required");
                    $('#'+employeeRole).prop('readonly', false);
                }
                if(input == 'f')
                {
                    var billableHours = input+sectionKey+"billableHours"+index;
                    var employeeRole = input+sectionKey+"EmployeeRole"+index;
                    var monthHours = input+sectionKey+"MonthHours"+index;
                    $('#'+billableHours).prop('readonly', false);
                    $('#'+billableHours).prop("required", "required");
                    $('#'+employeeRole).prop('readonly', false);
                    $('#'+monthHours).prop('readonly', false);
                    $('#'+monthHours).prop("required", "required");
                }
        }
        else
        {
                if(section=="shared" && projectUpdated == "false" )
                {
                    $('#'+managerEcode).prop("disabled", true);
                    $('#'+managerEcode).val($('#o'+managerEcode).val());
                    $('#'+managerName).val($('#o'+managerName).val());
                    $('#'+coordinatorEcode).prop('disabled', true);
                    $('#'+coordinatorEcode).val($('#o'+coordinatorEcode).val());
                    $('#'+coordinatorName).val($('#o'+coordinatorName).val());
                    $('#'+leadEcode).prop('disabled', true);
                    $('#'+leadEcode).val($('#o'+leadEcode).val());
                    $('#'+leadName).val($('#o'+leadName).val());
                    $('#'+account).prop('readonly',true);
                    $('#'+account).val($('#o'+account).val());
                    $('#'+project).prop('readonly',true);
                    $('#'+project).val($('#o'+project).val());
                }

                $('#'+current).children().hide();
                $('#'+current).prop("required", false);
                $('#'+current).val($('#o'+current).val());
                $('#'+currentPlusOne).children().hide();
                $('#'+currentPlusOne).prop("required", false);
                $('#'+currentPlusOne).val($('#o'+currentPlusOne).val());
                $('#'+currentPlusTwo).children().hide();
                $('#'+currentPlusTwo).prop("required", false);
                $('#'+currentPlusTwo).val($('#o'+currentPlusTwo).val());
                $('#'+shadowResourceType).children().hide();
                $('#'+shadowResourceType).val($('#o'+shadowResourceType).val());
                $('#'+shadowResourceType).prop("required", false);
                $('#'+shadowStartDate).prop('readonly', true);
                $('#'+shadowStartDate).val($('#o'+shadowStartDate).val());
                $('#'+shadowStartDate).prop("required", false);
                $('#'+benchStartDate).prop('readonly', true);
                $('#'+benchStartDate).val($('#o'+benchStartDate).val());
                $('#'+benchStartDate).prop("required", false);
                $('#'+billingStartDate).prop('readonly', true);
                $('#'+billingStartDate).prop("required", false);
                $('#'+billingStartDate).val($('#o'+billingStartDate).val());
                $('#'+billingEndDate).prop('readonly', true);
                $('#'+billingEndDate).prop("required", false);
                $('#'+billingEndDate).val($('#o'+billingEndDate).val());
                if(section=="nonShared")
                {
                    $('#'+allocationStatus).children().hide();
                    $('#'+allocationStatus).prop("required", false);
                    $('#'+allocationStatus).val($('#o'+allocationStatus).val());
                }
                $('#'+employeeStatus).children().hide();
                $('#'+employeeStatus).prop("required",false);
                $('#'+employeeStatus).val($('#o'+employeeStatus).val());
                $('#'+lastWorkingDate).prop('readonly', true);
                $('#'+lastWorkingDate).prop("required", false);
                $('#'+lastWorkingDate).val($('#o'+lastWorkingDate).val());
                $('#'+percentUtilization).prop('readonly', true);
                $('#'+percentUtilization).prop("required", false);
                $('#'+percentUtilization).val($('#o'+percentUtilization).val());
                $('#'+remarks).prop('readonly', true);
                $('#'+vertical).children().hide();
                $('#'+vertical).val($('#o'+vertical).val());
                $('#'+vertical).prop("required", false);
                if(input == 'm')
                {
                    var billableHours = input+sectionKey+"billableHours"+index;
                    var employeeRole = input+sectionKey+"EmployeeRole"+index;
                    $('#'+billableHours).prop('readonly',true);
                    $('#'+billableHours).val($('#o'+billableHours).val());
                    $('#'+billableHours).prop("required", false);
                    $('#'+employeeRole).prop('readonly',true);
                    $('#'+employeeRole).val($('#o'+employeeRole).val());
                }
                if(input == 'f')
                {
                    var billableHours = input+sectionKey+"billableHours"+index;
                    var employeeRole = input+sectionKey+"EmployeeRole"+index;
                    var monthHours = input+sectionKey+"MonthHours"+index;
                    $('#'+billableHours).prop('readonly',true);
                    $('#'+billableHours).val($('#o'+billableHours).val());
                    $('#'+billableHours).prop("required", false);
                    $('#'+employeeRole).prop('readonly',true);
                    $('#'+employeeRole).val($('#o'+employeeRole).val());
                    $('#'+monthHours).prop('readonly',true);
                    $('#'+monthHours).val($('#o'+monthHours).val());
                    $('#'+monthHours).prop("required", false);
                }
       }
    }

    function validateColumnsOnSelect(obj,section)
    {
        //alert("section== "+section);
        var rowId = $(obj).attr('id');
        //alert("rowId== "+rowId);
        var input = rowId.substring(0,1);
        //alert("input== "+input);
        var num = rowId.split(/(\d+)/)
        var index =num[1];
        var columnDetail = num[0];
        var column;
        var currentValue;
        var currentPlusOneValue;
        var currentPlusTwoValue;
        var shadowResourceType;
        var shadowStartDateTarget;
        var allocationStatusTarget;
        var benchStartDateTarget;
        var billingStartDateTarget;
        var percentUtilizationTarget;
        var lastWorkingDateTarget;
        var medUnpaidValue;
        var billingEndDateTarget;
        if(section=="nonShared")
        {
            //alert("Inside nonshared");
            if(columnDetail.includes("CurrentPlusOne"))
            {
                column = "CurrentPlusOne";
            }
            else if(columnDetail.includes("CurrentPlusTwo"))
            {
                column = "CurrentPlusTwo";
            }
            else if(columnDetail.includes("employeeStatus"))
            {
                column = "employeeStatus";
            }
            else
            {
                column = "Current";
            }
            //alert("column== "+column);
            //alert("index== "+index);
             currentValue = input+"Current"+index;
             currentPlusOneValue = input+"CurrentPlusOne"+index;
             currentPlusTwoValue = input+"CurrentPlusTwo"+index;
             shadowResourceType = input+"ShadowResourceType"+index;
             shadowStartDateTarget = input+"shadowStartDate"+index;
             allocationStatusTarget = input+"AllocationStatus"+index;
             benchStartDateTarget = input+"benchStartDate"+index;
             billingStartDateTarget = input+"billingStartDate"+index;
             percentUtilizationTarget = input+"percentUtilization"+index;
             lastWorkingDateTarget = input+"lastWorkingDate"+index;
             medUnpaidValue=input+"employeeStatus"+index;
             billingEndDateTarget = input+"billingEndDate"+index;
        }
        else if(section=="shared")
        {
        	//alert("Inside shared");
        	if(columnDetail.includes("CurrentPlusOne"))
        	{
        		column = "CurrentPlusOne";
        	}
        	else if(columnDetail.includes("CurrentPlusTwo"))
        	{
        		column = "CurrentPlusTwo";
        	}
        	else if(columnDetail.includes("employeeStatus"))
        	{
        		column = "employeeStatus";
        	}
        	else
        	{
        		column = "Current";
        	}
        	//alert("index== "+index);
        	 currentValue = input+"SharedCurrent"+index;
        	 currentPlusOneValue = input+"SharedCurrentPlusOne"+index;
        	 currentPlusTwoValue = input+"SharedCurrentPlusTwo"+index;
        	 medUnpaidValue = input+"SharedemployeeStatus"+index;
        	 shadowResourceType = input+"SharedShadowResourceType"+index;
        	 shadowStartDateTarget = input+"SharedshadowStartDate"+index;
        	 benchStartDateTarget = input+"SharedbenchStartDate"+index;
        	 billingStartDateTarget = input+"SharedbillingStartDate"+index;
        	 percentUtilizationTarget = input+"SharedpercentUtilization"+index;
        	 lastWorkingDateTarget = input+"SharedlastWorkingDate"+index;
        	 billingEndDateTarget = input+"SharedbillingEndDate"+index;
        }
        if(column == "Current")
        {
            var valueMColumn = $("#"+currentValue).val();
            $("#"+currentPlusOneValue).val(valueMColumn);
            $("#"+currentPlusTwoValue).val(valueMColumn);
        }
        var curVal=$("#"+currentValue).val();
        var curPlusOneVal=$("#"+currentPlusOneValue).val();
        var curPlusTwoVal=$("#"+currentPlusTwoValue).val();
        var medUnpaidVal=$("#"+medUnpaidValue).val();
        //alert(curVal+","+curPlusOneVal+","+curPlusTwoVal+","+medUnpaidVal);
        if($("#"+currentValue).val()=='Shadow' || $("#"+currentPlusOneValue).val()=='Shadow' || $("#"+currentPlusTwoValue).val()=='Shadow')
        {
           // alert("if Shadow");
            //alert(shadowStartDateTarget);
            $("#"+shadowResourceType).children().show();
            $("#"+shadowResourceType).prop("required", "required");
            $("#"+shadowStartDateTarget).prop("required", "required");
            $("#"+shadowStartDateTarget).prop("readonly", false);
            if(section=="nonShared"){
                $("#"+allocationStatusTarget).children().show();
                $("#"+allocationStatusTarget).prop("required", "required");
            }
        }else
        {
           // alert("else shadow");
            $("#"+shadowResourceType).children().hide();
            $("#"+shadowResourceType).prop("required", false);
            $("#"+shadowResourceType).val('');
            if(!(curVal=='Shadow - Client Committed/24*7 Support') && !(curPlusOneVal=='Shadow - Client Committed/24*7 Support')
                && !(curPlusTwoVal=='Shadow - Client Committed/24*7 Support') && !(curVal=='Shadow - Exit') && !(curPlusOneVal=='Shadow - Exit')
                && !(curPlusTwoVal=='Shadow - Exit') && !(curVal=='Shadow - Planned Billing') && !(curPlusOneVal=='Shadow - Planned Billing')
                && !(curPlusTwoVal=='Shadow - Planned Billing'))
            {
                //alert("else if shadow-shadowStartDateTarget");
                $("#"+shadowStartDateTarget).prop("required", false);
                $("#"+shadowStartDateTarget).prop("readonly", true);
                $("#"+shadowStartDateTarget).val('');
            }

            if(!(curVal=='Bench') && !(curPlusOneVal=='Bench') && !(curPlusTwoVal=='Bench') && (section=="nonShared"))
            {
               // alert("curVal== "+curVal);
              //  alert("else if shadow-allocationStatusTarget");
                $("#"+allocationStatusTarget).children().hide();
                $("#"+allocationStatusTarget).prop("required", false);
                $("#"+allocationStatusTarget).val('');
            }
        }
        if($("#"+currentValue).val()=='Bench' || $("#"+currentPlusOneValue).val()=='Bench' || $("#"+currentPlusTwoValue).val()=='Bench')
        {
               // alert("if Bench");
                $("#"+benchStartDateTarget).prop("required", "required");
                $("#"+benchStartDateTarget).prop("readonly", false);
                if(section=="nonShared"){
                    $("#"+allocationStatusTarget).children().show();
                    $("#"+allocationStatusTarget).prop("required", "required");
                }
        }else
        {
               // alert("else Bench");
                if(!(curVal=='Bench - Exit') && !(curPlusOneVal=='Bench - Exit')
                           && !(curPlusTwoVal=='Bench - Exit') && !(curVal=='Bench - Maternity') && !(curPlusOneVal=='Bench - Maternity')
                           && !(curPlusTwoVal=='Bench - Maternity'))
                {
                    //alert("else if Bench-benchStartDateTarget");
                    $("#"+benchStartDateTarget).prop("required", false);
                    $("#"+benchStartDateTarget).prop("readonly", true);
                    $("#"+benchStartDateTarget).val('');
                }

                if(!(curVal=='Shadow') && !(curPlusOneVal=='Shadow') && !(curPlusTwoVal=='Shadow') && (section=="nonShared"))
                {
                   // alert("else if Bench-allocationStatusTarget");
                    $("#"+allocationStatusTarget).children().hide();
                    $("#"+allocationStatusTarget).prop("required", false);
                    $("#"+allocationStatusTarget).val('');
                }
        }
        if($("#"+currentValue).val()=='Billing' || $("#"+currentPlusOneValue).val()=='Billing' || $("#"+currentPlusTwoValue).val()=='Billing')
        {
               // alert("if Billing");
                $("#"+billingStartDateTarget).prop("required", "required");
                $("#"+billingStartDateTarget).prop("readonly", false);
        }else
        {
                //alert("else Billing");
                if(!(curVal=='Business Readiness') && !(curPlusOneVal=='Business Readiness')
                           && !(curPlusTwoVal=='Business Readiness') && !(curVal=='Shadow - Planned Billing') && !(curPlusOneVal=='Shadow - Planned Billing')
                           && !(curPlusTwoVal=='Shadow - Planned Billing') && !(curVal=='Trainee - Planned Billing') && !(curPlusOneVal=='Trainee - Planned Billing')
                           && !(curPlusTwoVal=='Trainee - Planned Billing'))
                {
                    //alert("else if Billing-billingStartDateTarget");
                    $("#"+billingStartDateTarget).prop("required", false);
                    $("#"+billingStartDateTarget).prop("readonly", true);
                    $("#"+billingStartDateTarget).val('');
                }
        }
        if($("#"+currentValue).val()=='Partial Billing' || $("#"+currentPlusOneValue).val()=='Partial Billing' || $("#"+currentPlusTwoValue).val()=='Partial Billing')
        {
                //alert("if Partial Billing");
                $("#"+percentUtilizationTarget).prop("required", "required");
                 $("#"+percentUtilizationTarget).prop("readonly", false);
        }else
        {
                //alert("else - Partial Billing");
                $("#"+percentUtilizationTarget).prop("required", false);
                $("#"+percentUtilizationTarget).prop("readonly", true);
        }
        if($("#"+currentValue).val()=='Bench - Exit' || $("#"+currentPlusOneValue).val()=='Bench - Exit' || $("#"+currentPlusTwoValue).val()=='Bench - Exit')
        {
               // alert("if Bench - Exit");
                $("#"+benchStartDateTarget).prop("required", "required");
                $("#"+benchStartDateTarget).prop("readonly", false);
                $("#"+lastWorkingDateTarget).prop("required", "required");
                $("#"+lastWorkingDateTarget).prop("readonly", false);
        }else
        {
                //alert("else Bench - Exit");
                if(!(curVal=='Bench') && !(curPlusOneVal=='Bench')
                           && !(curPlusTwoVal=='Bench') && !(curVal=='Bench - Maternity') && !(curPlusOneVal=='Bench - Maternity')
                           && !(curPlusTwoVal=='Bench - Maternity'))
                {
                   // alert("else if- (Bench - Exit) - benchStartDateTarget");
                    $("#"+benchStartDateTarget).prop("required", false);
                    $("#"+benchStartDateTarget).prop("readonly", true);
                    $("#"+benchStartDateTarget).val('');
                }
                if(curVal!='Internal - Exit' && curPlusOneVal!='Internal - Exit'
                           && curPlusTwoVal!='Internal - Exit' && curVal!='Shadow - Exit' && curPlusOneVal!='Shadow - Exit'
                           && curPlusTwoVal!='Shadow - Exit' &&  medUnpaidVal!='Resigned')
                {
                    //alert("else if- (Bench - Exit) - lastWorkingDateTarget");
                    //alert(medUnpaidValue);
                    $("#"+lastWorkingDateTarget).prop("required", false);
                    $("#"+lastWorkingDateTarget).prop("readonly", true);
                    $("#"+lastWorkingDateTarget).val('');
                }
        }
        if($("#"+currentValue).val()=='Bench - Maternity' || $("#"+currentPlusOneValue).val()=='Bench - Maternity' || $("#"+currentPlusTwoValue).val()=='Bench - Maternity')
        {
                //alert("if Bench - Maternity");
                $("#"+benchStartDateTarget).prop("required", "required");
                $("#"+benchStartDateTarget).prop("readonly", false);
        }else
        {
                //alert("else Bench - Maternity");
                if(curVal!='Bench' && curPlusOneVal!='Bench'
                           && curPlusTwoVal!='Bench' && curVal!='Bench - Exit' && curPlusOneVal!='Bench - Exit'
                           && curPlusTwoVal!='Bench - Exit')
                {
                    //alert("else -if - (Bench - Maternity) - benchStartDateTarget");
                    $("#"+benchStartDateTarget).prop("required", false);
                    $("#"+benchStartDateTarget).prop("readonly", true);
                    $("#"+benchStartDateTarget).val('');
                }
        }

        if($("#"+currentValue).val()=='Business Readiness' || $("#"+currentPlusOneValue).val()=='Business Readiness' || $("#"+currentPlusTwoValue).val()=='Business Readiness')
        {
                //alert("if Business Readiness");
                $("#"+billingStartDateTarget).prop("required", "required");
                $("#"+billingStartDateTarget).prop("readonly", false);
        }
        else
        {
                //alert("else Business Readiness");
                if(curVal!='Billing' && curPlusOneVal!='Billing'
                           && curPlusTwoVal!='Billing' && curVal!='Shadow - Planned Billing' && curPlusOneVal!='Shadow - Planned Billing'
                           && curPlusTwoVal!='Shadow - Planned Billing' && curVal!='Trainee - Planned Billing' && curPlusOneVal!='Trainee - Planned Billing'
                           && curPlusTwoVal!='Trainee - Planned Billing')
                {
                    //alert("else -if - (Business Readiness) - billingStartDateTarget");
                    $("#"+billingStartDateTarget).prop("required", false);
                    $("#"+billingStartDateTarget).prop("readonly", true);
                    $("#"+billingStartDateTarget).val('');
                }
        }
        if($("#"+currentValue).val()=='Internal - Exit' || $("#"+currentPlusOneValue).val()=='Internal - Exit' || $("#"+currentPlusTwoValue).val()=='Internal - Exit')
        {
                //alert("if Internal - Exit");
                $("#"+lastWorkingDateTarget).prop("required", "required");
                $("#"+lastWorkingDateTarget).prop("readonly", false);
        }else
        {
                //alert("else Internal - Exit");
                if(curVal!='Bench - Exit' && curPlusOneVal!='Bench - Exit'
                           && curPlusTwoVal!='Bench - Exit' && curVal!='Shadow - Exit' && curPlusOneVal!='Shadow - Exit'
                           && curPlusTwoVal!='Shadow - Exit' && medUnpaidVal!='Resigned')
                {
                    //alert("else -if - (Internal - Exit) - lastWorkingDateTarget");
                    $("#"+lastWorkingDateTarget).prop("required", false);
                    $("#"+lastWorkingDateTarget).prop("readonly", true);
                    $("#"+lastWorkingDateTarget).val('');
                }
        }

        if($("#"+currentValue).val()=='Shadow - Client Committed/24*7 Support' || $("#"+currentPlusOneValue).val()=='Shadow - Client Committed/24*7 Support' || $("#"+currentPlusTwoValue).val()=='Shadow - Client Committed/24*7 Support')
        {
                 //alert("if Shadow - Client Committed/24*7 Support");
                 $("#"+shadowStartDateTarget).prop("required", "required");
                 $("#"+shadowStartDateTarget).prop("readonly", false);
        }
        else
        {
                //alert("else Shadow - Client Committed/24*7 Support");
                if((curVal!='Shadow') && (curPlusOneVal!='Shadow')
                           && (curPlusTwoVal!='Shadow') && (curVal!='Shadow - Exit') && (curPlusOneVal!='Shadow - Exit')
                           && (curPlusTwoVal!='Shadow - Exit') && (curVal!='Shadow - Planned Billing') && (curPlusOneVal!='Shadow - Planned Billing')
                           && (curPlusTwoVal!='Shadow - Planned Billing'))
                {
                    //alert("else -if - {Shadow - Client Committed/24*7 Support} - shadowStartDateTarget");
                    $("#"+shadowStartDateTarget).prop("required", false);
                    $("#"+shadowStartDateTarget).prop("readonly", true);
                    $("#"+shadowStartDateTarget).val('');
                }
        }
        if($("#"+currentValue).val()=='Shadow - Exit' || $("#"+currentPlusOneValue).val()=='Shadow - Exit' || $("#"+currentPlusTwoValue).val()=='Shadow - Exit')
        {
                 //alert("if Shadow - Exit");
                 $("#"+shadowStartDateTarget).prop("required", "required");
                 $("#"+shadowStartDateTarget).prop("readonly", false);
                 $("#"+lastWorkingDateTarget).prop("required", "required");
                 $("#"+lastWorkingDateTarget).prop("readonly", false);
        }
        else
        {
                //alert("else Shadow - Exit");
                if(curVal!='Shadow' && curPlusOneVal!='Shadow'
                               && curPlusTwoVal!='Shadow' && curVal!='Shadow - Client Committed/24*7 Support' && curPlusOneVal!='Shadow - Client Committed/24*7 Support'
                               && curPlusTwoVal!='Shadow - Client Committed/24*7 Support' && curVal!='Shadow - Planned Billing' && curPlusOneVal!='Shadow - Planned Billing'
                               && curPlusTwoVal!='Shadow - Planned Billing')
                {
                    //alert("else -if - {Shadow - Exit} - shadowStartDateTarget");
                    $("#"+shadowStartDateTarget).prop("required", false);
                    $("#"+shadowStartDateTarget).prop("readonly", true);
                    $("#"+shadowStartDateTarget).val('');
                }
                if(curVal!='Bench - Exit' && curPlusOneVal!='Bench - Exit'
                           && curPlusTwoVal!='Bench - Exit' && curVal!='Internal - Exit' && curPlusOneVal!='Internal - Exit'
                           && curPlusTwoVal!='Internal - Exit' && medUnpaidVal!='Resigned')
                {
                    //alert("else -if - {Shadow - Exit} - lastWorkingDateTarget");
                    $("#"+lastWorkingDateTarget).prop("required", false);
                    $("#"+lastWorkingDateTarget).prop("readonly", true);
                    $("#"+lastWorkingDateTarget).val('');
                }
        }
        if($("#"+currentValue).val()=='Shadow - Planned Billing' || $("#"+currentPlusOneValue).val()=='Shadow - Planned Billing' || $("#"+currentPlusTwoValue).val()=='Shadow - Planned Billing')
        {
                 //alert("if Shadow - Planned Billing");
                 $("#"+shadowStartDateTarget).prop("required", "required");
                 $("#"+shadowStartDateTarget).prop("readonly", false);
                 $("#"+billingStartDateTarget).prop("required", "required");
                 $("#"+billingStartDateTarget).prop("readonly", false);
        }
        else
        {
                //alert("else Shadow - Planned Billing");
                if(curVal!='Shadow' && curPlusOneVal!='Shadow'
                           && curPlusTwoVal!='Shadow' && curVal!='Shadow - Client Committed/24*7 Support' && curPlusOneVal!='Shadow - Client Committed/24*7 Support'
                           && curPlusTwoVal!='Shadow - Client Committed/24*7 Support' && curVal!='Shadow - Exit' && curPlusOneVal!='Shadow - Exit'
                           && curPlusTwoVal!='Shadow - Exit')
                {
                    //alert("else -if - (Shadow - Planned Billing) - shadowStartDateTarget");
                    $("#"+shadowStartDateTarget).prop("required", false);
                    $("#"+shadowStartDateTarget).prop("readonly", true);
                    $("#"+shadowStartDateTarget).val('');
                }
                if(curVal!='Billing' && curPlusOneVal!='Billing'
                           && curPlusTwoVal!='Billing' && curVal!='Business Readiness' && curPlusOneVal!='Business Readiness'
                           && curPlusTwoVal!='Business Readiness' && curVal!='Trainee - Planned Billing' && curPlusOneVal!='Trainee - Planned Billing'
                           && curPlusTwoVal!='Trainee - Planned Billing')
                {
                    //alert("else -if - (Shadow - Planned Billing) - billingStartDateTarget");
                    $("#"+billingStartDateTarget).prop("required", false);
                    $("#"+billingStartDateTarget).prop("readonly", true);
                    $("#"+billingStartDateTarget).val('');
                }
        }
        if($("#"+currentValue).val()=='Trainee - Planned Billing' || $("#"+currentPlusOneValue).val()=='Trainee - Planned Billing' || $("#"+currentPlusTwoValue).val()=='Trainee - Planned Billing')
        {
                //alert("if Trainee - Planned Billing");
                $("#"+billingStartDateTarget).prop("required", "required");
                $("#"+billingStartDateTarget).prop("readonly", false);
        }else
        {
                //alert("else Trainee - Planned Billing");
                if(curVal!='Billing' && curPlusOneVal!='Billing'
                           && curPlusTwoVal!='Billing' && curVal!='Business Readiness' && curPlusOneVal!='Business Readiness'
                           && curPlusTwoVal!='Business Readiness' && curVal!='Shadow - Planned Billing' && curPlusOneVal!='Shadow - Planned Billing'
                           && curPlusTwoVal!='Shadow - Planned Billing')
                {
                    //alert("else -if - (Trainee - Planned Billing) - billingStartDateTarget");
                    $("#"+billingStartDateTarget).prop("required", false);
                    $("#"+billingStartDateTarget).prop("readonly", true);
                    $("#"+billingStartDateTarget).val('');
                }
        }
        $("#"+percentUtilizationTarget).prop("required", "required"); // Percent utilization column is non-conditional also.
        $("#"+percentUtilizationTarget).prop("readonly", false);
        if(column == 'employeeStatus')
        {
            //alert("Employee status");
            //alert(curVal+","+curPlusOneVal+","+curPlusTwoVal+","+medUnpaidVal);
            var current = $(obj).val();
            if(current == 'Resigned')
            {
                //alert("if Current resigned");
                $("#"+lastWorkingDateTarget).prop("required", "required");
                $("#"+lastWorkingDateTarget).prop("readonly", false);
                $("#"+billingEndDateTarget).prop("required", "required");
                $("#"+billingEndDateTarget).prop("readonly", false);
            }
            else
            {
                //alert("else Current resigned");
                if(curVal!='Bench - Exit' && curPlusOneVal!='Bench - Exit'
                       && curPlusTwoVal!='Bench - Exit' && curVal!='Internal - Exit' && curPlusOneVal!='Internal - Exit'
                       && curPlusTwoVal!='Internal - Exit' && curVal!='Shadow - Exit' && curPlusOneVal!='Shadow - Exit'
                       && curPlusTwoVal!='Shadow - Exit')
                {
                    $("#"+lastWorkingDateTarget).prop("required", false);
                    $("#"+lastWorkingDateTarget).prop("readonly", true);
                    $("#"+lastWorkingDateTarget).val('');
                }
                $("#"+billingEndDateTarget).prop("required", false);
                $("#"+billingEndDateTarget).prop("readonly", true);
                $("#"+billingEndDateTarget).val('');
            }
        }
    }

    $('#terms').on('change', function() {
        //alert('inside checkbox');
        var input = this.checked ? '2' : '1';
        //alert(input);
        $('#managerAction').val(input);
        if(input=='2')
        {
            $("#saveBtn").text('Submit and Send Final Confirmation to Finance team');
            $("#saveBtn").css("margin-left","10px");
        }
        else if(input=='1'){
             $("#saveBtn").text('Submit');
        }
        //alert(val);
    });

    function confirmFormSubmission(input)
    {
        if(input == 'm')
        {
            var action = $('#mvalidateAction').val();
            var managerAction= $('#managerAction').val();
            //alert("action== "+managerAction);
            var confirmationMessage='Are you sure you want to submit details of selected employee/s ?';
            //alert("action==>"+ action);
            if(action == 1)
            {
                if(confirm('Are you sure you want to delete details of employee ?'))
                {
                    loaderModal.style.display="block";
                    document.getElementById("mBillingForm").action="<%=submitBillingManagerDetails%>";
                          return true;
                } else {
                          return false;
                }
            }
            else
            {
                var checkboxId = input+"Checkbox";
                var checkedLength =$('input[id^="'+checkboxId+'"]:checked').length;
                var count;
                var checkboxSharedId = input+"SharedCheckbox";
                var checkedSharedLength =$('input[id^="'+checkboxSharedId+'"]:checked').length;
                count = document.getElementById("managerSharedTablebBody").rows.length;
                $('#mTotalRows').val(count);
                //First check terms checkbox if checked --> Submit
                if(managerAction==2)
                {
                    confirmationMessage='After Submitting, data will be available in view only mode. In order to make any changes in the data submitted, you will be required to connect to the Finance Team. Are you sure you want to proceed?';
                    if(confirm(confirmationMessage)){
                        loaderModal.style.display="block";
                        document.getElementById("mBillingForm").action="<%=submitBillingManagerDetails%>";
                        return true;
                    } else {
                        return false;
                    }
                }
                else if(managerAction==1)
                {
                    if(checkedLength > 0 || checkedSharedLength > 0 || count > mExistingRows)
                    {
                        var totalValue=[];
                        for (i = 0; i < count; i++)
                        {
                             var billingId = $("#managerSharedTablebBody").find("tr").eq(i).find("td").eq(0).find("input").eq(0).val();
                             var ecodeColumn = $("#managerSharedTablebBody").find("tr").eq(i).find("td").eq(2).find("input").eq(0).val();
                             var managerEcodeColumn,accountColumn;
                             if(billingId == "")
                             {
                                managerEcodeColumn = $("#managerSharedTablebBody").find("tr").eq(i).find("td").eq(4).find("input").eq(0).val();
                                accountColumn = $("#managerSharedTablebBody").find("tr").eq(i).find("td").eq(14).find("input").eq(0).val();
                             }else
                             {
                                managerEcodeColumn = $("#managerSharedTablebBody").find("tr").eq(i).find("td").eq(4).find("input").eq(1).val();
                                accountColumn = $("#managerSharedTablebBody").find("tr").eq(i).find("td").eq(14).find("input").eq(1).val();
                             }
                             var comparableString = ecodeColumn + managerEcodeColumn + accountColumn;
                             totalValue.push(comparableString);
                        }
                        var uniqueValue = totalValue.filter((item, i, ar) => ar.indexOf(item) === i);
                        if(totalValue.length != uniqueValue.length) {
                              alert("Please enter unique details in shared resources");
                              return false;
                        }
                        else
                        {
                            if(confirm(confirmationMessage)){
                                loaderModal.style.display="block";
                                document.getElementById("mBillingForm").action="<%=submitBillingManagerDetails%>";
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }
                    else
                    {
                        alert("Please select at least one checkbox to continue");
                        return false;
                    }
                }
                else
                {
                    alert("Something went wrong..!!");
                }
            }
        }
        else if(input == 'f')
        {
               var action = $('#fvalidateAction').val();
               if(action == 1)
               {
               		if(confirm('Are you sure you want to delete details of employee ?'))
               		{
               		    loaderModal.style.display="block";
               			document.getElementById("fBillingForm").action="<%=submitBillingFinanceDetails%>";
               			return true;
               		} else {
               			return false;
               		}
               }
               else
               {
                   var checkboxId = input+"Checkbox";
                   var checkedLength =$('input[id^="'+checkboxId+'"]:checked').length;
                   var count;
                   var checkboxSharedId = input+"SharedCheckbox";
                   var checkedSharedLength =$('input[id^="'+checkboxSharedId+'"]:checked').length;
                   count = document.getElementById("financeSharedTableBody").rows.length;
                   $('#fTotalRows').val(count);
                   if(checkedLength > 0 || checkedSharedLength > 0 || count > fExistingRows)
                   {
                       var totalValue=[];
                       for (i = 0; i < count; i++)
                       {
                            var billingId = $("#financeSharedTableBody").find("tr").eq(i).find("td").eq(0).find("input").eq(0).val();
                            var ecodeColumn = $("#financeSharedTableBody").find("tr").eq(i).find("td").eq(2).find("input").eq(0).val();
                            var managerEcodeColumn,accountColumn;
                            if(billingId == "")
                            {
                               managerEcodeColumn = $("#financeSharedTableBody").find("tr").eq(i).find("td").eq(4).find("input").eq(0).val();
                               accountColumn = $("#financeSharedTableBody").find("tr").eq(i).find("td").eq(14).find("input").eq(0).val();
                            }else
                            {
                               managerEcodeColumn = $("#financeSharedTableBody").find("tr").eq(i).find("td").eq(4).find("input").eq(1).val();
                               accountColumn = $("#financeSharedTableBody").find("tr").eq(i).find("td").eq(14).find("input").eq(1).val();
                            }
                            var comparableString = ecodeColumn + managerEcodeColumn + accountColumn;
                            totalValue.push(comparableString);
                       }
                       var uniqueValue = totalValue.filter((item, i, ar) => ar.indexOf(item) === i);
                       if(totalValue.length != uniqueValue.length) {
                             alert("Shared/Partial employee with same manager & account name already exists");
                             return false;
                       }
                       else
                       {
                           if(confirm('Are you sure you want to submit details of selected employee/s ?')){
                                loaderModal.style.display="block";
                               document.getElementById("fBillingForm").action="<%=submitBillingFinanceDetails%>";
                               return true;
                           } else {
                               return false;
                           }
                       }
                   }
                   else
                   {
                       alert("Please select at least one checkbox to continue");
                       return false;
                   }
               }
        }
        else
        {
             var checkboxId = input+"Checkbox";
             var checkedLength =$('input[id^="'+checkboxId+'"]:checked').length;
             if(checkedLength > 0)
             {
                  if(confirm('Are you sure you want to submit details of selected employee/s ?')){
                            loaderModal.style.display="block";
                            document.getElementById("hBillingForm").action="<%=submitBillingHrDetails%>";
                            return true;
                  } else {
                            return false;
                  }
             }
             else
             {
                  alert("Please select at least one checkbox to continue");
                  return false;
             }
        }
    }
    function validateActionPerformed(actionPerformed,input)
    {
        //alert(input);
        if(input == 'm')
        {
            $('#mvalidateAction').val(actionPerformed);
        }
        if(input == 'f')
        {
            $('#fvalidateAction').val(actionPerformed);
        }
        else if(input == 'h')
        {
            $('#hvalidateAction').val(actionPerformed);
        }
    }
    function confirmHrSharedFormSubmission(input)
    {
        var count;
        var action = $('#hvalidateAction').val();
        if(action == 1)
        {
            if(confirm('Are you sure you want to delete details of employee ?'))
            {
                loaderModal.style.display="block";
                document.getElementById("hSharedBillingForm").action="<%=submitHrSharedBillingDetails%>";
                      return true;
            } else {
                      return false;
            }
        }
        else
        {
            var checkboxId = input+"SharedCheckbox";
            var checkedLength =$('input[id^="'+checkboxId+'"]:checked').length;
            count = document.getElementById("hrSharedTablebBody").rows.length;
            $('#hTotalRows').val(count);
            if(checkedLength > 0 || count > hExistingRows)
            {
                var totalValue=[];
                for (i = 0; i < count; i++)
                {
                     var billingId = $("#hrSharedTablebBody").find("tr").eq(i).find("td").eq(0).find("input").eq(0).val();
                     var ecodeColumn = $("#hrSharedTablebBody").find("tr").eq(i).find("td").eq(2).find("input").eq(0).val();
                     var managerEcodeColumn,accountColumn;
                     if(billingId == "")
                     {
                        managerEcodeColumn = $("#hrSharedTablebBody").find("tr").eq(i).find("td").eq(4).find("input").eq(0).val();
                        accountColumn = $("#hrSharedTablebBody").find("tr").eq(i).find("td").eq(14).find("input").eq(0).val();
                     }else
                     {
                        managerEcodeColumn = $("#hrSharedTablebBody").find("tr").eq(i).find("td").eq(4).find("input").eq(1).val();
                        accountColumn = $("#hrSharedTablebBody").find("tr").eq(i).find("td").eq(14).find("input").eq(1).val();
                     }
                     var comparableString = ecodeColumn + managerEcodeColumn + accountColumn;
                     totalValue.push(comparableString);
                }
                var uniqueValue = totalValue.filter((item, i, ar) => ar.indexOf(item) === i);
                if(totalValue.length != uniqueValue.length) {
                      alert("Please enter unique details in shared resources");
                      return false;
                }
                else
                {
                    if(confirm('Are you sure you want to submit details of employee/s ?')){
                        loaderModal.style.display="block";
                         document.getElementById("hSharedBillingForm").action="<%=submitHrSharedBillingDetails%>";
                         return true;
                    } else {
                         return false;
                    }
                }
            }
            else
            {
                    alert("Please select at least one checkbox or add new resource to continue");
                    return false;
            }
        }
    }
    function addNewRow(input)
    {
        if(input == 'm')
        {
            counter = document.getElementById("managerSharedTablebBody").rows.length;
        }
        else if(input == 'f')
        {
            counter = document.getElementById("financeSharedTableBody").rows.length;
        }
        else
        {
            counter = document.getElementById("hrSharedTablebBody").rows.length;
        }
     	var cols="<tr>";
     	cols += '<td class="p-0 managerHrSharedColFixed1"><input type="hidden" value="" id="'+input+'SharedEcodeId'+counter+'"/>New</td>';
     	cols += '<td class="p-0 managerHrSharedColFixed2"><button type="button" onclick="removeRowAndResetIndex(this)" id="'+input+'Shared'+counter+'" class="btn-danger"><i class="far fa-trash-alt" style="cursor: pointer"></i></button></td>';
     	cols += '<td class="p-0 managerHrSharedColFixed3"><input type="text" class="form-control form-control-sm" required="required" maxlength="75" id="'+input+'SharedEcode'+counter+'" value="" name="<portlet:namespace/>'+input+'SharedEcode'+counter+'" onblur="fetchAndValidateEmployeeDetails(this)" /></td>';
        cols += '<td class="p-0 managerHrSharedColFixed4"><input type="text" class="form-control form-control-sm" required="required" readonly="readonly" id="'+input+'SharedName'+counter+'" /></td>';
        cols += '<td class="p-0 managerHrSharedExtraCol"><input type="text" class="form-control form-control-sm" value =""  id="'+input+'SharedManagerEcode'+counter+'" onblur="fetchAndValidateEmployeeDetails(this)" name="<portlet:namespace/>'+input+'SharedManagerEcode'+counter+'" required="required" /></td>';
        cols += '<td class="p-0 managerHrSharedExtraCol"><input type="text" class="form-control form-control-sm" required="required" readonly="readonly" id="'+input+'SharedManagerName'+counter+'" name="<portlet:namespace/>'+input+'SharedManagerName'+counter+'" /></td>';
        cols += '<td class="p-0 managerHrSharedExtraCol"><input type="text" class="form-control form-control-sm" value ="" id="'+input+'SharedCoordinatorEcode'+counter+'" onblur="fetchAndValidateEmployeeDetails(this)" name="<portlet:namespace/>'+input+'SharedCoordinatorEcode'+counter+'"  /></td>';
        cols += '<td class="p-0 managerHrSharedExtraCol"><input type="text" class="form-control form-control-sm" id="'+input+'SharedCoordinatorName'+counter+'" readonly="readonly" name="<portlet:namespace/>'+input+'SharedCoordinatorName'+counter+'"  /></td>';
        cols += '<td class="p-0 managerHrSharedExtraCol"><input type="text" class="form-control form-control-sm" value ="" id="'+input+'SharedleadEcode'+counter+'" onblur="fetchAndValidateEmployeeDetails(this)" name="<portlet:namespace/>'+input+'SharedleadEcode'+counter+'"  /></td>';
        cols += '<td class="p-0 managerHrSharedExtraCol"><input type="text" class="form-control form-control-sm" readonly="readonly" id="'+input+'SharedleadName'+counter+'" name="<portlet:namespace/>'+input+'SharedleadName'+counter+'"  /></td>';
        cols += '<td class="p-0 managerHrSharedExtraCol"><input type="text" class="form-control form-control-sm" readonly="readonly" id="'+input+'SharedDesignation'+counter+'" /></td>';
        cols += '<td class="p-0 managerHrSharedExtraCol"><input type="text" class ="form-control form-control-sm" readonly="readonly" id="'+input+'SharedDoj'+counter+'" /></td>';
        cols += '<td class="p-0 managerHrSharedExtraCol"><input type="text" class="form-control form-control-sm" readonly="readonly" id="'+input+'SharedExperience'+counter+'" /></td>';
        cols += '<td class="p-0 managerHrSharedExtraCol"><input type="text" class="form-control form-control-sm" readonly="readonly" id="'+input+'SharedSkill'+counter+'" /></td>';
        cols += '<td class="p-0 managerHrSharedExtraCol"><input type="text" class="form-control form-control-sm" value ="" id="'+input+'SharedAccount'+counter+'" onblur="$(this).val($(this).val().trim())" name="<portlet:namespace/>'+input+'SharedAccount'+counter+'" required="required" /></td>';
        cols += '<td class="p-0 managerHrSharedExtraCol"><input type="text" class="form-control form-control-sm" value ="" id="'+input+'SharedProject'+counter+'" onblur="$(this).val($(this).val().trim())" name="<portlet:namespace/>'+input+'SharedProject'+counter+'" required="required" /></td>';
        cols += '<td class="p-0 managerHrSharedExtraCol"><select class="mdb-select md-form form-control form-control-sm w-100" required="required"  id="'+input+'SharedCurrent'+counter+'" name="<portlet:namespace/>'+input+'SharedCurrent'+counter+'" onchange="return validateColumnsOnSelect(this,\'shared\');"><option value="" disabled selected>Select</option><option value="Bench">Bench</option><option value="Bench - Exit">Bench - Exit</option><option value="Bench - Maternity">Bench - Maternity</option><option value="Billing">Billing</option><option value="Business Readiness">Business Readiness</option><option value="Internal">Internal</option><option value="Internal - Exit">Internal - Exit</option><option value="Management">Management</option><option value="Overhead">Overhead</option><option value="Partial Billing">Partial Billing</option><option value="Sales & Marketing">Sales & Marketing</option><option value="Shadow">Shadow</option><option value="Shadow - Client Committed/24*7 Support">Shadow - Client Committed/24*7 Support</option><option value="Shadow - Exit">Shadow - Exit</option><option value="Shadow - Planned Billing">Shadow - Planned Billing</option><option value="Support">Support</option><option value="Trainee">Trainee</option><option value="Trainee - Planned Billing">Trainee - Planned Billing</option></select></td>';
        cols += '<td class="p-0 managerHrSharedExtraCol"><select class="mdb-select md-form form-control form-control-sm w-100" required="required"  id="'+input+'SharedCurrentPlusOne'+counter+'" name="<portlet:namespace/>'+input+'SharedCurrentPlusOne'+counter+'" onchange="return validateColumnsOnSelect(this,\'shared\');"><option value="" disabled selected>Select</option><option value="Bench">Bench</option><option value="Bench - Exit">Bench - Exit</option><option value="Bench - Maternity">Bench - Maternity</option><option value="Billing">Billing</option><option value="Business Readiness">Business Readiness</option><option value="Internal">Internal</option><option value="Internal - Exit">Internal - Exit</option><option value="Management">Management</option><option value="Overhead">Overhead</option><option value="Partial Billing">Partial Billing</option><option value="Sales & Marketing">Sales & Marketing</option><option value="Shadow">Shadow</option><option value="Shadow - Client Committed/24*7 Support">Shadow - Client Committed/24*7 Support</option><option value="Shadow - Exit">Shadow - Exit</option><option value="Shadow - Planned Billing">Shadow - Planned Billing</option><option value="Support">Support</option><option value="Trainee">Trainee</option><option value="Trainee - Planned Billing">Trainee - Planned Billing</option></select></td>';
        cols += '<td class="p-0 managerHrSharedExtraCol"><select class="mdb-select md-form form-control form-control-sm w-100" required="required"  id="'+input+'SharedCurrentPlusTwo'+counter+'" name="<portlet:namespace/>'+input+'SharedCurrentPlusTwo'+counter+'" onchange="return validateColumnsOnSelect(this,\'shared\');"><option value="" disabled selected>Select</option><option value="Bench">Bench</option><option value="Bench - Exit">Bench - Exit</option><option value="Bench - Maternity">Bench - Maternity</option><option value="Billing">Billing</option><option value="Business Readiness">Business Readiness</option><option value="Internal">Internal</option><option value="Internal - Exit">Internal - Exit</option><option value="Management">Management</option><option value="Overhead">Overhead</option><option value="Partial Billing">Partial Billing</option><option value="Sales & Marketing">Sales & Marketing</option><option value="Shadow">Shadow</option><option value="Shadow - Client Committed/24*7 Support">Shadow - Client Committed/24*7 Support</option><option value="Shadow - Exit">Shadow - Exit</option><option value="Shadow - Planned Billing">Shadow - Planned Billing</option><option value="Support">Support</option><option value="Trainee">Trainee</option><option value="Trainee - Planned Billing">Trainee - Planned Billing</option></select></td>';
        cols += '<td class="p-0 managerHrSharedExtraCol"><select class="mdb-select md-form form-control form-control-sm w-100" required="required" id="'+input+'SharedemployeeStatus'+counter+'" onchange="return validateColumnsOnSelect(this,\'shared\');" name="<portlet:namespace/>'+input+'SharedemployeeStatus'+counter+'"><option value="" disabled selected>Select</option><option value="Active">Active</option><option value="Resigned">Resigned</option><option value="Under Monitoring">Under Monitoring</option><option value="Maternity Leave">Maternity Leave</option><option value="Ramp up period">Ramp up period</option><option value="Sabbatical Leave">Sabbatical Leave</option><option value="Deferred Billing">Deferred Billing</option></select></td>';
        cols += '<td class="p-0 managerHrSharedExtraCol"><input type="date" style="cursor:pointer" onkeydown="return false;" class="form-control form-control-sm" readonly="readonly" id="'+input+'SharedlastWorkingDate'+counter+'" name="<portlet:namespace/>'+input+'SharedlastWorkingDate'+counter+'"/></td>';
        cols += '<td class="p-0 managerHrSharedExtraCol"><input type="date" style="cursor:pointer" onkeydown="return false;" class="form-control form-control-sm" readonly="readonly" id="'+input+'SharedbenchStartDate'+counter+'"  name="<portlet:namespace/>'+input+'SharedbenchStartDate'+counter+'" /></td>';
        cols += '<td class="p-0 managerHrSharedExtraCol"><select class="mdb-select md-form form-control form-control-sm w-100" id="'+input+'SharedShadowResourceType'+counter+'"  name="<portlet:namespace/>'+input+'SharedShadowResourceType'+counter+'"><option value="" disabled selected>Select</option><option value="Client Requested" style="display:none">Client Requested</option><option value="Strategic Hiring" style="display:none">Strategic Hiring</option><option value="Replacement" style="display:none">Replacement</option></select></td>';
        cols += '<td class="p-0 managerHrSharedExtraCol"><input type="date" style="cursor:pointer" onkeydown="return false;" class="form-control form-control-sm" readonly="readonly" id="'+input+'SharedshadowStartDate'+counter+'"  name="<portlet:namespace/>'+input+'SharedshadowStartDate'+counter+'" /></td>';
        cols += '<td class="p-0 managerHrSharedExtraCol"><input type="date" style="cursor:pointer" onkeydown="return false;" class="form-control form-control-sm" readonly="readonly" id="'+input+'SharedbillingStartDate'+counter+'"  name="<portlet:namespace/>'+input+'SharedbillingStartDate'+counter+'"/></td>';
        cols += '<td class="p-0 managerHrSharedExtraCol"><input type="date" style="cursor:pointer" onkeydown="return false;" class="form-control form-control-sm" readonly="readonly" id="'+input+'SharedbillingEndDate'+counter+'"  name="<portlet:namespace/>'+input+'SharedbillingEndDate'+counter+'"/></td>';
        cols += '<td class="p-0 managerHrSharedExtraCol"><input type="number" class="form-control form-control-sm" value ="100" max="100" min="0" id="'+input+'SharedpercentUtilization'+counter+'" name="<portlet:namespace/>'+input+'SharedpercentUtilization'+counter+'" /></td>';
        cols += '<td class="p-0 managerHrSharedExtraCol"><input type="text" class="form-control form-control-sm" value =""  id="'+input+'SharedRemarks'+counter+'" name="<portlet:namespace/>'+input+'SharedRemarks'+counter+'" /></td>';
        if(input == 'm')
        {
            cols += '<td class="p-0 managerHrSharedExtraCol"><input type="number" class="form-control form-control-sm" min = "0" step="0.01" style="background-color: #ADD8E6;" required="required" name="<portlet:namespace/>'+input+'SharedbillableHours'+counter+'" id="'+input+'SharedbillableHours'+counter+'" /></td>';
            cols += '<td class="p-0 managerHrSharedExtraCol"><input type="text" class="form-control form-control-sm" style="background-color: #ADD8E6;" name="<portlet:namespace/>'+input+'SharedEmployeeRole'+counter+'" id="'+input+'SharedEmployeeRole'+counter+'" /></td>';
            cols += '<td class="p-0 managerHrSharedExtraCol"><select class="mdb-select md-form form-control form-control-sm w-100" required="required"  id="'+input+'SharedVertical'+counter+'" name="<portlet:namespace/>'+input+'SharedVertical'+counter+'"><option value="" disabled selected>Select</option><option value="BBU">BBU</option><option value="Captive CoE">Captive CoE</option><option value="Cloud">Cloud</option><option value="Enterprise">Enterprise</option><option value="Other">Other</option><option value="Sales">Sales</option><option value="Support">Support</option></select></td></tr>';

            $("#managerSharedTablebBody").append(cols);
        }
        else if(input == 'f')
        {
            cols += '<td class="p-0 managerHrSharedExtraCol"><input type="number" class="form-control form-control-sm" min = "0" step="0.01" style="background-color: #ADD8E6;" required="required" name="<portlet:namespace/>'+input+'SharedbillableHours'+counter+'" id="'+input+'SharedbillableHours'+counter+'" /></td>';
            cols += '<td class="p-0 managerHrSharedExtraCol"><input type="number" class="form-control form-control-sm" min = "0" step="0.01" style="background-color: #ADD8E6;" required="required" name="<portlet:namespace/>'+input+'SharedMonthHours'+counter+'" id="'+input+'SharedMonthHours'+counter+'" /></td>';
            cols += '<td class="p-0 managerHrSharedExtraCol"><input type="text" class="form-control form-control-sm" style="background-color: #ADD8E6;" name="<portlet:namespace/>'+input+'SharedEmployeeRole'+counter+'" id="'+input+'SharedEmployeeRole'+counter+'" /></td>';
            cols += '<td class="p-0 managerHrSharedExtraCol"><select class="mdb-select md-form form-control form-control-sm w-100" required="required"  id="'+input+'SharedVertical'+counter+'" name="<portlet:namespace/>'+input+'SharedVertical'+counter+'"><option value="" disabled selected>Select</option><option value="BBU">BBU</option><option value="Captive CoE">Captive CoE</option><option value="Cloud">Cloud</option><option value="Enterprise">Enterprise</option><option value="Other">Other</option><option value="Sales">Sales</option><option value="Support">Support</option></select></td></tr>';
            $("#financeSharedTableBody").append(cols);
        }
        else
        {
            cols += '<td class="p-0 managerHrSharedExtraCol"><select class="mdb-select md-form form-control form-control-sm w-100" required="required" id="'+input+'SharedVertical'+counter+'" name="<portlet:namespace/>'+input+'SharedVertical'+counter+'"><option value="" disabled selected>Select</option><option value="BBU">BBU</option><option value="Captive CoE">Captive CoE</option><option value="Cloud">Cloud</option><option value="Enterprise">Enterprise</option><option value="Other">Other</option><option value="Sales">Sales</option><option value="Support">Support</option></select></td></tr>';
            $("#hrSharedTablebBody").append(cols);
        }

    }
    function removeRowAndResetIndex(obj){
        var rowId = $(obj).attr('id');
        var input = rowId.substring(0, 1);
        $(obj).closest("tr").remove();
        var sharedTableBody;
        if(input == "m")
        {
            sharedTableBody= "managerSharedTablebBody tr";
        }
        else if(input == "f")
        {
            sharedTableBody= "financeSharedTableBody tr";
        }
        else
        {
            sharedTableBody="hrSharedTablebBody tr";
        }
        var checkboxId = input+"SharedCheckbox"+index;
        var ecodeId= input+"SharedEcodeId"+index;
        var ecode = input+"SharedEcode"+index;
        var managerEcode = input+"SharedManagerEcode"+index;
        var managerName = input+"SharedManagerName"+index;
        var coordinatorEcode = input+"SharedCoordinatorEcode"+index;
        var coordinatorName = input+"SharedCoordinatorName"+index;
        var leadEcode = input+"SharedleadEcode"+index;
        var leadName = input+"SharedleadName"+index;
        var account = input+"SharedAccount"+index;
        var project = input+"SharedProject"+index;
        var current = input+"SharedCurrent"+index;
        var currentPlusOne = input+"SharedCurrentPlusOne"+index;
        var currentPlusTwo = input+"SharedCurrentPlusTwo"+index;
        var employeeStatus = input+"SharedemployeeStatus"+index;
        var lastWorkingDate = input+"SharedlastWorkingDate"+index;
        var benchStartDate = input+"SharedbenchStartDate"+index;
        var shadowResourceType = input+"SharedShadowResourceType"+index;
        var shadowStartDate = input+"SharedshadowStartDate"+index;
        var billingStartDate = input+"SharedbillingStartDate"+index;
        var billingEndDate = input+"SharedbillingEndDate"+index;
        var percentUtilization = input+"SharedpercentUtilization"+index;
        var remarks = input+"SharedRemarks"+index;
        var billingHours = input+"SharedbillableHours"+index;
        var employeeRole = input+"SharedEmployeeRole"+index;
        var vertical = input+"Vertical"+index;
        var monthHours =input+"SharedMonthHours"+index;

        $("#"+sharedTableBody).each(function(index){
                    $(this).find('td').eq(0).find('input').eq(1).attr('id',checkboxId);
                    $(this).find('td').eq(0).find('input').eq(1).attr('name',"<portlet:namespace />"+checkboxId);
                    $(this).find('td').eq(0).find('input').eq(0).attr('id',ecodeId);

                    $(this).find('td').eq(2).find('input').attr('name',"<portlet:namespace />"+ecode);

                    $(this).find('td').eq(4).find('input').eq(0).attr('id',"o"+managerEcode);
                    $(this).find('td').eq(4).find('input').eq(1).attr('id',managerEcode);
                    $(this).find('td').eq(4).find('input').eq(1).attr('name',"<portlet:namespace />"+managerEcode);

                    $(this).find('td').eq(5).find('input').eq(0).attr('id',"o"+managerName);
                    $(this).find('td').eq(5).find('input').eq(1).attr('id',managerName);
                    $(this).find('td').eq(5).find('input').eq(1).attr('name',"<portlet:namespace />"+managerName);

                    $(this).find('td').eq(6).find('input').eq(0).attr('id',"o"+coordinatorEcode);
                    $(this).find('td').eq(6).find('input').eq(1).attr('id',coordinatorEcode);
                    $(this).find('td').eq(6).find('input').eq(1).attr('name',"<portlet:namespace />"+coordinatorEcode);

                    $(this).find('td').eq(7).find('input').eq(0).attr('id',"o"+coordinatorName);
                    $(this).find('td').eq(7).find('input').eq(1).attr('id',coordinatorName);
                    $(this).find('td').eq(7).find('input').eq(1).attr('name',"<portlet:namespace />"+coordinatorName);

                    $(this).find('td').eq(8).find('input').eq(0).attr('id',"o"+leadEcode);
                    $(this).find('td').eq(8).find('input').eq(1).attr('id',leadEcode);
                    $(this).find('td').eq(8).find('input').eq(1).attr('name',"<portlet:namespace />"+leadEcode);

                    $(this).find('td').eq(9).find('input').eq(0).attr('id',"o"+leadName);
                    $(this).find('td').eq(9).find('input').eq(1).attr('id',leadName);
                    $(this).find('td').eq(9).find('input').eq(1).attr('name',"<portlet:namespace />"+leadName);

                    $(this).find('td').eq(14).find('input').eq(0).attr('id',"o"+account);
                    $(this).find('td').eq(14).find('input').eq(1).attr('id',account);
                    $(this).find('td').eq(14).find('input').eq(1).attr('name',"<portlet:namespace />"+account);

                    $(this).find('td').eq(15).find('input').eq(0).attr('id',"o"+project);
                    $(this).find('td').eq(15).find('input').eq(1).attr('id',project);
                    $(this).find('td').eq(15).find('input').eq(1).attr('name',"<portlet:namespace />"+project);

                    $(this).find('td').eq(16).find('input').eq(0).attr('id',"o"+current);
                    $(this).find('td').eq(16).find('select').eq(1).attr('id',current);
                    $(this).find('td').eq(16).find('select').eq(1).attr('name',"<portlet:namespace />"+current);

                    $(this).find('td').eq(17).find('input').eq(0).attr('id',"o"+currentPlusOne);
                    $(this).find('td').eq(17).find('select').eq(1).attr('id',currentPlusOne);
                    $(this).find('td').eq(17).find('select').eq(1).attr('name',"<portlet:namespace />"+currentPlusOne);

                    $(this).find('td').eq(18).find('input').eq(0).attr('id',"o"+currentPlusTwo);
                    $(this).find('td').eq(18).find('select').eq(1).attr('id',currentPlusTwo);
                    $(this).find('td').eq(18).find('select').eq(1).attr('name',"<portlet:namespace />"+currentPlusTwo);

                    $(this).find('td').eq(19).find('input').eq(0).attr('id',"o"+employeeStatus);
                    $(this).find('td').eq(19).find('select').eq(1).attr('id',employeeStatus);
                    $(this).find('td').eq(19).find('select').eq(1).attr('name',"<portlet:namespace />"+employeeStatus);

                    $(this).find('td').eq(20).find('input').eq(0).attr('id',"o"+lastWorkingDate);
                    $(this).find('td').eq(20).find('input').eq(1).attr('id',lastWorkingDate);
                    $(this).find('td').eq(20).find('input').eq(1).attr('name',"<portlet:namespace />"+lastWorkingDate);

                    $(this).find('td').eq(21).find('input').eq(0).attr('id',"o"+benchStartDate);
                    $(this).find('td').eq(21).find('input').eq(1).attr('id',benchStartDate);
                    $(this).find('td').eq(21).find('input').eq(1).attr('name',"<portlet:namespace />"+benchStartDate);

                    $(this).find('td').eq(22).find('input').eq(0).attr('id',"o"+shadowResourceType);
                    $(this).find('td').eq(22).find('select').eq(1).attr('id',shadowResourceType);
                    $(this).find('td').eq(22).find('select').eq(1).attr('name',"<portlet:namespace />"+shadowResourceType);

                    $(this).find('td').eq(23).find('input').eq(0).attr('id',"o"+shadowStartDate);
                    $(this).find('td').eq(23).find('input').eq(1).attr('id',shadowStartDate);
                    $(this).find('td').eq(23).find('input').eq(1).attr('name',"<portlet:namespace />"+shadowStartDate);

                    $(this).find('td').eq(24).find('input').eq(0).attr('id',"o"+billingStartDate);
                    $(this).find('td').eq(24).find('input').eq(1).attr('id',billingStartDate);
                    $(this).find('td').eq(24).find('input').eq(1).attr('name',"<portlet:namespace />"+billingStartDate);

                    $(this).find('td').eq(25).find('input').eq(0).attr('id',"o"+billingEndDate);
                    $(this).find('td').eq(25).find('input').eq(1).attr('id',billingEndDate);
                    $(this).find('td').eq(25).find('input').eq(1).attr('name',"<portlet:namespace />"+billingEndDate);

                    $(this).find('td').eq(26).find('input').eq(0).attr('id',"o"+percentUtilization);
                    $(this).find('td').eq(26).find('input').eq(1).attr('id',percentUtilization);
                    $(this).find('td').eq(26).find('input').eq(1).attr('name',"<portlet:namespace />"+percentUtilization);

                    $(this).find('td').eq(27).find('input').eq(0).attr('id',"o"+remarks);
                    $(this).find('td').eq(27).find('input').eq(1).attr('id',remarks);
                    $(this).find('td').eq(27).find('input').eq(1).attr('name',"<portlet:namespace />"+remarks);

                    if(input == "m")
                    {
                        $(this).find('td').eq(28).find('input').eq(0).attr('id',"o"+billingHours);
                        $(this).find('td').eq(28).find('input').eq(1).attr('id',billingHours);
                        $(this).find('td').eq(28).find('input').eq(1).attr('name',"<portlet:namespace />"+billingHours);

                        $(this).find('td').eq(29).find('input').eq(0).attr('id',"o"+employeeRole);
                        $(this).find('td').eq(29).find('input').eq(1).attr('id',employeeRole);
                        $(this).find('td').eq(29).find('input').eq(1).attr('name',"<portlet:namespace />"+employeeRole);

                        $(this).find('td').eq(30).find('input').eq(0).attr('id',"o"+vertical);
                        $(this).find('td').eq(30).find('select').eq(1).attr('id',vertical);
                        $(this).find('td').eq(30).find('select').eq(1).attr('name',"<portlet:namespace />"+vertical);
                    }
                    if(input == "f")
                    {
                        $(this).find('td').eq(28).find('input').eq(0).attr('id',"o"+billingHours);
                        $(this).find('td').eq(28).find('input').eq(1).attr('id',billingHours);
                        $(this).find('td').eq(28).find('input').eq(1).attr('name',"<portlet:namespace />"+billingHours);

                        $(this).find('td').eq(29).find('input').eq(0).attr('id',"o"+monthHours);
                        $(this).find('td').eq(29).find('input').eq(1).attr('id',monthHours);
                        $(this).find('td').eq(29).find('input').eq(1).attr('name',"<portlet:namespace />"+monthHours);

                        $(this).find('td').eq(30).find('input').eq(0).attr('id',"o"+employeeRole);
                        $(this).find('td').eq(30).find('input').eq(1).attr('id',employeeRole);
                        $(this).find('td').eq(30).find('input').eq(1).attr('name',"<portlet:namespace />"+employeeRole);

                        $(this).find('td').eq(31).find('input').eq(0).attr('id',"o"+vertical);
                        $(this).find('td').eq(31).find('select').eq(1).attr('id',vertical);
                        $(this).find('td').eq(31).find('select').eq(1).attr('name',"<portlet:namespace />"+vertical);
                    }
                    if(input == "h")
                    {
                        $(this).find('td').eq(28).find('input').eq(0).attr('id',"o"+vertical);
                        $(this).find('td').eq(28).find('select').eq(1).attr('id',vertical);
                        $(this).find('td').eq(28).find('select').eq(1).attr('name',"<portlet:namespace />"+vertical);
                    }
        });
    }

    function fetchAndValidateEmployeeDetails(obj)
    {
        var rowId = $(obj).attr('id');
        var input = rowId.substring(0, 1);
        var num = rowId.split(/(\d+)/)
        var index=num[1];
        var str=num[0];
        str= str.substring(1);
        var ecode = $(obj).val().trim();
        var empType;
        if(str.includes("Manager"))
        {
            empType="m";
        }
        else if(str.includes("Coordinator"))
        {
            empType = "c";
        }
        else if(str.includes("lead"))
        {
            empType = "l";
        }
        else
        {
            empType = "e";
        }
        if(ecode != "")
        {
            AUI().use('aui-io-request',function(A)
             {
                   A.io.request('${getEmployeeDetailsUrl}',{
                        method : 'post',
                        data : {
                            <portlet:namespace/>ecode : ecode,
                            <portlet:namespace/>empType : empType,
                        },
                        on : {
                            success : function()
                            {
                                var responseData = JSON.parse(this.get('responseData'));
                                if (responseData == null || responseData.data.employeeName == null)
                                {
                                    if(str=="SharedManagerEcode")
                                    {
                                         alert("Not a valid manager ecode");
                                         $('#'+input+'SharedManagerEcode'+index).val('');
                                         $('#'+input+'SharedManagerName'+index).val('');
                                    }
                                    else if(str=="SharedCoordinatorEcode")
                                    {
                                         alert("Not a valid coordinator ecode");
                                         $('#'+input+'SharedCoordinatorEcode'+index).val('');
                                         $('#'+input+'SharedCoordinatorName'+index).val('');
                                    }
                                    else if(str=="SharedleadEcode")
                                    {
                                         alert("Not a valid Lead ecode");
                                         $('#'+input+'SharedleadEcode'+index).val('');
                                         $('#'+input+'SharedleadName'+index).val('');
                                    }
                                    else if(str=="SharedEcode")
                                    {
                                        alert("Not a valid ecode");
                                        $('#'+input+'SharedEcode'+index).val('');
                                        $('#'+input+'SharedName'+index).val('');
                                        $('#'+input+'SharedDesignation'+index).val('');
                                        $('#'+input+'SharedDoj'+index).val('');
                                        $('#'+input+'SharedExperience'+index).val('');
                                        $('#'+input+'SharedSkill'+index).val('');
                                        $('#'+input+'SharedAccount'+index).val('');
                                        $('#'+input+'SharedProject'+index).val('');
                                    }
                                }
                                else
                                {
                                        if(str=="SharedManagerEcode")
                                        {
                                             $('#'+input+'SharedManagerEcode'+index).val(ecode);
                                             $('#'+input+'SharedManagerName'+index).val(responseData.data.employeeName);
                                        }
                                        if(str=="SharedCoordinatorEcode")
                                        {
                                              $('#'+input+'SharedCoordinatorEcode'+index).val(ecode);
                                              $('#'+input+'SharedCoordinatorName'+index).val(responseData.data.employeeName);
                                        }
                                        if(str=="SharedleadEcode")
                                        {
                                              $('#'+input+'SharedleadEcode'+index).val(ecode);
                                              $('#'+input+'SharedleadName'+index).val(responseData.data.employeeName);
                                        }
                                         else if(str=="SharedEcode")
                                        {
                                             $('#'+input+'SharedEcode'+index).val(ecode);
                                             $('#'+input+'SharedName'+index).val(responseData.data.employeeName);
                                             $('#'+input+'SharedDesignation'+index).val(responseData.data.employeeDesignation);
                                             $('#'+input+'SharedDoj'+index).val(responseData.data.doj);
                                             $('#'+input+'SharedExperience'+index).val(responseData.data.experience);
                                             $('#'+input+'SharedSkill'+index).val(responseData.data.skill);
                                        }
                                }
                            }
                        }
                   });
             });
        }
        else
        {
            if(str.includes("Manager"))
            {
                $('#'+input+'SharedManagerName'+index).val('');
            }
            else if(str.includes("Coordinator"))
            {
                $('#'+input+'SharedCoordinatorName'+index).val('');
            }
            else if(str.includes("lead"))
            {
                $('#'+input+'SharedleadName'+index).val('');
            }
        }
    }
    function downloadManual(type){
         AUI().use('aui-io-request', function(A){
             A.io.request(  '${downloadManualUrl}', {
                      method: 'post',
                     data : {
                            <portlet:namespace/>type : type
                     },
                     on: {
                            success: function() {
                                var headerData = JSON.parse(this.get('responseData'));
                                const blob =  convertBase64toBlob(headerData.data, 'application/pdf');
                                let tab = window.open();
                                tab.location.href =  URL.createObjectURL(blob);
                           }
                     }
             });
         });
    }

    function convertBase64toBlob(content, contentType)
	{
	   contentType = contentType || '';
	   var sliceSize = 512;
	   var byteCharacters = window.atob(content); //method which converts base64 to binary
	   var byteArrays = [
	   ];
	   for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {
	     var slice = byteCharacters.slice(offset, offset + sliceSize);
	     var byteNumbers = new Array(slice.length);
	     for (var i = 0; i < slice.length; i++) {
	       byteNumbers[i] = slice.charCodeAt(i);
	     }
	     var byteArray = new Uint8Array(byteNumbers);
	     byteArrays.push(byteArray);
	   }
	   var blob = new Blob(byteArrays, {
	     type: contentType
	   }); //statement which creates the blob
	   return blob;
	}
    function getSkillData(ecode,obj)
    {
         $(obj).removeAttr("onclick");
         $("#skillTable").empty();
         $("#certificateTable").empty();
         AUI().use('aui-io-request', function(A)
         {
             A.io.request('${employeeSkillUrl}', {
                 method: 'post',
                 data: {
                     <portlet:namespace/>ecode: ecode,
                 },
                 on: {
                 	success: function() {
                 		var employeeData = JSON.parse(this.get('responseData'));
                 		var tableRowSkill = "";
                 		var tableRowCertificate = "";
                 		document.getElementById("employeeName").value=employeeData.data.employeeName;
                        document.getElementById("employeeAccount").value=employeeData.data.account;
                        document.getElementById("employeeEcode").value=employeeData.data.employeeCode;
                        if(employeeData.data.skills.length > 0)
                        {
                                for(i=0;i<employeeData.data.skills.length;i++){
 									tableRowSkill += '<tr><td style="border-color:black;">'+employeeData.data.skills[i].coreSkill+'</td ><td style="border-color:black;">'+employeeData.data.skills[i].subSkill+'</td><td style="border-color:black;">'+employeeData.data.skills[i].typeStr+'</td><td style="border-color:black;">'+employeeData.data.skills[i].proficiencyLevelStr+'</td><td style="border-color:black;">'+employeeData.data.skills[i].tool+'</td></tr>';
 								}
 						}
 						else
 						{
 						    tableRowSkill += '<tr><td colspan="5" style="border-color:black;text-align:center"> No skills added yet </td></tr>';
 						}
                        if(employeeData.data.certificates.length > 0)
                        {
                                for(i=0;i<employeeData.data.certificates.length;i++){
 									tableRowCertificate += '<tr><td style="border-color:black;">'+employeeData.data.certificates[i].certificateCategory+'</td ><td style="border-color:black;">'+employeeData.data.certificates[i].certificateName+'</td></tr>';
 								}
 						}
 						else
 						{
 						    tableRowCertificate += '<tr><td colspan="2" style="border-color:black;text-align:center"> No certificates added yet </td></tr>';
 						}
 						$("#skillTable").append(tableRowSkill);
                        $("#lineItemsSkillTable").show();
                        $("#certificateTable").append(tableRowCertificate);
                        $("#lineItemsCertificateTable").show();
                        modal.style.display = "block";
                        $(obj).attr("onClick","getSkillData('"+ecode+"', this)");
                    }
                 }
             });
         });
    }
</script>

