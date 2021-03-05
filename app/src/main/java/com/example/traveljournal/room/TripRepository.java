package com.example.traveljournal.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TripRepository {
    private final TripDao mTripDao;
    private final LiveData<List<Trip>> mAllTrips;
    private final LiveData<List<Trip>> mFavoriteTrips;

    TripRepository(Application application) {
        TripRoomDatabase db = TripRoomDatabase.getDatabase(application);
        mTripDao = db.tripDao();
        mAllTrips = mTripDao.getAlphabetizedTrips();
        mFavoriteTrips = mTripDao.getFavoriteTrips();
    }

    LiveData<List<Trip>> getAllTrips() {
        return mAllTrips;
    }

    LiveData<List<Trip>> getFavoriteTrips() {
        return mFavoriteTrips;
    }

    void insert(Trip trip) {
        TripRoomDatabase.databaseWriteExecutor.execute(() -> {
            mTripDao.insert(trip);
        });
    }

    void deleteTrip(Trip trip) {
        TripRoomDatabase.databaseWriteExecutor.execute(() -> {
            mTripDao.deleteTrip(trip);
        });
    }

    void updateTrip(Trip trip) {
        TripRoomDatabase.databaseWriteExecutor.execute(() -> {
            mTripDao.updateTrip(trip);
        });
    }

    void updateTripForEdit(int mID, String mTrip, String mDestination, String mPrice, float mRating, String mStartDate, String mEndDate, boolean mIsFavourite) {
        TripRoomDatabase.databaseWriteExecutor.execute(() -> {
            mTripDao.updateForEdit(mID, mTrip, mDestination, mPrice, mRating, mStartDate, mEndDate, mIsFavourite);
        });
    }
}

