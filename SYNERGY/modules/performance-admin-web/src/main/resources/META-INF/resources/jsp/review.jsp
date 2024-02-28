<%@ include file="/init.jsp"%>
<portlet:resourceURL id="getReviewerName" var="getReviewerNameUrl"></portlet:resourceURL>
<portlet:resourceURL id="setManagerForm" var="setManagerFormUrl"></portlet:resourceURL>
<portlet:actionURL var="rejectReviewLinesUrl" name="rejectReviewLines"></portlet:actionURL>
<portlet:actionURL var="submitManagerReviewUrl" name="submitManagerReview"></portlet:actionURL>
<portlet:resourceURL var="downloadAttachmentsUrl" id="downloadAttachments"></portlet:resourceURL>
<portlet:actionURL var="submitHrReviewUrl" name="submitHrReview"></portlet:actionURL>
<portlet:actionURL var="rollbackReviewUrl" name="rollbackReview"></portlet:actionURL>
<portlet:actionURL var="assigneeReviewUrl" name="assigneeReview"></portlet:actionURL>
<portlet:resourceURL var="saveFormDataUrl" id="saveFormData"></portlet:resourceURL>
<portlet:resourceURL var="saveHrFormDataUrl" id="saveHrFormData"></portlet:resourceURL>
<portlet:renderURL var="reviewRenderURL" windowState="normal"></portlet:renderURL>


