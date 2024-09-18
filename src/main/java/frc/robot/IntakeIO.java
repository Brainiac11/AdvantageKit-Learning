package frc.robot;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.math.geometry.Rotation2d;

public interface IntakeIO {

    @AutoLog
    public static class IntakeIOInputs {
        public Rotation2d intakeSetpoint = new Rotation2d(0);
        public double rollerSetpoint = 0;
        public double rollerSpeed = 0;
        public Rotation2d rollerPosition = Rotation2d.fromDegrees(0);
        public Rotation2d intakePosition = new Rotation2d(0);
        public boolean atSetpoint = false;
    }

    public default void updateInputs(IntakeIOInputs inputs) {
    }

    public default void changePivotSetpoint(Rotation2d setpoint) {
    }

    public default void changeRollerSpeed(double speed) {
    }
}
