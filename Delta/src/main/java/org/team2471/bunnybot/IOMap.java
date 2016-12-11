package org.team2471.bunnybot;

import commands.ShooterCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.team2471.frc.lib.control.DriveAxis;
import org.team2471.frc.lib.control.DriveController;

public class IOMap {
  Joystick driverStick;
  Button fireButton;

  public IOMap() {
    driverStick = new Joystick(0);
    fireButton = new JoystickButton(driverStick,6);
    fireButton.whenPressed(new ShooterCommand());
  }
  private static final DriveController mainController = new DriveController(0);

  public static final DriveAxis throttleAxis = mainController.getAxis(1).withInvert().withDeadband(.2);
  public static final DriveAxis turnAxis = mainController.getAxis(4).withDeadband(.2);
}
