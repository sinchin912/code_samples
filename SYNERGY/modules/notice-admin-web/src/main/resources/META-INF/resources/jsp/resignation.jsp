<%@ include file="/init.jsp"%>
<portlet:actionURL name="submitHrResign" var="submitHrResignUrl"></portlet:actionURL>
<portlet:actionURL name="submitManagerResign" var="submitManagerResignUrl"></portlet:actionURL>
<portlet:actionURL name="submitItAssetsResign" var="submitItAssetsResignUrl"></portlet:actionURL>
<portlet:actionURL name="submitFinalLwd" var="submitFinalLwdUrl"></portlet:actionURL>
<portlet:actionURL name="submitHrWithdrawalActionForm" var="submitHrWithdrawalActionFormUrl"></portlet:actionURL>
<portlet:resourceURL var="getValidateRoleUrl" id="getValidateRole"></portlet:resourceURL>
<portlet:actionURL var="assigneeSubmissionUrl"	name="assigneeSubmission"></portlet:actionURL>
<portlet:renderURL var="resignationRenderURL" windowState="normal"></portlet:renderURL>
<div class="container">
    <input type="hidden" id="getValidateRoleResourceUrl" value="${getValidateRoleUrl}"/>
    <input type="hidden" id="managerEmail" value="${employeeDetails.managerEmail}"/>
    <input type="hidden" id="assigneeEmail" value="${employeeDetails.assigneeEmail}"/>
    <input type="hidden" id="lastWorkingDate" value="${employeeDetails.lastWorkingDate}"/>
    <input type="hidden" id="tentativeLWD" value="${employeeDetails.tentativeLWD}"/>
    <input type="hidden" id="portletNamespace"   value="<portlet:namespace/>" />
    <input type="hidden" id="holidayDates" value='${holidayDates}'/>
   <c:set var="pageUrl" value="${fn:split(resignationRenderURL.toString(),'?')}" />
   <div class="card">
      <div class="card-header">
         <div class="row">
            <div class="col-3 pt-2">
               <c:choose>
                  <c:when test="${employeeDetails.roleType == 'Trantor_Manager' && employeeDetails.statusInt ==5}">
                     <h5>General Information</h5>
                  </c:when>
                  <c:otherwise>
                     <h5>Resignation Form</h5>
                  </c:otherwise>
               </c:choose>
            </div>
               <div class="col-7 form-inline justify-content-md-center"  id="assigneeFormDiv">
            <c:if test="${(employeeDetails.roleType == 'Trantor_HR' ||(employeeDetails.roleType == 'Trantor_Manager' && employeeDetails.primaryManager =='true' )) && (employeeDetails.statusInt !=6)}">

                  <form method="post" action="${assigneeSubmissionUrl}" id="assigneeSubmissionForm" onsubmit="return submitAssignee()">
                     <input type="hidden" id="assigneeResignationId" value="${employeeDetails.resignationId}" name="<portlet:namespace/>assigneeResignationId" />
                     <label style="display:inline">Assign to</label>
                     <input type="text" placeholder="Enter email id" onfocus="$('#assigneeButton').attr('disabled','disabled');" onblur="checkAssigneeRole()" class="form-control form-control-sm" maxlength="75" name="<portlet:namespace/>assigneeEmail" id="assigneeEmailReg" style="width:225px;" />
                     <span class="email-has-error"
                        style="color: red; font-size: .8em"></span>
                     <button class="btn btn-outline-secondary" type="submit" id="assigneeButton" disabled >Submit</button>
                  </form>
            </c:if>
               </div>
            <div class="col-2 text-right">
               <a class="btn btn-outline-secondary" id="reset" href="${pageUrl[0]}" role="button">
               Cancel</a>
            </div>
         </div>
      </div>
      <div class="col-12 pt-2">
         <c:if test="${(employeeDetails.roleType == 'Trantor_HR') && (employeeDetails.statusInt !=6)}">
            <form id="retainEmployeeForm" method="post" class="text-center" onSubmit="return retainEmployeeForm();" action="${submitHrWithdrawalActionFormUrl}">
               <input type="hidden" id="withdrawResignationId" value="${employeeDetails.resignationId}" name="<portlet:namespace/>withdrawResignationId" />
               <input type="hidden" id="withdrawActionPerformed" value="1" name="<portlet:namespace/>withdrawActionPerform" />
               <button type="submit" class="btn btn-success">Retain Employee</button>
            </form>
         </c:if>
      </div>
      <div class="card-body px-5">
         <div class="row">
            <div class="col-md-2 mt-1 text-right">
               <label>Ecode</label>
            </div>
            <div class="col-md-4">
               <input type="text" class="form-control-sm form-control-plaintext"
                  readonly id="employeeEcode" value="${employeeDetails.employeeCode}" />
            </div>
            <div class="col-md-2 mt-1 text-right">
               <label>Name</label>
            </div>
            <div class="col-md-4">
               <input type="text" class="form-control-sm form-control-plaintext pl-1"
                  readonly id="employeeName" value="${employeeDetails.employeeName}" />
            </div>
         </div>
         <div class="row">
            <div class="col-md-2 mt-1 text-right">
               <label>Designation</label>
            </div>
            <div class="col-md-4">
               <input type="text" class="form-control-sm form-control-plaintext"
                  readonly id="designation" value="${employeeDetails.employeeDesignation}" />
            </div>
            <div class="col-md-2 mt-1 text-right">
               <label>Manager Name</label>
            </div>
            <div class="col-md-4">
               <input type="text" class="form-control-sm form-control-plaintext pl-1"
                  readonly id="employeeManagerName" value="${employeeDetails.managerName}" />
            </div>
         </div>
         <div class="row">
            <div class="col-md-2 mt-1 text-right">
               <label>Account Name</label>
            </div>
            <div class="col-md-4">
               <input type="text" class="form-control-sm form-control-plaintext"
                  readonly id="employeeProjectName" value="${employeeDetails.account}" />
            </div>
            <div class="col-md-2 mt-1 text-right">
               <label>Assignee Name</label>
            </div>
            <div class="col-md-4">
               <input type="text" class="form-control-sm form-control-plaintext pl-1"
                  readonly id="assigneeName" value="${employeeDetails.assigneeName}" />
            </div>
         </div>
         <div class="row">
            <div class="col-md-2 mt-1 text-right">
               <label>Mobile</label>
            </div>
            <div class="col-md-4">
               <input type="text" class="form-control-sm form-control-plaintext"
                  readonly id="mobile" value="${employeeDetails.empMobNo}" />
            </div>
            <c:if test="${(employeeDetails.statusInt!=4 && employeeDetails.statusInt!=5) || employeeDetails.roleType == 'Trantor_HR'}" >
               <div class="col-md-2 mt-1 text-right">
                  <label>Status</label>
               </div>
               <div class="col-md-4">
                  <input type="text" class="form-control-sm form-control-plaintext font-weight-bold pl-1"
                     readonly id="status" title="${employeeDetails.empStatus}" value="${employeeDetails.empStatus}" />
               </div>
            </c:if>
         </div>
         <c:if test="${employeeDetails.roleType == 'Trantor_HR' || employeeDetails.roleType == 'Trantor_Manager'}">
            <div class="row">
               <div class="col-6">
                  <div class="row">
                     <div class="col-md-4 mt-1 text-right">
                        <label>DOJ</label>
                     </div>
                     <div class="col-md-8">
                        <input type="text" class="form-control-sm form-control-plaintext" readonly="" id="employeeDoj" value="${employeeDetails.doj}">
                     </div>
                  </div>
                  <c:choose>
                     <c:when test="${(employeeDetails.statusInt==4 || employeeDetails.statusInt==5)}">
                        <div class="row">
                           <div class="col-md-4 mt-1 text-right">
                              <label>Band</label>
                           </div>
                           <div class="col-md-8">
                              <input type="text" class="form-control-sm form-control-plaintext" readonly="" id="dor" value="${employeeDetails.employeeBand}">
                           </div>
                        </div>
                     </c:when>
                     <c:otherwise>
                        <div class="row">
                                 <div class="col-md-4 mt-1 text-right">
                                    <label>Date of Resignation</label>
                                 </div>
                                 <div class="col-md-8">
                                    <input type="text" class="form-control-sm form-control-plaintext" readonly="" id="dor" value="${employeeDetails.resignationDate}">
                                 </div>
                        </div>
                     </c:otherwise>
                  </c:choose>
               </div>
               <div class="col-6">
                  <c:choose>
                     <c:when test="${(employeeDetails.statusInt==4 || employeeDetails.statusInt==5)}">
                        <div class="row">
                           <div class="col-md-4 mt-1 text-right">
                              <label>HR Comments</label>
                           </div>
                           <div class="col-md-8">
                              <textarea class="form-control-sm form-control-plaintext border border-secondary pl-1" style="height: 69px;" maxlength="500"
                                 cols="47" readonly="" id="hrComments">${employeeDetails.hrComment}</textarea>
                           </div>
                        </div>
                     </c:when>
                     <c:otherwise>
                        <div class="row">
                           <div class="col-md-4 mt-1 text-right">
                              <label>Employee Comments</label>
                           </div>
                           <div class="col-md-8">
                              <textarea class="form-control-sm form-control-plaintext border pl-1" style="height: 69px;" maxlength="500"
                                 cols="47" readonly="" id="employeeComment">${employeeDetails.employeeComment != '' ? employeeDetails.employeeComment : 'NA'}</textarea>
                           </div>
                        </div>
                     </c:otherwise>
                  </c:choose>
               </div>
            </div>
          <c:if test="${employeeDetails.statusInt==1 ||  employeeDetails.statusInt==2||  employeeDetails.statusInt==3}">
            <div class="row">
            <div class="col-md-2 mt-1 text-right">
               <label>Last Working day <br>(Based on notice period)</label>
            </div>
            <div class="col-md-4">
             <input type="text" class="form-control-sm form-control-plaintext" readonly value="${employeeDetails.tentativeLWD}">
            </div>
          </div>
          </c:if>
            <c:if test="${employeeDetails.statusInt !=4}">
               <div class="row">
                  <div class="col-md-2 mt-1 text-right">
                     <label>Reason for Leaving</label>
                  </div>
                  <div class="col-md-4">
                     <input type="text" class="form-control-sm form-control-plaintext"
                        readonly id="reason" value="${employeeDetails.reason}" />
                  </div>
               </div>
               <c:if test="${employeeDetails.roleType == 'Trantor_HR' && employeeDetails.abscondRetained=='false'}">
                  <div class="row">
                     <div class="col-md-2 mt-1 text-right">
                        <label>City Name</label>
                     </div>
                     <div class="col-md-4">
                        <input type="text" class="form-control-sm form-control-plaintext"
                           readonly id="city" value="${employeeDetails.cityName}"/>
                     </div>
                     <div class="col-md-2 mt-1 text-right">
                        <label>Personal Email Id</label>
                     </div>
                     <div class="col-md-4">
                        <input type="text" class="form-control-sm form-control-plaintext pl-1"
                           readonly id="personalEmail" value="${employeeDetails.personalEmail}" />
                     </div>
                  </div>
                  <div class="row">
                     <div class="col-6">
                        <div class="row">
                           <div class="col-md-4 mt-1 text-right">
                              <label>State Name</label>
                           </div>
                           <div class="col-md-8">
                              <input type="text" class="form-control-sm form-control-plaintext" readonly="" id="state" value="${employeeDetails.stateName}">
                           </div>
                        </div>
                        <div class="row">
                           <div class="col-md-4 mt-1 text-right">
                              <label>Pincode</label>
                           </div>
                           <div class="col-md-8">
                              <input type="text" class="form-control-sm form-control-plaintext" readonly="" id="pincode" value="${employeeDetails.pincode}">
                           </div>
                        </div>
                     </div>
                     <div class="col-6">
                        <div class="row">
                           <div class="col-md-4 mt-1 text-right">
                              <label>Full Postal Address</label>
                           </div>
                           <div class="col-md-8">
                              <textarea class="form-control-sm form-control-plaintext pl-1" style="height: 69px;" maxlength="500"
                                 cols="47" readonly="" id="postalAddress">${employeeDetails.postalAddress}</textarea>
                           </div>
                        </div>
                     </div>
                  </div>
               </c:if>
            </c:if>
         </c:if>
      </div>
   </div>
   <c:if test="${(((employeeDetails.roleType == 'Trantor_Manager'  && employeeDetails.statusInt!=0) || employeeDetails.roleType == 'Trantor_HR') && (employeeDetails.statusInt==0 ||  employeeDetails.statusInt==1 ||  employeeDetails.statusInt==2 || employeeDetails.statusInt==4|| employeeDetails.statusInt==5)) }">
      <div class="card">
         <div class="card-header py-0">
            <div class="row">
               <div class="col pt-2">
                  <h5>HR Form</h5>
               </div>
            </div>
         </div>
         <div class="card-body">
            <form id="hrApproveForm" method="post" onSubmit="return confirmHrApproveForm();" action="${submitHrResignUrl}">
               <input type="hidden" id="hrResignationId" value="${employeeDetails.resignationId}" name="<portlet:namespace/>hrResignationId" />
               <c:if test="${employeeDetails.statusInt==0 ||employeeDetails.statusInt==1 ||employeeDetails.statusInt==2 }">
                  <div class="form-row mb-2 form-control-sm">
                     <div class="form-group col-md-2 mt-2 text-right">
                        <label>
                           Separation Type
                           <c:if test="${employeeDetails.statusInt==0}">*</c:if>
                        </label>
                     </div>
                     <div class="form-group col-md-4">
                        <select class="mdb-select md-form form-control form-control-sm" id="separationType" required="required" name="<portlet:namespace/>separationType">
                           <c:if test="${employeeDetails.statusInt==0}">
                              <option value="" selected>Choose your option</option>
                           </c:if>
                           <option
                           <c:choose>
                              <c:when test="${employeeDetails.separationType=='Involuntary'}"> selected="selected" </c:when>
                              <c:otherwise>
                                 <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled</c:if>
                              </c:otherwise>
                           </c:choose>
                           value="Involuntary">Involuntary</option>
                           <option
                           <c:choose>
                              <c:when test="${employeeDetails.separationType=='Absconded'}"> selected="selected" </c:when>
                              <c:otherwise>
                                 <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled</c:if>
                              </c:otherwise>
                           </c:choose>
                           value="Absconded">Absconded</option>
                           <option
                           <c:choose>
                              <c:when test="${employeeDetails.separationType=='Mutual'}"> selected="selected" </c:when>
                              <c:otherwise>
                                 <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled</c:if>
                              </c:otherwise>
                           </c:choose>
                           value="Mutual">Mutual</option>
                           <option
                           <c:choose>
                              <c:when test="${employeeDetails.separationType=='Contract Completed'}"> selected="selected" </c:when>
                              <c:otherwise>
                                 <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled</c:if>
                              </c:otherwise>
                           </c:choose>
                           value="Contract Completed">Contract Completed</option>
                           <option
                           <c:choose>
                              <c:when test="${employeeDetails.separationType=='Voluntary'}"> selected="selected" </c:when>
                              <c:otherwise>
                                 <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled</c:if>
                              </c:otherwise>
                           </c:choose>
                           value="Voluntary">Voluntary</option>
                        </select>
                     </div>
                     <div class="form-group col-md-2 mt-2 text-right">
                        <label>HR Comments</label>
                     </div>
                     <div class="form-group col-md-4">
                        <textarea class="form-control-sm form-control-plaintext border border-secondary pl-1" <c:if test="${employeeDetails.statusInt!=0}">readonly</c:if> style="height: 69px;" maxlength="500"
                                         cols="47"  id="hrComment" maxlength="500" cols="47" onblur="$(this).val($(this).val().trim())" name="<portlet:namespace/>hrComment"><c:if test="${employeeDetails.statusInt!=0}">${employeeDetails.hrComment}</c:if></textarea>
                     </div>
                  </div>
                  <div class="form-row mb-2 form-control-sm">
                     <div class="form-group col-md-2 mt-2 text-right">
                        <label>
                           Notice Period
                           <c:if test="${employeeDetails.statusInt==0}">*</c:if>
                        </label>
                     </div>
                     <div class="form-group col-md-4">
                        <select class="mdb-select md-form form-control form-control-sm" id="noticePeriod" onchange="setLastWorkingDay()" required="required" name="<portlet:namespace/>noticePeriod">
                           <c:if test="${employeeDetails.statusInt==0}">
                              <option value="" selected>Choose your option</option>
                           </c:if>
                           <option
                           <c:choose>
                              <c:when test="${employeeDetails.noticePeriod=='0'}"> selected="selected" </c:when>
                              <c:otherwise>
                                 <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled</c:if>
                              </c:otherwise>
                           </c:choose>
                           value="0">0</option>
                           <option
                           <c:choose>
                              <c:when test="${employeeDetails.noticePeriod=='15'}"> selected="selected" </c:when>
                              <c:otherwise>
                                 <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled</c:if>
                              </c:otherwise>
                           </c:choose>
                           value="15">15</option>
                           <option
                           <c:choose>
                              <c:when test="${employeeDetails.noticePeriod=='30'}"> selected="selected" </c:when>
                              <c:otherwise>
                                 <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled</c:if>
                              </c:otherwise>
                           </c:choose>
                           value="30">30</option>
                           <option
                           <c:choose>
                              <c:when test="${employeeDetails.noticePeriod=='45'}"> selected="selected" </c:when>
                              <c:otherwise>
                                 <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled</c:if>
                              </c:otherwise>
                           </c:choose>
                           value="45">45</option>
                           <option
                           <c:choose>
                              <c:when test="${employeeDetails.noticePeriod=='60'}"> selected="selected" </c:when>
                              <c:otherwise>
                                 <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled</c:if>
                              </c:otherwise>
                           </c:choose>
                           value="60">60</option>
                           <option
                           <c:choose>
                              <c:when test="${employeeDetails.noticePeriod=='75'}"> selected="selected" </c:when>
                              <c:otherwise>
                                 <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled</c:if>
                              </c:otherwise>
                           </c:choose>
                           value="75">75</option>
                           <option
                           <c:choose>
                              <c:when test="${employeeDetails.noticePeriod=='90'}"> selected="selected" </c:when>
                              <c:otherwise>
                                 <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled</c:if>
                              </c:otherwise>
                           </c:choose>
                           value="90">90</option>
                        </select>
                     </div>
                  </div>
         <div class="row">
            <div class="col-md-2 mt-1 text-right">
               <label>Final Lwd*</label>
            </div>
            <div class="col-md-4">
               <input type="text" class="pl-3 form-control-sm form-control-plaintext border border-secondary"
               <c:choose><c:when test="${employeeDetails.statusInt == 0 }">id="finalLwdByHr" required</c:when><c:otherwise>readonly</c:otherwise></c:choose>
                    value="${employeeDetails.lastWorkingDate}"  name="<portlet:namespace/>finalLwd"  />
            </div>
         </div>
                  <div class="form-row mb-2 form-control-sm">
                     <div class="form-group col-md-2 mt-2 text-right">
                        <label>
                           Key to Company
                           <c:if test="${employeeDetails.statusInt==0}">*</c:if>
                        </label>
                     </div>
                     <div class="form-group col-md-4">
                        <select class="mdb-select md-form form-control form-control-sm" id="keyToCompany" required="required" name="<portlet:namespace/>keyToCompany">
                           <c:if test="${employeeDetails.statusInt==0}">
                              <option value="" selected>Choose your option</option>
                           </c:if>
                           <option
                           <c:choose>
                              <c:when test="${employeeDetails.keyToCompany=='Yes'}"> selected="selected" </c:when>
                              <c:otherwise>
                                 <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled</c:if>
                              </c:otherwise>
                           </c:choose>
                           value="Yes">Yes</option>
                           <option
                           <c:choose>
                              <c:when test="${employeeDetails.keyToCompany=='No'}"> selected="selected" </c:when>
                              <c:otherwise>
                                 <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled</c:if>
                              </c:otherwise>
                           </c:choose>
                           value="No">No</option>
                        </select>
                     </div>
                     <c:if test="${employeeDetails.statusInt !=4 && employeeDetails.statusInt !=5}">
                        <div class="form-group col-md-2  text-right">
                           <label>
                              Reason for leaving
                           </label>
                        </div>
                        <div class="form-group col-md-4">
                           <select class="mdb-select md-form form-control form-control-sm"   name="<portlet:namespace/>reasonForLeavingByHr">
                              <c:if test="${employeeDetails.statusInt==0}">
                                 <option value="" selected>Choose your option</option>
                              </c:if>
                              <option
                              <c:choose>
                                 <c:when test="${employeeDetails.reasonForLeavingByHr=='Better Brand'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled</c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="Better Brand">Better Brand</option>
                              <option
                              <c:choose>
                                 <c:when test="${employeeDetails.reasonForLeavingByHr=='Better Salary'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled </c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="Better Salary">Better Salary</option>
                              <option
                              <c:choose>
                                 <c:when test="${employeeDetails.reasonForLeavingByHr=='Family/Domestic Constraints'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled</c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="Family/Domestic Constraints">Family/Domestic Constraints</option>
                              <option
                              <c:choose>
                                 <c:when test="${employeeDetails.reasonForLeavingByHr=='Getting Married'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled</c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="Getting Married">Getting Married</option>
                              <option
                              <c:choose>
                                 <c:when test="${employeeDetails.reasonForLeavingByHr=='Going Abroad'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled </c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="Going Abroad">Going Abroad</option>
                              <option
                              <c:choose>
                                 <c:when test="${employeeDetails.reasonForLeavingByHr=='Higher Education'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled </c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="Higher Education">Higher Education</option>
                              <option
                              <c:choose>
                                 <c:when test="${employeeDetails.reasonForLeavingByHr=='Job Security'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled</c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="Job Security">Job Security</option>
                              <option
                              <c:choose>
                                 <c:when test="${employeeDetails.reasonForLeavingByHr=='Medical Reasons'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled </c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="Medical Reasons">Medical Reasons</option>
                              <option
                              <c:choose>
                                 <c:when test="${employeeDetails.reasonForLeavingByHr=='Promotion/Career Growth'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled </c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="Promotion/Career Growth">Promotion/Career Growth</option>
                              <option
                              <c:choose>
                                 <c:when test="${employeeDetails.reasonForLeavingByHr=='Relation With Co Workers'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled </c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="Relation With Co Workers">Relation With Co Workers</option>
                              <option
                              <c:choose>
                                 <c:when test="${employeeDetails.reasonForLeavingByHr=='Relation with Supervisor'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled</c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="Relation with Supervisor">Relation with Supervisor</option>
                              <option
                              <c:choose>
                                 <c:when test="${employeeDetails.reasonForLeavingByHr=='Relocation'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled</c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="Relocation">Relocation</option>
                              <option
                              <c:choose>
                                 <c:when test="${employeeDetails.reasonForLeavingByHr=='Training opportunities'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled</c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="Training opportunities">Training opportunities</option>
                              <option
                              <c:choose>
                                 <c:when test="${employeeDetails.reasonForLeavingByHr=='Work Conditions'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled </c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="Work Conditions">Work Conditions</option>
                              <option
                              <c:choose>
                                 <c:when test="${employeeDetails.reasonForLeavingByHr=='Terminated - Ethical Issues'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled </c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="Terminated - Ethical Issues">Terminated - Ethical Issues</option>
                              <option
                              <c:choose>
                                 <c:when test="${employeeDetails.reasonForLeavingByHr=='Terminated - Mutual'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled </c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="Terminated - Mutual">Terminated - Mutual</option>
                              <option
                              <c:choose>
                                 <c:when test="${employeeDetails.reasonForLeavingByHr=='Terminated - No Project Allocation'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled </c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="Terminated - No Project Allocation">Terminated - No Project Allocation</option>
                              <option
                              <c:choose>
                                 <c:when test="${employeeDetails.reasonForLeavingByHr=='Terminated - Performance'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled </c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="Terminated - Performance">Terminated - Performance</option>
                              <option
                              <c:choose>
                                 <c:when test="${employeeDetails.reasonForLeavingByHr=='Terminated - Others'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled </c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="Terminated - Others">Terminated - Others</option>
                              <option
                              <c:choose>
                                 <c:when test="${employeeDetails.reasonForLeavingByHr=='Absconded'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled </c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="Absconded">Absconded</option>
                              <option
                              <c:choose>
                                 <c:when test="${employeeDetails.reasonForLeavingByHr=='Others'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${employeeDetails.statusInt!=0}"> style="display:none" disabled </c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="Others">Others</option>
                              <c:if test="${employeeDetails.reasonForLeavingByHr=='' && employeeDetails.statusInt!=0}">
                                  <option>None</option>
                              </c:if>
                           </select>
                        </div>
                     </c:if>
                  </div>
                  <div class="form-row mb-2 form-control-md">
                     <div class="form-group col-md-2 mt-2 text-right">
                        <label>
                           Generate Exit Questionnaire at Employee Side
                           <c:if test="${employeeDetails.statusInt==0}">*</c:if>
                        </label>
                     </div>
                     <div class="form-group col-md-4 mt-3 pl-3" required="required" >
                        <label>
                           <input type="radio" name="<portlet:namespace/>exitQuestionnaire" value="true" required
                           <c:if test="${employeeDetails.statusInt != 0 && employeeDetails.exitQuestionnaire =='true'}">checked </c:if>
                           <c:if test="${employeeDetails.statusInt != 0 }">onclick="return false"</c:if>
                           >&nbsp;Yes
                        </label>
                        <label class="pl-2">
                           <input type="radio" name="<portlet:namespace/>exitQuestionnaire" value="false"
                           <c:if test="${employeeDetails.statusInt != 0 && employeeDetails.exitQuestionnaire =='false'}">checked </c:if>
                           <c:if test="${employeeDetails.statusInt != 0 }">onclick="return false"</c:if>
                           >&nbsp;No
                        </label>
                     </div>
                  </div>
               </c:if>
               <c:if test="${employeeDetails.statusInt==4 || employeeDetails.statusInt==5 }">
                  <div class="form-row mb-4 form-control-sm"  id="abscondDiv">
                     <c:if test="${employeeDetails.statusInt==4 && employeeDetails.abscondedByHR=='false' }">
                        <div class="form-group col-md-2 mt-2 text-right">
                           <label>
                              Tentative LWD
                              <c:choose>
                                 <c:when test="${employeeDetails.showManagerMessage=='true'}"><br>(Added By Manager)</c:when>
                                 <c:otherwise><br>(Added By HR)</c:otherwise>
                              </c:choose>
                           </label>
                        </div>
                        <div class="form-group col-md-4">
                           <input type="text" class="form-control form-control-sm"  readonly  value="${employeeDetails.abscondTerminateDate}" readonly/>
                        </div>
                     </c:if>
                     <div class="form-group col-md-2 mt-2 text-right" id="abscondDateLabel">
                        <label>Final Lwd*</label>
                     </div>
                     <div class="form-group col-md-4">
                        <input type="text"   autocomplete="off" style="cursor: pointer" onkeydown="return false" required
                        <c:choose>
                           <c:when test="${(employeeDetails.roleType != 'Trantor_HR' || (employeeDetails.roleType == 'Trantor_HR' && employeeDetails.lastWorkingDate !=null))}">readonly </c:when>
                           <c:otherwise>id="abscondDate"</c:otherwise>
                        </c:choose>
                        value="${employeeDetails.lastWorkingDate}"  class="form-control form-control-sm"  name="<portlet:namespace/>hrFinalLwd" />
                     </div>
                  </div>
                  <c:if test="${employeeDetails.statusInt == 5 && employeeDetails.roleType == 'Trantor_HR'}">
                     <div class="form-row mb-4 form-control-sm">
                        <div class="form-group col-md-2 mt-2 text-right" id="abscondDateLabel">
                           <label>Personal Email Id </label>
                        </div>
                        <div class="form-group col-md-4">
                           <input type="text"  readonly
                              value="${employeeDetails.alternateEmail}"  class="form-control form-control-sm"  />
                        </div>
                     </div>
                  </c:if>
               </c:if>
               <c:if test="${(employeeDetails.showHideHrUpdateButton=='true')}">
                  <div class="row pt-2 pb-2" id="approveButtonDiv">
                     <div class="col-12 text-center">
                        <button class="btn btn-success mt-1" id="updateButtonId" >Update</button>
                     </div>
                  </div>
               </c:if>
            </form>
         </div>
      </div>
   </c:if>
   <c:if test="${(employeeDetails.roleType == 'Trantor_HR' && employeeDetails.managerSubmitted=='true') || (employeeDetails.roleType == 'Trantor_Manager' && (employeeDetails.statusInt==1 ||  employeeDetails.statusInt==2 || employeeDetails.statusInt==4 ||  employeeDetails.statusInt==5))}">
      <div class="card">
         <div class="card-header py-0">
            <div class="row">
               <div class="col pt-2">
                  <h5>Manager Form</h5>
               </div>
            </div>
         </div>
         <div class="card-body">
            <div class="col pt-2 pb-2 text-center">
               <h5>
                  Section 1 -
                  <c:choose>
                     <c:when test="${employeeDetails.statusInt==4 || employeeDetails.statusInt==5}" >Basic Details </c:when>
                     <c:otherwise> Resignation Details</c:otherwise>
                  </c:choose>
               </h5>
            </div>
            <form id="managerApproveForm" method="post" onSubmit="return confirmManagerApproveForm();" action="${submitManagerResignUrl}">
               <input type="hidden" id="mResignationId" value="${employeeDetails.resignationId}" name="<portlet:namespace/>mResignationId" />
               <c:if test="${employeeDetails.statusInt!=4 && employeeDetails.statusInt!=5}">
                  <div class="form-row form-control-sm mb-2">
                     <div class="form-group col-md-2 mt-2 text-right">
                        <label>
                           Want to retain employee?
                           <c:if test="${employeeDetails.managerSubmitted=='false'}">*</c:if>
                        </label>
                     </div>
                     <div class="form-group col-md-4">
                        <select class="mdb-select md-form form-control form-control-sm manager" id="keyToRetention" required="required" name="<portlet:namespace/>keyToRetention">
                           <c:if test="${employeeDetails.managerSubmitted=='false'}">
                              <option value="" selected>Choose your option</option>
                           </c:if>
                           <option
                           <c:choose>
                              <c:when test="${employeeDetails.keyToRetention=='Yes'}"> selected="selected" </c:when>
                              <c:otherwise>
                                 <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled</c:if>
                              </c:otherwise>
                           </c:choose>
                           value="Yes">Yes</option>
                           <option
                           <c:choose>
                              <c:when test="${employeeDetails.keyToRetention=='No'}"> selected="selected" </c:when>
                              <c:otherwise>
                                 <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled</c:if>
                              </c:otherwise>
                           </c:choose>
                           value="No">No</option>
                        </select>
                     </div>
                     <c:choose>
                        <c:when test="${employeeDetails.managerSubmitted=='false'}">
                           <div id= "retainEmpId" style="display:none" class="form-group col-md-6 mt-2 text-left">
                              <label class="ml-5">Kindly Contact HR for the same</label>
                           </div>
                           <div class="form-group col-md-2 mt-2 text-right" style="display:none" id= "NonRetainEmpLabelId">
                              <label>
                                 Reason for Non-Retention
                                 <c:if test="${employeeDetails.managerSubmitted=='false'}">*</c:if>
                              </label>
                           </div>
                           <div class="form-group col-md-4"
                           <c:if test="${employeeDetails.managerSubmitted=='false'}"> style="display:none"</c:if>
                           id= "NonRetainEmpId">
                           <select class="mdb-select md-form form-control form-control-sm manager" id="reasonNonRetention" name="<portlet:namespace/>reasonNonRetention">
                              <c:if test="${employeeDetails.managerSubmitted=='false'}">
                                 <option value="" selected>Choose your option</option>
                              </c:if>
                              <option
                              <c:choose>
                                 <c:when test="${employeeDetails.reasonNonRetention=='Employee Not Willing'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled</c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="Employee Not Willing">Employee Not Willing</option>
                              <option
                              <c:choose>
                                 <c:when test="${employeeDetails.reasonNonRetention=='Family Reasons'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled</c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="Family Reasons">Family Reasons</option>
                              <option
                              <c:choose>
                                 <c:when test="${employeeDetails.reasonNonRetention=='High Cost'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled</c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="High Cost">High Cost</option>
                              <option
                              <c:choose>
                                 <c:when test="${employeeDetails.reasonNonRetention=='Onsite opportunity'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled</c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="Onsite opportunity">Onsite opportunity</option>
                              <option
                              <c:choose>
                                 <c:when test="${employeeDetails.reasonNonRetention=='Others'}"> selected="selected" </c:when>
                                 <c:otherwise>
                                    <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled</c:if>
                                 </c:otherwise>
                              </c:choose>
                              value="Others">Others</option>
                           </select>
                  </div>
                  </c:when>
                  <c:otherwise>
                  <c:if test="${employeeDetails.keyToRetention=='Yes'}">
                  <div id= "retainEmpId" class="form-group col-md-6 mt-2 text-left">
                  <label class="ml-5">Kindly Contact HR for the same</label>
                  </div>
                  </c:if>
                  <c:if test="${employeeDetails.keyToRetention=='No'}">
                  <div class="form-group col-md-2 mt-2 text-right" id= "NonRetainEmpLabelId">
                  <label>Reason for Non-Retention<c:if test="${employeeDetails.managerSubmitted=='false'}">*</c:if></label>
                  </div>
                  <div class="form-group col-md-4" <c:if test="${employeeDetails.managerSubmitted=='false'}"> style="display:none"</c:if> id= "NonRetainEmpId">
                  <select class="mdb-select md-form form-control form-control-sm manager" id="reasonNonRetention" name="<portlet:namespace/>reasonNonRetention">
                  <c:if test="${employeeDetails.managerSubmitted=='false'}"><option value="" selected>Choose your option</option></c:if>
                  <option <c:choose><c:when test="${employeeDetails.reasonNonRetention=='Employee Not Willing'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Employee Not Willing">Employee Not Willing</option>
                  <option <c:choose><c:when test="${employeeDetails.reasonNonRetention=='Family Reasons'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Family Reasons">Family Reasons</option>
                  <option <c:choose><c:when test="${employeeDetails.reasonNonRetention=='High Cost'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="High Cost">High Cost</option>
                  <option <c:choose><c:when test="${employeeDetails.reasonNonRetention=='Onsite opportunity'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Onsite opportunity">Onsite opportunity</option>
                  <option <c:choose><c:when test="${employeeDetails.reasonNonRetention=='Others'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Others">Others</option>
                  </select>
         </div>
         </c:if>
         </c:otherwise>
         </c:choose>
      </div>
      </c:if>
      <div class="form-row form-control-sm mb-2">
      <div class="form-group col-md-2 mt-2 text-right">
      <label>Replacement Plan<c:if test="${employeeDetails.managerSubmitted=='false'}">*</c:if></label>
      </div>
      <div class="form-group col-md-4">
      <select class="mdb-select md-form form-control form-control-sm manager" id="replacementPlan" required="required" name="<portlet:namespace/>replacementPlan">
      <c:if test="${employeeDetails.managerSubmitted=='false'}"><option value="" selected disabled>Choose your option</option></c:if>
      <option <c:choose><c:when test="${employeeDetails.replacementPlan=='Not Needed'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled </c:if> </c:otherwise></c:choose>value="Not Needed">Not Needed</option>
      <option <c:choose><c:when test="${employeeDetails.replacementPlan=='Requisition Opened'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled </c:if> </c:otherwise></c:choose>value="Requisition Opened">Requisition Opened</option>
      <option <c:choose><c:when test="${employeeDetails.replacementPlan=='Internal Allocation'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled </c:if> </c:otherwise></c:choose>value="Internal Allocation">Internal Allocation</option>
      </select>
      </div>
      <div id= "replacementFormId" <c:if test="${employeeDetails.managerSubmitted=='false' || (employeeDetails.managerSubmitted=='true' && employeeDetails.replacementPlan =='Internal Allocation')}"> style="display:none" </c:if> class="form-group col-md-6  text-left">
      <label class="ml-5 mt-1">Click <a href="https://docs.google.com/forms/d/e/1FAIpQLSfsC5JLdgV0sg3D7tGYZwITd_acFGq2MFEkUt5ey2oTBSQLuA/viewform" target="_blank">here</a> to access Resource Requisition Form</label>
      </div>
      <div class="form-group col-md-2 text-right" id="empLabel" <c:if test="${employeeDetails.managerSubmitted=='false' || (employeeDetails.managerSubmitted=='true' && employeeDetails.replacementPlan !='Internal Allocation')}"> style="display:none" </c:if>>
      <label>Email id of allocated employee </label>
