<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.error" var="error"/>
<fmt:message bundle="${error}" key="label.title" var="pageTitle"/>
<fmt:message bundle="${error}" key="label.link.main" var="mainPageMessage"/>
<fmt:message bundle="${error}" key="label.information" var="informationMessage"/>
<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>
<p>${informationMessage}</p>
<a href="${pageContext.request.contextPath}/controller?command=show_main">${mainPageMessage}</a>
</body>
</html>
