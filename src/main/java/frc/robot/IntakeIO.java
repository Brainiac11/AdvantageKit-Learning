package frc.robot;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.math.geometry.Rotation2d;

public interface IntakeIO {

    @AutoLog
    public static class IntakeIOInputs {
        public static Rotation2d intakeSetpoint = new Rotation2d(0);
        public static double rollerSetpoint = 0;
        public static double rollerSpeed = 0;
        public static Rotation2d intakePosition = new Rotation2d(0);
        public static boolean atSetpoint = false;
    }

    public default void updateInputs(IntakeIOInputs inputs) {
    }

    public default void changePivotSetpoint(Rotation2d setpoint) {
    }

    public default void changeRollerSpeed(double speed) {
    }
}
