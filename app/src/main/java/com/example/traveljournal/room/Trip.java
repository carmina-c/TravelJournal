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

    @ColumnInfo(name = "tripStartDate")
    private final String mStartDate;

    @ColumnInfo(name = "tripEndDate")
    private final String mEndDate;

    @ColumnInfo(name = "isFavourite")
    private final boolean mIsFavourite;


    public Trip(@NonNull String mTrip, String mDestination, String mPrice, float mRating, String mStartDate, String mEndDate, boolean mIsFavourite) {
        this.mTrip = mTrip;
        this.mDestination = mDestination;
        this.mPrice = mPrice;
        this.mRating = mRating;
        this.mStartDate = mStartDate;
        this.mEndDate = mEndDate;
        this.mIsFavourite = mIsFavourite;
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

    public String getStartDate() {
        return mStartDate;
    }

    public String getEndDate() {
        return mEndDate;
    }

    public boolean getIsFavourite() {
        return mIsFavourite;
    }
}
