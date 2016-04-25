package com.bdjobs.retrofittest;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.bdjobs.retrofittest.data.WeatherAPI;
import com.bdjobs.retrofittest.model.Forecast;
import com.bdjobs.retrofittest.model.Weather;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ProgressDialog mProgressDialog;
    List<Forecast> forecast;
    String temp, location, wind, humidity, rain, d1, d2, d3, d4, t1, t2, t3, t4, formattedDate;
    TextView temparetureTV, locationTV, dateTV, windTV, rainTV, humidityTV, day1TV, day2TV, day3TV, day4TV, temp1TV, temp2TV, temp3TV, temp4TV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializer();
        setRoboto();
        setProgressbar();
        callWeatherData();


    }

    private void callWeatherData() {
        WeatherAPI.Factory.getInstance().getWeather().enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                temp = response.body().getQuery().getResults().getChannel().getItem().getCondition().getTemp();
                location = response.body().getQuery().getResults().getChannel().getLocation().getCity();
                wind = response.body().getQuery().getResults().getChannel().getWind().getSpeed();
                humidity = response.body().getQuery().getResults().getChannel().getAtmosphere().getHumidity();
                forecast = response.body().getQuery().getResults().getChannel().getItem().getForecast();
                rain = response.body().getQuery().getResults().getChannel().getAtmosphere().getRising();
                settingData();

                mProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setProgressbar() {
        mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setTitle("Please Wait!");
        mProgressDialog.setMessage("Loading Weather Data....");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.show();
        dateTV.setText(formattedDate);
    }

    private void settingData() {

        d1 = forecast.get(0).getDay();
        d2 = forecast.get(1).getDay();
        d3 = forecast.get(2).getDay();
        d4 = forecast.get(3).getDay();

        t1 = forecast.get(0).getHigh();
        t2 = forecast.get(1).getHigh();
        t3 = forecast.get(2).getHigh();
        t4 = forecast.get(3).getHigh();

        temparetureTV.setText(temp + (char) 0x00B0);
        locationTV.setText(location);
        windTV.setText(wind + "mph");
        humidityTV.setText("%" + humidity);
        day1TV.setText(d1);
        day2TV.setText(d2);
        day3TV.setText(d3);
        day4TV.setText(d4);
        temp1TV.setText(t1 + (char) 0x00B0);
        temp2TV.setText(t2 + (char) 0x00B0);
        temp3TV.setText(t3 + (char) 0x00B0);
        temp4TV.setText(t4 + (char) 0x00B0);
        rainTV.setText("%"+rain);
    }

    private void initializer() {
        temparetureTV = (TextView) findViewById(R.id.textView);
        locationTV = (TextView) findViewById(R.id.locationTV);
        dateTV = (TextView) findViewById(R.id.dateTV);
        windTV = (TextView) findViewById(R.id.windTV);
        rainTV = (TextView) findViewById(R.id.rainTV);
        humidityTV = (TextView) findViewById(R.id.humidityTV);
        day1TV = (TextView) findViewById(R.id.day1TV);
        day2TV = (TextView) findViewById(R.id.day2TV);
        day3TV = (TextView) findViewById(R.id.day3TV);
        day4TV = (TextView) findViewById(R.id.day4TV);
        temp1TV = (TextView) findViewById(R.id.temp1TV);
        temp2TV = (TextView) findViewById(R.id.temp2TV);
        temp3TV = (TextView) findViewById(R.id.temp3TV);
        temp4TV = (TextView) findViewById(R.id.temp4TV);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        formattedDate = df.format(c.getTime());
    }

    private void setRoboto() {
        Typeface roboto = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "font/Roboto-Thin.ttf"); //use this.getAssets if you are calling from an Activity
        temparetureTV.setTypeface(roboto);
        locationTV.setTypeface(roboto);
        dateTV.setTypeface(roboto);
        windTV.setTypeface(roboto);
        rainTV.setTypeface(roboto);
        humidityTV.setTypeface(roboto);
        day1TV.setTypeface(roboto);
        day2TV.setTypeface(roboto);
        day3TV.setTypeface(roboto);
        day4TV.setTypeface(roboto);
        temp1TV.setTypeface(roboto);
        temp2TV.setTypeface(roboto);
        temp3TV.setTypeface(roboto);
        temp4TV.setTypeface(roboto);
    }
}
