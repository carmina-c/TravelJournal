package com.example.traveljournal.retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveljournal.R;
import com.example.traveljournal.retrofit.POJO.Example;

public class WeatherActivity extends AppCompatActivity {

    private WeatherRepository weatherRepository;
    private TextView cityTextView, temperatureTextView, temperatureFeelsLikeTextView, humidityTextView, pressureTexView;
    static String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        getSupportActionBar().setTitle("Weather");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cityTextView = findViewById(R.id.txt_city);
        temperatureTextView = findViewById(R.id.txt_temperature);
        temperatureFeelsLikeTextView = findViewById(R.id.txt_temperature_feels_like);
        humidityTextView = findViewById(R.id.txt_humidity);
        pressureTexView = findViewById(R.id.txt_press);

        Intent intentReceived = getIntent();
        Bundle data = intentReceived.getExtras();
        if (data != null) {
            city = data.getString("City");
        }

        weatherRepository = WeatherRepository.getInstance();

        getDataSource();
    }

    private void getDataSource() {
        weatherRepository.getWeatherInfos(new OnGetWeatherInfosCallback() {
            @Override
            public void onSuccess(Example weatherInfo) {
                /*Toast.makeText(WeatherActivity.this, weatherInfo.getMain().toString(),
                        Toast.LENGTH_LONG).show();*/
                cityTextView.setText("City: " + weatherInfo.getName());
                temperatureTextView.setText("Temperature: " + weatherInfo.getMain().getTemp() + "\u2103");
                temperatureFeelsLikeTextView.setText("Feels like: " + weatherInfo.getMain().getFeelsLike() + "\u2103");
                humidityTextView.setText("Humidity: " + weatherInfo.getMain().getHumidity());
                pressureTexView.setText("Pressure: " + weatherInfo.getMain().getPressure());

            }

            @Override
            public void onError() {
                Toast.makeText(WeatherActivity.this, R.string.error_getting_weather,
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}