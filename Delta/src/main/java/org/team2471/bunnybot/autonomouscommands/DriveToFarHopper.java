package org.team2471.bunnybot.autonomouscommands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveToFarHopper extends CommandGroup {
  public DriveToFarHopper() {
    DriverStation driverStation = DriverStation.getInstance();

    DriverStation.Alliance alliance = driverStation.getAlliance();
    int location = driverStation.getLocation();

    if (alliance == DriverStation.Alliance.Blue){
      addSequential(new DriveToFarHopperBlueAlliance(1.0));
    }
    else if (alliance == DriverStation.Alliance.Red);
    addSequential(new DriveToFarHopperRedAlliance(1.0));

  }
}
