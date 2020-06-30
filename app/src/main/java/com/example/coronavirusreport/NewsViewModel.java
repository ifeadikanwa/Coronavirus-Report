package com.example.coronavirusreport;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class NewsViewModel extends ViewModel {

    private NewsRepository newsRepository;
    private LiveData<List<News>> newsLiveData;

    public void initialize(){
        newsRepository = NewsRepository.getInstance();
        newsLiveData = newsRepository.getNewsList();
    }

    public LiveData<List<News>> getNewsLiveData(){
        return newsLiveData;
    }
}
