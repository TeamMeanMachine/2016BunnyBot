package org.team2471.frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team2471.frc.robot.HardwareMap;

public class Shooter extends Subsystem {
  private static final CANTalon panMotor = HardwareMap.Shooter.panMotor;
  private static final CANTalon shootMotor = HardwareMap.Shooter.shootMotor;
  private static final Servo tiltServo = HardwareMap.Shooter.tiltServo;
  private static final AnalogInput ammo = HardwareMap.Shooter.ammo;

  @Override
  protected void initDefaultCommand() {

  }

  public void shoot() {
    shootMotor.set(0.5);
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

  public void steer(double angle) {
    double targetAngle = tiltServo.getAngle() + angle;
    if (tiltServo.getAngle() <= 0) {
      //according to the rule, the angle of the gun cannot be higher than 0 degree
      //the "zero" of the gun still need to be tested
      tiltServo.set(targetAngle);
    }
    else {
      tiltServo.set(0);
    }
  }
}
