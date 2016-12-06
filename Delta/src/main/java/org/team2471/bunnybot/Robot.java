package org.team2471.bunnybot;

import org.team2471.bunnybot.subsystem.Arm;

import edu.wpi.first.wpilibj.command.Scheduler;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {
  public static Arm arm;

  @Override
  public void robotInit() {
    arm = new Arm();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }
}
