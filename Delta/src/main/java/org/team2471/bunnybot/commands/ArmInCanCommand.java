package org.team2471.bunnybot.commands;

import edu.wpi.first.wpilibj.PIDController;
import javafx.animation.Animation;
import org.team2471.bunnybot.subsystems.Arm;
import org.team2471.frc.lib.motion_profiling.*;
import static org.team2471.bunnybot.HardwareMap.Arm.*;
import static org.team2471.bunnybot.Robot.arm;

public class ArmInCanCommand extends PlayAnimationCommand {

  MotionProfileAnimation animation;
  MotionProfileCurve shoulderCurve;
  MotionProfileCurve elbowCurve;
  PIDController shoulderController;
  PIDController elbowController;

  public ArmInCanCommand() {
    requires( arm );

    animation = new MotionProfileAnimation();
    setAnimation(animation);

    shoulderController = new PIDController( -0.04, -0.0, -0.01, shoulderEncoder, shoulderMotor );
    elbowController = new PIDController( -0.04, -0.0, -0.01, elbowEncoder, elbowMotor );
    shoulderController.setAbsoluteTolerance(2.0);
    elbowController.setAbsoluteTolerance(2.0);

    shoulderCurve = new MotionProfileCurve( shoulderController );
    elbowCurve = new MotionProfileCurve( elbowController );

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
