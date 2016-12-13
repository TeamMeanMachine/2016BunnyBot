package org.team2471.bunnybot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Scheduler;
import org.team2471.bunnybot.subsystems.DriveTrain;
import org.team2471.bunnybot.subsystems.Grabber;
import org.team2471.bunnybot.subsystems.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {
  public static Shooter shooter;
  public static DriveTrain driveTrain;
  public static Grabber grabber;

  @Override
  public void robotInit() {

    shooter = new Shooter();
    driveTrain = new DriveTrain();
    // grabber = new Grabber();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }
}
