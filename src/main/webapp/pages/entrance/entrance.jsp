<%@ include file="/WEB-INF/jspf/settings.jspf" %>
<html>
<head>
    <%@ include file="/WEB-INF/jspf/stylesheetEntrance.jspf" %>
    <title><fmt:message key='menu.log'/></title>
</head>
<body>
<div class="wrapper">
    <div class="entrance_form">
        <div class="title">
            <fmt:message key='menu.welcomelog'/>
        </div>
        <form action=<%= request.getContextPath() %>/controller method="post">
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
                    <a href="<c:url value="/pages/entrance/registration.jsp"/>" class="btn btn-light action-button"
                       role="button">
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
