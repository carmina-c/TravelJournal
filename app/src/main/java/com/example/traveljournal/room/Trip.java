package com.example.traveljournal.room;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "trip_table")
public class Trip {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "tripID")
    private int mID;

    @ColumnInfo(name = "tripName")
    private String mTrip;

    @ColumnInfo(name = "tripDestination")
    private String mDestination;

    @ColumnInfo(name = "tripPrice")
    private String mPrice;

    @ColumnInfo(name = "tripRating")
    private float mRating;

    @ColumnInfo(name = "tripStartDate")
    private String mStartDate;

    @ColumnInfo(name = "tripEndDate")
    private String mEndDate;

    @ColumnInfo(name = "isFavourite")
    private boolean mIsFavourite;

    @ColumnInfo(name = "tripImagePath")
    private String mPath;


    public Trip(@NonNull String mTrip, String mDestination, String mPrice, float mRating, String mStartDate, String mEndDate, boolean mIsFavourite) {
        this.mTrip = mTrip;
        this.mDestination = mDestination;
        this.mPrice = mPrice;
        this.mRating = mRating;
        this.mStartDate = mStartDate;
        this.mEndDate = mEndDate;
        this.mIsFavourite = mIsFavourite;
    }

    public int getID() {return this.mID;}

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

    public String getPath(){return mPath;}

    public void setIsFavourite(boolean value){
        mIsFavourite = value;
    }

    public void setID(int id){mID = id;}

    public void setPath(String path){mPath = path;}
}
