package org.team2471.bunnybot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.team2471.bunnybot.Robot;

public class GrabberSpitCommand extends Command {
  private double endTime;
  private boolean noBunny;

  public GrabberSpitCommand() {
    requires(Robot.grabber);
  }

  @Override
  protected void initialize() {
    endTime = Timer.getFPGATimestamp() + 1;
    noBunny = false;
  }

  @Override
  protected void execute() {
    Robot.grabber.spitOut();
    if (!Robot.grabber.hasBunny() && noBunny) {
      endTime = Timer.getFPGATimestamp() + 0.5;
      noBunny = true;
    }
  }

  @Override
  protected boolean isFinished() {
    return Timer.getFPGATimestamp() > endTime;
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
