<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Customer page
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <h1>Welcome back! ${sessionScope.email} </h1>
        You are now logged in as a Customer of our wonderful site.
        You can <a href="${pageContext.request.contextPath}/fc/basketpage">finish your order here</a>



        Role: ${sessionScope.role}
    </jsp:body>

</t:genericpage>

