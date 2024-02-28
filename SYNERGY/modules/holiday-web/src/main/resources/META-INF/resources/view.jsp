<%@ include file="/init.jsp" %>

<table class="table table-sm table-striped mt-2" >
   <thead class="thead-dark">
      <tr>
         <th scope="col">Date</th>
         <th scope="col">Occasion</th>
      </tr>
   </thead>
   <tbody>
   <c:forEach items="${holidays}" var="holiday" varStatus="count">
      <tr>
         <td>${holiday.onDate}</td>
         <td>${holiday.name}</td>
      </tr>
   </c:forEach>
   </tbody>
</table>