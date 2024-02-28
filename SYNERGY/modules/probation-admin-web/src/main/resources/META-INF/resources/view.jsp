<%@ include file="/init.jsp" %>

<portlet:actionURL name="abscondProbation" var="abscondProbationUrl"></portlet:actionURL>
<portlet:actionURL name="probationWorkflow" var="probationWorkflowUrl"></portlet:actionURL>
<portlet:resourceURL var="downloadHrUserManualUrl" id="downloadHrUserManual"></portlet:resourceURL>
<portlet:resourceURL var="downloadManagerUserManualUrl" id="downloadManagerUserManual"></portlet:resourceURL>
<portlet:resourceURL var="downloadPendingProbationsUrl" id="downloadPendingProbations"></portlet:resourceURL>
<portlet:resourceURL var="downloadCompletedProbationsUrl" id="downloadCompletedProbations"></portlet:resourceURL>

<div class="container">
   <input type="hidden" id="portletNamespace" value="<portlet:namespace/>"/>
   <input type="hidden" id="downloadHrUserManualResourceUrl" value="${downloadHrUserManualUrl}"/>
   <input type="hidden" id="downloadManagerUserManualResourceUrl" value="${downloadManagerUserManualUrl}"/>

   <ul class="nav nav-tabs nav-justified mb-3"  role="tablist">
      <li class="nav-item"><a class="nav-link" id="first-tab" data-toggle="tab" href="#first" role="tab" aria-controls="first" aria-selected="true" >Manager/Reviewer</a></li>
      <li class="nav-item"><a class="nav-link" id="second-tab" data-toggle="tab" href="#second" role="tab" aria-controls="second" aria-selected="true" >HR</a></li>
   </ul>
   <div class="tab-content">
      <div class="tab-pane fade" id="first" role="tabpanel" aria-labelledby="first-tab">
         <div class="card">
            <div class="card-header">
               <div class="row">
                  <div class="col pt-2">
                     <h5>My Requests</h5>
                  </div>
               </div>
            </div>
            <div class="card-body">
               <table class="table table-sm table-bordered table-responsive-md table-striped text-center" id="managerTable">
                  <thead class="thead-dark">
                     <tr>
                        <th class="text-center align-middle" width="15%">Ecode</th>
                        <th class="text-center align-middle" width="25%">Name</th>
                        <th class="text-center align-middle" width="25%">Joining Date</th>
                        <th class="text-center align-middle" width="25%">Probation Status</th>
                        <th class="text-center align-middle" width="10%">Action</th>
                     </tr>
                  </thead>
                  <tbody>
                     <c:forEach items="${managerList}" var="item" varStatus="count">
                        <tr>
                           <td>${item.empCode}</td>
                           <td>${item.empName}</td>
                           <td>${item.empDoj}</td>
                           <td>
                              <c:set var = "probationStateName" scope = "session" value = "${item.probationStateName}"/>
                              <c:if test = "${(item.probationState == 1 )}">
                                 <span style="color: green;">
                                    <c:out value = "${probationStateName}"/>
                                 </span>
                              </c:if>
                              <c:if test = "${(item.probationState == 2 )}">
                                 <span style="color: red;">
                                    <c:out value = "${probationStateName}"/>
                                 </span>
                              </c:if>
                              <c:if test = "${(item.probationState == 3 )}">
                                 <span style="color: blue;">
                                    <c:out value = "${probationStateName}"/>
                                 </span>
                              </c:if>
                              <c:if test = "${(item.probationState == 4 )}">
                                 <span style="color: blue;">
                                    <c:out value = "${probationStateName}"/>
                                 </span>
                              </c:if>
                              <c:if test = "${(item.probationState == 0 )}">
                                 <span style="color: black;">
                                    <c:out value = "${probationStateName}"/>
                                 </span>
                              </c:if>
                              <c:if test = "${(item.probationState == 5 )}">
                                 <span style="color: red;">
                                    <c:out value = "${probationStateName}"/>
                                 </span>
                              </c:if>
                           </td>
                           <td class="py-0">
                              <form  method="post" action="${probationWorkflowUrl}" style="display:inline;" >
                                <input type="hidden" name="<portlet:namespace/>probationId" value="${item.empCode}"/>
                                <c:if test = "${(item.probationState == 0 )}"><button class="btn btn-outline-primary btn-sm" type="submit" style="cursor: pointer;">Action</button></c:if>
                                <c:if test = "${(item.probationState != 0 )}"><button class="btn btn-outline-primary btn-sm" type="submit"   style="cursor: pointer;">View</button></c:if>
                              </form>
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
                     <h5>User Manual</h5>
                  </div>
                  <div class="col-2 text-right" ><button class="btn btn-outline-secondary" onClick="downloadManagerUserManual();">Download</button></div>
               </div>
            </div>
         </div>
      </div>
      <div class="tab-pane fade" id="second" role="tabpanel" aria-labelledby="second-tab">
         <div class="card pb-2" >
            <div class="card-header">
                  <div class="row">
                     <div class="col pt-2">
                        <h5>Pending Probations</h5>
                     </div>
                     <div class="col-2 text-right">
                        <a class="btn btn-outline-secondary" href="<%= downloadPendingProbationsUrl %>">Download</a>
                     </div>
                  </div>
            </div>
            <div class="card-body">
               <table class="table table-sm table-bordered table-responsive-md table-striped text-center" id="pendingTable">
                  <thead class="thead-dark">
                     <tr>
                        <th class="text-center align-middle" width="5%">Ecode</th>
                        <th class="text-center align-middle" width="25%">Name</th>
                        <th class="text-center align-middle" width="25%">Manager Email</th>
                        <th class="text-center align-middle" width="15%">Probation Status</th>
                        <th class="text-center align-middle" width="20%">Probation Start Date</th>
                        <th class="text-center align-middle" width="10%">Action</th>
                     </tr>
                  </thead>
                  <tbody>
                     <c:forEach items="${hrPendingList}" var="item" varStatus="count">
                        <tr>
                           <td>${item.empCode}</td>
                           <td>${item.empName}</td>
                           <td>${item.managerEmail}</td>
                           <td>${item.probationStateName}</td>
                           <td>${item.createDate}</td>
                           <td class="py-0">
                              <form  method="post" action="${probationWorkflowUrl}" style="display:inline;" >
                                 <input type="hidden" name="<portlet:namespace/>probationId" value="${item.empCode}"/>
                                 <button class="btn btn-outline-primary btn-sm" title="Go to form" style="cursor: pointer;" type="submit" ><i class="fas fa-edit"></i></button>
                              </form>
                              <form  method="post" action="${abscondProbationUrl}" style="display:inline;" >
                                 <input type="hidden" name="<portlet:namespace/>abscondId" value="${item.empCode}"/>
                                 <button class=" btn btn-outline-primary btn-sm" style="cursor: pointer;" title="Abscond" type="submit" onclick="return confirm('Are you sure you want to close probation for \'${item.empName}\' ?');" ><i class="fa-solid fa-ban"></i></button>
                              </form>
                           </td>
                        </tr>
                     </c:forEach>
                  </tbody>
               </table>
            </div>
         </div>
         <div class="card pb-2" >
            <div class="card-header">
                  <div class="row">
                     <div class="col pt-2">
                        <h5>Completed Probations</h5>
                     </div>
                     <div class="col-2 text-right">
                        <a class="btn btn-outline-secondary" href="<%= downloadCompletedProbationsUrl %>">Download</a>
                     </div>
                  </div>
            </div>
            <div class="card-body">
               <table class="table table-sm table-bordered table-responsive-md table-striped text-center" id="completedTable">
                  <thead class="thead-dark">
                     <tr>
                        <th class="text-center align-middle" width="5%">Ecode</th>
                        <th class="text-center align-middle" width="25%">Name</th>
                        <th class="text-center align-middle" width="25%">Manager Email</th>
                        <th class="text-center align-middle" width="15%">Probation Status</th>
                        <th class="text-center align-middle" width="20%">Completion Date</th>
                        <th class="text-center align-middle" width="10%">Action</th>
                     </tr>
                  </thead>
                  <tbody>
                     <c:forEach items="${hrCompleteList}" var="item" varStatus="count">
                        <tr>
                           <td>${item.empCode}</td>
                           <td>${item.empName}</td>
                           <td>${item.managerEmail}</td>
                           <td>
                              <c:set var = "probationStateName" scope = "session" value = "${item.probationStateName}"/>
                              <c:if test = "${(item.probationState == 1 )}">
                                 <span style="color: green;">
                                    <c:out value = "${probationStateName}"/>
                                 </span>
                              </c:if>
                              <c:if test = "${(item.probationState == 2 )}">
                                 <span style="color: red;">
                                    <c:out value = "${probationStateName}"/>
                                 </span>
                              </c:if>
                              <c:if test = "${(item.probationState == 3 )}">
                                 <span style="color: blue;">
                                    <c:out value = "${probationStateName}"/>
                                 </span>
                              </c:if>
                              <c:if test = "${(item.probationState == 4 )}">
                                 <span style="color: blue;">
                                    <c:out value = "${probationStateName}"/>
                                 </span>
                              </c:if>
                              <c:if test = "${(item.probationState == 5 )}">
                                 <span style="color: red;">
                                    <c:out value = "${probationStateName}"/>
                                 </span>
                              </c:if>
                           </td>
                           <td>${item.updateDate}</td>
                           <td class="py-0">
                              <form  method="post" action="${probationWorkflowUrl}" style="display:inline;" >
                                 <input type="hidden" name="<portlet:namespace/>probationId" value="${item.empCode}"/>
                                 <button class=" btn btn-outline-primary btn-sm" type="submit" style="cursor: pointer;">View</button>
                              </form>
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
                     <h5>User Manual</h5>
                  </div>
                  <div class="col-2 text-right" ><button class="btn btn-outline-secondary" onClick="downloadHrUserManual();">Download</button></div>
               </div>
            </div>
         </div>
      </div>
   </div>
</div>