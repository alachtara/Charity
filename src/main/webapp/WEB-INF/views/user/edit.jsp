<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@include file="../fragments/headerLogged.jsp"%>

<section class="login-page">
    <h2>Edytuj dane</h2>

    <h1>${message}</h1>
    <h1>${not empty errormessage ? errormessage : "" }</h1>

    <form:form method="post" modelAttribute="user">
        <form:hidden path="id" />
        <div class="form-group">
            <form:input type="text" path="name" class="form-control" placeholder="Imię "/>
            <form:errors path="name" cssClass="error" />
        </div>
        <div class="form-group">
            <form:input type="text" path="lastname" class="form-control" placeholder="Nazwisko "/>
            <form:errors path="lastname" cssClass="error" />
        </div>

<%--        <sec:authorize access="hasRole('ADMIN')">--%>
        <form:select path="roles" >
            <form:option selected="selected" value="1" />
        </form:select>

<%--        </sec:authorize>--%>

        <div class="form-group form-group--buttons">
            <div class="form-group form-group--buttons">
                <button class="btn" type="submit">Zapisz </button>
            </div>
        </div>
    </form:form>

</section>

<%@include file="../fragments/footer.jsp"%>