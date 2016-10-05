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
    if(IOMap.gunner.getRawButton(8)) {
      Robot.shooter.enableShooting();
    } else {
      Robot.shooter.stopShooting();
    }

    double panStick = IOMap.gunner.getRawAxis(0);
    if (Math.abs(panStick) < 0.1)
      panStick=0.0;
    Robot.shooter.pan(panStick*panStick*panStick * 0.2);

    double tiltStick = IOMap.gunner.getRawAxis(1);
    if (Math.abs(tiltStick) < 0.2) {
      tiltStick = 0;
    }
    Robot.shooter.tilt(tiltStick * 3.0);
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
