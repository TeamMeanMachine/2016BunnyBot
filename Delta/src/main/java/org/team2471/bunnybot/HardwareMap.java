package org.team2471.bunnybot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;

public class HardwareMap {

  public static final class Drivetrain {
    public static final CANTalon rightMotor1 = new CANTalon(15);
    public static final CANTalon rightMotor2 = new CANTalon(14);
    public static final CANTalon rightMotor3 = new CANTalon(13);
    public static final CANTalon leftMotor1 = new CANTalon(0);
    public static final CANTalon leftMotor2 = new CANTalon(1);
    public static final CANTalon leftMotor3 = new CANTalon(2);
    public static final Solenoid shiftSolenoid = new Solenoid(0);
  }
}
