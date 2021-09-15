<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="<c:url value="/index.jsp"/>"><fmt:message key='user.cancel'/></a>
<form action="<%= request.getContextPath() %>/controller" method="get">
    <input type="hidden" name="command" value="analogOrder"/>
    <button name = "orderOption" value = "anotherCategory"><fmt:message key='user.carwithanothercategory'/></button>
    <button name = "orderOption" value = "sameCategory"><fmt:message key='user.acoupleofcars'/></button>
</form>
</body>
</html>
