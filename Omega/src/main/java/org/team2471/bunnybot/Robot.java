package org.team2471.bunnybot;

import org.team2471.bunnybot.autonomouscommands.BunnyAuto;
import org.team2471.bunnybot.autonomouscommands.DriveLeftAuto;
import org.team2471.bunnybot.autonomouscommands.DriveRightAuto;
import org.team2471.bunnybot.autonomouscommands.PickupBunnyAuto;
import org.team2471.bunnybot.commands.CallibrationCommand;
import org.team2471.bunnybot.subsystems.DriveTrain;
import org.team2471.bunnybot.subsystems.Grabber;
import org.team2471.bunnybot.subsystems.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {
  public static Shooter shooter;
  public static DriveTrain driveTrain;
  public static Grabber grabber;
  public static SendableChooser autoChooser;
  Command autonomousCommand;
  Preferences preferences;

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

    IOMap.getInstance();
    HardwareMap.init();

    autoChooser = new SendableChooser();
    autoChooser.addDefault("Bunny Auto",new BunnyAuto());
    autoChooser.addObject("Drive Left Auto",new DriveLeftAuto());
    autoChooser.addObject("Drive Right Auto",new DriveRightAuto());
    autoChooser.addObject("Pick Up Bunny Auto",new PickupBunnyAuto());
    SmartDashboard.putData("AutoChooser", autoChooser);

    SmartDashboard.putData("Callibrate", new CallibrationCommand());

    preferences = Preferences.getInstance();
    SmartDashboard.putNumber("Left Offset", preferences.getDouble("Left Offset", 12.0));
    SmartDashboard.putNumber("Right Offset", preferences.getDouble("Right Offset", 2.0));

  }

  @Override
  public void disabledInit() {
    preferences.putDouble("Left Offset", SmartDashboard.getNumber("Left Offset", 0));
    preferences.putDouble("Right Offset", SmartDashboard.getNumber("Right Offset", 0));

  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void autonomousInit() {
    autonomousCommand = (Command)autoChooser.getSelected();
    autonomousCommand.start();
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void disabledPeriodic() {
    SmartDashboard.putNumber("Left Steer", HardwareMap.DriveTrainMap.LeftModule.turnEncoder.pidGet());
    SmartDashboard.putNumber("Right Steer", HardwareMap.DriveTrainMap.RightModule.turnEncoder.pidGet());
  }

  @Override
  public void testInit() {
    new CallibrationCommand().start();
    System.out.println("Left: " + HardwareMap.DriveTrainMap.LeftModule.turnEncoder.pidGet());
    System.out.println("Right: " + HardwareMap.DriveTrainMap.RightModule.turnEncoder.pidGet());
  }


  @Override
  public void testPeriodic() {
    Scheduler.getInstance().run();
  }
}
