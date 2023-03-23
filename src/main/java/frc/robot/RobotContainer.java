// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.ClawConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.ArcadeDriveCommand;
import frc.robot.commands.ClawToggleCommand;
import frc.robot.commands.SimpleArmCommand;
// import frc.robot.commands.AimAssist;
// import frc.robot.commands.ArcadeDriveCommand;
// import frc.robot.commands.AutoTrackObject;
// import frc.robot.commands.CheckObject;
import frc.robot.commands.SimpleElevatorMovementCommand;
import frc.robot.commands.autonomous.ScoreandMobility;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ClawSubsystem;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IMUGyroSubsystem;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrainSubsystem m_driveTrain = new DriveTrainSubsystem();
  private final Limelight m_light = new Limelight();
 
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
  private final CommandXboxController m_driverController2 =
      new CommandXboxController(OperatorConstants.kDriverControllerPort2);
  private final ClawSubsystem m_claw = new ClawSubsystem();
  private final ArmSubsystem m_arm = new ArmSubsystem();
  private final ElevatorSubsystem m_elevator = new ElevatorSubsystem();
  

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    m_elevator.resetEncoder();
    
   

    
  }

  private void configureBindings() {
    m_driveTrain.setDefaultCommand(new ArcadeDriveCommand(m_driveTrain, () -> m_driverController.getLeftY(), () -> m_driverController.getRightX()));
    m_driverController2.rightBumper().onTrue(new ClawToggleCommand(m_claw));
    m_arm.setDefaultCommand(new SimpleArmCommand(m_arm, () -> m_driverController2.getRightY()));
    m_elevator.setDefaultCommand(new SimpleElevatorMovementCommand(m_elevator, () -> m_driverController2.getLeftY()));
    m_driverController
        .rightBumper()
        .onTrue(Commands.runOnce(() -> m_driveTrain.setMaxOutput(DriveConstants.kminOutput)))
        .onFalse(Commands.runOnce(() -> m_driveTrain.setMaxOutput(DriveConstants.kmaxOutput)));
    m_driverController2.a().onTrue(m_elevator.setPose(ElevatorConstants.LOW));
    m_driverController2.b().onTrue(m_elevator.setPose(ElevatorConstants.MID));
    m_driverController2.y().onTrue(m_elevator.setPose(ElevatorConstants.HIGH));
    m_driverController2.x().onTrue(m_arm.setPose(ClawConstants.IN));
    m_driverController2.leftBumper().onTrue(m_arm.setPose(ClawConstants.OUT));
    
    m_driverController.x().onTrue(Commands.runOnce(() -> m_driveTrain.resetGyro()));
    //m_driverController.a().onTrue(m_driveTrain.TurnToAngle(90)); 
    //m_driverController.a().onTrue(m_driveTrain.balance(DriveConstants.kbalanceAngle)); 
    //m_driverController.b().onTrue(m_driveTrain.forward(20)); 
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    //return Autos.exampleAuto(m_exampleSubsystem);
    //return new ScoreandMobility(m_driveTrain);
    return null;
  }
}
