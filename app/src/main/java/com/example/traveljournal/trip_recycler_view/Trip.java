package com.example.traveljournal.trip_recycler_view;

import android.widget.RatingBar;

public class Trip {
    private int id;
    //private ImageView picture;
    private String tripName;
    private String destination;
    private String price;
    private final RatingBar ratingBar;

    public Trip(int id,
                //ImageView picture,
                String tripName,
                String destination,
                String price,
                RatingBar ratingBar) {
        this.id = id;
        //this.picture = picture;
        this.tripName = tripName;
        this.destination = destination;
        this.price = price;
        this.ratingBar = ratingBar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public ImageView getPicture() {
//        return picture;
//    }
//
//    public void setPicture(ImageView picture) {
//        this.picture = picture;
//    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public RatingBar getRatingBar() {
        return ratingBar;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", tripName='" + tripName + '\'' +
                ", destination='" + destination + '\'' +
                ", price='" + price + '\'' +
                ", ratingBar=" + ratingBar +
                '}';
    }
}
