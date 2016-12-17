package org.team2471.bunnybot.subsystems;

import org.team2471.bunnybot.HardwareMap;
import org.team2471.bunnybot.defaultcommands.ArmDefaultCommand;
import org.team2471.bunnybot.util.Magnepot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm extends Subsystem {

  private Magnepot shoulderEncoder = HardwareMap.ArmMap.shoulderEncoder;
  private Magnepot elbowEncoder = HardwareMap.ArmMap.elbowEncoder;
  private CANTalon shoulderMotor = HardwareMap.ArmMap.shoulderMotor;
  public PIDController shoulderController = new PIDController(-0.04, -0.0, -0.01, shoulderEncoder, shoulderMotor);
  private CANTalon elbowMotor = HardwareMap.ArmMap.elbowMotor;
  public PIDController elbowController = new PIDController(-0.04, -0.0, -0.01, elbowEncoder, elbowMotor);
  private CANTalon bunnySucker = HardwareMap.ArmMap.bunnySucker;

  public Arm() {
    SmartDashboard.putData("Shoulder PID", shoulderController);
    SmartDashboard.putData("Elbow PID", elbowController);
    shoulderController.setAbsoluteTolerance(2.0);
    elbowController.setAbsoluteTolerance(2.0);
  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new ArmDefaultCommand());
  }

  /**
   * Gets the shoulder angle
   */
  public double getShoulderAngle() {
    return shoulderEncoder.pidGet();
  }

  /**
   * Sets the shoulder to a specific angle
   */
  public void setShoulderAngle(double angle) {
    shoulderController.setSetpoint(angle);
  }

  /**
   * Gets the elbow angle
   */
  public double getElbowAngle() {
    return elbowEncoder.pidGet();
  }

  /**
   * Sets the elbow to a specific angle
   */
  public void setElbowAngle(double angle) {
    elbowController.setSetpoint(angle);
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