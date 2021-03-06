package org.team2471.bunnybot.commands;

import org.team2471.bunnybot.IOMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static org.team2471.bunnybot.Robot.grabber;

public class GrabberSpitCommand extends Command {
  public GrabberSpitCommand() {
    requires(grabber);
  }

  @Override
  protected void initialize() {
    grabber.setSetpoint(SmartDashboard.getNumber("Arm Burrow Angle"));
  }

  @Override
  protected void execute() {
    double power = IOMap.getInstance().spitAxis.get();
    grabber.spitOut(power);
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
