<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create order</title>
</head>
<body>
<a href="<c:url value="/index.jsp"/>">Back</a>
    <table>
        <form action="<%= request.getContextPath() %>/controller" method="get">
            <input type="hidden" name="command" value="checkOrder"/>
            <tr>
                <th>
                    <label for="departure">From: </label>
                    <select id="departure" name="departure">
                        <option value="Railway Station">Railway Station</option>
                        <option value="Market Square">Market Square</option>
                        <option value="Park of Culture">Park of Culture</option>
                        <option value="Lychakiv Cemetery">Lychakiv Cemetery</option>
                    </select>
                </th>
                <th>
                    <label for="category">Car category</label>
                    <select id="category" name="category">
                        <option value="economic">economic</option>
                        <option value="comfort">comfort</option>
                        <option value="business">business</option>
                        <option value="minivan">minivan</option>
                    </select>
                </th>
            </tr>
            <tr>
                <th>
                    <label for="arrival">To</label>
                    <select id="arrival" name="arrival">
                        <option value="Railway Station">Railway Station</option>
                        <option value="Market Square">Market Square</option>
                        <option value="Park of Culture">Park of Culture</option>
                        <option value="Lychakiv Cemetery">Lychakiv Cemetery</option>
                    </select>
                </th>
                <th>
                    <label for="numberOfSeats">number of seats</label>
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
                    <button type="submit">Make order</button>
                </th>
            </tr>
        </form>
    </table>
</body>
</html>
