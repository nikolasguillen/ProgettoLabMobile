package com.example.progetto;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progetto.RecyclerView.CardAdapter;
import com.example.progetto.RecyclerView.OnItemListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements OnItemListener {

    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Activity activity = getActivity();
        if(activity != null) {

            Utilities.setupToolbar((AppCompatActivity) activity, getString(R.string.app_name));

            setRecyclerView(activity);

            FloatingActionButton floatingActionButton = view.findViewById(R.id.fab_add);
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utilities.insertFragment((AppCompatActivity) activity, new AddFragment(), "AddFragment");
                }
            });
        } else {
            Log.e("HomeFragment.java", "Activity is null");
        }
    }

    private void setRecyclerView(final Activity activity) {
        recyclerView = getView().findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        List<CardItem> list = new ArrayList<>();
        list.add(new CardItem("ic_launcher_foreground", "Place", "Date", "Description"));
        list.add(new CardItem("ic_launcher_foreground", "Place", "Date", "Description"));
        final OnItemListener listener = this;
        cardAdapter = new CardAdapter(list, activity, listener);
        recyclerView.setAdapter(cardAdapter);
    }

    @Override
    public void onItemClick(int position) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        if(appCompatActivity != null) {
            Utilities.insertFragment(appCompatActivity, new DetailsFragment(), DetailsFragment.class.getSimpleName());
        }
    }
}
