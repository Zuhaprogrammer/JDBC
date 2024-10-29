package com.zuhriddin.dao.config;

import com.zuhriddin.dao.config.annotation.Configuration;
import com.zuhriddin.dao.config.exception.PostgresConnectException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class PostgresDatabaseConfig implements DatabaseConfig {

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "192002";
    private static final String DATABASE = "user_card";

    @Override
    public Connection connect() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + DATABASE, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PostgresConnectException("Postgres Database Connection Failed");
        }
    }
}
