<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<%@include file="fragments/headerlog.jsp"%>

<section class="login-page">
    <h2>Załóż konto</h2>

    ${param.success != null ? "<div class='alert alert-success'> Zostałeś poprawnie zarejestrowany. </div>" : ""}
    ${empty message ? "" : message}

    <form:form method="post" modelAttribute="user">
        <div class="form-group">
            <form:input type="text" path="name" class="form-control" placeholder="Imię "/>
            <form:errors path="name" cssClass="error" />
        </div>
        <div class="form-group">
            <form:input type="text" path="lastname" class="form-control" placeholder="Nazwisko "/>
            <form:errors path="lastname" cssClass="error" />
        </div>
        <div class="form-group">
            <form:input path="username" class="form-control"  placeholder="Email"  autocomplete="off" />
            <form:errors path="username" cssClass="error" />
        </div>
        <c:if test="${param.error}">
            <div class="alert alert-danger">
                Hasła nuszą być jednakowe.
            </div>
        </c:if>
        <div class="form-group">
            <form:input type="password" path="password" class="form-control" placeholder="Hasło"  autocomplete="off" />
            <form:errors path="password" cssClass="error" />
        </div>
        <div class="form-group">
            <form:input type="password" path="matchingPassword" class="form-control" placeholder="Powtórz hasło "/>
            <form:errors path="matchingPassword" cssClass="error" />
        </div>

        <form:hidden path="roles"/>

        <div class="form-group form-group--buttons">
            <div class="form-group form-group--buttons">
                <a href="/login" class="btn btn--without-border">Zaloguj się</a>
                <button class="btn" type="submit">Załóż konto</button>
            </div>
        </div>
    </form:form>

</section>

<%@include file="fragments/footer.jsp"%>