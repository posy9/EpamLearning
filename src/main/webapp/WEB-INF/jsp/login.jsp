<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="l10n.page.login" var="login"/>
<fmt:message bundle="${login}" key="label.title" var="pageTitle"/>
<fmt:message bundle="${login}" key="label.link.main" var="mainPageMessage"/>
<fmt:message bundle="${login}" key="label.login" var="loginMessage"/>
<fmt:message bundle="${login}" key="label.password" var="passwordMessage"/>
<fmt:message bundle="${login}" key="label.errorMessage" var="errorMessage"/>
<fmt:message bundle="${login}" key="label.submit.login" var="loginSubmitMessage"/>
<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>

<jsp:include page="languageSwapper.jsp"/>

<a href="${pageContext.request.contextPath}/controller?command=show_main">${mainPageMessage}</a>

<h2>${pageTitle}</h2>

<form method="POST" action="${pageContext.request.contextPath}/controller?command=login">
    <p>
        <label for="login">${loginMessage}</label><br/>
        <input type="text" id="login" name="login"/>
    </p>

    <p>
        <label for="password">${passwordMessage}</label><br/>
        <input type="password" id="password" name="password"/>
    </p>

    <c:if test="${not empty requestScope.errorLoginPassMessage}">
        <p style="color: red;">${errorMessage}</p>
    </c:if>

    <input type="submit" value="${loginSubmitMessage}"/>
</form>

<hr/>

</body>
</html>

