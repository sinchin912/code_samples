<%@ include file="/init.jsp" %>
<portlet:resourceURL var="downloadUserManualUrl" id="downloadUserManual"></portlet:resourceURL>
<portlet:resourceURL var="searchEmployeeDetailsUrl" id="employeeDetailsByKeywords"></portlet:resourceURL>
<portlet:actionURL name="addSubSkill" var="addSubSkillUrl"></portlet:actionURL>
<portlet:actionURL name="addNewCertificate" var="addNewCertificateUrl"></portlet:actionURL>
<portlet:actionURL name="skillWorkflow" var="skillWorkflowUrl"></portlet:actionURL>
<portlet:resourceURL id="downloadEmployeeDataReport" var="downloadEmployeeDataReportUrl"></portlet:resourceURL>
<div class="container">
   <input type="hidden" id="searchEmployeeDetailsUrl" value="${searchEmployeeDetailsUrl}"/>
   <input type="hidden" id="skillWorkflowUrl" value="${skillWorkflowUrl}"/>
   <input type="hidden" id="portletNamespace"   value="<portlet:namespace/>" />
   <input type="hidden" id="skillMapGson" value='${skillMapGson}'  />
   <input type="hidden" id="downloadUserManualResourceUrl" value="${downloadUserManualUrl}"/>
   <ul class="nav nav-tabs nav-justified mb-3"  role="tablist">
      <li class="nav-item" id="leadRole" style="display:none" ><a class="nav-link" id="first-tab" data-toggle="tab" href="#first" role="tab" aria-controls="first" aria-selected="true" >Reviewer</a></li>
      <li class="nav-item" id="managerRole" style="display:none"><a class="nav-link" id="second-tab" data-toggle="tab" href="#second" role="tab" aria-controls="second" aria-selected="false" >Manager</a></li>
      <li class="nav-item" id="hrRole" style="display:none"><a class="nav-link" id="third-tab" data-toggle="tab" href="#third" role="tab" aria-controls="third" aria-selected="false" >HR</a></li>
      <li class="nav-item" id="leadershipRole" style="display:none"><a class="nav-link" id="fourth-tab" data-toggle="tab" href="#fourth" role="tab" aria-controls="fourth" aria-selected="false" >Leadership</a></li>
      <li class="nav-item" id="recruiterRole" style="display:none"><a class="nav-link" id="fifth-tab" data-toggle="tab" href="#fifth" role="tab" aria-controls="fifth" aria-selected="false" >Recruiter</a></li>
   </ul>
   <div class="tab-content">
      <div class="tab-pane fade" id="first" role="tabpanel" aria-labelledby="first-tab">
         <div class="card">
            <div class="card-header">
               <div class="row">
                  <div class="col pt-2">
                     <h5>Skill Assigned To Me</h5>
                  </div>
               </div>
            </div>
            <div class="card-body">
               <table
                  class="table table-sm table-bordered text-center table-striped table-hover "
                  id="leadTable">
                  <thead class="thead-dark">
                     <tr>
                        <th class="text-center align-middle" width="10%">Ecode</th>
                        <th class="text-center align-middle" width="30%">Employee Name</th>
                        <th class="text-center align-middle" width="20%">Account</th>
                        <th class="text-center align-middle" width="30%">Manager</th>
                        <th class="text-center align-middle" width="10%">Action</th>
                     </tr>
                  </thead>
                  <tbody>
                     <c:forEach items="${leadEmployeeList}" var="item"
                        varStatus="count">
                        <tr>
                           <td>${item.empEcode}</td>
                           <td>${item.empName}</td>
                           <td>${item.account}</td>
                           <td>${item.managerName}</td>
                           <td>
                              <form  method="post" action="${skillWorkflowUrl}"
                                 >
                                 <input type="hidden" value="${item.empEcode}"
                                    name="<portlet:namespace/>ecode" /> <input
                                    type="hidden" value="Trantor_Teamlead"
                                    name="<portlet:namespace/>role" />
                                 <c:choose>
                                    <c:when test="${item.overview == 'Action Required'}">
                                       <button  style="cursor: pointer;"
                                          title="Go to Action Form" class=" btn btn-sm btn-outline-primary ">
                                       Action
                                       </button>
                                    </c:when>
                                    <c:otherwise>
                                       <button  style="cursor: pointer;"
                                          title="Go to View Form" class=" btn btn-sm btn-outline-primary ">
                                       View
                                       </button>
                                    </c:otherwise>
                                 </c:choose>
                              </form>
                           </td>
                        </tr>
                     </c:forEach>
                  </tbody>
               </table>
            </div>
         </div>
         <div class="card">
            <div class="card-header">
               <div class="row">
                  <div class="col">
                     <h5>User Manual</h5>
                  </div>
                  <div class="col-3 text-right">
                     <button class="btn btn-outline-secondary" onClick="downloadUserManual(1);">Download
                     </button>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <div class="tab-pane fade" id="second" role="tabpanel" aria-labelledby="second-tab">
         <div class="card">
            <div class="card-header">
               <div class="row">
                  <div class="col pt-2">
                     <h5>My Team</h5>
                  </div>
               </div>
            </div>
            <div class="card-body">
               <table
                  class="table table-sm table-bordered  text-center table-striped table-hover"
                  id="managerTable">
                  <thead class="thead-dark">
                     <tr>
                        <th class="text-center align-middle" width="10%">Ecode</th>
                        <th class="text-center align-middle" width="30%">Employee Name</th>
                        <th class="text-center align-middle" width="20%">Account</th>
                        <th class="text-center align-middle" width="30%">Manager</th>
                        <th class="text-center align-middle" width="10%">Action</th>
                     </tr>
                  </thead>
                  <tbody>
                     <c:forEach items="${managerDataList}" var="item"
                        varStatus="count">
                        <tr>
                           <td>${item.empEcode}</td>
                           <td>${item.empName}</td>
                           <td>${item.account}</td>
                           <td>${item.managerName}</td>
                           <td>
                              <form action="${skillWorkflowUrl}" method="post"
                                 >
                                 <input type="hidden" value="${item.empEcode}"
                                    name="<portlet:namespace/>ecode" /> <input
                                    type="hidden" value="Trantor_Manager"
                                    name="<portlet:namespace/>role" />
                                 <c:choose>
                                    <c:when test="${item.overview == 'Action Required'}">
                                       <button  style="cursor: pointer;"
                                          title="Go to Action Form" class="btn btn-sm btn-outline-primary ">
                                        Action
                                       </button>
                                    </c:when>
                                    <c:when test="${item.overview == 'No Action Required'}">
                                       <button  style="cursor: pointer;"
                                          title="Go to View Form" class="btn btn-sm btn-outline-primary ">
                                        View
                                       </button>
                                    </c:when>
                                    <c:otherwise>
                                    </c:otherwise>
                                 </c:choose>
                              </form>
                           </td>
                        </tr>
                     </c:forEach>
                  </tbody>
               </table>
            </div>
         </div>
         <div class="card">
            <div class="card-header">
               <div class="row">
                  <div class="col">
                     <h5>User Manual</h5>
                  </div>
                  <div class="col-3 text-right">
                     <button class="btn btn-outline-secondary" onClick="downloadUserManual(2);">Download
                     </button>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <div class="tab-pane fade" id="third" role="tabpanel" aria-labelledby="third-tab">
         <div class="card">
            <div class="card-header">
               <div class="row">
                  <div class="col pt-2">
                     <h5>Search Employee</h5>
                  </div>
                  <div class="col pt-2  text-right">
                  <input type="text" placeholder="Enter at least 3 characters" id="keyword1" >
                  </div>
                  <div class="col-2 pt-2 text-right">
                     <button class="btn btn-outline-secondary"
                        onclick="searchEmpData(this, flag = 1);">Search</button>
                  </div>
               </div>
            </div>
            <div class="card-body" id="hrEmployeeDetailsDiv" style="display:none">
               <table
                  class="table table-sm table-bordered table-striped table-hover "
                  >
                  <thead class="thead-dark">
                     <tr>
                        <th >Ecode</th>
                        <th >Name</th>
                        <th >Account</th>
                        <th >Manager Name</th>
                        <th >Skill Details</th>
                     </tr>
                  </thead>
                  <tbody id="hrEmpDetailsListTableBody">
                  </tbody>
               </table>
            </div>
         </div>
         <div class="row">
            <div class="col-6">
               <div class="card">
                  <form method="post" action="${addSubSkillUrl}" id="addSkillForm"
                     onsubmit="return validateNewSkill()">
                     <div class="card-header">
                        <div class="row">
                           <div class="col pt-2">
                              <h5>Add SubSkill</h5>
                           </div>
                           <div class="col pt-2">
                              <select
                                 class="mdb-select md-form form-control form-control-sm"
                                 id="selectedCoreSkill" onChange="setSubSkillList(this)"
                                 name="<portlet:namespace/>coreSkill">
                                 <option value="" selected>Select Core Skill</option>
                                 <c:forEach items="${coreSkills}" var="item"
                                    varStatus="count">
                                    <option value="${item}">${item}</option>
                                 </c:forEach>
                              </select>
                           </div>
                        </div>
                     </div>
                     <div class="card-body" id="subSkillDetailsDiv" style="display:none">
                        <table
                           class="table table-sm table-bordered table-striped table-hover ">
                           <thead class="thead-dark">
                              <tr>
                                 <th width="20%">SubSkills</th>
                              </tr>
                           </thead>
                           <tbody id="subSkillTbody">
                              <c:forEach items="${skillMap}" var="coreSkill"
                                 >
                                 <c:forEach items="${coreSkill.value}" var="subSkill">
                                    <tr class="${coreSkill.key}">
                                       <td>${subSkill}</td>
                                    </tr>
                                 </c:forEach>
                              </c:forEach>
                           </tbody>
                           <tbody>
                              <tr id="additionalSubSkillRow">
                                 <td><input type="text"
                                    class="form-control form-control-sm" maxlength="75"
                                    required="required" placeholder="Enter New SubSkill"
                                    onblur="$(this).val($(this).val().trim())"
                                    name="<portlet:namespace/>newSubSkill" id="newSubskill" />
                                 </td>
                              </tr>
                           </tbody>
                        </table>
                        <div class="row mt-4" id="submitCancelSubSkillButtonDiv">
                           <div class="col text-center">
                              <button class="btn btn-primary " >Submit</button>
                           </div>
                           <div class="col text-center">
                              <button class="btn btn-outline-secondary "
                                 onclick="cancel()" >Cancel</button>
                           </div>
                        </div>
                     </div>
                  </form>
               </div>
            </div>
            <div class="col-6">
               <div class="card">
                  <div class="card-header">
                     <div class="row">
                        <div class="col pt-2">
                           <h5>Add Certification</h5>
                        </div>
                        <div class="col pt-2 text-right">
                           <button class="btn  btn-outline-secondary" id="addButton"
                              onclick="add()" >Add</button>
                        </div>
                     </div>
                  </div>
                  <div class="card-body" id="certificateDetailsDiv" style="display:none">
                     <form method="post" action="${addNewCertificateUrl}" id="addCertificateForm"
                        onsubmit="return validateNewCertificate()">
                        <table
                           class="table table-sm table-bordered table-striped table-hover">
                           <thead class="thead-dark">
                              <tr>
                                 <th width="20%">Category</th>
                              </tr>
                           </thead>
                           <tbody id="subSkillTbody">
                              <c:forEach items="${certificates}" var="item"
                                 >
                                 <tr>
                                    <td class="certificates">${item}</td>
                                 </tr>
                              </c:forEach>
                           </tbody>
                           <tbody>
                              <tr id="additionalCategoryRow">
                                 <td><input type="text"
                                    class="form-control form-control-sm" maxlength="75"
                                    required="required" placeholder="Enter New Category"
                                    onblur="$(this).val($(this).val().trim())"
                                    name="<portlet:namespace/>newCertificateName" id="newCertificate" />
                                 </td>
                              </tr>
                           </tbody>
                        </table>
                        <div class="row mt-4" id="submitCancelCategoryButtonDiv">
                           <div class="col text-center">
                              <button class="btn btn-primary " >Submit</button>
                           </div>
                           <div class="col text-center">
                              <button class="btn btn-outline-secondary"
                                 onclick="cancelCertificate()" >Cancel</button>
                           </div>
                        </div>
                     </form>
                  </div>
               </div>
            </div>
         </div>
         <div class="card">
            <div class="card-header">
               <div class="row">
                  <div class="col pt-2">
                     <h5>Employees Report</h5>
                  </div>
                  <div class="col-2 pt-2 text-right">
                     <a class="btn btn-outline-secondary" href="<%= downloadEmployeeDataReportUrl %>">Download
                     </a>
                  </div>
               </div>
            </div>

         </div>
         <div class="card">
            <div class="card-header">
               <div class="row">
                  <div class="col">
                     <h5>User Manual</h5>
                  </div>
                  <div class="col-3 text-right">
                     <button class="btn btn-outline-secondary" onClick="downloadUserManual(3);">Download
                     </button>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <div class="tab-pane fade" id="fourth" role="tabpanel" aria-labelledby="fourth-tab">


          <div class="card">
             <div class="card-header">
                <div class="row">
                   <div class="col pt-2">
                      <h5>Search Employee</h5>
                   </div>
                   <div class="col pt-2  text-right">
                   <input type="text" placeholder="Enter at least 3 characters" id="keyword2" >
                   </div>
                   <div class="col-2 pt-2 text-right">
                      <button class="btn btn-outline-secondary"
                         onclick="searchEmpData(this, flag = 2);">Search</button>
                   </div>
                </div>
             </div>
             <div class="card-body" id="leaderShipEmployeeDetailsDiv" style="display:none">
                <table
                   class="table table-sm table-bordered table-striped table-hover "
                   >
                   <thead class="thead-dark">
                      <tr>
                         <th >Ecode</th>
                         <th >Name</th>
                         <th >Account</th>
                         <th >Manager Name</th>
                         <th >Skill Details</th>
                      </tr>
                   </thead>
                   <tbody id="leaderShipEmpDetailsListTableBody">
                   </tbody>
                </table>
             </div>
          </div>
         <div class="card">
            <div class="card-header">
               <div class="row">
                  <div class="col pt-2">
                     <h5>Employees Report</h5>
                  </div>
                  <div class="col-2 pt-2 text-right">
                     <a class="btn btn-outline-secondary" href="<%= downloadEmployeeDataReportUrl %>">Download
                     </a>
                  </div>
               </div>
            </div>
         </div>
         <div class="card">
            <div class="card-header">
               <div class="row">
                  <div class="col">
                     <h5>User Manual</h5>
                  </div>
                  <div class="col-3 text-right">
                     <button class="btn btn-outline-secondary" onClick="downloadUserManual(4);">Download
                     </button>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <div class="tab-pane fade" id="fifth" role="tabpanel" aria-labelledby="fifth-tab">

         <div class="card">
            <div class="card-header">
               <div class="row">
                  <div class="col pt-2">
                     <h5>Search Employee</h5>
                  </div>
                  <div class="col pt-2  text-right">
                  <input type="text" placeholder="Enter at least 3 characters" id="keyword3" >
                  </div>
                  <div class="col-2 pt-2 text-right">
                     <button class="btn btn-outline-secondary"
                        onclick="searchEmpData(this, flag = 3);">Search</button>
                  </div>
               </div>
            </div>
            <div class="card-body" id="recruiterEmployeeDetailsDiv" style="display:none">
               <table
                  class="table table-sm table-bordered table-striped table-hover "
                  >
                  <thead class="thead-dark">
                     <tr>
                        <th >Ecode</th>
                        <th >Name</th>
                        <th >Account</th>
                        <th >Manager Name</th>
                        <th >Skill Details</th>
                     </tr>
                  </thead>
                  <tbody id="recruiterEmpDetailsListTableBody">
                  </tbody>
               </table>
            </div>
         </div>
         <div class="card">
            <div class="card-header">
               <div class="row">
                  <div class="col">
                     <h5>User Manual</h5>
                  </div>
                  <div class="col-3 text-right">
                     <button class="btn btn-outline-secondary" onClick="downloadUserManual(5);">Download
                     </button>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>
</div>