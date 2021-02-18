package com.example.traveljournal.retrofit;

import java.util.List;

public interface OnGetWeatherInfosCallback {
    void onSuccess(List<Weather> weatherInfos);
    void onError();
}
