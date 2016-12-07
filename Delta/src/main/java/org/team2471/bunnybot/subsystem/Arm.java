package org.team2471.bunnybot.subsystem;

import org.team2471.bunnybot.HardwareMap;
import org.team2471.bunnybot.util.MagnepotPIDSource;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm {

  private AnalogInput shoulderEncoder = HardwareMap.ArmMap.shoulderEncoder;
  private AnalogInput elbowEncoder = HardwareMap.ArmMap.elbowEncoder;
  private CANTalon shoulderMotor = HardwareMap.ArmMap.shoulderMotor;
  private CANTalon elbowMotor = HardwareMap.ArmMap.elbowMotor;
  private CANTalon bunnySucker = HardwareMap.ArmMap.bunnySucker;
  private PIDController shoulderPID = new PIDController(0,0,0, new MagnepotPIDSource(shoulderEncoder),shoulderMotor);
  private PIDController elbowPID = new PIDController(0,0,0, new MagnepotPIDSource(elbowEncoder),elbowMotor);

  public Arm() {
    SmartDashboard.putData("Shoulder PID", shoulderPID);
    SmartDashboard.putData("Elbow PID", elbowPID);
  }
  /** Sets the shoulder to a specific angle*/
  public void setShoulderAngle(double angle) {shoulderPID.setSetpoint(angle);}

  /** Sets the elbow to a specific angle */
  public void setElbowAngle(double angle) {elbowPID.setSetpoint(angle);}

  /** Gets the shoulder angle*/
  public double getShoulderAngle() {return (shoulderEncoder.getVoltage()-2.5)/2.3*180;}

  /** Gets the elbow angle*/
  public double getElbowAngle() {return (elbowEncoder.getVoltage()-2.5)/2.3*180;}

  /** Sucks the bunny in*/
  public void suckIn() {bunnySucker.set(1);}

  /** Spits the bunny out*/
  public void spitOut() {bunnySucker.set(-1);}

  /** Stops the intake*/
  public void stopIntake() {bunnySucker.set(0);}


}