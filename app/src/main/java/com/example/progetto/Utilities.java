package com.example.progetto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Utilities {

    static void insertFragment(AppCompatActivity activity, Fragment fragment, String tag) {

        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container_view, fragment, tag);

        if(!(fragment instanceof HomeFragment) && !(fragment instanceof SettingsFragment)) {
            transaction.addToBackStack(tag);
        }

        transaction.commit();
    }

    static void setupToolbar(AppCompatActivity activity, String title) {
        Toolbar toolbar = activity.findViewById(R.id.topAppBar);
        toolbar.setTitle(title);

        if (activity.getSupportActionBar() == null) {
            activity.setSupportActionBar(toolbar);
        }
    }
}
