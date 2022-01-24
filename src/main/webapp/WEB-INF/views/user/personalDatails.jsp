<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<%@include file="../fragments/headerLogged.jsp"%>

<section class="login-page">

    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <a href="<c:url value="/user/form"/>" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
            <i class="fas fa-download fa-sm text-white-50"></i> Powrót </a>
    </div>

    <div class="card-header py-3">
        <h2 class="m-0 font-weight-bold text-primary"> Dane profilowe: </h2>
    </div>

    <c:if test="${param.success}">
        <div class="alert">
           <h2>Hasło zostało zmienione. </h2>
        </div>
    </c:if>

    <div class="container-fluid">
        <div class="card shadow mb-4">
            <div class="schedules-content">
                <table class="table border-bottom">
                    <thead>
                    <tr class="d-flex">
                        <th class="col-2 center">Email</th>
                        <th class="col-2 center">Imię</th>
                        <th class="col-2 center">Nazwisko</th>


                    </tr>
                    </thead>
                    <tbody class="text-color-lighter">
                        <tr class="d-flex">
                            <td class="col-2 ">${user.email}</td>
                            <td class="col-2">${user.name}</td>
                            <td class="col-2">${user.lastname}</td>

                            <td class="col-2 d-flex align-items-center justify-content-center flex-wrap">
                                <a href="<c:url value="/user/changePassword"/>" class="btn btn-danger rounded-0 text-light m-1">Zmień hasło </a>
                                <a href="<c:url value="/user/edit"/>" class="btn btn-info rounded-0 text-light m-1">Edytuj dane </a>

                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>

<%@include file="../fragments/footer.jsp"%>