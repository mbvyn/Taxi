<%@ include file="/WEB-INF/jspf/settings.jspf"%>
<html>
<head>
    <title><fmt:message key='user.orderdetails'/></title>
    <%@ include file="/WEB-INF/jspf/stylesheets.jspf"%>
    <link rel="stylesheet" href="<c:url value="/content/css/analog.css"/>">
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<div class="wrapper">
    <div class="entrance_form">
        <div class="title">
            <fmt:message key='user.analog'/>
        </div>
        <div class="form_wrap">

            <form action="<%= request.getContextPath() %>/controller" method="get">
                <input type="hidden" name="command" value="analogOrder"/>
                <div class="optionButton">
                    <button  class="selectBox" name="orderOption" value="anotherCategory"><fmt:message key='user.carwithanothercategory'/></button>
                </div>
                <div class="optionButton">
                    <button  class="selectBox" name="orderOption" value="sameCategory"><fmt:message key='user.acoupleofcars'/></button>
                </div>
            </form>
            <div class="optionButton">
                <button class="cancelButton" type="button">
                    <a href="<c:url value="/index.jsp"/>" style="color: #f0f0f0"><fmt:message key='user.cancel'/></a>
                </button>
            </div>
        </div>
    </div>
</div>

<div class="footer">
    <%@ include file="/WEB-INF/jspf/footer.jspf"%>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>

</body>
</html>