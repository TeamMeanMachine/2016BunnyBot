package org.team2471.bunnybot.autonomouscommands;

import org.team2471.bunnybot.HardwareMap;
import org.team2471.frc.lib.motion_profiling.FollowPathTankDriveCommand;
import org.team2471.frc.lib.motion_profiling.Path2D;

import static org.team2471.bunnybot.HardwareMap.DriveTrainMap.shiftSolenoid;
import static org.team2471.bunnybot.Robot.driveTrain;

public class TurnToCan extends FollowPathTankDriveCommand {

  Path2D m_path;

  public TurnToCan(double speed ) {
    requires(driveTrain);

    setSpeed( speed );
    setLeftController(HardwareMap.DriveTrainMap.leftMotor1);
    setRightController(HardwareMap.DriveTrainMap.rightMotor1);

    m_path = new Path2D();
    m_path.setTravelDirection(-1.0);

    m_path.addPointAndTangent( 0.0, 0.0, 0.0, 8.0 );
    m_path.addPointAndTangent( 11.7, 0.0, 0.0, -8.0 );

    m_path.addEasePoint( 0.0, 0.0 );
    m_path.addEasePoint( 3.0, 1.0 );

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
