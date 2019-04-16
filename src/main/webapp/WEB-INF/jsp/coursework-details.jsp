<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${cw.name} | ${cw.teacher.user.shortNameWithInitials}</title>
</head>
<body>
<div>
    <a href="/">На головну</a> | <a href="/coursework/free">Назад</a>
</div>
<div>
    <h3>Деталі курсової</h3>
    <table>
        <tr>
            <td>Тема:</td>
            <td>${cw.name}</td>
        </tr>
        <tr>
            <td>Опис:</td>
            <td>${cw.description}</td>
        </tr>
        <tr>
            <td>Викладач:</td>
            <td>
                <a href="/coursework/teacher/${cw.teacher.user.email}">
                    ${cw.teacher.user.shortNameWithInitials}
                </a>
            </td>
        </tr>
        <tr>
            <td>Факультет:</td>
            <td>${cw.faculty.name}</td>
        </tr>
    </table>
</div>

</body>
</html>
