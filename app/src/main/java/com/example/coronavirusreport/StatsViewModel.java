package com.example.coronavirusreport;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class StatsViewModel extends ViewModel {
    final String TAG  = this.getClass().getSimpleName();
    private LiveData<Stats> theStats;
    private StatsRepository repository;

    public void initialize(){
        if(repository == null){
            this.repository = StatsRepository.getInstance();
            theStats = repository.getStats();

        }
    }

    LiveData<Stats> getTheStats(){
        return theStats;
    }



}
