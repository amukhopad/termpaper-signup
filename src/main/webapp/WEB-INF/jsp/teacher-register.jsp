<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Teacher registration</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
<div>
    <a href="/">Додому</a>
</div>
<form:form method="POST" action="/teacher/register" modelAttribute="teacher">
    <table>
        <tr>
            <td><form:label path="id">Код викладача:</form:label></td>
            <td><form:input path="id"/></td>
        </tr>
        <tr>
            <td><form:label path="user.email">Користувач:</form:label></td>
            <td>
                <form:select path="user.email">
                    <form:options items="${users}" itemLabel="fullName" itemValue="email"/>
                </form:select>
            </td>
        </tr>
        <tr>
            <td><form:label path="academicRole">Посада:</form:label></td>
            <td>
                <form:select path="academicRole">
                    <form:options itemLabel="name"/>
                </form:select>
            </td>
        </tr>
        <tr>
            <td><form:label path="degree">Вчений ступінь:</form:label></td>
            <td>
                <form:select path="degree">
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
