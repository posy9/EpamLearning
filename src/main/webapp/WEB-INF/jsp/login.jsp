<%--
  Created by IntelliJ IDEA.
  User: Daniil
  Date: 19.03.2025
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/controller?command=show_main">Go to main page</a>
<h2>Вход</h2>
<form name="loginForm" method="POST" action="${pageContext.request.contextPath}/controller?command=login">
    Login:<br/>
    <input type="text" name="login" value=""/>
    <br/>Password:<br/>
    <input type="password" name="password" value=""/>
    <br/>
    ${requestScope.errorLoginPassMessage}
    <br/>
    <input type="submit" value="Log in"/>
</form><hr/>
</body>
</html>
