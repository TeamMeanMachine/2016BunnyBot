package org.team2471.bunnybot.commandgroups;

import org.team2471.bunnybot.commands.GrabberIntakeCommand;
import org.team2471.bunnybot.commands.GrabberToAngleCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IntakeCommandGroup extends CommandGroup {
  public IntakeCommandGroup() {
    addParallel(new GrabberToAngleCommand(SmartDashboard.getNumber("Arm Intake Angle")));
    addSequential(new WaitCommand(0.4));
    addSequential(new GrabberIntakeCommand());
  }

  @Override
  protected void end() {
    // Move back to resting position
    new GrabberToAngleCommand(SmartDashboard.getNumber("Arm Resting Angle")).start();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
