package org.team2471.bunnybot;


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

    if (IOMap.callibrationButton.get() && !hasBeenPressed) {
      hasBeenPressed = true;
      motorNumber++;
    } else if (hasBeenPressed && IOMap.callibrationButton.get()) {
      hasBeenPressed = false;
      motorNumber++;
    }
    if (motorNumber == 1) {
      HardwareMap.DriveTrainMap.LeftModule.turnMotor.set(IOMap.callibrationAxis.get());
    }
    if (motorNumber == 2) {
      HardwareMap.DriveTrainMap.RightModule.turnMotor.set(IOMap.callibrationAxis.get());
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

