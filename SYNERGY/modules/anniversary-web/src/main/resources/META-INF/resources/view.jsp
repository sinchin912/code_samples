<%@ include file="/init.jsp" %>

<portlet:resourceURL id="getPhoto" var="getPhotoUrl"></portlet:resourceURL>
<input type="hidden" id="portletNamespace" value="<portlet:namespace/>"/>
<input type="hidden" id="getPhotoResourceUrl" value="${getPhotoUrl}"/>

<div class="row">
    <div class="col-6">
        <h5>BIRTHDAYS</h5>
        <c:forEach var="birthday" items="${birthdays}">
          <strong><c:out value="${birthday.key}"/></strong><br>
          <c:set var = "birthdayList" scope = "session" value = "${birthday.value}"/>
          <c:choose>
              <c:when test="${empty birthdayList}">
                 No Birthdays<br>
              </c:when>
              <c:otherwise>
                <c:forEach var="bEmployee" items="${birthdayList}">
                <p style="margin-bottom:0px;"><a id="anchor_${bEmployee.photoId}" href="" data-lightbox="birthday"><img class="pr-2 fn_image" style="height:25px;" id="${bEmployee.photoId}" src="<%= themeDisplay.getPathThemeImages() %>/spinner.gif" /></a><span class="pr-2"><c:out value="${bEmployee.name}"/></span></p>
                </c:forEach>
              </c:otherwise>
          </c:choose>
        </c:forEach>
    </div>
    <div class="col-6">
        <h5>ANNIVERSARIES</h5>
        <c:forEach var="anniversary" items="${anniversaries}">
          <strong><c:out value="${anniversary.key}"/></strong><br>
          <c:set var = "anniversaryList" scope = "session" value = "${anniversary.value}"/>
          <c:choose>
              <c:when test="${empty anniversaryList}">
                 No Anniversaries<br>
              </c:when>
              <c:otherwise>
                <c:forEach var="aEmployee" items="${anniversaryList}">
                <p style="margin-bottom:0px;"><a id="anchor_${aEmployee.photoId}" href="" data-lightbox="anniversary"><img class="pr-2 fn_image" style="height:25px;" id="${aEmployee.photoId}" src="<%= themeDisplay.getPathThemeImages() %>/spinner.gif" /></a><span class="pr-2"><c:out value="${aEmployee.name}"/></span><span class="pr-2"><small><c:out value="${aEmployee.comment}"/></small></span></p>
                </c:forEach>
              </c:otherwise>
          </c:choose>
        </c:forEach>
    </div>
</div>