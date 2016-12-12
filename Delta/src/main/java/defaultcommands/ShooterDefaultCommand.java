package defaultcommands;

import edu.wpi.first.wpilibj.command.Command;
import static org.team2471.bunnybot.Robot.shooter;
/**
 * Created by Bob on 12/10/2016.
 */
public class ShooterDefaultCommand extends Command {

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    shooter.shoot();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    shooter.stop();
  }

  @Override
  protected void interrupted() {

  }
}
