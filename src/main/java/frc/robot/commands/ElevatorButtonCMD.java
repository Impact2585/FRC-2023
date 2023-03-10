package frc.robot.commands;
//merge code
import frc.robot.subsystems.Elevator;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ElevatorButtonCMD extends CommandBase 
{
    private final Elevator elevator;
    private final double speed;
    public ElevatorButtonCMD(Elevator elevator, double speed)
    {
       this.elevator = elevator;
       this.speed = speed;
       addRequirements(elevator);
    }

    @Override
  public void initialize() 
  {
    System.out.println("Elevator Started");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    elevator.setMotor(speed);
}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    elevator.setMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

}

