package com.zuhriddin.dao;

import com.zuhriddin.dao.config.DatabaseConfig;
import com.zuhriddin.dao.config.PostgresDatabaseConfig;
import com.zuhriddin.model.Card;
import com.zuhriddin.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class CardDao {
    private final DatabaseConfig databaseConfig = new PostgresDatabaseConfig();
    private static final String INSERT_CARD = "select * from add_card(i_name := ?, i_card_number := ?, i_expiry_date := ?, i_password := ?, i_user_id := ?, i_balance := ?)";
    private static final String GET_MY_CARDS = "select * from get_my_cards(i_user_id := ?)";
    private static final String DELETE_MY_CARD = "select * from delete_my_card(i_user_id := ?, i_card_id := ?)";

    public Card addCard(Card card) {
        try (Connection connection = databaseConfig.connect();
             PreparedStatement statement = connection.prepareStatement(INSERT_CARD)) {

            statement.setString(1, card.getName());
            statement.setString(2, card.getCardNumber());
            statement.setString(3, card.getExpiryDate());
            statement.setString(4, card.getPassword());
            statement.setInt(5, card.getUserId());
            statement.setDouble(6, card.getBalance());
            ResultSet resultSet =statement.executeQuery();
            if (resultSet.next()){
                return new Card(resultSet);
            } else {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Card> getMyCards(int userId) {
        List<Card> cards = new ArrayList<>();

        try (Connection connection = databaseConfig.connect();
            PreparedStatement statement = connection.prepareStatement(GET_MY_CARDS)) {

            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Card card = new Card(resultSet);
                cards.add(card);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cards;
    }

    public void deleteMyCard(int userId, int cardId) {
        try (Connection connection = databaseConfig.connect();
            PreparedStatement statement = connection.prepareStatement(DELETE_MY_CARD)) {

            statement.setInt(1, userId);
            statement.setInt(2, cardId);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
