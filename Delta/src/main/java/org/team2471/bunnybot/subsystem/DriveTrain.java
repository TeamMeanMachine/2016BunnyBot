package org.team2471.bunnybot.subsystem;

import defaultcommands.DriveTrainDefaultCommand;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

import static org.team2471.bunnybot.HardwareMap.Drivetrain.*;

public class DriveTrain extends Subsystem {

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

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new DriveTrainDefaultCommand());
  }

  public void drive(double throttle, double turn) {
    double left = throttle + turn;
    double right = throttle - turn;

    rightMotor1.set(right);
    leftMotor1.set(left);

  }


}