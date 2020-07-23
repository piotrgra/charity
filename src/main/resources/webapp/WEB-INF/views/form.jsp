<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <script type="text/javascript">
        document.addEventListener("DOMContentLoaded", function () {


            let summaryButton = document.querySelector("#summary");

            summaryButton.addEventListener("click", function () {
                let category = document.querySelectorAll("input[name='categories']:checked");
                let bags = document.querySelector("#bags").value;
                let institutions2 = document.querySelector("#ins");
                let selectedInstitution = institutions2.options[institutions2.selectedIndex].innerText;
                let street = document.querySelector("#street").value;
                let city = document.querySelector("#city").value;
                let zipCode = document.querySelector("#zipCode").value;
                let phone = document.querySelector("#phone").value;
                let pickUpDate = document.querySelector("#pickUpDate").value;
                let pickUpTime = document.querySelector("#pickUpTime").value;
                let pickUpComment = document.querySelector("#pickUpComment").value;

                let stuffPicked = [];

                category.forEach((checkbox) => {
                    stuffPicked.push(checkbox.nextElementSibling.nextElementSibling.innerText);
                })

                // Edycja kroku podsumowania
                document.querySelector("#stuff").innerText = bags + " worki z " + stuffPicked.toString();
                document.querySelector("#summary-ins").innerText = selectedInstitution;
                document.querySelector("#summary-street").innerText = street;
                document.querySelector("#summary-city").innerText = city;
                document.querySelector("#summary-zipCode").innerText = zipCode;
                document.querySelector("#summary-phone").innerText = phone;
                document.querySelector("#summary-date").innerText = pickUpDate;
                document.querySelector("#summary-time").innerText = pickUpTime;
                document.querySelector("#summary-comment").innerText = pickUpComment;

                let confirm = document.querySelector("#confirm");

                if (stuffPicked.length < 1 || bags < 1 || street === "" || city === "" || zipCode === "" || phone === "" || pickUpDate === "" || pickUpTime === "") {
                    confirm.disabled = true;
                    document.querySelector("#error").innerText = "Nie podales wszystkich wymaganych danych";
                } else {
                    document.querySelector("#error").innerText = "";
                    confirm.disabled = false;
                }

            });
        });

    </script>
</head>
<body>
<header class="header--main-page">
    <jsp:include page="includes/header.jsp"/>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Oddaj rzeczy, których już nie chcesz<br/>
                <span class="uppercase">potrzebującym</span>
            </h1>

            <div class="slogan--steps">
                <div class="slogan--steps-title">Wystarczą 4 proste kroki:</div>
                <ul class="slogan--steps-boxes">
                    <li>
                        <div><em>1</em><span>Wybierz rzeczy</span></div>
                    </li>
                    <li>
                        <div><em>2</em><span>Spakuj je w worki</span></div>
                    </li>
                    <li>
                        <div><em>3</em><span>Wybierz fundację</span></div>
                    </li>
                    <li>
                        <div><em>4</em><span>Zamów kuriera</span></div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>
<section class="form--steps">
    <div class="form--steps-instructions">
        <div class="form--steps-container">
            <h3>Ważne!</h3>
            <p data-step="1" class="active">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="2">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="3">
                Wybierz jedną, do
                której trafi Twoja przesyłka.
            </p>
            <p data-step="4">Podaj adres oraz termin odbioru rzeczy.</p>
        </div>
    </div>

    <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>1</span>/4</div>

        <form:form method="post" modelAttribute="donation" action="/form">
            <!-- STEP 1: class .active is switching steps -->
            <div data-step="1" class="active">
                <h3>Zaznacz co chcesz oddać:</h3>
                <h1>
                    <c:forEach items="${allCategories}" var="c">
                        <label>
                            <form:checkbox path="categories" id="${c}" value="${c}"/>

                            <span class="description"> ${c.name} </span>

                        </label>
                        <br/>
                    </c:forEach>
                </h1>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 2 -->
            <div data-step="2">
                <h3>Podaj liczbę 60l worków, w które spakowałeś/aś rzeczy:</h3>

                <div class="form-group form-group--inline">

                    <form:label path="quantity">Liczba 60l worków:</form:label>
                    <form:input path="quantity" id="bags"/>

                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>


            <!-- STEP 4 -->
            <div data-step="3">
                <h3>Wybierz organizacje, której chcesz pomóc:</h3>
                <h1>
                    <form:select path="institution" id="ins">
                        <c:forEach items="${allInstitutions}" var="i">
                            <form:option value="${i}" label="${i.name}" id="${i}"/>
                        </c:forEach>
                    </form:select>
                </h1>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 5 -->
            <div data-step="4">
                <h3>Podaj adres oraz termin odbioru rzecz przez kuriera:</h3>

                <div class="form-section form-section--columns" id="step5">
                    <div class="form-section--column">

                        <h4>Adres odbioru</h4>
                        <div class="form-group form-group--inline">
                            <form:label path="street">Ulica:</form:label>
                            <form:input path="street" id="street"/>
                        </div>

                        <div class="form-group form-group--inline">
                            <form:label path="city">Miasto:</form:label>
                            <form:input path="city" id="city"/>
                        </div>

                        <div class="form-group form-group--inline">
                            <form:label path="zipCode">Kod pocztowy</form:label>
                            <form:input path="zipCode" id="zipCode"/>
                        </div>

                        <div class="form-group form-group--inline">
                            <form:label path="phone">Telefon:</form:label>
                            <form:input path="phone" id="phone"/>
                        </div>
                    </div>

                    <div class="form-section--column">
                        <h4>Termin odbioru</h4>
                        <div class="form-group form-group--inline">
                            <form:label path="pickUpDate">Data odbioru:</form:label>
                            <form:input type="date" path="pickUpDate" id="pickUpDate"/>
                        </div>

                        <div class="form-group form-group--inline">
                            <form:label path="pickUpTime">Godzina odbioru:</form:label>
                            <form:input type="time" path="pickUpTime" id="pickUpTime"/>
                        </div>

                        <div class="form-group form-group--inline">
                            <form:label path="pickUpComment">Uwagi dla kuriera:</form:label>
                            <form:textarea path="pickUpComment" id="pickUpComment"/>

                        </div>
                    </div>
                </div>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step" id="summary">Dalej</button>
                </div>
            </div>

            <!-- STEP 6 -->
            <div data-step="5">
                <h3>Podsumowanie Twojej darowizny</h3>

                <div class="summary">
                    <div class="form-section">
                        <h4>Oddajesz:</h4>
                        <ul>
                            <li>
                                <span class="icon icon-bag"></span>
                                <span class="summary--text" id="stuff"></span>
                            </li>


                            <li>
                                <span class="icon icon-hand"></span>
                                <span class="summary--text" id="summary-ins"></span>
                            </li>

                        </ul>
                    </div>

                    <div class="form-section form-section--columns">
                        <div class="form-section--column">
                            <h4>Adres odbioru:</h4>
                            <ul>
                                <li id="summary-street"></li>
                                <li id="summary-city"></li>
                                <li id="summary-zipCode"></li>
                                <li id="summary-phone"></li>
                            </ul>
                        </div>

                        <div class="form-section--column">
                            <h4>Termin odbioru:</h4>
                            <ul>
                                <li id="summary-date"></li>
                                <li id="summary-time"></li>
                                <li id="summary-comment"></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <h1 id="error" style="color: red"></h1>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="submit" class="btn" id="confirm">Potwierdzam</button>
                </div>
            </div>
        </form:form>
    </div>
</section>

<jsp:include page="includes/footer.jsp"/>
</body>
</html>
