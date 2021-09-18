<%@ include file="/WEB-INF/jspf/settings.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="/WEB-INF/jspf/stylesheets.jspf"%>
  <link rel="stylesheet" href="<c:url value="/content/css/main.css"/>">
  <title>Taxi</title>
</head>
<body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>

  <%@ include file="/WEB-INF/jspf/header.jspf"%>
    <div class="container hero">
      <div class="row">
        <div class="col-12 col-lg-6 col-xl-50 offset-xl-3">
          <c:choose>
            <c:when test="${account.getRole() == true}">
              <h1><fmt:message key='admin.carsinfo'/></h1>
              <form action= <%= request.getContextPath() %>/controller method="get">
                <button  class="btn btn-light btn-lg action-button" name="command" value="getCarsList">
                  <fmt:message key='admin.autopark'/>
                </button>
              </form>
            </c:when>
            <c:when test="${account.getRole() == false}">
              <h1><fmt:message key='menu.welcome'/></h1>
            <form action = <%= request.getContextPath() %>/pages/customer/order.jsp method="get">
              <button class="btn btn-light btn-lg action-button"><fmt:message key='user.makeorder'/></button>
            </form>
            </c:when>
            <c:otherwise>
              <h1><fmt:message key='menu.welcome'/></h1>
                <a class="btn btn-light action-button" href="pages/entrance/entrance.jsp" class="login"><fmt:message key='menu.login'/></a>
            </c:otherwise>
          </c:choose>
        </div>
        </div>
      </div>

  <div class="footer">
    <%@ include file="/WEB-INF/jspf/footer.jspf"%>
  </div>
</body>
</html>