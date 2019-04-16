<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Головна сторінка</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
<h1>Головна сторінка</h1>
<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
    </c:if>
</div>
<h2>Користувачі</h2>
<div>
    <a href="/user/all">Список користувачів</a>
</div>
<div>
    <a href="/user/registration">Додати користувача</a>
</div>

<h2>Студенти</h2>
<div>
    <a href="/users/registration">Реєстрація студентів</a>
</div>

<h2>Курсові роботи</h2>
<div>
    <a href="/coursework/free">Незайняті курсові роботи</a>
</div>
<div>
    <a href="/coursework/new">Додати курсову роботу</a>
</div>
</body>
</html>
