package com.example.coronavirusreport;

public class Stats {
    private String place;
    private String totalCases;
    private String recovered;
    private String deaths;
    private String critical;

    //constructor that takes in place, total cases, recovered and deaths
    public Stats(String p, String tc, String r, String d, String c){
        place = p;
        totalCases = tc;
        recovered = r;
        deaths = d;
        critical = c;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(String totalCases) {
        this.totalCases = totalCases;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }
}
