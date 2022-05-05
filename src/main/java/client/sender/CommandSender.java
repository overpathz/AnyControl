package client.sender;

import client.configuration.Configuration;
import client.controller.Controller;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class CommandSender {

    private Controller controller;

    private String ip;
    private int port;

    public CommandSender() {
        setReceiverData(Configuration.getIP(), Configuration.getPORT());
    }

    public CommandSender(Controller controller) {
        this();
        this.controller = controller;
    }

    public void sendCommand(KeyEvent event) {
        String msgToSend = "/keyboard/" + event.getCharacter();
        System.out.println(msgToSend);
        sendData(msgToSend);
    }

    public void sendCommand(MouseEvent event) {
        // getting coefficients that show the ratio between the client and server resolutions
        double xCoefficient = Configuration.getRemoteWidth() / Configuration.getStageWidth();
        double yCoefficient = Configuration.getRemoteHeight() / Configuration.getStageHeight();

        // layout scene coordinates
        double layoutX = event.getX();
        double layoutY = event.getY();

        // converted x and y coordinates to send
        int x = (int) (layoutX * xCoefficient);
        int y = (int) (layoutY * yCoefficient);

        String command = getCommandType(event);

        String msgToSend = command + x + "/" + y;
        sendData(msgToSend);

        System.out.println(msgToSend);
        if (controller != null) controller.getCoordinates().setText("X: " + layoutX + " | Y: " + layoutY);
    }

    private String getCommandType(MouseEvent event) {
        String name = event.getEventType().getName().toLowerCase();
        if (name.contains("moved")) return "/cursor/";
        if (name.contains("clicked")) return "/tap/";
        return null;
    }

    private void sendData(String data)  {
        try(Socket socket = new Socket(ip, port);
            OutputStream outputStream = socket.getOutputStream()) {
            outputStream.write(data.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setReceiverData(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
}
