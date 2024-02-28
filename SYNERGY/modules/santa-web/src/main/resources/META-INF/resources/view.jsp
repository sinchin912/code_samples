<%@ include file="/init.jsp" %>

<portlet:actionURL name="submitRegistrationForm" var="submitRegistrationFormUrl"></portlet:actionURL>
<portlet:actionURL name="submitAllocationForm" var="submitAllocationFormUrl"></portlet:actionURL>
<portlet:actionURL name="submitGameForm" var="submitGameFormUrl"></portlet:actionURL>
<portlet:actionURL name="submitSendEmailForm" var="submitSendEmailFormUrl"></portlet:actionURL>

<div class="container">
   <div class="card">
      <div class="card-header">
         <div class="row">
             <div class="col pt-1">
                <h5>Secret Santa ${currentYear}</h5>
             </div>
        </div>
      </div>
      <c:choose>
            <c:when test="${mode == 'preOpen'}">
            <div class="card-body">
               <div class="row">
                  <div class="col pt-1">
                     <h6>Secret Santa is expected to open near X-Mas. Tune back to Synergy once deadline opens.</h6>
                  </div>
               </div>
            </div>
            </c:when>
            <c:when test="${mode == 'postClose'}">
            <div class="card-body">
               <div class="row">
                  <div class="col pt-1">
                     <h6>Deadlines for Secret Santa has already closed. Please come back next year once deadline opens.</h6>
                  </div>
               </div>
            </div>
            </c:when>
            <c:when test="${mode == 'registration'}">
            <div class="card-body">
                  <form id="registrationForm" method="post" action="${submitRegistrationFormUrl}" onSubmit="return confirmRegistration();">
                     <div class="row">
                        <div class="col-md-2 mt-1 text-right">
                           <label>Ecode</label>
                        </div>
                        <div class="col-md-4">
                           <input type="text" id="ecode" class="form-control-sm form-control-plaintext pl-3 " value="${registrationModel.employeeCode}" readonly />
                        </div>
                        <div class="col-md-2 mt-1 text-right">
                           <label>Name</label>
                        </div>
                        <div class="col-md-4">
                           <input type="text" id="name" class="form-control-sm form-control-plaintext  pl-3" value="${registrationModel.employeeName}" readonly />
                        </div>
                     </div>
                     <div class="row">
                        <div class="col-md-2 mt-1 text-right">
                           <label>
                              Mobile
                              <c:if test="${registrationModel.pageMode =='edit'}">*</c:if>
                           </label>
                        </div>
                        <div class="col-md-4">
                           <input type="text" id="mobile"
                           minlength="10" maxlength="10"
                           oninvalid="this.setCustomValidity('Please enter 10 digits mobile number')"
                           oninput="this.setCustomValidity('')"
                           pattern="^[0-9]*$"
                           <c:choose>
                              <c:when test="${registrationModel.pageMode =='view'}">class="form-control-sm form-control-plaintext pl-3" readonly</c:when>
                              <c:otherwise>class="form-control form-control-sm" required</c:otherwise>
                           </c:choose>
                           value="${registrationModel.employeeMobile}" name="<portlet:namespace/>mobile"/>
                        </div>
                        <div class="col-md-2 mt-1 text-right">
                           <label>Account</label>
                        </div>
                        <div class="col-md-4">
                           <input type="text" id="accountName" class="form-control-sm form-control-plaintext  pl-3" value="${registrationModel.account}"  readonly />
                        </div>
                     </div>
                     <div class="row pt-2">
                        <div class="col-md-2 mt-1 text-right">
                           <label>
                              City
                              <c:if test="${registrationModel.pageMode=='edit'}">*</c:if>
                           </label>
                        </div>
                        <div class="col-md-4">
                           <input type="text"  id="city" maxlength="75"
                           <c:choose>
                              <c:when test="${registrationModel.pageMode=='view'}">class="form-control-sm form-control-plaintext pl-3" value="${registrationModel.cityName}" readonly</c:when>
                              <c:otherwise>class="form-control form-control-sm" value="" required</c:otherwise>
                           </c:choose>
                           name="<portlet:namespace/>city"  onblur="$(this).val($(this).val().trim())"/>
                        </div>
                        <div class="col-md-2 mt-1 text-right">
                           <label>
                              Pincode
                              <c:if test="${registrationModel.pageMode=='edit'}">*</c:if>
                           </label>
                        </div>
                        <div class="col-md-4">
                           <input type="text" id="pincode"  minlength="6" maxlength="6"
                           oninvalid="this.setCustomValidity('Please enter 6 digits for pincode')"
                           oninput="this.setCustomValidity('')"
                           pattern="^[0-9]*$"
                           <c:choose>
                              <c:when test="${registrationModel.pageMode=='view'}">class="form-control-sm form-control-plaintext  pl-3" value="${registrationModel.pincode}" readonly</c:when>
                              <c:otherwise>class="form-control form-control-sm" value="" required</c:otherwise>
                           </c:choose>
                           name="<portlet:namespace/>pincode"/>
                        </div>
                     </div>
                     <div class="row pt-2">
                        <div class="col-md-2 mt-1 text-right">
                           <label>
                              State
                              <c:if test="${registrationModel.pageMode=='edit'}">*</c:if>
                           </label>
                        </div>
                        <div class="col-md-4">
                           <select class="mdb-select md-form form-control form-control-sm" id="state" required="required" name="<portlet:namespace/>state">
                              <c:if test="${registrationModel.pageMode=='edit'}">
                                 <option value="" selected>Choose your option</option>
                              </c:if>
                              <c:forEach items="${registrationModel.states}" var="stateName" varStatus="count">
                                 <option
                                 <c:if test="${registrationModel.pageMode=='view'}">
                                    <c:choose>
                                       <c:when test="${registrationModel.stateName==stateName}"> selected="selected" </c:when>
                                       <c:otherwise> style="display:none" disabled</c:otherwise>
                                    </c:choose>
                                 </c:if>
                                 value="${stateName}">${stateName}</option>
                              </c:forEach>
                           </select>
                        </div>
                        <div class="col-md-2 mt-1 text-right">
                           <label>
                              Postal Address
                              <c:if test="${registrationModel.pageMode=='edit'}">*</c:if>
                           </label>
                        </div>
                        <div class="col-md-4">
                           <textarea rows="3"  style="height: 69px;" maxlength="500" onblur="$(this).val($(this).val().trim())"
                           <c:choose>
                              <c:when test="${registrationModel.pageMode=='view'}">class="form-control-sm form-control-plaintext pl-3" readonly</c:when>
                              <c:otherwise>class="form-control form-control-sm" required</c:otherwise>
                           </c:choose>
                           id="postalAddress" cols="47" onblur="$(this).val($(this).val().trim())"
                           name="<portlet:namespace/>postalAddress"><c:if test="${registrationModel.pageMode=='view'}">${registrationModel.postalAddress}</c:if></textarea>
                        </div>
                     </div>
                     <div class="pt-3 pl-1" style="font-size:1.0em;">
                        <strong>Terms & Conditions :</strong>
                        <ul>
                           <li>Santa will be randomly assigned to all participating employees.</li>
                           <li>Cost of gift you will send as Secret Santa should be minimum &#8377;200 and maximum &#8377;500.</li>
                           <li>There will be no reimbursement of this amount, this is in the spirit of X-Mas gifting.</li>
                           <li>If you are Secret Santa of someone, you should order gift for employee assigned to you by <u>${registrationModel.giftDispatchDate}</u> so that it reaches on time.</li>
                           <li>Once you have registered, participation is mandatory. In case of back out, we will send gift coupon of &#8377;500 on your behalf and deduct the same from your salary.</li>
                           <li>In case of any issues, HR decision will be final.</li>
                           <li>Registration ends on EOD of ${registrationModel.registrationEndDate}.</li>
                           <li>Secret Santa Allocation will happen on ${registrationModel.allocationDate}.</li>
                           <li>Secret Santa has to ensure gifts are sent and acknowledge on synergy that gift has been sent is to be done by ${registrationModel.allocationEndDate}.</li>
                           <li>Guess who is your secret Santa from ${registrationModel.gameDate} to ${registrationModel.gameEndDate}</li>
                        </ul>
                     </div>
                     <c:choose>
                        <c:when test="${registrationModel.pageMode=='edit'}">
                           <div class="custom-control custom-checkbox ml-2" id="ackDiv">
                              <input type="checkbox" class="custom-control-input" id="terms" required  oninvalid="this.setCustomValidity('Kindly information this to proceed')"
                                 oninput="this.setCustomValidity('')" />
                              <label  for="terms" class="custom-control-label pl-2" style="font-size:0.8em;">
                              I provide consent to share my contact information with my Secret Santa. Also, I confirm that I will not misuse contact info of employee shared with me.
                              If I do, disciplinary action may be taken against me.</label>
                           </div>
                           <div class="row pt-4" >
                              <div class="col-12 text-center">
                                 <button class="btn btn-primary" >Register</button>
                              </div>
                           </div>
                        </c:when>
                        <c:otherwise>
                           <h6 class="text-center text-primary">Thank you for registering in Secret Santa game. Please visit Synergy on ${registrationModel.allocationDate} for further updates.</h6>
                        </c:otherwise>
                     </c:choose>
                  </form>
            </div>
            </c:when>
            <c:when test="${mode == 'allocation'}">
            <div class="card-body">
                 <h6>You are Secret Santa for following employee. Keep this secret !!</h6>
                  <div class="row pt-3">
                     <div class="col-md-2 mt-1 text-right">
                        <label>Ecode</label>
                     </div>
                     <div class="col-md-4">
                        <input type="text" id="ecode" class="form-control-sm form-control-plaintext" value="${allocationModel.secretSantaEcode}"  readonly />
                     </div>
                     <div class="col-md-2 mt-1 text-right">
                        <label>Name</label>
                     </div>
                     <div class="col-md-4">
                        <input type="text" id="name" class="form-control-sm form-control-plaintext" value="${allocationModel.secretSantaName}" readonly />
                     </div>
                  </div>
                  <div class="row">
                     <div class="col-md-2 mt-1 text-right">
                        <label>Mobile</label>
                     </div>
                     <div class="col-md-4">
                        <input type="text" id="mobile" class="form-control-sm form-control-plaintext" value="${allocationModel.employeeMobile}" readonly />
                     </div>
                     <div class="col-md-2 mt-1 text-right">
                        <label>Account</label>
                     </div>
                     <div class="col-md-4">
                        <input type="text" id="projectName" class="form-control-sm form-control-plaintext" value="${allocationModel.account}"  readonly />
                     </div>
                  </div>
                  <div class="row">
                     <div class="col-md-2 mt-1 text-right">
                        <label>City</label>
                     </div>
                     <div class="col-md-4">
                        <input type="text" class="form-control-sm form-control-plaintext" value="${allocationModel.cityName}" readonly />
                     </div>
                     <div class="col-md-2 mt-1 text-right">
                        <label>State</label>
                     </div>
                     <div class="col-md-4">
                        <input type="text" class="form-control-sm form-control-plaintext" value="${allocationModel.stateName}" readonly />
                     </div>
                  </div>
                  <div class="row">
                     <div class="col-md-2 mt-1 text-right">
                        <label>Pincode</label>
                     </div>
                     <div class="col-md-4">
                        <input type="text" class="form-control-sm form-control-plaintext" value="${allocationModel.pincode}" readonly />
                     </div>
                     <div class="col-md-2 mt-1 text-right">
                        <label>Postal Address</label>
                     </div>
                     <div class="col-md-4">
                        <textarea rows="3" class="form-control-sm form-control-plaintext" readonly style="height: 69px;" cols="47">${allocationModel.postalAddress}</textarea>
                     </div>
                  </div>
                  <div class="pt-3 pl-1" style="font-size:1.0em;">
                    <strong>Terms & Conditions :</strong>
                    <ul>
                      <li>Santa will be randomly assigned to all participating employees.</li>
                      <li>Cost of gift you will send as Secret Santa should be minimum &#8377;200 and maximum &#8377;500.</li>
                      <li>There will be no reimbursement of this amount, this is in the spirit of X-Mas gifting.</li>
                      <li>If you are Secret Santa of someone, you should order gift for employee assigned to you by <u>${allocationModel.giftDispatchDate}</u> so that it reaches on time.</li>
                      <li>Once you have registered, participation is mandatory. In case of back out, we will send gift coupon of &#8377;500 on your behalf and deduct the same from your salary.</li>
                      <li>In case of any issues, HR decision will be final.</li>
                      <li>Registration ends on EOD of ${allocationModel.registrationEndDate}.</li>
                      <li>Secret Santa Allocation will happen on ${allocationModel.allocationDate}.</li>
                      <li>Secret Santa has to ensure gifts are sent and acknowledge on synergy that gift has been sent is done by ${allocationModel.allocationEndDate}.</li>
                      <li>Guess who is your secret Santa from ${allocationModel.gameDate} to ${allocationModel.gameEndDate}</li>
                    </ul>
                  </div>
                  <c:choose>
                     <c:when test="${allocationModel.pageMode=='edit'}">
                        <form method="post" id="allocationForm"  onSubmit="return confirmAllocation();" action="${submitAllocationFormUrl}">
                           <div class="custom-control custom-checkbox">
                              <input type="checkbox" class="custom-control-input" id="gifts" required  oninvalid="this.setCustomValidity('Kindly acknowledge this to proceed')"
                                 oninput="this.setCustomValidity('')" />
                              <label for="gifts" class="custom-control-label pl-2" style="font-size:0.8em;">
                              I confirm that I have sent Xmas gift to above assigned employee.</label>
                           </div>
                           <div class="row pt-4" >
                              <div class="col-12 text-center">
                                 <button class="btn btn-primary">Submit</button>
                              </div>
                           </div>
                        </form>
                     </c:when>
                     <c:otherwise>
                        <h6 class="text-center text-primary pt-1"> Hurray! You have played your Secret Santa role. Please visit Synergy on ${allocationModel.gameDate} for further updates.</h6>
                     </c:otherwise>
                  </c:choose>
            </div>
            </c:when>
            <c:when test="${mode == 'game'}">
               <div class="card-body">
                  <form id="gameForm" method="post"  action="${submitGameFormUrl}" onSubmit="return confirmGame();" enctype="multipart/form-data">
                     <div class="form-row">
                        <div class="form-group col-md-6 mt-1" id="uploadZipDiv">
                           <div class="custom-file">
                              <input type="file" class="custom-file-input" id="uploadPic" required="required"
                                 accept="image/png,image/jpeg,image/jpg" name="<portlet:namespace/>uploadPic"
                                 placeholder="Please upload your picture with gift received" />
                              <label class="custom-file-label" for="uploadPic">Choose file</label>
                           </div>
                           <div>
                              <p>
                                 <small>Please upload your pic with gift received</small>
                              </p>
                           </div>
                        </div>
                        <div class="form-group col-md-2 mt-2 text-right">
                           <label>My Secret Santa was</label>
                        </div>
                        <div class="form-group col-md-4">
                           <select class="mdb-select md-form form-control form-control-sm" id="guessedEcodeId" required="required" name="<portlet:namespace/>guessedEcode">
                              <option value="" disabled selected>Choose your option</option>
                              <c:forEach items="${gameModel.santaOptions}" var="item" varStatus="count">
                                 <option value="${item.ecode}">${item.name} (${item.account})</option>
                              </c:forEach>
                           </select>
                        </div>
                     </div>
                  <div class="pt-3 pl-1" style="font-size:1.0em;">
                    <strong>Terms & Conditions :</strong>
                    <ul>
                      <li>Santa will be randomly assigned to all participating employees.</li>
                      <li>Cost of gift you will send as Secret Santa should be minimum &#8377;200 and maximum &#8377;500.</li>
                      <li>There will be no reimbursement of this amount, this is in the spirit of X-Mas gifting.</li>
                      <li>If you are Secret Santa of someone, you should order gift for employee assigned to you by <u>${gameModel.giftDispatchDate}</u> so that it reaches on time.</li>
                      <li>Once you have registered, participation is mandatory. In case of back out, we will send gift coupon of &#8377;500 on your behalf and deduct the same from your salary.</li>
                      <li>In case of any issues, HR decision will be final.</li>
                      <li>Registration ends on EOD of ${gameModel.registrationEndDate}.</li>
                      <li>Secret Santa Allocation will happen on ${gameModel.allocationDate}.</li>
                      <li>Secret Santa has to ensure gifts are sent and acknowledge on synergy that gift has been sent is done by ${gameModel.allocationEndDate}.</li>
                      <li>Guess who is your secret Santa from ${gameModel.gameDate} to ${gameModel.gameEndDate}</li>
                    </ul>
                  </div>
                     <div class="row pt-4" >
                        <div class="col-12 text-center">
                           <button class="btn btn-primary">Submit</button>
                        </div>
                     </div>
                  </form>
               </div>
            </c:when>
            <c:when test="${mode == 'result'}">
               <div class="card-body">
                  <form id="sendMailForm" method="post" action="${submitSendEmailFormUrl}" onSubmit="return confirmSendEmailForm();">
                     <div class="row">
                        <div class="col-6">
                            <c:choose>
                            <c:when test="${resultModel.employeeGiftPic == ''}">
                               <a href="<%=request.getContextPath()%>/images/santa.png" data-lightbox="roadtrip">
                               <img style="height:300px" class="card-img-top" src="<%=request.getContextPath()%>/images/santa.png" alt="Gift Picture">
                               </a>
                            </c:when>
                            <c:otherwise>
                               <a href="data:image/jpeg;base64,${resultModel.employeeGiftPic}" data-lightbox="roadtrip">
                               <img style="height:300px" class="card-img-top" src="data:image/jpeg;base64,${resultModel.employeeGiftPic}" alt="Gift Picture">
                               </a>
                            </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="col-6">
                           <div class="form-row mb-2 form-control-sm">
                              <c:choose>
                                 <c:when test="${resultModel.guessedCorrect == null}">
                                    <label>
                                       Hey! Your Secret Santa is ${resultPageModel.secretSantaName}.
                                       <c:if test="${resultModel.emailSent=='false'}"><br>Now Send your thank you note to your Secret Santa to make them feel special.</c:if>
                                    </label>
                                 </c:when>
                                 <c:otherwise>
                                    <c:choose>
                                       <c:when test="${resultModel.guessedCorrect}">
                                          <label>
                                             Hey! you guessed it correctly! Your Secret Santa is ${resultModel.secretSantaName}.
                                             <c:if test="${resultModel.emailSent=='false'}"><br>Now Send your thank you note to your Secret Santa to make them feel special.</c:if>
                                          </label>
                                       </c:when>
                                       <c:otherwise>
                                          <label>
                                             Oooooops! You guessed it wrong! Your Secret Santa is ${resultModel.secretSantaName}.
                                             <c:if test="${resultModel.emailSent=='false'}"><br>Now Send your thank you note to your Secret Santa to make them feel special.</c:if>
                                          </label>
                                       </c:otherwise>
                                    </c:choose>
                                 </c:otherwise>
                              </c:choose>
                              <c:if test="${resultModel.emailSent=='false'}">
                                 <button class="btn btn-primary mt-3 float-center">Send Thank you Email</button>
                              </c:if>
                           </div>
                        </div>
                     </div>
                  <div class="pt-3 pl-1" style="font-size:1.0em;">
                    <strong>Terms & Conditions :</strong>
                    <ul>
                      <li>Santa will be randomly assigned to all participating employees.</li>
                      <li>Cost of gift you will send as Secret Santa should be minimum &#8377;200 and maximum &#8377;500.</li>
                      <li>There will be no reimbursement of this amount, this is in the spirit of X-Mas gifting.</li>
                      <li>If you are Secret Santa of someone, you should order gift for employee assigned to you by <u>${resultModel.giftDispatchDate}</u> so that it reaches on time.</li>
                      <li>Once you have registered, participation is mandatory. In case of back out, we will send gift coupon of &#8377;500 on your behalf and deduct the same from your salary.</li>
                      <li>In case of any issues, HR decision will be final.</li>
                      <li>Registration ends on EOD of ${resultModel.registrationEndDate}.</li>
                      <li>Secret Santa Allocation will happen on ${resultModel.allocationDate}.</li>
                      <li>Secret Santa has to ensure gifts are sent and acknowledge on synergy that gift has been sent is done by ${resultModel.allocationEndDate}.</li>
                      <li>Guess who is your secret Santa from ${resultModel.gameDate} to ${resultModel.gameEndDate}</li>
                    </ul>
                  </div>
                  </form>
               </div>
            </c:when>

            <c:otherwise>
            </c:otherwise>
      </c:choose>
   </div>
</div>