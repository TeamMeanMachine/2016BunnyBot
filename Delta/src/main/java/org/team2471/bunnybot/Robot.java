package org.team2471.bunnybot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team2471.bunnybot.subsystems.DriveTrain;
import org.team2471.bunnybot.subsystems.Shooter;
import org.team2471.bunnybot.subsystems.Arm;

import static org.team2471.bunnybot.HardwareMap.Arm.*;
import static org.team2471.bunnybot.IOMap.*;

public class Robot extends IterativeRobot {

  public static DriveTrain driveTrain;
  public static Shooter shooter;
  public static Arm arm;

  PIDController shoulderController;
  PIDController elbowController;

  @Override
  public void robotInit() {
    driveTrain = new DriveTrain();
    shooter = new Shooter();
    arm = new Arm();
  }

  @Override
  public void teleopInit() {
//    shoulderController = new PIDController( -0.04, -0.0, -0.01, shoulderEncoder, shoulderMotor );
//    SmartDashboard.putData("Shoulder PID", shoulderController);
//    shoulderController.enable();
//
//    elbowController = new PIDController( -0.04, -0.0, -0.01, elbowEncoder, elbowMotor );
//    SmartDashboard.putData("Elbow PID", elbowController);
//    elbowController.enable();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

/*    shoulderController.setSetpoint( shoulderAxis.get() * 20 + 30 );
    elbowController.setSetpoint( elbowAxis.get() * 20 - 85 );

    SmartDashboard.putNumber("Elbow Angle", arm.getElbowAngle());
    SmartDashboard.putNumber("Shoulder Angle", arm.getShoulderAngle());

    SmartDashboard.putNumber("Error", elbowController.getError());
    SmartDashboard.putNumber("Output", elbowMotor.get());*/

  }

  @Override
  public void disabledPeriodic() {

    SmartDashboard.putNumber("Elbow Angle", arm.getElbowAngle());
    SmartDashboard.putNumber("Shoulder Angle", arm.getShoulderAngle());
  }
}
