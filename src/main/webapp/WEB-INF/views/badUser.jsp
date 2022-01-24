<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<%@include file="fragments/headerlog.jsp"%>

<section class="login-page">
    <h2>Błąd rejestracji</h2>

${empty errorMessage ? "" : errorMessage}

</section>

<%@include file="fragments/footer.jsp"%>