package com.example.progetto;

public class CardItem {

    private String imageResource;
    private String place;
    private String date;
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
}
