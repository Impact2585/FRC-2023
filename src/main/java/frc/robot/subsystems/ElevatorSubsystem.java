package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;
import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class ElevatorSubsystem extends SubsystemBase{
    
  private final CANSparkMax elevatorMotor = new CANSparkMax(ElevatorConstants.kElevatorMotorPort,MotorType.kBrushless);
  private RelativeEncoder m_encoder;
  ElevatorFeedforward feedforward;
  public ElevatorSubsystem()
  {
    m_encoder = elevatorMotor.getEncoder();
    elevatorMotor.setInverted(true);
    feedforward = new ElevatorFeedforward(0.5, 0.5, 0.5);
  }

  public void setMotor(double speed)
  {
    
     elevatorMotor.set(speed);
  }

  public void setMotor(double speed, double acceleration)
  {
     double calc = feedforward.calculate(20, 30);
     elevatorMotor.setVoltage(calc);
  }

    
    

  @Override
  public void periodic() {
    
    SmartDashboard.putNumber("Encoder Position", m_encoder.getPosition());
    SmartDashboard.putNumber("Encoder Velocity", m_encoder.getVelocity());
    
  }
}
