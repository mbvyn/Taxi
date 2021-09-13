package com.epam.taxi.db;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {

    private static final Logger LOGGER = Logger.getLogger(DBManager.class);
    private static DBManager instance;

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private DBManager() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup(
                    "java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/Taxi");
        } catch (NamingException e) {
            LOGGER.error("Cannot init DBManager", e);
        }
    }
    public DataSource dataSource;

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void commitAndClose(Connection connection) {
        try {
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Cannot commit and close", e);
        }
    }

    public void rollbackAndClose(Connection connection) {
        try {
            connection.rollback();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Cannot rollback and close", e);
        }
    }

    public void close(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.error("Cannot close statement", e);
            }
        }
    }

    public void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.error("Cannot close resultSet", e);
            }
        }
    }
}
