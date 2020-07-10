package com.example.coronavirusreport;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class StatsFragment extends Fragment {

    private StatsViewModel statsViewModel;
    TextView usaName;
    TextView usaCases;
    TextView usaRec;
    TextView usaDeath;
    TextView usaCritical;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public StatsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatsFragment newInstance(String param1, String param2) {
        StatsFragment fragment = new StatsFragment();
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stats_fragment, container, false);
        usaName = view.findViewById(R.id.usa);
        usaCases = view.findViewById(R.id.usaTotal);
        usaRec = view.findViewById(R.id.usaRecovered);
        usaDeath = view.findViewById(R.id.usaDeaths);
        usaCritical = view.findViewById(R.id.usaCritical);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        statsViewModel = new ViewModelProvider(this).get(StatsViewModel.class);
        statsViewModel.initialize();
        LiveData<Stats> worldLiveData = statsViewModel.getTheStats();
        worldLiveData.observe(getViewLifecycleOwner(), new Observer<Stats>() {
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