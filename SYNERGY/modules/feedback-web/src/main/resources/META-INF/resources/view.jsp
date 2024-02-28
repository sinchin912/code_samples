<%@ include file="/init.jsp" %>

<portlet:actionURL name="submitFeedback" var="submitFeedbackUrl"></portlet:actionURL>

<div  class="container">
    <div  class="card">
        <div class="card-header">
            <div class="row">
                <div class="col">
                    <h5>Synergy Feedback Form</h5>
                </div>
            </div>
        </div>
        <div class="card-body">
            <form  action="${submitFeedbackUrl}" id="feedbackForm" method="post" onsubmit="return confirmSubmitDetails()">
                <div class="form-row mb-2 form-control-sm">
                    <div class="form-group col-md-2 mt-1 text-right">
                        <label>E-Code</label>
                    </div>
                    <div class="form-group col-md-10">
                        <input type="text" class="form-control-sm form-control-plaintext pl-2  p-1 " value="${employee.ecode}" name="<portlet:namespace/>ecode"  readonly />
                    </div>
                </div>
                <div class="form-row mb-2 form-control-sm">
                    <div class="form-group col-md-2 mt-1 text-right">
                        <label>Name</label>
                    </div>
                    <div class="form-group col-md-10">
                        <input type="text" class="form-control-sm form-control-plaintext pl-2  p-1 " value="${employee.name}"  name="<portlet:namespace/>name" readonly />
                    </div>
                </div>
                <div class="form-row mb-2 form-control-sm">
                    <div class="form-group col-md-2 mt-1 text-right">
                        <label>E-Mail</label>
                    </div>
                    <div class="form-group col-md-10">
                        <input type="text" class="form-control-sm form-control-plaintext pl-2  p-1 " value="${employee.email}"  readonly />
                    </div>
                </div>
                <div class="form-row mb-2 form-control-sm">
                    <div class="form-group col-md-2 mt-1 text-right">
                        <label>Rating</label>
                    </div>
                    <div class="form-group col-md-10">
                        <select class="mdb-select md-form form-control form-control-sm border border-secondary pl-2" name="<portlet:namespace/>rating" required>
                            <option value="" selected disabled>Choose your option</option>
                            <option value="Excellent">Excellent</option>
                            <option value="Very-Good">Very-Good</option>
                            <option value="Good">Good</option>
                            <option value="Average">Average</option>
                            <option value="Poor">Poor</option>
                        </select>
                    </div>
                </div>
                <div class="form-row mb-2 form-control-sm" style="height:80px;">
                    <div class="form-group col-md-2 mt-1 text-right">
                        <label>Suggestion</label>
                    </div>
                    <div class="form-group col-md-10">
                       <textarea class="form-control form-control-sm pl-2" onblur="$(this).val($(this).val().trim())" maxlength="1000" style="height:80px;" name="<portlet:namespace/>suggestion" required></textarea>
                    </div>
                </div>
                <div class="row mt-5" >
                    <div class="col-12 text-center">
                        <button class="btn btn-primary" >Submit</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>


