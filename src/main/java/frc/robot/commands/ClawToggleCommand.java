package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ClawSubsystem;

public class ClawToggleCommand extends InstantCommand {
  private ClawSubsystem m_claw;

  public ClawToggleCommand(ClawSubsystem claw) {
    m_claw = claw;
    addRequirements(claw);
  }

  @Override
  public void execute() {
    m_claw.toggle();
  }
}