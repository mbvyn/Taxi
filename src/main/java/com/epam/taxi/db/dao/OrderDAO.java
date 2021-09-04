package com.epam.taxi.db.dao;

import com.epam.taxi.db.DBManager;
import com.epam.taxi.db.entity.Account;
import com.epam.taxi.db.entity.Order;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private static final Logger LOGGER = Logger.getLogger(OrderDAO.class);

    private static final String GET_ORDERS =
            "SELECT o.id, account_id, o.departure, o.arrival, o.price, o.number_of_passengers, o.create_date\n" +
                    "FROM account_has_order\n" +
                    "INNER JOIN `order` o ON account_has_order.order_id = o.id";
    private static final String GET_CUSTOMER_ORDERS =
            "SELECT o.id, account_id, o.departure, o.arrival, o.price, o.number_of_passengers, o.create_date\n" +
                    "FROM account_has_order\n" +
                    "INNER JOIN `order` o ON account_has_order.order_id = o.id\n" +
                    "WHERE account_id = ?";
    private static final String GET_ORDERS_BY_DATE =
            "SELECT o.id, account_id, o.departure, o.arrival, o.price, o.number_of_passengers, o.create_date\n" +
                    "FROM account_has_order\n" +
                    "INNER JOIN `order` o ON account_has_order.order_id = o.id\n" +
                    "WHERE create_date  LIKE ?+'%";
    private static final String GET_ORDER_CARS =
            "SELECT car_id FROM order_has_car WHERE order_id = ?";
    private static final String INSERT_ORDER =
            "INSERT INTO `order` VALUES (DEFAULT, ?, ?, ?, ?, DEFAULT)";
    private static final String INSERT_ACCOUNT_TO_ORDER =
            "INSERT INTO account_has_order VALUES (?, ?)";
    private static final String INSERT_CARS_TO_ORDER =
            "INSERT INTO order_has_car VALUES (?, ?)";

    public boolean insertOrder(Order order){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement
                    (INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, order.getDeparture());
            preparedStatement.setString(2, order.getArrival());
            preparedStatement.setDouble(3, order.getPrice());
            preparedStatement.setInt(4, order.getNumberOfPassengers());
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setId(resultSet.getInt(1));
            }
            DBManager.getInstance().commitAndClose(connection);

            insertAccountToOrder(order);
            insertCarsToOrder(order);

        } catch (SQLException e) {
            LOGGER.error("Cannot insert order", e);
            DBManager.getInstance().rollbackAndClose(connection);
            return false;
        } finally {
            DBManager.getInstance().close(resultSet);
            DBManager.getInstance().close(preparedStatement);
        }
        return true;
    }
    public List<Order> getOrders() {
        List<Order> ordersList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
             connection = DBManager.getInstance().getConnection();
             preparedStatement = connection.prepareStatement(GET_ORDERS);
             resultSet = preparedStatement.executeQuery();

             while (resultSet.next()) {
                 ordersList.add(createOrder(resultSet));
             }
             DBManager.getInstance().commitAndClose(connection);
        } catch (SQLException e) {
            LOGGER.error("Cannot get orders", e);
            DBManager.getInstance().rollbackAndClose(connection);
        } finally {
            DBManager.getInstance().close(resultSet);
            DBManager.getInstance().close(preparedStatement);
        }
        return ordersList;
    }

    public List<Order> getCustomerOrders(Account account) {
        List<Order> ordersList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(GET_CUSTOMER_ORDERS);
            preparedStatement.setInt(1, account.getId());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ordersList.add(createOrder(resultSet));
            }
            DBManager.getInstance().commitAndClose(connection);
        } catch (SQLException e) {
            LOGGER.error("Cannot get customer orders", e);
            DBManager.getInstance().rollbackAndClose(connection);
        } finally {
            DBManager.getInstance().close(resultSet);
            DBManager.getInstance().close(preparedStatement);
        }
        return ordersList;
    }
    public List<Order> getOrdersByDate(String date) {
        List<Order> ordersList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(GET_ORDERS_BY_DATE);
            preparedStatement.setDate(1, Date.valueOf(date));
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ordersList.add(createOrder(resultSet));
            }
            DBManager.getInstance().commitAndClose(connection);
        } catch (SQLException e) {
            LOGGER.error("Cannot get orders", e);
            DBManager.getInstance().rollbackAndClose(connection);
        } finally {
            DBManager.getInstance().close(resultSet);
            DBManager.getInstance().close(preparedStatement);
        }
        return ordersList;
    }

    private void insertAccountToOrder(Order order) throws SQLException {
        Connection connection = DBManager.getInstance().getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT_TO_ORDER);
        preparedStatement.setInt(1, order.getAccountId());
        preparedStatement.setInt(2, order.getId());
        preparedStatement.executeUpdate();

        DBManager.getInstance().commitAndClose(connection);
        DBManager.getInstance().close(preparedStatement);
    }
    private void insertCarsToOrder(Order order) throws SQLException {
        List<Integer> carsId = order.getCarIdList();
        Connection connection = DBManager.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CARS_TO_ORDER);

        for (Integer carId: carsId) {
            preparedStatement.setInt(1, order.getId());
            preparedStatement.setInt(2, carId);
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();

        DBManager.getInstance().commitAndClose(connection);
        DBManager.getInstance().close(preparedStatement);
    }

    private void getOrderCars(Order order) throws SQLException {
        Connection connection = DBManager.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_CARS);
        preparedStatement.setInt(1, order.getId());
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            order.setCarId(resultSet.getInt(1));
        }
    }

    private Order createOrder(ResultSet resultSet) throws SQLException {
        Order order = Order.createOrder();
        order.setId(resultSet.getInt(1));
        order.setAccountId(resultSet.getInt(2));
        order.setDeparture(resultSet.getString(3));
        order.setArrival(resultSet.getString(4));
        order.setPrice(resultSet.getDouble(5));
        order.setNumberOfPassengers(resultSet.getInt(6));
        order.setOrderingDate(resultSet.getDate(7));
        getOrderCars(order);

        return order;
    }
}
