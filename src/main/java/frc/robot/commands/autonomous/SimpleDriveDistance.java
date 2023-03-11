// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;

public class SimpleDriveDistance extends CommandBase {
  /** Creates a new SimpleDriveDistance. */
  private DriveTrainSubsystem m_drivetrain;
  double distance;
  public SimpleDriveDistance(DriveTrainSubsystem drivetrain, double distance) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = drivetrain;
    addRequirements(drivetrain);
    this.distance = distanceToRevolutions(distance);
  }

  public double distanceToRevolutions(double distance) {
    return distance * 3 * 2 * Math.PI * 12.75; //here u should multiply by wheel circumference and gear ratio
  }

  // Called when the command is initially scheduled.
  public PIDController pidControl;

  @Override
  public void initialize() {

    pidControl = new PIDController(0.05, 0, 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double measurement = m_drivetrain.getAverageEncoderDistance();


    double minEffort = 0.2; //minimum effort for the robot to move
    double pidEffort = pidControl.calculate(measurement, distance);

    double direction = Math.signum(pidControl.getPositionError()); //get the sign (0, 1, or -1) of the error 

    
    m_drivetrain.arcadeDrive(pidEffort + minEffort * direction, 0);


  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}