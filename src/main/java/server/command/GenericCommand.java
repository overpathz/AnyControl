package server.command;

public abstract class GenericCommand {
    private final int x;
    private final int y;
    private final CommandType commandType;

    public GenericCommand(int x, int y, CommandType commandType) {
        this.x = x;
        this.y = y;
        this.commandType = commandType;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "x=" + x +
                ", y=" + y +
                ", commandType=" + commandType +
                '}';
    }
}
