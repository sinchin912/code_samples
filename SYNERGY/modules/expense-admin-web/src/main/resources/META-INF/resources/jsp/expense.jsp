<%@ include file="/init.jsp" %>
<portlet:renderURL var="expenseRenderURL" windowState="normal"></portlet:renderURL>
<portlet:actionURL name="submitExpenseForm" var="submitExpenseForm"></portlet:actionURL>
<portlet:resourceURL var="downloadExpenseBillUrl" id="downloadExpenseBill"></portlet:resourceURL>
<portlet:renderURL var="expenseRenderURL" windowState="normal"></portlet:renderURL>
<div class="container">
   <c:set var="pageUrl" value="${fn:split(expenseRenderURL.toString(),'?')}" />
   <input type="hidden" id="portletNamespace"   value="<portlet:namespace/>" />
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
         <input type="hidden" id="downloadExpenseBillResourceUrl" value="${downloadExpenseBillUrl}"/>
         <input type="hidden" id="fileId" value="${empExpenseDetails.fileId}"  />
         <div class="form-row mb-2 form-control-sm">
            <div class="form-group col-md-2 mt-1 text-right">
               <label>E-Code  </label>
            </div>
            <div class="form-group col-md-4">
               <input type="text" class="form-control-sm form-control-plaintext pl-2  p-1 " value="${empExpenseDetails.ecode}"   readonly />
            </div>
            <div class="form-group col-md-2 mt-1 text-right">
               <label>Name  </label>
            </div>
            <div class="form-group col-md-4">
               <input type="text" id="name" class="form-control-sm form-control-plaintext pl-2  p-1 " value="${empExpenseDetails.name}" readonly />
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
         <c:if test="${empExpenseDetails.financeSubmissionDate != null || empExpenseDetails.managerSubmissionDate != null}">
         <div class="form-row mb-2 form-control-sm">
         <c:if test="${empExpenseDetails.financeSubmissionDate != null}">
            <div class="form-group col-md-2 mt-1 text-right">
               <label>${empExpenseDetails.financeLebelName} </label>
            </div>
            <div class="form-group col-md-4">
               <input type="text"  class="form-control-sm form-control-plaintext pl-2  p-1  " value="${empExpenseDetails.financeSubmissionDate}"  readonly />
            </div>
         </c:if>
         <c:if test="${empExpenseDetails.managerSubmissionDate != null}">
            <div class="form-group col-md-2 mt-1 text-right">
               <label>${empExpenseDetails.managerLebelName}</label>
            </div>
            <div class="form-group col-md-4">
               <input type="text"  class="form-control-sm form-control-plaintext pl-2  p-1 " value="${empExpenseDetails.managerSubmissionDate}" readonly />
            </div>
         </c:if>
         </div>
      </c:if>
         <div class="form-row mb-2 form-control-sm">
            <div class="form-group col-md-2 mt-2 text-right">
               <label >Expense Type</label>
            </div>
            <div class="form-group col-md-4">
               <input type="text" class="form-control-sm form-control-plaintext pl-2  p-1" readonly value="${empExpenseDetails.expenseType}" id="expenseType"
                  />
            </div>
            <c:if test = "${empExpenseDetails.showApprovingManagerField == 'true'}">
               <div class="form-group col-md-2 mt-2 text-right" id="managerLabelDiv">
                  <label >Manager Name</label>
               </div>
               <div class="form-group col-md-4" id="managerOptionDiv">
                  <input type="text" class="form-control-sm form-control-plaintext pl-2  p-1" readonly value="${empExpenseDetails.approvingManager}" name="<portlet:namespace/>managerName" />
               </div>
            </c:if>
         </div>
         <c:if test="${empExpenseDetails.expenseType == 'Marriage Gift Card'}">
            <div id="marriageExpense" >
               <div class="form-row mb-2 form-control-sm">
                  <div class="form-group col-md-2 mt-2 text-right">
                     <label >Name of Spouse</label>
                  </div>
                  <div class="form-group col-md-4">
                     <input type="text" class="form-control-sm form-control-plaintext pl-2  p-1"
                        value="${empExpenseDetails.spouseName}"  readonly/>
                  </div>
                  <div class="form-group col-md-2 mt-2 text-right">
                     <label>DoB of Spouse</label>
                  </div>
                  <div class="form-group col-md-4">
                     <input type="text" class="form-control-sm form-control-plaintext pl-2  p-1"
                        value="${empExpenseDetails.dobSpouse}" readonly/>
                  </div>
               </div>
               <div class="form-row mb-2 form-control-sm">
                  <div class="form-group col-md-2 mt-2 text-right">
                     <label>Date Of Marriage</label>
                  </div>
                  <div class="form-group col-md-4">
                     <input type="text" class="form-control-sm form-control-plaintext pl-2  p-1" value="${empExpenseDetails.marriageDate}" readonly />
                  </div>
                  <div class="form-group col-md-2 mt-2 text-right">
                     <label>Gender Of Spouse</label>
                  </div>
                  <div class="form-group col-md-4">
                     <input type="text" class="form-control-sm form-control-plaintext pl-2  p-1" value="${empExpenseDetails.selectGender}" readonly />
                  </div>
               </div>
            </div>
         </c:if>
         <c:if test="${empExpenseDetails.expenseType == 'Child Birth Gift Card'}">
            <div id="childBirthExpense" >
               <div class="form-row mb-2 form-control-sm">
                  <div class="form-group col-md-2 mt-2 text-right">
                     <label>Name of Baby</label>
                  </div>
                  <div class="form-group col-md-4">
                     <input type="text" class="form-control-sm form-control-plaintext pl-2  p-1"
                        value="${empExpenseDetails.babyName}" readonly />
                  </div>
                  <div class="form-group col-md-2 mt-2 text-right">
                     <label>Date Of Birth of Baby</label>
                  </div>
                  <div class="form-group col-md-4">
                     <input type="text" class="form-control-sm form-control-plaintext pl-2  p-1"
                        value="${empExpenseDetails.dobBaby}" readonly/>
                  </div>
               </div>
               <div class="form-row mb-2 form-control-sm" >
                  <div class="form-group col-md-2 mt-2 text-right">
                     <label>Gender</label>
                  </div>
                  <div class="form-group col-md-4">
                     <input type="text" class="form-control-sm form-control-plaintext pl-2  p-1"  value="${empExpenseDetails.selectBabyGender}" readonly/>
                  </div>
               </div>
            </div>
         </c:if>
         <c:if test="${empExpenseDetails.expenseType != 'Child Birth Gift Card' && empExpenseDetails.expenseType != 'Marriage Gift Card'}">
         <table class="table table-sm order-list table-bordered table-striped text-center mt-3" id="empTable">
            <thead class="thead-dark">
               <tr>
                  <th class="text-center align-middle" >Serial No.</th>
                  <th class="text-center align-middle" >Start Date</th>
                  <th class="text-center align-middle" >End Date</th>
                  <th class="text-center align-middle">Expense Description</th>
                  <th class="text-center align-middle" >Bill Amount(&#8377;)
                  </th>
               </tr>
            </thead>
            <tbody>
               <c:forEach items="${expenseLineList}" var="item" varStatus="count">
                  <tr>
                     <td>${count.index+1}</td>
                     <td>${item.startDate}</td>
                     <td>${item.endDate}</td>
                     <td>${item.description}</td>
                     <td>${item.billAmount}</td>
                  </tr>
               </c:forEach>
            </tbody>
         </table>
         </c:if>
         <div class="form-row mt-3">
            <div class="col-2 text-right">Total Amount (&#8377;)</div>
            <div class="col-md-4">${empExpenseDetails.totalBillAmount}</div>
            <div class="col-2"></div>
            <c:if test="${empExpenseDetails.expenseType != 'Child Birth Gift Card' && empExpenseDetails.expenseType != 'Marriage Gift Card'}">
            <div class="col-4">
            <button class="btn btn-link  btn-sm" type="button" id="downloadBillBtn" onClick="downloadBill();"><i class="fa-solid fa-download"></i> Download Bills </button>
            </div>
            </c:if>
         </div>
         <div class="form-row mt-3">
            <div class="col-6">
               <c:if test="${empExpenseDetails.managerComment != null}">
               <div class="form-group">
                     <label class="font-weight-bold">Manager Comments</label>
                     <textarea  style="height:80px;"
                        class="form-control-plaintext border border-dark pl-2" readonly>${empExpenseDetails.managerComment}</textarea>
               </div>
               </c:if>
               <c:if test="${empExpenseDetails.financeComment != ''}">
                  <div class="form-group">
                     <label class="font-weight-bold">Finance Comments</label>
                     <textarea style="height:80px;"
                        class="form-control-plaintext border border-dark pl-2" readonly>${empExpenseDetails.financeComment}</textarea>
                  </div>
               </c:if>
            </div>
         </div>
         <c:if test="${empExpenseDetails.mode == 'edit'}">
                  <form  onsubmit="return confirmSubmitDetails()" id="expenseAdminForm"   action="${submitExpenseForm}" method="post">
                     <input type="hidden" value="${empExpenseDetails.expenseId}" name="<portlet:namespace/>expenseId" />
                     <input type="hidden"  id="action" name="<portlet:namespace/>action" />
                     <div class="form-row mt-3">
                        <div class="col-12">
                     <label class="font-weight-bold">Comments</label>
                     <textarea style="height:80px;" maxlength="1000"  onblur="$(this).val($(this).val().trim())"
                        name="<portlet:namespace/>comments" required
                        class="form-control "></textarea>
                        </div>
                     </div>
                     <div class="row mt-4 d-flex justify-content-center">
                        <div class="col-4">
                           <button class="btn btn-outline-success" onClick="return submitForm(1)"
                              id="approveBtn">Approve</button>
                        </div>
                        <div class="col-4">
                           <div class="input-group mb-3">
                              <div class="input-group-prepend">
                                 <div class="input-group-text">
                                    <input type="checkbox" name="<portlet:namespace/>noResubmit" id="noResubmitCheckbox" value="true">
                                 </div>
                              </div>
                              <input type="text" readonly class="form-control"
                                 placeholder="No Resubmit" aria-describedby="basic-addon2">
                              <div class="input-group-append">
                                 <button class="btn btn-outline-danger" onClick="return submitForm(2)" id="rejectBtn">Reject</button>
                              </div>
                           </div>
                        </div>
                     </div>
                  </form>
         </c:if>
      </div>
   </div>
</div>