package org.team2471.bunnybot.autonomouscommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team2471.bunnybot.commands.IntakeAnimationCommand;
import org.team2471.bunnybot.commands.ReadyToSpitCommand;
import org.team2471.bunnybot.commands.SpitAnimationCommand;

public class Intake3Auto extends CommandGroup {
  public Intake3Auto() {
    addParallel(new IntakeAnimationCommand(1.0),5.0);
    addSequential(new DriveAgainstWall(1.0));
    addParallel(new IntakeAnimationCommand(-1.0));
//    addSequential(new TurnToCan(1.0));
//    addParallel(new ReadyToSpitCommand());
//    addSequential(new DriveToCan(1.0));
//    //addSequential(new RunIntakeCommand(0.8), 2.0);
//    addSequential(new SpitAnimationCommand(1.0), 2.0);
//    addSequential(new SpitAnimationCommand(-1.0));
  }
}
