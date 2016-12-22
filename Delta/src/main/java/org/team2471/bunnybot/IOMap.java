package org.team2471.bunnybot;

import org.team2471.bunnybot.commands.IntakeCommand;
import org.team2471.bunnybot.commands.ReadyToSpitCommand;
import org.team2471.bunnybot.commands.ShooterCommand;
import org.team2471.bunnybot.commands.SpitAnimationCommand;
import org.team2471.frc.lib.control.DriveAxis;
import org.team2471.frc.lib.control.DriveButton;
import org.team2471.frc.lib.control.DriveController;

public class IOMap {
  private static IOMap instance = null;

  public final DriveController driveController = new DriveController(0);

  public final DriveController coPilotController = new DriveController(1);

  public final DriveAxis driverThrottleAxis = driveController.getAxis(1)
      .withInvert()
      .withDeadband(.2)
      .withExponentialScaling(2);

  public final DriveAxis driverTurnAxis = driveController.getAxis(4)
      .withDeadband(.2)
      .map(value -> value * 0.7)
      .withExponentialScaling(2);

  public final DriveAxis coPilotThrottleAxis = coPilotController.getAxis(1)
      .withDeadband(.2)
      .map(value -> value * 0.8)
      .withExponentialScaling(2);

  public final DriveAxis coPilotTurnAxis = coPilotController.getAxis(4)
      .withDeadband(.2)
      .map(value -> value * 0.7)
      .withExponentialScaling(2);

  public final DriveButton turnInPlaceButton = driveController.getButton(4);
  public final DriveButton driverRumbleButton = driveController.getButton(8);
  public final DriveButton coPilotRumbleButton = coPilotController.getButton(6);

  private IOMap() {
//    driveController
//        .withRunCommandOnButtonPressEvent(6, new ShooterCommand());
//
//    coPilotController
//        .withRunCommandOnButtonPressEvent(3, new ReadyToSpitCommand())
//        .withRunCommandWhileButtonHoldEvent(5, new IntakeCommand(1.0))
//        .withRunCommandOnButtonReleaseEvent(5, new IntakeCommand(-1.0))
//        .withRunCommandWhileButtonHoldEvent(4, new SpitAnimationCommand(1.0))
//        .withRunCommandOnButtonReleaseEvent(4, new SpitAnimationCommand(-1.0));
  }

  public static IOMap getInstance() {
    if(instance == null) {
      instance = new IOMap();
    }

    return instance;
  }
}
