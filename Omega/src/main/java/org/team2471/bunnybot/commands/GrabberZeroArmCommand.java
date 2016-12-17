package org.team2471.bunnybot.commands;

import edu.wpi.first.wpilibj.command.Command;

import static org.team2471.bunnybot.Robot.grabber;

public class GrabberZeroArmCommand extends Command {

  public GrabberZeroArmCommand() {
    requires(grabber);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    grabber.setSetpoint(-15);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    grabber.zero();
  }

  @Override
  protected void interrupted() {
  }
}
