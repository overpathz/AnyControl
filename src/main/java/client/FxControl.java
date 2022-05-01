package client;

import configuration.Configuration;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

public class FxControl extends Application {

    private static final Logger LOGGER = Logger.getLogger(FxControl.class.getSimpleName());

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(FxControl.class.getResource("../Main.fxml"));
        fxmlLoader.load();
        Scene scene = new Scene(fxmlLoader.getRoot());
        primaryStage.setScene(scene);
        primaryStage.setWidth(Configuration.STAGE_WIDTH);
        primaryStage.setHeight(Configuration.STAGE_HEIGHT);
        applySocketAction(scene, fxmlLoader.getController());
        primaryStage.show();
    }

    private void applySocketAction(Scene scene, Controller controller) {
        scene.setOnMouseMoved(event -> extracted(event, "/cursor/", controller));
        scene.setOnMouseClicked(event -> extracted(event, "/tap/", controller));
    }

    private void extracted(MouseEvent event, String command, Controller controller) {
        double xCoefficient = Configuration.REMOTE_WIDTH / Configuration.STAGE_WIDTH;
        double yCoefficient = Configuration.REMOTE_HEIGHT / Configuration.STAGE_HEIGHT;

        double layoutX = event.getX();
        double layoutY = event.getY();

        int x = (int) (layoutX * xCoefficient);
        int y = (int) (layoutY * yCoefficient);

        String msgToSend = command + x + "/" + y;
        LOGGER.info(msgToSend);
        controller.getCoordinates().setText("X: " + layoutX + " | Y: " + layoutY);
        sendData(msgToSend);
    }

    private void sendData(String data)  {
        try {
            try(Socket socket = new Socket(Configuration.IP, Configuration.PORT);
                OutputStream outputStream = socket.getOutputStream()) {
                outputStream.write(data.getBytes());
                outputStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
