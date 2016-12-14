package org.team2471.bunnybot.defaultcommands;

import org.team2471.bunnybot.IOMap;
import org.team2471.bunnybot.Robot;
import org.team2471.bunnybot.subsystems.Arm;

import edu.wpi.first.wpilibj.command.Command;

public class ArmDefaultCommand extends Command{
private Arm arm = Robot.arm;

  public ArmDefaultCommand() {
    requires(arm);
  }
  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    arm.setShoulderAngle(arm.getShoulderAngle() + IOMap.shoulderAxis.get());
    arm.setElbowAngle(arm.getElbowAngle() + IOMap.elbowAxis.get());
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
