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
<form:form method="POST"
           action="/users/register" modelAttribute="student">
    <table>
        <tr>
            <td><form:label path="email">Електронна адреса</form:label></td>
            <td><form:input path="email"/></td>
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
            <td><form:label path="faculty">Факультет</form:label></td>
            <td>
                <form:select path="faculty">
                    <form:options itemLabel="name"/>
                </form:select>
            </td>
        </tr>
        <tr>
            <td><form:label path="familyName">Прізвище</form:label></td>
            <td><form:input path="familyName"/></td>
        </tr>
        <tr>
            <td><form:label path="drfo">ІПН/ДРФО</form:label></td>
            <td><form:input path="drfo"/></td>
        </tr>
        <tr>
            <td><form:label path="studentIdNumber">Номер студентського квитка</form:label></td>
            <td><form:input path="studentIdNumber"/></td>
        </tr>
        <tr>
            <td><form:label path="contactInfo">Контактна інформація</form:label></td>
            <td><form:textarea path="contactInfo"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>
