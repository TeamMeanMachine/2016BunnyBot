package org.team2471.bunnybot.defaultcommands;

import edu.wpi.first.wpilibj.command.Command;
import org.team2471.bunnybot.HardwareMap;
import org.team2471.bunnybot.IOMap;

import static org.team2471.bunnybot.Robot.arm;

public class ArmDefaultCommand extends Command {

  public ArmDefaultCommand() {
    requires(arm);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if (IOMap.coPilotController.getAxis(3).get()>0.1)
      arm.spitOut( IOMap.coPilotController.getAxis(3).get() * 0.8 );
    else if (IOMap.coPilotController.getAxis(2).get()>0.1)
      arm.suckIn( IOMap.coPilotController.getAxis(2).get() * 0.8 );
    else
      arm.stopIntake();
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
