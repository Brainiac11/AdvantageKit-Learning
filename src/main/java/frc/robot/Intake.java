package frc.robot;

import org.littletonrobotics.junction.AutoLog;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.controls.PositionDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.IntakeIO.IntakeIOInputs;

public class Intake extends SubsystemBase {
    private TalonFX pivotMotor;
    private TalonFX rollerMotor;
    private PIDController pivotController;
    private Rotation2d pivotSetpoint = Rotation2d.fromDegrees(0);
    private double rollerSpeed = 0;
    private final IntakeIO io;
    private final IntakeIOInputs inputs = new IntakeIOInputs();

    private static Intake instance;

    private Intake(IntakeIO io) {
        pivotMotor = new TalonFX(10);
        rollerMotor = new TalonFX(11);
        this.io = io;
        pivotController = new PIDController(1.0, 0, 0);
        pivotMotor.getConfigurator().apply(new Slot0Configs().withKP(pivotController.getP()));
    }

    public static Intake getInstance() {
        if (instance == null) {
            instance = new Intake(new IntakeIO() {

            });

        }
        return instance;
    }

    @Override
    public void periodic() {
        pivotMotor.setControl(new PositionDutyCycle(pivotSetpoint.getRotations()));
        rollerMotor.set(rollerSpeed);
    }

    public void setPivotSetpoint(Rotation2d setpoint) {
        this.pivotSetpoint = setpoint;
    }

    public void setRollerSetpoint(double setpoint) {
        this.rollerSpeed = setpoint;
    }

    public Rotation2d getPivotSetpoint() {
        return Rotation2d.fromRotations(pivotMotor.getPosition().getValueAsDouble());
    }

}
