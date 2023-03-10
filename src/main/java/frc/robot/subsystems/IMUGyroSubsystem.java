
package frc.robot.subsystems;



import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class IMUGyroSubsystem extends SubsystemBase{
    public static final ADIS16470_IMU imu = new ADIS16470_IMU();
    

    public IMUGyroSubsystem() {
        imu.calibrate();
        imu.resetAllAngles();
    }

    public double getYaw()
    {
        return imu.getAngle(imu.getYawAxis());
    }

    public double getPitch()
    {
        return imu.getAngle(imu.getPitchAxis());
    }
    
    public double getRoll()
    {
        return imu.getAngle(imu.getRollAxis());
    }


    @Override
    public void periodic() {
      SmartDashboard.putNumber("Yaw", getYaw());
      SmartDashboard.putNumber("Pitch", getPitch());
      SmartDashboard.putNumber("Roll", getRoll());  
    }
}
