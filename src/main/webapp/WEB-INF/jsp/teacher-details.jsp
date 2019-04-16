<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>${teacher.user.shortNameWithInitials}</title>
</head>
<body>
<div>
    <a href="/">На головну</a> |
    <div>
        <a href="/teacher/all">До списку викладачів</a>
    </div>
</div>
<div>
    <h3>${teacher.user.fullName}</h3>
    <table>
        <tr>
            <td>Пошта:</td>
            <td>${teacher.user.email}</td>
        </tr>
        <tr>
            <td>Код викладача:</td>
            <td>${teacher.id}</td>
        </tr>
        <tr>
            <td>Посада:</td>
            <td>${teacher.academicRole.name}</td>
        </tr>
        <tr>
            <td>Вчений ступінь:</td>
            <td>${teacher.degree.name}</td>
        </tr>
    </table>
</div>
<div>
    <h3>Курсові роботи</h3>
    <c:choose>
        <c:when test="${foundCWs ne null}">
            <c:forEach items="${foundCWs}" var="cw">
                <p>${cw.teacher.user.shortNameWithInitials} |
                    <a href="/coursework/${cw.id}">
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
