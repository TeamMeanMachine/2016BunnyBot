package org.team2471.bunnybot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.team2471.bunnybot.Robot;

public class GrabberIntakeCommand extends Command {

  public GrabberIntakeCommand() {
    requires(Robot.grabber);
  }


  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    Robot.grabber.suckIn();

  }

  @Override
  protected boolean isFinished() {
    return Robot.grabber.hasBunny();
  }

  @Override
  protected void end() {
    Robot.grabber.stopIntake();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
