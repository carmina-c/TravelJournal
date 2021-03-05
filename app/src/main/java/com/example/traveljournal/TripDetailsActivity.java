package com.example.traveljournal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveljournal.retrofit.WeatherActivity;

public class TripDetailsActivity extends AppCompatActivity {

    private TextView tripNameDetails;
    private TextView destinationDetails;
    private RatingBar ratingBarDetails;
    private TextView startDate;
    private TextView endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);

        tripNameDetails = findViewById(R.id.tripNameDetails);
        destinationDetails = findViewById(R.id.destinationDetails);
        ratingBarDetails = findViewById(R.id.ratingBarDetails);
        startDate = findViewById(R.id.startDateDetails);
        endDate = findViewById(R.id.endDateDetails);

        getSupportActionBar().setTitle("Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intentReceived = getIntent();
        Bundle data = intentReceived.getExtras();
        if (data != null) {
            tripNameDetails.setText(data.getString("TripName"));
            destinationDetails.setText(data.getString("Destination"));
            ratingBarDetails.setRating(data.getFloat("Rating"));
            startDate.setText(data.getString("StartDate"));
            endDate.setText(data.getString("EndDate"));
        }
    }

    public void openWeatherDetailsOnClick(View view) {
        Intent intent = new Intent(this, WeatherActivity.class);
        Bundle data = new Bundle();
        data.putString("City", destinationDetails.getText().toString());
        intent.putExtras(data);
        startActivity(intent);
    }
}