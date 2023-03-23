




// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants
  {
    public static final int kDriverControllerPort = 0;
    public static final int kDriverControllerPort2 = 1;
  }
  public static class DriveConstants
  {
    public static final int kLeftMotor1Port = 11;
    public static final int kLeftMotor2Port = 12;
    public static final int kRightMotor1Port = 21;
    public static final int kRightMotor2Port = 22;

    public static final double kmotorSpeed = 0.2;

    public static final double kTurnSpeed = 0.35;

    public static final double kBalanceSpeed = 0.15;

    public static final double kForwardSpeed = 0.3;

    public static final double kP = 0.375;
    public static final double kI = 0;
    public static final double kD = 0;

    public static final double kBalanceP = 0.1;
    public static final double kBalanceI = 0;
    public static final double kBalanceD = 0;

    public static final double kminOutput = 0.25;
    public static final double kmaxOutput = 0.8;
    
    public static final double kmaxSpeed = 0.8;

    public static final double kbalanceAngle = 0;

    public static final double kMoveP = 0.4;
    public static final double kMoveI = 0;
    public static final double kMoveD = 0;
  }

  public static class VisionConstants
  {
    public static final int kConePipeline = 0;
    public static final int kCubePipeline = 0;
  }
  public static final class ClawConstants 
  {
    public static final int kdeployClawChannel = 15;
    public static final int kPCMID = 1;

    public static final int kArmMotorPort = 50;

    public static final double teleopArmSpeed = 0.3;

    public static final double IN = -3;
    public static final double OUT = 33;


    public static final double kP = 0.4;
    public static final double kI = 0;
    public static final double kD = 0;

    public static final double maxVoltage = 4;
    
  }
  public static final class ElevatorConstants
  {
    public static final int kElevatorMotorPort = 40;
    public static final double inchestorot = 1.92; 

    public static final double teleopElevatorSpeed = 0.75;

    public static final double kpositionTolerance = .25;

    public static final double kP = 1.5;
    public static final double kI = 0;
    public static final double kD = 0;

    public static final double LOW = 0;
    public static final double MID = -60;
    public static final double HIGH = -118;

    public static final double maxVoltage = 5;
  }
}