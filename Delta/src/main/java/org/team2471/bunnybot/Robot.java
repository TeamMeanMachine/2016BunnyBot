package org.team2471.bunnybot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import org.team2471.bunnybot.autonomouscommands.*;
import org.team2471.bunnybot.subsystems.Arm;
import org.team2471.bunnybot.subsystems.DriveTrain;
import org.team2471.bunnybot.subsystems.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static org.team2471.bunnybot.HardwareMap.DriveTrainMap.leftMotor1;
import static org.team2471.bunnybot.HardwareMap.DriveTrainMap.rightMotor1;

public class Robot extends IterativeRobot {
  public static DriveTrain driveTrain;
  public static Shooter shooter;
  public static Arm arm;
  private static Preferences prefs;
  public static SendableChooser autoChooser;
  Command autonomousCommand;

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

    // make sure IOMap is initialized
    IOMap.getInstance();

    autoChooser = new SendableChooser();
    autoChooser.addDefault("Forward Six Feet", new DriveSixFeet(1.0));
    autoChooser.addObject("Don't Move", new DoNothingAuto());
    autoChooser.addObject("Figure Eight", new FigureEightCommand(1.0));
    autoChooser.addObject("Bunnies", new BunniesAuto());
    autoChooser.addObject("Bunny", new BunnyAuto());
    autoChooser.addObject("U Shape Left", new DriveArroundCanLeft(1.0));
    autoChooser.addObject("U Shape Right", new DriveArroundCanRight(1.0));
    SmartDashboard.putData("AutoChooser", autoChooser);
  }

  @Override
  public void teleopInit() {
    // tune the drive position PID's
    SmartDashboard.putData("Left PID", leftMotor1);
    SmartDashboard.putData("Right PID", rightMotor1);

    HardwareMap.DriveTrainMap.leftMotor1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    HardwareMap.DriveTrainMap.rightMotor1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    HardwareMap.DriveTrainMap.leftMotor1.set(0);
    HardwareMap.DriveTrainMap.rightMotor1.set(0);
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
  public void autonomousInit() {
    if (autoChooser != null) {
      autonomousCommand = (Command) autoChooser.getSelected();
      if (autonomousCommand != null) {
        autonomousCommand.start();
      }
    }
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void disabledPeriodic() {
    SmartDashboard.putNumber("Elbow Angle", arm.getElbowAngle());
    SmartDashboard.putNumber("Shoulder Angle", arm.getShoulderAngle());
    SmartDashboard.putNumber("Left Distance", leftMotor1.getEncPosition() / 820.0);  // works regardless of control mode
    SmartDashboard.putNumber("Right Distance", rightMotor1.getEncPosition() / 820.0);
  }
}
