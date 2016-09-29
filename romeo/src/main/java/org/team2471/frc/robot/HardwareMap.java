package org.team2471.frc.robot;

import edu.wpi.first.wpilibj.*;

public class HardwareMap {
  public static class Shooter {
    public static CANTalon shootMotor = new CANTalon(8);
    public static CANTalon panMotor = new CANTalon(9);
    public static Servo tiltServo = new Servo(0);
    public static AnalogInput ammo = new AnalogInput(0);
  }
}
