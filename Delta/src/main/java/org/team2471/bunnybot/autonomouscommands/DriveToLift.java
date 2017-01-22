package org.team2471.bunnybot.autonomouscommands;

import org.team2471.bunnybot.HardwareMap;
import org.team2471.frc.lib.motion_profiling.FollowPathTankDriveCommand;
import org.team2471.frc.lib.motion_profiling.Path2D;

import static org.team2471.bunnybot.Robot.driveTrain;
//this autonomous works only if robot is right in front of the lift(in a straight line)
public class DriveToLift extends FollowPathTankDriveCommand{

    Path2D m_path;

    public DriveToLift(double speed){

        requires(driveTrain);

        setSpeed( speed );
        setLeftController(HardwareMap.DriveTrainMap.leftMotor1);
        setRightController(HardwareMap.DriveTrainMap.rightMotor1);

        m_path = new Path2D();
        m_path.setTravelDirection(1.0);

        m_path.addPoint( 0.0, 0.0);
        m_path.addPoint( 0.0, 7.1);

        m_path.addEasePoint( 0.0, 0.0 );
        m_path.addEasePoint( 3.0, 1.0 );


        setPath( m_path );
    }

}