</div>
<div class="form-group col-md-4" id="empList" <c:if test="${employeeDetails.managerSubmitted=='false' || (employeeDetails.managerSubmitted=='true' && employeeDetails.replacementPlan !='Internal Allocation')}"> style="display:none" </c:if>>
<input type="text" id="emailId" class="form-control form-control-sm p-2  " name="<portlet:namespace/>replacementDetail" value="${employeeDetails.replacementDetail}" <c:if test="${employeeDetails.managerSubmitted=='true'}">readonly</c:if> onblur="validEmail()" />
</div>
</div>
<c:if test="${employeeDetails.statusInt==1}">
<div class="form-row mb-2 form-control-sm">
<div class="form-group col-md-2  text-right">
<label>Last Working day <br>(By Hr)</label>
</div>
<div class="form-group col-md-4">
<input type="text" class="form-control-sm form-control-plaintext p-2"
   readonly id="tentativeLwd" value="${employeeDetails.lastWorkingDate}" />
</div>
</div>
</c:if>
<c:if test="${ employeeDetails.statusInt ==1}">
<div class="form-row mb-2 form-control-sm ">
<div class="form-row form-control-sm mb-2 col-md-12 pt-2">
<div class="form-group col-md-2 mt-2 text-right">
<label>Last Working Day (Finalized)<c:if test="${employeeDetails.managerSubmitted=='false'}">*</c:if></label>
</div>
<div class="form-group col-md-4 position:relative">
<input type="text" id="finalLwd" required="required" style="cursor: pointer" onkeydown="return false"
   value="${employeeDetails.lastWorkingDate}" autocomplete="off"
   class="form-control form-control-sm"  name="<portlet:namespace/>mFinalLwd" />
