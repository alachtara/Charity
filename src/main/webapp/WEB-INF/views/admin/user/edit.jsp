<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@include file="../fragments/header.jsp"%>

<section class="login-page">
    <h2>Eduj użytkownika ${user.username}</h2>

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
            <form:select path="roles" items="${roleList}" multiple="true" itemValue="id" itemLabel="name"/>


<%--        <c:forEach items="${roleList}" var="role">--%>
<%--
<%--                <input type="checkbox" name="roles" value="${role.id}" />--%>
<%--                <span class="checkbox"></span>--%>
<%--                <span class="description">${role.name}</span>--%>
<%--            </label>--%>
<%--        </c:forEach>--%>

        <div class="form-group form-group--buttons">
            <div class="form-group form-group--buttons">
                <button class="btn" type="submit">Zapisz </button>
            </div>
        </div>
    </form:form>

</section>

<%@include file="../fragments/footer.jsp"%>