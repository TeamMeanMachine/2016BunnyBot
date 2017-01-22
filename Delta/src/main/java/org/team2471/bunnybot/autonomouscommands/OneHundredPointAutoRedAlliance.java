package org.team2471.bunnybot.autonomouscommands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OneHundredPointAutoRedAlliance extends CommandGroup {
  public OneHundredPointAutoRedAlliance(){
    addSequential(new DriveToRightLift(1.0));
    addSequential(new DriveBackwardsFromRightLift(1.0));
    addSequential(new DriveToHopperFromRightLift(1.0));
  }
}
