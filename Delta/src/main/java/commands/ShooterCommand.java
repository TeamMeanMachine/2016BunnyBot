package commands;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;
import static org.team2471.bunnybot.Robot.shooter;
/**
 * Created by Bob on 12/10/2016.
 */
public class ShooterCommand extends Command {
  double startTime;

  @Override
  protected void initialize() {
    startTime = Utility.getFPGATime();
    System.out.println("Init");
  }

  @Override
  protected void execute() {
    shooter.shoot();
    System.out.println("executed");
  }

  @Override
  protected boolean isFinished() {
    System.out.println((Utility.getFPGATime() - startTime)/1.0e6);
    return (Utility.getFPGATime() - startTime) / 1.0e6 > 0.25;
  }

  @Override
  protected void end() {
    shooter.stop();
    System.out.println("end");
  }

  @Override
  protected void interrupted() {
    end();
  }
}
