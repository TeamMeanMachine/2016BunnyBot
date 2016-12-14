package org.team2471.bunnybot.commands;

import edu.wpi.first.wpilibj.command.Command;

import static org.team2471.bunnybot.Robot.grabber;

public class GrabberSpitCommand extends Command {
  public GrabberSpitCommand() {
    requires(grabber);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    grabber.spitOut();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    grabber.stopIntake();
  }

  @Override
  protected void interrupted() {
    grabber.stopIntake();
  }
}
