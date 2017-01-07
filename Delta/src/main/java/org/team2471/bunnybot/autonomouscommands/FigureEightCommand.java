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

/*
    double averageSpeed = driveTrain.getSpeed();
    if (averageSpeed > HIGH_SHIFTPOINT) {
      shiftSolenoid.set(false);  // high gear
      HardwareMap.DriveTrainMap.leftMotor1.setProfile(1);
    } else if (averageSpeed < LOW_SHIFTPOINT) {
      shiftSolenoid.set(true);
      HardwareMap.DriveTrainMap.rightMotor1.setProfile(0);
    }
*/
  }

  public FigureEightCommand( double speed ) {
    requires(driveTrain);

    setSpeed( speed * 0.25 );
    setLeftController(HardwareMap.DriveTrainMap.leftMotor1);
    setRightController(HardwareMap.DriveTrainMap.rightMotor1);

    m_path = new Path2D();
//    m_path.setRobotWidth( 26.0 / 12.0 );

    m_path.addPointAndTangent( 0.0,  0.0, 0.0, 1.0 );
    m_path.addPoint(  -4.0,  4.0 );
    m_path.addPoint( -34.0, -4.0 );
    m_path.addPoint( -38.0,  0.0 );
    m_path.addPoint( -34.0,  4.0 );
    m_path.addPoint(  -4.0, -4.0 );
    m_path.addPointAndTangent( 0.0,  0.0, 0.0, 1.0 );

    m_path.addEasePoint(    0,   0 );
    m_path.addEasePoint(  7.0,0.40 );  // two middle points to create a slowdown
    m_path.addEasePoint( 13.0,0.60 );  // two middle points to create a slowdown
    m_path.addEasePoint( 20.0, 1.0 );

    setPath( m_path );
  }

  @Override
  protected void initialize() {
    super.initialize();
    shiftSolenoid.set(true);
  }
}
