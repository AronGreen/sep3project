package services;

import java.io.*;
import java.net.Socket;

class Connection {
    private Socket socket;
    private DataOutputStream outputStream;
    private BufferedInputStream inputStream;

    Connection() {
        try {
            // TODO: move host and port to some sort of config or constant
            socket = new Socket("localhost", 3000);
            inputStream = new BufferedInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void send(byte[] bytes) throws IOException {
        outputStream.write(bytes);
    }

    byte[] receive() throws IOException {
        byte[] buffer = new byte[8192];
        inputStream.read(buffer, 0, buffer.length);
        return buffer;
    }

    void close() {
        try {
            outputStream.close();
            inputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
