package org.team2471.bunnybot.defaultcommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team2471.bunnybot.IOMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static org.team2471.bunnybot.IOMap.turnInPlaceButton;
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
            SmartDashboard.getBoolean("Cheesy Drive", true),
            IOMap.driverThrottleAxis.get() < 0.5 || turnInPlaceButton.get());

    // driver non-verbal communication
    IOMap.coPilotController.rumbleLeft(IOMap.driverRumbleButton.get() || IOMap.coPilotRumbleButton.get() ? 1.0f : 0.0f );
    IOMap.driveController.rumbleLeft(IOMap.driverRumbleButton.get() || IOMap.coPilotRumbleButton.get() ? 1.0f : 0.0f );
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
