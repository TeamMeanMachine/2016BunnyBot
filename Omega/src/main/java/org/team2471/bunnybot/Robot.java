package org.team2471.bunnybot;

import org.team2471.bunnybot.subsystems.DriveTrain;
import org.team2471.bunnybot.subsystems.Grabber;
import org.team2471.bunnybot.subsystems.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {
  public static Shooter shooter;
  public static DriveTrain driveTrain;
  public static Grabber grabber;

  @Override
  public void robotInit() {
    // Ensure we get logs when we crash here
    FRCNetworkCommunicationsLibrary.FRCNetworkCommunicationObserveUserProgramStarting();

    SmartDashboard.putNumber("Arm Resting Angle", 0);
    SmartDashboard.putNumber("Arm Burrow Angle", 100);
    SmartDashboard.putNumber("Arm Intake Angle", 212);

    shooter = new Shooter();
    driveTrain = new DriveTrain();
    grabber = new Grabber();

    HardwareMap.init();
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void testInit() {
    System.out.println("Left: " + HardwareMap.DriveTrainMap.LeftModule.turnEncoder.getVoltage());
    System.out.println("Right: " + HardwareMap.DriveTrainMap.RightModule.turnEncoder.getVoltage());
  }
}
