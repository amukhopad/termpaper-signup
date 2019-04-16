<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>${teacher.shortNameWithInitials}</title>
</head>
<body>
<div>
    <a href="/coursework/free">Назад</a>
</div>
<div>
    <h3>${teacher.fullName}</h3>
    <table>
        <tr>
            <td>Пошта:</td>
            <td>${teacher.email}</td>
        </tr>
        <tr>
            <td>Роль:</td>
            <td>${teacher.academicRole}</td>
        </tr>
        <tr>
            <td>Вчене звання:</td>
            <td>${teacher.degree}</td>
        </tr>
        <tr>
            <td>Факультет:</td>
            <td>${teacher.faculty.name}</td>
        </tr>
    </table>
</div>
<div>
    <h3>Курсові роботи</h3>
    <c:choose>
        <c:when test="${foundCWs ne null}">
            <c:forEach items="${foundCWs}" var="cw">
                <p>${cw.teacher.shortNameWithInitials} |
                    <a href="/coursework/${cw.key}">
                            ${cw.name}
                    </a><br>---
                </p>
            </c:forEach>
        </c:when>
        <c:otherwise>
            Немає курсових для даного викладача.
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>
