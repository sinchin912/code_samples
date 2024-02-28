<%@ include file="/init.jsp" %>
<portlet:actionURL name="submitResign" var="submitResignUrl"></portlet:actionURL>
<portlet:resourceURL var="managerDetailUrl" id="managerDetail"></portlet:resourceURL>
<portlet:renderURL var="resignationRenderURL" windowState="normal"></portlet:renderURL>
<style>
   .carousel-caption{
   position : relative;
   top : 0;
   left : 0;
   }
</style>
<div class="container">
<c:set var="pageUrl" value="${fn:split(resignationRenderURL.toString(),'?')}" />
<div id="resignationForm" class="card">
   <div class="card-header">
      <div class="row">
         <div class="col pt-2">
            <h5>Resignation Form</h5>
         </div>
         <div class="col-2 text-right">                <a class="btn btn-outline-secondary" id="reset" href="${pageUrl[0]}"  role="button">
            Cancel</a>
         </div>
      </div>
   </div>
   <div class="card-body">
      <form id="resignForm" method="post" action="${submitResignUrl}"
         onSubmit="return confirmResignFormSubmission(flag);">
         <input type="hidden" id="resignationId"
            value="${empCoreDetails.resignationId}" name="<portlet:namespace/>resignationId" />
         <input type="hidden" id="managerEmail"
            value="${empCoreDetails.managerEmail}" />
         <div class="pb-5">
            <div class="row mb-2 form-control-sm">
               <div class="col-md-2 mt-2 text-right">
                  <label>E-Code  </label>
               </div>
               <div class="col-md-4">
                  <input type="text" id="ecode" class="form-control-sm form-control-plaintext pl-3 pt-2" value="${empCoreDetails.employeeCode}"  readonly />
               </div>
               <div class="col-md-2 mt-2 text-right">
                  <label>Name  </label>
               </div>
               <div class="col-md-4">
                  <input type="text" id="name" class="form-control-sm form-control-plaintext pl-3 pt-2" value="${empCoreDetails.employeeName}" readonly />
               </div>
            </div>
            <div class="row mb-2 form-control-sm">
               <div class="col-md-2 mt-2 text-right">
                  <label>Manager Name</label>
               </div>
               <div class="col-md-4">
                  <input type="text" id="managerName" class="form-control-sm form-control-plaintext pl-3 pt-2" value="${empCoreDetails.managerName}"  readonly />
               </div>
               <div class="col-md-2 mt-2 text-right">
                  <label>Project Name</label>
               </div>
               <div class="col-md-4">
                  <input type="text" id="projectName" class="form-control-sm form-control-plaintext pl-3 pt-2" value="${empCoreDetails.account}"  readonly />
               </div>
            </div>
            <div class="row mb-2 form-control-sm">
               <div class="col-md-2 mt-2 text-right">
                  <label>Band</label>
               </div>
               <div class="col-md-4">
                  <input type="text" id="band" class="form-control-sm form-control-plaintext pl-3 pt-2" value="${empCoreDetails.employeeBand}" readonly />
               </div>
               <div class="col-md-2 mt-2 text-right">
                  <label>Location</label>
               </div>
               <div class="col-md-4">
                  <input type="text" id="location" class="form-control-sm form-control-plaintext pl-3  pt-2" value="${empCoreDetails.location}" readonly />
               </div>
            </div>
            <div class="row mb-2 form-control-sm">
               <div class="col-md-2 mt-2 text-right">
                  <label>
                     Mobile
                     <c:if test ="${empCoreDetails.employeeUpdated == 'false'}">*</c:if>
                  </label>
               </div>
               <div class="col-md-4">
                  <input type="text" id="mobile"
                  <c:choose>
                     <c:when test="${empCoreDetails.employeeUpdated == 'false' && empCoreDetails.empStatus != statusMap.get(4).getExitStateKey()  && empCoreDetails.abscondTerminateRetained == 'false'}">
                        minlength="10" maxlength="10"
                        oninvalid="this.setCustomValidity('Please enter 10 digits mobile number')"
                        oninput="this.setCustomValidity('')"
                        pattern="^[0-9]*$" class="form-control-sm form-control pl-3" required
                     </c:when>
                     <c:otherwise>
                        readonly
                        class="${empCoreDetails.empStatus != 4 && empCoreDetails.abscondTerminateRetained == 'false'? 'form-control-sm form-control-plaintext pl-3  border border-secondary' : 'form-control-sm form-control-plaintext pl-3 '}"
                     </c:otherwise>
                  </c:choose>
                  value="${empCoreDetails.empMobNo}"   name="<portlet:namespace/>mobileNo" />
               </div>
               <c:if test="${empCoreDetails.resignationId != 0 && empCoreDetails.empStatus != statusMap.get(6).getExitStateKey() && empCoreDetails.empStatus != statusMap.get(1).getExitStateKey()}">
                  <div class="col-md-2 text-right " id="lwday">
                     <label>Last Working Day(Finalized)</label>
                  </div>
                  <div class="col-md-4">
                     <input type="text" id="lwd" class="form-control-sm form-control-plaintext pl-3"  value="${empCoreDetails.lastWorkingDate}" readonly />
                  </div>
               </c:if>
            </div>
            <c:if test = "${empCoreDetails.empStatus != statusMap.get(4).getExitStateKey() && empCoreDetails.abscondTerminateRetained == 'false'}">
               <div class="row mb-2 form-control-sm">
                  <div class="col-md-2 mt-2 text-right">
                     <label>
                        State Name
                        <c:if test ="${empCoreDetails.stateName == null}">*</c:if>
                     </label>
                  </div>
                  <div class="col-md-4">
                     <c:choose>
                        <c:when test="${empCoreDetails.stateName == null}">
                           <select class="mdb-select md-form form-control form-control-sm" id="state" required="required" name="<portlet:namespace/>state">
                              <option value="" selected>Choose your option</option>
                              <c:forEach items="${empCoreDetails.states}" var="stateName" varStatus="count">
                                 <option   value="${stateName}">${stateName}</option>
                              </c:forEach>
                           </select>
                        </c:when>
                        <c:otherwise>
                           <input type="text" id="state"  class="form-control-sm form-control-plaintext pl-3 border border-secondary" value="${empCoreDetails.stateName}" readonly/>
                        </c:otherwise>
                     </c:choose>
                  </div>
                  <div class="col-md-2 mt-2 text-right">
                     <label>
                        Personal Email Id
                        <c:if test ="${empCoreDetails.employeeEmail == null}">*</c:if>
                     </label>
                  </div>
                  <div class="col-md-4">
                     <input type="text"  id="emailId"
                     <c:choose>
                        <c:when test="${empCoreDetails.employeeEmail== null}">
                           onblur="validEmail()"  maxlength="75" class="form-control form-control-sm pl-3" value="${empCoreDetails.alternateEmail}" name="<portlet:namespace/>email"  required />
                        </c:when>
                        <c:otherwise>
                           class="form-control-sm form-control-plaintext pl-3 border border-secondary" value="${empCoreDetails.employeeEmail}" readonly/>
                        </c:otherwise>
                     </c:choose>
                  </div>
               </div>
               <div class="row mb-2 form-control-sm">
                  <div class="col-md-2 mt-2 text-right">
                     <label>
                        City Name
                        <c:if test ="${empCoreDetails.cityName == null}">*</c:if>
                     </label>
                  </div>
                  <div class="col-md-4">
                     <input type="text"  id="city"
                     <c:choose>
                        <c:when test="${empCoreDetails.cityName == null}">
                           onblur="$(this).val($(this).val().trim())" maxlength="75" class="form-control form-control-sm pl-3" name="<portlet:namespace/>city" required />
                        </c:when>
                        <c:otherwise>
                           class="form-control-sm form-control-plaintext pl-3 border border-secondary" value="${empCoreDetails.cityName}"  name="<portlet:namespace/>city" readonly/>
                        </c:otherwise>
                     </c:choose>
                  </div>
                  <c:if test = "${empCoreDetails.empStatus != statusMap.get(5).getExitStateKey() &&  (empCoreDetails.abscondTerminateDate == ''  || empCoreDetails.abscondTerminateDate == null)}">
                     <div class="col-md-2 mt-2 text-right">
                        <label>
                           Reason for leaving
                           <c:if test ="${empCoreDetails.stateName == null}">*</c:if>
                        </label>
                     </div>
                     <div class="col-md-4">
                        <c:choose>
                           <c:when test="${empCoreDetails.stateName == null}">
                              <select class="mdb-select md-form form-control form-control-sm" id="reason" required="required" name="<portlet:namespace/>reason">
                                 <option value="" disabled selected>Choose your option</option>
                                 <option ${reason == 'Better Brand' ? 'selected="selected"': ' '} value="Better Brand">Better Brand</option>
                                 <option ${reason == 'Better Salary' ? 'selected="selected"': ' '} value="Better Salary">Better Salary</option>
                                 <option ${reason == 'Family/Domestic Constraints' ? 'selected="selected"': ' '} value="Family/Domestic Constraints">Family/Domestic Constraints</option>
                                 <option ${reason == 'Getting Married' ? 'selected="selected"': ' '} value="Getting Married">Getting Married</option>
                                 <option ${reason == 'Going Abroad' ? 'selected="selected"': ' '} value="Going Abroad">Going Abroad</option>
                                 <option ${reason == 'Higher Education' ? 'selected="selected"': ' '} value="Higher Education">Higher Education</option>
                                 <option ${reason == 'Job Security' ? 'selected="selected"': ' '} value="Job Security">Job Security</option>
                                 <option ${reason == 'Medical Reasons' ? 'selected="selected"': ' '} value="Medical Reasons">Medical Reasons</option>
                                 <option ${reason == 'Promotion/Career Growth' ? 'selected="selected"': ' '} value="Promotion/Career Growth">Promotion/Career Growth</option>
                                 <option ${reason == 'Relation With Co Workers' ? 'selected="selected"': ' '} value="Relation With Co Workers">Relation With Co-Workers</option>
                                 <option ${reason == 'Relation with Supervisor' ? 'selected="selected"': ' '} value="Relation with Supervisor">Relation With Supervisor</option>
                                 <option ${reason == 'Relocation' ? 'selected="selected"': ' '} value="Relocation">Relocation</option>
                                 <option ${reason == 'Training opportunities' ? 'selected="selected"': ' '} value="Training opportunities">Training opportunities</option>
                                 <option ${reason == 'Work Conditions' ? 'selected="selected"': ' '} value="Work Conditions">Work Conditions</option>
                                 <option ${reason == 'Others' ? 'selected="selected"': ' '} value="Others">Others</option>
                              </select>
                           </c:when>
                           <c:otherwise>
                              <input type="text" id="reason"  class="form-control-sm form-control-plaintext pl-3 border border-secondary" value="${empCoreDetails.reason}" readonly/>
                           </c:otherwise>
                        </c:choose>
                     </div>
                  </c:if>
               </div>
               <div class="row mb-2 form-control-sm">
                  <div class="col-md-2 mt-2 text-right">
                     <label>
                        Pincode
                        <c:if test ="${empCoreDetails.pincode == null}">*</c:if>
                     </label>
                  </div>
                  <div class="col-md-4">
                     <input type="text" id="pincode"
                     <c:choose>
                        <c:when test="${empCoreDetails.pincode == null}">
                           minlength="6" maxlength="6"
                           oninvalid="this.setCustomValidity('Please enter 6 digits for pincode')"
                           oninput="this.setCustomValidity('')"
                           pattern="^[0-9]*$" class="form-control form-control-sm pl-3" name="<portlet:namespace/>pincode" required/>


                        </c:when>
                        <c:otherwise>
                           class="form-control-sm form-control-plaintext pl-3 border border-secondary" value="${empCoreDetails.pincode}" readonly/>
                        </c:otherwise>
                     </c:choose>
                  </div>
                  <c:if test = "${empCoreDetails.empStatus != statusMap.get(5).getExitStateKey() &&  (empCoreDetails.abscondTerminateDate == ''  || empCoreDetails.abscondTerminateDate == null) }">
                     <div class="col-md-2 mt-2 text-right">
                        <label>More Information For Leaving</label>
                     </div>
                     <div class="col-md-4">
                        <textarea rows="3"  style="height:69px;" maxlength="500" id="employeeComment" cols="49"
                        <c:choose>
                           <c:when test="${empCoreDetails.postalAddress== null}">
                              onblur="$(this).val($(this).val().trim())" class="form-control form-control-sm pl-3"onblur="$(this).val($(this).val().trim())" name="<portlet:namespace/>employeeComment"></textarea>
                           </c:when>
                           <c:otherwise>
                              <textarea rows="3" class="form-control-sm form-control-plaintext pl-3 border border-secondary"  readonly>${empCoreDetails.employeeComment}</textarea>
                           </c:otherwise>
                        </c:choose>
                     </div>
                  </c:if>
               </div>
               <div class="row mb-2 form-control-sm">
                  <div class="col-md-2 mt-2 text-right">
                     <label>
                        Full Postal Address
                        <c:if test ="${empCoreDetails.postalAddress == null}">*</c:if>
                     </label>
                  </div>
                  <div class="col-md-4">
                     <textarea rows="3"  id="postalAddress" cols="49"
                     <c:choose>
                        <c:when test="${empCoreDetails.postalAddress== null}">
                           class="form-control form-control-sm pl-3" style="height: 69px;" maxlength="500"  onblur="$(this).val($(this).val().trim())" required name="<portlet:namespace/>postalAddress"></textarea>
                        </c:when>
                        <c:otherwise>
                           class="form-control-sm form-control-plaintext pl-3 border border-secondary" style="height: 69px;" maxlength="500" readonly>${empCoreDetails.postalAddress}</textarea>
                        </c:otherwise>
                     </c:choose>
                  </div>
               </div>
            </c:if>
            <c:if test = "${empCoreDetails.withdrawCount == '2' || empCoreDetails.withdrawCount == '1'}">
               <div id="commentDiv" class="row mb-2 mt-4 form-control-sm">
                  <div class="col-md-2 mt-4 text-right">
                     <label>Withdrawn Comments</label>
                  </div>
                  <div class="col-md-4 mt-4">
                     <textarea rows="3" class="form-control-sm form-control-plaintext pl-3 border border-secondary" id="withdrawcomment" cols="49" style="height:69px;" readonly>${empCoreDetails.empWithdrawComment}</textarea>
                  </div>
               </div>
            </c:if>
         </div>
         <c:if test="${empCoreDetails.employeeUpdated == 'false' && empCoreDetails.empStatus != statusMap.get(4).getExitStateKey()  && empCoreDetails.abscondTerminateRetained == 'false'}">
            <div class="row pt-4" >
               <div class="col-12 text-center">
                  <button id="submitButtonId" onClick="${empCoreDetails.empStatus ==5 ?'flag=3':'flag=1'} " class="btn btn-primary" >Submit</button>
               </div>
            </div>
         </c:if>
         <c:if test = "${(empCoreDetails.empStatus == statusMap.get(0).getExitStateKey() || empCoreDetails.empStatus == statusMap.get(1).getExitStateKey() || empCoreDetails.empStatus == statusMap.get(2).getExitStateKey() )  && empCoreDetails.withdrawCount !='2'}">
            <div id="withdrawDivId">
               <div class="row pt-4">
                  <div class="col-12">
                     <hr/>
                  </div>
               </div>
               <div class="row pt-4">
                  <div class="col-12" >
                     <div class="input-group">
                        <textarea class="form-control form-control-sm"
                           onblur="$(this).val($(this).val().trim())"
                           placeholder="Provide your reason for withdrawing"
                           style="height: 40px;" rows="2" cols="10" maxlength="500"
                           name="<portlet:namespace/>withdrawReason" id="withdrawReasonId" required></textarea>
                        <button class="btn btn-danger" onClick="flag=2">Withdraw</button>
                     </div>
                  </div>
               </div>
            </div>
         </c:if>
      </form>
   </div>
