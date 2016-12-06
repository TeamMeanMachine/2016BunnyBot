package org.team2471.bunnybot;

import org.team2471.frc.lib.control.DriveAxis;
import org.team2471.frc.lib.control.DriveController;

public class IOMap {
  private static final DriveController mainController = new DriveController(0);

  public static final DriveAxis throttleAxis = mainController.getAxis(1);
  public static final DriveAxis turnAxis = mainController.getAxis(2);
}
