package org.team2471.bunnybot.defaultcommands;

import edu.wpi.first.wpilibj.command.Command;
import org.team2471.bunnybot.HardwareMap;
import org.team2471.bunnybot.IOMap;

import static org.team2471.bunnybot.Robot.arm;

public class SpitCommand extends Command{

  public SpitCommand() {
    requires(arm);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    arm.spitOut( IOMap.coPilotController.getAxis(3).get() * 0.8 );
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    arm.stopIntake();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
