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
<form:form method="POST" action="/student/register" modelAttribute="student">
    <table>
        <tr>
            <td><form:label path="studentId">Номер студентського квитка:</form:label></td>
            <td><form:input path="studentId"/></td>
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
            <td><form:label path="contactInfo">Контактна інформація</form:label></td>
            <td><form:input path="contactInfo"/></td>
        </tr>
        <tr>
            <td><form:label path="faculty">Факультет</form:label></td>
            <td>
                <form:select path="faculty">
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
