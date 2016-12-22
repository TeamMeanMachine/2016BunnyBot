package org.team2471.bunnybot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Preferences;
import org.team2471.bunnybot.subsystems.Arm;
import org.team2471.bunnybot.subsystems.DriveTrain;
import org.team2471.bunnybot.subsystems.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static org.team2471.bunnybot.HardwareMap.DriveTrainMap.leftMotor1;
import static org.team2471.bunnybot.HardwareMap.DriveTrainMap.rightMotor1;

public class Robot extends IterativeRobot {
  public static DriveTrain driveTrain;
  public static Shooter shooter;
  public static Arm arm;
  private static Preferences prefs;

  double startLeft;
  double startRight;

  @Override
  public void robotInit() {
    // Ensure we get logs when we crash here
    FRCNetworkCommunicationsLibrary.FRCNetworkCommunicationObserveUserProgramStarting();

    SmartDashboard.putBoolean("Disable Cheesy Drive", false);
    driveTrain = new DriveTrain();
    shooter = new Shooter();
    arm = new Arm();
    prefs = Preferences.getInstance();

    SmartDashboard.putBoolean("Cheesy Drive", prefs.getBoolean("Cheesy Drive", true));

    // make sure IOMap is initialized
    IOMap.getInstance();
  }

  @Override
  public void teleopInit() {
    // tune the drive position PID's
    SmartDashboard.putData("Left PID", driveTrain.m_leftController);
    SmartDashboard.putData("Right PID", driveTrain.m_rightController);
//    driveTrain.m_leftController.enable();
//    driveTrain.m_rightController.enable();

    leftMotor1.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
    leftMotor1.reverseSensor(false);
    leftMotor1.configEncoderCodesPerRev(820*4);
    leftMotor1.changeControlMode(CANTalon.TalonControlMode.Position);
    leftMotor1.setProfile(0);
    leftMotor1.setF(0);
    leftMotor1.setP(0.1);
    leftMotor1.setI(0);
    leftMotor1.setD(0);

    rightMotor1.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
    rightMotor1.reverseSensor(true);
    rightMotor1.reverseOutput(true);
    rightMotor1.configEncoderCodesPerRev(820*4);
    rightMotor1.changeControlMode(CANTalon.TalonControlMode.Position);
    rightMotor1.setProfile(0);
    rightMotor1.setF(0);
    rightMotor1.setP(0.1);
    rightMotor1.setI(0);
    rightMotor1.setD(0);

    startLeft = leftMotor1.getEncPosition();
    startRight = rightMotor1.getEncPosition();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

    // tune the drive position PID's
    double leftStick = IOMap.getInstance().coPilotController.getAxis(1).get();
    double rightStick = IOMap.getInstance().coPilotController.getAxis(5).get();
    if (Math.abs(leftStick)<0.1)
      leftStick = 0.0;
    if (Math.abs(rightStick)<0.1)
      rightStick = 0.0;

//    driveTrain.m_leftController.setSetpoint( leftStick * 4.0);
//    driveTrain.m_rightController.setSetpoint( rightStick * 4.0);

//    leftMotor1.set( leftStick );
//    rightMotor1.set( rightStick );

    leftMotor1.set( leftStick );
    rightMotor1.set( rightStick );

    SmartDashboard.putNumber( "Left Power", leftMotor1.get() );
    SmartDashboard.putNumber( "Right Power", rightMotor1.get() );

//    SmartDashboard.putNumber( "Left Error", driveTrain.m_leftController.getError() );
//    SmartDashboard.putNumber( "Right Error", driveTrain.m_rightController.getError() );

  }

  @Override
  public void disabledInit() {
    prefs.putBoolean("Cheesy Drive", SmartDashboard.getBoolean("Cheesy Drive", true));
  }

  @Override
  public void disabledPeriodic() {
    SmartDashboard.putNumber("Elbow Angle", arm.getElbowAngle());
    SmartDashboard.putNumber("Shoulder Angle", arm.getShoulderAngle());
  }
}
