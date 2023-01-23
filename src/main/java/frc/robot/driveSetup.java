package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrainSubsystem;

public class driveSetup {

    // drive chooser: Tank Drive, Arcade Drive, and CurvatureDrive
    public static final SendableChooser<String> chooser = new SendableChooser<>();

    public static void driveSelector()
    {
        chooser.setDefaultOption("TankDrive", "Tank");
        chooser.addOption("Arcade Drive", "Arcade");
        chooser.addOption("Curvature Drive", "Curvature");
        SmartDashboard.putData("Drive choices", chooser);
    }

    public static String getDrive()
    {
        return chooser.getSelected();
    }
}
