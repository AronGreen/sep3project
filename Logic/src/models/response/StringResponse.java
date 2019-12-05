package models.response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class StringResponse {

    private String status;
    private String body;

    public StringResponse(String status, String body) {
        this.status = status;
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public String getBody() {
        return body;
    }

    // public String toJson() {
    //     Gson gson = new GsonBuilder()
    //             .setLenient()
    //             .create();
    //     return gson.toJson(this);
    // }

//    public static <T> StringResponse fromJson(String json, Class<T> type) {
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();
//        Type thisType = new TypeToken<StringResponse<T>>() {
//        }.getType();
//        return gson.fromJson(json, thisType);
//    }
}
