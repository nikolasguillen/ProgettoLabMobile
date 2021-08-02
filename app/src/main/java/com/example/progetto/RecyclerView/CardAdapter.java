package com.example.progetto.RecyclerView;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progetto.CardItem;
import com.example.progetto.R;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardViewHolder> {

    private List<CardItem> cardItemList;
    private Activity activity;
    private OnItemListener listener;

    public CardAdapter(List<CardItem> cardItemList, Activity activity, OnItemListener listener) {
        this.cardItemList = cardItemList;
        this.activity = activity;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new CardViewHolder(layoutView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        CardItem currentCardItem = cardItemList.get(position);

        holder.placeTextView.setText(currentCardItem.getPlace());
        holder.dateTextView.setText(currentCardItem.getDate());

        String image_path = currentCardItem.getImageResource();
        if(image_path.contains("ic_")) {
            Drawable drawable = ContextCompat.getDrawable(activity, activity.getResources().getIdentifier(image_path, "drawable", activity.getPackageName()));
            holder.imageCardView.setImageDrawable(drawable);
        }
    }

    @Override
    public int getItemCount() {
        return cardItemList.size();
    }
}
