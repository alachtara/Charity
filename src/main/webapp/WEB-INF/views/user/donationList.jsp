<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<%@include file="../fragments/headerLogged.jsp"%>

<section class="login-page">

    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <a href="<c:url value="/user/form"/>" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
            <i class="fas fa-download fa-sm text-white-50"></i> Przekaż dary </a>
    </div>

    <div class="card-header py-3">
        <h2 class="m-0 font-weight-bold text-primary">
            ${empty donations ? "Twoja lista jest pusta. Złóż dary." : "Lista przekazanych darów:"}
          </h2>
    </div>

    <div class="container-fluid">
        <div class="card shadow mb-4">
            <div class="schedules-content">
                <table class="table border-bottom">
                    <thead>
                    <tr class="d-flex">
                        <th class="col-2 center">Ilość worków</th>
                        <th class="col-2 center">Kategia </th>
                        <th class="col-2 center">Data utworzenia wpisu </th>
                        <th class="col-2 center">Data odebrania </th>
                        <th class="col-2 center">Status </th>

                    </tr>
                    </thead>
                    <tbody class="text-color-lighter">
                    <c:forEach items="${donations}" var="d">
                        <tr class="d-flex">
                            <td class="col-1">${d.quantity}</td>
                            <td class="col-2 ">
                                <c:forEach items="${d.categories}" var="category">
                                    ${category.name},
                                </c:forEach>
                            </td>
                            <td class="col-2">${d.created}</td>
                            <td class="col-2">${d.pickUpDate}</td>
                            <td class="col-2">
                                    ${d.pickUpStatus ? "odebrany" : "nieodebrany"}
                            </td>

                            <td class="col-2 d-flex align-items-center justify-content-center flex-wrap">
                                <a href="<c:url value="/user/donationDetails?id=${d.id}"/>" class="btn btn-danger rounded-0 text-light m-1">Szczegóły </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</section>

<%@include file="../fragments/footer.jsp"%>