</div>
</div>
</div>
</c:if>
<div class="col pt-3 pb-2 text-center">
<h5>Section 2 - Employee Evaluation form</h5>
</div>
<div class="form-row mb-2 form-control-sm">
<div class="form-group col-md-2 mt-2 text-right">
<label>Key to Project<c:if test="${employeeDetails.statusInt !=4 && employeeDetails.managerSubmitted=='false' }">*</c:if></label>
</div>
<div class="form-group col-md-4">
<select class="mdb-select md-form form-control form-control-sm manager" id="keyToProject" <c:if test="${employeeDetails.statusInt !=4 }">required="required" </c:if> name="<portlet:namespace/>keyToProject">
<c:if test="${employeeDetails.managerSubmitted=='false'}"><option value="" selected>Choose your option</option></c:if>
<option <c:choose><c:when test="${employeeDetails.keyToProject=='Yes'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Yes">Yes</option>
<option <c:choose><c:when test="${employeeDetails.keyToProject=='No'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="No">No</option>
</select>
</div>
<div class="form-group col-md-2 mt-2 text-right">
<label>Client Impact<c:if test="${employeeDetails.managerSubmitted=='false' }">*</c:if></label>
</div>
<div class="form-group col-md-4">
<select class="mdb-select md-form form-control form-control-sm manager" id="clientImpact" required="required" name="<portlet:namespace/>clientImpact">
<c:if test="${employeeDetails.managerSubmitted=='false'}"><option value="" selected>Choose your option</option></c:if>
<option <c:choose><c:when test="${employeeDetails.clientImpact=='Yes'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Yes">Yes</option>
<option <c:choose><c:when test="${employeeDetails.clientImpact=='No'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="No">No</option>
</select>
</div>
</div>
<div class="form-row mb-2 form-control-sm">
<div class="form-group col-md-2 mt-2 text-right">
<label>Performance Evaluation<c:if test="${employeeDetails.statusInt !=4 && employeeDetails.managerSubmitted=='false' }">*</c:if></label>
</div>
<div class="form-group col-md-4">
<select class="mdb-select md-form form-control form-control-sm manager" id="rating" <c:if test="${employeeDetails.statusInt !=4 }">required="required" </c:if> name="<portlet:namespace/>rating">
<c:if test="${employeeDetails.managerSubmitted=='false'}"><option value="" selected>Choose your option</option></c:if>
<option <c:choose><c:when test="${employeeDetails.rating=='Consistently Exceeds Expectations'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Consistently Exceeds Expectations">Consistently Exceeds Expectations</option>
<option <c:choose><c:when test="${employeeDetails.rating=='Sometimes Exceeds Expectations'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Sometimes Exceeds Expectations">Sometimes Exceeds Expectations</option>
<option <c:choose><c:when test="${employeeDetails.rating=='Consistently Meets Expectations'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Consistently Meets Expectations">Consistently Meets Expectations</option>
<option <c:choose><c:when test="${employeeDetails.rating=='Inconsistently Meets Expectations'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Inconsistently Meets Expectations">Inconsistently Meets Expectations</option>
<option <c:choose><c:when test="${employeeDetails.rating=='Does not Meet Expectations'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Does not Meet Expectations">Does not Meet Expectations</option>
</select>
</div>
<c:if test="${employeeDetails.statusInt !=4 && employeeDetails.statusInt !=5}">
<div class="form-group col-md-2  text-right">
<label>Reason for leaving<c:if test="${employeeDetails.managerSubmitted=='false'}">*</c:if></label>
</div>
<div class="form-group col-md-4">
<select class="mdb-select md-form form-control form-control-sm manager" id="reasonForLeaving" required="required" name="<portlet:namespace/>reasonForLeaving">
<c:if test="${employeeDetails.managerSubmitted=='false'}"><option value="" selected>Choose your option</option></c:if>
<option <c:choose><c:when test="${employeeDetails.reasonForLeaving=='Better Brand'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Better Brand">Better Brand</option>
<option <c:choose><c:when test="${employeeDetails.reasonForLeaving=='Better Salary'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled </c:if> </c:otherwise></c:choose>value="Better Salary">Better Salary</option>
<option <c:choose><c:when test="${employeeDetails.reasonForLeaving=='Family/Domestic Constraints'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Family/Domestic Constraints">Family/Domestic Constraints</option>
<option <c:choose><c:when test="${employeeDetails.reasonForLeaving=='Getting Married'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Getting Married">Getting Married</option>
<option <c:choose><c:when test="${employeeDetails.reasonForLeaving=='Going Abroad'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled </c:if> </c:otherwise></c:choose>value="Going Abroad">Going Abroad</option>
<option <c:choose><c:when test="${employeeDetails.reasonForLeaving=='Higher Education'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled </c:if> </c:otherwise></c:choose>value="Higher Education">Higher Education</option>
<option <c:choose><c:when test="${employeeDetails.reasonForLeaving=='Job Security'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Job Security">Job Security</option>
<option <c:choose><c:when test="${employeeDetails.reasonForLeaving=='Medical Reasons'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled </c:if> </c:otherwise></c:choose>value="Medical Reasons">Medical Reasons</option>
<option <c:choose><c:when test="${employeeDetails.reasonForLeaving=='Promotion/Career Growth'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled </c:if> </c:otherwise></c:choose>value="Promotion/Career Growth">Promotion/Career Growth</option>
<option <c:choose><c:when test="${employeeDetails.reasonForLeaving=='Relation With Co Workers'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled </c:if> </c:otherwise></c:choose>value="Relation With Co Workers">Relation With Co Workers</option>
<option <c:choose><c:when test="${employeeDetails.reasonForLeaving=='Relation with Supervisor'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Relation with Supervisor">Relation with Supervisor</option>
<option <c:choose><c:when test="${employeeDetails.reasonForLeaving=='Relocation'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Relocation">Relocation</option>
<option <c:choose><c:when test="${employeeDetails.reasonForLeaving=='Training opportunities'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Training opportunities">Training opportunities</option>
<option <c:choose><c:when test="${employeeDetails.reasonForLeaving=='Work Conditions'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled </c:if> </c:otherwise></c:choose>value="Work Conditions">Work Conditions</option>
<option <c:choose><c:when test="${employeeDetails.reasonForLeaving=='Others'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled </c:if> </c:otherwise></c:choose>value="Others">Others</option>
</select>
</div>
</c:if>
</div>
<div class="form-row mb-2 form-control-sm">
<div class="form-group col-md-2  text-right">
<label>Is employee eligible for rehire?<c:if test="${employeeDetails.managerSubmitted=='false' }">*</c:if></label>
</div>
<div class="form-group col-md-4">
<select class="mdb-select md-form form-control form-control-sm manager" id="eligibleForRehire" required="required" name="<portlet:namespace/>eligibleForRehire">
<c:if test="${employeeDetails.managerSubmitted=='false'}"><option value="" selected>Choose your option</option></c:if>
<option <c:choose><c:when test="${employeeDetails.eligibleForRehire=='Yes'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Yes">Yes</option>
<option <c:choose><c:when test="${employeeDetails.eligibleForRehire=='No' || (employeeDetails.managerSubmitted=='false' && employeeDetails.statusInt ==4)}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="No">No</option>
</select>
</div>
<div class="form-group col-md-2 mt-2 text-right">
<label>Manager Comments</label>
</div>
<div class="form-group col-md-4">
<textarea <c:if test="${employeeDetails.managerSubmitted=='true'}">readonly</c:if> rows="3" class="form-control form-control-sm manager" maxlength="500"
style="height: 69px;" id="managerComment" cols="47" onblur="$(this).val($(this).val().trim())"
name="<portlet:namespace/>managerComment"> <c:if test="${employeeDetails.statusInt!=1}">${employeeDetails.managerComment}</c:if></textarea>
</div>
</div>
<div class="form-row form-control-sm mb-2">
<div class="form-group col-md-2 mt-2 text-right">
<label>Any other issue including but not limited to attitude, work timings, etc.?<c:if test="${employeeDetails.statusInt !=4 && employeeDetails.managerSubmitted=='false' }">*</c:if></label>
</div>
<div class="form-group col-md-4 mt-2 position:relative">
<select class="mdb-select md-form form-control form-control-sm manager" id="otherIssue"   <c:if test="${employeeDetails.statusInt !=4 }">required="required" </c:if> name="<portlet:namespace/>otherIssue">
<c:if test="${employeeDetails.managerSubmitted=='false'}"><option value="" selected>Choose your option</option></c:if>
<option <c:choose><c:when test="${employeeDetails.otherIssue=='Yes'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Yes">Yes</option>
<option <c:choose><c:when test="${employeeDetails.otherIssue=='No'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${employeeDetails.managerSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="No">No</option>
</select>
</div>
</div>
<div class="row pt-4 pb-2" id="approveButtonDiv">
<c:if test="${(employeeDetails.managerSubmitted=='false'&& employeeDetails.roleType == 'Trantor_Manager')}">
<div class="col-12 text-center">
<button class="btn btn-success mt-4" id="approveButtonId" >
<c:choose><c:when test="${employeeDetails.statusInt ==4  || employeeDetails.statusInt ==5}">Submit</c:when><c:otherwise>Approve</c:otherwise></c:choose></button>
</div>
</c:if>
<c:if test="${(employeeDetails.showManagerMessage=='true'&& employeeDetails.roleType == 'Trantor_Manager' && employeeDetails.lastWorkingDate ==null)}">
<div class="col-12 pt-4 text-center">
Employee's final last working day will be finalized by the HR
</div>
</c:if>
</div>
</form>
</div>
</div>
</c:if>
<c:if test="${(employeeDetails.roleType == 'Trantor_IT' && (employeeDetails.statusInt==2 || employeeDetails.statusInt==5 || employeeDetails.statusInt==4)) || (employeeDetails.roleType == 'Trantor_HR' && employeeDetails.itAssetsSubmitted == 'true' && employeeDetails.statusInt!=6)  }">
   <div class="card">
      <div class="card-header py-0">
         <div class="row">
            <div class="col pt-2">
               <h5>IT Assets Form</h5>
            </div>
         </div>
      </div>
      <div class="card-body">
         <form id="itAssetsListForm" method="post" onSubmit="return confirmItAssetsForm();" action="${submitItAssetsResignUrl}">
            <input type="hidden" id="itResignationId" value="${employeeDetails.resignationId}" name="<portlet:namespace/>itResignationId" />
            <div class="form-row mb-2 form-control-sm">
               <div class="form-group col-md-2 mt-2 text-right">
                  <label>
                     Laptop issued
                     <c:if test="${employeeDetails.itAssetsSubmitted == 'false'}">*</c:if>
                  </label>
               </div>
               <div class="form-group col-md-4">
                  <select class="mdb-select md-form form-control form-control-sm manager" id="laptop" required="required" name="<portlet:namespace/>laptop">
                     <c:if test="${employeeDetails.itAssetsSubmitted == 'false'}">
                        <option value="" selected>Choose your option</option>
                     </c:if>
                     <option
                     <c:choose>
                        <c:when test="${employeeDetails.hasLaptop=='1'}"> selected="selected" </c:when>
                        <c:otherwise>
                           <c:if test="${employeeDetails.itAssetsSubmitted == 'true'}"> style="display:none" disabled</c:if>
                        </c:otherwise>
                     </c:choose>
                     value="1">Yes</option>
                     <option
                     <c:choose>
                        <c:when test="${employeeDetails.hasLaptop=='2'}"> selected="selected" </c:when>
                        <c:otherwise>
                           <c:if test="${employeeDetails.itAssetsSubmitted == 'true'}"> style="display:none" disabled</c:if>
                        </c:otherwise>
                     </c:choose>
                     value="2">No</option>
                  </select>
               </div>
               <div class="form-group col-md-2 mt-2 text-right">
                  <label>
                     Charger issued
                     <c:if test="${employeeDetails.itAssetsSubmitted == 'false'}">*</c:if>
                  </label>
               </div>
               <div class="form-group col-md-4">
                  <select class="mdb-select md-form form-control form-control-sm manager" id="charger" required="required" name="<portlet:namespace/>charger">
                     <c:if test="${employeeDetails.itAssetsSubmitted == 'false'}">
                        <option value="" selected>Choose your option</option>
                     </c:if>
                     <option
                     <c:choose>
                        <c:when test="${employeeDetails.hasCharger=='1'}"> selected="selected" </c:when>
                        <c:otherwise>
                           <c:if test="${employeeDetails.itAssetsSubmitted == 'true'}"> style="display:none" disabled</c:if>
                        </c:otherwise>
                     </c:choose>
                     value="1">Yes</option>
                     <option
                     <c:choose>
                        <c:when test="${employeeDetails.hasCharger=='2'}"> selected="selected" </c:when>
                        <c:otherwise>
                           <c:if test="${employeeDetails.itAssetsSubmitted == 'true'}"> style="display:none" disabled</c:if>
                        </c:otherwise>
                     </c:choose>
                     value="2">No</option>
                  </select>
               </div>
            </div>
            <div class="form-row mb-2 form-control-sm">
               <div class="form-group col-md-2 mt-2 text-right">
                  <label>
                     HeadSet issued
                     <c:if test="${employeeDetails.itAssetsSubmitted == 'false'}">*</c:if>
                  </label>
               </div>
               <div class="form-group col-md-4">
                  <select class="mdb-select md-form form-control form-control-sm manager" id="headset" required="required" name="<portlet:namespace/>headset">
                     <c:if test="${employeeDetails.itAssetsSubmitted == 'false'}">
                        <option value="" selected>Choose your option</option>
                     </c:if>
                     <option
                     <c:choose>
                        <c:when test="${employeeDetails.hasHeadSet=='1'}"> selected="selected" </c:when>
                        <c:otherwise>
                           <c:if test="${employeeDetails.itAssetsSubmitted == 'true'}"> style="display:none" disabled</c:if>
                        </c:otherwise>
                     </c:choose>
                     value="1">Yes</option>
                     <option
                     <c:choose>
                        <c:when test="${employeeDetails.hasHeadSet=='2'}"> selected="selected" </c:when>
                        <c:otherwise>
                           <c:if test="${employeeDetails.itAssetsSubmitted == 'true'}"> style="display:none" disabled</c:if>
                        </c:otherwise>
                     </c:choose>
                     value="2">No</option>
                  </select>
               </div>
               <div class="form-group col-md-2 mt-2 text-right">
                  <label>
                     Mouse issued
                     <c:if test="${employeeDetails.itAssetsSubmitted == 'false'}">*</c:if>
                  </label>
               </div>
               <div class="form-group col-md-4">
                  <select class="mdb-select md-form form-control form-control-sm manager" id="mouse" required="required" name="<portlet:namespace/>mouse">
                     <c:if test="${employeeDetails.itAssetsSubmitted == 'false'}">
                        <option value="" selected>Choose your option</option>
                     </c:if>
                     <option
                     <c:choose>
                        <c:when test="${employeeDetails.hasMouse=='1'}"> selected="selected" </c:when>
                        <c:otherwise>
                           <c:if test="${employeeDetails.itAssetsSubmitted == 'true'}"> style="display:none" disabled</c:if>
                        </c:otherwise>
                     </c:choose>
                     value="1">Yes</option>
                     <option
                     <c:choose>
                        <c:when test="${employeeDetails.hasMouse=='2'}"> selected="selected" </c:when>
                        <c:otherwise>
                           <c:if test="${employeeDetails.itAssetsSubmitted == 'true'}"> style="display:none" disabled</c:if>
                        </c:otherwise>
                     </c:choose>
                     value="2">No</option>
                  </select>
               </div>
            </div>
            <div class="form-row mb-2 form-control-sm">
               <div class="form-group col-md-2 mt-2 text-right">
                  <label>
                     LaptopBag issued
                     <c:if test="${employeeDetails.itAssetsSubmitted == 'false'}">*</c:if>
                  </label>
               </div>
               <div class="form-group col-md-4">
                  <select class="mdb-select md-form form-control form-control-sm manager" id="laptopBag" required="required" name="<portlet:namespace/>laptopBag">
                     <c:if test="${employeeDetails.itAssetsSubmitted == 'false'}">
                        <option value="" selected>Choose your option</option>
                     </c:if>
                     <option
                     <c:choose>
                        <c:when test="${employeeDetails.hasLaptopBag=='1'}"> selected="selected" </c:when>
                        <c:otherwise>
                           <c:if test="${employeeDetails.itAssetsSubmitted == 'true'}"> style="display:none" disabled</c:if>
                        </c:otherwise>
                     </c:choose>
                     value="1">Yes</option>
                     <option
                     <c:choose>
                        <c:when test="${employeeDetails.hasLaptopBag=='2'}"> selected="selected" </c:when>
                        <c:otherwise>
                           <c:if test="${employeeDetails.itAssetsSubmitted == 'true'}"> style="display:none" disabled</c:if>
                        </c:otherwise>
                     </c:choose>
                     value="2">No</option>
                  </select>
               </div>
               <div class="form-group col-md-2 mt-2 text-right">
                  <label>Other Assets</label>
               </div>
               <div class="form-group col-md-4">
                  <textarea rows="3" class="form-control form-control-sm" style="height: 69px;" maxlength="500" id="otherAssetsList" cols="47"
                  <c:if test="${employeeDetails.itAssetsSubmitted == 'true'}"> readonly</c:if>
                  onblur="$(this).val($(this).val().trim())" name="<portlet:namespace/>otherAssetsList">${employeeDetails.otherAssetsList}</textarea>
               </div>
            </div>
            <div class="form-row mb-2 form-control-sm">
               <div class="form-group col-md-2 mt-2 text-right">
                  <label>
                     Monitor for home issued
                     <c:if test="${employeeDetails.itAssetsSubmitted == 'false'}">*</c:if>
                  </label>
               </div>
               <div class="form-group col-md-4">
                  <select class="mdb-select md-form form-control form-control-sm manager" id="homeMonitor" required="required" name="<portlet:namespace/>homeMonitor">
                     <c:if test="${employeeDetails.itAssetsSubmitted == 'false'}">
                        <option value="" selected>Choose your option</option>
                     </c:if>
                     <option
                     <c:choose>
                        <c:when test="${employeeDetails.hasHomeMonitor=='1'}"> selected="selected" </c:when>
                        <c:otherwise>
                           <c:if test="${employeeDetails.itAssetsSubmitted == 'true'}"> style="display:none" disabled</c:if>
                        </c:otherwise>
                     </c:choose>
                     value="1">Yes</option>
                     <option
                     <c:choose>
                        <c:when test="${employeeDetails.hasHomeMonitor=='2'}"> selected="selected" </c:when>
                        <c:otherwise>
                           <c:if test="${employeeDetails.itAssetsSubmitted == 'true'}"> style="display:none" disabled</c:if>
                        </c:otherwise>
                     </c:choose>
                     value="2">No</option>
                  </select>
               </div>
            </div>
            <div class="form-row mb-2 form-control-sm">
               <div class="form-group col-md-2 mt-2 text-right">
                  <label>
                     Desktop for home issued
                     <c:if test="${employeeDetails.itAssetsSubmitted == 'false'}">*</c:if>
                  </label>
               </div>
               <div class="form-group col-md-4">
                  <select class="mdb-select md-form form-control form-control-sm manager" id="homeDesktop" required="required" name="<portlet:namespace/>homeDesktop">
                     <c:if test="${employeeDetails.itAssetsSubmitted == 'false'}">
                        <option value="" selected>Choose your option</option>
                     </c:if>
                     <option
                     <c:choose>
                        <c:when test="${employeeDetails.hasHomeDesktop=='1'}"> selected="selected" </c:when>
                        <c:otherwise>
                           <c:if test="${employeeDetails.itAssetsSubmitted == 'true'}"> style="display:none" disabled</c:if>
                        </c:otherwise>
                     </c:choose>
                     value="1">Yes</option>
                     <option
                     <c:choose>
                        <c:when test="${employeeDetails.hasHomeDesktop=='2'}"> selected="selected" </c:when>
                        <c:otherwise>
                           <c:if test="${employeeDetails.itAssetsSubmitted == 'true'}"> style="display:none" disabled</c:if>
                        </c:otherwise>
                     </c:choose>
                     value="2">No</option>
                  </select>
               </div>
            </div>
            <c:if test="${employeeDetails.itAssetsSubmitted == 'false'}">
               <div class="row pt-4 pb-2" id="submitItAssetsButtonDiv">
                  <div class="col-12 text-center">
                     <button class="btn btn-success mt-4" id="submitItAssetsButtonDiv" >Submit</button>
                  </div>
               </div>
            </c:if>
         </form>
      </div>
   </div>
