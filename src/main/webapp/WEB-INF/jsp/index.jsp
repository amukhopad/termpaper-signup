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
<h3>Головна сторінка</h3>
<div class="container">
    <div>
        <c:choose>
            <c:when test="${pageContext.request.userPrincipal.name ne null}">
                <form id="logoutForm" method="POST" action="/logout">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="submit" style="display: none" id="submitInput">
                </form>
                Ласкаво просимо, ${user.givenName} ${user.fathersName} <br>
                ${user.role.name} | <a href="#"><label for="submitInput">Вийти із системи</label></a>
            </c:when>
            <c:otherwise>
                <a href="/login">Увійти</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<c:if test="${user.role eq 'METHODIST'}">
    <h2>Користувачі</h2>
    <div>
        <a href="/user/all">Список користувачів</a>
    </div>
    <div>
        <a href="/user/register">Додати користувача</a>
    </div>
</c:if>

<h3>Студенти</h3>
<div>
    <a href="/student/all">Список студентів</a>
</div>
<c:if test="${user.role eq 'METHODIST' or user.role eq 'STUDENT'}">
    <div>
        <a href="/student/register">Реєстрація студентів</a>
    </div>
</c:if>

<h3>Викладачі</h3>
<div>
    <a href="/teacher/all">Список викладачів</a>
</div>

<c:if test="${user.role eq 'METHODIST' or user.role eq 'TEACHER'}">
    <div>
        <a href="/teacher/register">Реєстрація викладачів</a>
    </div>
</c:if>
<div>

</div>

<h3>Курсові роботи</h3>
<div>
    <a href="/coursework/free">Незайняті курсові роботи</a>
</div>
<c:if test="${user.role eq 'METHODIST' or user.role eq 'TEACHER'}">
    <div>
        <a href="/coursework/new">Додати курсову роботу</a>
    </div>
</c:if>
</body>
</html>
