package frc.robot.commands;
//merge code 
import frc.robot.subsystems.DriveTrainSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import edu.wpi.first.math.controller.PIDController;

public class PIDDrive extends CommandBase 
{
private final DriveTrainSubsystem drive;
private final PIDController pidController;
double kP = DriveConstants.kP;
double kI = DriveConstants.kI;
double kD = DriveConstants.kD;
double iLimit = DriveConstants.iLimit; 


    public PIDDrive(DriveTrainSubsystem drive, double setpoint)
    {
        this.drive = drive;
        this.pidController = new PIDController(kP, kI, kD);
        pidController.setSetpoint(setpoint);
        addRequirements(drive);
    }
    
    @Override
    public void initialize()
    {
       System.out.println("PID DriveTrain Command Started");
       pidController.reset();
    }
    @Override
    public void execute()
    {
       double speed = pidController.calculate(drive.getEncoderMeters());
       drive.arcadeDrive(speed, speed);
    }
    @Override 

    public void end(boolean interrupted)
    {
        drive.stop();
    }

    public boolean isFinished()
    {
        return false;
    }
}
