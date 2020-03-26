package com.supreme.ab.polymap;

public class EventModel {
    private String eventName;
    private String date;
    private String time;
    private String discription ;
    private String latitude;
    private String longitude;
    private String place;
    EventModel(){
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public EventModel(String date,String discription,String eventName,String latitude, String longitude ,String time,String place) {
        this.eventName = eventName;
        this.date = date;
        this.time = time;
        this.place=place;
        this.discription = discription;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
