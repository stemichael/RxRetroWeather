package com.dmich.restfulretrofitrxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dmich.restfulretrofitrxjava.adapter.RecyclerViewAdapter;
import com.dmich.restfulretrofitrxjava.network.WeatherDataService;
import com.dmich.restfulretrofitrxjava.network.retrofit.ApiClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private Disposable subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        final WeatherDataService weatherDataService = new WeatherDataService(new ApiClient().getInstance());

        subscription = weatherDataService.getWeatherData("London")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weatherData -> {
                    mAdapter = new RecyclerViewAdapter(weatherData.getWeather());
                    mRecyclerView.setAdapter(mAdapter);
                });
    }

    @Override
    protected void onDestroy() {
        if (subscription != null && !subscription.isDisposed()) {
            subscription.dispose();
        }

        super.onDestroy();
    }
}
