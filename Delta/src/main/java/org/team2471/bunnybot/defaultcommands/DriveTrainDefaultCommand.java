package org.team2471.bunnybot.defaultcommands;

import org.team2471.bunnybot.IOMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    boolean cheesy = !(IOMap.noCheesyDriveButton.get() || SmartDashboard.getBoolean("Disable Cheesy Drive"));
    driveTrain.drive(IOMap.throttleAxis.get(), IOMap.turnAxis.get(), cheesy);
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
