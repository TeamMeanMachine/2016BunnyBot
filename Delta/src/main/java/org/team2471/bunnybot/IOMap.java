package org.team2471.bunnybot;

import org.team2471.bunnybot.commands.IntakeAnimationCommand;
import org.team2471.bunnybot.commands.ReadyToSpitCommand;
import org.team2471.bunnybot.commands.ShooterCommand;
import org.team2471.bunnybot.commands.SpitAnimationCommand;
import org.team2471.frc.lib.control.CommandTrigger;
import org.team2471.frc.lib.io.Controller;
import org.team2471.frc.lib.io.ControllerAxis;
import org.team2471.frc.lib.io.ControllerButton;

public class IOMap {
  private static IOMap instance = null;

  public final Controller driveController = new Controller(0);

  public final Controller coPilotController = new Controller(1);

  public final ControllerAxis driverThrottleAxis = driveController.getAxis(1)
      .withInvert()
      .withDeadband(.2)
      .withExponentialScaling(2);

  public final ControllerAxis driverTurnAxis = driveController.getAxis(4)
      .withDeadband(.2)
      .map(value -> value * 0.7)
      .withExponentialScaling(2);

  public final ControllerAxis coPilotThrottleAxis = coPilotController.getAxis(1)
      .withDeadband(.2)
      .map(value -> value * 0.8)
      .withExponentialScaling(2);

  public final ControllerAxis coPilotTurnAxis = coPilotController.getAxis(4)
      .withDeadband(.2)
      .map(value -> value * 0.7)
      .withExponentialScaling(2);

  public final ControllerButton turnInPlaceButton = driveController.getButton(4);
  public final ControllerButton driverRumbleButton = driveController.getButton(8);
  public final ControllerButton coPilotRumbleButton = coPilotController.getButton(6);

  private IOMap() {
    new CommandTrigger(driveController.getButton(6)::get).whenActive(new ShooterCommand());

    CommandTrigger readyToSpitTrigger = new CommandTrigger(driveController.getButton(3)::get);
    readyToSpitTrigger.whenActive(new ReadyToSpitCommand());

    CommandTrigger intakeAnimationTrigger = new CommandTrigger(driveController.getButton(6)::get);
    intakeAnimationTrigger.whileActive(new IntakeAnimationCommand(1.0));
    intakeAnimationTrigger.whenInactive(new IntakeAnimationCommand(-1.0));

    CommandTrigger spitAnimationTrigger = new CommandTrigger(driveController.getButton(4)::get);
    intakeAnimationTrigger.whileActive(new SpitAnimationCommand(1.0));
    intakeAnimationTrigger.whenInactive(new SpitAnimationCommand(-1.0));
  }

  public static IOMap getInstance() {
    if(instance == null) {
      instance = new IOMap();
    }

    return instance;
  }
}
