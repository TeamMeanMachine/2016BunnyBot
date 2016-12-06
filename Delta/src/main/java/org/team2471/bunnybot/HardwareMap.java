package org.team2471.bunnybot;

import edu.wpi.first.wpilibj.CANTalon;

public class HardwareMap {

  public static final class Drivetrain {
    public static final CANTalon rightmotor1 = new CANTalon(0);
    public static final CANTalon rightmotor2 = new CANTalon(0);
    public static final CANTalon rightmotor3 = new CANTalon(0);
    public static final CANTalon leftmotor1 = new CANTalon(0);
    public static final CANTalon leftmotor2 = new CANTalon(0);
    public static final CANTalon leftmotor3 = new CANTalon(0);

  }
}
