package org.team2471.bunnybot.autonomouscommands;

import org.team2471.bunnybot.commands.DriveDistanceCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveLeftAuto extends CommandGroup {
  public DriveLeftAuto()

  {
    addSequential(new DriveDistanceCommand(12,0.5,-0.2), 5.0);

  }
}
