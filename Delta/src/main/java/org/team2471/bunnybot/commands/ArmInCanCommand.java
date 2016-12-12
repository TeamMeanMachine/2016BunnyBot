package org.team2471.bunnybot.commands;

import edu.wpi.first.wpilibj.PIDController;
import javafx.animation.Animation;
import javafx.stage.Modality;
import org.team2471.frc.lib.motion_profiling.*;

public class ArmInCanCommand extends PlayAnimationCommand {

  Animation animation;
  MotionProfilingCurve shoulderCurve;
  MotionProfilingCurve elbowCurve;
  PIDController shoulderController;
  PIDController elbowController;

  public ArmInCanCommand() {
    requires( ArmSubsystem );

    animation = new Animation();
    setAnimation( animation );

    shoulderController = new PIDController( 1.0, 0.0, 0.0, shoulderEncoder, shoulderMotor );
    elbowController = new PIDController( 1.0, 0.0, 0.0, elbowEncoder, elbowMotor );

    shoulderCurve = new MotionProfilingCurve( shoulderController );
    elbowCurve = new MotionProfilingCurve( elbowController );

    animation.addMotionProfilingCurve( shoulderCurve );
    animation.addMotionProfilingCurve( elbowCurve );

    shoulderCurve.storeValue( 0.0, 0.0 );
    shoulderCurve.storeValue( 1.5, 100.0 );
    shoulderCurve.storeValue( 3.0, 80.0 );

    elbowCurve.storeValue( 0.0, 0.0 );
    elbowCurve.storeValue( 1.5, 60.0 );
    elbowCurve.storeValue( 3.0, 80.0 );
  }
}
