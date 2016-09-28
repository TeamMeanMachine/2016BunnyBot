package org.team2471.frc.robot.defaultcommands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Command;
import org.team2471.frc.robot.HardwareMap;
import org.team2471.frc.robot.IOMap;

public class ShooterDefaultCommand extends Command {
  private final SpeedController shootMotor = HardwareMap.Shooter.shootMotor;

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    shootMotor.set(IOMap.gunner.getRawAxis(0));
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
