package org.team2471.bunnybot.commands;

import org.team2471.bunnybot.IOMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static org.team2471.bunnybot.Robot.grabber;

public class GrabberSpitCommand extends Command {
  private final IOMap ioMap = IOMap.getInstance();

  public GrabberSpitCommand() {
    requires(grabber);
  }

  @Override
  protected void initialize() {
    grabber.setSetpoint(SmartDashboard.getNumber("Arm Burrow Angle"));
  }

  @Override
  protected void execute() {
    grabber.spitOut(ioMap.spitAxis.get());
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
