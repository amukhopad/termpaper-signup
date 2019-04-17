<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>${student.user.shortNameWithInitials}</title>
</head>
<body>
<div>
    <a href="/">На головну</a>
</div>
<div>
    <a href="/student/all">До списку студентів</a>
</div>
<div>
    <h3>${student.user.fullName}</h3>
    <table>
        <tr>
            <td>Пошта:</td>
            <td>${student.user.email}</td>
        </tr>
        <tr>
            <td>Номер студентського квитка:</td>
            <td>${student.studentId}</td>
        </tr>
        <tr>
            <td>Факультет:</td>
            <td>${student.faculty.name}</td>
        </tr>
        <tr>
            <td>Контактна інформація:</td>
            <td>${student.contactInfo}</td>
        </tr>
    </table>
</div>

</body>
</html>
