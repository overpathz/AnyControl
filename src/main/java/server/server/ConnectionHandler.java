package server.server;

import server.handler.CommandChooser;

import java.io.InputStream;
import java.net.Socket;

public class ConnectionHandler {

    private final CommandChooser commandChooser;

    public ConnectionHandler() {
        this.commandChooser = new CommandChooser();
    }

    public void handle(Socket socket) {
        try(InputStream inputStream = socket.getInputStream()) {

            int read;
            StringBuilder sb = new StringBuilder();
            while ((read=inputStream.read()) != -1)
                sb.append((char) read);

            String controlRequest = sb.toString();
            System.out.println("Request: " + controlRequest);
            commandChooser.handle(controlRequest);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
