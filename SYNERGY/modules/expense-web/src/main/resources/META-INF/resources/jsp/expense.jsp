<%@ include file="/init.jsp" %>
<portlet:renderURL var="expenseRenderURL" windowState="normal"></portlet:renderURL>
<portlet:actionURL name="submitExpense" var="submitExpenseUrl"></portlet:actionURL>
<portlet:resourceURL var="downloadExpenseBillUrl" id="downloadExpenseBill"></portlet:resourceURL>
<div class="container">

    <c:set var="pageUrl" value="${fn:split(expenseRenderURL.toString(),'?')}" />
    <div  class="card">
        <div class="card-header">
            <div class="row">
                <div class="col">
                    <h5>Expense Form</h5>
                </div>
                <div class="col-2 text-right">
                    <a class="btn btn-outline-secondary" id="reset" href="${pageUrl[0]}"  role="button">
                     Cancel</a>
                </div>
            </div>
        </div>
        <div class="card-body">
            <form onsubmit="return confirmSubmitDetails()" action="${submitExpenseUrl}" id="expenseForm" method="post" <c:if test="${empExpenseDetails.expenseType != 'Child Birth Gift Card' && empExpenseDetails.expenseType != 'Marriage Gift Card'}"> enctype="multipart/form-data" </c:if>  >
                <input type="hidden" id="totalRows" value="" name="<portlet:namespace/>totalRows"  />
                <input type="hidden" id="downloadExpenseBillResourceUrl" value="${downloadExpenseBillUrl}"/>
               <input type="hidden" id="portletNamespace"   value="<portlet:namespace/>" />
               <input type="hidden" id="expenseId" value="${empExpenseDetails.expenseId}" name="<portlet:namespace/>expenseId" />
               <input type="hidden" id="fileId" value="${empExpenseDetails.fileId}"  />
               <input type="hidden" id="fyStartDate" value="${empExpenseDetails.fyStartDate}"/>
               <input type="hidden" id="fyEndDate" value="${empExpenseDetails.fyEndDate}"  />
                <div class="form-row mb-2 form-control-sm">
                    <div class="form-group col-md-2 mt-1 text-right">
                        <label>E-Code  </label>
                    </div>
                    <div class="form-group col-md-4">
                        <input type="text" class="form-control-sm form-control-plaintext pl-2  p-1 " value="${empExpenseDetails.ecode}" name="<portlet:namespace/>ecode"  readonly />
                    </div>
                    <div class="form-group col-md-2 mt-1 text-right">
                        <label>Name  </label>
                    </div>
                    <div class="form-group col-md-4">
                        <input type="text" id="name" class="form-control-sm form-control-plaintext pl-2  p-1 " value="${empExpenseDetails.name}" name="<portlet:namespace/>name" readonly />
                    </div>
                </div>
                <div class="form-row mb-2 form-control-sm">
                    <div class="form-group col-md-2 mt-1 text-right">
                        <label>Band</label>
                    </div>
                    <div class="form-group col-md-4">
                        <input type="text"  class="form-control-sm form-control-plaintext pl-2  p-1 " value="${empExpenseDetails.band}" readonly />
                    </div>
                    <div class="form-group col-md-2 mt-1 text-right">
                        <label>Account Name</label>
                    </div>
                    <div class="form-group col-md-4">
                        <input type="text"  class="form-control-sm form-control-plaintext pl-2  p-1 " value="${empExpenseDetails.account}"  readonly />
                    </div>
                </div>
                <div class="form-row mb-2 form-control-sm">
                    <div class="form-group col-md-2 mt-1 text-right">
                        <label>Mobile Number</label>
                    </div>
                    <div class="form-group col-md-4">
                        <input type="text"  class="form-control-sm form-control-plaintext pl-2  p-1  " value="${empExpenseDetails.mobile}"  readonly />
                    </div>
                    <div class="form-group col-md-2 mt-1 text-right">
                        <label>Location</label>
                    </div>
                    <div class="form-group col-md-4">
                        <input type="text"  class="form-control-sm form-control-plaintext pl-2  p-1 " value="${empExpenseDetails.location}" readonly />
                    </div>
                </div>
                <div class="form-row mb-2 form-control-sm">
                    <div class="form-group col-md-2 mt-2 text-right">
                        <label >Expense Type</label>
                    </div>
                    <div class="form-group col-md-4">
                        <select class="mdb-select md-form form-control form-control-sm border border-secondary pl-2"
                            id="selectExpenseType" name="<portlet:namespace/>expenseType" required>
                            <c:if test="${empExpenseDetails.viewMode == 'false'}"><option value="" disabled selected>Choose your option</option></c:if>
                            <option <c:choose><c:when test="${empExpenseDetails.expenseType=='Birthday Celebrations'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${empExpenseDetails.viewMode == 'true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Birthday Celebrations">Birthday Celebrations</option>
                            <option <c:choose><c:when test="${empExpenseDetails.expenseType=='Relocation-Luggage Movement'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${empExpenseDetails.viewMode == 'true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Relocation-Luggage Movement">Relocation-Luggage Movement</option>
                            <option <c:choose><c:when test="${empExpenseDetails.expenseType=='Relocation-Travelling Expenses'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${empExpenseDetails.viewMode == 'true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Relocation-Travelling Expenses">Relocation-Travelling Expenses</option>
                            <option <c:choose><c:when test="${empExpenseDetails.expenseType=='Relocation-Hotel Stay'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${empExpenseDetails.viewMode == 'true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Relocation-Hotel Stay">Relocation-Hotel Stay</option>
                            <option <c:choose><c:when test="${empExpenseDetails.expenseType=='Team Quarterly Party Reimbursement'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${empExpenseDetails.viewMode == 'true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Team Quarterly Party Reimbursement">Team Quarterly Party Reimbursement</option>
                            <option <c:choose><c:when test="${empExpenseDetails.expenseType=='Telephone & Broadband Expenses'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${empExpenseDetails.viewMode == 'true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Telephone & Broadband Expenses">Telephone & Broadband Expenses</option>
                            <option <c:choose><c:when test="${empExpenseDetails.expenseType=='Reimbursement Celebrations'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${empExpenseDetails.viewMode == 'true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Reimbursement Celebrations">Reimbursement Celebrations</option>
                            <option <c:choose><c:when test="${empExpenseDetails.expenseType=='Reimbursement-Team Outing/Lunch'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${empExpenseDetails.viewMode == 'true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Reimbursement-Team Outing/Lunch">Reimbursement-Team Outing/Lunch</option>
                            <option <c:choose><c:when test="${empExpenseDetails.expenseType=='Cab reimbursement-Late Night/Weekend Working'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${empExpenseDetails.viewMode == 'true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Cab reimbursement-Late Night/Weekend Working">Cab reimbursement-Late Night/Weekend Working</option>
                            <option <c:choose><c:when test="${empExpenseDetails.expenseType=='Cab reimbursement-Others'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${empExpenseDetails.viewMode == 'true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Cab reimbursement-Others">Cab reimbursement-Others</option>
                            <option <c:choose><c:when test="${empExpenseDetails.expenseType=='Marriage Gift Card'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${empExpenseDetails.viewMode == 'true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Marriage Gift Card">Marriage Gift Card</option>
                            <option <c:choose><c:when test="${empExpenseDetails.expenseType=='Child Birth Gift Card'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${empExpenseDetails.viewMode == 'true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Child Birth Gift Card">Child Birth Gift Card</option>
                            <option <c:choose><c:when test="${empExpenseDetails.expenseType=='Food Reimbursement-Late Night/Weekend Working'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${empExpenseDetails.viewMode == 'true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Food Reimbursement-Late Night/Weekend Working">Food Reimbursement-Late Night/Weekend Working</option>
                            <option <c:choose><c:when test="${empExpenseDetails.expenseType=='Food Reimbursement-Others'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${empExpenseDetails.viewMode == 'true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Food Reimbursement-Others">Food Reimbursement-Others</option>
                            <option <c:choose><c:when test="${empExpenseDetails.expenseType=='Inland per diem'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${empExpenseDetails.viewMode == 'true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Inland per diem">Inland per diem</option>
                            <option <c:choose><c:when test="${empExpenseDetails.expenseType=='Inland Travelling Expenses'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${empExpenseDetails.viewMode == 'true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Inland Travelling Expenses">Inland Travelling Expenses</option>
                            <option <c:choose><c:when test="${empExpenseDetails.expenseType=='Local Conveyance'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${empExpenseDetails.viewMode == 'true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Local Conveyance">Local Conveyance</option>
                            <option <c:choose><c:when test="${empExpenseDetails.expenseType=='Covid 19 Vaccination Reimbursement - I'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${empExpenseDetails.viewMode == 'true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Covid 19 Vaccination Reimbursement - I">Covid 19 Vaccination Reimbursement - I</option>
                            <option <c:choose><c:when test="${empExpenseDetails.expenseType=='Covid 19 Vaccination Reimbursement - II'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${empExpenseDetails.viewMode == 'true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Covid 19 Vaccination Reimbursement - II">Covid 19 Vaccination Reimbursement - II</option>
                            <option <c:choose><c:when test="${empExpenseDetails.expenseType=='Others'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${empExpenseDetails.viewMode == 'true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Others">Others</option>
                        </select>
                    </div>
                    <div class="form-group col-md-2 mt-2 text-right" <c:if test = "${empExpenseDetails.showApprovingManagerField == 'false'}"> style="display: none;" </c:if> id="managerLabelDiv">
                        <label >Manager Email</label>
                    </div>
                     <div class="form-group col-md-4" <c:if test = "${empExpenseDetails.showApprovingManagerField == 'false'}"> style="display: none;" </c:if> id="managerOptionDiv">
                        <select class="mdb-select md-form form-control form-control-sm border border-secondary pl-2"  id="approvingManager"
                            name="<portlet:namespace/>approvingManager" >
                            <c:if test="${empExpenseDetails.viewMode == 'false'}"> <option value="" disabled selected>Select Manager Email</option> </c:if>
                            <c:forEach items="${empExpenseDetails.managers}" var="manager" varStatus="count">
                                <option
                                <c:choose>
                                    <c:when test="${manager.key == empExpenseDetails.approvingManager}"> selected="selected" </c:when>
                                    <c:otherwise> <c:if test="${empExpenseDetails.viewMode == 'true'}"> style="display:none" disabled</c:if> </c:otherwise>
                                </c:choose>
                                value="${manager.key}">${manager.value} (${manager.key})</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div id="marriageExpense" <c:if test="${empExpenseDetails.expenseType != 'Marriage Gift Card'}"> style="display: none;" </c:if> >
                    <div class="form-row mb-2 form-control-sm">
                        <div class="form-group col-md-2 mt-2 text-right">
                            <label >Name of Spouse</label>
                        </div>
                        <div class="form-group col-md-4">
                            <input type="text" class="form-control-sm form-control-plaintext pl-2  p-1 border border-secondary fn_text-trim" id="spouseName"
                                name="<portlet:namespace/>spouseName" value="${empExpenseDetails.spouseName}" maxlength="75" onkeypress="return isAlfa(event)" onpaste="return false" <c:if test="${empExpenseDetails.viewMode == 'true'}">readonly</c:if> />
                        </div>
                        <div class="form-group col-md-2 mt-2 text-right">
                            <label>DoB of Spouse</label>
                        </div>
                        <div class="form-group col-md-4">
                            <input type="text"  class="form-control-sm form-control-plaintext pl-2  p-1 border border-secondary fn_text-trim" value="${empExpenseDetails.dobSpouse}" <c:choose><c:when test="${empExpenseDetails.viewMode == 'true'}"> readonly </c:when> <c:otherwise> id="dobSpouse"  </c:otherwise></c:choose>
                              autocomplete="off"  name="<portlet:namespace/>dobSpouse" />
                        </div>
                    </div>
                    <div class="form-row mb-2 form-control-sm">
                        <div class="form-group col-md-2 mt-2 text-right">
                            <label>Date Of Marriage</label>
                        </div>
                        <div class="form-group col-md-4">
                            <input type="text" class="form-control-sm form-control-plaintext pl-2  p-1 border border-secondary fn_text-trim" value="${empExpenseDetails.marriageDate}"
                              autocomplete="off"   name="<portlet:namespace/>marriageDate" <c:choose><c:when test="${empExpenseDetails.viewMode == 'true'}"> readonly </c:when> <c:otherwise> id="marriageDate" </c:otherwise></c:choose> />
                        </div>
                        <div class="form-group col-md-2 mt-2 text-right">
                            <label>Gender Of Spouse</label>
                        </div>
                        <div class="form-group col-md-4">
                            <select class="mdb-select md-form form-control form-control-sm border border-secondary pl-2" id="selectGender"
                                name="<portlet:namespace/>selectGender">
                                <c:if test="${empExpenseDetails.viewMode == 'false'}"><option value="" disabled selected>Choose your option</option></c:if>
                                <option <c:choose><c:when test="${empExpenseDetails.selectGender=='Male'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${empExpenseDetails.viewMode == 'true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Male">Male</option>
                                <option <c:choose><c:when test="${empExpenseDetails.selectGender=='Female'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${empExpenseDetails.viewMode == 'true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Female">Female</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div id="childBirthExpense"  <c:if test="${empExpenseDetails.expenseType != 'Child Birth Gift Card'}"> style="display: none;" </c:if>>
                    <div class="form-row mb-2 form-control-sm">
                        <div class="form-group col-md-2 mt-2 text-right">
                            <label>Name of Baby</label>
                        </div>
                        <div class="form-group col-md-4">
                            <input type="text" class="form-control-sm form-control-plaintext pl-2  p-1 border border-secondary fn_text-trim" id="babyName" value="${empExpenseDetails.babyName}"
                                name="<portlet:namespace/>babyName" maxlength="75" onkeypress="return isAlfa(event)" onpaste="return false" <c:if test="${empExpenseDetails.viewMode == 'true'}">readonly</c:if> />
                        </div>
                        <div class="form-group col-md-2 mt-2 text-right">
                            <label>Date Of Birth of Baby</label>
                        </div>
                        <div class="form-group col-md-4">
                            <input type="text" class="form-control-sm form-control-plaintext pl-2  p-1 border border-secondary fn_text-trim" value="${empExpenseDetails.dobBaby}"
                              autocomplete="off"  name="<portlet:namespace/>dobBaby" <c:choose><c:when test="${empExpenseDetails.viewMode == 'true'}"> readonly </c:when> <c:otherwise> id="dobBaby" </c:otherwise></c:choose> />
                        </div>
                    </div>
                    <div class="form-row mb-2 form-control-sm" >
                        <div class="form-group col-md-2 mt-2 text-right">
                            <label>Gender</label>
                        </div>
                        <div class="form-group col-md-4">
                            <select class="mdb-select md-form form-control form-control-sm border border-secondary pl-2"
                                id="selectBabyGender"
                                name="<portlet:namespace/>selectBabyGender">
                                <c:if test="${empExpenseDetails.viewMode == 'false'}"><option value="" disabled selected>Choose your option</option></c:if>
                                <option <c:choose><c:when test="${empExpenseDetails.selectBabyGender=='Male'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${empExpenseDetails.viewMode == 'true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Male">Male</option>
                                <option <c:choose><c:when test="${empExpenseDetails.selectBabyGender=='Female'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${empExpenseDetails.viewMode == 'true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Female">Female</option>
                            </select>
                        </div>
                    </div>
                </div>
                <table class="table table-bordered table-striped text-center mt-3" id="empTable" <c:if test="${empExpenseDetails.expenseType == 'Child Birth Gift Card' || empExpenseDetails.expenseType == 'Marriage Gift Card'}"> style="display: none;" </c:if> >
                   <thead class="thead-dark">
                      <tr>
                         <c:if test = "${empExpenseDetails.viewMode == 'false'}">
                            <th class="text-center align-middle" width="10%">
                               <button type="button" class="btn btn-sm btn-outline-success py-0" id="addNewRow"  onclick="addNewRows()">
                               <i class="far fa-plus-square"></i>
                               </button>
                            </th>
                         </c:if>
                         <th class="text-center align-middle">#</th>
                         <th class="text-center align-middle">Start Date</th>
                         <th class="text-center align-middle">End Date</th>
                         <th class="text-center align-middle">Expense Description</th>
                         <th class="text-center align-middle">Bill Amount(&#8377;)
                         </th>
                      </tr>
                   </thead>
                   <tbody id="expItemsTable">
                      <c:choose>
                         <c:when test="${empExpenseDetails.expenseId == '0'}">
                            <tr>
                               <td><small>1 row is mandate.</small> </td>
                               <td>1</td>
                               <td>
                                  <input type="text" class="fn_startDateDatepicker" readonly  name="<portlet:namespace/>startDate1" id="startDate1" value="${item.startDate}" />
                               </td>
                               <td>
                                  <input type="text" class="fn_endDateDatepicker" readonly name="<portlet:namespace/>endDate1" id= "endDate1" value="${item.endDate}" />
                               </td>
                               <td class="p-0"><textarea id="empDesc1" rows="2" cols="50" class="form-control form-control-sm fn_text-trim"  style="height:80px;"  maxlength="1000"  placeholder="Add Employee Id and other additional Information here..." name="<portlet:namespace/>empDesc1" required >${item.description}</textarea></td>
                               <td><input type="number" id="amount1"  oninvalid="setCustomValidity('Bill amount should be less than 1 lakh but greater than zero.')"  oninput="setCustomValidity('')" step="0.01"  min="0.01" max="100000"   onKeyUp= "calculatedSum()" onblur="roundTextBoxes(this)" class="form-control fn_amount " name="<portlet:namespace/>amount1" value="${item.billAmount}" required /></td>
                            </tr>
                         </c:when>
                         <c:when test="${empExpenseDetails.expenseId != '0' && empExpenseDetails.viewMode == 'false'}">
                            <c:forEach items="${expenseLineList}" var="item" varStatus="count">
                               <tr>
                                  <td>
                                  <c:if test ="${count.index !='0'}">
                                  <button type="button" id="exp${count.index+1}" onclick="removeRowAndResetIndex(this)"  class="btn btn-sm btn-outline-danger py-0"><i class="fas fa-trash-alt" style="cursor: pointer"></i></button></td>
                                 </c:if>
                                  <td><input type="text" id="num${count.index+1}" value="${count.index+1}" class="form-control-plaintext text-center " readonly    /></td>
                                  <td>
                                     <input type="text" class="fn_startDateDatepicker" name="<portlet:namespace/>startDate${count.index+1}" id="startDate${count.index+1}" value="${item.startDate}" readonly/>
                                  </td>
                                  <td>
                                     <input type="text" class="fn_endDateDatepicker"  name="<portlet:namespace/>endDate${count.index+1}" id= "endDate${count.index+1}" value="${item.endDate}" readonly/>
                                  </td>
                                  <td class="p-0"><textarea id="empDesc${count.index+1}" rows="2" cols="50" class="form-control form-control-sm fn_text-trim"  style="height:80px;"  maxlength="1000"  placeholder="Add Employee Id and other additional Information here..." name="<portlet:namespace/>empDesc${count.index+1}" required >${item.description}</textarea></td>
                                  <td><input type="number" id="amount${count.index+1}"  oninvalid="setCustomValidity('Bill amount should be less than 1 lakh but greater than zero.')"  oninput="setCustomValidity('')" step="0.01"  min="0.01" max="100000"   onKeyUp= "calculatedSum()" onblur="roundTextBoxes(this)" class="form-control fn_amount " name="<portlet:namespace/>amount${count.index+1}" value="${item.billAmount}" required /></td>
                               </tr>
                            </c:forEach>
                         </c:when>
                         <c:when test="${empExpenseDetails.expenseId != '0' && empExpenseDetails.viewMode == 'true'}">
                            <c:forEach items="${expenseLineList}" var="item" varStatus="count">
                               <tr>
                                  <td>${count.index+1}</td>
                                  <td>
                                     <input type="text"  value="${item.startDate}" readonly/>
                                  </td>
                                  <td>
                                     <input type="text"  value="${item.endDate}" readonly />
                                  </td>
                                  <td class="p-0"><textarea  class="form-control form-control-sm"  style="height:80px;"  readonly >${item.description}</textarea></td>
                                  <td><input type="number"  class="form-control" value="${item.billAmount}" readonly /></td>
                               </tr>
                            </c:forEach>
                         </c:when>
                      </c:choose>
                   </tbody>
                </table>
                <div class="form-row pt-3">
                    <div class="form-group col-md-2 mt-1 text-right">
                        <label>Total Amount (&#8377;)</label>
                    </div>
                    <div class="form-group col-md-4">
                            <input type="text" id="totalAmount" class="form-control bg-transparent border-0 p-0 pl-1 pb-1" name="<portlet:namespace/>totalAmount" value="${empExpenseDetails.totalBillAmount}" readonly />
                    </div>
                    <c:choose>
                        <c:when test ="${empExpenseDetails.viewMode == 'false' }" >
                        <div class="col-2"></div>
                            <div class="form-group col-md-4 mt-1" id="uploadZipDiv">
                            <c:if test="${empExpenseDetails.expenseType != 'Child Birth Gift Card' && empExpenseDetails.expenseType != 'Marriage Gift Card'}">
                                <div class="custom-file">
                                    <input type="file" class="custom-file-input fn_upload-file" id="uploadZip" name="<portlet:namespace/>uploadZip" required /> <label class="custom-file-label">Choose file</label>
                                </div>
                                <div>
                                       <small>Please upload a zip size of max 30 mb</small>
                                </div>
                                </c:if>
                                       <c:if test = "${empExpenseDetails.fileId != null}"  >
                                           <button class="btn btn-link  btn-sm" type="button" id="downloadBillBtn" onClick="downloadBill();"><i class="fa-solid fa-download"></i> Download Old Bills </button>
                                       </c:if>
                            </div>
                        </c:when>
                        <c:otherwise>
                        <c:if test="${empExpenseDetails.expenseType != 'Child Birth Gift Card' && empExpenseDetails.expenseType != 'Marriage Gift Card'}">
                            <div class="form-group col-md-4 mt-1">
                                 <button class="btn btn-link  btn-sm" type="button" id="downloadBillBtn" onClick="downloadBill();"><i class="fa-solid fa-download"></i> Download Bills </button>
                            </div>
                        </c:if>
                        </c:otherwise>
                    </c:choose>
                </div>
                <c:if test="${empExpenseDetails.managerFinanceSection == 'true'}">
                   <div class="form-row">
                      <c:if test="${empExpenseDetails.managerComment != null}">
                         <div class="form-group col-md-6" >
                            <label>Manager Comments</label>
                            <textarea class="form-control form-control-sm pl-2" readonly style="height:80px;"
                               >${empExpenseDetails.managerComment}</textarea>
                         </div>
                      </c:if>
                      <c:if test="${empExpenseDetails.financeComment != ''}">
                         <div class="form-group col-md-6">
                            <label>Finance Comments</label>
                            <textarea class="form-control form-control-sm pl-2"  style="height:80px;" readonly
                               >${empExpenseDetails.financeComment}</textarea>
                         </div>
                      </c:if>
                   </div>
                </c:if>
                <c:if test="${empExpenseDetails.viewMode == 'false'}">
                <div class="custom-control custom-checkbox" id="checkboxDiv">
                    <input type="checkbox" class="custom-control-input" required id="terms" /> <label class="custom-control-label pl-2 " for="terms">
                    <small>I accept that the information provided above is
                    true to the best of my knowledge<c:if test="${empExpenseDetails.expenseType != 'Child Birth Gift Card' && empExpenseDetails.expenseType != 'Marriage Gift Card'}"><span id="tcBill"> and all
                    the bills are self attested and have Employee Id on every bill</span></c:if>.
                    </small>
                    </label>
                </div>
                <div class="row pt-4 pb-2" >
                    <div class="col-12 text-center">
                        <button class="btn btn-primary mt-4" id="submitItAssetsButtonDiv" onclick="setValue()" >Submit</button>
                    </div>
                </div>
                </c:if>
                <div class="row mt-4" id="noteForExpenses" >
                <c:if test="${empExpenseDetails.viewMode == 'false'}">
                    <div class="col-12">
                        <p>
                            <strong>Note :</strong> Expense submission using this system can
                            be done effective Previous & Current financial year.
                        </p>
                    </div>
                </c:if>
                    <div class="col-12" id="covidDiv" <c:if test="${empExpenseDetails.expenseType !='Covid 19 Vaccination Reimbursement - I' && empExpenseDetails.expenseType !='Covid 19 Vaccination Reimbursement - II'}"> style="display:none" </c:if>>
                        <p>
                            <strong>For Covid 19 vaccination reimbursement</strong>
                        <ul>
                            <li>Both FTE and contractors are eligible for reimbursement under "Covid 19 Expense reimbursement"</li>
                            <li>You can claim this amount for self only</li>
                            <li>You can claim this amount only twice - once for each dose</li>
                            <li>Amount claimed can not exceed Vaccination charges as decided by Govt of India/State government and will be reimbursed as per receipt produced</li>
                            <li>You have to upload vaccination receipt and vaccination certificate as proof of reimbursement</li>
                            <li>You have to keep start and end date same as vaccination date for expense reimbursement</li>
                        </ul>
                        </p>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

