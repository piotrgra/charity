<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">

<jsp:include page="includes/header.jsp"/>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">
    <jsp:include page="includes/navbars.jsp"/>

    <div class="container-fluid">
        <div class="card shadow mb-4">
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h5 class="m-0 font-weight-bold text-primary">Lista: </h5>
            </div>
            <div class="card-body">
                <div class="col-lg-12 text-center">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">Nazwa uzytkownika</th>
                            <th scope="col">Imie</th>
                            <th scope="col">Nazwisko</th>
                            <th scope="col">Email</th>
                            <th scope="col">Rola</th>
                            <th scope="col">Aktywny?</th>
                            <th scope="col">Edytuj</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${allUsers}" var="a" varStatus="loop">
                            <tr>
                                <td>${a.username}</td>
                                <td>${a.firstName}</td>
                                <td>${a.lastName}</td>
                                <td>${a.email}</td>
                                <td><c:forEach items="${a.roles}" var="r">
                                    ${r.name}
                                </c:forEach>

                                </td>
                                <td>${a.enabled}
                                    <a href="<c:url value="users/enable/${a.id}"/>"
                                       class="btn btn-danger btn-circle btn-sm">
                                        <i class="fas fa-trash"></i>
                                    </a></td>
                                <td>
                                    <a href="<c:url value="users/update/${a.id}"/>"
                                       class="btn btn-warning btn-circle btn-sm">
                                        <i class="fas fa-exclamation-triangle"></i>
                                    </a>
                                    <a href="<c:url value="users/delete/${a.id}"/>"
                                       class="btn btn-danger btn-circle btn-sm">
                                        <i class="fas fa-trash"></i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>

                    </table>
                </div>
            </div>
        </div>

    </div>
    <!-- /.row -->

</div>
<!-- End of Page Wrapper -->

<jsp:include page="includes/footer.jsp"/>


<jsp:include page="includes/js.jsp"/>
</body>

</html>
