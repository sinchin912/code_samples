<%@ include file="/init.jsp" %>
<portlet:actionURL name="addKpiDetails" var="addKpiDetailsUrl"></portlet:actionURL>
<portlet:actionURL name="generateReview" var="generateReviewUrl"></portlet:actionURL>
<portlet:actionURL name="reviewWorkflow" var="reviewWorkflowUrl"></portlet:actionURL>
<portlet:actionURL name="addNewAccount" var="addNewAccountUrl"></portlet:actionURL>
<portlet:actionURL name="deleteReview" var="deleteReviewUrl"></portlet:actionURL>
<portlet:actionURL name="deleteKpiAccount" var="deleteKpiAccountUrl"></portlet:actionURL>
<portlet:resourceURL id="getManagerName" var="getManagerNameUrl"></portlet:resourceURL>
<portlet:resourceURL id="getReviewerName" var="getReviewerNameUrl"></portlet:resourceURL>
<portlet:resourceURL id="getYearlyEntries" var="getYearlyEntriesURL"></portlet:resourceURL>
<portlet:resourceURL var="downloadUserManualUrl" id="downloadUserManual"></portlet:resourceURL>
<portlet:resourceURL id="getManagerEmailForEditAccount"var="getManagerEmailForEditAccountUrl"></portlet:resourceURL>
<portlet:resourceURL id="getReviewerEmailForEditAccount"var="getReviewerEmailForEditAccountUrl"></portlet:resourceURL>
<div class="container">
    <input type="hidden" id="reviewWorkflowResourceUrl" value="${reviewWorkflowUrl}"/>
    <input type="hidden" id="deleteAccount" value=""/>
    <input type="hidden" id="deleteKpi" value=""/>
   <input type="hidden" id="downloadUserManualResourceUrl" value="${downloadUserManualUrl}"/>
   <input type="hidden" id="getManagerEmailForEditAccountResourceUrl" value="${getManagerEmailForEditAccountUrl}"/>
   <input type="hidden" id="getReviewerEmailForEditAccountResourceUrl" value="${getReviewerEmailForEditAccountUrl}"/>
   <input type="hidden" id="portletNamespace"   value="<portlet:namespace/>" />
   <input type="hidden" id="getManagerNameResourceUrl" value="${getManagerNameUrl}"/>
   <input type="hidden" id="getReviewerNameResourceUrl" value="${getReviewerNameUrl}"/>
   <input type="hidden" id="getYearlyEntriesResourceUrl" value="${getYearlyEntriesURL}"/>
   <input type="hidden" id="downloadUserManualResourceUrl" value="${downloadUserManualUrl}"/>
   <ul class="nav nav-tabs nav-justified mb-3" role="tablist">
      <li class="nav-item"><a class="nav-link active" id="first-tab" data-toggle="tab" href="#first" role="tab" aria-controls="first" aria-selected="true">Current Year Reviews</a></li>
      <li class="nav-item"><a class="nav-link" id="second-tab" data-toggle="tab" href="#second" role="tab" aria-controls="third" aria-selected="false">Previous Years Reviews</a></li>
   </ul>
   <div class="tab-content">
      <div class="tab-pane fade show active" id="first" role="tabpanel" aria-labelledby="first-tab">
         <div class="card">
            <div class="card-header">
               <h5>General Information</h5>
            </div>
            <div class="card-body">
               <div class="row">
                  <div class="col-md-2 mt-1 text-right">
                     <label>Employee Name</label>
                  </div>
                  <div class="col-md-4">
                     <input type="text" class=" form-control-sm form-control-plaintext"
                        readonly value="${empCoreDetails.empName}" />
                  </div>
                  <div class="col-md-2 mt-1 text-right">
                     <label>Ecode</label>
                  </div>
                  <div class="col-md-4">
                     <input type="text" id="ecode" class=" form-control-sm form-control-plaintext"
                        readonly value="${empCoreDetails.empEcode}" />
                  </div>
               </div>
               <div class="row">
                  <div class="col-md-2 mt-1 text-right">
                     <label>Designation</label>
                  </div>
                  <div class="col-md-4">
                     <input type="text" class=" form-control-sm form-control-plaintext"
                        value="${empCoreDetails.empDesignation}" readonly />
                  </div>
                  <div class="col-md-2 mt-1 text-right">
                     <label>Band</label>
                  </div>
                  <div class="col-md-4">
                     <input type="text" class=" form-control-sm form-control-plaintext"
                        value="${empCoreDetails.empBand}" readonly />
                  </div>
               </div>
               <div class="row">
                  <div class="col-md-2 mt-1 text-right">
                     <label>Account</label>
                  </div>
                  <div class="col-md-4">
                     <input type="text" class=" form-control-sm form-control-plaintext"
                        readonly value="${empCoreDetails.account}" />
                  </div>
                  <div class="col-md-2 mt-1 text-right">
                     <label>Mobile</label>
                  </div>
                  <div class="col-md-4">
                     <input type="text" class=" form-control-sm form-control-plaintext"
                        readonly value="${empCoreDetails.empMobileNo}" />
                  </div>
               </div>
               <div class="row">
                  <div class="col-md-2 mt-1 text-right">
                     <label>Manager Name</label>
                  </div>
                  <div class="col-md-4">
                     <input type="text" class=" form-control-sm form-control-plaintext"
                        readonly value="${empCoreDetails.managerName}" />
                  </div>
                  <div class="col-md-2 mt-1 text-right">
                     <label>Manager Email</label>
                  </div>
                  <div class="col-md-4">
                     <input type="text" class=" form-control-sm form-control-plaintext"
                        readonly value="${empCoreDetails.managerEmail}" />
                  </div>
               </div>
               <div class="row">
                  <div class="col-md-2 mt-1 text-right">
                     <label>Reviewer Name</label>
                  </div>
                  <div class="col-md-4">
                     <input type="text" class=" form-control-sm form-control-plaintext"
                        readonly value="${empCoreDetails.reviewerName}" />
                  </div>
                  <div class="col-md-2 mt-1 text-right">
                     <label>Reviewer Email</label>
                  </div>
                  <div class="col-md-4">
                     <input type="text" class=" form-control-sm form-control-plaintext"
                        readonly value="${empCoreDetails.reviewerEmail}" />
                  </div>
               </div>
            </div>
         </div>
         <div class="card pb-2">
            <div class="card-header">
               <div class="row">
                  <div class="col pt-2">
                     <h5>KPI Setting</h5>
                  </div>
               </div>
            </div>
            <div class="card-body">
               <p>
                  <c:if test="${(empCoreDetails.generateReviewButtonTextDisplay=='true')}">Click on <i class="fa fa-line-chart">&nbsp;</i> button to generate self review form.<br></c:if>
               </p>
               <table id="kpiSettingTable"
                  class="table table-sm table-bordered table-striped table-hover mt-2">
                  <thead class="thead-dark">
                     <tr>
                        <th class="text-center align-middle" >Account</th>
                        <th class="text-center align-middle">Account
                           Type
                        </th>
                        <th class="text-center align-middle">Manager
                           Name
                        </th>
                        <th class="text-center align-middle">Lead
                           Name
                        </th>
                        <th class="text-center align-middle">KPI Setting</th>
                        <th class="text-center align-middle">Action</th>
                     </tr>
                  </thead>
                            <tbody id="kpiTableBodyId">
                                <c:forEach items="${employeeKpiList}" var="item"
                                    varStatus="count">
                                    <tr id="tr${item.kpiId}">
                                        <td class="text-center">${item.account}</td>
                                        <td class="text-center"><c:choose>
                                                <c:when test="${(item.accountPrimary=='true')}">
                                                    Primary
                                                </c:when>
                                                <c:otherwise>
                                                    Secondary
                                                </c:otherwise>
                                            </c:choose></td>
                                        <td class="text-center"><input type="hidden" value="${item.managerEmail}" />
                                             ${item.managerName}</td>
                                        <td class="text-center"><input type="hidden" value="${item.reviewerEmail}" />
                                            ${item.reviewerName}</td>
                                        <td class="text-center">
                                            <c:choose>
                                                <c:when test="${(item.kpiSettingStatus=='true')}">
                                                    Complete
                                                </c:when>
                                                <c:otherwise>
                                                   <span class="text-danger"> Pending</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:if test="${(item.generateReviewButtonDisplay=='true')}">
                                                <form action="${generateReviewUrl}" method="post" id="generateReviewForm" style="display: inline">
                                                    <input type="hidden" value="${item.kpiId}" name="<portlet:namespace/>kpiId" />
                                                    <button type="submit" class="btn btn-primary btn-sm fn_viewFormButton" title="Generate Review Form" onClick="return  generateReviewForm();"
                                                              style="cursor: pointer;" ><i class="fa fa-line-chart"></i>
                                                    </button>
                                                </form>
                                            </c:if>
                                            <form action="${addKpiDetailsUrl}" method="post" style="display: inline" >
                                                <input type="hidden" value="${item.kpiId}" name="<portlet:namespace/>kpiId" />
                                                <button type="submit" class="btn btn-outline-primary btn-sm fn_viewFormButton" title="View/Edit Kpi Settings"
                                                          style="cursor: pointer;" >${item.kpiSettingStatus=='true' ? ' <i class="fas fa-eye"></i>' : '<i class="fas fa-tasks"></i>'}
                                                </button>
                                            </form>
                                        <c:if test="${(item.accountPrimary=='false')}">
                                                    <button id="${item.kpiId}" type="button" title="Edit Project Details"
                                                        class="btn btn-outline-warning btn-sm fn_editAccountDetail"
                                                        onClick="editAccountDetails(this)" style="cursor: pointer;">
                                                        <i class="fas fa-edit"></i>
                                                    </button>
                                                    <form action="${deleteKpiAccountUrl}" method="post" id="deleteKpiAccount_${item.kpiId}" style="display: inline" onsubmit="return deleteAccountForm(${item.kpiId})">
                                                        <input type="hidden" value="${item.kpiId}" name="<portlet:namespace/>deleteId" />
                                                        <button type="submit" title="Delete Account"  onClick="return setKpiVal(this)" class=" btn btn-sm btn-outline-danger fn_deleteAccountButton" value="${item.account}" style="cursor: pointer;"><i class="fas fa-trash-alt"></i></button>
                                                    </form>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
               </table>
               <c:if test = "${employeeKpiList.size() <=0}"><p class="m-3" style="text-align: center;">No data available</p></c:if>
                        <div class="text-center mt-3">
                                    <button type="button" class="btn btn-outline-primary" onClick="addSecondaryAccount()"
                                        id="addAccountButton">Add Accounts</button>
                        </div>
                        <div class="mt-2" id="addEditAccountDiv" style="display:none">
                            <hr>
                            <form  onsubmit="return addNewAccountFormValidation()" method="post" id="addNewAccountForm" action="${addNewAccountUrl}" >

                                <input type="hidden" id="kpiId"
                                    name="<portlet:namespace/>kpiId" />
                                <div class="form-row">
                                    <div class="col-md-2 mt-1 text-right">
                                        <label>Account</label>
                                    </div>
                                    <div class="col-md-4">
                                        <select class="form-control form-control-sm" id="account"
                                            name="<portlet:namespace/>account" required>
                                            <option value="" selected disabled>Select Account</option>
                                            <c:forEach items="${accountList}" var="account"
                                                varStatus="count">
                                                <option value="${account}">${account}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-md-2 mt-2 text-right">
                                        <label>Account Type</label>
                                    </div>
                                    <div class="col-md-4 mt-2">
                                        <label>Secondary</label>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="col-md-2 mt-1 text-right">
                                        <label>Manager Email</label>
                                    </div>
                                    <div class="col-md-4">
                                        <input type="text" class="form-control form-control-sm fn_text-trim" required
                                            id="managerEmail" name="<portlet:namespace/>managerEmail"
                                            value="" onblur="getManagerName();" onfocus="removeWarning('M')" maxlength="75" />
                                            <div style="text-align: center">
                                                <span class="fn_email-has-errorManager"
                                                    style="color: red; "></span>
                                            </div>
                                    </div>
                                    <div class="col-md-2 mt-2 text-right">
                                        <label>Manager Name</label>
                                    </div>
                                    <div class="col-md-4 mt-1">
                                        <input type="text" class="form-control-plaintext form-control-sm  fn_text-trim" readonly
                                            autocomplete="off" required
                                            id="managerName" name="<portlet:namespace/>managername" />
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="col-md-2 mt-1 text-right">
                                        <label>Reviewer Email</label>
                                    </div>
                                    <div class="col-md-4">
                                        <input type="text" class="form-control form-control-sm fn_text-trim" required
                                            id="reviewerEmail" name="<portlet:namespace/>reviewerEmail"
                                            value="" onblur="getReviewerName();"  onfocus="removeWarning('R')"
                                            maxlength="75" />
                                        <div style="text-align: center">
                                            <span class="fn_email-has-errorReviewer"
                                                style="color: red; "></span>
                                        </div>
                                    </div>
                                    <div class="col-md-2 mt-2 text-right">
                                        <label>Reviewer Name</label>
                                    </div>
                                    <div class="col-md-4 mt-1">
                                        <input type="text" class="form-control-plaintext form-control-sm fn_text-trim" readonly
                                            autocomplete="off" required
                                            id="reviewerName" name="<portlet:namespace/>reviewername" />
                                    </div>
                                </div>
                                        <div class="text-center">
                                            <button type="submit" class="btn btn-outline-success"
                                                id="saveAccountButton"> Save </button>
                                            <button type="button" class=" btn btn-outline-secondary ml-4"
                                                onClick="cancelAddAccount()">  Cancel
                                            </button>
                                        </div>
                        </form>
                    </div>
            </div>
         </div>
            <div class="card pb-2">
                            <div class="card-header">
                                <h5>Performance Review ${displayCurrentAY}</h5>
                            </div>
                            <div class="card-body">
                                <table id="projectsDetailsTable"
                                    class="table table-sm table-bordered table-striped table-hover table-custom">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th class="text-center align-middle" width="10%">Account</th>
                                            <th class="text-center align-middle">Review
                                                Type</th>
                                            <th class="text-center align-middle">Account
                                                Type</th>
                                            <th class="text-center align-middle">Manager
                                                Name</th>
                                            <th class="text-center align-middle">Review Form Status</th>
                                            <th class="text-center align-middle">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${currentYearEntries}" var="item"
                                            varStatus="count">
                                            <tr id="tr${item.reviewId}">
                                                <td class="text-center">${item.account}</td>
                                                <td class="text-center"><c:choose>
                                                        <c:when test="${(item.currentYRReview=='true')}">Annual
                                                        </c:when>
                                                        <c:otherwise>Mid Year
                                                        </c:otherwise>
                                                    </c:choose></td>
                                                <td class="text-center"><c:choose>
                                                        <c:when test="${(item.currentAccount=='true')}">Primary
                                                        </c:when>
                                                        <c:otherwise>Secondary
                                                        </c:otherwise>
                                                    </c:choose></td>
                                                <td class="text-center">${item.managerName}</td>
                                                <td class="text-center"><input type="hidden" value="${item.accountDate}" />
                                                    <c:choose>
                                                        <c:when test="${(item.reviewState == 6)}">
                                                            <div style="color: red" >${item.reviewStateName}
                                                        </c:when>
                                                        <c:when test="${(item.reviewState == 5)}">
                                                            <div style="color: green" >${item.reviewStateName}
                                                        </c:when>
                                                        <c:otherwise>
                                                            <div style="color: black" >${item.reviewStateName}
                                                        </c:otherwise>
                                                    </c:choose></td>
                                                <td>
                                                    <form action="${reviewWorkflowUrl}" method="post" style="display: inline">
                                                        <input type="hidden" value="${item.reviewId}"
                                                                name="<portlet:namespace/>reviewId" />
                                                        <button type="submit" class=" btn btn-primary btn-sm" title="Go to Review Form"
                                                                style="cursor: pointer;"><i class="fas fa-tasks"></i></button>
                                                    </form>
                                                    <c:if test="${item.reviewState == 1}">
                                                        <form method="post"  action="${deleteReviewUrl}" id="deleteReviewForm_${item.reviewId}" style="display: inline" onsubmit="return deleteReviewForm(${item.reviewId})">
                                                            <input type="hidden" value="${item.reviewId}"
                                                                         name="<portlet:namespace/>reviewId" />
                                                            <button type="submit" title="Delete Review Form" value="${item.account}" onClick="setAccountVal(this)"
                                                                    class=" btn btn-outline-danger btn-sm" style="cursor: pointer;"><i class="fas fa-trash-alt"></i>
                                                            </button>
                                                        </form>
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <c:if test = "${currentYearEntries.size() <=0}"><p class="m-3" style="text-align: center;">No data available</p></c:if>
                            </div>
            </div>
         <div class="card">
            <div class="card-header">
               <div class="row">
                  <div class="col pt-2">
                     <h5>Due dates for all stages of Review Year ${displayCurrentAY}</h5>
                  </div>
               </div>
            </div>
            <div class="card-body">
               <div class="row">
                  <div class="col-1 my-auto">
                     <label>Annual</label>
                  </div>
                  <div class="col-11">
                     <ul class="progress-indicator" >
                        <c:forEach items="${finalYearMap}" var="item" varStatus="count">
                        <c:if test="${count.index+1 != fn:length(finalYearMap)}">
                           <li  >
                              <span class="bubble"></span>
                              <span class="stacked-text text-dark">
                                 <span class="subdued">${reviewStateMapAnnual[item.key]}</span><br>
                                 <c:if test="${count.index+2 != fn:length(finalYearMap)}">
                                    <span class="fa fa-calendar"></span>  ${item.value}
                                 </c:if>
                              </span>
                           </li>
                           </c:if>
                        </c:forEach>
                     </ul>
                  </div>
               </div>
               <div class="row mt-4">
                  <div class="col-2 my-auto">
                     <label>Mid Year</label>
                  </div>
                  <div class="col-10">
                     <ul class="progress-indicator" >
                        <c:forEach items="${midYearMap}" var="item" varStatus="count">
                        <c:if test="${count.index+1 != fn:length(midYearMap)}">
                           <li><span class="bubble"></span><span class="stacked-text text-dark">
                              <span class="subdued">${reviewStateMapMidYear[item.key]}</span><br>
                              <c:if test="${count.index+2 != fn:length(midYearMap)}">
                              <span class="fa fa-calendar"></span>  ${item.value}
                              </span></c:if>
                           </li>
                           </c:if>
                        </c:forEach>
                     </ul>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <div class="tab-pane fade" id="second" role="tabpanel" aria-labelledby="second-tab">
      <div class="card">
                      <div class="card-header">
                          <div class="row">
                              <div class="col-2 pt-2">
                                  <h5>Reviews for</h5>
                              </div>
                              <div class="col-3 pt-2">
                                  <select class="mdb-select md-form form-control form-control-sm" onchange="getYearlyEntries()" id="ayForEntries">
                                      <option value="" selected disabled>Select Review Year</option>
                                      <c:forEach items="${yearList}" var="item" varStatus="count">
                                          <option value="${item.key}"  >${item.value}</option>
                                      </c:forEach>
                                  </select>
                              </div>
                          </div>
                      </div>
                      <div class="card-body" id="yearlyReviewDiv" style="display:none">
                          <table  class="table table-sm table-bordered table-responsive-md table-striped text-center" id="previousProjectTable" >
                              <thead class="thead-dark">
                                  <tr>
                                          <th class="text-center align-middle">Account</th>
                                          <th class="text-center align-middle">Review
                                              Type</th>
                                          <th class="text-center align-middle">Account
                                              Type</th>
                                          <th class="text-center align-middle">Manager
                                              Name</th>
                                          <th class="text-center align-middle">Status</th>
                                          <th class="text-center align-middle">Action</th>
                                  </tr>
                              </thead>
                              <tbody id="previousAccountTableBody">
                              </tbody>
                          </table>
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
                     <button class="btn btn-outline-secondary" onClick="downloadUserManual();">Download
                     </button>
                  </div>
               </div>
            </div>
         </div>
</div>