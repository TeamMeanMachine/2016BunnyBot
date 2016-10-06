package org.team2471.frc.robot.defaultcommands;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;
import org.team2471.frc.robot.IOMap;
import org.team2471.frc.robot.Robot;

public class ShooterDefaultCommand extends Command {
  private double prevShotTime = Utility.getFPGATime() - 3000000;

  public ShooterDefaultCommand() {
    requires(Robot.shooter);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    double shootStick = IOMap.gunner.getRawAxis(3);
    if(Utility.getFPGATime() - prevShotTime <= 330000) {
    }
    else if (shootStick >= 0.5 && Utility.getFPGATime() - prevShotTime >= 500000) {
      Robot.shooter.enableShooting();
      prevShotTime = Utility.getFPGATime();
    }
    else {
      Robot.shooter.stopShooting();
    }

    double panStick = IOMap.gunner.getRawAxis(0);
    if (Math.abs(panStick) < 0.1) {
      panStick = 0.0;
    }
    Robot.shooter.pan(panStick*panStick*panStick * 0.2);

    double tiltStick = IOMap.gunner.getRawAxis(1);
    if (Math.abs(tiltStick) < 0.15) {
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
