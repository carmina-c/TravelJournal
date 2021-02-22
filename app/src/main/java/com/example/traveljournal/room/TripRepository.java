package com.example.traveljournal.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TripRepository {
    private TripDao mTripDao;
    private LiveData<List<Trip>> mAllTrips;

    TripRepository(Application application) {
        TripRoomDatabase db = TripRoomDatabase.getDatabase(application);
        mTripDao = db.tripDao();
        mAllTrips = mTripDao.getAlphabetizedTrips();
    }

    LiveData<List<Trip>> getAllTrips() {
        return mAllTrips;
    }

    void insert(Trip trip) {
        TripRoomDatabase.databaseWriteExecutor.execute(() -> {
            mTripDao.insert(trip);
        });
    }
}

