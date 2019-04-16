<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Список користувачів</title>
</head>
<body>
<div>
    <a href="/">На головну</a>
</div>
<div>
    <h3>Список викладачів</h3>
    <c:forEach items="${teacherList}" var="teacher">
        <p>
            <a href="/teacher?teacherId=${teacher.id}">${teacher.user.fullName}</a> |
                ${teacher.user.email} | ${teacher.academicRole.name} | ${teacher.degree.name}
        </p>
    </c:forEach>
</div>
</body>
</html>
