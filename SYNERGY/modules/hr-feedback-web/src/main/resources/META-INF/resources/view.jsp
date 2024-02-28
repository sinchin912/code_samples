<%@ include file="/init.jsp" %>

<portlet:actionURL name="submitFeedback" var="submitFeedbackUrl"></portlet:actionURL>

<div  class="container">
    <div  class="card">
        <div class="card-header">
            <div class="row">
                <div class="col">
                    <h5>HR Feedback Form</h5>
                </div>
            </div>
        </div>
        <div class="card-body">
            <form  action="${submitFeedbackUrl}" id="feedbackForm" method="post" onsubmit="return confirmSubmitDetails()">
                <div class="form-row mb-2 form-control-sm">
                    <div class="form-group col-md-5 mt-1 text-right">
                        <label>E-Code</label>
                    </div>
                    <div class="form-group col-md-7">
                        <input type="text" class="form-control-sm form-control-plaintext pl-2  p-1 " value="${employee.ecode}" name="<portlet:namespace/>ecode"  readonly />
                    </div>
                </div>
                <div class="form-row mb-2 form-control-sm">
                    <div class="form-group col-md-5 mt-1 text-right">
                        <label>Name</label>
                    </div>
                    <div class="form-group col-md-7">
                        <input type="text" class="form-control-sm form-control-plaintext pl-2  p-1 " value="${employee.name}"  name="<portlet:namespace/>name" readonly />
                    </div>
                </div>
                <div class="form-row mb-2 form-control-sm">
                    <div class="form-group col-md-5 mt-1 text-right">
                        <label>E-Mail</label>
                    </div>
                    <div class="form-group col-md-7">
                        <input type="text" class="form-control-sm form-control-plaintext pl-2  p-1 " value="${employee.email}"  readonly />
                    </div>
                </div>
                <div class="form-row mb-2 form-control-sm">
                    <div class="form-group col-md-5 mt-1 text-right">
                        <label>Which area of HR you would like to give feedback on ?</label>
                    </div>
                    <div class="form-group col-md-7">
                       <input type="text" class="form-control form-control-sm pl-2  p-1 " onblur="$(this).val($(this).val().trim())" maxlength="100" name="<portlet:namespace/>area" required />
                    </div>
                </div>
                <div class="form-row mb-2 form-control-sm">
                    <div class="form-group col-md-5 mt-1 text-right">
                        <label>How satisfied are you with query resolution by HR team ?</label>
                    </div>
                    <div class="form-group col-md-7">
                        <select class="mdb-select md-form form-control form-control-sm border border-secondary pl-2" name="<portlet:namespace/>resolution" required>
                            <option value="" selected disabled>Choose your option</option>
                            <option value="Satisfied">Satisfied</option>
                            <option value="Dissatisfied">Dissatisfied</option>
                        </select>
                    </div>
                </div>
                <div class="form-row mb-2 form-control-sm">
                    <div class="form-group col-md-5 mt-1 text-right">
                        <label>How satisfied are you with turnaround response time ?</label>
                    </div>
                    <div class="form-group col-md-7">
                        <select class="mdb-select md-form form-control form-control-sm border border-secondary pl-2" name="<portlet:namespace/>turnaround" required>
                            <option value="" selected disabled>Choose your option</option>
                            <option value="Satisfied">Satisfied</option>
                            <option value="Dissatisfied">Dissatisfied</option>
                        </select>
                    </div>
                </div>
                <div class="form-row mb-2 form-control-sm" style="height:80px;">
                    <div class="form-group col-md-5 mt-1 text-right">
                        <label>How can we improve our process ?</label>
                    </div>
                    <div class="form-group col-md-7">
                       <textarea class="form-control form-control-sm pl-2" onblur="$(this).val($(this).val().trim())" maxlength="1000" style="height:80px;" name="<portlet:namespace/>improve" required></textarea>
                    </div>
                </div>
                <div class="form-row mb-2 form-control-sm" style="height:80px;">
                    <div class="form-group col-md-5 mt-1 text-right">
                        <label>Any other comments</label>
                    </div>
                    <div class="form-group col-md-7">
                       <textarea class="form-control form-control-sm pl-2" onblur="$(this).val($(this).val().trim())"  maxlength="1000" style="height:80px;" name="<portlet:namespace/>comment" required></textarea>
                    </div>
                </div>
                <div class="form-row mb-2 form-control-sm">
                    <div class="form-group col-md-5 mt-1 text-right">
                        <label>HR Representative who helped you out</label>
                    </div>
                    <div class="form-group col-md-7">
                        <select class="mdb-select md-form form-control form-control-sm border border-secondary pl-2" name="<portlet:namespace/>representative" required>
                            <option value="" selected disabled>Choose your option</option>
                            <option value="Aditi Sharma">Aditi Sharma</option>
                            <option value="Bhupinder Singh">Bhupinder Singh</option>
                            <option value="Geetika">Geetika</option>
                            <option value="Manju">Manju</option>
                            <option value="Megha Aggarwal">Megha Aggarwal</option>
                            <option value="Nidhi Ayri">Nidhi Ayri</option>
                            <option value="Priya Dhiman">Priya Dhiman</option>
                        </select>
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
