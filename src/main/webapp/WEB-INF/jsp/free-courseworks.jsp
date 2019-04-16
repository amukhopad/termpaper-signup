<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Незайняті теми курсових робіт</title>
</head>
<body>
<div>
    <a href="/">Назад</a>
</div>
<div>
    <h3>Незайняті теми курсових робіт</h3>
    <c:forEach items="${freeCWs}" var="cw">
        <p>
            <a href="/coursework/teacher/${cw.teacher.user.email}">
                    ${cw.teacher.user.shortNameWithInitials}
            </a> |
            <a href="/coursework/${cw.id}">
                    ${cw.name}
            </a><br>---
        </p>
    </c:forEach>
</div>
</body>
</html>
