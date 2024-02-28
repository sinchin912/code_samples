<%@ include file="/init.jsp" %>
<portlet:defineObjects />

<a href="" id="createStudentLink"></a>
<style>
    /* Add your CSS styles here */
    #studentData {
        margin-top: 20px;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        border: 1px solid #ccc;
    }

    th, td {
        padding: 8px;
        text-align: left;
        border-bottom: 1px solid #ccc;
    }

    th {
        background-color: #f2f2f2;
    }

    tr:hover {
        background-color: #f5f5f5;
    }

</style>

<portlet:renderURL var="createEditRender">
    <portlet:param name="mvcRenderCommandName" value="/create/edit"/>
</portlet:renderURL>

<a href="" id=""></a>
<a href="<%= createEditRender.toString() %>" id="createStudentLink">Create Student</a>
<div id="studentData">
    <table>
        <tr>
            <th>Name</th>
            <th>DOB</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Address</th>
        </tr>
        <c:forEach var="student" items="${entries}">
            <tr>
                <td>${student.name}</td>
                <td>${student.dob}</td>
                <td>${student.emailAddress}</td>
                <td>${student.phoneNo}</td>
                <td>${student.address}</td>
            </tr>
        </c:forEach>
    </table>
</div>

<script>
AUI().use('aui-io-request', function(A) {
    A.one('#createStudentLink').on('click', function(event) {
        event.preventDefault();

        A.io.request('<%= renderResponse.createResourceURL().toString() %>', {
            method: 'get',
            dataType: 'json',
            on: {
                success: function(event, id, xhr) {
                    // Handle the successful Ajax response here
                    var studentData = A.one('#studentData');
                    var data = xhr.responseText;
                    var students = JSON.parse(data);

                    if (students.length > 0) {
                        var html = '<table>';
                        html += '<tr><th>Name</th><th>DOB</th><th>Email</th><th>Phone</th><th>Address</th></tr>';

                        students.forEach(function(student) {
                            html += '<tr>';
                            html += '<td>' + student.name + '</td>';
                            html += '<td>' + student.dob + '</td>';
                            html += '<td>' + student.emailAddress + '</td>';
                            html += '<td>' + student.phoneNo + '</td>';
                            html += '<td>' + student.address + '</td>';
                            html += '</tr>';
                        });

                        html += '</table>';
                        studentData.setHTML(html);
                    } else {
                        studentData.setHTML('No student data available.');
                    }
                }
            }
        });
    });
});
</script>