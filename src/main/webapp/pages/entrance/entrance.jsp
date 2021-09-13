<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Entrance</title>
    <link rel="stylesheet" href="../../content/css/entrance.css">
</head>
<body>
<div class="wrapper">
    <div class="entrance_form">
        <div class="title">
            Get moving with Taxi
        </div>
        <form action= <%= request.getContextPath() %>/controller method="post">
            <input type="hidden" name="command" value="login"/>
            <div class="form_wrap">
                <div class="input_wrap">
                    <label for="login">Login</label>
                    <input type="text" id="login" name="login" required>
                </div>
                <div class="input_wrap">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="input_grp">
                    <a href="<c:url value="/pages/entrance/registration.jsp"/>" class= "submit_btn">
                        <p class=text>Sign Up</p>
                    </a>
                    <input type="submit" value="LogIn" class="submit_btn">
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
