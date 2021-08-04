package com.example.progetto.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.progetto.CardItem;

import java.util.List;

public class CardItemRepository {

    private CardItemDAO cardItemDAO;

    private LiveData<List<CardItem>> cardItemList;

    public CardItemRepository(Application application) {
        CardItemDatabase cardItemDatabase = CardItemDatabase.getDatabase(application);

        cardItemDAO = cardItemDatabase.cardItemDAO();

        cardItemList = cardItemDAO.getCardItems();
    }

    public LiveData<List<CardItem>> getCardItemList() {
        return cardItemList;
    }

    public void addCardItem(CardItem item) {
        CardItemDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                cardItemDAO.addCartItem(item);
            }
        });
    }
}
