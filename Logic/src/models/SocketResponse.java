package models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SocketResponse {
    private int id;
    private String status;
    private String body;

    public SocketResponse(int id, String status, String body) {
        this.id = id;
        this.status = status;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getBody() {
        return body;
    }

    public String toJson(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(this);
    }

    public static SocketResponse fromJson(String json){
        System.out.println(json);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(json, SocketResponse.class);
    }
}
