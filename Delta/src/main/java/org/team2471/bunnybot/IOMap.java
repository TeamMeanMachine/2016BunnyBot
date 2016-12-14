package org.team2471.bunnybot;

import org.team2471.bunnybot.commands.ArmInCanCommand;
import org.team2471.bunnybot.commands.ReadyToSpitCommand;
import org.team2471.bunnybot.commands.SpitCommand;
import org.team2471.bunnybot.commands.SuckCommand;
import commands.ShooterCommand;

import org.team2471.frc.lib.control.DriveAxis;
import org.team2471.frc.lib.control.DriveController;

public class IOMap {
  private static final DriveController driveController = new DriveController(0);

  private static final DriveController coPilotController = new DriveController(0)
          .withRunCommandOnButtonPressEvent(3, new ReadyToSpitCommand())
          .withRunCommandOnButtonPressEvent(0, new ArmInCanCommand())
          .withRunCommandWhileButtonHoldEvent(5, new SuckCommand())
          .withRunCommandWhileButtonHoldEvent(4, new SpitCommand());
  public IOMap() {
    driverStick = new Joystick(0);
    fireButton = new JoystickButton(driverStick,6);
    fireButton.whenPressed(new ShooterCommand());
  }
  private static final DriveController mainController = new DriveController(0);

  public static final DriveAxis throttleAxis = driveController.getAxis(1)
      .withInvert()
      .withDeadband(.2)
      .withExponentialScaling(2);
  public static final DriveAxis turnAxis = driveController.getAxis(4)
      .withDeadband(.2)
      .map(value -> value * 0.7)
      .withExponentialScaling(2);
}
