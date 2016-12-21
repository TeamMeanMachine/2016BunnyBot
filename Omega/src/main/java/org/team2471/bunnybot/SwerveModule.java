package org.team2471.bunnybot;

import org.team2471.bunnybot.sensors.Magnepot;
import org.team2471.frc.lib.vector.Vector2;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import org.team2471.frc.lib.sensors.Magnepot;

public class SwerveModule {
  private final SpeedController steerMotor;
  private final SpeedController driveMotor;
  private final Magnepot steerEncoder;
  private final PIDController steerController;
  private final Vector2 position;
  private double offset = 0;
  private double m_power = 0;

  public SwerveModule(SpeedController driveMotor, SpeedController steerMotor, Magnepot steerEncoder, Vector2 position, double offset) {
    this.steerMotor = steerMotor;
    this.driveMotor = driveMotor;
    this.steerEncoder = steerEncoder;
    this.position = position;
    this.offset = offset;
    steerController = new PIDController(-0.02, -0, -0.01, steerEncoder, steerMotor);
    steerController.enable();
    steerController.setInputRange(-180, 180);
    steerController.setContinuous();
    SmartDashboard.putData("Steer Controller", steerController);
  }

  public void drive(double drivePower, double steerAngle) {
    setAngle(steerAngle);
    driveMotor.set(drivePower);
  }

  public double getPower(double throttle, double steering) {
    Vector2 forwardVector = new Vector2(0, throttle);
    Vector2 delta = Vector2.subtract(position, Robot.driveTrain.getPivot());
    Vector2 turnVector = Vector2.normalize(Vector2.perpendicular(delta));

    turnVector = Vector2.multiply(turnVector, -steering);
    Vector2 sumVector = Vector2.add(forwardVector, turnVector);
    m_power = Vector2.length(sumVector);

    double currentAngle = steerEncoder.pidGet();
    double desiredAngle = Math.toDegrees(Vector2.angle(sumVector));

    desiredAngle = refineAngle(desiredAngle, currentAngle);

    setAngle(desiredAngle);
    return m_power;
  }

  public void setFactor(double factor) {
    driveMotor.set(-m_power * factor);
  }

  public double getOffset() {
    return offset;
  }

  public void setOffset(double offset) {
    this.offset = offset;
  }

  private void setAngle(double angle) {
    steerController.setSetpoint(angle + offset);
  }

  double refineAngle(double desiredAngle, double currentAngle) {
    double delta = desiredAngle - currentAngle;
    if (delta > 180) {
      delta = delta - 360;
    } else if (delta < -180) {
      delta = delta + 360;
    }

    if (delta > 90) {
      delta = delta - 180;
      desiredAngle = currentAngle + delta;
      m_power = -m_power;
    } else if (delta < -90) {
      delta = delta + 180;
      desiredAngle = currentAngle + delta;
      m_power = -m_power;
    } else
      desiredAngle = currentAngle + delta;

    return desiredAngle;
  }
}
