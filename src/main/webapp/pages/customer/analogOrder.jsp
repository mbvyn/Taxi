<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="<c:url value="/index.jsp"/>">Cancel</a>
<form action="<%= request.getContextPath() %>/controller" method="get">
    <input type="hidden" name="command" value="analogOrder"/>
    <button name = "orderOption" value = "anotherCategory">Car with another category</button>
    <button name = "orderOption" value = "sameCategory">A couple of cars</button>
</form>
</body>
</html>
