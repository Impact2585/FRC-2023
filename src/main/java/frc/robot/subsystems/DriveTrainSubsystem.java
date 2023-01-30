// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveTrainSubsystem extends SubsystemBase {
  private final MotorControllerGroup m_leftMotors =
      new MotorControllerGroup(
          new Spark(DriveConstants.kLeftMotor1Port),
          new Spark(DriveConstants.kLeftMotor2Port));

  // The motors on the right side of the drive.
  private final MotorControllerGroup m_rightMotors =
      new MotorControllerGroup(
          new Spark(DriveConstants.kRightMotor1Port),
          new Spark(DriveConstants.kRightMotor2Port));

  SendableChooser<String> m_chooser = new SendableChooser<>();
  
  // The robot's drive
  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

  public DriveTrainSubsystem()
  {
    m_chooser.setDefaultOption("Tank Drive", "Tank Drive");
    m_chooser.addOption("Arcade Drive", "Arcade Drive");
    m_chooser.addOption("Curvature Drive", "Curvature Drive");
  }
  public void tankDrive(double leftSpeed, double rightSpeed)
  {
    m_drive.tankDrive(leftSpeed, rightSpeed, true);
  }

  public void arcadeDrive(double speed, double rot)
  {
    m_drive.arcadeDrive(speed, rot, true);
  }

  public void curvatureDrive(double speed, double rot)
  {
    m_drive.curvatureDrive(speed, rot, false);
  }
  
  public void swingTurnLeft()
  {
    m_leftMotors.set(0);
    m_rightMotors.set(DriveConstants.kmotorSpeed);
  }

  public void swingTurnRight()
  {
    m_leftMotors.set(DriveConstants.kmotorSpeed);
    m_rightMotors.set(0);
  }

  public void pointTurnLeft()
  {
    m_leftMotors.set(-DriveConstants.kmotorSpeed);
    m_rightMotors.set(DriveConstants.kmotorSpeed);
  }

  public void pointTurnRight()
  {
    m_leftMotors.set(DriveConstants.kmotorSpeed);
    m_rightMotors.set(-DriveConstants.kmotorSpeed);
  }

  public void inputTurn(double left, double right)
  {
    m_leftMotors.set(left);
    m_rightMotors.set(right);
  }
  public void stop()
  {
    m_leftMotors.set(0);
    m_rightMotors.set(0);
  }

  public void setDrive(double lx, double ly, double ry)
  {
    switch (m_chooser.getSelected())
    {
      case "Tank Drive": 
        tankDrive(ly, ry);
        break;
      case "Arcade Drive": 
        arcadeDrive(ly, lx);
        break;
      case "Curvature Drive": 
        curvatureDrive(ly, lx);
        break;
    }
  }
  
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
