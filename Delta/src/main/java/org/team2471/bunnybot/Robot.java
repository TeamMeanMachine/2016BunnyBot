package org.team2471.bunnybot;

import edu.wpi.first.wpilibj.command.Scheduler;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {

  @Override
  public void robotInit() {
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }
}
