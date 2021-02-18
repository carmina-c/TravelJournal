package com.example.traveljournal.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.traveljournal.R;
import com.example.traveljournal.store_data.ApplicationData;

import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends AppCompatActivity {

    private static final String WEATHER_TOKEN = "";
    private static final String VERSION_API = "VERSION_API";

    private WeatherRepository weatherRepository;

    private List<Weather> weatherInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        weatherRepository = WeatherRepository.getInstance();
        weatherInfos = new ArrayList<>();

        ApplicationData.setIntValueInSharedPreferences(WeatherActivity.this, VERSION_API, 1);
        int savedVersion = ApplicationData.getIntValueFromSharedPreferences(WeatherActivity.this, VERSION_API);

        getDataSource();
    }

    private void getDataSource() {
        weatherRepository.getWeatherInfos(new OnGetWeatherInfosCallback() {
            @Override
            public void onSuccess(List<Weather> weatherListFromApi) {
                weatherInfos = weatherListFromApi;
                StringBuilder stringBuilder = new StringBuilder();
                for (Weather weatherInfo : weatherListFromApi) {
                    stringBuilder.append(weatherInfo.toString() + " /// ");
                }
                Toast.makeText(WeatherActivity.this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError() {
                Toast.makeText(WeatherActivity.this, R.string.error_getting_weather,
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}