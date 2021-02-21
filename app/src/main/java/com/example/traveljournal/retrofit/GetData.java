package com.example.traveljournal.retrofit;

import com.example.traveljournal.retrofit.POJO.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetData {
    @GET("/data/2.5/weather")
    Call<Example> getAllWeatherInfos(@Query("q") String city, @Query("appid") String app_id, @Query("units") String units);
}