<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Car Info</title>
</head>
<body>
<form action= <%= request.getContextPath() %>/controller method="get">
    <button name="command" value="getOrdersList"><fmt:message key='menu.back'/></button>
<table>
    <tr>
        <th><fmt:message key='car.numberofseats'/></th>
        <th><fmt:message key='car.category'/></th>
        <th><fmt:message key='car.description'/></th>
    </tr>
    <tr>
        <td>${car.getNumberOfSeats()}</td>
        <td><fmt:message key='${car.getCategory()}'/></td>
        <td>${car.getDescription()}</td>
    </tr>
</table>
</body>
</html>
