package org.team2471.bunnybot.autonomouscommands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;

import static edu.wpi.first.wpilibj.DriverStation.Alliance;

public class OneHundredPointAuto extends CommandGroup {
  public OneHundredPointAuto() {
    DriverStation driverStation = DriverStation.getInstance();

    Alliance alliance = driverStation.getAlliance();
    int location = driverStation.getLocation();

    if (alliance == Alliance.Blue) {
      addSequential(new DriveToLeftLift(1.0));
      addSequential(new PrintCommand("Finished first drive"));
      addSequential(new DriveBackwardsFromLeftLift(1.0));
      addSequential(new PrintCommand("Finished driving backwards"));
      addSequential(new DriveToHopperFromLeftLift(1.0));
      addSequential(new PrintCommand("Done!"));
    } else if (alliance == Alliance.Red) {
      addSequential(new DriveToRightLift(1.0));
      addSequential(new PrintCommand("Finished First drive"));
      addSequential(new DriveBackwardsFromRightLift(1.0));
      addSequential(new PrintCommand("Finished driving backwards"));
      addSequential(new DriveToHopperFromRightLift(1.0));
      addSequential(new PrintCommand("Done!"));
    }
  }
}