</c:if>
<c:if test="${((employeeDetails.roleType == 'Trantor_HR'&& (employeeDetails.statusInt==1 ||employeeDetails.statusInt==2 ||  employeeDetails.statusInt==5)) || (employeeDetails.roleType == 'Trantor_Manager' && employeeDetails.statusInt==2) )}">
   <div class="card m-0">
      <div class="card-header">
         <form  id="updateFinalLwd" method="post" onSubmit="return confirmFinalLwd();" action="${submitFinalLwdUrl}">
            <input type="hidden" id="lwdResignationId" value="${employeeDetails.resignationId}" name="<portlet:namespace/>lwdResignationId" />
            <input type="hidden"
            <c:if test="${employeeDetails.roleType == 'Trantor_HR'}"> value="Trantor_HR" </c:if>
            <c:if test="${employeeDetails.roleType == 'Trantor_Manager'}"> value="Trantor_Manager" </c:if>
            name=" <portlet:namespace/> roleType" />
            <div class="row">
               <div class="col-2 pt-2 text-left pb-2">
                  <h5>Final LWD*</h5>
               </div>
               <div class="col-2 p-2">
                  <input type="text" id="finalLwd" required="required" style="cursor: pointer" onkeydown="return false" value="${employeeDetails.lastWorkingDate}"
                     class="form-control form-control-sm border border-dark" name="<portlet:namespace/>finalLwd" />
               </div>
               <c:if test="${employeeDetails.roleType == 'Trantor_Manager'}">
                  <div class="p-3 text-left pb-2">
                     <input type="checkbox"  id="managerDisclaimer" />
                     <label class= "pr-2">Last Working Day (to be changed after discussion with HR)</label>
                  </div>
               </c:if>
               <div class="col-2 p-2 text-center" style="cursor: pointer;">
                  <button class="btn btn-primary btn-sm" id="lwdButton"
                  <c:if test="${employeeDetails.roleType == 'Trantor_Manager'}"> disabled </c:if>
                  >Update</button>
               </div>
               <div class="col-6"></div>
            </div>
         </form>
      </div>
   </div>
