package org.team2471.bunnybot.defaultcommands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import org.team2471.bunnybot.subsystems.Shooter;

public class ShooterDefaultCommand extends Command {
    private Joystick coStick = new Joystick(1); // temporary
    private Shooter shooter = new Shooter(); // temporary

    public ShooterDefaultCommand(){
        requires(shooter);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        double angle = Math.atan2(coStick.getRawAxis(0), coStick.getRawAxis(1));
        shooter.setAngle(angle);
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
