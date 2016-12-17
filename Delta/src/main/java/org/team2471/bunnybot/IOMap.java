package org.team2471.bunnybot;

import org.team2471.bunnybot.commands.ArmInCanCommand;
import org.team2471.bunnybot.commands.IntakePositionCommand;
import org.team2471.bunnybot.commands.ReadyToSpitCommand;
import org.team2471.bunnybot.commands.ShooterCommand;
import org.team2471.bunnybot.commands.SpitCommand;
import org.team2471.bunnybot.commands.SuckCommand;
import org.team2471.frc.lib.control.DriveAxis;
import org.team2471.frc.lib.control.DriveButton;
import org.team2471.frc.lib.control.DriveController;

public class IOMap {
  public static final DriveController driveController = new DriveController(0)
      .withRunCommandOnButtonPressEvent(5, new ShooterCommand());

  public static final DriveAxis throttleAxis = driveController.getAxis(1)
      .withInvert()
      .withDeadband(.2)
      .withExponentialScaling(2);
  public static final DriveAxis turnAxis = driveController.getAxis(4)
      .withDeadband(.2)
      .map(value -> value * 0.7)
      .withExponentialScaling(2);
  public static final DriveButton noCheesyDriveButton = driveController.getButton(4).withInvert();
  public static final DriveAxis elbowAxis = driveController.getAxis(4);


  private static final DriveController coController = new DriveController(1)
      .withRunCommandOnButtonPressEvent(3, new ReadyToSpitCommand())
      .withRunCommandOnButtonPressEvent(0, new ArmInCanCommand())
      .withRunCommandOnButtonPressEvent(2, new IntakePositionCommand())
      .withRunCommandWhileButtonHoldEvent(5, new SuckCommand())
      .withRunCommandWhileButtonHoldEvent(4, new SpitCommand());

  public static void init(){
  }
}
