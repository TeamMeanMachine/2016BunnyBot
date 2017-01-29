package org.team2471.bunnybot.autonomouscommands;

import org.team2471.bunnybot.HardwareMap;
import org.team2471.frc.lib.motion_profiling.FollowPathTankDriveCommand;
import org.team2471.frc.lib.motion_profiling.Path2D;

import static org.team2471.bunnybot.Robot.driveTrain;

public class CircleTestAutonomous extends FollowPathTankDriveCommand {

  Path2D m_path;

  public CircleTestAutonomous(double speed) {

    requires(driveTrain);

    setSpeed(speed);
    setLeftController(HardwareMap.DriveTrainMap.leftMotor1);
    setRightController(HardwareMap.DriveTrainMap.rightMotor1);

    m_path = new Path2D();
    m_path.setTravelDirection(1.0);
    m_path.setRobotWidth(30.0 / 12);

//    m_path.addPointAndTangent(0.0, 0.0, 0.0, 3.0);
    m_path.addPointAndTangent(4.0, 4.0, 3.0, 0.0);
    m_path.addPointAndTangent(0.0, 8.0, 0.0, -3.0);
/*    m_path.addPointAndTangent(-4.0, 4.0, -3.0, 0.0);
    m_path.addPointAndTangent(0.0, 0.0, 0.0, 3.0);
    m_path.addPointAndTangent(4.0, 4.0, 3.0, 0.0);
    m_path.addPointAndTangent(0.0, 8.0, 0.0, -3.0);
    m_path.addPointAndTangent(-4.0, 4.0, -3.0, 0.0);
*/
    m_path.addEasePoint(0.0, 0.0);
    m_path.addEasePoint(8.0, 1.0);

    setPath(m_path);
  }
}
