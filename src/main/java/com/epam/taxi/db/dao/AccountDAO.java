package com.epam.taxi.db.dao;

import com.epam.taxi.db.DBManager;
import com.epam.taxi.db.entity.Account;
import org.apache.log4j.Logger;

import java.sql.*;

public class AccountDAO {

    private static final Logger LOGGER = Logger.getLogger(AccountDAO.class);

    private static final String INSERT_ACCOUNT =
            "INSERT INTO account VALUES(DEFAULT, ?, ?, ?, ?, ?, DEFAULT)";

    private static final String GET_ACCOUNT =
            "SELECT * FROM account WHERE login = ?";

    private static final String UPDATE_DISCOUNT_STATUS =
            "UPDATE account SET discount = ? WHERE id = ?";

    public boolean insertAccount(Account account){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBManager.getInstance().getConnection();

            preparedStatement = connection.prepareStatement(
                    INSERT_ACCOUNT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, account.getLogin());
            preparedStatement.setString(2, account.getEmail());
            preparedStatement.setString(3, account.getPassword());
            preparedStatement.setString(4, account.getPhoneNumber());
            preparedStatement.setBoolean(5, account.isDiscount());
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                account.setId(resultSet.getInt(1));
            }

            DBManager.getInstance().commitAndClose(connection);
        } catch (SQLException e) {
            LOGGER.error("Cannot insert account", e);
            e.printStackTrace();
            DBManager.getInstance().rollbackAndClose(connection);
            return false;
        } finally {
            DBManager.getInstance().close(resultSet);
            DBManager.getInstance().close(preparedStatement);
        }
        return true;
    }

    public Account getAccount(String accountLogin) {
        Account account = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(GET_ACCOUNT);
            preparedStatement.setString(1, accountLogin);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
               account = Account.createAccount();

               account.setId(resultSet.getInt(1));
               account.setLogin(resultSet.getString(2));
               account.setEmail(resultSet.getString(3));
               account.setPassword(resultSet.getString(4));
               account.setPhoneNumber(resultSet.getString(5));
               account.setDiscount(resultSet.getBoolean(6));
               account.updateRole(resultSet.getBoolean(7));
            }

            DBManager.getInstance().commitAndClose(connection);
        } catch (SQLException e) {
            LOGGER.error("Cannot get account", e);
            DBManager.getInstance().rollbackAndClose(connection);
        } finally {
            DBManager.getInstance().close(resultSet);
            DBManager.getInstance().close(preparedStatement);
        }
        return account;
    }

    public boolean updateAccountDiscountStatus(Account account) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_DISCOUNT_STATUS);
            preparedStatement.setBoolean(1, account.isDiscount());
            preparedStatement.setInt(2, account.getId());
            preparedStatement.executeUpdate();

            DBManager.getInstance().commitAndClose(connection);
        } catch (SQLException e) {
            LOGGER.error("Cannot get account", e);
            DBManager.getInstance().rollbackAndClose(connection);
            return false;
        } finally {
            DBManager.getInstance().close(preparedStatement);
        }
        return true;
    }
}
