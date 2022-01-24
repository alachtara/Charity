<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<%@include file="../fragments/header.jsp"%>

<section class="login-page">

    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <a href="<c:url value="/admin/users/add"/>" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
            <i class="fas fa-download fa-sm text-white-50"></i> Dodaj użytkowanika </a>
    </div>

    <div class="card-header py-3">
        <h2 class="m-0 font-weight-bold text-primary"> Lista użytkowników: </h2>
    </div>

    <div>
        <h1>${not empty errormessage ? errormessage : "" }</h1>
    </div>



    <div class="container-fluid">
        <div class="card shadow mb-4">
            <div class="schedules-content">
                <table class="table border-bottom">
                    <thead>
                    <tr class="d-flex">
                        <th class="col-1">ID</th>
                        <th class="col-2 center">Email</th>
                        <th class="col-2 center">Imię</th>
                        <th class="col-2 center">Nazwisko</th>
                        <th class="col-2 center">Role</th>
                        <th class="col-2 center">Aktywny</th>


                    </tr>
                    </thead>
                    <tbody class="text-color-lighter">
                    <c:forEach items="${users}" var="u">
                        <tr class="d-flex">
                            <td class="col-1">${u.id}</td>
                            <td class="col-2 ">${u.email}</td>
                            <td class="col-2">${u.name}</td>
                            <td class="col-2">${u.lastname}</td>
                            <td class="col-2">
                                <c:forEach items="${u.roles}" var="role">
                                    ${role.name},
                                </c:forEach>
                            </td>
                            <td class="col-2">${u.enabled}</td>


                            <td class="col-2 d-flex align-items-center justify-content-center flex-wrap">
                                <a href="<c:url value="/admin/users/delete?deleteId=${u.id}"/>" class="btn btn-danger rounded-0 text-light m-1">Usuń </a>
                                <a href="<c:url value="/admin/users/edit?editId=${u.id}"/>" class="btn btn-info rounded-0 text-light m-1">Edytuj </a>
                                <c:forEach items="${u.roles}" var="role">
                                    <c:if test="${role.name == 'ROLE_USER' && u.enabled == 'true'}">
                                        <a href="<c:url value="/admin/users/block?blockId=${u.id}"/>" class="btn btn-danger rounded-0 text-light m-1">Zablokuj </a>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${u.enabled == 'false'}">
                                    <a href="<c:url value="/admin/users/unblock?unblockId=${u.id}"/>" class="btn btn-danger rounded-0 text-light m-1">Odblokuj </a>
                                </c:if>

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