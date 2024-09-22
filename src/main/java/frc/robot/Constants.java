package frc.robot;

public class Constants {
    public static final Mode currentMode = Mode.REAL;

    public static enum Mode {
        /** Running on a real robot. */
        REAL,

        /** Running a physics simulator. */
        SIM,

        /** Replaying from a log file. */
        REPLAY
    }

    public static double rollerGearing = 1 / 1.5;
    public static double pivotGearing = 125 / 1;
}
