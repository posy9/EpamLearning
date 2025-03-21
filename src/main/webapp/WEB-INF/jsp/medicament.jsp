<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Medicament</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/controller?command=show_main">Go to main page</a>
<c:forEach var="medicament" items="${requestScope.medicamentToShow}">
    <p>${medicament.name} ${medicament.price} ${medicament.category} ${medicament.country} ${medicament.information} ${medicament.amount}</p>
    <br>
</c:forEach>
<c:if test="${sessionScope.currentPage != 1}">
    <a href="${pageContext.request.contextPath}/controller?command=show_medicament&page=${sessionScope.currentPage - 1}">Previous</a>
</c:if>
<p>${sessionScope.currentPage}</p>
<c:if test="${sessionScope.currentPage lt sessionScope.numberOfPages}">
    <a href="${pageContext.request.contextPath}/controller?command=show_medicament&page=${sessionScope.currentPage + 1}">Next</a>
</c:if>
</body>
</html>
