package org.team2471.bunnybot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.team2471.bunnybot.IOMap;

import static org.team2471.bunnybot.Robot.arm;

public class RunIntakeCommand extends Command {

  double power;

  private final IOMap io = IOMap.getInstance();

  public RunIntakeCommand(double power) {
    requires(arm);
    this.power = power;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
      arm.spitOut( power );
  }

  @Override
  protected boolean isFinished() {
    return isTimedOut();
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
