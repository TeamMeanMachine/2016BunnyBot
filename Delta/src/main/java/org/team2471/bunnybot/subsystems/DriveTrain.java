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
  private static final double HIGH_SHIFTPOINT = 2.5;
  private static final double LOW_SHIFTPOINT = 2.0;

  private int m_leftStartDistance;
  private int m_rightStartDistance;

  public PIDController m_leftController;
  public PIDController m_rightController;

  private CANTalonQuadPIDSource m_leftPIDSource;
  private CANTalonQuadPIDSource m_rightPIDSource;

  public DriveTrain() {

    leftMotor1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    leftMotor2.changeControlMode(CANTalon.TalonControlMode.Follower);
    leftMotor3.changeControlMode(CANTalon.TalonControlMode.Follower);
    leftMotor2.set(leftMotor1.getDeviceID());
    leftMotor3.set(leftMotor1.getDeviceID());

    rightMotor1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    rightMotor1.setInverted(true);    // does this work? supposed to be for PercentVBus
    rightMotor2.changeControlMode(CANTalon.TalonControlMode.Follower);
    rightMotor3.changeControlMode(CANTalon.TalonControlMode.Follower);
    rightMotor2.set(rightMotor1.getDeviceID());
    rightMotor3.set(rightMotor1.getDeviceID());

    //rightMotor1.configEncoderCodesPerRev( 200 );  // closed loop only
    //rightMotor1.reverseOutput(true);  // I've tried this before, it is only for closed loop on the talon
    //rightMotor1.reverseSensor(true);  // closed loop only

    m_leftPIDSource = new CANTalonQuadPIDSource(leftMotor1);
    m_rightPIDSource = new CANTalonQuadPIDSource(rightMotor1);

    m_leftController = new PIDController( 0.01, 0.0, 0.0, m_leftPIDSource, leftMotor1 );
    m_rightController = new PIDController( 0.01, 0.0, 0.0, m_rightPIDSource, rightMotor1 );

    resetEncoders();

    cheesyDriveHelper = new CheesyDriveHelper();
  }

  public void resetEncoders() {
    m_leftStartDistance = leftMotor1.getEncPosition();
    m_rightStartDistance = rightMotor1.getEncPosition();
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
    SmartDashboard.putNumber("Left Distance", m_leftPIDSource.pidGet());
    SmartDashboard.putNumber("Right Distance", m_rightPIDSource.pidGet());
  }

  public void drive(double throttle, double turn) {
    drive(throttle, turn, 0, 0, false, false);
  }

  private double getSpeed() {
    return (Math.abs(leftMotor1.getSpeed()/82.0) + Math.abs(rightMotor1.getSpeed()/82.0)) / 2;
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
}