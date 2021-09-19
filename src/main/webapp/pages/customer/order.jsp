<%@ include file="/WEB-INF/jspf/settings.jspf"%>
<html>
<head>
    <title><fmt:message key="user.makeorder"/></title>
    <%@ include file="/WEB-INF/jspf/stylesheets.jspf"%>
    <link rel="stylesheet" href="<c:url value="/content/css/order.css"/>">

</head>
<body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>

<%@ include file="/WEB-INF/jspf/header.jspf"%>
<div class="wrapper">
    <div class="entrance_form">
        <div class="title">
            <fmt:message key='user.makeorder'/>
        </div>
        <div class="form_wrap">
            <div class="input_wrap">

                <form action="<%= request.getContextPath() %>/controller" method="get">
                    <input type="hidden" name="command" value="checkOrder"/>
                    <div class="selectWrapper">

                        <select class="selectBox" id="departure" name="departure">
                            <option disabled selected><fmt:message key='user.from'/></option>
                            <option value="Railway_Station"><fmt:message key='Railway_Station'/></option>
                            <option value="Market_Square"><fmt:message key='Market_Square'/></option>
                            <option value="Park_of_Culture"><fmt:message key='Park_of_Culture'/></option>
                            <option value="Lychakiv_Cemetery"><fmt:message key='Lychakiv_Cemetery'/></option>
                        </select>

                    </div>
                    <div class="selectWrapper">

                        <select class="selectBox" id="arrival" name="arrival">
                            <option disabled selected><fmt:message key='user.to'/></option>
                            <option value="Railway_Station"><fmt:message key='Railway_Station'/></option>
                            <option value="Market_Square"><fmt:message key='Market_Square'/></option>
                            <option value="Park_of_Culture"><fmt:message key='Park_of_Culture'/></option>
                            <option value="Lychakiv_Cemetery"><fmt:message key='Lychakiv_Cemetery'/></option>
                        </select>

                    </div>
                    <div class="input_grp">
                        <select class="selectBox" id="category" name="category">
                            <option disabled selected><fmt:message key='user.carcategory'/></option>
                            <option value="economic"><fmt:message key='economic'/></option>
                            <option value="comfort"><fmt:message key='comfort'/></option>
                            <option value="business"><fmt:message key='business'/></option>
                            <option value="minivan"><fmt:message key='minivan'/></option>
                        </select>

                        <select class="selectBox" id="numberOfSeats" name="numberOfSeats">
                            <option disabled selected><fmt:message key='car.numberofseats'/></option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                        </select>

                    </div>
                    <div class="input_grp">
                        <button class="selectBox" type="button">
                            <a href="<c:url value="/index.jsp"/>" style="color: #f0f0f0">
                                <fmt:message key='user.cancel'/>
                            </a>
                        </button>
                        <button class="selectBox" type="submit"><fmt:message key='user.submit'/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<div class="footer">
    <%@ include file="/WEB-INF/jspf/footer.jspf"%>
</div>
</body>
</html>