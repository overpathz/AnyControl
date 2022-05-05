package server.command;

import oshi.SystemInfo;

import java.awt.*;

public abstract class GenericCommand {

    private Robot robot;
    private final SystemInfo systemInfo;

    protected GenericCommand(){
        initRobot();
        this.systemInfo = new SystemInfo();
    }

    private void initRobot() {
        try {
            this.robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public abstract void performCommand(String request);
    public abstract String getCmdName();

    public Robot getRobot() {
        return robot;
    }

    public SystemInfo getSystemInfo() {
        return systemInfo;
    }
}
