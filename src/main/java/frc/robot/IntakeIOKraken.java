package frc.robot;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.geometry.Rotation2d;

public class IntakeIOKraken implements IntakeIO {

    private final TalonFX rollerMotor = new TalonFX(1);
    private final TalonFX pivotMotor = new TalonFX(0);

    private final StatusSignal<Double> rotationPosition = pivotMotor.getPosition();
    private final StatusSignal<Double> pivotVelocity = pivotMotor.getVelocity();
    private final StatusSignal<Double> rollerVelocity = rollerMotor.getVelocity();
    private final StatusSignal<Double> rollerPosition = rollerMotor.getPosition();

    public IntakeIOKraken() {
        var config = new TalonFXConfiguration();
        config.CurrentLimits.SupplyCurrentLimit = 80.0;
        config.CurrentLimits.SupplyCurrentLimitEnable = true;
        config.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        pivotMotor.getConfigurator().apply(config);
        rollerMotor.getConfigurator().apply(config);

        BaseStatusSignal.setUpdateFrequencyForAll(
                50.0,
                rotationPosition,
                pivotVelocity,
                rollerVelocity,
                rollerPosition);
        pivotMotor.optimizeBusUtilization();
        rollerMotor.optimizeBusUtilization();
    }

    @Override
    public void updateInputs(IntakeIOInputs inputs) {
        BaseStatusSignal.refreshAll(
                rotationPosition,
                pivotVelocity,
                rollerVelocity,
                rollerPosition);

        inputs.intakePosition = Rotation2d.fromRotations(rotationPosition.getValueAsDouble());
        inputs.rollerSpeed = rollerVelocity.getValueAsDouble();
        inputs.rollerSetpoint = rollerPosition.getValueAsDouble();
    }
}
