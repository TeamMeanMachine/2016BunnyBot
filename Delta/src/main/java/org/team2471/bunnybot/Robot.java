package org.team2471.bunnybot;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team2471.bunnybot.subsystems.DriveTrain;
import org.team2471.bunnybot.subsystems.Shooter;
import org.team2471.bunnybot.subsystems.Arm;

public class Robot extends IterativeRobot {

  public static DriveTrain driveTrain;
  public static Shooter shooter;
  public static Arm arm;

  public static IOMap ioMap;

  @Override
  public void robotInit() {
    driveTrain = new DriveTrain();
    shooter = new Shooter();
    arm = new Arm();
  }

  @Override
  public void teleopInit() {
    ioMap = new IOMap();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    SmartDashboard.putNumber("Elbow Angle", arm.getElbowAngle());
    SmartDashboard.putNumber("Shoulder Angle", arm.getShoulderAngle());
  }
}
