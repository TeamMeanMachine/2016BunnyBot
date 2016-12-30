package org.team2471.bunnybot.autonomouscommands;

import org.team2471.bunnybot.commandgroups.IntakeCommandGroup;
import org.team2471.bunnybot.commands.DriveDistanceCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class PickupBunnyAuto extends CommandGroup {
  public PickupBunnyAuto() {

    addParallel(new IntakeCommandGroup(), 4.5);
    addSequential(new WaitCommand(.75));
    addSequential(new DriveDistanceCommand(14,-0.7,0.0));


  }
}
