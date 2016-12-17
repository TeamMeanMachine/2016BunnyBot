package org.team2471.bunnybot.commands;

import org.team2471.frc.lib.motion_profiling.MotionProfileAnimation;
import org.team2471.frc.lib.motion_profiling.MotionProfileCurve;
import org.team2471.frc.lib.motion_profiling.PlayAnimationCommand;

import static org.team2471.bunnybot.Robot.arm;

public class IntakePositionCommand extends PlayAnimationCommand {

  public IntakePositionCommand() {
    requires(arm);

    if (arm.shoulderController.getSetpoint() > 40) {
      setSpeed(1.0);
    } else {
      setSpeed(-1.0);
    }
    MotionProfileAnimation animation = new MotionProfileAnimation();
    setAnimation(animation);

    MotionProfileCurve shoulderCurve = new MotionProfileCurve(arm.shoulderController);
    MotionProfileCurve elbowCurve = new MotionProfileCurve(arm.elbowController);

    animation.addMotionProfileCurve(shoulderCurve);
    animation.addMotionProfileCurve(elbowCurve);

    shoulderCurve.storeValue(0.0, 51.0);
    shoulderCurve.storeValue(1.0, 51.0);

    elbowCurve.storeValue(0.0, -113.0);
    elbowCurve.storeValue(1.0, -113.0);
  }
}
