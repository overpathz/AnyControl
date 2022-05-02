package client;

import client.controller.Controller;
import client.sender.CommandSender;
import client.configuration.Configuration;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class SenderApp extends Application {

    private CommandSender commandSender;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(SenderApp.class.getResource("../Main.fxml"));
        fxmlLoader.load();
        Scene scene = new Scene(fxmlLoader.getRoot());
        Controller controller = fxmlLoader.getController();
        primaryStage.setScene(scene);
        primaryStage.setWidth(Configuration.getStageWidth());
        primaryStage.setHeight(Configuration.getStageHeight());
        commandSender = new CommandSender(controller);
        applySocketAction(scene);
        primaryStage.show();
    }

    private void applySocketAction(Scene scene) {
        scene.setOnMouseMoved(commandSender::sendCommand);
        scene.setOnMouseClicked(commandSender::sendCommand);
    }
}
