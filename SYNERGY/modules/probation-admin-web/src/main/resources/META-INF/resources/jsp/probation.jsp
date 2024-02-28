<%@ include file="/init.jsp" %>

<portlet:actionURL name="saveProbation" var="saveProbationUrl"></portlet:actionURL>
<portlet:renderURL var="probationRenderURL" windowState="normal"></portlet:renderURL>

<div class="container">
   <c:set var="pageUrl" value="${fn:split(probationRenderURL.toString(),'?')}" />
   <div class="card">
      <div class="card-header">
         <div class="row">
            <div class="col pt-2">
               <h5>Probation Form</h5>
            </div>
            <div class="col-2 text-right">
                <a class="btn btn-outline-secondary" id="reset" href="${pageUrl[0]}"  role="button">Cancel</a>
            </div>
         </div>
      </div>
      <div class="card-body">
         <form method="post" id="probationForm"  action="${saveProbationUrl}" onsubmit="return saveProbation();" >
            <input type="hidden" name="<portlet:namespace/>probationState" id="newProbationState" />
            <div class="form-row">
               <div class="form-group col-md-2 mt-2 text-right mb-0">
                  <label for="ecode">Ecode</label>
               </div>
               <div class="form-group col-md-4 mb-0">
                  <input type="text" class="form-control-plaintext" readonly id="ecode" name="<portlet:namespace/>probationId" value="${probation.empCode}"  />
               </div>
               <div class="form-group col-md-2 mt-2 text-right mb-0">
                  <label for="name">Name</label>
               </div>
               <div class="form-group col-md-4 mb-0">
                  <input type="text" class="form-control-plaintext" readonly id="name" value="${probation.empName}"  />
               </div>
            </div>
            <div class="form-row">
               <div class="form-group col-md-2 mt-2 text-right mb-0">
                  <label for="designation">Designation</label>
               </div>
               <div class="form-group col-md-4 mb-0">
                  <input type="text" class="form-control-plaintext" readonly id="designation" value="${probation.empDesignation}"  />
               </div>
               <div class="form-group col-md-2 mt-2 text-right mb-0">
                  <label for="doj">DOJ</label>
               </div>
               <div class="form-group col-md-4 mb-0">
                  <input type="text" class="form-control-plaintext" readonly id="doj" value="${probation.empDoj}"  />
               </div>
            </div>
            <div class="form-row">
               <div class="form-group col-md-2 mt-2 text-right mb-0">
                  <label for="manager">Manager</label>
               </div>
               <div class="form-group col-md-4 mb-0">
                  <input type="text" class="form-control-plaintext" readonly id="manager" value="${probation.managerName}"  />
               </div>
               <div class="form-group col-md-2 mt-2 text-right mb-0">
                  <label for="reviewer">Reviewer</label>
               </div>
               <div class="form-group col-md-4 mb-0">
                  <input type="text" class="form-control-plaintext" readonly id="reviewer" value="${probation.reviewerName}"  />
               </div>
            </div>
            <div class="form-row">
               <div class="form-group col-md-2 mt-2 text-right mb-0">
                  <label for="account">Account</label>
               </div>
               <div class="form-group col-md-4 mb-0">
                  <input type="text" class="form-control-plaintext"  readonly id="account" value="${probation.empAccount}"  />
               </div>
               <div class="form-group col-md-2 mt-2 text-right mb-0">
                  <label for="status">Probation Status</label>
               </div>
               <div class="form-group col-md-4 mb-0">
                  <input type="text" class="form-control-plaintext" readonly id="status" value="${probation.probationStateName}"  />
               </div>
            </div>
            <table class="table table-sm table-bordered table-responsive-md table-striped mt-3">
               <thead class="thead-dark text-center">
                  <tr>
                     <th class="text-center align-middle" width="40%">Parameter</th>
                     <th class="text-center align-middle" width="20%">Rating</th>
                     <th class="text-center align-middle" width="40%">Supporting Comment</th>
                  </tr>
               </thead>
               <tbody>
                  <tr>
                     <td class="bg-info text-white py-0" colspan="3"><strong>Quality Of Work</strong></td>
                  </tr>
                  <tr>
                     <td class="py-0">1. Demonstrate high quality standards in one's work</td>
                     <td class="p-0 text-center">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <select class="mdb-select md-form form-control form-control-sm" required="required" name="<portlet:namespace/>rating1" >
                                   <option value="" disabled selected>Choose your option</option>
                                   <option value="1" >Very Poor</option>
                                   <option value="2" >Poor</option>
                                   <option value="3" >Good</option>
                                   <option value="4" >Very Good</option>
                                   <option value="5" >NA</option>
                                </select>
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[0].ratingDescription}
                            </c:otherwise>
                        </c:choose>
                     </td>
                     <td class="p-0">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <input type="text" class="form-control form-control-sm" maxlength="300" name="<portlet:namespace/>comment1" onblur="$(this).val($(this).val().trim())" />
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[0].comment}
                            </c:otherwise>
                        </c:choose>
                     </td>
                  </tr>
                  <tr>
                     <td class="py-0">2. Deliver on promise - meet commitments</td>
                     <td class="p-0 text-center">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <select class="mdb-select md-form form-control form-control-sm" required="required" name="<portlet:namespace/>rating2" >
                                   <option value="" disabled selected>Choose your option</option>
                                   <option value="1" >Very Poor</option>
                                   <option value="2" >Poor</option>
                                   <option value="3" >Good</option>
                                   <option value="4" >Very Good</option>
                                   <option value="5" >NA</option>
                                </select>
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[1].ratingDescription}
                            </c:otherwise>
                        </c:choose>
                     </td>
                     <td class="p-0">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <input type="text" class="form-control form-control-sm" maxlength="300" name="<portlet:namespace/>comment2" onblur="$(this).val($(this).val().trim())" />
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[1].comment}
                            </c:otherwise>
                        </c:choose>
                     </td>
                  </tr>
                  <tr>
                     <td class="py-0">3. Accept Accountability</td>
                     <td class="p-0 text-center">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <select class="mdb-select md-form form-control form-control-sm" required="required" name="<portlet:namespace/>rating3" >
                                   <option value="" disabled selected>Choose your option</option>
                                   <option value="1" >Very Poor</option>
                                   <option value="2" >Poor</option>
                                   <option value="3" >Good</option>
                                   <option value="4" >Very Good</option>
                                   <option value="5" >NA</option>
                                </select>
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[2].ratingDescription}
                            </c:otherwise>
                        </c:choose>
                     </td>
                     <td class="p-0">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <input type="text" class="form-control form-control-sm" maxlength="300" name="<portlet:namespace/>comment3" onblur="$(this).val($(this).val().trim())" />
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[2].comment}
                            </c:otherwise>
                        </c:choose>
                     </td>
                  </tr>
                  <tr>
                     <td  class="py-0">4. View products & services from a global perspective with world wide standards</td>
                     <td class="p-0 text-center">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <select class="mdb-select md-form form-control form-control-sm" required="required" name="<portlet:namespace/>rating4" >
                                   <option value="" disabled selected>Choose your option</option>
                                   <option value="1" >Very Poor</option>
                                   <option value="2" >Poor</option>
                                   <option value="3" >Good</option>
                                   <option value="4" >Very Good</option>
                                   <option value="5" >NA</option>
                                </select>
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[3].ratingDescription}
                            </c:otherwise>
                        </c:choose>
                     </td>
                     <td class="p-0">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <input type="text" class="form-control form-control-sm" maxlength="300" name="<portlet:namespace/>comment4" onblur="$(this).val($(this).val().trim())" />
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[3].comment}
                            </c:otherwise>
                        </c:choose>
                     </td>
                  </tr>
                  <tr>
                     <td class="bg-info text-white py-0" colspan="3"><strong>Customer Focus/Orientation</strong></td>
                  </tr>
                  <tr>
                     <td  class="py-0">5. Identify & understand Customer needs and Expectations</td>
                     <td class="p-0 text-center">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <select class="mdb-select md-form form-control form-control-sm" required="required" name="<portlet:namespace/>rating5" >
                                   <option value="" disabled selected>Choose your option</option>
                                   <option value="1" >Very Poor</option>
                                   <option value="2" >Poor</option>
                                   <option value="3" >Good</option>
                                   <option value="4" >Very Good</option>
                                   <option value="5" >NA</option>
                                </select>
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[4].ratingDescription}
                            </c:otherwise>
                        </c:choose>
                     </td>
                     <td class="p-0">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <input type="text" class="form-control form-control-sm" maxlength="300" name="<portlet:namespace/>comment5" onblur="$(this).val($(this).val().trim())" />
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[4].comment}
                            </c:otherwise>
                        </c:choose>
                     </td>
                  </tr>
                  <tr>
                     <td  class="py-0">6. Anticipate customer requirements</td>
                     <td class="p-0 text-center">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <select class="mdb-select md-form form-control form-control-sm" required="required" name="<portlet:namespace/>rating6" >
                                   <option value="" disabled selected>Choose your option</option>
                                   <option value="1" >Very Poor</option>
                                   <option value="2" >Poor</option>
                                   <option value="3" >Good</option>
                                   <option value="4" >Very Good</option>
                                   <option value="5" >NA</option>
                                </select>
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[5].ratingDescription}
                            </c:otherwise>
                        </c:choose>
                     </td>
                     <td class="p-0">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <input type="text" class="form-control form-control-sm" maxlength="300" name="<portlet:namespace/>comment6" onblur="$(this).val($(this).val().trim())" />
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[5].comment}
                            </c:otherwise>
                        </c:choose>
                     </td>
                  </tr>
                  <tr>
                     <td  class="py-0">7. Ensure customer needs are met</td>
                     <td class="p-0 text-center">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <select class="mdb-select md-form form-control form-control-sm" required="required" name="<portlet:namespace/>rating7" >
                                   <option value="" disabled selected>Choose your option</option>
                                   <option value="1" >Very Poor</option>
                                   <option value="2" >Poor</option>
                                   <option value="3" >Good</option>
                                   <option value="4" >Very Good</option>
                                   <option value="5" >NA</option>
                                </select>
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[6].ratingDescription}
                            </c:otherwise>
                        </c:choose>
                     </td>
                     <td class="p-0">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <input type="text" class="form-control form-control-sm" maxlength="300" name="<portlet:namespace/>comment7" onblur="$(this).val($(this).val().trim())" />
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[6].comment}
                            </c:otherwise>
                        </c:choose>
                     </td>
                  </tr>
                  <tr>
                     <td  class="py-0">8. Use feedback as a tool for improvement</td>
                     <td class="p-0 text-center">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <select class="mdb-select md-form form-control form-control-sm" required="required" name="<portlet:namespace/>rating8" >
                                   <option value="" disabled selected>Choose your option</option>
                                   <option value="1" >Very Poor</option>
                                   <option value="2" >Poor</option>
                                   <option value="3" >Good</option>
                                   <option value="4" >Very Good</option>
                                   <option value="5" >NA</option>
                                </select>
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[7].ratingDescription}
                            </c:otherwise>
                        </c:choose>
                     </td>
                     <td class="p-0">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <input type="text" class="form-control form-control-sm" maxlength="300" name="<portlet:namespace/>comment8" onblur="$(this).val($(this).val().trim())" />
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[7].comment}
                            </c:otherwise>
                        </c:choose>
                     </td>
                  </tr>
                  <tr>
                     <td class="bg-info text-white py-0" colspan="3"><strong>Initiative</strong></td>
                  </tr>
                  <tr>
                     <td class="py-0">9. Being proactive</td>
                     <td class="p-0 text-center">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <select class="mdb-select md-form form-control form-control-sm" required="required" name="<portlet:namespace/>rating9" >
                                   <option value="" disabled selected>Choose your option</option>
                                   <option value="1" >Very Poor</option>
                                   <option value="2" >Poor</option>
                                   <option value="3" >Good</option>
                                   <option value="4" >Very Good</option>
                                   <option value="5" >NA</option>
                                </select>
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[8].ratingDescription}
                            </c:otherwise>
                        </c:choose>
                     </td>
                     <td class="p-0">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <input type="text" class="form-control form-control-sm" maxlength="300" name="<portlet:namespace/>comment9" onblur="$(this).val($(this).val().trim())" />
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[8].comment}
                            </c:otherwise>
                        </c:choose>
                     </td>
                  </tr>
                  <tr>
                     <td class="py-0">10. Take personal responsibility for the outcome of events</td>
                     <td class="p-0 text-center">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <select class="mdb-select md-form form-control form-control-sm" required="required" name="<portlet:namespace/>rating10" >
                                   <option value="" disabled selected>Choose your option</option>
                                   <option value="1" >Very Poor</option>
                                   <option value="2" >Poor</option>
                                   <option value="3" >Good</option>
                                   <option value="4" >Very Good</option>
                                   <option value="5" >NA</option>
                                </select>
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[9].ratingDescription}
                            </c:otherwise>
                        </c:choose>
                     </td>
                     <td class="p-0">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <input type="text" class="form-control form-control-sm" maxlength="300" name="<portlet:namespace/>comment10" onblur="$(this).val($(this).val().trim())" />
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[9].comment}
                            </c:otherwise>
                        </c:choose>
                     </td>
                  </tr>
                  <tr>
                     <td class="py-0">11. Work independently to complete tasks</td>
                     <td class="p-0 text-center">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <select class="mdb-select md-form form-control form-control-sm" required="required" name="<portlet:namespace/>rating11" >
                                   <option value="" disabled selected>Choose your option</option>
                                   <option value="1" >Very Poor</option>
                                   <option value="2" >Poor</option>
                                   <option value="3" >Good</option>
                                   <option value="4" >Very Good</option>
                                   <option value="5" >NA</option>
                                </select>
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[10].ratingDescription}
                            </c:otherwise>
                        </c:choose>
                     </td>
                     <td class="p-0">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <input type="text" class="form-control form-control-sm" maxlength="300" name="<portlet:namespace/>comment11" onblur="$(this).val($(this).val().trim())" />
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[10].comment}
                            </c:otherwise>
                        </c:choose>
                     </td>
                  </tr>
                  <tr>
                     <td class="py-0">12. Take action beyond explicit requests</td>
                     <td class="p-0 text-center">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <select class="mdb-select md-form form-control form-control-sm" required="required" name="<portlet:namespace/>rating12" >
                                   <option value="" disabled selected>Choose your option</option>
                                   <option value="1" >Very Poor</option>
                                   <option value="2" >Poor</option>
                                   <option value="3" >Good</option>
                                   <option value="4" >Very Good</option>
                                   <option value="5" >NA</option>
                                </select>
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[11].ratingDescription}
                            </c:otherwise>
                        </c:choose>
                     </td>
                     <td class="p-0">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <input type="text" class="form-control form-control-sm" maxlength="300" name="<portlet:namespace/>comment12" onblur="$(this).val($(this).val().trim())" />
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[11].comment}
                            </c:otherwise>
                        </c:choose>
                     </td>
                  </tr>
                  <tr>
                     <td class="bg-info text-white py-0" colspan="3"><strong>Problem Solving Ability</strong></td>
                  </tr>
                  <tr>
                     <td class="py-0">13. Define problems & identify root cause</td>
                     <td class="p-0 text-center">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <select class="mdb-select md-form form-control form-control-sm" required="required" name="<portlet:namespace/>rating13" >
                                   <option value="" disabled selected>Choose your option</option>
                                   <option value="1" >Very Poor</option>
                                   <option value="2" >Poor</option>
                                   <option value="3" >Good</option>
                                   <option value="4" >Very Good</option>
                                   <option value="5" >NA</option>
                                </select>
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[12].ratingDescription}
                            </c:otherwise>
                        </c:choose>
                     </td>
                     <td class="p-0">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <input type="text" class="form-control form-control-sm" maxlength="300" name="<portlet:namespace/>comment13" onblur="$(this).val($(this).val().trim())" />
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[12].comment}
                            </c:otherwise>
                        </c:choose>
                     </td>
                  </tr>
                  <tr>
                     <td class="py-0">14. Anticipate & prevent problems</td>
                     <td class="p-0 text-center">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <select class="mdb-select md-form form-control form-control-sm" required="required" name="<portlet:namespace/>rating14" >
                                   <option value="" disabled selected>Choose your option</option>
                                   <option value="1" >Very Poor</option>
                                   <option value="2" >Poor</option>
                                   <option value="3" >Good</option>
                                   <option value="4" >Very Good</option>
                                   <option value="5" >NA</option>
                                </select>
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[13].ratingDescription}
                            </c:otherwise>
                        </c:choose>
                     </td>
                     <td class="p-0">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <input type="text" class="form-control form-control-sm" maxlength="300" name="<portlet:namespace/>comment14" onblur="$(this).val($(this).val().trim())" />
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[13].comment}
                            </c:otherwise>
                        </c:choose>
                     </td>
                  </tr>
                  <tr>
                     <td class="py-0">15. Generate alternative solutions & ensure implementation</td>
                     <td class="p-0 text-center">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <select class="mdb-select md-form form-control form-control-sm" required="required" name="<portlet:namespace/>rating15" >
                                   <option value="" disabled selected>Choose your option</option>
                                   <option value="1" >Very Poor</option>
                                   <option value="2" >Poor</option>
                                   <option value="3" >Good</option>
                                   <option value="4" >Very Good</option>
                                   <option value="5" >NA</option>
                                </select>
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[14].ratingDescription}
                            </c:otherwise>
                        </c:choose>
                     </td>
                     <td class="p-0">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <input type="text" class="form-control form-control-sm" maxlength="300" name="<portlet:namespace/>comment15" onblur="$(this).val($(this).val().trim())" />
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[14].comment}
                            </c:otherwise>
                        </c:choose>
                     </td>
                  </tr>
                  <tr>
                     <td class="bg-info text-white py-0" colspan="3"><strong>Discipline</strong></td>
                  </tr>
                  <tr>
                     <td class="py-0">16. Punctuality</td>
                     <td class="p-0 text-center">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <select class="mdb-select md-form form-control form-control-sm" required="required" name="<portlet:namespace/>rating16" >
                                   <option value="" disabled selected>Choose your option</option>
                                   <option value="1" >Very Poor</option>
                                   <option value="2" >Poor</option>
                                   <option value="3" >Good</option>
                                   <option value="4" >Very Good</option>
                                   <option value="5" >NA</option>
                                </select>
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[15].ratingDescription}
                            </c:otherwise>
                        </c:choose>
                     </td>
                     <td class="p-0">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <input type="text" class="form-control form-control-sm" maxlength="300" name="<portlet:namespace/>comment16" onblur="$(this).val($(this).val().trim())" />
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[15].comment}
                            </c:otherwise>
                        </c:choose>
                     </td>
                  </tr>
                  <tr>
                     <td class="py-0">17. Focused on task</td>
                     <td class="p-0 text-center">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <select class="mdb-select md-form form-control form-control-sm" required="required" name="<portlet:namespace/>rating17" >
                                   <option value="" disabled selected>Choose your option</option>
                                   <option value="1" >Very Poor</option>
                                   <option value="2" >Poor</option>
                                   <option value="3" >Good</option>
                                   <option value="4" >Very Good</option>
                                   <option value="5" >NA</option>
                                </select>
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[16].ratingDescription}
                            </c:otherwise>
                        </c:choose>
                     </td>
                     <td class="p-0">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <input type="text" class="form-control form-control-sm" maxlength="300" name="<portlet:namespace/>comment17" onblur="$(this).val($(this).val().trim())" />
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[16].comment}
                            </c:otherwise>
                        </c:choose>
                     </td>
                  </tr>
                  <tr>
                     <td class="py-0">18. Work within organizational framework / policies</td>
                     <td class="p-0 text-center">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <select class="mdb-select md-form form-control form-control-sm" required="required" name="<portlet:namespace/>rating18" >
                                   <option value="" disabled selected>Choose your option</option>
                                   <option value="1" >Very Poor</option>
                                   <option value="2" >Poor</option>
                                   <option value="3" >Good</option>
                                   <option value="4" >Very Good</option>
                                   <option value="5" >NA</option>
                                </select>
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[17].ratingDescription}
                            </c:otherwise>
                        </c:choose>
                     </td>
                     <td class="p-0">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <input type="text" class="form-control form-control-sm" maxlength="300" name="<portlet:namespace/>comment18" onblur="$(this).val($(this).val().trim())" />
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[17].comment}
                            </c:otherwise>
                        </c:choose>
                     </td>
                  </tr>
                  <tr>
                     <td class="bg-info text-white py-0" colspan="3"><strong>Emotional Balance</strong></td>
                  </tr>
                  <tr>
                     <td  class="py-0">19. Demonstrate maturity in dealing with issues</td>
                     <td class="p-0 text-center">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <select class="mdb-select md-form form-control form-control-sm" required="required" name="<portlet:namespace/>rating19" >
                                   <option value="" disabled selected>Choose your option</option>
                                   <option value="1" >Very Poor</option>
                                   <option value="2" >Poor</option>
                                   <option value="3" >Good</option>
                                   <option value="4" >Very Good</option>
                                   <option value="5" >NA</option>
                                </select>
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[18].ratingDescription}
                            </c:otherwise>
                        </c:choose>
                     </td>
                     <td class="p-0">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <input type="text" class="form-control form-control-sm" maxlength="300" name="<portlet:namespace/>comment19" onblur="$(this).val($(this).val().trim())" />
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[18].comment}
                            </c:otherwise>
                        </c:choose>
                     </td>
                  </tr>
                  <tr>
                     <td  class="py-0">20. Accept feedback & handle failure in a positive way</td>
                     <td class="p-0 text-center">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <select class="mdb-select md-form form-control form-control-sm" required="required" name="<portlet:namespace/>rating20" >
                                   <option value="" disabled selected>Choose your option</option>
                                   <option value="1" >Very Poor</option>
                                   <option value="2" >Poor</option>
                                   <option value="3" >Good</option>
                                   <option value="4" >Very Good</option>
                                   <option value="5" >NA</option>
                                </select>
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[19].ratingDescription}
                            </c:otherwise>
                        </c:choose>
                     </td>
                     <td class="p-0">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <input type="text" class="form-control form-control-sm" maxlength="300" name="<portlet:namespace/>comment20" onblur="$(this).val($(this).val().trim())" />
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[19].comment}
                            </c:otherwise>
                        </c:choose>
                     </td>
                  </tr>
                  <tr>
                     <td  class="py-0">21. Ability to control & filter emotions in a constructive manner</td>
                     <td class="p-0 text-center">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <select class="mdb-select md-form form-control form-control-sm" required="required" name="<portlet:namespace/>rating21" >
                                   <option value="" disabled selected>Choose your option</option>
                                   <option value="1" >Very Poor</option>
                                   <option value="2" >Poor</option>
                                   <option value="3" >Good</option>
                                   <option value="4" >Very Good</option>
                                   <option value="5" >NA</option>
                                </select>
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[20].ratingDescription}
                            </c:otherwise>
                        </c:choose>
                     </td>
                     <td class="p-0">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <input type="text" class="form-control form-control-sm" maxlength="300" name="<portlet:namespace/>comment21" onblur="$(this).val($(this).val().trim())" />
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[20].comment}
                            </c:otherwise>
                        </c:choose>
                     </td>
                  </tr>
                  <tr>
                     <td  class="py-0">22. Ability to handle pressure/ stress</td>
                     <td class="p-0 text-center">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <select class="mdb-select md-form form-control form-control-sm" required="required" name="<portlet:namespace/>rating22" >
                                   <option value="" disabled selected>Choose your option</option>
                                   <option value="1" >Very Poor</option>
                                   <option value="2" >Poor</option>
                                   <option value="3" >Good</option>
                                   <option value="4" >Very Good</option>
                                   <option value="5" >NA</option>
                                </select>
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[21].ratingDescription}
                            </c:otherwise>
                        </c:choose>
                     </td>
                     <td class="p-0">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <input type="text" class="form-control form-control-sm" maxlength="300" name="<portlet:namespace/>comment22" onblur="$(this).val($(this).val().trim())" />
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[21].comment}
                            </c:otherwise>
                        </c:choose>
                     </td>
                  </tr>
                  <tr>
                     <td class="bg-info text-white py-0" colspan="3"><strong>Decision Making</strong></td>
                  </tr>
                  <tr>
                     <td class="py-0">23. Decisions made after careful thought and evaluating all pros & cons</td>
                     <td class="p-0 text-center">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <select class="mdb-select md-form form-control form-control-sm" required="required" name="<portlet:namespace/>rating23" >
                                   <option value="" disabled selected>Choose your option</option>
                                   <option value="1" >Very Poor</option>
                                   <option value="2" >Poor</option>
                                   <option value="3" >Good</option>
                                   <option value="4" >Very Good</option>
                                   <option value="5" >NA</option>
                                </select>
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[22].ratingDescription}
                            </c:otherwise>
                        </c:choose>
                     </td>
                     <td class="p-0">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <input type="text" class="form-control form-control-sm" maxlength="300" name="<portlet:namespace/>comment23" onblur="$(this).val($(this).val().trim())" />
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[22].comment}
                            </c:otherwise>
                        </c:choose>
                     </td>
                  </tr>
                  <tr>
                     <td class="py-0">24. Implement the decision</td>
                     <td class="p-0 text-center">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <select class="mdb-select md-form form-control form-control-sm" required="required" name="<portlet:namespace/>rating24" >
                                   <option value="" disabled selected>Choose your option</option>
                                   <option value="1" >Very Poor</option>
                                   <option value="2" >Poor</option>
                                   <option value="3" >Good</option>
                                   <option value="4" >Very Good</option>
                                   <option value="5" >NA</option>
                                </select>
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[23].ratingDescription}
                            </c:otherwise>
                        </c:choose>
                     </td>
                     <td class="p-0">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <input type="text" class="form-control form-control-sm" maxlength="300" name="<portlet:namespace/>comment24" onblur="$(this).val($(this).val().trim())" />
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[23].comment}
                            </c:otherwise>
                        </c:choose>
                     </td>
                  </tr>
                  <tr>
                     <td class="py-0" >25. Conviction & Responsibility on decision taken</td>
                     <td class="p-0 text-center">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <select class="mdb-select md-form form-control form-control-sm" required="required" name="<portlet:namespace/>rating25" >
                                   <option value="" disabled selected>Choose your option</option>
                                   <option value="1" >Very Poor</option>
                                   <option value="2" >Poor</option>
                                   <option value="3" >Good</option>
                                   <option value="4" >Very Good</option>
                                   <option value="5" >NA</option>
                                </select>
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[24].ratingDescription}
                            </c:otherwise>
                        </c:choose>
                     </td>
                     <td class="p-0">
                        <c:choose>
                            <c:when test="${probation.probationState == 0}">
                                <input type="text" class="form-control form-control-sm" maxlength="300" name="<portlet:namespace/>comment25" onblur="$(this).val($(this).val().trim())" />
                            </c:when>
                            <c:otherwise>
                                ${probation.lines[24].comment}
                            </c:otherwise>
                        </c:choose>
                     </td>
                  </tr>
               </tbody>
            </table>
            <div class="row">
               <div class="col-4">
                  <div class="form-group">
                     <label for="areaOfStrength">Areas of Strength</label>
                     <textarea rows="2" style="height:80px;" cols="50" class="form-control" maxlength="1000" onblur="$(this).val($(this).val().trim())" required name="<portlet:namespace/>areaOfStrength" <c:if test="${probation.probationState != 0}">readonly</c:if> >${probation.areaOfStrength}</textarea>
                  </div>
               </div>
               <div class="col-4">
                  <div class="form-group">
                     <label for="areaOfImprovement">Areas of Improvement</label>
                     <textarea rows="2" style="height:80px;" cols="50" class="form-control" maxlength="1000" onblur="$(this).val($(this).val().trim())" required name="<portlet:namespace/>areaOfImprovement" <c:if test="${probation.probationState != 0}">readonly</c:if> >${probation.areaOfImprovement}</textarea>
                  </div>
               </div>
               <div class="col-4">
                  <div class="form-group">
                     <label for="managerComment">To Be filled by Reporting Manager</label>
                     <textarea rows="2" style="height:80px;" cols="50" class="form-control" maxlength="1000" onblur="$(this).val($(this).val().trim())" required name="<portlet:namespace/>managerComment" <c:if test="${probation.probationState != 0}">readonly</c:if> <c:if test="${probation.probationState == 0}">placeholder="Provide supporting comments/reasons"</c:if> >${probation.managerComment}</textarea>
                  </div>
               </div>
            </div>
            <c:if test="${probation.probationState == 0}">
                <div class="row mt-2">
                   <div class="col">
                      <input type="checkbox" id="deceleration" required="required"/>
                      <span class="form-control-sm">
                      I confirm that I have reviewed performance of employee during probation review against KPIs set for him/her and same has been discussed and communicated to employee verbally as well as on email
                      </span>
                   </div>
                </div>
                <div class="row mt-4">
                   <div class="col-4 text-center">
                      <button type="submit" value="1" class="btn btn-success fn_submit">Confirmed</button>
                   </div>
                   <div class="col-4 text-center">
                      <div class="input-group mb-3">
                         <div class="input-group-prepend" style="width:100px;">
                            <div class="input-group-text">
                               <input class="form-control" id="numOfDays" type="number" min="11" max="999"  step="1" name="<portlet:namespace/>numOfDays"  />
                            </div>
                         </div>
                         <input type="text" readonly class="form-control"
                            placeholder="Days" aria-describedby="basic-addon2">
                         <div class="input-group-append">
                            <button type="submit" value="3" class="btn btn-primary fn_submit">Extend</button>
                            <button type="submit" value="4" class="btn btn-warning fn_submit" >Revise</button>
                         </div>
                      </div>
                   </div>
                   <div class="col-4 text-center">
                      <button type="submit" value="2" class="btn btn-danger fn_submit">Not Confirmed</button>
                   </div>
                </div>
            </c:if>
         </form>
      </div>
   </div>
</div>