package org.team2471.bunnybot.subsystem;

import com.team254.frc2016.CheesyDriveHelper;
import com.team254.lib.util.DriveSignal;
import defaultcommands.DriveTrainDefaultCommand;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team2471.bunnybot.IOMap;

import static org.team2471.bunnybot.HardwareMap.Drivetrain.*;

public class DriveTrain extends Subsystem {
  private CheesyDriveHelper cheesyDriveHelper;
  private static final double HIGH_SHIFTPOINT = 400.0;
  private static final double LOW_SHIFTPOINT = 325.0;

  public DriveTrain() {

    //rightMotor1.reverseOutput(true);
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

  public void drive(double throttle, double turn, boolean cheesyDrive) {
    if (cheesyDrive) { //SmartDashboard.getBoolean("CheesyDrive", true)) {              // left bumper permits quick turn (in place)
      DriveSignal driveSignal = cheesyDriveHelper.cheesyDrive(throttle, turn, IOMap.mainController.getButton(4).get());

      rightMotor1.set(-driveSignal.rightMotor);
      leftMotor1.set(driveSignal.leftMotor);
    } else {
      double left = throttle + turn;
      double right = throttle - turn;

      rightMotor1.set(-right);
      leftMotor1.set(left);

      double averageSpeed = (Math.abs(leftMotor1.getSpeed()) + Math.abs(rightMotor1.getSpeed())) / 2;
      if (averageSpeed > HIGH_SHIFTPOINT) {
        shiftSolenoid.set(false);
      } else if (averageSpeed < LOW_SHIFTPOINT) {
        shiftSolenoid.set(true);
      }
      SmartDashboard.putNumber("Speed", averageSpeed);
    }
  }

  public void drive(double throttle, double turn) {
    drive(throttle, turn, false);
  }

  private double getSpeed() {
    return ( leftMotor1.getSpeed() + rightMotor1.getSpeed() ) / 2.0;
  }
  
  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new DriveTrainDefaultCommand());
  }
}