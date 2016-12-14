package org.team2471.bunnybot.commands;

import edu.wpi.first.wpilibj.PIDController;
import org.team2471.frc.lib.motion_profiling.MotionProfileAnimation;
import org.team2471.frc.lib.motion_profiling.MotionProfileCurve;
import org.team2471.frc.lib.motion_profiling.PlayAnimationCommand;

import static org.team2471.bunnybot.Robot.arm;

public class IntakePositionCommand extends PlayAnimationCommand{

  MotionProfileAnimation animation;
  MotionProfileCurve shoulderCurve;
  MotionProfileCurve elbowCurve;

  public IntakePositionCommand(double speed ) {
    requires(arm);

    setSpeed( speed );
    animation = new MotionProfileAnimation();
    setAnimation(animation);

    shoulderCurve = new MotionProfileCurve( arm.shoulderController );
    elbowCurve = new MotionProfileCurve( arm.elbowController );

    animation.addMotionProfileCurve( shoulderCurve );
    animation.addMotionProfileCurve( elbowCurve );

    shoulderCurve.storeValue( 0.0, 51.0 );
    shoulderCurve.storeValue( 1.0, 51.0 );

    elbowCurve.storeValue( 0.0, -113.0 );
    elbowCurve.storeValue( 1.0, -113.0 );
  }
}
