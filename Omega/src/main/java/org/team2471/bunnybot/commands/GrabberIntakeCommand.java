package org.team2471.bunnybot.commands;

import edu.wpi.first.wpilibj.command.Command;

import static org.team2471.bunnybot.Robot.grabber;

public class GrabberIntakeCommand extends Command {
  public GrabberIntakeCommand() {
    requires(grabber);
  }

  @Override
  protected void initialize() {
    grabber.suckIn();
  }

  @Override
  protected void execute() {

  }

  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  @Override
  protected void end() {
    grabber.stopIntake();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
