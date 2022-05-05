package server.handler;

import server.command.*;

import java.util.ArrayList;
import java.util.List;

public class CommandChooser {

    private static final List<GenericCommand> availableCommands = new ArrayList<>();

    static {
        availableCommands.add(new CursorCommand());
        availableCommands.add(new TapCommand());
        availableCommands.add(new SwipeCommand());
        availableCommands.add(new KeyboardCommand());
    }

    public void handle(String request) {
        for (GenericCommand availableCommand : availableCommands) {
            if (request.contains(availableCommand.getCmdName())) {
                availableCommand.performCommand(request);
            }
        }
    }
}
