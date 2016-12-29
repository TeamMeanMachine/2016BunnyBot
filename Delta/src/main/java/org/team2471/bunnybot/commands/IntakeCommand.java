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
    groundShoulderCurve = new MotionProfileCurve( arm.shoulderController, groundAnimation );
    groundElbowCurve = new MotionProfileCurve( arm.elbowController, groundAnimation );

    groundShoulderCurve.storeValue( 0.0, 51.0 );
    groundShoulderCurve.storeValue( 0.25, 51.0 );
    groundShoulderCurve.storeValue( 1.0, -22.0 );

    groundElbowCurve.storeValue( 0.0, -110.0 );
    groundElbowCurve.storeValue( 0.5, -90.0 );

    // can pickup Animation
    canAnimation = new MotionProfileAnimation();
    canShoulderCurve = new MotionProfileCurve( arm.shoulderController, canAnimation );
    canElbowCurve = new MotionProfileCurve( arm.elbowController, canAnimation );

    canShoulderCurve.storeValue( 0.0, 26);
    canShoulderCurve.storeValue( 0.75, -13 );
    canShoulderCurve.storeValue( 1.25, -27 );

    canElbowCurve.storeValue( 0.0, -55 );
    canElbowCurve.storeValue( 0.75, -76 );
    canElbowCurve.storeValue( 1.25, -83 );
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
    if (getSpeed() > 0) {  // running lowerAnimation forward
      arm.suckIn(1.0);
    }
  }

  @Override
  protected boolean isFinished() {
    if (getSpeed() > 0) {  // running Animation forward
      return isTimedOut();  // when button is released the scheduler will interrupt us
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
