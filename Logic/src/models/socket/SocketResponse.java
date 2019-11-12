package models.socket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class SocketResponse<T> {
    private String status;
    private T body;

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

    public String toJson() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return gson.toJson(this);
    }

    public static <T> SocketResponse<T> fromJson(String json, Class<T> type) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Type thisType = new TypeToken<SocketResponse<T>>() {
        }.getType();
        return gson.fromJson(json, thisType);
    }
}
