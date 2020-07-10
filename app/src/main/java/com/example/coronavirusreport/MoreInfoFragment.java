package com.example.coronavirusreport;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoreInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoreInfoFragment extends Fragment {
    ArrayList<MoreInfo> moreInfoArr;
    ListView listView;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MoreInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoreInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoreInfoFragment newInstance(String param1, String param2) {
        MoreInfoFragment fragment = new MoreInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.more_info_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.more_info_listView);

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

        MoreInfoAdapter arrayAdapter = new MoreInfoAdapter(getContext(), 0 , moreInfoArr);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MoreInfo moreInfo = (MoreInfo)adapterView.getItemAtPosition(i);
                String url = moreInfo.getLink();
                Uri webpage = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if(intent.resolveActivity(getActivity().getPackageManager()) != null){
                    startActivity(intent);
                }
                else{
                    Log.i("MoreInfoActivity", "Couldn't handle intent");
                }
            }
        });
    }
}