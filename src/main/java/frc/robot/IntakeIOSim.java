package frc.robot;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionDutyCycle;
import com.ctre.phoenix6.controls.VelocityDutyCycle;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;

public class IntakeIOSim implements IntakeIO {
    private static final double kLoopPeriodSeconds = 0.02;
    private final DCMotorSim rollerMotorSim = new DCMotorSim(DCMotor.getKrakenX60(1), Constants.rollerGearing, 2);
    private final DCMotorSim pivotMotorSim = new DCMotorSim(DCMotor.getKrakenX60(1), Constants.pivotGearing, 3);

    private final double rotationPosition = pivotMotorSim.getAngularPositionRotations();
    private final double pivotVelocity = pivotMotorSim.getAngularVelocityRPM();
    private final double rollerVelocity = rollerMotorSim.getAngularVelocityRPM();
    private final double rollerPosition = rollerMotorSim.getAngularPositionRotations();

    private double pivotAppliedVolts = 0.0;
    private double rollerAppliedVolts = 0.0;

    @Override
    public void updateInputs(IntakeIOInputs inputs) {
        pivotMotorSim.update(kLoopPeriodSeconds);
        rollerMotorSim.update(kLoopPeriodSeconds);

        inputs.intakePosition = Rotation2d.fromRotations(rotationPosition);
        inputs.rollerSpeed = rollerVelocity;
        inputs.rollerSetpoint = rollerPosition;
    }

    @Override
    public void setPivotVoltage(double volts) {
        pivotAppliedVolts = MathUtil.clamp(volts, -12.0, 12.0);
        pivotMotorSim.setInputVoltage(pivotAppliedVolts);
    }

    @Override
    public void setRollerVoltage(double volts) {
        rollerAppliedVolts = MathUtil.clamp(volts, -12.0, 12.0);
        rollerMotorSim.setInputVoltage(rollerAppliedVolts);
    }
}
