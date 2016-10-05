package org.team2471.frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team2471.frc.robot.HardwareMap;
import org.team2471.frc.robot.defaultcommands.ShooterDefaultCommand;

public class Shooter extends Subsystem {
  private static final CANTalon panMotor = HardwareMap.Shooter.panMotor;
  private static final CANTalon shootMotor = HardwareMap.Shooter.shootMotor;
  private static final Servo tiltServo = HardwareMap.Shooter.tiltServo;
  private static final AnalogInput ammo = HardwareMap.Shooter.ammo;
  private double currentAngle=0;

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new ShooterDefaultCommand());
  }

  public void enableShooting() {
    shootMotor.set(0.5);
  }

  public void stopShooting() {
    shootMotor.set(0.0);
  }

  public void pan(double power) {
    double axis = power;
    if (Math.abs(axis) >= 0.2) {
      panMotor.set(axis);
    }
    else {
      panMotor.set(0);
    }
  }

  public void tilt(double angleChange) {
    double targetAngle = currentAngle /*tiltServo.getAngle()*/ + angleChange;
    if (targetAngle<70)
      targetAngle=70;
    if (targetAngle>160)
      targetAngle=160;
    currentAngle = targetAngle;
    System.out.println(targetAngle);
    tiltServo.setAngle(targetAngle);
//    if (tiltServo.getAngle() <= 0) {
//      //according to the rule, the angle of the gun cannot be higher than 0 degree
//      //the "zero" of the gun still need to be tested
      //tiltServo.setAngle(targetAngle);
//    }
//    else {
//      tiltServo.set(0);
//    }
  }
}
