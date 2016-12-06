package org.team2471.bunnybot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;

public class HardwareMap {

  public static final class ArmMap{
    public static final AnalogInput shoulderEncoder = new AnalogInput(1);
    public static final AnalogInput elbowEncoder = new AnalogInput(2);
    public static final CANTalon shoulderMotor = new CANTalon(3);
    public static final CANTalon elbowMotor = new CANTalon(4);
    public static final CANTalon bunnySucker = new CANTalon(5);
  }
}
