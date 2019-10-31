package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.SocketRequest;
import models.SocketResponse;

import java.io.IOException;

/**
 * Enum singleton, pretty neat
 */
public enum DataConnection {

    INSTANCE;
//    private static final DataConnection instance = new DataConnection();
//
//    private DataConnection() {
//    }
//
//    public static DataConnection getInstance() {
//        return instance;
//    }

    public SocketResponse sendRequest(SocketRequest request) {
        SocketResponse response = null;
        try {
            Connection conn = new Connection();
            conn.send(request.toJson().getBytes());
            byte[] responseBytes = conn.receive();
            conn.close();
            if (responseBytes == null) {
                // TODO: consider returning an error response
                return null;
            }

            response = SocketResponse.fromJson(responseBytes.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
