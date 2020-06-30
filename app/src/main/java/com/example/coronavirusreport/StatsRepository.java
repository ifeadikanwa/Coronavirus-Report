package com.example.coronavirusreport;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class StatsRepository {
    final String TAG = this.getClass().getSimpleName();
    public static final String WORLD_URL = "https://covid-19-data.p.rapidapi.com/totals?format=json";
    public static final String USA_URL = "https://covid-19-data.p.rapidapi.com/country?format=json&name=usa";
    private OkHttpClient client = new OkHttpClient();
    private MutableLiveData<Stats> statsLiveData = new MutableLiveData<>();
    private LiveData<Stats> finalData = statsLiveData;
    private static StatsRepository instance;



    private StatsRepository() {
    }


    public static StatsRepository getInstance() {
        if (instance == null) {
            instance = new StatsRepository();
        }
        return instance;
    }


    public LiveData<Stats> getStats() {
        Log.i(TAG, "in the getStats");

        loadStats(USA_URL);

        return finalData;
    }



    public void loadStats(String url) {
        Log.i(TAG, "in the setStats");

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-host", "covid-19-data.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "888c012920msh3b306e7cac1899ap11c722jsnd21797080252")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.i(TAG, "in the WORLD onResponse");

                if (response.isSuccessful()) {
                    String jsonStr = response.body().string();
                    Log.i(TAG, jsonStr);
                    try {
                        JSONArray jsonArray = new JSONArray(jsonStr);
                        Log.i(TAG + "array", jsonArray.toString());

                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        String totalCases = jsonObject.getString("confirmed");

                        String recovered = jsonObject.getString("recovered");

                        String deaths = jsonObject.getString("deaths");

                        String place = jsonObject.getString("country");

                        String critical = jsonObject.getString("critical");

                        Stats theStats = new Stats(place, totalCases, recovered, deaths, critical);
                        statsLiveData.postValue(theStats);
                        Log.i(TAG, "set the stats value");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                else{
                    throw new IOException("Unexpected code " + response);
                }

            }
        });
    }



}


