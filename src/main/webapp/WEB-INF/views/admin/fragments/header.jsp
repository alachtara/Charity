<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header>
    <nav class="container container--70">


        <ul class="nav--actions">
            <li>
                <h3>
                    <sec:authorize access="isAuthenticated()">
                        Witaj <sec:authentication property="principal.username"/> !!
                    </sec:authorize>
                </h3>
            </li>
            <li><a href="<c:url value="/logout" />" class="btn btn--small btn--highlighted">Wyloguj się</a></li>
        </ul>

        <ul>
            <li><a href="<c:url value="/admin/institutions/"/>" class="btn btn--without-border">Fundacje</a></li>
            <li><a href="<c:url value="/admin/users/admins/"/>" class="btn btn--without-border">Użytkownicy-Admin</a></li>
            <li><a href="<c:url value="/admin/users/users/"/>" class="btn btn--without-border">Użytkownicy</a></li>
        </ul>
    </nav>
</header>


