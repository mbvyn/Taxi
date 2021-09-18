<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages"/>
<html>
    <head>
        <title>Autopark</title>
    </head>
    <body>
    <a href="<c:url value="/index.jsp"/>"><fmt:message key='menu.back'/></a>
        <table>
            <tr>
                <th><fmt:message key='car.numberofseats'/></th>
                <th><fmt:message key='car.category'/></th>
                <th><fmt:message key='car.description'/></th>
                <th><fmt:message key='car.status'/></th>
            </tr>

            <c:forEach var="car" items="${entities}">
                <tr>
                    <td>${car.getNumberOfSeats()}</td>
                    <td><fmt:message key='${car.getCategory()}'/></td>
                    <td>${car.getDescription()}</td>
                    <td>
                    <form action="<%= request.getContextPath() %>/controller" method="post">
                        <input type="hidden" name="command" value="changeCarStatus"/>
                        <select id="status" name="status">
                            <option disabled selected><fmt:message key='${car.getStatus()}'/></option>
                            <option value="to_order"><fmt:message key='to_order'/></option>
                            <option value="in_run"><fmt:message key='in_run'/></option>
                            <option value="inactive"><fmt:message key='inactive'/></option>
                        </select>
                        <button type="submit" name="carId" value="${car.getId()}"><fmt:message key='admin.changestatus'/></button>
                    </form>
                    </td>
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
                                        <input type="hidden" name="command" value="getCarsList"/>
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
