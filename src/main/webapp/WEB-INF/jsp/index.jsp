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
            <input type="submit" style="display: none" id="submitInput">
        </form>

        <h2>Welcome ${pageContext.request.userPrincipal.name} | <a href="#"><label
                for="submitInput">Logout</label></a></h2>
    </c:if>
    <c:if test="${pageContext.request.userPrincipal.name == null}">
        <div>
            <a href="/login">Login</a>
        </div>
    </c:if>
</div>
<h2>Користувачі</h2>
<div>
    <a href="/user/all">Список користувачів</a>
</div>
<div>
    <a href="/user/register">Додати користувача</a>
</div>

<h2>Студенти</h2>
<div>
    <a href="/student/all">Список студентів</a>
</div>
<div>
    <a href="/student/register">Реєстрація студентів</a>
</div>

<h2>Викладачі</h2>
<div>
    <a href="/teacher/all">Список викладачів</a>
</div>
<div>
    <a href="/teacher/register">Реєстрація викладачів</a>
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
