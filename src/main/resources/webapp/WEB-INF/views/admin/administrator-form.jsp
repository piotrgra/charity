<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">

<jsp:include page="includes/header.jsp"/>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">
    <jsp:include page="includes/navbars.jsp"/>

    <div class="container-fluid">
        <div class="card shadow mb-4">
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h5 class="m-0 font-weight-bold text-primary">Edytuj administratora</h5>
            </div>
            <div class="card-body">
                <div class="col-lg-12 text-left">
                    <form:form method="post" modelAttribute="admin" action="/admin/administrators/add">
                        <div class="mb-3">
                            <form:label path="username">Nazwa:</form:label>
                            <form:input path="username"/>
                            <form:errors path="username" class="text-danger"/><br/>
                        </div>
                        <div class="mb-3">
                            <form:label path="firstName">Imie:</form:label>
                            <form:input path="firstName"/>
                            <form:errors path="firstName" class="text-danger"/><br/>
                        </div>
                        <div class="mb-3">
                            <form:label path="lastName">Nazwisko:</form:label>
                            <form:input path="lastName"/>
                            <form:errors path="lastName" class="text-danger"/><br/>
                        </div>
                        <div class="mb-3">
                            <form:label path="email">Email:</form:label>
                            <form:input path="email"/>
                            <form:errors path="email" class="text-danger"/><br/>
                        </div>
                        
                        <form:hidden path="id"/>

                        <form:button class="btn btn-primary btn-lg"
                                     type="submit">Dodaj</form:button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- End of Main Content -->

<jsp:include page="includes/footer.jsp"/>

<jsp:include page="includes/js.jsp"/>
</body>

</html>
