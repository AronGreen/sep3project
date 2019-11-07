package models.socket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import models.Trip;

public class TripSocketResponse {
    @SerializedName("Status")
    private String status;

    @SerializedName("Body")
    private Trip body;

    public TripSocketResponse() {
    }

    public TripSocketResponse(String status, Trip body) {
        this.status = status;
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public Trip getBody() {
        return body;
    }

    public String toJson() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return gson.toJson(this);
    }

    public static TripSocketResponse fromJson(String json) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return gson.fromJson(json, TripSocketResponse.class);
    }
}
