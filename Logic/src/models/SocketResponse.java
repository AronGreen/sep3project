package models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SocketResponse<T> {
    private String status;
    private T body;

    public SocketResponse() {
    }

    public SocketResponse(String status, T body) {
        this.status = status;
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public T getBody() {
        return body;
    }

    public String toJson(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(this);
    }

    public SocketResponse<T> fromJson(String json){
        System.out.println(json);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return gson.fromJson(json, this.getClass());
    }
}
