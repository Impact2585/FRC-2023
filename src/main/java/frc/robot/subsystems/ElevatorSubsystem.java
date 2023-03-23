package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class ElevatorSubsystem extends SubsystemBase{
    
  private final CANSparkMax elevatorMotor = new CANSparkMax(ElevatorConstants.kElevatorMotorPort,MotorType.kBrushless);
  private RelativeEncoder m_encoder;
  private PIDController pidController;

  private double currentPosition;
  public ElevatorSubsystem()
  {
    m_encoder = elevatorMotor.getEncoder();
    elevatorMotor.setInverted(true);
    elevatorMotor.setIdleMode(IdleMode.kBrake);
    elevatorMotor.setSmartCurrentLimit(30);
    
    pidController = new PIDController(ElevatorConstants.kP, ElevatorConstants.kI, ElevatorConstants.kD);
    pidController.setTolerance(ElevatorConstants.kpositionTolerance);

    resetEncoder();
  }

  public void setMotor(double speed)
  {
     elevatorMotor.set(speed*ElevatorConstants.teleopElevatorSpeed);
  }


  public void resetEncoder() {
    elevatorMotor.getEncoder().setPosition(0);
    
}

public Command setPose(double position) {
    return run(() -> setPosition(position)).until(() -> atSetpoint());
}

public void setPosition(double position) {
    currentPosition = position;
}

public double getPosition() {
    return currentPosition;
}

public void move(double voltage) {
    elevatorMotor.setVoltage(voltage);
}

public boolean reachedSetpoint(double distance) {
    return pidController.getPositionTolerance() >= Math.abs(currentPosition - distance);
}

public double getEncoderPosition() {
    return elevatorMotor.getEncoder().getPosition();
}

public boolean atSetpoint() {
    return pidController.atSetpoint();
}

    
    

  @Override
  public void periodic() {
    
    SmartDashboard.putNumber("Elevator Velocity", m_encoder.getVelocity());
    SmartDashboard.putBoolean("Elevator at setpoint", atSetpoint());
    SmartDashboard.putNumber("Elevator Position", getEncoderPosition());
    SmartDashboard.putNumber("Elevator Goal Position", currentPosition);

    move(
          MathUtil.clamp(
                  pidController.calculate(getEncoderPosition(), currentPosition),
                  -ElevatorConstants.maxVoltage,
                  ElevatorConstants.maxVoltage));
  }
}
