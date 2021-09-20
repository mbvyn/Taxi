package com.epam.taxi;

public class Path {
    private final String pageUrl;
    private final boolean isRedirect;
    private String errorMessage;

    public static final String MAIN = "/index.jsp";
    public static final String PAGE_ERROR_PAGE = "/pages/error/error.jsp";

    public static final String PAGE_CUSTOMER_ACCOUNT = "/pages/customer/customerAccount.jsp";
    public static final String PAGE_CAR_INFO = "/pages/customer/carInfo.jsp";
    public static final String PAGE_ANALOG_ORDER = "/pages/customer/analogOrder.jsp";
    public static final String PAGE_SUCCESSFUL_ORDER = "/pages/customer/successfulOrder.jsp";

    public static final String PAGE_ADMIN_ACCOUNT = "/pages/admin/adminAccount.jsp";
    public static final String PAGE_AUTOPARK = "/pages/admin/autopark.jsp";
    public static final String PAGE_ORDER_CARS = "/pages/admin/orderCarsInfo.jsp";

    public Path(String pageUrl, boolean isRedirect) {
        this.pageUrl = pageUrl;
        this.isRedirect = isRedirect;
    }
    public Path(String pageUrl, boolean isRedirect, String errorMessage) {
        this.pageUrl = pageUrl;
        this.isRedirect = isRedirect;
        this.errorMessage = errorMessage;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public boolean isRedirect() {
        return isRedirect;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
