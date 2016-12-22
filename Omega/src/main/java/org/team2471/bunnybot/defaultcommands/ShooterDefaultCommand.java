package org.team2471.bunnybot.defaultcommands;

import org.team2471.bunnybot.IOMap;
import org.team2471.bunnybot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

import static org.team2471.bunnybot.Robot.shooter;

public class ShooterDefaultCommand extends Command {
  private final IOMap ioMap = IOMap.getInstance();

  public ShooterDefaultCommand() {
    requires(shooter);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    double axis = ioMap.turretXAxis.get();

    shooter.setPan(axis);

    double tiltRange = Shooter.UPPER_TILT_LIMIT - Shooter.LOWER_TILT_LIMIT;
    double tiltCenterpoint = (Shooter.UPPER_TILT_LIMIT + Shooter.LOWER_TILT_LIMIT) / 2;

    double tiltAngle = ioMap.tiltAxis.get() * tiltRange / 2 + tiltCenterpoint;

    shooter.setTilt(tiltAngle);

    if (ioMap.shootButton.get()) {
      shooter.enableShooting();
    } else {
      shooter.disableShooting();
    }

    if (ioMap.flashlightButton.get()) {
      shooter.enableFlashlight();
    } else {
      shooter.disableFlashlight();
    }
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
