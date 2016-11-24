package org.team2471;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Talon;

public class SandboxBot extends IterativeRobot {
  private Talon motor0;
  private Talon motor1;
  private Talon motor2;
  private Talon motor3;
  private Talon motor4;
  private Talon motor5;
  private Talon motor6;
  private Talon motor7;
  private Talon motor8;

  @Override
  public void robotInit() {
    motor0 = new Talon(0);
    motor1 = new Talon(1);
    motor2 = new Talon(2);
    motor3 = new Talon(3);
    motor4 = new Talon(4);
    motor5 = new Talon(5);
    motor6 = new Talon(6);
    motor7 = new Talon(7);
    motor8 = new Talon(8);
  }

  @Override
  public void teleopPeriodic() {
    motor0.set(0.5);
    motor1.set(0.5);
    motor2.set(0.5);
    motor3.set(0.5);
    motor4.set(0.5);
    motor5.set(0.5);
    motor6.set(0.5);
    motor7.set(0.5);
    motor8.set(0.5);
  }
}
