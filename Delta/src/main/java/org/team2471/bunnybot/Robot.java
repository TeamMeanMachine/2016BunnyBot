package org.team2471.bunnybot;

import edu.wpi.first.wpilibj.Preferences;
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
  Preferences prefs;

  @Override
  public void robotInit() {
    SmartDashboard.putBoolean("Disable Cheesy Drive", false);
    driveTrain = new DriveTrain();
    shooter = new Shooter();
    arm = new Arm();
    prefs = Preferences.getInstance();

    //SmartDashboard.putBoolean("Cheesy Drive", prefs.getBoolean("Cheesy Drive", true));

    //IOMap.init();
  }

  @Override
  public void teleopInit() {
    // tune the drive position PID's
    SmartDashboard.putData("Left PID", driveTrain.m_leftController);
    SmartDashboard.putData("Right PID", driveTrain.m_rightController);
//    driveTrain.m_leftController.enable();
//    driveTrain.m_rightController.enable();
  }

  @Override
  public void teleopPeriodic() {
    //Scheduler.getInstance().run();

    // tune the drive position PID's
//    driveTrain.m_leftController.setSetpoint( IOMap.driveController.getAxis(1).get() * 4.0);
//    driveTrain.m_rightController.setSetpoint( IOMap.driveController.getAxis(3).get() * 4.0);
  }

  @Override
  public void disabledInit() {
    prefs.putBoolean("Cheesy Drive", SmartDashboard.getBoolean("Cheesy Drive", true));
  }

  @Override
  public void disabledPeriodic() {
    SmartDashboard.putNumber("Elbow Angle", arm.getElbowAngle());
    SmartDashboard.putNumber("Shoulder Angle", arm.getShoulderAngle());
  }
}
