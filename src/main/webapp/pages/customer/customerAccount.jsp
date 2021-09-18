<%@ include file="/WEB-INF/jspf/settings.jspf"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="content/account/fonts/icomoon/style.css">

    <link rel="stylesheet" href="content/account/css/owl.carousel.min.css">

    <%@ include file="/WEB-INF/jspf/stylesheets.jspf"%>
    <link rel="stylesheet" href="<c:url value="/content/css/main.css"/>">

    <link rel="stylesheet" href="content/account/css/style.css">

    <title><fmt:message key='user.info'/></title>
</head>
<body>

<div class="header">
    <%@ include file="/WEB-INF/jspf/head.jspf"%>
</div>
<div class="content">

    <div class="container">

        <h2 class="mb-5"><fmt:message key='user.info'/></h2>
        <a href="<c:url value="/controller?command=getOrdersList&refresh=refresh"/>">
            <fmt:message key='admin.refresh'/>
        </a>
        <div class="table-responsive">

            <table class="table table-striped custom-table">
                <thead>
                <tr>

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

<script src="content/account/js/jquery-3.3.1.min.js"></script>
<script src="content/account/js/popper.min.js"></script>
<script src="content/account/js/bootstrap.min.js"></script>
<script src="content/account/js/main.js"></script>
</body>
</html>