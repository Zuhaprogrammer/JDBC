package com.zuhriddin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String createdDate;
    private String updatedDate;

    public User (String username, String password, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public User(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.username = resultSet.getString("username");
        this.email = resultSet.getString("email");
        this.phoneNumber = resultSet.getString("phone_number");
        this.createdDate = resultSet.getString("created_date");
        this.updatedDate = resultSet.getString("updated_date");
    }
}
