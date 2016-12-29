package org.team2471.bunnybot.defaultcommands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team2471.bunnybot.HardwareMap;
import org.team2471.bunnybot.IOMap;

import static org.team2471.bunnybot.Robot.arm;

public class ArmDefaultCommand extends Command {
  private final IOMap io = IOMap.getInstance();

  public ArmDefaultCommand() {
    requires(arm);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if (io.coPilotController.getAxis(3).get()>0.1) {
      arm.spitOut( io.coPilotController.getAxis(3).get() * 0.8 );
    } else if (io.coPilotController.getAxis(2).get()>0.1) {
      arm.suckIn( io.coPilotController.getAxis(2).get() * 0.8 );
    }
    else {
      arm.stopIntake();
    }
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
