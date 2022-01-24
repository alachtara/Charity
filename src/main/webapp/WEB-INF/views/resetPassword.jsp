<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<%@include file="fragments/headerlog.jsp"%>

<section class="login-page">
    <h2>Zapomniałeś hasła? </h2>

    <form:form action="/passwordReset" method="post" modelAttribute="user">

        <h3>Aby ustawić nowe hasło podaj e-mail. </h3>
        <div class="form-group">
            <form:input type="text" path="username" placeholder="Email" />
        </div>

        <div class="form-group form-group--buttons">
            <a href="<c:url value="/login" />" class="btn btn--without-border">Powrót</a>
            <button class="btn" type="submit">Potwierdź</button>
        </div>

    </form:form>

</section>

<%@include file="fragments/footer.jsp"%>