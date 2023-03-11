// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.ProfiledPIDSubsystem;
import frc.robot.Constants.ElevatorConstants;

public class ElevatorPIDSubsystem extends ProfiledPIDSubsystem {
  /** Creates a new ElevatorPIDSubsystem. */
  private final CANSparkMax elevatorMotor = new CANSparkMax(ElevatorConstants.kElevatorMotorPort,MotorType.kBrushless);
  private RelativeEncoder m_encoder = elevatorMotor.getEncoder();
  private final ElevatorFeedforward m_feedforward = new ElevatorFeedforward(1, 0.15, 3);

  public ElevatorPIDSubsystem() {
    super(
        // The ProfiledPIDController used by the subsystem
        new ProfiledPIDController(
            0.5,
            0,
            0,
            // The motion profile constraints
            new TrapezoidProfile.Constraints(2, 5)));
    setGoal(-50);
  }

  @Override
  public void useOutput(double output, TrapezoidProfile.State setpoint) {
    // Use the output (and optionally the setpoint) here
    // Calculate the feedforward from the sepoint
    double feedforward = m_feedforward.calculate(setpoint.position, setpoint.velocity);
    // Add the feedforward to the PID output to get the motor output
    elevatorMotor.setVoltage(output + feedforward);
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return m_encoder.getPosition() + 50;
  }
}
