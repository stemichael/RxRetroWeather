package com.dmich.restfulretrofitrxjava.network;

import com.dmich.restfulretrofitrxjava.network.retrofit.WeatherService;
import com.dmich.restfulretrofitrxjava.weatherdata.WeatherData;

import io.reactivex.Single;
import retrofit2.Retrofit;

public class WeatherDataService {

    private Retrofit retrofit;

    public WeatherDataService(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public Single<WeatherData> getWeatherData(final String location) {
        WeatherService weatherService = retrofit.create(WeatherService.class);
        return weatherService.getWeatherData(location);
    }
}
