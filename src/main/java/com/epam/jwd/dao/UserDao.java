package com.epam.jwd.dao;

import com.epam.jwd.dbconnection.ConnectionPool;
import com.epam.jwd.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDao extends CommonDao<User> {

    private static final String STATEMENT_FOR_CREATION ="INSERT INTO user (login,pass) VALUES (?,?)";
    private static final String STATEMENT_FOR_READING_BY_LOGIN ="SELECT id, login, pass FROM user WHERE login=?";
    private final ConnectionPool connectionPool;

    private UserDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    };

    public static UserDao getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        public static final UserDao INSTANCE = new UserDao(ConnectionPool.instance());
    }

    @Override
    public Optional<User> create(User user) {
        try (Connection connection = connectionPool.takeConnection()) {
            PreparedStatement creationPrepared = connection.prepareStatement(STATEMENT_FOR_CREATION);
            creationPrepared.setString(1, user.getLogin());
            creationPrepared.setString(2, user.getPassword());
            creationPrepared.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return readUserByLogin(user.getLogin());
    }

    @Override
    List<User> Read() {
        return List.of();
    }

    public Optional<User> readUserByLogin(String login)  {
        try (Connection connection = connectionPool.takeConnection()) {
            PreparedStatement readingPrepared = connection.prepareStatement(STATEMENT_FOR_READING_BY_LOGIN);
            readingPrepared.setString(1, login);
            ResultSet resultSet = readingPrepared.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new User(resultSet.getString("login"), resultSet.getString("pass")).withId(resultSet.getInt("id")));
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
