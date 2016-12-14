package org.team2471.bunnybot.commands;

import org.team2471.bunnybot.HardwareMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static org.team2471.bunnybot.Robot.grabber;

public class GrabberToAngleCommand extends Command {
  private final double targetAngle;

  public GrabberToAngleCommand(double angle) {
    requires(grabber);
    this.targetAngle = angle;
  }

  @Override
  protected void initialize() {
    grabber.setSetpoint(targetAngle);
  }

  @Override
  protected void execute() {
    System.out.println("Delta: " + (grabber.getSetpoint() - grabber.getAngle()) + ". Voltage: " + HardwareMap.GrabberMap.armMotor.get());

  }

  @Override
  protected boolean isFinished() {
    double delta = Math.abs(targetAngle - grabber.getAngle());
    SmartDashboard.putNumber("Grabber Delta", delta);
    SmartDashboard.putNumber("Grabber Angle", grabber.getAngle());
    SmartDashboard.putNumber("Grabber Setpoint", grabber.getSetpoint());
    return delta < 1;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
