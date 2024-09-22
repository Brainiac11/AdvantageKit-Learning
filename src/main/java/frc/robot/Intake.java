package frc.robot;

import org.littletonrobotics.junction.AutoLog;
import org.littletonrobotics.junction.Logger;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.controls.PositionDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.IntakeIO.IntakeIOInputs;

public class Intake extends SubsystemBase {
    private PIDController pivotController;
    private PIDController rollerController;
    private final IntakeIO io;
    private final IntakeIOInputs inputs = new IntakeIOInputs();
    private final IntakeIOInputsAutoLogged inputsAutoLogged = new IntakeIOInputsAutoLogged();
    private Rotation2d angleSetpoint = null; // Setpoint for closed loop control, null for open loop
    private Double speedSetpoint = null; // Setpoint for closed loop control, null for open loop
    private static Intake instance;

    private Intake(IntakeIO io) {
        this.io = io;
        pivotController = new PIDController(1.0, 0, 0);
        rollerController = new PIDController(1.0, 0, 0);
    }

    public static Intake getInstance() {
        if (instance == null) {
            if (RobotBase.isReal()) {
                instance = new Intake(new IntakeIOKraken());
            } else {
                instance = new Intake(new IntakeIOSim());
            }
        }
        return instance;
    }

    @Override
    public void periodic() {
        Logger.recordOutput("Intake Running", true);
        Logger.processInputs("Intake", inputsAutoLogged);
        io.setPivotVoltage(
                pivotController.calculate(getIntakeAngle() != null ? getIntakeAngle().getRotations() : 0,
                        angleSetpoint != null ? angleSetpoint.getRotations() : 0));
        io.setRollerVoltage(rollerController.calculate(getIntakeSpeed(), inputs.rollerSetpoint));

    }

    public void setPivotSetpoint(Rotation2d setpoint) {
        io.changePivotSetpoint(setpoint);
    }

    public void setRollerSetpoint(double setpoint) {
        io.changeRollerSpeed(setpoint);
    }

    public Rotation2d getIntakeAngle() {
        return inputs.intakePosition;
    }

    public double getIntakeSpeed() {
        return inputs.rollerSpeed;
    }

}
