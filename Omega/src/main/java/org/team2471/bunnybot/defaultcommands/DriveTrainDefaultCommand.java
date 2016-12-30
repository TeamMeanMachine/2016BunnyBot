package org.team2471.bunnybot.defaultcommands;

import org.team2471.bunnybot.IOMap;
import org.team2471.bunnybot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveTrainDefaultCommand extends Command {
  private final IOMap ioMap = IOMap.getInstance();

  public DriveTrainDefaultCommand() {
    requires(Robot.driveTrain);
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    double throttle = ioMap.throttleAxis.get();
    double turn = ioMap.turnAxis.get();
    Robot.driveTrain.drive(throttle, turn, !ioMap.intakeButton.get() && !ioMap.noCheesyDriveButton.get());
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
