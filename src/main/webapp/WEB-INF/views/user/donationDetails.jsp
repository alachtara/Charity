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

    <div class="container-fluid">
        <div class="card shadow mb-4">
            <div class="schedules-content">
                <div class="card-header py-3">
                    <h2 class="m-0 font-weight-bold text-primary"> Szczegóły daru: </h2>
                </div>
                <table class="table border-bottom">
                    <thead>
                    <tr class="d-flex">
                        <th class="col-2 center">Ilość worków </th>
                        <th class="col-2 center">Przekazane dary </th>
                        <th class="col-2 center">Fundacja </th>
                        <th class="col-2 center">Data utworzenia wpisu </th>
                        <th class="col-2 center">Data odbioru </th>
                        <th class="col-2 center">Status odbioru </th>
                    </tr>
                    </thead>
                    <tbody class="text-color-lighter">
                    <tr class="d-flex">
                        <td class="col-2 ">${donation.quantity}</td>
                        <td class="col-2 ">
                            <c:forEach items="${donation.categories}" var="category" >
                                ${category.name},
                            </c:forEach>
                        </td>
                        <td class="col-2">${donation.institution.name}</td>
                        <td class="col-2">${donation.created}</td>
                        <td class="col-2">${donation.pickUpDate}</td>
                        <td class="col-2">
                        <td class="col-2">${donation.pickUpStatus ? "odebrany" : "nieodebrany"}</td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <div class="schedules-content">
                <div class="card-header py-3">
                    <h2 class="m-0 font-weight-bold text-primary"> Adres odbioru: </h2>
                </div>

                <table class="table border-bottom">
                    <tbody class="text-color-lighter">
                        <tr class="d-flex" >
                            <td>${donation.street} </td>
                        </tr>
                        <tr class="d-flex" >
                            <td>${donation.zipCode} ${donation.city} </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="card-header py-3">
        <h3 class="m-0 font-weight-bold text-primary"> Zmień status odbioru: </h3>
    </div>

    <div class="card-body">
        <form method="post" action="/user/changeDonationStatus?donationId=${donation.id}">
            <div class="form-group">
                <button type="submit" class="btn btn-primary">
                        ${!donation.pickUpStatus ? "odebrany" : "nieodebrany"}
                </button>
            </div>
        </form>

    </div>
</section>

<%@include file="../fragments/footer.jsp"%>