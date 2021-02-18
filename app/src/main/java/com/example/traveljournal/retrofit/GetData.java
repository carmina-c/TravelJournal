package com.example.traveljournal.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetData {
    @GET("data/2.5/weather?")
    Call<List<Weather>> getAllWeatherInfos(@Query("lat") String lat, @Query("lon") String lon, @Query("APPID") String app_id);
}
