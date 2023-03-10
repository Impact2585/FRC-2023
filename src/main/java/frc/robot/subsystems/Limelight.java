
package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class Limelight extends SubsystemBase{

    public NetworkTable m_limelight;
    private double ta, tx, ty;
    private int pipeline; 

    public Limelight() {
        m_limelight = NetworkTableInstance.getDefault().getTable("limelight");
        
    }

    
    public boolean hasTarget() {
        return ta >= 2;
    }

    /**
     * Returns the yaw from the limelight to the (estimated) center of the hub.
     * Note: The limelight returns measurements relative to the center of the targets in its field of
     * view, which may differ from the center of the hub.
     *
     * @return The yaw (horizontal rotation) in degrees.
     */
    public double getYaw() {
        return tx;
    }

    /**
     * Returns the raw pitch value from the limelight.
     *
     * @return The raw pitch (vertical rotation) in degrees.
     */
    public double getPitch() {
        return ty;
    }

    public double getTA() {
        return ta;
    }

    public void setLedOn() {
        m_limelight.getEntry("ledMode").setValue("1");
    }

    public void setLedOff() {
        m_limelight.getEntry("ledMode").setValue("0");
    }


    public void setPipeline(int pipelineNum) {
        m_limelight.getEntry("pipeline").setNumber(pipelineNum);
    }

    public int getPipeline() {
        return pipeline;
    }

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
        ta = m_limelight.getEntry("ta").getDouble(0);
        tx = m_limelight.getEntry("tx").getDouble(0);
        ty = m_limelight.getEntry("ty").getDouble(0);
        pipeline = m_limelight.getEntry("pipeline").getNumber(0).intValue();
        SmartDashboard.putNumber("LimelightX", tx);
        SmartDashboard.putNumber("LimelightY", ty);
        SmartDashboard.putNumber("LimelightArea", ta);
    }
}
