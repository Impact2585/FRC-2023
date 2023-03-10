// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Limelight;

public class CheckObject extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Limelight m_light;

  private int objectTracking;

  public CheckObject(Limelight lm, int tracked) {
    m_light = lm;
    objectTracking = tracked;
    addRequirements(lm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    m_light.setPipeline(objectTracking);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    if (m_light.hasTarget())
    {
        System.out.println("test");
    }
    else{
      System.out.println(m_light.getTA());
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }


}
