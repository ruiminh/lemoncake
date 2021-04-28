<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Home
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>

        <div style="margin-top: 5em;" class="container">
            <form name="login" action="${pageContext.request.contextPath}/fc/basket"  method="POST">
                <div class="container">
                    <select class="form-select form-select-sm mb-3"  aria-label="Bund" name="topping" id="topping">
                        <option selected>Vælg topping</option>
                        <c:forEach var="topping" items="${applicationScope.toppingList}">
                            <option value="${topping.toppingId}">${topping.name} (${topping.price}Kr.) </option>
                        </c:forEach>
                    </select>

                    <select class="form-select form-select-sm mb-3"  aria-label="Bund" name="bottom" id="bottom">
                        <option selected>Vælg bund</option>
                        <c:forEach var="bottom" items="${applicationScope.bottomList}">
                            <option value="${bottom.bottomId}">${bottom.name} (${bottom.price}Kr.) </option>
                        </c:forEach>
                    </select>

                    <select class="form-select form-select-sm mb-3" aria-label="antal" name="amount" id="amount">
                        <option selected>Vælg antal</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                </div>
                <button class="btn btn-primary" type="submit">læg i kurv</button>
            </form>
        </div>

    </jsp:body>
</t:genericpage>