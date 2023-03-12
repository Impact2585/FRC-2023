// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.ArcadeDriveCommand;
import frc.robot.commands.ArmToggleCommand;
import frc.robot.commands.ClawToggleCommand;
// import frc.robot.commands.AimAssist;
// import frc.robot.commands.ArcadeDriveCommand;
// import frc.robot.commands.AutoTrackObject;
// import frc.robot.commands.CheckObject;
import frc.robot.commands.CurvatureDriveCommand;
import frc.robot.commands.SimpleElevatorMovementCommand;
import frc.robot.commands.autonomous.ScoreandMobility;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ClawSubsystem;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ElevatorPIDSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IMUGyroSubsystem;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrainSubsystem m_driveTrain = new DriveTrainSubsystem();
  private final Limelight m_light = new Limelight();
  private final IMUGyroSubsystem m_gyro = new IMUGyroSubsystem();
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
  private final CommandXboxController m_driverController2 =
      new CommandXboxController(OperatorConstants.kDriverControllerPort2);
  private final ClawSubsystem m_claw = new ClawSubsystem();
  private final ArmSubsystem m_arm = new ArmSubsystem();
  //private final ElevatorSubsystem m_elevator = new ElevatorSubsystem();
  private final ElevatorSubsystem m_elevator = new ElevatorSubsystem();
  

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();


   

    
  }

  private void configureBindings() {
    m_driveTrain.setDefaultCommand(new ArcadeDriveCommand(m_driveTrain, () -> m_driverController.getLeftY(), () -> m_driverController.getRightX()));
    //m_driveTrain.setDefaultCommand(new CurvatureDriveCommand(m_driveTrain, () -> m_driverController.getLeftY(), () -> m_driverController.getRightX(), m_driverController.x()));
    m_driverController2.a().onTrue(new ClawToggleCommand(m_claw));
    m_driverController2.b().onTrue(new ArmToggleCommand(m_arm));
    m_elevator.setDefaultCommand(new SimpleElevatorMovementCommand(m_elevator, () -> m_driverController.getLeftY()));
    m_driverController
        .rightBumper()
        .onTrue(Commands.runOnce(() -> m_driveTrain.setMaxOutput(0.3)))
        .onFalse(Commands.runOnce(() -> m_driveTrain.setMaxOutput(0.8)));

    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    //return Autos.exampleAuto(m_exampleSubsystem);
    return new ScoreandMobility(m_driveTrain);
    //return null;
  }
}
