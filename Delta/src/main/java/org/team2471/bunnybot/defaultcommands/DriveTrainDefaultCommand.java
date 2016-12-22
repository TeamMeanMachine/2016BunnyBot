package org.team2471.bunnybot.defaultcommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team2471.bunnybot.IOMap;
import edu.wpi.first.wpilibj.command.Command;

import static org.team2471.bunnybot.Robot.driveTrain;

public class DriveTrainDefaultCommand extends Command {
  private final IOMap io = IOMap.getInstance();

  public DriveTrainDefaultCommand() {
    requires(driveTrain);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    driveTrain.drive(io.driverThrottleAxis.get(), io.driverTurnAxis.get(),
            io.coPilotThrottleAxis.get(), io.coPilotTurnAxis.get(),
            SmartDashboard.getBoolean("Cheesy Drive", true),                   // this is the cheesy drive setting
            io.driverThrottleAxis.get() < 0.5 || io.turnInPlaceButton.get());  // this is quick turn for cheesy drive

    // driver non-verbal communication
    io.coPilotController.rumbleLeft(io.driverRumbleButton.get() || io.coPilotRumbleButton.get() ? 1.0f : 0.0f );
    io.driveController.rumbleLeft(io.driverRumbleButton.get() || io.coPilotRumbleButton.get() ? 1.0f : 0.0f );
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
