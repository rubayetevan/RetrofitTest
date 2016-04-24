package com.bdjobs.retrofittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.bdjobs.retrofittest.data.WeatherAPI;
import com.bdjobs.retrofittest.model.Weather;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    String temp;
    TextView temparetureTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        temparetureTV= (TextView) findViewById(R.id.textView);

        WeatherAPI.Factory.getInstance().getWeather().enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                temp=response.body().getQuery().getResults().getChannel().getItem().getCondition().getTemp();
                //Toast.makeText(MainActivity.this,temp,Toast.LENGTH_SHORT).show();
                temparetureTV.setText(temp);
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_SHORT).show();

            }
        });

    }
}
