package org.team2471.bunnybot.autonomouscommands;

import org.team2471.bunnybot.HardwareMap;
import org.team2471.frc.lib.motion_profiling.FollowPathTankDriveCommand;
import org.team2471.frc.lib.motion_profiling.Path2D;

import static org.team2471.bunnybot.HardwareMap.DriveTrainMap.shiftSolenoid;
import static org.team2471.bunnybot.Robot.driveTrain;

public class DriveToHopperFromLeftLift extends FollowPathTankDriveCommand {
  Path2D m_path;
  public DriveToHopperFromLeftLift(double speed, boolean mirror){
    requires(driveTrain);

    setSpeed(speed);
    setMirrorPath(mirror);
    setLeftController(HardwareMap.DriveTrainMap.leftMotor1);
    setRightController(HardwareMap.DriveTrainMap.rightMotor1);

    m_path = new Path2D();
    m_path.setTravelDirection(1.0);

    m_path.addPointAndTangent(4.8, 3.0, -1.5, 1.0);
    m_path.addPointAndTangent(-0.1, 7.8, -10.0, 0.0);

    m_path.addEasePoint(0.0, 0.0);
    m_path.addEasePoint(3.0, 1.0);

    setPath(m_path);
  }
  @Override
  protected void initialize() {
    super.initialize();
    shiftSolenoid.set(true);
  }
}
