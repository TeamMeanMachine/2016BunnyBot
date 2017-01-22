package org.team2471.bunnybot.autonomouscommands;

import org.team2471.bunnybot.HardwareMap;
import org.team2471.frc.lib.motion_profiling.FollowPathTankDriveCommand;
import org.team2471.frc.lib.motion_profiling.Path2D;

import static org.team2471.bunnybot.Robot.driveTrain;

public class DriveToHopperFromLeftLift extends FollowPathTankDriveCommand {
  Path2D m_path;
  public DriveToHopperFromLeftLift(double speed){
    requires(driveTrain);

    setSpeed(speed);
    setLeftController(HardwareMap.DriveTrainMap.leftMotor1);
    setRightController(HardwareMap.DriveTrainMap.rightMotor1);

    m_path = new Path2D();
    m_path.setTravelDirection(1.0);

    m_path.addPointAndTangent(5.0, 7.0, 0.0, 0.0);
    m_path.addPointAndTangent(-7.0, 8.0, 3.0, 0.0);

    m_path.addEasePoint(0.0, 0.0);
    m_path.addEasePoint(3.0, 0.0);

    setPath(m_path);
  }
}
