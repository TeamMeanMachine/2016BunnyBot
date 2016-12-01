package org.team2471.bunnybot;

import edu.wpi.first.wpilibj.*;

public class HardwareMap {
  public static final class ShooterMap {
    public static final CANTalon panMotor = new CANTalon(3);
    public static final SpeedController shootMotor = new CANTalon(5);
    public static final AnalogGyro shooterGyro = new AnalogGyro(0);
    public static final Servo tiltMotor = new Servo(9);
    public static final DigitalInput ammoSensor = new DigitalInput(9);
    public static final Solenoid flashLight = new Solenoid(0);
  }

  public static final class DriveTrainMap {
    public static final class LeftModule {
      public static final AnalogInput turnEncoder = new AnalogInput(1);
      public static final CANTalon turnMotor = new CANTalon(15);
      public static final SpeedController forwardMotor = new Talon(8);
    }

    public static final class RightModule {
      public static final AnalogInput turnEncoder = new AnalogInput(2);
      public static final CANTalon turnMotor = new CANTalon(0);
      public static final SpeedController forwardMotor = new Talon(0);
    }

    public static final SpeedController frontLeftMotor = new Talon(6);
    public static final SpeedController frontRightMotor = new Talon(2);
    public static final SpeedController backLeftMotor = new Talon(7);
    public static final SpeedController backRightMotor = new Talon(1);
  }

  public static final class GrabberMap {
    public static final AnalogInput armEncoder = new AnalogInput(1);
    public static final DigitalInput bunnySensor = new DigitalInput(0);
    public static final CANTalon bunnySucker = new CANTalon(0);
    public static final CANTalon leftJointMotor = new CANTalon(0);
    public static final CANTalon rightJointMotor = new CANTalon(0);
  }
}
