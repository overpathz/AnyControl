package server;

import server.configuration.Configuration;
import server.server.DataReceiver;

import java.io.IOException;

public class Runner {
    public static void main(String[] args) throws IOException {
        DataReceiver receiver = new DataReceiver(Configuration.getPORT());
        receiver.run();
    }
}
