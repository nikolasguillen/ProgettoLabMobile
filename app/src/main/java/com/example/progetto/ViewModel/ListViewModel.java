package com.example.progetto.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.progetto.CardItem;
import com.example.progetto.Database.CardItemRepository;

import java.util.ArrayList;
import java.util.List;

public class ListViewModel extends AndroidViewModel {

    private final MutableLiveData<CardItem> itemSelected = new MutableLiveData<>();

    private LiveData<List<CardItem>> cardItems;

    public ListViewModel(@NonNull Application application) {
        super(application);
        CardItemRepository repository = new CardItemRepository(application);
        cardItems = repository.getCardItemList();
    }

    public void select(CardItem item) {
        itemSelected.setValue(item);
    }

    public LiveData<CardItem> getSelected() {
        return itemSelected;
    }

    public LiveData<List<CardItem>> getCardItems() {
        return cardItems;
    }

    public CardItem getCardItem (int position) {
        return cardItems.getValue() == null ? null : cardItems.getValue().get(position);
    }
}
