<%@ include file="/init.jsp" %>
<portlet:actionURL name="exitWorkflow" var="exitWorkflowUrl"></portlet:actionURL>
<portlet:actionURL name="exitClearanceWorkflow" var="exitClearanceWorkflowUrl"></portlet:actionURL>
<portlet:actionURL name="exitQuestionnaireFormWorkflow" var="exitQuestionnaireFormWorkflowUrl"></portlet:actionURL>
<portlet:resourceURL var="downloadUserManual" id="downloadUserManual"></portlet:resourceURL>
<div class="container">
<div id="homeDiv">
   <c:choose>
      <c:when test="${initiateResignation == '1'}">
         <div class="card" >
            <form action="${exitWorkflowUrl}" method="post" style="display: inline">
               <input type="hidden" id="resignationId"
                  value="0" name="<portlet:namespace/>resignationId" />
               <div class="card-header">
                  <p class="d-inline">Click on 'Initiate Resignation' button to start the Exit process</p>
                  <button type="submit" class="btn btn-primary btn-sm mb-3 float-right" id="initiateResignation"  style="cursor: pointer;">Initiate Resignation</button>
               </div>
            </form>
         </div>
      </c:when>
      <c:when test="${initiateResignation == '2'}">
         <div class="card-header mb-3">
            <h5>Either you have active resignation or its a weekend/holiday.
            </h5>
         </div>
      </c:when>
   </c:choose>
   <c:if test="${empResignList.size() > 0}">
      <div class="card" id="resignationCardDiv">
         <div class="card-header">
            <div class="row">
               <h5>Resignations</h5>
            </div>
         </div>
         <div class="card-body">
            <table id="resignationTable" class="table table-sm table-bordered table-responsive-md table-striped text-center">
               <thead class="thead-dark">
                  <tr>
                     <th class="text-center align-middle" > Date of Resign/Notice Start Date</th>
                     <th class="text-center align-middle" >Project</th>
                     <th class="text-center align-middle" >Manager Name</th>
                     <th class="text-center align-middle" >Status</th>
                     <th class="text-center align-middle" >Resignation Form</th>
                     <th class="text-center align-middle" >Exit Questionnaire</th>
                  </tr>
               </thead>
               <tbody id="resignationTableBodyId">
                  <c:forEach items="${empResignList}" var="item" varStatus="count">
                     <tr>
                        <td>${item.createDate}</td>
                        <td>${item.account}</td>
                        <td>${item.managerName}</td>
                        <td>${item.status}</td>
                        <td>
                           <form action="${exitWorkflowUrl}" method="post" style="display: inline">
                              <input type="hidden" id="resignationId"
                                 value="${item.resignationId}" name="<portlet:namespace/>resignationId" />
                              <c:set var="actionMode" scope="session" value="${item.actionMode}" />
                              <button  type="submit" style="cursor: pointer;" id="${item.resignationId}"
                                 class=" btn btn-sm btn-outline-primary">${actionMode == 'true' ? '  Action' : ' View'  }</button>
                           </form>
                        </td>
                        <td>
                           <c:if test="${item.questionnaireFormGenerated=='true' }">
                              <form action="${exitQuestionnaireFormWorkflowUrl}" method="post" style="display: inline">
                                 <input type="hidden" id="resignationId"
                                    value="${item.resignationId}" name="<portlet:namespace/>resignationId" />
                                 <button type="submit" class=" btn btn-outline-primary btn-sm" title="Go to Questionnaire Form" class=" btn btn-sm btn-outline-primary"
                                    style="cursor: pointer;">
                                    <c:choose>
                                       <c:when test="${item.questionnaireFormSubmitted=='false' }"> Action</c:when>
                                       <c:otherwise> View </c:otherwise>
                                    </c:choose>
                                 </button>
                              </form>
                           </c:if>
                        </td>
                     </tr>
                  </c:forEach>
               </tbody>
            </table>
         </div>
      </div>
   </c:if>
   <div class="card mt-2">
      <div class="card-header">
         <div class="row">
            <div class="col pt-2">
               <h5>Help - Exit User Manual </h5>
            </div>
            <div class="col-2 text-rig3t" ><input type="button" class="btn btn-link"  onClick="downloadUserManual();"  value="Download the file" /></div>
         </div>
      </div>
   </div>
</div>