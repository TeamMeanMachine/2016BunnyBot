package org.team2471.bunnybot;

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
    // Commented for now because our test board only has the connections to a shooter.
    // driveTrain = new DriveTrain();
    // grabber = new Grabber();
  }
}
