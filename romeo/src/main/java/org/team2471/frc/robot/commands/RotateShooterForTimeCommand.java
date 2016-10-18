package org.team2471.frc.robot.commands;

import org.team2471.frc.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class RotateShooterForTimeCommand extends Command {
  private double time;
  private double power;

  private double startTimestamp;

  public RotateShooterForTimeCommand(double time, double power) {
    this.time = time;
    this.power = power;
  }

  @Override
  protected void initialize() {
    startTimestamp = Timer.getFPGATimestamp();
  }

  @Override
  protected void execute() {
    Robot.shooter.pan(power);
  }

  @Override
  protected boolean isFinished() {
    // true after time is elapsed
    return Timer.getFPGATimestamp() > startTimestamp + time;
  }

  @Override
  protected void end() {
    Robot.shooter.pan(0.0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
