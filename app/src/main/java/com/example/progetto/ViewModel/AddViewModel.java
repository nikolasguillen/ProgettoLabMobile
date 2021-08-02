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

import com.example.progetto.R;
import com.example.progetto.Utilities;

public class AddViewModel extends AndroidViewModel {

    private final MutableLiveData<Bitmap> imageBitmap = new MutableLiveData<>();
    private final  Application application;

    public AddViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        initializeBitmap();
    }

    public void setImageBitmap(Bitmap bitmap) {
        imageBitmap.setValue(bitmap);
    }

    public LiveData<Bitmap> getBitmap() {
        return imageBitmap;
    }

    public void initializeBitmap() {
        Drawable drawable = ResourcesCompat.getDrawable(application.getResources(), R.drawable.ic_launcher_foreground, application.getTheme());
        Bitmap bitmap = Utilities.drawableToBitmap(drawable);

        imageBitmap.setValue(bitmap);
    }
}
