package org.team2471.bunnybot.defaultcommands;

import edu.wpi.first.wpilibj.command.Command;
import org.team2471.bunnybot.IOMap;
import org.team2471.bunnybot.subsystems.DriveTrain;

public class DriveTrainDefaultCommand extends Command {
    private DriveTrain driveTrain = new DriveTrain();

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        driveTrain.drive(IOMap.throttleAxis.get(), IOMap.turnAxis.get()*70.0);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {

    }

    @Override
    protected void interrupted() {
        end();
    }
}
