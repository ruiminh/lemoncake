<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Basket
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <div>
        <h3>Du har valgt følgende cupcakes:</h3>

        <br/>

        <table class="table table-striped">

            <thead><th>Bund</th><th>Topping</th><th>Antal</th><th>pris</th><th></th></thead>
            <c:forEach var="cupcakeEntry" varStatus="status" items="${sessionScope.basket.cupcakeList}">

                <tr>
                    <td>${cupcakeEntry.bottom.name}</td>
                    <td>${cupcakeEntry.topping.name}</td>
                    <td>${cupcakeEntry.amount}</td>
                    <td>${cupcakeEntry.price}</td>
                    <td><button class="btn btn-outline-danger btn-sm" type="submit" name="delete" value="${status.index}">
                        delete</button>

                    </td>
                <tr/>
            </c:forEach>
        </table>

        <p>Total pris:  ${sessionScope.basket.totalPrice()} Kr</p>

        <br/>
        <br/>

        <div/>

        <div>

            <a class="btn btn-primary" role="button" href="${pageContext.request.contextPath}/fc/index" >Shop more</a>


            <c:if test="${sessionScope.user == null }">
                <a type="button" class="btn btn-sm"
                   href="${pageContext.request.contextPath}/fc/loginpage">betale</a>
            </c:if>

            <c:if test="${sessionScope.user != null }">
                <a type="button" class="btn btn-sm"
                   href="${pageContext.request.contextPath}/fc/checkout">betale</a>
            </c:if>

        </div>
    </jsp:body>

</t:genericpage>

