<%@ include file="/init.jsp" %>

<portlet:resourceURL var="accountDirectoryUrl"	id="accountDirectory"></portlet:resourceURL>
<portlet:resourceURL var="accountOrgchartUrl"	id="accountOrgchart"></portlet:resourceURL>
<portlet:resourceURL var="managerOrgchartUrl" id="managerOrgchart"></portlet:resourceURL>

<div class="container">
    <input type="hidden" id="portletNamespace" value="<portlet:namespace/>"/>
    <input type="hidden" id="getAccountDirectoryUrl" value="${accountDirectoryUrl}"/>
    <input type="hidden" id="getAccountOrgchartUrl" value="${accountOrgchartUrl}"/>
    <input type="hidden" id="getManagerOrgchartUrl" value="${managerOrgchartUrl}"/>
    <input type="hidden" id="initManagerOrgchart" value='${managerOrgchart}'/>
    <input type="hidden" id="initAccountOrgchart" value='${accountOrgchart}'/>

    <ul class="nav nav-tabs nav-justified mb-3" role="tablist">
            <li class="nav-item"><a class="nav-link active" id="first-tab"
                data-toggle="tab" href="#first" role="tab" aria-controls="first"
                aria-selected="true">Manager Orgchart</a></li>
            <li class="nav-item"><a class="nav-link" id="second-tab"
                data-toggle="tab" href="#second" role="tab"
                aria-controls="second" aria-selected="false">Account Orgchart</a></li>
            <li class="nav-item"><a class="nav-link" id="third-tab"
                data-toggle="tab" href="#third" role="tab"
                aria-controls="third" aria-selected="false">Account Directory</a></li>
    </ul>
        <div class="tab-content">

            <!-- MANAGER ORGCHART TAB -->

            <div class="tab-pane fade show active" id="first" role="tabpanel" aria-labelledby="first-tab">

                <div class="card">
                    <div class="card-header">
                            <div class="row">
                                <div class="col-4 pt-2">
                                    <select class="form-control" id="managerOrgchartEcode" >
                                       <option value="" selected disabled>Select Manager</option>
                                       <c:forEach items="${managers}" var="manager" varStatus="count">
                                          <option value="${manager.key}">${manager.value}</option>
                                       </c:forEach>
                                    </select>
                                </div>
                                <div class="col-8 pt-2">
                                    <button class="btn btn-outline-secondary"  onclick="doManagerOrgchart(this);">Search</button>
                                </div>
                            </div>
                    </div>
                    <div class="card-body text-center" id="managerOrgchartCardBody">
                        <div id="managerChart"></div>
                    </div>
                </div>
            </div>

            <!-- ACCOUNT ORGCHART TAB -->

            <div class="tab-pane fade" id="second" role="tabpanel" aria-labelledby="second-tab">
                <div class="card">
                    <div class="card-header">
                            <div class="row">
                                <div class="col-4 pt-2">
                                    <select class="form-control" id="accountOrgchartName" >
                                       <option value="" selected disabled>Select Account</option>
                                       <c:forEach items="${accounts}" var="account" varStatus="count">
                                           <c:if test="${account != 'MANAGEMENT'}" ><option value="${account}">${account}</option></c:if>
                                       </c:forEach>
                                    </select>
                                </div>
                                <div class="col-8 pt-2">
                                    <button class="btn btn-outline-secondary"  onclick="doAccountOrgchart(this);">Search</button>
                                </div>
                            </div>
                    </div>
                    <div class="card-body text-center" id="accountOrgchartCardBody">
                        <div id="accountChart"></div>
                    </div>
                </div>
            </div>

            <!-- ACCOUNT DIRECTORY TAB -->

            <div class="tab-pane fade" id="third" role="tabpanel" aria-labelledby="third-tab">
                <div class="card">
                    <div class="card-header">
                            <div class="row">
                                <div class="col-4 pt-2">
                                    <select class="form-control" id="accountDirectoryName" >
                                       <option value="" selected disabled>Select Account</option>
                                       <c:forEach items="${accounts}" var="account" varStatus="count">
                                           <option value="${account}">${account}</option>
                                       </c:forEach>
                                    </select>
                                </div>
                                <div class="col-8 pt-2">
                                    <button class="btn btn-outline-secondary"  onclick="doAccountDirectory(this);">Search</button>
                                </div>
                            </div>
                    </div>
                    <div class="card-body" id="accountDirectoryCardBody" >
                        <div class="row">
                            <div class="col-md-12 mt-1 text-left">
                                <label for="total">Total Employees :</label>
                                <input type="text" id="totalEmployees" class="border-0" readonly value="" />
                            </div>
                        </div>
                        <table class="table table-sm table-bordered table-striped table-hover table-custom" >
                            <thead class="thead-dark">
                                <tr>
                                    <th width="6%">Ecode</th>
                                    <th width="20%">Name</th>
                                    <th width="20%">Email Address</th>
                                    <th width="24%">Designation</th>
                                    <th width="15%">Manager</th>
                                    <th width="15%">Reviewer</th>
                                </tr>
                            </thead>
                            <tbody id="accountDirectoryTableBody">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
</div>