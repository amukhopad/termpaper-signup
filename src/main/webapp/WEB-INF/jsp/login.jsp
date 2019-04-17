<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Увійти</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <form method="POST" action="/login" class="form-signin">
        <h2 class="form-heading">Увійти</h2>

        <div class="form-group ${error ne null ? 'has-error' : ''}">
            <p>${message}</p>
            <input name="username" type="text" class="form-control" placeholder="Електронна пошта"
                   autofocus="true"/>
            <input name="password" type="password" class="form-control" placeholder="Пароль"/>
            <p>${error}</p>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Увійти</button>
            <h4 class="text-center"><a href="/user/register">Створити новий обліковий запис</a></h4>
        </div>
    </form>
</div>
</body>
</html>
