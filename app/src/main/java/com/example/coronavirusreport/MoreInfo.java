package com.example.coronavirusreport;

public class MoreInfo {
    private String title;
    private String link;

    public MoreInfo(String title, String link){
        this.title = title;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }
}
