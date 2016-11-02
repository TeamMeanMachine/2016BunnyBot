package org.team2471.bunnybot.defaultcommands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import org.team2471.bunnybot.Robot;
import org.team2471.bunnybot.subsystems.Grabber;


public class GrabberDefaultCommand extends Command {
    private Joystick joystick1 = new Joystick(1);
    private Grabber grabber = Robot.grabber;

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        grabber.setAngle(grabber.getAngle() + joystick1.getRawAxis(1) * 1.0);

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
