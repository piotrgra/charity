<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<body>
<header>
    <jsp:include page="includes/header.jsp"/>
</header>

<section class="login-page">
    <h2>Załóż konto</h2>
    <form:form method="post" action="/register" modelAttribute="user">
        <div class="form-group">
            <form:label path="firstName">Imię: </form:label>
            <form:input path="firstName"/>
        </div>
        <div class="form-group">
            <form:label path="lastName">Nazwisko: </form:label>
            <form:input path="lastName"/>
        </div>
        <div class="form-group">
            <form:label path="email">Email: </form:label>
            <form:input path="email"/>
        </div>
        <div class="form-group">
            <form:label path="username">Nazwa uzytkownika: </form:label>
            <form:input path="username"/>
        </div>
        <div class="form-group">
            <form:label path="password">Password: </form:label>
            <form:password path="password"/>
        </div>

        <form:hidden path="id"/>
        <div class="form-group form-group--buttons">
            <a href="<c:url value="/login"/>" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>
    </form:form>
</section>

<jsp:include page="includes/footer.jsp"/>
</body>
</html>
