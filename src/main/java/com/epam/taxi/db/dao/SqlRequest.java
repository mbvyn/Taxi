package com.epam.taxi.db.dao;

public class SqlRequest {
    public static final String GET_ORDERS =
            "SELECT o.id, account_id, departure, arrival, o.price, o.number_of_passangers, o.create_date\n" +
                    "FROM account_has_order\n" +
                    "INNER JOIN `order` o ON account_has_order.order_id = o.id\n" +
                    "INNER JOIN route ON o.route_id = route.id\n";
    public static final String GET_CUSTOMER_ORDERS =
            "SELECT o.id, account_id, departure, arrival, o.price, o.number_of_passangers, o.create_date\n" +
                    "FROM account_has_order\n" +
                    "INNER JOIN `order` o ON account_has_order.order_id = o.id\n" +
                    "INNER JOIN route ON o.route_id = route.id\n" +
                    "WHERE account_id = ?";
    public static final String GET_ORDERS_BY_DATE =
            "SELECT o.id, account_id, departure, arrival, o.price, o.number_of_passangers, o.create_date\n" +
                    "FROM account_has_order\n" +
                    "INNER JOIN `order` o ON account_has_order.order_id = o.id\n" +
                    "INNER JOIN route ON o.route_id = route.id\n" +
                    "WHERE create_date >= ?";
    public static final String GET_ORDER_CARS =
            "SELECT car_id FROM order_has_car WHERE order_id = ?";
    public static final String INSERT_ORDER =
            "INSERT INTO `order` VALUES (DEFAULT, ?, ?, ?, DEFAULT)";
    public static final String INSERT_ACCOUNT_TO_ORDER =
            "INSERT INTO account_has_order VALUES (?, ?)";
    public static final String INSERT_CARS_TO_ORDER =
            "INSERT INTO order_has_car VALUES (?, ?)";
    public static final String GET_DISTANCE =
            "SELECT distance FROM route WHERE departure = ? AND arrival = ?";
    public static final String GET_ROUTE_ID =
            "SELECT id FROM route WHERE departure = ? AND arrival = ?";
    public static final String GET_CAR_ID_FROM_ORDER =
            "SELECT car_id FROM order_has_car WHERE order_id = ?";

    public static final String GET_CAR =
            "SELECT car.id, status, category, number_of_seats " +
                    "FROM car JOIN car_details cd on car.car_details_id = cd.id " +
                    "WHERE car.id = ?";
    public static final String GET_CARS =
            "SELECT car.id, status, category, number_of_seats " +
                    "FROM car JOIN car_details cd on car.car_details_id = cd.id";
    public static final String GET_CAR_BY_NUMBER_OF_SEATS =
            "SELECT car.id, status, category, number_of_seats " +
                    "FROM car JOIN car_details cd ON cd.id = car.car_details_id " +
                    "WHERE car.status = 'to_order' AND cd.number_of_seats >= ?";
    public static final String GET_CARS_BY_CATEGORY =
            "SELECT car.id, status, category, number_of_seats " +
                    "FROM car JOIN car_details cd ON cd.id = car.car_details_id\n" +
                    "WHERE car.status = 'to_order' AND cd.category = ? limit 2";
    public static final String UPDATE_CAR_STATUS =
            "UPDATE car SET status = ? WHERE id = ?";
    public static final String GET_CAR_DESCRIPTION =
            "SELECT description FROM car_has_language \n" +
                    "WHERE car_id = ? AND language_id = \n" +
                    "(SELECT id FROM language WHERE short_name = ?)";

    public static final String INSERT_ACCOUNT =
            "INSERT INTO account VALUES(DEFAULT, ?, ?, ?, ?, ?, DEFAULT)";
    public static final String GET_ACCOUNT =
            "SELECT * FROM account WHERE login = ?";
    public static final String UPDATE_DISCOUNT_STATUS =
            "UPDATE account SET discount = ? WHERE id = ?";

}