<div class="container">
   <input type="hidden" id="portletNamespace"   value="<portlet:namespace/>" />
   <c:set var="pageUrl" value="${fn:split(reviewRenderURL.toString(),'?')}" />
   <input type="hidden" id="getReviewerNameResourceUrl" value="${getReviewerNameUrl}"/>
   <input type="hidden" id="reviewState" value="${reviewDetails.reviewState}"/>
   <input type="hidden" id="currentYrReview" value="${reviewDetails.currentYRReview}"/>
   <input type="hidden" id="setManagerFormResourceUrl" value="${setManagerFormUrl}"/>
   <input type="hidden" id="rejectReviewLinesResourceUrl" value="${rejectReviewLinesUrl}"/>
   <input type="hidden" id="submitManagerReviewResourceUrl" value="${submitManagerReviewUrl}"/>
   <input type="hidden" id="submitHrReviewResourceUrl" value="${submitHrReviewUrl}"/>
   <input type="hidden" id="saveFormDataResourceUrl" value="${saveFormDataUrl}"/>
   <input type="hidden" id="saveHrFormDataResourceUrl" value="${saveHrFormDataUrl}"/>
    <input type="hidden" id="downloadAttachmentsUrlResourceUrl" value="${downloadAttachmentsUrl}"/>
   <div class="card pb-2">
      <div class="card-header">
         <button class="btn btn-outline-primary" data-toggle="collapse" data-target="#generalInstructions" title="Click here to Expand">General Instructions <i class="fas fa-expand-arrows-alt"></i></button>
         <h6>(For Leads and Managers only, must be reviewed before going for employee discussion)</h6>
      </div>
      <div class="card-body collapse" id="generalInstructions">
         <div  class="border border-dark rounded p-2 ">
            <label> General Instructions :</label>
            <ul style="font-size:0.7em;">
               <li>
                  For ratings, please note :
                  <ul>
                     <li>Overall Rating of 3 means: You have MET the expectations as per your role/level</li>
                     <li>Overall Rating of 4 means: You have EXCEEDED the expectations (i.e. you have done over and above of what was expected from you)</li>
                     <li>Overall Rating of 5 means: You have EXECLLED in the given KPI and there is no scope of further improvement and the person is ready for next level. Ideally, rating of 5 must not be given as there is always scope of improvement in every KRA and role.</li>
                  </ul>
               </li>
               <li>You MUST NOT COMMIT any percentage hike, in hand salary or designation changes at the time of one to one discussion or on the review form. Please ensure same is raised by employee, do not prompt them on these.</li>
               <li>If any employee raises a concern about his/her salary or designation, please tell them that we have noted your concern and we will get back to you after discussion with HR. All such concerns are to be captured in "Below comments are for HR Reference. They are not to be committed to or discussed with employee" section at the bottom of form.</li>
               <li><mark>Employees hired in the period Oct - Dec will be eligible for performance review, however their hikes can be as low as 0% depending on ratings, offer hike, value add etc.</mark></li>
               <li>Employees on maternity leave or going on Maternity leave till completion of exercise will be given increment wef date they join back after maternity leave. However, Performance review exercise must be completed for such employee.</li>
               <li>
                  Rating in number is for internal reference only. Please use below criteria while discussion with employee
                  <ul>
                     <li>4.5 & above : Consistently Exceeds Expectations</li>
                     <li>3.5 to 4.49 : Sometimes Exceeds Expectations</li>
                     <li>3.0 to 3.49 : Consistently Meets Expectations</li>
                     <li>1.5 to 2.99 : Inconsistently Meets Expectations</li>
                     <li>1.49 & less : Does Not Meet Expectations</li>
                  </ul>
               </li>
            </ul>
         </div>
      </div>
      <div class="card-header">
         <div class="row">
            <div class="col pt-2">
               <h5>Review Form</h5>
            </div>
            <c:if test="${(reviewDetails.managerViewStatus == 'true' && reviewDetails.reviewState == 2)}">
               <div class="col-8 form-inline justify-content-center"  id="assigneeFormDiv">
                  <form method="post" action="${assigneeReviewUrl}" id="assigneeReviewForm"  onsubmit="return assigneeFormSubmission() ">
                     <input type="hidden"  name="<portlet:namespace/>reviewId" value="${reviewDetails.reviewId}"/>
                     <label style="display:inline">Assign to</label>
                     <input type="text" placeholder="Enter email id" onfocus="$('#assigneeButton').attr('disabled','disabled');" onblur="checkReviewerRole()" class="form-control form-control-sm" maxlength="75" name="<portlet:namespace/>assigneeEmail" id="assigneeEmail" style="width:225px;" />
                     <span class="email-has-error"
                        style="color: red; font-size: .8em"></span>
                     <button class="btn btn-outline-secondary" type="submit" id="assigneeButton" disabled >Submit</button>
                  </form>
               </div>
            </c:if>
            <c:if test="${(reviewDetails.managerViewStatus == 'false' && (reviewDetails.reviewState == 4 || reviewDetails.reviewState == 5))}">
               <div class="col-6 form-inline justify-content-md-center"  id="rollbackFormDiv">
                  <form method="post" action="${rollbackReviewUrl}" id="rollbackReviewForm" onsubmit="return rollbackReviewFormSubmission() ">
                     <input type="hidden"  name="<portlet:namespace/>reviewId" value="${reviewDetails.reviewId}"/>
                     <label style="display:inline">Recall to</label>
                     <select class="mdb-select md-form form-control form-control-sm mx-2"  name="<portlet:namespace/>rollbackState"  required id="rollbackState" style="width:225px;">
                        <c:choose>
                           <c:when test="${(reviewDetails.currentYRReview=='true')}">
                              <option value="" disabled selected>Select option</option>
                              <c:if test="${(reviewDetails.currentAccount == 'true' && reviewDetails.reviewState > 4 && reviewDetails.adminRole == 'false')}">
                                 <option  value="4" >HR Review State</option>
                              </c:if>
                              <option value="2" >Manager Review State</option>
                              <c:if test="${( reviewDetails.adminRole == 'false')}">
                              <option value="1" >KPI/Self Review State</option>
                           </c:if>
                           </c:when>
                           <c:otherwise>
                              <option value="" disabled selected>Select option</option>
                              <option value="2" >Manager Review State</option>
                           </c:otherwise>
                        </c:choose>
                     </select>
                     <button class="btn btn-outline-secondary" type="submit" >Rollback</button>
                  </form>
               </div>
            </c:if>
            <div class="col-2 text-right">
               <a class="btn btn-outline-secondary" id="reset" href="${pageUrl[0]}"  role="button">
               Cancel</a>
            </div>
         </div>
      </div>
      <div class="card-body">
         <div class="row">
            <div class="col-md-2 mt-1 text-right">
               <label>Employee Name</label>
            </div>
            <div class="col-md-4">
               <input type="text" class="form-control-sm form-control-plaintext"
                  readonly  value="${reviewDetails.employeeName}" />
            </div>
            <div class="col-md-2 mt-1 text-right">
               <label>ECode</label>
            </div>
            <div class="col-md-4">
               <input type="text" class="form-control-sm form-control-plaintext"
                  readonly  value="${reviewDetails.employeeCode}" />
            </div>
         </div>
         <div class="row">
            <div class="col-md-2 mt-1 text-right">
               <label>Designation</label>
            </div>
            <div class="col-md-4">
               <input type="text" class="form-control-sm form-control-plaintext"
                  readonly
                  value="${reviewDetails.employeeDesignation}" />
            </div>
            <div class="col-md-2 mt-1 text-right">
               <label>Band</label>
            </div>
            <div class="col-md-4">
               <input type="text" class="form-control-sm form-control-plaintext"
                  readonly  value="${reviewDetails.employeeBand}" />
            </div>
         </div>
         <div class="row">
            <div class="col-md-2 mt-1 text-right">
               <label>Account</label>
            </div>
            <div class="col-md-4">
               <input type="text" class="form-control-sm form-control-plaintext"
                  readonly id="account" value="${reviewDetails.account}" />
            </div>
            <div class="col-md-2 mt-1 text-right">
               <label>Account Type</label>
            </div>
            <div class="col-md-4 form-control-sm">
               <input type="hidden" value="${reviewDetails.currentAccount}" id="primaryAccount"/>
               <input type="text" class="form-control-sm form-control-plaintext pl-2"
                  readonly  value="${reviewDetails.currentAccount=='true' ? 'Primary' : 'Secondary' }" />
            </div>
         </div>
         <div class="row">
            <div class="col-md-2 mt-1 text-right">
               <label>Manager Name</label>
            </div>
            <div class="col-md-4">
               <input type="text" class="form-control-sm form-control-plaintext"
                  readonly
                  value="${reviewDetails.managerName}" />
            </div>
            <div class="col-md-2 mt-1 text-right">
               <label>Reviewer Name</label>
            </div>
            <div class="col-md-4">
               <input type="text" class="form-control-sm form-control-plaintext"
                  readonly
                  value="${reviewDetails.assigneeName}" />
            </div>
         </div>
         <div class="row">
            <div class="col-md-2 mt-1 text-right">
               <label>Review Type</label>
            </div>
            <div class="col-md-4">
               <input type="hidden" value="${reviewDetails.currentYRReview}" id="currentYRReview"/>
               <c:choose>
                  <c:when test="${(reviewDetails.currentYRReview=='true')}">
                     <input type="text" class="form-control-sm form-control-plaintext"
                        readonly value="Annual" />
                  </c:when>
                  <c:otherwise>
                     <input type="text" class="form-control-sm form-control-plaintext"
                        readonly value="Mid Year" />
                  </c:otherwise>
               </c:choose>
            </div>
            <div class="col-md-2 mt-1 text-right">
               <label>Review State</label>
            </div>
            <div class="col-md-4">
               <input type="hidden"
                  value="${reviewDetails.reviewState}" id="reviewState"/>
               <input type="text" class="form-control-sm form-control-plaintext"
                  readonly
                  value="${reviewDetails.reviewStateName}" />
            </div>
         </div>
      </div>
      <c:if test="${(reviewDetails.currentAccount=='true' && reviewDetails.reviewState == 5)}">
         <div class="row">
            <div class="col-md-2 mt-1 text-right">
               <label>Final Rating</label>
            </div>
            <div class="col-md-4">
               <input type="text"
                  class="form-control-sm form-control-plaintext" readonly
                  value="${reviewDetails.rating}" />
            </div>
         </div>
      </c:if>
      <c:if test="${reviewDetails.currentYRReview=='true' && reviewDetails.currentAccount=='true' && reviewDetails.managerViewStatus == 'false' && reviewDetails.reviewState == 4 && reviewDetails.raiseToHrComment != ''}">
         <div class="row">
            <div class="col-md-2 ml-4 mt-1 text-right">
               <label>Raised by Employee :</label>
            </div>
            <div class="col-md-8 mt-1">
               <span class="form-control-sm text-danger p-0">${reviewDetails.raiseToHrComment}</span>
            </div>
         </div>
      </c:if>
      <c:if test="${(reviewDetails.reviewState != 6)}">
         <div class="mt-3">
            <ul class="progress-indicator">
               <c:forEach items="${stateMapping}" var="item" varStatus="count">
                  <c:if test="${!(item.key == 6 || ((item.key == 3 || item.key == 4) && reviewDetails.currentAccount == 'false' ))}">
                     <li
                     <c:if test="${(  count.index <= reviewDetails.reviewState -1  )}">
                        class="completed"
                     </c:if>
                     id="stateMap${item.key}"><span class="bubble"></span>
                     <span class="stacked-text">
                        <span class="subdued">${item.value}</span><br>
                        <c:if test="${progressMap[item.key] != ''}">
                           <span class="fa fa-calendar"></span>  ${progressMap[item.key]}
                        </c:if>
                     </span>
                     </li>
                  </c:if>
               </c:forEach>
            </ul>
         </div>
      </c:if>
   </div>
   <c:if test="${(reviewDetails.reviewState != 6)}">
      <div class="card-body">
         <c:if test="${(reviewDetails.currentYRReview=='false')}">
            <!-- Mid year -->
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
                  <c:forEach items="${reviewDetails.reviewLines}" var="item"
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
            <form onsubmit="return confirmMidyearWithUser(this);" id="managerMidyearRatingForm" method="post" >
               <input type="hidden" id="actionTaken" value="" />
               <input type="hidden" value="${reviewDetails.reviewId}"
                  name="<portlet:namespace/>reviewId" />
               <div class="form-row mt-2">
                  <div class="form-group col" id="achievementDiv">
                     <label class="w-100">Appraisee's Achievements</label>
                     <textarea row="2" column="50" style="height: 150px;"
                     class="w-100 form-control-plaintext border border-secondary form-control-sm"
                     <c:if test="${reviewDetails.reviewState > 2 }"> readonly</c:if>
                     >${reviewDetails.achievementsByEmp}</textarea></td>
                  </div>
                  <div class="form-group col" id="improvementDiv">
                     <label class="w-100">Improvement Area</label>
                     <textarea row="2" column="50" style="height: 150px;"
                     class="w-100 form-control-plaintext border border-secondary form-control-sm"
                     <c:if test="${reviewDetails.reviewState > 2 }"> readonly</c:if>
                     >${reviewDetails.improvementComment}</textarea></td>
                  </div>
                  <div class="form-group col" id="managerCommentDiv">
                     <label class="w-100">Manager's Overall Comments
                     </label>
                     <textarea row="2" column="50" style="height: 150px;"
                     class="w-100 form-control-plaintext border border-secondary form-control-sm"
                     title="${reviewDetails.overallManagerComment}"
                     <c:if test="${reviewDetails.reviewState > 2 }"> readonly</c:if>
                     >${reviewDetails.overallManagerComment}</textarea></td>
                  </div>
               </div>
               <div class="row">
                  <div class="col-4" id="managerFinalRatingDiv">
                     <label>Manager's Rating</label>
                     <select
                     class="mdb-select md-form form-control form-control-sm "
                     <c:if test="${reviewDetails.reviewState > 2 }"> disabled</c:if>
                     name="<portlet:namespace/>managerFinalRating" required="required" id="managerFinalRating">
                     <option value="" disabled selected>Select Rating</option>
                     <option ${(reviewDetails.rating == '4.5' || reviewDetails.rating == 'Consistently Exceeds Expectations') ? 'selected="selected"': ''} value="A">Consistently Exceeds Expectations</option>
                     <option ${(reviewDetails.rating == '3.5' || reviewDetails.rating == 'Sometimes Exceeds Expectations')  ? 'selected="selected"': ''} value="B">Sometimes Exceeds Expectations</option>
                     <option ${(reviewDetails.rating == '3.0' || reviewDetails.rating == 'Consistently Meets Expectations')  ? 'selected="selected"': ''} value="C">Consistently Meets Expectations</option>
                     <option ${(reviewDetails.rating == '1.5' || reviewDetails.rating == 'Inconsistently Meets Expectations')  ? 'selected="selected"': ''} value="D">Inconsistently Meets Expectations</option>
                     <option ${(reviewDetails.rating == '1.0' || reviewDetails.rating == 'Does not Meet Expectations')  ? 'selected="selected"': ''} value="E">Does not Meet Expectations</option>
                     </select>
                  </div>
               </div>
               <div class="row mt-3">
                  <c:if test="${(reviewDetails.currentAccount=='true')}">
                     <div class="col-md-6">
                        <div >
                           <c:if test="${reviewDetails.otherAccounts.size() > 0}">
                              <label>Employee's secondary assessments</label>
                              <table class="table table-sm table-bordered">
                                 <thead>
                                    <tr>
                                       <th width="25%" class="bg-info text-white text-center">Account</th>
                                       <th width="30%" class="bg-info text-white text-center">Manager</th>
                                       <th width="45%" class="bg-info text-white text-center">Manager's Rating</th>
                                    </tr>
                                 </thead>
                                 <tbody  >
                                    <c:forEach items="${reviewDetails.otherAccounts}" var="item" varStatus="count">
                                       <tr>
                                          <td class="text-center">${item.account}</td>
                                          <td class="text-center"><a href="mailto:${item.managerEmail}">${item.managerName}</a></td>
                                          <td class="text-center">
                                             <c:choose>
                                                <c:when test="${(item.rating != '')}">
                                                   <c:out value="${item.rating}" />
                                                </c:when>
                                                <c:otherwise>
                                                   <c:out value="Pending" />
                                                </c:otherwise>
                                             </c:choose>
                                          </td>
                                       </tr>
                                    </c:forEach>
                                 </tbody>
                              </table>
                           </c:if>
                        </div>
                     </div>
                  </c:if>
               </div>
               <c:if test="${ reviewDetails.reviewState == 2}">
                  <div class="custom-control custom-checkbox mt-3" id="undertakingDiv">
                     <input type="checkbox" class="custom-control-input"  oninvalid="this.setCustomValidity('Kindly acknowledge this to proceed')" oninput="this.setCustomValidity('')"
                        id="terms" /> <label  for="terms"
                        class="custom-control-label pl-2" style="font-size:0.8em;">
                     I acknowledge that Rating and Review is finalized with manager.</label>
                  </div>
               </c:if>
               <div class="row mt-5 mb-4 justify-content-md-center" id="managerHrSubmitDiv">
                  <c:if test="${(reviewDetails.managerViewStatus == 'true' && reviewDetails.reviewState == 2)}">
                     <div class="col text-center my-auto" id="rejectCommentDiv">
                        <div class="input-group">
                           <textarea class="form-control form-control-sm text-trim"
                              placeholder="Provide your reason for sending this review form back to KPI Settings stage for correction"
                              style="height: 40px;width:300px;" name="<portlet:namespace/>rejectObjRsn"
                              id="rejectObjRsn"></textarea>
                           <button class="btn btn-outline-warning" id="managerReject"
                              type="submit" onClick="submitMidyearForm(this)">Send Back to Employee</button>
                        </div>
                     </div>
                     <div class="col-2 text-center my-auto" id="saveDiv">
                        <button class="btn btn-outline-primary" type="button"
                           id="managerSave" onClick="saveForm(2)">Save</button>
                     </div>
                     <div class="col-2 text-center my-auto" id="submitDiv">
                        <button class="btn btn-primary" type="submit" id="managerSubmit"
                           onClick="submitMidyearForm(this)">Submit</button>
                     </div>
                  </c:if>
               </div>
            </form>
         </c:if>
      </div>

   <c:if test="${(reviewDetails.currentYRReview=='true')}">
      <!--Annual year MANAGER REVIEW -->
      <form  method="post"  onsubmit="return confirmWithUser(this);" id="annualSubmissionForm">
         <input type="hidden" id="actionTaken" value="" />
         <input type="hidden" value="${reviewDetails.reviewId}"
            name="<portlet:namespace/>reviewId" />
         <h6 id="managerHelp" class="text-center font-italic"> Please enter supporting comments for each KPIs by using <button  onclick="return false" class="btn btn-sm btn-info"><i class="fas fa-expand-arrows-alt"></i></button> <button    onclick="return false" class="btn btn-sm btn-warning"><i class="fas fa-compress-arrows-alt"></i></button> buttons below. It is a good practice to save the form frequently.</h6>
         <table
            class="table  table-sm  table-bordered table-striped table-hover table-custom mt-3" id="mainTable">
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
                  <th width="20%">KPIs <i class="fas fa-history" id="rejectionHistory" title="${reviewDetails.comment}"></i></th>
                  <th width="20%">Employee Self Review</th>
                  <th width="20%">Manager Review</th>
                  <c:if test="${reviewDetails.currentAccount=='true' && ((reviewDetails.managerViewStatus == 'false' && reviewDetails.adminRole == 'false' && reviewDetails.reviewState > 3)  || reviewDetails.reviewState > 4)}">
                     <th width="20%">HR Review</th>
                  </c:if>
               </tr>
            </thead>
            <tbody id="managerReviewBody">
               <c:forEach items="${reviewDetails.reviewLines}" var="item" varStatus="count">
                  <tr>
                     <td class="text-center py-0"><input type="hidden"
                        value="${item.reviewLineId}"
                        name="<portlet:namespace/>reviewLineId${count.index}" />
                        <button type="button" class="btn btn-link btn-sm"
                           data-toggle="collapse"
                           data-target="#reviewLineId${count.index}">Details</button>
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
                     <c:if test="${reviewDetails.reviewState > 2}">
                        <td class="py-0"><input type="hidden" value="${item.managerRating}"
                           id="managerRating${count.index}" /><strong>Manager Rating : </strong>${item.managerRating}</td>
                     </c:if>
                     <c:if test="${reviewDetails.reviewState == 2}">
                        <td class="p-0">
                           <select
                              class="mdb-select md-form form-control form-control-sm "
                              name="<portlet:namespace/>managerRating${count.index}" required="required"
                              id="managerRating${count.index}">
                              <option value="" disabled selected>Manager Rating</option>
                              <option value="A"
                              ${(item.managerRating =='A' || item.managerRating == 'Consistently Exceeds Expectations') ? 'selected="selected"': ''}>Consistently Exceeds Expectations</option>
                              <option value="B"
                              ${(item.managerRating =='B' || item.managerRating == 'Sometimes Exceeds Expectations') ? 'selected="selected"': ''}>Sometimes Exceeds Expectations</option>
                              <option value="C"
                              ${(item.managerRating =='C' || item.managerRating == 'Consistently Meets Expectations') ? 'selected="selected"': ''}>Consistently Meets Expectations</option>
                              <option value="D"
                              ${(item.managerRating =='D' || item.managerRating == 'Inconsistently Meets Expectations') ? 'selected="selected"': ''}>Inconsistently Meets Expectations</option>
                              <option value="E"
                              ${(item.managerRating =='E' || item.managerRating == 'Does not Meet Expectations') ? 'selected="selected"': ''}>Does not Meet Expectations</option>
                           </select>
                        </td>
                     </c:if>
                     <c:if test="${reviewDetails.currentAccount=='true' && reviewDetails.reviewState > 4}">
                        <td class="py-0"><input type="hidden" value="${item.hrRating}"
                           id="hrRating${count.index}" /><strong>HR Rating : </strong>${item.hrRating}</td>
                     </c:if>
                     <c:if test="${reviewDetails.currentAccount=='true' && (reviewDetails.managerViewStatus == 'false' && reviewDetails.adminRole == 'false' && reviewDetails.reviewState == 4)}">
                        <td class="p-0">
                           <select
                              class="mdb-select md-form form-control form-control-sm "
                              name="<portlet:namespace/>hrRating${count.index}" required="required"
                              id="hrRating${count.index}">
                              <option value="" disabled selected>HR Rating</option>
                              <option value="A"
                              ${(item.hrRating =='A' || item.hrRating == 'Consistently Exceeds Expectations') ? 'selected="selected"': ''}>Consistently Exceeds Expectations</option>
                              <option value="B"
                              ${(item.hrRating =='B' || item.hrRating == 'Sometimes Exceeds Expectations') ? 'selected="selected"': ''}>Sometimes Exceeds Expectations</option>
                              <option value="C"
                              ${(item.hrRating =='C' || item.hrRating == 'Consistently Meets Expectations') ? 'selected="selected"': ''}>Consistently Meets Expectations</option>
                              <option value="D"
                              ${(item.hrRating =='D' || item.hrRating == 'Inconsistently Meets Expectations') ? 'selected="selected"': ''}>Inconsistently Meets Expectations</option>
                              <option value="E"
                              ${(item.hrRating =='E' || item.hrRating == 'Does not Meet Expectations') ? 'selected="selected"': ''}>Does not Meet Expectations</option>
                           </select>
                        </td>
                     </c:if>
                  </tr>
                  <tr id="reviewLineId${count.index}" class="collapse">
                     <c:if test="${(item.attribute)}">
                        <td></td>
                        <td></td>
                        <td><strong>Employee Comments : </strong><br> <textarea row="2" column="50"
                           style="height: 70px;"
                           class="form-control-plaintext form-control-sm"
                           title="${item.employeeComment}" readonly>${item.employeeComment}</textarea></td>
                        <c:if test="${reviewDetails.reviewState > 2}">
                           <td><strong>Manager Comments : </strong><br> <textarea row="2" column="50"
                              style="height: 70px;"
                              class="form-control-plaintext form-control-sm"
                              title="${item.managerComment}" readonly>${item.managerComment}</textarea></td>
                        </c:if>
                        <c:if test="${reviewDetails.reviewState == 2}">
                           <td class="px-0"><strong>Manager Comments : </strong><br> <textarea row="2" column="50"
                              style="height: 70px;" maxlength="10000"
                              class="form-control form-control-sm text-trim"
                              placeholder="Give your Comments here.."
                              title="${item.managerComment}"
                              id="managerComment${count.index}"
                              name="<portlet:namespace/>managerComment${count.index}">${item.managerComment}</textarea></td>
                        </c:if>
                        <c:if test="${reviewDetails.currentAccount=='true' && reviewDetails.reviewState > 4}">
                           <td><strong>HR Comments : </strong><br> <textarea row="2" column="50"
                              style="height: 70px;"
                              class="form-control-plaintext form-control-sm"
                              title="${item.hrComment}" readonly>${item.hrComment}</textarea></td>
                        </c:if>
                        <c:if test="${reviewDetails.currentAccount=='true' && (reviewDetails.managerViewStatus == 'false' && reviewDetails.adminRole == 'false' && reviewDetails.reviewState == 4)}">
                           <td class="px-0"><strong>HR Comments : </strong><br> <textarea row="2" column="50"
                              style="height: 70px;" maxlength="10000"
                              class="form-control form-control-sm text-trim"
                              placeholder="Give your Comments here.."
                              title="${item.hrComment}"
                              id="hrComment${count.index}"
                              name="<portlet:namespace/>hrComment${count.index}">${item.hrComment}</textarea></td>
                        </c:if>
                     </c:if>
                     <c:if test="${(!item.attribute)}">
                        <td><strong>Description : </strong><br> <textarea row="2" column="50"
                           class="form-control-plaintext form-control-sm"
                           style="height: 150px;" title="${item.reviewLineDescription}"
                           readonly>${item.reviewLineDescription}</textarea></td>
                        <td><strong>Target : </strong><br> <textarea row="2" column="50"
                           style="height: 150px;"
                           class="form-control-plaintext form-control-sm"
                           title="${item.reviewLineTitle}" readonly>${item.reviewLineTitle}</textarea></td>
                        <td><strong>Employee Comments : </strong><br> <textarea row="2" column="50"
                           style="height: 150px;"
                           class="form-control-plaintext form-control-sm"
                           title="${item.employeeComment}" readonly>${item.employeeComment}</textarea></td>
                        <c:if test="${reviewDetails.reviewState > 2}">
                           <td><strong>Manager Comments : </strong><br> <textarea row="2" column="50"
                              style="height: 150px;"
                              class="form-control-plaintext form-control-sm"
                              title="${item.managerComment}" readonly>${item.managerComment}</textarea></td>
                        </c:if>
                        <c:if test="${reviewDetails.reviewState == 2}">
                           <td class="px-0"><strong>Manager Comments : </strong><br> <textarea row="2" column="50"
                              style="height: 150px;" maxlength="10000"
                              class="form-control form-control-sm text-trim"
                              placeholder="Enter Comments" title="${item.managerComment}"
                              id="managerComment${count.index}"
                              name="<portlet:namespace/>managerComment${count.index}">${item.managerComment}</textarea></td>
                        </c:if>
                        <c:if test="${reviewDetails.currentAccount=='true' && reviewDetails.reviewState > 4}">
                           <td><strong>HR Comments : </strong><br> <textarea row="2" column="50"
                              style="height: 150px;"
                              class="form-control-plaintext form-control-sm"
                              title="${item.hrComment}" readonly>${item.hrComment}</textarea></td>
                        </c:if>
                        <c:if test="${reviewDetails.currentAccount=='true' && (reviewDetails.managerViewStatus == 'false' && reviewDetails.adminRole == 'false' && reviewDetails.reviewState == 4)}">
                           <td class="px-0"><strong>HR Comments : </strong><br> <textarea row="2" column="50"
                              style="height: 150px;" maxlength="10000"
                              class="form-control form-control-sm text-trim"
                              placeholder="Enter Comments" title="${item.hrComment}"
                              id="hrComment${count.index}"
                              name="<portlet:namespace/>hrComment${count.index}">${item.hrComment}</textarea></td>
                        </c:if>
                     </c:if>
                  </tr>
               </c:forEach>
            </tbody>
         </table>
         <div class="form-row mt-2">
            <div class="form-group col" id="achievementDiv">
               <label>Appraisee Achievement</label>
               <textarea class="form-control-plaintext form-control-sm border border-secondary" row="2"
                  column="50" style="height: 300px;" title="" id="achievements"
                  readonly>${reviewDetails.achievementsByEmp}</textarea>
            </div>
            <div class="form-group col" id="remarkDiv">
               <label>Appraisee Remark</label>
               <textarea class="form-control-plaintext form-control-sm border border-secondary" row="2"
                  column="50" style="height: 300px;" title="" id="apraiseeRemarks"
                  readonly>${reviewDetails.overallEmployeeComment}</textarea>
            </div>
            <div class="form-group col" id="trainingNeededDiv">
               <label>Trainings Needed</label>
               <textarea class="w-100 form-control form-control-sm" row="2" column="50" onblur="$(this).val($(this).val().trim())"
                  style="height: 300px;" title=""
                  name="<portlet:namespace/>trainingReqd" id="trainingReqdToEmp" <c:if test="${reviewDetails.reviewState > 2 }"> readonly</c:if> >${reviewDetails.trainingsReqd}</textarea>
            </div>
            <div class="form-group col" id="improvementDiv">
               <label class="w-100">Improvement Area</label>
               <textarea  class="w-100 form-control form-control-sm" row="2" column="50" onblur="$(this).val($(this).val().trim())"
                  style="height: 300px;" title=""
                  name="<portlet:namespace/>improvementComment"
                  id="improvementComment" <c:if test="${reviewDetails.reviewState > 2 }"> readonly</c:if> >${reviewDetails.improvementComment}</textarea>
            </div>
            <div class="form-group col" id="managerOCommentDiv">
               <label>Manager Comment</label>
               <textarea class="w-100 form-control form-control-sm" row="2" column="50" onblur="$(this).val($(this).val().trim())"
                  style="height: 300px;"
                  name="<portlet:namespace/>managerOverallComments"
                  id="managerOverallComment" <c:if test="${reviewDetails.reviewState > 2 }"> readonly</c:if>>${reviewDetails.overallManagerComment}</textarea>
            </div>
            <c:if test="${reviewDetails.currentAccount=='true' && ((reviewDetails.managerViewStatus == 'false' && reviewDetails.adminRole == 'false' && reviewDetails.reviewState > 3) || reviewDetails.reviewState > 4)}">
               <div class="form-group col" id="hrOCommentDiv">
                  <label>HR Comment</label>
                  <textarea class="w-100 form-control form-control-sm" row="2" column="50" onblur="$(this).val($(this).val().trim())"
                     style="height: 300px;" title="" id="hrOverallComments"
                     name="<portlet:namespace/>hrOverallComments" <c:if test="${reviewDetails.reviewState > 4 }"> readonly</c:if>>${reviewDetails.overallHrComment}</textarea>
               </div>
            </c:if>
         </div>
                                 <div>
                                    <button type="button" class="btn btn-outline-primary mt-2" onclick="toggleOverlay()">Manager Section <i class="fas fa-expand-arrows-alt"></i></button>
                                    <h6>(For Leads and Managers only, mandatory to be reviewed and updated after employee's performance review discussion and before form submission)</h6>
                                 </div>
                                 <div id="overlay" style="opacity:0.0;">
                                     <div class="row">
                                         <div class="col" id="criticalAccountDiv">
                                             <label>Critical To Account <i class="fas fa-question text-danger" style="cursor: pointer"
                                                     title=" Resources who are pillars of your Account. You can consider combination (ideally all four) of following to evaluate such resources - Value Add, Client Facing, Billing Loss, Performance Level"></i>
                                             </label><br> <input
                                                 type="radio" class="fn_criticalToAcct" <c:if test="${reviewDetails.criticalToAcct == 1}"> checked="checked" </c:if> <c:if test="${reviewDetails.reviewState > 2 }"> onclick="return false"</c:if>  name="<portlet:namespace/>criticalToAcct" style="cursor:default;"
                                                 value="Yes"  /> <label>Yes</label>
                                             <input type="radio" class="fn_criticalToAcct" <c:if test="${reviewDetails.criticalToAcct == 2}"> checked="checked" </c:if> <c:if test="${reviewDetails.reviewState > 2 }"> onclick="return false"</c:if>  name="<portlet:namespace/>criticalToAcct" style="cursor:default;"
                                                 value="No"  /> <label>No</label>
                                         </div>
                                         <div class="col" id="criticalCompanyDiv">
                                             <label>Key For Retention <i class="fas fa-question text-danger" style="cursor: pointer"
                                                    title=" Resources who are to be retained if they resign. You can consider combination (ideally all four) of following to evaluate such resources - Value Add,  Client Facing, Billing Loss, Performance Level"></i>
                                             </label><br>
                                             <input type="radio" class="fn_criticalToCompany"  <c:if test="${reviewDetails.criticalToCompany == 1}"> checked="checked" </c:if> <c:if test="${reviewDetails.reviewState > 2 }"> onclick="return false"</c:if>  name="<portlet:namespace/>criticalToCompany" style="cursor:default;"
                                                 value="Yes"  /> <label>Yes</label>
                                             <input type="radio" class="fn_criticalToCompany" <c:if test="${reviewDetails.criticalToCompany == 2}"> checked="checked" </c:if> <c:if test="${reviewDetails.reviewState > 2 }"> onclick="return false"</c:if>  name="<portlet:namespace/>criticalToCompany" style="cursor:default;"
                                                 value="No"  /> <label>No</label>
                                         </div>
                                         <div class="col" id="managerRatingDiv">
                                             <label>Manager's Rating</label>
                                             <input type="text" readonly
                                                 class="form-control-plaintext form-control-sm border border-secondary text-center"
                                                 name="<portlet:namespace/>managerFinalRating" id="managerFinalRating"  />
                                                 <span style="font-size:.7em;">Do not discuss numeric rating  <i class="fa fa-question-circle" aria-hidden="true" title="For discussion with employee, use rating criteria in instructions on top of page."></i><span>
                                         </div>
                                         <c:if test="${reviewDetails.currentAccount=='true' && ((reviewDetails.managerViewStatus == 'false' && reviewDetails.adminRole == 'false') || reviewDetails.reviewState > 4)}">
                                             <div class="col" id="hrRatingDiv">
                                                 <label>HR's Rating</label><input type="text"
                                                     readonly
                                                     class="form-control-plaintext form-control-sm border border-secondary text-center"
                                                     id="hrFinalRating" name="<portlet:namespace/>hrFinalRating" />
                                             </div>
                                         </c:if>
                                     </div>
                                     <div class="border border-secondary rounded mt-2" id="promotionRequiredDiv">
                                         <table class="table table-sm table-bordered">
                                             <thead>
                                                 <tr>
                                                     <th colspan="2" class="text-center"><label>Below comments are for HR Reference. They are not to be committed to or discussed with employee</label><br><span>( Don't ask Employee's expectations on Salary and/or Designation, record only if employee gives input )</span></th>
                                                 </tr>
                                             </thead>
                                             <tbody>
                                                 <tr>
                                                     <td width="36%">Employee's Expected Designation</td>
                                                     <td  class="p-0"><input type="text" class="form-control form-control-sm py-0 text-trim"
                                                         maxlength="75" name="<portlet:namespace/>exceptedDesignation"
                                                         id="exceptedDesignation" value="${reviewDetails.exceptedDesignation}" <c:if test="${reviewDetails.reviewState > 2 }"> readonly</c:if> /></td>
                                                 </tr>
                                                 <tr>
                                                     <td>Employee's Expected Salary</td>
                                                     <td  class="p-0"><input type="text" class="form-control form-control-sm py-0 text-trim"
                                                         maxlength="75" name="<portlet:namespace/>exceptedSalary"
                                                         id="exceptedSalary" value="${reviewDetails.exceptedSalary}" <c:if test="${reviewDetails.reviewState > 2 }"> readonly</c:if> /></td>
                                                 </tr>
                                             </tbody>
                                         </table>
                                         <table class="table table-sm table-bordered">
                                             <thead>
                                                 <tr>
                                                     <th colspan="2"><label>Promotion Required : </label>
                                                         <input type="checkbox" value="Promotion is Required" <c:if test="${reviewDetails.promotionReqd}"> checked="checked" </c:if><c:if test="${reviewDetails.reviewState > 2 }"> onclick="return false"</c:if>  id="promotionReqd" name="<portlet:namespace/>promotionReqd"  />
                                                     </th>
                                                 </tr>
                                             </thead>
                                             <tbody id="promotionRequiredBody" <c:if test="${reviewDetails.promotionReqd=='false'}"> style="display:none" </c:if>>
                                                 <tr>
                                                     <td width="36%">Where resource has continuously excelled</td>
                                                     <td class="p-0"><textarea class="form-control form-control-sm py-0 text-trim" style="height:40px;"
                                                         maxlength="2000" name="<portlet:namespace/>excelledAreaAns" <c:if test="${reviewDetails.reviewState > 2 }"> readonly</c:if>
                                                         id="excelledAreaAns" <c:if test="${reviewDetails.promotionReqd}"> required </c:if>>${reviewDetails.excelledArea}</textarea></td>
                                                 </tr>
                                                 <tr>
                                                     <td>If and how resource is already playing role being
                                                         promoted to</td>
                                                     <td  class="p-0"><textarea class="form-control form-control-sm py-0 text-trim" style="height:40px;"
                                                         maxlength="2000" name="<portlet:namespace/>rolePlayedAns"
                                                         id="rolePlayedAns" <c:if test="${reviewDetails.promotionReqd}"> required </c:if> <c:if test="${reviewDetails.reviewState > 2 }"> readonly</c:if> >${reviewDetails.rolePlayed}</textarea></td>
                                                 </tr>
                                                 <tr>
                                                     <td>Reason for recommendation</td>
                                                     <td class="p-0"><textarea class="form-control form-control-sm py-0 text-trim" style="height:40px;"
                                                         maxlength="2000" name="<portlet:namespace/>reasonRecommendAns"
                                                         id="reasonRecommendAns" <c:if test="${reviewDetails.promotionReqd}"> required </c:if> <c:if test="${reviewDetails.reviewState > 2 }"> readonly</c:if> >${reviewDetails.rsnForPromotion}</textarea></td>
                                                 </tr>
                                                 <tr>
                                                     <td>Why is employee ready for next role</td>
                                                     <td  class="p-0"><textarea class="form-control form-control-sm py-0 text-trim" style="height:40px;"
                                                         maxlength="2000" name="<portlet:namespace/>readyAns" <c:if test="${reviewDetails.reviewState > 2 }"> readonly</c:if>
                                                         id="readyAns" <c:if test="${reviewDetails.promotionReqd}"> required </c:if> >${reviewDetails.readyAns}</textarea></td>
                                                 </tr>
                                             </tbody>
                                         </table>
                                     </div>
                                     <c:if test="${(reviewDetails.currentYRReview == 'true' && reviewDetails.currentAccount == 'true')}">
                                     <div class="border border-secondary rounded mt-2">
                                         <table class="table table-sm table-bordered">
                                             <thead>
                                                 <tr>
                                                     <th colspan="2" class="text-center"><label>Succession Planning</label></th>
                                                 </tr>
                                             </thead>
                                             <tbody>
                                                 <tr>
                                                 <td>
                                                     <div class="row pt-2">
                                                         <div class="col-5" id="replacementRadioDiv">
                                                             <label>Can someone from current project replace the resource ? </label><br> <input
                                                                 type="radio" class="fn_replacement" <c:if test="${reviewDetails.replacement == 1}"> checked="checked" </c:if><c:choose><c:when test="${reviewDetails.reviewState > 2}"> onclick="return false" </c:when><c:otherwise> onclick="toggleReplacementDetail()"</c:otherwise></c:choose> name="<portlet:namespace/>replacement" style="cursor:default;"
                                                                 value="Yes"  /> <label>Yes</label>
                                                             <input type="radio" class="fn_replacement"  <c:if test="${reviewDetails.replacement == 2}"> checked="checked" </c:if><c:choose><c:when test="${reviewDetails.reviewState > 2}"> onclick="return false" </c:when><c:otherwise> onclick="toggleReplacementDetail()"</c:otherwise></c:choose>  name="<portlet:namespace/>replacement" style="cursor:default;"
                                                                 value="No"  /> <label>No</label>
                                                         </div>
                                                         <div class="col-3" id="replacementDropdownDiv" <c:if test="${reviewDetails.replacement != 1 }"> style="display:none;" </c:if>>
                                                                 <label>Team Member </label><br>
                                                                 <select class="mdb-select md-form form-control form-control-sm"  name="<portlet:namespace/>replacementEcode" id="replacementDropdown" >
                                                                   <option value="" disabled selected>Select team member</option>
                                                                   <c:forEach items="${reviewDetails.teamMembers}" var="member" varStatus="count">
                                                                   <option  ${(member.key == reviewDetails.replacementDetail) ? 'selected="selected"': ''}
                                                                   value="${member.key}" <c:if test="${reviewDetails.reviewState!=2}">disabled style="display:none"</c:if>>${member.value} (${member.key})</option>
                                                                   </c:forEach>
                                                                 </select>
                                                         </div>
                                                         <div class="col-7" id="replacementReasonDiv" <c:if test="${reviewDetails.replacement != 2 }"> style="display:none;" </c:if>>
                                                             <label>Provide Reasons </label><br>
                                                             <textarea class="form-control form-control-sm py-0 text-trim" style="height:40px;"
                                                                 maxlength="2000" name="<portlet:namespace/>replacementDetail"
                                                                 id="replacementReason" <c:if test="${reviewDetails.reviewState > 2 && reviewDetails.currentAccount=='true'}"> readonly </c:if>>${reviewDetails.replacementDetail}</textarea>
                                                         </div>
                                                         <div class="col-4" id="replacementReasonDropdownDiv" <c:if test="${reviewDetails.replacement != 1 }"> style="display:none;" </c:if>>
                                                              <label>Reason why this resource can be successor </label><br>
                                                              <select class="mdb-select md-form form-control form-control-sm"  name="<portlet:namespace/>replacementReason" id = "replacementReasonId" onchange = "showHideReplacementReasonDiv(this)" >
                                                                <c:if test="${reviewDetails.reviewState==2}"><option value="" disabled selected>Select Option</option></c:if>
                                                                <option <c:choose><c:when test="${reviewDetails.replacementReason=='Successor is already shadowing Employee' && reviewDetails.replacementReasonOther =='false'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${reviewDetails.reviewState!=2}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Successor is already shadowing Employee">Successor is already shadowing Employee</option>
                                                                <option <c:choose><c:when test="${reviewDetails.replacementReason=='Successor has shown potential to move to next level' && reviewDetails.replacementReasonOther =='false'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${reviewDetails.reviewState!=2}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Successor has shown potential to move to next level">Successor has shown potential to move to next level</option>
                                                                <option <c:choose><c:when test="${reviewDetails.replacementReason=='Successor is already playing the role currently being played by employee' && reviewDetails.replacementReasonOther =='false'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${reviewDetails.reviewState!=2}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Successor is already playing the role currently being played by employee">Successor is already playing the role currently being played by employee</option>
                                                                <option <c:choose><c:when test="${reviewDetails.replacementReasonOther =='true'}"> selected="selected"  </c:when> <c:otherwise> <c:if test="${reviewDetails.reviewState!=2}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Other">Other</option>
                                                              </select>
                                                                  <div id="replacementOtherReasonDiv" <c:if  test="${(reviewDetails.replacementReasonOther=='false')}"> style="display:none;"</c:if> >
                                                                     <br />
                                                                     <textarea placeholder="Please provide reason" style="height: 46px;" id="replacementOtherReason" class="mdb-select md-form form-control form-control-sm text-trim" maxlength="2000" name="<portlet:namespace/>replacementOtherReason"  <c:if test="${reviewDetails.reviewState!=2}">readonly</c:if>  >${ reviewDetails.replacementReasonOther=='false' ? '' :reviewDetails.replacementReason}</textarea>
                                                                  </div>
                                                         </div>
                                                     </div>
                                                 </td>
                                                 </tr>
                                             </tbody>
                                         </table>
                                     </div>
                                     </c:if>
                                 </div>
                          <div class="row mt-3">
                            <div class="col-md-6" id="employeeAttachedRemarks">
                                <c:if test="${reviewDetails.uploadsFiles.size() > 0}">
                                    <label>Uploaded Attachments:</label>
                                    <ul>
                                        <c:forEach items="${reviewDetails.uploadsFiles}"
                                            var="item" varStatus="count">
                                            <li style="height: 25px;"><span class="btn btn-link"
                                            onClick="downloadAttachments('${item.uploadId}','${item.fileName}');">${item.fileName}</span></li>
                                        </c:forEach>
                                    </ul>
                                </c:if>
                            </div>
                            <c:if test="${(reviewDetails.currentAccount=='true')}">
                                <div class="col-md-6">
                                    <div >
                                        <c:if test="${reviewDetails.otherAccounts.size() > 0}">
                                            <label>Employee's secondary reviews</label>
                                            <table class="table table-sm table-bordered">
                                                <thead>
                                                    <tr>
                                                        <th width="25%" class="bg-info text-white text-center">Account</th>
                                                        <th width="30%" class="bg-info text-white text-center">Manager</th>
                                                        <th width="45%" class="bg-info text-white text-center">Manager's Rating</th>
                                                    </tr>
                                                </thead>
                                                <tbody  >
                                                    <c:forEach items="${reviewDetails.otherAccounts}" var="item" varStatus="count">
                                                        <tr>
                                                            <td class="text-center">${item.account}</td>
                                                            <td class="text-center"><a href="mailto:${item.managerEmail}">${item.managerName}</a></td>
                                                            <td class="text-center">
                                                                <c:choose>
                                                                    <c:when test="${(item.rating != '')}">
                                                                       <c:out value="${item.rating}" />
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                       <c:out value="Pending" />
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </c:if>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                                                         <c:if test="${(reviewDetails.currentAccount=='true' && reviewDetails.reviewState == 2)}">
                                                             <div class="custom-control custom-checkbox mt-3" id="undertakingDiv">
                                                                 <input type="checkbox" class="custom-control-input"  oninvalid="this.setCustomValidity('Kindly acknowledge this to proceed')" oninput="this.setCustomValidity('')"
                                                                      id="terms" /> <label  for="terms"
                                                                      class="custom-control-label pl-2" style="font-size:0.8em;">
                                                                      I acknowledge that <c:if test="${reviewDetails.leadViewStatus == 'true'}">Rating and Review is finalized with manager.</c:if> I have done performance review meeting with employee<span id="primarydecleration"> and I have also considered the performance assessment of employee from other managers, as applicable while doing performance review</span>.</label>
                                                             </div>
                                                         </c:if>
                                                         <c:if test="${(reviewDetails.currentAccount=='false'  && reviewDetails.reviewState == 2)}">
                                                             <div class="custom-control custom-checkbox mt-3" id="undertakingDiv">
                                                                 <input type="checkbox" class="custom-control-input"  oninvalid="this.setCustomValidity('Kindly acknowledge this to proceed')" oninput="this.setCustomValidity('')"
                                                                      id="terms" /> <label  for="terms"
                                                                      class="custom-control-label pl-2" style="font-size:0.8em;">
                                                                      I acknowledge that Rating and Review is finalized with manager.</label>
                                                             </div>
                                                         </c:if>
                                                         <div class="row mt-5 mb-4 justify-content-md-center" id="managerHrSubmitDiv">
                                                             <c:if test="${(reviewDetails.managerViewStatus == 'true' && reviewDetails.reviewState == 2)}">
                                                                 <div class="col text-center my-auto" id="rejectCommentDiv">
                                                                     <div class="input-group">
                                                                         <textarea class="form-control form-control-sm text-trim"
                                                                             placeholder="Provide your reason for sending this review form back to Self Review stage for correction"
                                                                             style="height: 40px;width:300px;" name="<portlet:namespace/>rejectObjRsn"
                                                                             id="rejectObjRsn"></textarea>
                                                                         <button class="btn btn-outline-warning" id="managerReject"
                                                                             type="submit" onClick="submitForm(this)">Send Back to Employee</button>
                                                                     </div>
                                                                 </div>
                                                             </c:if>
                                                             <c:if test="${(reviewDetails.managerViewStatus == 'true' && reviewDetails.reviewState == 2)}">
                                                                 <div class="col-2 text-center my-auto" id="saveDiv">
                                                                     <button class="btn btn-outline-primary" type="button"
                                                                         id="managerSave" onClick="saveForm(1)">Save</button>
                                                                 </div>
                                                                 <c:if test="${reviewDetails.secondaryAssessed == 'true'}">
                                                                     <div class="col-2 text-center my-auto" id="submitDiv">
                                                                         <button class="btn btn-primary" type="submit" id="managerSubmit"
                                                                             onClick="submitForm(this)">Submit</button>
                                                                     </div>
                                                                 </c:if>
                                                                 <c:if test="${reviewDetails.currentAccount=='true' && reviewDetails.secondaryAssessed == 'false'}">
                                                                     <div class="col-2 text-center my-auto">
                                                                         <h6 class="text-center font-italic">
                                                                             Employee's all secondary reviews have not been rated yet, you may SAVE the review form.
                                                                         </h6>
                                                                     </div>
                                                                 </c:if>
                                                             </c:if>
                                                             <c:if test="${reviewDetails.currentAccount=='true' && ((reviewDetails.managerViewStatus == 'false' && reviewDetails.adminRole == 'false') && reviewDetails.reviewState == 4 )}">
                                                                 <div class="col-2 text-center my-auto" id="saveDiv">
                                                                     <button class="btn btn-outline-primary" type="button"
                                                                         id="hrSave" onClick="saveHrForm(1)">Save</button>
                                                                 </div>
                                                                 <div class="col-2 text-center my-auto" id="submitDiv">
                                                                     <button class="btn btn-primary" type="submit" id="hrSubmit"
                                                                         onClick="submitForm(this)">Submit</button>
                                                                 </div>
                                                             </c:if>
                                                         </div>
      </form>
   </c:if>
   </c:if>
</div>
<script>
$(document).ready(function() {
setInterval('alertModal("Performance Modal:It is a good practise to save your work frequently.")', 3600000);
});
</script>