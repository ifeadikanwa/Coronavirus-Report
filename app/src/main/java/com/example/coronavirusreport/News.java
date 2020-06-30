package com.example.coronavirusreport;

import androidx.annotation.NonNull;

public class News {
    private String title;
    private String newsUrl;
    private String imageUrl;

    public News(String title, String newsUrl, String imageUrl){
        this.title = title;
        this.newsUrl = newsUrl;
        this.imageUrl = imageUrl;
    }

    @NonNull
    @Override
    public String toString() {
        return title + " " + newsUrl + " "+ imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
