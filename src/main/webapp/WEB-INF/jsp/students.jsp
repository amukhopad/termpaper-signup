<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Список користувачів</title>
</head>
<body>
<div>
    <a href="/">Додому</a>
</div>
<div>
    <h3>Список користувачів</h3>
    <c:forEach items="${studentList}" var="student">
        <p>
            <a href="/student?studentId=${student.studentId}">${student.user.fullName}</a> |
                ${student.user.email} | ${student.studentId} | ${student.faculty}
        </p>
    </c:forEach>
</div>
</body>
</html>
