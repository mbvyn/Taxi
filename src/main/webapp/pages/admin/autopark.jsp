<%@ include file="/WEB-INF/jspf/settings.jspf"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="content/account/fonts/icomoon/style.css">

    <link rel="stylesheet" href="content/account/css/owl.carousel.min.css">

    <%@ include file="/WEB-INF/jspf/stylesheets.jspf"%>
    <link rel="stylesheet" href="<c:url value="/content/css/adminaccount.css"/>">

    <link rel="stylesheet" href="content/account/css/style.css">

    <title><fmt:message key='admin.autopark'/></title>
</head>
<body>

<div class="header">
    <%@ include file="/WEB-INF/jspf/head.jspf"%>
</div>
<div class="content">

    <div class="container">
        <div class="table-responsive">
            <table class="table table-striped custom-table">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key='car.numberofseats'/></th>
                    <th scope="col"><fmt:message key='car.category'/></th>
                    <th scope="col"><fmt:message key='car.description'/></th>
                    <th scope="col"><fmt:message key='car.status'/></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="car" items="${entities}">
                    <tr scope="row">
                        <td>${car.getNumberOfSeats()}</td>
                        <td><fmt:message key='${car.getCategory()}'/></td>
                        <td>${car.getDescription()}</td>
                        <td>
                            <form action="<%= request.getContextPath() %>/controller" method="post">
                                <input type="hidden" name="command" value="changeCarStatus"/>
                                    <select class="btn btn-light action-button" role="button"  id="status" name="status">
                                        <option disabled selected><fmt:message key='${car.getStatus()}'/></option>
                                        <option value="to_order"><fmt:message key='to_order'/></option>
                                        <option value="in_run"><fmt:message key='in_run'/></option>
                                        <option value="inactive"><fmt:message key='inactive'/></option>
                                    </select>
                                <button class="btn btn-light action-button" role="button" type="submit" name="carId" value="${car.getId()}"><fmt:message key='admin.changestatus'/></button>
                            </form>
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
                            <li><a href="controller?command=getCarsList&page=${i}">${i}</a></li>
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