package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClawConstants;

public class ArmSubsystem extends SubsystemBase{
    private Solenoid m_armSolenoid = new Solenoid(ClawConstants.kPCMID,PneumaticsModuleType.REVPH, ClawConstants.kdeployArmChannel);

    public ArmSubsystem()
    {

    }

    public void toggle()
    {
        m_armSolenoid.toggle();
    }
    
    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    Shuffleboard.getTab("Pneumatics")
        .add("Arm", m_armSolenoid.get())
        .withWidget(BuiltInWidgets.kBooleanBox);
          
    }
    
}


