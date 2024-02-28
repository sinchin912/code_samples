  <%@ include file="/init.jsp" %>
  <portlet:actionURL name="submitClearance" var="submitClearanceURL"></portlet:actionURL>
  <portlet:actionURL name="reopenForm" var="reopenFormUrl"></portlet:actionURL>
<portlet:actionURL name="exitWorkflow" var="exitWorkflowUrl"></portlet:actionURL>
<portlet:resourceURL id="saveClearanceForm" var="saveClearanceFormUrl"></portlet:resourceURL>
<portlet:renderURL var="resignationRenderURL" windowState="normal"></portlet:renderURL>
  <style>
     .carousel-caption{
     position : relative;
     top : 0;
     left : 0;
     }
.ds_readonlyTextbox  {
background-color: #f2f2f7 !important;
}

  </style>
    <div id="commonFormDiv" class="card pb-2">
  <c:set var="pageUrl" value="${fn:split(resignationRenderURL.toString(),'?')}" />
  <input type="hidden" value="${empCoreDetails.roleType}" id="roleType" />
  <input type="hidden" id="portletNamespace"   value="<portlet:namespace/>" />
  <input type="hidden" id="saveClearanceFormResourceURL"   value="${saveClearanceFormUrl}" />
    	<div class="card-header">
            <div class="row">
    			<div class="col pt-3">
    				<h5>Exit Clearance Form</h5>
    			</div>
                <c:if test="${empCoreDetails.roleType == 'Trantor_HR'}">
    			  <div class="col-6 pt-3">
                           <form id="reopenForm" method="post" action="${reopenFormUrl}"
                              onSubmit="return reopenForm();">
                                 <input type="hidden" value="${empCoreDetails.resignationId}"
                                                 name="<portlet:namespace/>resignationId" />

                           <div>
                           <c:if test="${clearanceFormData.managerClearanceDto.managerSubmitted==true}">
                                <input type="checkbox"  name="<portlet:namespace/>manager" value="1" />
                                       <label class= "pl-1 pr-2">Manager</label>
                           </c:if>
                           <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true'}">
                                <input type="checkbox"  name="<portlet:namespace/>it" value="1" />
                                       <label class= "pl-1 pr-2">IT</label>
                           </c:if>
                           <c:if test="${clearanceFormData.adminClearanceDto.adminSubmitted=='true'}">
                                <input type="checkbox"  name="<portlet:namespace/>admin" value="1" />
                                       <label class= "pl-1 pr-2">Admin</label>
                           </c:if>
                           <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true'}">
                                <input type="checkbox"  name="<portlet:namespace/>finance" value="1" />
                                       <label class= "pl-1 pr-2">Finance</label>
                           </c:if>
                           <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true'}">
                             <input type="checkbox"name="<portlet:namespace/>hr" value="1" /> <label class= "pl-1 pr-3"> HR</label>
                           </c:if>
                           <c:if test="${clearanceFormData.managerClearanceDto.managerSubmitted=='true' || clearanceFormData.itClearanceDto.itSubmitted=='true' || clearanceFormData.adminClearanceDto.adminSubmitted=='true' ||clearanceFormData.financeClearanceDto.financeSubmitted=='true' ||clearanceFormData.hrClearanceDto.hrSubmitted=='true'}">
                           <button class="btn btn-outline-warning " type="submit"  >Reopen</button>
                           </c:if>
                 </div>
             </form>
                  </div>
                 </c:if>
    			<div class="col-2 text-right">
                  <a class="btn btn-outline-secondary" id="reset" href="${pageUrl[0]}" style="color: black;" role="button">
                  Cancel</a>
    			</div>
    		</div>
    	</div>
    	<div class="card-body">
                   <form id="resignForm" method="post" action="${submitClearanceURL}"
                              onSubmit="return confirmClearanceFormSubmission();" >
                                 <input type="hidden" value="${empCoreDetails.resignationId}"
                                                 name="<portlet:namespace/>resignationId" />
                                  <input type="hidden" value="${empCoreDetails.roleType}"
                                                 name="<portlet:namespace/>roleType" />
                                             <input type="hidden" id="totalRows"
                                                     value="" name="<portlet:namespace/>totalRows" />

                                              <input type="hidden" id="reimbursementTotalRows"
                                                      value="" name="<portlet:namespace/>reimbursementTotalRows" />
                                              <input type="hidden" id="adminTotalRows"
                                                      value="" name="<portlet:namespace/>adminTotalRows" />
                                              <input type="hidden" id="itTotalRows"
                                                      value="" name="<portlet:namespace/>itTotalRows" />
                                              <input type="hidden" id="hrTotalRows"
                                                      value="" name="<portlet:namespace/>hrTotalRows" />
				<div class="form-row form-control-sm">
					<div class="form-group col-md-2 mt-2 text-right">
						<label>E-code</label>
					</div>
					<div class="form-group col-md-4 mt-1">
						<input type="text" class="form-control-sm form-control-plaintext" value="${empCoreDetails.employeeCode}" readonly />
					</div>
					<div class="form-group col-md-2 mt-2 text-right">
						<label>Name</label>
					</div>
					<div class="form-group col-md-4 mt-1">
						<input type="text" class="form-control-sm form-control-plaintext"  value="${empCoreDetails.employeeName}" readonly />
					</div>
				</div>
				<div class="form-row form-control-sm">
					<div class="form-group col-md-2 mt-2 text-right">
						<label>DOJ</label>
					</div>
					<div class="form-group col-md-4 mt-1">
						<input type="text" class="form-control-sm form-control-plaintext" value="${empCoreDetails.doj}" readonly />
					</div>
					<div class="form-group col-md-2 mt-2 text-right">
						<label>Designation</label>
					</div>
					<div class="form-group col-md-4 mt-1">
						<input type="text" class="form-control-sm form-control-plaintext" value="${empCoreDetails.employeeDesignation}" readonly />
					</div>
				</div>
				<div class="form-row form-control-sm">
					<div class="form-group col-md-2 mt-2 text-right">
						<label>Account</label>
					</div>
					<div class="form-group col-md-4 mt-1">
						<input type="text" class="form-control-sm form-control-plaintext" value="${empCoreDetails.project}" readonly />
					</div>
					<div class="form-group col-md-2 mt-2 text-right">
						<label>Last Working Day</label>
					</div>
					<div class="form-group col-md-4 mt-1">
						<input type="text" class="form-control-sm form-control-plaintext" value="${empCoreDetails.lastWorkingDate}" readonly />
					</div>
				</div>
				<div class="form-row form-control-sm">
					<div class="form-group col-md-2 mt-2 text-right">
						<label>Manager Name</label>
					</div>
					<div class="form-group col-md-4 mt-1">
						<input type="text" class="form-control-sm form-control-plaintext" value="${empCoreDetails.managerName}" readonly />
					</div>

				</div>
				<div id="exitFormStages" class="carousel slide pt-4" data-interval="false">
                  <ol class="carousel-indicators">
                  <c:if test="${empCoreDetails.roleType == 'Trantor_Manager' || empCoreDetails.roleType == 'Trantor_HR' || empCoreDetails.roleType == 'Trantor_Finance'}">  <li data-target="#exitFormStages" data-slide-to="0" <c:if test="${empCoreDetails.roleType == 'Trantor_Manager'}">  class="active"> </c:if> </li></c:if>
                  <c:if test="${empCoreDetails.roleType == 'Trantor_IT' || empCoreDetails.roleType == 'Trantor_HR' || empCoreDetails.roleType == 'Trantor_Finance'}">  <li data-target="#exitFormStages" data-slide-to="1" <c:if test="${empCoreDetails.roleType == 'Trantor_IT'}">  class="active"> </c:if> ></li></c:if>
                  <c:if test="${empCoreDetails.roleType == 'Trantor_Admin' || empCoreDetails.roleType == 'Trantor_HR' || empCoreDetails.roleType == 'Trantor_Finance'}">  <li data-target="#exitFormStages" data-slide-to="2" <c:if test="${empCoreDetails.roleType == 'Trantor_Admin'}">  class="active"> </c:if> ></li></c:if>
                  <c:if test="${empCoreDetails.roleType == 'Trantor_HR' || empCoreDetails.roleType == 'Trantor_Finance'}">  <li data-target="#exitFormStages" data-slide-to="3" <c:if test="${empCoreDetails.roleType == 'Trantor_Finance'}">  class="active"> </c:if> ></li></c:if>
                   <c:if test="${empCoreDetails.roleType == 'Trantor_HR' || empCoreDetails.roleType == 'Trantor_Finance'}"> <li data-target="#exitFormStages" data-slide-to="4" <c:if test="${empCoreDetails.roleType == 'Trantor_HR'}">  class="active"> </c:if> ></li></c:if>
                  </ol>
                  <div class="carousel-inner">
                 <c:if test="${empCoreDetails.roleType == 'Trantor_HR' || empCoreDetails.roleType == 'Trantor_Finance' ||empCoreDetails.roleType == 'Trantor_Manager'  }">
                    <div class="${empCoreDetails.roleType == 'Trantor_Manager' ? 'carousel-item active' : 'carousel-item'}" id="manager" >
                      <table  class="w-100 table table-sm table-bordered table-striped mb-0">
                        <thead class="thead-dark">
                            <tr>
                                <th width="5%">S No</th>
                                <th width="40%">Item</th>
                                <th width="20%">Yes/No</th>
                                <th width="35%">Remarks</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td class="py-0">a)</td>
                                <td class="py-0">IT Helpdesk Ticket Reference Number</td>
                                <td class="p-0"><select class="mdb-select md-form form-control form-control-sm " name="<portlet:namespace/>ticketNo"  id="ticketNo" <c:if test="${empCoreDetails.roleType == 'Trantor_Manager'}"> required="required" </c:if> >
                                        <c:if test="${clearanceFormData.managerClearanceDto.managerSubmitted==false}"> <option value="" disabled selected>Choose your option</option> </c:if>
                                        <option <c:choose><c:when test="${clearanceFormData.managerClearanceDto.ticketNo=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.managerClearanceDto.managerSubmitted==true || empCoreDetails.roleType != 'Trantor_Manager'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                        <option <c:choose><c:when test="${clearanceFormData.managerClearanceDto.ticketNo=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.managerClearanceDto.managerSubmitted==true || empCoreDetails.roleType != 'Trantor_Manager'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                        </select></td>
                                <td class="p-0"><input type="text" id="ticketNoRemark" placeholder="Put IT Ticket Helpdesk number here" onblur="$(this).val($(this).val().trim())"   maxlength="199"  <c:choose><c:when test="${clearanceFormData.managerClearanceDto.managerSubmitted==true || empCoreDetails.roleType != 'Trantor_Manager'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose>  value="${clearanceFormData.managerClearanceDto.ticketNoRemark}" name="<portlet:namespace/>ticketNoRemark"  <c:if test="${empCoreDetails.roleType == 'Trantor_Manager'}"> required="required" </c:if>   ></td>
                            </tr>
                            <tr>
                                <td class="py-0">b)</td>
                                <td class="py-0">All Separation requirements have been completed ?</td>
                                <td class="p-0"><select class="mdb-select md-form form-control form-control-sm " name="<portlet:namespace/>separationDocumentManager"  <c:if test="${empCoreDetails.roleType == 'Trantor_Manager'}"> required="required" </c:if> >
                                        <c:if test="${clearanceFormData.managerClearanceDto.managerSubmitted==false}"> <option value="" disabled selected>Choose your option</option> </c:if>
                                        <option <c:choose><c:when test="${clearanceFormData.managerClearanceDto.separationDocument=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.managerClearanceDto.managerSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Manager'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                        <option <c:choose><c:when test="${clearanceFormData.managerClearanceDto.separationDocument=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.managerClearanceDto.managerSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Manager'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                        </select></td>
                                <td class="p-0"><input type="text"  <c:choose><c:when test="${clearanceFormData.managerClearanceDto.managerSubmitted==true || empCoreDetails.roleType != 'Trantor_Manager' }"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> value="${clearanceFormData.managerClearanceDto.separationDocumentRemark}" name="<portlet:namespace/>separationDocumentRemarkManager"  maxlength="199"   ></td>
                            </tr>
                        </tbody>
                      </table>
                      <div class="carousel-caption d-none d-md-block bg-dark"><h6 class="pb-3">Manager Form</h6></div>
                    </div>
                    </c:if>
                    <c:if test="${empCoreDetails.roleType == 'Trantor_HR' || empCoreDetails.roleType == 'Trantor_Finance' ||empCoreDetails.roleType == 'Trantor_IT'  }">
                    <div class="${empCoreDetails.roleType == 'Trantor_IT' ? 'carousel-item active' : 'carousel-item'}" >
                      <table  class="w-100 table table-sm table-bordered table-striped  mb-0 ">
                        <thead class="thead-dark">
                            <tr>
                                <th width="5%">S No</th>
                                <th width="45%">Item</th>
                                <th width="20%">Yes/No/NA</th>
                                <th width="30%">Remarks</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td class="py-0"></td>
                                <td class="py-0">1) IT Ticket no (put by manager)</td>
                                <td class="p-0"><select class="mdb-select md-form form-control form-control-sm" name="<portlet:namespace/>itTicket"  <c:if test="${empCoreDetails.roleType == 'Trantor_IT'}"> required="required" </c:if> >
                                            <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='false'}"> <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.itTicket=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT' }"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.itTicket=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.itTicket=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                        </select></td>
                                <td class="p-0"><input type="text"  <c:choose><c:when test="${clearanceFormData.itClearanceDto.itSubmitted==true || empCoreDetails.roleType != 'Trantor_IT'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> value="${clearanceFormData.itClearanceDto.itTicketRemark}"  name="<portlet:namespace/>itTicketRemark"  maxlength="199"    /></td>
                            </tr>
                            <tr>
                                <td class="py-0">a)</td>
                                <td class="py-0" colspan="3">Manual back up done for <br/> (SVN back up is not applicable as it is automated)</td>
                            </tr>
                            <tr>
                                <td class="py-0"></td>
                                <td class="py-0">i) Mail/PST</td>
                                <td class="p-0"><select class="mdb-select md-form form-control form-control-sm" name="<portlet:namespace/>mailPst"  <c:if test="${empCoreDetails.roleType == 'Trantor_IT'}"> required="required" </c:if> >
                                            <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='false'}"> <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.mailPst=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.mailPst=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.mailPst=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                        </select></td>
                                <td class="p-0"><input type="text" <c:choose><c:when test="${clearanceFormData.itClearanceDto.itSubmitted==true || empCoreDetails.roleType != 'Trantor_IT'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> value="${clearanceFormData.itClearanceDto.mailPstRemark}"  name="<portlet:namespace/>mailPstRemarkIT"   maxlength="199"    /></td>
                            </tr>
                            <tr>
                                <td class="py-0"></td>
                                <td class="py-0">ii) Desktop machine/laptop Download Folder</td>
                                <td class="p-0"><select class="mdb-select md-form form-control form-control-sm" name="<portlet:namespace/>downloadFolder" <c:if test="${empCoreDetails.roleType == 'Trantor_IT'}"> required="required" </c:if> >
                                            <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='false'}"> <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.downloadFolder=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.downloadFolder=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.downloadFolder=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                        </select></td>
                                <td class="p-0"><input type="text" <c:choose><c:when test="${clearanceFormData.itClearanceDto.itSubmitted==true || empCoreDetails.roleType != 'Trantor_IT'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>downloadFolderRemarkIT" value="${clearanceFormData.itClearanceDto.downloadFolderRemark}"   maxlength="199"   /></td>
                            </tr>
                            <tr>
                                <td class="py-0"></td>
                                <td class="py-0">iii) Desktop machine/laptop My Documents Folder</td>
                                <td class="p-0"><select class="mdb-select md-form form-control form-control-sm" name="<portlet:namespace/>documentFolder" <c:if test="${empCoreDetails.roleType == 'Trantor_IT'}"> required="required" </c:if> >
                                            <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='false'}"> <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.documentFolder=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.documentFolder=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.documentFolder=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                        </select></td>
                                <td class="p-0"><input type="text" <c:choose><c:when test="${clearanceFormData.itClearanceDto.itSubmitted==true || empCoreDetails.roleType != 'Trantor_IT'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>documentFolderRemarkIT"  value="${clearanceFormData.itClearanceDto.documentFolderRemark}"   maxlength="199"    /></td>
                            </tr>
                            <tr>
