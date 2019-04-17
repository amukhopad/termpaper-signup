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
                <a href="/teacher?teacherId=${cw.teacher.id}">
                    ${cw.teacher.user.shortNameWithInitials}
                </a>
            </td>
        </tr>
        <tr>
            <td>Факультет:</td>
            <td>${cw.faculty.name}</td>
        </tr>
        <c:if test="${cw.student ne null}">
            <tr>
                <td>Записаний студент:</td>
                <td>
                    <a href="/student?studentId=${cw.student.studentId}">
                            ${cw.student.user.shortNameWithInitials}
                    </a>
                </td>
            </tr>
        </c:if>
        <tr>
            <td></td>
            <td>
                <c:choose>
                    <c:when test="${cw.student eq null and user.role eq 'STUDENT'}">
                        <form action="/coursework/${cw.id}/assign">
                            <input type="submit" value="Записатися на дисципліну"/>
                        </form>
                    </c:when>
                    <c:when test="${cw.student.user.email eq pageContext.request.userPrincipal.name}">
                        <form action="/coursework/${cw.id}/unassign">
                            <input type="submit" value="Виписатися з дисципліни"/>
                        </form>
                    </c:when>
                </c:choose>
            </td>
        </tr>
    </table>
</div>

</body>
</html>
