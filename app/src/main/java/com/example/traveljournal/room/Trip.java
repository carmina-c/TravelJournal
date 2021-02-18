package com.example.traveljournal.room;


import android.widget.RatingBar;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

@Entity(tableName = "trip_table")
public class Trip {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tripName")
    private final String mTrip;

    @ColumnInfo(name = "tripDestination")
    private final String mDestination;

    public Trip(@NonNull String mTrip, String mDestination) {
        this.mTrip = mTrip;
        this.mDestination = mDestination;
    }

    public String getTrip() {
        return this.mTrip;
    }

    public String getDestination() {
        return mDestination;
    }
}
