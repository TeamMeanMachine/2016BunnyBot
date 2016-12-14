package org.team2471.bunnybot.commands;

import edu.wpi.first.wpilibj.PIDController;
import org.team2471.bunnybot.subsystems.Arm;
import org.team2471.frc.lib.motion_profiling.*;

import static org.team2471.bunnybot.HardwareMap.Arm.*;
import static org.team2471.bunnybot.Robot.arm;

public class ArmInCanCommand extends PlayAnimationCommand {

  MotionProfileAnimation animation;
  MotionProfileCurve shoulderCurve;
  MotionProfileCurve elbowCurve;

  public ArmInCanCommand( double speed ) {

    requires( arm );

    setSpeed( speed );
    animation = new MotionProfileAnimation();
    setAnimation(animation);

    shoulderCurve = new MotionProfileCurve( arm.shoulderController );
    elbowCurve = new MotionProfileCurve( arm.elbowController );

    animation.addMotionProfileCurve( shoulderCurve );
    animation.addMotionProfileCurve( elbowCurve );

    shoulderCurve.storeValue( 0.0, 26);
    shoulderCurve.storeValue( 1.5, -13 );
    shoulderCurve.storeValue( 3.0, -32 );

    elbowCurve.storeValue( 0.0, -63 );
    elbowCurve.storeValue( 1.5, -84 );
    elbowCurve.storeValue( 3.0, -91 );
  }
}
