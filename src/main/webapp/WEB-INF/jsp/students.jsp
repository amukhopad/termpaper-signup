<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Список студентів</title>
</head>
<body>
<div>
    <a href="/">На головну</a>
</div>
<div>
    <h3>Список студентів</h3>
    <c:forEach items="${studentList}" var="student">
        <p>
            <a href="/student?studentId=${student.studentId}">${student.user.fullName}</a>
             | ${student.faculty.name} | ${student.studentId}  | ${student.user.email}
        </p>
    </c:forEach>
</div>
</body>
</html>
