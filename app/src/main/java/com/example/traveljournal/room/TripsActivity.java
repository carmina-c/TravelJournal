package com.example.traveljournal.room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveljournal.NewTripActivity;
import com.example.traveljournal.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TripsActivity extends AppCompatActivity {

    public static final int NEW_TRIP_ACTIVITY_REQUEST_CODE = 1;
    public static final int EDIT_TRIP_ACTIVITY_REQUEST_CODE = 2;

    private TripViewModel mTripViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);

        getSupportActionBar().setTitle("Trips");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                new AlertDialog.Builder(TripsActivity.this)
                        .setTitle("Confirmation")
                        .setMessage("Do you really want to delete this trip?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                Toast.makeText(TripsActivity.this, "The trip was deleted", Toast.LENGTH_SHORT).show();
                                int position = viewHolder.getAdapterPosition();
                                Trip trip = mTripViewModel.getAllTrips().getValue().get(position);
                                mTripViewModel.getAllTrips().getValue().remove(position);
                                adapter.notifyDataSetChanged();
                                try {
                                    mTripViewModel.deleteTrip(trip);
                                } catch (Exception exception) {

                                }
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                adapter.notifyDataSetChanged();
                            }
                        }).show();
            }
        };

        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            String tripName = data.getStringExtra(NewTripActivity.EXTRA_REPLY);
            String destination = data.getStringExtra(NewTripActivity.EXTRA_REPLY1);
            String price = data.getStringExtra(NewTripActivity.EXTRA_REPLY2);
            float rating = data.getFloatExtra(NewTripActivity.EXTRA_REPLY3, 0);
            String mStartDate = data.getStringExtra(NewTripActivity.EXTRA_REPLY4);
            String mEndDate = data.getStringExtra(NewTripActivity.EXTRA_REPLY5);
            boolean isFav = data.getBooleanExtra(NewTripActivity.EXTRA_REPLY6, false);
            if (requestCode == NEW_TRIP_ACTIVITY_REQUEST_CODE) {
                Trip trip = new Trip(tripName, destination, price, rating, mStartDate, mEndDate, isFav);
                mTripViewModel.insert(trip);
            } else if (requestCode == EDIT_TRIP_ACTIVITY_REQUEST_CODE) {
                int id = data.getIntExtra(NewTripActivity.EXTRA_REPLY_ID, 5);
                mTripViewModel.updateTripForEdit(id, tripName, destination, price, rating, mStartDate, mEndDate, isFav);
            }
        } else if (requestCode == NEW_TRIP_ACTIVITY_REQUEST_CODE && resultCode != RESULT_OK) {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }


    }
}