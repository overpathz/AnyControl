package server.command;

import java.awt.event.InputEvent;

public class TapCommand extends GenericCommand {

    private static final String COMMAND_NAME = "/tap";

    @Override
    public void performCommand(String request) {
        mouseClick();
    }

    private void mouseClick() {
        getRobot().mousePress(InputEvent.BUTTON1_DOWN_MASK);
        getRobot().mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        System.out.println("Tap command executed successfully");
    }

    @Override
    public String getCmdName() {
        return COMMAND_NAME;
    }
}
