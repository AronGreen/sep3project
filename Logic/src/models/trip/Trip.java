package models.trip;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Entity;

import java.util.Calendar;

public class Trip extends Entity {

    private String title;

    private String date;

    public Trip(int id, Calendar createDate, Calendar updateDate, Calendar deleted, String title, String date) {
        super(id, createDate, updateDate, deleted);
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String toJson(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return gson.toJson(this);
    }

    public static Trip fromJson(String json) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return gson.fromJson(json, Trip.class);
    }
}
