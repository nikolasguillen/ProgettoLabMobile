package com.example.progetto;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="item")
public class CardItem {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "item_id")
    private int id;

    @ColumnInfo(name = "item_image")
    private String imageResource;

    @ColumnInfo(name = "item_place")
    private String place;

    @ColumnInfo(name = "item_date")
    private String date;

    @ColumnInfo(name = "item_description")
    private String description;

    public CardItem(String imageResource, String place, String date, String description) {
        this.imageResource = imageResource;
        this.place = place;
        this.date = date;
        this.description = description;
    }

    public String getImageResource() {
        return imageResource;
    }

    public String getPlace() {
        return place;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
