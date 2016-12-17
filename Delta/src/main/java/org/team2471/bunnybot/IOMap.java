package org.team2471.bunnybot;

import org.team2471.frc.lib.control.DriveAxis;
import org.team2471.frc.lib.control.DriveButton;
import org.team2471.frc.lib.control.DriveController;

public class IOMap {
  private static final DriveController mainController = new DriveController(0);

  public static final DriveAxis throttleAxis = mainController.getAxis(1)
      .withInvert()
      .withDeadband(.2)
      .withExponentialScaling(2);
  public static final DriveAxis turnAxis = mainController.getAxis(4)
      .withDeadband(.2)
      .map(value -> value * 0.7)
      .withExponentialScaling(2);

  public static final DriveButton noCheesyDriveButton = mainController.getButton(4).withInvert();
  public static final DriveAxis elbowAxis = mainController.getAxis(4);
}
