package org.team2471.bunnybot.autonomouscommands;

import org.team2471.bunnybot.HardwareMap;
import org.team2471.frc.lib.motion_profiling.FollowPathTankDriveCommand;
import org.team2471.frc.lib.motion_profiling.Path2D;

import static org.team2471.bunnybot.HardwareMap.DriveTrainMap.shiftSolenoid;
import static org.team2471.bunnybot.Robot.driveTrain;

public class CoOpHopperAutoBlue extends FollowPathTankDriveCommand {
  Path2D m_path;

  public CoOpHopperAutoBlue(double speed, boolean mirror ) {
    requires(driveTrain);

    setSpeed( speed );
    setMirrorPath(mirror);
    setLeftController(HardwareMap.DriveTrainMap.leftMotor1);
    setRightController(HardwareMap.DriveTrainMap.rightMotor1);

    m_path = new Path2D();

    m_path.addPointAndTangent(0.0,0.0, 0.0, 3.0 );
    m_path.addPointAndTangent(-4.75,10.6, -12.0, 0.0 );

    m_path.addEasePoint( 0.0, 0.0 );
    m_path.addEasePoint( 4.0, 1.0 );

    setPath( m_path );
  }

  @Override
  protected void initialize() {
    super.initialize();
    shiftSolenoid.set(true);
  }
}
