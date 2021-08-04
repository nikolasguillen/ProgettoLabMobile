package com.example.progetto;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.progetto.ViewModel.AddViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.example.progetto.Utilities.REQUEST_IMAGE_CAPTURE;

public class AddFragment extends Fragment {

    private TextInputEditText placeTextInput;
    private TextInputEditText descriptionTestInput;
    private TextInputEditText dateTextInput;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_travel, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Activity activity = getActivity();
        if (activity != null) {
            Utilities.setupToolbar((AppCompatActivity) activity, "Add travel");

            placeTextInput = view.findViewById(R.id.placeTextInputEditText);
            descriptionTestInput = view.findViewById(R.id.descriptionTextInputEditText);
            dateTextInput = view.findViewById(R.id.dateTextInputEditText);

            view.findViewById(R.id.captureButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
                        activity.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    }
                }
            });

            ImageView imageView = view.findViewById(R.id.imageView);
            AddViewModel addViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(AddViewModel.class);

            addViewModel.getBitmap().observe(getViewLifecycleOwner(), new Observer<Bitmap>() {
                @Override
                public void onChanged(Bitmap bitmap) {
                    imageView.setImageBitmap(bitmap);
                }
            });

            view.findViewById(R.id.fab_add).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bitmap bitmap = addViewModel.getBitmap().getValue();
                    String imageUriString;
                    if(bitmap != null) {
                        try {
                            imageUriString = String.valueOf(saveImage(bitmap, activity));
                            /*Toast.makeText(activity, "Image Saved", Toast.LENGTH_SHORT).show();*/
                        } catch (IOException e) {
                            e.printStackTrace();
                            imageUriString = "ic_launcher_foreground";
                        }
                    } else {
                        imageUriString = "ic_launcher_foreground";
                    }

                    addViewModel.addCardItem(new CardItem(imageUriString,
                            placeTextInput.getText().toString(),
                            descriptionTestInput.getText().toString(),
                            dateTextInput.getText().toString()));

                    addViewModel.setImageBitmap(null);
                    ((AppCompatActivity) activity).getSupportFragmentManager().popBackStack();
                }
            });
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.app_bar_search).setVisible(false);
    }

    private Uri saveImage(Bitmap bitmap, Activity activity) throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ITALY).format(new Date());
        String name = "JPEG_" + timestamp + "_.jpeg";

        ContentResolver resolver = activity.getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name);
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
        Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

        OutputStream outputStream = resolver.openOutputStream(imageUri);

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

        outputStream.close();

        return imageUri;
    }
}
