package frc.robot.commands;

import frc.robot.subsystems.ArmSubsystem;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class SimpleArmCommand extends CommandBase {
  private ArmSubsystem m_arm;
  private DoubleSupplier m_speed;

  public SimpleArmCommand(ArmSubsystem arm, DoubleSupplier s) {
    m_arm = arm;
    m_speed = s;

    addRequirements(arm);
  }

  @Override
  public void execute() {
    m_arm.setMotor(m_speed.getAsDouble());
  }
}