.                                <td class="py-0"></td>
                                <td class="py-0">iv) Any other data on desktop machine/laptop (Specify folder location in remarks)</td>
                                <td class="p-0"><select class="mdb-select md-form form-control form-control-sm" name="<portlet:namespace/>otherData" <c:if test="${empCoreDetails.roleType == 'Trantor_IT'}"> required="required" </c:if> >
                                            <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='false'}"> <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.otherData=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.otherData=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.otherData=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                        </select></td>
                                <td class="p-0"><input type="text" <c:choose><c:when test="${clearanceFormData.itClearanceDto.itSubmitted==true || empCoreDetails.roleType != 'Trantor_IT'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>otherDataRemarkIT" value="${clearanceFormData.itClearanceDto.otherDataRemark}"   maxlength="199"    /></td>
                            </tr>
                            <tr>
                                <td class="py-0"></td>
                                <td class="py-0">v) Google Drive</td>
                                <td class="p-0"><select class="mdb-select md-form form-control form-control-sm" name="<portlet:namespace/>googleDrive" <c:if test="${empCoreDetails.roleType == 'Trantor_IT'}"> required="required" </c:if> >
                                            <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='false'}"> <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.googleDrive=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.googleDrive=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.googleDrive=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                       clearanceFormData
                                <td class="p-0"><input type="text" <c:choose><c:when test="${clearanceFormData.itClearanceDto.itSubmitted==true || empCoreDetails.roleType != 'Trantor_IT'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>googleDriveRemarkIT"  value="${clearanceFormData.itClearanceDto.googleDriveRemark}"   maxlength="199"   /></td>
                            </tr>
                            <tr>
                                <td class="py-0"></td>
                                <td class="py-0">vi)  Any other (Mention here)</td>
                                <td class="p-0"><select class="mdb-select md-form form-control form-control-sm" name="<portlet:namespace/>others" <c:if test="${empCoreDetails.roleType == 'Trantor_IT'}"> required="required" </c:if> >
                                            <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='false'}"> <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.others=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.others=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.others=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                        </select></td>
                                <td class="p-0"><input type="text" <c:choose><c:when test="${clearanceFormData.itClearanceDto.itSubmitted==true || empCoreDetails.roleType != 'Trantor_IT'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>othersRemarkIT" value="${clearanceFormData.itClearanceDto.othersRemark}"   maxlength="199"    /></td>
                            </tr>
                             <tr>
                                 <td class="py-0">b)</td>
                                 <td class="py-0">Mail Forwarding done</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm" name="<portlet:namespace/>mailForwarding" <c:if test="${empCoreDetails.roleType == 'Trantor_IT'}"> required="required" </c:if> >
                                           <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.mailForwarding=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.mailForwarding=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.mailForwarding=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                         </select></td>
                                 <td class="p-0"><input type="text" <c:choose><c:when test="${clearanceFormData.itClearanceDto.itSubmitted==true || empCoreDetails.roleType != 'Trantor_IT'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>mailForwardingRemarkIT" value="${clearanceFormData.itClearanceDto.mailForwardingRemark}"   maxlength="199"    /></td>
                             </tr>
                             <tr>
                                 <td class="py-0">c)</td>
                                 <td class="py-0">Email Address disabled</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm" name="<portlet:namespace/>emailDisable" <c:if test="${empCoreDetails.roleType == 'Trantor_IT'}"> required="required" </c:if> >
                                            <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='false'}"> <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.emailDisable=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.emailDisable=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.emailDisable=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                         </select></td>
                                 <td class="p-0"><input type="text" <c:choose><c:when test="${clearanceFormData.itClearanceDto.itSubmitted==true || empCoreDetails.roleType != 'Trantor_IT'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>emailDisableRemark" value="${clearanceFormData.itClearanceDto.emailDisableRemark}"   maxlength="199"    /></td>
                             </tr>
                             <tr>
                                 <td class="py-0">d)</td>
                                 <td class="py-0">Trantor Desktop/Laptop recovered</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm" name="<portlet:namespace/>systemRecovered" <c:if test="${empCoreDetails.roleType == 'Trantor_IT'}"> required="required" </c:if> >
                                            <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='false'}"> <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.systemRecovered=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.systemRecovered=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.systemRecovered=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                         </select></td>
                                 <td class="p-0"><input type="text" <c:choose><c:when test="${clearanceFormData.itClearanceDto.itSubmitted==true || empCoreDetails.roleType != 'Trantor_IT'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>systemRecoveredRemark" value="${clearanceFormData.itClearanceDto.systemRecoveredRemark}"   maxlength="199"    /></td>
                             </tr>
                            <tr>
                                 <td class="py-0">d)</td>
                                 <td class="py-0">Client Asset Recovered(Confirmed by Manager/HR)</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm" name="<portlet:namespace/>clientSystemRecovered" <c:if test="${empCoreDetails.roleType == 'Trantor_IT'}"> required="required" </c:if> >
                                            <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='false'}"> <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.clientSystemRecovered=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.clientSystemRecovered=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.clientSystemRecovered=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                         </select></td>
                                 <td class="p-0"><input type="text" <c:choose><c:when test="${clearanceFormData.itClearanceDto.itSubmitted==true || empCoreDetails.roleType != 'Trantor_IT'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>clientSystemRecoveredRemark" value="${clearanceFormData.itClearanceDto.clientSystemRecoveredRemark}"   maxlength="199"    /></td>
                             </tr>
                             <tr>
                                 <td class="py-0">e)</td>
                                 <td class="py-0">Access card disabled</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm" name="<portlet:namespace/>accessCardDisable" <c:if test="${empCoreDetails.roleType == 'Trantor_IT'}"> required="required" </c:if> >
                                            <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='false'}"> <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.accessCardDisable=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.accessCardDisable=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.accessCardDisable=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                         </select></td>
                                 <td class="p-0"><input type="text" <c:choose><c:when test="${clearanceFormData.itClearanceDto.itSubmitted==true || empCoreDetails.roleType != 'Trantor_IT'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>accessCardDisableRemark" value="${clearanceFormData.itClearanceDto.accessCardDisableRemark}" maxlength="199"   /></td>
                             </tr>
                             <tr>
                                 <td class="py-0">f)</td>
                                 <td class="py-0">All Separation requirements have been completed ?</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm" name="<portlet:namespace/>separationDocument" <c:if test="${empCoreDetails.roleType == 'Trantor_IT'}"> required="required" </c:if> >
                                            <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='false'}"> <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.separationDocument=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.separationDocument=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.separationDocument=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                         </select></td>
                                 <td class="p-0"><input type="text" <c:choose><c:when test="${clearanceFormData.itClearanceDto.itSubmitted==true || empCoreDetails.roleType != 'Trantor_IT'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>separationDocumentRemark" value="${clearanceFormData.itClearanceDto.separationDocumentRemark}" maxlength="199"   /></td>
                             </tr>
                        </tbody>
                      </table>
                      <table  class="w-100 table table-sm table-bordered table-striped  mb-0">
                        <thead class="thead-dark">
                            <tr>
                                <th width="10%">
                         <c:if test = "${clearanceFormData.itClearanceDto.viewMode == 'true'}">
                               <button type="button" class="btn btn-sm btn-outline-success py-0" title="Click on + button to add new recovery item"  onclick="addItRows()">
                               <i class="far fa-plus-square"></i>
                               </button>
                         </c:if>
                         <c:if test = "${clearanceFormData.itClearanceDto.viewMode == 'false'}">S No
                         </c:if>
                                </th>
                                <th width="30%">Item</th>
                                <th width="10%">Recovery to be done (Yes/No)</th>
                                <th width="10%">Recovery Amount  (&#8377;)</th>
                                <c:if test="${clearanceFormData.hrSubmitted=='true'}">
                                <th width="10%">Approved/Disapproved</th>
                                 </c:if>
                            </tr>
                        </thead>
                        <tbody id="itTableBodyRows">
                            <tr>
                                <td class="py-0">a)</td>
                                <td class="py-0">Desktop</td>
                                <td class="p-0"><select class="mdb-select md-form form-control form-control-sm fn_enableDisable " id="systemRecoveryAmtStatus" name="<portlet:namespace/>systemRecoveryAmtStatus" <c:if test="${empCoreDetails.roleType == 'Trantor_IT'}"> required="required" </c:if> >
                                            <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='false'}"> <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.systemRecoveryAmtStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.systemRecoveryAmtStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                        </select></td>
                                <td class="p-0"><input type="number" step="0.01" <c:choose><c:when test="${clearanceFormData.itClearanceDto.itSubmitted==true || empCoreDetails.roleType != 'Trantor_IT' || clearanceFormData.itClearanceDto.systemRecoveryAmtStatus!='1'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> id="systemRecoveryAmt" name="<portlet:namespace/>systemRecoveryAmt" value="${clearanceFormData.itClearanceDto.systemRecoveryAmt}" onblur="roundOfTwoDecimal(this)"  /></td>
                              <c:if test="${clearanceFormData.hrSubmitted=='true'}">
                           <td class="p-0"><c:if test="${clearanceFormData.itClearanceDto.systemRecoveryAmtStatus =='1'}"><input type="text" class="form-control form-control-sm " readonly title="${clearanceFormData.itClearanceDto.commentSystemRecovery}"  <c:if test="${clearanceFormData.itClearanceDto.approvedSystemRecovery=='true'}"> value="Approved" </c:if><c:if test="${clearanceFormData.itClearanceDto.approvedSystemRecovery=='false'}"> value="Disapproved" </c:if>   /> </c:if></td>

                               </c:if>
                            </tr>
                            <tr>
                                <td class="py-0">b)</td>
                                <td class="py-0">Laptop</td>
                                <td class="p-0"><select class="mdb-select md-form form-control form-control-sm fn_enableDisable" id="laptopRecoveryAmtStatus" name="<portlet:namespace/>laptopRecoveryAmtStatus" <c:if test="${empCoreDetails.roleType == 'Trantor_IT'}"> required="required" </c:if> >
                                            <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='false'}"> <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.laptopRecoveryAmtStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.laptopRecoveryAmtStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                        </select></td>
                                <td class="p-0"><input type="number" step="0.01" <c:choose><c:when test="${clearanceFormData.itClearanceDto.itSubmitted==true || empCoreDetails.roleType != 'Trantor_IT' || clearanceFormData.itClearanceDto.laptopRecoveryAmtStatus!='1'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose>  id="laptopRecoveryAmt" name="<portlet:namespace/>laptopRecoveryAmt" value="${clearanceFormData.itClearanceDto.laptopRecoveryAmt}" onblur="roundOfTwoDecimal(this)"  /></td>
                              <c:if test="${clearanceFormData.hrSubmitted=='true'}">
                           <td class="p-0"><c:if test="${clearanceFormData.itClearanceDto.laptopRecoveryAmtStatus =='1'}"><input type="text" class="form-control form-control-sm " readonly title="${clearanceFormData.itClearanceDto.commentLaptopRecovery}" <c:if test="${clearanceFormData.itClearanceDto.approvedLaptopRecovery=='true'}"> value="Approved" </c:if><c:if test="${clearanceFormData.itClearanceDto.approvedLaptopRecovery=='false'}"> value="Disapproved" </c:if>   /> </c:if></td>

                               </c:if>
                            </tr>
                            <tr>
                                <td class="py-0">c)</td>
                                <td class="py-0">Telephone/Mobile/Sim/Data Card</td>
                                <td class="p-0"><select class="mdb-select md-form form-control form-control-sm fn_enableDisable" id="communicationRecoveryStatus" name="<portlet:namespace/>communicationRecoveryStatus" <c:if test="${empCoreDetails.roleType == 'Trantor_IT'}"> required="required" </c:if> >
                                        <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='false'}"> <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.communicationRecoveryStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.communicationRecoveryStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                        </select></td>
                                <td class="p-0"><input type="number" step="0.01"  <c:choose><c:when test="${clearanceFormData.itClearanceDto.itSubmitted==true || empCoreDetails.roleType != 'Trantor_IT' || clearanceFormData.itClearanceDto.communicationRecoveryStatus!='1'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> id="communicationRecoveryAmt" name="<portlet:namespace/>communicationRecoveryAmt" value="${clearanceFormData.itClearanceDto.communicationRecoveryAmt}" onblur="roundOfTwoDecimal(this)"  /></td>
                              <c:if test="${clearanceFormData.hrSubmitted=='true'}">
                           <td class="p-0"><c:if test="${clearanceFormData.itClearanceDto.communicationRecoveryStatus =='1'}"><input type="text" class="form-control form-control-sm " readonly title="${clearanceFormData.itClearanceDto.commentCommunicationRecovery}"  <c:if test="${clearanceFormData.itClearanceDto.approvedCommunicationRecovery=='true'}"> value="Approved" </c:if><c:if test="${clearanceFormData.itClearanceDto.approvedCommunicationRecovery=='false'}"> value="Disapproved" </c:if>   /> </c:if></td>

                               </c:if>
                            </tr>
                             <tr>
                                 <td class="py-0">d)</td>
                                 <td class="py-0">Headphone</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm fn_enableDisable" id="headphoneRecoveryAmtStatus" name="<portlet:namespace/>headphoneRecoveryAmtStatus" <c:if test="${empCoreDetails.roleType == 'Trantor_IT'}"> required="required" </c:if> >
                                         <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='false'}"> <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.headphoneRecoveryAmtStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.headphoneRecoveryAmtStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                         </select></td>
                                 <td class="p-0"><input type="number" step="0.01" <c:choose><c:when test="${clearanceFormData.itClearanceDto.itSubmitted==true || empCoreDetails.roleType != 'Trantor_IT' || clearanceFormData.itClearanceDto.headphoneRecoveryAmtStatus!='1'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> id="headphoneRecoveryAmt" name="<portlet:namespace/>headphoneRecoveryAmt" value="${clearanceFormData.itClearanceDto.headphoneRecoveryAmt}"  onblur="roundOfTwoDecimal(this)"  /></td>
                              <c:if test="${clearanceFormData.hrSubmitted=='true'}">
                           <td class="p-0"><c:if test="${clearanceFormData.itClearanceDto.headphoneRecoveryAmtStatus =='1'}"><input type="text" class="form-control form-control-sm " readonly title="${clearanceFormData.itClearanceDto.commentHeadphoneRecovery}" <c:if test="${clearanceFormData.itClearanceDto.approvedHeadphoneRecovery=='true'}"> value="Approved" </c:if> <c:if test="${clearanceFormData.itClearanceDto.approvedHeadphoneRecovery=='false'}"> value="Disapproved" </c:if>   /> </c:if></td>

                               </c:if>
                             </tr>
                             <tr>
                                 <td class="py-0">e)</td>
                                 <td class="py-0">Laptop Bag</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm fn_enableDisable" id="laptopBagRecoveryAmtStatus" name="<portlet:namespace/>laptopBagRecoveryAmtStatus" <c:if test="${empCoreDetails.roleType == 'Trantor_IT'}"> required="required" </c:if> >
                                         <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='false'}"> <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.laptopBagRecoveryAmtStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.laptopBagRecoveryAmtStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                         </select></td>
                                 <td class="p-0"><input type="number" step="0.01" <c:choose><c:when test="${clearanceFormData.itClearanceDto.itSubmitted==true || empCoreDetails.roleType != 'Trantor_IT' || clearanceFormData.itClearanceDto.laptopBagRecoveryAmtStatus!='1'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> id="laptopBagRecoveryAmt" name="<portlet:namespace/>laptopBagRecoveryAmt" value="${clearanceFormData.itClearanceDto.laptopBagRecoveryAmt}" onblur="roundOfTwoDecimal(this)" /></td>
                              <c:if test="${clearanceFormData.hrSubmitted=='true'}">
                           <td class="p-0"><c:if test="${clearanceFormData.itClearanceDto.laptopBagRecoveryAmtStatus =='1'}"><input type="text" class="form-control form-control-sm " readonly title="${clearanceFormData.itClearanceDto.commentLaptopBagRecovery}"  <c:if test="${clearanceFormData.itClearanceDto.approvedLaptopBagRecovery=='true'}"> value="Approved" </c:if><c:if test="${clearanceFormData.itClearanceDto.approvedLaptopBagRecovery=='true'}"> value="Disapproved" </c:if>   /> </c:if></td>

                               </c:if>
                             </tr>
                             <tr>
                                 <td class="py-0">f)</td>
                                 <td class="py-0">Monitor</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm fn_enableDisable" id="monitorRecoveryAmtStatus" name="<portlet:namespace/>monitorRecoveryAmtStatus" <c:if test="${empCoreDetails.roleType == 'Trantor_IT'}"> required="required" </c:if> >
                                         <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='false'}"> <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.monitorRecoveryAmtStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.monitorRecoveryAmtStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                         </select></td>
                                 <td class="p-0"><input type="number" step="0.01" <c:choose><c:when test="${clearanceFormData.itClearanceDto.itSubmitted==true || empCoreDetails.roleType != 'Trantor_IT' || clearanceFormData.itClearanceDto.monitorRecoveryAmtStatus!='1'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> id="monitorAmt" name="<portlet:namespace/>monitorAmt" value="${clearanceFormData.itClearanceDto.monitorAmt}" onblur="roundOfTwoDecimal(this)"  /></td>
                              <c:if test="${clearanceFormData.hrSubmitted=='true'}">
                           <td class="p-0"><c:if test="${clearanceFormData.itClearanceDto.monitorRecoveryAmtStatus =='1'}"><input type="text" class="form-control form-control-sm " readonly title="${clearanceFormData.itClearanceDto.commentMonitorRecovery}"  <c:if test="${clearanceFormData.itClearanceDto.approvedMonitorRecovery=='true'}"> value="Approved" </c:if><c:if test="${clearanceFormData.itClearanceDto.approvedMonitorRecovery=='false'}"> value="Disapproved" </c:if>   /> </c:if></td>

                               </c:if>
                             </tr>
                                 <td class="py-0">g)</td>
                                 <td class="py-0">Charger</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm fn_enableDisable" id="chargerRecoveryAmtStatus" name="<portlet:namespace/>chargerRecoveryAmtStatus" <c:if test="${empCoreDetails.roleType == 'Trantor_IT'}"> required="required" </c:if> >
                                         <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='false'}"> <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.chargerRecoveryAmtStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.chargerRecoveryAmtStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                         </select></td>
                                 <td class="p-0"><input type="number" step="0.01" <c:choose><c:when test="${clearanceFormData.itClearanceDto.itSubmitted==true || empCoreDetails.roleType != 'Trantor_IT' || clearanceFormData.itClearanceDto.chargerRecoveryAmtStatus!='1'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> id="chargerAmt" name="<portlet:namespace/>chargerAmt" value="${clearanceFormData.itClearanceDto.chargerAmt}" onblur="roundOfTwoDecimal(this)"  /></td>
                              <c:if test="${clearanceFormData.hrSubmitted=='true'}">
                           <td class="p-0"><c:if test="${clearanceFormData.itClearanceDto.chargerRecoveryAmtStatus =='1'}"><input type="text" class="form-control form-control-sm " readonly title="${clearanceFormData.itClearanceDto.commentChargerRecovery}" <c:if test="${clearanceFormData.itClearanceDto.approvedChargerRecovery=='true'}"> value="Approved" </c:if><c:if test="${clearanceFormData.itClearanceDto.approvedChargerRecovery=='false'}"> value="Disapproved" </c:if>   /> </c:if></td>

                               </c:if>
                             </tr>
                             <tr>
                                 <td class="py-0">h)</td>
                                 <td class="py-0">Mouse</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm fn_enableDisable" id="mouseRecoveryAmtStatus" name="<portlet:namespace/>mouseRecoveryAmtStatus" <c:if test="${empCoreDetails.roleType == 'Trantor_IT'}"> required="required" </c:if> >
                                         <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='false'}"> <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.mouseRecoveryAmtStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.itClearanceDto.mouseRecoveryAmtStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || empCoreDetails.roleType != 'Trantor_IT'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                         </select></td>
                                 <td class="p-0"><input type="number" step="0.01" <c:choose><c:when test="${clearanceFormData.itClearanceDto.itSubmitted==true || empCoreDetails.roleType != 'Trantor_IT' || clearanceFormData.itClearanceDto.mouseRecoveryAmtStatus!='1'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> id="mouseRecoveryAmt" name="<portlet:namespace/>mouseRecoveryAmt" value="${clearanceFormData.itClearanceDto.mouseRecoveryAmt}" onblur="roundOfTwoDecimal(this)"  /></td>
                              <c:if test="${clearanceFormData.hrSubmitted=='true'}">
                           <td class="p-0"><c:if test="${clearanceFormData.itClearanceDto.mouseRecoveryAmtStatus =='1'}"><input type="text" class="form-control form-control-sm " readonly title="${clearanceFormData.itClearanceDto.commentMouseRecovery}" <c:if test="${clearanceFormData.itClearanceDto.approvedMouseRecovery=='true'}"> value="Approved" </c:if><c:if test="${clearanceFormData.itClearanceDto.approvedMouseRecovery=='false'}"> value="Disapproved" </c:if>   /></c:if></td>

                               </c:if>
                             </tr>
                           <c:if test="${itRecoveryFormData.size() > 0}">
                            <c:forEach items="${itRecoveryFormData}" var="item" varStatus="count">
                            <tr>
                            <td class="py-0 ">
                             <c:choose>
                             <c:when test="${clearanceFormData.itClearanceDto.itSubmitted=='false'}">
                             <button type="button" id="it${count.index+8}" onclick="removeRowAndResetItIndex(this)"  class="btn-danger"><i class="far fa-trash-alt" style="cursor: pointer"></i></button>
                             </c:when>
                             <c:otherwise>
                             &\#${(count.index+97+8)})
                             </c:otherwise>
                             </c:choose>
                             </td>
                            <td class="p-0"><input type="text"   <c:choose><c:when test="${clearanceFormData.itClearanceDto.itSubmitted==true}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> value="${item.recoveryItem}" id="itItem${count.index+8}" name="<portlet:namespace/>itItem${count.index+8}"  <c:if test="${empCoreDetails.roleType == 'Trantor_IT'}"> required="required" </c:if>   /></td>
                            <td class="p-0"><select class="mdb-select md-form form-control form-control-sm fn_enableDisable" name="<portlet:namespace/>itRecoveryStatus${count.index+8}" <c:if test="${empCoreDetails.roleType == 'Trantor_IT'}"> required="required" </c:if>  >
                            <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                             <option <c:choose><c:when test="${item.recoveryStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                             <option <c:choose><c:when test="${item.recoveryStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                             <option <c:choose><c:when test="${item.recoveryStatus=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                               </select></td>
                            <td class="p-0"><input type="number" step="0.01"  <c:choose><c:when test="${clearanceFormData.itClearanceDto.itSubmitted==true || item.recoveryStatus!='1'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> value="${item.recoveryAmount}" id="itRecoveryAmount${count.index+8}" name="<portlet:namespace/>itRecoveryAmount${count.index+8}" <c:if test="${empCoreDetails.roleType == 'Trantor_IT'}"> required="required" </c:if> onblur="roundOfTwoDecimal(this)"  /></td>
                              <c:if test="${clearanceFormData.hrSubmitted=='true'}">
                           <td class="p-0"><c:if test="${item.recoveryStatus =='1'}"><input type="text" class="form-control form-control-sm " readonly title="${item.comment}"  <c:if test="${item.approved=='true'}"> value="Approved" </c:if><c:if test="${item.approved=='false'}"> value="Disapproved" </c:if>   /></c:if></td>

                               </c:if>
                            </tr>
                            </c:forEach>
                           </c:if>
                        </tbody>
                      </table>
                       <div class="carousel-caption d-none d-md-block bg-dark"><h6 class="pb-3">IT Form</h6></div>
                    </div>
                    </c:if>
                    <c:if test="${empCoreDetails.roleType == 'Trantor_HR' || empCoreDetails.roleType == 'Trantor_Finance' ||empCoreDetails.roleType == 'Trantor_Admin'  }">
                    <div class="${empCoreDetails.roleType == 'Trantor_Admin' ? 'carousel-item active' : 'carousel-item'}" >
                      <table  class="w-100 table table-sm table-bordered table-striped  mb-0">
                        <thead class="thead-dark">
                            <tr>
                            <th class="text-center align-middle" width="10%">
                         <c:if test = "${clearanceFormData.adminClearanceDto.viewMode == 'true'}">
                               <button type="button" class="btn btn-sm btn-outline-success py-0" title="Click on + button to add new recovery item"  onclick="addAdminRows()">
                               <i class="far fa-plus-square"></i>
                               </button>
                         </c:if>
                         <c:if test = "${clearanceFormData.adminClearanceDto.viewMode == 'false'}">S No
                         </c:if>
                         </th>
                                <th width="30%">Item</th>
                                <th width="10%">Recovery to be done (Yes/No)</th>
                                <th width="10%">Recovery Amount   (&#8377;)</th>
                                <c:if test="${clearanceFormData.hrSubmitted=='true'}">
                                <th width="10%">Approved/Disapproved</th>
                                 </c:if>
                            </tr>
                        </thead>
                        <tbody id="adminBodyRows">
                            <tr>
                                <td class="py-0">a)</td>
                                <td class="py-0">Stationary</td>
                                <td class="p-0"><select class="mdb-select md-form form-control form-control-sm fn_enableDisable" name="<portlet:namespace/>stationaryRecoveryAmtStatus" id="stationaryRecoveryAmtStatus"  <c:if test="${empCoreDetails.roleType == 'Trantor_Admin'}"> required="required" </c:if> >
                                          <c:if test="${clearanceFormData.adminClearanceDto.adminSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.adminClearanceDto.stationaryRecoveryAmtStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.adminClearanceDto.adminSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Admin'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.adminClearanceDto.stationaryRecoveryAmtStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.adminClearanceDto.adminSubmitted=='true' ||empCoreDetails.roleType != 'Trantor_Admin'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                        </select></td>
                                <td class="p-0"><input type="number" step="0.01"  <c:choose><c:when test="${clearanceFormData.adminClearanceDto.adminSubmitted==true ||empCoreDetails.roleType != 'Trantor_Admin' || clearanceFormData.adminClearanceDto.stationaryRecoveryAmtStatus!='1'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose>  name="<portlet:namespace/>stationaryRecoveryAmt" id="stationaryRecoveryAmt" value="${clearanceFormData.adminClearanceDto.stationaryRecoveryAmt}" onblur="roundOfTwoDecimal(this)"  /></td>
                              <c:if test="${clearanceFormData.hrSubmitted=='true'}">
                           <td class="p-0"><input <c:if test="${clearanceFormData.adminClearanceDto.stationaryRecoveryAmtStatus=='1'}"> type="text" class="form-control form-control-sm " readonly title="${clearanceFormData.adminClearanceDto.commentStationaryRecovery}" <c:if test="${clearanceFormData.adminClearanceDto.approvedStationaryRecovery=='true'}"> value="Approved" </c:if><c:if test="${clearanceFormData.adminClearanceDto.approvedStationaryRecovery=='false'}"> value="Disapproved" </c:if>   /></c:if> </td>

                               </c:if>
                            </tr>
                            <tr>
                                <td class="py-0">b)</td>
                                <td class="py-0">Keys</td>
                                <td class="p-0"><select class="mdb-select md-form form-control form-control-sm fn_enableDisable" name="<portlet:namespace/>keysRecoveryAmtStatus" id="keysRecoveryAmtStatus"   <c:if test="${empCoreDetails.roleType == 'Trantor_Admin'}"> required="required" </c:if> >
                                          <c:if test="${clearanceFormData.adminClearanceDto.adminSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.adminClearanceDto.keysRecoveryAmtStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.adminClearanceDto.adminSubmitted=='true' ||empCoreDetails.roleType != 'Trantor_Admin'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.adminClearanceDto.keysRecoveryAmtStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.adminClearanceDto.adminSubmitted=='true' ||empCoreDetails.roleType != 'Trantor_Admin'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                        </select></td>
                                <td class="p-0"><input type="number" step="0.01" <c:choose><c:when test="${clearanceFormData.adminClearanceDto.adminSubmitted==true ||empCoreDetails.roleType != 'Trantor_Admin' || clearanceFormData.adminClearanceDto.keysRecoveryAmtStatus!='1'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>keysRecoveryAmt" id="keysRecoveryAmt" value="${clearanceFormData.adminClearanceDto.keysRecoveryAmt}" onblur="roundOfTwoDecimal(this)"  /></td>
                              <c:if test="${clearanceFormData.hrSubmitted=='true'}">
                           <td class="p-0"><c:if test="${clearanceFormData.adminClearanceDto.keysRecoveryAmtStatus=='1'}"><input type="text" class="form-control form-control-sm " readonly title="${clearanceFormData.adminClearanceDto.commentKeysRecovery}" <c:if test="${clearanceFormData.adminClearanceDto.approvedKeysRecovery=='true'}"> value="Approved" </c:if><c:if test="${clearanceFormData.adminClearanceDto.approvedKeysRecovery=='false'}"> value="Disapproved" </c:if>   /></c:if> </td>

                               </c:if>
                            </tr>
                             <tr>
                                 <td class="py-0">c)</td>
                                 <td class="py-0">Library</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm fn_enableDisable" id="libraryRecoveryAmtStatus" name="<portlet:namespace/>libraryRecoveryAmtStatus"  <c:if test="${empCoreDetails.roleType == 'Trantor_Admin'}"> required="required" </c:if>  >
                                          <c:if test="${clearanceFormData.adminClearanceDto.adminSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.adminClearanceDto.libraryRecoveryAmtStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.adminClearanceDto.adminSubmitted=='true' ||empCoreDetails.roleType != 'Trantor_Admin'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.adminClearanceDto.libraryRecoveryAmtStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.adminClearanceDto.adminSubmitted=='true' ||empCoreDetails.roleType != 'Trantor_Admin'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                         </select></td>
                                 <td class="p-0"><input type="number" step="0.01" <c:choose><c:when test="${clearanceFormData.adminClearanceDto.adminSubmitted==true ||empCoreDetails.roleType != 'Trantor_Admin' || clearanceFormData.adminClearanceDto.libraryRecoveryAmtStatus!='1'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>libraryRecoveryAmt" id="libraryRecoveryAmt"  value="${clearanceFormData.adminClearanceDto.libraryRecoveryAmt}" onblur="roundOfTwoDecimal(this)" /></td>
                              <c:if test="${clearanceFormData.hrSubmitted=='true'}">
                           <td class="p-0"><c:if test="${clearanceFormData.adminClearanceDto.libraryRecoveryAmtStatus=='1'}"><input type="text" class="form-control form-control-sm " readonly title="${clearanceFormData.adminClearanceDto.commentLibraryRecovery}" <c:if test="${clearanceFormData.adminClearanceDto.approvedLibraryRecovery=='true'}"> value="Approved" </c:if><c:if test="${clearanceFormData.adminClearanceDto.approvedLibraryRecovery=='false'}"> value="Disapproved" </c:if>   /></c:if> </td>

                               </c:if>
                             </tr>
                             <tr>
                                 <td class="py-0">d)</td>
                                 <td class="py-0">Sports equipment issued</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm fn_enableDisable" id="sportsRecoveryAmtStatus" name="<portlet:namespace/>sportsRecoveryAmtStatus"  <c:if test="${empCoreDetails.roleType == 'Trantor_Admin'}"> required="required" </c:if> >
                                          <c:if test="${clearanceFormData.adminClearanceDto.adminSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.adminClearanceDto.sportsRecoveryAmtStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.adminClearanceDto.adminSubmitted=='true' ||empCoreDetails.roleType != 'Trantor_Admin'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.adminClearanceDto.sportsRecoveryAmtStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.adminClearanceDto.adminSubmitted=='true' ||empCoreDetails.roleType != 'Trantor_Admin'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                         </select></td>
                                 <td class="p-0"><input type="number" step="0.01" <c:choose><c:when test="${clearanceFormData.adminClearanceDto.adminSubmitted==true ||empCoreDetails.roleType != 'Trantor_Admin' || clearanceFormData.adminClearanceDto.sportsRecoveryAmtStatus!='1'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> id="sportsRecoveryAmt" name="<portlet:namespace/>sportsRecoveryAmt" value="${clearanceFormData.adminClearanceDto.sportsRecoveryAmt}" onblur="roundOfTwoDecimal(this)"   /></td>
                              <c:if test="${clearanceFormData.hrSubmitted=='true'}">
                           <td class="p-0"><c:if test="${clearanceFormData.adminClearanceDto.sportsRecoveryAmtStatus=='1'}"><input type="text" class="form-control form-control-sm " readonly title="${clearanceFormData.adminClearanceDto.commentSportsRecovery}"  <c:if test="${clearanceFormData.adminClearanceDto.approvedSportsRecovery=='true'}"> value="Approved" </c:if> <c:if test="${clearanceFormData.adminClearanceDto.approvedSportsRecovery=='false'}">  value="Disapproved" </c:if>   /> </c:if> </td>

                               </c:if>
                             </tr>
                             <tr>
                                 <td class="py-0">e)</td>
                                 <td class="py-0">Infrastructure Issued</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm fn_enableDisable"  id="infrastructureIssuedAmtStatus" name="<portlet:namespace/>infrastructureIssuedAmtStatus"  <c:if test="${empCoreDetails.roleType == 'Trantor_Admin'}"> required="required" </c:if> >
                                          <c:if test="${clearanceFormData.adminClearanceDto.adminSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.adminClearanceDto.infrastructureIssuedAmtStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.adminClearanceDto.adminSubmitted=='true' ||empCoreDetails.roleType != 'Trantor_Admin'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.adminClearanceDto.infrastructureIssuedAmtStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.adminClearanceDto.adminSubmitted=='true' ||empCoreDetails.roleType != 'Trantor_Admin'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                         </select></td>
                                 <td class="p-0"><input type="number" step="0.01" <c:choose><c:when test="${clearanceFormData.adminClearanceDto.adminSubmitted==true ||empCoreDetails.roleType != 'Trantor_Admin' || clearanceFormData.adminClearanceDto.infrastructureIssuedAmtStatus!='1'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose>  id="infrastructureIssuedAmt" name="<portlet:namespace/>infrastructureIssuedAmt" value="${clearanceFormData.adminClearanceDto.infrastructureIssuedAmt}" onblur="roundOfTwoDecimal(this)" /></td>
                              <c:if test="${clearanceFormData.hrSubmitted=='true'}">
                           <td class="p-0"><c:if test="${clearanceFormData.adminClearanceDto.infrastructureIssuedAmtStatus=='1'}"><input type="text" class="form-control form-control-sm " readonly title="${clearanceFormData.adminClearanceDto.commentInfrastructureIssuedRecovery}" <c:if test="${clearanceFormData.adminClearanceDto.approvedInfrastructureIssuedRecovery=='true'}"> value="Approved" </c:if><c:if test="${clearanceFormData.adminClearanceDto.approvedInfrastructureIssuedRecovery=='false'}"> value="Disapproved" </c:if>    /></c:if> </td>

                               </c:if>
                             </tr>
                             <tr>
                                 <td class="py-0">f)</td>
                                 <td class="py-0">Lunch Deduction</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm fn_enableDisable" id="lunchDeductionAmtStatus" name="<portlet:namespace/>lunchDeductionAmtStatus" <c:if test="${empCoreDetails.roleType == 'Trantor_Admin'}"> required="required" </c:if> >
                                          <c:if test="${clearanceFormData.adminClearanceDto.adminSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.adminClearanceDto.lunchDeductionAmtStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.adminClearanceDto.adminSubmitted=='true' ||empCoreDetails.roleType != 'Trantor_Admin'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.adminClearanceDto.lunchDeductionAmtStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.adminClearanceDto.adminSubmitted=='true' ||empCoreDetails.roleType != 'Trantor_Admin'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                         </select></td>
                                 <td class="p-0"><input type="number" step="0.01" <c:choose><c:when test="${clearanceFormData.adminClearanceDto.adminSubmitted==true ||empCoreDetails.roleType != 'Trantor_Admin' || clearanceFormData.adminClearanceDto.lunchDeductionAmtStatus!='1'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> id="lunchDeductionAmt" name="<portlet:namespace/>lunchDeductionAmt" value="${clearanceFormData.adminClearanceDto.lunchDeductionAmt}" onblur="roundOfTwoDecimal(this)" /></td>
                              <c:if test="${clearanceFormData.hrSubmitted=='true'}">
                           <td class="p-0"><c:if test="${clearanceFormData.adminClearanceDto.lunchDeductionAmtStatus=='1'}"><input type="text" class="form-control form-control-sm " readonly title="${clearanceFormData.adminClearanceDto.commentLunchDeductionRecovery}"  <c:if test="${clearanceFormData.adminClearanceDto.approvedLunchDeductionRecovery=='true'}"> value="Approved" </c:if><c:if test="${clearanceFormData.adminClearanceDto.approvedLunchDeductionRecovery=='false'}"> value="Disapproved" </c:if>   /></c:if> </td>

                               </c:if>
                             </tr>
                             <tr>
                                 <td class="py-0">g)</td>
                                 <td class="py-0">Access Card</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm fn_enableDisable" id="accessCardRecoveryAmtStatus" name="<portlet:namespace/>accessCardRecoveryAmtStatus" <c:if test="${empCoreDetails.roleType == 'Trantor_Admin'}"> required="required" </c:if> >
                                         <c:if test="${clearanceFormData.adminClearanceDto.adminSubmitted=='false'}"> <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.adminClearanceDto.accessCardRecoveryAmtStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.adminClearanceDto.adminSubmitted=='true' ||empCoreDetails.roleType != 'Trantor_Admin'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.adminClearanceDto.accessCardRecoveryAmtStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.adminClearanceDto.adminSubmitted=='true' ||empCoreDetails.roleType != 'Trantor_Admin'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                         </select></td>
                                 <td class="p-0"><input type="number" step="0.01" <c:choose><c:when test="${clearanceFormData.adminClearanceDto.adminSubmitted==true ||empCoreDetails.roleType != 'Trantor_Admin' || clearanceFormData.adminClearanceDto.accessCardRecoveryAmtStatus!='1'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> id="accessCardRecoveryAmt" name="<portlet:namespace/>accessCardRecoveryAmt" value="${clearanceFormData.adminClearanceDto.accessCardRecoveryAmt}"  onblur="roundOfTwoDecimal(this)" /></td>
                              <c:if test="${clearanceFormData.hrSubmitted=='true'}">
                           <td class="p-0"><c:if test="${clearanceFormData.adminClearanceDto.accessCardRecoveryAmtStatus=='1'}"><input type="text" class="form-control form-control-sm " readonly title="${clearanceFormData.adminClearanceDto.commentAccessCardRecovery}"   <c:if test="${clearanceFormData.adminClearanceDto.approvedAccessCardRecovery=='true'}"> value="Approved" </c:if><c:if test="${clearanceFormData.adminClearanceDto.approvedAccessCardRecovery=='false'}"> value="Disapproved" </c:if> /></c:if> </td>

                               </c:if>
                             </tr>
                            <tr>
                                 <td class="py-0">h)</td>
                                 <td class="py-0">Identity Card</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm fn_enableDisable" id="identityCardStatus" name="<portlet:namespace/>identityCardStatus" <c:if test="${empCoreDetails.roleType == 'Trantor_Admin'}"> required="required" </c:if>  >
                                           <c:if test="${clearanceFormData.adminClearanceDto.adminSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.adminClearanceDto.identityCardStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.adminClearanceDto.adminSubmitted=='true' ||empCoreDetails.roleType != 'Trantor_Admin'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.adminClearanceDto.identityCardStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.adminClearanceDto.adminSubmitted=='true' ||empCoreDetails.roleType != 'Trantor_Admin'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                                 </select></td>
                                 <td class="p-0"><input type="number" step="0.01" <c:choose><c:when test="${clearanceFormData.adminClearanceDto.adminSubmitted==true ||empCoreDetails.roleType != 'Trantor_Admin' || clearanceFormData.adminClearanceDto.identityCardStatus!='1'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> id="identityCardAmt" name="<portlet:namespace/>identityCardAmt" value="${clearanceFormData.adminClearanceDto.identityCardAmt}"  onblur="roundOfTwoDecimal(this)"   /></td>
                              <c:if test="${clearanceFormData.hrSubmitted=='true'}">
                           <td class="p-0"><c:if test="${clearanceFormData.adminClearanceDto.identityCardStatus=='1'}"><input type="text" class="form-control form-control-sm " readonly   title="${clearanceFormData.adminClearanceDto.commentIdentityCardRecovery}" <c:if test="${clearanceFormData.adminClearanceDto.approvedIdentityCardRecovery=='true'}"> value="Approved" </c:if><c:if test="${clearanceFormData.adminClearanceDto.approvedIdentityCardRecovery=='false'}"> value="Disapproved" </c:if> /></c:if> </td>

                               </c:if>
                            </tr>
                      <c:if test="${adminRecoveryFormData.size() > 0}">
                            <c:forEach items="${adminRecoveryFormData}" var="item" varStatus="count">
                            <tr>
                            <td class="py-0 ">
                             <c:choose>
                             <c:when test="${clearanceFormData.adminClearanceDto.adminSubmitted=='false'}">
                             <button type="button" id="admin${count.index+8}" onclick="removeRowAndResetAdminIndex(this)"  class="btn-danger"><i class="far fa-trash-alt" style="cursor: pointer"></i></button>
                             </c:when>
                             <c:otherwise>
                             &\#${(count.index+97+8)})
                             </c:otherwise>
                             </c:choose>
                             </td>
                            <td class="p-0"><input type="text"   <c:choose><c:when test="${clearanceFormData.adminClearanceDto.adminSubmitted==true}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> value="${item.recoveryItem}" id="adminItem${count.index+8}" name="<portlet:namespace/>adminItem${count.index+8}"  <c:if test="${empCoreDetails.roleType == 'Trantor_Admin'}"> required="required" </c:if>   /></td>
                            <td class="p-0"><select class="mdb-select md-form form-control form-control-sm fn_enableDisable" name="<portlet:namespace/>adminRecoveryStatus${count.index+8}" <c:if test="${empCoreDetails.roleType == 'Trantor_Admin'}"> required="required" </c:if>  >
                            <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                             <option <c:choose><c:when test="${item.recoveryStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.adminClearanceDto.adminSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                             <option <c:choose><c:when test="${item.recoveryStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.adminClearanceDto.adminSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                             <option <c:choose><c:when test="${item.recoveryStatus=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.adminClearanceDto.adminSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                               </select></td>
                            <td class="p-0"><input type="number" step="0.01"  <c:choose><c:when test="${clearanceFormData.adminClearanceDto.adminSubmitted==true || item.recoveryStatus!='1'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> value="${item.recoveryAmount}" id="adminRecoveryAmount${count.index+8}" name="<portlet:namespace/>adminRecoveryAmount${count.index+8}" <c:if test="${empCoreDetails.roleType == 'Trantor_Admin'}"> required="required" </c:if> onblur="roundOfTwoDecimal(this)"  /></td>
                                <c:if test="${clearanceFormData.hrSubmitted=='true'}">
                             <td class="p-0"><c:if test="${item.recoveryStatus=='1'}"><input type="text" class="form-control form-control-sm " readonly title="${item.comment}" <c:if test="${item.approved=='true'}"> value="Approved" </c:if><c:if test="${item.approved=='false'}"> value="Disapproved" </c:if> /></c:if></td>
                                 </c:if>
                            </tr>
                            </c:forEach>
                           </c:if>
                        </tbody>
                      </table>
                       <div class="carousel-caption d-none d-md-block bg-dark"><h6 class="pb-3">Admin Form</h6></div>
                    </div>
                    </c:if>
                  <c:if test="${empCoreDetails.roleType == 'Trantor_HR' || empCoreDetails.roleType == 'Trantor_Finance' }">
                    <div class="${empCoreDetails.roleType == 'Trantor_Finance' ? 'carousel-item active' : 'carousel-item'}" >
                      <table  class="w-100 table table-sm table-bordered table-striped  mb-0">
                        <thead class="thead-dark">
                            <tr>
                                <th width="12%">S No</th>
                                <th width="22%">Item</th>
                                <th width="22%">No. Of Days</th>
                                <th width="22%">Month</th>
                                <th width="22%">Year</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                 <td class="py-0">a)</td>
                                 <td class="py-0">Details of last salary paid</td>
                                 <td class="p-0"><input type="number" class="form-control form-control-sm" value="${clearanceFormData.financeClearanceDto.lastSalaryPaidDays}"   name="<portlet:namespace/>lastSalaryPaidDays"  <c:if test="${empCoreDetails.roleType == 'Trantor_Finance'}"> required="required" </c:if> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> readonly </c:if>  /></td>
                                 <td class="p-0">
                                 <select class="mdb-select md-form form-control form-control-sm" name="<portlet:namespace/>lastSalaryPaidMonth" <c:if test="${empCoreDetails.roleType == 'Trantor_Finance'}"> required="required" </c:if>  >
                                 <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                 <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.lastSalaryPaidMonth=='January'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="January">January</option>
                                 <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.lastSalaryPaidMonth=='February'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="February">February</option>
                                 <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.lastSalaryPaidMonth=='March'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="March">March</option>
                                 <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.lastSalaryPaidMonth=='April'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="April">April</option>
                                 <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.lastSalaryPaidMonth=='May'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="May">May</option>
                                 <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.lastSalaryPaidMonth=='June'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="June">June</option>
                                 <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.lastSalaryPaidMonth=='July'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="July">July</option>
                                 <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.lastSalaryPaidMonth=='August'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="August">August</option>
                                 <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.lastSalaryPaidMonth=='September'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="September">September</option>
                                 <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.lastSalaryPaidMonth=='October'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="October">October</option>
                                 <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.lastSalaryPaidMonth=='November'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="November">November</option>
                                 <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.lastSalaryPaidMonth=='December'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="December">December</option>
                                 <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.lastSalaryPaidMonth=='Nil'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Nil">Nil</option>
                                 </select>
                                 </td>
                                 <td class="p-0">
                                 <select class="mdb-select md-form form-control form-control-sm" name="<portlet:namespace/>lastSalaryPaidYear" <c:if test="${empCoreDetails.roleType == 'Trantor_Finance'}"> required="required" </c:if>  >
                                 <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                 <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.lastSalaryPaidYear=='2023'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2023">2023</option>
                                 <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.lastSalaryPaidYear=='2024'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2024">2024</option>
                                 <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.lastSalaryPaidYear=='2025'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2025">2025</option>
                                 <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.lastSalaryPaidYear=='Nil'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Nil">Nil</option>
                                 </select>
                                 </td>
                            </tr>
                            <tr>
                            	 <td class="bg-info text-white py-0" colspan="5"><strong>Tax Proofs</strong></td>
                            </tr>
                        </tbody>
                      </table>
                      <table  class="w-100 table table-sm table-bordered table-striped  mb-0">
                        <thead class="thead-dark">
                            <tr>
                                <th width="5%">S No</th>
                                <th width="35%">Item</th>
                                <th width="20%">Yes/No/NA</th>
                                <th width="40%">Remarks</th>
                            </tr>
                        </thead>
                        <tbody >
                            <tr>
                                 <td class="py-0">a)</td>
                                 <td class="py-0">Income Tax proofs have been submitted</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm" name="<portlet:namespace/>taxProofStatus" <c:if test="${empCoreDetails.roleType == 'Trantor_Finance'}"> required="required" </c:if>  >
                                           <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.taxProofStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.taxProofStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                            <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.taxProofStatus=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                                 </select></td>
                                 <td class="p-0"><input type="text" class="form-control form-control-sm"   value="${clearanceFormData.financeClearanceDto.taxProofRemark}" name="<portlet:namespace/>taxProofRemark" maxlength="199"  <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> readonly </c:if>  /></td>
                            </tr>
                            <tr>
                            	 <td class="bg-info text-white py-0" colspan="4"><strong>Reimbursements to be made in Full and Final</strong></td>
                            </tr>
                        </tbody>
                      </table>
                      <table  class="w-100 table table-sm table-bordered table-striped  mb-0">
                      <thead       </thead>
                        <thead class="thead-dark">
                            <tr>
                                <th width="5%">
                                <c:if test = "${clearanceFormData.financeClearanceDto.viewMode == 'true'}">
                                  <button type="button" class="btn btn-sm btn-outline-success py-0" title="Click on + button to add new reimbursement item"  onclick="addReimbursementRows()">
                                 <i class="far fa-plus-square"></i>
                                 </button>
                           </c:if>
                           <c:if test = "${clearanceFormData.financeClearanceDto.viewMode == 'false'}">S No
                           </c:if>
                                </th>
                                <th width="60%">Item</th>
                                <th width="15%">Yes/No/NA</th>
                                <th width="20%">Amount   (&#8377;)</th>

                            </tr>

                        <tbody id="reimbursementBody">
                            <tr>
                                 <td class="py-0">a)</td>
                                 <td class="py-0">Reimbursement for Advance Subsidised Food Deducted</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm fn_enableDisable" id="foodReimbursementStatus" name="<portlet:namespace/>foodReimbursementStatus" <c:if test="${empCoreDetails.roleType == 'Trantor_Finance'}"> required="required" </c:if>  >
                                           <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.foodReimbursementStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.foodReimbursementStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                            <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.foodReimbursementStatus=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>

                                              </select>   </td>
                                 <td class="p-0"><input type="number" step = "0.01"  <c:choose><c:when test="${clearanceFormData.financeClearanceDto.financeSubmitted==true || empCoreDetails.roleType != 'Trantor_Finance' || clearanceFormData.financeClearanceDto.foodReimbursementStatus!='1'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> id="foodReimbursementAmt" value="${clearanceFormData.financeClearanceDto.foodReimbursementAmt}" name="<portlet:namespace/>foodReimbursementAmt"  onblur="roundOfTwoDecimal(this)"  /></td>
                            </tr>
                            <c:if test="${reimbursementFormData.size() > 0}">
                            <c:forEach items="${reimbursementFormData}" var="item" varStatus="count">
                            <tr>
                            <td class="py-0 ">
                             <c:choose>
                             <c:when test="${clearanceFormData.financeClearanceDto.financeSubmitted=='false'}">
                             <button type="button"  id="rem${count.index+1}" onclick="removeRowAndResetIndex(this)"  class="btn-danger"><i class="far fa-trash-alt" style="cursor: pointer"></i></button>
                             </c:when>
                             <c:otherwise>
                             &\#${(count.index+97+1)})
                             </c:otherwise>
                             </c:choose>
                             </td>

                            <td class="p-0"><input type="text"   <c:choose><c:when test="${clearanceFormData.financeClearanceDto.financeSubmitted==true}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> value="${item.reimbursementItem}" id="reimbursementItem${count.index+1}" name="<portlet:namespace/>reimbursementItem${count.index+1}" <c:if test="${empCoreDetails.roleType == 'Trantor_Finance'}"> required="required" </c:if>  /></td>
                            <td class="p-0"><select class="mdb-select md-form form-control form-control-sm fn_enableDisable" name="<portlet:namespace/>reimbursementStatus${count.index+1}" id="reimbursementStatus${count.index+1}"  <c:if test="${empCoreDetails.roleType == 'Trantor_Finance'}"> required="required" </c:if>  >
                               <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                               <option <c:choose><c:when test="${item.reimbursementStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                               <option <c:choose><c:when test="${item.reimbursementStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                               <option <c:choose><c:when test="${item.reimbursementStatus=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                  </select></td>
                            <td class="p-0"><input type="number" step="0.01"  <c:choose><c:when test="${clearanceFormData.financeClearanceDto.financeSubmitted==true  || item.reimbursementStatus!='1'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> value="${item.reimbursementAmount}" id="reimbursementAmount${count.index+6}" name="<portlet:namespace/>reimbursementAmount${count.index+1}" <c:if test="${empCoreDetails.roleType == 'Trantor_Finance'}"> required="required" </c:if> onblur="roundOfTwoDecimal(this)" /></td>
                            </tr>
                            </c:forEach>
                           </c:if>
                        </tbody>

                      </table>
                     <div class="bg-info text-white py-0" colspan="4">Recoveries to be made in Full and Final</div>
                      <table  id="chk" class="w-100 table table-sm table-bordered table-striped  mb-0">
                        <thead class="thead-dark">
                            <tr>
                                <th width="5%">
                                <c:if test = "${clearanceFormData.financeClearanceDto.viewMode == 'true'}">
                                 <button type="button" class="btn btn-sm btn-outline-success py-0" title="Click on + button to add new recovery item"  onclick="addNewRow()">
                                 <i class="far fa-plus-square"></i>
                                 </button>
                           </c:if>
                           <c:if test = "${clearanceFormData.financeClearanceDto.viewMode == 'false'}">S No
                           </c:if>
                                </th>
                                <th width="50%">Item</th>
                                <th width="15%">Yes/No/NA</th>
                                <th width="10%">Amount  (&#8377;)</th>
                                <c:if test="${clearanceFormData.hrSubmitted=='true'}">
                                <th width="20%">Approved/Disapproved</th>
                                 </c:if>
                            </tr>
                        </thead>
                        <tbody  id="recoveryBody">
                            <tr>
                                 <td class="py-0">a)</td>
                                 <td class="py-0">Recovery for travel under relocation policy in last one year</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm fn_enableDisable" id="travelRecoveryStatus" name="<portlet:namespace/>travelRecoveryStatus" <c:if test="${empCoreDetails.roleType == 'Trantor_Finance'}"> required="required" </c:if>  >
                                           <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.travelRecoveryStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.travelRecoveryStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                            <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.travelRecoveryStatus=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                                 </select></td>
                                 <td class="p-0"><input type="number" step="0.01"  <c:choose><c:when test="${clearanceFormData.financeClearanceDto.financeSubmitted==true || empCoreDetails.roleType != 'Trantor_Finance' || clearanceFormData.financeClearanceDto.travelRecoveryStatus!='1'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> id="travelRecoveryAmt" value="${clearanceFormData.financeClearanceDto.travelRecoveryAmt}" name="<portlet:namespace/>travelRecoveryAmt" onblur="roundOfTwoDecimal(this)"  /></td>
                                <c:if test="${clearanceFormData.hrSubmitted=='true'}">
                             <td class="p-0"><c:if test="${clearanceFormData.financeClearanceDto.travelRecoveryStatus=='1'}"><input type="text" class="form-control form-control-sm" readonly <c:if test="${clearanceFormData.financeClearanceDto.approvedTravelRecovery=='true'}"> value="Approved" </c:if><c:if test="${clearanceFormData.financeClearanceDto.approvedTravelRecovery=='false'}"> value="Disapproved" </c:if>  title="${clearanceFormData.financeClearanceDto.commentTravelRecovery}"   /> </c:if></td>
                                 </c:if>
                            </tr>
                            <tr>
                                 <td class="py-0">b)</td>
                                 <td class="py-0">Recovery for hotel stay under relocation policy in last one year</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm fn_enableDisable" id="hotelRecoveryStatus" name="<portlet:namespace/>hotelRecoveryStatus" <c:if test="${empCoreDetails.roleType == 'Trantor_Finance'}"> required="required" </c:if>  >
                                           <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.hotelRecoveryStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.hotelRecoveryStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                            <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.hotelRecoveryStatus=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                                 </select></td>
                                 <td class="p-0"><input type="number" step="0.01"  <c:choose><c:when test="${clearanceFormData.financeClearanceDto.financeSubmitted==true || empCoreDetails.roleType != 'Trantor_Finance' || clearanceFormData.financeClearanceDto.hotelRecoveryStatus!='1'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> id="hotelRecoveryAmt" value="${clearanceFormData.financeClearanceDto.hotelRecoveryAmt}" name="<portlet:namespace/>hotelRecoveryAmt" onblur="roundOfTwoDecimal(this)"  /></td>
                                <c:if test="${clearanceFormData.hrSubmitted=='true'}">
                             <td class="p-0"><c:if test="${clearanceFormData.financeClearanceDto.hotelRecoveryStatus=='1'}"><input type="text" class="form-control form-control-sm" readonly <c:if test="${clearanceFormData.financeClearanceDto.approvedHotelRecovery=='true'}"> value="Approved" </c:if><c:if test="${clearanceFormData.financeClearanceDto.approvedHotelRecovery=='false'}"> value="Disapproved" </c:if>  title="${clearanceFormData.financeClearanceDto.commentHotelRecovery}"   /> </c:if></td>
                                 </c:if>
                            </tr>
                            <tr>
                                 <td class="py-0">c)</td>
                                 <td class="py-0">Recovery for International travel (in previous 6 months)</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm fn_enableDisable" id="internationalRecoveryStatus" name="<portlet:namespace/>internationalRecoveryStatus" <c:if test="${empCoreDetails.roleType == 'Trantor_Finance'}"> required="required" </c:if>  >
                                           <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.internationalRecoveryStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.internationalRecoveryStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                            <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.internationalRecoveryStatus=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                                 </select></td>
                                 <td class="p-0"><input type="number" step="0.01"  <c:choose><c:when test="${clearanceFormData.financeClearanceDto.financeSubmitted==true || empCoreDetails.roleType != 'Trantor_Finance' || clearanceFormData.financeClearanceDto.internationalRecoveryStatus!='1'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> id="internationalRecoveryAmt" value="${clearanceFormData.financeClearanceDto.internationalRecoveryAmt}" name="<portlet:namespace/>internationalRecoveryAmt" onblur="roundOfTwoDecimal(this)"  /></td>
                                 <c:if test="${clearanceFormData.hrSubmitted=='true'}">
                              <td class="p-0"><c:if test="${clearanceFormData.financeClearanceDto.internationalRecoveryStatus=='1'}"><input type="text" class="form-control form-control-sm" readonly <c:if test="${clearanceFormData.financeClearanceDto.approvedInternationalRecovery=='true'}"> value="Approved" </c:if><c:if test="${clearanceFormData.financeClearanceDto.approvedInternationalRecovery=='false'}"> value="Approved" </c:if>  title="${clearanceFormData.financeClearanceDto.commentInternationalRecovery}"   /> </c:if></td>
                                  </c:if>
                            </tr>
                            <tr>
                                 <td class="py-0">d)</td>
                                 <td class="py-0">Recovery for Further education Program (in previous 6 months)</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm fn_enableDisable" id="educationRecoveryStatus" name="<portlet:namespace/>educationRecoveryStatus" <c:if test="${empCoreDetails.roleType == 'Trantor_Finance'}"> required="required" </c:if>  >
                                           <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.educationRecoveryStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.educationRecoveryStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                            <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.educationRecoveryStatus=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                                 </select></td>
                                 <td class="p-0"><input type="number" step="0.01"   <c:choose><c:when test="${clearanceFormData.financeClearanceDto.financeSubmitted==true || empCoreDetails.roleType != 'Trantor_Finance' || clearanceFormData.financeClearanceDto.educationRecoveryStatus!='1'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> id="educationRecoveryAmt" value="${clearanceFormData.financeClearanceDto.educationRecoveryAmt}" name="<portlet:namespace/>educationRecoveryAmt"  onblur="roundOfTwoDecimal(this)"  /></td>
                                  <c:if test="${clearanceFormData.hrSubmitted=='true'}">
                               <td class="p-0"><c:if test="${clearanceFormData.financeClearanceDto.educationRecoveryStatus=='1'}"><input type= "text" class="form-control form-control-sm" readonly <c:if test="${clearanceFormData.financeClearanceDto.approvedEducationRecovery=='true'}"> value="Approved" </c:if><c:if test="${clearanceFormData.financeClearanceDto.approvedEducationRecovery=='false'}"> value="Disapproved" </c:if>  title="${clearanceFormData.financeClearanceDto.commentEducationRecovery}"   /> </c:if></td>
                                   </c:if>
                            </tr>
                            <tr id="s">
                                 <td class="py-0">e)</td>
                                 <td class="py-0">Recovery for joining bonus paid to employee (in previous one year)</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm fn_enableDisable" id="joiningBonusRecoveryStatus" name="<portlet:namespace/>joiningBonusRecoveryStatus" <c:if test="${empCoreDetails.roleType == 'Trantor_Finance'}"> required="required" </c:if>  >
                                           <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.joiningBonusRecoveryStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.joiningBonusRecoveryStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                            <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.joiningBonusRecoveryStatus=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                                 </select></td>
                                 <td class="p-0"><input type="number" step="0.01"  <c:choose><c:when test="${clearanceFormData.financeClearanceDto.financeSubmitted==true || empCoreDetails.roleType != 'Trantor_Finance' || clearanceFormData.financeClearanceDto.joiningBonusRecoveryStatus!='1'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> id="joiningBonusRecoveryAmt" value="${clearanceFormData.financeClearanceDto.joiningBonusRecoveryAmt}" name="<portlet:namespace/>joiningBonusRecoveryAmt"  onblur="roundOfTwoDecimal(this)" /></td>
                                      <c:if test="${clearanceFormData.hrSubmitted=='true'}">
                                   <td class="p-0"><c:if test="${clearanceFormData.financeClearanceDto.joiningBonusRecoveryStatus=='1'}"><input type="text" class="form-control form-control-sm" readonly  <c:if test="${clearanceFormData.financeClearanceDto.approvedJoiningRecovery=='true'}"> value="Approved" </c:if> <c:if test="${clearanceFormData.financeClearanceDto.approvedJoiningRecovery=='false'}"> value="Disapproved" </c:if>  title="${clearanceFormData.financeClearanceDto.commentJoiningRecovery}"   /> </c:if></td>
                                       </c:if>
                            </tr>
                            <tr >
                                 <td class="py-0">f)</td>
                                 <td class="py-0">Recovery for Notice period buy out done on behalf of employee to previous employer (in previous one year)</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm fn_enableDisable" id="noticePeriodRecoveryStatusFinance" name="<portlet:namespace/>noticePeriodRecoveryStatusFinance" <c:if test="${empCoreDetails.roleType == 'Trantor_Finance'}"> required="required" </c:if>  >
                                           <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.noticePeriodRecoveryStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.noticePeriodRecoveryStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                            <option <c:choose><c:when test="${clearanceFormData.financeClearanceDto.noticePeriodRecoveryStatus=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true' || empCoreDetails.roleType != 'Trantor_Finance'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                                 </select></td>
                                 <td class="p-0"><input type="number" step="0.01"   <c:choose><c:when test="${clearanceFormData.financeClearanceDto.financeSubmitted==true || empCoreDetails.roleType != 'Trantor_Finance' || clearanceFormData.financeClearanceDto.noticePeriodRecoveryStatus!='1'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> id="noticePeriodRecoveryAmtFinance" value="${clearanceFormData.financeClearanceDto.noticePeriodRecoveryAmt}" name="<portlet:namespace/>noticePeriodRecoveryAmtFinance"  onblur="roundOfTwoDecimal(this)"  /></td>
                                      <c:if test="${clearanceFormData.hrSubmitted=='true'}">
                                   <td class="p-0"><c:if test="${clearanceFormData.financeClearanceDto.noticePeriodRecoveryStatus=='1'}"><input type="text" class="form-control form-control-sm" readonly  <c:if test="${clearanceFormData.financeClearanceDto.approvedNoticePeriodRecovery=='true'}"> value="Approved" </c:if><c:if test="${clearanceFormData.financeClearanceDto.approvedNoticePeriodRecovery=='false'}"> value="Disapproved" </c:if>  title="${clearanceFormData.financeClearanceDto.commentNoticePeriodRecovery}"   /> </c:if></td>
                                       </c:if>
                            </tr>
                            <c:if test="${recoveryFormData.size() > 0}">
                            <c:forEach items="${recoveryFormData}" var="item" varStatus="count">
                            <tr>
                            <td class="py-0 ">
                             <c:choose>
                             <c:when test="${clearanceFormData.financeClearanceDto.financeSubmitted=='false'}">
                             <button type="button" id="rec${count.index+6}" onclick="removeRowAndResetIndex(this)"  class="btn-danger"><i class="far fa-trash-alt" style="cursor: pointer"></i></button>
                             </c:when>
                             <c:otherwise>
                             &\#${(count.index+97+6)})
                             </c:otherwise>
                             </c:choose>
                             </td>
                            <td class="p-0"><input type="text"   <c:choose><c:when test="${clearanceFormData.financeClearanceDto.financeSubmitted==true}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> value="${item.recoveryItem}" id="recoveryItem${count.index+6}" name="<portlet:namespace/>recoveryItem${count.index+6}"  <c:if test="${empCoreDetails.roleType == 'Trantor_Finance'}"> required="required" </c:if>   /></td>
                            <td class="p-0"><select class="mdb-select md-form form-control form-control-sm fn_enableDisable" name="<portlet:namespace/>recoveryStatus${count.index+6}" <c:if test="${empCoreDetails.roleType == 'Trantor_Finance'}"> required="required" </c:if>  >
                            <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                             <option <c:choose><c:when test="${item.recoveryStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                             <option <c:choose><c:when test="${item.recoveryStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                             <option <c:choose><c:when test="${item.recoveryStatus=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                               </select></td>
                            <td class="p-0"><input type="number" step="0.01"  <c:choose><c:when test="${clearanceFormData.financeClearanceDto.financeSubmitted==true || item.recoveryStatus!='1'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> value="${item.recoveryAmount}" id="recoveryAmount${count.index+6}" name="<portlet:namespace/>recoveryAmount${count.index+6}" <c:if test="${empCoreDetails.roleType == 'Trantor_Finance'}"> required="required" </c:if> onblur="roundOfTwoDecimal(this)"  /></td>
                                   <c:if test="${clearanceFormData.hrSubmitted=='true'}">
                                <td class="p-0"><c:if test="${item.recoveryStatus=='1'}"><input type="text" class="form-control form-control-sm" readonly  <c:if test="${item.approved=='true'}"> value="Approved" </c:if> <c:if test="${item.approved=='false'}"> value="Disapproved" </c:if> title="${item.comment}"   /> </c:if></td>
                                    </c:if>
                            </tr>
                            </c:forEach>
                           </c:if>
                        </tbody>
                      </table>
                       <div class="carousel-caption d-none d-md-block bg-dark"><h6 class="pb-3">Finance Form</h6></div>
                    </div>
                    </c:if>
                    <c:if test="${empCoreDetails.roleType == 'Trantor_HR' || empCoreDetails.roleType == 'Trantor_Finance' }">
                    <div class="${empCoreDetails.roleType == 'Trantor_HR' ? 'carousel-item active' : 'carousel-item'}" >
                      <table  class="w-100 table table-sm table-bordered table-striped  mb-0">
                        <thead class="thead-dark">
                            <tr>
                                <th width="10%">S No</th>
                                <th width="30%">Item</th>
                                <th width="20%">Yes/No/NA</th>
                                <th width="40%">Remarks</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                 <td class="py-0">a)</td>
                                 <td class="py-0">Employee food option</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm" name="<portlet:namespace/>foodOption" <c:if test="${empCoreDetails.roleType == 'Trantor_HR'}"> required="required" </c:if>  >
                                 <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                  <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.foodOption=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                  <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.foodOption=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                  <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.foodOption=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                       </select></td>
                                 <td class="p-0"><input type="text"   <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>foodOptionRemark" value="${clearanceFormData.hrClearanceDto.foodOptionRemark}" maxlength="199"  /></td>
                            </tr>
                        </tbody>
                      </table>
                      <table  class="w-100 table table-sm table-bordered table-striped  mb-0">
                        <thead class="thead-dark">
                            <tr>
                                <th width="5%">S No</th>
                                <th width="35%">Item</th>
                                <th width="20%">Yes/No/NA</th>
                                <th width="40%">Remarks</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                 <td class="py-0">a)</td>
                                 <td class="py-0">Check Induction Feedback Form Status</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm" name="<portlet:namespace/>inductionFeedbackStatus" <c:if test="${empCoreDetails.roleType == 'Trantor_HR'}"> required="required" </c:if>  >
                                           <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.inductionFeedbackStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.inductionFeedbackStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.inductionFeedbackStatus=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                                 </select></td>
                                 <td class="p-0"><input type="text"   <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>inductionFeedbackRemark" value="${clearanceFormData.hrClearanceDto.inductionFeedbackRemark}"  maxlength="199" /></td>
                            </tr>
                            <tr>
                                 <td class="py-0">b)</td>
                                 <td class="py-0">Check Induction Quiz Status</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm" name="<portlet:namespace/>inductionQuizStatus" <c:if test="${empCoreDetails.roleType == 'Trantor_HR'}"> required="required" </c:if>  >
                                           <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.inductionQuizStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.inductionQuizStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.inductionQuizStatus=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                                 </select></td>
                                 <td class="p-0"><input type="text"   <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>inductionQuizRemark" value="${clearanceFormData.hrClearanceDto.inductionQuizRemark}" maxlength="199" /></td>
                            </tr>
                            <tr>
                                 <td class="py-0">d)</td>
                                 <td class="py-0">Check Training Feedback Form</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm" name="<portlet:namespace/>trainingFeedbackStatus" <c:if test="${empCoreDetails.roleType == 'Trantor_HR'}"> required="required" </c:if>  >
                                           <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.trainingFeedbackStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.trainingFeedbackStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.trainingFeedbackStatus=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                                 </select></td>
                                 <td class="p-0"><input type="text"   <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>trainingFeedbackRemark" value="${clearanceFormData.hrClearanceDto.trainingFeedbackRemark}" maxlength="199" /></td>
                            </tr>
                            <tr>
                                 <td class="py-0">e)</td>
                                 <td class="py-0">Employee has given his Exit interview ?</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm" name="<portlet:namespace/>exitInterviewStatus" <c:if test="${empCoreDetails.roleType == 'Trantor_HR'}"> required="required" </c:if>  >
                                           <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.exitInterviewStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.exitInterviewStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.exitInterviewStatus=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                                 </select></td>
                                 <td class="p-0"><input type="text"   <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>exitInterviewRemark" value="${clearanceFormData.hrClearanceDto.exitInterviewRemark}" maxlength="199"  /></td>
                            </tr>
                            <tr>
                                 <td class="py-0">f)</td>
                                 <td class="py-0">Update Master tab and left tab in employee Directory</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm" name="<portlet:namespace/>employeeDirectoryStatus" <c:if test="${empCoreDetails.roleType == 'Trantor_HR'}"> required="required" </c:if>  >
                                           <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.employeeDirectoryStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.employeeDirectoryStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.employeeDirectoryStatus=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                                 </select></td>
                                 <td class="p-0"><input type="text"   <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>employeeDirectoryRemark" value="${clearanceFormData.hrClearanceDto.employeeDirectoryRemark}" maxlength="199"  /></td>
                            </tr>
                            <tr>
                                 <td class="py-0">f)</td>
                                 <td class="py-0">Update LMS</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm" name="<portlet:namespace/>lmsStatus" <c:if test="${empCoreDetails.roleType == 'Trantor_HR'}"> required="required" </c:if>  >
                                           <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.lmsStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.lmsStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.lmsStatus=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                                 </select></td>
                                 <td class="p-0"><input type="text"   <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>lmsRemark" value="${clearanceFormData.hrClearanceDto.lmsRemark}" maxlength="199" /></td>
                            </tr>
                            <tr>
                                 <td class="py-0">f)</td>
                                 <td class="py-0">Update Vantage Circle Database</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm" name="<portlet:namespace/>vantageCircleStatus" <c:if test="${empCoreDetails.roleType == 'Trantor_HR'}"> required="required" </c:if>  >
                                           <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.vantageCircleStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.vantageCircleStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.vantageCircleStatus=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                                 </select></td>
                                 <td class="p-0"><input type="text"   <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>vantageCircleRemark" value="${clearanceFormData.hrClearanceDto.vantageCircleRemark}" maxlength="199" /></td>
                            </tr>
                            <tr>
                                 <td class="py-0">f)</td>
                                 <td class="py-0">Update Birthday Database used in Synergy</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm" name="<portlet:namespace/>birthdaySynergyStatus" <c:if test="${empCoreDetails.roleType == 'Trantor_HR'}"> required="required" </c:if>  >
                                           <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.birthdaySynergyStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.birthdaySynergyStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.birthdaySynergyStatus=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                                 </select></td>
                                 <td class="p-0"><input type="text"    <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>birthdaySynergyRemark" value="${clearanceFormData.hrClearanceDto.birthdaySynergyRemark}"  maxlength="199" /></td>
                            </tr>
                            <tr>
                                 <td class="py-0">g)</td>
                                 <td class="py-0">Relieving cum Experience letter has been issued to employee?</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm" name="<portlet:namespace/>experienceLetterStatus" <c:if test="${empCoreDetails.roleType == 'Trantor_HR'}"> required="required" </c:if>  >
                                           <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.experienceLetterStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.experienceLetterStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.experienceLetterStatus=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                                 </select></td>
                                 <td class="p-0"><input type="text"   <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>experienceLetterRemark" value="${clearanceFormData.hrClearanceDto.experienceLetterRemark}" maxlength="199" /></td>
                            </tr>
                            <tr>
                                 <td class="py-0">f)</td>
                                 <td class="py-0">Hand Signed NDA has been received</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm" name="<portlet:namespace/>ndaFormStatus" <c:if test="${empCoreDetails.roleType == 'Trantor_HR'}"> required="required" </c:if>  >
                                           <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.ndaFormStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.ndaFormStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.ndaFormStatus=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                                 </select></td>
                                 <td class="p-0"><input type="text"   <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>ndaFormRemark" value="${clearanceFormData.hrClearanceDto.ndaFormRemark}" maxlength="199"   /></td>
                            </tr>
                            <tr>
                                 <td class="py-0">h)</td>
                                 <td class="py-0">All Separation requirements have been completed ?</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm" name="<portlet:namespace/>separationDocumentStatus" <c:if test="${empCoreDetails.roleType == 'Trantor_HR'}"> required="required" </c:if>  >
                                           <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.separationDocumentStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.separationDocumentStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.separationDocumentStatus=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                                                 </select></td>
                                 <td class="p-0"><input type="text"   <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>separationDocumentRemarkHR" value="${clearanceFormData.hrClearanceDto.separationDocumentRemark}" maxlength="199"   /></td>
                            </tr>
                        </tbody>
                      </table>
                      <table  class="w-100 table table-sm table-bordered table-striped  mb-0">
                        <thead class="thead-dark">
                            <tr>
                            <th class="text-center align-middle" width="10%">
                         <c:if test = "${clearanceFormData.hrClearanceDto.viewMode == 'true'}">
                               <button type="button" class="btn btn-sm btn-outline-success py-0"  title="Click on + button to add new recovery item" onclick="addHrRows()">
                               <i class="far fa-plus-square"></i>
                               </button>
                         </c:if>
                         <c:if test = "${clearanceFormData.hrClearanceDto.viewMode == 'false'}">
                           S No
                         </c:if>
                         </th>
                                <th width="40%">Item</th>
                                <th width="25%">To be done (Yes/No)</th>
                                <th width="25%">Recovery Amount(&#8377;)</th>
                            </tr>
                        </thead>
                        <tbody id="hrTableBodyRows">
                            <tr>
                                 <td class="py-0">a)</td>
                                 <td class="py-0">Training agreement (for trainees)</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm fn_enableDisable" id="trainingAgreementStatus" name="<portlet:namespace/>trainingAgreementStatus" <c:if test="${empCoreDetails.roleType == 'Trantor_HR'}"> required="required" </c:if>  >
                                           <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.trainingAgreementStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.trainingAgreementStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                                 </select></td>
                                 <td class="p-0"><input type="number"  step="0.01"  <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR' || clearanceFormData.hrClearanceDto.trainingAgreementStatus !='1'}"> class="form-control form-control-sm ds_readonlyTextbox " readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose>  id="trainingAgreementAmt" name="<portlet:namespace/>trainingAgreementAmt" value="${clearanceFormData.hrClearanceDto.trainingAgreementAmt}" onblur="roundOfTwoDecimal(this)"   /></td>

                            </tr>
                            <tr>
                                 <td class="py-0">b)</td>
                                 <td class="py-0">Recoverable bonus (paid in last one year)</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm fn_enableDisable" id="recoverableBonusStatus" name="<portlet:namespace/>recoverableBonusStatus" <c:if test="${empCoreDetails.roleType == 'Trantor_HR'}"> required="required" </c:if>  >
                                           <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.recoverableBonusStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.recoverableBonusStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                                 </select></td>
                                 <td class="p-0"><input type="number"  step="0.01" <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR' || clearanceFormData.hrClearanceDto.recoverableBonusStatus !='1'}"> class="form-control form-control-sm ds_readonlyTextbox " readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> id="recoverableBonusAmt" name="<portlet:namespace/>recoverableBonusAmt"  value="${clearanceFormData.hrClearanceDto.recoverableBonusAmt}" onblur="roundOfTwoDecimal(this)" /></td>

                            </tr>

                            <tr>
                                 <td class="py-0">c)</td>
                                 <td class="py-0">Notice period recovery, if any</td>
                                 <td class="p-0"><select class="mdb-select md-form form-control form-control-sm fn_enableDisable" id="noticePeriodRecoveryStatus" name="<portlet:namespace/>noticePeriodRecoveryStatus" <c:if test="${empCoreDetails.roleType == 'Trantor_HR'}"> required="required" </c:if>  >
                                           <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.noticePeriodRecoveryStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                                            <option <c:choose><c:when test="${clearanceFormData.hrClearanceDto.noticePeriodRecoveryStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                                                 </select></td>
                                 <td class="p-0"><input type="number"  step="0.01" <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR'  || clearanceFormData.hrClearanceDto.noticePeriodRecoveryStatus !='1'}"> class="form-control form-control-sm ds_readonlyTextbox " readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> id="noticePeriodRecoveryAmt" name="<portlet:namespace/>noticePeriodRecoveryAmt" value="${clearanceFormData.hrClearanceDto.noticePeriodRecoveryAmt}"  onblur="roundOfTwoDecimal(this)"  /></td>

                            </tr>
                      <c:if test="${hrRecoveryFormData.size() > 0}">
                            <c:forEach items="${hrRecoveryFormData}" var="item" varStatus="count">
                            <tr>
                            <td class="py-0 ">
                             <c:choose>
                             <c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted=='false'}">
                             <button type="button" id="hr${count.index+8}" onclick="removeRowAndResetHrIndex(this)"  class="btn-danger"><i class="far fa-trash-alt" style="cursor: pointer"></i></button>
                             </c:when>
                             <c:otherwise>
                             &\#${(count.index+97+8)})
                             </c:otherwise>
                             </c:choose>
                             </td>
                            <td class="p-0"><input type="text"   <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> value="${item.recoveryItem}" id="hrItem${count.index+3}" name="<portlet:namespace/>hrItem${count.index+3}"  <c:if test="${empCoreDetails.roleType == 'Trantor_HR'}"> required="required" </c:if>   /></td>
                            <td class="p-0"><select class="mdb-select md-form form-control form-control-sm fn_enableDisable" name="<portlet:namespace/>hrRecoveryStatus${count.index+3}" <c:if test="${empCoreDetails.roleType == 'Trantor_HR'}"> required="required" </c:if>  >
                            <c:if test="${clearanceFormData.financeClearanceDto.financeSubmitted=='false'}">  <option value="" disabled selected>Choose your option</option> </c:if>
                             <option <c:choose><c:when test="${item.recoveryStatus=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Yes</option>
                             <option <c:choose><c:when test="${item.recoveryStatus=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">No</option>
                             <option <c:choose><c:when test="${item.recoveryStatus=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true'}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">NA</option>
                               </select></td>
                            <td class="p-0"><input type="number" step="0.01"  <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || item.recoveryStatus!='1'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> value="${item.recoveryAmount}" id="hrRecoveryAmount${count.index+3}" name="<portlet:namespace/>hrRecoveryAmount${count.index+3}" <c:if test="${empCoreDetails.roleType == 'Trantor_HR'}"> required="required" </c:if> onblur="roundOfTwoDecimal(this)"  /></td>
                            </tr>
                            </c:forEach>
                           </c:if>
                        </tbody>
                      </table>

                      <table  class="w-100 table table-sm table-bordered table-striped  mb-0">
                        <thead class="thead-dark">
                            <tr>
                                <th width="5%">S No</th>
                                <th width="35%">Leaves and LOP Details</th>
                                <th width="15%"></th>
                                <th width="15%"></th>
                                <th width="15%"></th>
                                <th width="15%"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                 <td class="py-0" rowspan="3">a)</td>
                                 <td class="py-0" rowspan="3">Leaves During Notice Period</td>
                                 <td class="py-0 pl-3">Month</td>
                                 <td class="p-0"><input type="text"   maxlength="74" <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>leavesMonth1" value="${clearanceFormData.hrClearanceDto.leavesMonth1}"   /></td>
                                 <td class="p-0"><input type="text"   maxlength="74" <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>leavesMonth2" value="${clearanceFormData.hrClearanceDto.leavesMonth2}"    /></td>
                                 <td class="p-0"><input type="text"    maxlength="74" <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>leavesMonth3" value="${clearanceFormData.hrClearanceDto.leavesMonth3}"  /></td>
                            </tr>
                            <tr>
                                 <td class="py-0 pl-3">No. of Days</td>
                                 <td class="p-0"><input type="text"   maxlength="74"     <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>leaveDaysMonth1" value="${clearanceFormData.hrClearanceDto.leaveDaysMonth1}"     /></td>
                                 <td class="p-0"><input type="text"   maxlength="74"     <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>leaveDaysMonth2" value="${clearanceFormData.hrClearanceDto.leaveDaysMonth2}"    /></td>
                                 <td class="p-0"><input type="text"   maxlength="74"     <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>leaveDaysMonth3" value="${clearanceFormData.hrClearanceDto.leaveDaysMonth3}"      /></td>
                            </tr>
                            <tr>
                                 <td class="py-0 pl-3">Date</td>
                                 <td class="p-0"><input type="text"   maxlength="74" <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>leaveDateMonth1" value="${clearanceFormData.hrClearanceDto.leaveDateMonth1}"     /></td>
                                 <td class="p-0"><input type="text"   maxlength="74" <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>leaveDateMonth2" value="${clearanceFormData.hrClearanceDto.leaveDateMonth2}"   /></td>
                                 <td class="p-0"><input type="text"   maxlength="74" <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose>  name="<portlet:namespace/>leaveDateMonth3" value="${clearanceFormData.hrClearanceDto.leaveDateMonth3}"   /></td>
                            </tr>
                            <tr>
                            <tr>
                                 <td class="py-0" rowspan="3">b)</td>
                                 <td class="py-0" rowspan="3">LOP (Unapproved Leaves) During Notice Period</td>
                                 <td class="py-0 pl-3">Month</td>
                                 <td class="p-0"><input type="text"   maxlength="74"  <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>lopMonth1" value="${clearanceFormData.hrClearanceDto.lopMonth1}"   /></td>
                                 <td class="p-0"><input type="text"   maxlength="74" <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>lopMonth2" value="${clearanceFormData.hrClearanceDto.lopMonth2}"   /></td>
                                 <td class="p-0"><input type="text"   maxlength="74"  <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>lopMonth3" value="${clearanceFormData.hrClearanceDto.lopMonth3}"   /></td>
                            </tr>
                            <tr>
                                 <td class="py-0 pl-3">No. of Days</td>
                                 <td class="p-0"><input type="text"   maxlength="74"   <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>lopDaysMonth1" value="${clearanceFormData.hrClearanceDto.lopDaysMonth1}"    /></td>
                                 <td class="p-0"><input type="text"   maxlength="74"   <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>lopDaysMonth2" value="${clearanceFormData.hrClearanceDto.lopDaysMonth2}"   /></td>
                                 <td class="p-0"><input type="text"   maxlength="74"   <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>lopDaysMonth3" value="${clearanceFormData.hrClearanceDto.lopDaysMonth3}"   /></td>
                            </tr>
                            <tr>
                                 <td class="py-0 pl-3">Date</td>
                                 <td class="p-0"><input type="text"   maxlength="74" <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>lopDateMonth1" value="${clearanceFormData.hrClearanceDto.lopDateMonth1}"     /></td>
                                 <td class="p-0"><input type="text"   maxlength="74"  <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>lopDateMonth2" value="${clearanceFormData.hrClearanceDto.lopDateMonth2}"   /></td>
                                 <td class="p-0"><input type="text"   maxlength="74" <c:choose><c:when test="${clearanceFormData.hrClearanceDto.hrSubmitted==true || empCoreDetails.roleType != 'Trantor_HR'}"> class="form-control form-control-sm ds_readonlyTextbox" readonly </c:when> <c:otherwise> class="form-control form-control-sm " </c:otherwise></c:choose> name="<portlet:namespace/>lopDateMonth3" value="${clearanceFormData.hrClearanceDto.lopDateMonth3}"  /></td>
                            </tr>
                            <tr>
                                 <td class="py-0">c)</td>
                                 <td class="py-0">Earned Leave balance<br><h5>Note - <ul><li>EL balance for current month should not be added here as accrual happens on completion of that month</li><li>This will be encashed in Full and Final.</li></ul></h5></td>
                                 <td class="p-0" colspan="4"><input type="text"    class="form-control form-control-sm" name="<portlet:namespace/>earnedLeaveBalance" maxlength="74" value="${clearanceFormData.hrClearanceDto.earnedLeaveBalance}"  <c:if test="${empCoreDetails.roleType == 'Trantor_HR'}"> required="required" </c:if> <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true' || empCoreDetails.roleType != 'Trantor_HR'}"> readonly </c:if>  /></td>
                            </tr>
                        </tbody>
                      </table>
                      <table  class="w-100 table table-sm table-bordered table-striped  mb-0">
                        <thead class="thead-dark">
                            <tr>
                            <th width="25%">HR Remarks</th>
                                <th width="75%"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                 <td class="py-0" >HR Remarks :</td>
                                 <td class="p-0"><input type="text"   class="form-control form-control-sm" onblur="$(this).val($(this).val().trim())" name="<portlet:namespace/>hrRemarks" maxlength="499" value="${clearanceFormData.hrClearanceDto.hrRemarks}" <c:if test="${(clearanceFormData.financeClearanceDto.financeSubmitted=='false'|| clearanceFormData.hrClearanceDto.hrSubmitted=='true')}" > readonly </c:if>   /></td>
                            </tr>
                        </tbody>
                      </table>
                      <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' || clearanceFormData.adminClearanceDto.adminSubmitted=='true' ||  clearanceFormData.financeClearanceDto.financeSubmitted=='true'}">
                      <table  class="w-100 table table-sm table-bordered table-striped  mb-0">
                        <thead class="thead-dark">
                            <tr>
                            <th class="text-center align-middle" width="10%">S No
                         </th>
                                <th width="25%">Item</th>
                                <th width="20%">Recovery Amount(&#8377;)</th>
                                <th width="10%">Approved</th>
                                <th width="35%">Comment</th>
                            </tr>
                        </thead>
                        <tbody >
                         <c:if test="${clearanceFormData.itClearanceDto.itSubmitted=='true' && (itRecoveryFormData.size() > 0 || itRecoveryFormData1.size() > 0)}">
                                                      <tr>
                                                         <td colspan="5">IT Recovery Form</td>
                                                       </tr>
                      <c:if test="${itRecoveryFormData1.size() > 0 }">
                      <c:forEach items="${itRecoveryFormData1}" var="item" varStatus="count">
                      <tr>
                            <td class="py-0 ">
                              &\#${(count.index+97)})
                             </td>
                            <td class="p-0"><input type="text"  class="form-control form-control-sm ds_readonlyTextbox" readonly  value="${item.recoveryItem}"   /></td>

                         <td class="p-0"><input type="number" class="form-control form-control-sm ds_readonlyTextbox" readonly value="${item.recoveryAmount}"   /></td>
                            <td class="p-0"><input type="checkbox" class="fn_isReadonly"  value="true" <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true'}">onclick="return false" </c:if>  <c:if test="${item.approved=='true'}"> checked </c:if>  name="<portlet:namespace/>approvedIt${item.nameSpace}"  /></td>
                            <td class="p-0"><input type="text" class="form-control form-control-sm " <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true'}">readonly </c:if>  value="${item.comment}" name="<portlet:namespace/>commentIt${item.nameSpace}"   /></td>
                            </tr>
                         </c:forEach>
                      </c:if>
                          <c:if test="${itSelectedRecoveryFormData.size() > 0 }">
                            <c:forEach items="${itSelectedRecoveryFormData}" var="item" varStatus="count">
                            <tr>
                            <td class="py-0 ">
                              &\#${(count.index+97+itRecoveryFormData1.size())})
                             </td>

                            <td class="p-0"><input type="text"  class="form-control form-control-sm ds_readonlyTextbox" readonly  value="${item.recoveryItem}"   /></td>
                            <td class="p-0"><input type="number" class="form-control form-control-sm ds_readonlyTextbox" readonly value="${item.recoveryAmount}"   /></td>
                            <td class="p-0"><input type="checkbox" class="fn_isReadonly" <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true'}">onclick="return false" </c:if>  name="<portlet:namespace/>approvedIt${item.id}" <c:if test="${item.approved=='true'}"> checked </c:if>  value="true"  /></td>
                            <td class="p-0"><input type="text" class="form-control form-control-sm "  value="${item.comment}" <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true'}">readonly </c:if>  name="<portlet:namespace/>commentIt${item.id}"   /></td>                            </tr>
                            </c:forEach>
                           </c:if>
                           </c:if>
                           <c:if test="${clearanceFormData.adminClearanceDto.adminSubmitted=='true'}">
                           <c:if test="${adminRecoveryFormData1.size() > 0 || adminRecoveryFormData.size() > 0 }">
                            <tr>    <td colspan="5">Admin Recovery Form</td>
                              </tr>
                      <c:if test="${adminRecoveryFormData1.size() > 0 }">
                      <c:forEach items="${adminRecoveryFormData1}" var="item" varStatus="count">
                      <tr>
                            <td class="py-0 ">
                              &\#${(count.index+97)})
                             </td>
                           <td class="p-0"><input type="text"  class="form-control form-control-sm ds_readonlyTextbox" readonly  value="${item.recoveryItem}"   /></td>

                         <td class="p-0"><input type="number" class="form-control form-control-sm ds_readonlyTextbox" readonly value="${item.recoveryAmount}"   /></td>
                            <td class="p-0"><input type="checkbox" class="fn_isReadonly" <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true'}">onclick="return false" </c:if>  name="<portlet:namespace/>approved${item.nameSpace}" <c:if test="${item.approved=='true'}"> checked </c:if>  value="true"  /></td>
                            <td class="p-0"><input type="text" class="form-control form-control-sm "  value="${item.comment}" <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true'}">readonly </c:if>  name="<portlet:namespace/>comment${item.nameSpace}"   /></td>
                            </tr>
                       </c:forEach>
                      </c:if>

                      <c:if test="${adminSelectedRecoveryFormData.size() > 0 }">
                            <c:forEach items="${adminSelectedRecoveryFormData}" var="item" varStatus="count">
                            <tr>
                            <td class="py-0 ">
                              &\#${(count.index+97+adminRecoveryFormData1.size())})
                             </td>
                            <td class="p-0"><input type="text"  class="form-control form-control-sm ds_readonlyTextbox" readonly  value="${item.recoveryItem}"   /></td>
                            <td class="p-0"><input type="number" class="form-control form-control-sm ds_readonlyTextbox" readonly value="${item.recoveryAmount}"   /></td>
                            <td class="p-0"><input type="checkbox" class="fn_isReadonly" <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true'}">onclick="return false" </c:if>  name="<portlet:namespace/>approved${item.id}" <c:if test="${item.approved=='true'}"> checked </c:if>  value="true"  /></td>
                            <td class="p-0"><input type="text" class="form-control form-control-sm "  value="${item.comment}" <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true'}">readonly </c:if>  name="<portlet:namespace/>comment${item.id}"   /></td>
                            </tr>
                            </c:forEach>
                           </c:if>
                           </c:if>
                           </c:if>
                                 <c:if test="${(recoveryFormData.size() > 0 || recoveryFormData1.size() > 0 )  && clearanceFormData.financeClearanceDto.financeSubmitted=='true'}">
                                                   <tr>
                                                      <td colspan="5">Finance Recovery Form</td>
                                                    </tr>
                     <c:if test="${recoveryFormData1.size() > 0 }">
                      <c:forEach items="${recoveryFormData1}" var="item" varStatus="count">
                      <tr>
                            <td class="py-0 ">
                              &\#${(count.index+97)})
                             </td>
                            <td class="p-0"><input type="text"  class="form-control form-control-sm ds_readonlyTextbox" readonly  value="${item.recoveryItem}"   /></td>
                         <td class="p-0"><input type="number" class="form-control form-control-sm ds_readonlyTextbox" readonly value="${item.recoveryAmount}"   /></td>
                             <td class="p-0"><input type="checkbox" class="fn_isReadonly" <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true'}">onclick="return false" </c:if>  name="<portlet:namespace/>approved${item.nameSpace}" <c:if test="${item.approved=='true'}"> checked </c:if>  value="true"  /></td>
                             <td class="p-0"><input type="text" class="form-control form-control-sm "  value="${item.comment}" <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true'}">readonly </c:if>  name="<portlet:namespace/>comment${item.nameSpace}"   /></td>
                            </tr>
                      </c:forEach>
                      </c:if>
                            <c:forEach items="${selectedRecoveryFormData}" var="item" varStatus="count">
                            <tr>
                            <td class="py-0 ">
                              &\#${(count.index+97+recoveryFormData1.size())})
                             </td>
                            <td class="p-0"><input type="text"  class="form-control form-control-sm ds_readonlyTextbox" readonly  value="${item.recoveryItem}"   /></td>
                            <td class="p-0"><input type="number" class="form-control form-control-sm ds_readonlyTextbox" readonly value="${item.recoveryAmount}"   /></td>
                             <td class="p-0"><input type="checkbox" class="fn_isReadonly" <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true'}">onclick="return false" </c:if>  name="<portlet:namespace/>approvedFinance${item.id}" <c:if test="${item.approved=='true'}"> checked </c:if>  value="true"  /></td>
                             <td class="p-0"><input type="text" class="form-control form-control-sm "  value="${item.comment}" <c:if test="${clearanceFormData.hrClearanceDto.hrSubmitted=='true'}">readonly </c:if>  name="<portlet:namespace/>commentFinance${item.id}"   /></td>                           </tr>
                            </c:forEach>
                           </c:if>
                        </tbody>
                      </table>
                      </c:if>
                       <div class="carousel-caption d-none d-md-block bg-dark"><h6 class="pb-3">HR Form</h6></div>
                    </div>
                    </c:if>
                  </div>
                </div>
                <c:if test="${clearanceFormData.saveSubmitEnable == 'true'}">
				<div class="row mt-4" id="commonButtonDiv">
                    <div class="col text-center">
                    </div>
                    <div class="col text-center">
                        <button  class="btn btn-primary" type="button" id="saveForm" >Save</button>
                    </div>
                    <c:choose>
                    <c:when test="${clearanceFormData.showSubmitButton=='false' &&  empCoreDetails.roleType == 'Trantor_HR'}">
                    <div class="col-4 text-center my-auto">
                    <h5 class="text-center font-italic">HR can submit the form after all departments have submitted </h5>  </div>
                    </c:when>
                    <c:otherwise>
                    <c:if test="${empCoreDetails.roleType != 'Trantor_Manager' || (clearanceFormData.managerClearanceDto.managerResignSubmitted==true && empCoreDetails.roleType == 'Trantor_Manager')}">
                      <div class="col text-center">
                       <button id="submitButtonId"  onclick="return confirmSubmission()"  class="btn btn-primary" >Submit</button>
                       </div>
                    </c:if>
                    </c:otherwise>
                    </c:choose>
                    <div class="col text-center mt-2"></div>
				</div>
				</c:if>
           </form>
           <c:if test="${clearanceFormData.managerClearanceDto.managerResignSubmitted==false && empCoreDetails.roleType == 'Trantor_Manager'}">
           <div class="row">
           <div class="col text-center">
           <form action="${exitWorkflowUrl}" method="post" style="display: inline" target="_blank">
               <input type="hidden" value="${empCoreDetails.resignationId}"
                               name="<portlet:namespace/>resignationId" />
               <input type="hidden" value="Trantor_Manager"
                               name="<portlet:namespace/>roleType" />
              <button type="submit" class=" btn btn-link pr-1 mb-1">Click here</button>to submit the Resignation Detail of the employee before submitting the clearance form
         </form>
    	</div>
    	</div>
    	</c:if>
    	</div>
    </div>
