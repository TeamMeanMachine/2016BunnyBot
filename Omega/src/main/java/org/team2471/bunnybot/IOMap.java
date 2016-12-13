package org.team2471.bunnybot;

import org.team2471.frc.lib.control.DriveAxis;
import org.team2471.frc.lib.control.DriveButton;
import org.team2471.frc.lib.control.DriveController;

public class IOMap {
  private static final DriveController mainController = new DriveController(0);
  public static final boolean isSteeringWheel = mainController.getName().equals("LUFA Wheel wFFB");
  public static final DriveAxis grabberAxis = mainController.getAxis(1);
  public static final DriveButton suckinButton = mainController.getButton(4);
  public static final DriveButton spitoutButton = mainController.getButton(2);
  public static final DriveButton backupButton = mainController.getButton(8);
  public static final DriveButton noCheesyDriveButton = mainController.getButton(isSteeringWheel ? 9 : 8);

  public static final DriveAxis callibrationAxis = mainController.getAxis(3);
  public static final DriveButton callibrationButton = mainController.getButton(1);



  public static final DriveAxis throttleAxis = isSteeringWheel ?
      () -> {
        double result = mainController.getAxis(1).get() / -2 + 0.5;
        // TODO: Factor back pedal (if we use a wheel)
//        result -= mainController.getAxis(7).get() / -2 + 0.5;
        if (backupButton.get()) {
          result = -result;
        }
        result *= 2;
        if (result > 1) {
          result = 1;
        } else if (result < -1) {
          result = -1;
        }
//        System.out.println(result);
        return result;
      }
//          - (mainController.getAxis(7).get() / -2 + 0.5)
      :
      mainController.getAxis(1)
          .withDeadband(0.05, false)
          .withExponentialScaling(2)
          .withInvert();

  public static final DriveAxis turnAxis = isSteeringWheel ?
      mainController.getAxis(2) :
      mainController.getAxis(2)
          .withDeadband(0.05, false)
          .withExponentialScaling(2); // scale down



  private static final DriveController coController = new DriveController(1);

  public static final DriveButton shootButton = coController.getButton(1);

  public static final DriveAxis turretXAxis = coController.getAxis(0)
      .withDeadband(0.1);
  public static final DriveAxis turretYAxis = coController.getAxis(1)
      .withDeadband(0.1)
      .withInvert();

  public static final DriveAxis tiltAxis = coController.getAxis(3);

}
