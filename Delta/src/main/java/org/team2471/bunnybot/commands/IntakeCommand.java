package org.team2471.bunnybot.commands;

import org.team2471.frc.lib.motion_profiling.MotionProfileAnimation;
import org.team2471.frc.lib.motion_profiling.MotionProfileCurve;
import org.team2471.frc.lib.motion_profiling.PlayAnimationCommand;

import static org.team2471.bunnybot.Robot.arm;

public class IntakeCommand extends PlayAnimationCommand {

  MotionProfileAnimation groundAnimation;
  MotionProfileCurve groundShoulderCurve;
  MotionProfileCurve groundElbowCurve;

  MotionProfileAnimation canAnimation;
  MotionProfileCurve canShoulderCurve;
  MotionProfileCurve canElbowCurve;

  public IntakeCommand(double speed ) {
    requires(arm);

    // ground pickup groundAnimation
    setSpeed( speed );
    groundAnimation = new MotionProfileAnimation();
    groundShoulderCurve = new MotionProfileCurve( arm.shoulderController );
    groundElbowCurve = new MotionProfileCurve( arm.elbowController );
    groundAnimation.addMotionProfileCurve(groundShoulderCurve);
    groundAnimation.addMotionProfileCurve(groundElbowCurve);

    groundShoulderCurve.storeValue( 0.0, 51.0 );
    groundShoulderCurve.storeValue( 1.0, 51.0 );

    groundElbowCurve.storeValue( 0.0, -113.0 );
    groundElbowCurve.storeValue( 1.0, -113.0 );

    // can pickup groundAnimation
    canAnimation = new MotionProfileAnimation();
    canShoulderCurve = new MotionProfileCurve( arm.shoulderController );
    canElbowCurve = new MotionProfileCurve( arm.elbowController );
    canAnimation.addMotionProfileCurve(canShoulderCurve);
    canAnimation.addMotionProfileCurve(canElbowCurve);

    canShoulderCurve.storeValue( 0.0, 26);
    canShoulderCurve.storeValue( 1.5, -13 );
    canShoulderCurve.storeValue( 3.0, -32 );

    canElbowCurve.storeValue( 0.0, -63 );
    canElbowCurve.storeValue( 1.5, -84 );
    canElbowCurve.storeValue( 3.0, -91 );
  }

  @Override
  protected void initialize() {
    if (arm.shoulderController.getSetpoint() > 40) {
      setAnimation( groundAnimation );
    }
    else {
      setAnimation( canAnimation );
    }

    super.initialize();
    if (getSpeed() > 0) {  // running groundAnimation forward
      arm.suckIn();
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    arm.stopIntake();
    super.end();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
