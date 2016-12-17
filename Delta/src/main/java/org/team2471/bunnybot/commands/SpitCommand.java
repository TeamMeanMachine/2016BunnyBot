package org.team2471.bunnybot.commands;

import edu.wpi.first.wpilibj.command.Command;

import static org.team2471.bunnybot.Robot.arm;

public class SpitCommand extends Command{

  public SpitCommand() {
    requires(arm);
  }
  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    arm.spitOut();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    arm.stopIntake();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
