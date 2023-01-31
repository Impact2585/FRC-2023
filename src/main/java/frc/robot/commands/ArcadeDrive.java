package frc.robot.commands;

import frc.robot.subsystems.DriveTrainSubsystem;


import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ArcadeDrive extends CommandBase {
  private DriveTrainSubsystem m_drivetrain;
  private DoubleSupplier m_forward, m_rotation;

  public ArcadeDrive(DriveTrainSubsystem drivetrain, DoubleSupplier d, DoubleSupplier e) {
    m_drivetrain = drivetrain;
    m_forward = d;
    m_rotation = e;

    addRequirements(m_drivetrain);
  }

  @Override
  public void execute() {
    m_drivetrain.arcadeDrive(m_forward.getAsDouble(), m_rotation.getAsDouble());
  }
}