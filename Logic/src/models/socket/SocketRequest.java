package models.socket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SocketRequest {
    private int id;
    private String type;
    private String operation;
    private String body;

    public SocketRequest(int id, String type, String operation, String body) {
        this.id = id;
        this.type = type;
        this.operation = operation;
        this.body = body;
    }

    public String toJson(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(this);
    }

    public static SocketRequest fromJson(String json){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(json, SocketRequest.class);
    }
}
