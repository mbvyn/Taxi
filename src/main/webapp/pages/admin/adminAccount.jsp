<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<a href="<c:url value="/index.jsp"/>">Back</a>
<form action=<%= request.getContextPath() %>/controller method="get">
    <input type="hidden" name="command" value="getOrdersList"/>
    <p>
        <label for="userId">User ID: </label>
        <input type="text" id="userId" name="userId" pattern="[0-9]"/>
        <button type="submit">Ok</button>
    </p>
</form>
<form action=<%= request.getContextPath() %>/controller method="get">
    <input type="hidden" name="command" value="getOrdersList"/>
    <p>
        <label for="date">Ordering Date</label>
        <input type="date" id="date" name="date"/>
        <button type="submit">Ok</button>
    </p>
</form>
<form action=<%= request.getContextPath() %>/controller method="get">
    <input type="hidden" name="command" value="getOrdersList"/>
    <button name="refresh" value="refresh">Refresh</button>
</form>
<table>
    <tr>
        <th><button>Account Id</button></th>
        <th><button>Departure</button></th>
        <th><button>Arrival</button></th>
        <th>
            <form  action=<%= request.getContextPath() %>/controller method="get">
                <input type="hidden" name="command" value="getOrdersList"/>
                <button name="sort" value="date">
                    Ordering Date
                </button>
            </form>
        </th>
        <th>
            <form  action=<%= request.getContextPath() %>/controller method="get">
                <input type="hidden" name="command" value="getOrdersList"/>
                <button name="sort" value="price">
                    Price
                </button>
            </form>
        </th>
        <th><button>Number Of Passengers</button></th>
        <th><button>Cars Id</button></th>
    </tr>

    <c:forEach var="order" items="${orders}">
        <tr>
            <td>${order.getAccountId()}</td>
            <td>${order.getDeparture()}</td>
            <td>${order.getArrival()}</td>
            <td>${order.getOrderingDate()}</td>
            <td>${order.getPrice()}</td>
            <td>${order.getNumberOfPassengers()}</td>
            <td>
                <c:forEach var="car" items="${order.getCarIdList()}">
                            <td>
                                 <form  action=<%= request.getContextPath() %>/controller method="get">
                                     <input type="hidden" name="command" value="getCarsList"/>
                                    <button name="carId" value="${car}">
                                            ${car}
                                    </button>
                                 </form>
                            </td>
                </c:forEach>
            </td>>
        </tr>
    </c:forEach>

    <table>
        <tr>
            <c:forEach begin="1" end="${numberOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>
                            <button>
                                    ${i}
                            </button>
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td>
                            <form  action=<%= request.getContextPath() %>/controller method="get">
                                <input type="hidden" name="command" value="getOrdersList"/>
                                <button name="page" value =${i}>
                                        ${i}
                                </button>
                            </form>
                        </td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>

</table>
</body>
</html>
