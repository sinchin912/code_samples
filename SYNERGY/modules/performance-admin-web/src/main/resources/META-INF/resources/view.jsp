<%@ include file="/init.jsp"%>
<portlet:actionURL name="kpiWorkflow" var="kpiWorkflowUrl"></portlet:actionURL>
<portlet:resourceURL var="downloadUserManualUrl" id="downloadUserManual"></portlet:resourceURL>
<portlet:actionURL name="reviewWorkflow" var="reviewWorkflowUrl"></portlet:actionURL>
<portlet:actionURL name="closeReviewForm" var="closeReviewFormUrl"></portlet:actionURL>
<portlet:actionURL name="allowSelfReview" var="allowSelfReviewUrl"></portlet:actionURL>
<portlet:actionURL name="submitRollbackRequest" var="submitRollbackRequestUrl"></portlet:actionURL>
<portlet:resourceURL id="getRollbackRequestStatusEntries" var="getRollbackRequestStatusEntriesURL"></portlet:resourceURL>
<portlet:actionURL name="submitCloseRequest" var="submitCloseRequestUrl"></portlet:actionURL>
<portlet:actionURL name="submitActionOnCloseByHR" var="submitActionOnCloseByHRUrl"></portlet:actionURL>
<portlet:actionURL name="submitActionOnRollbackByHR" var="submitActionOnRollbackByHRUrl"></portlet:actionURL>
<portlet:actionURL name="updateFinalReviewTimeline" var="updateFinalReviewTimelineUrl"></portlet:actionURL>
<portlet:actionURL name="updateMidReviewTimeline" var="updateMidReviewTimelineUrl"></portlet:actionURL>
<portlet:resourceURL id="downloadReviewReport" var="downloadReviewReportUrl"></portlet:resourceURL>
<portlet:resourceURL id="downloadKpiReport" var="downloadKpiReportUrl"></portlet:resourceURL>
<portlet:resourceURL id="getCloseRequestStatusEntries" var="getCloseRequestStatusEntriesURL"></portlet:resourceURL>
<portlet:resourceURL id="getEmployeeEntriesByHr" var="getEmployeeEntriesByHrURL"></portlet:resourceURL>
<portlet:resourceURL id="getManagerYearlyEntries" var="getManagerYearlyEntriesURL"></portlet:resourceURL>
<portlet:resourceURL id="downloadHrYearWiseReport" var="downloadHrYearWiseReportURL">
</portlet:resourceURL>
<portlet:resourceURL id="getHRYearlyEntriesURL" var="getHRYearlyEntriesURL"></portlet:resourceURL>
<div class="container">
    <input type="hidden" id="reviewWorkflowResourceUrl" value="${reviewWorkflowUrl}"/>
    <input type="hidden" id="getRollbackRequestStatusEntriesResourceUrl" value="${getRollbackRequestStatusEntriesURL}"/>
    <input type="hidden" id="getCloseRequestStatusEntriesResourceUrl" value="${getCloseRequestStatusEntriesURL}"/>
    <input type="hidden" id="portletNamespace"   value="<portlet:namespace/>" />
    <input type="hidden" id="getEmployeeEntriesByHrResourceUrl" value="${getEmployeeEntriesByHrURL}"/>
    <input type="hidden" id="closeReviewFormResourceUrl" value="${closeReviewFormUrl}"/>
    <input type="hidden" id="allowSelfReviewResourceUrl" value="${allowSelfReviewUrl}"/>
    <input type="hidden" id="getManagerYearlyEntriesResourceUrl" value="${getManagerYearlyEntriesURL}"/>
    <input type="hidden" id="getHRYearlyEntriesResourceUrl" value="${getHRYearlyEntriesURL}"/>
    <input type="hidden" id="financialYearStart" value="${financialYearStart}"/>
    <input type="hidden" id="financialMidYear" value="${financialMidYear}"/>
    <input type="hidden" id="financialNextMidDateYear" value="${financialNextMidDateYear}"/>
    <input type="hidden" id="financialYearEnd" value="${financialYearEnd}"/>
    <input type="hidden" id="finalReviewAnalyticsMap" value='${finalReviewAnalyticsMap}'/>
    <input type="hidden" id="finalReviewSecondaryAnalyticsMap" value='${finalReviewSecondaryAnalyticsMap}'/>
    <input type="hidden" id="midReviewAnalyticsMap" value='${midReviewAnalyticsMap}'/>
    <input type="hidden" id="midReviewSecondaryAnalyticsMap" value='${midReviewSecondaryAnalyticsMap}'/>
    <input type="hidden" id="midDates" value='${midDates}'/>
    <input type="hidden" id="finalDates" value='${finalDates}'/>
    <input type="hidden" id="downloadUserManualResourceUrl" value="${downloadUserManualUrl}"/>
    <ul class="nav nav-tabs nav-justified mb-3"  role="tablist">
        <li class="nav-item" id="leadRole" style="display:none" ><a class="nav-link" id="first-tab" data-toggle="tab" href="#first" role="tab" aria-controls="first" aria-selected="true" >Reviewer</a></li>
        <li class="nav-item" id="managerRole" style="display:none"><a class="nav-link" id="second-tab" data-toggle="tab" href="#second" role="tab" aria-controls="second" aria-selected="false" >Manager</a></li>
        <li class="nav-item" id="hrRole" style="display:none"><a class="nav-link" id="third-tab" data-toggle="tab" href="#third" role="tab" aria-controls="third" aria-selected="false" >HR</a></li>
        <li class="nav-item" id="performanceRole" style="display:none"><a class="nav-link" id="fourth-tab" data-toggle="tab" href="#fourth" role="tab" aria-controls="fourth" aria-selected="false" >Performance Admin</a></li>
    </ul>
    <div class="tab-content">
        <div class="tab-pane fade" id="first" role="tabpanel" aria-labelledby="first-tab">
            <div id="reviewerDiv">
                <hr />
                <ul class="nav nav-tabs nav-justified mb-3"  role="tablist">
                    <li class="nav-item" ><a class="nav-link active" id="reviewer-first-tab" data-toggle="tab" href="#reviewerFirst" role="tab" aria-controls="managerFirst" aria-selected="true" >KPIs</a></li>
                    <li class="nav-item" ><a class="nav-link" id="reviewer-second-tab" data-toggle="tab" href="#reviewerSecond" role="tab" aria-controls="managerSecond" aria-selected="false" >Reviews</a></li>
                </ul>
                <hr />
                <div class="tab-content">
                    <div class="tab-pane fade show active" id="reviewerFirst" role="tabpanel" aria-labelledby="reviewer-first-tab">
                        <div class="card">
                            <div class="card-header">
                                <h5>KPIs Assigned To Me</h5>
                            </div>
                            <div class="card-body" >
                                <table class="table  table-sm  table-bordered table-striped table-hover " >
                                    <thead class="thead-dark">
                                        <tr>
                                            <th class="text-center align-middle" width="10%">Ecode</th>
                                            <th class="text-center align-middle" width="20%">Employee Name</th>
                                            <th class="text-center align-middle" width="20%">Account</th>
                                            <th class="text-center align-middle" width="20%">Type</th>
                                            <th class="text-center align-middle" width="20%">Status</th>
                                            <th class="text-center align-middle" width="10%">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:if test="${leadEmployeeKpiList.size() > 0}">
                                            <c:forEach items="${leadEmployeeKpiList}" var="item"
                                                varStatus="count">
                                                <tr>
                                                    <td class="text-center align-middle">${item.ecode}</td>
                                                    <td class="text-center align-middle">${item.employeeName}</td>
                                                    <td class="text-center align-middle">${item.accountName}</td>
                                                    <td class="text-center align-middle">
                                                        <c:choose>
                                                            <c:when test="${(item.accountPrimary=='true')}">Primary
                                                            </c:when>
                                                            <c:otherwise>Secondary
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td class="text-center align-middle">
                                                        <c:choose>
                                                            <c:when test="${(item.kpiSettingStatus=='true')}">Complete
                                                            </c:when>
                                                            <c:otherwise>
                                                                <font color="red">Pending </font>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td class="text-center align-middle">
                                                        <c:if test="${(item.kpiSettingStatus=='true')}">
                                                            <form action="${kpiWorkflowUrl}" method="post"
                                                                style="display: inline">
                                                                <input type="hidden" value="Trantor_Teamlead"
                                                                    name="<portlet:namespace/>role" />
                                                                <input type="hidden" value="${item.kpiId}"
                                                                    name="<portlet:namespace/>kpiId" />
                                                                <button type="submit" style="cursor: pointer;"
                                                                    title="Go to View Form" class=" btn btn-outline-primary btn-sm">
                                                                View
                                                                </button>
                                                            </form>
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </c:if>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="reviewerSecond" role="tabpanel" aria-labelledby="reviewer-second-tab">
                           <div class="card">
                                <div class="card-header">
                                    <div class="row">
                                        <div class="col pt-2">
                                            <h5>Reviews Assigned To Me</h5>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <table class="table table-sm table-bordered table-striped table-hover table-custom" id="leadTable">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th class="text-center align-middle" width="10%">Ecode</th>
                                                <th class="text-center align-middle" width="20%">Employee Name</th>
                                                <th class="text-center align-middle" width="20%">Account</th>
                                                <th class="text-center align-middle" width="20%">Review Type</th>
                                                <th class="text-center align-middle" width="20%">Account Type</th>
                                                <th class="text-center align-middle" width="10%">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${actionReviews}" var="item" varStatus="count">
                                                <tr>
                                                    <td class="text-center align-middle">${item.employeeCode}</td>
                                                    <td class="text-center align-middle">${item.employeeName}</td>
                                                    <td class="text-center align-middle">${item.account}</td>
                                                    <td class="text-center align-middle">${item.reviewType}</td>
                                                    <td class="text-center align-middle">${item.accountType}</td>
                                                    <td class="text-center align-middle">
                                                        <form action="${reviewWorkflowUrl}" method="post" style="display: inline">
                                                            <input type="hidden" value="${item.reviewId}"
                                                                            name="<portlet:namespace/>reviewId" />
                                                            <input type="hidden" value="Trantor_Teamlead"
                                                                            name="<portlet:namespace/>role" />
                                                            <button type="submit" class=" btn btn-primary btn-sm" title="Go to Review Form"
                                                                            style="cursor: pointer;"><i class="fas fa-tasks"></i></button>
                                                        </form>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                    </div>
                </div>
            </div>
           <div class="card">
            <div class="card-header">
               <div class="row">
                  <div class="col">
                     <h5>User Manual</h5>
                  </div>
                  <div class="col-3 text-right">
                     <button class="btn btn-outline-secondary" onClick="downloadUserManual(1);">Download
                     </button>
                  </div>
               </div>
            </div>
         </div>
        </div>
        <div class="tab-pane fade" id="second" role="tabpanel" aria-labelledby="second-tab">
            <div id="managerDiv">
                <hr />
                <ul class="nav nav-tabs nav-justified mb-3"  role="tablist">
                    <li class="nav-item" ><a class="nav-link active" id="manager-first-tab" data-toggle="tab" href="#managerFirst" role="tab" aria-controls="managerFirst" aria-selected="true" >KPIs</a></li>
                    <li class="nav-item" ><a class="nav-link" id="manager-second-tab" data-toggle="tab" href="#managerSecond" role="tab" aria-controls="managerSecond" aria-selected="false" >Reviews</a></li>
                    <li class="nav-item" ><a class="nav-link" id="manager-third-tab" data-toggle="tab" href="#managerThird" role="tab" aria-controls="managerThird" aria-selected="false" >Manage Reviews</a></li>
                </ul>
                <hr />
                <div class="tab-content">
                    <div class="tab-pane fade show active" id="managerFirst" role="tabpanel" aria-labelledby="manager-first-tab">
                        <div class="card">
                            <div class="card-header">
                                <h5>KPIs Assigned To Me</h5>
                            </div>
                            <div class="card-body" >
                                <table class="table  table-sm  table-bordered table-striped table-hover " id="managerKpiTable" >
                                    <thead class="thead-dark">
                                        <tr>
                                            <th class="text-center align-middle" width="10%">Ecode</th>
                                            <th class="text-center align-middle" width="20%">Employee Name</th>
                                            <th class="text-center align-middle" width="20%">Account</th>
                                            <th class="text-center align-middle" width="20%">Type</th>
                                            <th class="text-center align-middle" width="20%">Status</th>
                                            <th class="text-center align-middle" width="10%">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:if test="${managerEmployeeKpiList.size() > 0}">
                                            <c:forEach items="${managerEmployeeKpiList}" var="item"
                                                varStatus="count">
                                                <tr>
                                                    <td class="text-center align-middle">${item.ecode}</td>
                                                    <td class="text-center align-middle">${item.employeeName}</td>
                                                    <td class="text-center align-middle">${item.accountName}</td>
                                                    <td class="text-center align-middle">
                                                        <c:choose>
                                                            <c:when test="${(item.accountPrimary=='true')}">Primary
                                                            </c:when>
                                                            <c:otherwise>Secondary
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td class="text-center align-middle">
                                                        <c:choose>
                                                            <c:when test="${(item.kpiSettingStatus=='true')}">Complete
                                                            </c:when>
                                                            <c:otherwise>
                                                                <font color="red">Pending </font>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td class="text-center align-middle">
                                                        <c:if test="${(item.kpiSettingStatus=='true')}">
                                                            <form action="${kpiWorkflowUrl}" method="post"
                                                                style="display: inline">
                                                                <input type="hidden" value="Trantor_Manager"
                                                                    name="<portlet:namespace/>role" />
                                                                <input type="hidden" value="${item.kpiId}"
                                                                    name="<portlet:namespace/>kpiId" />
                                                                <button type="submit" style="cursor: pointer;"
                                                                    title="Go to View Form" class=" btn btn-outline-primary btn-sm">
                                                                View
                                                                </button>
                                                            </form>
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </c:if>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="managerSecond" role="tabpanel" aria-labelledby="manager-second-tab">
                        <!-- Manager TAB -->
                        <div class="card">
                            <div class="card-header">
                                <div class="row">
                                    <div class="col pt-2">
                                        <h5>Current Year Reviews</h5>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <table id="managerCurrentTable"
                                    class="table table-sm table-bordered table-striped table-hover ">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th class="text-center align-middle" width="20%">Ecode</th>
                                            <th class="text-center align-middle" width="20%">Employee Name</th>
                                            <th class="text-center align-middle" width="20%">Current Manager</th>
                                            <th class="text-center align-middle" width="20%">Overview</th>
                                            <th class="text-center align-middle" width="20%">Review Forms</th>
                                        </tr>
                                    </thead>
                                    <tbody id="accordion">
                                        <c:forEach items="${currentYearMap}" var="item"
                                            varStatus="count">
                                            <tr>
                                                <td class="text-center align-middle">${item.key.employeeCode}</td>
                                                <td class="text-center align-middle">${item.key.employeeName}</td>
                                                <td class="text-center align-middle">${item.key.managerName}</td>
                                                <td class="text-center align-middle">${item.key.overview}</td>
                                                <td class="text-center align-middle"><button id="${item.key.employeeCode}-${item.key.employeeName}"
                                                    class="btn btn-link btn-sm collapsed"
                                                    data-toggle="collapse" data-target="#rowId${count.index}" aria-expanded="false" aria-controls="rowId${count.index}"
                                                    type="button">Review Form</button></td>
                                            </tr>
                                            <tr data-parent="#accordion" id="rowId${count.index}" class="collapse" aria-labelledby="${item.key.employeeCode}-${item.key.employeeName}">
                                                <td colspan="5">
                                                    <div class="mt-4">
                                                        <div class="row">
                                                            <div class="col pt-2 text-center">
                                                                <h4>${item.key.employeeCode}-${item.key.employeeName}</h4>
                                                            </div>
                                                        </div>
                                                        <div class="card-body pt-0">
                                                            <table class="table  table-sm  table-bordered table-striped table-hover ">
                                                                <thead>
                                                                    <tr>
                                                                        <th width="17%" class="bg-info text-white">Account</th>
                                                                        <th width="16%" class="bg-info text-white">Manager Email</th>
                                                                        <th width="17%" class="bg-info text-white">Review
                                                                            State
                                                                        </th>
                                                                        <th width="20%" class="bg-info text-white">Review
                                                                            Type
                                                                        </th>
                                                                        <th width="18%" class="bg-info text-white">Account Type</th>
                                                                        <th width="17%" class="bg-info text-white">Action</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <c:forEach items="${item.value}" var="reviewDetails">
                                                                        <tr>
                                                                            <td>${reviewDetails.account}</td>
                                                                            <td>${reviewDetails.manager}</td>
                                                                            <td>
                                                                                <c:choose>
                                                                                    <c:when test="${(reviewDetails.finalYearReview=='true')}">
                                                                                        <c:out value="${reviewStateMapAnnual[reviewDetails.reviewState]}" />
                                                                                    </c:when>
                                                                                    <c:otherwise>
                                                                                        <c:out value="${reviewStateMapMidYear[reviewDetails.reviewState]}" />
                                                                                    </c:otherwise>
                                                                                </c:choose>
                                                                            </td>
                                                                            <td>
                                                                                <c:choose>
                                                                                    <c:when
                                                                                        test="${(reviewDetails.finalYearReview=='true')}">Annual
                                                                                    </c:when>
                                                                                    <c:otherwise>Mid Year
                                                                                    </c:otherwise>
                                                                                </c:choose>
                                                                            </td>
                                                                            <td>
                                                                                <c:choose>
                                                                                    <c:when
                                                                                        test="${(reviewDetails.accountPrimary=='true')}">Primary
                                                                                    </c:when>
                                                                                    <c:otherwise>Secondary
                                                                                    </c:otherwise>
                                                                                </c:choose>
                                                                            </td>
                                                                            <td>
                                                                                <form action="${reviewWorkflowUrl}" method="post" style="display: inline">
                                                                                    <input type="hidden" value="${reviewDetails.reviewId}"
                                                                                        name="<portlet:namespace/>reviewId" />
                                                                                    <input type="hidden" value="Trantor_Manager"
                                                                                        name="<portlet:namespace/>role" />
                                                                                    <c:set var="status" scope="session"
                                                                                        value="${(reviewDetails.reviewState == 2) &&  (reviewDetails.manager.toUpperCase() == loggedManagerEmail.toUpperCase())}" />
                                                                                    <button type="submit"  class="btn btn-outline-primary btn-sm" title="Go to Review Form"
                                                                                        style="cursor: pointer;">${(status == 'true') ? ' Action' : 'View'}</button>
                                                                                </form>
                                                                            </td>
                                                                        </tr>
                                                                    </c:forEach>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="managerThird" role="tabpanel" aria-labelledby="manager-third-tab">
                        <div class="card">
                            <div class="card-header">
                                <div class="row">
                                    <div class="col pt-2">
                                        <h5>Send Rollback Request to HR</h5>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <p>Request HR for Rollback to "Manager Review Stage"</p>
                                <c:if test="${allRollbackReviewForManagerToRequest.size() > 0}">
                                    <div>
                                        <form action="${submitRollbackRequestUrl}" id="rollbackRequestForm"  method="post" onsubmit="return submitRollbackRequest()" >
                                            <table  class="table table-sm table-bordered table-responsive-md table-striped text-center" id="managerRollbackTable" >
                                                <thead class="thead-dark">
                                                    <tr>
                                                        <th class="text-center align-middle">Select</th>
                                                        <th class="text-center align-middle">Ecode</th>
                                                        <th class="text-center align-middle">Employee Name</th>
                                                        <th class="text-center align-middle">Account Name</th>
                                                        <th class="text-center align-middle">Review Type</th>
                                                        <th class="text-center align-middle">Account Type</th>
                                                        <th class="text-center align-middle">Stage</th>
                                                        <th class="text-center align-middle">Review Year</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${allRollbackReviewForManagerToRequest}" var="item" varStatus="count" >
                                                        <tr>
                                                            <td>
                                                                <input type="checkbox" value="${item.reviewId}"
                                                                    id="managerCheckbox${item.reviewId}" name="<portlet:namespace/>managerSelected" />
                                                            </td>
                                                            <td>${item.employeeCode}</td>
                                                            <td>${item.employeeName}</td>
                                                            <td>${item.account}</td>
                                                            <td>${item.reviewType}</td>
                                                            <td>${item.accountType}</td>
                                                            <td>${item.reviewStateName}</td>
                                                            <td>${item.reviewStartDate}</td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                            <div class="row mt-4"  id="btnSubmitRollbackRequest">
                                                <div class="col text-center">
                                                    <button type="submit" class=" btn btn-primary ml-5" >Request Rollback to HR</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </c:if>
                                <hr>
                                <div>
                                    <div class="row">
                                        <div class="col-md-4 mt-1 text-left">
                                            <strong><label>View Requested Rollback Status</label></strong>
                                        </div>
                                        <div class="col-md-4">
                                            <select class="mdb-select md-form form-control form-control-sm" id="rollbackRequestSearch" onchange="getRollbackRequestStatus()" >
                                                <option value="" selected >Select Option</option>
                                                <option value="0" >Pending</option>
                                                <option value="1" >Approved</option>
                                                <option value="2" >Rejected</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div id="rollbackCardSearchTableDiv">
                                        <table  class="table table-sm table-bordered  table-striped text-center" id="rollbackSearchTable" style="display:none">
                                            <thead class="thead-dark">
                                                <tr>
                                                    <th class="text-center align-middle">Ecode</th>
                                                    <th class="text-center align-middle">Employee Name</th>
                                                    <th class="text-center align-middle">Account Name</th>
                                                    <th class="text-center align-middle">Review Type</th>
                                                    <th class="text-center align-middle">Account Type</th>
                                                    <th class="text-center align-middle">Stage</th>
                                                    <th class="text-center align-middle">Review Year</th>
                                                    <th class="text-center align-middle">Status</th>
                                                </tr>
                                            </thead>
                                            <tbody id="rollbackSearchTableBody">
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card">
                            <div class="card-header">
                                <div class="row">
                                    <div class="col pt-2">
                                        <h5>Send Review Form Closure Request to HR</h5>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <p>Request HR to Close Review Form</p>
                                <c:if test="${allCloseReviewForManagerToRequest.size() > 0}">
                                    <div id="closeTableDiv">
                                        <form action="${submitCloseRequestUrl}" id="closeRequestForm"  method="post" onsubmit="return submitCloseRequest()" >
                                            <table  class="table table-sm table-bordered table-responsive-md table-striped text-center" id="managerCloseTable" >
                                                <thead class="thead-dark">
                                                    <tr>
                                                        <th class="text-center align-middle">Select</th>
                                                        <th class="text-center align-middle">Ecode</th>
                                                        <th class="text-center align-middle">Employee Name</th>
                                                        <th class="text-center align-middle">Account Name</th>
                                                        <th class="text-center align-middle">Review Type</th>
                                                        <th class="text-center align-middle">Account Type</th>
                                                        <th class="text-center align-middle">Stage</th>
                                                        <th class="text-center align-middle">Review Year</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${allCloseReviewForManagerToRequest}" var="item" varStatus="count" >
                                                        <tr>
                                                            <td>
                                                                <input type="checkbox" value="${item.reviewId}"
                                                                    id="managerCheckboxClose${item.reviewId}" name="<portlet:namespace/>managerSelectedClose" />
                                                            </td>
                                                            <td>${item.employeeCode}</td>
                                                            <td>${item.employeeName}</td>
                                                            <td>${item.account}</td>
                                                            <td>${item.reviewType}</td>
                                                            <td>${item.accountType}</td>
                                                            <td>${item.reviewStateName}</td>
                                                            <td>${item.reviewStartDate}</td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                            <div class="custom-control custom-checkbox mt-3">
                                                <input type="checkbox" class="custom-control-input" required="required"  oninvalid="this.setCustomValidity('Kindly acknowledge this to proceed')" oninput="this.setCustomValidity('')"
                                                    id="terms" /> <label  for="terms"
                                                    class="custom-control-label pl-2" style="font-size:0.8em;">
                                                I acknowledge that it has been discussed with employee.</label>
                                            </div>
                                            <div class="row mt-4"  id="btnSubmitCloseRequest">
                                                <div class="col text-center">
                                                    <button type="submit" class=" btn btn-primary ml-5" >Request HR for Form Closure</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </c:if>
                                <hr>
                                <div>
                                    <div class="row">
                                        <div class="col-md-4 mt-1 text-left">
                                            <strong><label>View Status of Form Closure Request</label></strong>
                                        </div>
                                        <div class="col-md-4">
                                            <select class="mdb-select md-form form-control form-control-sm" id="closeRequestSearch" onchange="getCloseRequestStatus()" >
                                                <option value="" selected >Select Option</option>
                                                <option value="0" >Pending</option>
                                                <option value="1" >Approved</option>
                                                <option value="2" >Rejected</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div id="closeCardSearchTableDiv">
                                        <table  class="table table-sm table-bordered table-responsive-md table-striped text-center" id="closeSearchTable" style="display:none" >
                                            <thead class="thead-dark">
                                                <tr>
                                                    <th class="text-center align-middle">Ecode</th>
                                                    <th class="text-center align-middle">Employee Name</th>
                                                    <th class="text-center align-middle">Account Name</th>
                                                    <th class="text-center align-middle">Review Type</th>
                                                    <th class="text-center align-middle">Account Type</th>
                                                    <th class="text-center align-middle">Stage</th>
                                                    <th class="text-center align-middle">Review Year</th>
                                                    <th class="text-center align-middle">Status</th>
                                                </tr>
                                            </thead>
                                            <tbody id="closeSearchTableBody">
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card">
                            <div class="card-header">
                                <div class="row">
                                    <div class="col pt-2">
                                        <h5>Glance ${displayReviewPeriod}</h5>
                                    </div>
                                    <div class="col-2 pt-2">
                                        <select class="mdb-select md-form form-control form-control-sm" id="managerSearchYear" onchange="filterManagerSearchTable()" >
                                            <option value="" selected >Select Option</option>
                                            <option value="F" >Annual</option>
                                            <option value="M" >Mid Year</option>
                                        </select>
                                    </div>
                                    <div class="col-2 pt-2">
                                        <select class="mdb-select md-form form-control form-control-sm" id="managerSearchType" onchange="filterManagerSearchTable()" >
                                            <option value="" selected >Select Option</option>
                                            <option value="P" >Primary</option>
                                            <option value="S" >Secondary</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body" id="managerSearchTableDiv"  style="display:none">
                                <table class="table  table-sm  table-bordered table-striped table-hover " id="managerSearchTable">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th>Ecode</th>
                                            <th>Employee Name</th>
                                            <th>Manager Name</th>
                                            <th>Assignee Name</th>
                                            <th>Review State</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody id="managerSearchTableBody">
                                        <c:forEach items="${allCurrentYearPrimaryMReview}" var="searchEntry" varStatus="count" >
                                            <tr class="M${searchEntry.rating}${searchEntry.reviewType}">
                                                <td>${searchEntry.employeeCode}</td>
                                                <td>${searchEntry.employeeName}</td>
                                                <td>${searchEntry.managerName}</td>
                                                <td>${searchEntry.reviewerName}</td>
                                                <td>${searchEntry.reviewStateName}</td>
                                                <td>
                                                    <c:if test="${(searchEntry.reviewState > 1)}">
                                                        <form action="${reviewWorkflowUrl}" method="post" style="display: inline">
                                                            <input type="hidden" value="${searchEntry.reviewId}"
                                                                name="<portlet:namespace/>reviewId" />
                                                            <input type="hidden" value="trantor_Manager"
                                                                name="<portlet:namespace/>role" />
                                                            <c:set var="status" scope="session"
                                                                value="${(searchEntry.reviewState == 2) &&  (searchEntry.managerEmail.toUpperCase() == loggedManagerEmail.toUpperCase())}" />
                                                            <button type="submit"  class="btn   btn-sm btn-outline-primary " title="Go to Review Form"
                                                                style="cursor: pointer;">${(status == 'true') ? ' Action' : 'View'}</button>
                                                        </form>
                                                    </c:if>
                                                </td>
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
                                        <h5>Previous Years Reviews</h5>
                                    </div>
                                    <div class="col-3 pt-2 float-right">
                                        <select class="mdb-select md-form form-control form-control-sm" onchange="getManagerYearlyEntries(1)" id="ayForManagerEntries">
                                            <option value="" selected disabled>Select Review Year</option>
                                            <c:forEach items="${yearList}" var="item" varStatus="count">
                                                <option value="${item.key}"  >${item.value}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body" id="ayForManagerEntriesDiv" style="display:none">
                                <table  class="table table-sm table-bordered table-responsive-md table-striped text-center" id="ayForManagerEntriesTable" >
                                    <thead class="thead-dark">
                                        <tr>
                                            <th class="text-center align-middle">Ecode</th>
                                            <th class="text-center align-middle">Employee Name</th>
                                            <th class="text-center align-middle">Review Type</th>
                                            <th class="text-center align-middle">Account Type</th>
                                            <th class="text-center align-middle">Status</th>
                                            <th class="text-center align-middle">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody id="ayForManagerEntriesTableBody">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <div class="card">
            <div class="card-header">
               <div class="row">
                  <div class="col">
                     <h5>User Manual</h5>
                  </div>
                  <div class="col-3 text-right">
                     <button class="btn btn-outline-secondary" onClick="downloadUserManual(2);">Download
                     </button>
                  </div>
               </div>
            </div>
         </div>
        </div>
        <div class="tab-pane fade" id="third" role="tabpanel" aria-labelledby="third-tab">
            <hr />
            <ul class="nav nav-tabs nav-justified mb-3"  role="tablist">
                <li class="nav-item" ><a class="nav-link active" id="hr-first-tab" data-toggle="tab" href="#hrFirst" role="tab" aria-controls="managerFirst" aria-selected="true" >KPIs</a></li>
                <li class="nav-item" ><a class="nav-link" id="hr-second-tab" data-toggle="tab" href="#hrSecond" role="tab" aria-controls="managerSecond" aria-selected="false" >Reviews</a></li>
                <li class="nav-item" ><a class="nav-link" id="hr-third-tab" data-toggle="tab" href="#hrThird" role="tab" aria-controls="managerThird" aria-selected="false" >Manage Reviews</a></li>
                <li class="nav-item" ><a class="nav-link" id="hr-fourth-tab" data-toggle="tab" href="#hrFourth" role="tab" aria-controls="managerFourth" aria-selected="false" >Timelines</a></li>
            </ul>
            <hr />
            <div class="tab-content">
                <div class="tab-pane fade show active" id="hrFirst" role="tabpanel" aria-labelledby="hr-first-tab">
                    <div class="card">

                                <div class="card-header">
                                <div class="row">
                                    <div class="col pt-2">
                                        <h5>All KPIs List</h5>
                                    </div>
                                    <div class="col-2 pt-2 text-right">
                                        <a class="btn btn-outline-secondary" href="<%= downloadKpiReportUrl %>">Download
                                                             </a>
                                    </div>
                                </div>
                                </div>
                        <div class="card-body" >
                            <table class="table  table-sm  table-bordered table-striped table-hover " id="hrKpiTable">
                                <thead class="thead-dark">
                                    <tr>
                                        <th class="text-center align-middle" width="10%">Ecode</th>
                                        <th class="text-center align-middle" width="20%">Employee Name</th>
                                        <th class="text-center align-middle" width="20%">Manager Name</th>
                                        <th class="text-center align-middle" width="20%">Reviewer Name</th>
                                        <th class="text-center align-middle" width="20%">Account</th>
                                        <th class="text-center align-middle" width="20%">Type</th>
                                        <th class="text-center align-middle" width="20%">Status</th>
                                        <th class="text-center align-middle" width="10%">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${hrEmployeeKpiList}" var="item"
                                        varStatus="count">
                                        <tr>
                                            <td class="text-center align-middle">${item.ecode}</td>
                                            <td class="text-center align-middle">${item.employeeName}</td>
                                            <td class="text-center align-middle">${item.managerName}</td>
                                            <td class="text-center align-middle">${item.reviewerName}</td>
                                            <td class="text-center align-middle">${item.accountName}</td>
                                            <td class="text-center align-middle">
                                                <c:choose>
                                                    <c:when test="${(item.accountPrimary=='true')}">Primary
                                                    </c:when>
                                                    <c:otherwise>Secondary
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td class="text-center align-middle">
                                                <c:choose>
                                                    <c:when test="${(item.kpiSettingStatus=='true')}">Complete
                                                    </c:when>
                                                    <c:otherwise>
                                                        <font color="red">Pending </font>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td class="text-center align-middle">
                                                <c:if test="${(item.kpiSettingStatus=='true')}">
                                                    <form action="${kpiWorkflowUrl}" method="post"
                                                        style="display: inline">
                                                        <input type="hidden" value="${item.kpiId}"
                                                            name="<portlet:namespace/>kpiId" />
                                                        <input type="hidden" value="hrRole"
                                                            name="<portlet:namespace/>role" />
                                                        <button type="submit" style="cursor: pointer;"
                                                            title="Go to View Form" class=" btn btn-outline-primary btn-sm"> View </button>
                                                    </form>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="tab-pane fade" id="hrSecond" role="tabpanel" aria-labelledby="hr-second-tab">
                    <div class="card">
                        <div class="card-header">
                                <div class="row">
                                    <div class="col pt-2">
                                        <h5>Current Year Reviews</h5>
                                    </div>
                                    <div class="col-2 pt-2 text-right">
                                        <a class="btn btn-outline-secondary" href="<%= downloadReviewReportUrl %>">Download
                                                             </a>
                                    </div>
                                </div>
                        </div>
                        <div class="card-body">
                            <table id="hrCurrentTable"
                                class="table  table-sm  table-bordered table-striped table-hover ">
                                <thead class="thead-dark">
                                    <tr>
                                        <th class="text-center align-middle" width="20%">Ecode</th>
                                        <th class="text-center align-middle" width="20%">Employee Name</th>
                                        <th class="text-center align-middle" width="20%">Current Manager</th>
                                        <th class="text-center align-middle" width="20%">Overview</th>
                                        <th class="text-center align-middle" width="20%">Review Forms</th>
                                    </tr>
                                </thead>
                                <tbody id="accordionHr">
                                    <c:forEach items="${hrCurrentYearMap}" var="item" varStatus="count">
                                        <tr>
                                            <td class="text-center align-middle">${item.key.employeeCode}</td>
                                            <td class="text-center align-middle">${item.key.employeeName}</td>
                                            <td class="text-center align-middle">${item.key.managerName}</td>
                                            <td class="text-center align-middle">${item.key.overview}</td>
                                            <td class="text-center align-middle"><button id="${item.key.employeeCode}-${item.key.employeeName}"
                                                class="btn btn-link btn-sm collapsed"
                                                data-toggle="collapse" data-target="#hrRowId${count.index}" aria-expanded="false" aria-controls="hrRowId${count.index}"
                                                type="button">Review Form</button></td>
                                        </tr>
                                        <tr data-parent="#accordionHr" id="hrRowId${count.index}" class="collapse" aria-labelledby="${item.key.employeeCode}-${item.key.employeeName}">
                                            <td colspan="5">
                                                <div class="mt-4">
                                                    <div class="row">
                                                        <div class="col pt-2 text-center">
                                                            <h4>${item.key.employeeCode}-${item.key.employeeName}</h4>
                                                        </div>
                                                    </div>
                                                    <div class="card-body pt-0">
                                                        <table class="table  table-sm  table-bordered table-striped table-hover ">
                                                            <thead>
                                                                <tr>
                                                                    <th width="17%" class="bg-info text-white">Account</th>
                                                                    <th width="16%" class="bg-info text-white">Manager Email</th>
                                                                    <th width="17%" class="bg-info text-white">Review
                                                                        State
                                                                    </th>
                                                                    <th width="20%" class="bg-info text-white">Review
                                                                        Type
                                                                    </th>
                                                                    <th width="18%" class="bg-info text-white">Account Type</th>
                                                                    <th width="17%" class="bg-info text-white">Action</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <c:forEach items="${item.value}" var="reviewDetails">
                                                                    <tr>
                                                                        <td>${reviewDetails.account}</td>
                                                                        <td>${reviewDetails.manager}</td>
                                                                        <td>
                                                                            <c:choose>
                                                                                <c:when test="${(reviewDetails.finalYearReview=='true')}">
                                                                                    <c:out value="${reviewStateMapAnnual[reviewDetails.reviewState]}" />
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <c:out value="${reviewStateMapMidYear[reviewDetails.reviewState]}" />
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </td>
                                                                        <td>
                                                                            <c:choose>
                                                                                <c:when
                                                                                    test="${(reviewDetails.finalYearReview=='true')}">
                                                                                    <c:out value="Annual" />
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <c:out value="Mid Year" />
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </td>
                                                                        <td>
                                                                            <c:choose>
                                                                                <c:when
                                                                                    test="${(reviewDetails.accountPrimary=='true')}">Primary
                                                                                </c:when>
                                                                                <c:otherwise>Secondary
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </td>
                                                                        <td>
                                                                            <form action="${reviewWorkflowUrl}" method="post" style="display: inline">
                                                                                <input type="hidden" value="${reviewDetails.reviewId}"
                                                                                    name="<portlet:namespace/>reviewId" />
                                                                                <input type="hidden" value="Trantor_HR"
                                                                                    name="<portlet:namespace/>role" />
                                                                                <c:set var="status" scope="session"
                                                                                    value="${(reviewDetails.reviewState == 4)}" />
                                                                                <button type="submit" class="btn btn-outline-primary btn-sm" title="Go to Review Form"
                                                                                    style="cursor: pointer;" >${(status == 'true') ? ' Action' : 'View'}
                                                                                </button>
                                                                            </form>
                                                                        </td>
                                                                    </tr>
                                                                </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="tab-pane fade" id="hrThird" role="tabpanel" aria-labelledby="hr-third-tab">
                    <div class="card">
                        <div class="card-header">
                            <div class="row">
                                <div class="col pt-2">
                                    <h5>Override ${displayReviewPeriod}</h5>
                                </div>
                                <div class="col-2 pt-2">
                                    <input type="text" placeholder="ECode/EName" class="form-control form-control-sm border border-dark" id="overrideId" />
                                </div>
                                <div class="col-2 pt-2 text-center">
                                    <input type="button" value="Search" onclick="getEmployeeReview(this);" class="btn btn-outline-secondary"/>
                                </div>
                            </div>
                        </div>
                        <div class="card-body" id="overrideCardBody" style="display:none">
                            <span><sup class="text-danger font-weight-bold">*</sup>Annual Reviews in KPI Finalized state can only be forwarded to KPI/Self Review state</span>
                            <br>
                            <span><sup class="text-danger font-weight-bold">*</sup>Mid Year Reviews in KPI Finalized state can only be forwarded to Manager Review state</span>
                            <div class="row mt-1 mx-1" id="kpiHeader">
                                <h4>
                                KPIs Table</h2>
                            </div>
                            <table class="table  table-sm  table-bordered table-striped table-hover "  id="hrBlockKpiTable">
                                <thead class="thead-dark text-center">
                                    <tr>
                                        <th width="15%">Employee Name</th>
                                        <th width="15%">Account</th>
                                        <th width="15%">Manager Name</th>
                                        <th width="10%">Account Type</th>
                                        <th width="15%">KPI Status</th>
                                        <th width="15%">Action <i class="fas fa-question" title="You call allow self review for those account having KPI setting complete and no review form in current period"></i></th>
                                    </tr>
                                </thead>
                                <tbody id="hrBlockKpiTableBody">
                                </tbody>
                            </table>
                            <div class="row mt-1 mx-1" id="reviewHeader">
                                <h4> Review Table</h4>
                            </div>
                            <table class="table  table-sm  table-bordered table-striped table-hover "  id="hrBlockTable">
                                <thead class="thead-dark text-center">
                                    <tr>
                                        <th width="15%">Employee Name</th>
                                        <th width="15%">Account</th>
                                        <th width="15%">Manager Name</th>
                                        <th width="15%">Review Type</th>
                                        <th width="10%">Account Type</th>
                                        <th width="15%">State</th>
                                        <th width="15%">Action</th>
                                    </tr>
                                </thead>
                                <tbody id="hrBlockTableBody">
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-header">
                            <div class="row">
                                <div class="col pt-2">
                                    <h5>Rollback Requests By Managers</h5>
                                </div>
                            </div>
                        </div>
                        <c:if test="${allRollbackReviewForHr.size() > 0}">
                            <div class="card-body">
                                <form id="hrRollbackForm" action="${submitActionOnRollbackByHRUrl}"  method="post" onsubmit="return submitRollbackActionByHR()" >
                                    <input type="hidden" id="actionTaken" value="" name="<portlet:namespace/>actionPerformed"/>
                                    <table  class="table table-sm table-bordered table-responsive-md table-striped text-center" id="hrRollbackTable" >
                                        <thead class="thead-dark">
                                            <tr>
                                                <th class="text-center align-middle">Select</th>
                                                <th class="text-center align-middle">Ecode</th>
                                                <th class="text-center align-middle">Employee Name</th>
                                                <th class="text-center align-middle">Account Name</th>
                                                <th class="text-center align-middle">Review Type</th>
                                                <th class="text-center align-middle">Account Type</th>
                                                <th class="text-center align-middle">Stage</th>
                                                <th class="text-center align-middle">Review Year</th>
                                            </tr>
                                        </thead>
                                        <tbody >
                                            <c:forEach items="${allRollbackReviewForHr}" var="item" varStatus="count" >
                                                <tr>
                                                    <td>
                                                        <input type="checkbox" value="${item.rollbackId}"
                                                            id="hrCheckbox${item.rollbackId}" name="<portlet:namespace/>hrSelected" />
                                                    </td>
                                                    <td>${item.employeeCode}</td>
                                                    <td>${item.employeeName}</td>
                                                    <td>${item.account}</td>
                                                    <td>${item.reviewType}</td>
                                                    <td>${item.accountType}</td>
                                                    <td>${item.reviewStateName}</td>
                                                    <td>${item.reviewStartDate}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <div class="row mt-4"  id="btnSubmitRollbackAction">
                                        <div class="col text-center">
                                            <button type="submit" class=" btn btn-outline-success ml-5" id="rollbackApprove" title="Rollback to Manager Review State " onClick="actionOnRollbackByHr(this)">Approve</button>
                                            <button type="submit" class=" btn btn-outline-danger ml-5" id="rollbackReject" onClick="actionOnRollbackByHr(this)">Reject</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </c:if>
                    </div>
                    <div class="card">
                        <div class="card-header">
                            <div class="row">
                                <div class="col pt-2">
                                    <h5>Review Form Closure Requests By Managers</h5>
                                </div>
                            </div>
                        </div>
                        <c:if test="${allCloseReviewForHr.size() > 0}">
                            <div class="card-body">
                                <form id="hrCloseForm" action="${submitActionOnCloseByHRUrl}"  method="post" onsubmit="return submitCloseActionByHR()" >
                                    <input type="hidden" id="actionTakenOnClose" value="" name="<portlet:namespace/>actionPerformedOnClose"/>
                                    <table  class="table table-sm table-bordered table-responsive-md table-striped text-center" id="hrCloseTable" >
                                        <thead class="thead-dark">
                                            <tr>
                                                <th class="text-center align-middle">Select</th>
                                                <th class="text-center align-middle">Ecode</th>
                                                <th class="text-center align-middle">Employee Name</th>
                                                <th class="text-center align-middle">Account Name</th>
                                                <th class="text-center align-middle">Review Type</th>
                                                <th class="text-center align-middle">Account Type</th>
                                                <th class="text-center align-middle">Stage</th>
                                                <th class="text-center align-middle">Review Year</th>
                                            </tr>
                                        </thead>
                                        <tbody >
                                            <c:forEach items="${allCloseReviewForHr}" var="item" varStatus="count" >
                                                <tr>
                                                    <td>
                                                        <input type="checkbox" value="${item.closeId}"
                                                            id="hrCloseCheckbox${item.closeId}" name="<portlet:namespace/>hrSelectedClose" />
                                                    </td>
                                                    <td>${item.employeeCode}</td>
                                                    <td>${item.employeeName}</td>
                                                    <td>${item.account}</td>
                                                    <td>${item.reviewType}</td>
                                                    <td>${item.accountType}</td>
                                                    <td>${item.reviewStateName}</td>
                                                    <td>${item.reviewStartDate}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <div class="row mt-4"  id="btnSubmitCloseAction">
                                        <div class="col text-center">
                                            <button type="submit" class=" btn btn-outline-success ml-5" id="closeApprove" onClick="actionOnCloseByHr(this)">Approve</button>
                                            <button type="submit" class=" btn btn-outline-danger ml-5" id="closeReject" onClick="actionOnCloseByHr(this)">Reject</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </c:if>
                    </div>
                    <div class="card">
                        <div class="card-header">
                            <div class="row">
                                <div class="col pt-2">
                                    <h5>Glance ${displayReviewPeriod}</h5>
                                </div>
                                <div class="col-2 pt-2">
                                    <select class="mdb-select md-form form-control form-control-sm" id="hrSearchAccount" onchange="filterHrSearchTable()" >
                                        <option value="" selected >Select Account</option>
                                        <c:forEach items="${accountList}" var="entry">
                                            <option value="${entry.value}">${entry.key}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-2 pt-2">
                                    <select class="mdb-select md-form form-control form-control-sm" id="hrSearchYear" onchange="filterHrSearchTable()" >
                                        <option value="" selected >Select Option</option>
                                        <option value="F" >Annual</option>
                                        <option value="M" >Mid Year</option>
                                    </select>
                                </div>
                                <div class="col-2 pt-2">
                                    <select class="mdb-select md-form form-control form-control-sm" id="hrSearchType" onchange="filterHrSearchTable()" >
                                        <option value="" selected >Select Type</option>
                                        <option value="P" >Primary</option>
                                        <option value="S" >Secondary</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="card-body" id="hrSearchTableDiv" style="display:none">
                            <table class="table  table-sm  table-bordered table-striped table-hover "  id="hrSearchTable">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>Ecode</th>
                                        <th>Employee Name</th>
                                        <th>Manager Name</th>
                                        <th>Review State</th>
                                    </tr>
                                </thead>
                                <tbody id="hrSearchTableBody">
                                    <c:forEach items="${allCurrentYearPrimaryHReview}" var="searchEntry" varStatus="count" >
                                        <tr class="H${searchEntry.account}${searchEntry.rating}${searchEntry.reviewType}">
                                            <td>${searchEntry.employeeCode}</td>
                                            <td>${searchEntry.employeeName}</td>
                                            <td>${searchEntry.managerName}</td>
                                            <td>${searchEntry.reviewStateName}</td>
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
                                    <h5>Previous Years Reviews</h5>
                                </div>
                                <div class="col-3 pt-2 float-right">
                                    <select class="mdb-select md-form form-control form-control-sm" onchange="getHRYearlyEntries()" id="ayForHREntries">
                                        <option value="" selected disabled>Select Review Year</option>
                                        <c:forEach items="${yearList}" var="item" varStatus="count">
                                            <option value="${item.key}"  >${item.value}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                    <div class="col-2 pt-1 text-right">
                                         <a class="btn btn-outline-secondary" onclick="setYear(this)" id="excel"  href="<%=downloadHrYearWiseReportURL%>&amp;<portlet:namespace/>year=0">Download
                                                                                                     </a>
                                    </div>
                            </div>
                        </div>
                        <div class="card-body" id="ayForHREntriesDiv" style="display:none">
                            <table  class="table table-sm table-bordered table-responsive-md table-striped text-center" id="ayForHREntriesTable" >
                                <thead class="thead-dark">
                                    <tr>
                                        <th class="text-center align-middle">Ecode</th>
                                        <th class="text-center align-middle">Employee Name</th>
                                        <th class="text-center align-middle">Review Type</th>
                                        <th class="text-center align-middle">Account Type</th>
                                        <th class="text-center align-middle">Status</th>
                                        <th class="text-center align-middle">Action</th>
                                    </tr>
                                </thead>
                                <tbody id="ayForHREntriesTableBody">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="tab-pane fade" id="hrFourth" role="tabpanel" aria-labelledby="hr-fourth-tab">
                   <div class="row">
                    <div class="col-6">
                        <div class="card">
                            <div class="card-header">
                                <div class="row">
                                    <div class="col">
                                        <h5>Mid-Year Reviews ${displayReviewPeriod}</h5>
                                    </div>
                                    <div class="col-4 text-right">
                                        <button class="btn btn-sm btn-outline-primary" type="button"
                                            id="updateMidTimelineAction" onclick="updateMidTimeline()">Update</button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <canvas id="midYearAnalytics">
                                </canvas>
                                <form method="post" action="${updateMidReviewTimelineUrl}">
                                    <table
                                        class="table table-sm table-bordered table-striped table-hover  mt-2">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th class="text-center">Stage</th>
                                                <th>End Date</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td title="KPI/Self Review will start from next date">${reviewStateMapMidYear[(0).intValue()]}</td>
                                                <td>
                                                    <input type="text" class="form-control form-control-sm fn_midYearDate"
                                                        required="required"
                                                        readonly="true" value=""
                                                        id="midyearDate0"  onfocus="onFocusBorder(this)"
                                                        name="<portlet:namespace/>midyearDate0" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>${reviewStateMapMidYear[(1).intValue()]}</td>
                                                <td>
                                                    <input type="text" class="form-control form-control-sm fn_midYearDate"
                                                        required="required"
                                                        readonly="true" value=""
                                                        id="midyearDate1"  onfocus="onFocusBorder(this)"
                                                        name="<portlet:namespace/>midyearDate1" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>${reviewStateMapMidYear[(2).intValue()]}  </td>
                                                <td>
                                                    <input type="text" class="form-control form-control-sm dateItemInput fn_midYearDate"
                                                        required="required"
                                                        readonly="true" value=""
                                                        id="midyearDate2"  onfocus="onFocusBorder(this)"
                                                        name="<portlet:namespace/>midyearDate2" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="2" class="text-center">${reviewStateMapMidYear[(5).intValue()]}</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <div class="row pt-3" id="updateMidTimelineButtons" style="display:none">
                                        <div class="col text-center">
                                            <button class="btn btn-sm btn-outline-secondary"
                                                type="button" onclick="updateMidTimelineCancel()">Cancel</button>
                                        </div>
                                        <div class="col text-center">
                                            <button class="btn btn-sm btn-outline-primary"
                                                type="submit" id="updateMidTimelineSubmit">Submit</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="card">
                            <div class="card-header">
                                <div class="row">
                                    <div class="col">
                                        <h5>Annual Reviews ${displayReviewPeriod}</h5>
                                    </div>
                                    <div class="col-4 text-right">
                                        <button class="btn btn-sm btn-outline-primary" type="button"
                                            id="updateFinalTimelineAction"
                                            onclick="updateFinalTimeline()">Update</button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <canvas id="finalYearAnalytics">
                                </canvas>
                                <form method="post"
                                    action="${updateFinalReviewTimelineUrl}">
                                    <table
                                        class="table table-sm table-bordered table-striped table-hover  mt-2">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th class="text-center">Stage</th>
                                                <th>End Date</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td title="KPI/Self Review will start from next date">${reviewStateMapAnnual[(0).intValue()]}</td>
                                                <td>
                                                    <input type="text" class="form-control form-control-sm fn_AnnualDate"
                                                        required="required"
                                                        readonly="true" value=""
                                                        id="finalyearDate0"  onfocus="onFocusBorder(this)"
                                                        name="<portlet:namespace/>finalyearDate0" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>${reviewStateMapAnnual[(1).intValue()]}</td>
                                                <td>
                                                    <input type="text" class="form-control form-control-sm fn_AnnualDate"
                                                        required="required"
                                                        readonly="true" value=""
                                                        id="finalyearDate1"  onfocus="onFocusBorder(this)"
                                                        name="<portlet:namespace/>finalyearDate1" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>${reviewStateMapAnnual[(2).intValue()]} </td>
                                                <td>
                                                    <input type="text" class="form-control form-control-sm fn_AnnualDate"
                                                        required="required"
                                                        readonly="true" value=""
                                                        id="finalyearDate2"  onfocus="onFocusBorder(this)"
                                                        name="<portlet:namespace/>finalyearDate2" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>${reviewStateMapAnnual[(3).intValue()]} </td>
                                                <td>
                                                    <input type="text" class="form-control form-control-sm fn_AnnualDate"
                                                        required="required"
                                                        readonly="true" value=""
                                                        id="finalyearDate3"  onfocus="onFocusBorder(this)"
                                                        name="<portlet:namespace/>finalyearDate3" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>${reviewStateMapAnnual[(4).intValue()]}</td>
                                                <td>
                                                    <input type="text" class="form-control form-control-sm fn_AnnualDate"
                                                        required="required"
                                                        readonly="true" value=""
                                                        id="finalyearDate4"  onfocus="onFocusBorder(this)"
                                                        name="<portlet:namespace/>finalyearDate4" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td  colspan="2" class="text-center">${reviewStateMapAnnual[(5).intValue()]}</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <div class="row pt-3" id="updateFinalTimelineButtons" style="display:none">
                                        <div class="col text-center">
                                            <button class="btn btn-sm btn-outline-secondary"
                                                type="button" onclick="updateFinalTimelineCancel()">Cancel</button>
                                        </div>
                                        <div class="col text-center">
                                            <button class="btn btn-sm btn-outline-primary"
                                                type="submit" id="updateFinalTimelineSubmit">Submit</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                </div>
            </div>
         <div class="card">
            <div class="card-header">
               <div class="row">
                  <div class="col">
                     <h5>User Manual</h5>
                  </div>
                  <div class="col-3 text-right">
                     <button class="btn btn-outline-secondary" onClick="downloadUserManual(3);">Download
                     </button>
                  </div>
               </div>
            </div>
         </div>
        </div>
        <div class="tab-pane fade" id="fourth" role="tabpanel" aria-labelledby="fourth-tab">
            <div class="card">
                <div class="card-header">
                    <div class="row">
                        <div class="col pt-2">
                            <h5>Rollback Forms to Manager Review State</h5>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <c:if test="${allRollbackReviewForAdminManagerToRequest.size() > 0}">
                        <div>
                            <table  class="table table-sm table-bordered table-responsive-md table-striped text-center" id="managerRollbackTable" >
                                <thead class="thead-dark">
                                    <tr>
                                        <th class="text-center align-middle">Ecode</th>
                                        <th class="text-center align-middle">Employee Name</th>
                                        <th class="text-center align-middle">Account Name</th>
                                        <th class="text-center align-middle">Review Type</th>
                                        <th class="text-center align-middle">Account Type</th>
                                        <th class="text-center align-middle">Stage</th>
                                        <th class="text-center align-middle">Review Year</th>
                                        <th class="text-center align-middle">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${allRollbackReviewForAdminManagerToRequest}" var="item" varStatus="count" >
                                        <tr>
                                            <td>${item.employeeCode}</td>
                                            <td>${item.employeeName}</td>
                                            <td>${item.account}</td>
                                            <td>${item.reviewType}</td>
                                            <td>${item.accountType}</td>
                                            <td>${item.reviewStateName}</td>
                                            <td>${item.reviewStartDate}</td>
                                            <td>
                                                <form action="${reviewWorkflowUrl}" method="post" style="display: inline">
                                                    <input type="hidden" value="${item.reviewId}"
                                                        name="<portlet:namespace/>reviewId" />
                                                    <input type="hidden" value="Trantor_Performance"
                                                        name="<portlet:namespace/>role" />
                                                    <button type="submit"  class="btn btn-outline-primary btn-sm" title="Go to Review Form"
                                                        style="cursor: pointer;">Action</button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:if>
                  <c:if test = "${allRollbackReviewForAdminManagerToRequest.size() <=0}"><p class="m-3" style="text-align: center;">No data available</p></c:if>
                </div>
            </div>
              <div class="card">
                        <div class="card-header">
                            <div class="row">
                                <div class="col pt-2">
                                    <h5>Previous Years Reviews</h5>
                                </div>
                                <div class="col-3 pt-2 float-right">
                                    <select class="mdb-select md-form form-control form-control-sm" onchange="getManagerYearlyEntries(2)" id="ayForAdminEntries">
                                        <option value="" selected disabled>Select Review Year</option>
                                        <c:forEach items="${yearList}" var="item" varStatus="count">
                                            <option value="${item.key}"  >${item.value}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="card-body" id="ayForAdminEntriesDiv" style="display:none">
                            <table  class="table table-sm table-bordered table-responsive-md table-striped text-center" id="ayForAdminEntriesTable" >
                                <thead class="thead-dark">
                                    <tr>
                                        <th class="text-center align-middle">Ecode</th>
                                        <th class="text-center align-middle">Employee Name</th>
                                        <th class="text-center align-middle">Review Type</th>
                                        <th class="text-center align-middle">Account Type</th>
                                        <th class="text-center align-middle">Status</th>
                                        <th class="text-center align-middle">Action</th>
                                    </tr>
                                </thead>
                                <tbody id="ayForAdminEntriesTableBody">
                                </tbody>
                            </table>
                        </div>
                    </div>
        </div>
    </div>
