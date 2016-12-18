package org.team2471.bunnybot.subsystems;

import com.team254.frc2016.CheesyDriveHelper;
import com.team254.lib.util.DriveSignal;
import org.team2471.bunnybot.defaultcommands.DriveTrainDefaultCommand;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static org.team2471.bunnybot.HardwareMap.DriveTrainMap.*;
import static org.team2471.bunnybot.IOMap.*;

public class DriveTrain extends Subsystem {
  private CheesyDriveHelper cheesyDriveHelper;
  private static final double HIGH_SHIFTPOINT = 250.0;
  private static final double LOW_SHIFTPOINT = 150.0;

  public DriveTrain() {

    rightMotor1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    rightMotor1.configEncoderCodesPerRev((int)(250 * Math.PI * 4.0 / 12));
    rightMotor2.changeControlMode(CANTalon.TalonControlMode.Follower);
    rightMotor3.changeControlMode(CANTalon.TalonControlMode.Follower);
    rightMotor2.set(rightMotor1.getDeviceID());
    rightMotor3.set(rightMotor1.getDeviceID());

    leftMotor1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    leftMotor1.configEncoderCodesPerRev((int)(250 * Math.PI * 4.0 / 12));
    leftMotor2.changeControlMode(CANTalon.TalonControlMode.Follower);
    leftMotor3.changeControlMode(CANTalon.TalonControlMode.Follower);
    leftMotor2.set(leftMotor1.getDeviceID());
    leftMotor3.set(leftMotor1.getDeviceID());

    cheesyDriveHelper = new CheesyDriveHelper();
  }

  public void drive( double dThrottle, double dTurn, double cThrottle, double cTurn, boolean cheesyDrive, boolean quickTurn ) {

    // copilot (never cheesy)
    double cLeft = cThrottle + cTurn;
    double cRight = cThrottle - cTurn;

    if (cheesyDrive) {
      DriveSignal driveSignal = cheesyDriveHelper.cheesyDrive(dThrottle, dTurn, quickTurn);  // left bumper permits quick turn (in place)

      rightMotor1.set(-driveSignal.rightMotor - cRight);
      leftMotor1.set(driveSignal.leftMotor + cLeft);
    }
    else {
      double dLeft = dThrottle + dTurn;
      double dRight = dThrottle - dTurn;

      rightMotor1.set(-dRight - cRight);
      leftMotor1.set(dLeft + cLeft);
    }

    double averageSpeed = getSpeed();
    if (averageSpeed > HIGH_SHIFTPOINT) {
      shiftSolenoid.set(false);  // high gear
    } else if (averageSpeed < LOW_SHIFTPOINT) {
      shiftSolenoid.set(true);
    }

    SmartDashboard.putNumber("Speed", averageSpeed);
    SmartDashboard.putNumber("Left Distance", leftMotor1.getEncPosition());
    SmartDashboard.putNumber("Right Distance", rightMotor1.getEncPosition());
  }

  public void drive(double throttle, double turn) {
    drive(throttle, turn, 0, 0, false, false);
  }

  private double getSpeed() {
    return (Math.abs(leftMotor1.getSpeed()) + Math.abs(rightMotor1.getSpeed())) / 2;
  }
  
  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new DriveTrainDefaultCommand());
  }
}