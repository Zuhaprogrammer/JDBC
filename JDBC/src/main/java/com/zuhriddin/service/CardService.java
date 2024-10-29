package com.zuhriddin.service;

import com.zuhriddin.dao.CardDao;
import com.zuhriddin.model.Card;
import java.util.*;

public class CardService {
    private final CardDao cardDao = new CardDao();

    public Card addCard(Card card) {
        return cardDao.addCard(card);
    }

    public List<Card> getMyCards(int userId) {
        return cardDao.getMyCards(userId);
    }

    public void deleteMyCard(int userId, int cardId) {
        cardDao.deleteMyCard(userId, cardId);
    }
}
