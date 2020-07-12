package com.example.coronavirusreport;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNav);

        bottomNavigationView.setOnNavigationItemSelectedListener(listener);


        Fragment theDefault = new StatsFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, theDefault).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selected = null;

            switch (item.getItemId()){
                case R.id.stats:
                    selected = new StatsFragment();
                    break;
                case R.id.news:
                    selected = new NewsFragment();
                    break;
                case R.id.info:
                    selected = new MoreInfoFragment();
                    break;
                case R.id.tribute:
                    selected = new TributeFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selected).commit();

            return true;
        }
    };
}
