
package frc.robot.subsystems;


import edu.wpi.first.wpilibj.ADIS16470_IMU;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class IMUGyroSubsystem extends SubsystemBase{
    public static final ADIS16470_IMU imu = new ADIS16470_IMU();
    

    public IMUGyroSubsystem() {
        
        
    }

    
    

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
        
    }
}
