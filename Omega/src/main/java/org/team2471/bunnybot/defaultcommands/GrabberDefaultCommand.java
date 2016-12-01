package org.team2471.bunnybot.defaultcommands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import org.team2471.bunnybot.Robot;
import org.team2471.bunnybot.subsystems.Grabber;

import static org.team2471.bunnybot.IOMap.grabberArmAxis;
import static org.team2471.bunnybot.Robot.grabber;


public class GrabberDefaultCommand extends Command {
    public GrabberDefaultCommand() {
        requires(grabber);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
//        grabber.setAngle(grabber.getAngle() + grabberArmAxis.get() * 1.0);
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

    }
}
