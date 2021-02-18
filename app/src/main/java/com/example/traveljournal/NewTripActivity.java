package com.example.traveljournal;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class NewTripActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    public static final String EXTRA_REPLY1 = "com.example.android.wordlistsql.REPLY1";

    int yearStart, yearEnd, monthStart, monthEnd, dayStart, dayEnd;
    TextView textViewDateStart, textViewDateEnd, textViewPriceValue;
    SeekBar seekBarPrice;
    int price;
    ScrollView scrollViewAddTrip;
    Button buttonSave;

    private EditText mEditTripNameView;
    private EditText mEditDestinationNameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);

        seekBarPrice = (SeekBar) findViewById(R.id.seekBarPrice);
        textViewPriceValue = findViewById(R.id.textViewPriceValue);
        seekBarPrice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewPriceValue.setText(progress + "/" + seekBar.getMax());
                price = progress;
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

        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditTripNameView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String trip = mEditTripNameView.getText().toString();
                    String destination = mEditDestinationNameView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, trip);
                    replyIntent.putExtra(EXTRA_REPLY1, destination);
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
        }
    }

    public void openDatePickerStartDate(View view) {
        final Calendar c = Calendar.getInstance();
        yearStart = c.get(Calendar.YEAR);
        monthStart = c.get(Calendar.MONTH);
        dayStart = c.get(Calendar.DAY_OF_MONTH);
        textViewDateStart = findViewById(R.id.textViewDateStart);

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
        textViewDateEnd = findViewById(R.id.textViewDateEnd);

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