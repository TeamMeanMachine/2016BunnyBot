package org.team2471.bunnybot.autonomouscommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;

public class OneHundredPointAutoRedAlliance extends CommandGroup {
  public OneHundredPointAutoRedAlliance(){
    addSequential(new DriveToRightLift(1.0));
    addSequential(new PrintCommand("Finished First drive"));
    addSequential(new DriveBackwardsFromRightLift(1.0));
    addSequential(new PrintCommand("Finished driving backwards"));
    addSequential(new DriveToHopperFromRightLift(1.0));
    addSequential(new PrintCommand("Done!"));
  }
}
