package com.example.traveljournal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveljournal.room.Trip;

public class TripDetailsActivity extends AppCompatActivity {

    public static final int NEW_TRIP_ACTIVITY_REQUEST_CODE = 1;

    private TextView tripNameDetails;
    private TextView destinationDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);

        tripNameDetails = findViewById(R.id.tripNameDetails);
        destinationDetails = findViewById(R.id.destinationDetails);

        Intent intentReceived = getIntent();
        Bundle data = intentReceived.getExtras();
        if(data != null) {
            tripNameDetails.setText(data.getString("TripName"));
            destinationDetails.setText(data.getString("Destination"));
        }
        getSupportActionBar().setTitle("Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}