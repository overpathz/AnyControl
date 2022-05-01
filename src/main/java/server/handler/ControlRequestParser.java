package server.handler;

import server.command.CommandType;
import server.command.CursorCommand;
import server.command.GenericCommand;
import server.command.SwipeCommand;
import server.command.TapCommand;

public class ControlRequestParser {

    public GenericCommand parse(String inputRequest) {
        String[] split = inputRequest.split("/");
        int x = Integer.parseInt(split[split.length - 2]);
        int y = Integer.parseInt(split[split.length - 1]);

        if (inputRequest.contains("tap"))
            return new TapCommand(x, y, CommandType.TAP);
        else if (inputRequest.contains("cursor"))
            return new CursorCommand(x, y, CommandType.CURSOR);
        else if (inputRequest.contains("swipe"))
            return new SwipeCommand(x, y, CommandType.SWIPE);

        return null;
    }
}
