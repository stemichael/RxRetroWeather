package com.dmich.restfulretrofitrxjava.network.retrofit;

import com.dmich.restfulretrofitrxjava.weatherdata.WeatherData;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("weather?")
    Single<WeatherData> getWeatherData(@Query("q") String city);
}
