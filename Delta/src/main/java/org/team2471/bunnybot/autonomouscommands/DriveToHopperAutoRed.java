package org.team2471.bunnybot.autonomouscommands;

import edu.wpi.first.wpilibj.command.Command;
import org.team2471.bunnybot.HardwareMap;
import org.team2471.frc.lib.motion_profiling.FollowPathTankDriveCommand;
import org.team2471.frc.lib.motion_profiling.Path2D;

import static org.team2471.bunnybot.HardwareMap.DriveTrainMap.shiftSolenoid;
import static org.team2471.bunnybot.Robot.driveTrain;

public class DriveToHopperAutoRed extends FollowPathTankDriveCommand {
  Path2D m_path;

  public DriveToHopperAutoRed(double speed ) {
    requires(driveTrain);

    setSpeed( speed );
    setLeftController(HardwareMap.DriveTrainMap.leftMotor1);
    setRightController(HardwareMap.DriveTrainMap.rightMotor1);

    m_path = new Path2D();

    m_path.addPointAndTangent(0.0,0.0, 0.0, 3.0 );
    m_path.addPointAndTangent(3.3,6.5, 10.0, 0.0 );

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
