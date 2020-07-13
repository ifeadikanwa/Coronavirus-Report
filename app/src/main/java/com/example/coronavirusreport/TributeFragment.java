package com.example.coronavirusreport;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TributeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TributeFragment extends Fragment {
    Boolean click = false;
    View.OnClickListener onClickListener;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TributeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TributeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TributeFragment newInstance(String param1, String param2) {
        TributeFragment fragment = new TributeFragment();
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
        return inflater.inflate(R.layout.tribute_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final GifImageView candleImage = view.findViewById(R.id.candleImageView);
        final Button candleButton = view.findViewById(R.id.candle_button);
        final TextView candleText = view.findViewById(R.id.candle_textView);
        final ConstraintLayout main = view.findViewById(R.id.mainLayout);

        candleImage.setVisibility(View.INVISIBLE);

        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!click){
                    click = true;
                    candleText.setVisibility(View.INVISIBLE);
                    candleButton.setVisibility(View.INVISIBLE);
                    main.setBackgroundColor(getResources().getColor(R.color.black));
                    candleImage.setVisibility(View.VISIBLE);
                }
            }
        };

        candleButton.setOnClickListener(onClickListener);
    }

}