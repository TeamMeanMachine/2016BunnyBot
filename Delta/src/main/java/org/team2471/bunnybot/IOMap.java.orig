package org.team2471.bunnybot;

import org.team2471.frc.lib.control.DriveAxis;
import org.team2471.frc.lib.control.DriveController;

public class IOMap {
  private static final DriveController driveController = new DriveController(0);
  private static final DriveController coPilotController = new DriveController(0);

  public static final DriveAxis shoulderAxis = coPilotController.getAxis(3);
  public static final DriveAxis elbowAxis = coPilotController.getAxis(4);

  public static final DriveAxis throttleAxis = driveController.getAxis(1).withInvert().withDeadband(.2);
  public static final DriveAxis turnAxis = driveController.getAxis(4).withDeadband(.2);
}
