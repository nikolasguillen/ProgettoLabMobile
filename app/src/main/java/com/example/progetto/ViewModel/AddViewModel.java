package com.example.progetto.ViewModel;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.progetto.CardItem;
import com.example.progetto.Database.CardItemRepository;
import com.example.progetto.R;
import com.example.progetto.Utilities;

public class AddViewModel extends AndroidViewModel {

    private CardItemRepository repository;

    private final MutableLiveData<Bitmap> imageBitmap = new MutableLiveData<>();

    public AddViewModel(@NonNull Application application) {
        super(application);

        repository = new CardItemRepository(application);
    }

    public void setImageBitmap(Bitmap bitmap) {
        imageBitmap.setValue(bitmap);
    }

    public LiveData<Bitmap> getBitmap() {
        return imageBitmap;
    }

    public void addCardItem(CardItem cardItem) {
        repository.addCardItem(cardItem);
    }
}
