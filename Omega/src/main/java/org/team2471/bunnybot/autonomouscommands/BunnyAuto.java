package org.team2471.bunnybot.autonomouscommands;

import org.team2471.bunnybot.commandgroups.IntakeCommandGroup;
import org.team2471.bunnybot.commands.DriveDistanceCommand;
import org.team2471.bunnybot.commands.GrabberToAngleCommand;


import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BunnyAuto extends CommandGroup {
  public BunnyAuto() {


    addParallel(new IntakeCommandGroup(), 4.5);
    addSequential(new WaitCommand(.75));
    addSequential(new DriveDistanceCommand(14,-0.5,0.01));

    addSequential(new GrabberToAngleCommand(SmartDashboard.getNumber("Arm Resting Angle")));


    addSequential(new DriveDistanceCommand(14,-0.5,0.18));

    //addSequential(new GrabberSpitCommand());
  }
}
