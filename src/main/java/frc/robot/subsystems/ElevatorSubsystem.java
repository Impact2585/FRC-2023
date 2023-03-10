package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class ElevatorSubsystem extends SubsystemBase{
    
  private final CANSparkMax elevatorMotor = new CANSparkMax(ElevatorConstants.kElevatorMotorPort,MotorType.kBrushless);
  private RelativeEncoder m_encoder;

  public ElevatorSubsystem()
  {
    m_encoder = elevatorMotor.getEncoder();
  }

  public void setMotor(double speed)
  {
     elevatorMotor.set(speed / 10);
  }

    
    

  @Override
  public void periodic() {
    
    SmartDashboard.putNumber("Encoder Position", m_encoder.getPosition());
    SmartDashboard.putNumber("Encoder Velocity", m_encoder.getVelocity());
    
  }
}
