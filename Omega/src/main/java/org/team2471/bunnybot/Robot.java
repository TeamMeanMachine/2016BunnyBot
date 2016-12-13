package org.team2471.bunnybot;

import org.team2471.bunnybot.commandgroups.IntakeCommandGroup;
import org.team2471.bunnybot.subsystems.DriveTrain;
import org.team2471.bunnybot.subsystems.Grabber;
import org.team2471.bunnybot.subsystems.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
  public static Shooter shooter;
  public static DriveTrain driveTrain;
  public static Grabber grabber;

  @Override
  public void robotInit() {
    SmartDashboard.putNumber("Arm Resting Angle", 0);
    SmartDashboard.putNumber("Arm Burrow Angle", 100);
    SmartDashboard.putNumber("Arm Intake Angle", 205);

    System.out.println(new Joystick(0).getButtonCount());
//    shooter = new Shooter();
    driveTrain = new DriveTrain();
    grabber = new Grabber();

    // temporary hack must die
//    new JoystickButton(new Joystick(0), 6).whileHeld(new IntakeCommandGroup());
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
}
