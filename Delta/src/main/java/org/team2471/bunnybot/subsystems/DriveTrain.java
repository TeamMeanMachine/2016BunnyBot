package org.team2471.bunnybot.subsystems;

import com.team254.frc2016.CheesyDriveHelper;
import com.team254.lib.util.DriveSignal;
import org.team2471.bunnybot.defaultcommands.DriveTrainDefaultCommand;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team2471.bunnybot.IOMap;

import static org.team2471.bunnybot.HardwareMap.Drivetrain.*;
import static org.team2471.bunnybot.IOMap.*;

public class DriveTrain extends Subsystem {
  private CheesyDriveHelper cheesyDriveHelper;
  private static final double HIGH_SHIFTPOINT = 400.0;
  private static final double LOW_SHIFTPOINT = 325.0;

  public DriveTrain() {

    rightMotor2.changeControlMode(CANTalon.TalonControlMode.Follower);
    rightMotor3.changeControlMode(CANTalon.TalonControlMode.Follower);
    rightMotor2.set(rightMotor1.getDeviceID());
    rightMotor3.set(rightMotor1.getDeviceID());

    leftMotor2.changeControlMode(CANTalon.TalonControlMode.Follower);
    leftMotor3.changeControlMode(CANTalon.TalonControlMode.Follower);
    leftMotor2.set(leftMotor1.getDeviceID());
    leftMotor3.set(leftMotor1.getDeviceID());

    cheesyDriveHelper = new CheesyDriveHelper();
  }

  public void drive(double throttle, double turn) {
    if (SmartDashboard.getBoolean("CheesyDrive", true)) {              // left bumper permits quick turn (in place)
      DriveSignal driveSignal = cheesyDriveHelper.cheesyDrive(throttle, turn, driveController.getButton(4).get());  // is there a way to declare a button in IO, so that all the constants remain there.

      rightMotor1.set(-driveSignal.rightMotor);
      leftMotor1.set(driveSignal.leftMotor);
    }
    else {
      double left = throttle + turn;
      double right = throttle - turn;

      rightMotor1.set(-right);
      leftMotor1.set(left);
    }

    double averageSpeed = getSpeed();
    if (averageSpeed > HIGH_SHIFTPOINT) {
      shiftSolenoid.set(false);
    } else if (averageSpeed < LOW_SHIFTPOINT) {
      shiftSolenoid.set(true);
    }
    SmartDashboard.putNumber("Speed", averageSpeed);
  }

  private double getSpeed() {
    return (Math.abs(leftMotor1.getSpeed()) + Math.abs(rightMotor1.getSpeed())) / 2;
  }
  
  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new DriveTrainDefaultCommand());
  }
}