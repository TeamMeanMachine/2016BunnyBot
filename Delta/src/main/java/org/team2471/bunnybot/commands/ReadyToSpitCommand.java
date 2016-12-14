package org.team2471.bunnybot.commands;

import org.team2471.frc.lib.motion_profiling.MotionProfileAnimation;
import org.team2471.frc.lib.motion_profiling.MotionProfileCurve;
import org.team2471.frc.lib.motion_profiling.PlayAnimationCommand;
import static org.team2471.bunnybot.HardwareMap.Arm.*;
import org.team2471.frc.lib.motion_profiling.*;
import edu.wpi.first.wpilibj.PIDController;

public class ReadyToSpitCommand extends PlayAnimationCommand{
  MotionProfileAnimation animation;
  MotionProfileCurve shoulderCurve;
  MotionProfileCurve elbowCurve;
  PIDController shoulderController;
  PIDController elbowController;

  public ReadyToSpitCommand() {
    //requires(Arm);

    animation = new MotionProfileAnimation();
    setAnimation(animation);

    shoulderController = new PIDController( -0.04, -0.0, -0.01, shoulderEncoder, shoulderMotor );
    elbowController = new PIDController( -0.04, -0.0, -0.01, elbowEncoder, elbowMotor );

    shoulderCurve = new MotionProfileCurve( shoulderController );
    elbowCurve = new MotionProfileCurve( elbowController );

    animation.addMotionProfileCurve( shoulderCurve );
    animation.addMotionProfileCurve( elbowCurve );

    shoulderCurve.storeValue( 0.0, 51 );
    shoulderCurve.storeValue( 1.0, 36.0 );

    elbowCurve.storeValue( 0.0, -113.0 );
    elbowCurve.storeValue( 1.5, -64.0 );
  }
}
