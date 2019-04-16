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
    <c:forEach items="${userList}" var="user">
        <p>
            <a href="/user?email=${user.email}">${user.fullName}</a> |
                ${user.email} | ${user.role}
        </p>
    </c:forEach>
</div>
</body>
</html>
