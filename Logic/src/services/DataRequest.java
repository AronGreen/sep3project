package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DataRequest {

    private String type;
    private String operation;
    private String body;

    public DataRequest(String type, String operation, String body) {
        this.type = type;
        this.operation = operation;
        this.body = body;
    }

    public String getType() {
        return type;
    }

    public String getOperation() {
        return operation;
    }

    public String getBody() {
        return body;
    }

    public String toJson(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(this);
    }

    // public static DataRequest fromJson(String json){
    //     GsonBuilder builder = new GsonBuilder();
    //     Gson gson = builder.create();
    //     return gson.fromJson(json, DataRequest.class);
    // }
}
