<%@ include file="/init.jsp"%>
<portlet:actionURL name="submitEmpSkillRating" var="submitSkillRatingUrl"></portlet:actionURL>
<portlet:renderURL var="skillRenderURL" windowState="normal"></portlet:renderURL>
<portlet:resourceURL var="downloadCertificatesUrl" id="downloadCertificates"></portlet:resourceURL>
<div class="container">
   <c:set var="pageUrl"
      value="${fn:split(skillRenderURL.toString(),'?')}" />
   <div class="card">
      <div class="card-header">
         <div class="row pt-2">
            <div class="col-md-10">
               <h5>General Information</h5>
            </div>
            <div class="col-2 text-right">
               <a class="btn btn-outline-secondary" href="${pageUrl[0]}"
                  style="color: black;" role="button"> Cancel</a>
            </div>
         </div>
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
               <input type="text" class=" form-control-sm form-control-plaintext"
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
         <div class="row pt-2">
            <div class="col-10">
               <h5>Employee Skills</h5>
            </div>
            <div class="col-2 text-right">
               <i class="fas fa-history" style="cursor: pointer"
                  title="${empCoreDetails.comment}"></i>
            </div>
         </div>
      </div>
      <div class="card-body">
         <c:if test="${empCoreDetails.mode == 'edit'}">
            <div  class="border border-dark p-2 mb-3" >
               <label> General Instructions for skill level :</label>
               <ul>
                  <li>Basic - Have common knowledge or understanding of the basic concepts</li>
                  <li>Beginner - Have classroom scenarios or as a trainee on-the-job experience and expected to need help when performing</li>
                  <li>Intermediate - Able to successfully complete tasks in this competency but expert level help needed from time to time</li>
                  <li>Advanced - Can perform without assistance and recognized as a person to ask</li>
                  <li>Expert - Known expert in this area and can provide guidance, troubleshoot and answer questions</li>
               </ul>
            </div>
         </c:if>
         <c:if test="${empCoreDetails.mode == 'edit'}">
            <h5 class="text-center font-italic">
               Please update level of one or more skill if required.
            </h5>
         </c:if>
         <form action="${submitSkillRatingUrl}" method="post" id="submitSkillForm"
            onSubmit="return confirmFormSubmission();">
            <input type="hidden" value="${empCoreDetails.empEcode}"
               name="<portlet:namespace/>ecode" /> <input type="hidden"
               id="actionPerformed" name="<portlet:namespace/>actionPerformed" />
            <table
               class="table table-sm  table-bordered  table-striped text-center"
               >
               <thead class="thead-dark">
                  <tr>
                     <c:if test="${empCoreDetails.mode == 'edit'}">
                        <th>
                           <input
                              type="checkbox" id="selectAllCheckbox" />
                        </th>
                     </c:if>
                     <th width="18%" class="text-center align-middle">Core & Sub
                        Skill
                     </th>
                     <th class="text-center align-middle" >
                        Type &
                        Level
                        <c:if test="${empCoreDetails.mode != 'view'}"> <i class="fas fa-question text-warning" style="cursor: pointer"
                           title="Please update employees proficiency level if required"></i></c:if>
                     </th>
                     <th class="text-center align-middle" >Tool</th>
                     <th class="text-center align-middle" >Version</th>
                     <th class="text-center align-middle" >Validity</th>
                     <th class="text-center align-middle" >Status</th>
                     <th class="text-center align-middle" >Required
                        Rating <i class="fas fa-question text-warning" style="cursor: pointer"
                           title="Rating required to perform at optimum performance level"></i>
                     </th>
                  </tr>
               </thead>
               <tbody id="empSkillTableBody">
                  <c:forEach items="${empSkillsSet}" var="item" varStatus="count">
                     <tr>
                        <c:if test="${empCoreDetails.mode == 'edit'}">
                           <c:choose>
                              <c:when test="${item.status != 'Submitted'}">
                                 <td></td>
                              </c:when>
                              <c:otherwise>
                                 <td>
                                    <input
                                    <c:if test="${item.status != 'Submitted'}">disabled</c:if>
                                    type="checkbox" onClick="singleCheckboxChecked();"
                                    value="${item.skillId}" id="checkbox${count.count}"
                                    name="<portlet:namespace/>checkbox" />
                                 </td>
                              </c:otherwise>
                           </c:choose>
                        </c:if>
                        <td ><input type="text"
                           title="${item.coreSkill}"
                           class=" form-control-sm form-control-plaintext pl-2" readonly
                           value="${item.coreSkill}" /> <input type="text"
                           title="${item.subSkill}"
                           class=" form-control-sm form-control-plaintext pl-2" readonly
                           value="${item.subSkill}" /></td>
                        <td>
                           <input type="text"
                              class=" form-control-sm form-control-plaintext pl-2" readonly
                              value="${item.skillType}" />
                           <select
                              class="mdb-select  form-control-sm" id="proficiencyLevel${count.count}" onfocus="setProficiencyLevelValue(this)" onchange="compareWithInitialProficiencyVal(this)"
                              name="<portlet:namespace/>proficiencyLevel${item.skillId}"
                              >
                              <c:if test="${empCoreDetails.mode == 'edit' && item.status == 'Submitted'}">
                                 <option value="" disabled selected>Select Level</option>
                              </c:if>
                              <option
                              <c:choose>
                                 <c:when test="${item.proficiencyLevel =='1'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${empCoreDetails.mode == 'view' || item.status != 'Submitted'}"> style="display:none" disabled</c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="1">Basic</option>
                              <option
                              <c:choose>
                                 <c:when test="${item.proficiencyLevel =='2'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${empCoreDetails.mode == 'view' || item.status != 'Submitted'}"> style="display:none" disabled</c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="2">Beginner</option>
                              <option
                              <c:choose>
                                 <c:when test="${item.proficiencyLevel =='3'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${empCoreDetails.mode == 'view' || item.status != 'Submitted'}"> style="display:none" disabled</c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="3">Intermediate</option>
                              <option
                              <c:choose>
                                 <c:when test="${item.proficiencyLevel =='4'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${empCoreDetails.mode == 'view' || item.status != 'Submitted'}"> style="display:none" disabled</c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="4">Advanced</option>
                              <option
                              <c:choose>
                                 <c:when test="${item.proficiencyLevel =='5'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${empCoreDetails.mode == 'view' || item.status != 'Submitted'}"> style="display:none" disabled</c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="5">Expert</option>
                           </select>
                        </td>
                        <td ><input type="hidden" id="proficiencyLevelChanged${count.count}"/><textarea rows="2" cols="50"
                           style="height: 65px" title="${item.tool}"
                           class="form-control-sm form-control-plaintext" readonly>${item.tool}</textarea>
                        </td>
                        <td ><textarea rows="2" cols="50"
                           style="height: 65px" title="${item.version}"
                           class="form-control-sm form-control-plaintext" readonly>${item.version}</textarea>
                        </td>
                        <td ><input type="text"
                           class=" form-control-sm form-control-plaintext text-center" readonly
                           value="${item.validity}" /></td>
                        <td >
                           <input type="text"
                           <c:if test="${(  item.status == 'Submitted'  )}"> class="form-control-sm form-control-plaintext text-center text-primary" </c:if>
                           <c:if test="${(  item.status == 'Approved'  )}"> class="form-control-sm form-control-plaintext text-center text-success" </c:if>
                           <c:if test="${(  item.status == 'Disapproved'  )}"> class="form-control-sm form-control-plaintext text-center text-danger" </c:if>
                           readonly value="${item.status}" />
                        </td>
                        <td >
                           <select
                              class="mdb-select  form-control-sm"
                              name="<portlet:namespace/>proficiencyLevelRating${item.skillId}">
                              <c:if test="${empCoreDetails.mode == 'edit' && item.status == 'Submitted'}">
                                 <option value="" disabled selected>Select Level</option>
                              </c:if>
                              <option
                              <c:choose>
                                 <c:when test="${item.requiredLevel =='1'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${empCoreDetails.mode == 'view' || item.status != 'Submitted'}"> style="display:none" disabled</c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="1">Basic</option>
                              <option
                              <c:choose>
                                 <c:when test="${item.requiredLevel =='2'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${empCoreDetails.mode == 'view' || item.status != 'Submitted'}"> style="display:none" disabled</c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="2">Beginner</option>
                              <option
                              <c:choose>
                                 <c:when test="${item.requiredLevel =='3'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${empCoreDetails.mode == 'view' || item.status != 'Submitted'}"> style="display:none" disabled</c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="3">Intermediate</option>
                              <option
                              <c:choose>
                                 <c:when test="${item.requiredLevel =='4'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${empCoreDetails.mode == 'view' || item.status != 'Submitted'}"> style="display:none" disabled</c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="4">Advanced</option>
                              <option
                              <c:choose>
                                 <c:when test="${item.requiredLevel =='5'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${empCoreDetails.mode == 'view' || item.status != 'Submitted'}"> style="display:none" disabled</c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="5">Expert</option>
                           </select>
                        </td>
                     </tr>
                  </c:forEach>
               </tbody>
            </table>
            <c:if test="${empCoreDetails.mode == 'edit'}">
               <div class="custom-control custom-checkbox mt-3 ml-3"
                  >
                  <input type="checkbox" class="custom-control-input" id="terms" />
                  <label   class="custom-control-label pl-2"> I acknowledge that I have changed level of submitted skill after discussion with the employee. </label>
               </div>
            </c:if>
            <c:if test="${empCoreDetails.mode == 'edit'}">
               <div class="row mt-3"
                  >
                  <div class="col-6 text-center">
                     <button class="btn btn-outline-success" type="submit"
                        onClick="return formValidation(1)">Approve</button>
                  </div>
                  <div class="col-6">
                     <div class="input-group">
                        <textarea class="form-control form-control-sm"
                           onblur="$(this).val($(this).val().trim())"
                           placeholder="Provide your reason for disapproving a skill"
                           style="height: 40px;" rows="2" cols="10" maxlength="1000"
                           name="<portlet:namespace/>skillRejectionReason" id="skillRejectionReason"></textarea>
                        <button class="btn btn-outline-danger" type="submit"
                           onClick="return formValidation(2)">Disapprove</button>
                     </div>
                  </div>
               </div>
            </c:if>
         </form>
      </div>
   </div>
   <c:if test="${certificatesList.size() >  0}">
      <div class="card pb-2"
         >
         <div class="card-header">
            <div class="row pt-2">
               <div class="col-md-8">
                  <h5>Employee Certifications</h5>
               </div>
            </div>
         </div>
         <div class="card-body">
            <input type="hidden" id="downloadCertificatesUrl" value="${downloadCertificatesUrl}"/>
            <input type="hidden" id="portletNamespace"   value="<portlet:namespace/>" />
            <table
               class="table table-sm  table-bordered  table-striped text-center">
               <thead class="thead-dark">
                  <tr>
                     <th class="text-center align-middle" width="20%">Category</th>
                     <th class="text-center align-middle" width="15%">Name</th>
                     <th class="text-center align-middle" width="30%">Description
                     </th>
                     <th class="text-center align-middle" width="10%">Expiry
                        Date
                     </th>
                     <th class="text-center align-middle" width="25%">Attachment
                     </th>
                  </tr>
               </thead>
               <tbody >
                  <c:forEach items="${certificatesList}" var="item"
                     varStatus="count">
                     <tr>
                        <td >${item.category}</td>
                        <td style=" word-break: break-all;">${item.certificateName}</td>
                        <td style=" word-break: break-all;">${item.description}
                        </td>
                        <td >${item.expiryDate}</td>
                        <td class="p-0">
                           <button type="button" class="btn btn-link btn-sm"
                              onClick="downloadAttachments('${item.certificateId}','${item.fileName}');"><i class="fa-solid fa-download"></i> ${item.fileName}</button>
                        </td>
                     </tr>
                  </c:forEach>
               </tbody>
            </table>
         </div>
      </div>
   </c:if>
</div>