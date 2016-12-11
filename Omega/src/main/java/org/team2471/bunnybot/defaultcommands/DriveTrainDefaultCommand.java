package org.team2471.bunnybot.defaultcommands;

import edu.wpi.first.wpilibj.command.Command;
import org.team2471.bunnybot.IOMap;
import org.team2471.bunnybot.Robot;
import org.team2471.bunnybot.subsystems.DriveTrain;

public class DriveTrainDefaultCommand extends Command {
  public DriveTrainDefaultCommand() {
    requires(Robot.driveTrain);
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    double throttle = IOMap.throttleAxis.get();
    double turn = IOMap.turnAxis.get();
    Robot.driveTrain.drive(throttle, turn);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {

  }

  @Override
  protected void interrupted() {
    end();
  }
}
