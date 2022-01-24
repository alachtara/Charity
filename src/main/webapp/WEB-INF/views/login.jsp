<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<%@include file="fragments/headerlog.jsp"%>

<section class="login-page">
    <h2>Zaloguj się</h2>

    ${empty successMessage ? "" : successMessage}

    <form action="/login" method="post">

        <!-- success message -->
        <c:if test="${param.error != null}">
            <div class="alert alert-danger"> Błędne hasło lub nazwa użytkownika.</div>
        </c:if>

        <!-- logout message -->
        <c:if test="${param.logout != null}">
            <div class="alert alert-info"> Zostałeś wylogowany. </div>
        </c:if>

        <div class="form-group">
            <input type="text" id ="username" name="username" placeholder="Email" />
        </div>
        <div class="form-group">
            <input type="password" id ="password"  name="password" placeholder="Hasło" />
            <a href="<c:url value="/resetPassword" />" class="btn btn--small btn--without-border reset-password">Przypomnij hasło</a>
        </div>

        <div class="form-group form-group--buttons">
            <a href="<c:url value="/registration" />" class="btn btn--without-border">Załóż konto</a>
            <button class="btn" type="submit">Zaloguj się</button>
        </div>

    </form>

</section>

<%@include file="fragments/footer.jsp"%>