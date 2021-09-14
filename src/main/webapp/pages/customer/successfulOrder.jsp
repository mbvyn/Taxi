<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
</head>
<body>
    <p>Order details</p>
    <p>From ${order.getDeparture()}</p>
    <p>To ${order.getArrival()}</p>
    <p>Price ${order.getPrice()}</p>
    <a href="/index.jsp">Back</a>
    <form action="<%= request.getContextPath() %>/controller" method="post">
        <input type="hidden" name="command" value="createOrder"/>
        <button type="submit">Submit</button>
    </form>
</body>
</html>
