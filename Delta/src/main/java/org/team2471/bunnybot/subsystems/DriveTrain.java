package org.team2471.bunnybot.subsystems;

import com.team254.frc2016.CheesyDriveHelper;
import com.team254.lib.util.DriveSignal;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import org.team2471.bunnybot.defaultcommands.DriveTrainDefaultCommand;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static org.team2471.bunnybot.HardwareMap.DriveTrainMap.*;
import static org.team2471.bunnybot.IOMap.*;

public class DriveTrain extends Subsystem {

  private CheesyDriveHelper cheesyDriveHelper;
  public static final double HIGH_SHIFTPOINT = 5.0;  // 250
  public static final double LOW_SHIFTPOINT = 2.0;  // 150

  private int m_leftStartDistance;
  private int m_rightStartDistance;

  public DriveTrain() {

    leftMotor1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    leftMotor2.changeControlMode(CANTalon.TalonControlMode.Follower);
    leftMotor3.changeControlMode(CANTalon.TalonControlMode.Follower);
    leftMotor2.set(leftMotor1.getDeviceID());
    leftMotor3.set(leftMotor1.getDeviceID());

    rightMotor1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    rightMotor1.setInverted(true);    // this one is for PercentVBus
    rightMotor2.changeControlMode(CANTalon.TalonControlMode.Follower);
    rightMotor3.changeControlMode(CANTalon.TalonControlMode.Follower);
    rightMotor2.set(rightMotor1.getDeviceID());
    rightMotor3.set(rightMotor1.getDeviceID());

    leftMotor1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    leftMotor1.reverseSensor(false);
    leftMotor1.reverseOutput(false);
    leftMotor1.configEncoderCodesPerRev(820 / 4);
    leftMotor1.setProfile(1);
    leftMotor1.setF(0);
    leftMotor1.setPID(1.5, 0, 4.0);
    leftMotor1.setProfile(0);
    leftMotor1.setF(0);
    leftMotor1.setPID(2.0, 0, 2.0);

    rightMotor1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    rightMotor1.reverseSensor(true);
    rightMotor1.reverseOutput(true);
    rightMotor1.configEncoderCodesPerRev(820 / 4);
    rightMotor1.setProfile(1);
    rightMotor1.setF(0);
    rightMotor1.setPID(1.5, 0, 4.0);
    rightMotor1.setProfile(0);
    rightMotor1.setF(0);
    rightMotor1.setPID(2.0, 0, 2.0);


    resetEncoders();

    cheesyDriveHelper = new CheesyDriveHelper();
  }

  public void resetEncoders() {
    m_leftStartDistance = leftMotor1.getEncPosition();
    m_rightStartDistance = rightMotor1.getEncPosition();
  }

  public void drive( double dTurn, double dThrottle, double cTurn, double cThrottle, boolean cheesyDrive, boolean quickTurn ) {

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


    SmartDashboard.putNumber("Left Distance", leftMotor1.getEncPosition() / 820.0);  // works regardless of control mode
    SmartDashboard.putNumber("Right Distance", rightMotor1.getEncPosition() / 820.0);
  }

  public void drive(double throttle, double turn) {
    drive(throttle, turn, 0, 0, false, false);
  }

  public double getSpeed() {
    return (Math.abs(leftMotor1.getEncVelocity()/82.0) + Math.abs(rightMotor1.getEncVelocity()/82.0)) / 2;
    // encoders return speed as
    // edges / 100 ms
    // * 1 foot / 820 edges (see below)
    // * 1000 ms / 1 sec
    // yields ft / sec
  }
  
  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new DriveTrainDefaultCommand());
  }

/*
  class CANTalonQuadPIDSource implements PIDSource {

    CANTalon m_talon;

    CANTalonQuadPIDSource( CANTalon talon ) {
      m_talon = talon;
    }

    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {
    }

    @Override
    public PIDSourceType getPIDSourceType() {
      return null;
    }

    @Override
    public double pidGet() {
      return m_talon.getEncPosition() / 820.0;
      // returns edge count
      // 1 tick / 4 edges
      // 1 rev / 200 ticks
      // 4 inches * 3.14 / 1 rev
      // 1 foot / 12 inches
      // = 1 foot / 837 edges
    }
  }
*/
}