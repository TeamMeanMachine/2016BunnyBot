package org.team2471.bunnybot.autonomouscommands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CoOpHopper extends CommandGroup {
  public CoOpHopper() {
  DriverStation driverStation = DriverStation.getInstance();

  DriverStation.Alliance alliance = driverStation.getAlliance();
  int location = driverStation.getLocation();

    if (alliance == DriverStation.Alliance.Blue) {
      addSequential(new CoOpHopperAutoBlue(1.0));
    }
    else if (alliance == DriverStation.Alliance.Red){
      addSequential(new CoOpHopperAutoRed(1.0));
    }
  }
}
