package com.example.coronavirusreport;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

import pl.droidsonroids.gif.GifImageView;

public class TributeActivity extends AppCompatActivity {
    Boolean click = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tribute);


        final GifImageView candleImage = findViewById(R.id.candleImageView);
        final Button candleButton = findViewById(R.id.candle_button);
        final TextView candleText = findViewById(R.id.candle_textView);

        candleImage.setVisibility(View.INVISIBLE);

        candleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!click){
                    click = true;
                    candleText.setVisibility(View.INVISIBLE);
                    candleButton.setVisibility(View.INVISIBLE);
                    candleImage.setVisibility(View.VISIBLE);
                }

            }
        });
    }
}