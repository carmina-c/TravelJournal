package com.example.traveljournal;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class NewTripActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    public static final String EXTRA_REPLY1 = "com.example.android.wordlistsql.REPLY1";
    public static final String EXTRA_REPLY2 = "com.example.android.wordlistsql.REPLY2";
    public static final String EXTRA_REPLY3 = "com.example.android.wordlistsql.REPLY3";
    public static final String EXTRA_REPLY4 = "com.example.android.wordlistsql.REPLY4";
    public static final String EXTRA_REPLY5 = "com.example.android.wordlistsql.REPLY5";

    int yearStart, yearEnd, monthStart, monthEnd, dayStart, dayEnd;
    TextView textViewDateStart, textViewDateEnd;
    SeekBar seekBarPrice;
    private String price;
    ScrollView scrollViewAddTrip;
    Button buttonSave;

    private EditText mEditTripNameView;
    private EditText mEditDestinationNameView;
    private TextView mTextViewPrice;
    private RatingBar mRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);

        seekBarPrice = (SeekBar) findViewById(R.id.seekBarPrice);
        mTextViewPrice = findViewById(R.id.textViewPriceValue);
        seekBarPrice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTextViewPrice.setText(progress + " EUR");
                price = String.valueOf(progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //write custom code to on start progress
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        getSupportActionBar().setTitle("Add/Edit Trip");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        scrollViewAddTrip = findViewById(R.id.scrollViewAddTrip);
        mEditTripNameView = findViewById(R.id.editTextTripName);
        mEditDestinationNameView = findViewById(R.id.editTextDestination);
        mRating = findViewById(R.id.ratingBar);

        textViewDateStart = findViewById(R.id.textViewDateStart);
        textViewDateEnd = findViewById(R.id.textViewDateEnd);

        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditTripNameView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String trip = mEditTripNameView.getText().toString();
                    String destination = mEditDestinationNameView.getText().toString();
                    float rating = mRating.getRating();
                    replyIntent.putExtra(EXTRA_REPLY, trip);
                    replyIntent.putExtra(EXTRA_REPLY1, destination);
                    replyIntent.putExtra(EXTRA_REPLY2, price);
                    replyIntent.putExtra(EXTRA_REPLY3, rating);
                    replyIntent.putExtra(EXTRA_REPLY4, textViewDateStart.getText());
                    replyIntent.putExtra(EXTRA_REPLY5, textViewDateEnd.getText());
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });

        Intent intentReceived = getIntent();
        Bundle data = intentReceived.getExtras();
        if(data != null) {
            mEditTripNameView.setText(data.getString("TripName"));
            mEditDestinationNameView.setText(data.getString("Destination"));
            mTextViewPrice.setText(data.getString("Price"));
            mRating.setRating(data.getFloat("Rating"));
            textViewDateStart.setText(data.getString("StartDate"));
            textViewDateEnd.setText(data.getString("EndDate"));
        }
    }

    public void openDatePickerStartDate(View view) {
        final Calendar c = Calendar.getInstance();
        yearStart = c.get(Calendar.YEAR);
        monthStart = c.get(Calendar.MONTH);
        dayStart = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        textViewDateStart.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, yearStart, monthStart, dayStart);
        datePickerDialog.show();
    }

    public void openDatePickerEndDate(View view) {
        final Calendar c = Calendar.getInstance();
        yearEnd = c.get(Calendar.YEAR);
        monthEnd = c.get(Calendar.MONTH);
        dayEnd = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        textViewDateEnd.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, yearEnd, monthEnd, dayEnd);
        datePickerDialog.show();
    }


    public void changeBackgroundToCityBreak(View view) {
        scrollViewAddTrip.setBackgroundResource(R.drawable.citybreak_1);
    }

    public void changeBackgroundToSeaSide(View view) {
        scrollViewAddTrip.setBackgroundResource(R.drawable.seaside_2);
    }

    public void changeBackgroundToMountain(View view) {
        scrollViewAddTrip.setBackgroundResource(R.drawable.mountain_3);
    }
}