package frc.robot.subsystems;



import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.ClawConstants;

public class Claw {
    private Solenoid zero = new Solenoid(ClawConstants.kPCMID,PneumaticsModuleType.REVPH, ClawConstants.kdeployForwardChannel);
    private Solenoid one = new Solenoid(ClawConstants.kPCMID,PneumaticsModuleType.REVPH, ClawConstants.kdeployBackwardChannel);
    private boolean toggle = false;
    Compressor pcmCompressor = new Compressor(ClawConstants.kPCMID,PneumaticsModuleType.REVPH);

    public Claw()
    {

        pcmCompressor.enableDigital(); 
    }

    public void clawIOToggle(XboxController controller, XboxController controller2, double power)
    {
        if(controller.getRightBumperPressed())
        {
            if(toggle)
            {
                toggle = false;
                zero.toggle();

                
            } else {
                toggle = true;
                zero.toggle();
            }
        }

        if(controller.getRightBumperPressed())
        {
            if(toggle)
            {
                toggle = false;
                System.out.println("Claw OFF\n");

            } else {
                toggle = true;
                System.out.println("Claw ON\n");

            }
        }
        
    }

    public void clawDirectionalToggle(XboxController controller, XboxController controller2, double power)
    {
        if(controller.getLeftBumperPressed())
        {
            if(toggle)
            {
                toggle = false;
                one.toggle();

                
            } else {
                toggle = true;
                one.toggle();
            }
        }

        if(controller.getLeftBumperPressed())
        {
            if(toggle)
            {
                toggle = false;
                System.out.println("Claw Up\n");

            } else {
                toggle = true;
                System.out.println("Claw Down\n");

            }
        }
    }
}


