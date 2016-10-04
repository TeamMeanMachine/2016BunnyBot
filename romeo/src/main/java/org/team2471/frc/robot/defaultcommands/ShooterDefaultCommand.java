package org.team2471.frc.robot.defaultcommands;

import edu.wpi.first.wpilibj.command.Command;
import org.team2471.frc.robot.IOMap;
import org.team2471.frc.robot.Robot;

public class ShooterDefaultCommand extends Command {
  public ShooterDefaultCommand() {
    requires(Robot.shooter);
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    if(IOMap.gunner.getRawButton(3)) {
      Robot.shooter.enableShooting();
    } else {
      Robot.shooter.stopShooting();
    }
    Robot.shooter.pan(IOMap.gunner.getRawAxis(3));
    // Robot.shooter.tilt(IOMap.gunner.getRawAxis()); //I don't know the ID of the axis we want, so it will be blank for now
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

  }
}
