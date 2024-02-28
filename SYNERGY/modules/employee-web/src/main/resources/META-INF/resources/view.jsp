<%@ include file="/init.jsp" %>
<portlet:resourceURL id="employeeDirectorySearch" var="employeeDirectorySearchUrl"></portlet:resourceURL>
<div class="container">
   <input type="hidden" id="employeeDirectorySearchResourceUrl" value="${employeeDirectorySearchUrl}" />
   <input type="hidden" id="portletNamespace"   value="<portlet:namespace/>" />
   <div class="card">
      <div class="card-header">
         <div class="row">
            <div class="col-3">
             <input id="name" class="form-control" placeholder="Name" name="<portlet:namespace/>name" type="text" />
            </div>
            <div class="col-3">
             <input id="ecode" class="form-control" placeholder="Ecode" name="<portlet:namespace/>ecode" type="text"/>
            </div>
            <div class="col-6">
             <button class="btn btn-outline-secondary"  onclick="doSearchUser(this);">Search</button>
            </div>
         </div>
      </div>
      <div class="card-body" id="employeeTable" style="display:none">
           <table class="table table-sm table-bordered table-striped table-hover table-custom" >
              <thead class="thead-dark">
                 <tr>
                    <th width="8%">Ecode</th>
                    <th width="15%">Name</th>
                    <th width="20%">Email Address</th>
                    <th width="10%">Skype</th>
                    <th width="15%">Account</th>
                    <th width="15%">Manager Name</th>
                    <th width="25%">Designation</th>
                 </tr>
              </thead>
              <tbody id="employeeTableBody"></tbody>
           </table>
      </div>
   </div>
</div>
