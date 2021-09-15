<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Entrance</title>
    <link rel="stylesheet" href="../../content/css/entrance.css">
</head>
<body>
<div class="wrapper">
    <div class="entrance_form">
        <div class="title">
            <fmt:message key='menu.welcomelog'/>
        </div>
        <form action= <%= request.getContextPath() %>/controller method="post">
            <input type="hidden" name="command" value="login"/>
            <div class="form_wrap">
                <div class="input_wrap">
                    <label for="login"><fmt:message key='menu.login'/></label>
                    <input type="text" id="login" name="login" required>
                </div>
                <div class="input_wrap">
                    <label for="password"><fmt:message key='menu.pass'/></label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="input_grp">
                    <a href="<c:url value="/pages/entrance/registration.jsp"/>" class= "submit_btn">
                        <p class=text><fmt:message key='menu.signup'/></p>
                    </a>
                    <input type="submit" value="<fmt:message key='menu.log'/>" class="submit_btn">
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
