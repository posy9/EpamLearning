<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:choose>
<c:when test="${not empty sessionScope.user}">
    <p>Hello, ${sessionScope.user.login}</p>
    <a href="${pageContext.request.contextPath}/controller?command=show_medicament&page=1">Medicament page</a>
    <br>
    <a href="${pageContext.request.contextPath}/controller?command=logout">Log out</a>
</c:when>
<c:otherwise>
    <a href="${pageContext.request.contextPath}/controller?command=show_signup">Sign up</a>
    <br>
    <a href="${pageContext.request.contextPath}/controller?command=show_login">Log in</a>
</c:otherwise>
</c:choose>

</body>
</html>
