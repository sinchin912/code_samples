<%@ include file="/init.jsp"%>
 <portlet:actionURL name="submitQuestionnaireForm" var="submitQuestionnaireFormUrl"></portlet:actionURL>
<portlet:renderURL var="resignationRenderURL" windowState="normal"></portlet:renderURL>
<div class="container">
    <c:set var="pageUrl" value="${fn:split(resignationRenderURL.toString(),'?')}" />
    <div id="exitForm" class="card">
        <div class="card-header">
            <div class="row">
                <div class="col pt-2">
                    <h5>Exit Interview Form</h5>
                </div>
                <div class="col-2 text-right">                <a class="btn btn-outline-secondary" id="reset" href="${pageUrl[0]}"  role="button">
                                                              Cancel</a> </div>
            </div>
        </div>

        <form id="resignForm" action="${submitQuestionnaireFormUrl}" method="post" onSubmit="return confirmResignFormSubmission();">
         <input type="hidden" id="resignationId" value="${empCoreDetails.resignationId}" name="<portlet:namespace/>resignationId" />
            <div class="card-body">
                <div class="row">
                    <div class="col-md-2 mt-1 text-right"> <label>Ecode</label> </div>
                    <div class="col-md-4"> <input type="text" class="form-control-plaintext" value="${empCoreDetails.employeeCode}" readonly /> </div>
                    <div class="col-md-2 mt-1 text-right"> <label>Name</label> </div>
                    <div class="col-md-4"> <input type="text" class="form-control-plaintext" value="${empCoreDetails.employeeName}" readonly /> </div>
                </div>
                <div class="row">
                    <div class="col-md-2 mt-1 text-right"> <label>Manager Name</label> </div>
                    <div class="col-md-4"> <input type="text" class="form-control-plaintext" value="${empCoreDetails.managerName}" readonly /> </div>
                    <div class="col-md-2 mt-1 text-right"> <label>Project</label> </div>
                    <div class="col-md-4"> <input type="text" class="form-control-plaintext" value="${empCoreDetails.account}" readonly /> </div>
                </div>
                <div class="row">
                    <div class="col-md-2 mt-1 text-right"> <label>Mobile</label> </div>
                    <div class="col-md-4"> <input type="text" class="form-control-plaintext" value="${empCoreDetails.empMobNo}" readonly /> </div>
                    <div class="col-md-2 mt-1 text-right"> <label>City</label> </div>
                    <div class="col-md-4"> <input type="text" class="form-control-plaintext" value="${empCoreDetails.cityName}" readonly /> </div>
                </div>
                <div class="row">
                    <div class="col-md-2 mt-1 text-right"> <label>Postal Address</label> </div>
                    <div class="col-md-4"> <textarea rows="3" id="postalAddress" cols="49" class="form-control-sm form-control-plaintext pl-3 border border-secondary" style="height: 49px;" maxlength="500" readonly>${empCoreDetails.postalAddress}</textarea> </div>
                    <div class="col-md-2 mt-1 text-right"> <label>Last Working Day</label> </div>
                    <div class="col-md-4"> <input type="text" class="form-control-plaintext" value="${empCoreDetails.lastWorkingDate}" readonly /> </div>
                </div>
                <div class="form-row mb-2 mt-4 pl-3 pt-3">
                        <p>We, at Trantor are committed to providing a positive work environment to all our employees.<br/></p>
                        <p>The Exit Interview Program provides us with a valuable source of information which helps us in enhancing recruitment and retention efforts and to assess the overall quality of work life at Trantor. <br/></p>
                        <p>Your responses to this questionnaire shall be kept confidential and if required, information in aggregate form only may be shared with managers or supervisors.<br/></p>
                       <p>While we do not require you to provide any of following information, your assistance will help us in our continuous endeavor to provide the best possible work environment for its employees. Therefore, we ask that you take a few minutes to complete the questionnaire below.<br/></p>
                        <p class="col-12">Thank you for your assistance and good luck in your future endeavors.<br/></p>
                         <p><strong>Please make sure that before starting this process : </strong><br/> - You have completed any Induction and/or training feedback form due from you<br/> - Updated LMS<br/> - Downloaded salary slips and income tax.<br/></p>

                </div>
            </div>
            <div class="card-body">
                <div class="col-12 pt-2 font-weight-bold"> Organizational Climate </div>
                <div class="col-12 pt-2 font-weight-normal">
                    <p>Please indicate how you feel about the following by answering below questions applicable to your most recent position at Trantor.</p>
                </div>
                <div class="row mb-2 form-control-sm">
                    <div class="col-md-8 mt-2 pt-2"> <label>1. Overall work experience at Trantor<c:if test="${questionnaireFormData.submitted==false}">*</c:if> </label> </div>
                    <div class="col-md-4 pt-1"> <select class="mdb-select md-form form-control form-control-sm" id="workExperience" required="required" name="<portlet:namespace/>workExperience">
                            <c:if test="${questionnaireFormData.submitted==false}"> <option value="" selected>Choose your option</option> </c:if>
                            <option <c:choose> <c:when test="${questionnaireFormData.workExperience=='Very Satisfied'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if>  </c:otherwise> </c:choose>value="Very Satisfied">Very Satisfied </option>
                            <option <c:choose> <c:when test="${questionnaireFormData.workExperience=='Mostly Satisfied'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="Mostly Satisfied">Mostly Satisfied </option>
                            <option <c:choose> <c:when test="${questionnaireFormData.workExperience=='Satisfied'}"> selected="selected" </c:when><c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if>  </c:otherwise> </c:choose>value="Satisfied">Satisfied </option>
                            <option <c:choose> <c:when test="${questionnaireFormData.workExperience=='Mostly Dissatisfied'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if></c:otherwise></c:choose>value="Mostly Dissatisfied">Mostly Dissatisfied</option>
                            <option <c:choose> <c:when test="${questionnaireFormData.workExperience=='Very Dissatisfied'}"> selected="selected" </c:when><c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="Very Dissatisfied">Very Dissatisfied </option>
                        </select> </div>
                </div>
                <div class="row mb-2 form-control-sm">
                    <div class="col-md-8 mt-2  pt-2"> <label>2. Perception of the Trantor&#039s overall communication with employees and transparency in the system<c:if test="${questionnaireFormData.submitted==false}">*</c:if> </label> </div>
                    <div class="col-md-4 pt-1"> <select class="mdb-select md-form form-control form-control-sm" id="communicationWithEmployees" required="required" name="<portlet:namespace/>communicationWithEmployees">
                       <c:if test="${questionnaireFormData.submitted==false}"> <option value="" selected>Choose your option</option> </c:if>
                       <option <c:choose> <c:when test="${questionnaireFormData.communicationWithEmployees=='Very Satisfied'}"> selected="selected" </c:when><c:otherwise><c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if></c:otherwise></c:choose>value="Very Satisfied">Very Satisfied </option>
                       <option <c:choose> <c:when test="${questionnaireFormData.communicationWithEmployees=='Mostly Satisfied'}"> selected="selected" </c:when><c:otherwise><c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if></c:otherwise> </c:choose>value="Mostly Satisfied">Mostly Satisfied </option>
                       <option <c:choose> <c:when test="${questionnaireFormData.communicationWithEmployees=='Satisfied'}"> selected="selected" </c:when><c:otherwise><c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if></c:otherwise></c:choose>value="Satisfied">Satisfied</option>
                       <option <c:choose> <c:when test="${questionnaireFormData.communicationWithEmployees=='Mostly Dissatisfied'}"> selected="selected" </c:when> <c:otherwise>  <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if></c:otherwise></c:choose>value="Mostly Dissatisfied">Mostly Dissatisfied</option>
                       <option <c:choose>  <c:when test="${questionnaireFormData.communicationWithEmployees=='Very Dissatisfied'}"> selected="selected" </c:when>  <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if></c:otherwise></c:choose>value="Very Dissatisfied">Very Dissatisfied</option>
                        </select> </div>
                </div>
                <div class="row mb-2 form-control-sm">
                    <div class="col-md-8 mt-2 pt-2"> <label>3. The opportunity to enroll in Training courses and workshops<c:if test="${questionnaireFormData.submitted==false}">*</c:if> </label> </div>
                    <div class="col-md-4 pt-2"> <select class="mdb-select md-form form-control form-control-sm" id="trainingOpportunity" required="required" name="<portlet:namespace/>trainingOpportunity">
                           <c:if test="${questionnaireFormData.submitted==false}"> <option value="" selected>Choose your option</option> </c:if>
                           <option <c:choose> <c:when test="${questionnaireFormData.trainingOpportunity=='Very Satisfied'}"> selected="selected" </c:when><c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if></c:otherwise></c:choose>value="Very Satisfied">Very Satisfied</option>
                           <option <c:choose><c:when test="${questionnaireFormData.trainingOpportunity=='Mostly Satisfied'}"> selected="selected" </c:when><c:otherwise><c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if></c:otherwise> </c:choose>value="Mostly Satisfied">Mostly Satisfied</option>
                           <option <c:choose><c:when test="${questionnaireFormData.trainingOpportunity=='Satisfied'}"> selected="selected" </c:when><c:otherwise><c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if></c:otherwise></c:choose>value="Satisfied">Satisfied</option>
                           <option <c:choose> <c:when test="${questionnaireFormData.trainingOpportunity=='Mostly Dissatisfied'}"> selected="selected" </c:when><c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise>  </c:choose>value="Mostly Dissatisfied">Mostly Dissatisfied</option>
                           <option <c:choose><c:when test="${questionnaireFormData.trainingOpportunity=='Very Dissatisfied'}"> selected="selected" </c:when><c:otherwise><c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="Very Dissatisfied">Very Dissatisfied</option>
                        </select> </div>
                </div>
                <div class="row mb-2 form-control-sm">
                    <div class="col-md-8 mt-2  pt-2"> <label>4. Your perception of the Trantor&#039s ability to deal fairly with staff<c:if test="${questionnaireFormData.submitted==false}">*</c:if></label> </div>
                    <div class="col-md-4 pt-2"> <select class="mdb-select md-form form-control form-control-sm" id="dealingWithStaff" required="required" name="<portlet:namespace/>dealingWithStaff">
                           <c:if test="${questionnaireFormData.submitted==false}"> <option value="" selected>Choose your option</option> </c:if>
                                <option <c:choose><c:when test="${questionnaireFormData.dealingWithStaff=='Very Satisfied'}"> selected="selected" </c:when><c:otherwise><c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if></c:otherwise></c:choose>value="Very Satisfied">Very Satisfied</option>
                                <option <c:choose><c:when test="${questionnaireFormData.dealingWithStaff=='Mostly Satisfied'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if></c:otherwise></c:choose>value="Mostly Satisfied">Mostly Satisfied</option>
                                <option <c:choose><c:when test="${questionnaireFormData.dealingWithStaff=='Satisfied'}"> selected="selected" </c:when><c:otherwise><c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if></c:otherwise></c:choose>value="Satisfied">Satisfied</option>
                                <option <c:choose><c:when test="${questionnaireFormData.dealingWithStaff=='Mostly Dissatisfied'}"> selected="selected" </c:when><c:otherwise><c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="Mostly Dissatisfied">Mostly Dissatisfied</option>
                                <option <c:choose>  <c:when test="${questionnaireFormData.dealingWithStaff=='Very Dissatisfied'}"> selected="selected" </c:when><c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if></c:otherwise></c:choose>value="Very Dissatisfied">Very Dissatisfied</option>
                        </select> </div>
                </div>
                <div class="row mb-2 form-control-sm">
                    <div class="col-md-8 mt-2  pt-2"> <label>5. Overall Satisfaction Level<c:if test="${questionnaireFormData.submitted==false}">*</c:if></label> </div>
                    <div class="col-md-4 pt-2"> <select class="mdb-select md-form form-control form-control-sm" id="satisfactionLevel" required="required" name="<portlet:namespace/>satisfactionLevel">
                            <c:if test="${questionnaireFormData.submitted==false}"> <option value="" selected>Choose your option</option> </c:if>
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
                    <div class="form-group col-md-8 mt-2 "> <label>1. Please elaborate on your top reason for leaving<c:if test="${questionnaireFormData.submitted==false}">*</c:if></label> </div>
                    <div class="form-group col-md-4 pt-2"> <select class="mdb-select md-form form-control form-control-sm" id="reasonForLeaving" required="required" name="<portlet:namespace/>reasonForLeaving">
                             <c:if test="${questionnaireFormData.submitted==false}">  <option value="" disabled selected>Choose your option</option>   </c:if>
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
                    <div class="form-group col-md-8 mt-2 "> <label>2. Please describe why are you leaving Trantor<c:if test="${questionnaireFormData.submitted==false}">*</c:if></label> </div>
                    <div class="form-group col-md-4 pt-2"> <textarea rows="3" cols="49" class="form-control form-control-sm pl-3" style="height: 49px;"  onblur="$(this).val($(this).val().trim())"  required name="<portlet:namespace/>reasonForLeavingDescribe" maxlength="198" <c:if test="${questionnaireFormData.submitted==true}"> readonly</c:if> >${questionnaireFormData.reasonForLeavingDescribe}</textarea> </div>
                </div>
            </div>
            <div class="card-body">
                <div class="col-12 pt-2 font-weight-bold"> Please indicate your response to following </div>
                <div class="row mb-2 form-control-sm">
                    <div class="col-md-8 mt-2   pt-2"> <label>1. What first attracted you to join Trantor? <c:if test="${questionnaireFormData.submitted==false}">*</c:if></label> </div>
                    <div class="col-md-4 pt-1"> <textarea rows="3" cols="49" class="form-control form-control-sm pl-3" style="height: 49px;" onblur="$(this).val($(this).val().trim())" required name="<portlet:namespace/>reasonForJoining" maxlength="198" <c:if test="${questionnaireFormData.submitted==true}"> readonly</c:if> >${questionnaireFormData.reasonForJoining}</textarea> </div>
                </div>
                <div class="row mb-2 form-control-sm">
                    <div class="col-md-8 mt-2  pt-2"> <label>2. Would you work for Trantor again in the future<c:if test="${questionnaireFormData.submitted==false}">*</c:if></label> </div>
                    <div class="col-md-4 pt-3"> <select class="mdb-select md-form form-control form-control-sm " id="workAgain" required="required" name="<portlet:namespace/>workAgain">
                        <c:if test="${questionnaireFormData.submitted==false}">  <option value="" disabled selected>Choose your option</option> </c:if>
                              <option <c:choose> <c:when test="${questionnaireFormData.workAgain=='1'}"> selected="selected" </c:when><c:otherwise><c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                              <option <c:choose> <c:when test="${questionnaireFormData.workAgain=='2'}"> selected="selected" </c:when><c:otherwise><c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="2">No</option>
                        </select> </div>
                </div>
                <div class="form-row mb-2 form-control-sm pt-3 ">
                    <div class="col-md-8 mt-2  pt-2"> <label>3. Please let us know the reason why you would not want to work for Trantor again, if indicated "No" in question no 2 above</label> </div>
                    <div class="col-md-4 pt-2"> <textarea rows="3" id="notWorkAgain" cols="49" class="form-control form-control-sm pl-3" style="height: 49px;"  onblur="$(this).val($(this).val().trim())" maxlength="198" required name="<portlet:namespace/>notWorkAgain" <c:if test="${questionnaireFormData.submitted==true}"> readonly</c:if> >${questionnaireFormData.notWorkAgain}</textarea> </div>
                </div>
                <div class="row mb-2 form-control-lg pt-4">
                    <div class="col-md-8 mt-2   pt-2"> <label>4. If a friend asked, would you recommend employment at Trantor? <c:if test="${questionnaireFormData.submitted==false}">*</c:if></label> </div>
                    <div class="col-md-4 pt-3"> <select class="mdb-select md-form form-control form-control-sm " id="recommendTrantor" required="required" name="<portlet:namespace/>recommendTrantor">
                            <c:if test="${questionnaireFormData.submitted==false}">  <option value="" disabled selected>Choose your option</option> </c:if>
                            <option <c:choose>  <c:when test="${questionnaireFormData.recommendTrantor=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="1">Yes </option>
                            <option <c:choose><c:when test="${questionnaireFormData.recommendTrantor=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                            </select> </div>
                </div>
                <div class="row mb-2 form-control-lg pt-4">
                    <div class="col-md-8    "> <label>5. Please let us know the reason why you would not want to recommend Trantor to your friends, if indicated "No" in question no 4 above</label> </div>
                    <div class="col-md-4 "> <textarea rows="3" id="notRecommendTrantor" cols="49" class="form-control form-control-sm " style="height: 49px;"  onblur="$(this).val($(this).val().trim())" maxlength="198" required name="<portlet:namespace/>notRecommendTrantor" <c:if test="${questionnaireFormData.submitted==true}"> readonly</c:if> >${questionnaireFormData.notRecommendTrantor}</textarea> </div>
                </div>
                <div class="row mb-2 form-control-lg pt-4">
                    <div class="col-md-8  "> <label>6. What is the name of company you are going to join? <c:if test="${questionnaireFormData.submitted==false}">*</c:if> </label> </div>
                    <div class="col-md-4 "> <textarea rows="3" id="postalAddress" cols="49" class="form-control form-control-sm " style="height: 49px;"  onblur="$(this).val($(this).val().trim())" maxlength="74" required name="<portlet:namespace/>companyName" <c:if test="${questionnaireFormData.submitted==true}"> readonly</c:if> >${questionnaireFormData.companyName}</textarea> </div>
                </div>
                <div class="row mb-2 form-control-lg pt-4">
                    <div class="col-md-8  "> <label>7. Please share following details of your new company</label> </div>
                </div>
                <div class="row mb-2 form-control-lg pt-4">
                    <div class="col-md-8  "> <label>I. Designation<c:if test="${questionnaireFormData.submitted==false}">*</c:if> </label> </div>
                    <div class=" col-md-4 "> <textarea rows="3" id="postalAddress" cols="49" class="form-control form-control-sm " style="height: 49px;"  onblur="$(this).val($(this).val().trim())" maxlength="74" required name="<portlet:namespace/>designation" <c:if test="${questionnaireFormData.submitted==true}"> readonly</c:if> >${questionnaireFormData.designation}</textarea> </div>
                </div>
                <div class="row mb-2 form-control-lg pt-4">
                    <div class="col-md-8  "> <label>II. Location<c:if test="${questionnaireFormData.submitted==false}">*</c:if> </label> </div>
                    <div class=" col-md-4 "> <textarea rows="3" id="postalAddress" cols="49" class="form-control form-control-sm " style="height: 49px;"  onblur="$(this).val($(this).val().trim())" maxlength="74" required name="<portlet:namespace/>location" <c:if test="${questionnaireFormData.submitted==true}"> readonly</c:if> >${questionnaireFormData.location}</textarea> </div>
                </div>
                <div class="row  form-control-lg">
                    <div class="col-md-8"> <label>III. Salary Hike - Select One<c:if test="${questionnaireFormData.submitted==false}">*</c:if></label> </div>
                    <div class="col-md-4  pt-3"> <select class="mdb-select md-form form-control form-control-sm " required="required" name="<portlet:namespace/>salaryHike">
                            <c:if test="${questionnaireFormData.submitted==false}">  <option value="" disabled selected>Choose your option</option> </c:if>
                              <option <c:choose> <c:when test="${questionnaireFormData.salaryHike=='0 to 10 %'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="0 to 10 %">0 to 10 %</option>
                              <option <c:choose> <c:when test="${questionnaireFormData.salaryHike=='10 to 20 %'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="10 to 20 %">10 to 20 %</option>
                              <option <c:choose> <c:when test="${questionnaireFormData.salaryHike=='20 to 30 %'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="20 to 30 %">20 to 30 %</option>
                              <option <c:choose> <c:when test="${questionnaireFormData.salaryHike=='30 to 40 %'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="30 to 40 %">30 to 40 % </option>
                              <option <c:choose> <c:when test="${questionnaireFormData.salaryHike=='More than 40%'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${questionnaireFormData.submitted==true}"> style="display:none" disabled</c:if> </c:otherwise> </c:choose>value="More than 40%">More than 40%</option>
                        </select> </div>
                </div>
                <div class="row form-control-lg pt-3">
                    <div class="col-md-8  "> <label>8. What are your suggestions / feedback on the current processes and functions? </label> </div>
                    <div class=" col-md-4 "> <textarea rows="3" id="postalAddress" cols="49" class="form-control form-control-sm " style="height: 49px;"  onblur="$(this).val($(this).val().trim())" maxlength="74" required name="<portlet:namespace/>feedback" <c:if test="${questionnaireFormData.submitted==true}"> readonly</c:if> >${questionnaireFormData.feedback}</textarea> </div>
                </div>
                <div class="row form-control-xl pt-4 ">
                    <c:if test="${questionnaireFormData.submitted==false}">
                        <div class="col-12 text-center"> <button id="submitButtonId" class="btn btn-primary">Submit</button> </div>
                    </c:if>
                </div>
            </div>
        </form>
    </div>
</div>
<script>

</script>