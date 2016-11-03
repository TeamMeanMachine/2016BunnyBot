package org.team2471.bunnybot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.SpeedController;
import org.team2471.bunnybot.HardwareMap;
import org.team2471.bunnybot.SwerveModule;

import java.beans.Encoder;

public class DriveTrain {
  private final SwerveModule leftSwerveModule = new SwerveModule(HardwareMap.DriveTrainMap.LeftModule.forwardMotor,
          HardwareMap.DriveTrainMap.LeftModule.turnMotor, HardwareMap.DriveTrainMap.LeftModule.turnEncoder);
  private final SwerveModule rightSwerveModule = new SwerveModule(HardwareMap.DriveTrainMap.RightModule.forwardMotor,
          HardwareMap.DriveTrainMap.RightModule.turnMotor, HardwareMap.DriveTrainMap.RightModule.turnEncoder);

  private final SpeedController frontLeftMotor = HardwareMap.DriveTrainMap.frontLeftMotor;
  private final SpeedController frontRightMotor = HardwareMap.DriveTrainMap.frontRightMotor;
  private final SpeedController backLeftMotor = HardwareMap.DriveTrainMap.backLeftMotor;
  private final SpeedController backRightMotor = HardwareMap.DriveTrainMap.backRightMotor;
  /**
   * Sets the forward speed and the swerve module target angles.
   *
   * @param throttle speed to be set between -1 and 1 (inclusive)
   * @param steeringRate steering rate between -1 and 1 (inclusive)
   */
  public void drive(double throttle, double steeringRate) {
    frontLeftMotor.set(throttle);
    frontRightMotor.set(throttle);
    backLeftMotor.set(throttle);
    backRightMotor.set(throttle);
    leftSwerveModule.drive(steeringRate, throttle);
    rightSwerveModule.drive(steeringRate, throttle);
  }
}
