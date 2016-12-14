package org.team2471.bunnybot.subsystems;

import org.team2471.bunnybot.defaultcommands.ShooterDefaultCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

import static org.team2471.bunnybot.HardwareMap.Shooter.*;

/**
 * Created by Bob on 12/10/2016.
 */
public class Shooter extends Subsystem {

  public Shooter() {

  }

  public void shoot() {
    shooterMotor.set(.5);
  }
  public void stop() {
    shooterMotor.set(0);
  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new ShooterDefaultCommand());
  }
}