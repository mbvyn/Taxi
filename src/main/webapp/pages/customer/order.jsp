<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Create order</title>
</head>
<body>
<a href="<c:url value="/index.jsp"/>"><fmt:message key='user.cancel'/></a>
    <table>
        <form action="<%= request.getContextPath() %>/controller" method="get">
            <input type="hidden" name="command" value="checkOrder"/>
            <tr>
                <th>
                    <label for="departure"><fmt:message key='user.from'/> </label>
                    <select id="departure" name="departure">
                        <option value="Railway_Station"><fmt:message key='Railway_Station'/></option>
                        <option value="Market_Square"><fmt:message key='Market_Square'/></option>
                        <option value="Park_of_Culture"><fmt:message key='Park_of_Culture'/></option>
                        <option value="Lychakiv_Cemetery"><fmt:message key='Lychakiv_Cemetery'/></option>
                    </select>
                </th>
                <th>
                    <label for="category"><fmt:message key='user.carcategory'/></label>
                    <select id="category" name="category">
                        <option value="economic"><fmt:message key='economic'/></option>
                        <option value="comfort"><fmt:message key='comfort'/></option>
                        <option value="business"><fmt:message key='business'/></option>
                        <option value="minivan"><fmt:message key='minivan'/></option>
                    </select>
                </th>
            </tr>
            <tr>
                <th>
                    <label for="arrival"><fmt:message key='user.to'/></label>
                    <select id="arrival" name="arrival">
                        <option value="Railway_Station"><fmt:message key='Railway_Station'/></option>
                        <option value="Market_Square"><fmt:message key='Market_Square'/></option>
                        <option value="Park_of_Culture"><fmt:message key='Park_of_Culture'/></option>
                        <option value="Lychakiv_Cemetery"><fmt:message key='Lychakiv_Cemetery'/></option>
                    </select>
                </th>
                <th>
                    <label for="numberOfSeats"><fmt:message key='car.numberofseats'/></label>
                    <select id="numberOfSeats" name="numberOfSeats">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                    </select>
                </th>
            </tr>
            <tr>
                <th>
                    <button type="submit"><fmt:message key='user.submit'/></button>
                </th>
            </tr>
        </form>
    </table>
</body>
</html>
