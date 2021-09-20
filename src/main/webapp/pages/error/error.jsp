<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jspf/settings.jspf"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/jspf/stylesheets.jspf"%>
    <link rel="stylesheet" href="<c:url value="/content/css/error.css"/>">
    <title>Title</title>
</head>
<body>
<div class="wrapper">
    <div class="text">
        <h1><fmt:message key="error.main"/></h1>
        <a class="btn btn-light action-button" role="button" href="${pageContext.request.contextPath}/index.jsp">
            <fmt:message key="menu.back"/>
        </a>
    </div>
    <div class="img">
        <img src="<c:url value="/content/imgs/29.png"/>" width="1040" height="680">
    </div>
</div>
</body>
</html>
