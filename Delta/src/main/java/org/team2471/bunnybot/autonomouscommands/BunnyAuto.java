package org.team2471.bunnybot.autonomouscommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team2471.bunnybot.commands.IntakeAnimationCommand;
import org.team2471.bunnybot.commands.ReadyToSpitCommand;
import org.team2471.bunnybot.commands.SpitAnimationCommand;

public class BunnyAuto extends CommandGroup {
  public BunnyAuto() {
    addSequential(new IntakeAnimationCommand(1.0),1.5);
    addParallel(new IntakeAnimationCommand(-1.0));
    addSequential(new DriveAwayFromWall(1.0));
    addParallel(new ReadyToSpitCommand());
    addSequential(new DriveToCan(1.0, 9));
    //addSequential(new RunIntakeCommand(0.6), 2.0);
    addSequential(new SpitAnimationCommand(1.0), 2.0);
    addSequential(new SpitAnimationCommand(-1.0));
  }
}
