package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;
import edu.wpi.first.wpilibj.Encoder;


public class Elevator extends SubsystemBase{
    
  private final CANSparkMax elevatorMotor = new CANSparkMax(ElevatorConstants.kElevatorMotorPort,MotorType.kBrushless);
  private final Encoder encoder = new Encoder(4,5);

  public double getEncoderMeters()
  {
       return encoder.getDistance();
  }
  public Elevator()
  {

  }

  public void setMotor(double speed)
  {
     elevatorMotor.set(speed);
  }

    
    

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
        
    }
}
