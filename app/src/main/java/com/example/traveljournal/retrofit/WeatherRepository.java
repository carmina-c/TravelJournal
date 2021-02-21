package com.example.traveljournal.retrofit;

import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.traveljournal.retrofit.POJO.Example;
import com.example.traveljournal.retrofit.POJO.Weather;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherRepository {
    private static WeatherRepository weatherRepository;

    private final GetData getData;

    final static String BASE_URL = "https://api.openweathermap.org/";
    public static String appID = "635de0ffe609758867df0c2955c0c1e3";
    private final String city = "London,uk";
    private final String units = "metric";

    private WeatherRepository(GetData data) {
        this.getData = data;
    }

    public static WeatherRepository getInstance() {
        if (weatherRepository == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            weatherRepository = new WeatherRepository(retrofit.create(GetData.class));
        }

        return weatherRepository;
    }

    public void getWeatherInfos(final OnGetWeatherInfosCallback callback) {
        getData.getAllWeatherInfos(city, appID, units)
                .enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> call, @NonNull Response<Example> response) {
                        if (response.isSuccessful()) {
                            Example weatherInfos = response.body();
                            if (weatherInfos != null) {
                                callback.onSuccess(weatherInfos);
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {
                        Log.println(Log.ERROR, "EROARE GET", t.getMessage());
                        callback.onError();
                    }
                });
    }
}
