package org.team2471.frc.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import org.team2471.frc.robot.subsystems.Shooter;

public class Robot extends IterativeRobot {
  public static Shooter shooter;
  /**
   * Robot-wide initialization code should go here.
   */
  @Override
  public void robotInit() {
    shooter = new Shooter();
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
