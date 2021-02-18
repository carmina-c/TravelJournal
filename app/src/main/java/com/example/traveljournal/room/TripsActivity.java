package com.example.traveljournal.room;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.traveljournal.NewTripActivity;
import com.example.traveljournal.R;
import com.example.traveljournal.TripDetailsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TripsActivity extends AppCompatActivity {

    public static final int NEW_TRIP_ACTIVITY_REQUEST_CODE = 1;

    private TripViewModel mTripViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewTrips);
        final TripListAdapter adapter = new TripListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mTripViewModel = new ViewModelProvider(this).get(TripViewModel.class);

        mTripViewModel.getAllTrips().observe(this, new Observer<List<Trip>>() {
            @Override
            public void onChanged(@Nullable final List<Trip> trips) {
                adapter.setTrips(trips);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TripsActivity.this, NewTripActivity.class);
                startActivityForResult(intent, NEW_TRIP_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_TRIP_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String tripName = data.getStringExtra(NewTripActivity.EXTRA_REPLY);
            String destination = data.getStringExtra(NewTripActivity.EXTRA_REPLY1);
            Trip trip = new Trip(tripName,destination);
            mTripViewModel.insert(trip);

        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}