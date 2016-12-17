package org.team2471.bunnybot.commands;

import org.team2471.bunnybot.IOMap;
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

  static MotionProfileAnimation previousAnimation;

  public IntakeCommand(double speed ) {
    requires(arm);

    // ground pickup Animation
    setSpeed( speed );
    groundAnimation = new MotionProfileAnimation();
    groundShoulderCurve = new MotionProfileCurve( arm.shoulderController );
    groundElbowCurve = new MotionProfileCurve( arm.elbowController );
    groundAnimation.addMotionProfileCurve(groundShoulderCurve);
    groundAnimation.addMotionProfileCurve(groundElbowCurve);

    groundShoulderCurve.storeValue( 0.0, 51.0 );
    groundShoulderCurve.storeValue( 0.25, 51.0 );
    groundShoulderCurve.storeValue( 1.0, -18.0 );

    groundElbowCurve.storeValue( 0.0, -113.0 );
    groundElbowCurve.storeValue( 0.5, -100.0 );

    // can pickup Animation
    canAnimation = new MotionProfileAnimation();
    canShoulderCurve = new MotionProfileCurve( arm.shoulderController );
    canElbowCurve = new MotionProfileCurve( arm.elbowController );
    canAnimation.addMotionProfileCurve(canShoulderCurve);
    canAnimation.addMotionProfileCurve(canElbowCurve);

    canShoulderCurve.storeValue( 0.0, 26);
    canShoulderCurve.storeValue( 0.75, -13 );
    canShoulderCurve.storeValue( 1.25, -32 );

    canElbowCurve.storeValue( 0.0, -63 );
    canElbowCurve.storeValue( 0.75, -84 );
    canElbowCurve.storeValue( 1.25, -91 );
  }

  @Override
  protected void initialize() {
    if (getSpeed()>0)  // running forward (starting intake)
    {
      if (arm.shoulderController.getSetpoint() > 40) {  // in the low position
        setAnimation( groundAnimation );
        previousAnimation = groundAnimation;
      }
      else {
        setAnimation( canAnimation );  // in the can position
        previousAnimation = canAnimation;
      }
    }
    else {
      setAnimation( previousAnimation );
    }

    super.initialize();
    if (getSpeed() > 0) {  // running groundAnimation forward
      arm.suckIn();
    }
  }

  @Override
  protected boolean isFinished() {
    if (getSpeed() > 0) {  // running Animation forward
      return false;  // when button is released the scheduler will interrupt us
    }
    else {  // on button release, play animation to the end.
      return super.isFinished();
    }
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
