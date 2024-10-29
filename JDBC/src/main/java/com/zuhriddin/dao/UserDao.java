package com.zuhriddin.dao;

import com.zuhriddin.dao.config.DatabaseConfig;
import com.zuhriddin.dao.config.PostgresDatabaseConfig;
import com.zuhriddin.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    private final DatabaseConfig databaseConfig = new PostgresDatabaseConfig();
    private static final String INSERT_USER = "select * from add_user(i_username := ?, i_password := ?, i_phone_number := ?, i_email := ?)";
    private static final String CHECK_USER = "select * from check_user(i_username := ?, i_password := ?)";

    public User addUser(User user) {
        try (Connection connect = databaseConfig.connect();
             PreparedStatement statement = connect.prepareStatement(INSERT_USER)) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getPhoneNumber());
            statement.setString(4, user.getEmail());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet);
            }else {
                throw new IllegalArgumentException();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User checkUser(String username, String password) {
        try (Connection connection = databaseConfig.connect();
            PreparedStatement statement = connection.prepareStatement(CHECK_USER)) {

            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return new User(resultSet);
            } else {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
