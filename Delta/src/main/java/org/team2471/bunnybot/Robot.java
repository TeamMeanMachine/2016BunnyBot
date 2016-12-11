package org.team2471.bunnybot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import org.team2471.bunnybot.subsystems.DriveTrain;
import org.team2471.bunnybot.subsystems.Shooter;

public class Robot extends IterativeRobot {
  public static DriveTrain driveTrain;
  public static Shooter shooter;

  @Override
  public void robotInit() {
    driveTrain = new DriveTrain();
    shooter = new Shooter();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }
}
