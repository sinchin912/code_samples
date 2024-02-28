<%@ include file="/init.jsp" %>
<portlet:actionURL name="updateSkills" var="updateSkills"></portlet:actionURL>
<portlet:actionURL name="updateCertificates" var="updateCertificates"></portlet:actionURL>
<portlet:resourceURL var="downloadUserManualUrl" id="downloadUserManual"></portlet:resourceURL>
<portlet:resourceURL var="downloadCertificatesUrl" id="downloadCertificates"></portlet:resourceURL>
<portlet:renderURL var="skillRenderURL" windowState="normal"></portlet:renderURL>
<div class="container">
   <input type="hidden" id="certificateSet"   value='${certificateSet}' />
   <input type="hidden" id="downloadCertificatesUrl" value="${downloadCertificatesUrl}"/>
   <input type="hidden" id="skillsMap"   value='${skillsMap}' />
   <input type="hidden" id="downloadUserManualResourceUrl" value="${downloadUserManualUrl}"/>
   <input type="hidden" id="portletNamespace"   value="<portlet:namespace/>" />
   <c:set var="pageUrl" value="${fn:split(skillRenderURL.toString(),'?')}" />
   <div class="card">
      <div class="card-header">
      <h5>General Information</h5>
      </div>
      <div class="card-body">
         <div class="row">
            <div class="col-md-2 mt-1 text-right">
               <label>Employee Name</label>
            </div>
            <div class="col-md-4">
               <input type="text" class=" form-control-sm form-control-plaintext"
                  readonly value="${empCoreDetails.empName}" />
            </div>
            <div class="col-md-2 mt-1 text-right">
               <label>Ecode</label>
            </div>
            <div class="col-md-4">
               <input type="text" class=" form-control-sm form-control-plaintext"
                  readonly value="${empCoreDetails.empEcode}" />
            </div>
         </div>
         <div class="row">
            <div class="col-md-2 mt-1 text-right">
               <label>Designation</label>
            </div>
            <div class="col-md-4">
               <input type="text" class=" form-control-sm form-control-plaintext"
                  value="${empCoreDetails.empDesignation}" readonly />
            </div>
            <div class="col-md-2 mt-1 text-right">
               <label>Band</label>
            </div>
            <div class="col-md-4">
               <input type="text" class=" form-control-sm form-control-plaintext"
                  value="${empCoreDetails.empBand}" readonly />
            </div>
         </div>
         <div class="row">
            <div class="col-md-2 mt-1 text-right">
               <label>Account</label>
            </div>
            <div class="col-md-4">
               <input type="text" class=" form-control-sm form-control-plaintext"
                  readonly value="${empCoreDetails.account}" />
            </div>
            <div class="col-md-2 mt-1 text-right">
               <label>Mobile</label>
            </div>
            <div class="col-md-4">
               <input type="text" class=" form-control-sm form-control-plaintext"
                  readonly value="${empCoreDetails.empMobileNo}" />
            </div>
         </div>
         <div class="row">
            <div class="col-md-2 mt-1 text-right">
               <label>Manager Name</label>
            </div>
            <div class="col-md-4">
               <input type="text" class=" form-control-sm form-control-plaintext"
                  readonly value="${empCoreDetails.managerName}" />
            </div>
            <div class="col-md-2 mt-1 text-right">
               <label>Manager Email</label>
            </div>
            <div class="col-md-4">
               <input type="text" class=" form-control-sm form-control-plaintext"
                  readonly value="${empCoreDetails.managerEmail}" />
            </div>
         </div>
         <div class="row">
            <div class="col-md-2 mt-1 text-right">
               <label>Reviewer Name</label>
            </div>
            <div class="col-md-4">
               <input type="text" class=" form-control-sm form-control-plaintext"
                  readonly value="${empCoreDetails.reviewerName}" />
            </div>
            <div class="col-md-2 mt-1 text-right">
               <label>Reviewer Email</label>
            </div>
            <div class="col-md-4">
               <input type="text" class=" form-control-sm form-control-plaintext"
                  readonly value="${empCoreDetails.reviewerEmail}" />
            </div>
         </div>
      </div>
   </div>
   <div class="card pb-2">
      <div class="card-header">
         <div class="row pt-2">
            <div class="col-md-8">
               <h5>My Skills</h5>
            </div>
            <div class="col-md-4 text-right">
               <button type="button"
                  class="btn btn-outline-secondary" title="Click Here to add/update your Skills" id="editSkillButton" onClick="changeSkillFormToEditMode();">Update </button>
               <a class="btn btn-outline-secondary" style="display : none" onclick="return cancelButton(1)" id="cancelButtonSkill" >Cancel</a>
            </div>
         </div>
      </div>
      <div class="card-body">
         <div  class="border border-dark rounded p-2 mb-3" >
            <label> General Instructions for skill level :</label>
            <ul style="font-size:0.7em;">
               <li>Basic - Have common knowledge or understanding of the basic concepts</li>
               <li>Beginner - Have classroom scenarios or as a trainee on-the-job experience and expected to need help when performing</li>
               <li>Intermediate - Able to successfully complete tasks in this competency but expert level help needed from time to time</li>
               <li>Advanced - Can perform without assistance and recognized as a person to ask</li>
               <li>Expert - Known expert in this area and can provide guidance, troubleshoot and answer questions</li>
            </ul>
         </div>
         <form onSubmit="return confirmSkillFormSubmission();" id="skillForm" action="${updateSkills}" method="post" >
            <input type="hidden" id="totalRows" name="<portlet:namespace/>totalRows" />
            <input type="hidden" id="deletedSkillId" name="<portlet:namespace/>deletedSkillId" />
            <input type="hidden" id="skillActionPerformed" name="<portlet:namespace/>skillActionPerformed" />
            <table class="table table-bordered table-striped text-center mt-3" id="empSkillTable">
               <thead class="thead-dark">
                  <tr>
                     <th class="text-center align-middle" width="4%" style="display : none">
                        <button type="button" class="btn btn-sm btn-outline-success" id="addSkillRow" onclick='addNewSkillRow()'>
                        <i class="far fa-plus-square"></i>
                        </button>
                     </th>
                     <th class="text-center align-middle" width="20%">Core & Sub Skill *</th>
                     <th class="text-center align-middle" width="18%">Type & Level *</th>
                     <th class="text-center align-middle" width="18%">Tool</th>
                     <th class="text-center align-middle" width="18%">Version</th>
                     <th class="text-center align-middle" width="10%">Validity</th>
                     <th class="text-center align-middle" width="16%">Status</th>
                  </tr>
               </thead>
               <tbody id="empSkillTableBody">
                  <c:forEach items="${empSkillsSet}" var="item" varStatus="count">
                     <tr>
                        <td class="p-0" style="display : none" id="action${count.index+1}">
                           <c:choose>
                              <c:when test="${item.status == 'Submitted' || item.coreSkill == 'Information Security'}">
                        </td>
                        </c:when><c:otherwise>
                        <button type="submit" id="${item.skillId}" class="btn btn-outline-danger btn-sm" onClick="return customSkillFormValidation(2,this)"><i class="far fa-trash-alt"></i></button>
                        </td></c:otherwise></c:choose>
                        <td class="p-0">
                        <input type="hidden" value="${item.skillId}" name="<portlet:namespace/>skillId${count.index+1}" />
                        <select class="custom-select custom-select-sm mb-1" id="coreSkill${count.index+1}"  onChange="setSubSkillDropdown(this)" name="<portlet:namespace/>coreSkill${count.index+1}">
                              <c:forEach items="${skillMap}" var="skills" varStatus="index">
                                 <option <c:choose><c:when test="${item.coreSkill != skills.key}">style="display:none" </c:when> <c:otherwise> selected </c:otherwise></c:choose> value="${skills.key}">${skills.key}</option>
                              </c:forEach>
                        </select>
                        <select class="custom-select custom-select-sm mb-1" id="subSkill${count.index+1}" name="<portlet:namespace/>subSkill${count.index+1}">
                        <c:forEach items="${skillMap}" var="coreSkill" >
                                 <c:forEach items="${coreSkill.value}" var="subSkill">
                                    <option <c:choose><c:when test="${item.subSkill != subSkill}">style="display:none"</c:when> <c:otherwise> selected </c:otherwise></c:choose> class="${coreSkill.key}" value="${subSkill}">${subSkill}</option>
                                 </c:forEach>
                        </c:forEach>
                        </select>
                        </td>
                        <td class="p-0"><select class="custom-select custom-select-sm mb-1" id="type${count.index+1}" name="<portlet:namespace/>type${count.index+1}">
                           <option <c:choose><c:when test="${item.type=='true'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${item.type !=null}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Primary">Primary</option>
                           <option <c:choose><c:when test="${item.type=='false'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${item.type !=null}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="Secondary">Secondary</option>
                           </select>
                            <select class="custom-select custom-select-sm mb-1" id="proficiencyLevel${count.index+1}" name="<portlet:namespace/>proficiencyLevel${count.index+1}">
                             <option <c:choose><c:when test="${item.proficiencyLevel=='1'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${item.proficiencyLevel !=''}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="1">Basic</option>
                             <option <c:choose><c:when test="${item.proficiencyLevel=='2'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${item.proficiencyLevel !=''}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="2">Beginner</option>
                             <option <c:choose><c:when test="${item.proficiencyLevel=='3'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${item.proficiencyLevel !=''}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="3">Intermediate</option>
                             <option <c:choose><c:when test="${item.proficiencyLevel=='4'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${item.proficiencyLevel !=''}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="4">Advanced</option>
                             <option <c:choose><c:when test="${item.proficiencyLevel=='5'}"> selected="selected" </c:when> <c:otherwise> <c:if test="${item.proficiencyLevel !=''}"> style="display:none" disabled</c:if> </c:otherwise></c:choose>value="5">Expert</option>
                           </select>
                        </td>
                        <td class="p-0"><textarea rows="2" cols="50" maxlength="1000" style="height: 85px;" class="form-control-sm form-control-plaintext pl-2" readonly id="tool${count.index+1}" name="<portlet:namespace/>tool${count.index+1}">${item.tool}</textarea></td>
                        <td class="p-0"><textarea rows="2" cols="50" maxlength="1000" style="height: 85px;" class="form-control-sm form-control-plaintext pl-2"  readonly id="version${count.index+1}" name="<portlet:namespace/>version${count.index+1}">${item.version}</textarea></td>
                        <td class="p-0 "><input type="text"  style="cursor: pointer" class="form-control-sm form-control-plaintext  text-center" readonly id="validity${count.index+1}" name="<portlet:namespace/>validity${count.index+1}" value="${item.validity}" /></td>
                        <td <c:if test="${item.status == 'Submitted'}"> class="p-0 text-center text-primary" </c:if> <c:if test="${item.status == 'Approved'}"> class="p-0 text-center  text-success" </c:if> <c:if test="${item.status == 'Disapproved'}"> class="p-0 text-center  text-danger" </c:if> >${item.status}</td>
                     </tr>
                  </c:forEach>
               </tbody>
            </table>
            <p <c:if test = "${empSkillsSet.size() > 0}"> style="display:none" </c:if> class="text-center m-3" id="noSkill">No Skills added</p>
            <c:if test="${empCoreDetails.comment != ''}">
               <div class="row mt-3" id="rejectSkillRsnDiv">
                  <div class="col-12">
                     <strong>Manager Comments for Disapproval</strong>
                     <textarea class="form-control-sm form-control-plaintext border border-dark pl-2" style="height: 100px;" rows="2" cols="10" readonly>${empCoreDetails.comment}</textarea>
                  </div>
               </div>
            </c:if>
            <div class="row mt-3" id="submitCancelSkillDiv" class="mt-4" style="display : none">
               <div class="col text-center">
                  <button class="btn btn-primary" type="submit"
                     onClick="return customSkillFormValidation(1,this)">Submit</button>
               </div>
            </div>
         </form>
      </div>
   </div>
   <div class="card pb-2">
      <div class="card-header">
         <div class="row pt-2">
            <div class="col-md-8">
               <h5>My Certifications</h5>
            </div>
            <div class="col-md-4 text-right">
               <button type="button" class="btn btn-outline-secondary" id="editCertificateButton" onClick="changeCertificateFormToEditMode()">Update</button>
               <a class="btn btn-outline-secondary" style="display : none" onclick="return cancelButton(2)" id="cancelButtonCertificate">Cancel</a>
            </div>
         </div>
      </div>
      <div class="card-body">
         <form  method="post" enctype="multipart/form-data" id="certificateForm" onSubmit="return confirmCertificateFormSubmission();" action="${updateCertificates}">
            <input type="hidden" id="totalRowsCertificate" name="<portlet:namespace/>totalRowsCertificate" />
            <input type="hidden" id="deletedCertificateId" name="<portlet:namespace/>deletedCertificateId" />
            <input type="hidden" id="certificateActionPerformed" name="<portlet:namespace/>certificateActionPerformed" />
            <table class="table table-sm  table-bordered  table-striped text-center" id="empCertificateTable">
               <thead class="thead-dark">
                  <tr>
                     <th class="text-center align-middle" width="4%" style = "display:none">
                        <button type="button" class="btn btn-sm btn-outline-success" id="addCertificateRow"  onclick='addNewCertificateRow()'><i class="fas fa-plus-square"></i></button>
                     </th>
                     <th class="text-center align-middle" width="18%">Category *</th>
                     <th class="text-center align-middle" width="14%">Name *</th>
                     <th class="text-center align-middle" width="26%">Description</th>
                     <th class="text-center align-middle" width="15%">Expiry Date
                     </th>
                     <th class="text-center align-middle" width="18%">Upload
                        Certificate *
                     </th>
                  </tr>
               </thead>
               <tbody id="empCertificateTableBody">
                  <c:forEach items="${certificatesList}" var="item"
                     varStatus="count">
                     <tr>
                        <td class="p-0" style = "display:none">
                           <button type="submit" id="${item.certificateId}" class="btn btn-sm btn-outline-danger"  onClick="return customCertificateFormValidation(2,this)">
                           <i class="far fa-trash-alt"></i></button>
                        </td>
                        <td class="p-0 align-top"><input type="text" class="form-control-sm form-control-plaintext pl-2" value="${item.certificateCategory}" readonly /></td>
                        <td class="p-0 align-top"><input type="text" title="${item.certificateName}" class="form-control-sm form-control-plaintext pl-2" value="${item.certificateName}" readonly /></td>
                        <td class="p-0 align-top"><textarea rows="2" cols="50" style="height: 65px" class="form-control-sm form-control-plaintext pl-2" readonly>${item.certificateDesc}</textarea></td>
                        <td class="p-0 align-top"><input type="text" style="cursor: pointer" class="form-control-sm form-control-plaintext pl-2" value="${item.expiryDate}" readonly /></td>
                        <td class="p-0 ">
                           <button type="button" class="btn btn-link  btn-sm" onClick="downloadAttachments('${item.certificateId}','${item.fileName}');" ><i class="fa-solid fa-download"></i> ${item.fileName}</button>
                        </td>
                     </tr>
                  </c:forEach>
               </tbody>
            </table>
             <p <c:if test = "${certificatesList.size() > 0}"> style="display:none" </c:if> class="text-center" id="noCertificate">No Certifications added</p>
            <div class="row" id="submitCertificateDiv" class="mt-4" style= "display : none">
               <div class="col text-center">
                  <button class="btn btn-primary" onClick="return customCertificateFormValidation(1,this)" >Submit</button>
               </div>
            </div>
         </form>
      </div>
   </div>
   <div class="card">
      <div class="card-header">
         <div class="row">
            <div class="col">
               <h5>User Manual</h5>
            </div>
            <div class="col-3 text-right">
               <button class="btn btn-outline-secondary" onClick="downloadUserManual();">Download
               </button>
            </div>
         </div>
      </div>
   </div>
</div>