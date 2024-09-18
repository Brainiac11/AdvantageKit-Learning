package frc.robot;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class IntakeDownCommand extends Command {
    Intake intake;

    public IntakeDownCommand() {
        intake = Intake.getInstance();

        addRequirements(intake);
    }

    @Override
    public void execute() {
        intake.setPivotSetpoint(Rotation2d.fromDegrees(90));
        intake.setRollerSetpoint(10);
    }
}
