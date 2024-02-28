<%@ include file="/init.jsp" %>
<portlet:resourceURL var="employeeEcodeSearchUrl" id="employeeEcodeSearch"></portlet:resourceURL>
<portlet:resourceURL var="employeeResignationDetailUrl" id="employeeResignationDetail"></portlet:resourceURL>
<portlet:actionURL name="exitWorkflow" var="exitWorkflowUrl"></portlet:actionURL>
<portlet:actionURL name="submitAbscondTerminateForm" var="submitAbscondTerminateFormUrl"></portlet:actionURL>
<portlet:actionURL name="exitClearanceWorkflow" var="exitClearanceWorkflowUrl"></portlet:actionURL>
 <portlet:actionURL name="exitClearancePdfDownload" var="exitClearancePdfDownloadUrl"></portlet:actionURL>
<portlet:actionURL name="exitQuestionnaireFormWorkflow" var="exitQuestionnaireFormWorkflowUrl"></portlet:actionURL>
<portlet:actionURL var="downloadYearlyResignationReport" name="downloadYearlyResignationReport"></portlet:actionURL>
<portlet:resourceURL var="downloadUserManualUrl" id="downloadUserManual"></portlet:resourceURL>
<portlet:resourceURL var="downloadPdfUrl" id="downloadPdf"></portlet:resourceURL>
<portlet:resourceURL var="downloadExcelUrl" id="downloadExcel"></portlet:resourceURL>


<div class="container">
    <input type="hidden" id="yearSelected"  />
    <input type="hidden" id="years" value='${yearJson}'/>
    <input type="hidden" id="statusMapJSON" value='${statusMapJSON}'/>
    <input type="hidden" id="holidayDates" value='${holidayDates}'/>
    <input type="hidden" id="employeeEcodeSearchResourceUrl" value="${employeeEcodeSearchUrl}"/>
    <input type="hidden" id="downloadExcelResourceUrl" value="${downloadExcelUrl}"/>
    <input type="hidden" id="portletNamespace"   value="<portlet:namespace/>" />
    <input type="hidden" id="downloadUserManualResourceUrl" value="${downloadUserManualUrl}"/>
<ul class="nav nav-tabs nav-justified mb-3"  role="tablist">
   <li class="nav-item" id="managerRole" style="display:none" ><a class="nav-link" id="first-tab" data-toggle="tab" href="#first" role="tab" aria-controls="first" aria-selected="true" >Manager/Coordinator/Lead</a></li>
   <li class="nav-item" id="itRole" style="display:none"><a class="nav-link" id="second-tab" data-toggle="tab" href="#second" role="tab" aria-controls="second" aria-selected="false" >IT</a></li>
   <li class="nav-item" id="adminRole" style="display:none"><a class="nav-link" id="third-tab" data-toggle="tab" href="#third" role="tab" aria-controls="third" aria-selected="false" >Admin</a></li>
   <li class="nav-item" id="financeRole" style="display:none"><a class="nav-link" id="fourth-tab" data-toggle="tab" href="#fourth" role="tab" aria-controls="fourth" aria-selected="false" >Finance</a></li>
   <li class="nav-item" id="hrRole" style="display:none"><a class="nav-link" id="fifth-tab" data-toggle="tab" href="#fifth" role="tab" aria-controls="fifth" aria-selected="false" >HR</a></li>
