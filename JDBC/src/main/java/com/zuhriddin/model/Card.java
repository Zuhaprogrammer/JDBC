package com.zuhriddin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Card {
    private int id;
    private String name;
    private String cardNumber;
    private String password;
    private String expiryDate;
    private double balance;
    private int userId;
    private String createdDate;

    public Card (String name, String cardNumber, String password,
                 String expiryDate, double balance, int userId) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.password = password;
        this.expiryDate = expiryDate;
        this.balance = balance;
        this.userId = userId;
    }

    public Card (ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.name = resultSet.getString("name");
        this.cardNumber = resultSet.getString("card_number");
        this.expiryDate = resultSet.getString("expiry_date");
        this.balance = resultSet.getDouble("balance");
        this.createdDate = resultSet.getString("created_date");
    }
}
