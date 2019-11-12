package models.socket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SocketListResponse<T> {

    private String status;
    private ArrayList<T> body;

    public SocketListResponse(String status, ArrayList<T> body) {
        this.status = status;
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<T> getBody() {
        return body;
    }

    public static <T> SocketListResponse<T> fromJson(String json, Class<T> type) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Type listType = new TypeToken<SocketListResponse<T>>() {
        }.getType();
        return gson.fromJson(json, listType);
    }
}
