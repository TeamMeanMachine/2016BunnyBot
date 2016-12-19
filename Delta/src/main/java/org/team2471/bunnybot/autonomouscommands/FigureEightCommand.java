package org.team2471.bunnybot.autonomouscommands;

import org.team2471.bunnybot.Robot;
import org.team2471.frc.lib.motion_profiling.FollowPathTankDriveCommand;
import org.team2471.frc.lib.motion_profiling.Path2D;

public class FigureEightCommand extends FollowPathTankDriveCommand {

  Path2D m_path;

  public FigureEightCommand( double speed ) {

    setSpeed( speed );
    setLeftController(Robot.driveTrain.m_leftController);
    setRightController(Robot.driveTrain.m_leftController);

    m_path = new Path2D();

    //              seconds   x     y
    m_path.AddPoint(  0.0,   0.0,  0.0 );
    m_path.AddPoint(  2.0,  -4.0,  4.0 );
    m_path.AddPoint( 10.0, -32.0, -4.0 );
    m_path.AddPoint( 12.0, -36.0,  0.0 );
    m_path.AddPoint( 14.0, -32.0,  4.0 );
    m_path.AddPoint( 22.0,  -4.0, -4.0 );
    m_path.AddPoint( 24.0,   0.0,  0.0 );

    setPath( m_path );
  }

  @Override
  protected void initialize() {
    Robot.driveTrain.resetEncoders();
    super.initialize();
  }
}
