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
  private double currentAngle = 0;

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
      panMotor.set(power);
  }

  public void tilt(double angleChange) {
    double targetAngle = currentAngle + angleChange;
    if (targetAngle < 70) {
      targetAngle = 70;
    }
    if (targetAngle > 160) {
      targetAngle = 160;
    }
    currentAngle = targetAngle;
    tiltServo.setAngle(targetAngle);
  }
}
