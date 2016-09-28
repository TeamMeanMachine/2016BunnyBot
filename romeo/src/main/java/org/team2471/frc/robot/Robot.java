package org.team2471.frc.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends IterativeRobot {
  /**
   * Robot-wide initialization code should go here.
   */
  @Override
  public void robotInit() {
  }

  /**
   * Initialization code for teleop mode should go here.
   */
  @Override
  public void teleopInit() {
  }

  /**
   * Periodic code for teleop mode should go here.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }
}
