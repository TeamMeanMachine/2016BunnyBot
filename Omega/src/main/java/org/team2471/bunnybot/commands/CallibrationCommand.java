package org.team2471.bunnybot.commands;


import org.team2471.bunnybot.HardwareMap;
import org.team2471.bunnybot.IOMap;
import org.team2471.bunnybot.Robot;
import org.team2471.bunnybot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class CallibrationCommand extends Command {

  private boolean hasBeenPressed;
  private int motorNumber;

  @Override
  protected void initialize() {
    hasBeenPressed = false;
    motorNumber = 0;
  }


  @Override
  protected void execute() {
    IOMap ioMap = IOMap.getInstance();

    if (ioMap.callibrationButton.get() && !hasBeenPressed) {
      hasBeenPressed = true;
      motorNumber++;
    } else if (hasBeenPressed && ioMap.callibrationButton.get()) {
      hasBeenPressed = false;
      motorNumber++;
    }
    if (motorNumber == 1) {
      Robot.driveTrain.getLeftSwerveModule().setOffset(ioMap.callibrationAxis.get());
    }
    if (motorNumber == 2) {
      Robot.driveTrain.getRightSwerveModule().setOffset(ioMap.callibrationAxis.get());
    }
  }


  @Override
  protected boolean isFinished() {
    return motorNumber >= 2;
  }

  @Override
  protected void end() {

  }

  @Override
  protected void interrupted() {

  }
}

