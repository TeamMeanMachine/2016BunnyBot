package org.team2471.bunnybot;

import org.team2471.bunnybot.commands.*;
import org.team2471.bunnybot.defaultcommands.SpitCommand;
import org.team2471.frc.lib.control.DriveAxis;
import org.team2471.frc.lib.control.DriveController;
//import commands.ShooterCommand;

public class IOMap {
  public IOMap() {
  }

  public static final DriveController driveController = new DriveController(0)
          .withRunCommandOnButtonPressEvent( 5, new ShooterCommand());

  public static final DriveController coPilotController = new DriveController(1)
          .withRunCommandOnButtonPressEvent(3, new ReadyToSpitCommand())
          .withRunCommandWhileButtonHoldEvent(5, new IntakeCommand(1.0))
          .withRunCommandOnButtonReleaseEvent(5, new IntakeCommand(-1.0))
          .withRunCommandWhileButtonHoldEvent(4, new SpitCommand());

  public static final DriveAxis throttleAxis = driveController.getAxis(1)
      .withInvert()
      .withDeadband(.2)
      .withExponentialScaling(2);

  public static final DriveAxis turnAxis = driveController.getAxis(4)
      .withDeadband(.2)
      .map(value -> value * 0.7)
      .withExponentialScaling(2);

  public static final DriveAxis throttle2Axis = coPilotController.getAxis(1)
          .withInvert()
          .withDeadband(.2)
          .withExponentialScaling(2);

  public static final DriveAxis turn2Axis = coPilotController.getAxis(4)
          .withDeadband(.2)
          .map(value -> value * 0.7)
          .withExponentialScaling(2);
}
