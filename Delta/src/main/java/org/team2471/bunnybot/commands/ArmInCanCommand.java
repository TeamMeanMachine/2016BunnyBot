package org.team2471.bunnybot.commands;

import edu.wpi.first.wpilibj.PIDController;
import javafx.animation.Animation;
import org.team2471.bunnybot.subsystems.Arm;
import org.team2471.frc.lib.motion_profiling.*;
import static org.team2471.bunnybot.HardwareMap.Arm.*;

public class ArmInCanCommand extends PlayAnimationCommand {

  MotionProfileAnimation animation;
  MotionProfileCurve shoulderCurve;
  MotionProfileCurve elbowCurve;
  PIDController shoulderController;
  PIDController elbowController;

  public ArmInCanCommand() {

    // requires( Arm );

    animation = new MotionProfileAnimation();
    setAnimation( animation );

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
