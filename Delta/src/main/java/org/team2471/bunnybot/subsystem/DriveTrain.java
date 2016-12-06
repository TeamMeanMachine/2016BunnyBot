package org.team2471.bunnybot.subsystem;

import edu.wpi.first.wpilibj.CANTalon;
import org.team2471.bunnybot.HardwareMap.Drivetrain;

public class DriveTrain{
  private CANTalon rightMotor1 = Drivetrain.rightmotor1;
  private CANTalon rightMotor2 = Drivetrain.rightmotor2;
  private CANTalon rightMotor3 = Drivetrain.rightmotor3;
  private CANTalon leftMotor1 = Drivetrain.leftmotor1;
  private CANTalon leftMotor2 = Drivetrain.leftmotor2;
  private CANTalon leftMotor3 = Drivetrain.leftmotor3;

  public DriveTrain() {
    rightMotor2.changeControlMode(CANTalon.TalonControlMode.Follower);
    rightMotor3.changeControlMode(CANTalon.TalonControlMode.Follower);
    rightMotor2.set(rightMotor1.getDeviceID());
    rightMotor3.set(rightMotor1.getDeviceID());

    leftMotor2.changeControlMode(CANTalon.TalonControlMode.Follower);
    leftMotor3.changeControlMode(CANTalon.TalonControlMode.Follower);
    leftMotor2.set(leftMotor1.getDeviceID());
    leftMotor3.set(leftMotor1.getDeviceID());
  }

  public void drive(double throttle, double turn) {
    double left = throttle + turn;
    double right = throttle - turn;

    rightMotor1.set(right);
    leftMotor1.set(left);

  }


}