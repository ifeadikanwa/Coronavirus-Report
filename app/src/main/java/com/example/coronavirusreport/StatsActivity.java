package com.example.coronavirusreport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Loader;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class StatsActivity extends AppCompatActivity {

    TextView usaName;
    TextView usaCases;
    TextView usaRec;
    TextView usaDeath;
    TextView usaCritical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);


        usaName = findViewById(R.id.usa);
        usaCases = findViewById(R.id.usaTotal);
        usaRec = findViewById(R.id.usaRecovered);
        usaDeath = findViewById(R.id.usaDeaths);
        usaCritical = findViewById(R.id.usaCritical);

        StatsViewModel statsViewModel = new ViewModelProvider(this).get(StatsViewModel.class);
        statsViewModel.initialize();
        LiveData<Stats> worldLiveData = statsViewModel.getTheStats();
        worldLiveData.observe(this, new Observer<Stats>() {
            @Override
            public void onChanged(Stats stats) {
                Log.i("OnCreateMethod", "in the on onchanged method");
                if (stats != null) {

                    usaName.setText(stats.getPlace());
                    usaCases.setText(stats.getTotalCases());
                    usaRec.setText(stats.getRecovered());
                    usaDeath.setText(stats.getDeaths());
                    usaCritical.setText(stats.getCritical());
                }
            }
        });



    }

}


