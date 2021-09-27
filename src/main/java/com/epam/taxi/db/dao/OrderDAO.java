package com.epam.taxi.db.dao;

import com.epam.taxi.db.DBManager;
import com.epam.taxi.db.entity.Order;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.epam.taxi.db.dao.SqlRequest.*;

/**
 * Data access object for Order entity.
 *
 * @author M.-B.Vynnytskyi
 * @see Order
 */
public class OrderDAO {
    private static final Logger LOGGER = Logger.getLogger(OrderDAO.class);

    /**
     * Method that allows to insert a new order into a database.
     */
    public void insertOrder(Order order) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement
                    (INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, getRouteId(order));
            preparedStatement.setDouble(2, order.getPrice());
            preparedStatement.setInt(3, order.getNumberOfPassengers());
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
        } finally {
            DBManager.getInstance().close(resultSet);
            DBManager.getInstance().close(preparedStatement);
        }
    }

    /**
     * Order needn't has a route id, because it has departure and arrival fields,
     * but we have to insert the route id into Order table.
     */
    private int getRouteId(Order order) throws SQLException {
        int routeId = 0;

        Connection connection = DBManager.getInstance().getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(GET_ROUTE_ID);

        preparedStatement.setString(1, order.getDeparture());
        preparedStatement.setString(2, order.getArrival());

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            routeId = resultSet.getInt(1);
        }

        DBManager.getInstance().commitAndClose(connection);
        DBManager.getInstance().close(resultSet);
        DBManager.getInstance().close(preparedStatement);

        return routeId;
    }

    /**
     * Order and Account tables have many-to-many relation,
     * therefore, in this case, we have to assign account id to order.
     */
    private void insertAccountToOrder(Order order) throws SQLException {
        Connection connection = DBManager.getInstance().getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT_TO_ORDER);

        preparedStatement.setInt(1, order.getAccountId());
        preparedStatement.setInt(2, order.getId());

        preparedStatement.executeUpdate();

        DBManager.getInstance().commitAndClose(connection);
        DBManager.getInstance().close(preparedStatement);
    }

    /**
     * Order and Car tables have many-to-many relation,
     * therefore, in this case, we must assign each car ID to order.
     *
     * @see Order
     */
    private void insertCarsToOrder(Order order) throws SQLException {
        List<Integer> carsId = order.getCarIdList();
        Connection connection = DBManager.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CARS_TO_ORDER);

        for (Integer carId : carsId) {

            preparedStatement.setInt(1, order.getId());
            preparedStatement.setInt(2, carId);

            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();

        DBManager.getInstance().commitAndClose(connection);
        DBManager.getInstance().close(preparedStatement);
    }

    /**
     * Method that allows to get all orders from a database.
     */
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

    /**
     * Method that allows to receive a specific customer order from a database
     */
    public List<Order> getCustomerOrders(int accountId) {
        List<Order> ordersList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(GET_CUSTOMER_ORDERS);

            preparedStatement.setInt(1, accountId);

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

    /**
     * Method that allows to receive orders for specific dates from the database
     */
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

    /**
     * Method which creates the Order object
     *
     * @see Order
     */
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

    /**
     * Method which adds Cars ID to Order object
     *
     * @see Order
     */
    private void getOrderCars(Order order) throws SQLException {
        Connection connection = DBManager.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_CARS);

        preparedStatement.setInt(1, order.getId());

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            order.setCarId(resultSet.getInt(1));
        }

        DBManager.getInstance().commitAndClose(connection);
        DBManager.getInstance().close(resultSet);
        DBManager.getInstance().close(preparedStatement);
    }

    /**
     * Method that helps to obtain information about cars for a specific order
     *
     * @see com.epam.taxi.command.common.GetCarInfoCommand
     */
    public List<Integer> getCarIdFromOrder(int orderId) {
        List<Integer> carIdList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(GET_CAR_ID_FROM_ORDER);
            preparedStatement.setInt(1, orderId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                carIdList.add(resultSet.getInt(1));
            }

            DBManager.getInstance().commitAndClose(connection);
        } catch (SQLException e) {
            LOGGER.error("Cannot get orders", e);
            DBManager.getInstance().rollbackAndClose(connection);
        } finally {
            DBManager.getInstance().close(resultSet);
            DBManager.getInstance().close(preparedStatement);
        }
        return carIdList;
    }

    /**
     * Method that helps to obtain information about route distance for a specific order.
     *
     * @see com.epam.taxi.command.client.CheckOrderCommand
     * @see com.epam.taxi.utils.PriceCalculator
     */
    public double getRouteDistance(String departure, String arrival) {
        double distance = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(GET_DISTANCE);

            preparedStatement.setString(1, departure);
            preparedStatement.setString(2, arrival);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                distance = resultSet.getDouble(1);
            }

            DBManager.getInstance().commitAndClose(connection);
        } catch (SQLException e) {
            LOGGER.error("Cannot get orders", e);
            DBManager.getInstance().rollbackAndClose(connection);
        } finally {
            DBManager.getInstance().close(resultSet);
            DBManager.getInstance().close(preparedStatement);
        }
        return distance;
    }
}
