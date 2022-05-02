package server.configuration;

public final class Configuration {

    private Configuration() {
        throw new UnsupportedOperationException("invoking private constructor");
    }

    public static int PORT = 8199;

    public static String IP = "localhost";
    public static double STAGE_WIDTH = 300;
    public static double STAGE_HEIGHT = 500;

    public static double REMOTE_WIDTH = 1290;
    public static double REMOTE_HEIGHT = 760;

    public static int getPORT() {
        return PORT;
    }

    public static void setPORT(int PORT) {
        Configuration.PORT = PORT;
    }

    public static String getIP() {
        return IP;
    }

    public static void setIP(String IP) {
        Configuration.IP = IP;
    }

    public static double getStageWidth() {
        return STAGE_WIDTH;
    }

    public static void setStageWidth(double stageWidth) {
        STAGE_WIDTH = stageWidth;
    }

    public static double getStageHeight() {
        return STAGE_HEIGHT;
    }

    public static void setStageHeight(double stageHeight) {
        STAGE_HEIGHT = stageHeight;
    }

    public static double getRemoteWidth() {
        return REMOTE_WIDTH;
    }

    public static void setRemoteWidth(double remoteWidth) {
        REMOTE_WIDTH = remoteWidth;
    }

    public static double getRemoteHeight() {
        return REMOTE_HEIGHT;
    }

    public static void setRemoteHeight(double remoteHeight) {
        REMOTE_HEIGHT = remoteHeight;
    }
}
