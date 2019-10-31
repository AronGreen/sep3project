package models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

public class Trip {
    private int id;
    private String title;
    private Date date;

    public Trip(int id, String title, Date date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public String toJson(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(this);
    }

    public static Trip fromJson(String json){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(json, Trip.class);
    }
}
