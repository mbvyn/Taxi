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
    <title><fmt:message key='user.carinfo'/></title>
</head>
<body>
<div class="header">
    <%@ include file="/WEB-INF/jspf/head.jspf"%>
</div>
<div class="content">

    <div class="container">
        <h2 class="mb-5"><fmt:message key='user.carinfo'/></h2>

        <div class="table-responsive">

            <table class="table table-striped custom-table">
                <thead>
                <tr>

                    <th scope="col"><fmt:message key='car.numberofseats'/></th>
                    <th scope="col"><fmt:message key='car.category'/></th>
                    <th scope="col"><fmt:message key='car.description'/></th>
                </tr>
                </thead>

                <tbody>
                    <c:forEach var="car" items="${carsList}">
                        <tr scope="row">
                            <td>${car.getNumberOfSeats()}</td>
                            <td><fmt:message key='${car.getCategory()}'/></td>
                            <td>${car.getDescription()}</td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>
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
