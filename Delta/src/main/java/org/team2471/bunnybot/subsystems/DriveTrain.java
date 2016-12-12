package org.team2471.bunnybot.subsystems;

//import com.team254.frc2016.CheesyDriveHelper;
//import com.team254.lib.util.DriveSignal;
import org.team2471.bunnybot.defaultcommands.DriveTrainDefaultCommand;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import static org.team2471.bunnybot.HardwareMap.Drivetrain.*;

public class DriveTrain extends Subsystem {
  //private CheesyDriveHelper cheesyDriveHelper;
  private static final double LOWSHIFTPOINT = 6.0;
  private static final double HIGHSHIFTPOINT = 8.0;

  public DriveTrain() {

    //rightMotor1.reverseOutput(true);
    rightMotor2.changeControlMode(CANTalon.TalonControlMode.Follower);
    rightMotor3.changeControlMode(CANTalon.TalonControlMode.Follower);
    rightMotor2.set(rightMotor1.getDeviceID());
    rightMotor3.set(rightMotor1.getDeviceID());

    leftMotor2.changeControlMode(CANTalon.TalonControlMode.Follower);
    leftMotor3.changeControlMode(CANTalon.TalonControlMode.Follower);
    leftMotor2.set(leftMotor1.getDeviceID());
    leftMotor3.set(leftMotor1.getDeviceID());

    //cheesyDriveHelper = new CheesyDriveHelper();
  }

  public void drive(double throttle, double turn) {
/*   if (SmartDashboard.getBoolean("CheesyDrive", false)) {
     DriveSignal driveSignal = cheesyDriveHelper.cheesyDrive(throttle, turn, false);

     rightMotor1.set(driveSignal.rightMotor);
     leftMotor1.set(driveSignal.leftMotor);
   }
   else {*/
     double left = throttle + turn;
     double right = throttle - turn;

     rightMotor1.set(-right);
     leftMotor1.set(left);
//   }
    if (leftMotor1.getSpeed() > HIGHSHIFTPOINT) {
      shiftSolenoid.set(true);
    }
    else if(leftMotor1.getSpeed() < LOWSHIFTPOINT) {
      shiftSolenoid.set(false);
    }
  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new DriveTrainDefaultCommand());
  }


}