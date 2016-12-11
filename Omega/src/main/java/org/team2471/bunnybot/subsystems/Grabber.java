package org.team2471.bunnybot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static org.team2471.bunnybot.HardwareMap.GrabberMap.ARM_MOTOR_PDPSLOT;
import static org.team2471.bunnybot.HardwareMap.GrabberMap.armEncoder;
import static org.team2471.bunnybot.HardwareMap.GrabberMap.armMotor;
import static org.team2471.bunnybot.HardwareMap.GrabberMap.bunnySensor;
import static org.team2471.bunnybot.HardwareMap.GrabberMap.bunnySucker;
import static org.team2471.bunnybot.HardwareMap.pdp;

public class Grabber extends Subsystem {
  private static final double armOffset = 0.61;
  private static final double ARM_MAX_CURRENT = 30;
  private PIDController grabController = new PIDController(0.8, 0, 0, armEncoder,
//      new RateLimitedPIDOutput(0.5,
      value -> {
        SmartDashboard.putNumber("Grabber Voltage", -value);
        armMotor.set(-value);
      });

  public Grabber() {
    grabController.enable();
    SmartDashboard.putData("GrabController", grabController);
  }

  @Override
  protected void initDefaultCommand() {
//    setDefaultCommand(new GrabberDefaultCommand());
  }

  /**
   * Set the arm angle to the desired position.
   *
   * @param angle target angle
   */
  public void setSetpoint(double angle) {
//    System.out.println("Angle: " + getAngle());
    System.out.println("angle: " + angle);
    grabController.setSetpoint(angle);
  }

  /**
   * @return the target angle in degrees
   */
  public double getSetpoint() {
    return grabController.getSetpoint();
  }


  /**
   * @return the current angle of the grabber in degrees.
   */
  public double getAngle() {
    return (armEncoder.getVoltage() - 0.2) / 4.6 * 360 - (armOffset - 0.2) / 4.6 * 360; // inverted because mechanical
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

  private void set(double power) {
    if (pdp.getCurrent(ARM_MOTOR_PDPSLOT) > ARM_MAX_CURRENT) {
      armMotor.set(0);
    }
    armMotor.set(power);
  }
}
