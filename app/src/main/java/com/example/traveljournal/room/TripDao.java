package com.example.traveljournal.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TripDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Trip trip);

    @Query("DELETE FROM trip_table")
    void deleteAll();

    @Query("DELETE FROM trip_table WHERE tripName = :tripN")
    void deleteTrip(String tripN);

    @Query("SELECT * from trip_table ORDER BY tripName ASC")
    LiveData<List<Trip>> getAlphabetizedTrips();
}