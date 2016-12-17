package org.team2471.bunnybot.defaultcommands;

import edu.wpi.first.wpilibj.command.Command;
import org.team2471.bunnybot.IOMap;

import static org.team2471.bunnybot.Robot.driveTrain;

public class DriveTrainDefaultCommand extends Command {

  public DriveTrainDefaultCommand() {
    requires(driveTrain);
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    driveTrain.drive(IOMap.throttleAxis.get(), IOMap.turnAxis.get());
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    //explode
  }

  @Override
  protected void interrupted() {
    end();
  }
}
