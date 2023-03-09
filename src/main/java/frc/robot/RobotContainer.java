// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.AimAssist;
import frc.robot.commands.ArcadeDriveCommand;
import frc.robot.commands.AutoTrackObject;
import frc.robot.commands.CheckObject;
import frc.robot.commands.CurvatureDriveCommand;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrainSubsystem m_driveTrain = new DriveTrainSubsystem();
  private final Limelight m_light = new Limelight();
  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();


    m_driveTrain.setDefaultCommand(new ArcadeDriveCommand(m_driveTrain, () -> m_driverController.getLeftY(), () -> m_driverController.getRightX()));
    //m_driveTrain.setDefaultCommand(new CurvatureDriveCommand(m_driveTrain, () -> m_driverController.getLeftY(), () -> m_driverController.getRightX(), m_driverController.x()));

    
  }

  private void configureBindings() {


  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    //return Autos.exampleAuto(m_exampleSubsystem);
    return new InstantCommand();
  }
}
