package server.command;

public class CursorCommand extends GenericCommand {

    private static final String COMMAND_NAME = "/cursor";

    @Override
    public void performCommand(String request) {
        String[] split = request.split("/");
        int x = Integer.parseInt(split[split.length - 2]);
        int y = Integer.parseInt(split[split.length - 1]);
        moveMouse(x, y);
    }

    private void moveMouse(int x, int y) {
        getRobot().mouseMove(x, y);
        System.out.println("Cursor command executed successfully");
    }

    @Override
    public String getCmdName() {
        return COMMAND_NAME;
    }
}
