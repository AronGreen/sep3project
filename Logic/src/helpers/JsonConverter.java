package helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonConverter {

    public static String toJson(Object o) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return gson.toJson(o);
    }

    public static<T> T fromJson(String json, Class<T> type) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return gson.fromJson(json, type);
    }

}
