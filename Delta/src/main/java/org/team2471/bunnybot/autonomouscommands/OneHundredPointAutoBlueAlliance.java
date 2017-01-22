package org.team2471.bunnybot.autonomouscommands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OneHundredPointAutoBlueAlliance extends CommandGroup {
  public OneHundredPointAutoBlueAlliance(){
    addSequential(new DriveToLeftLift(1.0));
    addSequential(new DriveBackwardsFromLeftLift(1.0));
    addSequential(new DriveToHopperFromLeftLift(1.0));
  }
}
