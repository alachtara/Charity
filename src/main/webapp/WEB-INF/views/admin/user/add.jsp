<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<%@include file="../fragments/header.jsp"%>

<section class="login-page">
    <h2>Dodaj użytkownika </h2>

    <c:if test="${param.success != null}">
        <div class="alert alert-success"> Użytkownik został poprawnie zarejestrowany. </div>
    </c:if>

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
            <form:input path="username" class="form-control"  placeholder="Email"/>
            <form:errors path="username" cssClass="error" />
                ${empty message ? "" : message}
        </div>
        <c:if test="${param.error}">
            <div class="alert alert-danger">
                Hasła nuszą być jednakowe.
            </div>
        </c:if>
        <div class="form-group">
            <form:input type="password" path="password" class="form-control" placeholder="Hasło"/>
            <form:errors path="password" cssClass="error" />
        </div>
        <div class="form-group">
            <form:input type="password" path="matchingPassword" class="form-control" placeholder="Powtórz hasło "/>
            <form:errors path="matchingPassword" cssClass="error" />
        </div>

        <c:forEach items="${roleList}" var="role">
            <label>
                    <%--                            <form:checkbox path="roleList" value="${role}" />--%>
                    <%--                            <form:errors path="categories" cssClass="error" />--%>
                <input type="checkbox" name="roles" value="${role.id}" />
                <span class="checkbox"></span>
                <span class="description">${role.name}</span>
            </label>
        </c:forEach>

        <div class="form-group form-group--buttons">
            <div class="form-group form-group--buttons">
                <button class="btn" type="submit">Zapisz </button>
            </div>
        </div>
    </form:form>

</section>

<%@include file="../fragments/footer.jsp"%>