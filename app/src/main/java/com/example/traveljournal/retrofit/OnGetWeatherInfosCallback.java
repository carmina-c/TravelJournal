package com.example.traveljournal.retrofit;

import com.example.traveljournal.retrofit.POJO.Example;

public interface OnGetWeatherInfosCallback {
    void onSuccess(Example weatherInfos);
    void onError();
}
