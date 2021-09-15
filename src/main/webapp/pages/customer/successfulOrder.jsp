<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title>Order</title>
</head>
<body>
    <p><fmt:message key='user.orderdetails'/></p>
    <p><fmt:message key='user.from'/> <fmt:message key='${order.getDeparture()}'/></p>
    <p><fmt:message key='user.to'/> <fmt:message key='${order.getArrival()}'/></p>
    <p><fmt:message key='menu.price'/> ${order.getPrice()}</p>
    <a href="<c:url value="/index.jsp"/>"><fmt:message key='menu.back'/></a>
    <form action="<%= request.getContextPath() %>/controller" method="post">
        <input type="hidden" name="command" value="createOrder"/>
        <button type="submit"><fmt:message key='user.submit'/></button>
    </form>
</body>
</html>
