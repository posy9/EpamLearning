<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="l10n.page.main" var="main"/>
<fmt:message bundle="${main}" key="label.title" var="pageTitle"/>
<fmt:message bundle="${main}" key="label.hello" var="helloMessage"/>
<fmt:message bundle="${main}" key="label.link.medicament" var="medicamentMessage"/>
<fmt:message bundle="${main}" key="label.link.logout" var="logoutMessage"/>
<fmt:message bundle="${main}" key="label.link.signup" var="signupMessage"/>
<fmt:message bundle="${main}" key="label.link.login" var="loginMessage"/>


<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>

<jsp:include page="languageSwapper.jsp"/>

<c:choose>
    <c:when test="${not empty sessionScope.user}">
        <p>${helloMessage} <strong>${sessionScope.user.login}</strong>!</p>
        <p>
            <a href="${pageContext.request.contextPath}/controller?command=show_medicament">${medicamentMessage}</a>
        </p>
        <p>
            <a href="${pageContext.request.contextPath}/controller?command=logout">${logoutMessage}</a>
        </p>
    </c:when>
    <c:otherwise>
        <p>
            <a href="${pageContext.request.contextPath}/controller?command=show_signup">${signupMessage}</a>
        </p>
        <p>
            <a href="${pageContext.request.contextPath}/controller?command=show_login">${loginMessage}</a>
        </p>
    </c:otherwise>
</c:choose>

</body>
</html>
