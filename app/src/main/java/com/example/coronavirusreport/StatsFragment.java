package com.example.coronavirusreport;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DecimalFormat;

public class StatsFragment extends Fragment {

    private StatsViewModel statsViewModel;
    TextView usaName;
    TextView usaCases;
    TextView usaRec;
    TextView usaDeath;
    TextView usaCritical;
    ProgressBar loading;
    TextView emptyStateText;

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
        loading = view.findViewById(R.id.loading);
        usaName = view.findViewById(R.id.usa);
        usaCases = view.findViewById(R.id.usaTotal);
        usaRec = view.findViewById(R.id.usaRecovered);
        usaDeath = view.findViewById(R.id.usaDeaths);
        usaCritical = view.findViewById(R.id.usaCritical);
        emptyStateText = view.findViewById(R.id.empty_textview);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {

            statsViewModel = new ViewModelProvider(this).get(StatsViewModel.class);
            statsViewModel.initialize();
            LiveData<Stats> worldLiveData = statsViewModel.getTheStats();
            worldLiveData.observe(getViewLifecycleOwner(), new Observer<Stats>() {
                @Override
                public void onChanged(Stats stats) {
                    Log.i("OnCreateMethod", "in the on onchanged method");
                    if (stats != null) {

                        String number = "1000500000.574";
                        double amount = Double.parseDouble(number);
                        DecimalFormat formatter = new DecimalFormat("#,###");
                        String formatted = formatter.format(amount);

                        loading.setVisibility(View.GONE);
                        usaName.setText(stats.getPlace());

                        String cases = stats.getTotalCases();
                        double c = Double.parseDouble(cases);
                        String totalCases = formatter.format(c);
                        usaCases.setText(totalCases);

                        String rec = stats.getRecovered();
                        double r = Double.parseDouble(rec);
                        String recovered = formatter.format(r);
                        usaRec.setText(recovered);

                        String death = stats.getDeaths();
                        double d = Double.parseDouble(death);
                        String totalDeath = formatter.format(d);
                        usaDeath.setText(totalDeath);

                        String crit = stats.getCritical();
                        double cr = Double.parseDouble(crit);
                        String critical = formatter.format(cr);
                        usaCritical.setText(critical);
                    }
                }
            });

        }
        else {
            loading.setVisibility(View.GONE);
            emptyStateText.setText("No Internet Connection");
        }


    }

}