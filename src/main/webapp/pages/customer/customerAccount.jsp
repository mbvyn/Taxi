<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="<c:url value="/index.jsp"/>"><fmt:message key='menu.back'/></a>
<table>
    <tr>
        <th><button><fmt:message key='menu.departure'/></button></th>
        <th><button><fmt:message key='menu.arrival'/></button></th>
        <th>
            <form  action=<%= request.getContextPath() %>/controller method="get">
                <input type="hidden" name="command" value="getOrdersList"/>
                <button name="sort" value="date">
                    <fmt:message key='menu.date'/>
                </button>
            </form>
        </th>
        <th>
            <form  action=<%= request.getContextPath() %>/controller method="get">
                <input type="hidden" name="command" value="getOrdersList"/>
                <button name="sort" value="price">
                    <fmt:message key='menu.price'/>
                </button>
            </form>
        </th>
        <th><button><fmt:message key='menu.numberofpassengers'/></button></th>
        <th><button><fmt:message key='menu.cars'/></button></th>
    </tr>

    <c:forEach var="order" items="${orders}">
        <tr>
            <td><fmt:message key='${order.getDeparture()}'/></td>
            <td><fmt:message key='${order.getArrival()}'/></td>
            <td>${order.getOrderingDate()}</td>
            <td>${order.getPrice()}</td>
            <td>${order.getNumberOfPassengers()}</td>
            <td>
                <c:forEach var="car" items="${order.getCarIdList()}">
            <td>
                <form  action=<%= request.getContextPath() %>/controller method="get">
                    <input type="hidden" name="command" value="getCarInfo"/>
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
