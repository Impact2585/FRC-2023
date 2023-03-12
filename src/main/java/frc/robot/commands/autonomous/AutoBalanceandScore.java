// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.autoBalance;
import frc.robot.subsystems.DriveTrainSubsystem;

public class AutoBalanceandScore extends CommandBase {
  /** Creates a new AutoBalanceandScore. */
  private autoBalance m_auto = new autoBalance();
  private DriveTrainSubsystem m_drive;
  public AutoBalanceandScore(DriveTrainSubsystem drive) {
    m_drive = drive;
    addRequirements(drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    double speed = m_auto.scoreAndBalance();
    m_drive.arcadeDrive(-speed, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
