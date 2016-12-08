package org.team2471.bunnybot.subsystem;

import com.team254.frc2016.CheesyDriveHelper;
import com.team254.lib.util.DriveSignal;
import defaultcommands.DriveTrainDefaultCommand;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static org.team2471.bunnybot.HardwareMap.Drivetrain.*;

public class DriveTrain extends Subsystem {
  private CheesyDriveHelper cheesyDriveHelper;

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
   if (SmartDashboard.getBoolean("CheesyDrive", false)) {
     DriveSignal driveSignal = cheesyDriveHelper.cheesyDrive(throttle, turn, false);

     rightMotor1.set(driveSignal.rightMotor);
     leftMotor1.set(driveSignal.leftMotor);
   }
   else {
     double left = throttle + turn;
     double right = throttle - turn;

     rightMotor1.set(right);
     leftMotor1.set(left);
   }
  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new DriveTrainDefaultCommand());
  }


}