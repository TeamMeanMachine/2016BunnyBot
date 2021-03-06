package org.team2471.bunnybot.subsystems;

import org.team2471.bunnybot.HardwareMap;
import org.team2471.bunnybot.SwerveModule;
import org.team2471.bunnybot.defaultcommands.DriveTrainDefaultCommand;
import org.team2471.frc.lib.vector.Vector2;
import org.team2471.util.BetterMath;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class DriveTrain extends Subsystem {
  private final SwerveModule leftSwerveModule = new SwerveModule(
      HardwareMap.DriveTrainMap.LeftModule.forwardMotor,
      HardwareMap.DriveTrainMap.LeftModule.turnMotor,
      HardwareMap.DriveTrainMap.LeftModule.turnEncoder,
      new Vector2(-14, 19), 0);  // positive adjusts to the right
  private final SwerveModule rightSwerveModule = new SwerveModule(
      HardwareMap.DriveTrainMap.RightModule.forwardMotor,
      HardwareMap.DriveTrainMap.RightModule.turnMotor,
      HardwareMap.DriveTrainMap.RightModule.turnEncoder,
      new Vector2(14, 19), 0);

  private final SpeedController frontLeftMotor = HardwareMap.DriveTrainMap.frontLeftMotor;
  private final SpeedController frontRightMotor = HardwareMap.DriveTrainMap.frontRightMotor;
  private final SpeedController backLeftMotor = HardwareMap.DriveTrainMap.backLeftMotor;
  private final SpeedController backRightMotor = HardwareMap.DriveTrainMap.backRightMotor;

  private final Vector2 pivot = new Vector2(0, -19);

  public DriveTrain() {

//    HardwareMap.DriveTrainMap.LeftModule.turnMotor.configEncoderCodesPerRev(820);
//    HardwareMap.DriveTrainMap.LeftModule.turnMotor.configEncoderCodesPerRev(820);

    SmartDashboard.putData("Left Steer PID", leftSwerveModule.getSteerController());
    SmartDashboard.putData("Right Steer PID", rightSwerveModule.getSteerController());
  }

  /**
   * Sets the forward speed and the swerve module target angles.
   *
   * @param throttle     speed to be set between -1 and 1 (inclusive)
   * @param steeringRate steering rate between -1 and 1 (inclusive)
   * @param cheesyDrive  enable or disable cheezy drive
   */
  public void drive(double throttle, double steeringRate, boolean cheesyDrive) {
    if (cheesyDrive) {
      steeringRate *= throttle;
    }

    double leftPower = throttle + steeringRate;
    double rightPower = throttle - steeringRate;

    double maxPower = BetterMath.max(leftSwerveModule.getPower(throttle, steeringRate),
        rightSwerveModule.getPower(throttle, steeringRate), leftPower, rightPower);
    double factor = 1;
    if (maxPower > 1) {
      factor = 1.0 / maxPower;
    }

    leftSwerveModule.setFactor(factor);
    rightSwerveModule.setFactor(factor);

    frontLeftMotor.set(-leftPower * factor);
    backLeftMotor.set(-leftPower * factor);

    frontRightMotor.set(rightPower * factor);
    backRightMotor.set(rightPower * factor);

    SmartDashboard.putNumber("Left Steer", HardwareMap.DriveTrainMap.LeftModule.turnEncoder.pidGet());
    SmartDashboard.putNumber("Right Steer", HardwareMap.DriveTrainMap.LeftModule.turnEncoder.pidGet());

    SmartDashboard.putNumber("Distance", getDistance());

    leftSwerveModule.setOffset(SmartDashboard.getNumber("Left Offset", 0));
    rightSwerveModule.setOffset(SmartDashboard.getNumber("Right Offset", 0));
  }

  /**
   * Sets the forward speed and the swerve module target angles.
   *
   * @param throttle     speed to be set between -1 and 1 (inclusive)
   * @param steeringRate steering rate between -1 and 1 (inclusive)
   */
  public void drive(double throttle, double steeringRate) {
    drive(throttle, steeringRate, false);
  }

  public Vector2 getPivot() {
    return pivot;
  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new DriveTrainDefaultCommand());
  }

  // VV should only be used for callibration

  public SwerveModule getLeftSwerveModule() {
    return leftSwerveModule;
  }

  public SwerveModule getRightSwerveModule() {
    return rightSwerveModule;
  }

  public double getDistance() {
    return (Math.abs(HardwareMap.DriveTrainMap.LeftModule.turnMotor.getEncPosition()/1500) +
        Math.abs(HardwareMap.DriveTrainMap.RightModule.turnMotor.getEncPosition()/1500)) / 2.0;
//    return (Math.abs(HardwareMap.DriveTrainMap.LeftModule.turnMotor.getPosition()) +
//        Math.abs(HardwareMap.DriveTrainMap.RightModule.turnMotor.getPosition())) / 2.0;
  }
}
