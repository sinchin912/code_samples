<%@ include file="/init.jsp" %>
<portlet:actionURL name="expenseWorkflow" var="expenseWorkflowUrl"></portlet:actionURL>
<portlet:resourceURL var="downloadUserManualUrl" id="downloadUserManual"></portlet:resourceURL>
<div class="container">
   <div class="card">
      <div class="card-header">
         <div class="row">
            <div class="col">
               <h5>My Requests</h5>
            </div>
            <div class="col-3 text-right">
            <form action="${expenseWorkflowUrl}" method="post"  >
               <button type="submit" class="btn btn-outline-secondary"  style="cursor: pointer;">New Expense</button>
            </form>
            </div>
         </div>
      </div>
      <c:if test="${empExpenseDetails.size() > 0}">
         <div class="card-body">
            <table class="table table-sm table-bordered  table-striped text-center table-hover" id="expenseTable">
               <thead class="thead-dark">
                  <tr>
                     <th class="text-center align-middle" >Created Date</th>
                     <th class="text-center align-middle" >Expense Type</th>
                     <th class="text-center align-middle" >Amount (&#8377;)</th>
                     <th class="text-center align-middle" >Assignee</th>
                     <th class="text-center align-middle" >Status</th>
                     <th class="text-center align-middle" >Action</th>
                  </tr>
               </thead>
               <tbody>
                  <c:forEach items="${empExpenseDetails}" var="item" varStatus="count">
                  <c:set var="statusInt" scope="session" value="${item.statusInt}" />
                     <tr>
                        <td width="15% !important">${item.createdDate}</td>
                        <td width="30% !important">${item.expenseType}</td>
                        <td width="15% !important">${item.totalBillAmount}</td>
                        <td width="25% !important">${item.assignee}</td>
                        <td width="15% !important">
                        <c:if test="${(statusInt == 0 )}">${item.status}</c:if>
                        <c:if test="${(statusInt == 1 )}"><label class="text-success pt-2">${item.status}</label></c:if>
                        <c:if test="${(statusInt == 3 )}"><label class="text-primary pt-2">${item.status}</label></c:if>
                        <c:if test="${(statusInt == 2 || statusInt == 4 || statusInt == 5)}"><label class="text-danger pt-2">${item.status}</label></c:if>
                        </td>
                        <td width="10% !important">
                           <form action="${expenseWorkflowUrl}" method="post" >
                              <input type="hidden" value="${item.expenseId}"
                                 name="<portlet:namespace/>expenseId" />
                              <button type="submit" class=" btn btn-sm btn-outline-primary" title="Go to Expense Form"
                                 style="cursor: pointer;"><c:choose><c:when test="${statusInt == 2 || statusInt == 4 }">Action</c:when><c:otherwise>View</c:otherwise></c:choose></button>
                           </form>
                        </td>
                     </tr>
                  </c:forEach>
               </tbody>
            </table>
         </div>
      </c:if>
   </div>
   <div class="card">
      <div class="card-header">
         <div class="row">
            <div class="col">
               <h5>User Manual</h5>
            </div>
            <div class="col-3 text-right">
               <input type="hidden" id="downloadUserManualResourceUrl" value="${downloadUserManualUrl}"/>
               <button class="btn btn-outline-secondary" onClick="downloadUserManual();">Download
               </button>
            </div>
         </div>
      </div>
   </div>
</div>


