package org.team2471.bunnybot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static org.team2471.bunnybot.Robot.grabber;

public class GrabberToAngleCommand extends Command {
  private final double targetAngle;

  public GrabberToAngleCommand(double angle) {
    requires(grabber);
    this.targetAngle = angle;
  }

  @Override
  protected void initialize() {
    grabber.setSetpoint(targetAngle);
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    double delta = Math.abs(targetAngle - grabber.getAngle());
    return delta < 1;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
