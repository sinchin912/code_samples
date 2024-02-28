<%@ include file="/init.jsp" %>
<portlet:actionURL name="submitKpiForm" var="submitKpiFormUrl"></portlet:actionURL>
<portlet:actionURL name="updateKpiDetails" var="updateKpiDetailsUrl"></portlet:actionURL>

<div class="container">
   <input type="hidden" id="portletNamespace"   value="<portlet:namespace/>" />
   <div class="card">
      <div class="card-header">
         <h5>Project Kpi Information</h5>
      </div>
      <div class="card-body">
         <div class="row">
            <div class="col-2 mt-1 text-right">
               <label>Employee Name</label>
            </div>
            <div class="col-4">
               <input type="text" class=" form-control-sm form-control-plaintext"
                  readonly value="${reviewDetails.empName}" />
            </div>
            <div class="col-2 mt-1 text-right">
               <label>Ecode</label>
            </div>
            <div class="col-4">
               <input type="text" class=" form-control-sm form-control-plaintext"
                  readonly value="${reviewDetails.empEcode}" />
            </div>
         </div>
         <div class="row">
            <div class="col-2 mt-1 text-right">
               <label>Designation</label>
            </div>
            <div class="col-4">
               <input type="text" class=" form-control-sm form-control-plaintext"
                  value="${reviewDetails.empDesignation}" readonly />
            </div>
            <div class="col-2 mt-1 text-right">
               <label>Band</label>
            </div>
            <div class="col-4">
               <input type="text" class=" form-control-sm form-control-plaintext"
                  value="${reviewDetails.empBand}" readonly />
            </div>
         </div>
         <div class="row">
            <div class="col-2 mt-1 text-right">
               <label>Account</label>
            </div>
            <div class="col-4">
               <input type="text" class=" form-control-sm form-control-plaintext"
                  readonly value="${reviewDetails.account}" id="account" />
            </div>
            <div class="col-2 mt-1 text-right">
               <label>Mobile</label>
            </div>
            <div class="col-4">
               <input type="text" class=" form-control-sm form-control-plaintext"
                  readonly value="${reviewDetails.empMobileNo}" />
            </div>
         </div>
         <div class="row">
            <div class="col-2 mt-1 text-right">
               <label>Manager Name</label>
            </div>
            <div class="col-4">
               <input type="text" class=" form-control-sm form-control-plaintext"
                  readonly value="${reviewDetails.managerName}" />
            </div>
            <div class="col-2 mt-1 text-right">
               <label>Manager Email</label>
            </div>
            <div class="col-4">
               <input type="text" class=" form-control-sm form-control-plaintext"
                  readonly value="${reviewDetails.managerEmail}" />
            </div>
         </div>
         <div class="row">
            <div class="col-2 mt-1 text-right">
               <label>Reviewer Name</label>
            </div>
            <div class="col-4">
               <input type="text" class=" form-control-sm form-control-plaintext"
                  readonly value="${reviewDetails.reviewerName}" />
            </div>
            <div class="col-2 mt-1 text-right">
               <label>Reviewer Email</label>
            </div>
            <div class="col-4">
               <input type="text" class=" form-control-sm form-control-plaintext"
                  readonly value="${reviewDetails.reviewerEmail}" />
            </div>
         </div>
         <c:if test="${reviewDetails.comment != '' &&  reviewDetails.comment != 'systemupdate'}">
            <div class="row">
               <div class="col-2 mt-1 text-right">
                  <label>Manager Comments</label>
               </div>
               <div class="col-10 mt-1">
                  <span class="form-control-sm text-danger p-0">${reviewDetails.comment}</span>
               </div>
            </div>
         </c:if>
      </div>
   </div>
   <div class="card pb-2">
      <div class="card-header">
         <div class="row">
            <div class="col pt-2">
               <h5>KPI Setting Form</h5>
            </div>
         </div>
      </div>
      <div class="card-body">
         <!-- OBJECTIVE SETTING -->
         <c:if test="${(reviewDetails.reviewState == 1)}">
            <form id="kpiSubmissionForm"  method="post" onsubmit="return validateKpiSubmissionForm()" action="${submitKpiFormUrl}">
               <input id="numOfOtherObjectives" name="<portlet:namespace/>numOfOtherReviewLines" type="hidden" value="${reviewDetails.otherReviewLines.size()}" />
               <h5 class="text-center font-italic">
                  Please select the respective checkbox to fill the targets.
               </h5>
               <input name="<portlet:namespace/>reviewId" value="${reviewDetails.reviewId}" type="hidden" />
               <input name="<portlet:namespace/>kpiId" value="${reviewDetails.reviewId}" type="hidden" />
               <table
                  class="table  table-sm table-bordered table-responsive-md table-striped">
                  <thead class="thead-dark">
                     <tr>
                        <th width="5%">                               <button type="button" class="btn btn-sm btn-outline-success py-0" title="Add 'Other' KPIs from here"  onclick="addObjectives()">
                                                                      <i class="far fa-plus-square"></i>
                                                                      </button></th>
                        <th width="20%">KPI Title</th>
                        <th width="33%">KPI Description</th>
                        <th width="42%">KPI Target</th>
                     </tr>
                  </thead>
                  <tbody id="objSettingsRows">
                     <c:forEach items="${reviewDetails.lineDtoList}" var="item"
                        varStatus="count">
                        <tr>
                           <td>
                              <input type="checkbox"  value="${item.guideId}"
                              <c:if test="${(item.mandatory) || (item.selected)}"> checked="checked" </c:if>
                              <c:choose>
                                 <c:when test="${item.mandatory}">
                                    onclick="return false;"
                                 </c:when>
                                 <c:otherwise>
                                    onclick="return updateTargetOnSelect(this);"
                                 </c:otherwise>
                              </c:choose>
                              id="checkbox${item.guideId}" name="<portlet:namespace/>checkbox" />
                           </td>
                           <td class="py-0"><input
                              type="text"
                              class="form-control-sm form-control-plaintext"
                              readonly="readonly"
                              value="${item.reviewLineTitle}" title="${item.reviewLineTitle}" /></td>
                           <td class="p-0"><textarea row="2" column="50"
                              class="form-control-sm form-control-plaintext"
                              style="height: 80px;" title="${item.reviewLineDescription}"
                              maxlength="10000" readonly="readonly" >${item.reviewLineDescription}</textarea></td>
                           <td class="p-0">
                              <textarea row="2" column="50" style="height: 80px;"
                              <c:choose>
                                 <c:when test="${(item.mandatory) || (item.selected)}">
                                    class = "form-control form-control-sm fn_text-trim" required="required"
                                 </c:when>
                                 <c:otherwise>
                                    class = "form-control-plaintext form-control-sm fn_text-trim" readonly="readonly"
                                 </c:otherwise>
                              </c:choose>
                              maxlength="10000" id="objTarget${item.guideId}"
                              name="<portlet:namespace/>objTarget${item.guideId}">${item.reviewLineTarget}</textarea>
                           </td>
                        </tr>
                     </c:forEach>
                     <c:forEach items="${reviewDetails.otherReviewLines}" var="item"
                        varStatus="count">
                        <tr>
                           <td>
                              <button class="btn btn-sm btn-outline-danger py-0 fn_customKpi" id="deleteKpi${count.count}" onclick="removeKpiRow(this);" > <i class="fas fa-trash-alt" aria-hidden="true"></i></button>
                           </td>
                           <td class="p-0"><input
                              type="text"
                              class="form-control-sm form-control fn_text-trim"
                              maxlength="75" required="required" placeholder="Others"
                              id="otherTitle${count.count}"
                              name="<portlet:namespace/>otherTitle${count.count}"
                              value="${item.reviewLineTitle}" /></td>
                           <td class="p-0"><textarea row="2" column="50"
                              class="form-control-sm form-control fn_text-trim"
                              style="height: 80px;" title="${item.reviewLineDescription}"
                              maxlength="10000"
                              required="required"
                              name="<portlet:namespace/>otherDescription${count.count}" >${item.reviewLineDescription}</textarea></td>
                           <td class="p-0"><textarea row="2" column="50"
                              style="height: 80px;"
                              class = "form-control form-control-sm fn_text-trim" required="required"
                              maxlength="10000"
                              name="<portlet:namespace/>otherTarget${count.count}">${item.reviewLineTarget}</textarea></td>
                        </tr>
                     </c:forEach>
                  </tbody>
                  <tbody>
                     <tr>
                        <td><input type="checkbox" onclick="return false;"
                           checked="checked" /></td>
                        <td>Attributes</td>
                        <td colspan="2"><textarea row="2" column="50" style="height:180px;" class = "form-control-plaintext form-control-sm" readonly="readonly" >${reviewDetails.attributeText}</textarea></td>
                     </tr>
                  </tbody>
               </table>
               <div class="text-center mt-4"  id="btnSubmitObjSetting">
                     <button type="submit" class=" btn btn-primary ml-5" >Submit</button>
               </div>
            </form>
         </c:if>
         <c:if test="${(reviewDetails.reviewState == 2)}">
            <!-- OBJECTIVE FINALIZED -->
            <table
               class="table  table-sm table-bordered table-responsive-md table-striped">
               <thead class="thead-dark">
                  <tr>
                     <th width="18%">KPI Title</th>
                     <th width="36%">KPI Description</th>
                     <th width="44%">KPI Target</th>
                  </tr>
               </thead>
               <tbody>
                  <c:forEach items="${reviewDetails.lineDtoList}" var="item"
                     varStatus="count">
                     <c:if test="${(!item.attribute)}">
                        <tr>
                           <td class="py-0"><input
                              type="text" class="form-control-sm form-control-plaintext"
                              value="${item.reviewLineTitle}" readonly /></td>
                           <td class="p-0"><textarea row="2" column="50"
                              class="form-control-plaintext form-control-sm"
                              style="height: 80px;" readonly
                              title="${item.reviewLineDescription}">${item.reviewLineDescription}</textarea></td>
                           <td class="p-0"><textarea row="2" column="50"
                              style="height: 80px;"
                              class="form-control-plaintext form-control-sm"
                              title="${item.reviewLineTarget}" readonly>${item.reviewLineTarget}</textarea></td>
                        </tr>
                     </c:if>
                  </c:forEach>
               </tbody>
               <tbody>
                  <tr>
                     <td>Attributes</td>
                     <td colspan="2"><textarea row="2" column="50" style="height:180px;" class = "form-control-plaintext form-control-sm" readonly="readonly" >${reviewDetails.attributeText}</textarea></td>
                  </tr>
               </tbody>
            </table>
            <form method="post" action="${updateKpiDetailsUrl}" id="updateKpiForm">
               <input type="hidden" value="${reviewDetails.kpiId}" name="<portlet:namespace/>kpiId" />
               <div class="row mt-4"  id="btnUpdateObjSetting">
                  <div class="col-md-6 text-right">
                     <button type="submit" class=" btn btn-outline-primary ml-5" onClick="return  updateKpiSubmissionForm();">Update</button>
                  </div>
                  <div class="col-md-6">
                     <a class="btn btn-outline-secondary" href="${pageUrl[0]}" role="button">Cancel</a>
                  </div>
               </div>
            </form>
         </c:if>
      </div>
   </div>
</div>
