package frc.robot.commands;

import frc.robot.subsystems.DriveTrainSubsystem;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class CurvatureDriveCommand extends CommandBase {
  private DriveTrainSubsystem m_drivetrain;
  private DoubleSupplier m_forward, m_rotation;
  private BooleanSupplier m_pointTurn;

  public CurvatureDriveCommand(DriveTrainSubsystem drivetrain, DoubleSupplier d, DoubleSupplier e, BooleanSupplier b) {
    m_drivetrain = drivetrain;
    m_forward = d;
    m_rotation = e;
    m_pointTurn = b;

    addRequirements(drivetrain);
  }

  @Override
  public void execute() {
    m_drivetrain.curvatureDrive(m_forward.getAsDouble(), m_rotation.getAsDouble(), m_pointTurn.getAsBoolean());
  }
}