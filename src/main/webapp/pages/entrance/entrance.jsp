<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fmt:message key='menu.log'/></title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700">
    <link rel="stylesheet" href="<c:url value="/content/css/entrance.css"/>">
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
                    <a href="<c:url value="/pages/entrance/registration.jsp"/>" class="btn btn-light action-button" role="button">
                        <p class=text><fmt:message key='menu.signup'/></p>
                    </a>
                    <input class="btn btn-light action-button" type="submit" value="<fmt:message key='menu.log'/>">
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
