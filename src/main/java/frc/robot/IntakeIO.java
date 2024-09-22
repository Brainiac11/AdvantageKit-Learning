package frc.robot;

import org.littletonrobotics.junction.AutoLog;

import com.ctre.phoenix6.configs.TalonFXConfiguration;

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
        public double pivotAppliedVolts = 0;
        public double rollerAppliedVolts = 0;
    }

    public default void updateInputs(IntakeIOInputs inputs) {
    }

    public default void changePivotSetpoint(Rotation2d setpoint) {
    }

    public default void changeRollerSpeed(double speed) {
    }

    /** Run the drive motor at the specified voltage. */
    public default void setPivotVoltage(double volts) {
    }

    /** Run the turn motor at the specified voltage. */
    public default void setRollerVoltage(double volts) {
    }
}
