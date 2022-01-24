<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<%@include file="../fragments/header.jsp"%>

<section class="login-page">
    <h2>Czy chcesz zablokować ${user.username}? </h2>
    <div class="container-fluid">
        <div class="card shadow mb-4">
            <div class="card-body">
                <form:form method="post" modelAttribute="user">
                    <form:hidden path="id"/> <br />

                    <div class="form-group">
                        <button type="submit" value="yes" name="confirmed" class="btn btn-primary">TAK</button>
                        <button type="submit" value="no" name="confirmed" class="btn btn-primary">NIE</button>
                    </div>
                </form:form>

            </div>
        </div>
    </div>

</section>

<%@include file="../fragments/footer.jsp"%>