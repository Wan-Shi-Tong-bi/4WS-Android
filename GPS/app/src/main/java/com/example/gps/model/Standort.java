package com.example.gps.model;

public class Standort {
    int id;
    String longitude;
    String latitude;
    String date;

    public Standort() {
    }

    public Standort(String longitude, String latitude, String date) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.date = date;
    }

    public Standort(int id, String longitude, String latitude, String date) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.date = date;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Längengrad: "+longitude + ", \n"+ "Breitengrad:" + latitude;
    }

}
