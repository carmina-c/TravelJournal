package com.example.traveljournal.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveljournal.R;
import com.example.traveljournal.trip_recycler_view.Trip;
import com.example.traveljournal.trip_recycler_view.TripAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private List<Trip> trips;
    private RecyclerView recyclerViewTrips;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerViewTrips = root.findViewById(R.id.recyclerViewTrips);
        displayTripsList();

        return root;
    }

    private void displayTripsList() {
        inbox();
        setTripsLayoutManager();
        setTripsAdapter();
    }

    private void inbox() {
        trips = new ArrayList<>();
        Trip trip = null;
        for (int i = 0; i < 25; i++) {
            trip = new Trip(0, "Trip Name", "Destination", "Price", null);
            trips.add(trip);
        }
    }

    private void setTripsLayoutManager() {
        recyclerViewTrips.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setTripsAdapter() {
        recyclerViewTrips.setAdapter(new TripAdapter(trips, getContext()));
    }

    /*private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }*/
}