package org.team2471.bunnybot;

import edu.wpi.first.wpilibj.command.Scheduler;

import edu.wpi.first.wpilibj.IterativeRobot;
import org.team2471.bunnybot.subsystem.DriveTrain;

public class Robot extends IterativeRobot {
  public static DriveTrain driveTrain;

  @Override
  public void robotInit() {
    driveTrain = new DriveTrain();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }
}
