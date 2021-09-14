<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1> "Hello" </h1>
<c:choose>
  <c:when test="${account.getRole() == true}">
  <form action= <%= request.getContextPath() %>/controller method="get">
    <button name="command" value="getOrdersList">Account</button>
    <button name="command" value="getCarsList">Autopark</button>
    <button name="command" value="logout">Logout</button>
  </form>
  </c:when>
  <c:when test="${account.getRole() == false}">
    <form action = <%= request.getContextPath() %>/pages/customer/order.jsp method="get">
      <button>Make order</button>
    </form>
    <form action= <%= request.getContextPath() %>/controller method="get">
      <button name="command" value="getOrdersList">Account</button>
      <button name="command" value="logout">Logout</button>
    </form>
  </c:when>
  <c:otherwise>
      <a href="pages/entrance/entrance.jsp">Log In</a>
      <a href="pages/entrance/registration.jsp">Sign Up</a>
  </c:otherwise>
</c:choose>
<br/>

</body>
</html>