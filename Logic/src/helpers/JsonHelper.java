package helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.SocketResponse;

public class JsonHelper {
    public static <T> SocketResponse<T> socketResponseFromJson(String json){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        SocketResponse<T> response = new SocketResponse<T>();
        return gson.fromJson(json, response.getClass());
    }
}
