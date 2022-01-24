<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@include file="../fragments/headerLogged.jsp"%>

<section class="login-page">
    <h2>Zmiana hasła </h2>

    <h1>${message}</h1>

    <form:form method="post" modelAttribute="newPassword">
        <c:if test="${param.error}">
            <div class="alert alert-danger">
                Hasła nuszą być jednakowe.
            </div>
        </c:if>
        <div class="form-group">
            <form:input type="password" path="password" class="form-control" placeholder="Nowe hasło "/>
            <form:errors path="password" cssClass="error" />
        </div>
        <div class="form-group">
            <form:input type="password" path="confirmPassword" class="form-control" placeholder="Potwierdź hasło "/>
            <form:errors path="confirmPassword" cssClass="error" />
        </div>

        <div class="form-group form-group--buttons">
            <div class="form-group form-group--buttons">
                <button class="btn" type="submit">Zapisz </button>
            </div>
        </div>
    </form:form>

</section>

<%@include file="../fragments/footer.jsp"%>