package server.command;

import java.awt.*;

public class SwipeCommand extends GenericCommand {

    private static final String COMMAND_NAME = "/swipe";

    @Override
    public void performCommand(String request) {
        String[] split = request.split("/");
        int x = Integer.parseInt(split[split.length - 2]);
        int y = Integer.parseInt(split[split.length - 1]);
        swipe(x, y);
    }

    private void swipe(int x, int y) {
        Point location = MouseInfo.getPointerInfo().getLocation();
        int locX = (int) (location.getX() + x);
        int locY = (int) (location.getY() + y);
        getRobot().mouseMove(locX, locY);
    }

    @Override
    public String getCmdName() {
        return COMMAND_NAME;
    }
}
