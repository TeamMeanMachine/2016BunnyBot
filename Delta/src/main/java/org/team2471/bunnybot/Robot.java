package org.team2471.bunnybot;

import org.team2471.bunnybot.subsystems.Arm;
import org.team2471.bunnybot.subsystems.DriveTrain;
import org.team2471.bunnybot.subsystems.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

  public static DriveTrain driveTrain;
  public static Shooter shooter;
  public static Arm arm;


  @Override
  public void robotInit() {
    driveTrain = new DriveTrain();
    shooter = new Shooter();
    arm = new Arm();

    IOMap.init();
  }

  @Override
  public void teleopInit() {
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
