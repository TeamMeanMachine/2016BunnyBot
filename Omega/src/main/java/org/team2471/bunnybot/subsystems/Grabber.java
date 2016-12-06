package org.team2471.bunnybot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.team2471.bunnybot.HardwareMap;
import org.team2471.bunnybot.defaultcommands.GrabberDefaultCommand;

import static org.team2471.bunnybot.HardwareMap.GrabberMap.*;
import static org.team2471.bunnybot.HardwareMap.pdp;

public class Grabber extends Subsystem {
  private double armOffset = 0;
  private static final double ARM_MAX_CURRENT = 30;
  private PIDController grabController = new PIDController(0.066667,0,0,armEncoder, value -> {
    System.out.println(value);
    set(value);
  });

  public Grabber() {
    armOffset = getAngle();
    grabController.enable();
    SmartDashboard.putData("GrabController", grabController);
  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new GrabberDefaultCommand());
  }

  /**
   * Set the arm angle to the desired position.
   *
   * @param angle target angle
   */
  public void setAngle(double angle) {
//    System.out.println("Angle: " + getAngle());
    grabController.setSetpoint(angle);
  }


  /**
   * @return the current angle of the grabber in degrees.
   */
  public double getAngle() {
    return (armEncoder.getVoltage() - 0.2) / 4.6 - armOffset;
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
