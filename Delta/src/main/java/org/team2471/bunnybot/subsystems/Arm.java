package org.team2471.bunnybot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.team2471.bunnybot.HardwareMap;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team2471.frc.lib.sensors.Magnepot;

public class Arm extends Subsystem {

  private Magnepot shoulderEncoder = HardwareMap.Arm.shoulderEncoder;
  private Magnepot elbowEncoder = HardwareMap.Arm.elbowEncoder;
  private CANTalon shoulderMotor = HardwareMap.Arm.shoulderMotor;
  private CANTalon elbowMotor = HardwareMap.Arm.elbowMotor;
  private CANTalon bunnySucker = HardwareMap.Arm.bunnySucker;

  public PIDController shoulderController = new PIDController( -0.04, -0.0, -0.01, shoulderEncoder, shoulderMotor );
  public PIDController elbowController = new PIDController( -0.04, -0.0, -0.01, elbowEncoder, elbowMotor );

  public Arm() {
    SmartDashboard.putData("Shoulder PID", shoulderController);
    SmartDashboard.putData("Elbow PID", elbowController);
    shoulderController.setAbsoluteTolerance(2.0);
    elbowController.setAbsoluteTolerance(2.0);
  }

  @Override
  protected void initDefaultCommand() {
  }

  /**
   * Sets the shoulder to a specific angle
   */
  public void setShoulderAngle(double angle) {
    shoulderController.setSetpoint(angle);
  }

  /**
   * Sets the elbow to a specific angle
   */
  public void setElbowAngle(double angle) {
    elbowController.setSetpoint(angle);
  }

  /**
   * Gets the shoulder angle
   */
  public double getShoulderAngle() {
    return shoulderEncoder.pidGet();
  }

  /**
   * Gets the elbow angle
   */
  public double getElbowAngle() {
    return elbowEncoder.pidGet();
  }

  /**
   * Sucks the bunny in
   */
  public void suckIn() {
    bunnySucker.set(1);
  }

  /**
   * Spits the bunny out
   */
  public void spitOut() {
    bunnySucker.set(-1);
  }

  /**
   * Stops the intake
   */
  public void stopIntake() {
    bunnySucker.set(0);
  }


}