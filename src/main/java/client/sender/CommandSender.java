package client.sender;

import client.controller.Controller;
import client.configuration.Configuration;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class CommandSender {

    private final Controller controller;

    public CommandSender(Controller controller) {
        this.controller = controller;
    }

    public void sendCommand(MouseEvent event) {
        // getting coefficients that show the ratio between the client and server resolutions
        double xCoefficient = Configuration.REMOTE_WIDTH / Configuration.STAGE_WIDTH;
        double yCoefficient = Configuration.REMOTE_HEIGHT / Configuration.STAGE_HEIGHT;

        // layout scene coordinates
        double layoutX = event.getX();
        double layoutY = event.getY();

        // converted x and y coordinates to send
        int x = (int) (layoutX * xCoefficient);
        int y = (int) (layoutY * yCoefficient);

        String command = getCommandType(event);

        String msgToSend = command + x + "/" + y;
        System.out.println(msgToSend);
        controller.getCoordinates().setText("X: " + layoutX + " | Y: " + layoutY);
        sendData(msgToSend);
    }

    private String getCommandType(MouseEvent event) {
        String name = event.getEventType().getName().toLowerCase();
        if (name.contains("moved")) return "/cursor/";
        if (name.contains("clicked")) return "/tap/";
        return null;
    }

    private void sendData(String data)  {
        try(Socket socket = new Socket(Configuration.IP, Configuration.PORT);
            OutputStream outputStream = socket.getOutputStream()) {
            outputStream.write(data.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
