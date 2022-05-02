package server.handler;

import server.command.CommandType;
import server.command.GenericCommand;

import java.awt.*;
import java.awt.event.InputEvent;

public class ControlHandler {

    private final Robot robot;
    private final String controlRequest;
    private final ControlRequestParser controlRequestParser;

    public ControlHandler(String controlRequest) throws AWTException {
        this.robot = new Robot();
        this.controlRequest = controlRequest;
        this.controlRequestParser = new ControlRequestParser();
        handle();
    }

    private void handle() {
        GenericCommand command = controlRequestParser.parse(controlRequest);
        CommandType cmdType = command.getCommandType();

        int x = command.getX();
        int y = command.getY();

        System.err.println(command);

        if (cmdType == CommandType.TAP) {
            mouseClick();
        } else if (cmdType == CommandType.CURSOR) {
            moveMouse(x, y);
        } else if (cmdType == CommandType.SWIPE) {
            swipe(x, y);
        }
    }

    private void swipe(int x, int y) {
        Point location = MouseInfo.getPointerInfo().getLocation();
        int locX = (int) (location.getX() + x);
        int locY = (int) (location.getY() + y);
        robot.mouseMove(locX, locY);
    }

    private void moveMouse(int x, int y) {
        robot.mouseMove(x, y);
        System.out.println("Cursor command executed successfully");
    }

    private void mouseClick() {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        System.out.println("Tap command executed successfully");
    }
}
