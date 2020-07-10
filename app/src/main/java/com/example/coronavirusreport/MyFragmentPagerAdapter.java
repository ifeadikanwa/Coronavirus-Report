package com.example.coronavirusreport;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyFragmentPagerAdapter extends FragmentStateAdapter {

    public MyFragmentPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    String[] tabTitles = new String[]{"STATS"};


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
        case 0:
            return new StatsFragment();
        default:
            return null;
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
