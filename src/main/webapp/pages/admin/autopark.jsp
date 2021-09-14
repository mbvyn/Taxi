<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
    <head>
        <title>Autopark</title>
    </head>
    <body>
    <a href="<c:url value="/index.jsp"/>">Back</a>
        <table>
            <tr>
                <th>Number of seats</th>
                <th>Category</th>
                <th>Description</th>
                <th>Status</th>
            </tr>

            <c:forEach var="car" items="${cars}">
                <tr>
                    <td>${car.getNumberOfSeats()}</td>
                    <td>${car.getCategory()}</td>
                    <td>${car.getDescription()}</td>
                    <td>
                    <form action="<%= request.getContextPath() %>/controller" method="post">
                        <input type="hidden" name="command" value="changeCarStatus"/>
                        <select id="status" name="status">
                            <option disabled selected>${car.getStatus()}</option>
                            <option value="to order">To order</option>
                            <option value="in run">In run</option>
                            <option value="inactive">Inactive</option>
                        </select>
                        <button type="submit" name="carId" value="${car.getId()}">Change status</button>
                    </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
