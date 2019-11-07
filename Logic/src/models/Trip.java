package models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Trip {
    private int id;

    private String title;

    private String date;

    public Trip() {
    }

    public Trip(int id, String title, String date) {
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

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String toJson(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return gson.toJson(this);
    }
//
//    public static Trip fromJson(String json){
//        System.out.println(json);
//        GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder.create();
//        return gson.fromJson(json, Trip.class);
//    }
//
//    public static SocketResponse<Trip> fromJsonResponse(String json){
//        System.out.println(json);
//        GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder.create();
//        return gson.fromJson(json, SocketResponse<Trip>.class);
//    }
}
