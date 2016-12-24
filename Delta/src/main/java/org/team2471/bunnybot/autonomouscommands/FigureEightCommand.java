package org.team2471.bunnybot.autonomouscommands;

import edu.wpi.first.wpilibj.CANTalon;
import org.team2471.bunnybot.HardwareMap;
import org.team2471.bunnybot.Robot;
import org.team2471.frc.lib.motion_profiling.FollowPathTankDriveCommand;
import org.team2471.frc.lib.motion_profiling.Path2D;

import static org.team2471.bunnybot.HardwareMap.DriveTrainMap.shiftSolenoid;
import static org.team2471.bunnybot.Robot.driveTrain;
import static org.team2471.bunnybot.subsystems.DriveTrain.HIGH_SHIFTPOINT;
import static org.team2471.bunnybot.subsystems.DriveTrain.LOW_SHIFTPOINT;

public class FigureEightCommand extends FollowPathTankDriveCommand {

  Path2D m_path;

  @Override
  protected void execute() {
    super.execute();

    double averageSpeed = driveTrain.getSpeed();
    if (averageSpeed > HIGH_SHIFTPOINT) {
      shiftSolenoid.set(false);  // high gear
      HardwareMap.DriveTrainMap.leftMotor1.setProfile(1);
    } else if (averageSpeed < LOW_SHIFTPOINT) {
      shiftSolenoid.set(true);
      HardwareMap.DriveTrainMap.rightMotor1.setProfile(0);
    }
  }

  public FigureEightCommand(double speed ) {
    requires(driveTrain);

    setSpeed( speed );
    setLeftController(HardwareMap.DriveTrainMap.leftMotor1);
    setRightController(HardwareMap.DriveTrainMap.rightMotor1);

    m_path = new Path2D();

    //              seconds   x     y
    m_path.AddPoint(  0.0,   0.0,  0.0 );
    m_path.AddPoint(  0.5,   -0.16,  0.5 );
    m_path.AddPoint(  3.0,  -4.0,  4.0 );

    m_path.AddPoint( 5.0, -34.0, -4.0 );
    m_path.AddPoint( 6.0, -38.0,  0.0 );
    m_path.AddPoint( 7.0, -34.0,  4.0 );
    m_path.AddPoint( 11.0,  -4.0, -4.0 );
    m_path.AddPoint( 12.0,   0.0,  0.0 );

    setPath( m_path );
  }

  @Override
  protected void initialize() {
    driveTrain.resetEncoders();
    super.initialize();
    shiftSolenoid.set(true);
  }
}
