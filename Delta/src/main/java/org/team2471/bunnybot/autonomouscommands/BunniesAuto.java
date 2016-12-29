package org.team2471.bunnybot.autonomouscommands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team2471.bunnybot.commands.IntakeCommand;
import org.team2471.bunnybot.commands.ReadyToSpitCommand;

public class BunniesAuto extends CommandGroup {
  public BunniesAuto() {
    addParallel(new IntakeCommand(1.0),4.0);
    addSequential(new DriveAgainstWall(1.0));
    addParallel(new IntakeCommand(-1.0));
    addSequential(new TurnToCan(1.0));
    addParallel(new ReadyToSpitCommand());
    addSequential(new DriveToCan(1.0));
  }
}
