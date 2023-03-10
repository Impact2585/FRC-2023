package frc.robot.subsystems;



import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClawConstants;

public class ClawSubsystem extends SubsystemBase{
    private Solenoid one = new Solenoid(ClawConstants.kPCMID,PneumaticsModuleType.REVPH, ClawConstants.kdeployClawChannel);
    Compressor pcmCompressor = new Compressor(ClawConstants.kPCMID,PneumaticsModuleType.REVPH);

    public ClawSubsystem()
    {
        pcmCompressor.enableDigital(); 
    }

    public void toggle()
    {
        one.toggle();
    }
    
    
}


