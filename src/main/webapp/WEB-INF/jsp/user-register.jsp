<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User registration</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
<div>
    <a href="/">Додому</a>
</div>
<form:form method="POST" action="/user/register" modelAttribute="user">
    <table>
        <tr>
            <td><form:label path="email">Електронна адреса</form:label></td>
            <td><form:input path="email"/></td>
        </tr>
        <tr>
            <td><form:label path="password">Пароль</form:label></td>
            <td><form:password path="password"/></td>
        </tr>
        <tr>
            <td><form:label path="drfo">ІПН/ДРФО</form:label></td>
            <td><form:input path="drfo"/></td>
        </tr>
        <tr>
            <td><form:label path="familyName">Прізвище</form:label></td>
            <td><form:input path="familyName"/></td>
        </tr>
        <tr>
            <td><form:label path="givenName">Ім'я</form:label></td>
            <td><form:input path="givenName"/></td>
        </tr>

        <tr>
            <td><form:label path="fathersName">По-батькові</form:label></td>
            <td><form:input path="fathersName"/></td>
        </tr>
        <tr>
            <td><form:label path="role">Роль</form:label></td>
            <td>
                <form:select path="role">
                    <form:options itemLabel="name"/>
                </form:select>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="Зареєструвати"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>
