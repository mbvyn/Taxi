<%@ include file="/WEB-INF/jspf/settings.jspf"%>
<html>
<head>
    <meta charset="utf-8">

    <%@ include file="/WEB-INF/jspf/stylesheets.jspf"%>
    <%@ include file="/WEB-INF/jspf/stylesheetContent.jspf" %>
    <link rel="stylesheet" href="<c:url value="/content/css/adminaccount.css"/>">
    <link rel="stylesheet" href="content/account/css/style.css">

    <title><fmt:message key='menu.account'/></title>
</head>
<body>

<div class="header">
    <%@ include file="/WEB-INF/jspf/head.jspf"%>
</div>
<div class="content">

    <div class="container">
        <div class="search-box">
            <form action=<%= request.getContextPath() %>/controller method="get">
                <input type="hidden" name="command" value="getOrdersList"/>
                <button class="btn-search"><i class='fas fa-search'></i></button>
                <input type="text" name="userId" pattern="[0-9]+" class="input-search" placeholder="<fmt:message key='admin.userid'/>">
            </form>
        </div>
        <div class="search-box">
            <form action=<%= request.getContextPath() %>/controller method="get">
                <input type="hidden" name="command" value="getOrdersList"/>
                <button class="btn-search" type="submit"><i class='fas fa-search'></i></button>
                <input class="input-search" type="date" name="date">
            </form>
        </div>
        <a href="<c:url value="/controller?command=getOrdersList&refresh=refresh"/>">
            <fmt:message key='admin.refresh'/>
        </a>
        <div class="table-responsive">

            <table class="table table-striped custom-table">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key='admin.userid'/></th>
                    <th scope="col"><fmt:message key='menu.departure'/></th>
                    <th scope="col"><fmt:message key='menu.arrival'/></th>
                    <th scope="col"><a href="controller?command=getOrdersList&sort=date" class="more"><fmt:message key='menu.date'/></a></th>
                    <th scope="col"><a href="controller?command=getOrdersList&sort=price" class="more"><fmt:message key='menu.price'/></a></th>
                    <th scope="col"><fmt:message key='menu.numberofpassengers'/></th>
                    <th scope="col"><fmt:message key='menu.cars'/></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="order" items="${entities}">
                    <tr scope="row">
                        <td>${order.getAccountId()}</td>
                        <td>
                            <fmt:message key='${order.getDeparture()}'/>
                        </td>
                        <td><fmt:message key='${order.getArrival()}'/></td>
                        <td>${order.getOrderingDate()}</td>
                        <td>${order.getPrice()}</td>
                        <td>${order.getNumberOfPassengers()}</td>
                        <td>
                            <a href="controller?command=getCarInfo&orderId=${order.getId()}" class="more">
                                <fmt:message key='user.details'/>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>
        </div>

        <div class="container_pagination">
            <ul class="pagination">
                <c:forEach begin="1" end="${numberOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <li><a>${i}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="controller?command=getOrdersList&page=${i}">${i}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
<div class="footer">
    <%@ include file="/WEB-INF/jspf/footer.jspf"%>
</div>
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<script src="content/account/js/jquery-3.3.1.min.js"></script>
<script src="content/account/js/popper.min.js"></script>
<script src="content/account/js/bootstrap.min.js"></script>
<script src="content/account/js/main.js"></script>
</body>
</html>