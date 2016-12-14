package org.team2471.bunnybot.commands;

import org.team2471.frc.lib.motion_profiling.MotionProfileAnimation;
import org.team2471.frc.lib.motion_profiling.MotionProfileCurve;
import org.team2471.frc.lib.motion_profiling.PlayAnimationCommand;
import static org.team2471.bunnybot.HardwareMap.Arm.*;
import static org.team2471.bunnybot.Robot.arm;

import org.team2471.frc.lib.motion_profiling.*;
import edu.wpi.first.wpilibj.PIDController;

public class ReadyToSpitCommand extends PlayAnimationCommand{
  MotionProfileAnimation animation;
  MotionProfileCurve shoulderCurve;
  MotionProfileCurve elbowCurve;
  PIDController shoulderController;
  PIDController elbowController;

  public ReadyToSpitCommand( double speed ) {
    requires(arm);

    setSpeed( speed );
    animation = new MotionProfileAnimation();
    setAnimation(animation);

    shoulderCurve = new MotionProfileCurve( arm.shoulderController );
    elbowCurve = new MotionProfileCurve( arm.elbowController );

    animation.addMotionProfileCurve( shoulderCurve );
    animation.addMotionProfileCurve( elbowCurve );

    shoulderCurve.storeValue( 0.0, 51 );
    shoulderCurve.storeValue( 1.0, 26.0 );

    elbowCurve.storeValue( 0.0, -113.0 );
    elbowCurve.storeValue( 1.0, -63.0 );
  }
}
