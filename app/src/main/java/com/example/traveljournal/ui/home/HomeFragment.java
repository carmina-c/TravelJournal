package com.example.traveljournal.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveljournal.R;
import com.example.traveljournal.room.Trip;
import com.example.traveljournal.room.TripsActivity;

import java.util.List;

public class HomeFragment extends Fragment {

    private List<Trip> trips;
    private RecyclerView recyclerViewTrips;
    private Button openTripsActivityButton, openWeatherActivityButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerViewTrips = root.findViewById(R.id.recyclerViewTrips);
        openTripsActivityButton = root.findViewById(R.id.openTripListActivityButton);

        openTripsActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TripsActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }
}