// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;



import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveTrainSubsystem extends SubsystemBase {
  private CANSparkMax leftController = new CANSparkMax(DriveConstants.kLeftMotor1Port, MotorType.kBrushless);
  private CANSparkMax leftController2 = new CANSparkMax(DriveConstants.kLeftMotor2Port, MotorType.kBrushless);
  private CANSparkMax rightController = new CANSparkMax(DriveConstants.kRightMotor1Port, MotorType.kBrushless);
  private CANSparkMax rightController2 = new CANSparkMax(DriveConstants.kRightMotor2Port, MotorType.kBrushless);
  private SlewRateLimiter filter = new SlewRateLimiter(2);
  private SlewRateLimiter filter2 = new SlewRateLimiter(2);
  private final IMUGyroSubsystem m_gyro = new IMUGyroSubsystem();
  private PIDController pidControllerYaw;
  private PIDController pidControllerPitch;
  private PIDController pidControllerForward;
  
 private final MotorControllerGroup m_leftMotors =
      new MotorControllerGroup(
          leftController,
          leftController2);

  // The motors on the right side of the drive.
  private final MotorControllerGroup m_rightMotors =
      new MotorControllerGroup(
          rightController,
          rightController2);

          
  private RelativeEncoder rightEncoder;
  private RelativeEncoder leftEncoder;
  private double currentAngle;
  private double balanceAngle;
  private double currentDistance;
  
  // The robot's drive
  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);
  public DriveTrainSubsystem()
  {
    leftController.setSmartCurrentLimit(40);
    leftController2.setSmartCurrentLimit(40);
    rightController.setSmartCurrentLimit(40);
    rightController2.setSmartCurrentLimit(40);
    leftController.setIdleMode(IdleMode.kCoast);
    rightController.setIdleMode(IdleMode.kCoast);

    rightEncoder = rightController.getEncoder();
    leftEncoder = leftController.getEncoder();
    m_leftMotors.setInverted(true);

    pidControllerYaw = new PIDController(DriveConstants.kP, DriveConstants.kI, DriveConstants.kD);
    pidControllerYaw.setTolerance(1);

    pidControllerPitch = new PIDController(DriveConstants.kBalanceP, DriveConstants.kBalanceI, DriveConstants.kBalanceD);
    pidControllerYaw.setTolerance(5);

    pidControllerForward = new PIDController(DriveConstants.kMoveP, DriveConstants.kMoveI, DriveConstants.kMoveD);
    pidControllerForward.setTolerance(1);
    currentDistance = 0;
    resetEncoders();
    resetGyro();
  }


  public void arcadeDrive(double speed, double rot)
  {
    m_drive.arcadeDrive(filter.calculate(speed*0.85), filter2.calculate(rot*0.85));
  }

  
  public void resetGyro()
  {
    m_gyro.reset();
  }
   public double getYaw()
  {
    return m_gyro.getYaw();
  }

  public double getPitch()
  {
    return m_gyro.getPitch();
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

  public Command TurnToAngle(double angle)
  {
    return run(() -> setAngle(angle)).until(() -> reachedSetpoint());
  }

  public Command balance(double angle)
  {
    resetGyro();
    return run(() -> setBalanceAngle(angle)).until(() -> reachedBalanceSetpoint());
  }

  public Command forward(double distance)
  {
    resetEncoders();
    return run(() -> setDistance(distance)).until(() -> reachedBalanceSetpoint());
  }

  public boolean reachedSetpoint() {
    return pidControllerYaw.getPositionTolerance() >= Math.abs(currentAngle - getYaw());
  }


public boolean reachedBalanceSetpoint() {
  return pidControllerPitch.getPositionTolerance() >= Math.abs(balanceAngle - getPitch());
}

public boolean reachedForwardSetpoint() {
  return pidControllerPitch.getPositionTolerance() >= Math.abs(currentDistance - getAverageEncoderDistance());
}

  public void turn(double direction)
  {
    m_drive.arcadeDrive(0, -direction);
  }

  public void moveToBalance(double speed)
  {
    m_drive.arcadeDrive(speed, 0);
  }

  public void move(double speed)
  {
    m_drive.arcadeDrive(speed, 0);
  }

  public boolean atSetpoint() {
    return pidControllerYaw.atSetpoint();
  }

  public void setAngle(double angle) {
    currentAngle = angle;
  }

  public void setDistance(double distance) {
    currentDistance = distance;
  }

  public void setBalanceAngle(double angle) {
    balanceAngle = angle;
  }

  public void setMaxOutput(double maxOutput)
  {
    m_drive.setMaxOutput(maxOutput);
  }

  public double getAverageEncoderDistance()
  {
    return leftEncoder.getPosition() + rightEncoder.getPosition();
  }

  public void resetEncoders() {
    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);
  }
  


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Moved to Angle", reachedSetpoint());
    SmartDashboard.putNumber("Goal Yaw Angle", currentAngle);

    SmartDashboard.putBoolean("Drivetrain Engaged", reachedBalanceSetpoint());
    SmartDashboard.putNumber("Goal Pitch Angle", balanceAngle);
      // turn(
      //     MathUtil.clamp(
      //             pidControllerYaw.calculate(getYaw(), currentAngle),
      //             -DriveConstants.kTurnSpeed,
      //             DriveConstants.kTurnSpeed
      //             ));
      // moveToBalance(
      //   MathUtil.clamp(
      //           pidControllerYaw.calculate(getPitch(), balanceAngle),
      //           -DriveConstants.kBalanceSpeed,
      //           DriveConstants.kBalanceSpeed
      //           ));
      // move(
      //   MathUtil.clamp(
      //           pidControllerForward.calculate(getAverageEncoderDistance(), currentDistance),
      //           -DriveConstants.kForwardSpeed,
      //           DriveConstants.kForwardSpeed 
      //           ));

    SmartDashboard.putBoolean("Drivetrain moved", reachedForwardSetpoint());
    SmartDashboard.putNumber("Encoder Distance", getAverageEncoderDistance());
    SmartDashboard.putNumber("Goal Distance", currentDistance);
    
  }


}
