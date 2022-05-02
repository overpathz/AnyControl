import client.configuration.Configuration;
import client.sender.CommandSender;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.server.DataReceiver;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class TestCursorCommand {

    private static DataReceiver dataReceiver;
    private CommandSender commandSender;

    private static final int PORT = 8766;
    private static final String IP = "localhost";

    private static final int SENT_X_COORDINATE = 150;
    private static final int SENT_Y_COORDINATE = 150;

    private static final ExecutorService performer = Executors.newFixedThreadPool(5);

    @BeforeAll
    static void startDataReceiver() throws IOException {
        dataReceiver = new DataReceiver(PORT);
        performer.execute(() -> {
            try {
                dataReceiver.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @BeforeEach
    void setUp() {
        commandSender = new CommandSender();
        commandSender.setReceiverData(IP, PORT);
        applyTestConfigurationData();
    }

    @Test
    void sendCursorCommand() throws InterruptedException {
        MouseEvent mouseEvent = generateMouseEvent();
        commandSender.sendCommand(mouseEvent);

        assertEquals(SENT_X_COORDINATE, (int) mouseEvent.getX());
        assertEquals(SENT_Y_COORDINATE, (int) mouseEvent.getY());

        TimeUnit.SECONDS.sleep(1); // waiting command performing from server side
        Point location = MouseInfo.getPointerInfo().getLocation();

        int actualX = (int) location.getX();
        int actualY = (int) location.getY();

        assertEquals(SENT_X_COORDINATE, actualX);
        assertEquals(SENT_Y_COORDINATE, actualY);
    }

    @AfterAll
    static void stopReceiver() throws IOException {
        dataReceiver.stop();
        performer.shutdown();
    }

    private MouseEvent generateMouseEvent() {
        return new MouseEvent(MouseEvent.MOUSE_MOVED, SENT_X_COORDINATE, SENT_Y_COORDINATE,
                SENT_X_COORDINATE, SENT_Y_COORDINATE, MouseButton.PRIMARY,
                1, true, true, true,
                true, true, true,
                true, true, false, true,
                null);
    }

    private void applyTestConfigurationData() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) size.getWidth();
        int height = (int) size.getHeight();
        Configuration.setRemoteWidth(width);
        Configuration.setRemoteHeight(height);
        Configuration.setStageWidth(width);
        Configuration.setStageHeight(height);
    }
}
