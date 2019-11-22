package services;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Enum singleton, pretty neat
 */
public enum DataConnection {

    INSTANCE;

    public String sendRequest (DataRequest request) {
        try {
            Connection conn = new Connection();
            conn.send(request.toJson().getBytes());
            // conn.send(json.getBytes());
            byte[] responseBytes = conn.receive();
            conn.close();
            if (responseBytes == null) {
                // TODO: consider returning an error response
                return null;
            }
            byte[] trimmedBytes = trimBytes(responseBytes);

            return new String(trimmedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // TODO: consider returning an error response
        return null;
    }

    private byte[] trimBytes(byte[] bytes){
        ArrayList<Byte> byteList = new ArrayList<Byte>();
        for (byte responseByte : bytes) {
            if (responseByte == 0) break;
            byteList.add(responseByte);
        }
        byte[] trimmedBytes = new byte[byteList.size()];
        for (int i = 0; i < byteList.size(); i++){
            trimmedBytes[i] = byteList.get(i);
        }
        return trimmedBytes;
    }
}
