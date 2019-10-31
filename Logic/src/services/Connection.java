package services;

import java.io.*;
import java.net.Socket;

class Connection {
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    Connection() {
        try {
            // TODO: move host and port to some sort of config or constant
            socket = new Socket("localhost", 2910);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void send(byte[] bytes) throws IOException {
        outputStream.write(bytes);
    }

    byte[] receive() throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        while (true) {
            int nextBytes = inputStream.read(buffer);
            if (nextBytes < 0) break;
            byteStream.write(buffer, 0, nextBytes);
        }
        return byteStream.toByteArray();
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
