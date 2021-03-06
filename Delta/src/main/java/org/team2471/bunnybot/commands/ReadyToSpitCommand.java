package org.team2471.bunnybot.commands;

import org.team2471.frc.lib.motion_profiling.MotionProfileAnimation;
import org.team2471.frc.lib.motion_profiling.MotionProfileCurve;
import org.team2471.frc.lib.motion_profiling.PlayAnimationCommand;

import static org.team2471.bunnybot.Robot.arm;

public class ReadyToSpitCommand extends PlayAnimationCommand{

  MotionProfileAnimation animation;
  MotionProfileCurve shoulderCurve;
  MotionProfileCurve elbowCurve;

  public ReadyToSpitCommand() {
    requires(arm);

    animation = new MotionProfileAnimation();
    shoulderCurve = new MotionProfileCurve( arm.shoulderController, animation );
    elbowCurve = new MotionProfileCurve( arm.elbowController, animation );

    shoulderCurve.storeValue( 0.0, 51.0 );
    shoulderCurve.storeValue( 0.5, 44.0 );
    shoulderCurve.storeValue( 0.75, 26.0 );

    elbowCurve.storeValue( 0.0, -110.0 );
    elbowCurve.storeValue( 0.5, -70.0 );
    elbowCurve.storeValue( 0.75, -55.0 );

    setAnimation(animation);
  }

  @Override
  protected void initialize() {
    super.initialize();
    if (arm.shoulderEncoder.pidGet() > 40) {
      setSpeed(1.0);
    }
    else {
      setSpeed(-0.75);
    }
  }
}
