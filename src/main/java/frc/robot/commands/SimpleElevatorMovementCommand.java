package frc.robot.commands;

import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class SimpleElevatorMovementCommand extends CommandBase {
  private ElevatorSubsystem m_elevator;
  private DoubleSupplier m_speed;

  public SimpleElevatorMovementCommand(ElevatorSubsystem elevator, DoubleSupplier s) {
    m_elevator = elevator;
    m_speed = s;

    addRequirements(m_elevator);
  }

  @Override
  public void execute() {
    m_elevator.setMotor(m_speed.getAsDouble());
  }
}