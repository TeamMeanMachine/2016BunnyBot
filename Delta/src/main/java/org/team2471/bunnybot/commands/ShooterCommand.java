package org.team2471.bunnybot.commands;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;

import static org.team2471.bunnybot.Robot.shooter;

public class ShooterCommand extends Command {
  double startTime;

  @Override
  protected void initialize() {
    startTime = Utility.getFPGATime();
  }

  @Override
  protected void execute() {
    shooter.shoot();
  }

  @Override
  protected boolean isFinished() {
    return (Utility.getFPGATime() - startTime) / 1.0e6 > 0.25;
  }

  @Override
  protected void end() {
    shooter.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
