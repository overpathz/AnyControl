package server;

import server.server.DataReceiver;

import java.io.IOException;

public class Runner {
    public static void main(String[] args) throws IOException {
        DataReceiver receiver = new DataReceiver(8199);
        receiver.run();
    }
}
