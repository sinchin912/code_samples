<%@ include file="/init.jsp"%>
<portlet:actionURL name="rejectKpiForm" var="rejectKpiFormUrl"></portlet:actionURL>
<portlet:renderURL var="kpiRenderURL" windowState="normal"></portlet:renderURL>
<div class="container">
    <c:set var="pageUrl" value="${fn:split(kpiRenderURL.toString(),'?')}" />
	<div class="card pb-2">
		<div class="card-header">
			<div class="row">
				<div class="col pt-2">
					<h5>Project Kpi Information</h5>
				</div>
                <div class="col-2 text-right">
					<a class="btn btn-outline-secondary" id="reset" href="${pageUrl[0]}"
						style="color: black;" role="button">Cancel</a>
				</div>
			</div>
		</div>
		<div class="card-body">
			<div class="row">
				<div class="col-md-2 mt-1 text-right">
					<label>Employee Name</label>
				</div>
				<div class="col-md-4">
					<input type="text" class="form-control-sm form-control-plaintext"
						readonly value="${reviewDetails.employeeName}" />
				</div>
				<div class="col-md-2 mt-1 text-right">
					<label>Ecode</label>
				</div>
				<div class="col-md-4">
					<input type="text" class="form-control-sm form-control-plaintext"
						readonly value="${reviewDetails.employeeCode}" />
				</div>
			</div>
			<div class="row">
				<div class="col-md-2 mt-1 text-right">
					<label>Designation</label>
				</div>
				<div class="col-md-4">
					<input type="text" class="form-control-sm form-control-plaintext"
						readonly
						value="${reviewDetails.employeeDesignation}" />
				</div>
				<div class="col-md-2 mt-1 text-right">
					<label>Band</label>
				</div>
				<div class="col-md-4">
					<input type="text" class="form-control-sm form-control-plaintext"
						readonly value="${reviewDetails.employeeBand}" />
				</div>
			</div>
			<div class="row">
				<div class="col-md-2 mt-1 text-right">
					<label>Account</label>
				</div>
				<div class="col-md-4">
					<input type="text" class="form-control-sm form-control-plaintext"
						readonly value="${reviewDetails.account}" />
				</div>
                <div class="col-md-2 mt-1 text-right">
					<label>Account Type</label>
				</div>
				<div class="col-md-4 form-control-sm">
					<c:choose>
						<c:when test="${(reviewDetails.currentAccount=='true')}">Primary
						</c:when>
						<c:otherwise>Secondary
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="row">
				<div class="col-md-2 mt-1 text-right">
					<label>Manager Name</label>
				</div>
				<div class="col-md-4">
					<input type="text" class="form-control-sm form-control-plaintext"
						readonly
						value="${reviewDetails.managerName}" />
				</div>
				<div class="col-md-2 mt-1 text-right">
					<label>Lead Name</label>
				</div>
				<div class="col-md-4">
					<input type="text" class="form-control-sm form-control-plaintext"
						readonly
						value="${reviewDetails.reviewerName}" />
				</div>
			</div>
		</div>
	</div>
		<div class="card pb-2">
            <div class="card-header">
    			<div class="row">
    				<div class="col pt-2">
    					<h5>KPI Setting Form</h5>
    				</div>
    			</div>
    		</div>
    		<div class="card-body">
                        <table
                            class="table  table-sm table-bordered  table-striped mb-4">
                            <thead class="thead-dark">
                                <tr>
                                    <th width="25%">KPI Title</th>
                                    <th width="34%">KPI Description</th>
                                    <th width="41%">KPI Target</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${reviewDetails.reviewLines}" var="item"
                                    varStatus="count">
                                    <c:if test="${(!item.attribute)}">
                                        <tr>
                                            <td class="py-0"><input
                                                type="text" class="form-control-sm form-control-plaintext" title="${item.reviewLineTitle}"
                                                value="${item.reviewLineTitle}" readonly /></td>
                                            <td class="p-0"><textarea row="2" column="50"
                                                    class="form-control-plaintext form-control-sm"
                                                    style="height: 80px;" readonly
                                                    title="${item.reviewLineDescription}">${item.reviewLineDescription}</textarea></td>
                                            <td class="p-0"><textarea row="2" column="50"
                                                    style="height: 80px;"
                                                    class="form-control-plaintext form-control-sm"
                                                    title="${item.reviewLineTarget}" readonly>${item.reviewLineTarget}</textarea></td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </tbody>
                            <tbody>
                                <tr>
                                    <td>Attributes</td>
                                    <td colspan="2"><textarea row="2" column="50" style="height:180px;" class = "form-control-plaintext form-control-sm" readonly="readonly" >${reviewDetails.attributeText}</textarea></td>
                                </tr>
                            </tbody>
                        </table>
                        <c:if test="${(reviewDetails.editMode=='true')}">
                            <form id="rejectKpiForm"
                                            method="post" action="${rejectKpiFormUrl}"  onsubmit="return confirmation();">
                                <input type="hidden" value="${reviewDetails.kpiId}" name="<portlet:namespace/>kpiId" />
                                    <div class="input-group">
                                        <textarea class="form-control form-control-sm"
                                            placeholder="Provide your reason for sending this kpi form back to KPI Settings stage for correction"
                                            maxlength="200" style="height: 40px;width:300px;" name="<portlet:namespace/>rejectObjRsn"
                                            id="rejectObjRsn" required="required" onblur="$(this).val($(this).val().trim())"></textarea>
                                        <button class="btn btn-outline-warning" id="managerReject"
                                            type="submit" >Send Back to Employee</button>
                            </form>
                        </c:if>
    		</div>
    	</div>
</div>
<script>
function confirmation(obj){
event.preventDefault();
confirmModal("Performance Admin:Are you sure you want to reject and send back these KPIs to employee for improvement?",'rejectKpiForm',null);
}
</script>