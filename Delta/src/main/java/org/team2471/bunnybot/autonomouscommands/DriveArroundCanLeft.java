package org.team2471.bunnybot.autonomouscommands;

import org.team2471.bunnybot.HardwareMap;
import org.team2471.frc.lib.motion_profiling.FollowPathTankDriveCommand;
import org.team2471.frc.lib.motion_profiling.Path2D;

import static org.team2471.bunnybot.HardwareMap.DriveTrainMap.shiftSolenoid;
import static org.team2471.bunnybot.Robot.driveTrain;

public class DriveArroundCanLeft extends FollowPathTankDriveCommand {

  Path2D m_path;

  public DriveArroundCanLeft(double speed ) {
    requires(driveTrain);

    setSpeed( speed );
    setLeftController(HardwareMap.DriveTrainMap.leftMotor1);
    setRightController(HardwareMap.DriveTrainMap.rightMotor1);

    m_path = new Path2D();

    m_path.addPointAndTangent(0.0,0.0, 0.0, 3.0 );
    m_path.addPoint(5.0,5.0 );
    m_path.addPointAndTangent(10.0,0.0, 0.0, -3.0 );
    m_path.addPoint(10.0,-7 );

    m_path.addEasePoint( 0.0, 0.0 );
    m_path.addEasePoint( 8.0, 1.0 );

    setPath( m_path );
  }

  @Override
  protected void initialize() {
    driveTrain.resetEncoders();
    super.initialize();
    System.out.println("Got To Drive Six Feet Initialize");
    shiftSolenoid.set(true);
  }

  @Override
  protected void end() {
    super.end();
  }

  @Override
  protected void interrupted() {
    super.interrupted();
    end();
  }
}
