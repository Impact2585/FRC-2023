package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClawConstants;


public class ClawSubsystem extends SubsystemBase{
    private Solenoid m_clawSolenoid = new Solenoid(ClawConstants.kPCMID,PneumaticsModuleType.REVPH, ClawConstants.kdeployClawChannel);
    

    public ClawSubsystem()
    {

    }

    public void toggle()
    {
        m_clawSolenoid.toggle();
    }
    
    @Override
    public void periodic() {
      // This method will be called once per scheduler run
          
    }
    
}