</ul>
<div class="tab-content">
   <div class="tab-pane fade" id="first" role="tabpanel" aria-labelledby="first-tab">
      <div id="managerDiv">
         <div class="card">
            <div class="card-header">
               <div class="row">
                  <div class="col pt-2">
                     <h5>Add Abscond Entries</h5>
                  </div>
               </div>
            </div>
            <div class="card-body">
               <div class="row">
                  <div class="col-2 mt-2 text-right">
                     <label>Ecode</label>
                  </div>
                  <div class="col-4 pt-2 text-left">
                     <input id="searchEmpEcode" class="form-control form-control-sm managerEmpSearch" type="text" name="<portlet:namespace/>ecode"/>
                  </div>
                  <div class="col-2 pt-1 text-right">
                     <button class="btn btn-outline-primary" id="managerSearchButton"   onclick="doSearchUser(this,flag=1);">Search</button>
                  </div>
               </div>
            </div>
            <form id="managerAbscondTerminateForm" method="post" onSubmit="return confirmAbscondTerminateFormSubmission(flag=1);" action="${submitAbscondTerminateFormUrl}">
               <input type="hidden" id="managerActionPerformed" value="${statusMap.get(4).getExitStateDescription()}"  name="<portlet:namespace/>actionPerformed" />
               <input type="hidden" value="Trantor_Manager" name="<portlet:namespace/>role" />
               <div class="card-body" id="employeeDataCard" style="display:none">
                  <div class="form-row mb-2 form-control-sm">
                     <div class="form-group col-md-2 mt-2 text-right">
                        <label>Ecode</label>
                     </div>
                     <div class="form-group col-md-4">
                        <input type="text" class="form-control-plaintext" id="absEcode" name="<portlet:namespace/>empEcode" readonly />
                     </div>
                     <div class="form-group col-md-2 mt-2 text-right">
                        <label>Name</label>
                     </div>
                     <div class="form-group col-md-4">
                        <input type="text" class="form-control-plaintext" id="absName" readonly />
                     </div>
                  </div>
                  <div class="form-row mb-2 form-control-sm">
                     <div class="form-group col-md-2 mt-2 text-right">
                        <label>Manager Name</label>
                     </div>
                     <div class="form-group col-md-4">
                        <input type="text" class="form-control-plaintext" id="absManagerName" readonly />
                     </div>
                     <div class="form-group col-md-2 mt-2 text-right">
                        <label>Band</label>
                     </div>
                     <div class="form-group col-md-4">
                        <input type="text" class="form-control-plaintext" id="absBand" readonly />
                     </div>
                  </div>
                  <div class="form-row mb-2 form-control-sm">
                     <div class="form-group col-md-2 mt-2 text-right">
                        <label>Account Name</label>
                     </div>
                     <div class="form-group col-md-4">
                        <input type="text" class="form-control-plaintext" id="absProjectName" readonly />
                     </div>
                     <div class="form-group col-md-2 mt-2 text-right">
                        <label>Location</label>
                     </div>
                     <div class="form-group col-md-4">
                        <input type="text" class="form-control-plaintext" id="absLocation" readonly />
                     </div>
                  </div>
                  <div class="form-row mb-2 form-control-sm">
                     <div class="form-group col-md-2 mt-2 text-right">
                        <label>DOJ</label>
                     </div>
                     <div class="form-group col-md-4">
                        <input type="text" class="form-control-plaintext" id="absDoj" readonly />
                     </div>
                  </div>
                  <div class="form-row mb-2 form-control-sm">
                     <div class="form-group col-md-2 mt-2 text-right" id="absSeparationTypeLabel">
                        <label>Separation Type</label>
                     </div>
                     <div class="form-group col-md-4">
                        <input type="text" class="form-control-plaintext" id="absSeparationType" value="${statusMap.get(4).getExitStateDescription()}"  name="<portlet:namespace/>abscondTerminateSeparationType" readonly />
                     </div>
                  </div>
                  <div class="col pt-2 pb-2 text-center">
                     <h5>Section 1 - Resignation Details</h5>
                  </div>
                  <div class="form-row mb-2 form-control-sm">
                     <div class="form-group col-md-2 mt-2 text-right">
                        <label>Replacement Plan*</label>
                     </div>
                     <div class="form-group col-md-4">
                        <select class="mdb-select md-form form-control form-control-sm manager" id="replacementPlan" required="required" name="<portlet:namespace/>replacementPlan">
                           <option value="" disabled selected>Choose your option</option>
                           <option value="Not Needed">Not Needed</option>
                           <option value="Requisition Opened">Requisition Opened</option>
                        </select>
                     </div>
                     <div id= "replacementFormId"  style="display:none" class="form-group col-md-6  text-left">
                        <label class="ml-5 mt-1">Click <a href="https://docs.google.com/forms/d/e/1FAIpQLSfsC5JLdgV0sg3D7tGYZwITd_acFGq2MFEkUt5ey2oTBSQLuA/viewform" target="_blank">here</a> to access Resource Requisition Form</label>
                     </div>
                  </div>
                  <div class="form-row mb-2 form-control-sm"  id="abscondDiv">
                     <div class="form-group col-md-2 mt-2 text-right" id="abscondDateLabel">
                        <label>Tentative LWD*</label>
                     </div>
                     <div class="form-group col-md-4">
                        <input type="text" id="abscondDate"  autocomplete="off" style="cursor: pointer" onkeydown="return false"
                           value="" class="form-control form-control-sm" required name="<portlet:namespace/>tentativeAbscondDate" />
                     </div>
                  </div>
                  <div class="col pt-3 pb-2 text-center">
                     <h5>Section 2 - Employee Evaluation form</h5>
                  </div>
                  <div class="form-row mb-2 form-control-sm">
                     <div class="form-group col-md-2 mt-2 text-right">
                        <label>Key to Project</label>
                     </div>
                     <div class="form-group col-md-4">
                        <select class="mdb-select md-form form-control form-control-sm manager" id="keyToProject"  name="<portlet:namespace/>keyToProject">
                           <option value="" selected>Choose your option</option>
                           <option  value="Yes">Yes</option>
                           <option value="No">No</option>
                        </select>
                     </div>
                     <div class="form-group col-md-2 mt-2 text-right">
                        <label>Client Impact*</label>
                     </div>
                     <div class="form-group col-md-4">
                        <select class="mdb-select md-form form-control form-control-sm manager" id="clientImpact" required="required" name="<portlet:namespace/>clientImpact">
                           <option value="" selected>Choose your option</option>
                           <option  value="Yes">Yes</option>
                           <option   value="No">No</option>
                        </select>
                     </div>
                  </div>
                  <div class="form-row mb-2 form-control-sm">
                     <div class="form-group col-md-2 mt-2 text-right">
                        <label>Performance Evaluation</label>
                     </div>
                     <div class="form-group col-md-4">
                        <select class="mdb-select md-form form-control form-control-sm manager" id="rating"   name="<portlet:namespace/>rating">
                           <option value="" selected>Choose your option</option>
                           <option  value="Consistently Exceeds Expectations">Consistently Exceeds Expectations</option>
                           <option  value="Sometimes Exceeds Expectations">Sometimes Exceeds Expectations</option>
                           <option   value="Consistently Meets Expectations">Consistently Meets Expectations</option>
                           <option   value="Inconsistently Meets Expectations">Inconsistently Meets Expectations</option>
                           <option  value="Does not Meet Expectations">Does not Meet Expectations</option>
                        </select>
                     </div>
                     <div class="form-group col-md-2  text-right">
                        <label>Is employee eligible for rehire?*</label>
                     </div>
                     <div class="form-group col-md-4">
                        <select class="mdb-select md-form form-control form-control-sm manager" id="eligibleForRehire"
                        <c:if test="${employeeDetails.statusInt !=4 }">required="required" </c:if>
                        name="<portlet:namespace/>eligibleForRehire">
                        <option  value="Yes">Yes</option>
                        <option  value="No" selected >No</option>
                        </select>
                     </div>
                  </div>
                  <div class="form-row mb-2 form-control-sm">
                     <div class="form-group col-md-2  text-right">
                        <label>Any other issue including but not limited to attitude, work timings, etc.?</label>
                     </div>
                     <div class="form-group col-md-4 mt-2">
                        <select class="mdb-select md-form form-control form-control-sm manager" id="otherIssue"   name="<portlet:namespace/>otherIssue">
                           <option value="" selected>Choose your option</option>
                           <option value="Yes">Yes</option>
                           <option  value="No">No</option>
                        </select>
                     </div>
                     <div class="form-group col-md-2 mt-2 text-right">
                        <label>Manager Comments</label>
                     </div>
                     <div class="form-group col-md-4">
                        <textarea  rows="3" class="form-control form-control-sm manager" maxlength="500"
                           style="height: 69px;" id="managerComment" cols="47" onblur="$(this).val($(this).val().trim())"
                           name="<portlet:namespace/>managerComment"></textarea>
                     </div>
                  </div>
                  <div class="row mt-4">
                     <div class="col-12 text-center mt-4" id="abscondTerminateButtonId" >
                        <button class="btn btn-outline-danger mt-2" >Submit</button>
                     </div>
                  </div>
               </div>
            </form>
         </div>
         <div class="card">
            <div class="card-header">
               <div class="row">
                  <div class="col pt-2">
                     <h5>Resignations</h5>
                  </div>
               </div>
            </div>
            <div class="card-body">
               <table class="table order-list1 table-bordered table-responsive-md table-striped text-center" id="managerResignationsTable">
                  <thead class="thead-dark">
                     <tr>
                        <th class="text-center align-middle" >Date of Resignation</th>
                        <th class="text-center align-middle" >Ecode</th>
                        <th class="text-center align-middle" >Employee Name</th>
                        <th class="text-center align-middle" >Manager Name</th>
                        <th class="text-center align-middle" >Status</th>
                        <th class="text-center align-middle" >Resign Form</th>
                        <th class="text-center align-middle" >Exit Form</th>
                     </tr>
                  </thead>
                  <tbody>
                     <c:forEach items="${employeeResignListAssignedToManager}" var="item" varStatus="count">
                        <tr>
                           <td>${item.resignationDate}</td>
                           <td>${item.employeeCode}</td>
                           <td>${item.employeeName}</td>
                           <td>${item.managerName}</td>
                           <td>${item.status}</td>
                           <td>
                              <form action="${exitWorkflowUrl}" method="post" style="display: inline">
                                 <input type="hidden" value="${item.resignationId}"
                                    name="<portlet:namespace/>resignationId" />
                                 <input type="hidden" value="Trantor_Manager"
                                    name="<portlet:namespace/>roleType" />
                                 <button type="submit" class=" btn btn-outline-primary btn-sm" title="Go to Resignation Form"
                                    style="cursor: pointer;"><i class="fas fa-edit"></i></button>
                              </form>
                           </td>
                           <td>
                           <c:if test="${item.showQuesFormManager=='true'}">
                              <form action="${exitClearanceWorkflowUrl}" method="post" style="display: inline">
                                 <input type="hidden" value="${item.resignationId}"
                                    name="<portlet:namespace/>resignationId" />
                                 <input type="hidden" value="Trantor_Manager"
                                    name="<portlet:namespace/>roleType" />
                                 <button type="submit" class=" btn btn-outline-primary btn-sm" title="Go to Clearance Form "
                                 style="cursor: pointer;"><i class="fas fa-edit"></i></button>
                              </form>
                              </c:if>
                           </td>
                        </tr>
                     </c:forEach>
                  </tbody>
               </table>
            </div>
         </div>
         <div class="card mt-2">
            <div class="card-header">
               <div class="row">
                  <div class="col pt-2">
                     <h5>User Manual </h5>
                  </div>
                  <div class="col-3 text-right" ><button class="btn btn-outline-secondary" onClick="downloadUserManual(1);">Download</button></div>
               </div>
            </div>
         </div>
      </div>
   </div>
   <div class="tab-pane fade" id="second" role="tabpanel" aria-labelledby="second-tab">
      <div id="itDiv">
         <div class="card">
            <div class="card-header">
               <div class="row">
                  <div class="col pt-2">
                     <h5>Resignations</h5>
                  </div>
               </div>
            </div>
            <div class="card-body">
               <table class="table order-list1 table-bordered table-responsive-md table-striped text-center" id="itExitFormsTable">
                  <thead class="thead-dark">
                     <tr>
                        <th class="text-center align-middle" >Date of Resignation</th>
                        <th class="text-center align-middle" >Ecode</th>
                        <th class="text-center align-middle" >Employee Name</th>
                        <th class="text-center align-middle" >Manager Name</th>
                        <th class="text-center align-middle" >Status</th>
                        <th class="text-center align-middle" >IT Asset List</th>
                        <th class="text-center align-middle" >Clearance Form</th>
                     </tr>
                  </thead>
                  <tbody>
                     <c:forEach items="${employeeResignList}" var="item" varStatus="count">
                        <c:if test="${item.statusInt==2 || item.statusInt==4 || item.statusInt==5 || item.managerFormSubmitted=='true'}">
                           <tr>
                              <td>${item.resignationDate}</td>
                              <td>${item.employeeCode}</td>
                              <td>${item.employeeName}</td>
                              <td>${item.managerName}</td>
                              <td>${item.status}</td>
                              <td>
                                 <form action="${exitWorkflowUrl}" method="post" style="display: inline">
                                    <input type="hidden" value="${item.resignationId}"
                                       name="<portlet:namespace/>resignationId" />
                                    <input type="hidden" value="Trantor_IT"
                                       name="<portlet:namespace/>roleType" />
                                    <button type="submit" class=" btn btn-outline-primary btn-sm" title="Go to Resignation Form"
                                       style="cursor: pointer;"><i class="fas fa-edit"></i></button>
                                 </form>
                              </td>
                              <td>
                              <c:if test="${item.managerFormSubmitted=='true'}">
                                 <form action="${exitClearanceWorkflowUrl}" method="post" style="display: inline">
                                    <input type="hidden" value="${item.resignationId}"
                                       name="<portlet:namespace/>resignationId" />
                                    <input type="hidden" value="Trantor_IT"
                                       name="<portlet:namespace/>roleType" />
                                    <button type="submit" class=" btn btn-outline-primary btn-sm" title="Go to Clearance Form "
                                    style="cursor: pointer;"><i class="fas fa-edit"></i></button>
                                 </form>
                                 </c:if>
                              </td>
                           </tr>
                        </c:if>
                     </c:forEach>
                  </tbody>
               </table>
            </div>
         </div>
         <div class="card mt-2">
            <div class="card-header">
               <div class="row">
                  <div class="col pt-2">
                     <h5>User Manual </h5>
                  </div>
                  <div class="col-3 text-right" ><button class="btn btn-outline-secondary" onClick="downloadUserManual(2);">Download</button></div>
               </div>
            </div>
         </div>
      </div>
   </div>
   <div class="tab-pane fade" id="third" role="tabpanel" aria-labelledby="third-tab">
      <div id="adminDiv">
         <div class="card">
            <div class="card-header">
                     <h5>Exit Forms</h5>
            </div>
            <div class="card-body">
               <table class="table order-list1 table-bordered table-responsive-md table-striped text-center" id="adminExitFormsTable">
                  <thead class="thead-dark">
                     <tr>
                        <th class="text-center align-middle" >Date of Resignation</th>
                        <th class="text-center align-middle" >Ecode</th>
                        <th class="text-center align-middle" >Employee Name</th>
                        <th class="text-center align-middle" >Manager Name</th>
                        <th class="text-center align-middle" >Status</th>
                        <th class="text-center align-middle" >Clearance Form</th>
                     </tr>
                  </thead>
                  <tbody>
                     <c:forEach items="${employeeResignList}" var="item" varStatus="count">
                        <c:if test="${item.clearanceFormGenerated=='true' && item.statusInt !=6 && item.statusInt !=1}">
                           <td>${item.resignationDate}</td>
                           <td>${item.employeeCode}</td>
                           <td>${item.employeeName}</td>
                           <td>${item.managerName}</td>
                           <td>${item.status}</td>
                           <td class="py-0">
                              <form action="${exitClearanceWorkflowUrl}" method="post" style="display: inline">
                                 <input type="hidden" value="${item.resignationId}"
                                    name="<portlet:namespace/>resignationId" />
                                 <input type="hidden" value="Trantor_Admin"
                                    name="<portlet:namespace/>roleType" />
                                 <button type="submit" class=" btn btn-outline-primary btn-sm" title="Go to Clearance Form"
                                    style="cursor: pointer;"><i class="fas fa-edit"></i></button>
                              </form>
                           </td>
                           </tr>
                        </c:if>
                     </c:forEach>
                  </tbody>
               </table>
            </div>
         </div>
         <div class="card mt-2">
            <div class="card-header">
               <div class="row">
                  <div class="col pt-2">
                     <h5>User Manual </h5>
                  </div>
                  <div class="col-3 text-right" ><button class="btn btn-outline-secondary" onClick="downloadUserManual(3);">Download</button></div>
               </div>
            </div>
         </div>
      </div>
   </div>
   <div class="tab-pane fade" id="fourth" role="tabpanel" aria-labelledby="fourth-tab">
      <div id="finance">
         <div class="card">
            <div class="card-header">
                     <h5>Exit Forms</h5>
            </div>
            <div class="card-body">
               <table class="table order-list1 table-bordered table-responsive-md table-striped text-center" id="financeExitFormsTable">
                  <thead class="thead-dark">
                     <tr>
                        <th class="text-center align-middle" >Date of Resignation</th>
                        <th class="text-center align-middle" >Ecode</th>
                        <th class="text-center align-middle" >Employee Name</th>
                        <th class="text-center align-middle" >Manager Name</th>
                        <th class="text-center align-middle" >Status</th>
                        <th class="text-center align-middle" >Clearance Form</th>
                     </tr>
                  </thead>
                  <tbody>
                     <c:forEach items="${employeeResignList}" var="item" varStatus="count">
                        <c:if test="${item.clearanceFormGenerated=='true' && item.statusInt !=6}">
                           <td>${item.resignationDate}</td>
                           <td>${item.employeeCode}</td>
                           <td>${item.employeeName}</td>
                           <td>${item.managerName}</td>
                           <td>${item.status}</td>
                           <td class="py-0">
                              <form action="${exitClearanceWorkflowUrl}" method="post" style="display: inline">
                                 <input type="hidden" value="${item.resignationId}"
                                    name="<portlet:namespace/>resignationId" />
                                 <input type="hidden" value="Trantor_Finance"
                                    name="<portlet:namespace/>roleType" />
                                 <button type="submit" class=" btn btn-outline-primary btn-sm" title="Go to Clearance Form"
                                    style="cursor: pointer;"><i class="fas fa-edit"></i></button>
                              </form>
                           </td>
                           </tr>
                        </c:if>
                     </c:forEach>
                  </tbody>
               </table>
            </div>
         </div>
         <div class="card mt-2">
            <div class="card-header">
               <div class="row">
                  <div class="col pt-2">
                     <h5>User Manual </h5>
                  </div>
                  <div class="col-2 text-right" ><button class="btn btn-outline-secondary" onClick="downloadUserManual(4);">Download</button></div>
               </div>
            </div>
         </div>
      </div>
   </div>
   <div class="tab-pane fade" id="fifth" role="tabpanel" aria-labelledby="fifth-tab">
      <div id="hrDiv">
         <div class="card">
            <div class="card-header">
               <div class="row">
                  <div class="col pt-2">
                     <h5>Add Abscond/Terminate Entries</h5>
                  </div>
               </div>
            </div>
            <div class="card-body">
               <div class="row">
                  <div class="col-2 mt-2 text-right">
                     <label>Ecode</label>
                  </div>
                  <div class="col-4 pt-2 text-left">
                     <input id="searchEcode" class="form-control form-control-sm employeeSearch" type="text" name="<portlet:namespace/>ecode"/>
                  </div>
                  <div class="col-2 pt-1 text-right">
                     <button class="btn btn-outline-primary" id="searchButton"  onclick="doSearchUser(this,flag=2);">Search</button>
                  </div>
               </div>
            </div>
            <form id="abscondTerminateForm" method="post" onSubmit="return confirmAbscondTerminateFormSubmission(flg=2);" action="${submitAbscondTerminateFormUrl}">
               <input type="hidden" id="actionPerformed" value="" name="<portlet:namespace/>actionPerformed" />
               <input type="hidden" value="Trantor_HR" name="<portlet:namespace/>role" />
               <div class="card-body" id="employeeDetailCard" style="display:none">
                  <div class="form-row mb-2 form-control-sm">
                     <div class="form-group col-md-2 mt-2 text-right">
                        <label>Ecode</label>
                     </div>
                     <div class="form-group col-md-4">
                        <input type="text" class="form-control-plaintext" id="ecode" name="<portlet:namespace/>empEcode" readonly />
                     </div>
                     <div class="form-group col-md-2 mt-2 text-right">
                        <label>Name</label>
                     </div>
                     <div class="form-group col-md-4">
                        <input type="text" class="form-control-plaintext" id="name" readonly />
                     </div>
                  </div>
                  <div class="form-row mb-2 form-control-sm">
                     <div class="form-group col-md-2 mt-2 text-right">
                        <label>Manager Name</label>
                     </div>
                     <div class="form-group col-md-4">
                        <input type="text" class="form-control-plaintext" id="managerName" readonly />
                     </div>
                     <div class="form-group col-md-2 mt-2 text-right">
                        <label>Band</label>
                     </div>
                     <div class="form-group col-md-4">
                        <input type="text" class="form-control-plaintext" id="band" readonly />
                     </div>
                  </div>
                  <div class="form-row mb-2 form-control-sm">
                     <div class="form-group col-md-2 mt-2 text-right">
                        <label>Account Name</label>
                     </div>
                     <div class="form-group col-md-4">
                        <input type="text" class="form-control-plaintext" id="projectName" readonly />
                     </div>
                     <div class="form-group col-md-2 mt-2 text-right">
                        <label>Location</label>
                     </div>
                     <div class="form-group col-md-4">
                        <input type="text" class="form-control-plaintext" id="location" readonly />
                     </div>
                  </div>
                  <div class="form-row mb-2 form-control-sm">
                     <div class="form-group col-md-2 mt-2 text-right">
                        <label>DOJ</label>
                     </div>
                     <div class="form-group col-md-4">
                        <input type="text" class="form-control-plaintext" id="doj" readonly />
                     </div>
                     <div class="form-group col-md-2 mt-2 text-right">
                        <label>Comments*</label>
                     </div>
                     <div class="form-group col-md-4">
                        <textarea type="text" class="form-control form-control-sm" style="height: 69px;" id="hrComments" onblur="$(this).val($(this).val().trim())"
                           maxlength="500" cols="47" name="<portlet:namespace/>hrComments" required ></textarea>
                     </div>
                  </div>
                  <div class="form-row mb-2 form-control-sm">
                     <div class="form-group col-md-2 mt-2 text-right" id="absTerSeparationTypeLabel">
                        <label>Separation Type*</label>
                     </div>
                     <div class="form-group col-md-4">
                        <select class="mdb-select md-form form-control form-control-sm manager" id="absTerSeparationType" onchange="changeSeparationType()" required="required" name="<portlet:namespace/>abscondTerminateSeparationType">
                           <option value="" selected>Choose your option</option>
                           <option id="Absconded"   value="${statusMap.get(4).getExitStateDescription()}">Abscond</option>
                           <option id="Terminated"  value="${statusMap.get(5).getExitStateDescription()}">Terminate</option>
                        </select>
                     </div>
                  </div>
                  <div class="form-row form-control-sm mb-2 col-md-12" style="display:none" id="abscondLebelDiv">
                     <div class="form-group col-md-2 mt-2 text-right" >
                        <label>Final LWD*</label>
                     </div>
                     <div class="form-group col-md-4 position:relative">
                        <input type="text" id="absFinalLwd" required="required" style="cursor: pointer" onkeydown="return false"
                           value=""
                           class="form-control form-control-sm" autocomplete="off" name="<portlet:namespace/>mFinalLwd" />
                     </div>
                  </div>
                  <div class="form-row mb-2 form-control-sm" style="display:none" id="terminationTypeDiv">
                     <div class="form-group col-md-2 mt-2 text-right" id="terminationStartDateLabel">
                        <label>Employee Notification Date*</label>
                     </div>
                     <div class="form-group col-md-4">
                        <input type="text" id="terminationStartDate"  autocomplete="off" style="cursor: pointer" onkeydown="return false" onchange="setNoticePeriodDate()"
                           value="" class="form-control form-control-sm" name="<portlet:namespace/>terminationStartDate" />
                     </div>
                     <div class="form-group col-md-2 mt-2 text-right">
                        <label>Type*</label>
                     </div>
                     <div class="form-group col-md-4">
                        <select class="mdb-select md-form form-control form-control-sm manager" id="terminationType"  name="<portlet:namespace/>terminationType">
                           <option value="" selected>Choose your option</option>
                           <option value="Mutual">Mutual</option>
                           <option value="Behavior Issues">Behavior Issues</option>
                           <option value="Ethical Issues">Ethical Issues</option>
                           <option value="No project allocated">No project allocated</option>
                           <option value="Not willing to work in profile assigned"> Not willing to work in profile assigned</option>
                           <option value="Performance Issue">Performance Issue</option>
                           <option value="Project Ended">Project Ended</option>
                        </select>
                     </div>
                  </div>
                  <div class="form-row mb-2 form-control-sm pt-2" style="display:none" id="terminationNoticePeriodDiv">
                     <div class="form-group col-md-2 mt-2 text-right">
                        <label>Last Working Day</label>
                     </div>
                     <div class="form-group col-md-4">
                        <input type="text" class="form-control form-control-sm" autocomplete="off" id="lwd" value="" name="<portlet:namespace/>lwd" onchange="setNoticePeriodDate()"/>
                     </div>
                     <div class="form-group col-md-2 mt-2 text-right">
                        <label>Notice Period</label>
                     </div>
                     <div class="form-group col-md-4">
                        <input type="text" class="form-control form-control-sm" readonly id="terminationNoticePeriod" readonly name="<portlet:namespace/>terminationNoticePeriod"/>
                     </div>
                  </div>
                  <div class="form-row mb-2 form-control-sm pt-2" style="display:none" id="emailIdField">
                     <div class="form-group col-md-2 mt-2 text-right">
                        <label>Personal Email Id</label>
                     </div>
                     <div class="form-group col-md-4">
                        <input type="text" id="emailId" class="form-control form-control-sm" onblur="validEmail()"  maxlength="75" autocomplete="off"  name="<portlet:namespace/>emailId"/>
                     </div>
                  </div>
                  <div class="row mt-4">
                     <div class="col-6 text-right" id="abscondTerminateButtonId" >
                        <button class="btn btn-outline-danger" >Submit</button>
                     </div>
                  </div>
               </div>
            </form>
         </div>
         <div class="card">
            <div class="card-header">
               <div class="row">
                  <div class="col-7 pt-2">
                     <h5>Resignations</h5>
                  </div>
                  <div class="col-3 pt-2 float-right">
                     <select class="mdb-select md-form form-control form-control-sm"  id="selectedYear" onchange="setYearValue()">
                        <option   value="" disabled selected>Choose an option</option>
                        <c:forEach items="${years}" var="years" varStatus="count">
                           <option   value="${years}">${years}</option>
                        </c:forEach>
                     </select>
                  </div>

                  <a class="btn btn-outline-secondary" style="cursor: pointer;" onclick="setExcel(this)" style="display: inline" title="Download Report " id="excel"> Download
                                                                                                                                                        </a>
               </div>
            </div>
            <div class="card-body">
               <table class="table order-list1 table-bordered table-responsive-md table-striped text-center" id="hrResignationsTable">
                  <thead class="thead-dark">
                     <tr>
                        <th class="text-center align-middle fn_no-sort" >Date of Resignation</th>
                        <th class="text-center align-middle" >Last Working Day</th>
                        <th class="text-center align-middle fn_no-sort" >Employee Name</th>
                        <th class="text-center align-middle fn_no-sort" >Employee's Reason</th>
                        <th class="text-center align-middle fn_no-sort" >Status</th>
                        <th class="text-center align-middle fn_no-sort" >Resignation Form</th>
                        <th class="text-center align-middle fn_no-sort" >Exit Questionnaire</th>
                        <th style="width:200px!important;" class="text-center align-middle fn_no-sort" >Clearance Form</th>
                     </tr>
                  </thead>
                  <tbody>
                     <c:forEach items="${employeeResignList}" var="item" varStatus="count">
                        <tr>
                           <td>${item.resignationDate}</td>
                           <td><c:choose><c:when test="${item.lastWorkingDate == null || item.lastWorkingDate == ''}">---</c:when>
                           <c:otherwise>${item.lastWorkingDate}</c:otherwise></c:choose></td>
                           <td>${item.employeeName}(${item.employeeCode})</td>
                           <td title="${item.hrComment}">${item.reason}</td>
                           <td title="${item.empStatus}">
                              <c:choose>
                                 <c:when test="${(item.statusInt == 4 || item.statusInt == 5) && item.clearanceCompleted=='false'}">
                                    <span class="text-danger">
                                       <c:out value="${item.empStatus}"/>
                                    </span>
                                 </c:when>
                                 <c:otherwise>
                                    ${item.empStatus}
                                 </c:otherwise>
                              </c:choose>
                           </td>
                           <td>
                              <form action="${exitWorkflowUrl}" method="post" style="display: inline">
                                 <input type="hidden" value="${item.resignationId}" name="<portlet:namespace/>resignationId" />
                                 <input type="hidden" value="Trantor_HR" name="<portlet:namespace/>roleType" />
                                 <button  type="submit" class=" btn btn-outline-primary btn-sm" title="Go to Resignation Form"
                                    style="cursor: pointer;"><i class="fas fa-tasks"></i></button>
                              </form>
                           </td>
                           <td>
                           <c:if test="${item.questionnaireFormSubmitted=='true'}">
                              <form action="${exitQuestionnaireFormWorkflowUrl}" method="post" style="display: inline">
                                 <input type="hidden" id="resignationId"
                                    value="${item.resignationId}" name="<portlet:namespace/>resignationId" />
                                 <button type="submit" class=" btn btn-outline-primary btn-sm" title="${item.questionnaireFormSubmitted=='false' ? 'Form is not submitted' : 'go to Questionnaire Form'}"
                                 style="cursor: pointer;"><i class="fas fa-edit"></i></button>
                              </form>
                              </c:if>
                           </td>
                           <td>
                              <div style="display: inline">
                              <c:if test="${item.clearanceFormGenerated=='true'}">
                                 <form action="${exitClearanceWorkflowUrl}" method="post" style="display: inline">
                                    <input type="hidden" value="${item.resignationId}"
                                       name="<portlet:namespace/>resignationId" />
                                    <input type="hidden" value="Trantor_HR"
                                       name="<portlet:namespace/>roleType" />
                                    <button type="submit" class=" btn btn-outline-primary btn-sm mr-3" title="Go to Clearance Form"
                                    style="cursor: pointer;"><i class="fas fa-edit"></i></button>
                                 </form>
                                 </c:if>
                                 <c:if test="${item.showPdfDownloadButton=='true'}">
                                  <a class="btn btn-outline-primary btn-sm" style="cursor: pointer;"  style="display: inline" title="Download Report"  <a href="<%=downloadPdfUrl %>&amp;<portlet:namespace/>resignationId=${item.resignationId}"> <i class="fa fa-download"></i>
                                                                                                                                      </a></c:if>
                              </div>
                           </td>
                        </tr>
                     </c:forEach>
                  </tbody>
               </table>
            </div>
         </div>
         <div class="card mt-2">
            <div class="card-header">
               <div class="row">
                  <div class="col pt-2">
                     <h5>User Manual </h5>
                  </div>
                  <div class="col-3 text-right" ><button class="btn btn-outline-secondary" onClick="downloadUserManual(5);">Download</button></div>
               </div>
            </div>
         </div>
      </div>
   </div>
</div>
<script>
   var holidays = new Array();
$(document).ready(function ()
{
   var years = JSON.parse($('#years').val());

   if (years != '' && null != years)
   {
      years.forEach(function (year)
      {
         if (new Date().getFullYear() == year)
         {
            $("#selectedYear").val(new Date().getFullYear());
         }
      });
   }

   var holidayDates = JSON.parse($('#holidayDates').val());
      if (holidayDates != '' && null != holidayDates)
      {
         holidayDates.forEach(function (holidayDate)
         {
            holidays.push(holidayDate);
         });
      }
    });
function setExcel(obj){

var hrefVal= $('#downloadExcelResourceUrl').val()+"&amp;<portlet:namespace/>year=2023";
$('#excel').attr("href",hrefVal);
}
</script>