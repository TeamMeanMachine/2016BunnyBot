package org.team2471.frc.robot.defaultcommands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Command;
import org.team2471.frc.robot.HardwareMap;
import org.team2471.frc.robot.IOMap;
import org.team2471.frc.robot.Robot;
import org.team2471.frc.robot.subsystems.Shooter;

public class ShooterDefaultCommand extends Command {

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    if(IOMap.gunner.getRawButton(3)) {
      Robot.shooter.shoot();
    }
    Robot.shooter.pan(IOMap.gunner.getRawAxis(3));
    Robot.shooter.steer(IOMap.gunner.getRawAxis()); //I don't know the ID of the axis we want, so it will be blank for now
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
