package org.team2471.bunnybot;

import org.team2471.bunnybot.commands.ReadyToSpitCommand;
import org.team2471.frc.lib.control.DriveAxis;
import org.team2471.frc.lib.control.DriveController;

public class IOMap {
  private static final DriveController driveController = new DriveController(0)
          .withRunCommandOnButtonPressEvent(1, new ReadyToSpitCommand());
  private static final DriveController coPilotController = new DriveController(0);

  public static final DriveAxis shoulderAxis = coPilotController.getAxis(1);
  public static final DriveAxis elbowAxis = coPilotController.getAxis(5);

  public static final DriveAxis throttleAxis = driveController.getAxis(1).withInvert().withDeadband(.2);
  public static final DriveAxis turnAxis = driveController.getAxis(4).withDeadband(.2);
}
