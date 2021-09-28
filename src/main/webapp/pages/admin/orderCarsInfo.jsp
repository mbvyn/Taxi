<%@ include file="/WEB-INF/jspf/settings.jspf" %>
<html>
<head>
    <meta charset="utf-8">

    <%@ include file="/WEB-INF/jspf/stylesheetContent.jspf" %>
    <%@ include file="/WEB-INF/jspf/stylesheets.jspf" %>
    <link rel="stylesheet" href="<c:url value="/content/css/adminaccount.css"/>">
    <link rel="stylesheet" href="content/account/css/style.css">

    <title><fmt:message key='user.carinfo'/></title>
</head>
<body>
<div class="header">
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</div>
<div class="content">
    <div class="container">
        <h2 class="mb-5"><fmt:message key='user.carinfo'/></h2>

        <div class="table-responsive">

            <table class="table table-striped custom-table">
                <form action=<%= request.getContextPath() %>/controller method="get">
                    <button class="btn btn-light action-button" role="button" name="command" value="getOrdersList">
                        <fmt:message key='admin.backtoorder'/></button>
                </form>
                <thead>
                <tr>
                    <th scope="col"><fmt:message key='car.numberofseats'/></th>
                    <th scope="col"><fmt:message key='car.category'/></th>
                    <th scope="col"><fmt:message key='car.description'/></th>
                    <th scope="col"><fmt:message key='car.status'/></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="car" items="${carsList}">
                    <tr scope="row">
                        <td>${car.getNumberOfSeats()}</td>
                        <td><fmt:message key='${car.getCategory()}'/></td>
                        <td>${car.getDescription()}</td>
                        <td>
                            <form action="<%= request.getContextPath() %>/controller" method="post">
                                <input type="hidden" name="command" value="changeCarStatus"/>
                                <input type="hidden" name="orderId" value=${orderId}>
                                <select class="btn btn-light action-button" role="button" id="status" name="status">
                                    <option disabled selected><fmt:message key='${car.getStatus()}'/></option>
                                    <option value="to_order"><fmt:message key='to_order'/></option>
                                    <option value="in_run"><fmt:message key='in_run'/></option>
                                    <option value="inactive"><fmt:message key='inactive'/></option>
                                </select>
                                <button class="btn btn-light action-button" role="button" type="submit" name="carId"
                                        value="${car.getId()}">
                                    <fmt:message key='admin.changestatus'/>
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>
        </div>
    </div>
</div>
<div class="footer">
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>

<script src="content/account/js/jquery-3.3.1.min.js"></script>
<script src="content/account/js/popper.min.js"></script>
<script src="content/account/js/bootstrap.min.js"></script>
<script src="content/account/js/main.js"></script>

</body>
</html>
