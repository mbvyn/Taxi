package com.epam.taxi.db.dao;

import com.epam.taxi.db.DBManager;
import com.epam.taxi.db.entity.Car;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.epam.taxi.db.dao.SqlRequest.*;

public class CarDAO {
    private static final Logger LOGGER = Logger.getLogger(CarDAO.class);

    public List<Car> getCars(String language) {
        List<Car> carList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(GET_CARS);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
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

    public Car getCar(int carId, String language) {
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

    public Car getCarByNumberOfSeats(int numberOfSeats, String language) {
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

    public void updateCarStatus(int carId, String carStatus) {
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
        } finally {
            DBManager.getInstance().close(preparedStatement);
        }
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
