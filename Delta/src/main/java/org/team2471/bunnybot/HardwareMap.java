package org.team2471.bunnybot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;

import org.team2471.bunnybot.sensors.Magnepot;
import org.team2471.frc.lib.sensors.CANController;
//import org.team2471.frc.lib.sensors.Magnepot;


public class HardwareMap {

  public static final class ArmMap {
    public static final Magnepot shoulderEncoder = new Magnepot(2);
    public static final Magnepot elbowEncoder = new Magnepot(3);
    public static final CANTalon shoulderMotor = new CANTalon(3);
    public static final CANTalon elbowMotor = new CANTalon(9);
    public static final CANTalon bunnySucker = new CANTalon(4);
  }

  public static final class DriveTrainMap {
    public static final CANController rightMotor1 = new CANController(15);
    public static final CANTalon rightMotor2 = new CANTalon(14);
    public static final CANTalon rightMotor3 = new CANTalon(13);
    public static final CANController leftMotor1 = new CANController(0);
    public static final CANTalon leftMotor2 = new CANTalon(1);
    public static final CANTalon leftMotor3 = new CANTalon(2);
    public static final Solenoid shiftSolenoid = new Solenoid(0);
  }

  public static final class ShooterMap {
    public static final CANTalon shooterMotor = new CANTalon(8);
  }
}
