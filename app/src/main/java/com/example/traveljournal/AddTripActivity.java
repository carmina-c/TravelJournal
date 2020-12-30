package com.example.traveljournal;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Calendar;

public class AddTripActivity extends AppCompatActivity {

    int yearStart, yearEnd, monthStart, monthEnd, dayStart, dayEnd;
    TextView textViewDateStart, textViewDateEnd, textViewPriceValue;
    SeekBar seekBar;
    ScrollView scrollViewAddTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);

        seekBar = (SeekBar) findViewById(R.id.seekBarPrice);
        textViewPriceValue = findViewById(R.id.textViewPriceValue);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewPriceValue.setText(progress + "/" + seekBar.getMax());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //write custom code to on start progress
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        scrollViewAddTrip = findViewById(R.id.scrollViewAddTrip);
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
        scrollViewAddTrip.setBackgroundResource(R.drawable.citybreak_2);
    }

    public void changeBackgroundToSeaSide(View view) {
        scrollViewAddTrip.setBackgroundResource(R.drawable.seaside_2);
    }

    public void changeBackgroundToMountain(View view) {
        scrollViewAddTrip.setBackgroundResource(R.drawable.mountain_2);
    }
}