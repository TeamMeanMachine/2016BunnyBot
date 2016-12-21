package org.team2471.bunnybot;

import edu.wpi.first.wpilibj.Preferences;
import org.team2471.bunnybot.subsystems.Arm;
import org.team2471.bunnybot.subsystems.DriveTrain;
import org.team2471.bunnybot.subsystems.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
  public static DriveTrain driveTrain;
  public static Shooter shooter;
  public static Arm arm;
  public static Preferences prefs;

  @Override
  public void robotInit() {
    // Ensure we get logs when we crash here
    FRCNetworkCommunicationsLibrary.FRCNetworkCommunicationObserveUserProgramStarting();

    SmartDashboard.putBoolean("Disable Cheesy Drive", false);
    driveTrain = new DriveTrain();
    shooter = new Shooter();
    arm = new Arm();
    prefs = Preferences.getInstance();

    SmartDashboard.putBoolean("Cheesy Drive", prefs.getBoolean("Cheesy Drive", true));
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
    prefs.putBoolean("Cheesy Drive", SmartDashboard.getBoolean("Cheesy Drive", true));
  }

  @Override
  public void disabledPeriodic() {
    SmartDashboard.putNumber("Elbow Angle", arm.getElbowAngle());
    SmartDashboard.putNumber("Shoulder Angle", arm.getShoulderAngle());
  }
}
