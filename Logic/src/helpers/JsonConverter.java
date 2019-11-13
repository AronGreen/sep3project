package helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonConverter {

    public static String toJson(Object o) {
        Gson gson = new GsonBuilder().setLenient().create();
        String json = gson.toJson(o);

        return json;
    }

}
