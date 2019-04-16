<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Додати курсову роботу</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
<div>
    <a href="/">На головну</a>
</div>
<div>
    <h3>Додати курсову роботу</h3>
    <form:form method="POST"
               action="/coursework/submit" modelAttribute="coursework">
        <table>
            <form:hidden path="year" value="2019"/>
            <tr>
                <td><form:label path="id">Код</form:label></td>
                <td><form:input path="id"/></td>
            </tr>
            <tr>
                <td><form:label path="name">Назва</form:label></td>
                <td><form:input path="name"/></td>
            </tr>
            <tr>
                <td><form:label path="description">Опис роботи</form:label></td>
                <td><form:textarea path="description"/></td>
            </tr>
            <tr>
                <td><form:label path="teacher.id">Викладач:</form:label></td>
                <td>
                    <form:select path="teacher.id">
                        <form:options items="${teachers}" itemLabel="user.fullName" itemValue="id"/>
                    </form:select>
                </td>
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
                <td><input type="submit" value="Submit"/></td>
            </tr>
        </table>
    </form:form>
</div>


</body>
</html>
