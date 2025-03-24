<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="l10n.page.medicament" var="medicament"/>
<fmt:message bundle="${medicament}" key="label.title" var="pageTitle"/>
<fmt:message bundle="${medicament}" key="label.link.main" var="mainPageMessage"/>
<fmt:message bundle="${medicament}" key="label.category" var="categoryMessage"/>
<fmt:message bundle="${medicament}" key="label.country" var="countryMessage"/>
<fmt:message bundle="${medicament}" key="label.information" var="informationMessage"/>
<fmt:message bundle="${medicament}" key="label.amount" var="amountMessage"/>
<fmt:message bundle="${medicament}" key="label.page" var="pageMessage"/>
<fmt:message bundle="${medicament}" key="label.link.previous" var="previousPageMessage"/>
<fmt:message bundle="${medicament}" key="label.link.next" var="nextPageMessage"/>

<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>

<jsp:include page="languageSwapper.jsp"/>

<a href="${pageContext.request.contextPath}/controller?command=show_main">${mainPageMessage}</a>

<c:forEach var="medicament" items="${requestScope.medicamentToShow}">
    <p>
        <strong>${medicament.name}</strong> - ${medicament.price}$
        <br>${categoryMessage} ${medicament.category}
        <br>${countryMessage} ${medicament.country}
        <br>${informationMessage} ${medicament.information}
        <br>${amountMessage} ${medicament.amount}
    </p>
    <hr>
</c:forEach>

<c:if test="${sessionScope.currentPage != 1}">
    <a href="${pageContext.request.contextPath}/controller?command=show_medicament&page=1">1</a>
    <a href="${pageContext.request.contextPath}/controller?command=show_medicament&page=${sessionScope.currentPage - 1}">${previousPageMessage}</a>
</c:if>

<strong>${pageMessage} ${sessionScope.currentPage}</strong>

<c:if test="${sessionScope.currentPage lt sessionScope.numberOfPages}">
    <a href="${pageContext.request.contextPath}/controller?command=show_medicament&page=${sessionScope.currentPage + 1}">${nextPageMessage}</a>
</c:if>

</body>
</html>
