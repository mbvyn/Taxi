package com.epam.taxi;

public class Path {
    private final String pageUrl;
    private final boolean isRedirect;

    public static final String MAIN = "/index.jsp";
    public static final String PAGE_LOGIN = "/pages/entrance/entrance.jsp";
    public static final String PAGE_REGISTRATION = "pages/entrance/registration.jsp";
    public static final String PAGE_ERROR_PAGE = "/pages/error/error.jsp";

    public static final String PAGE_CUSTOMER_ACCOUNT = "/pages/customer/customerAccount.jsp";
    public static final String PAGE_ORDER = "/pages/customer/order.jsp";
    public static final String PAGE_ANALOG_ORDER = "/pages/customer/analogOrder.jsp";
    public static final String PAGE_SUCCESSFUL_ORDER = "/pages/customer/successfulOrder.jsp";
    public static final String PAGE_UNSUCCESSFUL_ORDER = "/pages/customer/unsuccessfulOrder.jsp";

    public static final String PAGE_ADMIN_ACCOUNT = "/pages/admin/adminAccount.jsp";
    public static final String PAGE_AUTOPARK = "/pages/admin/autopark.jsp";

    public Path(String pageUrl, boolean isRedirect) {
        this.pageUrl = pageUrl;
        this.isRedirect = isRedirect;
    }


    public String getPageUrl() {
        return pageUrl;
    }


    public boolean isRedirect() {
        return isRedirect;
    }
}
