package frc.robot.commands;

import frc.robot.subsystems.DriveTrainSubsystem;


import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ArcadeDriveCommand extends CommandBase {
  private DriveTrainSubsystem m_drivetrain;
  private DoubleSupplier m_forward, m_rotation;

  public ArcadeDriveCommand(DriveTrainSubsystem drivetrain, DoubleSupplier d, DoubleSupplier e) {
    m_drivetrain = drivetrain;
    m_forward = e;
    m_rotation = d;

    addRequirements(m_drivetrain);
  }

  @Override
  public void execute() {
    m_drivetrain.arcadeDrive(m_forward.getAsDouble(), m_rotation.getAsDouble());
  }
}