package org.team2471.bunnybot.defaultcommands;

import org.team2471.bunnybot.IOMap;

import edu.wpi.first.wpilibj.command.Command;

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
    driveTrain.drive(IOMap.driverThrottleAxis.get(), IOMap.driverTurnAxis.get(),
            IOMap.coPilotThrottleAxis.get(), IOMap.coPilotTurnAxis.get(),
            !IOMap.noCheesyDriveButton.get());
    // driver non-verbal communication
    IOMap.coPilotController.rumbleLeft(IOMap.driverRumbleButton.get() ? 1.0f : 0.0f );
    IOMap.driveController.rumbleLeft(IOMap.coPilotRumbleButton.get() ? 1.0f : 0.0f );
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