</div>
<div></div>
<c:if test="${empCoreDetails.itAssetsSubmitted == 'true'}">
   <div id="it-assets" class="card ">
      <div class="card-header py-0">
         <div class="row">
            <div class="col pt-2">
               <h5>List of IT Assets to be returned : </h5>
            </div>
         </div>
      </div>
      <div class="card-body">
         <div class="row mb-2 form-control-sm">
            <div class="col-md-2 mt-2 text-right">
               <label>Laptop issued : </label>
            </div>
            <div class="col-md-4">
               <input type="text" id="laptop" class="form-control-sm form-control-plaintext pl-3  border border-secondary" value="${empCoreDetails.hasLaptop}" readonly />
            </div>
            <div class="col-md-2 mt-2 text-right">
               <label>Charger issued : </label>
            </div>
            <div class="col-md-4">
               <input type="text" id="charger" class="form-control-sm form-control-plaintext pl-3  border border-secondary" value="${empCoreDetails.hasCharger}" readonly />
            </div>
         </div>
         <div class="row mb-2 form-control-sm">
            <div class="col-md-2 mt-2 text-right">
               <label>HeadSet issued : </label>
            </div>
            <div class="col-md-4">
               <input type="text" id="headset" class="form-control-sm form-control-plaintext pl-3  border border-secondary" value="${empCoreDetails.hasHeadSet}" readonly />
            </div>
            <div class="col-md-2 mt-2 text-right">
               <label>Mouse issued : </label>
            </div>
            <div class="col-md-4">
               <input type="text" id="mouse" class="form-control-sm form-control-plaintext pl-3  border border-secondary" value="${empCoreDetails.hasMouse}" readonly />
            </div>
         </div>
         <div class="row mb-2 form-control-sm">
            <div class="col-md-2 mt-2 text-right">
               <label>LaptopBag issued : </label>
            </div>
            <div class="col-md-4">
               <input type="text" id="laptopbag" class="form-control-sm form-control-plaintext pl-3  border border-secondary" value="${empCoreDetails.hasLaptopBag}" readonly />
            </div>
            <div class="col-md-2 mt-2 text-right">
               <label>Desktop for home issued : </label>
            </div>
            <div class="col-md-4">
               <input type="text" id="desktop" class="form-control-sm form-control-plaintext pl-3  border border-secondary" value="${empCoreDetails.hasHomeDesktop}" readonly />
            </div>
         </div>
         <div class="row mb-2 form-control-sm">
            <div class="col-md-2 mt-2 text-right">
               <label>Monitor for home issued </label>
            </div>
            <div class="col-md-4">
               <input type="text" id="monitor" class="form-control-sm form-control-plaintext pl-3  border border-secondary" value="${empCoreDetails.hasHomeMonitor}" readonly />
            </div>
            <div class="col-md-2 mt-2 text-right">
               <label>Other Assets issued </label>
            </div>
            <div class="col-md-4">
               <textarea rows="3" class="form-control-sm form-control-plaintext pl-3  border border-secondary" style="height: 49px;" maxlength="500" id="otherAssetsList" cols="47" readonly >${empCoreDetails.otherAssetsList}</textarea>
            </div>
         </div>
         <c:if test="${empCoreDetails.empStatus != statusMap.get(5).getExitStateKey()}">
            <div class="p-3 text-left pb-2">
               <label class= "pl-1 pr-2">In case of any concerns, please raise it on IT Helpdesk</label>
            </div>
         </c:if>
      </div>
   </div>
</c:if>