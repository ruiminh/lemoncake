<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Home
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${true}" scope="request"/>
    </jsp:attribute>

    <jsp:body>
        <p><h3>Thank you for your purchase</h3></p>
        <p><h3>You can pick up cupcakes after 1 hour</h3></p>
        <p><h3>Thank you for your purchase</h3></p>
        <p><h3>your new balace is ${sessionScope.user.balance-sessionScope.basket.totalPrice()}</h3> </p>



    </jsp:body>
</t:genericpage>