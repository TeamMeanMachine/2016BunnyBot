package org.team2471.bunnybot;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.IterativeRobot;
import org.team2471.bunnybot.subsystems.DriveTrain;
import org.team2471.bunnybot.subsystems.Shooter;
import org.team2471.bunnybot.subsystems.Arm;

public class Robot extends IterativeRobot {

  public static DriveTrain driveTrain;
  public static Shooter shooter;
  public static Arm arm;

  @Override
  public void robotInit() {
    driveTrain = new DriveTrain();
    shooter = new Shooter();
    arm = new Arm();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }
}
