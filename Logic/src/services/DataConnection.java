package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.SocketRequest;
import models.SocketResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

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
            System.out.println("writing");
            conn.send(request.toJson().getBytes());
            System.out.println("reading");
            byte[] responseBytes = conn.receive();
            conn.close();
            if (responseBytes == null) {
                // TODO: consider returning an error response
                return null;
            }

            response = SocketResponse.fromJson(new String(responseBytes, StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
            return new SocketResponse(0, "Bad stuff", e.toString());
        }
        return response;
    }
}
