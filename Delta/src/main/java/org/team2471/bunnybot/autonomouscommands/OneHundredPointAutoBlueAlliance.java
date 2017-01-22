package org.team2471.bunnybot.autonomouscommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;

public class OneHundredPointAutoBlueAlliance extends CommandGroup {
  public OneHundredPointAutoBlueAlliance(){
    addSequential(new DriveToLeftLift(1.0));
    addSequential(new PrintCommand("Finished first drive"));
    addSequential(new DriveBackwardsFromLeftLift(1.0));
    addSequential(new PrintCommand("Finished driving backwards"));
    addSequential(new DriveToHopperFromLeftLift(1.0));
    addSequential(new PrintCommand("Done!"));
  }
}
