<%@ include file="/init.jsp" %>

<portlet:actionURL name="saveStudent" var="saveStudentURL">
</portlet:actionURL>

<h1>Create/Edit Student Details</h1>

<aui:form name="fm" action="${saveStudentURL}">

    <aui:input name="name"> </aui:input>
    <aui:input name="dob"> </aui:input>
    <aui:input name="phoneNo"> </aui:input>
    <aui:input name="emailAddress"> </aui:input>
    <aui:input name="address"> </aui:input>

    <aui:button-row>
        <aui:button cssClass="btn-btn-primary" type="submit"/>
    </aui:button-row>

</aui:form>