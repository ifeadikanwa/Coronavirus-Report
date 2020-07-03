package com.example.coronavirusreport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.net.URI;
import java.util.ArrayList;

public class MoreInfoActivity extends AppCompatActivity {
    ArrayList<MoreInfo> moreInfoArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);

        ListView listView = findViewById(R.id.more_info_listView);

        moreInfoArr = new ArrayList<>();
        moreInfoArr.add(new MoreInfo("Coronavirus Symptoms", "https://www.cdc.gov/coronavirus/2019-ncov/symptoms-testing/symptoms.html"));
        moreInfoArr.add(new MoreInfo("Testing for Coronavirus","https://www.cdc.gov/coronavirus/2019-ncov/testing/index.html"));
        moreInfoArr.add(new MoreInfo("How You Can Prevent Getting Coronavirus","https://www.cdc.gov/coronavirus/2019-ncov/prevent-getting-sick/index.html"));
        moreInfoArr.add(new MoreInfo("How To Protect Yourself And Others","https://www.cdc.gov/coronavirus/2019-ncov/prevent-getting-sick/prevention.html"));
        moreInfoArr.add(new MoreInfo("If You Are Sick or Caring for Someone ","https://www.cdc.gov/coronavirus/2019-ncov/if-you-are-sick/index.html"));
        moreInfoArr.add(new MoreInfo("Daily Life and Coping","https://www.cdc.gov/coronavirus/2019-ncov/daily-life-coping/index.html"));
        moreInfoArr.add(new MoreInfo("People Who Need to Take Extra Precautions","https://www.cdc.gov/coronavirus/2019-ncov/need-extra-precautions/index.html"));
        moreInfoArr.add(new MoreInfo("Travel","https://www.cdc.gov/coronavirus/2019-ncov/travelers/index.html"));
        moreInfoArr.add(new MoreInfo("Pets and Others Animals","https://www.cdc.gov/coronavirus/2019-ncov/animals/pets-other-animals.html"));
        moreInfoArr.add(new MoreInfo("Centers for Disease Control and Prevention Answers Frequently Asked Questions on COVID-19","https://www.cdc.gov/coronavirus/2019-ncov/faq.html"));
        moreInfoArr.add(new MoreInfo("The World Health Organization Answers Frequently Asked Questions on COVID-19","https://www.who.int/emergencies/diseases/novel-coronavirus-2019/question-and-answers-hub"));

        MoreInfoAdapter arrayAdapter = new MoreInfoAdapter(this, 0 , moreInfoArr);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MoreInfo moreInfo = (MoreInfo)adapterView.getItemAtPosition(i);
                String url = moreInfo.getLink();
                Uri webpage = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }
                else{
                    Log.i("MoreInfoActivity", "Couldn't handle intent");
                }
            }
        });
    }


}