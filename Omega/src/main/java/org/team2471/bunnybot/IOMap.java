package org.team2471.bunnybot;

import org.team2471.bunnybot.commandgroups.IntakeCommandGroup;
import org.team2471.bunnybot.commands.GrabberToAngleCommand;
import org.team2471.bunnybot.commands.GrabberZeroArmCommand;
import org.team2471.frc.lib.control.DriveAxis;
import org.team2471.frc.lib.control.DriveButton;
import org.team2471.frc.lib.control.DriveController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IOMap {
  private static final DriveController mainController = new DriveController(0)
      .withRunCommandWhileButtonHoldEvent(6, new IntakeCommandGroup())
      .withRunCommandOnButtonReleaseEvent(6, new GrabberToAngleCommand(SmartDashboard.getNumber("Arm Resting Angle", 0)));

  public static final DriveButton spitoutButton = mainController.getButton(2);
  public static final DriveButton backupButton = mainController.getButton(8);
  public static final DriveButton noCheesyDriveButton = mainController.getButton(10);


  public static final DriveAxis throttleAxis = mainController.getAxis(1)
      .withDeadband(0.2)
      .withExponentialScaling(2)
      .withInvert();

  public static final DriveAxis turnAxis =
      mainController.getAxis(4)
          .withDeadband(0.2)
          .withExponentialScaling(2); // scale down

  /* Co Pilot */

  private static final DriveController coController = new DriveController(1)
      .withRunCommandWhileButtonHoldEvent(8, new GrabberZeroArmCommand());

  public static final DriveButton shootButton = coController.getButton(1);

  public static final DriveAxis turretXAxis = coController.getAxis(0)
      .withDeadband(0.1);
  public static final DriveAxis turretYAxis = coController.getAxis(1)
      .withDeadband(0.1)
      .withInvert();

  public static final DriveAxis tiltAxis = coController.getAxis(3);
}
