package org.team2471.bunnybot.commandgroups;

import org.team2471.bunnybot.commands.GrabberIntakeCommand;
import org.team2471.bunnybot.commands.GrabberToAngleCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IntakeCommandGroup extends CommandGroup {
  public IntakeCommandGroup() {
    addParallel(new GrabberToAngleCommand(SmartDashboard.getNumber("Arm Intake Angle", 212)));
    addSequential(new WaitCommand(0.8));
    addSequential(new GrabberIntakeCommand());
  }

  @Override
  protected void interrupted() {
    end();
  }
}
