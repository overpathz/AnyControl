package server.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataReceiver {

    private final ConnectionHandler connectionHandler;
    private final ExecutorService executorService = Executors.newFixedThreadPool(50);
    private final ServerSocket serverSocket;
    private final int port;
    private volatile boolean isRunning = true;

    public DataReceiver(int port) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
        connectionHandler = new ConnectionHandler();
    }

    public void run() throws IOException {
        System.out.println("Server started at " + port + " port..");
        while (isRunning) {
            Socket accept = serverSocket.accept();
            System.out.println("New connection: " + accept.getPort());
            executorService.execute(() -> connectionHandler.handle(accept));
        }
    }

    public void stop() throws IOException {
        isRunning = false;
        serverSocket.close();
    }
}
