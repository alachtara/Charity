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
            <li><a href="<c:url value="/login" />" class="btn btn--small btn--without-border">Zaloguj</a></li>
            <li><a href="<c:url value="/registration" />" class="btn btn--small btn--highlighted">Załóż konto</a></li>
        </ul>

        <ul>
            <li><a href="home.jsp" class="btn btn--without-border active">Start</a></li>
            <li><a href="home.jsp#steps" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="home.jsp#about-us" class="btn btn--without-border">O nas</a></li>
            <li><a href="home.jsp#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="home.jsp#contact" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>
</header>