package com.example.progetto.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.progetto.CardItem;

import java.util.ArrayList;
import java.util.List;

public class ListViewModel extends AndroidViewModel {

    private final MutableLiveData<CardItem> itemSelected = new MutableLiveData<>();

    private MutableLiveData<List<CardItem>> cardItems;

    public ListViewModel(@NonNull Application application) {
        super(application);
    }

    public void select(CardItem item) {
        itemSelected.setValue(item);
    }

    public LiveData<CardItem> getSelected() {
        return itemSelected;
    }

    public LiveData<List<CardItem>> getCardItems() {
        if(cardItems == null) {
            cardItems = new MutableLiveData<>();
            loadItems();
        }
        return cardItems;
    }

    private void loadItems() {
        addCardItem(new CardItem("ic_launcher_foreground", "Place1", "Date", "Description1"));
        addCardItem(new CardItem("ic_launcher_foreground", "Place2", "Date", "Description2"));
    }

    public void addCardItem(CardItem item) {
        List<CardItem> list = new ArrayList<>();
        list.add(item);

        if(cardItems.getValue() != null) {
            list.addAll(cardItems.getValue());
        }
        cardItems.setValue(list);
    }

    public CardItem getCardItem (int position) {
        return cardItems.getValue() == null ? null : cardItems.getValue().get(position);
    }
}
