<%@ include file="/init.jsp" %>
<portlet:resourceURL var="uploadAttachmentsUrl" id="uploadAttachments"></portlet:resourceURL>
<portlet:resourceURL var="deleteAttachmentsUrl" id="deleteAttachments"></portlet:resourceURL>
<portlet:resourceURL var="downloadAttachmentsUrl" id="downloadAttachments"></portlet:resourceURL>
<portlet:resourceURL var="saveFormDataUrl" id="saveFormData"></portlet:resourceURL>
<portlet:actionURL name="selfReviewSubmit" var="selfReviewSubmitUrl"></portlet:actionURL>
<portlet:actionURL name="updateKpiDetails" var="updateKpiDetailsUrl"></portlet:actionURL>
<portlet:actionURL name="raiseMeetingToManager" var="raiseMeetingToManagerUrl"></portlet:actionURL>
<portlet:actionURL name="acceptManagerRating" var="acceptManagerRatingUrl"></portlet:actionURL>
<portlet:actionURL name="rejectManagerRating" var="rejectManagerRatingUrl"></portlet:actionURL>
<portlet:renderURL var="reviewRenderURL" windowState="normal"></portlet:renderURL>
<div class="container">
   <input type="hidden" id="deleteAttachmentsResourceUrl" value="${deleteAttachmentsUrl}"/>
   <input type="hidden" id="uploadAttachmentsResourceUrl" value="${uploadAttachmentsUrl}"/>
   <input type="hidden" id="saveFormDataResourceUrl" value="${saveFormDataUrl}"/>
   <input type="hidden" id="selfReviewSubmitResourceUrl" value="${selfReviewSubmitUrl}"/>
   <input type="hidden" id="downloadAttachmentsUrlResourceUrl" value="${downloadAttachmentsUrl}"/>
   <input type="hidden" id="updateKpiDetailsResourceUrl" value="${updateKpiDetailsUrl}"/>
   <input type="hidden" id="acceptManagerRatingResourceUrl" value="${acceptManagerRatingUrl}"/>
   <input type="hidden" id="rejectManagerRatingResourceUrl" value="${rejectManagerRatingUrl}"/>
   <c:set var="pageUrl" value="${fn:split(reviewRenderURL.toString(),'?')}" />
   <input type="hidden" id="portletNamespace"   value="<portlet:namespace/>" />
   <div class="card">
      <div class="card-header">
         <div class="row">
            <div class="col">
               <h5>Review Form</h5>
            </div>
            <div class="col-2 text-right">
               <a class="btn btn-outline-secondary" id="reset" href="${pageUrl[0]}"  role="button">
               Cancel</a>
            </div>
         </div>
      </div>
      <div class="card-body">
         <div  class="border border-dark rounded p-2" id="generalInstructions">
            <label> General Instructions :</label>
            <ul style="font-size:0.7em;">
               <li>
                  Please use below criteria while doing self review
                  <ul>
                     <li>Rating of "Does Not Meet Expectations" means : You have not MET the expectations as per your role/level</li>
                     <li>Rating of "Inconsistently Meets Expectations" means : You have inconsistently Met the expectations and sometimes you do perform as per role assigned and sometimes you do not</li>
                     <li>Rating of "Consistently Meets Expectations" means : You have MET the expectations as per your role/level and you are performing as per role assigned to you</li>
                     <li>Rating of "Sometimes Exceeds Expectations" means : You have EXCEEDED the expectations (i.e. you have done over and above of what was expected from you)</li>
                     <li>Rating of "Consistently Exceeds Expectations" means : You have EXCELLED in the given KPI and there is no scope of further improvement and the person is ready for next level</li>
                  </ul>
               </li>
            </ul>
         </div>
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
               <label>Reviewer Name</label>
            </div>
            <div class="col-4">
               <input type="text" class=" form-control-sm form-control-plaintext"
                  readonly value="${reviewDetails.reviewerName}" />
            </div>
         </div>
         <div class="row">
            <div class="col-2 mt-1 text-right">
               <label>Review Type</label>
            </div>
            <div class="col-4">
               <input type="hidden"
                  value="${reviewDetails.currentYRReview}" id="currentYRReview"/>
               <input type="text" class=" form-control-sm form-control-plaintext"
               readonly  value=
               <c:choose>
                  <c:when test="${(reviewDetails.currentYRReview=='true')}">"Annual"</c:when>
                  <c:otherwise>"Mid Year"</c:otherwise>
               </c:choose>
               />
            </div>
            <div class="col-2 mt-1 text-right">
               <label>Review State</label>
            </div>
            <div class="col-4">
               <input type="hidden"
                  value="${reviewDetails.reviewState}" id="reviewState"/>
               <input type="text" class=" form-control-sm form-control-plaintext"
                  readonly value="${reviewDetails.reviewStateName}" />
            </div>
         </div>
         <div class="row">
            <c:if test="${(reviewDetails.currentAccount=='true')}">
               <div class="col-md-2 mt-1 text-right">
                  <label>Final Rating</label>
               </div>
               <div class="col-md-4">
                  <c:if test="${(reviewDetails.reviewState == 5 || reviewDetails.reviewState == 6)}">
                     <input type="text"
                        class="form-control-sm form-control-plaintext" readonly
                        value="${reviewDetails.rating}" />
                  </c:if>
               </div>
            </c:if>
         </div>
         <c:if test="${(reviewDetails.reviewState != 6)}">
            <div class="mt-3">
               <ul class="progress-indicator">
                  <c:forEach items="${reviewStateMap}" var="item" varStatus="count">
                     <c:if test="${!(item.key == 6 || ((item.key == 3 || item.key == 4) && reviewDetails.currentAccount == 'false' ))}">
                        <li
                        <c:if test="${(count.index <= reviewDetails.reviewState -1)}">
                           class="completed"
                        </c:if>
                        id="stateMap${item.key}"><span class="bubble"></span><span class="stacked-text">
                        <span class="subdued">${item.value}</span><br>
                        <c:if test="${progressMap[item.key] != ''}">
                           <span class="fa fa-calendar"></span>  ${progressMap[item.key]}
                        </c:if>
                        </span</li>
                     </c:if>
                  </c:forEach>
               </ul>
            </div>
         </c:if>
      </div>
   </div>
   <c:if test="${(reviewDetails.reviewState != 6)}">
      <div class="card pb-2">
         <div class="card-header">
            <div class="row">
               <div class="col">
                  <h5>KPIs</h5>
               </div>
               <div class="col-1 float-right">
                  <c:if test="${(reviewDetails.comment != '')  && (reviewDetails.reviewState > 1)}">
                     <i class="fas fa-history" title="${reviewDetails.comment}"></i>
                  </c:if>
               </div>
            </div>
         </div>
         <div class="card-body">
            <c:if test="${(reviewDetails.currentYRReview=='false' && reviewDetails.reviewState != 6)}">
               <!-- Mid year Manager Review-->
               <table
                  class="table  table-sm table-bordered  table-striped">
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
               <c:if test="${reviewDetails.reviewState > 2}">
                  <div class="form-row mt-2">
                     <div class="form-group col" id="achievementDiv">
                        <label class="w-100">Your Achievements</label>
                        <textarea row="2" column="50" style="height: 150px;"
                           class="form-control-plaintext border border-secondary form-control-sm pl-1"
                           readonly>${reviewDetails.achievementsByEmp}</textarea></td>
                     </div>
                     <div class="form-group col" id="improvementDiv">
                        <label class="w-100">Improvement Area</label>
                        <textarea row="2" column="50" style="height: 150px;"
                           class="form-control-plaintext border border-secondary form-control-sm pl-1"
                           readonly>${reviewDetails.improvementComment}</textarea></td>
                     </div>
                     <div class="form-group col" id="managerCommentDiv">
                        <label class="w-100">Manager Comments</label>
                        <textarea row="2" column="50" style="height: 150px;"
                           class=" form-control-plaintext border border-secondary form-control-sm pl-1"
                           title="${reviewDetails.overallManagerComment}" readonly>${reviewDetails.overallManagerComment}</textarea></td>
                     </div>
                  </div>
               </c:if>
               <c:if test="${reviewDetails.reviewState == 1 && reviewDetails.comment != '' &&  reviewDetails.comment != 'systemupdate'}">
                  <div class="row">
                     <div class="col-md-2 mt-1 text-right">
                        <label>Manager Comments</label>
                     </div>
                     <div class="col-md-10 mt-1">
                        <textarea row="2" column="50" style="height: 150px;"
                           class="w-50 form-control-plaintext border border-secondary form-control-sm"
                           title="${reviewDetails.comment}" readonly>${reviewDetails.comment}</textarea></td>
                     </div>
                  </div>
               </c:if>
               <c:if test="${(reviewDetails.reviewState == 1 && reviewDetails.kpiExistingStatus == 'true')}">
                  <div class="col text-center">
                     <form method="post" action="${updateKpiDetailsUrl}" onsubmit="return kpiUpdateForm(this)" id="kpiFormUpdate" >
                        <input type="hidden" value="${reviewDetails.kpiId}" name="<portlet:namespace/>kpiId" />
                        <div class="row mt-4">
                           <div class="col text-center">
                              <button type="submit" class=" btn btn-warning ml-5" >Update KPIs</button>
                           </div>
                        </div>
                     </form>
                  </div>
               </c:if>
               <c:if test="${(reviewDetails.reviewState == 5 || reviewDetails.reviewState == 6)}">
                  <div class="row mt-3">
                     <div class="col text-center">
                        <h2>Your calibrated final rating is
                           ${reviewDetails.rating}
                        </h2>
                     </div>
                  </div>
               </c:if>
               <c:if test="${reviewDetails.raiseMeetingButtonDisplay == 'true'}">
                  <form method="post" action="${raiseMeetingToManagerUrl}" onsubmit="return managerMeetingRaise()" id="raiseManagerMeeting">
                     <input type="hidden" value="${reviewDetails.reviewId}" name="<portlet:namespace/>reviewId" />
                     <div class="row mt-4"  id="btnRaiseMeeting">
                        <div class="col text-center">
                           <button type="submit" class=" btn btn-primary ml-5" >Request a meeting with Manager</button>
                           <p align="center" style="color:grey;">(Request a meeting with your manager if you are not satisfied with the Ratings received.)</p>
                        </div>
                     </div>
                  </form>
               </c:if>
               <c:if test="${reviewDetails.reviewState == 2 }">
                  <div class="col text-center">
                     <p> Manager Review is Pending</p>
                  </div>
               </c:if>
            </c:if>
            <c:if test="${(reviewDetails.reviewState == 1 && reviewDetails.currentYRReview=='true')}">
               <!--Annual SELF REVIEW --primary and secondary both -->
               <form action="${selfReviewSubmitUrl}" onsubmit="return submitSelfReview()"  id="selfReviewForms" method="post" >
                  <input type="hidden" id="actionTaken" value="" />
                  <input name="<portlet:namespace/>reviewId"
                     value="${reviewDetails.reviewId}" type="hidden" />
                  <h6 class="text-center font-italic">Please enter supporting comments for each KPIs by using <button onclick="return false" class="btn btn-sm btn-info"><i class="fas fa-expand-arrows-alt"></i></button> <button onclick="return false" class="btn btn-sm btn-warning"><i class="fas fa-compress-arrows-alt"></i></button> buttons below. It is a good practice to save the form frequently.</h6>
                  <table class="table table-sm table-striped">
                     <thead class="thead-dark">
                        <tr>
                           <th width="33%" class="text-center"><button
                              class="btn btn-sm btn-info"
                              onclick="return expandAll()" title="Expand All">
                              <i class="fas fa-expand-arrows-alt"></i>
                              </button>
                              <button class="btn btn-sm btn-warning ml-4"
                                 onclick="return collapseAll()" title="Collapse All">
                              <i class="fas fa-compress-arrows-alt"></i>
                              </button>
                           </th>
                           <th width="33%">KPIs</th>
                           <th width="33%">Employee Self Review</th>
                        </tr>
                     </thead>
                     <tbody id="selfReviewState">
                        <c:forEach items="${reviewDetails.lineDtoList}" var="item"
                           varStatus="count">
                           <tr>
                              <td class="text-center py-0"><input type="hidden"
                                 value="${item.reviewLineId}"
                                 name="<portlet:namespace/>reviewId${count.index}" />
                                 <button type="button" class="btn btn-link btn-sm"
                                    data-toggle="collapse"
                                    data-target="#reviewId${count.index}">Details</button>
                              </td>
                              <td class="py-0">
                                 <c:if test="${(!item.attribute)}">
                                    <strong>Title :</strong>
                                 </c:if>
                                 <c:if test="${(item.attribute)}">
                                    <strong>Attribute :</strong>
                                 </c:if>
                                 ${item.reviewLineTitle}
                              </td>
                              <td class="p-0">
                                 <select
                                    class="mdb-select md-form form-control form-control-sm "
                                    name="<portlet:namespace/>selfRating${count.index}" required="required"
                                    id="selfRating${count.index}">
                                    <option value="" disabled selected>Employee Rating</option>
                                    <option value="A"
                                    ${(item.employeeRating =='A' || item.employeeRating == 'Consistently Exceeds Expectations') ? 'selected="selected"': ''}>
                                    Consistently Exceeds Expectations</option>
                                    <option value="B"
                                    ${(item.employeeRating =='B' || item.employeeRating == 'Sometimes Exceeds Expectations')   ? 'selected="selected"': ''}>
                                    Sometimes Exceeds Expectations</option>
                                    <option value="C"
                                    ${(item.employeeRating =='C' || item.employeeRating == 'Consistently Meets Expectations')  ? 'selected="selected"': ''}>
                                    Consistently Meets Expectations</option>
                                    <option value="D"
                                    ${(item.employeeRating =='D' || item.employeeRating == 'Inconsistently Meets Expectations')  ? 'selected="selected"': ''}>
                                    Inconsistently Meets Expectations</option>
                                    <option value="E"
                                    ${(item.employeeRating =='E' || item.employeeRating == 'Does not Meet Expectations')  ? 'selected="selected"': ''}>
                                    Does not Meet Expectations</option>
                                 </select>
                              </td>
                           </tr>
                           <tr id="reviewId${count.index}" class="collapse">
                              <c:if test="${(item.attribute)}">
                                 <td></td>
                                 <td></td>
                                 <td class="px-0"><strong>Employee Comments : </strong><br> <textarea row="2" column="50"
                                    style="height: 70px;" maxlength="10000"
                                    class="form-control form-control-sm fn_text-trim"
                                    placeholder="Give your Self Comments here.."
                                    title="${item.employeeComment}"
                                    id="selfComments${count.index}"
                                    name="<portlet:namespace/>selfComments${count.index}">${item.employeeComment}</textarea></td>
                              </c:if>
                              <c:if test="${(!item.attribute)}">
                                 <td><strong>Description : </strong><br> <textarea row="2" column="50"
                                    class="form-control-plaintext form-control-sm"
                                    style="height: 150px;" title="${item.reviewLineDescription}"
                                    readonly>${item.reviewLineDescription}</textarea></td>
                                 <td><strong>Target : </strong><br> <textarea row="2" column="50"
                                    class="form-control-plaintext form-control-sm"
                                    style="height: 150px;"  title="${item.reviewLineTarget}"
                                    readonly>${item.reviewLineTarget}</textarea></td>
                                 <td class="px-0"><strong>Employee Comments : </strong><br> <textarea row="2" column="50"
                                    style="height: 150px;" maxlength="10000"
                                    class="form-control form-control-sm fn_text-trim"
                                    placeholder="Enter Comments" title="${item.employeeComment}"
                                    id="selfComments${count.index}"
                                    name="<portlet:namespace/>selfComments${count.index}">${item.employeeComment}</textarea></td>
                              </c:if>
                           </tr>
                        </c:forEach>
                     </tbody>
                  </table>
                  <div class="row mt-3">
                     <div class="col">
                        <label>Achievements (Trainings, Client
                        Appreciation, Awards, Certification)</label>
                        <textarea row="2" column="50"
                           class="form-control form-control-sm fn_text-trim pl-1"
                           placeholder="Give your achievements here.."
                           style="height: 130px;"
                           title="${reviewDetails.achievementsByEmp}" maxlength="10000"
                           required="required" id="achievementsByEmp" required
                           name="<portlet:namespace/>achievementsByEmpComment">${reviewDetails.achievementsByEmp}</textarea>
                     </div>
                     <div class="col">
                        <label>Your Remarks</label>
                        <textarea row="2" column="50"
                           class="form-control form-control-sm fn_text-trim pl-1"
                           placeholder="Give your remarks here.." style="height: 130px;"
                           maxlength="10000"
                           title="${reviewDetails.overallEmployeeComment}"
                           required="required" id="overallEmployeeComment"
                           name="<portlet:namespace/>empComments" required >${reviewDetails.overallEmployeeComment}</textarea>
                     </div>
                  </div>
                  <div class="row mt-3">
                     <div class="col">
                        <label  for="uploadAchievements"
                           class="btn btn-sm btn-outline-primary" id="uploadFileCloud" title="Max size per upload is 4 mb">
                        Upload Achievements</label> <img
                           style="height: 35px;display:none;" id="uploadingFileCloud"
                           src="<%= themeDisplay.getPathThemeImages() %>/spinner.gif" />
                        <input type="file" style="display: none;" id="uploadAchievements"
                           name="<portlet:namespace/>uploadAchievemnts"
                           onChange="uploadFile(${reviewDetails.reviewId});"
                           multiple="multiple" />
                        <div id="filesList">
                           <ul>
                              <c:forEach items="${reviewDetails.uploadsAttachments}"
                                 var="item" varStatus="count">
                                 <li style="height: 25px;"><span class="btn btn-link"
                                    onClick="downloadAttachments('${item.uploadId}','${item.fileName}');">${item.fileName}</span>
                                    -
                                    <button class="close" style="color: red;" type="button"
                                       aria-label="Close" id="${item.uploadId}"
                                       onClick="deleteAttachment(this,'${item.fileName}')">
                                    <i class="fas fa-trash-alt" aria-hidden="true"></i>
                                    </button>
                                 </li>
                              </c:forEach>
                           </ul>
                        </div>
                     </div>
                  </div>
                  <c:if test="${reviewDetails.reviewState == 1 && reviewDetails.comment != ''&& reviewDetails.comment != null &&  reviewDetails.comment != 'systemupdate'}">
                     <div class="row">
                        <div class="col">
                           <label>Manager Comments</label>
                           <textarea row="2" column="50" style="height: 150px;"
                              class="w-50 form-control-plaintext border border-secondary form-control-sm"
                              title="${reviewDetails.comment}" readonly>${reviewDetails.comment}</textarea></td>
                        </div>
                     </div>
                  </c:if>
                  <div class="row mt-4" id="btnSubmitSelfReviewDiv">
                     <div class="col text-center">
                        <button type="button"  class=" btn btn-outline-primary ml-5" id="saveSelfReview">Save</button>
                        <button  type="submit"  class=" btn btn-primary ml-5">Submit</button>
                     </div>
                  </div>
               </form>
               <c:if test="${(reviewDetails.reviewState == 1 && reviewDetails.kpiExistingStatus == 'true')}">
                  <form method="post" action="${updateKpiDetailsUrl}" onsubmit="return kpiUpdateForm(this)" id="kpiUpdateForm" >
                     <input type="hidden" value="${reviewDetails.kpiId}" name="<portlet:namespace/>kpiId" />
                     <div class="row mt-4">
                        <div class="col text-center">
                           <button type="submit" class=" btn btn-primary ml-5" >Update KPIs</button>
                        </div>
                     </div>
                  </form>
               </c:if>
            </c:if>
            <c:if test="${(reviewDetails.reviewState == 2 && reviewDetails.currentYRReview=='true')}">
               <!--Annual year MANAGER REVIEW -->
               <table class="table  table-sm table-striped">
                  <thead class="thead-dark">
                     <tr>
                        <th width="33%" class="text-center"><button
                           class="btn btn-sm btn-info"
                           onclick="return expandAll()" title="Expand All">
                           <i class="fas fa-expand-arrows-alt"></i>
                           </button>
                           <button class="btn btn-sm btn-warning ml-4"
                              onclick="return collapseAll()" title="Collapse All">
                           <i class="fas fa-compress-arrows-alt"></i>
                           </button>
                        </th>
                        <th width="33%">KPIs</th>
                        <th width="33%">Employee Self Review</th>
                     </tr>
                  </thead>
                  <tbody>
                     <c:forEach items="${reviewDetails.lineDtoList}" var="item"
                        varStatus="count">
                        <tr>
                           <td class="text-center py-0"><input type="hidden"
                              value="${item.reviewLineId}"
                              name="<portlet:namespace/>reviewId${count.index}" />
                              <button type="button" class="btn btn-link btn-sm"
                                 data-toggle="collapse"
                                 data-target="#reviewId${count.index}">Details</button>
                           </td>
                           <td class="py-0">
                              <c:if test="${(!item.attribute)}">
                                 <strong>Title :</strong>
                              </c:if>
                              <c:if test="${(item.attribute)}">
                                 <strong>Attribute :</strong>
                              </c:if>
                              ${item.reviewLineTitle}
                           </td>
                           <td class="py-0"><strong>Employee Rating : </strong>${item.employeeRating}</td>
                        </tr>
                        <tr id="reviewId${count.index}" class="collapse">
                           <c:if test="${(item.attribute)}">
                              <td></td>
                              <td></td>
                              <td><strong>Employee Comments : </strong><br> <textarea row="2" column="50"
                                 style="height: 70px;"
                                 class="form-control-plaintext form-control-sm"
                                 title="${item.employeeComment}" readonly>${item.employeeComment}</textarea></td>
                           </c:if>
                           <c:if test="${(!item.attribute)}">
                              <td><strong>Description : </strong><br> <textarea row="2" column="50"
                                 class="form-control-plaintext form-control-sm"
                                 style="height: 150px;" title="${item.reviewLineDescription}"
                                 readonly>${item.reviewLineDescription}</textarea></td>
                              <td><strong>Target : </strong><br> <textarea row="2" column="50"
                                 style="height: 150px;"
                                 class="form-control-plaintext form-control-sm"
                                 title="${item.reviewLineTarget}" readonly>${item.reviewLineTarget}</textarea></td>
                              <td><strong>Employee Comments : </strong><br> <textarea row="2" column="50"
                                 style="height: 150px;"
                                 class="form-control-plaintext form-control-sm"
                                 title="${item.employeeComment}" readonly>${item.employeeComment}</textarea></td>
                           </c:if>
                        </tr>
                     </c:forEach>
                  </tbody>
               </table>
               <div class="row">
                  <div class="col">
                     <label>Your Achievements</label>
                     <textarea row="4" column="50" class="form-control-plaintext form-control-sm border border-secondary pl-1"
                        style="height: 130px;"
                        title="${reviewDetails.achievementsByEmp}" readonly>${reviewDetails.achievementsByEmp}</textarea>
                  </div>
                  <div class="col">
                     <label>Your Remarks</label>
                     <textarea row="4" column="50" class="form-control-plaintext form-control-sm border border-secondary pl-1"
                        style="height: 130px;"
                        title="${reviewDetails.overallEmployeeComment}" readonly>${reviewDetails.overallEmployeeComment}</textarea>
                  </div>
               </div>
               <c:if test="${reviewDetails.uploadsAttachments.size() > 0}">
                  <div class="row ">
                     <div class="col">
                        <div>
                           <label>Uploaded Attachments:</label>
                           <ul>
                              <c:forEach items="${reviewDetails.uploadsAttachments}"
                                 var="item" varStatus="count">
                                 <li style="height: 25px;"><span class="btn btn-link"
                                    onClick="downloadAttachments('${item.uploadId}','${item.fileName}');">${item.fileName}</span></li>
                              </c:forEach>
                           </ul>
                        </div>
                     </div>
                  </div>
               </c:if>
            </c:if>
            <c:if
               test="${(reviewDetails.currentYRReview=='true' && (reviewDetails.reviewState == 3 || reviewDetails.reviewState == 4))}">
               <!-- EMPLOYEE SIGN OFF, HR REVIEW -->
               <table
                  class="table table-sm table-bordered table-responsive-md table-striped">
                  <thead class="thead-dark">
                     <tr>
                        <th width="25%" class="text-center"><button
                           class="btn btn-sm btn-info"
                           onclick="return expandAll()" title="Expand All">
                           <i class="fas fa-expand-arrows-alt"></i>
                           </button>
                           <button class="btn btn-sm btn-warning ml-4"
                              onclick="return collapseAll()" title="Collapse All">
                           <i class="fas fa-compress-arrows-alt"></i>
                           </button>
                        </th>
                        <th width="25%">KPIs</th>
                        <th width="25%">Employee Self Review</th>
                        <th width="25%">Manager Review</th>
                     </tr>
                  </thead>
                  <tbody>
                     <c:forEach items="${reviewDetails.lineDtoList}" var="item"
                        varStatus="count">
                        <tr>
                           <td class="text-center py-0"><button type="button"
                              class="btn btn-link btn-sm" data-toggle="collapse"
                              data-target="#reviewLineId${count.index}">Details</button></td>
                           <td class="py-0">
                              <c:if test="${(!item.attribute)}">
                                 <strong>Title :</strong>
                              </c:if>
                              <c:if test="${(item.attribute)}">
                                 <strong>Attribute :</strong>
                              </c:if>
                              ${item.reviewLineTitle}
                           </td>
                           <td class="py-0"><strong>Employee Rating : </strong>${item.employeeRating}</td>
                           <td class="py-0"><strong>Manager Rating : </strong>${item.managerRating}</td>
                        </tr>
                        <tr id="reviewLineId${count.index}" class="collapse">
                           <c:if test="${(item.attribute)}">
                              <td></td>
                              <td></td>
                              <td><strong>Employee Comments : </strong><br> <textarea row="2" column="50"
                                 style="height: 70px;"
                                 class="form-control-plaintext form-control-sm"
                                 title="${item.employeeComment}" readonly>${item.employeeComment}</textarea></td>
                              <td><strong>Manager Comments : </strong><br> <textarea row="2" column="50"
                                 style="height: 70px;"
                                 class="form-control-plaintext form-control-sm"
                                 title="${item.managerComment}" readonly>${item.managerComment}</textarea>
                              </td>
                           </c:if>
                           <c:if test="${(!item.attribute)}">
                              <td><strong>Description : </strong><br> <textarea row="2" column="50"
                                 class="form-control-plaintext form-control-sm"
                                 style="height: 300px;" title="${item.reviewLineDescription}"
                                 readonly>${item.reviewLineDescription}</textarea></td>
                              <td><strong>Target : </strong><br> <textarea row="2" column="50"
                                 style="height: 300px;"
                                 class="form-control-plaintext form-control-sm"
                                 title="${item.reviewLineTarget}" readonly>${item.reviewLineTarget}</textarea></td>
                              <td><strong>Employee Comments : </strong><br> <textarea row="2" column="50"
                                 style="height: 300px;"
                                 class="form-control-plaintext form-control-sm"
                                 title="${item.employeeComment}" readonly>${item.employeeComment}</textarea></td>
                              <td><strong>Manager Comments : </strong><br> <textarea row="2" column="50"
                                 style="height: 300px;"
                                 class="form-control-plaintext form-control-sm"
                                 title="${item.managerComment}" readonly>${item.managerComment}</textarea>
                              </td>
                           </c:if>
                        </tr>
                     </c:forEach>
                  </tbody>
               </table>
               <div class="form-row mt-2">
                  <div class="form-group col">
                     <label>Your Achievements</label>
                     <textarea class="form-control-plaintext form-control-sm border border-secondary pl-1" row="2" column="50"
                        style="height: 300px;"
                        title="${reviewDetails.achievementsByEmp}" readonly> ${reviewDetails.achievementsByEmp}</textarea>
                  </div>
                  <div class="form-group col">
                     <label>Your Remarks</label>
                     <textarea class="form-control-plaintext form-control-sm  border border-secondary pl-1" row="2" column="50"
                        style="height: 300px;"
                        title="${reviewDetails.overallEmployeeComment}" readonly> ${reviewDetails.overallEmployeeComment}</textarea>
                  </div>
                  <div class="form-group col">
                     <label>Improvements</label>
                     <textarea class="form-control-plaintext form-control-sm border border-secondary pl-1" row="2" column="50"
                        style="height: 300px;"
                        title="${reviewDetails.improvementComment}" readonly> ${reviewDetails.improvementComment}</textarea>
                  </div>
                  <div class="form-group col">
                     <label>Trainings Required</label>
                     <textarea class="form-control-plaintext form-control-sm border border-secondary pl-1" row="2" column="50"
                        style="height: 300px;"
                        title="${reviewDetails.trainingsNeeded}" readonly> ${reviewDetails.trainingsNeeded}</textarea>
                  </div>
                  <div class="form-group col">
                     <label>Manager Comments</label>
                     <textarea class="form-control-plaintext form-control-sm border border-secondary pl-1"  row="2" column="50"
                        style="height: 300px;"
                        title="${reviewDetails.overallManagerComment}" readonly> ${reviewDetails.overallManagerComment}</textarea>
                  </div>
               </div>
               <div class="row ">
                  <div class="col">
                     <c:if test="${reviewDetails.uploadsAttachments.size() > 0}">
                        <label>Uploaded Attachments:</label>
                        <ul>
                           <c:forEach items="${reviewDetails.uploadsAttachments}"
                              var="item" varStatus="count">
                              <li style="height: 25px;"><span class="btn btn-link"
                                 onClick="downloadAttachments('${item.uploadId}','${item.fileName}');">${item.fileName}</span></li>
                           </c:forEach>
                        </ul>
                     </c:if>
                  </div>
                  <div class="col-6">
                     <c:if test="${(reviewDetails.reviewState == 4) && (reviewDetails.raiseToHrComments != '')}">
                        <label>Raised to HR</label>
                        <textarea class="form-control-plaintext form-control-sm border border-secondary pl-2" row="2"
                           column="50" style="height: 100px;"
                           title="${reviewDetails.raiseToHrComments}" readonly>${reviewDetails.raiseToHrComments}</textarea>
                     </c:if>
                  </div>
               </div>
               <div class="row mt-3">
                  <div class="col text-center">
                     <h2>Your reviewer rating is ${reviewDetails.rating}</h2>
                  </div>
               </div>
               <c:if test="${(reviewDetails.reviewState == 3)}">
                  <form id="empSignOffForm" method="post" onsubmit="return submitEmpSignOff(this)">
                     <input name="<portlet:namespace/>action" id="action"
                        value="" type="hidden" />
                     <input name="<portlet:namespace/>reviewId"
                        value="${reviewDetails.reviewId}" type="hidden" />
                     <div class="row mt-5 mb-3">
                        <div class="col-6 text-center">
                           <div class="input-group">
                              <textarea class="form-control form-control-sm fn_text-trim"
                                 placeholder="Enter your comments to raise to HR if you have any concern about your rating"
                                 style="height: 40px;width:300px;" name="<portlet:namespace/>raiseToHrComments"
                                 id="raiseToHrComments"></textarea>
                              <button type="submit" class=" btn btn-outline-warning" onClick="return validateEmpSignOff(2)">Raise To HR</button>
                           </div>
                        </div>
                        <div class="col-6 text-center">
                           <button type="submit" class=" btn btn-primary" onClick="return validateEmpSignOff(1)">Accept</button>
                        </div>
                     </div>
                  </form>
               </c:if>
            </c:if>
            <c:if test="${(reviewDetails.reviewState == 5) && (reviewDetails.currentAccount=='true') && (reviewDetails.currentYRReview=='true')}">
               <!--Annual COMPLETE PRIMARY-->
               <table
                  class="table table-sm table-bordered table-responsive-md table-striped">
                  <thead class="thead-dark">
                     <tr>
                        <th width="20%" class="text-center"><button
                           class="btn btn-sm btn-info"
                           onclick="return expandAll()" title="Expand All">
                           <i class="fas fa-expand-arrows-alt"></i>
                           </button>
                           <button class="btn btn-sm btn-warning ml-4"
                              onclick="return collapseAll()" title="Collapse All">
                           <i class="fas fa-compress-arrows-alt"></i>
                           </button>
                        </th>
                        <th width="20%">KPIs</th>
                        <th width="20%">Employee Self Review</th>
                        <th width="20%">Manager Review</th>
                        <th width="20%">HR Review</th>
                     </tr>
                  </thead>
                  <tbody>
                     <c:forEach items="${reviewDetails.lineDtoList}" var="item"
                        varStatus="count">
                        <tr>
                           <td class="text-center py-0"><button type="button"
                              class="btn btn-link btn-sm" data-toggle="collapse"
                              data-target="#reviewLineId${count.index}">Details</button></td>
                           <td class="py-0">
                              <c:if test="${(!item.attribute)}">
                                 <strong>Title :</strong>
                              </c:if>
                              <c:if test="${(item.attribute)}">
                                 <strong>Attribute :</strong>
                              </c:if>
                              ${item.reviewLineTitle}
                           </td>
                           <td class="py-0"><strong>Employee Rating : </strong>${item.employeeRating}</td>
                           <td class="py-0"><strong>Manager Rating : </strong>${item.managerRating}</td>
                           <td class="py-0"><strong>HR Rating : </strong>${item.hrRating}</td>
                        </tr>
                        <tr id="reviewLineId${count.index}" class="collapse">
                           <c:if test="${(item.attribute)}">
                              <td></td>
                              <td></td>
                              <td><strong>Employee Comments : </strong><br> <textarea row="2" column="50"
                                 style="height: 70px;"
                                 class="form-control-plaintext form-control-sm"
                                 title="${item.employeeComment}" readonly>${item.employeeComment}</textarea></td>
                              <td><strong>Manager Comments : </strong><br> <textarea row="2" column="50"
                                 style="height: 70px;"
                                 class="form-control-plaintext form-control-sm"
                                 title="${item.managerComment}" readonly>${item.managerComment}</textarea>
                              </td>
                              <td><strong>HR Comments : </strong><br> <textarea row="2" column="50"
                                 style="height: 70px;"
                                 title="${item.hrComment}"
                                 class="form-control-plaintext form-control-sm" readonly> ${item.hrComment} </textarea></td>
                           </c:if>
                           <c:if test="${(!item.attribute)}">
                              <td><strong>Description : </strong><br> <textarea  row="2" column="50"
                                 class="form-control-plaintext form-control-sm"
                                 style="height: 300px;" title="${item.reviewLineDescription}"
                                 readonly>${item.reviewLineDescription}</textarea></td>
                              <td><strong>Target : </strong><br> <textarea row="2" column="50"
                                 style="height: 300px;"
                                 class="form-control-plaintext form-control-sm"
                                 title="${item.reviewLineTitle}" readonly>${item.reviewLineTitle}</textarea></td>
                              <td><strong>Employee Comments : </strong><br> <textarea row="2" column="50"
                                 style="height: 300px;"
                                 class="form-control-plaintext form-control-sm"
                                 title="${item.employeeComment}" readonly>${item.employeeComment}</textarea></td>
                              <td><strong>Manager Comments : </strong><br> <textarea row="2" column="50"
                                 style="height: 300px;"
                                 class="form-control-plaintext form-control-sm"
                                 title="${item.managerComment}" readonly>${item.managerComment}</textarea>
                              </td>
                              <td><strong>HR Comments : </strong><br> <textarea  row="2" column="50"
                                 style="height: 300px;"
                                 class="form-control-plaintext form-control-sm"
                                 title="${item.hrComment}" readonly>${item.hrComment}</textarea></td>
                           </c:if>
                        </tr>
                     </c:forEach>
                  </tbody>
               </table>
               <div class="form-row mt-2">
                  <div class=" form-group col">
                     <label>Your Achievements</label>
                     <textarea class="form-control-plaintext form-control-sm border border-secondary pl-1" row="2" column="50"
                        style="height: 300px;"
                        title="${reviewDetails.achievementsByEmp}"
                        readonly> ${reviewDetails.achievementsByEmp}</textarea>
                  </div>
                  <div class=" form-group col">
                     <label>Your Remarks</label>
                     <textarea class="form-control-plaintext form-control-sm border border-secondary pl-1" row="2" column="50"
                        style="height: 300px;"
                        title="${reviewDetails.overallEmployeeComment}"
                        readonly> ${reviewDetails.overallEmployeeComment}</textarea>
                  </div>
                  <div class=" form-group col">
                     <label>Improvements</label>
                     <textarea class="form-control-plaintext form-control-sm border border-secondary pl-1" row="2" column="50"
                        style="height: 300px;"
                        title="${reviewDetails.improvementComment}"
                        readonly> ${reviewDetails.improvementComment}</textarea>
                  </div>
                  <div class=" form-group col">
                     <label>Trainings Required</label>
                     <textarea class="form-control-plaintext form-control-sm border border-secondary pl-1" row="2" column="50"
                        style="height: 300px;"
                        title="${reviewDetails.trainingsNeeded}"
                        readonly> ${reviewDetails.trainingsNeeded}</textarea>
                  </div>
                  <div class="form-group col">
                     <label>Manager Comments</label>
                     <textarea class="form-control-plaintext form-control-sm border border-secondary pl-1" row="2" column="50"
                        style="height: 300px;"
                        title="${reviewDetails.overallManagerComment}"
                        readonly> ${reviewDetails.overallManagerComment}</textarea>
                  </div>
                  <div class="form-group col ">
                     <label>HR Comments</label>
                     <textarea class="form-control-plaintext form-control-sm border border-secondary pl-1" row="2" column="50"
                        style="height: 300px;"
                        title="${reviewDetails.overallHrComment}"
                        readonly> ${reviewDetails.overallHrComment}</textarea>
                  </div>
               </div>
               <div class="row ">
                  <div class="col">
                     <c:if test="${reviewDetails.uploadsAttachments.size() > 0}">
                        <label>Uploaded Attachments:</label>
                        <ul>
                           <c:forEach items="${reviewDetails.uploadsAttachments}"
                              var="item" varStatus="count">
                              <li style="height: 25px;"><span class="btn btn-link"
                                 onClick="downloadAttachments('${item.uploadId}','${item.fileName}');">${item.fileName}</span></li>
                           </c:forEach>
                        </ul>
                     </c:if>
                  </div>
               </div>
               <div class="row mt-3">
                  <div class="col text-center">
                     <h2>Your calibrated final rating is
                        ${reviewDetails.rating}
                     </h2>
                  </div>
               </div>
            </c:if>
            <c:if
               test="${(reviewDetails.reviewState == 5) && (reviewDetails.currentAccount=='false') && (reviewDetails.currentYRReview=='true')}">
               <!-- ANNUAL COMPLETE SECONDARY -->
               <table
                  class="table table-sm table-bordered table-responsive-md table-striped">
                  <thead class="thead-dark">
                     <tr>
                        <th width="20%">KPI</th>
                        <th width="20%">KPI Target</th>
                        <th width="20%">Employee Self Review</th>
                        <th width="20%">Manager Review</th>
                     </tr>
                  </thead>
                  <tbody>
                     <c:forEach items="${reviewDetails.lineDtoList}" var="item"
                        varStatus="count">
                        <tr>
                           <td class="py-0">
                              <c:if test="${(!item.attribute)}">
                                 <strong>Title : </strong>
                              </c:if>
                              <c:if test="${(item.attribute)}">
                                 <strong>Attribute : </strong>
                              </c:if>
                              <br>${item.reviewLineTitle}<br>
                              <c:if test="${(!item.attribute)}"><strong>Description : </strong><br> <textarea
                                 class="form-control-plaintext form-control-sm" style="height: 100px;" title="${item.reviewLineDescription}" readonly>${item.reviewLineDescription}</textarea></c:if>
                           </td>
                           <td class="py-0"><textarea style="height: 150px;" class="form-control-plaintext form-control-sm"
                              title="${item.reviewLineTarget}" readonly>${item.reviewLineTarget}</textarea></td>
                           <td class="py-0"><strong>Employee Rating : </strong>${item.employeeRating}<br><strong>Employee Comments : </strong><br> <textarea
                              style="height: 100px;" class="form-control-plaintext form-control-sm" title="${item.employeeComment}" readonly>${item.employeeComment}</textarea></td>
                           <td class="py-0"><strong>Manager Comments : </strong><br><textarea style="height: 130px;"
                              class="form-control-plaintext form-control-sm" title="${item.managerComment}" readonly>${item.managerComment}</textarea></td>
                        </tr>
                     </c:forEach>
                  </tbody>
               </table>
               <div class="form-row mt-2">
                  <div class=" form-group col">
                     <label>Your Achievements</label>
                     <textarea class="form-control-plaintext form-control-sm border border-dark pl-1" row="2" column="50"
                        style="height: 300px;"
                        title="${reviewDetails.achievementsByEmp}"
                        readonly> ${reviewDetails.achievementsByEmp}</textarea>
                  </div>
                  <div class=" form-group col">
                     <label>Your Remarks</label>
                     <textarea class="form-control-plaintext form-control-sm border border-dark pl-1" row="2" column="50"
                        style="height: 300px;"
                        title="${reviewDetails.overallEmployeeComment}"
                        readonly> ${reviewDetails.overallEmployeeComment}</textarea>
                  </div>
                  <div class=" form-group col">
                     <label>Trainings Required</label>
                     <textarea class="form-control-plaintext form-control-sm border border-dark pl-1" row="2" column="50"
                        style="height: 300px;"
                        title="${reviewDetails.trainingsNeeded}"
                        readonly> ${reviewDetails.trainingsNeeded}</textarea>
                  </div>
                  <div class=" form-group col">
                     <label>Improvement Comments</label>
                     <textarea class="form-control-plaintext form-control-sm border border-dark pl-1" row="2" column="50"
                        style="height: 300px;"
                        title="${reviewDetails.improvementComment}"
                        readonly> ${reviewDetails.improvementComment}</textarea>
                  </div>
                  <div class="form-group col">
                     <label>Manager Comments</label>
                     <textarea class="form-control-plaintext form-control-sm border border-dark pl-1" row="2" column="50"
                        style="height: 300px;"
                        title="${reviewDetails.overallManagerComment}"
                        readonly> ${reviewDetails.overallManagerComment}</textarea>
                  </div>
               </div>
               <div class="row ">
                  <div class="col">
                     <c:if test="${reviewDetails.uploadsAttachments.size() > 0}">
                        <label>Uploaded Attachments:</label>
                        <ul>
                           <c:forEach items="${reviewDetails.uploadsAttachments}"
                              var="item" varStatus="count">
                              <li style="height: 25px;"><span class="btn btn-link"
                                 onClick="downloadAttachments('${item.uploadId}','${item.fileName}');">${item.fileName}</span></li>
                           </c:forEach>
                        </ul>
                     </c:if>
                  </div>
               </div>
            </c:if>
         </div>
      </div>
   </c:if>
</div>
<script>
  $(document).ready(function() {
        reviewState = $('#reviewState').val();
        reviewType = $('#currentYRReview').val();
        if(reviewState == 1 && reviewType == 'true'){
            setInterval(("Performance:It is a good practise to save your work frequently."), 3600000);
            $("#generalInstructions").show();
        }else{
            $("#generalInstructions").hide();
        }
        });
</script>