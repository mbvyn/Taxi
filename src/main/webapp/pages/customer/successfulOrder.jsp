<%@ include file="/WEB-INF/jspf/settings.jspf" %>
<html>
<head>
    <title><fmt:message key='user.orderdetails'/></title>
    <%@ include file="/WEB-INF/jspf/stylesheets.jspf" %>
    <link rel="stylesheet" href="<c:url value="/content/css/order.css"/>">
</head>
<body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>

<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="wrapper">
    <div class="entrance_form">
        <div class="title">
            <fmt:message key='user.orderdetails'/>
        </div>
        <div class="form_wrap">
            <div class="details">
                <p><fmt:message key='user.from'/>: <fmt:message key='${order.getDeparture()}'/></p>
                <p><fmt:message key='user.to'/>: <fmt:message key='${order.getArrival()}'/></p>
                <p><fmt:message key='menu.price'/>: ${order.getPrice()}</p>
            </div>
            <div class="input_grp">
                <button class="selectBox" type="button">
                    <a href="<c:url value="/index.jsp"/>" style="color: #f0f0f0">
                        <fmt:message key='user.cancel'/>
                    </a>
                </button>
                <form action="<%= request.getContextPath() %>/controller" method="post">
                    <input type="hidden" name="command" value="createOrder"/>
                    <button class="selectBox" type="submit"><fmt:message key='user.submit'/></button>
                </form>
            </div>
        </div>
    </div>
</div>


<div class="footer">
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
</body>
</html>