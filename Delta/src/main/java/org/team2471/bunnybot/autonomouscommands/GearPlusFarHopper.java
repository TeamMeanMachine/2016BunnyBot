package org.team2471.bunnybot.autonomouscommands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearPlusFarHopper extends CommandGroup {
  public GearPlusFarHopper() {
    DriverStation driverStation = DriverStation.getInstance();

    DriverStation.Alliance alliance = driverStation.getAlliance();
    int location = driverStation.getLocation();

    if (alliance == alliance.Blue){
      addSequential(new DriveToRightLift(1.0));
      addSequential(new DriveBackwardsFromRLToFarHopper(1.0));
      addSequential(new DriveToFarHopperFromRightLift(1.0));
    }
    else if (alliance == alliance.Red){
      addSequential(new DriveToLeftLift(1.0));
      addSequential(new DriveBackwardsFromLLToFarHopper(1.0));
      addSequential(new DriveToFarHopperFromLeftLift(1.0));
    }

  }
}
