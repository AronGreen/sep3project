package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import helpers.JsonHelper;
import models.SocketRequest;
import models.SocketResponse;
import models.TripSocketResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Enum singleton, pretty neat
 */
public enum DataConnection {

    INSTANCE;

    public TripSocketResponse sendRequest (SocketRequest request) {
        TripSocketResponse response = null;
        try {
            Connection conn = new Connection();
            conn.send(request.toJson().getBytes());
            byte[] responseBytes = conn.receive();
            conn.close();
            if (responseBytes == null) {
                // TODO: consider returning an error response
                return null;
            }
            ArrayList<Byte> byteList = new ArrayList<Byte>();
            for (byte responseByte : responseBytes) {
                if (responseByte == 0) break;
                byteList.add(responseByte);
            }
            byte[] trimmedBytes = new byte[byteList.size()];
            for (int i = 0; i < byteList.size(); i++){
                trimmedBytes[i] = byteList.get(i);
            }

            String json = new String(trimmedBytes, StandardCharsets.UTF_8);

            json = json.replace("\\u0022", "\"");
            response = TripSocketResponse.fromJson(json);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
