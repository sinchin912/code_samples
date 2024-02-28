<%@ include file="/init.jsp" %>
<portlet:resourceURL id="exportEmployee" var="exportEmployeeUrl"></portlet:resourceURL>
<div class="container">
    <div class="card">
         <div class="card-header">
               <div class="row mt-2">
                    <div class="col-3">
                        <h5>Employee Report</h5>
                    </div>
                    <div class="col-9 text-right">
                        <a class="btn btn-outline-secondary" href="<%= exportEmployeeUrl %>">Download</a>
                    </div>
               </div>
         </div>
    </div>
</div>