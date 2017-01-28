package org.team2471.bunnybot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PrintCommand;
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
    autoChooser.addObject("Don't Move", new DoNothingAuto());
    autoChooser.addObject("Drive to Hopper", new DriveToHopperAuto(1.0));
    autoChooser.addObject("Drive Eight Feet", new DriveToHopperAuto(1.0));
    autoChooser.addObject("Drive to left Lift", new DriveToLeftLift(1.0));
    autoChooser.addObject("Drive to right Lift", new DriveToRightLift(1.0));
    autoChooser.addObject("Drive to middle lift", new DriveToLift(1.0));
    autoChooser.addObject("One Hundred point Auto", new OneHundredPointAuto());
    autoChooser.addObject("Drop off gear and go to far Hopper", new GearPlusFarHopper());
    autoChooser.addObject("Drive backwards,red alliance", new DriveBackwardsFromRLToFarHopper(1.0));
    SmartDashboard.putData("AutoChooser", autoChooser);

    SmartDashboard.putNumber("Shoulder Offset", prefs.getDouble("Shoulder Offset", 0.0));
    SmartDashboard.putNumber("Elbow Offset", prefs.getDouble("Elbow Offset", 0.0));
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

    prefs.putDouble("Shoulder Offset", SmartDashboard.getNumber("Shoulder Offset", 0.0));
    prefs.putDouble("Elbow Offset", SmartDashboard.getNumber("Elbow Offset", 0.0));
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
