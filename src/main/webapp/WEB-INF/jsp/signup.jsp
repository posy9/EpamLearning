<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="l10n.page.signup" var="signup"/>
<fmt:message bundle="${signup}" key="label.title" var="pageTitle"/>
<fmt:message bundle="${signup}" key="label.link.main" var="mainPageMessage"/>
<fmt:message bundle="${signup}" key="label.login" var="loginMessage"/>
<fmt:message bundle="${signup}" key="label.password" var="passwordMessage"/>
<fmt:message bundle="${signup}" key="label.userWithName" var="userWithNameMessage"/>
<fmt:message bundle="${signup}" key="label.alreadyExists" var="alreadyExistsMessage"/>
<fmt:message bundle="${signup}" key="label.submit.registration" var="submitRegistrationMessage"/>
<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>

<jsp:include page="languageSwapper.jsp"/>

<a href="${pageContext.request.contextPath}/controller?command=show_main">${mainPageMessage}</a>

<h2>${pageTitle}</h2>

<form method="POST" action="${pageContext.request.contextPath}/controller?command=signup">
    <p>${loginMessage}<br/>
        <label>
            <input type="text" name="login"/>
        </label>
    </p>
    <p>${passwordMessage}<br/>
        <label>
            <input type="password" name="password"/>
        </label>
    </p>

    <c:if test="${not empty requestScope.errorSignupLogin}">
        <p style="color: red;"> ${userWithNameMessage} ${requestScope.errorSignupLogin} ${alreadyExistsMessage}</p>
    </c:if>

    <input type="submit" value="${submitRegistrationMessage}"/>
</form>

<hr/>

</body>
</html>
