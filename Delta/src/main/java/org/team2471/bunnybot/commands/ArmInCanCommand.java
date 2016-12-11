package org.team2471.bunnybot.commands;

import edu.wpi.first.wpilibj.PIDController;
import javafx.animation.Animation;
import javafx.stage.Modality;

/**
 * Created by Bob on 12/10/2016.
 */
public class ArmInCanCommand extends PlayAnimationCommand {

  Animation animation;
  MotionProfileCurve shoulderCurve;
  MotionProfileCurve elbowCurve;
  PIDController shoulderController;
  PIDController elbowController;

  public ArmInCanCommand() {
    requires( ArmSubsystem );

    m_animation = new Animation();

    shoulderController = new PIDController( 1.0, 0.0, 0.0, shoulderEncoder, shoulderMotor );
    elbowController = new PIDController( 1.0, 0.0, 0.0, elbowEncoder, elbowMotor );

    shoulderCurve = new MotionProfileCurve( shoulderController );
    elbowCurve = new MotionProfileCurve( elbowController );

    animation.addMotionProfileCurve( shoulderCurve );
    animation.addMotionProfileCurve( elbowCurve );

    shoulderCurve.storeValue( 0.0, 0.0 );
    shoulderCurve.storeValue( 1.5, 100.0 );
    shoulderCurve.storeValue( 3.0, 80.0 );

    elbowCurve.storeValue( 0.0, 0.0 );
    elbowCurve.storeValue( 1.5, 60.0 );
    elbowCurve.storeValue( 3.0, 80.0 );
  }
}
