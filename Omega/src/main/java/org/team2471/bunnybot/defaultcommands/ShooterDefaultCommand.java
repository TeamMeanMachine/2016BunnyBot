package org.team2471.bunnybot.defaultcommands;

import edu.wpi.first.wpilibj.command.Command;

import static org.team2471.bunnybot.IOMap.tiltAxis;
import static org.team2471.bunnybot.IOMap.turretXAxis;
import static org.team2471.bunnybot.IOMap.turretYAxis;
import static org.team2471.bunnybot.Robot.shooter;
import static org.team2471.bunnybot.IOMap.shootButton;

public class ShooterDefaultCommand extends Command {

  public ShooterDefaultCommand() {
    requires(shooter);
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    shooter.setPan(turretXAxis.get());

    double tiltAngle = tiltAxis.get() * 90 + 100;
    shooter.setTilt(tiltAngle);
    System.out.println(tiltAngle);

    if (shootButton.get()){
      shooter.enableShooting();
    } else {
      shooter.disableShooting();
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
