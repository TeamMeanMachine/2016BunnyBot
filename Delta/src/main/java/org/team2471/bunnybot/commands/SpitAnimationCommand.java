package org.team2471.bunnybot.commands;

import org.team2471.frc.lib.motion_profiling.MotionProfileAnimation;
import org.team2471.frc.lib.motion_profiling.MotionProfileCurve;
import org.team2471.frc.lib.motion_profiling.PlayAnimationCommand;

import static org.team2471.bunnybot.Robot.arm;

public class SpitAnimationCommand extends PlayAnimationCommand {

  MotionProfileAnimation lowerAnimation;
  MotionProfileCurve lowerShoulderCurve;
  MotionProfileCurve lowerElbowCurve;

  MotionProfileAnimation upperAnimation;
  MotionProfileCurve upperShoulderCurve;
  MotionProfileCurve upperElbowCurve;

  static MotionProfileAnimation previousAnimation;

  public SpitAnimationCommand(double speed ) {
    requires(arm);

    // floor spit Animation
    setSpeed( speed );
    lowerAnimation = new MotionProfileAnimation();
    lowerShoulderCurve = new MotionProfileCurve( arm.shoulderController, lowerAnimation );
    lowerElbowCurve = new MotionProfileCurve( arm.elbowController, lowerAnimation );

    lowerShoulderCurve.storeValue( 0.0, 51.0 );
    lowerShoulderCurve.storeValue( 0.5, 44.0 );
    lowerShoulderCurve.storeValue( 1.0, 28.0 );
    lowerShoulderCurve.storeValue( 1.5, -8.0 );

    lowerElbowCurve.storeValue( 0.0, -110.0 );
    lowerElbowCurve.storeValue( 0.5, -70.0 );
    lowerElbowCurve.storeValue( 1.0, -50.0 );
    lowerElbowCurve.storeValue( 1.5, -65.0 );

    // spit from praying mantis position
    upperAnimation = new MotionProfileAnimation();
    upperShoulderCurve = new MotionProfileCurve( arm.shoulderController, upperAnimation );
    upperElbowCurve = new MotionProfileCurve( arm.elbowController, upperAnimation );

    upperShoulderCurve.storeValue( 0.0, 28.0);
    upperShoulderCurve.storeValue( 0.5, 0.0 );

    upperElbowCurve.storeValue( 0.0, -55.0 );
    upperElbowCurve.storeValue( 0.5, -65.0 );
  }

  @Override
  protected void initialize() {
    if (getSpeed()>0)  // running forward (starting intake)
    {
      if (arm.shoulderController.getSetpoint() > 40) {  // in the low position
        setAnimation(lowerAnimation);
        previousAnimation = lowerAnimation;
      }
      else {
        setAnimation(upperAnimation);  // in the can position
        previousAnimation = upperAnimation;
      }
    }
    else {
      setAnimation( previousAnimation );
    }

    super.initialize();
  }

  @Override
  protected void execute() {
    super.execute();

    if (getSpeed() > 0 && getTime() >= getLength()) {  // running lowerAnimation forward
      arm.spitOut(0.6);
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
