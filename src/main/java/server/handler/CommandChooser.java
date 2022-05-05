package server.handler;

import server.command.CursorCommand;
import server.command.GenericCommand;
import server.command.SwipeCommand;
import server.command.TapCommand;

import java.util.ArrayList;
import java.util.List;

public class CommandChooser {

    private static final List<GenericCommand> availableCommands = new ArrayList<>();

    static {
        availableCommands.add(new CursorCommand());
        availableCommands.add(new TapCommand());
        availableCommands.add(new SwipeCommand());
    }

    public void handle(String request) {
        for (GenericCommand availableCommand : availableCommands) {
            if (request.contains(availableCommand.getCmdName())) {
                availableCommand.performCommand(request);
            }
        }
    }
}
