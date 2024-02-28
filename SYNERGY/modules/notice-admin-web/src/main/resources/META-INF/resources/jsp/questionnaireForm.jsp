<%@ include file="/init.jsp"%> <portlet:actionURL name="submitQuestionnaireForm" var="submitQuestionnaireFormUrl"></portlet:actionURL>
<portlet:renderURL var="resignationRenderURL" windowState="normal"></portlet:renderURL>
    <portlet:actionURL name="hrRemarksSubmissionUrl" var="hrRemarksSubmissionUrl"></portlet:actionURL>


<style>

   textarea {
   resize: none;
   }
</style>
<div class="container">
    <c:set var="pageUrl" value="${fn:split(resignationRenderURL.toString(),'?')}" />
    <div id="exitForm" class="card">
        <div class="card-header">
            <div class="row">
                <div class="col pt-2">
                    <h5>Exit Interview Form</h5>
                </div>
                <div class="col-2 text-right"> <a class="btn btn-outline-secondary" id="reset" href="${pageUrl[0]}" style="color: black;" role="button"> Cancel</a> </div>
            </div>
        </div>
            <div class="card-body">
                <div class="form-row mb-2 form-control-sm">
                    <div class="form-group col-md-2 mt-2 text-right"> <label>Ecode</label> </div>
                    <div class="form-group col-md-4"> <input type="text" class="form-control-plaintext pl-3" value="${empCoreDetails.employeeCode}" readonly /> </div>
                    <div class="form-group col-md-2 mt-2 text-right"> <label>Name</label> </div>
                    <div class="form-group col-md-4"> <input type="text" class="form-control-plaintext pl-3" value="${empCoreDetails.employeeName}" readonly /> </div>
                </div>
                <div class="form-row mb-2 form-control-sm">
                    <div class="form-group col-md-2 mt-2 text-right"> <label>Manager Name</label> </div>
                    <div class="form-group col-md-4"> <input type="text" class="form-control-plaintext pl-3" value="${empCoreDetails.managerName}" readonly /> </div>
                    <div class="form-group col-md-2 mt-2 text-right"> <label>Project</label> </div>
                    <div class="form-group col-md-4"> <input type="text" class="form-control-plaintext pl-3" value="${empCoreDetails.account}" readonly /> </div>
                </div>
                <div class="form-row mb-2 form-control-sm">
                    <div class="form-group col-md-2 mt-2 text-right"> <label>Mobile</label> </div>
                    <div class="form-group col-md-4"> <input type="text" class="form-control-plaintext pl-3" value="${empCoreDetails.empMobNo}" readonly /> </div>
                    <div class="form-group col-md-2 mt-2 text-right"> <label>City</label> </div>
                    <div class="form-group col-md-4"> <input type="text" class="form-control-plaintext pl-3" value="${empCoreDetails.cityName}" readonly /> </div>
                </div>
                <div class="form-row mb-2 form-control-sm">
                    <div class="form-group col-md-2 mt-2 text-right"> <label>Postal Address</label> </div>
                    <div class="form-group col-md-4"> <textarea rows="3" id="postalAddress" cols="49" class="form-control-sm form-control-plaintext  border border-secondary pl-3" style="height: 49px;" maxlength="500" readonly>${empCoreDetails.postalAddress}</textarea> </div>
                    <div class="form-group col-md-2 mt-2 text-right"> <label>Last Working Day</label> </div>
                    <div class="form-group col-md-4"> <input type="text" class="form-control-plaintext pl-3" value="${empCoreDetails.lastWorkingDate}" readonly /> </div>
                </div>
            </div>
            <div class="card-body">
                <div class="col-12 pt-2 font-weight-bold"> Organizational Climate </div>
                <div class="col-12 pt-2 font-weight-normal">
                    <p>Please indicate how you feel about the following by circling the proper number applicable to your most recent position at Trantor.</p>
                </div>
                <div class="form-row mb-2 form-control-sm">
                    <div class="form-group col-md-8 mt-2 pt-2"> <label> Overall work experience at Trantor </label> </div>
                    <div class="form-group col-md-4 pt-2"> <select class="mdb-select md-form form-control form-control-sm" id="workExperience" >
                            <option <c:choose> <c:when test="${questionnaireFormData.workExperience=='Very Satisfied'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if>  </c:otherwise> </c:choose>value="Very Satisfied">Very Satisfied </option>
                            <option <c:choose> <c:when test="${questionnaireFormData.workExperience=='Mostly Satisfied'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="Mostly Satisfied">Mostly Satisfied </option>
                            <option <c:choose> <c:when test="${questionnaireFormData.workExperience=='Satisfied'}"> selected="selected" </c:when><c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if>  </c:otherwise> </c:choose>value="Satisfied">Satisfied </option>
                            <option <c:choose> <c:when test="${questionnaireFormData.workExperience=='Mostly Dissatisfied'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if></c:otherwise></c:choose>value="Mostly Dissatisfied">Mostly Dissatisfied</option>
                            <option <c:choose> <c:when test="${questionnaireFormData.workExperience=='Very Dissatisfied'}"> selected="selected" </c:when><c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="Very Dissatisfied">Very Dissatisfied </option>
                        </select> </div>
                </div>
                <div class="form-row mb-2 form-control-sm">
                    <div class="form-group col-md-8 mt-2  pt-2"> <label>Perception of the Trantor&#039s overall communication with employees and transparency in the system </label> </div>
                    <div class="form-group col-md-4 pt-2"> <select class="mdb-select md-form form-control form-control-sm" id="communicationWithEmployees" >
                       <option <c:choose> <c:when test="${questionnaireFormData.communicationWithEmployees=='Very Satisfied'}"> selected="selected" </c:when><c:otherwise><c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if></c:otherwise></c:choose>value="Very Satisfied">Very Satisfied </option>
                       <option <c:choose> <c:when test="${questionnaireFormData.communicationWithEmployees=='Mostly Satisfied'}"> selected="selected" </c:when><c:otherwise><c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if></c:otherwise> </c:choose>value="Mostly Satisfied">Mostly Satisfied </option>
                       <option <c:choose> <c:when test="${questionnaireFormData.communicationWithEmployees=='Satisfied'}"> selected="selected" </c:when><c:otherwise><c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if></c:otherwise></c:choose>value="Satisfied">Satisfied</option>
                       <option <c:choose> <c:when test="${questionnaireFormData.communicationWithEmployees=='Mostly Dissatisfied'}"> selected="selected" </c:when> <c:otherwise>  <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if></c:otherwise></c:choose>value="Mostly Dissatisfied">Mostly Dissatisfied</option>
                       <option <c:choose>  <c:when test="${questionnaireFormData.communicationWithEmployees=='Very Dissatisfied'}"> selected="selected" </c:when>  <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if></c:otherwise></c:choose>value="Very Dissatisfied">Very Dissatisfied</option>
                        </select> </div>
                </div>
                <div class="form-row mb-2 form-control-sm">
                    <div class="form-group col-md-8 mt-2 pt-2"> <label> The opportunity to enroll in Training courses and workshops </label> </div>
                    <div class="form-group col-md-4 pt-2"> <select class="mdb-select md-form form-control form-control-sm" id="trainingOpportunity" >
                           <option <c:choose> <c:when test="${questionnaireFormData.trainingOpportunity=='Very Satisfied'}"> selected="selected" </c:when><c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if></c:otherwise></c:choose>value="Very Satisfied">Very Satisfied</option>
                           <option <c:choose><c:when test="${questionnaireFormData.trainingOpportunity=='Mostly Satisfied'}"> selected="selected" </c:when><c:otherwise><c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if></c:otherwise> </c:choose>value="Mostly Satisfied">Mostly Satisfied</option>
                           <option <c:choose><c:when test="${questionnaireFormData.trainingOpportunity=='Satisfied'}"> selected="selected" </c:when><c:otherwise><c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if></c:otherwise></c:choose>value="Satisfied">Satisfied</option>
                           <option <c:choose> <c:when test="${questionnaireFormData.trainingOpportunity=='Mostly Dissatisfied'}"> selected="selected" </c:when><c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise>  </c:choose>value="Mostly Dissatisfied">Mostly Dissatisfied</option>
                           <option <c:choose><c:when test="${questionnaireFormData.trainingOpportunity=='Very Dissatisfied'}"> selected="selected" </c:when><c:otherwise><c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="Very Dissatisfied">Very Dissatisfied</option>
                        </select> </div>
                </div>
                <div class="form-row mb-2 form-control-sm">
                    <div class="form-group col-md-8 mt-2  pt-2"> <label>Your perception of the Trantor&#039s ability to deal fairly with staff</label> </div>
                    <div class="form-group col-md-4 pt-2"> <select class="mdb-select md-form form-control form-control-sm" id="dealingWithStaff" >
                                <option <c:choose><c:when test="${questionnaireFormData.dealingWithStaff=='Very Satisfied'}"> selected="selected" </c:when><c:otherwise><c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if></c:otherwise></c:choose>value="Very Satisfied">Very Satisfied</option>
                                <option <c:choose><c:when test="${questionnaireFormData.dealingWithStaff=='Mostly Satisfied'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if></c:otherwise></c:choose>value="Mostly Satisfied">Mostly Satisfied</option>
                                <option <c:choose><c:when test="${questionnaireFormData.dealingWithStaff=='Satisfied'}"> selected="selected" </c:when><c:otherwise><c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if></c:otherwise></c:choose>value="Satisfied">Satisfied</option>
                                <option <c:choose><c:when test="${questionnaireFormData.dealingWithStaff=='Mostly Dissatisfied'}"> selected="selected" </c:when><c:otherwise><c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="Mostly Dissatisfied">Mostly Dissatisfied</option>
                                <option <c:choose>  <c:when test="${questionnaireFormData.dealingWithStaff=='Very Dissatisfied'}"> selected="selected" </c:when><c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if></c:otherwise></c:choose>value="Very Dissatisfied">Very Dissatisfied</option>
                        </select> </div>
                </div>
                <div class="form-row mb-2 form-control-sm">
                    <div class="form-group col-md-8 mt-2  pt-2"> <label>Overall Satisfaction Level</label> </div>
                    <div class="form-group col-md-4 pt-2"> <select class="mdb-select md-form form-control form-control-sm" id="satisfactionLevel" >
                            <option <c:choose> <c:when test="${questionnaireFormData.satisfactionLevel=='Very Satisfied'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if></c:otherwise></c:choose>value="Very Satisfied">Very Satisfied</option>
                            <option <c:choose><c:when test="${questionnaireFormData.satisfactionLevel=='Mostly Satisfied'}"> selected="selected" </c:when><c:otherwise><c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if></c:otherwise></c:choose>value="Mostly Satisfied">Mostly Satisfied</option>
                            <option <c:choose> <c:when test="${questionnaireFormData.satisfactionLevel=='Satisfied'}"> selected="selected" </c:when><c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if></c:otherwise> </c:choose>value="Satisfied">Satisfied</option>
                            <option <c:choose> <c:when test="${questionnaireFormData.satisfactionLevel=='Mostly Dissatisfied'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if></c:otherwise></c:choose>value="Mostly Dissatisfied">Mostly Dissatisfied</option>
                            <option <c:choose> <c:when test="${questionnaireFormData.satisfactionLevel=='Very Dissatisfied'}"> selected="selected" </c:when>  <c:otherwise><c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if></c:otherwise></c:choose>value="Very Dissatisfied">Very Dissatisfied</option>
                        </select> </div>
                </div>
            </div>
            <div class="card-body">
                <div class="col-12 pt-2 font-weight-bold"> Factors affecting departure </div>
                <div class="col-12 pt-2 font-weight-normal">
                    <p>In deciding to leave your most recent position at Trantor, how did each of the following influence your decision? </p>
                </div>
                <div class="form-row mb-2 form-control-sm">
                    <div class="form-group col-md-8 mt-2 "> <label> Please elaborate on your top reason for leaving </label> </div>
                    <div class="form-group col-md-4 pt-2"> <select class="mdb-select md-form form-control form-control-sm" id="reasonForLeaving" >
                            <option <c:choose> <c:when test="${questionnaireFormData.reasonForLeaving=='Better Brand'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="Better Brand">Better Brand </option>
                            <option <c:choose> <c:when test="${questionnaireFormData.reasonForLeaving=='Better Salary'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="Better Salary">Better Salary </option>
                            <option <c:choose> <c:when test="${questionnaireFormData.reasonForLeaving=='Family/Domestic Constraints'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="Family/Domestic Constraints">Family/Domestic Constraints </option>
                            <option <c:choose> <c:when test="${questionnaireFormData.reasonForLeaving=='Getting Married'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="Getting Married">Getting Married </option>
                            <option <c:choose> <c:when test="${questionnaireFormData.reasonForLeaving=='Going Abroad'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="Going Abroad">Going Abroad </option>
                            <option <c:choose> <c:when test="${questionnaireFormData.reasonForLeaving=='Higher Education'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="Higher Education">Higher Education </option>
                            <option <c:choose> <c:when test="${questionnaireFormData.reasonForLeaving=='Job Security'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="Job Security">Job Security </option>
                            <option <c:choose> <c:when test="${questionnaireFormData.reasonForLeaving=='Medical Reasons'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="Medical Reasons">Medical Reasons </option>
                            <option <c:choose> <c:when test="${questionnaireFormData.reasonForLeaving=='Promotion/Career Growth'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="Promotion/Career Growth">Promotion/Career Growth </option>
                            <option <c:choose> <c:when test="${questionnaireFormData.reasonForLeaving=='Relation With Co-Workers'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="Relation With Co-Workers">Relation With Co-Workers </option>
                            <option <c:choose> <c:when test="${questionnaireFormData.reasonForLeaving=='Relation With Supervisor'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="Relation With Supervisor">Relation With Supervisor </option>
                            <option <c:choose> <c:when test="${questionnaireFormData.reasonForLeaving=='Relocation'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="Relocation">Relocation </option>
                            <option <c:choose> <c:when test="${questionnaireFormData.reasonForLeaving=='Training opportunities'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="Training opportunities">Training opportunities </option>
                            <option <c:choose> <c:when test="${questionnaireFormData.reasonForLeaving=='Work Conditions'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="Work Conditions">Work Conditions </option>
                        </select> </div>
                </div>
                <div class="form-row mb-2 form-control-sm">
                    <div class="form-group col-md-8 mt-2 "> <label>Please describe why are you leaving Trantor</label> </div>
                    <div class="form-group col-md-4 pt-2"> <textarea rows="3" cols="49" class="form-control form-control-sm pl-3" style="height: 49px;" maxlength="500"     readonly >${questionnaireFormData.reasonForLeavingDescribe}</textarea> </div>
                </div>
            </div>
            <div class="card-body">
                <div class="col-12 pt-2 font-weight-bold"> Please indicate your response to following </div>
                <div class="form-row mb-2 form-control-sm">
                    <div class="form-group col-md-8 mt-2   pt-2"> <label>1.What first attracted you to join Trantor?  </label> </div>
                    <div class="form-group col-md-4 pt-1"> <textarea rows="3" cols="49" class="form-control form-control-sm pl-3" style="height: 49px;" maxlength="500"   readonly >${questionnaireFormData.reasonForJoining}</textarea> </div>
                </div>
                <div class="form-row mb-2 form-control-sm">
                    <div class="form-group col-md-8 mt-2  pt-2"> <label>2.Would you work for Trantor again in the future </label> </div>
                    <div class="form-group col-md-4 pt-3"> <select class="mdb-select md-form form-control form-control-sm " id="workAgain" >
                            <option <c:choose><c:when test="${questionnaireFormData.workAgain=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="1">Yes </option>
                            <option <c:choose><c:when test="${questionnaireFormData.workAgain=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="2">No </option>
                        </select> </div>
                </div>
                <div class="form-row mb-2 form-control-sm pt-3 ">
                    <div class="form-group col-md-8 mt-2  pt-2"> <label>3.Please let us know the reason why you would not want to work for Trantor again, if indicated "No" in question no 2 above</label> </div>
                    <div class="form-group col-md-4 pt-2"> <textarea rows="3" id="postalAddress" cols="49" class="form-control form-control-sm pl-3" style="height: 49px;" maxlength="500"  readonly >${questionnaireFormData.notWorkAgain}</textarea> </div>
                </div>
                <div class="form-row mb-2 form-control-lg pt-4">
                    <div class="form-group col-md-8 mt-2   pt-2"> <label>4.If a friend asked, would you recommend employment at Trantor?  </label> </div>
                    <div class="form-group col-md-4 pt-3"> <select class="mdb-select md-form form-control form-control-sm " >


                    <option ${questionnaireFormData.recommendTrantor == '1' ? 'selected="selected"': ' style="display:none"  disabled'} value="1">Yes</option>
                    <option ${questionnaireFormData.recommendTrantor == '2' ? 'selected="selected"': ' style="display:none" disabled'} value="2">No</option>

                                                    </select> </div>
                </div>
                <div class="form-row mb-2 form-control-lg pt-4">
                    <div class="form-group col-md-8    "> <label>5.Please let us know the reason why you would not want to recommend Trantor to your friends, if indicated "No" in question no 4 above</label> </div>
                    <div class="form-group col-md-4 "> <textarea rows="3" id="postalAddress" cols="49" class="form-control form-control-sm " style="height: 49px;" maxlength="500"   readonly >${questionnaireFormData.notRecommendTrantor}</textarea> </div>
                </div>
                <div class="form-row mb-2 form-control-lg pt-4">
                    <div class="form-group col-md-8  "> <label>6.What is the name of company you are going to join?   </label> </div>
                    <div class="form-group col-md-4 "> <textarea rows="3" id="postalAddress" cols="49" class="form-control form-control-sm " style="height: 49px;" maxlength="500"   readonly >${questionnaireFormData.companyName}</textarea> </div>
                </div>
                <div class="form-row mb-2 form-control-lg pt-4">
                    <div class="form-group col-md-8  "> <label>7.Please share following details of your new company  </label> </div>
                </div>
                <div class="form-row mb-2 form-control-lg pt-4">
                    <div class="form-group col-md-8  "> <label>I.Designation  </label> </div>
                    <div class="form-group col-md-4 "> <textarea rows="3" id="postalAddress" cols="49" class="form-control form-control-sm " style="height: 49px;" maxlength="500"   readonly >${questionnaireFormData.designation}</textarea> </div>
                </div>
                <div class="form-row mb-2 form-control-lg pt-4">
                    <div class="form-group col-md-8  "> <label>II.Location   </label> </div>
                    <div class="form-group col-md-4 "> <textarea rows="3" id="postalAddress" cols="49" class="form-control form-control-sm " style="height: 49px;" maxlength="500"   readonly >${questionnaireFormData.location}</textarea> </div>
                </div>
                <div class="form-row  form-control-lg">
                    <div class="form-group col-md-8"> <label>III.Salary Hike - Select One </label> </div>
                    <div class="form-group col-md-4  pt-3"> <select class="mdb-select md-form form-control form-control-sm " >
                              <option <c:choose> <c:when test="${questionnaireFormData.salaryHike=='0 to 10 %'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="0 to 10 %">0 to 10 %</option>
                              <option <c:choose> <c:when test="${questionnaireFormData.salaryHike=='10 to 20 %'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="10 to 20 %">10 to 20 %</option>
                              <option <c:choose> <c:when test="${questionnaireFormData.salaryHike=='20 to 30 %'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="20 to 30 %">20 to 30 %</option>
                              <option <c:choose> <c:when test="${questionnaireFormData.salaryHike=='30 to 40 %'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="30 to 40 %">30 to 40 % </option>
                              <option <c:choose> <c:when test="${questionnaireFormData.salaryHike=='More than 40%'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="More than 40%">More than 40%</option>
                        </select> </div>
                </div>
                <div class="form-row form-control-lg pt-3">
                    <div class="form-group col-md-8  "> <label>8.What are your suggestions / feedback on the current processes and functions? </label> </div>
                    <div class="form-group col-md-4 "> <textarea rows="3" id="postalAddress" cols="49" class="form-control form-control-sm " style="height: 49px;" maxlength="500"    readonly >${questionnaireFormData.feedback}</textarea> </div>
                </div>
