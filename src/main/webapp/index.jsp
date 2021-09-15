<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<form action= <%= request.getContextPath() %>/controller method="get">
  <input type="hidden" name="command" value="changeLanguage"/>
  <button name="locale" value = "en">EN</button>
  <button name="locale" value = "uk">UA</button>
</form>
<c:choose>
  <c:when test="${account.getRole() == true}">
    <p>Admin</p>
  <form action= <%= request.getContextPath() %>/controller method="get">
    <button name="command" value="getOrdersList"><fmt:message key='menu.account'/></button>
    <button name="command" value="getCarsList"><fmt:message key='admin.autopark'/></button>
    <button name="command" value="logout"><fmt:message key='menu.logout'/></button>
  </form>
  </c:when>
  <c:when test="${account.getRole() == false}">

    <p>User</p>
    <form action = <%= request.getContextPath() %>/pages/customer/order.jsp method="get">
      <button><fmt:message key='user.makeorder'/></button>
    </form>
    <form action= <%= request.getContextPath() %>/controller method="get">
      <button name="command" value="getOrdersList"><fmt:message key='menu.account'/></button>
      <button name="command" value="logout"><fmt:message key='menu.logout'/></button>
    </form>
  </c:when>
  <c:otherwise>
      <a href="pages/entrance/entrance.jsp"><fmt:message key='menu.login'/></a>
      <a href="pages/entrance/registration.jsp"><fmt:message key='menu.signup'/></a>
  </c:otherwise>
</c:choose>
<br/>

</body>
</html>