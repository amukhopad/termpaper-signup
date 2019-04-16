<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>${user.shortNameWithInitials}</title>
</head>
<body>
<div>
    <a href="/">Додому</a>
</div>
<div>
    <a href="/user/all">До списку користувачів</a>
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
            <td>${user.role}</td>
        </tr>
    </table>
</div>
</body>
</html>
