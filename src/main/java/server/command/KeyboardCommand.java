package server.command;

public class KeyboardCommand extends GenericCommand {

    @Override
    public void performCommand(String request) {
        String[] split = request.split("/");
        char character = split[split.length - 1].toCharArray()[0];
        getRobot().keyPress(character);
        getRobot().keyRelease(character);
    }

    @Override
    public String getCmdName() {
        return "/keyboard";
    }
}
