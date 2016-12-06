package org.team2471.bunnybot.defaultcommands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import org.team2471.bunnybot.Robot;
import org.team2471.bunnybot.subsystems.Grabber;

import static org.team2471.bunnybot.IOMap.grabberAxis;
import static org.team2471.bunnybot.IOMap.grabberIntakeButton;
import static org.team2471.bunnybot.IOMap.grabberSpitButton;
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
        grabber.setAngle(grabberAxis.get() * 45);

        if(grabberIntakeButton.get()) {
            grabber.suckIn();
        } else if(grabberSpitButton.get()) {
            grabber.spitOut();
        } else {
            grabber.stopIntake();
        }
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
