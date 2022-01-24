<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<%@include file="../fragments/header.jsp"%>

<section class="login-page">

    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <a href="<c:url value="/admin/institutions/add"/>" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
            <i class="fas fa-download fa-sm text-white-50"></i> Dodaj fundację</a>
    </div>

    <div class="card-header py-3">
        <h2 class="m-0 font-weight-bold text-primary">Lista fundacji: </h2>
    </div>

    <div class="container-fluid">
        <div class="card shadow mb-4">
            <div class="schedules-content">
                <table class="table border-bottom">
                    <thead>
                    <tr class="d-flex">
                        <th class="col-1">ID</th>
                        <th class="col-2">Nazwa</th>
                        <th class="col-2 center">Opis</th>
                    </tr>
                    </thead>
                    <tbody class="text-color-lighter">
                    <c:forEach items="${institutions}" var="i">
                        <tr class="d-flex">
                            <td class="col-1">${i.id}</td>
                            <td class="col-2">${i.name}</td>
                            <td class="col-2">${i.description}</td>

                            <td class="col-2 d-flex align-items-center justify-content-center flex-wrap">
                                <a href="delete?deleteId=${i.id}" class="btn btn-danger rounded-0 text-light m-1">Usuń </a>
                                <a href="edit?editId=${i.id}" class="btn btn-info rounded-0 text-light m-1">Edytuj </a>
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