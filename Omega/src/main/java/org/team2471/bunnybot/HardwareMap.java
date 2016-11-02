package org.team2471.bunnybot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedController;

public class HardwareMap {
  // TODO: Provide proper IDs
  public static final class ShooterMap {
    public static final CANTalon panMotor = new CANTalon(0);
    public static final SpeedController shootMotor = new CANTalon(0);
    public static final AnalogGyro shooterGyro = new AnalogGyro(0);
    public static final Servo tiltMotor = new Servo(0);
    public static final AnalogInput ammoSensor = new AnalogInput(0);
  }

  public static final class DriveTrainMap {
    public static final class LeftModule {
      public static final AnalogInput turnEncoder = new AnalogInput(0);
      public static final CANTalon turnMotor = new CANTalon(0);
      public static final CANTalon forwardMotor = new CANTalon(0);
    }

    public static final class RightModule {
      public static final AnalogInput turnEncoder = new AnalogInput(0);
      public static final CANTalon turnMotor = new CANTalon(0);
      public static final CANTalon forwardMotor = new CANTalon(0);
    }

    public static final SpeedController frontLeftMotor = new CANTalon(0);
    public static final SpeedController frontRightMotor = new CANTalon(0);
    public static final SpeedController backLeftMotor = new CANTalon(0);
    public static final SpeedController backRightMotor = new CANTalon(0);
  }

  // TODO: complete this
  public static final class GrabberMap {

  }
}
