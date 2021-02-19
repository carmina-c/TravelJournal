package com.example.traveljournal.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherRepository {
    private static WeatherRepository weatherRepository;
    private GetData getData;
    public static String lat = "35";
    public static String lon = "139";
    public static String AppId = "635de0ffe609758867df0c2955c0c1e3";

    private WeatherRepository(GetData data){
        this.getData = data;
    }

    public static WeatherRepository getInstance(){
        if(weatherRepository == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            weatherRepository = new WeatherRepository(retrofit.create(GetData.class));
        }

        return weatherRepository;
    }

    public void getWeatherInfos(final OnGetWeatherInfosCallback callback) {
        getData.getAllWeatherInfos(lat, lon, AppId)
                .enqueue(new Callback<List<Weather>>() {
                    @Override
                    public void onResponse(Call<List<Weather>> call, Response<List<Weather>> response) {
                        if(response.isSuccessful()){
                            List<Weather> weatherInfos = response.body();
                            if(weatherInfos != null) {
                                callback.onSuccess(weatherInfos);
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Weather>> call, Throwable t) {
                        callback.onError();
                    }
                });
    }
}
