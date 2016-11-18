package org.team2471.bunnybot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import org.team2471.bunnybot.HardwareMap;
import org.team2471.bunnybot.IOMap;
import org.team2471.bunnybot.Robot;
import org.team2471.bunnybot.subsystems.Shooter;

/**
 * Created by GuGU on 11/16/2016.
 */
public class ShooterZeroCommand extends Command {
    private Shooter shooter = new Shooter();

    public ShooterZeroCommand() {
        requires(Robot.shooter);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
       shooter.zeroAngle();
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
