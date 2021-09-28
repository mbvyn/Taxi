<%@ include file="/WEB-INF/jspf/settings.jspf" %>
<html>
<head>
    <%@ include file="/WEB-INF/jspf/stylesheetEntrance.jspf" %>
    <title><fmt:message key='menu.signup'/></title>
</head>
<body>
<div class="wrapper">
    <div class="entrance_form">
        <div class="title">
            <fmt:message key='menu.welcomesign'/>
        </div>
        <form action=<%= request.getContextPath() %>/controller method="post">
            <input type="hidden" name="command" value="registration"/>
            <div class="form_wrap">
                <div class="input_wrap">
                    <label for="login"><fmt:message key='menu.log'/></label>
                    <input type="text" id="login" name="login" required>
                </div>
                <div class="input_wrap">
                    <label for="phone_number"><fmt:message key='menu.phone'/></label>
                    <input type="tel" id="phone_number" name="phone_number"
                           pattern="[0-9]{10}"
                           required>
                </div>
                <div class="input_wrap">
                    <label for="email"><fmt:message key='menu.email'/></label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div class="input_wrap">
                    <label for="password"><fmt:message key='menu.pass'/></label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="input_grp">
                    <a href="<c:url value="/pages/entrance/entrance.jsp"/>" class="btn btn-light action-button">
                        <p class=text><fmt:message key='menu.login'/></p>
                    </a>
                    <input type="submit" value="<fmt:message key='menu.signup'/>" class="btn btn-light action-button">
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
