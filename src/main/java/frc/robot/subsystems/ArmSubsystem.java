package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClawConstants;

public class ArmSubsystem extends SubsystemBase{

    private final CANSparkMax armMotor = new CANSparkMax(ClawConstants.kArmMotorPort, MotorType.kBrushless);
    private RelativeEncoder m_encoder;
    private PIDController pidController;
    private double currentPosition;

    public ArmSubsystem()
    {
      m_encoder = armMotor.getEncoder();
      armMotor.setIdleMode(IdleMode.kBrake);
      armMotor.setSmartCurrentLimit(30);

      pidController = new PIDController(ClawConstants.kP, ClawConstants.kI, ClawConstants.kD);
      pidController.setTolerance(3);

      resetEncoder();
    }

    public void move(double voltage) {
      armMotor.setVoltage(voltage);
    }

    public void setMotor(double speed)
    {
      armMotor.set(speed*ClawConstants.teleopArmSpeed);
    }
    
    public Command setPose(double position) {
      return run(() -> setPosition(position)).until(() -> reachedSetpoint());
    }

    public void setPosition(double position) {
      currentPosition = position;
    }

    public void resetEncoder() {
      m_encoder.setPosition(0);
    }

    public double getEncoderPosition() {
      return armMotor.getEncoder().getPosition();
    }

    public boolean reachedSetpoint() {
      return pidController.getPositionTolerance() >= Math.abs(currentPosition - getEncoderPosition());
    }

    
    public boolean atSetpoint() {
        return pidController.atSetpoint();
    }

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
      SmartDashboard.putNumber("Arm Position", getEncoderPosition());
      SmartDashboard.putBoolean("Arm at setpoint", reachedSetpoint());

      move(
          MathUtil.clamp(
                  pidController.calculate(getEncoderPosition(), currentPosition),
                  -ClawConstants.maxVoltage,
                  ClawConstants.maxVoltage));
    }
    
}


