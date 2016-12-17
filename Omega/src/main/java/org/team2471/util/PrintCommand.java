package org.team2471.util;

import edu.wpi.first.wpilibj.command.Command;

public class PrintCommand extends Command {
  private final String message;

  public PrintCommand(String message) {
    this.message = message;
  }

  @Override
  protected void initialize() {
    System.out.println(message);
  }

  @Override
  protected void execute() {

  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {

  }

  @Override
  protected void interrupted() {

  }
}