</div>
<script>
$(document).ready(function () {
var hrRole = $('#isHR').val();
if (hrRole == 'true') {
      setFinalYearEndDates();
      setMidYearEndDates();
   }
   var finalReviewAnalyticsMapGson = $('#finalReviewAnalyticsMap').val();
      var midReviewAnalyticsMapGson = $('#midReviewAnalyticsMap').val();
      var finalReviewSecondaryAnalyticsMapGson = $('#finalReviewSecondaryAnalyticsMap').val();
      var midReviewSecondaryAnalyticsMapGson = $('#midReviewSecondaryAnalyticsMap').val();
      var finalReviewAnalyticsMap = JSON.parse(finalReviewAnalyticsMapGson);
      var finalReviewSecondaryAnalyticsMap = JSON.parse(finalReviewSecondaryAnalyticsMapGson);
      var midReviewAnalyticsMap = JSON.parse(midReviewAnalyticsMapGson);
      var midReviewSecondaryAnalyticsMap = JSON.parse(midReviewSecondaryAnalyticsMapGson);
      var finalYearGraph = new Chart(document.getElementById("finalYearAnalytics"), {
         type: 'bar',
         data: {
            labels: Object.keys(finalReviewAnalyticsMap),
            datasets: [{
               label: "Primary",
               borderWidth: 1,
               backgroundColor: "#3cba9f",
               data: Object.keys(finalReviewAnalyticsMap).map(function (key) {
                  return finalReviewAnalyticsMap[key];
               })
            }, {
               label: "Secondary",
               borderWidth: 1,
               backgroundColor: "#8e5ea2",
               data: Object.keys(finalReviewAnalyticsMap).map(function (key) {
                  return finalReviewSecondaryAnalyticsMap[key];
               })
            }]
         },
         options: {
            responsive: true,
            plugins: {
               legend: {
                  position: 'top',
               },
               title: {
                  display: false,
                  text: 'Annual Reviews',
                  fontColor: 'blue'
               }
            }
         },
      });

      var midYearGraph = new Chart(document.getElementById("midYearAnalytics"), {
         type: 'bar',
         data: {
            labels: Object.keys(midReviewAnalyticsMap),
            datasets: [{
               label: "Primary",
               borderWidth: 1,
               backgroundColor: "#3cba9f",
               data: Object.keys(midReviewAnalyticsMap).map(function (key) {
                  return midReviewAnalyticsMap[key];
               })
            }, {
               label: "Secondary",
               borderWidth: 1,
               backgroundColor: "#8e5ea2",
               data: Object.keys(midReviewAnalyticsMap).map(function (key) {
                  return midReviewSecondaryAnalyticsMap[key];
               })
            }]
         },
         options: {
            responsive: true,
            plugins: {
               legend: {
                  position: 'top',
               },
               title: {
                  display: false,
                  text: 'Mid Year Reviews',
                  fontColor: 'blue'
               }
            }
         },
      });
   });
function setFinalYearEndDates() {
   $.each($.parseJSON($('#finalDates').val()), function (k, v) {
      var d = new Date(v);
      $('#finalyearDate' + k).datepicker("setDate", d);
   });
}
function updateFinalTimelineCancel() {
   setFinalYearEndDates(finalDates);
   $('.fn_AnnualDate').datepicker('disable');
   $('#updateFinalTimelineButtons').hide();
   $('#updateFinalTimelineAction').show();
   $(".fn_AnnualDate").each(function () {
      $(this).css("border", "none");
   });
}
function setMidYearEndDates() {
   $.each($.parseJSON($('#midDates').val()), function (k, v) {
      var d = new Date(v);
      $('#midyearDate' + k).datepicker("setDate", d);
   });
}
function updateMidTimelineCancel() {
   setMidYearEndDates(midDates);
   $('.fn_midYearDate').datepicker('disable');
   $('#updateMidTimelineButtons').hide();
   $('#updateMidTimelineAction').show();
   $(".fn_midYearDate").each(function () {
      $(this).css("border", "none");
   });
}
</script>