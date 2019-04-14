<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello!</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
<form:form method="POST"
           action="/coursework/submit" modelAttribute="coursework">
    <table>
        <form:hidden path="year" value="2019"/>
        <tr>
            <td><form:label path="name">Назва</form:label></td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td><form:label path="description">Опис роботи</form:label></td>
            <td><form:textarea path="description"/></td>
        </tr>
        <tr>
            <td><form:label path="teacherEmail">Пошта викладача</form:label></td>
            <td><form:input path="teacherEmail"/></td>
        </tr>
        <tr>
            <td><form:label path="studentEmail">Пошта студента</form:label></td>
            <td><form:input path="studentEmail"/></td>
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
        <c:if test="${foundCWs ne null}">
            <tr>
                <td>
                    <ul>
                    <c:forEach items="${foundCWs}" var="cw">
                        <div>
                            <p>Назва: ${cw.name}</p>
                            <p>Опис: ${cw.description}</p>
                            <p>Рік: ${cw.year}</p>
                            <p>Викладач: ${cw.teacherEmail}</p>
                            <p>Студент: ${cw.studentEmail}</p>
                            <p>Факультет: ${cw.faculty}</p>
                            ---
                        </div>
                    </c:forEach>
                    </ul>
                </td>
            </tr>
        </c:if>
    </table>
</form:form>


</body>
</html>
