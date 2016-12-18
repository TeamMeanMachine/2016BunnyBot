package org.team2471.bunnybot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.team2471.bunnybot.HardwareMap;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.team2471.bunnybot.defaultcommands.ArmDefaultCommand;
import org.team2471.bunnybot.sensors.Magnepot;
//import org.team2471.frc.lib.sensors.Magnepot;

public class Arm extends Subsystem {

  public Magnepot shoulderEncoder = HardwareMap.ArmMap.shoulderEncoder;
  private Magnepot elbowEncoder = HardwareMap.ArmMap.elbowEncoder;
  private CANTalon shoulderMotor = HardwareMap.ArmMap.shoulderMotor;
  private CANTalon elbowMotor = HardwareMap.ArmMap.elbowMotor;
  private CANTalon bunnySucker = HardwareMap.ArmMap.bunnySucker;

  public PIDController shoulderController;
  public PIDController elbowController;

  public Arm() {
    shoulderController = new PIDController( -0.04, -0.0, -0.01, shoulderEncoder, shoulderMotor );
    elbowController = new PIDController( -0.04, -0.0, -0.01, elbowEncoder, elbowMotor );
    shoulderController.setSetpoint(51.0);
    elbowController.setSetpoint(-70.0);
    shoulderController.enable();
    elbowController.enable();

    SmartDashboard.putData("Shoulder PID", shoulderController);
    SmartDashboard.putData("Elbow PID", elbowController);
    shoulderController.setAbsoluteTolerance(10.0);
    elbowController.setAbsoluteTolerance(10.0);
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
  public void suckIn( double power ) {
    bunnySucker.set(-power);
  }

  /**
   * Spits the bunny out
   */
  public void spitOut( double power ) {
    bunnySucker.set( power );
  }

  /**
   * Stops the intake
   */
  public void stopIntake() {
    bunnySucker.set(0);
  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new ArmDefaultCommand());
  }
}