package com.example.traveljournal.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TripDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Trip trip);

    @Query("DELETE FROM trip_table")
    void deleteAll();

    @Delete
    void deleteTrip(Trip trip);

    @Update
    void updateTrip(Trip trip);

    @Query("UPDATE trip_table SET " +
            "tripName = :mTrip, " +
            "tripDestination = :mDestination, " +
            "tripPrice = :mPrice, " +
            "tripRating = :mRating, " +
            "tripStartDate = :mStartDate, " +
            "tripEndDate = :mEndDate, " +
            "isFavourite = :mIsFavourite " +
            "WHERE tripID = :mID")
    int updateForEdit(int mID, String mTrip, String mDestination, String mPrice, float mRating,  String mStartDate, String mEndDate, boolean mIsFavourite);

    @Query("SELECT * from trip_table ORDER BY tripName ASC")
    LiveData<List<Trip>> getAlphabetizedTrips();
}