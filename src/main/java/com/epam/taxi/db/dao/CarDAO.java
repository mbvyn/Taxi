package com.epam.taxi.db.dao;

import com.epam.taxi.db.DBManager;
import com.epam.taxi.db.entity.Car;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {
    private static final Logger LOGGER = Logger.getLogger(CarDAO.class);

    private static final String INSERT_CAR =
            "INSERT INTO car VALUES(DEFAULT, ?, ?)";
    private static final String GET_CAR =
            "SELECT car.id, status, category, number_of_seats " +
                    "FROM car JOIN car_details cd on car.car_details_id = cd.id " +
                    "WHERE car.id = ?";
    private static final String GET_CARS =
            "SELECT car.id, status, category, number_of_seats " +
            "FROM car JOIN car_details cd on car.car_details_id = cd.id";
    private static final String GET_CAR_DETAILS_ID =
            "SELECT id FROM car_details WHERE category = ?";
    private static final String GET_CAR_BY_NUMBER_OF_SEATS =
            "SELECT car.id, status, category, number_of_seats " +
                    "FROM car JOIN car_details cd ON cd.id = car.car_details_id " +
                    "WHERE car.status = 'to_order' AND cd.number_of_seats = ?";
    private static final String GET_CARS_BY_CATEGORY =
            "SELECT car.id, status, category, number_of_seats " +
                    "FROM car JOIN car_details cd ON cd.id = car.car_details_id\n" +
                    "WHERE car.status = 'to_order' AND cd.category = ? limit 2";
    private static final String UPDATE_CAR_STATUS =
            "UPDATE car SET status = ? WHERE id = ?";
    private static final String INSERT_CAR_DESCRIPTION =
            "INSERT INTO car_has_language " +
            "VALUES (?,(select id from language where short_name = ?), ?)";
    private static final String GET_CAR_DESCRIPTION =
            "SELECT description FROM car_has_language \n" +
            "WHERE car_id = ? AND language_id = \n" +
            "(SELECT id FROM language WHERE short_name = ?)";

    public boolean insertCar(Car car) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(INSERT_CAR, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, car.getStatus());
            preparedStatement.setInt(2, getCarDetailsId(car));
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                car.setId(resultSet.getInt(1));
            }
            DBManager.getInstance().commitAndClose(connection);
        } catch (SQLException e) {
            LOGGER.error("Cannot insert car", e);
            DBManager.getInstance().rollbackAndClose(connection);
            return false;
        } finally {
            DBManager.getInstance().close(resultSet);
            DBManager.getInstance().close(preparedStatement);
        }
        return true;
    }

    public boolean insertCarDescription(Car car, String language) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBManager.getInstance().getConnection();

            preparedStatement = connection.prepareStatement(INSERT_CAR_DESCRIPTION);
            preparedStatement.setInt(1, car.getId());
            preparedStatement.setString(2, language);
            preparedStatement.setString(3, car.getDescription());
            preparedStatement.executeUpdate();

            DBManager.getInstance().commitAndClose(connection);
        } catch (SQLException e) {
            LOGGER.error("Can't insert car description", e);
            DBManager.getInstance().rollbackAndClose(connection);
            return false;
        } finally {
            DBManager.getInstance().close(preparedStatement);
        }
        return true;
    }

    public List<Car> getCars(String language) {
        List<Car> carList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(GET_CARS);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
               Car car = createCarWithAttributes(resultSet);
               car.setDescription(getCarDescription(car, language));
               carList.add(car);
            }
            DBManager.getInstance().commitAndClose(connection);
        } catch (SQLException e) {
            LOGGER.error("Cannot get car", e);
            DBManager.getInstance().rollbackAndClose(connection);
        } finally {
            DBManager.getInstance().close(resultSet);
            DBManager.getInstance().close(preparedStatement);
        }
        return carList;
    }

    public Car getCar(int carId, String language){
        Car car = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(GET_CAR);
            preparedStatement.setInt(1, carId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                car = createCarWithAttributes(resultSet);
                car.setDescription(getCarDescription(car, language));
            }

            DBManager.getInstance().commitAndClose(connection);
        } catch (SQLException e) {
            LOGGER.error("Cannot get car", e);
            DBManager.getInstance().rollbackAndClose(connection);
        } finally {
            DBManager.getInstance().close(resultSet);
            DBManager.getInstance().close(preparedStatement);
        }
        return car;
    }

    public Car getCarByNumberOfSeats(int numberOfSeats, String language){
        Car car = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(GET_CAR_BY_NUMBER_OF_SEATS);
            preparedStatement.setInt(1, numberOfSeats);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                car = createCarWithAttributes(resultSet);
                car.setDescription(getCarDescription(car, language));
            }

            DBManager.getInstance().commitAndClose(connection);
        } catch (SQLException e) {
            LOGGER.error("Cannot get car", e);
            DBManager.getInstance().rollbackAndClose(connection);
        } finally {
            DBManager.getInstance().close(resultSet);
            DBManager.getInstance().close(preparedStatement);
        }
        return car;
    }

    public List<Car> getCarsByCategory(String category, String language) {
        List<Car> carsList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(GET_CARS_BY_CATEGORY);
            preparedStatement.setString(1, category);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Car car = createCarWithAttributes(resultSet);
                car.setDescription(getCarDescription(car, language));
                carsList.add(car);
            }

            DBManager.getInstance().commitAndClose(connection);
        } catch (SQLException e) {
            LOGGER.error("Cannot get cars", e);
            DBManager.getInstance().rollbackAndClose(connection);
        } finally {
            DBManager.getInstance().close(resultSet);
            DBManager.getInstance().close(preparedStatement);
        }
        return carsList;
    }

    public boolean updateCarStatus(int carId, String carStatus) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_CAR_STATUS);
            preparedStatement.setString(1, carStatus);
            preparedStatement.setInt(2, carId);
            preparedStatement.executeUpdate();
            DBManager.getInstance().commitAndClose(connection);
        } catch (SQLException e) {
            LOGGER.error("Cannot update car status", e);
            DBManager.getInstance().rollbackAndClose(connection);
            return false;
        } finally {
            DBManager.getInstance().close(preparedStatement);
        }
        return true;
    }

    private int getCarDetailsId(Car car) throws SQLException {
        int carDetailsId = 1;

        Connection connection = DBManager.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_CAR_DETAILS_ID);
        preparedStatement.setString(1, car.getCategory());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            carDetailsId = resultSet.getInt(1);
        }
        DBManager.getInstance().commitAndClose(connection);
        return carDetailsId;
    }

    private Car createCarWithAttributes(ResultSet resultSet) throws SQLException {
        Car car = Car.createCar();
        car.setId(resultSet.getInt(1));
        car.setStatus(resultSet.getString(2));
        car.setCategory(resultSet.getString(3));
        car.setNumberOfSeats(resultSet.getInt(4));
        return car;
    }

    private String getCarDescription(Car car, String language) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String carDescription = "";
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(GET_CAR_DESCRIPTION);
            preparedStatement.setInt(1, car.getId());
            preparedStatement.setString(2, language);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                carDescription = resultSet.getString(1);
            }
            DBManager.getInstance().commitAndClose(connection);
        } catch (SQLException e) {
            LOGGER.error("Cannot get car description", e);
            DBManager.getInstance().rollbackAndClose(connection);
        } finally {
            DBManager.getInstance().close(resultSet);
            DBManager.getInstance().close(preparedStatement);
           }
        return carDescription;
    }
}
