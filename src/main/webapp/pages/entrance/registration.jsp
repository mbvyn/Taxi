<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <link rel="stylesheet" href="../../content/css/registration.css">
</head>
<body>
<div class="wrapper">
    <div class="registration_form">
        <div class="title">
            Sign Up with Taxi
        </div>
        <form action= <%= request.getContextPath() %>/controller method="post">
            <input type="hidden" name="command" value="registration"/>
            <div class="form_wrap">
                <div class="input_wrap">
                    <label for="login">Login</label>
                    <input type="text" id="login" name="login" required>
                </div>
                <div class="input_wrap">
                    <label for="phone_number">Phone number</label>
                    <input type="tel" id="phone_number" name="phone_number"
                           pattern="[0-9]{10}"
                           required>
                </div>
                <div class="input_wrap">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div class="input_wrap">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="input_grp">
                    <a href="<c:url value="/pages/entrance/entrance.jsp"/>" class= "submit_btn">
                        <p class=text>Log In</p>
                    </a>
                    <input type="submit" value="Sing Up" class="submit_btn">
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
