// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveTrainSubsystem extends SubsystemBase {
  private CANSparkMax leftController = new CANSparkMax(DriveConstants.kLeftMotor1Port, MotorType.kBrushless);
  private CANSparkMax rightController = new CANSparkMax(DriveConstants.kRightMotor1Port, MotorType.kBrushless);
  private SlewRateLimiter filter = new SlewRateLimiter(2.5);
  private final MotorControllerGroup m_leftMotors =
      new MotorControllerGroup(
          leftController,
          new CANSparkMax(DriveConstants.kLeftMotor2Port, MotorType.kBrushless));
 

  // The motors on the right side of the drive.
  private final MotorControllerGroup m_rightMotors =
      new MotorControllerGroup(
          rightController,
          new CANSparkMax(DriveConstants.kRightMotor2Port, MotorType.kBrushless));

  private RelativeEncoder rightEncoder;
  private RelativeEncoder leftEncoder;

  
  // The robot's drive
  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

  public DriveTrainSubsystem()
  {
    rightEncoder = rightController.getEncoder();
    leftEncoder = leftController.getEncoder();
  }


  public void arcadeDrive(double speed, double rot)
  {
    m_drive.arcadeDrive(filter.calculate(speed), rot * 0.75);
  }

  public void curvatureDrive(double throttle, double rot, boolean turnInPlace)
  {
    m_drive.curvatureDrive(throttle * 0.75, rot * 0.75, turnInPlace);
  }
  
  
  public void stop()
  {
    m_leftMotors.set(0);
    m_rightMotors.set(0);
  }

  public double getLeftSpeed()
  {
    return m_leftMotors.get();
  }

  public double getRightSpeed()
  {
    return m_rightMotors.get();
  }



  public void setMaxOutput(double maxOutput)
  {
    m_drive.setMaxOutput(maxOutput);
  }

  public double getAverageEncoderDistance()
  {
    double avgDistance = leftEncoder.getPosition() + rightEncoder.getPosition();
    return avgDistance;
  }

  
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run

        
  }


}
