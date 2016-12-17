package org.team2471.bunnybot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import static org.team2471.bunnybot.HardwareMap.ShooterMap.shooterMotor;

public class Shooter extends Subsystem {

  public Shooter() {
  }

  @Override
  protected void initDefaultCommand() {
  }

  public void shoot() {
    shooterMotor.set(-.8);
  }

  public void stop() {
    shooterMotor.set(0);
  }
}