<%@ include file="/init.jsp" %>

<portlet:actionURL name="createFolder" var="createFolderUrl"></portlet:actionURL>
<portlet:actionURL name="uploadFile" var="uploadFileUrl"></portlet:actionURL>
<portlet:resourceURL id="executeScheduler" var="executeSchedulerUrl"></portlet:resourceURL>
<portlet:resourceURL id="getScheduler" var="getSchedulerUrl"></portlet:resourceURL>
<portlet:resourceURL id="getEmail" var="getEmailUrl"></portlet:resourceURL>
<portlet:resourceURL id="getUser" var="getUserUrl"></portlet:resourceURL>

<div class="container">
    <input type="hidden" id="portletNamespace" value="<portlet:namespace/>"/>
    <input type="hidden" id="executeSchedulerResourceUrl" value="${executeSchedulerUrl}"/>
    <input type="hidden" id="getSchedulerResourceUrl" value="${getSchedulerUrl}"/>
    <input type="hidden" id="getEmailResourceUrl" value="${getEmailUrl}"/>
    <input type="hidden" id="getUserResourceUrl" value="${getUserUrl}"/>
    <input type="hidden" id="emailStats" value='${emailStats}'/>
   <ul class="nav nav-tabs nav-justified my-3" role="tablist">
      <li class="nav-item"><a class="nav-link active" id="drive-tab" data-toggle="tab" href="#drive" role="tab" aria-controls="drive" aria-selected="true">Drive</a></li>
      <li class="nav-item"><a class="nav-link" id="scheduler-tab" data-toggle="tab" href="#scheduler" role="tab" aria-controls="scheduler" aria-selected="true">Scheduler</a></li>
      <li class="nav-item"><a class="nav-link" id="email-tab" data-toggle="tab" href="#email" role="tab" aria-controls="email" aria-selected="true">Email</a></li>
      <li class="nav-item"><a class="nav-link" id="user-tab" data-toggle="tab" href="#user" role="tab" aria-controls="user" aria-selected="true">User</a></li>
   </ul>
   <div class="tab-content">

      <div class="tab-pane fade show active" id="drive" role="tabpanel" aria-labelledby="drive-tab">
         <c:if test="${hasDrive}">
         <div class="card mt-2">
             <div class="card-header my-1">
                    <div class="row">
                         <div class="col-6 pt-2 float-left">
                             <h4>Upload File</h4>
                         </div>
                         <div class="col-6 text-right">
                            <form method="post" enctype="multipart/form-data class="form-inline float-right"  action="${uploadFileUrl}" onsubmit="return confirmUploadFile()" >
                            <input type="hidden" id="newFilename" name="<portlet:namespace/>newFilename" value="" />
                            <div class="input-group">
                              <div class="input-group-prepend">
                                <select class="custom-select bg-white" required name="<portlet:namespace/>driveName" >
                                    <option value="" selected >Select Module</option>
                                    <c:forEach items="${drives}" var="drive" varStatus="count">
                                       <c:if test="${not empty drive.id}">
                                           <option value="${drive.name}">${drive.name}</option>
                                       </c:if>
                                    </c:forEach>
                                </select>
                              </div>
                              <div class="custom-file ml-2">
                                <input required type="file" multiple id="newFile" name="<portlet:namespace/>newFile" onchange="newFileOnChange()" />
                              </div>
                              <div class="input-group-append">
                                <button class="btn btn-primary ml-2" type="submit">Upload</button>
                              </div>
                            </div>
                             </form>
                         </div>
                    </div>
             </div>
         </div>
         </c:if>
         <table class="table table-sm table-striped mt-2" >
            <thead class="thead-dark">
               <tr>
                  <th scope="col">Module Name</th>
                  <th scope="col">File Count</th>
                  <th scope="col">Updated On</th>
               </tr>
            </thead>
            <tbody>
            <c:forEach items="${drives}" var="drive" varStatus="count">
               <c:if test="${not empty drive.id}">
               <tr>
                  <td>${drive.name}</td>
                  <td>${drive.fileCount}</td>
                  <td>${drive.updatedOn}</td>
               </tr>
               </c:if>
            </c:forEach>
            </tbody>
         </table>
         <c:if test="${!allDrive}">
         <form method="post" class="pt-2"  action="${createFolderUrl}" onsubmit="return confirmCreateFolder()">
            <div class="input-group my-3">
                  <select required name="<portlet:namespace/>driveName" class="custom-select bg-white">
                      <option value="" selected >Select Module</option>
                      <c:forEach items="${drives}" var="drive" varStatus="count">
                         <c:if test="${empty drive.id}">
                             <option value="${drive.name}">${drive.name}</option>
                         </c:if>
                      </c:forEach>
                  </select>
                  <div class="input-group-append">
                    <button class="btn btn-primary" type="submit">Create Folder</button>
                  </div>
            </div>
         </form>
         </c:if>
      </div>

      <div class="tab-pane fade" id="scheduler" role="tabpanel" aria-labelledby="scheduler-tab">
          <div class="row pt-2">
              <div class="col-2">
                 <h3>Schedulers on</h3>
              </div>
              <div class="col-2">
                <input type="text" class="form-control form-control-sm" readonly="true" value="" id="schedulerDate" />
              </div>
          </div>
         <table class="table table-sm table-striped my-3">
            <thead class="thead-dark">
               <tr>
                  <th scope="col">View</th>
                  <th scope="col">Name</th>
                  <th scope="col">Schedule</th>
                  <th scope="col">Status</th>
                  <th scope="col">Triggered</th>
                  <th scope="col">Action</th>
               </tr>
            </thead>
            <tbody id="schedulerTableBody">
            </tbody>
         </table>
      </div>

      <div class="tab-pane fade" id="email" role="tabpanel" aria-labelledby="email-tab">
         <div class="card mt-3">
            <div class="card-header">
                <div class="row">
                    <div class="col-6">
                        <h4>Email stats</h4>
                    </div>
                    <div class="col-6">
                        <button onclick="resetZoom()" class="btn btn-sm btn-secondary float-right">Reset</button>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <canvas id="emailStatCanvas"></canvas>
            </div>
          </div>
          <div class="row pt-5">
              <div class="col-2">
                 <h3>Emails on</h3>
              </div>
              <div class="col-2">
                <input type="text" class="form-control form-control-sm" readonly="true" value="" id="emailDate" />
              </div>
          </div>
         <table class="table table-sm table-striped my-3">
            <thead class="thead-dark">
               <tr>
                  <th scope="col">View</th>
                  <th scope="col">Module</th>
                  <th scope="col">Scheduled</th>
                  <th scope="col">TO</th>
                  <th scope="col">CC</th>
                  <th scope="col">BCC</th>
                  <th scope="col">Subject</th>
               </tr>
            </thead>
            <tbody id="emailTableBody">
            </tbody>
         </table>
      </div>

      <div class="tab-pane fade" id="user" role="tabpanel" aria-labelledby="user-tab">
          <div class="row pt-2">
              <div class="col-2">
                 <h3>Users on</h3>
              </div>
              <div class="col-2">
                <input type="text" class="form-control form-control-sm" readonly="true" value="" id="userDate" />
              </div>
          </div>
        <table class="table table-sm table-striped mt-2">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">View</th>
                    <th scope="col">User Name</th>
                    <th scope="col">Login time</th>
                    <th scope="col">User IP</th>
                    <th scope="col">User Agent</th>
                </tr>
            </thead>
            <tbody id="userTableBody">
            </tbody>
        </table>
      </div>
   </div>

    <!-- Confirm Modal -->
    <div class="modal fade" id="confirmAdminModal" tabindex="-1" role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header text-center border-0">
            <h6 class="modal-title text-dark" id="confirmAdminModalLabel"></h6>
          </div>
          <div class="modal-body text-center p-0 border-0">
            <p id="confirmAdminModalDescription"></p>
          </div>
          <div class="modal-footer d-flex justify-content-center border-0" >
            <button type="button" id="confirmAdmin_yes" class="btn btn-outline-success mr-5">Yes</button>
            <button type="button" id="confirmAdmin_no" class="btn btn-outline-danger">No</button>
          </div>
        </div>
      </div>
    </div>

</div>
