package org.team2471.bunnybot;

import org.team2471.frc.lib.control.DriveAxis;
import org.team2471.frc.lib.control.DriveButton;
import org.team2471.frc.lib.control.DriveController;

public class IOMap {
  private static final DriveController mainController = new DriveController(0);
  private static final DriveController coController = new DriveController(1);

  public static final DriveAxis grabberAxis = mainController.getAxis(1);
  public static final DriveButton suckinButton = mainController.getButton(4);
  public static final DriveButton spitoutButton = mainController.getButton(2);
}
