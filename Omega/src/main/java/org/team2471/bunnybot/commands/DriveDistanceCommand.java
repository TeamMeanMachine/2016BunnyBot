package org.team2471.bunnybot.commands;

import java.security.Timestamp;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static org.team2471.bunnybot.Robot.driveTrain;
import static org.team2471.bunnybot.HardwareMap.DriveTrainMap;

public class DriveDistanceCommand extends Command {

  double distance;
  double steer;
  double throttle;
  double startDistance;

  public DriveDistanceCommand(double distance, double throttle, double steer ) {
    requires(driveTrain);

    this.distance = distance;
    this.steer = steer;
    this.throttle = throttle;
  }

  @Override
  protected void initialize() {
    startDistance = Math.abs(driveTrain.getDistance());
  }

  @Override
  protected void execute() {
    driveTrain.drive(throttle, steer, false);
  }

  @Override
  protected boolean isFinished() {
    double traveled = Math.abs(driveTrain.getDistance() - startDistance);
    SmartDashboard.putNumber("Traveled", driveTrain.getDistance());
    return traveled >= distance || isTimedOut();
  }

  @Override
  protected void end() {
    driveTrain.drive(0, 0, false);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