<div class="form-row form-control-xl pt-4 "></div>
            </div>
    </div>
               <form method="post" action="${hrRemarksSubmissionUrl}" onsubmit="return confirm('Are you sure you want to submit this form ?')">
                                 <input type="hidden" value="${questionnaireFormData.resignationId}"
                                                 name="<portlet:namespace/>resignationId" />
               <div class="row pt-4">
                <div class="col-8 text-center" >
                   <div class="input-group">
                   <div class="col-md-2 mt-1">
                   <label> HR Remarks : </label>
                   </div>
                      <textarea class="form-control form-control-sm"
                         onblur="$(this).val($(this).val().trim())"
                         placeholder="Provide fill answer"
                         style="height: 40px;" rows="2" cols="10" maxlength="500"
                         name="<portlet:namespace/>hrRemarksQues" id="withdrawReasonId" maxlength="499" required <c:if test ="${questionnaireFormData.hrRemarks !=''}">readonly </c:if> >${questionnaireFormData.hrRemarks}</textarea>
                     <c:if test ="${questionnaireFormData.hrRemarks ==''}"> <button class="btn btn-success" >Submit</button> </c:if>

                   </div>
                </div>
               </div>
               </form>
</div>
<script>
    function confirmResignFormSubmission()
    {
    if (confirm('Are you sure you want to submit the resign form to your manager and HR ?')) {
                    document.getElementById("resignForm").action = "<%=submitQuestionnaireFormUrl%>";
                    return true;
                } else {
                    return false;
                }
    }
</script>