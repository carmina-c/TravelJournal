package com.example.traveljournal.room;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "trip_table")
public class Trip {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tripName")
    private final String mTrip;

    @ColumnInfo(name = "tripDestination")
    private final String mDestination;

    @ColumnInfo(name = "tripPrice")
    private final String mPrice;

    @ColumnInfo(name = "tripRating")
    private final float mRating;

    public Trip(@NonNull String mTrip, String mDestination, String mPrice, float mRating) {
        this.mTrip = mTrip;
        this.mDestination = mDestination;
        this.mPrice = mPrice;
        this.mRating = mRating;
    }

    public String getTrip() {
        return this.mTrip;
    }

    public String getDestination() {
        return mDestination;
    }

    public String getPrice() {
        return mPrice;
    }

    public float getRating() {
        return mRating;
    }
}
