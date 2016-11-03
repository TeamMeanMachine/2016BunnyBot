package org.team2471.bunnybot.subsystems;

import edu.wpi.first.wpilibj.*;
import org.team2471.bunnybot.HardwareMap;

public class Grabber {
  private AnalogInput armEncoder = HardwareMap.GrabberMap.armEncoder;
  private DigitalInput bunnySensor = HardwareMap.GrabberMap.bunnySensor;
  private CANTalon bunnySucker = HardwareMap.GrabberMap.bunnySucker;
  private CANTalon leftJointMotor = HardwareMap.GrabberMap.leftJointMotor;
  private CANTalon rightJointMotor = HardwareMap.GrabberMap.rightJointMotor;
  private PIDController grabController = new PIDController(0.066667,0,0,armEncoder,leftJointMotor);

  public Grabber() {
    rightJointMotor.changeControlMode(CANTalon.TalonControlMode.Follower);
    rightJointMotor.set(leftJointMotor.getDeviceID());
  }
  /**
   * Set the arm angle to the desired position.
   *
   * @param angle target angle
   */
  public void setAngle(double angle) {
    grabController.setSetpoint(angle);
  }


  public double getAngle() {
    return 0;
  }
  /**
   * Rotates the intake motors inward.
   */
  public void suckIn() {
    bunnySucker.set(1);
  }

  /**
   * Rotates the intake moters outward.
   */
  public void spitOut() {
    bunnySucker.set(-1);
  }

  /**
   * Stops the intake motors.
   */
  public void stopIntake() {
    bunnySucker.set(0);
  }

  /**
   * @return whether or not bunny is present in intake
   */
  public boolean hasBunny() {
    return bunnySensor.get();
  }
}
