package com.example.traveljournal;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveljournal.room.TripViewModel;

import java.util.Calendar;

public class NewTripActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    public static final String EXTRA_REPLY1 = "com.example.android.wordlistsql.REPLY1";
    public static final String EXTRA_REPLY2 = "com.example.android.wordlistsql.REPLY2";
    public static final String EXTRA_REPLY3 = "com.example.android.wordlistsql.REPLY3";
    public static final String EXTRA_REPLY4 = "com.example.android.wordlistsql.REPLY4";
    public static final String EXTRA_REPLY5 = "com.example.android.wordlistsql.REPLY5";
    public static final String EXTRA_REPLY6 = "com.example.android.wordlistsql.REPLY6";
    public static final String EXTRA_REPLY_ID = "com.example.android.wordlistsql.REPLY_ID";

    int yearStart, yearEnd, monthStart, monthEnd, dayStart, dayEnd;
    TextView textViewDateStart, textViewDateEnd;
    SeekBar seekBarPrice;
    private String price;
    ScrollView scrollViewAddTrip;
    Button buttonSave;
    ImageButton bookmarkButton;
    public boolean levelBookmark = false;

    private EditText mEditTripNameView;
    private EditText mEditDestinationNameView;
    private TextView mTextViewPrice;
    private RatingBar mRating;

    private TripViewModel mTripViewModel;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);

        seekBarPrice = (SeekBar) findViewById(R.id.seekBarPrice);
        mTextViewPrice = findViewById(R.id.textViewPriceValue);
        scrollViewAddTrip = findViewById(R.id.scrollViewAddTrip);
        mEditTripNameView = findViewById(R.id.editTextTripName);
        mEditDestinationNameView = findViewById(R.id.editTextDestination);
        mRating = findViewById(R.id.ratingBar);
        textViewDateStart = findViewById(R.id.textViewDateStart);
        textViewDateEnd = findViewById(R.id.textViewDateEnd);
        bookmarkButton = findViewById(R.id.bookmarkButton);
        buttonSave = findViewById(R.id.buttonSave);

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

        bookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBookmarkImage();
            }
        });

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
                    replyIntent.putExtra(EXTRA_REPLY6, levelBookmark);
                    replyIntent.putExtra(EXTRA_REPLY_ID, id);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });

        Intent intentReceived = getIntent();
        Bundle data = intentReceived.getExtras();
        if (data != null) {
            mEditTripNameView.setText(data.getString("TripName"));
            mEditDestinationNameView.setText(data.getString("Destination"));
            mTextViewPrice.setText(data.getString("Price"));
            try {
                seekBarPrice.setProgress(Integer.parseInt(data.getString("Price").replaceAll("[\\D]", "")));
            } catch (NumberFormatException error) {
                System.out.println("Eroare format: " + error);
            } catch (Exception exception) {
                System.out.println("Exceptie: " + exception);
            }
            mRating.setRating(data.getFloat("Rating"));
            textViewDateStart.setText(data.getString("StartDate"));
            textViewDateEnd.setText(data.getString("EndDate"));
            levelBookmark = data.getBoolean("Bookmark");
            id = data.getInt("ID");

            if (levelBookmark == false) {
                bookmarkButton.setImageLevel(0);
                levelBookmark = true;
            } else {
                bookmarkButton.setImageLevel(1);
                levelBookmark = false;
            }
            /*Trip trip = new Trip(data.getString("TripName"), data.getString("Destination"), data.getString("Price"),
                    data.getFloat("Rating"), data.getString("StartDate"), data.getString("EndDate"), data.getBoolean("Bookmark"));
            mTripViewModel.updateTrip(trip);*/
        }
    }

    private void setBookmarkImage() {
        if (levelBookmark == false) {
            bookmarkButton.setImageLevel(1);
            levelBookmark = true;
        } else {
            bookmarkButton.setImageLevel(0);
            levelBookmark = false;
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
                        yearStart = year;
                        monthStart = monthOfYear;
                        dayStart = dayOfMonth;
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

        Calendar minDate = Calendar.getInstance();
        minDate.set(yearStart, monthStart, dayStart);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        textViewDateEnd.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, yearStart, monthStart, dayStart);

        datePickerDialog.getDatePicker().setMinDate(minDate.getTimeInMillis());
        datePickerDialog.show();
    }

    public void changeBackgroundToCityBreak(View view) {
        scrollViewAddTrip.setBackgroundResource(R.drawable.citybreak_1);
    }

    public void changeBackgroundToSeaSide(View view) {
        scrollViewAddTrip.setBackgroundResource(R.drawable.seaside_2);
    }

    public void changeBackgroundToMountain(View view) {
        scrollViewAddTrip.setBackgroundResource(R.drawable.background_4);
    }
}