</c:if>
<c:if test="${(employeeDetails.roleType == 'Trantor_HR' || employeeDetails.roleType == 'Trantor_Manager') && employeeDetails.statusInt==3}">
   <div class="card">
      <div class="card-header py-0">
         <div class="row">
            <div class="col pt-2">
               <h5>Review Withdraw</h5>
            </div>
         </div>
      </div>
      <form id="hrWithdrawalActionForm" method="post" onSubmit="return confirmHrWithdrawalActionForm();" action="${submitHrWithdrawalActionFormUrl}">
         <div class="card-body">
            <input type="hidden" id="withdrawResignationId" value="${employeeDetails.resignationId}" name="<portlet:namespace/>withdrawResignationId" />
            <input type="hidden" id="withdrawActionPerform" value="" name="<portlet:namespace/>withdrawActionPerform" />
            <div class="form-row mb-2 form-control-sm">
               <div class="form-group col-md-2 mt-2 text-right">
                  <label>Employee comments</label>
               </div>
               <div class="form-group col-md-4">
                  <textarea class="form-control-sm form-control-plaintext border border-secondary pl-3" rows="3" style="height: 69px;" maxlength="500"
                     cols="47" readonly id="empWithdrawComments">${employeeDetails.empWithdrawComment}</textarea>
               </div>
               <div class="form-group col-md-2 mt-2 text-right">
                  <label>
                     <c:choose>
                        <c:when test="${employeeDetails.roleType == 'Trantor_Manager'}">Manager</c:when>
                        <c:otherwise>HR</c:otherwise>
                     </c:choose>
                     Comments
                  </label>
               </div>
               <div class="form-group col-md-4">
                  <textarea rows="3" class="form-control form-control-sm" maxlength="500"
                     style="height: 69px;" id="hrWithdrawComment" cols="47" onblur="$(this).val($(this).val().trim())"
                     name="<portlet:namespace/>hrWithdrawComments"></textarea>
               </div>
            </div>
            <div class="row mt-5">
               <div class="col-6 text-right">
                  <button class="btn btn-outline-success" id="approveWithdrawId" onClick="return approveRejectValidation(2)">Approve</button>
               </div>
               <div class="col-6 text-left">
                  <button class="btn btn-outline-danger" id="rejectWithdrawId" onClick="return approveRejectValidation(3)" >Reject</button>
               </div>
            </div>
         </div>
      </form>
   </div>
</c:if>
</div>
<script>
   var holidays = new Array();
$(document).ready(function ()
{
     <c:forEach var="holidayDate" items="${holidayDates}" varStatus="status">
                 holidays.push("${holidayDate}");
             </c:forEach>
             $("#lwd").datepicker(
                    {
                       dateFormat: "yy-mm-dd",
                       firstDay: 0,
                       beforeShowDay: setHoliday,
                    });
                    $("#finalLwdByHr").datepicker(
                    {
                       minDate: 0,
                       dateFormat: "yy-mm-dd",
                       beforeShowDay: setHoliday,
                    });
    });

       function setHoliday(date)
          {
             var string = $.datepicker.formatDate('yy-mm-dd', date);
             var filterDate = new Date(string);
             var day = filterDate.getDay();
             var isHoliday = ($.inArray(string, holidays) != -1);
             return [day != 0 && day != 6 && !isHoliday]
          }
</script>