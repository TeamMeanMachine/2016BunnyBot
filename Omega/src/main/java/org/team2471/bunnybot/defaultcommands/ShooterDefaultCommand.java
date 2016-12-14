package org.team2471.bunnybot.defaultcommands;

import org.team2471.bunnybot.subsystems.Shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import static org.team2471.bunnybot.IOMap.shootButton;
import static org.team2471.bunnybot.IOMap.tiltAxis;
import static org.team2471.bunnybot.IOMap.turretXAxis;
import static org.team2471.bunnybot.Robot.shooter;

public class ShooterDefaultCommand extends Command {
  public ShooterDefaultCommand() {
    requires(shooter);
  }

  @Override
  protected void initialize() {
    double axis = turretXAxis.get();

    shooter.setPan(axis);

    double tiltRange = Shooter.UPPER_TILT_LIMIT - Shooter.LOWER_TILT_LIMIT;
    double tiltCenterpoint = (Shooter.UPPER_TILT_LIMIT + Shooter.LOWER_TILT_LIMIT) / 2;

    double tiltAngle = tiltAxis.get() * tiltRange / 2 + tiltCenterpoint;

    shooter.setTilt(tiltAngle);

    if (shootButton.get()) {
      shooter.enableShooting();
    } else {
      shooter.disableShooting();
    }
  }

  @Override
  protected void execute() {
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
