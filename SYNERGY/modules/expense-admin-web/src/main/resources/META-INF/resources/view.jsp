<%@ include file="/init.jsp" %>
<portlet:actionURL name="expenseWorkflow" var="expenseWorkflowUrl"></portlet:actionURL>
<portlet:resourceURL var="downloadUserManualUrl" id="downloadUserManual"></portlet:resourceURL>

<div class="container">
<input type="hidden" id="portletNamespace"   value="<portlet:namespace/>" />
<input type="hidden" id="downloadUserManualResourceUrl" value="${downloadUserManualUrl}"/>
   <ul class="nav nav-tabs nav-justified mb-3"  role="tablist">
      <li class="nav-item" id="managerRole" ><a class="nav-link" id="first-tab" data-toggle="tab" href="#first" role="tab" aria-controls="first" aria-selected="true" >Manager</a></li>
      <li class="nav-item" id="financeRole" ><a class="nav-link" id="second-tab" data-toggle="tab" href="#second" role="tab" aria-controls="second" aria-selected="false" >Finance</a></li>
   </ul>
   <div class="tab-content">
      <div class="tab-pane fade" id="first" role="tabpanel" aria-labelledby="first-tab">
         <div class="card">
            <div class= "card-header">
               <h5>For Action</h5>
            </div>
            <div class = "card-body">
               <table class="table table-sm table-bordered  table-striped text-center table-hover" id="managerEditableTable">
                  <thead class="thead-dark">
                     <tr>
                        <th class="text-center align-middle">Submitted Date</th>
                        <th class="text-center align-middle">Submitted By</th>
                        <th class="text-center align-middle">Expense Type</th>
                        <th class="text-center align-middle">Amount (&#8377;)</th>
                        <th class="text-center align-middle" >Status</th>
                        <th class="text-center align-middle" >Action</th>
                     </tr>
                  </thead>
                  <tbody>
                     <c:forEach items="${managerEditableEntries}" var="item" varStatus="count">
                     <c:set var="statusInt" scope="session" value="${item.statusInt}" />
                        <tr>
                           <td>${item.submittedDate}</td>
                           <td>${item.submittedBy}</td>
                           <td>${item.expenseType}</td>
                           <td>${item.totalBillAmount}</td>
                           <td>
                           <c:if test="${(statusInt == 0 )}">${item.status}</c:if>
                           <c:if test="${(statusInt == 1 )}"><label class="text-success pt-2">${item.status}</label></c:if>
                           <c:if test="${(statusInt == 3 )}"><label class="text-primary pt-2">${item.status}</label></c:if>
                           <c:if test="${(statusInt == 2 || statusInt == 4 || statusInt == 5)}"><label class="text-danger pt-2">${item.status}</label></c:if>
                           </td>
                           <td>
                              <form action="${expenseWorkflowUrl}" method="post" >
                                 <input type="hidden" value="edit"
                                    name="<portlet:namespace/>mode" />

                                 <input type="hidden" value="${item.expenseId}"
                                    name="<portlet:namespace/>expenseId" />
                                 <button type="submit" class=" btn btn-outline-primary btn-sm" title="Go to Expense Form"
                                    style="cursor: pointer;">Action</button>
                              </form>
                           </td>
                        </tr>
                     </c:forEach>
                  </tbody>
               </table>
            </div>
         </div>
         <div class="card">
            <div class= "card-header">
               <h5>For View</h5>
            </div>
            <div class = "card-body">
               <table class="table table-sm table-bordered  table-striped text-center table-hover" id="managerViewTable">
                  <thead class="thead-dark">
                     <tr>
                        <th class="text-center align-middle">Submitted</th>
                        <th class="text-center align-middle">Submitted By</th>
                        <th class="text-center align-middle" >Expense Type</th>
                        <th class="text-center align-middle" >Amount (&#8377;)</th>
                        <th class="text-center align-middle">Status</th>
                        <th class="text-center align-middle" >Action</th>
                     </tr>
                  </thead>
                  <tbody>
                     <c:forEach items="${managerViewEntries}" var="item" varStatus="count">
                     <c:set var="statusInt" scope="session" value="${item.statusInt}" />
                        <tr>
                           <td>${item.submittedDate}</td>
                           <td>${item.submittedBy}</td>
                           <td>${item.expenseType}</td>
                           <td>${item.totalBillAmount}</td>
                           <td>
                           <c:if test="${(statusInt == 0 )}">${item.status}</c:if>
                           <c:if test="${(statusInt == 1 )}"><label class="text-success pt-2">${item.status}</label></c:if>
                           <c:if test="${(statusInt == 3 )}"><label class="text-primary pt-2">${item.status}</label></c:if>
                           <c:if test="${(statusInt == 2 || statusInt == 4 || statusInt == 5)}"><label class="text-danger pt-2">${item.status}</label></c:if>
                           </td>
                           <td>
                              <form action="${expenseWorkflowUrl}" method="post" >

                                 <input type="hidden" value="${item.expenseId}"
                                    name="<portlet:namespace/>expenseId" />
                                 <button type="submit" class=" btn btn-outline-primary btn-sm" title="Go to Expense Form"
                                    style="cursor: pointer;">View</button>
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
         <div class="card">
            <div class= "card-header">
               <h5>For Action</h5>
            </div>
            <div class = "card-body">
               <table class="table table-sm table-bordered  table-striped text-center table-hover" id="financeEditableTable">
                  <thead class="thead-dark">
                     <tr>
                        <th class="text-center align-middle">Submitted Date</th>
                        <th class="text-center align-middle">Submitted By</th>
                        <th class="text-center align-middle">Expense Type</th>
                        <th class="text-center align-middle">Amount (&#8377;)</th>
                        <th class="text-center align-middle">Status</th>
                        <th class="text-center align-middle">Action</th>
                     </tr>
                  </thead>
                  <tbody>
                     <c:forEach items="${financeEditableEntries}" var="item" varStatus="count">
                     <c:set var="statusInt" scope="session" value="${item.statusInt}" />
                        <tr>
                           <td>${item.submittedDate}</td>
                           <td>${item.submittedBy}</td>
                           <td>${item.expenseType}</td>
                           <td>${item.totalBillAmount}</td>
                           <td>
                           <c:if test="${(statusInt == 0 )}">${item.status}</c:if>
                           <c:if test="${(statusInt == 1 )}"><label class="text-success pt-2">${item.status}</label></c:if>
                           <c:if test="${(statusInt == 3 )}"><label class="text-primary pt-2">${item.status}</label></c:if>
                           <c:if test="${(statusInt == 2 || statusInt == 4 || statusInt == 5)}"><label class="text-danger pt-2">${item.status}</label></c:if>
                           </td>
                           <td>
                              <form action="${expenseWorkflowUrl}" method="post" >
                                 <input type="hidden" value="edit"
                                    name="<portlet:namespace/>mode" />
                                    <input type="hidden" value="Finance"
                                       name="<portlet:namespace/>role" />
                                 <input type="hidden" value="${item.expenseId}"
                                    name="<portlet:namespace/>expenseId" />
                                 <button type="submit" class=" btn btn-outline-primary btn-sm" title="Go to Expense Form"
                                    style="cursor: pointer;">Action</button>
                              </form>
                           </td>
                        </tr>
                     </c:forEach>
                  </tbody>
               </table>
            </div>
         </div>
         <div class="card">
            <div class= "card-header">
               <h5>For View</h5>
            </div>
            <div class = "card-body">
               <table class="table table-sm table-bordered  table-striped text-center table-hover" id="financeViewTable">
                  <thead class="thead-dark">
                     <tr>
                        <th class="text-center align-middle">Submitted Date</th>
                        <th class="text-center align-middle">Submitted By</th>
                        <th class="text-center align-middle">Expense Type</th>
                        <th class="text-center align-middle">Amount (&#8377;)</th>
                        <th class="text-center align-middle">Status</th>
                        <th class="text-center align-middle">Action</th>
                     </tr>
                  </thead>
                  <tbody>
                     <c:forEach items="${financeViewEntries}" var="item" varStatus="count">
                     <c:set var="statusInt" scope="session" value="${item.statusInt}" />
                        <tr>
                           <td>${item.submittedDate}</td>
                           <td>${item.submittedBy}</td>
                           <td>${item.expenseType}</td>
                           <td>${item.totalBillAmount}</td>
                           <td>
                           <c:if test="${(statusInt == 0 )}">${item.status}</c:if>
                           <c:if test="${(statusInt == 1 )}"><label class="text-success pt-2">${item.status}</label></c:if>
                           <c:if test="${(statusInt == 3 )}"><label class="text-primary pt-2">${item.status}</label></c:if>
                           <c:if test="${(statusInt == 2 || statusInt == 4 || statusInt == 5)}"><label class="text-danger pt-2">${item.status}</label></c:if>
                           </td>
                           <td>
                              <form action="${expenseWorkflowUrl}" method="post" >
                                 <input type="hidden" value="Finance"
                                       name="<portlet:namespace/>role" />
                                 <input type="hidden" value="${item.expenseId}"
                                    name="<portlet:namespace/>expenseId" />
                                 <button type="submit" class=" btn btn-outline-primary btn-sm" title="Go to Expense Form"
                                    style="cursor: pointer;">View</button>
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
   </div>
</div>
