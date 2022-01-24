<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<%@include file="../fragments/header.jsp"%>

<section class="login-page">
    <h2>Dodaj fundację: </h2>
    <div class="container-fluid">
        <div class="card shadow mb-4">
            <div class="card-body">
                <form:form method="post" modelAttribute="institution">
                    <form:hidden path="id"/> <br />

                    <div class="form-group">
                        <form:input path="name" class="form-control" placeholder="Nazwa"/>
                        <form:errors path="name" cssClass="error" />
                            ${empty message ? "" : message}
                    </div>

                    <div class="form-group">
                        <form:textarea path="description" class="form-control" placeholder="Opis " rows="5"/>
                        <form:errors path="description" cssClass="error" />
                    </div>

                    <div class="form-group form-group--buttons">
                        <input type="submit" value="Zapisz" class="btn btn-primary"/>
                    </div>
                </form:form>

            </div>
        </div>
    </div>

</section>

<%@include file="../fragments/footer.jsp"%>