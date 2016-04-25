package com.bdjobs.retrofittest.data;

import com.bdjobs.retrofittest.model.Weather;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by Rubayet on 24-Apr-16.
 */
public interface WeatherAPI {

    String baseURL = "https://query.yahooapis.com/v1/public/";

    @GET("yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22dhaka%2Cbd%22)%20and%20u%3D'c'&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys")
    Call<Weather> getWeather();

    class Factory {
        public static WeatherAPI weatherAPI;

        public static WeatherAPI getInstance() {
            if (weatherAPI == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(baseURL)
                        .build();

                weatherAPI = retrofit.create(WeatherAPI.class);
                return weatherAPI;
            } else {
                return weatherAPI;
            }
        }
    }


}
