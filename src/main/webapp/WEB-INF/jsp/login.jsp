<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello!</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <form method="POST" action="/login" class="form-signin">
        <h2 class="form-heading">Log in</h2>

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span>${message}</span>
            <input name="username" type="text" class="form-control" placeholder="Username"
                   autofocus="true"/>
            <input name="password" type="password" class="form-control" placeholder="Password"/>
            <span>${error}</span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
            <h4 class="text-center"><a href="/user/registration">Create an account</a></h4>
        </div>
    </form>
</div>


<%--<form:form method="POST" action="/">--%>
    <%--<table>--%>
        <%--<tr>--%>
            <%--<td><form:label path="username">Електронна адреса</form:label></td>--%>
            <%--<td><form:input path="username"/></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td><form:label path="password">Пароль</form:label></td>--%>
            <%--<td><form:password path="password"/></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td><input type="submit" value="Submit"/></td>--%>
        <%--</tr>--%>
    <%--</table>--%>
<%--</form:form>--%>
</body>
</html>
