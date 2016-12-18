package org.team2471.bunnybot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static org.team2471.bunnybot.HardwareMap.GrabberMap.ARM_MOTOR_PDPSLOT;
import static org.team2471.bunnybot.HardwareMap.GrabberMap.armEncoder;
import static org.team2471.bunnybot.HardwareMap.GrabberMap.armMotor;
import static org.team2471.bunnybot.HardwareMap.GrabberMap.bunnySensor;
import static org.team2471.bunnybot.HardwareMap.GrabberMap.bunnySucker;
import static org.team2471.bunnybot.HardwareMap.pdp;

public class Grabber extends PIDSubsystem {
  private static final double ARM_MAX_CURRENT = 30;
  private static double armOffset = 0.58;

  public Grabber() {
    super(0.025, 0, 0);
    super.enable();
    SmartDashboard.putData("GrabController", super.getPIDController());
  }

  @Override
  protected void initDefaultCommand() {
  }

  @Override
  protected double returnPIDInput() {
    return getAngle();
  }

  @Override
  protected void usePIDOutput(double value) {
    armMotor.set(-value);
  }

  /**
   * Set the arm angle to the desired position.
   *
   * @param angle target angle
   */
  public void setSetpoint(double angle) {
    super.setSetpoint(angle);
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
   *
   * @param speed rate to spit out (should be positive)
   */
  public void spitOut(double speed) {
    bunnySucker.set(-speed);
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

  /**
   * Zeroes the arm encoder.
   */
  public void zero() {
    armOffset = armEncoder.getVoltage();
  }
}
