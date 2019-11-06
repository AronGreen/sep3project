package models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class TripSocketResponse {

    @SerializedName("Status")
    public String status;
    @SerializedName("Body")
    public Trip body;

    public TripSocketResponse() {
    }

    public TripSocketResponse(String status, Trip body) {
        this.status = status;
        this.body = body;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Trip getBody() {
        return body;
    }

    public void setBody(Trip body) {
        this.body = body;
    }

    public static TripSocketResponse fromJson(String json){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return gson.fromJson(json, TripSocketResponse.class);
    }
}
