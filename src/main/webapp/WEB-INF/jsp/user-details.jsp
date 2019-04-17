<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>${user.shortNameWithInitials}</title>
</head>
<body>
<div>
    <a href="/">На головну</a> | <a href="/user/all">До списку користувачів</a>
</div>
<div>
    <h3>${user.fullName}</h3>
    <table>
        <tr>
            <td>Пошта:</td>
            <td>${user.email}</td>
        </tr>
        <tr>
            <td>Код ДРФО:</td>
            <td>${user.drfo}</td>
        </tr>
        <tr>
            <td>Роль:</td>
            <td>${user.role.name}</td>
        </tr>
    </table>
</div>
</body>
</html>
