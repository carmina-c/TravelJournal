package com.example.traveljournal.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TripViewModel extends AndroidViewModel {
    private TripRepository mRepository;

    private LiveData<List<Trip>> mAllTrips;

    public TripViewModel (Application application) {
        super(application);
        mRepository = new TripRepository(application);
        mAllTrips = mRepository.getAllTrips();
    }

    LiveData<List<Trip>> getAllTrips() { return mAllTrips; }

    public void insert(Trip trip) { mRepository.insert(trip); }

    public void deleteTrip(Trip trip) {mRepository.deleteTrip(trip);}
}